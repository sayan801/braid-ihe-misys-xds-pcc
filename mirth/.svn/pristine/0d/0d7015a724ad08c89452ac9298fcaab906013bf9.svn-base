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

/**
 * This interface is used by the patient broker to look up
 * patient ID cross-reference information.
 * 
 * @author Jim Firby
 * @version 2.0 - Dec 23, 2005
 */
public interface IPatientXref {

	/**
	 * Start this document xref.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();
	
	/**
	 * Stop this document xref.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();
	
	/**
	 * Ask the patient ID cross reference source for all of the IDs that it
	 * knows for a given patient.  If a Misys Unique ID is discovered, it will be
	 * returned.  Other IDs are added directly to the patientId object.
	 * 
	 * @param patientId The known IDs for the patient of interest
	 * @return The Misys Unique ID for the patient according to this cross reference source
	 * @throws PatientException When there is a problem talking with the cross reference source
	 */
	public String getPatientIds(PatientID patientId) throws PatientException;
	
	/**
	 * Get an informative name for this patient ID cross reference source for use in error
	 * and log messages.
	 * 
	 * @return An informative name for this patient ID cross reference source
	 */
	public String getName();

}
