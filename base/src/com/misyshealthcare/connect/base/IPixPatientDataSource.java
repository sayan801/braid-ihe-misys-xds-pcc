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

/**
 * The PIX Data Source is the source that provides patient data for a PIX Manager.
 *
 * @author Wenzhi Li
 * @version 2.0, Mar 21, 2007
 */
public interface IPixPatientDataSource {

    /**
     * Whether the given patient is a valid patient in the patient data source.
     *
     * @param pid The given patient Identifier.
     * @return <code>true</code> if the patient id is valide; <code>false</code> otherwise.
     */
    public boolean isValidPatient(PatientIdentifier pid);

    /**
     * Find all patient IDs cross all domains given a patinet Identifier from the patient
     * data source. All retrived patient ids must represent the same patient, though there
     * may have differnt ID domains (system IDs).
     *
     * @param pid
     * @return A list of PatientIdentifier which does not include the request patient id.
     */
    public List<PatientIdentifier> crossFindAllPatients(PatientIdentifier pid);

}
