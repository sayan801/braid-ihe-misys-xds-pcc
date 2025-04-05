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

import java.util.List;

import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;

/**
 * This interface is used by the PatientManager to get a
 * set of patients from a system.
 * <p>
 * If there are multiple sets of demographics for the same patient
 * from this source, return each set in a separate PatientDescriptor.
 * The PatientManager will group them into a PatientDescriptorSet.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 20, 2005
 */
public interface IPatientSource {
	
	/**
	 * Start this patient source.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();
	
	/**
	 * Stop this patient source.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();
	
	/**
	 * Find all of the patients from a particular patient source
	 * that have properties matching those in the given descriptor.
	 * 
	 * @param descriptor The patient description to match against
	 * @return The patients found that match the description
	 * @throws PatientException When there is a problem making the query
	 */
	public List<PatientDescriptor> findPatients(PatientQuery query) throws PatientException;
	
	/**
	 * Get an informative name for this patient source for use in error
	 * and log messages.
	 * 
	 * @return An informative name for this patient source
	 */
	public String getName();

}
