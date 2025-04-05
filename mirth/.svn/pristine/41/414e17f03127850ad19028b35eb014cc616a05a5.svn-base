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
import java.util.List;
import java.util.Vector;

import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.clinicaldata.Visit;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientMRN;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;

import junit.framework.TestCase;

/**
 * JUnit tests for base.PatientDescriptor
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 24, 2005
 */
public class PatientDescriptorTest extends TestCase {

	private PatientDescriptor pd = null;
	
	public PatientDescriptorTest(String arg0) {
		super(arg0);
		pd = new PatientDescriptor();
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getPatientId()'
	 */
	public void testGetPatientId() {
		PatientID pid = new PatientID("1234");
		pd.setPatientId(pid);
		PatientID pid1 = pd.getPatientId();
		assertSame(pid, pid1);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setPatientId(PatientId)'
	 */
	public void testSetPatientId() {
		PatientID pid = new PatientID("1234");
		pd.setPatientId(pid);
		PatientID pid1 = pd.getPatientId();
		assertSame(pid, pid1);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getFullName()'
	 */
	public void testGetNameString() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("Dr. Bob J. McBozo III", pd.getNameString());
		pd = new PatientDescriptor();
		assertEquals("", pd.getNameString());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getAlphabeticalName()'
	 */
	public void testGetNameFullAlphabetical() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("McBozo III, Dr. Bob J.", pd.getAlphabeticalNameString());
		pd = new PatientDescriptor();
		assertEquals("", pd.getAlphabeticalNameString());
	}
	
	public void testGetAgeInDays() {
		pd.setBirthDateTime(null);
		assertEquals(0, pd.getAgeInDays());
		pd.setBirthDateTime(new Date());
		assertEquals(0, pd.getAgeInDays());
	}


	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getFullAddress()'
	 */
	public void testGetAddressString() {
		Address add = new Address();
		add.setAddType(AddressType.HOME);
		pd.addAddress(add);
		assertSame(add, pd.getAddress(AddressType.HOME));
		assertEquals("", pd.getAddressString(AddressType.HOME));
		add.setAddLine1("PO Box 123");
		add.setAddLine2("456 Alfredo Way, #4F");
		add.setAddCity("Chicago");
		add.setAddState("IL");
		add.setAddZip("60614");
		add.setAddCountry("USA");
		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Chicago, IL 60614, USA", pd.getAddressString(AddressType.HOME));
		pd.removeAddress(AddressType.HOME);
		assertEquals("", pd.getAddressString(AddressType.HOME));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getMisysHomeSystem()'
	 */
	public void testGetHomeSystem() {
		pd.setHomeSystem("Home234");
		assertEquals("Home234", pd.getHomeSystem());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setMisysHomeSystem(String)'
	 */
	public void testSetHomeSystem() {
		pd.setHomeSystem("Home234");
		assertEquals("Home234", pd.getHomeSystem());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getProvider()'
	 */
//	public void testGetProvider() {
//		Provider p = new Provider();
//		pd.setProvider(p);
//		assertSame(p, pd.getProvider());
//	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setProvider(Provider)'
	 */
//	public void testSetProvider() {
//		Provider p = new Provider();
//		pd.setProvider(p);
//		assertSame(p, pd.getProvider());
//	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getNameFirst()'
	 */
	public void testGetNameFirst() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("Bob", pd.getNameFirst());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getNameLast()'
	 */
	public void testGetNameLast() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("McBozo", pd.getNameLast());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getNameMiddle()'
	 */
	public void testGetNameMiddle() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("J.", pd.getNameMiddle());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getNamePrefix()'
	 */
	public void testGetNameTitle() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("Dr. ", pd.getNameTitle());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getNameSuffix()'
	 */
	public void testGetNameSuffix() {
		pd.setNameTitle("Dr. ");
		pd.setNameFirst("Bob");
		pd.setNameMiddle("J.");
		pd.setNameLast("McBozo");
		pd.setNameSuffix("III");
		assertEquals("III", pd.getNameSuffix());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getAddresses()'
	 */
	public void testGetAddress() {
		Address p = new Address();
		p.setAddType(AddressType.HOME);
		pd.addAddress(p);
		assertSame(p, pd.getAddress(AddressType.HOME));
		Address p1 = new Address();
		p1.setAddType(AddressType.WORK);
		pd.addAddress(p1);
		assertSame(p1, pd.getAddress(AddressType.WORK));
		assertSame(p, pd.getAddress(AddressType.HOME));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setAddresses(Address)'
	 */
	public void testAddAddress() {
		Address p = new Address();
		p.setAddType(AddressType.HOME);
		pd.addAddress(p);
		assertSame(p, pd.getAddress(AddressType.HOME));
		Address p1 = new Address();
		p1.setAddType(AddressType.WORK);
		pd.addAddress(p1);
		assertSame(p1, pd.getAddress(AddressType.WORK));
		assertSame(p, pd.getAddress(AddressType.HOME));
		Address p2 = new Address();
		p2.setAddType(AddressType.HOME);
		pd.addAddress(p2);
		assertSame(p2, pd.getAddress(AddressType.HOME));
		assertNotSame(p, pd.getAddress(AddressType.HOME));
		assertSame(p1, pd.getAddress(AddressType.WORK));
	}
	
	public void testRemoveAddress() {
		Address p = new Address();
		p.setAddType(AddressType.HOME);
		pd.addAddress(p);
		assertSame(p, pd.getAddress(AddressType.HOME));
		Address p1 = new Address();
		p1.setAddType(AddressType.WORK);
		pd.addAddress(p1);
		assertSame(p1, pd.getAddress(AddressType.WORK));
		assertSame(p, pd.getAddress(AddressType.HOME));
		pd.removeAddress(AddressType.HOME);
		assertNull(pd.getAddress(AddressType.HOME));
		assertSame(p1, pd.getAddress(AddressType.WORK));
		pd.removeAddress(AddressType.WORK);
		assertNull(pd.getAddress(AddressType.HOME));
		assertNull(pd.getAddress(AddressType.WORK));
		pd.removeAddress(AddressType.HOME);
		assertNull(pd.getAddress(AddressType.HOME));
		assertNull(pd.getAddress(AddressType.WORK));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getDateOfBirth()'
	 */
	public void testGetBirthDateTime() {
		Date ts = new Date();
		pd.setBirthDateTime(ts);
		assertSame(ts, pd.getBirthDateTime());
		pd.setBirthDateTime(null);
		assertSame(null, pd.getBirthDateTime());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setDateOfBirth(Timestamp)'
	 */
	public void testSetBirthDateTime() {
		Date ts = new Date();
		pd.setBirthDateTime(ts);
		assertSame(ts, pd.getBirthDateTime());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getDriversLicenseNumber()'
	 */
	public void testGetDriverLicense() {
		String ts = "123456";
		pd.setDriverLicense(ts);
		assertEquals(ts, pd.getDriverLicense());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setDriversLicenseNumber(String)'
	 */
	public void testSetDriverLicense() {
		String ts = "123456";
		pd.setDriverLicense(ts);
		assertEquals(ts, pd.getDriverLicense());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getGender()'
	 */
	public void testGetAdministrativeSex() {
		pd.setAdministrativeSex(SexType.MALE);
		assertEquals(SexType.MALE, pd.getAdministrativeSex());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setGender(String)'
	 */
	public void testSetAdministrativeSex() {
		pd.setAdministrativeSex(SexType.FEMALE);
		assertEquals(SexType.FEMALE, pd.getAdministrativeSex());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getHomePhone()'
	 */
	public void testGetPhoneList() {
		Vector<PhoneNumber> pl = new Vector<PhoneNumber>();
		pd.setPhoneList(pl);
		assertSame(pl, pd.getPhoneList());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setHomePhone(String)'
	 */
	public void testSetPhoneList() {
	}
	
	public void testAddPhoneNumber() {
		PhoneNumber pn = new PhoneNumber(PhoneType.HOME);
		pd.addPhoneNumber(pn);
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
		PhoneNumber pn1 = new PhoneNumber(PhoneType.FAX);
		pd.addPhoneNumber(pn1);
		assertSame(pn1, pd.getPhoneNumber(PhoneType.FAX));
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
	}

	public void testGetPhoneNumber() {
	}
	
	public void testRemovePhoneNumber() {
		pd.setPhoneList(new Vector<PhoneNumber>());
		PhoneNumber pn = new PhoneNumber(PhoneType.HOME);
		pd.addPhoneNumber(pn);
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
		PhoneNumber pn1 = new PhoneNumber(PhoneType.FAX);
		pd.addPhoneNumber(pn1);
		assertSame(pn1, pd.getPhoneNumber(PhoneType.FAX));
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
		PhoneNumber pn2 = new PhoneNumber(PhoneType.WORK);
		pd.addPhoneNumber(pn2);
		assertSame(pn1, pd.getPhoneNumber(PhoneType.FAX));
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
		assertSame(pn2, pd.getPhoneNumber(PhoneType.WORK));
		pd.removePhoneNumber(PhoneType.FAX);
		assertNull(pd.getPhoneNumber(PhoneType.FAX));
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
		assertSame(pn2, pd.getPhoneNumber(PhoneType.WORK));
		pd.removePhoneNumber(PhoneType.WORK);
		assertNull(pd.getPhoneNumber(PhoneType.FAX));
		assertSame(pn, pd.getPhoneNumber(PhoneType.HOME));
		assertNull(pd.getPhoneNumber(PhoneType.WORK));
		pd.removePhoneNumber(PhoneType.HOME);
		assertNull(pd.getPhoneNumber(PhoneType.FAX));
		assertNull(pd.getPhoneNumber(PhoneType.HOME));
		assertNull(pd.getPhoneNumber(PhoneType.WORK));
		assertEquals(0, pd.getPhoneList().size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getMothersMaidenName()'
	 */
	public void testGetMotherMaidenName() {
		String ts = "Marian";
		pd.setMotherMaidenName(ts);
		assertEquals(ts, pd.getMotherMaidenName());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setMothersMaidenName(String)'
	 */
	public void testSetMotherMaidenName() {
		String ts = "Esmeralda";
		pd.setMotherMaidenName(ts);
		assertEquals(ts, pd.getMotherMaidenName());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.getSsn()'
	 */
	public void testGetSsn() {
		String ts = "456-76-0987";
		pd.setSsn(ts);
		assertEquals(ts, pd.getSsn());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientDescriptor.setSsn(String)'
	 */
	public void testSetSsn() {
		String ts = "456-76-0987";
		pd.setSsn(ts);
		assertEquals(ts, pd.getSsn());
	}

	
	public void testGetVisitList() {
		List<Visit> vl = new Vector<Visit>();
		pd.setVisitList(vl);
		assertSame(vl, pd.getVisitList());
	}
	
	public void testSetVisitList() {
		List<Visit> vl = new Vector<Visit>();
		pd.setVisitList(vl);
		assertSame(vl, pd.getVisitList());
	}

	public void testGetMrnList() {
		List<PatientMRN> vl = new Vector<PatientMRN>();
		pd.setMrnList(vl);
		assertSame(vl, pd.getMrnList());
	}
	
	public void testSetMrnList() {
		List<PatientMRN> vl = new Vector<PatientMRN>();
		pd.setMrnList(vl);
		assertSame(vl, pd.getMrnList());
	}
	
	public void testIsConfidential() {
		pd.setConfidential(true);
		assertTrue(pd.isConfidential());
		pd.setConfidential(false);
		assertFalse(pd.isConfidential());		
	}
	
	public void testSetConfidential() {
		pd.setConfidential(true);
		assertTrue(pd.isConfidential());
		pd.setConfidential(false);
		assertFalse(pd.isConfidential());		
	}

}
