/* Copyright 2008 Misys PLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License. 
 */
package com.misyshealthcare.connect.ihe;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.XmlUtil;

/**
 * This class executes a client SOAP request to a remote
 * server.  This class uses the ATNA 
 * secure/insecure connection library.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 1, 2005
 */
public class HttpSoapChannel {
	
	/* Logger for problems during SOAP exchanges */
  private static final Logger log = Logger.getLogger(HttpSoapChannel.class.getName());

	/* The XML declaration required before the SOAP envelope */
	private final static String DEFAULT_XML_DECLARATION_START = "<?xml version=\"1.0\" charset=\"";
	private final static String DEFAULT_XML_DECLARATION_END = "\" ?>";
	
	/* The connection to make */
	private IConnectionDescription connection = null;
	
	/* A MESA logger, if there is one */
	private IMesaLogger mesaLogger = null;
	
	/**
	 * Create a new channel that will communicate with the system
	 * in a particular connection description.  
	 * 
	 * @param connection The connection that messages will be sent to
	 * @param logger The MESA logger that will get the response when it arrives
	 */
	public HttpSoapChannel(IConnectionDescription connection, IMesaLogger logger) {
		this.connection = connection;
		this.mesaLogger = logger;
	}
	
	/**
	 * Send a SOAP message along the channel via HTTP.
	 * 
	 * @param message The SOAP message to send
	 * @return The SOAP response
	 * @throws IOException When a communication problem occurs
	 * @throws SOAPException When the server responds with ill-formed SOAP
	 */
	public SOAPMessage sendMessage(SOAPMessage message) throws IOException, SOAPException {
		// Get an HttpClient
		HttpClient client = ConnectionFactory.getHttpConnection(connection);
		String urlPath = connection.getUrlPath();
		// Build the HTTP SOAP POST method
		PostMethod method = assembleSoapPost(message, urlPath);
		// Send it
		try {
      // Execute the method.
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {
        log.error("Http POST failed: " + method.getStatusLine());
      }
		} catch (HttpException e) {
			log.error("Http error sending SOAP message to \"" + connection.getDescription() + "\"", e);
			method.releaseConnection();
			throw e;
		} catch (IOException e) {
			log.error("IO error sending SOAP message to \"" + connection.getDescription() + "\"", e);
			method.releaseConnection();
			throw e;
		}
		// Read the reply
		SOAPMessage response = null;
		try {
			response = getSoapResponse(method);
		} catch (IOException e) {
			// Could not read the message
			method.releaseConnection();
			throw e;
		} catch (SOAPException e) {
			// The response was not well-formed SOAP
			method.releaseConnection();
			throw e;
		}
		// Close the connection
		method.releaseConnection();
		// Done
		return response;
	}
	
	/**
	 * Assemble a POST method containing the SOAP message.
	 * 
	 * @param message The message to send
	 * @param url The URL path pointing to the POST service
	 * @return
	 */
	private PostMethod assembleSoapPost(SOAPMessage message, String url) {
		PostMethod method = new PostMethod(url);
		// Add mime headers from the SOAP message
		MimeHeaders headers = message.getMimeHeaders();
		Iterator headerNames = headers.getAllHeaders();
		while (headerNames.hasNext()) {
			MimeHeader header = (MimeHeader) headerNames.next();
			method.setRequestHeader(header.getName(), header.getValue());
		}
		// Pour in the message
		try {
			addMessage(method, message);
		} catch (IOException e) {
			// Should never happen
			log.error("Can't add SOAP message to Http POST body", e);
			return null;
		} catch (SOAPException e) {
			// Again, should neven happen
			log.error("Can't add SOAP message to Http POST body", e);
			return null;
		}
		// Okay, ready to go
		return method;
	}
	
	/**
	 * Add the SOAP message itself to the POST method.
	 * 
	 * @param method The POST method
	 * @param message The SOAP message to add
	 * @throws IOException When there is a problem adding the message to the method
	 * @throws SOAPException When the SOAP message is not well-defined
	 */
	private void addMessage(PostMethod method, SOAPMessage message) throws IOException, SOAPException {
		//TODO There has to be a more efficient way to do this!
		// Get the output stream for saving the SOAP message
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		// Write out the XML header
		String writesXML = getMessageProperty(message, SOAPMessage.WRITE_XML_DECLARATION);
		if ((writesXML == null) || !writesXML.equalsIgnoreCase("true")) {
			String charset = getMessageProperty(message, SOAPMessage.CHARACTER_SET_ENCODING);
		  if (charset == null) charset = "utf-8";
			PrintStream output = new PrintStream(buffer);
			output.print(DEFAULT_XML_DECLARATION_START);
			output.print(charset);
			output.println(DEFAULT_XML_DECLARATION_END);
		}
		// Write out the SOAP message
		message.writeTo(buffer);
		// Okay, now put the buffer into the POST method
		method.setRequestBody(new ByteArrayInputStream(buffer.toByteArray()));
	}

	/**
	 * Read the SOAP response to the message sent by the POST method.
	 * 
	 * @param method The POST method holding the response
	 * @return The SOAP response as a SOAP message
	 * @throws IOException When there is a problem reading the message
	 * @throws SOAPException 
	 * @throws SOAPException When the response is not well-formed SOAP
	 */
	private SOAPMessage getSoapResponse(PostMethod method) throws IOException, SOAPException {
		// Get the input stream for the connection
		InputStream stream = method.getResponseBodyAsStream();
		// Read the response
		String response = readResponse(stream);
		// Close the stream
		stream.close();
		// Create a soap message from the stream
		MessageFactory factory = null;
		try {
		    factory = XmlUtil.newMessageFactoryInstance(XmlUtil.SunJDK6MessageFactory);
		} catch (SOAPException e) {
			// Cannot access SOAP implementation
			log.error("Cannot access SOAP implementation", e);
			throw e;
		}
		try {
            MimeHeaders mheaders = new MimeHeaders();
            Header[] respHeaders = method.getResponseHeaders();
            if (respHeaders != null) {
                for (Header header : respHeaders) {
                    mheaders.addHeader( header.getName(), header.getValue() );
                }
            }
            byte[] responseBytes = response.getBytes();
            SOAPMessage result = factory.createMessage(mheaders, new ByteArrayInputStream(responseBytes));
			// Okay, it worked.  Now create a new result and log it.  We need to do this because some
			//  SOAP implementations, like Axiom, make it impossible to turn a response into text and
			//  then still use it as a structure.  Some sort of caching issue. 
			// Move this at your own peril!
			if ((mesaLogger != null) && (result != null)) {
				SOAPMessage loggingResult = factory.createMessage(mheaders, new ByteArrayInputStream(responseBytes));
				try {
					// Change something to force a reformating
					result.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "false");
					result.saveChanges();
				} catch (SOAPException e) {
					// This should never happen
					log.error("Can't reformat SOAP result", e);
				}
				mesaLogger.writeString("Received response ...");
				mesaLogger.writeSoapMessage(loggingResult);
			}
			// Done
			return result;
		} catch (SOAPException e) {
			// Cannot parse the SOAP response
			log.error("Invalid SOAP response");
			log.error(response);
			log.error("SOAP server \"" + connection.getDescription() + "\" responded with ill-formed SOAP", e);
			throw e;
		}
	}

	/**
	 * Get a SOAP message property without causing an exception
	 * 
	 * @param message The message to get the property from
	 * @param property The property name
	 * @return The value of the property, or null
	 */
	private String getMessageProperty(SOAPMessage message, String property) {
		String value = null;
		try {
			value = (String) message.getProperty(property);
		} catch (SOAPException e) {
			log.error("Can't get SOAP message property", e);
			value = null;
		}
		return value;
	}

	/**
	 * Read the entire response from the HTTP POST as a string.
	 * The SOAP message factory does not seem to be able to do this
	 * without choking when characters are delayed.
	 * 
	 * @param stream The input stream to read the response from
	 * @return The response as a string
	 */
	private String readResponse(InputStream stream) {
		BufferedReader input = new BufferedReader(new InputStreamReader(stream));
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			line = input.readLine();
		} catch (IOException e) {
			line = null;
		}
		while (line != null) {
			if (!line.trim().equals("")) {
				if (sb.length() > 0) sb.append("\n");
				sb.append(line);
			}
			try {
				line = input.readLine();
			} catch (IOException e) {
				line = null;
			}
		}
		return sb.toString();
	}

}
