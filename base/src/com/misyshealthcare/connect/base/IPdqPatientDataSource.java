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
 *  The PIX Data Source is the source that provides patient data for a PIX Manager.
 *
 * @author Wenzhi Li
 * @version 2.1, Apr 25, 2007
 */
public interface IPdqPatientDataSource {

    /**
     * Find a list of Matchied patients based on a Patient query
     *
     * @param query the PatientQuery
     * @return a list of list of PatientDescriptor. The first list is a list of different patients, while the second
     *         list is a list of the same patient in different domain systems.
     */
    public List<List<PatientDescriptor>> findPatientsFromQuery(PatientQuery query);

}
