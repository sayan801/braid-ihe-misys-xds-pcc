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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.MailConnection;
import com.misyshealthcare.connect.util.EmailException;
import com.misyshealthcare.connect.util.EmailSender;

/**
 * This class executes a client SOAP request to a remote
 * server via SMTP offline mode.
 * 
 * DO NOT USE, NOT YET FINISHED!!!
 * 
 * @author Josh Flachsbart
 * @version 1.0 - Dec 1, 2006
 */
public class SmtpSoapChannel {
	
	/* Logger for problems during SOAP exchanges */
	private static final Logger log = Logger.getLogger(SmtpSoapChannel.class.getName());

	/* The XML declaration required before the SOAP envelope */
	private final static String DEFAULT_XML_DECLARATION_START = "<?xml version=\"1.0\" charset=\"";
	private final static String DEFAULT_XML_DECLARATION_END = "\" ?>";
	private final static String DEFAULT_XDR_SUBJECT = "XDS/1.0/PnR";
	private final static String DEFAULT_XDR_MIME_TYPE = "multipart/related";
	private final static String DEFAULT_XDR_ACTION_NAME = "SOAPAction";
	private final static String DEFAULT_XDR_ACTION = "ebXML";
	
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
	public SmtpSoapChannel(IConnectionDescription connection, IMesaLogger logger) {
		this.connection = connection;
		this.mesaLogger = logger;
	}
	
	public List<SOAPMessage> getMessages() throws MessagingException, IOException, SOAPException {
		List<SOAPMessage> soapMessages = new ArrayList<SOAPMessage>();
		MailConnection mailConnection = ConnectionFactory.getMailConnection(connection);
		Message[] messages = mailConnection.retrieveAllMessages();
		for (Message message: messages) {
			SOAPMessage soapMessage = null;
			String subject = message.getSubject();
			if (message.isMimeType("multipart/related") && subject != null && subject.startsWith(DEFAULT_XDR_SUBJECT)) {
				log.info("Found SOAP message from: " + message.getFrom()[0] + " : " + message.getSubject());
				soapMessage = getSoapResponse(message, false, false);
			} else if (message.isMimeType("application/pkcs7-mime") && subject != null && subject.startsWith(DEFAULT_XDR_SUBJECT)) {
				log.info("Found encrypted SOAP message from: " + message.getFrom()[0] + " : " + message.getSubject());
				soapMessage = getSoapResponse(message, true, false);
			} else if (message.isMimeType("multipart/signed") && subject != null && subject.startsWith(DEFAULT_XDR_SUBJECT)) {
				log.info("Found signed SOAP message from: " + message.getFrom()[0] + " : " + message.getSubject());
				soapMessage = getSoapResponse(message, false, true);
			} else {
				log.info("Non ebXML message found in inbox.");
			}
			if (soapMessage != null) soapMessages.add(soapMessage);
		}
		mailConnection.finishedWithMessages();
		return soapMessages;
	}
	
	/**
	 * Send a SOAP message along the channel via SMTP.  Since this is asynchronous, don't deal with responses yet.
	 * 
	 * @param message The SOAP message to send
	 * @throws IOException When a communication problem occurs
	 * @throws SOAPException When the server responds with ill-formed SOAP
	 */
	public void sendMessage(SOAPMessage message) throws IOException, SOAPException, MessagingException {
		MailConnection mailConnection = ConnectionFactory.getMailConnection(connection);
	
		String from = connection.getProperty("OfflineFromAddress");
		String fromName = connection.getProperty("OfflineFromName");
		String to = connection.getProperty("OfflineToAddress");
		String toName = connection.getProperty("OfflineToName");
		String recipientCertFile = connection.getProperty("recipientCert");
		boolean noSubmit = "true".equalsIgnoreCase(connection.getProperty("DoNotSubmit"));

		// Get blank message:
		Message mailMessage = mailConnection.getNewMessage();
		
		// Add endpoints:
		mailMessage.setFrom(new InternetAddress(from, fromName));
		mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to, toName));
		// Add subject: 
		mailMessage.setSubject(DEFAULT_XDR_SUBJECT);
		mailMessage.addHeader(DEFAULT_XDR_ACTION_NAME, '"' + DEFAULT_XDR_ACTION + '"');
		
		// Create the message part, to hold each mime part:
		BodyPart messageBodyPart = new MimeBodyPart();
		String contentId = "<aaaa>";
		Multipart multipart = new MultipartRelated(contentId);
		// Fill it:
		// First part is body:
		messageBodyPart.setContent(XdrMessenger.serializeXml(message.getSOAPPart().getEnvelope()), "text/xml");
		messageBodyPart.addHeader("Content-Id", contentId);
		multipart.addBodyPart(messageBodyPart);
		// Second (etc.) parts are attachments:
		Iterator attachmentParts = message.getAttachments();
		while (attachmentParts.hasNext()) {
			AttachmentPart attachmentPart = (AttachmentPart) attachmentParts.next();
			DataHandler handler = attachmentPart.getDataHandler();
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(handler);
			Iterator headers = attachmentPart.getAllMimeHeaders();
			//MimeHeaders headers = message.getMimeHeaders();
			//Iterator headerNames = headers.getAllHeaders();
			while (headers.hasNext()) {
				MimeHeader header = (MimeHeader) headers.next();
				String headerName = header.getName();
				if (headerName.equalsIgnoreCase("Name")) messageBodyPart.setFileName(header.getValue());
				else if (headerName.equalsIgnoreCase("Filename")) messageBodyPart.setFileName(header.getValue());
				else messageBodyPart.addHeader(headerName, header.getValue());
			}
			multipart.addBodyPart(messageBodyPart);
		}
		// Put it all together:
		// for plain text messages only, no mime multiplart.
		//mailMessage.setContent("Hello", "text/plain");
		mailMessage.setContent(multipart);
		mailMessage.saveChanges(); // implicit with send()
		boolean isEncrypted = recipientCertFile != null;
		EmailSender emailSender = null;
		if (isEncrypted) {
			emailSender = new EmailSender(mailConnection);
			emailSender.setToAddress(to);
			emailSender.setRecipientCertFile(recipientCertFile);
			emailSender.setSecure(true);
			emailSender.setMessage(mailMessage);
		}

		try {
			if (!noSubmit) {
				if (isEncrypted) {
					Message encryptedMessage = emailSender.getEncryptedMessage();
					mailConnection.sendMessage(encryptedMessage);
				} else {
					mailConnection.sendMessage(mailMessage);
				}
			}
		} catch (EmailException e) {
			throw new MessagingException(e.getMessage());
		}
		
	}
	
	/**
	 * Assemble a POST method containing the SOAP message.
	 * 
	 * @param message The message to send
	 * @param url The URL path pointing to the POST service
	 * @return
	 *
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
	 *
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
	 * @param encrypted Whether or not the message is encrypted.
	 * @return The SOAP response as a SOAP message
	 * @throws IOException When there is a problem reading the message
	 * @throws SOAPException 
	 * @throws SOAPException When the response is not well-formed SOAP
	 */
	private SOAPMessage getSoapResponse(Message message, boolean isEncrypted, boolean isSigned) throws IOException, SOAPException, MessagingException {
		MailConnection mailConnection = ConnectionFactory.getMailConnection(connection);
		
		// Make sure this is a SOAP message
//		String actionHeaders[] = message.getHeader(DEFAULT_XDR_ACTION_NAME);
//		boolean isEbXmlMessage = false;
//		CharSequence defaultAction = DEFAULT_XDR_ACTION.subSequence(0, DEFAULT_XDR_ACTION.length() - 1);
//		if (actionHeaders != null) {
//			for (String actionHeader: actionHeaders) {
//				if (actionHeader.contains(defaultAction)) isEbXmlMessage = true;
//			}
//		}
//		if (!isEbXmlMessage) {
//			log.info("Non ebXML message found in XDR inbox.");
//			return null;
//		}
		
		// Get the input stream for the connection
		InputStream stream = null;
		Enumeration respHeaders = null;
		if (isEncrypted) {
			MimeBodyPart body = mailConnection.decryptMessage(message);
			if (body.isMimeType("multipart/signed")) {
				log.info("----AND SIGNED!");
				Properties props = System.getProperties();
				Session session = Session.getDefaultInstance(props, null);
				MimeMessage mm = new MimeMessage(session);
				mm.setContent(body.getContent(), body.getContentType());
				mm.saveChanges();
				body = mailConnection.unSignMessage(mm);
			}
			respHeaders = body.getAllHeaders();
			stream = body.getInputStream();
		//	Enumeration headers = body.getAllHeaderLines();
		//	System.out.println("Encrypted headers:");
		//	while (headers.hasMoreElements()) System.out.println(headers.nextElement());
		//	Object content = body.getContent();
		//	BufferedReader in = new BufferedReader(new InputStreamReader(body.getInputStream()));
		//	String streamS = new String();
		//	String lineS = in.readLine();
		//	while (lineS != null) {
		//		streamS += lineS + "\n";
		//		lineS = in.readLine();
		//	}
		//	System.out.println("-------------Encrypted stream:");
		//	System.out.println(streamS);
		//	System.out.println(content);
		//	if (content instanceof MimeMultipart) {
		//		MimeMultipart mm = (MimeMultipart) content;
		//		for (int i = 0; i < mm.getCount(); i++) {
		//			System.out.println("Body part "+ i + ": ");
		//			System.out.println(mm.getBodyPart(i).getContent());
		//		}
		//	}
		//	else return null;
		} else if (isSigned) {
			MimeBodyPart body = mailConnection.unSignMessage(message);
			respHeaders = body.getAllHeaders();
			stream = body.getInputStream();			
		} else {
			stream = message.getInputStream();
			respHeaders = message.getAllHeaders();
		//	BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		//	String streamS = new String();
		//	String lineS = in.readLine();
		//	while (lineS != null) {
		//		streamS += lineS + "\n";
		//		lineS = in.readLine();
		//	}
		//	System.out.println("-------------Unencrypted stream:");
		//	System.out.println(streamS);
		}
		
		// Read the response
		//String response = readResponse(stream);
		// Create a soap message from the stream
		MessageFactory factory = null;
		try {
			factory = MessageFactory.newInstance();
		} catch (SOAPException e) {
			// Cannot access SOAP implementation
			log.error("Cannot access SOAP implementation", e);
			throw e;
		}
		try {
			MimeHeaders mheaders = new MimeHeaders();
			while (respHeaders.hasMoreElements()) {
				Header header = (Header) respHeaders.nextElement();
				String name = header.getName();
				String value = header.getValue();
				mheaders.addHeader( name, value );
			}
			//byte[] responseBytes = response.getBytes();
			//SOAPMessage result = factory.createMessage(mheaders, new ByteArrayInputStream(responseBytes));
			SOAPMessage result = factory.createMessage(mheaders, stream);
			// Okay, it worked.  Now create a new result and log it.  We need to do this because some
			//  SOAP implementations, like Axiom, make it impossible to turn a response into text and
			//  then still use it as a structure.  Some sort of caching issue. 
			// Move this at your own peril!
			if ((mesaLogger != null) && (result != null)) {
		//		SOAPMessage loggingResult = factory.createMessage(mheaders, new ByteArrayInputStream(responseBytes));
				try {
					// Change something to force a reformating
					result.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "false");
					result.saveChanges();
				} catch (SOAPException e) {
					// This should never happen
					log.error("Can't reformat SOAP result", e);
				}
				mesaLogger.writeString("Received response ...");
				//mesaLogger.writeSoapMessage(loggingResult);
				mesaLogger.writeSoapMessage(result);
			}
			// Done
			return result;
		} catch (SOAPException e) {
			// Cannot parse the SOAP response
			log.error("Invalid SOAP response");
	//		log.error(response);
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
	
	class MultipartRelated extends MimeMultipart {
		String startCid;
		MultipartRelated(String start) {
			super("related");
			startCid = start;
		}
		
		public String getContentType() {
			String contentType = super.getContentType();
			String start = contentType.substring(0, contentType.indexOf(";"));
			String middle = "; type=\"text/xml\"; start=\"" + startCid + "\"; ";
			String end = contentType.substring(contentType.lastIndexOf("boundary"));
			return start + middle + end;
		}
	}

	public static void main(String [] argv){
		SmtpSoapChannel s = new SmtpSoapChannel(null, null);
		MultipartRelated mmr = s.new MultipartRelated("<AABBCC>");
		System.out.println(mmr.getContentType());
	}
}
