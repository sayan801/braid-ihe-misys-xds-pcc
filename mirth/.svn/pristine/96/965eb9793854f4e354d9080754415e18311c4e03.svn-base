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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class is a wrapper to make the ebXML rim:RegistryPackage
 * representation of an XDSSubmissionSet look like a simple Java
 * object.
 * <p>
 * This class can extract data the XML DOM returned from the
 * registry in query results and it can generate the XML DOM
 * needed to make a submission.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 11, 2005
 */
public class XdsSubmissionSet {

	/* A logger for error and debug messages */
	private static final Logger log = Logger.getLogger(XdsSubmissionSet.class);
	
	/* XDS UUIDs to indentify XDS Submission Set components */
	private static final String XDS_SUBMISSION_SET = "urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd";

	private static final String XDS_SUBMISSION_SET_UNIQUE_ID = "urn:uuid:96fdda7c-d067-4183-912e-bf5ee74998a8";
	private static final String XDS_SUBMISSION_SET_PATIENT_ID = "urn:uuid:6b5aea1a-874d-4603-a4bc-96a0a7b38446";
	private static final String XDS_SUBMISSION_SET_SOURCE_ID = "urn:uuid:554ac39e-e3fe-47fe-b233-965d2a147832";
	
	private static final String XDS_SUBMISSION_SET_CONTENT_TYPE_CODE = "urn:uuid:aa543740-bdda-424e-8c96-df4873be8500";
    private static final String XDS_SUBMISSION_SET_AUTHOR_PERSON = "urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d";

	/* XDSSubmissionSet values */
    private List<XdsAuthor> authors = new ArrayList<XdsAuthor>();
    private List<XdrIntendedRecipient> intendedRecipients = new ArrayList<XdrIntendedRecipient>();
	private String comments = null;
	private String submissionTime = null;
	
	private String contentTypeCode = null;
	private String contentTypeCodeDisplayName = null;
	
	private String patientId = null;
	private String sourceId = null;
	private String uniqueId = null;
	
	/**
	 * Construct a new, empty XdsSubmissionSet.
	 */
	public XdsSubmissionSet() {
	}
	
	/**
	 * Construct an XdsSubmissionSet from an XML DOM describing
	 * the right type of ebRIM RegistryPackage.
	 * 
	 * @param xml The XML DOM for the RegistryPackage
	 * @throws XdsRimException When this XML does not describe an XdsSubmissionSet
	 */
	public XdsSubmissionSet(Element xml) throws XdsRimException {
		this();
//		String type = RimXml.getAttributeValue(xml, "objectType");
//		if (!XDS_DOCUMENT_ENTRY.equals(type)) {
//			throw new XdsRegistryObjectException("Not an XDSDocumentEntry: \"" + xml.toString());
//		}
		initializeFromDom(xml);
	}
	
//	/**
//	 * Construct an XdsSubmissionSet from an XML Axiom describing
//	 * the right type of ebRIM RegistryPackage.
//	 * 
//	 * @param xml The XML Axiom for the RegistryPackage
//	 * @throws XdsRimException When this XML does not describe an XdsSubmissionSet
//	 */
//	public XdsSubmissionSet(OMElement xml) throws XdsRimException {
//		this();
//		initializeFromAxiom(xml);
//	}
	
    /**
     * Get a list of authors
     * @return The list of XdsAuthor
     */
     public List<XdsAuthor> getAuthors() {
         return authors;
     }

    /**
     * Add an author to the existing author list.
     * @param author The XdsAuthor to be added
     */
     public void addAuthor(XdsAuthor author){
         this.authors.add(author);
     }

     public void setIntendedRecipients(List<XdrIntendedRecipient> intendedRecipient) {
        this.intendedRecipients = intendedRecipient;
     }
	

     public List<XdrIntendedRecipient> getIntendedRecipients() {
         return intendedRecipients;
	 }

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the contentTypeCode.
	 */
	public String getContentTypeCode() {
		return contentTypeCode;
	}

	/**
	 * @param contentTypeCode The contentTypeCode to set.
	 */
	public void setContentTypeCode(String contentTypeCode) {
		this.contentTypeCode = contentTypeCode;
	}

	/**
	 * @return Returns the contentTypeCodeDisplayName.
	 */
	public String getContentTypeCodeDisplayName() {
		return contentTypeCodeDisplayName;
	}

	/**
	 * @param contentTypeCodeDisplayName The contentTypeCodeDisplayName to set.
	 */
	public void setContentTypeCodeDisplayName(String contentTypeCodeDisplayName) {
		this.contentTypeCodeDisplayName = contentTypeCodeDisplayName;
	}

	/**
	 * @return Returns the patientId.
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId The patientId to set.
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return Returns the sourceId.
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId The sourceId to set.
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return Returns the submissionTime.
	 */
	public String getSubmissionTime() {
		return submissionTime;
	}

	/**
	 * @param submissionTime The submissionTime to set.
	 */
	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	/**
	 * @return Returns the uniqueId.
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId The uniqueId to set.
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/* -------------------------------------------------------------------- */
	/* Extract fields from an XML DOM representation of an XDSSubmissionSet */
	
	/**
	 * Extract all the information for an XDSSubmissionSet from an
	 * XML DOM version of an ebRim RegistryPackage.
	 * 
	 * @param root The XML DOM for the RegistryPackage
	 * @return True if the DOM was used successfully
	 */
	private boolean initializeFromDom(Element root) {
		// Grab the title from the <Name> element
		Element name = RimXml.getRimElement(root, "Description", XmlRegistry.XDS_RIM_NAMESPACE);
		if (name != null) {
			Element value = RimXml.getRimElement(name, "LocalizedString", XmlRegistry.XDS_RIM_NAMESPACE);
			if (value != null) {
				comments = RimXml.getAttributeValue(value, "value");
			}
		}
		// Pull out slot values
		initializeFromSlotElements(root);
		// Grab out classification values
		initializeFromClassificationElements(root);
		// Grab out external identifier values
		initializeFromExternalIdentifierElements(root);
		return true;
	}

//	/**
//	 * Extract all the information for an XDSSubmissionSet from an
//	 * XML Axiom version of an ebRim RegistryPackage.
//	 * 
//	 * @param root The XML Axiom for the RegistryPackage
//	 * @return True if the Axiom was used successfully
//	 */
//	private boolean initializeFromAxiom(OMElement root) {
//		// Grab the title from the <Name> element
//		OMElement name = RimAxiom.getRimElement(root, "Description", XmlRegistry.XDS_RIM_V3_NAMESPACE);
//		if (name != null) {
//			OMElement value = RimAxiom.getRimElement(name, "LocalizedString", XmlRegistry.XDS_RIM_V3_NAMESPACE);
//			if (value != null) {
//				comments = RimAxiom.getAttributeValue(value, "value");
//			}
//		}
//		// Pull out slot values
//		initializeFromSlotElements(root);
//		// Grab out classification values
//		initializeFromClassificationElements(root);
//		// Grab out external identifier values
//		initializeFromExternalIdentifierElements(root);
//		return true;
//	}	
	/**
	 * Grab out all the information for the XDSSubmissionSet that
	 * is represented using ebRIM Slots.
	 * 
	 * @param root The XML DOM for the RegistryPackage
	 */
	private void initializeFromSlotElements(Element root) {
		// Get all of the slots
		NodeList slots = RimXml.getRimSlots(root, XmlRegistry.XDS_RIM_NAMESPACE);
		for (int i=0; i<slots.getLength(); i++) {
			Node slot = slots.item(i);
			// Pull out the values from each one
			if (slot instanceof Element) {
				Element theSlot = (Element) slot;
				String theName = RimXml.getAttributeValue(theSlot, "name");
				if (theName != null) {
					if (theName.equals("submissionTime")) submissionTime = RimXml.getRimSlotValue(theSlot, XmlRegistry.XDS_RIM_NAMESPACE);
					else {
						// Don't generate an error, there are other slots we don't use
						log.debug("Unused slot in XDSSubmissionSet" + theSlot.toString());
					}
				}
			}
		}
	}

//	/**
//	 * Grab out all the information for the XDSSubmissionSet that
//	 * is represented using ebRIM Slots.
//	 * 
//	 * @param root The XML Axiom for the RegistryPackage
//	 */
//	private void initializeFromSlotElements(OMElement root) {
//		// Get all of the slots
//		Iterator slots = RimAxiom.getRimSlots(root, XmlRegistry.XDS_RIM_V3_NAMESPACE);
//		for (; slots.hasNext(); ) {
//			Object slot = slots.next();
//			// Pull out the values from each one
//			if (slot instanceof OMElement) {
//				OMElement theSlot = (OMElement) slot;
//				String theName = RimAxiom.getAttributeValue(theSlot, "name");
//				if (theName != null) {
//					if (theName.equals("submissionTime")) submissionTime = RimAxiom.getRimSlotValue(theSlot, XmlRegistry.XDS_RIM_V3_NAMESPACE);
//					else {
//						// Don't generate an error, there are other slots we don't use
//						log.debug("Unused slot in XDSSubmissionSet" + theSlot.toString());
//					}
//				}
//			}
//		}
//	}

	/**
	 * Grab out all the information for the XDSSubmissionSet that
	 * is represented using ebRIM Classifications.
	 * 
	 * @param root The XML DOM for the RegistryPackage
	 */
	private void initializeFromClassificationElements(Element root) {
		// Get all of the classifications
		NodeList classes = RimXml.getRimClassifications(root, XmlRegistry.XDS_RIM_NAMESPACE);
		for (int i=0; i<classes.getLength(); i++) {
			Node classification = classes.item(i);
			// Pull out the values from each one
			if (classification instanceof Element) {
				Element theClassification = (Element) classification;
				String theScheme = RimXml.getAttributeValue(theClassification, "classificationScheme");
				if (theScheme != null) {
                    if (theScheme.equals(XDS_SUBMISSION_SET_AUTHOR_PERSON) ) {
                        XdsAuthor author = XdsAuthor.getAuthorFromClassificationElements(theClassification, XmlRegistry.XDS_RIM_NAMESPACE);
                        authors.add( author );
                    } else if (theScheme.equals(XDS_SUBMISSION_SET_CONTENT_TYPE_CODE)) {
						contentTypeCode = RimXml.getRimClassificationCode(theClassification);
						contentTypeCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, XmlRegistry.XDS_RIM_NAMESPACE);
					} else {
						// We should have pulled out all the allowed classifications
						log.debug("Unexpected classification type in XDSSubmissionSet" + theClassification.toString());
					}
				}
			}
		}
	}

	/**
	 * Grab out all the information for the XDSSubmissionSet that
	 * is represented using ebRIM ExternalIdentifiers.
	 * 
	 * @param root The XML DOM for the RegistryPackage
	 */
	private void initializeFromExternalIdentifierElements(Element root) {
		// Get all of the identifier
		NodeList ids = RimXml.getRimExternalIdentifiers(root, XmlRegistry.XDS_RIM_NAMESPACE);
		for (int i=0; i<ids.getLength(); i++) {
			Node identifier = ids.item(i);
			if (identifier instanceof Element) {
				Element theIdentifier = (Element) identifier;
				String theScheme = RimXml.getAttributeValue(theIdentifier, "identificationScheme");
				if (theScheme != null) {
					if (theScheme.equals(XDS_SUBMISSION_SET_UNIQUE_ID)) {
						uniqueId = RimXml.getRimExternalIdentifierValue(theIdentifier);
					} else if (theScheme.equals(XDS_SUBMISSION_SET_PATIENT_ID)) {
						patientId = RimXml.getRimExternalIdentifierValue(theIdentifier);
					} else if (theScheme.equals(XDS_SUBMISSION_SET_PATIENT_ID)) {
						patientId = RimXml.getRimExternalIdentifierValue(theIdentifier);
					} else if (theScheme.equals(XDS_SUBMISSION_SET_SOURCE_ID)) {
						sourceId = RimXml.getRimExternalIdentifierValue(theIdentifier);
					} else {
						// We should have pulled out all the allowed classifications
						log.debug("Unexpected external identifier type in XDSSubmissionSet" + theIdentifier.toString());
					}
				}
			}
		}
	}

	/* -------------------------------------------------------------------- */
	/* Check whether enough fields are filled for this to be valid */
	
	/**
	 * Check whether this XDSSubmissionSet holds the values required
	 * for it to generate valid XDS metadata.
	 * 
	 * @return True if all the required values are present
	 */
	public boolean holdsValidMetadata() {
		boolean okay = true;
		okay = okay && (contentTypeCode != null);
		okay = okay && (submissionTime != null);
		okay = okay && (patientId != null);
		okay = okay && (sourceId != null);
		okay = okay && (uniqueId != null);
		return okay;
	}

	/* ----------------------------------------------------------------- */
	/* Generate an XML DOM representation from an XDSSubmissionSet */
	
	/**
	 * Get an XDSSubmissionSet as an
	 * XML DOM version of an ebRim RegistryPackage.
	 * 
	 * @param id The id to give this XDSSubmissionSet
	 * @param connection The connection that this XDSSubmissionSet will be sent to
	 * @return The XML DOM holding this XDSSubmissionSet
	 * @throws XdsRimException When this XDSSubmissionSet cannot be turned into valid metadata
	 * @throws SOAPException When a SOAP implementation cannot be found
	 */
	public SOAPElement getAsXmlDom(String id, IConnectionDescription connection) throws XdsRimException, SOAPException {
		// First, create the Extrinsic object
		RimSoap xml= RimSoap.newXdsSubmissionSetBuilder(id, connection, log);
		// Next, add the description
		xml.addDescription(comments, false);
		// Now a bunch of slots
		xml.addSlot("submissionTime", submissionTime, true);
		// Add intended recipients if there are any (for offline mode)
		// TODO think about a better place to stick this conversion...
		List<String> intendedRecipientStrings = null;
		if (intendedRecipients != null && !intendedRecipients.isEmpty()) {
			intendedRecipientStrings = new ArrayList<String>();
			for (XdrIntendedRecipient intendedRecipient: intendedRecipients) 
				intendedRecipientStrings.add(intendedRecipient.getCodedValue());
		}
		xml.addSlot("intendedRecipient", intendedRecipientStrings, false);
		// Some classifications
        //Add Author Classification   - implement XDS CP-122
        for (XdsAuthor author : authors) {
            xml.addClassification("authorPerson", author.getAuthorPerson(), author.getAuthorInfo(), XDS_SUBMISSION_SET_AUTHOR_PERSON, false);
        }
		xml.addClassification("contentTypeCode", contentTypeCode, XDS_SUBMISSION_SET_CONTENT_TYPE_CODE, true);
		// And a couple of identifiers
		xml.addExternalIdentifier("XDSSubmissionSet.patientId", patientId, XDS_SUBMISSION_SET_PATIENT_ID, true);
		xml.addExternalIdentifier("XDSSubmissionSet.sourceId", sourceId, XDS_SUBMISSION_SET_SOURCE_ID, true);
		xml.addExternalIdentifier("XDSSubmissionSet.uniqueId", uniqueId, XDS_SUBMISSION_SET_UNIQUE_ID, true);
		// Done
		return xml.getSoapElement();
	}
	
	/**
	 * Get an XDSSubmissionSet as an
	 * XML Axiom version of an ebRim RegistryPackage.
	 * 
	 * @param id The id to give this XDSSubmissionSet
	 * @param connection The connection that this XDSSubmissionSet will be sent to
	 * @return The XML Axiom OMElement holding this XDSSubmissionSet
	 * @throws XdsRimException When this XDSSubmissionSet cannot be turned into valid metadata
	 */
	public OMElement getAsXmlAxiom(String id, IConnectionDescription connection) throws XdsRimException {
		// First, create the RegistryPackage object
		RimAxiom xml= RimAxiom.newXdsSubmissionSetBuilder(id, connection, log);
		// Now a bunch of slots
		xml.addSlot("submissionTime", submissionTime, true);
		// Add intended recipients if there are any (for offline mode)
		// TODO think about a better place to stick this conversion...
		List<String> intendedRecipientStrings = null;
		if (intendedRecipients != null && !intendedRecipients.isEmpty()) {
			intendedRecipientStrings = new ArrayList<String>();
			for (XdrIntendedRecipient intendedRecipient: intendedRecipients) 
				intendedRecipientStrings.add(intendedRecipient.getCodedValue());
		}
		xml.addSlot("intendedRecipient", intendedRecipientStrings, false);
		//Next add name
        xml.addName("Submission Set", true);
		// Next, add the description
		xml.addDescription(comments, false);
		// Some classifications
        int index=1;
		//Add Author Classification   - implement XDS CP-122
        for (XdsAuthor author : authors) {
            xml.addClassification("authorPerson", author.getAuthorPerson(), author.getAuthorInfo(), XDS_SUBMISSION_SET_AUTHOR_PERSON, "cl-ss"+index++, false);
        }
		xml.addClassification("contentTypeCode", contentTypeCode, XDS_SUBMISSION_SET_CONTENT_TYPE_CODE, "cl-ss"+index++, true);
		// And a couple of identifiers
		index = 1;
		xml.addExternalIdentifier("XDSSubmissionSet.patientId", patientId, XDS_SUBMISSION_SET_PATIENT_ID, id, "ei-ss"+index++, true);
		xml.addExternalIdentifier("XDSSubmissionSet.sourceId", sourceId, XDS_SUBMISSION_SET_SOURCE_ID, id, "ei-ss"+index++, true);
		xml.addExternalIdentifier("XDSSubmissionSet.uniqueId", uniqueId, XDS_SUBMISSION_SET_UNIQUE_ID, id, "ei-ss"+index++, true);
		// Done
		return xml.getRootElement();
	}
	
	/**
	 * Get the XDS Registry classification node for this registry object type
	 * @return The classification node UUID
	 */
	public String getClassificationNode() {
		return XDS_SUBMISSION_SET;
	}
	
	/**
	 * Get the set of XDS Registry object references used by the metadata in this registry object
	 * @return The registry object references UUIDs
	 */
	public Collection<String> getObjectReferences() {
		ArrayList<String> refs = new ArrayList<String>();
		refs.add(XDS_SUBMISSION_SET);
        if (authors.size() > 0) refs.add(XDS_SUBMISSION_SET_AUTHOR_PERSON);
        if (contentTypeCode != null) refs.add(XDS_SUBMISSION_SET_CONTENT_TYPE_CODE);
		if (patientId != null) refs.add(XDS_SUBMISSION_SET_PATIENT_ID);
		if (sourceId != null) refs.add(XDS_SUBMISSION_SET_SOURCE_ID);
		if (uniqueId != null) refs.add(XDS_SUBMISSION_SET_UNIQUE_ID);
		return refs;
	}
}
