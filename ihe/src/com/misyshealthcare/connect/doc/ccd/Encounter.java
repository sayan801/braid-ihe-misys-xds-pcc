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

import java.util.Calendar;

import com.misyshealthcare.connect.base.SharedEnums;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 7, 2007
 */
public class Encounter {
	private Id encounterId = null;
	private Calendar startTime = null;
	private Calendar endTime = null;
	private SharedEnums.DischargeDispositionCode dischargeDispositionCode = null;
	/**
	 * @return the encounterId
	 */
	public Id getEncounterId() {
		return encounterId;
	}
	/**
	 * @param encounterId the encounterId to set
	 */
	public void setEncounterId(Id encounterId) {
		this.encounterId = encounterId;
	}
	/**
	 * @return the startTime
	 */
	public Calendar getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Calendar getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the dischargeDispositionCode
	 */
	public SharedEnums.DischargeDispositionCode getDischargeDispositionCode() {
		return dischargeDispositionCode;
	}
	/**
	 * @param dischargeDispositionCode the dischargeDispositionCode to set
	 */
	public void setDischargeDispositionCode(SharedEnums.DischargeDispositionCode dischargeDispositionCode) {
		this.dischargeDispositionCode = dischargeDispositionCode;
	}
}
