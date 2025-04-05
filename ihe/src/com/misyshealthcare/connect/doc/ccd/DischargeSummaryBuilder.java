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
 * The document builder to build a Discharge Summary.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 11, 2006
 */
public class DischargeSummaryBuilder extends XDSMSBuider implements IDocBuilder {

    private DischargeSummaryData data;
 //   private final CodeSystem docCode = new CodeSystem("18842-5", "2.16.840.1.113883.6.1", "LOINC", "Discharge Summarization Note");
    private final CodeSystem docCode = new CodeSystem("34133-9", "2.16.840.1.113883.6.1", "LOINC", "SUMMARIZATION OF EPISODE NOTE");
    private static MetaDataAgent.Setting metadataSetting = null;

    public DischargeSummaryBuilder(DischargeSummaryData data) {
        super(data);
        this.data = data;
    }

    /**
     * Builds a Discharge Summary document, which conforms to the IHE XDS-MS standard.
     *
     * @return the Document object to be built.
     * @throws CCDException if it fails to build a document.
     */
    public CCDDocument build() throws CCDException {
        if (!isValid() ) 
        	return getDocumentWithError(); 

        buildHeader();

        POCDMT000040StructuredBody body = buildStructuredBody( doc );
        //Problems
        buildActiveProblemComponent( body, data.getActiveProblems(), true );
        buildResolvedProblemComponent( body, data.getResolvedProblems(), true );
        buildDischargeDiagnosisComponent(body, data.getDischargeDiagnosis(), true);
        buildAdmissionDiagnosisComponent( body, data.getAdmittingDiagnosis(), true );   
        //Medications
        buildAdministeredMedicationComponent( body, data.getAdministeredMedications(), false);
        buildDischargeMedicationComponent( body, data.getMedications(), true);
        buildAdmissionMedicationComponent( body, data.getAdmissionMedications(), false);
        
        buildAllergyComponent(body, data.getAllergies(), true);
        buildHosptialCourse(body, data.getHospitalCourse(), true);
        buildAdvancedDirectiveComponent(body, data.getAdvanceDirectives(), false);
        buildHistoryOfPresentalIllness(body, data.getHistoryOfPresentIllness(), false);
        //Optional Functional Status
        buildReviewOfSystemComponent(body, data.getReviewOfSystems(), false);
        buildPhysicalExamComponent(body, data.getPhysicalExams(), false);
        buildVitalSignComponent(body, data.getMeasurements(), false);
        buildHospitalStudiesSummaryComponent(body, data.getProcedures(), false);
        //reqired Plan of Care
        buildPlanOfCareComponent( body, data.getPlanOfCares(), true);
        buildDischargeDietComponent( body, data.getDischargeDiets(), false);

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
        //require discharge disposition code
        buildComponentOf( doc, metadata.getEncounters(), true, true );        
    }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = super.getTemplateId();
        templateIds.add(new Id(TID_DISCHARGE_SUMMARY_TEMPLATE, null));
        return templateIds;
    }
    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
    	super.isValid();
    	
    	//Discharge Summary requires a Discharge Disposition Code for each encounter
    	Encounter[] encounters = metadata.getEncounters();
    	if (encounters == null) {
    		//EMR does not sending encounter information
    		addWarning("Missing Encounter information for this discharge summary");
    	}
    	if (encounters != null) {
    		for (Encounter enc : encounters) {
    			SharedEnums.DischargeDispositionCode ddc = enc.getDischargeDispositionCode();
    			if (ddc == null) {
    				addError("Each discharge encounter should provide a required discharge disposition code");
    			}
    		}
    	}
    	
    	if (data.getHospitalCourse()==null) {
    		addWarning("Missing required Hospital Course" );
    	}

        return !hasError();
    }

    protected String getTitle() {
    	return getTitle("Discharge Summary");
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
	    	metadataSetting.setClassCode(SharedEnums.XdsClassCode.Discharge_Summarization);
	    	metadataSetting.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);
	    	metadataSetting.setFacilityTypeCode("Hospital Setting");
	    	metadataSetting.setPracticeSettingCode("Multidisciplinary");
	    	metadataSetting.setTypeCode(SharedEnums.XdsTypeCode.Discharge_Summarization_Note);
        }
	    return metadataSetting;        
    }

}
