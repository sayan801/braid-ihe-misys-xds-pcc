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

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentSet;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;

public class DocumentSetTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.DocumentSet()'
	 */
	public void testDocumentSet() {
		DocumentSet ds = new DocumentSet();
		assertNull(ds.getFacility());
		assertEquals(0, ds.size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.DocumentSet(Document)'
	 */
	public void testDocumentSetDocument() {
		PatientDescriptor patient = new PatientDescriptor(new PatientID("1234"));
		Document doc = new Document(patient);
		AuthorDescriptor author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital A");
        doc.addAuthorDescriptor(author);
		DocumentSet ds = new DocumentSet(doc);
		assertEquals("Hospital A", ds.getFacility());
		assertEquals(1, ds.size());
		assertSame(doc, ds.iterator().next());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.getFacility()'
	 */
	public void testGetFacility() {
		DocumentSet ds = new DocumentSet();
		assertNull(ds.getFacility());
		ds.setFacility("Hospital A");
		assertEquals("Hospital A", ds.getFacility());
		assertEquals(0, ds.size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.setFacility(String)'
	 */
	public void testSetFacility() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.add(Document)'
	 */
	public void testAdd() {
		PatientDescriptor patient = new PatientDescriptor(new PatientID("1234"));
		Document doc = new Document(patient);
		AuthorDescriptor author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital A");
        doc.addAuthorDescriptor(author);
		DocumentSet ds = new DocumentSet();
		assertTrue(ds.isEmpty());
		assertTrue(ds.add(doc));
		assertEquals("Hospital A", ds.getFacility());
		assertEquals(1, ds.size());
		assertSame(doc, ds.iterator().next());
		Document doc2 = new Document(patient);
        author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital A");
        doc2.addAuthorDescriptor(author);
 		assertTrue(ds.add(doc2));
		assertEquals("Hospital A", ds.getFacility());
		assertEquals(2, ds.size());
		Document doc3 = new Document(patient);
        author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital A");
        doc3.addAuthorDescriptor(author);
		assertTrue(ds.add(doc3));
		assertEquals("Hospital A", ds.getFacility());
		assertEquals(3, ds.size());
		Document doc4 = new Document(patient);
        author = new AuthorDescriptor();
        author.addAuthorInstitution("Hospital B");
        doc4.addAuthorDescriptor(author);
		assertEquals("Hospital A", ds.getFacility());
		assertEquals(3, ds.size());
		assertFalse(ds.isEmpty());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.size()'
	 */
	public void testSize() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.isEmpty()'
	 */
	public void testIsEmpty() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentSet.iterator()'
	 */
	public void testIterator() {

	}

}
