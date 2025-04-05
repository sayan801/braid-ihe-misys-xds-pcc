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

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.SerializerFactory;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.IDocumentContents;
import com.misyshealthcare.connect.ihe.log.Log4jLogger;
import com.misyshealthcare.connect.ihe.registry.XdrIntendedRecipient;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsResponse;
import com.misyshealthcare.connect.ihe.registry.XdsRimException;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
import com.misyshealthcare.connect.ihe.registry.XmlErrorResponse;
import com.misyshealthcare.connect.ihe.registry.XmlRegistry;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * 
 * @author Josh Flachsbart
 * @version 1.0 - Dec 10, 2006
 */
public class XdrMessenger {
	
	/* The logger for error and debugging messages */
	private static final Logger log = Logger.getLogger(XdrMessenger.class.getName());

    private static final String XDS_REGISTRY_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:registry:xsd:2.1";
    private static final String XDS_QUERY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0";

	private static final String DEFAULT_SOAP_CHARSET = "utf-8";
	private static final String DEFAULT_SOAP_MIME_TYPE = "text/xml";
	private static final String DEFAULT_SOAP_ACTION = "\"ebXML\"";
  
	/* The connection description of the registry/repository */
	private IConnectionDescription connection = null;
	/* A utility object for encoding and decoding data */
    private XmlRegistry registry = null;
    /*Whether to use of ebXml registry version 3 or older version such as 2.1 */
    private boolean isEbXmlV3 = true;
    private MimeTypeToFileSuffixConverter mimeTypeMap = new MimeTypeToFileSuffixConverter();

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
  // TODO make private.
  public XdrMessenger(IConnectionDescription description, boolean useEbxmlV3) {
  	registry = new XmlRegistry();
  	connection = description;
    isEbXmlV3 = useEbxmlV3;
  }
	
	/** Creates a response to recieving a submit objects request. 
	 * @param message The message we are responding to.
	 * */
	public SOAPMessage createRegistryResponse(SOAPMessage message) {
		
		// Create an empty SOAP message
		SOAPMessage response = getEmptySoapMessage();
		if (message != null) {
			try {
				// Add the metadata to the message
				registry.addSmtpResponseHeaders(response, message);
				
				SOAPFactory contentFactory = SOAPFactory.newInstance();

				// Create ebRS reply message
				// TODO make this more general and stick it in the XML registry.
				Name reqName = contentFactory.createName("RegistryResponse", "rs", XDS_REGISTRY_NAMESPACE);        
				Name status = contentFactory.createName("status", "rs", XDS_REGISTRY_NAMESPACE);        
				SOAPElement request = contentFactory.createElement(reqName);
				request.addAttribute(status, "Success");

				// Attach reply to soap message.
				AttachmentPart attachment = response.createAttachmentPart(new DataHandler(serializeXml(request), "text/xml"));
				addAttachmentMimeHeaders(attachment, "METADATA.XML");
				attachment.addMimeHeader("content-description", "Document Recipient Response");
				attachment.setContentId("<Metadata>");
				response.addAttachmentPart(attachment);
				// Process it all
				response.saveChanges();
			} catch (SOAPException e) {
				// Problems creating the message
				log.error("Cannot assemble RegistryResponse", e);
				throw new DocumentException("Cannot assemble SubmitObjectsRequest", e);
			}
		}
		// Done
		return response;
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
				registry.addSmtpDocumentHeader(message, submissionSet, documentEntries, connection);
				//registry.addDocumentMetadata(message.getSOAPBody(), submissionSet, documentEntries, connection);
				// Now, add the actual documents as attachments (maybe none for some Mesa tests)
				if (documentEntries != null) {
					// Add initial (rim) attachment
					AttachmentPart attachment = message.createAttachmentPart(new DataHandler(getRimMessage(submissionSet, documentEntries), "text/xml"));
					addAttachmentMimeHeaders(attachment, "METADATA.XML");
					attachment.addMimeHeader("content-description", "Send Document Set Metadata");
					attachment.setContentId("<Metadata>");
					message.addAttachmentPart(attachment);
					// Add the actual documents
					for (XdsDocumentEntry doc: documentEntries) {
						try {
							Object content = doc.getContent();
							if (content == null) {
								throw new IOException("blah blah");
//								throw new DocumentException("Cannot submit document with no content");
							} else if (content instanceof URL) {
								// If the content object is a URL then send what it points to
								DataHandler handler = new DataHandler((URL) content);
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
							addAttachmentMimeHeaders(attachment, getFileName(doc));
							// TODO figure out which one to use.  answer: switch which library we are using?
							attachment.setContentId("<" + doc.getEntryUuid() + ">");
							//attachment.setContentId(doc.getEntryUuid());
							message.addAttachmentPart(attachment);
						} catch (IOException e) {
							throw new DocumentException("I/O Exception loading document data.", e);
						}
					}
				}
				// Process it all
				message.saveChanges();
			} catch (SOAPException e) {
				// Problems creating the message
				log.error("Cannot assemble SubmitObjectsRequest", e);
                throw new DocumentException("Cannot assemble SubmitObjectsRequest", e);
			} catch (XdsRimException e) {
				// Invalid metadata
				log.error("Invalid metadata passed to SubmitObjectsRequest", e);
                throw new DocumentException("Invalid metadata passed to SubmitObjectsRequest", e);
			}
		}
		// Done
		return message;
	}
	
	private String getFileName(XdsDocumentEntry doc) {
		String fileName = doc.getEntryUuid();
		String mimeType = doc.getMimeType();
		fileName += "." + mimeTypeMap.getSuffix(mimeType);
		return fileName;
	}
	
	private void addAttachmentMimeHeaders(AttachmentPart attachment, String metadataFilename) {
		attachment.addMimeHeader("charset", "UTF-8");
		attachment.addMimeHeader("name", metadataFilename);
		attachment.addMimeHeader("content-location", metadataFilename);
		attachment.addMimeHeader("content-disposition", "attachment");
		attachment.addMimeHeader("filename", metadataFilename);
	}
	
    /** This needs to go somewhere else, but I can't think of a good place for it right now.
     * 
     * @param toSerialize
     * @param documentEntries
     * @return
     */
	public static String serializeXml(SOAPElement toSerialize) {
		String elementAsString = null;
		try {
			StringWriter content = new StringWriter();
			SerializerFactory factory = SerializerFactory.getSerializerFactory(Method.XML);
			Serializer serializer = factory.makeSerializer(content, new OutputFormat());
			XMLSerializer ser = (XMLSerializer) serializer.asDOMSerializer();
			ser.setNamespaces(true);
			ser.setOutputFormat(new OutputFormat(Method.XML, "utf-8", true));
			ser.serialize(toSerialize);
			elementAsString = content.toString();
		} catch (IOException e) {
			log.error("Problem serializing soap element.", e);
			elementAsString = null;
		}
		return elementAsString;
	}
	
	private String getRimMessage(XdsSubmissionSet submissionSet, List<XdsDocumentEntry> documentEntries) {
		String rimMessage = null;
		try {
			rimMessage = serializeXml(registry.addDocumentMetadata(null, submissionSet, documentEntries, connection));
		} catch (SOAPException e) {
			log.error("Unable to build soap message.", e);
			rimMessage = null;
		} catch (XdsRimException e) {
			log.error("Unable to build rim message.", e);
			rimMessage = null;
		}
		return rimMessage;
	}
	
	/**
	 * Create a new, empty SOAP message
	 * 
	 * @return The empty message
	 */
	private SOAPMessage getEmptySoapMessage() {
		try {
			// Grab the default SOAP implementation
			MessageFactory factory = MessageFactory.newInstance();
			// Create an empty SOAP message
			SOAPMessage message = factory.createMessage();
			// Make sure that the body is written with an XML declaration at the beginning
			message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
			// Make sure the message is written using the default charset
	 		message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, DEFAULT_SOAP_CHARSET);
			// Add a couple of Mime headers to be put in the SMTP message
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
	public void send(SOAPMessage message, IMesaLogger logger, String kind, boolean doSendMessage) {
		// Write the message to the MESA test log
		if (logger != null) {
			// MESA output capture enabled
			logger.writeString("Sending " + kind + " to '" + getConnectionString() + "' ...");
			logger.writeSoapMessage(message);
			logger.writeString("Connection " +getConnectionDescription());
		}
		// If just testing bail out now
		if (!doSendMessage) {
			log.warn("Sending SOAP message is inhibited for testing purposes.\nMessage follows:");
			if (message != null) {
				log.info("SMTP Headers ...");
				MimeHeaders headers = message.getMimeHeaders();
				Iterator headerNames = headers.getAllHeaders();
				while (headerNames.hasNext()) {
					MimeHeader header = (MimeHeader)headerNames.next();
					log.info(header.getName() + ": " + header.getValue());
				}
				try {
					log.info("SOAP Message ...");
					message.writeTo(System.out);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return;
		}
		// Create a connection for sending the message
		SmtpSoapChannel channel = new SmtpSoapChannel(connection, logger);
		// Make the call and catch the output
		try {
			// Response is logged by the channel
			channel.sendMessage(message);
		} catch (IOException e) {
			// Cannot communicate with XDS server
			log.error("Cannot communicate with SMTP server \"" + connection.getDescription() + "\"", e);
		} catch (MessagingException e) {
			// SMTP server responded with error
			log.error("Problem communicating with SMTP server \"" + connection.getDescription() + "\"", e);
		} catch (SOAPException e) {
			log.error("Ill formed soap message handed to send.", e);
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
	
	XdsResponse getXdrResponse(SOAPMessage message) {
		ResponseType responseType = getResponseType(message);
		// Make sure that we got a message from an xdr messenger
		if (responseType == ResponseType.REGISTRY_RESPONSE) {
			XdsResponse response = registry.getSubmissionResponse(message);
			if (response == null) { // Something is malformed with respect to the response
				response = new XmlErrorResponse("Unexpected submission request type from \"" + connection.getDescription() + "\"");
			}
			return response;
		} else if (responseType == ResponseType.FAULT_RESPONSE) {
			return new XmlErrorResponse("Fault submission... Source misconfigured?");
		} else if (responseType == ResponseType.EMPTY_RESPONSE) {
			return new XmlErrorResponse("Empty soap message");
		} else if (responseType == ResponseType.OFF_PROTOCOL_RESPONSE) {
			return new XmlErrorResponse("Soap message doesn't meet protocol.");
		} else { // if (responseType == ResponseType.INVALID_RESPONSE) {
			return new XmlErrorResponse("Critical SOAP error.");
		}
	}
	
	private ResponseType getResponseType(SOAPMessage response) {
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
		NodeList children = body.getChildNodes();
		for (int i=0; i<children.getLength(); i++){
			Node child = children.item(i);
			if( child.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			String responseNamespace = child.getNamespaceURI();
			if ((responseNamespace != null) &&
					(!isEbXmlV3 && responseNamespace.equals(XDS_REGISTRY_NAMESPACE))) {
				return ResponseType.REGISTRY_RESPONSE;
			} else {
				return ResponseType.UNEXPECTED_RESPONSE;
			}
		}
        // Not a registry response, only possible is TEXT_REPONSE
		return ResponseType.TEXT_RESPONSE;
	}

	public List<SOAPMessage> retrieveAllSoapMessages(IMesaLogger logger) {
		// Create a connection for sending the message
		SmtpSoapChannel channel = new SmtpSoapChannel(connection, logger);
		// Make the call and catch the output
		try {
			// Response is logged by the channel
			List<SOAPMessage> messages = channel.getMessages();
			log.info("Messages retrieved:");
			for (SOAPMessage m : messages)
				log.info(m.getContentDescription() + " \n" + m.getSOAPBody().toString() + " \n" + m.countAttachments());
			return messages;
		} catch (IOException e) {
			// Cannot communicate with XDS server
			log.error("Cannot communicate with SMTP server \"" + connection.getDescription() + "\"", e);
		} catch (MessagingException e) {
			// SMTP server responded with error
			log.error("Problem communicating with SMTP server \"" + connection.getDescription() + "\"", e);
		} catch (SOAPException e) {
			log.error("Ill formed soap message handed to send.", e);
		}
		return new ArrayList<SOAPMessage>();
	}

	
	public class MimeTypeToFileSuffixConverter {
		private class MimeTypeSuffixSet {
			String mimeType;
			Collection<String> suffixes;
			
			MimeTypeSuffixSet(String mimeType) {
				this.mimeType = mimeType.toLowerCase();
				suffixes = new ArrayList<String>();
			}
			
			String getMimeType() { return mimeType; }
			void addSuffix(String ending) { suffixes.add(ending.toUpperCase()); }			
			boolean hasSuffix(String ending) { return suffixes.contains(ending.toUpperCase()); }
			
			String getDefaultSuffix() { 
				String suffix = "";
				if (!suffixes.isEmpty()) suffix = suffixes.iterator().next();
				return suffix;
			}
			
		}
		
		private HashMap<String,MimeTypeSuffixSet> mimeSuffixTable;

		public String getSuffix(String mimeType) {
			MimeTypeSuffixSet set = mimeSuffixTable.get(mimeType.toLowerCase());
			String suffix = set == null ? "" : set.getDefaultSuffix();
			if (suffix.length() == 0) log.warn("No suffix found for this mime type: " + mimeType);
			return suffix;
		}
		
		public String getMimeType(String suffix) {
			String mimeType = "";
			for (MimeTypeSuffixSet set: mimeSuffixTable.values()) {
				if (set.hasSuffix(suffix)) {
					mimeType = set.getMimeType();
					break;
				}
			}
			if (mimeType.length() == 0) log.warn("No mime type found for suffix: " + suffix);
			return mimeType;
		}
		
		MimeTypeToFileSuffixConverter() {
			mimeSuffixTable = new HashMap<String,MimeTypeSuffixSet>();
			MimeTypeSuffixSet entry = new MimeTypeSuffixSet("text/plain");
			entry.addSuffix("TXT");
			entry.addSuffix("TEXT");
			mimeSuffixTable.put(entry.getMimeType(), entry);
			entry = new MimeTypeSuffixSet("text/xml");
			entry.addSuffix("XML");
			mimeSuffixTable.put(entry.getMimeType(), entry);
			entry = new MimeTypeSuffixSet("application/pdf");
			entry.addSuffix("PDF");
			mimeSuffixTable.put(entry.getMimeType(), entry);
		}
	}

	/**
	 * Test program to produce metadata to validate against the NIST repository.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a sample connection object
		ConnectionFactory.loadConnectionDescriptionsFromFile("src/config/connectathon/XdrConnections.xml");
		IConnectionDescription conn = ConnectionFactory.getConnectionDescription("test-xdr-connection");
		// Create a sample document entry
		XdsDocumentEntry doc = new XdsDocumentEntry();
		doc.setTitle("Discharge Summary for Mr. Clown");
		//doc.setAuthorInstitution("Misys Healthcare");
		Vector<String> authors = new Vector<String>();
		authors.add("Joe^Mary^^^");
		//doc.setAuthorPersons(authors);
		doc.setCreationTime("20051112");
		doc.setLanguageCode("en-ca");
		doc.setLegalAuthenticator("Mayor^The^^^");
		doc.setServiceStartTime("20051112");
		doc.setServiceStopTime("20051112");
		doc.setSourcePatientId("jfjfjf^^^jfjfjf");
		Vector<String> sourceInfo = new Vector<String>();
		sourceInfo.add("PID-3|pid1^^^domain");
		sourceInfo.add("PID-5|Doe^John^^^");
		sourceInfo.add("PID-7|19560527");
		sourceInfo.add("PID-8|M");
		sourceInfo.add("PID-11|100 Main St^^Metropolis^Il^44130^USA");
		doc.setSourcePatientInfo(sourceInfo);
		doc.setClassCode("Discharge summarization");
		doc.setFormatCode("PDF/IHE 1.x");
		doc.setPracticeSettingCode("Dialysis");
		doc.setHealthcareFacilityTypeCode("Home");
		doc.setTypeCode("11488-4");
		doc.setPatientId("TEST-1^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
		doc.setUniqueId("123.345.567");
		doc.setMimeType("application/pdf");
		doc.setEntryUuid("doc1");
		try {
		doc.setContent(new URL("file:/Users/josh/Documents/Mesa/testkit/11964/MrClown.pdf"));
		} catch (Exception e) { log.error("ut oh"); }
		// Now create a sample submission set
		XdsSubmissionSet set = new XdsSubmissionSet();
		set.setComments("Test Submission Set");
		//set.setAuthorInstitution("Misys Healthcare");
		Vector<String> authors2 = new Vector<String>();
		authors2.add("Joe^Mary^^^");
		authors2.add("Joe^Betty^^^");
		//set.setAuthorPersons(authors2);
		set.setSubmissionTime("20051112");
		set.setContentTypeCode("Admission evaluation");
		set.setPatientId("TEST-1^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
		set.setUniqueId("123.345.568");
		set.setSourceId("Debugging Code");
		List<XdrIntendedRecipient> intendedRecipients = new ArrayList<XdrIntendedRecipient>();
		intendedRecipients.add(new XdrIntendedRecipient("^Wel^Marcus^^^Dr^MD", "Fair Clinic^L^716"));
		intendedRecipients.add(new XdrIntendedRecipient("^Al^Peter^^^Dr^MD", "Fair Clinic^L^716"));
		intendedRecipients.add(new XdrIntendedRecipient("12345^John^Smith^^^Dr^MD", null));
		intendedRecipients.add(new XdrIntendedRecipient(null, "Main Hospital"));
		set.setIntendedRecipients(intendedRecipients);
		// Bundle it up into a soap envelope and a rim message.
		// rim message
		// soap message
		SOAPMessage message = null;
		try {
			boolean sending = true;
			IMesaLogger logger = new Log4jLogger();
			
			ArrayList<XdsDocumentEntry> docs = new ArrayList<XdsDocumentEntry>();
			docs.add(doc);
			XdrMessenger me = new XdrMessenger(conn, false);
			message = me.createSubmitObjectsRequest(set, docs);
			me.send(message, logger, "foo", sending);
			if (sending) {
				message = me.retrieveAllSoapMessages(null).get(0);
//				SOAPMessage response = me.createRegistryResponse(msg);
//				me.send(response, logger, "response", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Write it out
		if (message != null) {
			log.info("SMTP Headers ...");
			MimeHeaders headers = message.getMimeHeaders();
			Iterator headerNames = headers.getAllHeaders();
			while (headerNames.hasNext()) {
				MimeHeader header = (MimeHeader)headerNames.next();
				log.info(header.getName() + ": " + header.getValue());
			}
			try {
				log.info("SOAP Message ...");
				message.writeTo(System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
