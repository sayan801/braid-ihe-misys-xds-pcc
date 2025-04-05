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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.ihe.hl7.HL7v231;
import junit.framework.TestCase;

public class HL7v231Test extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.hl7.HL7v231.formatDateTime(Date)'
	 */
	public void testFormatDateTime() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2005, 10, 25, 18, 27, 45);
		String tz = (new SimpleDateFormat("Z")).format(calendar.getTime());
		assertEquals("20051125182745"+tz, HL7v231.formatDateTime(calendar.getTime()));
	}
	
	public void testparseDateTime() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2005, 11, 25, 12, 27, 45);
		String tz = (new SimpleDateFormat("Z")).format(calendar.getTime());
		String test = "20051125122745" + tz;
		assertEquals(test, HL7v231.formatDateTime(HL7v231.parseDateTime(test)));
		test = "20051125122745";
		assertEquals(test+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test)));
		test = "200511251227";
		assertEquals(test+"00"+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test)));
		test = "2005112512";
		assertEquals(test+"0000"+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test)));
		test = "20051125";
		assertEquals(test+"000000"+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test)));
		test = "200511251227";
		assertEquals(test+"00"+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test + tz)));
		test = "2005112512";
		assertEquals(test+"0000"+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test + tz)));
		test = "20051125";
		assertEquals(test+"000000"+tz, HL7v231.formatDateTime(HL7v231.parseDateTime(test + tz)));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.hl7.HL7v231.formatDate(Date)'
	 */
	public void testFormatDate() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2005, 10, 25, 18, 27, 45);
		assertEquals("20051125", HL7v231.formatDate(calendar.getTime()));
	}

	public void testUnformatDate() {
		String test = "20051125";
		assertEquals(test, HL7v231.formatDate(HL7v231.parseDate(test)));
		test = "200511";
		assertEquals("20051101", HL7v231.formatDate(HL7v231.parseDate(test)));
		test = "2005";
		assertEquals("20050101", HL7v231.formatDate(HL7v231.parseDate(test)));
		test = "2005112546";
		assertEquals("20051125", HL7v231.formatDate(HL7v231.parseDate(test)));
	}
	
	public void testbuildDateFromInts() { 
		int tz;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2005, 11, 22, 04, 00, 00);
		String tzs = (new SimpleDateFormat("Z")).format(calendar.getTime());
		try{
		tz = Integer.parseInt(tzs);
		assertEquals("20050101", HL7v231.formatDate(HL7v231.buildDateFromInts(2005, 0, 0, 0, 0, 0, tz, false)));
		assertEquals("20051101", HL7v231.formatDate(HL7v231.buildDateFromInts(2005, 11, 0, 0, 0, 0, tz, false)));
		assertEquals("20051122", HL7v231.formatDate(HL7v231.buildDateFromInts(2005, 11, 22, 0, 0, 0, tz, false)));
		assertEquals("20051122040000" + tzs, HL7v231.formatDateTime(HL7v231.buildDateFromInts(2005, 11, 22, 4, 0, 0, tz, false)));
		assertEquals("20051122040500" + tzs, HL7v231.formatDateTime(HL7v231.buildDateFromInts(2005, 11, 22, 4, 5, 0, tz, false)));
		assertEquals("20051122040506" + tzs, HL7v231.formatDateTime(HL7v231.buildDateFromInts(2005, 11, 22, 4, 5, 6, tz, false)));
		assertEquals("20051122020506" + tzs, HL7v231.formatDateTime(HL7v231.buildDateFromInts(2005, 11, 22, 4, 5, 6, tz+200, true)));
		}catch (NumberFormatException e) {
			assertFalse(false);
		}
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.hl7.HL7v231.formatPhoneNumber(String, String, String, String, String)'
	 */
	public void testFormatPhoneNumber() {
		assertEquals("555-5555", HL7v231.formatPhoneNumber(null, null, "5555555", null, null));
		assertEquals("(123)555-5555", HL7v231.formatPhoneNumber(null, "123", "5555555", null, null));
		assertEquals("1 555-5555", HL7v231.formatPhoneNumber("1", null, "5555555", null, null));
		assertEquals("1(123)555-5555", HL7v231.formatPhoneNumber("1", "123", "5555555", null, null));
		assertEquals("555-5555X678", HL7v231.formatPhoneNumber(null, null, "5555555", "678", null));
		assertEquals("555-5555X678CCall Ext 23", HL7v231.formatPhoneNumber(null, null, "5555555", "678", "Call Ext 23"));
		assertEquals("555-5555CCall Ext 23", HL7v231.formatPhoneNumber(null, null, "5555555", null, "Call Ext 23"));
		assertEquals("1(123)555-5555X678CCall Ext 23", HL7v231.formatPhoneNumber("1", "123", "5555555", "678", "Call Ext 23"));
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.hl7.HL7v231.parsePhoneNumber(PhoneNumber, String)'
	 */
	public void testparsePhoneNumber() {
		PhoneNumber phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber(null, null, "5555555", null, null));
		assertNull(phone.getCountryCode());
		assertNull(phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertNull(phone.getExtension());
		assertNull(phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber(null, "123", "5555555", null, null));
		assertNull(phone.getCountryCode());
		assertEquals("123", phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertNull(phone.getExtension());
		assertNull(phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber("1", null, "5555555", null, null));
		assertEquals("1", phone.getCountryCode());
		assertNull(phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertNull(phone.getExtension());
		assertNull(phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber("1", "123", "5555555", null, null));
		assertEquals("1", phone.getCountryCode());
		assertEquals("123", phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertNull(phone.getExtension());
		assertNull(phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber(null, null, "5555555", "678", null));
		assertNull(phone.getCountryCode());
		assertNull(phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertEquals("678", phone.getExtension());
		assertNull(phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber(null, null, "5555555", "678", "Call Ext 23"));
		assertNull(phone.getCountryCode());
		assertNull(phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertEquals("678", phone.getExtension());
		assertEquals("Call Ext 23", phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber(null, null, "5555555", null, "Call Ext 23"));
		assertNull(phone.getCountryCode());
		assertNull(phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertNull(phone.getExtension());
		assertEquals("Call Ext 23", phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, HL7v231.formatPhoneNumber("1", "123", "5555555", "678", "Call Ext 23"));
		assertEquals("1", phone.getCountryCode());
		assertEquals("123", phone.getAreaCode());
		assertEquals("555-5555", phone.getNumber());
		assertEquals("678", phone.getExtension());
		assertEquals("Call Ext 23", phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, "011 678-9999 X3 CHello");
		assertEquals("011", phone.getCountryCode());
		assertNull(phone.getAreaCode());
		assertEquals("678-9999", phone.getNumber());
		assertEquals("3", phone.getExtension());
		assertEquals("Hello", phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, "(45) 678.9999 X3 CHello");
		assertNull(phone.getCountryCode());
		assertEquals("45", phone.getAreaCode());
		assertEquals("678.9999", phone.getNumber());
		assertEquals("3", phone.getExtension());
		assertEquals("Hello", phone.getNote());
		phone = new PhoneNumber();
		HL7v231.parsePhoneNumber(phone, "( 45) 4678.9999 X3 CHello");
		assertNull(phone.getCountryCode());
		assertEquals("45", phone.getAreaCode());
		assertEquals("4678.9999", phone.getNumber());
		assertEquals("3", phone.getExtension());
		assertEquals("Hello", phone.getNote());
	}

}
