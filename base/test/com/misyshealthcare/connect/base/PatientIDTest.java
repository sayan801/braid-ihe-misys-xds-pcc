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

import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;

import junit.framework.TestCase;

public class PatientIDTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.PatientID()'
	 */
	public void testPatientID() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.PatientID(String)'
	 */
	public void testPatientIDString() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.PatientID(String, String, String)'
	 */
	public void testPatientIDStringStringString() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.hasMisysUniqueId()'
	 */
	public void testHasMisysUniqueId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.getMisysUniqueId()'
	 */
	public void testGetMisysUniqueId() {
		PatientID pid = new PatientID("1234", "a", "2");
		assertTrue(pid.hasLocalUniqueId());
		assertEquals("1234", pid.getLocalUniqueId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.addId(String, String)'
	 */
	public void testPutId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.getId(String)'
	 */
	public void testGetId() {
		PatientID pid = new PatientID("1234", "a", "2");
		assertTrue(pid.hasId("a"));
		assertTrue(pid.getIdList("a").contains("2"));
		pid.addId("b", "3");
		assertTrue(pid.hasId("a"));
		assertTrue(pid.getIdList("a").contains("2"));
		assertTrue(pid.hasId("b"));
        assertTrue(pid.getIdList("b").contains("3"));
		pid.addId("c", "4");
		assertTrue(pid.hasId("a"));
		assertTrue(pid.getIdList("a").contains("2"));
		assertTrue(pid.hasId("b"));
		assertTrue(pid.getIdList("b").contains("3"));
		assertTrue(pid.hasId("c"));
		assertTrue(pid.getIdList("c").contains("4"));
		pid.addId("a", "4");
		assertTrue(pid.hasId("a"));
        assertTrue(pid.getIdList("a").contains("2"));
        assertTrue(pid.getIdList("a").contains("4"));
		assertTrue(pid.hasId("b"));
		assertTrue(pid.getIdList("b").contains("3"));
		assertTrue(pid.hasId("c"));
		assertTrue(pid.getIdList("c").contains("4"));
		pid.addId("b", "4");
		assertTrue(pid.hasId("a"));
        assertTrue(pid.getIdList("a").contains("2"));
        assertTrue(pid.getIdList("a").contains("4"));
		assertTrue(pid.hasId("b"));
        assertTrue(pid.getIdList("b").contains("3"));
        assertTrue(pid.getIdList("b").contains("4"));
		assertTrue(pid.hasId("c"));
        assertTrue(pid.getIdList("c").contains("4"));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.hasId(String)'
	 */
	public void testHasId() {
		PatientID pid = new PatientID("1234", "a", "2");
		assertTrue(pid.hasId("a"));
		pid.addId("a", null);
		assertFalse(pid.hasId("a"));	
		assertEquals(0, pid.getIdList("a").size());
		assertEquals(0, pid.getIdList("b").size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.equals(Object)'
	 */
	public void testEqualsObject() {
		// Empty patient ID
		PatientID id1 = new PatientID();
		assertTrue(id1.equals(id1));
		// Patient ID with a Misys ID
		PatientID id2 = new PatientID("1234");
		assertTrue(id2.equals(id2));
		assertFalse(id1.equals(id2));
		assertFalse(id2.equals(id1));
		PatientID id2a = new PatientID("1234");
		assertTrue(id2.equals(id2a));
		PatientID id2b = new PatientID("1235");
		assertFalse(id2.equals(id2b));
		assertFalse(id2b.equals(id2a));
		assertTrue(id2b.equals(id2b));
		// Patients with other IDs
		PatientID id3 = new PatientID("1235", "a", "1");
		assertTrue(id3.equals(id2b));
		PatientID id3a = new PatientID("1235", "b", "2");
		assertTrue(id3a.equals(id2b));
		assertTrue(id3a.equals(id3));
		PatientID id3b = new PatientID("1235", "a", "2");
		assertTrue(id3b.equals(id2b));
		assertTrue(id3b.equals(id3));
		assertTrue(id3b.equals(id3a));
		assertFalse(id3b.equals(id2));
		// Now, with some merging
		PatientID id4 = null;
		try {
			id4 = id3.merge(id3a);
		} catch (PatientException e) {
			fail(e.toString());
		}
		assertTrue(id4.equals(id3));
		assertTrue(id4.equals(id3a));
		assertTrue(id4.equals(id3b));
		assertFalse(id4.equals(id2));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientID.merge(PatientID)'
	 */
	public void testMerge() {
		PatientID id1 = new PatientID("1235", "a", "1");
		PatientID id2 = new PatientID("1235", "b", "2");
		PatientID id3 = new PatientID("1235", "a", "2");
		PatientID id4 = null;
		PatientID id5 = null;
		try { id4 = id1.merge(id2); } 
		catch (PatientException e) {  fail(e.toString()); }
		//try { id5 = id1.merge(id3);  }
		//catch (PatientException e) {fail(e.toString());}
		try { id5 = id2.merge(id3); }
		catch (Exception e) { fail(e.toString()); }
		assertTrue(id4.equals(id5));
		assertTrue(id1.equals(id4));
		assertTrue(id2.equals(id4));
		assertTrue(id3.equals(id4));
		assertTrue(id3.equals(id5));
		assertTrue(id2.equals(id5));
		assertTrue(id3.equals(id4));
		try { id5 = id2.merge(new PatientID()); } 
		catch (Exception e) { fail(e.toString()); }
		try { id5 = id2.merge(new PatientID("1234")); fail("This merge should fail!");  } 
		catch (Exception e) { }
	}

}
