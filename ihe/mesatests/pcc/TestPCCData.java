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

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.DischargeDispositionCode;
import com.misyshealthcare.connect.base.SharedEnums.TransportModeCode;
import com.misyshealthcare.connect.base.clinicaldata.AdvanceDirective;
import com.misyshealthcare.connect.base.clinicaldata.Allergy;
import com.misyshealthcare.connect.base.clinicaldata.Battery;
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
import com.misyshealthcare.connect.doc.ccd.EffectiveTime;
import com.misyshealthcare.connect.doc.ccd.Encounter;
import com.misyshealthcare.connect.doc.ccd.Id;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.Organization;
import com.misyshealthcare.connect.doc.ccd.Participant;
import com.misyshealthcare.connect.doc.ccd.PatientContact;
import com.misyshealthcare.connect.doc.ccd.Performer;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ProposedDisposition;
import com.misyshealthcare.connect.doc.ccd.SourcePatientInfo;
import com.misyshealthcare.connect.doc.ccd.TransportMode;

/**
 * The test data for all PCC Tests.
 * 
 * @author Wenzhi Li
 * @version 1.0, Jan 11, 2009
 */
public class TestPCCData {
    
    public static AdvanceDirective[] getAdvanceDirective() {
	      AdvanceDirective[] ads = new AdvanceDirective[1];
	      ads[0] = new AdvanceDirective("Do not resuscitate", new Code("304251008", null, "SNOMED CT", null), GregorianCalendar.getInstance(), "completed", "No comment", null);
	      return ads;
    }
    public static String[] getPlanOfCare() {
	      String[] cares = new String[1];
	      cares[0] = "Retain cast for 6 weeks. Exercise plan thereafter.";
	      return cares;
    }
    public static String getHospitalCourse() {
    	 String ret = "Patient vital signs were recorded\n"  +
    	 			  "Patient was examined for broken wrist.\n" + 
    	 			  "X-Rays of the right wrist were taken. Radiological interpretation indicated fractured radius.\n"+ 
    	 			  "Cast was placed on right wrist.\n"+ 
    	 			  "Patient was given ibuprofen, instructions and discharged. ";
    	 return ret;
    }
    public static Procedure[] getProcedure() {
	      Procedure[] procedures = new Procedure[1];
	      procedures[0] = new Procedure(GregorianCalendar.getInstance(), "Total hip replacement, left", new Code("52734007", null, "LOINC", null), SharedEnums.StatusCode.COMPLETED, null, null);
	      return procedures;
    }
    public static String[] getPhysicalExams() {
	      String[] exams = new String[1];
	      exams[0] = "GENERAL APPEARANCE: Well developed, well nourished individual in no acute distress.";
	      return exams;
    }
    public static LabResult[] getLabResults() {
    	LabResult[] labs = new LabResult[1];
    	LabResult lab = new LabResult();
    	Result result = new Result(null, "100", "L", "Preliminary", 
    			new GregorianCalendar(2009, 0, 28), null, "Normal", "ACETYLCHOLINE RECEPTOR BLOCKING ANTIBODY", null);
    	Result[] results = new Result[]{result};    	
    	
    	Test test = new Test(results);
    	test.setOrder(new Order(null, "12324", "ResultsInProgress", null));
    	Battery battery = new Battery(new Test[]{test});
    	battery.setCode(new Code("235.0", null, "LOINC", null));
    	battery.setName("1");
    	lab.setBattery(new Battery[]{battery});
    	labs[0] = lab;
    	return labs;
    }
    public static Measurements[] getVitalSigns() {
	      Measurements[] measurements = new Measurements[1];
	      measurements[0] = new Measurements(GregorianCalendar.getInstance(), "Temperature", "Head", "Home",
	              "97", "F", null, "fever", null);
	      return measurements;
    }
    public static String[] getReviewOfSystem() {
	      String[] reviews = new String[1];
	      reviews[0] = "REVIEW OF SYSTEMS:  Patient denies all symptoms in all systems except as noted.";
	      return reviews;
    }
    public static String[] getSocialHistory() {
	      String[] histories = new String[1];
	      histories[0] = "MARITAL STATUS: Engaged, living with significant other.";
	      return histories;
    }
    public static String[] getFamilyHistory() {
	      String[] histories = new String[2];
	      histories[0] = "GENERAL FAMILY ILLNESS: POSITIVE HISTORY OF ALCOHOLISM.";
	      histories[1] = "FATHER: The father's health status is unknown.";
	      return histories;
    }
    public static Immunization[] getImmunizations() {
	      Immunization[] immunizations = new Immunization[3];
	      immunizations[0] = new Immunization(new GregorianCalendar(2007, 10, 10), "Influenza", new Code("88",  null, "LOINC", null),
	    		  new Code("IAINJP", "Injection", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", null), null), null, "Left arm", "reference",  "Schnucks Pharmacy");
	      immunizations[1] = new Immunization(new GregorianCalendar(2005, 7, 10), "Tetanus", new Code("88",  null, "LOINC", null),
	    		  new Code("IAINJP", "Injection", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", null), null), null, "Left arm", "reference",  "Eastern University Health Services");
	      immunizations[2] = new Immunization(new GregorianCalendar(1980, 0, 15), "DTaP (Diphtheria, Tetanus & Acellular Pertussis Vaccine)", new Code("88",  null, "LOINC", null),
	    		  new Code("IAINJP", "Injection", "RouteOfAdministration", null), new DoseQuantity(new Quantity("1", null), null), null, "Arm", "reference",  "Pediatrician Office: Dr. Flanders");
	      return immunizations;
    }
    public static String[] getSurgicalHistory() {
	      String[] surgeries = new String[2];
	      surgeries[0] = "Right knee arthroscopy by Right knee arthroscopy in St Lukes Hospital on 4/3/1995.";
	      surgeries[1] = "Left knee arthroscopy by Right knee arthroscopy in West County Orthopedic Center on 5/15/1997.";
	      return surgeries;
    }
    public static Allergy[] getAllergies() {
	      Allergy[] allergies = new Allergy[1];
	      allergies[0] = new Allergy("penicillin", "positive", null, null, new GregorianCalendar(), null);
	      return allergies;
    }
    public static Medication[] getMedications() {
	      Medication[] medications = new Medication[2];
	      medications[0] = new Medication("Augmentin 125-31.25 MG CHEW", new GregorianCalendar(2008, 0, 14), null, new Code("472.0", null, "ICD9", null), "3 once a week", new Code("CHEW", "Oral", "RouteOfAdministration", null), new DoseQuantity(new Quantity("3", null), null), null, null, "", null, "active", null);
	      medications[1] = new Medication("Albuterol Sulfate 108 MCG/ACT AERS", new GregorianCalendar(2008, 0, 14), null, new Code("472.0", null, "ICD9", null), "2 Puffs q6hrs prn", new Code("IPINHL", "Inhalation", "RouteOfAdministration", null), new DoseQuantity(new Quantity("2", "Puffs"), null), null, null, "", null, "active", null);
	      return medications;
    }
    public static Medication[] getDischargeMedications() {
	      Medication[] medications = new Medication[1];
	      medications[0] = new Medication("Ibuprofen, 200 mg", new GregorianCalendar(2009, 1, 23), null, new Code("472.0", null, "ICD9", null), "2 tables, 4 times per day ", new Code("PO", "Oral", "RouteOfAdministration", null), new DoseQuantity(new Quantity("2", "tablets"), null), "4 times per day", "5 days", "", null, "active", null);
	      return medications;
    }
    public static SimpleProblem[] getProblems() {
	      SimpleProblem[] problems = new SimpleProblem[2];
	      problems[0] = new SimpleProblem("Stress fracture, right foot", new Code("813.42", "closed dupuytrens fracture radius", "ICD9", null), new GregorianCalendar(2006, 7, 1), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys EMR", "");
	      problems[1] = new SimpleProblem("Ear infection", new Code(null, null, "ICD9", null), new GregorianCalendar(2006, 9, 1), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys EMR", "");
	      return problems;
    }
    public static SimpleProblem[] getResolvedProblems() {
	      SimpleProblem[] problems = new SimpleProblem[2];
	      problems[0] = new SimpleProblem("Torn meniscus in right knee", new Code("836.2", "Tear meniscus", "ICD9", null), new GregorianCalendar(1995, 2, 3), new GregorianCalendar(1995, 3, 3), SharedEnums.ClinicalStatusCode.RESOLVED, "Misys EMR", "Orthopedic surgeon: Roger Sutherland");
	      problems[1] = new SimpleProblem("Torn meniscus in left knee", new Code("836.2", "Tearr meniscus", "ICD9", null), new GregorianCalendar(1997, 3, 10), new GregorianCalendar(1997, 4, 15), SharedEnums.ClinicalStatusCode.RESOLVED, "Misys EMR", "Orthopedic surgeon: Roger Sutherland");
	      return problems;
    }
    public static SimpleProblem[] getAdmittingDisagnosis() {
	      SimpleProblem[] problems = new SimpleProblem[1];
	      problems[0] = new SimpleProblem("Fractured radius", new Code("813.42", "closed dupuytrens fracture radius", "ICD9", null), new GregorianCalendar(2006, 7, 1), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys EMR", "");
	      return problems;
    }
    public static SimpleProblem[] getDischargeDisagnosis() {
	      SimpleProblem[] problems = new SimpleProblem[4];
	      problems[0] = new SimpleProblem("Fractured Radius", new Code("813.42", "closed dupuytrens fracture radius", "ICD9", null), new GregorianCalendar(2009, 1, 23), null, SharedEnums.ClinicalStatusCode.RESOLVED, "Misys EMR", "");
	      problems[1] = new SimpleProblem("Right wrist was placed in cast ",  new Code(null, null, "ICD9", null), new GregorianCalendar(2009, 1, 23), null, SharedEnums.ClinicalStatusCode.RESOLVED, "Misys EMR", "");
	      problems[2] = new SimpleProblem("Cast to be removed in 6 weeks",  new Code(null, null, "ICD9", null), new GregorianCalendar(2009, 3, 10), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys EMR", "");
	      problems[3] = new SimpleProblem("Rehab exercises discussed with patient",  new Code(null, null, "ICD9", null), new GregorianCalendar(2009, 3, 10), null, SharedEnums.ClinicalStatusCode.ACTIVE, "Misys EMR", "");
	      return problems;    	
    }

    public static MetaData createMetaData(String firstName, String lastName) {
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
    	md.setSourcePatientId(new Id("2.16.840.1.113883.3.65.2.1", "33334455"));
    	md.setSourcePatientInfo(getPatientInfo(firstName, lastName));
    	md.setTitle("My Test Referral Summary Document");
    	Encounter encounter = new Encounter();
    	encounter.setDischargeDispositionCode(DischargeDispositionCode.DISCHARGE_TO_HOME_OR_SELFCARE);
    	encounter.setStartTime(new GregorianCalendar(2009, 0, 1, 12, 36, 58));
    	encounter.setStartTime(new GregorianCalendar(2009, 1, 23, 5, 18, 39));    	
    	md.setEncounters(new Encounter[]{encounter});
    	md.setCreationTime(GregorianCalendar.getInstance());
    	return md;
    }
    public static SourcePatientInfo getPatientInfo(String firstName, String lastName) {
    	SourcePatientInfo pi = new SourcePatientInfo();
    	pi.setAddress(new Address[]{getAddress()});
    	pi.setBirthdate(new GregorianCalendar(1959, 1, 3));
    	pi.setGender(SharedEnums.SexType.MALE);
    	pi.setMaritalStatus(SharedEnums.MartitalStatusType.MARRIED);
    	Organization org = new Organization();
    	org.setOrganizationName("Sunrise Investment LLC");
    	pi.setOrganization(new Organization());
    	pi.setPersonName(getPersonaName(firstName, lastName));
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
    	pc.setPatientContactType(SharedEnums.PatientContactType.EMERGENCY_CONTACTS);    	
    	PatientContact[] ret = new PatientContact[]{pc};
    	return ret;
    }
    public static Organization getCustodian() {    	 
    	return getOrganization();
    }
    public  static Organization getOrganization() {
    	Organization org = new Organization();
    	org.setOrganizationName("Great NC RHIO");
        org.setAddress(new Address[]{getAddress()});
    	org.setPhoneNumbers(new PhoneNumber[]{getPhoneNumber()});
    	org.setId(new Id("2.8.233.3", "12345"));   
    	return org;
    }
    public static Participant getParticipant() {
    	Participant ap = new Participant();
    	ap.setAddress(new Address[]{getAddress()});
    	ap.setPhoneNumbers(new PhoneNumber[]{getPhoneNumber()});
    	ap.setPersonName(getPersonaName("John", "DePinto"));
    	ap.setId(new Id("2.3.515.3", "12345"));    	
    	return ap;
    }

    public static PersonName getPersonaName(String firstName, String lastName) {
    	PersonName name = new PersonName();
    	name.setFirstName(firstName);
    	name.setLastName(lastName);
    	name.setMiddleName("M");
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

   public static ProposedDisposition getProposedDisposition() {
	   ProposedDisposition pd = new ProposedDisposition();
	   pd.setDischargeDispositionCode(DischargeDispositionCode.DISCHARGE_TO_HOME_OR_SELFCARE);
	   pd.setEncounterDisposition("Patient released at 17:00\n" + 
                                  "Patient walked out of ER and to be driven home by relative\n"+ 
                                  "Patient transferred to sister, Abigail Cox");
	   pd.setEffectiveTime(new EffectiveTime(new GregorianCalendar(1009, 1, 23, 17, 0), null));
	   
	   return pd;
   }
   public static TransportMode getTransportMode() {
	   TransportMode mode = new TransportMode(TransportModeCode.WALKIN_FOLLOWING_TRANSPORT_VIA_PRIVATE_TRANSPORTATION, new GregorianCalendar(2009, 1, 23, 13, 0)); 
	   return mode;
   }

}
