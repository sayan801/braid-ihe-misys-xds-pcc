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

import java.io.File;

import javax.xml.soap.SOAPElement;

import junit.framework.TestCase;

import com.misyshealthcare.connect.ihe.registry.XdsAuthor;
import com.misyshealthcare.connect.ihe.registry.XdsRimException;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.StandardConnectionDescription;

/**
 * Unit tests for the XdsSubmissionSet class.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 12, 2005
 */
public class XdsSubmissionSetTest extends TestCase {

	private static IConnectionDescription connection = null;

	public XdsSubmissionSetTest() {
		if (connection == null) {
			StandardConnectionDescription conn = new StandardConnectionDescription();
	    conn.setHostname("Fred");
			conn.loadConfiguration(XdsSubmissionSetTest.class.getResource("testdata/testCodes.xml").getPath());
	    connection = conn;
		}
	}
	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.XdsSubmissionSet()'
	 */
	public void testXdsSubmissionSet() {
		XdsSubmissionSet doc = new XdsSubmissionSet();
		assertNotNull(doc);
		assertFalse(doc.holdsValidMetadata());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.XdsSubmissionSet(Element)'
	 */
	public void testXdsSubmissionSetElement() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getAuthorInstitution()'
	 */
	public void testGetAuthorInstitution() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setAuthorInstitution(String)'
	 */
	public void testSetAuthorInstitution() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getAuthorPersons()'
	 */
	public void testGetAuthorPersons() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setAuthorPersons(List<String>)'
	 */
	public void testSetAuthorPersons() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getComments()'
	 */
	public void testGetComments() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setComments(String)'
	 */
	public void testSetComments() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getContentTypeCode()'
	 */
	public void testGetContentTypeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setContentTypeCode(String)'
	 */
	public void testSetContentTypeCode() {
		XdsSubmissionSet doc = new XdsSubmissionSet();
		doc.setSubmissionTime("2005");
		doc.setPatientId("abcdefg");
		doc.setUniqueId("1233456");
		doc.setSourceId("Misys");
		doc.setContentTypeCode("Fred");
		assertEquals("Fred", doc.getContentTypeCode());
		// Should be an invalid code, so it shouldn't turn itself into XML
		try { 
			doc.getAsXmlDom("Doc1", connection);
			fail("Invalid code value, should have thrown an error.");
		} catch (Exception e1) {
		}
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getContentTypeCodeDisplayName()'
	 */
	public void testGetContentTypeCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setContentTypeCodeDisplayName(String)'
	 */
	public void testSetContentTypeCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getPatientId()'
	 */
	public void testGetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setPatientId(String)'
	 */
	public void testSetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getSourceId()'
	 */
	public void testGetSourceId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setSourceId(String)'
	 */
	public void testSetSourceId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getSubmissionTime()'
	 */
	public void testGetSubmissionTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setSubmissionTime(String)'
	 */
	public void testSetSubmissionTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getUniqueId()'
	 */
	public void testGetUniqueId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.setUniqueId(String)'
	 */
	public void testSetUniqueId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet.getAsXmlDom(String, IConnectionDescription)'
	 */
	public void testGetAsXmlDom() {
		// Create a new XdsSubmissionSet
		XdsSubmissionSet doc = new XdsSubmissionSet();
		doc.setComments("Test Doc");
        XdsAuthor author = new XdsAuthor();
        author.addAuthorInstitution("Misys");
        author.setAuthorPerson("Fred Boggs");
        author.addAuthorRole("Priest");
        author.addAuthorSpeciality("Exorcism");
        doc.addAuthor(author);
		doc.setSubmissionTime("2005");
		assertFalse(doc.holdsValidMetadata());
		doc.setContentTypeCode("Admission evaluation");
		assertFalse(doc.holdsValidMetadata());
		doc.setPatientId("abcdefg");
		doc.setUniqueId("1233456");
		doc.setSourceId("Misys");
		assertTrue(doc.holdsValidMetadata());
		//xml.addClassification("eventCode", eventCodes, XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST);
		// Make sure everything is in there
		assertEquals("Test Doc", doc.getComments());
		assertEquals("Misys", doc.getAuthors().get(0).getAuthorInstitutions().get(0));
		assertEquals("Fred Boggs", doc.getAuthors().get(0).getAuthorPerson());
        assertEquals("Priest", doc.getAuthors().get(0).getAuthorRoles().get(0));
        assertEquals("Exorcism", doc.getAuthors().get(0).getAuthorSpecialities().get(0));
		assertEquals("2005", doc.getSubmissionTime());
		assertEquals("Admission evaluation", doc.getContentTypeCode());
		assertEquals("abcdefg", doc.getPatientId());
		assertEquals("1233456", doc.getUniqueId());
		assertEquals("Misys", doc.getSourceId());
		// Pull it out as an XML DOM
		SOAPElement xml = null;
		try {
			xml = doc.getAsXmlDom("Doc1", connection);
		} catch (Exception e1) {
			fail(e1.toString());
		}
		assertNotNull(xml);
//		DOMSource source = new DOMSource(xml);
//		StreamResult result = new StreamResult(System.out);
//		Transformer transformer = null;
//		try {
//			transformer = TransformerFactory.newInstance().newTransformer();
//			transformer.transform(source, result);
//		} catch (Exception e) {
//			fail(e.toString());
//		}
		// Now, create another XdsDocumentEntry by parsing out of the XML DOM
		XdsSubmissionSet doc2 = null;
		try {
			doc2 = new XdsSubmissionSet(xml);
		} catch (XdsRimException e) {
			fail(e.toString());
		}
		assertNotNull(doc2);
		// Make sure the two documents are the same
		assertEquals(doc.getComments(), doc2.getComments());
		assertEquals(doc.getAuthors().get(0).getAuthorInstitutions().get(0), doc2.getAuthors().get(0).getAuthorInstitutions().get(0));
		assertEquals(doc.getAuthors().get(0).getAuthorPerson(), doc2.getAuthors().get(0).getAuthorPerson());
        assertEquals(doc.getAuthors().get(0).getAuthorRoles().get(0), doc2.getAuthors().get(0).getAuthorRoles().get(0));
        assertEquals(doc.getAuthors().get(0).getAuthorSpecialities().get(0), doc2.getAuthors().get(0).getAuthorSpecialities().get(0));
		assertEquals(doc.getSubmissionTime(), doc2.getSubmissionTime());
		assertEquals(doc.getContentTypeCode(), doc2.getContentTypeCode());
		assertEquals("Admission evaluation", doc2.getContentTypeCodeDisplayName());
		assertEquals(doc.getPatientId(), doc2.getPatientId());
		assertEquals(doc.getUniqueId(), doc2.getUniqueId());
		assertEquals(doc.getSourceId(), doc2.getSourceId());
		assertTrue(doc2.holdsValidMetadata());
	}

}
