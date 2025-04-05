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
import java.util.Collection;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

import javax.xml.soap.SOAPElement;

import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsRimException;
import com.misyshealthcare.connect.ihe.registry.XdsAuthor;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.StandardConnectionDescription;
import com.misyshealthcare.connect.util.Pair;

import junit.framework.TestCase;

/**
 * Unit tests for the XdsDocumentEntry class.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 10, 2005
 */
public class XdsDocumentEntryTest extends TestCase {
	
	private static IConnectionDescription connection = null;

	public XdsDocumentEntryTest() {
		if (connection == null) {
			StandardConnectionDescription conn = new StandardConnectionDescription();
			conn.setHostname("Fred");
			conn.loadConfiguration(XdsDocumentEntryTest.class.getResource("testdata/testCodes.xml").getPath());
	    connection = conn;
		}
	}
	
	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.XdsDocumentEntry()'
	 */
	public void testXdsDocumentEntry() {
		XdsDocumentEntry doc = new XdsDocumentEntry();
		assertNotNull(doc);
		assertFalse(doc.holdsValidMetadata());
		assertEquals("en-us", doc.getLanguageCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.XdsDocumentEntry(Element)'
	 */
	public void testXdsDocumentEntryElement() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getAuthorInstitution()'
	 */
	public void testGetAuthorInstitution() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setAuthorInstitution(String)'
	 */
	public void testSetAuthorInstitution() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getAuthorPerson()'
	 */
	public void testGetAuthorPerson() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setAuthorPerson(String)'
	 */
	public void testSetAuthorPerson() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getClassCode()'
	 */
	public void testGetClassCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setClassCode(String)'
	 */
	public void testSetClassCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getClassCodeDisplayName()'
	 */
	public void testGetClassCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setClassCodeDisplayName(String)'
	 */
	public void testSetClassCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getConfidentialityCode()'
	 */
	public void testGetConfidentialityCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setConfidentialityCode(String)'
	 */
	public void testSetConfidentialityCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getConfidentialityCodeDisplayName()'
	 */
	public void testGetConfidentialityCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setConfidentialityCodeDisplayName(String)'
	 */
	public void testSetConfidentialityCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getCreationTime()'
	 */
	public void testGetCreationTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setCreationTime(String)'
	 */
	public void testSetCreationTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getEntryUuid()'
	 */
	public void testGetEntryUuid() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setEntryUuid(String)'
	 */
	public void testSetEntryUuid() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getEventCodeDisplayNames()'
	 */
	public void testGetEventCodeDisplayNames() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setEventCodeDisplayNames(List<String>)'
	 */
	public void testSetEventCodeDisplayNames() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getEventCodes()'
	 */
	public void testGetEventCodes() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setEventCodes(List<String>)'
	 */
	public void testSetEventCodes() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getFormatCode()'
	 */
	public void testGetFormatCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setFormatCode(String)'
	 */
	public void testSetFormatCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getFormatCodeDisplayName()'
	 */
	public void testGetFormatCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setFormatCodeDisplayName(String)'
	 */
	public void testSetFormatCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getHealthcareFacilityTypeCode()'
	 */
	public void testGetHealthcareFacilityTypeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setHealthcareFacilityTypeCode(String)'
	 */
	public void testSetHealthcareFacilityTypeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getHealthcareFacilityTypeCodeDisplayName()'
	 */
	public void testGetHealthcareFacilityTypeCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setHealthcareFacilityTypeCodeDisplayName(String)'
	 */
	public void testSetHealthcareFacilityTypeCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getLanguageCode()'
	 */
	public void testGetLanguageCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setLanguageCode(String)'
	 */
	public void testSetLanguageCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getLegalAuthenticator()'
	 */
	public void testGetLegalAuthenticator() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setLegalAuthenticator(String)'
	 */
	public void testSetLegalAuthenticator() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getMimeType()'
	 */
	public void testGetMimeType() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setMimeType(String)'
	 */
	public void testSetMimeType() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getPatientId()'
	 */
	public void testGetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setPatientId(String)'
	 */
	public void testSetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getPracticeSettingCode()'
	 */
	public void testGetPracticeSettingCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setPracticeSettingCode(String)'
	 */
	public void testSetPracticeSettingCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getPracticeSettingCodeDisplayName()'
	 */
	public void testGetPracticeSettingCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setPracticeSettingCodeDisplayName(String)'
	 */
	public void testSetPracticeSettingCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getServiceStartTime()'
	 */
	public void testGetServiceStartTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setServiceStartTime(String)'
	 */
	public void testSetServiceStartTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getServiceStopTime()'
	 */
	public void testGetServiceStopTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setServiceStopTime(String)'
	 */
	public void testSetServiceStopTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getSourcePatientId()'
	 */
	public void testGetSourcePatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setSourcePatientId(String)'
	 */
	public void testSetSourcePatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getSourcePatientInfo()'
	 */
	public void testGetSourcePatientInfo() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setSourcePatientInfo(Collection<String>)'
	 */
	public void testSetSourcePatientInfo() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getTitle()'
	 */
	public void testGetTitle() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setTitle(String)'
	 */
	public void testSetTitle() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getTypeCode()'
	 */
	public void testGetTypeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setTypeCode(String)'
	 */
	public void testSetTypeCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getTypeCodeDisplayName()'
	 */
	public void testGetTypeCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setTypeCodeDisplayName(String)'
	 */
	public void testSetTypeCodeDisplayName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getUniqueId()'
	 */
	public void testGetUniqueId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setUniqueId(String)'
	 */
	public void testSetUniqueId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getUri()'
	 */
	public void testGetUri() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.setUri(String)'
	 */
	public void testSetUri() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.holdsValidMetadata()'
	 */
	public void testHoldsValidMetadata() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry.getAsXmlDom(String, IConnectionDescription)'
	 */
	public void testGetAsXmlDom() {
		// Create a new XdsDocumentEntry
		XdsDocumentEntry doc = new XdsDocumentEntry();
		doc.setTitle("Test Doc");

        XdsAuthor author = new XdsAuthor();
        author.setAuthorPerson("Fred Boggs");
        author.addAuthorInstitution("Misys");
        author.addAuthorRole("Priest");
        author.addAuthorSpeciality("Exorcism");
        doc.addAuthor(author);
		doc.setCreationTime("2005");
		doc.setLanguageCode("en-ca");
		doc.setLegalAuthenticator("The Mayor");
		doc.setServiceStartTime("10am");
		doc.setServiceStopTime("11pm");
		doc.setSourcePatientId("12.345.6");
		Vector<String> sourceInfo = new Vector<String>();
		sourceInfo.add("Point 1");
		sourceInfo.add("Point 2");
		sourceInfo.add("Point 3");
		sourceInfo.add("Point 4");
		sourceInfo.add("Point 5");
		doc.setSourcePatientInfo(sourceInfo);
		assertFalse(doc.holdsValidMetadata());
		doc.setClassCode("Education");
		doc.setFormatCode("CDA/IHE 1.0");
		doc.setPracticeSettingCode("Dialysis");
		doc.setHealthcareFacilityTypeCode("Home");
		doc.setTypeCode("11488-4");
        List<Pair> confidentialityCodes = new ArrayList<Pair>();
        confidentialityCodes.add(new Pair("N", "Normal"));
        doc.setConfidentialityCodes( confidentialityCodes );
		assertFalse(doc.holdsValidMetadata());
		doc.setPatientId("abcdefg");
		doc.setUniqueId("1233456");
		doc.setMimeType("html");
		assertTrue(doc.holdsValidMetadata());
		//xml.addClassification("eventCode", eventCodes, XDS_DOCUMENT_ENTRY_EVENT_CODE_LIST);
		// Make sure everything is in there
		assertEquals("Test Doc", doc.getTitle());
		assertEquals("Misys", doc.getAuthors().get(0).getAuthorInstitutions().get(0));
		assertEquals("Fred Boggs", doc.getAuthors().get(0).getAuthorPerson());
		assertEquals("Priest", doc.getAuthors().get(0).getAuthorRoles().get(0));
		assertEquals("Exorcism", doc.getAuthors().get(0).getAuthorSpecialities().get(0));
		assertEquals("2005", doc.getCreationTime());
		assertEquals("en-ca", doc.getLanguageCode());
		assertEquals("The Mayor", doc.getLegalAuthenticator());
		assertEquals("10am", doc.getServiceStartTime());
		assertEquals("11pm", doc.getServiceStopTime());
		assertEquals("12.345.6", doc.getSourcePatientId());
		assertSame(sourceInfo, doc.getSourcePatientInfo());
		assertEquals("Education", doc.getClassCode());
		assertEquals("CDA/IHE 1.0", doc.getFormatCode());
		assertEquals("Dialysis", doc.getPracticeSettingCode());
		assertEquals("Home", doc.getHealthcareFacilityTypeCode());
		assertEquals("11488-4", doc.getTypeCode());
		assertEquals("N", doc.getConfidentialityCodes().get(0)._first);
		assertEquals("Normal", doc.getConfidentialityCodes().get(0)._second);
		assertEquals("abcdefg", doc.getPatientId());
		assertEquals("1233456", doc.getUniqueId());
		assertEquals("html", doc.getMimeType());
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
		XdsDocumentEntry doc2 = null;
		try {
            String XDS_RIM_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1";
            doc2 = new XdsDocumentEntry(xml, XDS_RIM_NAMESPACE);
		} catch (XdsRimException e) {
			fail(e.toString());
		}
		assertNotNull(doc2);
		// Make sure the two documents are the same
		assertEquals(doc.getTitle(), doc2.getTitle());
        assertEquals(doc.getAuthors().get(0).getAuthorInstitutions().get(0), doc2.getAuthors().get(0).getAuthorInstitutions().get(0));
        assertEquals(doc.getAuthors().get(0).getAuthorPerson(), doc2.getAuthors().get(0).getAuthorPerson());
        assertEquals(doc.getAuthors().get(0).getAuthorRoles().get(0), doc2.getAuthors().get(0).getAuthorRoles().get(0));
        assertEquals(doc.getAuthors().get(0).getAuthorSpecialities().get(0), doc2.getAuthors().get(0).getAuthorSpecialities().get(0));
		assertEquals(doc.getCreationTime(), doc2.getCreationTime());
		assertEquals(doc.getLanguageCode(), doc2.getLanguageCode());
		assertEquals(doc.getLegalAuthenticator(), doc2.getLegalAuthenticator());
		assertEquals(doc.getServiceStartTime(), doc2.getServiceStartTime());
		assertEquals(doc.getServiceStopTime(), doc2.getServiceStopTime());
		assertEquals(doc.getSourcePatientId(), doc2.getSourcePatientId());
		Collection<String> sourceInfo2 = doc2.getSourcePatientInfo();
		assertNotNull(sourceInfo2);
		assertEquals(sourceInfo.size(), sourceInfo2.size());
		assertTrue(sourceInfo2.containsAll(sourceInfo));
		assertEquals(doc.getClassCode(), doc2.getClassCode());
		assertEquals(doc.getFormatCode(), doc2.getFormatCode());
		assertEquals(doc.getPracticeSettingCode(), doc2.getPracticeSettingCode());
		assertEquals(doc.getHealthcareFacilityTypeCode(), doc2.getHealthcareFacilityTypeCode());
		assertEquals(doc.getTypeCode(), doc2.getTypeCode());
		assertEquals(doc.getConfidentialityCodes().get(0)._first, doc2.getConfidentialityCodes().get(0)._first);
		assertEquals(doc.getConfidentialityCodes().get(0)._second, doc2.getConfidentialityCodes().get(0)._second);
		assertEquals("Education", doc2.getClassCodeDisplayName());
		assertEquals("CDA/IHE 1.0", doc2.getFormatCodeDisplayName());
		assertEquals("Dialysis", doc2.getPracticeSettingCodeDisplayName());
		assertEquals("Home", doc2.getHealthcareFacilityTypeCodeDisplayName());
		assertEquals("Consultation Note", doc2.getTypeCodeDisplayName());
		assertEquals("Normal", doc2.getConfidentialityCodes().get(0)._second);
		assertEquals(doc.getPatientId(), doc2.getPatientId());
		assertEquals(doc.getUniqueId(), doc2.getUniqueId());
		assertEquals(doc.getMimeType(), doc2.getMimeType());
		assertTrue(doc2.holdsValidMetadata());
	}

}
