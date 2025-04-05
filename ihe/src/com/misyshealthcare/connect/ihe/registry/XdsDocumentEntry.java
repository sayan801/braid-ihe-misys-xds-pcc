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
import java.util.List;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.Pair;


/**
 * This class is a wrapper to make the ebXML rim:ExtrinsicObject
 * representation of an XDSDocumentEntry look like a simple Java
 * object.
 * <p>
 * This class can extract data from the XML DOM returned from the
 * registry in SOAP query results and it can generate the SOAP XML DOM
 * needed to make a submission.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 5, 2005
 */
public class XdsDocumentEntry {

    /* A logger for error and debug messages */
    private static final Logger log = Logger.getLogger(XdsDocumentEntry.class.getName());

    /* XDS UUIDs to indentify XDS Document Entry components */
    static final String XDS_DOCUMENT_ENTRY = "urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1";

    private static final String XDS_DOCUMENT_ENTRY_UNIQUE_ID = "urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab";
    private static final String XDS_DOCUMENT_ENTRY_PATIENT_ID = "urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427";

    private static final String XDS_DOCUMENT_ENTRY_CLASS_CODE = "urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a";
    private static final String XDS_DOCUMENT_ENTRY_FORMAT_CODE = "urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d";
    private static final String XDS_DOCUMENT_ENTRY_PRACTICE_SETTING_CODE = "urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead";
    private static final String XDS_DOCUMENT_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE = "urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1";
    private static final String XDS_DOCUMENT_ENTRY_CONFIDENTIALITY_CODE = "urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f";
    private static final String XDS_DOCUMENT_ENTRY_TYPE_CODE = "urn:uuid:f0306f51-975f-434e-a61c-c59651d33983";
    private static final String XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST = "urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4";
    private static final String XDS_DOCUMENT_ENTRY_AUTHOR_PERSON = "urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d";

    /* XDSDocumentEntry values */
    private String entryUuid = null;
    private String mimeType = null;
    private String title = null;

    List<XdsAuthor> authors = new ArrayList<XdsAuthor>();
    private String creationTime = null;
    private String languageCode = null;
    private String legalAuthenticator = null;
    private String serviceStartTime = null;
    private String serviceStopTime = null;
    private String sourcePatientId = null;
    private List<String> sourcePatientInfo = new ArrayList<String>();
    private String uri = null;

    private String classCode = null;
    private String classCodeDisplayName = null;
    private String formatCode = null;
    private String formatCodeDisplayName = null;
    private String practiceSettingCode = null;
    private String practiceSettingCodeDisplayName = null;
    private String healthcareFacilityTypeCode = null;
    private String healthcareFacilityTypeCodeDisplayName = null;
    private String typeCode = null;
    private String typeCodeDisplayName = null;
    private List<String> eventCodes = null;
    private List<String> eventCodeDisplayNames = null;
    /**Pair<String(confidentialityCode), String(confidentialityCodeDisplayName)>*/
    private List<Pair> confidentialityCodes = new ArrayList<Pair>();

    private String patientId = null;
    private String uniqueId = null;
    private String repositoryUniqueId = null;

    /* Used by the XDS actors to link documents to their replacements */
    private String parentDocumentUUID = null;

    /* Used by the XDS actors to link content to metadata */
    private Object content = null;

    /**
     * Construct a new, empty XdsDocumentEntry.
     */
    public XdsDocumentEntry() {
        languageCode = "en-us";
    }

    /**
     * Construct an XdsDocumentEntry from an XML DOM describing
     * the right type of ebRIM ExtrinsicObject.
     *
     * @param xml The XML DOM for the ExtrinsicObject
     * @throws XdsRimException When this XML does not describe an XDSDocumentEntry
     */
    public XdsDocumentEntry(Element xml, String rimNameSpace) throws XdsRimException {
        this();
        String type = RimXml.getAttributeValue(xml, "objectType");
        if (!XDS_DOCUMENT_ENTRY.equals(type)) {
            throw new XdsRimException("Not an XDSDocumentEntry: \"" + xml.toString());
        }
        languageCode = "en-us";
        initializeFromDom(xml, rimNameSpace);
    }

    /* ---------------------------------------------------------------------------------------------- */
  /* Getters and setters */

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

    /**
     * @return Returns the classCode.
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * @param classCode The classCode to set.
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * @return Returns the classCodeDisplayName.
     */
    public String getClassCodeDisplayName() {
        return classCodeDisplayName;
    }

    /**
     * @param classCodeDisplayName The classCodeDisplayName to set.
     */
    public void setClassCodeDisplayName(String classCodeDisplayName) {
        this.classCodeDisplayName = classCodeDisplayName;
    }

    /**
     * @return Returns the confidentialityCodes.
     * Pair<String(confidentialityCode), String(confidentialityCodeDisplayName)>
     */
    public List<Pair> getConfidentialityCodes() {
        return confidentialityCodes;
    }

    /**
     * @param confidentialityCodes a list of Pair<String(confidentialityCode), String(confidentialityCodeDisplayName)> of codes to set
     */
    public void setConfidentialityCodes(List<Pair> confidentialityCodes) {
        this.confidentialityCodes = confidentialityCodes;
    }

    /**
     * Get the content corresponding to this metadata.  This is used by internal IHE actor
     * processing and is not set or used by the XML parser and generator for this object.
     * @return Returns the content.
     */
    public Object getContent() {
        return content;
    }

    /**
     * Set the content corresponding to this metadata.  This is used by internal IHE actor
     * processing and is not set or used by the XML parser and generator for this object.
     * @param content The content to set.
     */
    public void setContent(Object content) {
        this.content = content;
    }

    /**
     * @return Returns the creationTime.
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime The creationTime to set.
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return Returns the entryUuid.
     */
    public String getEntryUuid() {
        return entryUuid;
    }

    /**
     * @param entryUuid The entryUuid to set.
     */
    public void setEntryUuid(String entryUuid) {
        this.entryUuid = entryUuid;
    }

    /**
     * @return Returns the eventCodeDisplayNames.
     */
    public List<String> getEventCodeDisplayNames() {
        return eventCodeDisplayNames;
    }

    /**
     * @param eventCodeDisplayNames The eventCodeDisplayNames to set.
     */
    public void setEventCodeDisplayNames(List<String> eventCodeDisplayNames) {
        this.eventCodeDisplayNames = eventCodeDisplayNames;
    }

    /**
     * @return Returns the eventCodes.
     */
    public List<String> getEventCodes() {
        return eventCodes;
    }

    /**
     * @param eventCodes The eventCodes to set.
     */
    public void setEventCodes(List<String> eventCodes) {
        this.eventCodes = eventCodes;
    }
    /**
     * @param eventCode The eventCode to add.
     */
    public void addEventCode(String eventCode) {
        if (this.eventCodes == null) this.eventCodes = new ArrayList<String>();
        this.eventCodes.add( eventCode );
    }
    /**
     * @return Returns the formatCode.
     */
    public String getFormatCode() {
        return formatCode;
    }

    /**
     * @param formatCode The formatCode to set.
     */
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    /**
     * @return Returns the formatCodeDisplayName.
     */
    public String getFormatCodeDisplayName() {
        return formatCodeDisplayName;
    }

    /**
     * @param formatCodeDisplayName The formatCodeDisplayName to set.
     */
    public void setFormatCodeDisplayName(String formatCodeDisplayName) {
        this.formatCodeDisplayName = formatCodeDisplayName;
    }

    /**
     * @return Returns the healthcareFacilityTypeCode.
     */
    public String getHealthcareFacilityTypeCode() {
        return healthcareFacilityTypeCode;
    }

    /**
     * @param healthcareFacilityTypeCode The healthcareFacilityTypeCode to set.
     */
    public void setHealthcareFacilityTypeCode(String healthcareFacilityTypeCode) {
        this.healthcareFacilityTypeCode = healthcareFacilityTypeCode;
    }

    /**
     * @return Returns the healthcareFacilityTypeCodeDisplayName.
     */
    public String getHealthcareFacilityTypeCodeDisplayName() {
        return healthcareFacilityTypeCodeDisplayName;
    }

    /**
     * @param healthcareFacilityTypeCodeDisplayName The healthcareFacilityTypeCodeDisplayName to set.
     */
    public void setHealthcareFacilityTypeCodeDisplayName(
            String healthcareFacilityTypeCodeDisplayName) {
        this.healthcareFacilityTypeCodeDisplayName = healthcareFacilityTypeCodeDisplayName;
    }

    /**
     * @return Returns the languageCode.
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * @param languageCode The languageCode to set.
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @return Returns the legalAuthenticator.
     */
    public String getLegalAuthenticator() {
        return legalAuthenticator;
    }

    /**
     * @param legalAuthenticator The legalAuthenticator to set.
     */
    public void setLegalAuthenticator(String legalAuthenticator) {
        this.legalAuthenticator = legalAuthenticator;
    }

    /**
     * @return Returns the mimeType.
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType The mimeType to set.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return Returns the parentDocumentUUID.
     */
    public String getParentDocumentUUID() {
        return parentDocumentUUID;
    }

    /**
     * @param parentDocumentUUID The parentDocumentUUID to set.
     */
    public void setParentDocumentUUID(String parentDocumentUUID) {
        this.parentDocumentUUID = parentDocumentUUID;
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
     * @return Returns the practiceSettingCode.
     */
    public String getPracticeSettingCode() {
        return practiceSettingCode;
    }

    /**
     * @param practiceSettingCode The practiceSettingCode to set.
     */
    public void setPracticeSettingCode(String practiceSettingCode) {
        this.practiceSettingCode = practiceSettingCode;
    }

    /**
     * @return Returns the practiceSettingCodeDisplayName.
     */
    public String getPracticeSettingCodeDisplayName() {
        return practiceSettingCodeDisplayName;
    }

    /**
     * @param practiceSettingCodeDisplayName The practiceSettingCodeDisplayName to set.
     */
    public void setPracticeSettingCodeDisplayName(
            String practiceSettingCodeDisplayName) {
        this.practiceSettingCodeDisplayName = practiceSettingCodeDisplayName;
    }

    /**
     * @return Returns the serviceStartTime.
     */
    public String getServiceStartTime() {
        return serviceStartTime;
    }

    /**
     * @param serviceStartTime The serviceStartTime to set.
     */
    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    /**
     * @return Returns the serviceStopTime.
     */
    public String getServiceStopTime() {
        return serviceStopTime;
    }

    /**
     * @param serviceStopTime The serviceStopTime to set.
     */
    public void setServiceStopTime(String serviceStopTime) {
        this.serviceStopTime = serviceStopTime;
    }

    /**
     * @return Returns the sourcePatientId.
     */
    public String getSourcePatientId() {
        return sourcePatientId;
    }

    /**
     * @param sourcePatientId The sourcePatientId to set.
     */
    public void setSourcePatientId(String sourcePatientId) {
        this.sourcePatientId = sourcePatientId;
    }

    /**
     * @return Returns the sourcePatientInfo.
     */
    public Collection<String> getSourcePatientInfo() {
        return sourcePatientInfo;
    }

    /**
     * @param sourcePatientInfo The sourcePatientInfo to set.
     */
    public void setSourcePatientInfo(List<String> sourcePatientInfo) {
        this.sourcePatientInfo = sourcePatientInfo;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the typeCode.
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode The typeCode to set.
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @return Returns the typeCodeDisplayName.
     */
    public String getTypeCodeDisplayName() {
        return typeCodeDisplayName;
    }

    /**
     * @param typeCodeDisplayName The typeCodeDisplayName to set.
     */
    public void setTypeCodeDisplayName(String typeCodeDisplayName) {
        this.typeCodeDisplayName = typeCodeDisplayName;
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
    
    /**
	 * @return the repositoryId
	 */
	public String getRepositoryId() {
		return repositoryUniqueId;
	}

	/**
	 * @param repositoryId the repositoryId to set
	 */
	public void setRepositoryId(String repositoryId) {
		this.repositoryUniqueId = repositoryId;
	}

	/**
     * @return Returns the uri.
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri The uri to set.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /* -------------------------------------------------------------------- */
    /* Extract fields from an XML DOM representation of an XDSDocumentEntry */

    /**
     * Extract all the information for an XDSDocumentEntry from an
     * XML DOM version of an ebRim ExtrinsicObject.
     *
     * @param root The XML DOM for the ExtrinsicObject
     * @param rimNameSpace the rim name space of the root element
     * @return True if the DOM was used successfully
     */
    private boolean initializeFromDom(Element root, String rimNameSpace) {
        // Pull out the attributes
        entryUuid = RimXml.getAttributeValue(root, "id");
        mimeType = RimXml.getAttributeValue(root, "mimeType");
        // Grab the title from the <Name> element
        Element name = RimXml.getRimElement(root, "Name", rimNameSpace);
        if (name != null) {
            Element value = RimXml.getRimElement(name, "LocalizedString", rimNameSpace);
            if (value != null) {
                title = RimXml.getAttributeValue(value, "value");
            }
        }
        // Pull out slot values
        initializeFromSlotElements(root, rimNameSpace);
        // Grab out classification values
        initializeFromClassificationElements(root, rimNameSpace);
        // Grab out external identifier values
        initializeFromExternalIdentifierElements(root, rimNameSpace);
        return true;
    }

    /**
     * Grab out all the information for the XDSDocumentEntry that
     * is represented using ebRIM Slots.
     *
     * @param root The XML DOM for the ExtrinsicObject
     * @param rimNameSpace the rim name space of the root element
     */
    private void initializeFromSlotElements(Element root, String rimNameSpace) {
        // Get all of the slots
        NodeList slots = RimXml.getRimSlots(root, rimNameSpace);
        for (int i=0; i<slots.getLength(); i++) {
            Node slot = slots.item(i);
            // Pull out the values from each one
            if (slot instanceof Element) {
                Element theSlot = (Element) slot;
                String theName = RimXml.getAttributeValue(theSlot, "name");
                if (theName != null) {
//                    if (theName.equals("authorInstitution")) authorInstitution = RimXml.getRimSlotValue(theSlot, rimNameSpace);
//                    else if (theName.equals("authorPerson")) authorPersons = RimXml.getRimSlotValues(theSlot, rimNameSpace);
//                    else if (theName.equals("authorRole")) authorRole = RimXml.getRimSlotValue(theSlot, rimNameSpace);
//                    else if (theName.equals("authorSpecialty")) authorSpecialty = RimXml.getRimSlotValue(theSlot, rimNameSpace);
//                    else
                    if (theName.equals("creationTime")) creationTime = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("languageCode")) languageCode = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("legalAuthenticator")) legalAuthenticator = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("serviceStartTime")) serviceStartTime = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("serviceStopTime")) serviceStopTime = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("sourcePatientId")) sourcePatientId = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("sourcePatientInfo")) sourcePatientInfo = RimXml.getRimSlotValues(theSlot, rimNameSpace);
                    else if (theName.equals("URI")) uri = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else if (theName.equals("repositoryUniqueId")) repositoryUniqueId = RimXml.getRimSlotValue(theSlot, rimNameSpace);
                    else {
                        // Don't generate an error, there are other slots we don't use
                        log.debug("Unused slot in XDSDocumentEntry" + theSlot.toString());
                    }
                }
            }
        }
    }

    /**
     * Grab out all the information for the XDSDocumentEntry that
     * is represented using ebRIM Classifications.
     *
     * @param root The XML DOM for the ExtrinsicObject
     * @param rimNameSpace the rim name space of the root element
     */
    private void initializeFromClassificationElements(Element root, String rimNameSpace) {
        // Get all of the classifications
        NodeList classes = RimXml.getRimClassifications(root, rimNameSpace);
        for (int i=0; i<classes.getLength(); i++) {
            Node classification = classes.item(i);
            // Pull out the values from each one
            if (classification instanceof Element) {
                Element theClassification = (Element) classification;
                String theScheme = RimXml.getAttributeValue(theClassification, "classificationScheme");
                if (theScheme != null) {
                    if (theScheme.equals(XDS_DOCUMENT_ENTRY_AUTHOR_PERSON) ) {
                        XdsAuthor author = XdsAuthor.getAuthorFromClassificationElements(theClassification, rimNameSpace);
                        authors.add( author );
                    }
                    if (theScheme.equals(XDS_DOCUMENT_ENTRY_CLASS_CODE)) {
                        classCode = RimXml.getRimClassificationCode(theClassification);
                        classCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, rimNameSpace);
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_FORMAT_CODE)) {
                        formatCode = RimXml.getRimClassificationCode(theClassification);
                        formatCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, rimNameSpace);
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_PRACTICE_SETTING_CODE)) {
                        practiceSettingCode = RimXml.getRimClassificationCode(theClassification);
                        practiceSettingCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, rimNameSpace);
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE)) {
                        healthcareFacilityTypeCode = RimXml.getRimClassificationCode(theClassification);
                        healthcareFacilityTypeCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, rimNameSpace);
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_TYPE_CODE)) {
                        typeCode = RimXml.getRimClassificationCode(theClassification);
                        typeCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, rimNameSpace);
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_CONFIDENTIALITY_CODE)) {
                        String confidentialityCode = RimXml.getRimClassificationCode(theClassification);
                        String confidentialityCodeDisplayName = RimXml.getRimClassificationCodeDisplayName(theClassification, rimNameSpace);
                        confidentialityCodes.add(new Pair(confidentialityCode, confidentialityCodeDisplayName));
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST)) {
                        // There may be many event codes, grab them all
                        if (eventCodes == null) {
                            eventCodes = new ArrayList<String>();
                            eventCodeDisplayNames = new ArrayList<String>();
                        }
                        String value = RimXml.getRimClassificationCode(theClassification);
                        if (value != null) {
                            eventCodes.add(value);
                            value = RimXml.getRimClassificationCode(theClassification);
                            if (value != null) value = "";
                            eventCodeDisplayNames.add(value);
                        }
                    }
                    else {
                        // We should have pulled out all the allowed classifications
                        log.debug("Unexpected classification type in XDSDocumentEntry" + theClassification.toString());
                    }
                }
            }
        }
    }


    /**
     * Grab out all the information for the XDSDocumentEntry that
     * is represented using ebRIM ExternalIdentifiers.
     *
     * @param root The XML DOM for the ExtrinsicObject
     * @param rimNameSpace the rim name space of the root element
     */
    private void initializeFromExternalIdentifierElements(Element root, String rimNameSpace) {
        // Get all of the identifier
        NodeList ids = RimXml.getRimExternalIdentifiers(root, rimNameSpace);
        for (int i=0; i<ids.getLength(); i++) {
            Node identifier = ids.item(i);
            if (identifier instanceof Element) {
                Element theIdentifier = (Element) identifier;
                String theScheme = RimXml.getAttributeValue(theIdentifier, "identificationScheme");
                if (theScheme != null) {
                    if (theScheme.equals(XDS_DOCUMENT_ENTRY_UNIQUE_ID)) {
                        uniqueId = RimXml.getRimExternalIdentifierValue(theIdentifier);
                    }
                    else if (theScheme.equals(XDS_DOCUMENT_ENTRY_PATIENT_ID)) {
                        patientId = RimXml.getRimExternalIdentifierValue(theIdentifier);
                    }
                    else {
                        // We should have pulled out all the allowed classifications
                        log.debug("Unexpected external identifier type in XDSDocumentEntry" + theIdentifier.toString());
                    }
                }
            }
        }
    }

    /* -------------------------------------------------------------------- */
    /* Check whether enough fields are filled for this to be valid */

    public boolean holdsValidMetadata() {
        boolean okay = true;
        okay = okay && (classCode != null);
        okay = okay && (confidentialityCodes.size() > 0);
        okay = okay && (creationTime != null);
        okay = okay && (formatCode != null);
        okay = okay && (healthcareFacilityTypeCode != null);
        okay = okay && (languageCode != null);
        okay = okay && (mimeType != null);
        okay = okay && (patientId != null);
        okay = okay && (practiceSettingCode != null);
        okay = okay && (sourcePatientId != null);
        okay = okay && (sourcePatientInfo.size() >= 5);
        okay = okay && (typeCode != null);
        okay = okay && (uniqueId != null);
        return okay;
    }

    /* ----------------------------------------------------------------- */
    /* Generate an XML DOM representation from an XDSDocumentEntry */

    /**
     * Get an XDSDocumentEntry as an
     * XML DOM version of an ebRim ExtrinsicObject.
     *
     * @param id The id to give this XDSDocumentEntry
     * @param connection The connection that this XDSDocumentEntry will be sent to
     * @return The XML DOM holding this XDSDocumentEntry
     * @throws XdsRimException When this XdsDocumentEntry cannot be turned into valid metadata
     * @throws SOAPException When a SOAP implementation cannot be found
     */
    public SOAPElement getAsXmlDom(String id, IConnectionDescription connection) throws XdsRimException, SOAPException {
        // First, create the Extrinsic object
        RimSoap xml = RimSoap.newXdsDocumentEntryBuilder(id, connection, log);
        // Add some attributes
        xml.addAttribute("objectType", XDS_DOCUMENT_ENTRY, true);
        xml.addAttribute("mimeType", mimeType, true);
        // Next, add the name
        xml.addName(title, false);
        // Now a bunch of slots
        xml.addSlot("creationTime", creationTime, true);
        xml.addSlot("languageCode", languageCode, true);
        xml.addSlot("legalAuthenticator", legalAuthenticator, false);
        xml.addSlot("serviceStartTime", serviceStartTime, false);
        xml.addSlot("serviceStopTime", serviceStopTime, false);
        xml.addSlot("sourcePatientId", sourcePatientId, true);
        xml.addSlot("sourcePatientInfo", sourcePatientInfo, true);
        xml.addSlot("URI", uri, false);
        // Some classifications
        //Add Author Classification   - implement XDS CP-122
        for (XdsAuthor author : authors) {
            xml.addClassification("authorPerson", author.getAuthorPerson(), author.getAuthorInfo(), XDS_DOCUMENT_ENTRY_AUTHOR_PERSON, false);
        }
        xml.addClassification("classCode", classCode, XDS_DOCUMENT_ENTRY_CLASS_CODE, true);
        xml.addClassification("formatCode", formatCode, XDS_DOCUMENT_ENTRY_FORMAT_CODE, true);
        xml.addClassification("practiceSettingCode", practiceSettingCode, XDS_DOCUMENT_ENTRY_PRACTICE_SETTING_CODE, true);
        xml.addClassification("healthcareFacilityTypeCode", healthcareFacilityTypeCode, XDS_DOCUMENT_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE, true);
        xml.addClassification("typeCode", typeCode, XDS_DOCUMENT_ENTRY_TYPE_CODE, true);
        for (Pair pairOfCode : confidentialityCodes) {
            String confidentialityCode = (String)pairOfCode._first;
            xml.addClassification("confidentialityCode", confidentialityCode, XDS_DOCUMENT_ENTRY_CONFIDENTIALITY_CODE, true);
        }
        xml.addClassification("eventCode", eventCodes, XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST, false);
        // And a couple of identifiers
        xml.addExternalIdentifier("XDSDocumentEntry.patientId", patientId, XDS_DOCUMENT_ENTRY_PATIENT_ID, true);
        xml.addExternalIdentifier("XDSDocumentEntry.uniqueId", uniqueId, XDS_DOCUMENT_ENTRY_UNIQUE_ID, true);
        // Done
        return xml.getSoapElement();
    }

    /**
     * Get an XDSDocumentEntry as an
     * XML Axiom version of an ebRim ExtrinsicObject.
     *
     * @param id The id to give this XDSDocumentEntry
     * @param mimeType The mime type of this XDSDocumentEntry
     * @param connection The connection that this XDSDocumentEntry will be sent to
     * @return The XML Axiom OMElement holding this XDSDocumentEntry
     * @throws XdsRimException When this XdsDocumentEntry cannot be turned into valid metadata
     */
    public OMElement getAsXmlAxiom(String id, String mimeType, IConnectionDescription connection) throws XdsRimException {
        // First, create the Extrinsic object
        RimAxiom xml = RimAxiom.newXdsDocumentEntryBuilder(id, connection, log);
        // Add some attributes
        xml.addAttribute("objectType", XDS_DOCUMENT_ENTRY, true);
        xml.addAttribute("mimeType", mimeType, true);
        // Now a bunch of slots
        xml.addSlot("creationTime", creationTime, true);
        xml.addSlot("languageCode", languageCode, true);
        xml.addSlot("legalAuthenticator", legalAuthenticator, false);
        xml.addSlot("serviceStartTime", serviceStartTime, false);
        xml.addSlot("serviceStopTime", serviceStopTime, false);
        xml.addSlot("sourcePatientId", sourcePatientId, true);
        xml.addSlot("sourcePatientInfo", sourcePatientInfo, true);
        xml.addSlot("URI", uri, false);
        // Next, add the name
        xml.addName(title, false);
        xml.addDescription("", false);
        // Some classifications
        int index = 1;
        //Add Author Classification   - implement XDS CP-122
        for (XdsAuthor author : authors) {
            xml.addClassification("authorPerson", author.getAuthorPerson(), author.getAuthorInfo(), XDS_DOCUMENT_ENTRY_AUTHOR_PERSON, "cl-"+id+"-"+index++, false);
        }
        xml.addClassification("classCode", classCode, XDS_DOCUMENT_ENTRY_CLASS_CODE, "cl-"+id+"-"+index++, true);
        xml.addClassification("formatCode", formatCode, XDS_DOCUMENT_ENTRY_FORMAT_CODE, "cl-"+id+"-"+index++, true);
        xml.addClassification("practiceSettingCode", practiceSettingCode, XDS_DOCUMENT_ENTRY_PRACTICE_SETTING_CODE, "cl-"+id+"-"+index++, true);
        xml.addClassification("healthcareFacilityTypeCode", healthcareFacilityTypeCode, XDS_DOCUMENT_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE, "cl-"+id+"-"+index++, true);
        xml.addClassification("typeCode", typeCode, XDS_DOCUMENT_ENTRY_TYPE_CODE, "cl-"+id+"-"+index++, true);
        for (Pair pairOfCode : confidentialityCodes) {
            String confidentialityCode = (String)pairOfCode._first;
            xml.addClassification("confidentialityCode", confidentialityCode, XDS_DOCUMENT_ENTRY_CONFIDENTIALITY_CODE, "cl-"+id+"-"+index++, true);
        }
        xml.addClassification("eventCode", eventCodes, XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST, "cl-"+id+"-"+index++, false);
        // And a couple of identifiers
        index = 1;
        xml.addExternalIdentifier("XDSDocumentEntry.patientId", patientId, XDS_DOCUMENT_ENTRY_PATIENT_ID, id, "ei-"+id+"-"+index++, true);
        xml.addExternalIdentifier("XDSDocumentEntry.uniqueId", uniqueId, XDS_DOCUMENT_ENTRY_UNIQUE_ID, id, "ei-"+id+"-"+index++, true);
        // Done
        return xml.getRootElement();
    }
    
    /**
     * Get the set of XDS Registry object references used by the metadata in this registry object
     * @return The registry object references UUIDs
     */
    public Collection<String> getObjectReferences() {
        ArrayList<String> refs = new ArrayList<String>();
        if (authors.size() > 0) refs.add(XDS_DOCUMENT_ENTRY_AUTHOR_PERSON);
        if (classCode != null) refs.add(XDS_DOCUMENT_ENTRY_CLASS_CODE);
        if (confidentialityCodes.size() > 0) refs.add(XDS_DOCUMENT_ENTRY_CONFIDENTIALITY_CODE);
        if (formatCode != null) refs.add(XDS_DOCUMENT_ENTRY_FORMAT_CODE);
        if (healthcareFacilityTypeCode != null) refs.add(XDS_DOCUMENT_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE);
        if (practiceSettingCode != null) refs.add(XDS_DOCUMENT_ENTRY_PRACTICE_SETTING_CODE);
        if ((eventCodes != null) && (eventCodes.size() > 0)) refs.add(XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST);
        if (typeCode != null) refs.add(XDS_DOCUMENT_ENTRY_TYPE_CODE);
        if (patientId != null) refs.add(XDS_DOCUMENT_ENTRY_PATIENT_ID);
        if (uniqueId != null) refs.add(XDS_DOCUMENT_ENTRY_UNIQUE_ID);
        if (parentDocumentUUID != null) refs.add(parentDocumentUUID);
        return refs;
    }

    /**
     * Get the XDSDocumentEntry type UUID used in the registry.
     *
     * @return The XDSDocumentEntry object type code
     */
    public static String getXdsDocumentEntryObjectType() {
        return XDS_DOCUMENT_ENTRY;
    }

    /**
     * Get the identification scheme used for patient IDs in the
     * XdsDocumentEntry metadata.
     *
     * @return The XDSDocumentEntry patient ID identification scheme
     */
    public static String getPatientIdIdentificationScheme() {
        return XDS_DOCUMENT_ENTRY_PATIENT_ID;
    }

    /**
     * Get the identification scheme used for document unique IDs in the
     * XdsDocumentEntry metadata.
     *
     * @return The XDSDocumentEntry document unique ID identification scheme
     */
    public static String getDocumentIdIdentificationScheme() {
        return XDS_DOCUMENT_ENTRY_UNIQUE_ID;
    }

}
