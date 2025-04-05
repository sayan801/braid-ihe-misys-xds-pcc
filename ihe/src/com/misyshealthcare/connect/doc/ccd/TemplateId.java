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

/**
 * The interface to specify all the CDA template ids. Refer to
 * IHE_PCC_TF_Final_20060812 Section 5.1.2 IHE PCC Template Identifiers.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 11, 2006
 */
public interface TemplateId {

    public static final String TID_CDA_DOC_TEMPLATE_ID            = "1.3.6.1.4.1.19376.1.5.3.1.1";
    public static final String TID_MEDICAL_DOC_TEMPLATE           = "1.3.6.1.4.1.19376.1.5.3.1.1.1";
    public static final String TID_MEDICAL_SUMMARY_TEMPLATE_ID    = "1.3.6.1.4.1.19376.1.5.3.1.1.2";
    public static final String TID_REFERRAL_SUMMARY_TEMPLATE      = "1.3.6.1.4.1.19376.1.5.3.1.1.3";
    public static final String TID_DISCHARGE_SUMMARY_TEMPLATE     = "1.3.6.1.4.1.19376.1.5.3.1.1.4";
    //Defined in BPPC
    public static final String TID_BPPC_TEMPLATE                  = "1.3.6.1.4.1.19376.1.5.3.1.1.7";    
    //Defined in EDR
    public static final String TID_ED_REFERRAL_TEMPLATE           = "1.3.6.1.4.1.19376.1.5.3.1.1.10";
    public static final String TID_PLAN_OF_CARE                   = "1.3.6.1.4.1.19376.1.5.3.1.1.10.3.1";
    public static final String TID_MODE_OF_TRANSPORT              = "1.3.6.1.4.1.19376.1.5.3.1.1.10.3.2";
    public static final String TID_ESTIMATED_TIME_OF_ARRIVAL      = "1.3.6.1.4.1.19376.1.5.3.1.1.10.4.1";
    public static final String TID_PROPOSED_DISPOSITION           = "1.3.6.1.4.1.19376.1.5.3.1.1.10.4.2";
    public static final String TID_ENCOUNTER_DISPOSITION          = "1.3.6.1.4.1.19376.1.5.3.1.1.13.2.10";
    public static final String TID_CDA_HEADER_TEMPLATE_ID         = "1.3.6.1.4.1.19376.1.5.3.1.2";
    public static final String TID_LANGUAGE_COMMUNICATION         = "1.3.6.1.4.1.19376.1.5.3.1.2.1";
    public static final String TID_EMPLOYER_SCHOOL_CONTACT        = "1.3.6.1.4.1.19376.1.5.3.1.2.2";
    public static final String TID_HEALTHCARE_PROVIDER            = "1.3.6.1.4.1.19376.1.5.3.1.2.3";
    public static final String TID_PATIENT_CONTACT                = "1.3.6.1.4.1.19376.1.5.3.1.2.4";
    public static final String TID_AUTHORIZATION                  = "1.3.6.1.4.1.19376.1.5.3.1.2.5";
    public static final String TID_CONSENT_SERVICE_EVENT          = "1.3.6.1.4.1.19376.1.5.3.1.2.6";
    public static final String TID_CDA_SECTION_TEMPALTE_ID        = "1.3.6.1.4.1.19376.1.5.3.1.3";
    public static final String TID_REASON_FOR_REFERRAL            = "1.3.6.1.4.1.19376.1.5.3.1.3.1";
    public static final String TID_REASON_FOR_REFERRAL_STRUCTURED = "1.3.6.1.4.1.19376.1.5.3.1.3.2";
    public static final String TID_HOSPITAL_ADMISSION_DIAGNOSIS   = "1.3.6.1.4.1.19376.1.5.3.1.3.3";
    public static final String TID_HISTORY_PRESENTANT_ILLNESS     = "1.3.6.1.4.1.19376.1.5.3.1.3.4";
    public static final String TID_HOSPTIAL_COURSE                = "1.3.6.1.4.1.19376.1.5.3.1.3.5";
    public static final String TID_ACTIVE_PROBLEM                 = "1.3.6.1.4.1.19376.1.5.3.1.3.6";
    public static final String TID_DISCHARGE_PROBLEM              = "1.3.6.1.4.1.19376.1.5.3.1.3.7";
    public static final String TID_RESOLVED_PROBLEM               = "1.3.6.1.4.1.19376.1.5.3.1.3.8";
    public static final String TID_HISTORY_OF_OUTPATIENT_VISITS   = "1.3.6.1.4.1.19376.1.5.3.1.3.9";
    public static final String TID_HISTORY_OF_INPATIENT_ADMISSIONS= "1.3.6.1.4.1.19376.1.5.3.1.3.10";
    public static final String TID_LIST_OF_SURGERIES              = "1.3.6.1.4.1.19376.1.5.3.1.3.11";
    public static final String TID_LIST_OF_SURGERIES_STRUCTURED   = "1.3.6.1.4.1.19376.1.5.3.1.3.12";
    public static final String TID_ALLERGIES_AND_ADVERSE_REACTIONS= "1.3.6.1.4.1.19376.1.5.3.1.3.13";
    public static final String TID_FAMILY_MEDICAL_HISTORY         = "1.3.6.1.4.1.19376.1.5.3.1.3.14";
    public static final String TID_FAMILY_MEDICAL_HISTORY_STRUCTURED= "1.3.6.1.4.1.19376.1.5.3.1.3.15";
    public static final String TID_SOCIAL_HISTORY                 = "1.3.6.1.4.1.19376.1.5.3.1.3.16";
    public static final String TID_FUNCTIONAL_STATUS              = "1.3.6.1.4.1.19376.1.5.3.1.3.17";
    public static final String TID_REVIEW_OF_SYSTEMS              = "1.3.6.1.4.1.19376.1.5.3.1.3.18";
    public static final String TID_MEDICATIONS                    = "1.3.6.1.4.1.19376.1.5.3.1.3.19";
    public static final String TID_ADMISSION_MEDICATION_HISTORY   = "1.3.6.1.4.1.19376.1.5.3.1.3.20";
    public static final String TID_HOSPITAL_MEDICATIONS           = "1.3.6.1.4.1.19376.1.5.3.1.3.21";
    public static final String TID_HOSPITAL_DISCHARGE_MEDICATIONS = "1.3.6.1.4.1.19376.1.5.3.1.3.22";
    public static final String TID_IMMUNIZATIONS                  = "1.3.6.1.4.1.19376.1.5.3.1.3.23";
    public static final String TID_PHYSICAL_EXAM                  = "1.3.6.1.4.1.19376.1.5.3.1.3.24";
    public static final String TID_VITAL_SIGNS                    = "1.3.6.1.4.1.19376.1.5.3.1.3.25";
    public static final String TID_HOSPITAL_DISCHARGE_PHYSICAL_EXAM= "1.3.6.1.4.1.19376.1.5.3.1.3.26";
    public static final String TID_RESULTS                        = "1.3.6.1.4.1.19376.1.5.3.1.3.27";
    public static final String TID_RESULTS_STRUCTURED             = "1.3.6.1.4.1.19376.1.5.3.1.3.28";
    public static final String TID_HOSPITAL_STUDIES_SUMMARY       = "1.3.6.1.4.1.19376.1.5.3.1.3.29";
    public static final String TID_HOSPITAL_STUDIES_SUMMARY_STRUCTURED= "1.3.6.1.4.1.19376.1.5.3.1.3.30";
    public static final String TID_CARE_PLAN                      = "1.3.6.1.4.1.19376.1.5.3.1.3.31";
    public static final String TID_DISCHARGE_DISPOSITION          = "1.3.6.1.4.1.19376.1.5.3.1.3.32";
    public static final String TID_DISCHARGE_DIET                 = "1.3.6.1.4.1.19376.1.5.3.1.3.33";
    public static final String TID_ADVANCE_DIRECTIVES             = "1.3.6.1.4.1.19376.1.5.3.1.3.34";
    public static final String TID_ADVANCE_DIRECTIVES_STRUCTURED_REFERENCE = "1.3.6.1.4.1.19376.1.5.3.1.3.35";
    public static final String TID_CDA_ENTRY_TEMPLATE_ID           = "1.3.6.1.4.1.19376.1.5.3.1.4";
    public static final String TID_SEVERITY_OBSERVATION            = "1.3.6.1.4.1.19376.1.5.3.1.4.1";
    public static final String TID_CLINICAL_STATUS_OBSERVATION     = "1.3.6.1.4.1.19376.1.5.3.1.4.1.1";
    public static final String TID_HEALTH_STATUS_OBSERVATION       = "1.3.6.1.4.1.19376.1.5.3.1.4.1.2";
    public static final String TID_COMMENT_OBSERVATION             = "1.3.6.1.4.1.19376.1.5.3.1.4.2";
    public static final String TID_INSTRUCTIONS_IN_MEDICATION_ORDER= "1.3.6.1.4.1.19376.1.5.3.1.4.3";
    public static final String TID_REFERENCE_TO_EXTERNAL_DOCS      = "1.3.6.1.4.1.19376.1.5.3.1.4.4";
    public static final String TID_OBSERVATION_CONCERN             = "1.3.6.1.4.1.19376.1.5.3.1.4.5.1";
    public static final String TID_OBSERVATION_PROBLEM_OF_CONCERN  = "1.3.6.1.4.1.19376.1.5.3.1.4.5.2";
    public static final String TID_OBSERVATION_ALLERGY_OR_ADVERSE_REACTION_OF_CONCERN = "1.3.6.1.4.1.19376.1.5.3.1.4.5.3";
    public static final String TID_OBSERVATION_DESCRIBE_PROBLEM    = "1.3.6.1.4.1.19376.1.5.3.1.4.5";
    public static final String TID_OBSERVATION_DESCRIBE_ALLERGY_OR_ADVERSE_REACTION = "1.3.6.1.4.1.19376.1.5.3.1.4.6";
    public static final String TID_OBSERVATION_DESCRIBE_MANIFESTATION_OF_ALLERGY    = "1.3.6.1.4.1.19376.1.5.3.1.4.6.1";
    public static final String TID_SUBSTANCE_ADMINISTRATION_EVENT                                = "1.3.6.1.4.1.19376.1.5.3.1.4.7";
    public static final String TID_SUBSTANCE_ADMINISTRATION_EVENT_NO_COMPLEX_DOS_PROCESSING      = "1.3.6.1.4.1.19376.1.5.3.1.4.7.1";
    public static final String TID_SUBSTANCE_ADMINISTRATION_PRODUCT_ENTRY                        = "1.3.6.1.4.1.19376.1.5.3.1.4.7.2";
    public static final String TID_SUBSTANCE_ADMINISTRATION_EVENT_RECORDS_TAPERED_DOSE           = "1.3.6.1.4.1.19376.1.5.3.1.4.8";
    public static final String TID_SUBSTANCE_ADMINISTRATION_EVENT_RECORDS_SPLIT_DOSE             = "1.3.6.1.4.1.19376.1.5.3.1.4.9";
    public static final String TID_SUBSTANCE_ADMINISTRATION_EVENT_RECORDS_CONDITIONAL_DOSE       = "1.3.6.1.4.1.19376.1.5.3.1.4.10";
    public static final String TID_SUBSTANCE_ADMINISTRATION_EVENT_RECORDS_COMBINATION_COMPONENT  = "1.3.6.1.4.1.19376.1.5.3.1.4.11";
    public static final String TID_SUBSTANCE_ADMINISTRATION_IMMUNIZATION                         = "1.3.6.1.4.1.19376.1.5.3.1.4.12";
    public static final String TID_ADVANCE_DIRECTIVES_ACT                    = "1.3.6.1.4.1.19376.1.5.3.1.4.13";
    public static final String TID_ADVANCE_DIRECTIVES_OBSERVATION            = "1.3.6.1.4.1.19376.1.5.3.1.4.13.7";
    public static final String TID_PROCEDURE_ENTRY                           = "1.3.6.1.4.1.19376.1.5.3.1.4.19";    


    //DEEDS from http://www.cdc.gov/ncipc/pub-res/pdf/deeds.pdf
    public static final String TID_DEED4_02    =   "2.16.840.1.113883.6.102.4.2";   //Mode of Transport
    
    //Template IDs for CCD
    public static final String CCDTID_CCDv1                     = "2.16.840.1.113883.10.20.1";
    //Section Templates
    public static final String CCDTID_ADVANCE_DIRECTIVE_SECTION = "2.16.840.1.113883.10.20.1.1";
    public static final String CCDTID_ALERTS_SECTION            = "2.16.840.1.113883.10.20.1.2";
    public static final String CCDTID_ENCOUNTERS_SECTION        = "2.16.840.1.113883.10.20.1.3";
    public static final String CCDTID_FAMILY_HISTORY_SECTION    = "2.16.840.1.113883.10.20.1.4";
    public static final String CCDTID_FUNCTIONAL_STATUS_SECTION = "2.16.840.1.113883.10.20.1.5";
    public static final String CCDTID_IMMUNIZATIONS_SECTION     = "2.16.840.1.113883.10.20.1.6";
    public static final String CCDTID_MEDICAL_EQUIPMENT_SECTION = "2.16.840.1.113883.10.20.1.7";
    public static final String CCDTID_MEDICATIONS_SECTION       = "2.16.840.1.113883.10.20.1.8";
    public static final String CCDTID_PAYERS_SECTION            = "2.16.840.1.113883.10.20.1.9";
    public static final String CCDTID_PLAN_OF_CARE_SECTION      = "2.16.840.1.113883.10.20.1.10";
    public static final String CCDTID_PROBLEM_SECTION           = "2.16.840.1.113883.10.20.1.11";
    public static final String CCDTID_PROCEDURES_SECTION        = "2.16.840.1.113883.10.20.1.12";
    public static final String CCDTID_PURPOSE_SECTION           = "2.16.840.1.113883.10.20.1.13";
    public static final String CCDTID_RESULTS_SECTION           = "2.16.840.1.113883.10.20.1.14";
    public static final String CCDTID_SOCIAL_HISTORY_SECTION    = "2.16.840.1.113883.10.20.1.15";
    public static final String CCDTID_VITAL_SIGNS_SECTION       = "2.16.840.1.113883.10.20.1.16";
    //  Clinical Statement Templates
    public static final String CCDTID_ADVANCE_DIRECTIVE_OBS     = "2.16.840.1.113883.10.20.1.17";
    public static final String CCDTID_ALERT_OBS                 = "2.16.840.1.113883.10.20.1.18";
    public static final String CCDTID_AUTHORIZATION_ACTIVITY    = "2.16.840.1.113883.10.20.1.19";
    public static final String CCDTID_COVERAGE_ACTIVITY         = "2.16.840.1.113883.10.20.1.20";
    public static final String CCDTID_ENCOUNTER_ACTIVITY        = "2.16.840.1.113883.10.20.1.21";
    public static final String CCDTID_FAMILY_HISTORY_OBS        = "2.16.840.1.113883.10.20.1.22";
    public static final String CCDTID_FAMILY_HISTORY_ORGANIZER  = "2.16.840.1.113883.10.20.1.23";
    public static final String CCDTID_MEDICATION_ACTIVITY       = "2.16.840.1.113883.10.20.1.24";
    public static final String CCDTID_PLAN_OF_CARE_ACTIVITY     = "2.16.840.1.113883.10.20.1.25";
    public static final String CCDTID_POLICY_ACTIVITIY          = "2.16.840.1.113883.10.20.1.26";
    public static final String CCDTID_PROBLEM_ACT               = "2.16.840.1.113883.10.20.1.27";
    public static final String CCDTID_PROBLEM_OBSERVATION       = "2.16.840.1.113883.10.20.1.28";
    public static final String CCDTID_PROCEDURE_ACTIVITY        = "2.16.840.1.113883.10.20.1.29";
    public static final String CCDTID_PURPOSE_ACTIVITY          = "2.16.840.1.113883.10.20.1.30";
    public static final String CCDTID_RESULT_OBSERVATION        = "2.16.840.1.113883.10.20.1.31";
    public static final String CCDTID_RESULT_ORGANIZER          = "2.16.840.1.113883.10.20.1.32";
    public static final String CCDTID_SOCIAL_HISTORY_OBS        = "2.16.840.1.113883.10.20.1.33";
    public static final String CCDTID_SUPPLY_ACTIVITY           = "2.16.840.1.113883.10.20.1.34";
    public static final String CCDTID_VITAL_SIGNS_ORGANIZER     = "2.16.840.1.113883.10.20.1.35";
    //  Supporting Templates (used within a clinical statement)
    public static final String CCDTID_ADVANCE_DIRECTIVE_REF     = "2.16.840.1.113883.10.20.1.36";
    public static final String CCDTID_ADVANCE_DIRECTIVE_STATUS_OBS = "2.16.840.1.113883.10.20.1.37";
    public static final String CCDTID_AGE_OBS                   = "2.16.840.1.113883.10.20.1.38";
    public static final String CCDTID_ALERT_STATUS_OBS          = "2.16.840.1.113883.10.20.1.39";
    public static final String CCDTID_COMMENT                   = "2.16.840.1.113883.10.20.1.40";
    public static final String CCDTID_EPISODE_OBS               = "2.16.840.1.113883.10.20.1.41";
    public static final String CCDTID_FAMILY_HISTORY_CAUSE_OF_DEATH_OBS = "2.16.840.1.113883.10.20.1.42";
    public static final String CCDTID_FULFILLMENT_INSTRUCTION           = "2.16.840.1.113883.10.20.1.43";
    public static final String CCDTID_STATUS_OF_FUNCTIONAL_STATUS_OBS   = "2.16.840.1.113883.10.20.1.44";
    public static final String CCDTID_LOCATION_PARTICIPATION            = "2.16.840.1.113883.10.20.1.45";
    public static final String CCDTID_MEDICATION_SERIES_NUMBER_OBS      = "2.16.840.1.113883.10.20.1.46";
    public static final String CCDTID_MEDICATION_STATUS_OBS             = "2.16.840.1.113883.10.20.1.47";
    public static final String CCDTID_PATIENT_AWARENESS                 = "2.16.840.1.113883.10.20.1.48";
    public static final String CCDTID_PATIENT_INSTRUCTION               = "2.16.840.1.113883.10.20.1.49";
    public static final String CCDTID_PROBLEM_STATUS_OBS                = "2.16.840.1.113883.10.20.1.50";
    public static final String CCDTID_PROBLEM_HEALTH_STATUS_OBS         = "2.16.840.1.113883.10.20.1.51";
    public static final String CCDTID_PRODUCT_INSTANCE                  = "2.16.840.1.113883.10.20.1.52";
    public static final String CCDTID_PRODUCT                           = "2.16.840.1.113883.10.20.1.53";
    public static final String CCDTID_REACTION_OBS                      = "2.16.840.1.113883.10.20.1.54";
    public static final String CCDTID_SEVERITY_OBS                      = "2.16.840.1.113883.10.20.1.55";
    public static final String CCDTID_SOCIAL_HISTORY_STATUS_OBS         = "2.16.840.1.113883.10.20.1.56";
    public static final String CCDTID_STATUS_OBS                        = "2.16.840.1.113883.10.20.1.57";
    public static final String CCDTID_VERIFICATION_OF_ADVANCE_DIRECTIVE_OBS= "2.16.840.1.113883.10.20.1.58";
    
    public static final String HL7_GENERAL_HEADER_CONSTRAINTS            = "2.16.840.1.113883.10.20.3";

 
    
}
