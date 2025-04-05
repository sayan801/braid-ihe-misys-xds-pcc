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
package com.misyshealthcare.connect.base.demographicdata;

import com.misyshealthcare.connect.util.StringUtil;

/**
 * This class contains a set of formatting tools that can be
 * used by UI and debugging code to format structures in this
 * package in a human readable way.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 27, 2005
 */
public class Formatter {
	
	/**
	 * Format the name of this patient as a single string.
	 * 
	 * @param descriptor The patient
	 * @return The patient's full name
	 */
	public static String getNameString(PatientDescriptor descriptor) {
		return 
		  getNameString(
				StringUtil.trimString(descriptor.getNameTitle()),
				StringUtil.trimString(descriptor.getNameFirst()),
				StringUtil.trimString(descriptor.getNameMiddle()),
				StringUtil.trimString(descriptor.getNameLast()),
				StringUtil.trimString(descriptor.getNameSuffix())
		    );
	}

	/**
	 * Format all the pieces of a name into a single string.
	 * 
	 * @param prefix The name prefix (ie. Dr., Mrs.)
	 * @param firstName The first name
	 * @param middleName The middle names or initials
	 * @param lastName The last name
	 * @param suffix The name suffix (ie. III, Jr.)
	 * @return The full name resulting from combining all the pieces
	 */
	public static String getNameString(String prefix, String firstName, String middleName, String lastName, String suffix) {
		StringBuffer sb = new StringBuffer();
		if (!StringUtil.isNullString(prefix)) {sb.append(prefix);}
		if (!StringUtil.isNullString(firstName)) {
			if (sb.length() > 0) sb.append(' ');
			sb.append(firstName); 
		}
		if (!StringUtil.isNullString(middleName)) {
			if (sb.length() > 0) sb.append(' ');
			sb.append(middleName); 
		}
		if (!StringUtil.isNullString(lastName)) {
			if (sb.length() > 0) sb.append(' ');
			sb.append(lastName); 
		}
		if (!StringUtil.isNullString(suffix)) {
			if (sb.length() > 0) sb.append(' ');
			sb.append(suffix); 
		}
		return sb.toString();
	}
	
	/**
	 * Format the name of this patient as a single string in
	 * a form suitable for putting into and alphabetical list
	 *  (ie. last name first).
	 * 
	 * @param descriptor The patient
	 * @return The patient's full name, alphabetized
	 */
	public static String getAlphabeticalNameString(PatientDescriptor descriptor) {
		return 
		  getAlphabeticalNameString(
		  		StringUtil.trimString(descriptor.getNameTitle()),
		  		StringUtil.trimString(descriptor.getNameFirst()),
		  		StringUtil.trimString(descriptor.getNameMiddle()),
		  		StringUtil.trimString(descriptor.getNameLast()),
		  		StringUtil.trimString(descriptor.getNameSuffix()));
	}

	/**
	 * Get the full name of this person by appending together
	 * all of the name pieces in a form suitable for putting in
	 * an alpahbetical list (ie. last name first).
	 * 
	 * @return The full name of this person, alphabetized
	 */
	public static String getAlphabeticalNameString(String prefix, String firstName, String middleName, String lastName, String suffix) {
		boolean atRoot = true;
		StringBuffer sb = new StringBuffer();
		if (!StringUtil.isNullString(lastName)) {
			// Have a last name
			sb.append(lastName);
			if (!StringUtil.isNullString(suffix)) {
				sb.append(' ');
				sb.append(suffix); 
			}
			if (!StringUtil.isNullString(prefix)) {
				if (atRoot) sb.append(", ");
				sb.append(prefix); 
				atRoot = false;
			}
			if (!StringUtil.isNullString(firstName)) {
				if (atRoot) sb.append(',');
				sb.append(' ');
				sb.append(firstName); 
				atRoot = false;
			}
			if (!StringUtil.isNullString(middleName)) {
				if (atRoot) sb.append(',');
				sb.append(' ');
				sb.append(middleName);
				if (middleName.trim().length() == 1){
					sb.append('.');
				}
				atRoot = false;
			}
			
		} else if (!StringUtil.isNullString(firstName)) {
			// No last name, go with first
			sb.append(firstName);
			if (!StringUtil.isNullString(middleName)) {
				sb.append(' ');
				sb.append(middleName); 
			}
			if (!StringUtil.isNullString(prefix)) {
				sb.append(", ");
				sb.append(prefix); 
			}
		} else if (!StringUtil.isNullString(middleName)) {
			// No last name or first name, go with middle
			sb.append(middleName);
			if (!StringUtil.isNullString(prefix)) {
				sb.append(", ");
				sb.append(prefix); 
			}
		}
		return sb.toString();		
	}
	
	/**
	 * Get a string combining all of the address information into
	 * a human readable form.
	 * 
	 * @param address The address to format
	 * @return The whole address as one string
	 */
	public static String getAddressString(Address address) {
		if (address == null) return "";
		String segment = null;
		StringBuffer sb = new StringBuffer();
		if (!StringUtil.isNullString(address.getAddLine1())) {
			sb.append(address.getAddLine1().trim());
		}
		if (!StringUtil.isNullString(address.getAddLine2())) {
			segment = address.getAddLine2().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(segment); 
			}
		}
		if (!StringUtil.isNullString(address.getAddCity())) {
			segment = address.getAddCity().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(segment); 
			}
		}
		if (!StringUtil.isNullString(address.getAddState())) {
			segment = address.getAddState().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(segment); 
			}
		}
		if (!StringUtil.isNullString(address.getAddZip())) {
			segment = address.getAddZip().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(" ");
				sb.append(segment); 
			}
		}
		if (!StringUtil.isNullString(address.getAddCountry())) {
			segment = address.getAddCountry().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(", ");
				sb.append(segment); 
			}
		}
		return sb.toString();		
	}
	
	/**
	 * Get a string representing all of the parts of the
	 * phone number.  Assuming the number conforms to the North
	 * American standard.
	 * 
	 * @param phone The phone number to format
	 * @return The phone number as a string
	 */
	public static String getPhoneString(PhoneNumber phone) {
		if (phone == null) return "";
		String segment = null;
		StringBuffer sb = new StringBuffer();
		if (!StringUtil.isNullString(phone.getCountryCode())) {
			sb.append(phone.getCountryCode().trim());
		}
		if (!StringUtil.isNullString(phone.getAreaCode())) {
			segment = phone.getAreaCode().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(" ");
				sb.append('(');
				sb.append(segment); 
				sb.append(')');
			}
		}
		if (!StringUtil.isNullString(phone.getNumber())) {
			segment = phone.getNumber().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(" ");
				sb.append(segment); 
			}
		}
		if (!StringUtil.isNullString(phone.getExtension())) {
			segment = phone.getExtension().trim();
			if (!segment.equals("")) {
				if (sb.length() > 0) sb.append(" ");
				sb.append("x");
				sb.append(segment); 
			}
		}
		return sb.toString();
	}

}
