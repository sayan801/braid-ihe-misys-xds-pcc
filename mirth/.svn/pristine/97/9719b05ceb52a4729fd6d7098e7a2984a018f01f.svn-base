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
 * This interface is used to define a patient ID cross reference manager.
 *
 * @author Wenzhi Li
 * @version 2.0, Mar 1, 2007
 */
public interface IPatientXrefManager {
    /**
	 * Start this patient ID cross reference manager.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();

	/**
	 * Stop this patient ID cross reference manager.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();

    /**
     * Register the patient data manager which handles the patient saving and retriving information.
     *
     * @param pixPatientDataSource
     */
    public void registerPixManagerDataSource(IPixPatientDataSource pixPatientDataSource);

    /**
	 * Get an informative name for this patient ID cross reference source for use in error
	 * and log messages.
	 *
	 * @return An informative name for this patient ID cross reference source
	 */
	public String getName();
}
