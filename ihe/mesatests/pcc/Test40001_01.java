/* Copyright 2009 Misys PLC
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
package mesatests.pcc;

import java.util.GregorianCalendar;

import mesatests.MesaTestLogger;
import mesatests.xds.TestKit;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.base.clinicaldata.AdvanceDirective;
import com.misyshealthcare.connect.base.clinicaldata.Allergy;
import com.misyshealthcare.connect.base.clinicaldata.Code;
import com.misyshealthcare.connect.base.clinicaldata.DoseQuantity;
import com.misyshealthcare.connect.base.clinicaldata.Immunization;
import com.misyshealthcare.connect.base.clinicaldata.Measurements;
import com.misyshealthcare.connect.base.clinicaldata.Medication;
import com.misyshealthcare.connect.base.clinicaldata.Procedure;
import com.misyshealthcare.connect.base.clinicaldata.Quantity;
import com.misyshealthcare.connect.base.clinicaldata.SimpleProblem;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.CCDException;
import com.misyshealthcare.connect.doc.ccd.EffectiveTime;
import com.misyshealthcare.connect.doc.ccd.Id;
import com.misyshealthcare.connect.doc.ccd.IheService;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.Organization;
import com.misyshealthcare.connect.doc.ccd.Participant;
import com.misyshealthcare.connect.doc.ccd.PatientContact;
import com.misyshealthcare.connect.doc.ccd.Performer;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryBuilder;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryData;
import com.misyshealthcare.connect.doc.ccd.SourcePatientInfo;
import com.misyshealthcare.connect.doc.ccd.SubmissionSet;
import com.misyshealthcare.connect.doc.ccd.SubmissionSetMetaData;

/**
 * @author Wenzhi Li
 * @version 1.0, Jan 10, 2009
 */
public class Test40001_01 extends TestPCCData {
    public static void main(String[] args) {
    	MesaTestLogger log = new MesaTestLogger(System.out);
        log.writeTestBegin("40001_01");
    	TestKit.configActor(log, "xdsnist");

        String outputFile = "c:\\temp\\pcctest\\ReferralSummary.xml";
    	try {	
            ReferralSummaryData data = new ReferralSummaryData();
              
              data.setMetadata( createMetaData("Allscripts", "EHR_Allscripts_Connect"));
              
              data.setReferralReason("Patient fell while skating and complains of a sore wrist.");
              data.setHistoryOfPresentIllness(new String[]{"Patient fell while skating. Suspect fractured radius."});
              data.setActiveProblems( getProblems() );  
              data.setMedications(getMedications());
              data.setAllergies(getAllergies());              
//              data.setResolvedProblems(resolvedProblems)
              data.setSurgicalHistories(getSurgicalHistory());
              data.setImmunizations(getImmunizations());

              data.setFamilyHistories(getFamilyHistory());
              data.setSocialHistories(getSocialHistory());
              data.setMeasurements(getVitalSigns());
              data.setPhysicalExams(getPhysicalExams());
              data.setProcedures(getProcedure());
              data.setLabResults(getLabResults());
//            data.setPlanOfCares(getPlanOfCare());
              data.setReviewOfSystems(getReviewOfSystem());
              
              data.setAdvanceDirectives(getAdvanceDirective());
            
            ReferralSummaryBuilder builder = new ReferralSummaryBuilder(data);
            CCDDocument doc = builder.build(outputFile);
            System.out.println(doc.getContent());
    	} catch(Exception e) {
    		e.printStackTrace(); 
    		log.writeString("Test Failed: " + e.getMessage());
    	}
		log.writeTestEnd("40001_01");
    }
    
    

}
