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


package com.misyshealthcare.connect.doc.ccd;

import hl7OrgV3.POCDMT000040StructuredBody;

import java.util.List;

import com.misyshealthcare.connect.base.SharedEnums;


/**
 * The document builder to build a Referral Summary.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 25, 2005
 */
public class ReferralSummaryBuilder extends XDSMSBuider implements IDocBuilder {
    protected ReferralSummaryData data;
    protected final CodeSystem docCode = new CodeSystem("34133-9", "2.16.840.1.113883.6.1", "LOINC", "SUMMARIZATION OF EPISODE NOTE");
    private MetaDataAgent.Setting metadataSetting = null;

    public ReferralSummaryBuilder(ReferralSummaryData data) {
        super(data);       
        this.data = data;
     }

    /**
     * Builds a Referral Summary document, which conforms to the IHE XDS-MS standard.
     *
     * @return the CCDDocument object to be built.
     * @throws CCDException if it fails to build a document.
     */
    public CCDDocument build() throws CCDException {
        if (!isValid() ) {
        	return getDocumentWithError();
        }
        
        buildHeader();

        POCDMT000040StructuredBody body = buildStructuredBody( doc );
        buildReasonForReferral( body, data.getReferralReason(), true );
        buildHistoryOfPresentalIllness( body, data.getHistoryOfPresentIllness(), true );
        buildActiveProblemComponent( body, data.getActiveProblems(), true );
        buildMedicationComponent( body, data.getMedications(), true );
        buildAllergyComponent( body, data.getAllergies(), true );
        
        buildResolvedProblemComponent( body, data.getResolvedProblems(), false );
        buildSurgeryComponent( body, data.getSurgicalHistories(), false );
        buildImmunizationComponent( body, data.getImmunizations(), false );
        buildFamilyHistoryComponent( body, data.getFamilyHistories(), false );
        buildSocialHistoryComponent( body, data.getSocialHistories(), false );
        buildReviewOfSystemComponent( body, data.getReviewOfSystems(), false);
        buildVitalSignComponent( body, data.getMeasurements(), false);
        buildPhysicalExamComponent( body, data.getPhysicalExams(), false );
        buildStudiesSummaryComponent( body, data.getProcedures(), data.getLabResults(), false );
        buildPlanOfCareComponent( body, data.getPlanOfCares(), false);
        buildAdvancedDirectiveComponent( body, data.getAdvanceDirectives(), false );

        return getDocument(true);
    }


    /**
     * Builds the head part of XDS-MS CDA document
     */
    protected void buildHeader() {
        buildHeader1( doc, metadata, getTemplateId(), docCode, getTitle());
        buildRecordTarget( doc, metadata.getSourcePatientId(), metadata.getSourcePatientInfo(), metadata.getPatientContacts() );
        buildAuthor( doc, metadata.getAuthors(), null );
        buildAuthorDevice(doc, CDABuilder.connCDA, false);
        buildDataEnterer(doc, metadata.getDataEnterers(), false);
        if( metadata.getCustodian() != null) 
        	buildCustodian( doc, metadata.getCustodian(), true );
        else
        	buildCustodian( doc, CDABuilder.connCDA, true);
        buildLegalAuthenticator(doc, metadata.getLegalAutheticator());
        buildInsuranceInformation( doc, data.getInsurances() );
        buildPatientContact(doc, metadata.getPatientContacts());
        buildDocumentOf( doc, metadata.getServiceStartTime(), metadata.getServiceStopTime(),  metadata.getPerformers(), false );
        buildComponentOf( doc, metadata.getEncounters(), false, false );
    }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = super.getTemplateId();
        templateIds.add(new Id(TID_REFERRAL_SUMMARY_TEMPLATE, null));
        return templateIds;
    }

    protected String getTitle() {
    	return getTitle("Referral Summary");
    }
    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
    	super.isValid();
    	 
    	//Referral Reason  
    	if (data.getReferralReason() == null)
    		addWarning("Missing required Referral Reason" );
    	//History of Present Illness
    	if (data.getHistoryOfPresentIllness() == null) {
    		addWarning("Missing required History of Preseant Illness");    		
    	}
    	
    	return !hasError();
    }

    
    /**
     * Gets the metadata setting of this builder.
     *
     * @return <code>MetaDataAgent.Setting</code>
     */
    synchronized MetaDataAgent.Setting getMetadataSetting() {
        if (metadataSetting==null) {
	    	//These are default metadata setting
	    	metadataSetting = new MetaDataAgent.Setting();
	    	metadataSetting.setClassCode(SharedEnums.XdsClassCode.Transfer_Summarization);
	    	metadataSetting.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);
	    	metadataSetting.setFacilityTypeCode("Outpatient");
	    	metadataSetting.setPracticeSettingCode("Multidisciplinary");
	    	metadataSetting.setTypeCode(SharedEnums.XdsTypeCode.Transfer_Summarization_Note);
        }
	    return metadataSetting;
    }

    
}
