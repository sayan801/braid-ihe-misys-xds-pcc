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

import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.AuthorDevice;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.DataEnterer;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryBuilder;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryData;

/**
 * Create a XD-MS Referral Summary Sample
 * 
 * @author Wenzhi Li
 * @version 1.0, Jan 13, 2009
 */
public class Test40155 extends TestPCCData {
	   public static void main(String[] args) {
	    	MesaTestLogger log = new MesaTestLogger(System.out);
	        log.writeTestBegin("40155");
	    	TestKit.configActor(log, "xdsnist");

	        String outputFile = "c:\\temp\\pcctest\\EHR_ALLSCRIPTS_CONNECT_40155.xml";
	    	try {	
	            ReferralSummaryData data = new ReferralSummaryData();
	              
	              data.setMetadata( createMetaData("Allscripts", "EHR_ALLSCRIPTS_CONNECT"));
	              
	              data.setReferralReason("Patient fell while skating and complains of a sore wrist.");
	              data.setHistoryOfPresentIllness(new String[]{"Patient fell while skating. Suspect fractured radius."});
	              data.setActiveProblems( getProblems() );  
	              data.setMedications(getMedications());
	              data.setAllergies(getAllergies());              
//	              data.setResolvedProblems(resolvedProblems)
	              data.setSurgicalHistories(getSurgicalHistory());
	              data.setImmunizations(getImmunizations());

	              data.setFamilyHistories(getFamilyHistory());
	              data.setSocialHistories(getSocialHistory());
	              data.setMeasurements(getVitalSigns());
	              data.setPhysicalExams(getPhysicalExams());
	              data.setProcedures(getProcedure());
	              data.setLabResults(getLabResults());
//	            data.setPlanOfCares(getPlanOfCare());
	              data.setReviewOfSystems(getReviewOfSystem());
	              
	              data.setAdvanceDirectives(getAdvanceDirective());
	            
	            ReferralSummaryBuilder builder = new ReferralSummaryBuilder(data);
	            CCDDocument doc = builder.build(outputFile);
	            System.out.println(doc.getContent());
	    	} catch(Exception e) {
	    		e.printStackTrace(); 
	    		log.writeString("Test Failed: " + e.getMessage());
	    	}
			log.writeTestEnd("40155");
	    }

	   private static MetaData getMetaData() {
           MetaData md = createMetaData("Allscripts", "EHR_ALLSCRIPTS_CONNECT");
	       DataEnterer enterer = new DataEnterer();
	       	enterer.setTime(GregorianCalendar.getInstance());
	       	enterer.setPersonName( new PersonName("Susan", "Smith", "F", null, null));
	       	md.setDataEnterers(new DataEnterer[]{enterer});
	
	       	return md;
	   }
	   
}
