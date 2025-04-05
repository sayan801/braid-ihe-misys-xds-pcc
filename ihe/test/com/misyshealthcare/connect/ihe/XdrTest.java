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


package com.misyshealthcare.connect.ihe;

import junit.framework.TestCase;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.configuration.IheActorDescription;
import com.misyshealthcare.connect.ihe.registry.XdrIntendedRecipient;
import com.misyshealthcare.connect.base.*;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.util.OID;
import com.misyshealthcare.connect.util.LibraryConfig.ILogContext;

import java.util.*;
import java.io.File;
import java.net.URL;

/**
 * This class tests IHE Actors against IBM Test Server. The server connection details is located at
 * http://ibmod235.dal-ebis.ihost.com:9080/IBMIHII/serverInfoIHE.htm
 * <p>
 * To access audit repository data located at http://ibmod235.dal-ebis.ihost.com:9080/IHIIARRDDQB/index.jsp,
 * use misys/misys as user/password.
 *<p>
 * The configuration files are all in the ihetest folder in the root. The starting file is /ihetest/IheActors.xml.
 * See the comments in each xml file for the explanation of each tag, its attributes and elements.
 *<p>
 * To run these tests, you'd better change the patient id, first name and last name in the _createPatient() to
 * avoid conflicts with previous tests;
 *
 * <p>
 * The order to run the tests are as follows:
 * <pre>
 *   1. testPixFeed
 *   2. testPixQuery
 *   3. testPixUpdatePatient
 *   4. testPdqQuery
 *   5. testSubmitDocument
 *   6. testQueryDocument
 *   7. testReplaceDocument
 *   8. testSubmitMultipleDocuments
 * </pre>
 *
 * @author Wenzhi Li
 * @version 2.1, Jun 14, 2007
 */
public class XdrTest extends TestCase {
    private static ConfigurationLoader loader = null;
    private static long id = System.currentTimeMillis();

    public void setUp() throws Exception {
        loader = ConfigurationLoader.getInstance();
        try {
            URL url = XdrTest.class.getResource("/config/connectathon/IheActors.xml");
            File file = new File(url.toURI());
            loader.loadConfiguration(file, false, "2.16.840.1.113883.3.65.2", new OidMock(), null, null, CodeMappingManager.getInstance(), new TestLogContext());

            //reset to add log file
            List actors = new ArrayList();
            actors.add("xdrs");
            actors.add("xdrr");
            loader.resetConfiguration(actors, "c:\\testlog.xml");
        } catch (IheConfigurationException e) {
            e.printStackTrace();
            fail("Cannot load the actor property");
        }

        //set up log context used for AuditTrail
//        LogContext context = new LogContext();
//        context.setUserId("emr123");
//        context.setUserName("Tom Catpilar");
//        context.setUserSystem("EMR");
//        context.setClientAddress("localhost123");
//        LogManager.setLogContext( context );
    }

    public class OidMock implements OID.OidSource {
        public synchronized String generateId() {
            return Long.toString( System.currentTimeMillis() );
        }
    }

//    public void testPixFeed() {
//        PatientDescriptor patient = _createPatient();
//        PatientBroker patientBroker = PatientBroker.getInstance();
//        try {
//            patientBroker.createPatient(patient, IPatientConsumer.CreationReason.OUTPATIENT_REGISTER);
//            assertTrue( true ); //should be fine if reach here
//        } catch (PatientException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            fail("failed to creat a patient");
//        }
//    }
//
//     //The Pix Query is indirectly used by Patient.java (the hasId, getIdList,
//    // hasMisysUniqueId, getMisysUniqueId). They all internally invoke the
//    // getPatientIds method in the PixConsumer.java.
//    public void testPixQuery() {
//       //PatientBroker patientBroker = PatientBroker.getInstance();
//       PatientDescriptor patient = _createPatient();
//       PatientID pid = patient.getPatientId();
//       //PatientID pid = new PatientID("120001");
//       String misysId = pid.getMisysUniqueId();
//
//       boolean success = false;
//       if ( pid.hasId("1.3.6.1.4.1.21367.2007.1.2.127&ISO") ) {
//           List<String> ids = pid.getIdList("1.3.6.1.4.1.21367.2007.1.2.127&ISO");
//           assertTrue(ids.contains(misysId));
//           success = true;
//       } else
//        if ( pid.hasId("1.3.6.1.4.1.21367.2007.1.2.400&ISO") ) {
//            List<String> ids = pid.getIdList("1.3.6.1.4.1.21367.2007.1.2.400&ISO");
//            assertTrue(ids.contains(misysId));
//            success = true;
//        }
//
//        assertTrue("Pix Query failed", success);
//    }
//
//    public void testPixMergePatient() {
//        PatientDescriptor patientOld = _createPatient();
//        PatientDescriptor patientNew = _createPatient();
//        patientOld.setNameFirst("J");
//        PatientID pidOld = new PatientID("120001");
//        patientOld.setPatientId( pidOld );
//        PatientBroker patientBroker = PatientBroker.getInstance();
//        try {
//            //first submit the new patient
//            patientBroker.createPatient(patientOld, IPatientConsumer.CreationReason.OUTPATIENT_REGISTER);
//            String oldMisysId = pidOld.getMisysUniqueId();
//            if ( pidOld.hasId("1.3.6.1.4.1.21367.2007.1.2.127&ISO") ) {
//                List<String> ids = pidOld.getIdList("1.3.6.1.4.1.21367.2007.1.2.127&ISO");
//                assertTrue(ids.contains(oldMisysId));
//            }
//             if ( pidOld.hasId("1.3.6.1.4.1.21367.2007.1.2.400&ISO") ) {
//                 List<String> ids = pidOld.getIdList("1.3.6.1.4.1.21367.2007.1.2.400&ISO");
//                 assertTrue(ids.contains(oldMisysId));
//             }
//
//            patientBroker.createPatient(patientNew, IPatientConsumer.CreationReason.OUTPATIENT_REGISTER);
//            testPixQuery();
//
//            //then merge the both patient
//            //The Merge needs to use only the local pix feed, not global pix feed.
//           // patientBroker.mergePatients(patientNew, patientOld);
//
//            assertTrue( true ); //should be fine if reach here
//        } catch (PatientException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            fail("failed to creat a patient");
//        }
//    }
//
//    public void testPixUpdatePatient() {
//        PatientDescriptor patient = _createPatient();
//        patient.setNameSuffix("III");
//        PhoneNumber phone = patient.getPhoneNumber(SharedEnums.PhoneType.HOME);
//        phone.setNumber("385-7777");
//        PatientBroker patientBroker = PatientBroker.getInstance();
//        try {
//            patientBroker.updatePatient(patient);
//            assertTrue( true ); //should be fine if reach here
//        } catch (PatientException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            fail("failed to creat a patient");
//        }
//
//
//        PatientQuery query = new PatientQuery();
//        query.setNameFirst("Jemi%");
//        query.setNameLast("Lope%");
//
//        List<PatientDescriptor> pds = patientBroker.findPatients(query);
//
//        assertTrue(pds.size() >= 1);
//
//        for (PatientDescriptor pd : pds) {
//            String firstname = pd.getNameFirst();
//            String lastname = pd.getNameLast();
//            PhoneNumber p = patient.getPhoneNumber(SharedEnums.PhoneType.HOME);
//            String number = p.getNumber();
//            assertEquals(firstname, "Jemis");
//            assertEquals(lastname, "Lopea");
//            assertEquals(number, "385-7777");
//        }
//
//    }
//
//    public void testPdqQuery() {
//        PatientBroker patientBroker = PatientBroker.getInstance();
//
//        PatientQuery query = new PatientQuery();
//        query.setNameFirst("Jemi%");
//        query.setNameLast("Lope%");
//        List<PatientDescriptor> pds = patientBroker.findPatients(query);
//        assertTrue(pds.size() >= 1);
//
//        for (PatientDescriptor pd : pds) {
//            String firstname = pd.getNameFirst();
//            String lastname = pd.getNameLast();
//            String middlename = pd.getNameMiddle();
//            assertEquals(firstname, "Jemis");
//            assertEquals(lastname, "Lopea");
//        }
//    }

    public void testSubmitDocument() {
         DocumentBroker docBroker = DocumentBroker.getInstance();
         Document doc = _createDocument("testdata/CDASample1.xml", "CDA Sample1" );

         List<XdrIntendedRecipient> recipients = new ArrayList<XdrIntendedRecipient>();
        recipients.add(new XdrIntendedRecipient("Name", "List A"));
        try {
            docBroker.sendXdrDocument(doc, "Submit a test doc", SharedEnums.XdsContentCode.Transfer_Summarization, recipients);
        } catch (DocumentException e) {
            e.printStackTrace();
            assertFalse("Failed to send a doc via XDR", true);
        }
        assertTrue("Successfully send a doc via XDR", true );
    }

//    public void testQueryDocument() {
//        DocumentBroker docBroker = DocumentBroker.getInstance();
//        PatientDescriptor pd = _createPatient();
//
//        DocumentQuery query = new DocumentQuery();
//        query.setPatientId(pd.getPatientId());
//        List<Document> docs = docBroker.findDocuments( query );
//
//        for (Document doc : docs) {
//            PatientID pid = doc.getPatientId();
//            boolean hasConents = doc.hasContents();
//            String uri = doc.getUri();
//            String title = doc.getTitle();
//            assertTrue( title.startsWith("CDA Sample"));
//        }
//    }
//
//    public void testReplaceDocument() {
//         DocumentBroker docBroker = DocumentBroker.getInstance();
//         Document doc2 = _createDocument("testdata/CDASample2.xml", "CDA Sample2");
//
//        try {
//            docBroker.submitDocument(doc2, "Submit a test doc", SharedEnums.XdsContentCode.REFERRAL_SUMMARY);
//        } catch (DocumentException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            assertFalse("Failed to submit a doc", true);
//        }
//        assertTrue("Successfully submitted a doc", true );
//
//        //Test document replacement
//        Document doc4 = _createDocument("testdata/CDASample4.xml", "CDA Sample4");
//        String doc2id = doc2.getUniqueId();
//        doc4.setReplacesId( doc2id );
//        try {
//            docBroker.submitDocument(doc4, "Test Submiting replacement docs", SharedEnums.XdsContentCode.REFERRAL_SUMMARY);
//        } catch (DocumentException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            assertFalse("Failed to submit a replacement doc", true);
//        }
//        assertTrue("succesfully submitted a replacement doc", true );
//
//        //get the history of doc4
//        DocumentQuery query = new DocumentQuery();
//        query.setDocumentId( doc4.getUniqueId() );
//        query.setDocumentRelationship(SharedEnums.DocumentRelationship.RPLC);
//        List<Document> historyDocs = docBroker.findDocuments( query );
//        for (Document doc : historyDocs){
//           //Somehow, the IBM server returns both the original doc and the replaced doc.
//           assertTrue( doc.getUniqueId().equals(doc2id) || doc.getUniqueId().equals(doc4.getUniqueId()) );
//        }
//    }
//
//    public void testSubmitMultipleDocuments() {
//        DocumentBroker docBroker = DocumentBroker.getInstance();
//        Document doc2 = _createDocument("testdata/CDASample2.xml", "CDA Sample2");
//        Document doc3 = _createDocument("testdata/CDASample3.xml", "CDA Sample3");
//        List<Document> docs = new ArrayList<Document>();
//        docs.add( doc2 );
//        docs.add( doc3 );
//       try {
//           docBroker.submitDocuments(docs, "Test Submiting two docs", SharedEnums.XdsContentCode.REFERRAL_SUMMARY);
//       } catch (DocumentException e) {
//           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//           assertFalse("Faile to submit two docs", true);
//       }
//       assertTrue("succesfully submitted two docs ", true );
//    }
//
//    public void testDocumentSet() {
//        DocumentBroker docBroker = DocumentBroker.getInstance();
//        PatientDescriptor pd = _createPatient();
//
//        DocumentQuery query = new DocumentQuery();
//        query.setPatientId(pd.getPatientId());
//        List<DocumentSet> docsets = docBroker.findDocumentSets( query );
//
//        for (DocumentSet docset : docsets) {
//             for( Document doc: docset.documents) {
//                PatientID pid = doc.getPatientId();
//                boolean hasConents = doc.hasContents();
//                String uri = doc.getUri();
//                String title = doc.getTitle();
//                assertTrue( title.startsWith("CDA Sample"));
//             }
//        }
//    }

    private PatientDescriptor _createPatient() {
         PatientDescriptor patient = new PatientDescriptor();
         patient.setNameFirst("Jemis");
         patient.setNameLast("Lopea");
         patient.setNameMiddle("N");
         patient.setNameSuffix("Sr");
         patient.setNameTitle("Dr.");
         patient.setAdministrativeSex(SharedEnums.SexType.MALE);
         patient.setBirthDateTime( new GregorianCalendar(1966, 2, 25).getTime() );
         patient.setMotherMaidenName("Wang"); //required by sending PIX
         PatientID pid = new PatientID("120002");
         pid.addId("EMR", "emrId111");
         patient.setPatientId( pid );

         patient.setHomeSystem("EMR");
         patient.setFacilityName("Misys");
         //Set MRN list
         //List<PatientMRN> lMrns = new ArrayList<PatientMRN>();
         //PatientMRN mrn = new PatientMRN()
         //lMrns.add( lMrns );
         //patient.setMrnList();
         //add phone list
         List<PhoneNumber> lphones = new ArrayList<PhoneNumber>();
         PhoneNumber phone = new PhoneNumber(SharedEnums.PhoneType.HOME, "716", "385-6666");
         lphones.add( phone );
         patient.setPhoneList( lphones );
         //Add patient address
         List<Address> lAddrs = new ArrayList<Address>();
         Address addr = new Address();
         addr.setAddLine1("11 Home Suite");
         addr.setAddCity("Buffalo");
         addr.setAddState("NY");
         addr.setAddZip("14236");
         lAddrs.add( addr );
         patient.setAddressList( lAddrs );

         return patient;
    }

    private Document _createDocument(String docfile, String docTitle) {
         Document doc = new Document();
         Provider provider = new Provider();
         provider.setProvNameFirst("Harold");
         provider.setProvNameLast("Chin");
         AuthorDescriptor author = new AuthorDescriptor();
         author.addAuthorInstitution("Prespbyterian Hosipital");
         author.setAuthorPerson(provider);
         author.addAuthorSpeciality("General Medicine");
         doc.addAuthorDescriptor(author);
         doc.setClassCode(SharedEnums.XdsClassCode.Transfer_Summarization);

         URL docUrl = IBMTest.class.getResource(docfile);
         doc.setContents( docUrl );
         doc.setCreationTime(new Date());
         doc.setFacilityCode("EMR");
         doc.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);
         doc.setLanguage("en-US");
         doc.setMimeType("text/xml");
         PatientDescriptor pd = _createPatient();
         doc.setPatientDescriptor( pd );
         doc.setPatientId( pd.getPatientId() );
         doc.setPracticeCode("General Medicine");
         doc.setServiceStart(new GregorianCalendar(2001, Calendar.JANUARY, 1).getTime() );
         doc.setServiceEnd( new Date() );
         doc.setSourceId("EMR-MISYSCONNECT");
         doc.setTitle( docTitle );
         doc.setTypeCode(SharedEnums.XdsTypeCode.Transfer_Summarization_Note);
         doc.setUniqueId( OID.getBase() + "^" + "docid" + id++);
         return doc;
    }

}

