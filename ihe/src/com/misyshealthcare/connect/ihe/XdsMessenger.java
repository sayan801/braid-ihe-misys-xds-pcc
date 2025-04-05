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
import java.io.StringReader;
import java.net.URL;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.base.IDocumentContents;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.QueryBuilder;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsResponse;
import com.misyshealthcare.connect.ihe.registry.XdsRimException;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
import com.misyshealthcare.connect.ihe.registry.XmlErrorResponse;
import com.misyshealthcare.connect.ihe.registry.XmlRegistry;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.XmlUtil;

/**
 * This class turns IHE XDS Registry/Repository requests into
 * SOAP messages, sends them, and then extracts the response
 * data from the SOAP warppers.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 3, 2005
 */
public class XdsMessenger {
	
	/* The logger for error and debugging messages */
	private final static Logger log = Logger.getLogger(XdsMessenger.class.getName());
	
    private static final String XDS_REGISTRY_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:registry:xsd:2.1";
    private static final String XDS_QUERY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0";

	private static final String DEFAULT_SOAP_CHARSET = "utf-8";
	private static final String DEFAULT_SOAP_MIME_TYPE = "text/xml";
	private static final String DEFAULT_SOAP_ACTION = "\"\"";
  
	/* The connection description of the registry/repository */
	private IConnectionDescription connection = null;
	/* A utility object for encoding and decoding data */
    private XmlRegistry registry = null;
    /*Whether to use of ebXml registry version 3 or older version such as 2.1 */
    private boolean isEbXmlV3 = true;

  public static enum ResponseType {
  	INVALID_RESPONSE,               // Not a well-formed SOAP response
  	FAULT_RESPONSE,                 // A SOAP fault is contained in the response
  	EMPTY_RESPONSE,                 // The SOAP body of the response is empty
  	OFF_PROTOCOL_RESPONSE,          // The response contains too many parts
  	TEXT_RESPONSE,                  // The SOAP body just includes text, typically an error
    REGISTRY_RESPONSE,              // A registry message
    ADHOC_QUERY_RESPONSE,           // An adhoc query message used by ebQuery 3.0
  	UNEXPECTED_RESPONSE             // A valid but unexpected response
  }
  
  /**
   * Create a new messenger object for communicating with a
   * registry/repository.
   * 
   * @param description The connection to the registry/repository
   */
  XdsMessenger(IConnectionDescription description, boolean useEbxmlV3) {
  	registry = new XmlRegistry();
  	connection = description;
    isEbXmlV3 = useEbxmlV3;
  }
	
  /**
   * Create a repository AdhocQueryRequest holding a SQL query
   * 
   * @param query The SQL query to execute on the repository/registry
   * @param returnType The return type desired from this query
   * @param returnObjects Return objects with the query or just IDs
   * @return The complete SOAP message for the query
   */
	public SOAPMessage createQueryRequest(DocumentQuery query, String patientId, String returnType, String returnObjects) throws IheConfigurationException {
		// Create an empty SOAP message
		SOAPMessage message = getEmptySoapMessage();
		if (message != null) {
			try {
				// Add the query to the message
                if(isEbXmlV3) {
                    //create registry stored query
                    registry.addAdhocQueryV3(message.getSOAPBody(), query, patientId, returnType, returnObjects, connection);
                } else {
                    // Create the query string
                    String sql = QueryBuilder.createDocumentQueryString(query, patientId, connection);
                    registry.addSqlQuery(message.getSOAPBody(), sql, returnType, returnObjects);
                }
                // Process it all
				message.saveChanges();
			} catch (SOAPException e) {
				// Problems creating the message
				log.error("Cannot assemble AdhocQueryRequest", e);
				message = null;
			}
		}
		// Done
		return message;
	}
	
	/**
	 * Create a SubmitObjectsRequest holding a submission set, document metadata, and document contents.
	 * 
	 * @param submissionSet The submission set metadata describing the submitted object
	 * @param documentEntries A collection of document metadata descriptions to submit in this set
	 * @return The complete SOAP message for the Submit Objects Request
	 * @throws DocumentException When the content for a document cannot be determined
	 */
	public SOAPMessage createSubmitObjectsRequest(XdsSubmissionSet submissionSet, List<XdsDocumentEntry> documentEntries) throws DocumentException {
		// Create an empty SOAP message
		SOAPMessage message = getEmptySoapMessage();
		if (message != null) {
			try {
				// Add the metadata to the message
				registry.addDocumentMetadata(message.getSOAPBody(), submissionSet, documentEntries, connection);
				// Now, add the actual documents as attachments (maybe none for some Mesa tests)
				if (documentEntries != null) {
					for (XdsDocumentEntry doc: documentEntries) {
						AttachmentPart attachment = null;
						Object content = doc.getContent();
						if (content == null) {
							throw new DocumentException("Cannot submit document with no content");
						} else if (content instanceof URL) {
							// If the content object is a URL then send what it points to
                            URL url = (URL)content;
                            DataSource ds = new ByteArrayDataSource(new BufferedInputStream(new FileInputStream( url.getFile())), doc.getMimeType());
                            DataHandler handler = new DataHandler(ds);
                            attachment = message.createAttachmentPart(handler);
						} else if (content instanceof IDocumentContents) {
							// One of our custom objects, just send the content
                            StreamSource source = new StreamSource(((IDocumentContents) content).getDocumentContentStream());
                            DataHandler handler = new DataHandler(source, doc.getMimeType());
                            attachment = message.createAttachmentPart(handler);
						} else if (content instanceof String) {
                            // A string, turn it into an StreamSource
                            StreamSource source = new StreamSource( new StringReader((String)content));
                            DataHandler handler = new DataHandler(source, doc.getMimeType());
                            attachment = message.createAttachmentPart(handler);
						} else {
							// Whatever it is, just pass it along
							attachment = message.createAttachmentPart(doc.getContent(), doc.getMimeType());
						}
						attachment.setContentId("<" + doc.getEntryUuid() + ">");
						message.addAttachmentPart(attachment);
                    }
				}
				// Process it all
				message.saveChanges();
			} catch (SOAPException e) {
				// Problems creating the message
				log.error("Cannot assemble SubmitObjectsRequest", e);
                throw new DocumentException("Cannot assemble SubmitObjectsRequest:" + e.getMessage(), e);
			} catch (XdsRimException e) {
				// Invalid metadata
				log.error("Invalid metadata passed to SubmitObjectsRequest", e);
		        throw new DocumentException("Invalid metadata passed to SubmitObjectsRequest:" + e.getMessage(), e);
			}  catch (IOException e) {
                log.error("Cannot read from URL", e);
                throw new DocumentException("Cannot read from URL" + e.getMessage(), e);
            }
		}
		// Done
		return message;
	}
	
	/**
	 * Get the type of response that this SOAP registry/repository
	 * response message represents.
	 * 
	 * @param response The SOAP registry response
	 * @return The response type
	 */
	public ResponseType getResponseType(SOAPMessage response) {
		// Extract the SOAPBody, if we can
		if (response == null) return ResponseType.INVALID_RESPONSE;
		SOAPBody body = null;
		try {
			body = response.getSOAPBody();
		} catch (SOAPException e) {
			log.error("Error extracting SOAP body from response.", e);
			return ResponseType.INVALID_RESPONSE;
		}
		if (body == null) return ResponseType.INVALID_RESPONSE;
		// See if there is a fault
		if (body.hasFault()) return ResponseType.FAULT_RESPONSE;
		// See if its empty
		if (!body.hasChildNodes()) return ResponseType.EMPTY_RESPONSE;
		// Make sure there is only one child
		NodeList children = body.getChildNodes();
        //Make sure there are at most 3 children. One for response Element, 2 are pre-text and post-text.
        if (children.getLength() > 3) return ResponseType.OFF_PROTOCOL_RESPONSE;
		// Examine the child to see if it is okay
        for (int i=0; i<children.getLength(); i++){
            Node child = children.item(i);
            if( child.getNodeType() == Node.TEXT_NODE) {
                continue;
            }
            String responseNamespace = child.getNamespaceURI();
            if ((responseNamespace != null) &&
                (!isEbXmlV3 && responseNamespace.equals(XDS_REGISTRY_NAMESPACE))) {
                // Some sort of registry response
                return ResponseType.REGISTRY_RESPONSE;
            } else if ((responseNamespace != null) &&
               (isEbXmlV3 && responseNamespace.equals(XDS_QUERY_V3_NAMESPACE))) {
                // Some sort of registry response
                return ResponseType.ADHOC_QUERY_RESPONSE;
            } else {
                // Not a registry response
                return ResponseType.UNEXPECTED_RESPONSE;
            }
        }
        // Not a registry response, only possible is TEXT_REPONSE
        return ResponseType.TEXT_RESPONSE;
	}

    /**
     * Get the ebXML registry response out of a SOAP message response.
     *
     * @param message The SOAP message
     * @return The registry response object
     */
    XdsResponse getXdsResponse(SOAPMessage message) {
        ResponseType responseType = getResponseType(message);
        // Make sure that we got a response from the registry
        if (responseType == ResponseType.REGISTRY_RESPONSE || responseType == ResponseType.ADHOC_QUERY_RESPONSE) {
            // The message holds a registry response
            XdsResponse response = extractRegistryResponse(message);
            if (response == null) {
                // Something is malformed with respect to the response
                response = new XmlErrorResponse("Unexpected registry or adhoc query response message type from \"" + connection.getDescription() + "\"");
            }
            return response;
        } else {
            // Some sort of bad response
            String description  = getResponseMessage(message);
            return new XmlErrorResponse(description);
        }
    }

	/**
	 * Get an appropriate description of this SOAP registry/repository
	 * response message.
	 * 
	 * @param response The SOAP registry response
	 * @return The response type
	 */
	public String getResponseMessage(SOAPMessage response) {
		// Extract the SOAPBody, if we can
		if (response == null) return "Registry \"" + connection.getDescription() + "\" did not respond.";
		SOAPBody body = null;
		try {
			body = response.getSOAPBody();
		} catch (SOAPException e) {
			return "Registry  \"" + connection.getDescription() + "\" responded with an invalid SOAP wrapper.";
		}
		if (body == null) return "Registry  \"" + connection.getDescription() + "\" responded with an invalid SOAP wrapper.";
		// See if there is a fault
		if (body.hasFault()) {
			SOAPFault fault = body.getFault();
			return "Registry  \"" + connection.getDescription() + "\" responded with a SOAP Fault: " + fault.toString();
		}
		// See if its empty
		if (!body.hasChildNodes()) return "Registry  \"" + connection.getDescription() + "\" responded with an empty SOAP message.";
		// Make sure there is only one child
		NodeList children = body.getChildNodes();
		if (children.getLength() > 3) return "Registry  \"" + connection.getDescription() + "\" responded with more than one registry message.";
		// Examine the child to see if it is okay
        for (int i=0; i<children.getLength(); i++){
            Node child = children.item(i);
            if( child.getNodeType() == Node.TEXT_NODE) {
                continue; //Could be an empty line
            }
            // Okay, now dig in to see what type of response it really is
            String responseNamespace = child.getNamespaceURI();
            if ((responseNamespace != null) && responseNamespace.equals(XDS_REGISTRY_NAMESPACE)) {
                // Some sort of registry response
                return "Registry  \"" + connection.getDescription() + "\" responded with something appropriate.";
            } else {
                // Not a registry response
                return "Registry  \"" + connection.getDescription() + "\" responded with an invalid namespace: " + responseNamespace;
            }
        }
        // Not a registry response, only possible is TEXT_REPONSE
        String data = null;
        for (int i=0; i<children.getLength(); i++) {
            Node child = children.item(i);
            if( data == null)
                data = child.getTextContent();
            else
               data += child.getTextContent();
        }
        if (data != null) {
            return "Registry  \"" + connection.getDescription() + "\" responded with the string \"" + data + "\".";
        } else {
            return "Registry  \"" + connection.getDescription() + "\" responded with the NULL string.";
        }
	}
	
	/**
	 * Extract a registry response object from a SOAP message.
	 * 
	 * @param message The SOAP message holding the response
	 * @return The registry response as a Java object
	 */
	public XdsResponse extractRegistryResponse(SOAPMessage message) {
		// Extract the SOAPBody, if we can
		if (message == null) return null;
		SOAPBody body = null;
		try {
			body = message.getSOAPBody();
		} catch (SOAPException e) {
			log.error("Invalid SOAP in response from registry \"" + connection.getDescription() + "\"", e);
		}
		if (body == null) return null;
		// Pull out the response
        if (isEbXmlV3) {
            return registry.getAdhocQueryResponse(body);
        } else {
            return registry.getRegistryResponse(body);
        }
	}
	
	/**
	 * Create a new, empty SOAP message
	 * 
	 * @return The empty message
	 */
	 SOAPMessage getEmptySoapMessage() {
		try {                                                      
			// System.setProperty("javax.xml.soap.MessageFactory", "com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl");
			//MessageFactory factory = MessageFactory.newInstance();
			MessageFactory factory = XmlUtil.newMessageFactoryInstance(XmlUtil.SunJDK6MessageFactory);
			// Create an empty SOAP message
			SOAPMessage message = factory.createMessage();
			// Make sure that the body is written with an XML declaration at the beginning
			message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
			// Make sure the message is written using the default charset
	 		message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, DEFAULT_SOAP_CHARSET);
			// Add a couple of Mime headers to be put in the HTTP message
		  MimeHeaders headers = message.getMimeHeaders();
		  headers.addHeader("Content-Type", DEFAULT_SOAP_MIME_TYPE + "; charset=\"" + DEFAULT_SOAP_CHARSET + "\"");
		  headers.addHeader("SOAPAction", DEFAULT_SOAP_ACTION);
			// Done
			return message;
		} catch (SOAPException e) {
			log.error("Cannot create new SOAP message", e);
			return null;
		}
	}
	
	/**
	 * Send a SOAP message to the repository/registry using one of
	 * our custom made HTTP channels.
	 * 
	 * @param message The message to be sent
	 * @param logger The MESA logger to catch the message and the response
	 * @param kind The type of SOAP message being sent
	 * @param doSendMessage If false, log the message but don't actually send it (for testing)
	 * @return The SOAP response to the message sent
	 */
	public SOAPMessage send(SOAPMessage message, IMesaLogger logger, String kind, boolean doSendMessage) {
		// Write the message to the MESA test log
		if (logger != null) {
			// MESA output capture enabled
			logger.writeString("Sending " + kind + " to '" + getConnectionString() + "' ...");
			logger.writeSoapMessage(message);
			logger.writeString("Connection " +getConnectionDescription());
		}
		// If just testing bail out now
		if (!doSendMessage) {
			log.warn("Sending SOAP message is inhibited for testing purposes.");
			return null;
		}
		// Create a connection for sending the message
		HttpSoapChannel channel = new HttpSoapChannel(connection, logger);
		// Make the call and catch the output
		try {
			// Response is logged by the channel
			return channel.sendMessage(message);
		} catch (IOException e) {
			// Cannot communicate with XDS server
			log.error("Cannot communicate with XDS repository \"" + connection.getDescription() + "\"", e);
			return null;
		} catch (SOAPException e) {
			// XDS server responded with ill-formed SOAP
			log.error("XDS repository responded with ill-formed SOAP \"" + connection.getDescription() + "\"", e);
			return null;
		}
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
