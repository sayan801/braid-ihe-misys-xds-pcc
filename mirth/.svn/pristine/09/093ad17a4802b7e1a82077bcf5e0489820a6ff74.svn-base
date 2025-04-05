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

import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.Formatter;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;

import junit.framework.TestCase;

/**
 * Unit test for base.Formatter
 * @author Jim Firby
 * @version 2.0 - Oct 27, 2005
 */
public class FormatterTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Formatter.getNameString(PatientDescriptor)'
	 */
	public void testGetNameStringPatientDescriptor() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Formatter.getNameString(String, String, String, String, String)'
	 */
	public void testGetNameStringStringStringStringStringString() {
		assertEquals(
				"Dr. Bob J. McBozo III", 
				Formatter.getNameString("Dr.", "Bob", "J.", "McBozo", "III"));
		assertEquals(
				"Dr. Bob McBozo III",
				Formatter.getNameString("Dr.", "Bob", "", "McBozo", "III"));
		assertEquals(
				"Dr. Bob McBozo",
				Formatter.getNameString("Dr.", "Bob", "", "McBozo", ""));
		assertEquals(
				"Dr. Bob",
				Formatter.getNameString("Dr.", "Bob", "", "", ""));
		assertEquals(
				"Dr.",
				Formatter.getNameString("Dr.", "", "", "", ""));
		assertEquals(
				"Dr. McBozo",
				Formatter.getNameString("Dr.", "", "", "McBozo", ""));
		assertEquals(
				"Dr. J. McBozo",
				Formatter.getNameString("Dr.", "", "J.", "McBozo", ""));
		assertEquals(
				"McBozo",
				Formatter.getNameString("", "", "", "McBozo", ""));
		assertEquals(
				"",
				Formatter.getNameString("", "", "", "", ""));
		assertEquals(
				"",
				Formatter.getNameString(null, null, null, null, null));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Formatter.getAlphabeticalNameString(PatientDescriptor)'
	 */
	public void testGetAlphabeticalNameStringPatientDescriptor() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Formatter.getAlphabeticalNameString(String, String, String, String, String)'
	 */
	public void testGetAlphabeticalNameStringStringStringStringStringString() {
		assertEquals(
				"McBozo III, Dr. Bob J.", 
				Formatter.getAlphabeticalNameString("Dr.", "Bob", "J.", "McBozo", "III"));
		assertEquals(
				"McBozo III, Dr. Bob",
				Formatter.getAlphabeticalNameString("Dr.", "Bob", "", "McBozo", "III"));
		assertEquals(
				"McBozo, Dr. Bob",
				Formatter.getAlphabeticalNameString("Dr.", "Bob", "", "McBozo", ""));
		assertEquals(
				"Bob, Dr.",
				Formatter.getAlphabeticalNameString("Dr.", "Bob", "", "", ""));
		assertEquals(
				"",
				Formatter.getAlphabeticalNameString("Dr.", "", "", "", ""));
		assertEquals(
				"McBozo, Dr.",
				Formatter.getAlphabeticalNameString("Dr.", "", "", "McBozo", ""));
		assertEquals(
				"McBozo, Dr. J.",
				Formatter.getAlphabeticalNameString("Dr.", "", "J.", "McBozo", ""));
		assertEquals(
				"McBozo",
				Formatter.getAlphabeticalNameString("", "", "", "McBozo", ""));
		assertEquals(
				"",
				Formatter.getAlphabeticalNameString("Dr.", "", "", "", "II"));
		assertEquals(
				"",
				Formatter.getAlphabeticalNameString("", "", "", "", ""));
		assertEquals(
				"",
				Formatter.getAlphabeticalNameString(null, null, null, null, null));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.Formatter.getAddressString(Address)'
	 */
	public void testGetAddressString() {
		Address add = new Address();
		add.setAddType(AddressType.HOME);
		assertEquals("", Formatter.getAddressString(add));
		add.setAddLine1("PO Box 123");
		assertEquals("PO Box 123", Formatter.getAddressString(add));
		add.setAddLine2("456 Alfredo Way, #4F");
		assertEquals("PO Box 123, 456 Alfredo Way, #4F", Formatter.getAddressString(add));
//		add.setLine3("Module 8");
//		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Module 8", pd.getAddressString("Home"));
		add.setAddCity("Chicago");
		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Chicago", Formatter.getAddressString(add));
		add.setAddState("IL");
		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Chicago, IL", Formatter.getAddressString(add));
		add.setAddZip("60614");
		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Chicago, IL 60614", Formatter.getAddressString(add));
		add.setAddCountry("USA");
		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Chicago, IL 60614, USA", Formatter.getAddressString(add));
//		add.setLine3("");
//		assertEquals("PO Box 123, 456 Alfredo Way, #4F, Chicago, IL 60614, USA", pd.getAddressString("Home"));
		add.setAddLine1("");
		assertEquals("456 Alfredo Way, #4F, Chicago, IL 60614, USA", Formatter.getAddressString(add));
		add.setAddState("");
		assertEquals("456 Alfredo Way, #4F, Chicago 60614, USA", Formatter.getAddressString(add));
		add.setAddZip("");
		assertEquals("456 Alfredo Way, #4F, Chicago, USA", Formatter.getAddressString(add));
		add.setAddLine2("");
		assertEquals("Chicago, USA", Formatter.getAddressString(add));
		add.setAddCountry("");
		assertEquals("Chicago", Formatter.getAddressString(add));
		add.setAddCity("");
		assertEquals("", Formatter.getAddressString(add));
	}
	
	public void testGetPhoneString() {
		PhoneNumber pn = new PhoneNumber(PhoneType.HOME, "555", "555-1212");
		assertEquals("(555) 555-1212", Formatter.getPhoneString(pn));
		pn.setCountryCode("1");
		assertEquals("1 (555) 555-1212", Formatter.getPhoneString(pn));
		pn.setExtension("876");
		assertEquals("1 (555) 555-1212 x876", Formatter.getPhoneString(pn));
		pn.setAreaCode(null);
		assertEquals("1 555-1212 x876", Formatter.getPhoneString(pn));
		pn.setCountryCode(null);
		assertEquals("555-1212 x876", Formatter.getPhoneString(pn));
		pn.setNumber(null);
		assertEquals("x876", Formatter.getPhoneString(pn));
		pn.setExtension("");
		assertEquals("", Formatter.getPhoneString(pn));
		pn = new PhoneNumber();
		assertEquals("", Formatter.getPhoneString(pn));
	}

}
