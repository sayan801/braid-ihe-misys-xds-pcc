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
package com.misyshealthcare.connect.base;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a query for IHE documents.  To find a set of
 * documents, create a <code>DocumentQuery</code> object and
 * fill in the slots with data about the documents to be found.
 * <p>
 * Then, call DocumentBroker.findDocuments(query), using the query
 * object to get the list of patient descriptors.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 28, 2005
 * @see DocumentBroker#findDocuments
 */
public class DocumentQuery {

    /* The patient ID for the documents to be retrieved */
    private PatientID patientId = null;
    /* The start and end times for documents of interest */
    private DateQuery startTime = null;
    private DateQuery endTime = null;
    /**A list of ConfidentialityCodes of interest*/
    private List<SharedEnums.ConfidentialityCode> confidentialityCodes = new ArrayList<SharedEnums.ConfidentialityCode>();
    private List<SharedEnums.XdsFormatCode> formatCodes = null;
    /*The globally unique id of the document to be retrieved. This id is usually assigned by the XDS source */
    private String documentId = null;
    /**The document relationship with the given documentId. When this parameter is present, documentId must be given also.*/
    private SharedEnums.DocumentRelationship documentRelationship = null;
    /*The UUID of document entry, which is used assigned by XDS repository/registry, and can be retrived from XDS Query.*/
    private String entryUUID = null;

    /* The Misys home system for the patient of interest */
    private String homeSystem = null;
    /* The maximum number of documents to return */
    private int maxDocumentCount = -1;

    /**
     * Get the ID of the patient this document query is about.
     *
     * @return Returns the patientId.
     */
    public PatientID getPatientId() {
        return patientId;
    }
    /**
     * Set the ID for the patient this document query is about.
     *
     * @param patientId The patientId to set.
     */
    public void setPatientId(PatientID patientId) {
        this.patientId = patientId;
    }

    /**
     * Get the date and time for the latest documents this
     * query will look for.
     *
     * @return Returns the endTime.
     */
    public DateQuery getEndTime() {
        return endTime;
    }
    /**
     * Set the date and time for the latest documents this query
     * will look for.
     *
     * @param endTime The endTime to set.
     */
    public void setEndTime(DateQuery endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the document confidentiality code of this query
     *
     * @return a list of SharedEnums.ConfidentialityCode
     */
    public List<SharedEnums.ConfidentialityCode> getConfidentialityCodes() {
        return confidentialityCodes;
    }

    /**
     * Add a confidentiality code to this query
     *
     * @param confidentialityCode the confidentiality code
     */
    public void addConfidentialityCode(SharedEnums.ConfidentialityCode confidentialityCode) {
        this.confidentialityCodes.add( confidentialityCode );
    }

    /**
     * Set a list of confidentialityCodes to this query. The document retrived will meet any
     * one of the confidentialityCodes.
     *
     * @param confidentialityCodes
     */
    public void setConfidentialityCodes(List<SharedEnums.ConfidentialityCode> confidentialityCodes) {
        this.confidentialityCodes = confidentialityCodes;
    }

    /**
     * Get the document format code of this query
     *
     * @return a list of SharedEnums.XdsFormatCode
     */
    public List<SharedEnums.XdsFormatCode> getFormatCodes() {
        if (formatCodes == null) return new ArrayList<SharedEnums.XdsFormatCode>();
        else return formatCodes;
    }

    /**
     * Add a format code to this query
     *
     * @param formatCode the format code
     */
    public void addFormatCode(SharedEnums.XdsFormatCode formatCode) {
        if (this.formatCodes == null) this.formatCodes = new ArrayList<SharedEnums.XdsFormatCode>();
        this.formatCodes.add( formatCode );
    }

    /**
     * Set a list of formatCodes to this query. The document retrived will meet any
     * one of the formatCodes.
     *
     * @param formatCodes
     */
    public void setFormatCodes(List<SharedEnums.XdsFormatCode> formatCodes) {
        this.formatCodes = formatCodes;
    }    

    /**
     * Get the date and time of the earliest documents this query
     * will look for.
     *
     * @return Returns the startTime.
     */
    public DateQuery getStartTime() {
        return startTime;
    }
    /**
     * Set the date and time for the earliest documents this query
     * will look for.
     *
     * @param startTime The startTime to set.
     */
    public void setStartTime(DateQuery startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the unique document id of this query. This unique doc id should be
     * provided by the document source.
     * 
     * @return the unique document id
     */
    public String getDocumentId() {
        return this.documentId;
    }

    /**
     * Set the unique document id of this query. This unique doc id should be
     * provided by the document source.
     *
     * @param documentId the unique document id to be set.
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * Get the document relationship.
     *
     * @return the document relationship
     * @see SharedEnums.DocumentRelationship for document relationship types
     */
    public SharedEnums.DocumentRelationship getDocumentRelationship() {
        return this.documentRelationship;
    }

    /**
     * Set the document relationship.
     *
     * @param relationship the relationship of the document to be set with the given document.
     * @see SharedEnums.DocumentRelationship for document relationship types
     */
    public void setDocumentRelationship(SharedEnums.DocumentRelationship relationship) {
        this.documentRelationship = relationship;
    }

    /**
     * Get the EntryUUID of this DocumentQuery. The entryUUID is the document id used in the XDS registry, and is
     * assigned by XDS registry or repository. To query a document by its id, provide either document Id (unique id) or
     * entryUUID.
     * 
     * @return  the UUID
     */
    public String getEntryUUID() {
        return this.entryUUID;
    }

    /**
     * Set the EntryUUID of thid DocumentQuery. The entryUUID is the document id used in the XDS registry, and is
     * assigned by XDS registry or repository. To query a document by its id, provide either document Id (unique id) or
     * entryUUID.
     *
     * @param uuid
     */
    public void setEntryUUID(String uuid) {
        this.entryUUID = uuid;
    }

    /**
     * Get the Misys home system that the documents should come from.
     *
     * @return Returns the homeSystem.
     */
    public String getHomeSystem() {
        return homeSystem;
    }
    /**
     * Set the Misys home system that the documents should come from.
     *
     * @param homeSystem The homeSystem to set.
     */
    public void setHomeSystem(String homeSystem) {
        this.homeSystem = homeSystem;
    }
    /**
     * Get the maximum number of documents to return with this query.
     *
     * @return Returns the maxDocumentCount.
     */
    public int getMaxDocumentCount() {
        return maxDocumentCount;
    }
    /**
     * Set the maximum number of documents to return with this query.
     * The default is all documents.
     *
     * @param maxDocumentCount The maxDocumentCount to set.
     */
    public void setMaxDocumentCount(int maxDocumentCount) {
        this.maxDocumentCount = maxDocumentCount;
    }

    public String verifyQueryParameters() {
        // make sure one of patientId, documentId and entryUUID is provided.
        if (patientId==null && documentId==null && entryUUID==null)
            return "One of patientId, documentId or entryUUID must be specified.";
        // If a document relationship is present, the documentId must be presented together.
        if (documentRelationship != null && documentId == null)
            return "The documentId must be specified for a related document query.";
        // documentId and entryUUID cannot not be coexisted
        if (documentId != null && entryUUID != null)
            return "documentId and entryUUID cannot be both specified";

        return "";
    }
}
