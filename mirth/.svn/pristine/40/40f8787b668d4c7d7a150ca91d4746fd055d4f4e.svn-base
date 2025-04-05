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
package com.misyshealthcare.connect.doc.ccd;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Nov 27, 2007
 */
public class Error {
	public enum SeverityType {
		ERROR("ERROR"),
		WARNING("WARNING");
		
		private String type = null;
		private SeverityType(String type) {
			this.type = type;
		}
		public String getType() {
			return this.type;
		}

	}
	public SeverityType severityType = null;
	public String message = null;
	
	public Error(SeverityType severityType, String message) {
		this.severityType = severityType;
		this.message = message;
	}
 
	/**
	 * @return the severityType
	 */
	public SeverityType getSeverityType() {
		return severityType;
	}

	/**
	 * @param severityType the severityType to set
	 */
	public void setSeverityType(SeverityType severityType) {
		this.severityType = severityType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
