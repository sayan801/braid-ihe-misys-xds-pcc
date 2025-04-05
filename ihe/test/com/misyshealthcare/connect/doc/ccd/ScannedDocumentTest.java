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
import java.util.GregorianCalendar;

import mesatests.MesaTestLogger;
import mesatests.xds.TestKit;

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsFormatCode;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.AuthorDevice;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.DataEnterer;
import com.misyshealthcare.connect.doc.ccd.EffectiveTime;
import com.misyshealthcare.connect.doc.ccd.Id;
import com.misyshealthcare.connect.doc.ccd.IheService;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.Organization;
import com.misyshealthcare.connect.doc.ccd.Participant;
import com.misyshealthcare.connect.doc.ccd.PatientContact;
import com.misyshealthcare.connect.doc.ccd.Performer;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentBuilder;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentData;
import com.misyshealthcare.connect.doc.ccd.SourcePatientInfo;
import com.misyshealthcare.connect.doc.ccd.SubmissionSet;
import com.misyshealthcare.connect.doc.ccd.SubmissionSetMetaData;
import com.misyshealthcare.connect.util.LibraryConfig;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 2.1 Feb 12, 2008
 */
public class ScannedDocumentTest extends TestCase {

	String outputFile = System.getProperty("java.io.tmpdir") + "scannedDocument.xml";
	protected void setUp() throws Exception {
    	LibraryConfig.getInstance().setOidSource(MemoryOidSource.getInstance());
    	LibraryConfig.getInstance().setOidRoot("2.16.840.1.113883.3.28.1");
		super.setUp();
}
	
    public void tearDown() {
        File file = new File(outputFile);
        if ( file.exists() ) {
            file.delete();
        }
    }
    
    public void testScannedDocument() throws Exception {
        ScannedDocumentData data = new ScannedDocumentData();
        MetaData metadata = _createMetaData();
        metadata.setHomeSystemId("EMR-MISYSCONNECT");
        data.setMetadata( metadata );
        data.setMediaType(ScannedDocumentData.MediaType.PDF);
        data.setFileContent(ScannedDocumentTest.class.getResourceAsStream("pdfSample.pdf"));
        
        //build scanned document
        ScannedDocumentBuilder builder = new ScannedDocumentBuilder(data);
        CCDDocument doc = builder.build(outputFile);
        assertNotNull(doc.getMetadata().getCreationTime());
        System.out.println("file path="+outputFile);
        //assertEquals("text/xml", doc.getMimeType());
        //verify doc
        if ( doc != null) {
        	
            XdsFormatCode classCode =  doc.getMetadata().getFormatCode();
            assertEquals(XdsFormatCode.IHE_SCAN_PDF_1X, classCode);
            assertEquals("text/xml", doc.getMetadata().getMimeType().getValue());
        } else {
        	assertTrue("Failed to build a valid document.", false);
        }
        //submit the scanned document
    	MesaTestLogger log = new MesaTestLogger(System.out);
    	TestKit.configActor(log, "xds_rep_ibm"); 
    	
    	try {	
			SubmissionSet ss = new SubmissionSet();
			ss.setDocuments(new CCDDocument[]{doc});
			
 			SubmissionSetMetaData ssmd = new SubmissionSetMetaData();
			ssmd.setComments("Test submit one document");
			ssmd.setTitle("First document");
			ssmd.setContentCode(XdsContentCode.Summarization_of_Episode);
			ss.setSubmissionSetMetaData( ssmd );
			//submit this document using a pre-defined patientId.
			IheService.setTestPatientId("NA5080^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
			IheService.submitDocuments(ss);

    	} catch(Exception e) {
    		//e.printStackTrace(); 
    		log.writeString("Test Failed: " + e.getMessage());
    		assertFalse(false);
    	}
    	assertTrue(true);
    }    
    
    private MetaData _createMetaData() {
    	MetaData md = new MetaData();
    	Author author = new Author();
        author.setOrganization(_getOrganization()); 
    	author.setAuthorPerson(_getParticipant());
    	AuthorDevice device = new AuthorDevice();
    	device.setManufacturerModelName("HP All-in-one 603");
    	device.setSoftwareName("HP Scan Software");
    	author.setAuthorDevice( device );
    	md.setAuthors(new Author[]{author});
    	md.setClassCode(SharedEnums.XdsClassCode.Transfer_Summarization);
    	md.setComment("Patient has sever Chest pain");
    	md.setConfidentialityCodes(new SharedEnums.ConfidentialityCode[]{SharedEnums.ConfidentialityCode.Restricted});
    	md.setCustodian(_getCustodian());
    	md.setFacilityTypeCode("Outpatient");
    	md.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);
    	md.setLegalAutheticator(_getParticipant());
    	md.setPatientContacts( _getPatientContacts());
    	md.setPerformers(new Performer[]{_getPerformer()});
    	md.setServiceStartTime(GregorianCalendar.getInstance() );
    	md.setSourcePatientId(new Id("2.16.840.1.113883.3.28.1", "33334455"));
    	md.setSourcePatientInfo(_getPatientInfo());
    	md.setTitle("My Test Scanned Document");
    	DataEnterer enterer = new DataEnterer();
    	enterer.setTime(GregorianCalendar.getInstance());
    	enterer.setPersonName( new PersonName("Susan", "Smith", "F", null, null));
    	md.setDataEnterers(new DataEnterer[]{enterer});
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
