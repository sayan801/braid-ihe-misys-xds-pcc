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
import com.misyshealthcare.connect.base.PatientQuery;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientMRN;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;

import junit.framework.TestCase;

public class PatientQueryTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getMisysPatientId()'
	 */
	public void testGetPatientId() {
		PatientQuery pq = new PatientQuery();
		pq.setMisysPatientId("123");
		assertEquals("123", pq.getMisysPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setMisysPatientId(PatientID)'
	 */
	public void testSetPatientId() {
		PatientQuery pq = new PatientQuery();
		pq.setMisysPatientId("345");
		assertEquals("345", pq.getMisysPatientId());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getNameFirst()'
	 */
	public void testGetNameFirst() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameFirst(ts);
		assertEquals(ts, pq.getNameFirst());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setNameFirst(String)'
	 */
	public void testSetNameFirst() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameFirst(ts);
		assertEquals(ts, pq.getNameFirst());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getNameLast()'
	 */
	public void testGetNameLast() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameLast(ts);
		assertEquals(ts, pq.getNameLast());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setNameLast(String)'
	 */
	public void testSetNameLast() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameLast(ts);
		assertEquals(ts, pq.getNameLast());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getNameMiddle()'
	 */
	public void testGetNameMiddle() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameMiddle(ts);
		assertEquals(ts, pq.getNameMiddle());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setNameMiddle(String)'
	 */
	public void testSetNameMiddle() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getNameSuffix()'
	 */
	public void testGetNameSuffix() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameSuffix(ts);
		assertEquals(ts, pq.getNameSuffix());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setNameSuffix(String)'
	 */
	public void testSetNameSuffix() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getNameTitle()'
	 */
	public void testGetNameTitle() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setNameTitle(ts);
		assertEquals(ts, pq.getNameTitle());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setNameTitle(String)'
	 */
	public void testSetNameTitle() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getSsn()'
	 */
	public void testGetSsn() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setSsn(ts);
		assertEquals(ts, pq.getSsn());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setSsn(String)'
	 */
	public void testSetSsn() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getDriverLicense()'
	 */
	public void testGetDriverLicense() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setDriverLicense(ts);
		assertEquals(ts, pq.getDriverLicense());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setDriverLicense(String)'
	 */
	public void testSetDriverLicense() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getAdministrativeSex()'
	 */
	public void testGetAdministrativeSex() {
		PatientQuery pq = new PatientQuery();
		pq.setAdministrativeSex(SexType.UNKNOWN);
		assertEquals(SexType.UNKNOWN, pq.getAdministrativeSex());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setAdministrativeSex(String)'
	 */
	public void testSetAdministrativeSex() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getMotherMaidenName()'
	 */
	public void testGetMotherMaidenName() {
		PatientQuery pq = new PatientQuery();
		String ts = "Nameish";
		pq.setMotherMaidenName(ts);
		assertEquals(ts, pq.getMotherMaidenName());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setMotherMaidenName(String)'
	 */
	public void testSetMotherMaidenName() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getBirthDateTime()'
	 */
	public void testGetBirthDateTime() {
		PatientQuery pq = new PatientQuery();
		DateQuery s = new DateQuery();
		pq.setBirthDateTime(s);
		assertSame(s, pq.getBirthDateTime());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setBirthDateTime(Date)'
	 */
	public void testSetBirthDateTime() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getAddresses()'
	 */
	public void testGetAddress() {
		PatientQuery pq = new PatientQuery();
		Address ts = new Address();
		pq.setAddress(ts);
		assertSame(ts, pq.getAddress());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setAddresses(Address)'
	 */
	public void testSetAddress() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getMrn()'
	 */
	public void testGetMrn() {
		PatientQuery pq = new PatientQuery();
		PatientMRN ts = new PatientMRN("test", "1234");
		pq.setMrn(ts);
		assertSame(ts, pq.getMrn());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setMrn(PatientMRN)'
	 */
	public void testSetMrn() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getPhone()'
	 */
	public void testGetPhone() {
		PatientQuery pq = new PatientQuery();
		PhoneNumber ts = new PhoneNumber();
		pq.setPhone(ts);
		assertSame(ts, pq.getPhone());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setPhone(PhoneNumber)'
	 */
	public void testSetPhone() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.getProvider()'
	 */
	public void testGetProvider() {
		PatientQuery pq = new PatientQuery();
		Provider ts = new Provider();
		pq.setProvider(ts);
		assertEquals(ts, pq.getProvider());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientQuery.setProvider(Provider)'
	 */
	public void testSetProvider() {

	}

}
