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

import mesatests.MesaTestLogger;
import mesatests.xds.TestKit;

import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.DischargeSummaryBuilder;
import com.misyshealthcare.connect.doc.ccd.DischargeSummaryData;

/**
 * Create a XD-MS Discharge Summary Sample
 * 
 * @author Wenzhi Li
 * @version 1.0, Jan 13, 2009
 */
public class Test40154 extends TestPCCData {
	   public static void main(String[] args) {
	    	MesaTestLogger log = new MesaTestLogger(System.out);
	        log.writeTestBegin("40154");
	    	TestKit.configActor(log, "xdsnist");

	        String outputFile = "c:\\temp\\pcctest\\EHR_ALLSCRIPTS_CONNECT_40154.xml";
	    	try {	
	            DischargeSummaryData data = new DischargeSummaryData();
	              
	              data.setMetadata( createMetaData("Allscripts", "EHR_ALLSCRIPTS_CONNECT"));
	              
	              data.setHistoryOfPresentIllness(new String[]{"Patient fell while skating. Suspect fractured radius."});
	              data.setActiveProblems( getProblems() );  
	              data.setMedications(getMedications());
	              data.setAllergies(getAllergies());              
	              data.setResolvedProblems(getResolvedProblems());
	              data.setPlanOfCares(getPlanOfCare());
	              data.setHospitalCourse(getHospitalCourse());
	              data.setDischargeDiagnosis(getDischargeDisagnosis());
	              data.setAdmittingDiagnosis(getAdmittingDisagnosis());
	              
	              data.setAdvanceDirectives(getAdvanceDirective());
	              data.setReviewOfSystems(getReviewOfSystem());
	              data.setPhysicalExams(getPhysicalExams());
	              data.setMeasurements(getVitalSigns());
	              data.setProcedures(getProcedure());

	            DischargeSummaryBuilder builder = new DischargeSummaryBuilder(data);
	            CCDDocument doc = builder.build(outputFile);
	            System.out.println(doc.getContent());
	    	} catch(Exception e) {
	    		e.printStackTrace(); 
	    		log.writeString("Test Failed: " + e.getMessage());
	    	}
			log.writeTestEnd("40154");
	    }

}
