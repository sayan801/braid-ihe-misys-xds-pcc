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
import com.misyshealthcare.connect.base.SharedEnums.DischargeDispositionCode;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.AuthorDevice;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.DataEnterer;
import com.misyshealthcare.connect.doc.ccd.Encounter;
import com.misyshealthcare.connect.doc.ccd.Id;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.Organization;
import com.misyshealthcare.connect.doc.ccd.Performer;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryBuilder;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryData;
import com.misyshealthcare.connect.doc.ccd.SourcePatientInfo;

/**
 * Create a XD-MS Referral Summary Sample
 * See http://ihewiki.wustl.edu/wiki/index.php/MESA/Content_Creator#40003:_PCC_Schematron_Patient_Randolph
 * 
 * @author Wenzhi Li
 * @version 1.0, Jan 13, 2009
 */
public class Test40003 extends TestPCCData {
	   public static void main(String[] args) {
	    	MesaTestLogger log = new MesaTestLogger(System.out);
	        log.writeTestBegin("40003");
	    	TestKit.configActor(log, "xdsnist");

	        String outputFile = "c:\\temp\\pcctest\\EHR_ALLSCRIPTS_CONNECT_40003.xml";
	    	try {	
	            ReferralSummaryData data = new ReferralSummaryData();
	              
	              data.setMetadata( getMetaData());
	              
	              data.setReferralReason("Patient fell while skating and complains of a sore wrist.");
	              data.setHistoryOfPresentIllness(new String[]{"Patient fell while skating. Suspect fractured radius."});
	              data.setActiveProblems( getProblems() );  
	              data.setMedications(getMedications());
	              data.setAllergies(getAllergies());              
	              data.setSurgicalHistories(getSurgicalHistory());
	              data.setImmunizations(getImmunizations());
//	              data.setResolvedProblems(resolvedProblems)

//	              data.setFamilyHistories(getFamilyHistory());
//	              data.setSocialHistories(getSocialHistory());
//	              data.setMeasurements(getVitalSigns());
//	              data.setPhysicalExams(getPhysicalExams());
//	              data.setProcedures(getProcedure());
//	              data.setLabResults(getLabResults());
//                  data.setPlanOfCares(getPlanOfCare());
//	              data.setReviewOfSystems(getReviewOfSystem());
//	              
//	              data.setAdvanceDirectives(getAdvanceDirective());
	            
	            ReferralSummaryBuilder builder = new ReferralSummaryBuilder(data);
	            CCDDocument doc = builder.build(outputFile);
	            System.out.println(doc.getContent());
	    	} catch(Exception e) {
	    		e.printStackTrace(); 
	    		log.writeString("Test Failed: " + e.getMessage());
	    	}
			log.writeTestEnd("40003");
	    }

	    public static MetaData getMetaData() {
	    	MetaData md = new MetaData();
	    	Author author = new Author();
	        author.setOrganization(getOrganization()); 
	    	author.setAuthorPerson(getParticipant());
	    	author.setAuthorSpecialities( new String[]{"483"});
	    	
	    	md.setAuthors(new Author[]{author});
	    	md.setClassCode(SharedEnums.XdsClassCode.Transfer_Summarization);
	    	md.setComment("Patient has sever Chest pain");
	    	md.setConfidentialityCodes(new SharedEnums.ConfidentialityCode[]{SharedEnums.ConfidentialityCode.Restricted});
	    	md.setCustodian(getCustodian());
	    	md.setFacilityTypeCode("Outpatient");
	    	md.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);
	    	md.setHomeSystemId("EMR-MISYSCONNECT");
	    	md.setLegalAutheticator(getParticipant());
	    	md.setPatientContacts( getPatientContacts());
	    	md.setPerformers(new Performer[]{getPerformer()});
	    	md.setServiceStartTime(GregorianCalendar.getInstance() );
	    	md.setSourcePatientId(new Id("1.3.6.1.4.1.21367.2005.1.1", "40003"));
	    	md.setSourcePatientInfo(getPatientInfo("Frederick", "Randolph"));
	    	md.setTitle("My Test Referral Summary Document");
	    	md.setCreationTime(GregorianCalendar.getInstance());
	    	return md;
	    }
 }
