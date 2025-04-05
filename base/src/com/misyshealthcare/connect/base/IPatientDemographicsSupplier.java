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
 * This interface is used to define a patient demographics supplier.
 *
 * @author Wenzhi Li
 * @version 2.1, Mar 27, 2007
 */
public interface IPatientDemographicsSupplier {
    /**
	 * Start this patient demographics supplier.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();

	/**
	 * Stop this patient demographics supplier.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();

    /**
     * Register the patient data source which provides patient data for PDQ.
     *
     * @param pdqPatientDataSource
     */
    public void registerPdqPatientDataSource(IPdqPatientDataSource pdqPatientDataSource);

    /**
	 * Get an informative name for this patient demographics supplier for use in error
	 * and log messages.
	 *
	 * @return An informative name for this patient demographics supplier
	 */
	public String getName();


}
