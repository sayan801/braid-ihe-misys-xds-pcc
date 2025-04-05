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

import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;

import junit.framework.TestCase;

public class PhoneNumberTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.PhoneNumber()'
	 */
	public void testPhoneNumber() {
		new PhoneNumber();
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.PhoneNumber(String)'
	 */
	public void testPhoneNumberString() {
		PhoneNumber pn = new PhoneNumber(PhoneType.HOME);
		assertEquals(PhoneType.HOME, pn.getType());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.PhoneNumber(String, String, String)'
	 */
	public void testPhoneNumberStringStringString() {
		PhoneNumber pn = new PhoneNumber(PhoneType.HOME, "818", "788-9876");
		assertEquals(PhoneType.HOME, pn.getType());
		assertEquals("818", pn.getAreaCode());
		assertEquals("788-9876", pn.getNumber());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.getAreaCode()'
	 */
	public void testGetAreaCode() {
		PhoneNumber pn = new PhoneNumber();
		pn.setAreaCode("12");
		assertEquals("12", pn.getAreaCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.setAreaCode(String)'
	 */
	public void testSetAreaCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.getCountryCode()'
	 */
	public void testGetCountryCode() {
		PhoneNumber pn = new PhoneNumber();
		pn.setCountryCode("12");
		assertEquals("12", pn.getCountryCode());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.setCountryCode(String)'
	 */
	public void testSetCountryCode() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.getExtension()'
	 */
	public void testGetExtension() {
		PhoneNumber pn = new PhoneNumber();
		pn.setExtension("12");
		assertEquals("12", pn.getExtension());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.setExtension(String)'
	 */
	public void testSetExtension() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.getNote()'
	 */
	public void testGetNote() {
		PhoneNumber pn = new PhoneNumber();
		pn.setNote("A note");
		assertEquals("A note", pn.getNote());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.setNote(String)'
	 */
	public void testSetNote() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.getNumber()'
	 */
	public void testGetNumber() {
		PhoneNumber pn = new PhoneNumber();
		pn.setNumber("12");
		assertEquals("12", pn.getNumber());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.setNumber(String)'
	 */
	public void testSetNumber() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.getType()'
	 */
	public void testGetType() {
		PhoneNumber pn = new PhoneNumber();
		pn.setType(PhoneType.FAX);
		assertEquals(PhoneType.FAX, pn.getType());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PhoneNumber.setType(String)'
	 */
	public void testSetType() {

	}

}
