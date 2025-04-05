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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.soap.SOAPMessage;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.IDocumentConsumer;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsResponse;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class implements the IHE XDS Document Source Actor.  
 * It also implements the com.misyshealthcare.connect.base.IDocumentConsumer
 * interface so that it can be used as document consumer by the
 * DocumentBroker.
 * <p>
 * This class constructs and sends ebRIM XML messages in SOAP wrappers as required
 * by the IHE Provide and Register Document Set Transation (ITI-15). See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.15
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.14 (Metadata format)
 * <li> OASIS/ebXML Registry Information Model v2.0, December 2001
 * <li> OASIS/ebXML Registry Services Specification v2.0, December 2001
 * </ul>
 * A number of connection configuration parameters are used by this
 * actor:
 * <ul>
 * <li> AssigningAuthority (Identifier) required:
 *        Specifies the Assigning Authority used by this registry/repository. It is used to
 *        encode the appropriate Patient ID for a document submission.
 * <li> LocalAssigningAuthority (Identifier) required: 
 *        Specifies the Local Assigning Authority used by this actor. It is used to
 *        encode the local unique ID for a patient in the submission set. 
 * <li> XdsClassCode (EnumMap) required: 
 *        This EnumMap describes the mapping from internal Misys Connect XdsClassCode enum codes
 *        to class code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Consumer.
 * <li> XdsFormatCode (EnumMap) required: 
 *        This EnumMap describes the mapping from internal Misys Connect XdsFormatCode enum codes
 *        to format code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Consumer.
 * <li> XdsTypeCode (EnumMap) required: 
 *        This EnumMap describes the mapping from internal Misys Connect XdsTypeCode enum codes
 *        to type code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Consumer.
 * <li> XdsContentCode (EnumMap) required:
 *        This EnumMap describes the mapping from internal Misys Connect XdsContentCode enum codes
 *        to type code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Consumer.
 * <li> authorRole (StringMap) optional: 
 *        This StringMap describes a mapping from internal Misys Connect author role values
 *        to author role code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Consumer.  If this string
 *        map is not supplied, author role codes will be written untranslated into the document
 *        metadata.
 * <li> authorSpecialty (StringMap) optional: 
 *        This StringMap describes a mapping from internal Misys Connect author specialty values
 *        to author specialty code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Consumer.  If this string
 *        map is not supplied, author specialty codes will be written untranslated into the document
 *        metadata.
 * <li> facilityCode (StringMap) optional:
 *        This StringMap describes a mapping from internal Misys Connect facility code values
 *        to healthcare facility code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Consumer.  If this map is not
 *        supplied, values will be taken as their own codes.
 * <li> practiceCode (StringMap) optional:
 *        This StringMap describes a mapping from internal Misys Connect practice code values
 *        to practice setting code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Consumer.  If this map is not
 *        supplied, values will be taken as their own codes.
 * <li> confidentialityCode (StringMap) required:
 *        This StringMap describes a mapping from internal Misys Connect confidentiality code values
 *        to confidentiality code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Consumer.
 * <li> sourceId {StringMap} required:
 *        This StringMap describes a mapping from internal Misys Connect document source code values
 *        to source code code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Consumer.
 * <li> contentTypeCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a ContentType code.
 *        The mapping from Misys XdsContentCode enum values to registry ContentType codes is captured in the
 *        XdsContentCode enum map.  The 'contentTypeCode' CodeMap includes additional informaton
 *        about the registry ContentType codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> classCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a Class code.
 *        The mapping from Misys XdsClassCode enum values to registry Class codes is captured in the
 *        XdsClassCode enum map.  The 'classCode' CodeMap includes additional informaton
 *        about the registry Class codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> formatCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a Format code.
 *        The mapping from Misys XdsFormatCode enum values to registry Format codes is captured in the
 *        XdsFormatCode enum map.  The 'formatCode' CodeMap includes additional informaton
 *        about the registry Format codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> practiceSettingCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a Practice Setting code.
 *        The mapping from Misys practiceCode values to registry PracticeSetting codes is captured in the
 *        practiceCode string map.  The 'practiceSettingCode' CodeMap includes additional informaton
 *        about the registry PracticeSetting codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> healthcareFacilityTypeCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a Healthcare Facility Type code.
 *        The mapping from Misys facilityCode values to registry HealthcareFacilityType codes is captured in the
 *        facilityCode string map.  The 'healthcareFacilityTypeCode' CodeMap includes additional informaton
 *        about the registry HealthcareFacilityType codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> typeCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a Type code.
 *        The mapping from Misys XdsTypeCode enum values to registry Type codes is captured in the
 *        XdsTypeCode enum map.  The 'typeCode' CodeMap includes additional informaton
 *        about the registry Type codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> confidentialityCode (CodeMap) required:
 *        This CodeMap includes all of the ebRS registry codes associated with a Confidentiality code.
 *        The mapping from Misys confidentialityCode values to registry Confidentiality codes is captured in the
 *        confidentialityCode string map.  The 'confidentialityCode' CodeMap includes additional informaton
 *        about the registry Confidentiality codes required by the registry.  This code map is often
 *        supplied by the registry.
 * <li> eventCode (CodeMap) optional:
 *        This CodeMap includes all of the ebRS registry codes associated with an Event code.
 *        Misys Connect does not currently use Event codes so this code map may be left out.
 * </ul>
 * 
 * @author Jim Firby
 * @version 3.0 - Dec 13, 2006
 * @see com.misyshealthcare.connect.base.IDocumentConsumer
 * @see com.misyshealthcare.connect.base.DocumentBroker
 * @see com.misyshealthcare.connect.base.Document
 * @see com.misyshealthcare.connect.base.IDocumentContents
 */
public class XdsDocumentSource extends XdDocumentSource implements IDocumentConsumer {

    private final static Logger log = Logger.getLogger(XdsDocumentSource.class);

    private IConnectionDescription connection = null;
    private XdsDocumentConsumer consumer = null;
    private IheAuditTrail auditTrail = null;

    /**
     * Create a new document source that will send documents to
     * the  system described by the connection.  This source will
     * not support document replacement.
     *
     * @param connection The system to receive new documents
     */
    public XdsDocumentSource(IConnectionDescription connection, IheAuditTrail auditTrail) {
        this(connection, null, auditTrail);
    }

    /**
     * Create a new document source that will send documents to the
     * system described by the connection and will allow document replacements
     * that require querying the registry.
     *
     * @param connection The system to receive new documents
     * @param queryConnection The system to return document registry IDs when replacing
     */
    public XdsDocumentSource(IConnectionDescription connection, IConnectionDescription queryConnection, IheAuditTrail auditTrail) {
        super(connection, log, auditTrail);
        this.connection = connection;
        this.auditTrail = auditTrail;
        if (queryConnection != null) {
            consumer = new XdsDocumentConsumer(queryConnection, null);
        }
    }

    /**
     * The submitDocument method defined in the IDocumentConsumer interface.
     * @see com.misyshealthcare.connect.base.IDocumentConsumer#submitDocument(Document, String, XdsContentCode)
     */
    public void submitDocument(Document document, String description, XdsContentCode contentCode) throws DocumentException {
        ArrayList<Document> documents = new ArrayList<Document>();
        documents.add(document);
        submitDocuments(documents, description, contentCode);
    }

    /**
     * The submitDocuments method defined in the IDocumentConsumer interface.
     * @see com.misyshealthcare.connect.base.IDocumentConsumer#submitDocuments (Collection<Document>, String, XdsContentCode)
     */
    public void submitDocuments(Collection<Document> documents, String description, XdsContentCode contentCode) throws DocumentException {
        submitDocumentSet(documents, description, contentCode);
    }

    /**
     * Submit a set of documents to the repository.
     *
     * @param documents The documents to be submitted
     * @param description A description of these documents
     * @param contentCode A code describing the contents of these documents
     * @throws DocumentException When there is a problem with the submission
     */
    private void submitDocumentSet(Collection<Document> documents, String description, XdsContentCode contentCode) throws DocumentException {
        // Placeholders for the submit metadata
        XdsSubmissionSet submissionSet = null;
        ArrayList<XdsDocumentEntry> documentEntries = new ArrayList<XdsDocumentEntry>();
        boolean isReplacement = false;
        // Create the submission set metadata
        try {
            submissionSet = createSubmissionSetMetadata(documents, description, contentCode);
        } catch (IheConfigurationException e) {
            throwBadConfigurationException("accept", e);
        }
        // Create metadata for each document
        try {
            int i = 1;
            for (Document document: documents) {
                XdsDocumentEntry entry = createDocumentMetadata(document);
                if (entry != null) {
                    // Create a metadata ID for this document
                    entry.setEntryUuid("doc" + i++);
                    // Add the actual content
                    entry.setContent(document.getContents());
                    documentEntries.add(entry);
                    // See if it is replacing an old document, temporarily cache the old document ID
                    if (document.getReplacesId() != null) {
                        entry.setParentDocumentUUID(document.getReplacesId());
                        isReplacement = true;
                    }
                }
            }
        } catch (IheConfigurationException e) {
            throwBadConfigurationException("accept", e);
        }
        // If we are replacing, find the orignal document UUID for each old document ID
        if (isReplacement) {
            if (consumer == null) {
                throwBadConfigurationException("replace", null);
            } else {
                IMesaLogger logger = getMesaLogger();
                for (XdsDocumentEntry entry: documentEntries) {
                    String docId = entry.getParentDocumentUUID();
                    if (docId != null) {
                        if (logger != null) logger.writeString("Getting UUID for old document '" + docId + "' ...");
                        String oldUUID = consumer.findOneDocumentRegistryId(docId);
                        if (logger != null) logger.writeString("UUID for old document is '" + oldUUID + "'");
                        if (oldUUID == null)
                            log.warn("Replacing document " + docId + " which is not in the repository");
                        entry.setParentDocumentUUID(oldUUID);
                    }
                }
            }
        }
        // Make the submission
        boolean isXdsb = false;
        String xdsb =  connection.getProperty("XDS.b");
        if(xdsb!=null && xdsb.equalsIgnoreCase("true")) isXdsb = true;
        
        XdsResponse response = null;
        Object message = null;        
        if (isXdsb) {
            //For XDS.b
        	XdsbMessenger messenger = new XdsbMessenger(connection);
        	OMElement xdsSubmission = messenger.createSubmitObjectsRequest(submissionSet, documentEntries);
            
            message = messenger.send(xdsSubmission, getMesaLogger(), "submission", getOkayToSend());
			response = messenger.getXdsResponse((OMElement)message);
        } else {
            XdsMessenger messenger = new XdsMessenger(connection, false);
            // Create the SOAP message encapulating the query
	        SOAPMessage xdsSubmission = messenger.createSubmitObjectsRequest(submissionSet, documentEntries);
	        // Write the message to the MESA test log (moved into messenger)
	        // Send the message to the repository and read the response
	        message = messenger.send(xdsSubmission, getMesaLogger(), "submission", getOkayToSend());
	        // Deal with the response
	        response = messenger.getXdsResponse((SOAPMessage)message); 
        }        
        if (response == null) {
            throwBadCommunicationException();
        } else if (response.isError()) {
            logSoapMessageError(message, response.getErrorMessage());
            throwBadCommunicationException();
        } else if (!response.isSuccess()) {
            // The registry returned failure message
            String error = "Submission failed at repository";
            if (isReplacement) error = "Replacement failed at repository";
            logSoapMessageError(message, error);
            throwBadCommunicationException();
        }
        
        // Got here, everything is good!
        // Log documents we created.
        if (auditTrail != null) {        	
        	ParticipantObject patientlog = new ParticipantObject(null, submissionSet.getPatientId());
        	ParticipantObject submissionsetlog = new ParticipantObject(null, submissionSet.getUniqueId());
        	auditTrail.documentSubmitted(connection, patientlog, submissionsetlog, isXdsb);        	
        }

    }
    

	/**
     * Throw an exception because the repository responded incorrectly.
     *
     * @throws DocumentException The exception being thrown
     */
    private void throwBadCommunicationException() throws DocumentException {
        String message = "Unexpected document submission response from connection \"" + connection.getDescription() + "\" (see log for details)";
        log.error(message);
        throw new DocumentException(message);
    }


    /**
     * We need to catch this so we can set both our mesa logger and that of our
     * local consumer.
     */
    public void setMesaLogger(IMesaLogger logger) {
        super.setMesaLogger(logger);
        if (consumer != null)
            consumer.setMesaLogger(logger);
    }

    /**
     * Get the MESA test inteface.  This interface enables a few lower-level
     * calls required by certain of the 2005 MESA tests.
     *
     * @return The mesa test interface
     */
    public MesaInterface getMesaInterface() {
        return new MesaInterface();
    }

    /**
     * The XdsDocumentConsumer MESA test interface.
     *
     * @author Jim Firby
     * @version 2.0 - Dec 19, 2005
     */
    public class MesaInterface {

        /**
         * Send an empty submission set to the registry.
         *
         * @return The SOAP response
         */
//        public SOAPMessage sendEmptySubmission() {
//            SOAPMessage xdsSubmission = messenger.createSubmitObjectsRequest(null, null);
//            return messenger.send(xdsSubmission, getMesaLogger(), "submission", getOkayToSend());
//        }

    }
    
    

}
