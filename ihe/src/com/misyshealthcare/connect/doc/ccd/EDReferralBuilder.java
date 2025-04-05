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

import com.misyshealthcare.connect.base.SharedEnums;

import hl7OrgV3.POCDMT000040StructuredBody;

import java.util.*;

/**
 * The document builder to build an ED Referral Summary.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 20, 2006
 */
public class EDReferralBuilder extends ReferralSummaryBuilder implements IDocBuilder {
    private EDReferralData data;
    private MetaDataAgent.Setting metadataSetting = null;

    public EDReferralBuilder(EDReferralData data) {
        super(data);
        this.data = data;
    }

    /**
     * Builds a Referral Summary document, which conforms to the IHE XDS-MS standard.
     *
     * @return the Document object to be built.
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
        buildStudiesSummaryComponent( body, data.getProcedures(),data.getLabResults(), false );
        buildPlanOfCareComponent( body, data.getPlanOfCares(), false);
        buildProposedEDDispositionComponent( body, data.getProposedDisposition(), false);
        
        buildModeOfTransport( body, data.getTransportMode(), true);        
        //required Advance Directive
        buildAdvancedDirectiveComponent( body, data.getAdvanceDirectives(), true );
        
        return getDocument(true);
    }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = super.getTemplateId();
        templateIds.add( new Id(TID_ED_REFERRAL_TEMPLATE, null) );
        return templateIds;
    }

    protected String getTitle() {
    	return getTitle("ED Referral");
    }

    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
        super.isValid();
    	
        //transport mode is required for EDR
        if (data.getTransportMode() == null) {
    		addError("Missing required Transport Mode for EDR.");    		
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
	    	metadataSetting.setClassCode(SharedEnums.XdsClassCode.Transfer_of_care_Referral);
	    	metadataSetting.setFormatCode(SharedEnums.XdsFormatCode.EDR);
	    	metadataSetting.setFacilityTypeCode("Outpatient");
	    	metadataSetting.setPracticeSettingCode("Multidisciplinary");
	    	metadataSetting.setTypeCode(SharedEnums.XdsTypeCode.Transfer_of_Care_Referral_Note);
        }
        return metadataSetting;
    }

}
