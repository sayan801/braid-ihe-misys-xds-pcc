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
package mesatests.xds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import mesatests.MesaTestLogger;

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
import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
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
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.configuration.IheActorDescription;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.IBMTest;
import com.misyshealthcare.connect.ihe.TestLogContext;
import com.misyshealthcare.connect.util.OID;



/**
 * Mesa Test 11746 Test Submit one document.
 * Mesa Test 12030 Test Submit one document with Audit Log
 * Mesa Test 11743 Test Submit document with TLS
 * Mesa Test 12049 XDS.b submit one document
 * Mesa Test 12046 XDS.b submit one document with TLS
 * 
 * @author Wenzhi Li
 * @version 3.0, Dec 11, 2007
 */
public class Test11746 {

    public static void main(String[] args) {
    	MesaTestLogger log = new MesaTestLogger(System.out);
    	log.writeTestBegin("11746");
    	
    	TestKit.configActor(log, "xdsb_rep_openxds");   //OpenXDS XDS.b
    	//TestKit.configActor(log, "xds_rep_nist");   //Nist XDS.a
    	//TestKit.configActor(log, "xds_srep_nist");    // Secure Nist XDS.a 
    	//TestKit.configActor(log, "xdsb_rep_nist");   //Nist XDS.b
    	//TestKit.configActor(log, "xdsb_srep_nist");    // Secure Nist XDS.b
    	
    	//TestKit.configActor(log, "xds_srep_ibm"); //secure IBM
    	//TestKit.configActor(log, "xds_srep_oracle"); //secure Oracle
    	//TestKit.configActor(log, "xds_srep_agfa"); //secure AGFA
    	//TestKit.configActor(log, "xds_srep_ge"); //secure GE
        //TestKit.configActor(log, "xds_srep_spirit"); //secure Spirit
    	//TestKit.configActor(log, "xds_srep_fujifilm"); //secure IBM
    	//TestKit.configActor(log, "xds_srep_carestream4"); //secure PACS_CareStream
    	
    	
    	//TestKit.configActor(log, "xdsnist", "localaudit", "arrnist");  //Test 12030
    	//TestKit.configActor(log, "xdsnists"); //secure XDS.a
    	//TestKit.configActor(log, "xdsbnists"); //secure XDS.b
    	//TestKit.configActor(log, "xdsb_srep_hxti"); //secure XDS.b HXTI-TIANI
    	//TestKit.configActor(log, "xdsb_srep_msft"); //secure XDS.b MSFT-IBM
    	//TestKit.configActor(log, "xdsb_srep_ibm"); //secure XDS.b IBM-HXTI
    	//TestKit.configActor(log, "xdsb_srep_spirit"); //secure Spirit
    	
    	try {	
			SubmissionSet ss = new SubmissionSet();
			CCDDocument ccd = createReferralSummary();
			ss.setDocuments(new CCDDocument[]{ccd});
			
 			SubmissionSetMetaData ssmd = new SubmissionSetMetaData();
			ssmd.setComments("Test submit one document");
			ssmd.setTitle("First document");
			ssmd.setContentCode(XdsContentCode.Transfer_Summarization);
			ss.setSubmissionSetMetaData( ssmd );
			IheService.submitDocuments(ss);

    	} catch(Exception e) {
    		e.printStackTrace(); 
    		log.writeString("Test Failed: " + e.getMessage());
    	}
		log.writeTestEnd("11746");
    }
    
    
    public static CCDDocument createReferralSummary() throws CCDException {
        ReferralSummaryData data = new ReferralSummaryData();
        data.setMetadata( createMetaData());
        data.setActiveProblems( getProblems() );
        data.setMedications(getMedications());
        data.setAllergies(getAllergies());
        data.setSurgicalHistories(getSurgicalHistory());
        data.setImmunizations(getImmunizations());
        data.setFamilyHistories(getFamilyHistory());
        data.setSocialHistories(getSocialHistory());
        data.setReviewOfSystems(getReviewOfSystem());
        data.setMeasurements(getVitalSigns());
        data.setPhysicalExams(getPhysicalExams());
        data.setProcedures(getProcedure());
        data.setPlanOfCares(getPlanOfCare());
        data.setAdvanceDirectives(getAdvanceDirective());
        
        ReferralSummaryBuilder builder = new ReferralSummaryBuilder(data);
        CCDDocument doc = builder.build();
        return doc;
    }
    
    public static AdvanceDirective[] getAdvanceDirective() {
	      AdvanceDirective[] ads = new AdvanceDirective[1];
	      ads[0] = new AdvanceDirective("Do not resuscitate", new Code("304251008", null, "LOINC", null), GregorianCalendar.getInstance(), "completed", "No comment", null);
	      return ads;
    }
    public static String[] getPlanOfCare() {
	      String[] cares = new String[2];
	      cares[0] = "PlanOfCare 1";
	      cares[1] = "PlanOfCare 2";
	      return cares;
    }
    public static Procedure[] getProcedure() {
	      Procedure[] procedures = new Procedure[1];
	      procedures[0] = new Procedure(GregorianCalendar.getInstance(), "Total hip replacement, left", new Code("52734007", null, "LOINC", null), SharedEnums.StatusCode.COMPLETED, null, null);
	      return procedures;
    }
    public static String[] getPhysicalExams() {
	      String[] exams = new String[2];
	      exams[0] = "PhysicalExam 1";
	      exams[1] = "PhysicalExam 2";
	      return exams;
    }
    public static Measurements[] getVitalSigns() {
	      Measurements[] measurements = new Measurements[1];
	      measurements[0] = new Measurements(GregorianCalendar.getInstance(), "Temperature", "Head", "Home",
	              "97", "F", null, "fever", null);
	      return measurements;
    }
    public static String[] getReviewOfSystem() {
	      String[] reviews = new String[2];
	      reviews[0] = "System Review 1";
	      reviews[1] = "System Review 2";
	      return reviews;
    }
    public static String[] getSocialHistory() {
	      String[] histories = new String[2];
	      histories[0] = "Social history 1";
	      histories[1] = "Social history 2";
	      return histories;
    }
    public static String[] getFamilyHistory() {
	      String[] histories = new String[2];
	      histories[0] = "Family history 1";
	      histories[1] = "Family history 2";
	      return histories;
    }
    public static Immunization[] getImmunizations() {
	      Immunization[] immunizations = new Immunization[1];
	      immunizations[0] = new Immunization(GregorianCalendar.getInstance(), "Influenza virus vaccine, IM", new Code("88",  null, "LOINC", null),
	    		  new Code("14628", "Oral", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", null), null), null, "comment", "reference",  null);
	      return immunizations;
    }
    public static String[] getSurgicalHistory() {
	      String[] surgeries = new String[2];
	      surgeries[0] = "Surgery history 1";
	      surgeries[1] = "Surgery history 2";
	      return surgeries;
    }
    public static Allergy[] getAllergies() {
	      Allergy[] allergies = new Allergy[1];
	      allergies[0] = new Allergy("Iodinated contrast", "laryngeal oedema", null, null, new GregorianCalendar(), null);
	      return allergies;
    }
    public static Medication[] getMedications() {
	      Medication[] medications = new Medication[1];
	      medications[0] = new Medication("Amoxicillin (250 mg tabs)", new GregorianCalendar(), null, new Code("472.0", null, "ICD9", null), "3 times per day  ora", new Code("14735", "Oral", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", "tablet"), null), "t.i.d", null, "", null, "active", null);
	      return medications;
    }
    public static SimpleProblem[] getProblems() {
	      SimpleProblem[] problems = new SimpleProblem[2];
	      problems[0] = new SimpleProblem("CHRONIC PHARYNGITIS AND NASOPHARYNGITIS", new Code("472.0", null, "ICD9", null), new GregorianCalendar(), null, SharedEnums.ClinicalStatusCode.RESOLVED, "Misys CPR", "This is comment1");
	      problems[1] = new SimpleProblem("Problem2", new Code("222.2", null, "ICD9", null), new GregorianCalendar(), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys CPR", "This is comment2");
	      return problems;
    }
    

    public static MetaData createMetaData() {
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
    	md.setSourcePatientId(new Id("254.345.22.15.3", "33334455"));
    	md.setSourcePatientInfo(getPatientInfo());
    	md.setTitle("My Test Referral Summary Document");
    	
    	md.setCreationTime(GregorianCalendar.getInstance());
    	return md;
    }
    public static SourcePatientInfo getPatientInfo() {
    	SourcePatientInfo pi = new SourcePatientInfo();
    	pi.setAddress(new Address[]{getAddress()});
    	pi.setBirthdate(GregorianCalendar.getInstance());
    	pi.setGender(SharedEnums.SexType.MALE);
    	pi.setOrganization(new Organization());
    	pi.setPersonName(getPersonaName("Susan"));
    	pi.setPhoneNumber(new PhoneNumber[]{getPhoneNumber()});
    	return pi;
    }
    public static Performer getPerformer() {
    	Performer performer = new Performer();
    	performer.setAddress(new Address[]{getAddress()});
    	performer.setStartTime(GregorianCalendar.getInstance());
    	performer.setPhoneNumbers(new PhoneNumber[]{getPhoneNumber()});
    	performer.setPersonName(new PersonName("Joe", "Atkinson", null, null, null));
    	return performer;
    }
    public static PatientContact[] getPatientContacts() {
    	PatientContact pc = new PatientContact();
    	pc.setEffectiveTime(new EffectiveTime(GregorianCalendar.getInstance(), null));
    	pc.setParticipant( getParticipant());
    	pc.setPatientContactType(SharedEnums.PatientContactType.GUARDIAN);
    	PatientContact pc2 = new PatientContact();
    	pc2.setEffectiveTime(new EffectiveTime(GregorianCalendar.getInstance(), null));
    	pc2.setParticipant( getParticipant());
    	pc2.setPatientContactType(SharedEnums.PatientContactType.CARE_GIVERS);    	
    	PatientContact[] ret = new PatientContact[]{pc, pc2};
    	return ret;
    }
    private static Organization getCustodian() {    	 
    	return getOrganization();
    }
    private static Organization getOrganization() {
    	Organization org = new Organization();
    	org.setOrganizationName("Great NC RHIO");
        org.setAddress(new Address[]{getAddress()});
    	org.setPhoneNumbers(new PhoneNumber[]{getPhoneNumber()});
    	org.setId(new Id("2.8.233.3", "12345"));   
    	return org;
    }
    private static Participant getParticipant() {
    	Participant ap = new Participant();
    	ap.setAddress(new Address[]{getAddress()});
    	ap.setPhoneNumbers(new PhoneNumber[]{getPhoneNumber()});
    	ap.setPersonName(getPersonaName("John"));
    	ap.setId(new Id("2.3.515.3", "12345"));    	
    	return ap;
    }

    public static PersonName getPersonaName(String firstName) {
    	PersonName name = new PersonName();
    	name.setFirstName(firstName);
    	name.setLastName("DePinto");
    	name.setMiddleName("F");
    	name.setPrefix("Mr.");
    	name.setSuffix("II");
    	return name;
    }
    
    public static Address getAddress() {
        Address addr = new Address();
        addr.setAddLine1("700 Euclid Ave");
        addr.setAddCity("St. Louis");
        addr.setAddState("MO");
        addr.setAddZip("63110");
        addr.setAddCountry("USA");
        return addr;
    }
    public static PhoneNumber getPhoneNumber() {
    	PhoneNumber phone = new PhoneNumber();
    	phone.setCountryCode("1");
    	phone.setAreaCode("716");
    	phone.setNumber("358-7421");
    	phone.setType(SharedEnums.PhoneType.WORK);
    	return phone;
    }
}
