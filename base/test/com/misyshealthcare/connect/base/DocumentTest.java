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
package com.misyshealthcare.connect.base;

import java.util.Date;

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums.XdsFormatCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;

public class DocumentTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.Document()'
	 */
	public void testDocument() {
		Document d = new Document();
		assertNull(d.getPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.Document(PatientDescriptor)'
	 */
	public void testDocumentPatientDescriptor() {
		PatientID id = new PatientID("1234");
		PatientDescriptor p = new PatientDescriptor(id);
		Document d = new Document(p);
		assertSame(id, d.getPatientId());
		assertSame(p, d.getPatientDescriptor());
		d = new Document(null);
		assertNull(d.getPatientDescriptor());
		assertNull(d.getPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getPatientId()'
	 */
	public void testGetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setPatientId(PatientID)'
	 */
	public void testSetPatientId() {
		PatientID id = new PatientID("1234");
		Document d = new Document();
		d.setPatientId(id);
		assertSame(id, d.getPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getPatientDescriptor()'
	 */
	public void testGetPatientDescriptor() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setPatientDescriptor(PatientDescriptor)'
	 */
	public void testSetPatientDescriptor() {
		PatientID id = new PatientID("1234");
		PatientDescriptor p = new PatientDescriptor(id);
		Document d = new Document();
		d.setPatientDescriptor(p);
		assertSame(id, d.getPatientId());
		assertSame(p, d.getPatientDescriptor());
		d.setPatientDescriptor(null);
		assertNull(d.getPatientDescriptor());
		assertSame(id, d.getPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.hasContents()'
	 */
	public void testHasContents() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getContents()'
	 */
	public void testGetContents() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setContents(IDocumentContent)'
	 */
	public void testSetContents() {
		Document d = new Document();
		assertFalse(d.hasContents());
		assertNull(d.getContents());
		Integer mc = new Integer(1); // Any object will do
		d.setContents(mc);
		assertTrue(d.hasContents());
		assertSame(mc, d.getContents());
		d.setContents(null);
		assertFalse(d.hasContents());
		assertNull(d.getContents());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getCreationTime()'
	 */
	public void testGetCreationTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setCreationTime(Date)'
	 */
	public void testSetCreationTime() {
		Document d = new Document();
		assertNull(d.getCreationTime());
		Date date = new Date();
		d.setCreationTime(date);
		assertSame(date, d.getCreationTime());
		d.setCreationTime(null);
		assertNull(d.getCreationTime());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getMimeType()'
	 */
	public void testGetMimeType() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setMimeType(String)'
	 */
	public void testSetMimeType() {
		Document d = new Document();
		assertNull(d.getMimeType());
		d.setMimeType("mime");
		assertEquals("mime", d.getMimeType());
		d.setMimeType(null);
		assertNull(d.getMimeType());
	}


	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getAuthorDescriptors()'
	 */
	public void testGetAuthorDescriptors() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setAuthorDescriptors(AuthorDescriptor)'
	 */
	public void testSetAuthorDescriptors() {
		Document d = new Document();
		assertEquals(d.getAuthorDescriptors().size(), 0);
		Provider thing = new Provider();
		AuthorDescriptor author = new AuthorDescriptor();
        author.setAuthorPerson(thing);
        d.addAuthorDescriptor(author);
        assertSame(thing, d.getAuthorDescriptors().get(0).getAuthorPerson());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getFacilityCode()'
	 */
	public void testGetFacilityCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setFacilityCode(String)'
	 */
	public void testSetFacilityCode() {
		Document d = new Document();
		assertNull(d.getFacilityCode());
		d.setFacilityCode("mime");
		assertEquals("mime", d.getFacilityCode());
		d.setFacilityCode(null);
		assertNull(d.getFacilityCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getFormatCode()'
	 */
	public void testGetFormatCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setFormatCode(String)'
	 */
	public void testSetFormatCode() {
		Document d = new Document();
		assertNull(d.getFormatCode());
		d.setFormatCode(XdsFormatCode.IHE_PDF_10);
		assertEquals(XdsFormatCode.IHE_PDF_10, d.getFormatCode());
		d.setFormatCode(null);
		assertNull(d.getFormatCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getLanguage()'
	 */
	public void testGetLanguage() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setLanguage(String)'
	 */
	public void testSetLanguage() {
		Document d = new Document();
		assertEquals("en-us", d.getLanguage());
		d.setLanguage("mime");
		assertEquals("mime", d.getLanguage());
		d.setLanguage(null);
		assertNull(d.getLanguage());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getPracticeCode()'
	 */
	public void testGetPracticeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setPracticeCode(String)'
	 */
	public void testSetPracticeCode() {
		Document d = new Document();
		assertNull(d.getPracticeCode());
		d.setPracticeCode("mime");
		assertEquals("mime", d.getPracticeCode());
		d.setPracticeCode(null);
		assertNull(d.getPracticeCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getServiceStart()'
	 */
	public void testGetServiceStart() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setServiceStart(Date)'
	 */
	public void testSetServiceStart() {
		Document d = new Document();
		assertNull(d.getServiceStart());
		Date date = new Date();
		d.setServiceStart(date);
		assertSame(date, d.getServiceStart());
		d.setServiceStart(null);
		assertNull(d.getServiceStart());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getServiceStop()'
	 */
	public void testGetServiceEnd() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setServiceStop(Date)'
	 */
	public void testSetServiceEnd() {
		Document d = new Document();
		assertNull(d.getServiceEnd());
		Date date = new Date();
		d.setServiceEnd(date);
		assertSame(date, d.getServiceEnd());
		d.setServiceEnd(null);
		assertNull(d.getServiceEnd());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getSigningPerson()'
	 */
	public void testGetLegalAuthenticator() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setSigningPerson(Provider)'
	 */
	public void testSetLegalAuthenticator() {
		Document d = new Document();
		assertNull(d.getLegalAuthenticator());
		Provider thing = new Provider();
		d.setLegalAuthenticator(thing);
		assertSame(thing, d.getLegalAuthenticator());
		d.setLegalAuthenticator(null);
		assertNull(d.getLegalAuthenticator());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getTitle()'
	 */
	public void testGetTitle() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setTitle(String)'
	 */
	public void testSetTitle() {
		Document d = new Document();
		assertNull(d.getTitle());
		d.setTitle("mime");
		assertEquals("mime", d.getTitle());
		d.setTitle(null);
		assertNull(d.getTitle());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getTypeCode()'
	 */
	public void testGetTypeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setTypeCode(String)'
	 */
	public void testSetTypeCode() {
		Document d = new Document();
		assertNull(d.getTypeCode());
		d.setTypeCode(XdsTypeCode.Transfer_Summarization_Note);
		assertEquals(XdsTypeCode.Transfer_Summarization_Note, d.getTypeCode());
		d.setTypeCode(null);
		assertNull(d.getTypeCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getUniqueId()'
	 */
	public void testGetUniqueId() {
		Document d = new Document();
		assertNull(d.getUniqueId());
		d = new MockDocument();
		assertEquals("0.1.2.3", d.getUniqueId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setUniqueId(String)'
	 */
	public void testSetUniqueId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.getUri()'
	 */
	public void testGetUri() {
		Document d = new Document();
		assertNull(d.getUri());
		d = new MockDocument();
		assertEquals("MockURI/home", d.getUri());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Document.setUri(String)'
	 */
	public void testSetUri() {

	}
	
	public void testGetDescription() {
		Document d = new Document();
		assertEquals("", d.getDescription());

        AuthorDescriptor author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital A");
        d.addAuthorDescriptor(author);
		assertEquals("Hospital A", d.getDescription());
		d.setTitle("title");
		assertEquals("title", d.getDescription());
		d = new MockDocument();
		assertEquals("An IHE Document", d.getDescription());
        author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital A");
        d.addAuthorDescriptor(author);
		assertEquals("An IHE Document", d.getDescription());
		d.setTitle("title");
		assertEquals("An IHE Document", d.getDescription());
	}
	
	private class MockDocument extends Document {
		
		public MockDocument() {
			IheInterface ihe = iheInterface();
			ihe.setUri("MockURI/home");
			ihe.setDescription("An IHE Document");
			setUniqueId("0.1.2.3");
		}
	}

}
