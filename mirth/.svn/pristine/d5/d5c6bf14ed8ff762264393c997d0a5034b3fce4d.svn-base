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
package com.misyshealthcare.connect.ihe.registry;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class implements builders for the various SOAP message contents objects
 * needed in submissions to XDR clients.
 * 
 * @author Josh Flachsbart
 * @version 1.0 - Nov 10, 2006
 */
public class EbXmlMsgSoap {
	private static final Logger myLog = Logger.getLogger(EbXmlMsgSoap.class);
	private static final String XDS_RIM_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1";
	private static final String XDS_RIM_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0";
	private static final String EBXML_MSG_NAMESPACE = "http://www.oasis-open.org/committees/ebxml-msg/schema/msg-header-2_0.xsd";
	private static final String SUBMIT_OBJECT_REQUEST_ROLE = "http://www.ihe.net/roles/iti/xds/SubmitObjectsRequest";
	private static final String DOCUMENT_SOURCE_ROLE = "http://www.ihe.net/roles/iti/xds/DocumentSource";
	private static final String DOCUMENT_RECIPIENT_ROLE = "http://www.ihe.net/roles/iti/xds/DocumentRecipient";
	// private static final int RIM_SHORT_NAME = 64;
	private static final int RIM_LONG_NAME = 128;

	/* Whether to use Rim V2.1 or V3.0. By default it is Rim V2.1. */
	private static String rimNameSpace = XDS_RIM_NAMESPACE;
	private static SimpleDateFormat defaultMessageFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS00");

	private SOAPElement root = null;
	private String id = null;
	private IConnectionDescription connection = null;
	private Logger log = null;

	/**
	 * Create a new XML builder.
	 * 
	 * @param id
	 *            The ID to assign to the ExtrinsicObject being built
	 * @param connection
	 *            The connection description for where this XML will be sent
	 * @throws SOAPException
	 *             When no SOAP implementation can be found
	 */
	EbXmlMsgSoap(String id, IConnectionDescription connection, Logger log)
			throws SOAPException {
		this.id = id;
		this.connection = connection;
		this.log = log;
	}

	/**
	 * Get a new instance of an XML builder for ebRIM XDSDocumentEntry metadata.
	 * 
	 * @param id
	 *            The id for the ExtrinsicObject representing the metadata
	 * @param connection
	 *            The connection to which this metadata will be sent
	 * @param log
	 *            The log to use when reporting warnings and errors
	 * @return The ebRIM XML builder
	 * @throws SOAPException
	 *             When an appropriate SOAP implementation cannot be found
	 */
	public static EbXmlMsgSoap newXdsDocumentEntryBuilder(String id,
			IConnectionDescription connection, Logger log) throws SOAPException {
		EbXmlMsgSoap xml = new EbXmlMsgSoap(id, connection, log);
		SOAPFactory factory = SOAPFactory.newInstance();
		xml.root = factory
				.createElement("ExtrinsicObject", "rim", rimNameSpace);
		xml.root.setAttribute("id", id);
		return xml;
	}

	/**
	 * Get a new instance of an XML builder for ebRIM XDSSubmissionSet metadata.
	 * 
	 * @param id
	 *            The id for the RegistryPackage representing the metadata
	 * @param connection
	 *            The connection to which this metadata will be sent
	 * @param log
	 *            The log to use when reporting warnings and errors
	 * @return The ebRIM XML builder
	 * @throws SOAPException
	 *             When an appropriate SOAP implementation cannot be found
	 */
	public static EbXmlMsgSoap newXdsSubmissionSetBuilder(String id,
			IConnectionDescription connection, Logger log) throws SOAPException {
		EbXmlMsgSoap xml = new EbXmlMsgSoap(id, connection, log);
		SOAPFactory factory = SOAPFactory.newInstance();
		xml.root = factory
				.createElement("RegistryPackage", "rim", rimNameSpace);

		xml.root.setAttribute("id", id);
		//try {
			//xml.addName("Submission Set", true);
		//} catch (XdsRimException e) {
		//}
		return xml;
	}

	/**
	 * Get the SOAPElement built by this builder.
	 * 
	 * @return The XML DOM document
	 */
	public SOAPElement getSoapElement() {
		return root;
	}

	/* -------------------------------------------------------------------------------------- */

	/**
	 * Add a new ebXML DOM element of the given type and add it to the parent as
	 * a child element.
	 * 
	 * @param parent
	 *            The parent element this should be added to
	 * @param tagName
	 *            The element tag name
	 * @param ebNameSpace
	 *            the rim name space of the root element
	 * @return The created element
	 * @throws SOAPException
	 *             If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbElement(SOAPElement parent, String tagName, String ebNameSpace) 
		throws SOAPException 
	{
		SOAPElement newElement = parent.addChildElement(tagName, "eb", ebNameSpace);
		return newElement;
	}

	/**
	 * Add a new ebXML DOM element of the given type and add it to the parent as
	 * a child element.  Include a text portion as well.
	 * 
	 * @param parent
	 *            The parent element this should be added to
	 * @param tagName
	 *            The element tag name
	 * @param ebNameSpace
	 *            the rim name space of the root element
	 * @return The created element
	 * @throws SOAPException
	 *             If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbTextElement(
		SOAPElement parent, String tagName, String text, String ebNameSpace) 
		throws SOAPException 
	{
		SOAPElement newElement = addEbElement(parent, tagName, ebNameSpace);
		newElement.addTextNode(text);
		return newElement;
	}

	/**
	 * @param submissionSet
	 * @param fromDoc
	 * @param toDoc
	 * @param header
	 * @param ebNamespace TODO
	 * @throws SOAPException
	 */
	public static void fillEbSoapHeaderElement(
		String description, String fromDoc, String toDoc, SOAPElement header, String conversationId, boolean isResponse, String ebNamespace) 
		throws SOAPException 
	{
		EbXmlMsgSoap.addEbFromElement(header, fromDoc, isResponse, ebNamespace);
		EbXmlMsgSoap.addEbToElement(header, toDoc, isResponse, ebNamespace);
		EbXmlMsgSoap.addEbCpaIdToElement(header, null, fromDoc, toDoc, ebNamespace);
		// TODO add ability to change format based on different CPAs.
		if (conversationId == null) conversationId = defaultMessageFormat.format(new Date());
		EbXmlMsgSoap.addEbConversationIdToElement(header, conversationId, ebNamespace);
		EbXmlMsgSoap.addEbMessageDataToElement(header, conversationId, ebNamespace);
		EbXmlMsgSoap.addEbTextElement(header, "Description", description, ebNamespace);
		EbXmlMsgSoap.addEbAckRequestedToElement(header, ebNamespace);
	}
	
	/**
	 * Add a new ebXML Reference element to the parent element.
	 * 
	 * @param parent The parent element this should be added to
	 * @param objectName The title of the document.
	 * @param extrinsicObjectId The id of the extrinsic object.
	 * @param ebNameSpace the rim name space of the root element
	 * @return The newly created element.
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbReferenceElement(
		SOAPElement parent, String objectName, String extrinsicObjectId, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement referenceElement = addEbElement(parent, "Reference", ebNameSpace);
		referenceElement.setAttributeNS(ebNameSpace, "id", extrinsicObjectId);
		referenceElement.setAttribute("xlink:href", "cid:" + extrinsicObjectId);
		addEbTextElement(referenceElement, "Descripition", objectName, ebNameSpace);
		return referenceElement;
	}

	/**
	 * Add a new ebXML Reference element to the parent element.
	 * 
	 * @param parent
	 *            The parent element this should be added to
	 * @param name
	 *            The name of the slot
	 * @param value
	 *            The value of the slot
	 * @param rimNameSpace
	 *            the rim name space of the root element
	 * @return The new slot element
	 * @throws SOAPException
	 *             If a SOAP implementation cannot be found
	 */
	public static SOAPElement addFirstEbReferenceElement(
		SOAPElement parent, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement referenceElement = addEbReferenceElement(parent, "Provide and Register Document Set Metadata", "Metadata", ebNameSpace);
		referenceElement.setAttribute("xlink:role", SUBMIT_OBJECT_REQUEST_ROLE);
		SOAPElement schemaElement = addEbElement(referenceElement, "Schema", ebNameSpace);
		schemaElement.setAttributeNS(ebNameSpace, "location", SUBMIT_OBJECT_REQUEST_ROLE);
		schemaElement.setAttributeNS(ebNameSpace, "version", "1.0");
		return referenceElement;
	}

	/**
	 * Add a new ebXML From element to the parent element.
	 * 
	 * @param parent The parent element this should be added to
	 * @param name The name of the sender
	 * @param ebNameSpace the ebXML messaging name space of the root element
	 * @return The new from element
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbFromElement(
		SOAPElement parent, String name, boolean response, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement fromElement = addEbElement(parent, "From", ebNameSpace);
		String role = DOCUMENT_SOURCE_ROLE;
		if (response) role = DOCUMENT_RECIPIENT_ROLE;
		addEbPartyIdElement(fromElement, name, role, ebNameSpace);
		return fromElement;
	}

	/**
	 * Add a new ebXML To element to the parent element.
	 * 
	 * @param parent The parent element this should be added to
	 * @param name The name of the sender
	 * @param ebNameSpace the ebXML messaging name space of the root element
	 * @return The new from element
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbToElement(
		SOAPElement parent, String name, boolean response, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement fromElement = addEbElement(parent, "To", ebNameSpace);
		// XDR only, for XDS should be doc. rep.
		String role = DOCUMENT_RECIPIENT_ROLE;
		if (response) role = DOCUMENT_SOURCE_ROLE;
		addEbPartyIdElement(fromElement, name, role, ebNameSpace);
		return fromElement;
	}

	/**
	 * Add a new ebXML party id and role element for the to or from element.
	 * 
	 * @param parent The parent element this should be added to
	 * @param name The name of the sender
	 * @param role The role of the sender as specified by ITI XDS Doc Source 
	 * @param ebNameSpace the ebXML messaging name space of the root element
	 * @return The new PartyId element.
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbPartyIdElement(
		SOAPElement parent, String name, String role, String ebNameSpace) 
		throws SOAPException
	{
		addEbTextElement(parent, "Role", role, ebNameSpace);
		SOAPElement partyElement = addEbTextElement(parent, "PartyId", "mailto:" + name, ebNameSpace);
		return partyElement;
	}
	
	/**
	 * Add a new Collaboration Protocol Agreement uri. 
	 * 
	 * @param parent The parent element this should be added to
	 * @param name The name of the sender
	 * @param ebNameSpace the ebXML messaging name space of the root element
	 * @return The new from element
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbCpaIdToElement(
		SOAPElement parent, String name, String from, String to, String ebNameSpace)
		throws SOAPException 
	{
		if (name == null) name = from + "-" + to;
		SOAPElement cpaIdElement = addEbTextElement(parent, "CPAId", name, ebNameSpace);
		return cpaIdElement;
	}
	
	/**
	 * Generates a unique conversation id.
	 * 
	 * @param parent The parent element this should be added to
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbConversationIdToElement(
		SOAPElement parent, String conversationId, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement conversationIdElement = 
			addEbTextElement(parent, "ConversationId", conversationId, ebNameSpace);
		// TODO: don't do this here...
		addEbTextElement(parent, "Service", "LifeCycleManager", ebNameSpace);
		addEbTextElement(parent, "Action", "submitObjects", ebNameSpace);
		addEbElement(parent, "DuplicateElimination", ebNameSpace);
		return conversationIdElement;
	}
	
	/**
	 * Generates a unique conversation id.
	 * 
	 * @param parent The parent element this should be added to
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbMessageDataToElement(
		SOAPElement parent, String messageId, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement messageDataElement = addEbElement(parent, "MessageData", ebNameSpace);
		addEbTextElement(messageDataElement, "MessageId", messageId, ebNameSpace);
		addEbTextElement(messageDataElement, "Timestamp", HL7.toDTM(new Date()), ebNameSpace);
		return messageDataElement;
	}
	
	/**
	 * Generates a unique conversation id.
	 * 
	 * @param parent The parent element this should be added to
	 * @throws SOAPException If a SOAP implementation cannot be found
	 */
	public static SOAPElement addEbAckRequestedToElement(
		SOAPElement parent, String ebNameSpace)
		throws SOAPException 
	{
		SOAPElement messageDataElement = addEbElement(parent, "AckRequested", ebNameSpace);
		messageDataElement.setAttributeNS(parent.getNamespaceURI(), "mustUnderstand", "1");
		messageDataElement.setAttributeNS(ebNameSpace, "version", "2.0");
		messageDataElement.setAttributeNS(ebNameSpace, "signed", "false");
		return messageDataElement;
	}
	
	/** 
	 * Returns the specified element.
	 */
	public static SOAPElement extractSOAPElement(
		SOAPElement parent, String elementName, String ebNameSpace)
		throws SOAPException
	{
		NodeList header = parent.getElementsByTagNameNS(ebNameSpace, elementName);
		if (header.getLength() < 1) { throw new SOAPException("No " + elementName + " found."); }
		SOAPElement element = (SOAPElement) header.item(0);
		return element;
	}
	
	/** 
	 * Returns the single text child from the specified element.
	 */
	public static String extractSOAPElementText(
		SOAPElement parent, String elementName, String ebNameSpace)
		throws SOAPException
	{
		SOAPElement element = extractSOAPElement(parent, elementName, ebNameSpace);
		if (element != null) {
			Node text = element.getFirstChild();
			if (text != null && text.getNodeType() == Node.TEXT_NODE) {
				return text.getNodeValue();
			}
		}
		return "";
	}
	
	public static String extractEbPartyName(
		SOAPElement parent, String partyType, String ebNameSpace)
		throws SOAPException
	{
		SOAPElement partyElement = extractSOAPElement(parent, partyType, ebNameSpace);
		String name = extractSOAPElementText(partyElement, "PartyId", ebNameSpace);
		if (name.toLowerCase().startsWith("mailto:")) name = name.substring(7);
		return name;
	}
}