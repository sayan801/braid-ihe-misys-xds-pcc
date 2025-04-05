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

import com.misyshealthcare.connect.base.DateQuery;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.base.PatientID;

import junit.framework.TestCase;

public class DocumentQueryTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.getMisysPatientId()'
	 */
	public void testGetPatientId() {
		PatientID id = new PatientID();
		DocumentQuery dq = new DocumentQuery();
		assertNull(dq.getPatientId());
		dq.setPatientId(id);
		assertSame(id, dq.getPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.setMisysPatientId(PatientID)'
	 */
	public void testSetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.getEndTime()'
	 */
	public void testGetEndTime() {
		DateQuery d = new DateQuery();
		DocumentQuery dq = new DocumentQuery();
		assertNull(dq.getEndTime());
		dq.setEndTime(d);
		assertSame(d, dq.getEndTime());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.setEndTime(Date)'
	 */
	public void testSetEndTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.getStartTime()'
	 */
	public void testGetStartTime() {
		DateQuery d = new DateQuery();
		DocumentQuery dq = new DocumentQuery();
		assertNull(dq.getStartTime());
		dq.setStartTime(d);
		assertSame(d, dq.getStartTime());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.setStartTime(Date)'
	 */
	public void testSetStartTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.getHomeSystem()'
	 */
	public void testGetHomeSystem() {
		DocumentQuery dq = new DocumentQuery();
		assertNull(dq.getHomeSystem());
		dq.setHomeSystem("a");
		assertEquals("a", dq.getHomeSystem());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.setHomeSystem(String)'
	 */
	public void testSetHomeSystem() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.getMaxDocumentCount()'
	 */
	public void testGetMaxDocumentCount() {
		DocumentQuery dq = new DocumentQuery();
		assertEquals(-1, dq.getMaxDocumentCount());
		dq.setMaxDocumentCount(4);
		assertEquals(4, dq.getMaxDocumentCount());
	}
	
	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentQuery.setMaxDocumentCount(int)'
	 */
	public void testSetMaxDocumentCount() {

	}

}
