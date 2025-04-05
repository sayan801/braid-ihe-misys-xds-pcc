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

import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;

/**
 * This interface is used by classes that want to accept update
 * information about patients.  The <code>PatientBroker</code>
 * uses this interface to pass patient creation information around
 * to all registered patient consumers.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 27, 2005
 * @see PatientBroker
 */
public interface IPatientConsumer {
	
	/**
	 * Values acceptable as reasons for creating a new patient within
	 * Misys Connect 2.0.
	 */
	public enum CreationReason {INPATIENT_ADMIT, INPATIENT_PREADMIT, OUTPATIENT_REGISTER}
	
	/**
	 * Start this patient consumer.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();
	
	/**
	 * Stop this patient consumer.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();
	
	/**
	 * Create a new patient with the supplied demographics.  The reason the patient
	 * was created must be supplied.
	 * 
	 * @param patient The demographics of the new patient
	 * @param reason The reason the patient was created
	 * @throws PatientException When there is an error creating the patient
	 */
	public void createPatient(PatientDescriptor patient, CreationReason reason) throws PatientException;
	
	/**
	 * Update the demographic information associated with a patient.
	 * 
	 * @param patient The new demographics for the patient
	 * @throws PatientException When there is an error updating the patient
	 */
	public void updatePatient(PatientDescriptor patient) throws PatientException;
	
	/**
	 * Merge the two patients demographics together as they represent the
	 * same patient.  The patient will now be known by the deomgraphics and IDs
	 * in the first patient.  The second patient IDs will no longer be used.
	 * 
	 * @param patientMain The patient demographics and IDs to remain
	 * @param patientOld The patient demographics and IDs found to be the same as the main patient
	 * @throws PatientException When there is an error merging the two patients together
	 */
	public void mergePatients(PatientDescriptor patientMain, PatientDescriptor patientOld) throws PatientException;

}
