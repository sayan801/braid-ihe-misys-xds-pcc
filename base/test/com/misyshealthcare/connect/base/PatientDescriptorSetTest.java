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

import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptorSet;

import junit.framework.TestCase;

public class PatientDescriptorSetTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.PatientDescriptorSet()'
	 */
	public void testPatientDescriptorSet() {
		PatientDescriptorSet ds = new PatientDescriptorSet();
		assertNull(ds.getPatientId());
		assertEquals(0, ds.size());
		assertTrue(ds.isEmpty());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.PatientDescriptorSet(PatientDescriptor)'
	 */
	public void testPatientDescriptorSetPatientDescriptor() {
		PatientID id = new PatientID("1234");
		PatientDescriptor patient = new PatientDescriptor(id);
		PatientDescriptorSet ds = new PatientDescriptorSet(patient);
		assertSame(id, ds.getPatientId());
		assertEquals(1, ds.size());
		assertFalse(ds.isEmpty());
		assertSame(patient, ds.iterator().next());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.getPatientId()'
	 */
	public void testGetPatientId() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.add(PatientDescriptor)'
	 */
	public void testAdd() {
		PatientID id = new PatientID("1234");
		PatientDescriptor p1 = new PatientDescriptor(id);
		PatientDescriptorSet ds = new PatientDescriptorSet(p1);
		assertSame(id, ds.getPatientId());
		PatientDescriptor p2 = new PatientDescriptor(new PatientID("1234"));
		assertTrue(ds.add(p2));
		assertEquals(2, ds.size());
		PatientDescriptor p3 = new PatientDescriptor(new PatientID("1235"));
		assertFalse(ds.add(p3));
		assertEquals(2, ds.size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.size()'
	 */
	public void testSize() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.isEmpty()'
	 */
	public void testIsEmpty() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptorSet.iterator()'
	 */
	public void testIterator() {

	}

}
