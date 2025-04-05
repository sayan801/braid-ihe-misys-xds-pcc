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
package com.misyshealthcare.connect.base.clinicaldata;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 *
 * This class represent a "Visit"
 * The equals and hashcode methods only use the systemId and the visitId because
 * these to variables taken together uniquely identify the visit.
 *
 * @author : Shantanu Paul
 * @version : 1.0
 * Date: Oct 26, 2005
 **/

public class Visit {
    private String          systemId;
    private String          visitId;
    private Date            visitStartTimestamp;
    private Date            visitEndTimestamp;
    private String          reason;
    private List<Problem>   problemList     = new ArrayList<Problem>();
    private List<Provider>  providerList    = new ArrayList<Provider>();
    private boolean         confidential = false;
    private boolean         deleted = false;
    private String          facilityName;
    private List<VisitDocumentDescriptor>    documentList = new ArrayList<VisitDocumentDescriptor>();
    private String systemPatientId;

    /**
     * Only constructor to create a Visit object - a visit must have
     * a systemId and a visitId. Hence these are included in the constructor,
     * no default constructor is provided and no setters for these 2 variables is
     * provided
     *
     * @param systemId - the systemId of teh visit
     * @param visitId - the visitId of the visit
     */
    public Visit(String systemId, String visitId) {
        this.systemId = systemId;
        this.visitId = visitId;
    }
    /**
     * Get the systemid of this visit
     * @return
     */
    public String getSystemId() {
        return systemId;
    }
    /**
     * Get teh visitId of this visit
     * @return
     */
    public String getVisitId() {
        return visitId;
    }

    public String getSystemPatientId() {
        return systemPatientId;
    }

    public void setSystemPatientId(String systemPatientId) {
        this.systemPatientId = systemPatientId;
    }

    /**
     * Get teh start date of this visit
     * @return
     */
    public Date getVisitStartTimestamp() {
        return visitStartTimestamp;
    }
    /**
     * Set the start date of this visit
     * @param visitStartTimestamp
     */
    public void setVisitStartTimestamp(Date visitStartTimestamp) {
        this.visitStartTimestamp = visitStartTimestamp;
    }
    /**
     * Get the end date of this visit
     * @return
     */
    public Date getVisitEndTimestamp() {
        return visitEndTimestamp;
    }
    /**
     * Set teh end date of this visit
     * @param visitEndTimestamp
     */
    public void setVisitEndTimestamp(Date visitEndTimestamp) {
        this.visitEndTimestamp = visitEndTimestamp;
    }
    /**
     * Get the reason for this visit
     * @return
     */
    public String getReason() {
        return reason;
    }
    /**
     * Set teh reason for this visit
     * @param reason - the reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * Get the list of Problems associated with the visit
     * @return
     */
    public List<Problem> getProblemList() {
        return problemList;
    }
    /**
     * Set a list of problems for this visit
     * @param problemList  a list of <code>Problem</code> objects
     */
    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }
    /**
     * Get the list of Providers for thsi visit
     * @return a list of <code>Provider</code> objects
     */
    public List<Provider> getProviderList() {
        return providerList;
    }
    /**
     * Set a list of Providers for this visit
     * @param providerList - a list of <code>Provider</code> objects
     */
    public void setProviderList(List<Provider> providerList) {
        this.providerList = providerList;
    }
    /**
     * Indicates whether this visit is confidential
     * @return
     */
    public boolean isConfidential() {
        return confidential;
    }
    /**
     * Set the confidentiality
     * @param confidential
     */
    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }
    /**
     * Indicates whether this visit is marked as deleted
     * @return
     */
    public boolean isDeleted() {
        return deleted;
    }
    /**
     * Set teh delete flag
     * @param deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public List<VisitDocumentDescriptor> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<VisitDocumentDescriptor> documentList) {
        this.documentList = documentList;
    }

    public void addDocument(VisitDocumentDescriptor doc) {
        if(!documentList.contains(doc))
            documentList.add(doc);
    }

    public void removeDocument(VisitDocumentDescriptor doc) {
        if(documentList.contains(doc))
            documentList.remove(doc);
    }

    public void addDocumentList(List<VisitDocumentDescriptor> docList) {
        for(VisitDocumentDescriptor doc : docList) {
            if(!documentList.contains(doc))
                documentList.add(doc);
        }

    }
    /**
     * Method to add a provider to the visit
     * @param provider - the Provider to be added
     */
    public void addProvider(Provider provider) {
        if(provider != null && !providerList.contains(provider))
            providerList.add(provider);
    }
    /**
     * Remove a provider from this visit
     * @param provider - The provider to be removed
     */
    public void removeProvider(Provider provider) {
        if(providerList.contains(provider))
            providerList.remove(provider);
    }
    /**
     * Add a problem to this visit's problem list
     * @param problem - the Problem to be added
     */
    public void addProblem(Problem problem) {
        if(problem != null && !problemList.contains(problem))
            problemList.add(problem);
    }
    /**
     * Remove a problem from this visit's problem list
     * @param problem - the Problem to be removed
     */
    public void removeProblem(Problem problem) {
        if(problemList.contains(problem))
            problemList.remove(problem);
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;

        final Visit visit = (Visit) o;

        if (systemId != null ? !systemId.equals(visit.systemId) : visit.systemId != null) return false;
        if (visitId != null ? !visitId.equals(visit.visitId) : visit.visitId != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (systemId != null ? systemId.hashCode() : 0);
        result = 29 * result + (visitId != null ? visitId.hashCode() : 0);
        return result;
    }

}
