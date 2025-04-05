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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.transform.stream.StreamSource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.description.WSDL2Constants;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.DocumentRequest;
import com.misyshealthcare.connect.base.IDocumentContents;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsResponse;
import com.misyshealthcare.connect.ihe.registry.XdsRimException;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
import com.misyshealthcare.connect.ihe.registry.XmlAxiomRegistry;
import com.misyshealthcare.connect.ihe.registry.XmlErrorResponse;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.SecureConnectionDescription;
import com.misyshealthcare.connect.net.SecureSocketFactory;

/**
 * This class turns IHE XDS Registry/Repository requests into 
 * SOAP messages, sends them, and then extracts the response
 * data from the SOAP wrappers.
 * 
 * @author Wenzhi Li
 * @version 2.1  Jan 12, 2008
 */
public class XdsbMessenger {
	
	/* The logger for error and debugging messages */
	private final static Logger log = Logger.getLogger(XdsbMessenger.class.getName());
	
    private static final String XDS_REGISTRY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0";
    private static final String XDS_QUERY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0";

	private static final String DEFAULT_SOAP_CHARSET = "utf-8";
	private static final String DEFAULT_SOAP_MIME_TYPE = "text/xml";
	private static final String DEFAULT_SOAP_ACTION = "\"\"";
  
	/* The connection description of the registry/repository */
	private IConnectionDescription connection = null;
	/* A utility object for encoding and decoding data */
    private XmlAxiomRegistry registry = null;
    /*Whether to use of ebXml registry version 3 or older version such as 2.1 */

  public static enum ResponseType {
  	INVALID_RESPONSE,               // Not a well-formed SOAP response
  	FAULT_RESPONSE,                 // A SOAP fault is contained in the response
  	INCOMPLETE_RESPONSE,                 // The SOAP body of the response is empty
  	OFF_PROTOCOL_RESPONSE,          // The response contains too many parts
    REGISTRY_RESPONSE,              // A registry message
    RETRIEVE_DOCUMENT_SET_RESPONSE, // A retrieving document set response message
    ADHOC_QUERY_RESPONSE,           // An adhoc query message used by ebQuery 3.0
  	UNEXPECTED_RESPONSE             // A valid but unexpected response
  }
  
  /**
   * Create a new messenger object for communicating with a
   * registry/repository.
   * 
   * @param description The connection to the registry/repository
   */
  XdsbMessenger(IConnectionDescription description) {
  	registry = new XmlAxiomRegistry();
  	connection = description;
  }
	
	
	/**
	 * Create a SubmitObjectsRequest holding a submission set, document metadata, and document contents.
	 * 
	 * @param submissionSet The submission set metadata describing the submitted object
	 * @param documentEntries A collection of document metadata descriptions to submit in this set
	 * @return The complete SOAP message for the Submit Objects Request
	 * @throws DocumentException When the content for a document cannot be determined
	 */
	public OMElement createSubmitObjectsRequest(XdsSubmissionSet submissionSet, List<XdsDocumentEntry> documentEntries) throws DocumentException {
		OMElement request = null;
		try {
			// Add the metadata to the message
		    request = registry.createDocumentMetadata(submissionSet, documentEntries, connection);
			// Now, add the actual documents as attachment
			if (documentEntries != null) {
				for (XdsDocumentEntry doc: documentEntries) {
					OMFactory fac = OMAbstractFactory.getOMFactory();
					OMNamespace ns = fac.createOMNamespace("urn:ihe:iti:xds-b:2007" , null);
					OMElement document = fac.createOMElement("Document", ns);
					document.addAttribute("id", doc.getEntryUuid(), null);

					Object content = doc.getContent();
					DataHandler handler = null;
					if (content == null) {
						throw new DocumentException("Cannot submit document with no content");
					} else if (content instanceof URL) {
						// If the content object is a URL then send what it points to
                        URL url = (URL)content;
                        DataSource ds = new ByteArrayDataSource(new BufferedInputStream(new FileInputStream( url.getFile())), doc.getMimeType());
                        handler = new DataHandler(ds);
					} else if (content instanceof IDocumentContents) {
						// One of our custom objects, just send the content
                        StreamSource source = new StreamSource(((IDocumentContents) content).getDocumentContentStream());
                        handler = new DataHandler(source, doc.getMimeType());
					} else if (content instanceof String) {
                        // A string, turn it into an StreamSource
					    DataSource ds = new ByteArrayDataSource((String)content, doc.getMimeType()); 
					    handler = new DataHandler(ds);
					} else {
						// Whatever it is, just pass it along
                        handler = new DataHandler(doc.getContent(), doc.getMimeType());
					}
                    OMText binaryData = fac.createOMText(handler, true);
                    document.addChild(binaryData);

                    request.addChild( document );
                }
			}
		}  catch (XdsRimException e) {
			// Invalid metadata
			log.error("Invalid metadata passed to SubmitObjectsRequest", e);
	        throw new DocumentException("Invalid metadata passed to SubmitObjectsRequest:" + e.getMessage(), e);
		}  catch (IOException e) {
            log.error("Cannot read from URL", e);
            throw new DocumentException("Cannot read from URL" + e.getMessage(), e);
        }
		// Done
		return request;
	}
	
	public OMElement createRetrieveDocumentSetRequest(List<DocumentRequest> docRequests) throws DocumentException {
		 return registry.createRetrieveDocumentSetRequest( docRequests );		  
	}

	/**
	 * Get the type of response that this SOAP registry/repository
	 * response message represents.
	 * 
	 * @param response The SOAP registry response
	 * @return The response type
	 */
	public ResponseType getResponseType(OMElement response) {
		// Extract the SOAPBody, if we can
		if (response == null) return ResponseType.INVALID_RESPONSE;
		// See if its empty
		if (!response.isComplete()) return ResponseType.INCOMPLETE_RESPONSE;
		
		if(response.getLocalName().equals("RegistryResponse")) {
            return ResponseType.REGISTRY_RESPONSE;
		}
		if(response.getLocalName().equals("RetrieveDocumentSetResponse")) {
			return ResponseType.RETRIEVE_DOCUMENT_SET_RESPONSE;
		}
        // Not a registry response
		return ResponseType.UNEXPECTED_RESPONSE;
	}
	
    /**
     * Get the ebXML registry response out of a SOAP message response.
     *
     * @param message The SOAP message
     * @return The registry response object
     */
    XdsResponse getXdsResponse(OMElement message) {
        ResponseType responseType = getResponseType(message);
        // Make sure that we got a response from the registry
        if (responseType == ResponseType.REGISTRY_RESPONSE || 
            responseType == ResponseType.RETRIEVE_DOCUMENT_SET_RESPONSE) {
            // The message holds a valid response
            XdsResponse response = extractXdsResponse(responseType, message);
            if (response == null ) {
                // Something is malformed with respect to the response
                response = new XmlErrorResponse("Unexpected XDS response message type from \"" + connection.getDescription() + "\"");
            }
            return response;
        } else {
            // Some sort of bad response
            String description  = null; //getResponseMessage(message);
            return new XmlErrorResponse(description);
        }
    }
    
	/**
	 * Extract a registry response object from a message.
	 * 
	 * @param responseType The type of response
	 * @param message The message holding the response
	 * @return The response as a Java object
	 */
	public XdsResponse extractXdsResponse(ResponseType responseType, OMElement message) {
		if (message == null) return null;
		// Pull out the response
        if (responseType == ResponseType.REGISTRY_RESPONSE)
        	return registry.getRegistryResponse(message);
        else if (responseType == ResponseType.RETRIEVE_DOCUMENT_SET_RESPONSE)
        	return registry.getRetrieveDocumentSetResponse(message);
        else
        	return null;
	}
	
	/**
	 * Send a SOAP message to the repository/registry.
	 * 
	 * @param message The message to be sent
	 * @param logger The MESA logger to catch the message and the response
	 * @param kind The type of SOAP message being sent
	 * @param doSendMessage If false, log the message but don't actually send it (for testing)
	 * @return The SOAP response to the message sent
	 */
	public OMElement send(OMElement message, IMesaLogger logger, String kind, boolean doSendMessage) {
		// Write the message to the MESA test log
		if (logger != null) {
			// MESA output capture enable
			logger.writeString("Sending document " + kind + " request to '" + getConnectionString() + "' ...");
		 	logger.writeAxiomElementMessage(message);
			logger.writeString("Connection " +getConnectionDescription());
		}
		// If just testing bail out now
		if (!doSendMessage) {
			log.warn("Sending SOAP message is inhibited for testing purposes.");
			return null;
		}
		
		Options options = new Options();
		if (kind.equals("submission"))
			options.setAction("urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b");
		else //must be retrieving
 			options.setAction("urn:ihe:iti:2007:RetrieveDocumentSet");
		
	    options.setProperty(WSDL2Constants.ATTRIBUTE_MUST_UNDERSTAND,"1");
	    options.setTo( getUrl() );
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
		//use SOAP12, 
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
//		options.setMessageId("id");
//		options.setReplyTo(replyTo);
		try{
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			sender.engageModule("addressing");			

			OMElement response = sender.sendReceive( message );
			// Write the response message to the MESA test log
			if (logger != null) {
				// MESA output capture enable
				logger.writeString("Receiving document " + kind + " from '" + getConnectionString() + "' ...");
			 	logger.writeAxiomElementMessage(response);
			}
			return response;
		} catch(AxisFault e) {
			log.error("XDS.b repository responded with ill-formed SOAP \"" + connection.getDescription() + "\"", e);
			return null;			
		}
	}
	/**
	 * Get the Endpoint of this SOAP message.
	 * 
	 * @return the URL of the web service
	 */
	private EndpointReference getUrl() {
		String host = connection.getHostname();
		int port = connection.getPort();
		boolean isSecure = connection.isSecure();
		String url = "http://";
		if(isSecure) {
			url="https://";
			//TODO: handle protocol multiple invocations
			Protocol.registerProtocol("https", new Protocol("https", 
			  new SecureSocketFactory((SecureConnectionDescription)connection), connection.getPort()));
		}
		 
		url+= host + ":" + port + connection.getUrlPath();
		return new EndpointReference(url);
	}
	
	/**
	 * Get a string that gives a 'rough' description of the target
	 * of this connection.  The description is encoded as a URL and
	 * 'https' means it will be a TLS connection, which is not actually
	 * an https SSL connection.
	 * 
	 * @return A description of this connection as a URL
	 */
	public String getConnectionString() {
		StringBuffer sb = new StringBuffer();
		if (connection.isSecure()) {
			sb.append("https:");
		} else {
			sb.append("http:");
		}
		sb.append("//");
		sb.append(connection.getHostname());
		sb.append(":");
		sb.append(connection.getPort());
		sb.append(connection.getUrlPath());
		return sb.toString();
	}

	/**
	 * Get a description of the target of this connection as a
	 * text string that includes the host, port, and path (if any).
	 * This description does not look like a url.
	 * 
	 * @return A description of the target of this connection
	 */
	public String getConnectionDescription() {
		StringBuffer sb = new StringBuffer();
		if (connection.isSecure()) sb.append("(TLS) ");
		sb.append("host:");
		sb.append(connection.getHostname());
		sb.append(" - port:");
		sb.append(connection.getPort());
		sb.append(" - path:");
		sb.append(connection.getUrlPath());
		return sb.toString();
	}

//	/* This just doesn't seem to work */
//	private SOAPMessage sendViaSaaj(SOAPMessage message, String target) {
//		try {
//			SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
//			SOAPConnection connection = factory.createConnection();
//			SOAPMessage result = connection.call(message, target);
//			return result;
//		} catch (UnsupportedOperationException e) {
//			// Cant use SOAP connections
//			log.error("No SOAP connection implementation", e);
//			return null;
//		} catch (SOAPException e) {
//			// Something bad happened
//			log.error("Can't send SOAP message to " + target, e);
//			return null;
//		}
//		
//	}
	
//	/**
//	 * Test program to produce metadata to validate against the NIST repository.
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// Create a sample connection object
//		StandardConnectionDescription conn = new StandardConnectionDescription();
//		conn.setHostname("Fred");
//		conn.loadConfiguration("testCodes.xml");
//		// Create a sample document entry
//		XdsDocumentEntry doc = new XdsDocumentEntry();
//		doc.setTitle("Test Metadata Document Entry");
//		doc.setAuthorInstitution("Misys Healthcare");
//		Vector<String> authors = new Vector<String>();
//		authors.add("Joe^Mary^^^");
//		doc.setAuthorPersons(authors);
//		doc.setCreationTime("20051112");
//		doc.setLanguageCode("en-ca");
//		doc.setLegalAuthenticator("Mayor^The^^^");
//		doc.setServiceStartTime("20051112");
//		doc.setServiceStopTime("20051112");
//		doc.setSourcePatientId("jfjfjf^^^jfjfjf");
//		Vector<String> sourceInfo = new Vector<String>();
//		sourceInfo.add("PID-3|pid1^^^domain");
//		sourceInfo.add("PID-5|Doe^John^^^");
//		sourceInfo.add("PID-7|19560527");
//		sourceInfo.add("PID-8|M");
//		sourceInfo.add("PID-11|100 Main St^^Metropolis^Il^44130^USA");
//		doc.setSourcePatientInfo(sourceInfo);
//		doc.setClassCode("Education");
//		doc.setFormatCode("CDA/IHE 1.0");
//		doc.setPracticeSettingCode("Dialysis");
//		doc.setHealthcareFacilityTypeCode("Home");
//		doc.setTypeCode("11488-4");
//		doc.setConfidentialityCode("N");
//		doc.setPatientId("TEST-1^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
//		doc.setUniqueId("123.345.567");
//		doc.setMimeType("text/plain");
//		doc.setEntryUuid("doc1");
//		doc.setContent("This is the document content.");
//		// Now create a sample submission set
//		XdsSubmissionSet set = new XdsSubmissionSet();
//		set.setComments("Test Submission Set");
//		set.setAuthorInstitution("Misys Healthcare");
//		Vector<String> authors2 = new Vector<String>();
//		authors2.add("Joe^Mary^^^");
//		authors2.add("Joe^Betty^^^");
//		set.setAuthorPersons(authors2);
//		set.setSubmissionTime("20051112");
//		set.setContentTypeCode("Admission evaluation");
//		set.setPatientId("TEST-1^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
//		set.setUniqueId("123.345.568");
//		set.setSourceId("Debugging Code");
//		// Bundle it up
//		SOAPMessage message = null;
//		try {
//			ArrayList<XdsDocumentEntry> docs = new ArrayList<XdsDocumentEntry>();
//			docs.add(doc);
//			XdsMessenger me = new XdsMessenger(conn);
//			message = me.createSubmitObjectsRequest(set, docs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// Write it out
//		if (message != null) {
//			System.out.println("HTTP Headers ...");
//			MimeHeaders headers = message.getMimeHeaders();
//			Iterator headerNames = headers.getAllHeaders();
//			while (headerNames.hasNext()) {
//				MimeHeader header = (MimeHeader)headerNames.next();
//				System.out.println(header.getName() + ": " + header.getValue());
//			}
//			try {
//				System.out.println("SOAP Message ...");
//				message.writeTo(System.out);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		// Now try a query
//		try {
//			XdsMessenger me = new XdsMessenger(conn);
//			message = me.createQueryRequest("SELECT * FROM Everything;", "Big", "Small");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// Write it out
//		if (message != null) {
//			System.out.println("HTTP Headers ...");
//			MimeHeaders headers = message.getMimeHeaders();
//			Iterator headerNames = headers.getAllHeaders();
//			while (headerNames.hasNext()) {
//				MimeHeader header = (MimeHeader)headerNames.next();
//				System.out.println(header.getName() + ": " + header.getValue());
//			}
//			try {
//				System.out.println("SOAP Message ...");
//				message.writeTo(System.out);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

}
