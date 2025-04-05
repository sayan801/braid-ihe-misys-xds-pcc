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

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.clinicaldata.AdvanceDirective;
import com.misyshealthcare.connect.base.clinicaldata.Allergy;
import com.misyshealthcare.connect.base.clinicaldata.Code;
import com.misyshealthcare.connect.base.clinicaldata.DoseQuantity;
import com.misyshealthcare.connect.base.clinicaldata.Immunization;
import com.misyshealthcare.connect.base.clinicaldata.LabResult;
import com.misyshealthcare.connect.base.clinicaldata.Measurements;
import com.misyshealthcare.connect.base.clinicaldata.Medication;
import com.misyshealthcare.connect.base.clinicaldata.Order;
import com.misyshealthcare.connect.base.clinicaldata.Procedure;
import com.misyshealthcare.connect.base.clinicaldata.Quantity;
import com.misyshealthcare.connect.base.clinicaldata.Result;
import com.misyshealthcare.connect.base.clinicaldata.SimpleProblem;
import com.misyshealthcare.connect.base.clinicaldata.Test;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.EffectiveTime;
import com.misyshealthcare.connect.doc.ccd.Id;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.Organization;
import com.misyshealthcare.connect.doc.ccd.Participant;
import com.misyshealthcare.connect.doc.ccd.PatientContact;
import com.misyshealthcare.connect.doc.ccd.Performer;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryBuilder;
import com.misyshealthcare.connect.doc.ccd.ReferralSummaryData;
import com.misyshealthcare.connect.doc.ccd.SourcePatientInfo;
import com.misyshealthcare.connect.util.LibraryConfig;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 7, 2007
 */
public class ReferralSummaryTest extends TestCase {

	String outputFile = System.getProperty("user.dir") + File.separator + "referralSummary.xml";
	protected void setUp() throws Exception {
    	LibraryConfig.getInstance().setOidSource(MemoryOidSource.getInstance());
    	LibraryConfig.getInstance().setOidRoot("2.16.840.1.113883.3.28.1");
		super.setUp();
}
	
    public void tearDown() {
        File file = new File(outputFile);
//        if ( file.exists() ) {
//            file.delete();
//        }
    }
    
    public void testReferralSummary() throws Exception {
        ReferralSummaryData data = new ReferralSummaryData();
        data.setMetadata( _createMetaData());
        data.setActiveProblems( _getProblems() );
        data.setMedications(_getMedications());
        data.setAllergies(_getAllergies());
        data.setSurgicalHistories(_getSurgicalHistory());
        data.setImmunizations(_getImmunizations());
        data.setFamilyHistories(_getFamilyHistory());
        data.setSocialHistories(_getSocialHistory());
        data.setReviewOfSystems(_getReviewOfSystem());
        data.setMeasurements(_getVitalSigns());
        data.setPhysicalExams(_getPhysicalExams());
        data.setProcedures(_getProcedure());
        data.setPlanOfCares(_getPlanOfCare());
        data.setAdvanceDirectives(_getAdvanceDirective());
        data.setLabResults(_getLabResults());
        
        ReferralSummaryBuilder builder = new ReferralSummaryBuilder(data);
        CCDDocument doc = builder.build(outputFile);
        System.out.println("file path="+outputFile);
//        CCDDocument doc = CDABuilder.getInstance().buildReferralSummary(data, outputFile);
//        assertEquals("text/xml", doc.getMimeType());
    }
    
    private LabResult[] _getLabResults() {
    	Test test = new Test();
	      test.setCodes(new Code[]{new Code("52734007", null, "LOINC", null)});
	      test.setName("testName1");
	      Order order = new Order();
	      order.setStatus("Completed");
	      
	      Result result = new Result();
	      result.setValue("13.2");
	      result.setUom("g/dl");
	      result.setStatus("completed");
	      result.setDate(Calendar.getInstance());
	      result.setRange("10-12");
	      test.setResult(new Result[] {result});
	      test.setOrder(order);
	      LabResult[] lResults = new LabResult[1];
	      lResults[0] = new LabResult(new Test[]{test},null);
	      
	      return lResults;
	}

	private AdvanceDirective[] _getAdvanceDirective() {
	      AdvanceDirective[] ads = new AdvanceDirective[1];
	      ads[0] = new AdvanceDirective("Do not resuscitate", new Code("304251008", null, "SNOMED", null), GregorianCalendar.getInstance(), "completed", "No comment", null);
	      return ads;
    }
    private String[] _getPlanOfCare() {
	      String[] cares = new String[2];
	      cares[0] = "PlanOfCare 1";
	      cares[1] = "PlanOfCare 2";
	      return cares;
    }
    private Procedure[] _getProcedure() {
	      Procedure[] procedures = new Procedure[1];
	      procedures[0] = new Procedure(GregorianCalendar.getInstance(), "Total hip replacement, left", new Code("52734007", null, "LOINC", null), SharedEnums.StatusCode.COMPLETED, null, null);
	      return procedures;
    }
    private String[] _getPhysicalExams() {
	      String[] exams = new String[2];
	      exams[0] = "PhysicalExam 1";
	      exams[1] = "PhysicalExam 2";
	      return exams;
    }
    private Measurements[] _getVitalSigns() {
	      Measurements[] measurements = new Measurements[1];
	      measurements[0] = new Measurements(GregorianCalendar.getInstance(), "Temperature", "Head", "Home",
	              "97", "F", null, "fever", null);
	      return measurements;
    }
    private String[] _getReviewOfSystem() {
	      String[] reviews = new String[2];
	      reviews[0] = "System Review 1";
	      reviews[1] = "System Review 2";
	      return reviews;
    }
    private String[] _getSocialHistory() {
	      String[] histories = new String[2];
	      histories[0] = "Social history 1";
	      histories[1] = "Social history 2";
	      return histories;
    }
    private String[] _getFamilyHistory() {
	      String[] histories = new String[2];
	      histories[0] = "Family history 1";
	      histories[1] = "Family history 2";
	      return histories;
    }
    private Immunization[] _getImmunizations() {
	      Immunization[] immunizations = new Immunization[1];
	      immunizations[0] = new Immunization(GregorianCalendar.getInstance(), "Influenza virus vaccine, IM", new Code("88", null, "LOINC", null),
	    		  new Code("14628", "Oral", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", null), null), null, "comment", "reference", null);
	      return immunizations;
    }
    private String[] _getSurgicalHistory() {
	      String[] surgeries = new String[2];
	      surgeries[0] = "Surgery history 1";
	      surgeries[1] = "Surgery history 2";
	      return surgeries;
    }
    private Allergy[] _getAllergies() {
	      Allergy[] allergies = new Allergy[1];
	      allergies[0] = new Allergy("Iodinated contrast", "laryngeal oedema", null, null, new GregorianCalendar(), null);
	      return allergies;
    }
    private Medication[] _getMedications() {
	      Medication[] medications = new Medication[1];
	      medications[0] = new Medication("Amoxicillin (250 mg tabs)", new GregorianCalendar(), null, new Code("472.0", null, "ICD9", null), "3 times per day  ora", new Code("14735", "Oral", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", "tablet"), null), "t.i.d", null, "", null, "active", null);
	      return medications;
    }
    private SimpleProblem[] _getProblems() {
	      SimpleProblem[] problems = new SimpleProblem[2];
	      problems[0] = new SimpleProblem("CHRONIC PHARYNGITIS AND NASOPHARYNGITIS", new Code("472.0", null, "ICD9", null), new GregorianCalendar(), null, SharedEnums.ClinicalStatusCode.RESOLVED, "Misys CPR", "This is comment1");
	      problems[1] = new SimpleProblem("Problem2", new Code("222.2", null, "ICD9", null), new GregorianCalendar(), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys CPR", "This is comment2");
	      return problems;
    }
    
    private MetaData _createMetaData() {
    	MetaData md = new MetaData();
    	Author author = new Author();
        author.setOrganization(_getOrganization()); 
    	author.setAuthorPerson(_getParticipant());
    	md.setAuthors(new Author[]{author});
    	md.setClassCode(SharedEnums.XdsClassCode.Transfer_Summarization);
    	md.setComment("Patient has sever Chest pain");
    	md.setConfidentialityCodes(new SharedEnums.ConfidentialityCode[]{SharedEnums.ConfidentialityCode.Restricted});
    	md.setCustodian(_getCustodian());
    	md.setFacilityTypeCode("Inpatient");
    	md.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);
    	md.setLegalAutheticator(_getParticipant());
    	md.setPatientContacts( _getPatientContacts());
    	md.setPerformers(new Performer[]{_getPerformer()});
    	md.setServiceStartTime(GregorianCalendar.getInstance() );
    	md.setSourcePatientId(new Id("2.16.840.1.113883.3.28.1", "33334455"));
    	md.setSourcePatientInfo(_getPatientInfo());
    	md.setTitle("My Test Referral Summary Document");
    	return md;
    }
    private SourcePatientInfo _getPatientInfo() {
    	SourcePatientInfo pi = new SourcePatientInfo();
    	pi.setAddress(new Address[]{_getAddress()});
    	pi.setBirthdate(GregorianCalendar.getInstance());
    	pi.setGender(SharedEnums.SexType.MALE);
    	pi.setOrganization(new Organization());
    	pi.setPersonName(_getPersonaName("Susan"));
    	pi.setPhoneNumber(new PhoneNumber[]{_getPhoneNumber()});
    	return pi;
    }
    private Performer _getPerformer() {
    	Performer performer = new Performer();
    	performer.setAddress(new Address[]{_getAddress()});
    	performer.setStartTime(GregorianCalendar.getInstance());
    	performer.setPhoneNumbers(new PhoneNumber[]{_getPhoneNumber()});
    	performer.setPersonName(new PersonName("Joe", "Atkinson", null, null, null));
    	return performer;
    }
    private PatientContact[] _getPatientContacts() {
    	PatientContact pc = new PatientContact();
    	pc.setEffectiveTime(new EffectiveTime(GregorianCalendar.getInstance(), null));
    	pc.setParticipant(_getParticipant());
    	pc.setPatientContactType(SharedEnums.PatientContactType.GUARDIAN);
    	PatientContact pc2 = new PatientContact();
    	pc2.setEffectiveTime(new EffectiveTime(GregorianCalendar.getInstance(), null));
    	pc2.setParticipant(_getParticipant());
    	pc2.setPatientContactType(SharedEnums.PatientContactType.CARE_GIVERS);    	
    	PatientContact[] ret = new PatientContact[]{pc, pc2};
    	return ret;
    }
    private Organization _getCustodian() {    	 
    	return _getOrganization();
    }
    private Organization _getOrganization() {
    	Organization org = new Organization();
    	org.setOrganizationName("Great NC RHIO");
        org.setAddress(new Address[]{_getAddress()});
    	org.setPhoneNumbers(new PhoneNumber[]{_getPhoneNumber()});
    	org.setId(new Id("2.8.233.3", "12345"));   
    	return org;
    }
    private Participant _getParticipant() {
    	Participant ap = new Participant();
    	ap.setAddress(new Address[]{_getAddress()});
    	ap.setPhoneNumbers(new PhoneNumber[]{_getPhoneNumber()});
    	ap.setPersonName(_getPersonaName("John"));
    	ap.setId(new Id("2.3.515.3", "12345"));    	
    	return ap;
    }
    private PersonName _getPersonaName(String firstName) {
    	PersonName name = new PersonName();
    	name.setFirstName(firstName);
    	name.setLastName("DePinto");
    	name.setMiddleName("F");
    	name.setPrefix("Mr.");
    	name.setSuffix("II");
    	return name;
    }
    
    private Address _getAddress() {
        Address addr = new Address();
        addr.setAddLine1("700 Euclid Ave");
        addr.setAddCity("St. Louis");
        addr.setAddState("MO");
        addr.setAddZip("63110");
        addr.setAddCountry("USA");
        return addr;
    }
    private PhoneNumber _getPhoneNumber() {
    	PhoneNumber phone = new PhoneNumber();
    	phone.setCountryCode("1");
    	phone.setAreaCode("716");
    	phone.setNumber("358-7421");
    	phone.setType(SharedEnums.PhoneType.WORK);
    	return phone;
    }
}
