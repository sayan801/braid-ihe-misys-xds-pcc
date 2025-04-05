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

import junit.framework.TestCase;

public class DateQueryTest extends TestCase {

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.DateQuery()'
	 */
	public void testDateQuery() {
		DateQuery q = new DateQuery();
		assertEquals("?-?-?", q.toString());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.DateQuery(int, int, int)'
	 */
	public void testDateQueryIntIntInt() {
		DateQuery q = new DateQuery(2004, 12, 6);
		assertEquals("2004-12-06", q.toString());
		q = new DateQuery(04, 12, 6);
		assertEquals("2004-12-06", q.toString());
		q = new DateQuery(04, 8, 16);
		assertEquals("2004-08-16", q.toString());
		q = new DateQuery(96, 8, 16);
		assertEquals("1996-08-16", q.toString());
		q = new DateQuery(96, 8, 56);
		assertEquals("1996-08-?", q.toString());
		q = new DateQuery(96, 0, 31);
		assertEquals("1996-?-31", q.toString());
		q = new DateQuery(9, 1, 0);
		assertEquals("1909-01-?", q.toString());
		q = new DateQuery(6, 12, 1);
		assertEquals("2006-12-01", q.toString());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.getDay()'
	 */
	public void testGetDay() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.setDay(String)'
	 */
	public void testSetDayString() {
		DateQuery q = new DateQuery();
		q.setDay("23");
		assertEquals(23, q.getDay());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.setDay(int)'
	 */
	public void testSetDayInt() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.getMonth()'
	 */
	public void testGetMonth() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.setMonth(String)'
	 */
	public void testSetMonthString() {
		DateQuery q = new DateQuery();
		q.setMonth("6");
		assertEquals(6, q.getMonth());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.setMonth(int)'
	 */
	public void testSetMonthInt() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.getYear()'
	 */
	public void testGetYear() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.setYear(String)'
	 */
	public void testSetYearString() {
		DateQuery q = new DateQuery();
		q.setYear("3");
		assertEquals(2003, q.getYear());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.setYear(int)'
	 */
	public void testSetYearInt() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DateQuery.toString()'
	 */
	public void testToString() {

	}

}
