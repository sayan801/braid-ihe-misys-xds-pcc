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

import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.log4j.Logger;

/**
 * This class represents a date query.  A structure is used so that
 * the month and day can be left unspecified.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 31, 2005
 */
public class DateQuery {
	
	/* Logger for problems and debugging messages */
  private static Logger log = Logger.getLogger(DateQuery.class);

  private int day = -1;
	private int month = -1;
	private int year = -1;
	
	public DateQuery() {}
	
	public DateQuery(Calendar date) {
		TimeZone tz = date.getTimeZone();
		date.setTimeZone(new SimpleTimeZone(0, "GMT"));
		setDay(date.get(Calendar.DAY_OF_MONTH));
		setMonth(date.get(Calendar.MONTH) + 1);
		setYear(date.get(Calendar.YEAR));	
		date.setTimeZone(tz);
	}
	
	/** Month is from 1 to 12
	 * 
	 * @param year
	 * @param month Number of month Jan = 1, Dec = 12
	 * @param day
	 */
	public DateQuery(int year, int month, int day) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}
	
	/**
	 * @return Returns the day.
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day The day to set.
	 */
	public void setDay(String day) {
		int theDay = 0;
		try {
			theDay = Integer.parseInt(day);
			setDay(theDay);
		} catch (NumberFormatException e) {
			// Log incorrect day values
			log.error("Invalid day string set in QueryDate '" + day + "'", e);
			this.day = -1;
		}
	}
	
	/**
	 * @param day The day to set.
	 */
	public void setDay(int day) {
		// This day check is not really strict enough
		if ((day < 1) || (day > 31)) {
			this.day = -1;
		} else {
			this.day = day;
		}
	}
	
	/**
	 * @return Returns the month (1 - 12).
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(String month) {
		try {
			int theMonth = Integer.parseInt(month);
			setMonth(theMonth);
		} catch (NumberFormatException e) {
			// Log incorrect month values
			log.error("Invalid month string set in QueryDate '" + month + "'", e);
			this.month = -1;
		}
	}
	
	/**
	 * @param month The month to set.
	 */
	public void setMonth(int month) {
		if ((month < 1) || (month > 12)) {
			this.month = -1;
		} else {
			this.month = month;
		}
	}
	
	/**
	 * @return Returns the year.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		try {
			int theYear = Integer.parseInt(year);
			setYear(theYear);
		} catch (NumberFormatException e) {
			// Log incorrect year values
			log.error("Invalid year string set in QueryDate '" + year + "'", e);
			this.year = -1;
		}
	}
	
	/**
	 * @param year The year to set.
	 */
	public void setYear(int year) {
		if (year < 100) {
			// A two digit year, guess at a prefix
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			int thisYear = now.get(Calendar.YEAR);
			int prefix = thisYear / 100;
			if (year > (thisYear - (prefix * 100))) {
				this.year = year + ((prefix - 1) * 100);
			} else {
				this.year = year + (prefix * 100);
			}
		} else if ((year < 1000) || (year > 9999)) {
			// Someday, this will come back to bite us
			this.year = -1;
		} else {
			// Okay
			this.year = year;
		}
	}

	/**
	 * Display this DateQuery as YYYY-MM-DD
	 */
	public String toString() {
		//return valStr(month) + "-"
		return valStr(year) + "-" + valStr(month) + "-" + valStr(day);
	}
	
	private String valStr(int val) {
		if (val < 0) {
			return "?";
		} else {
			String sRet = Integer.toString(val);
			if (val < 10){
				sRet = "0" + sRet;
			}
			return sRet;
		}
	}
	
}
