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

/**
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 *
 * This class represents a Patient MRN. Note that there are NO
 * setters for the 2 instance variables because both are required
 * and should be specified in the constructor
 *
 * @author : Shantanu Paul
 * @version : 1.0
 * Date: Oct 26, 2005
 **/

public class PatientMRN {
    private String      assigningAuth;
    private String      mrn;
    /**
     * Create a new PatientMRN object
     * @param assigningAuth - the Assigning authority for this MRN
     * @param mrn - teh Medical Record Number
     */
    public PatientMRN(String assigningAuth, String mrn){
        this.assigningAuth = assigningAuth;
        this.mrn = mrn;
    }
    /**
     * Get the Assigning authority
     * @return
     */
    public String getAssigningAuth() {
        return assigningAuth;
    }
    public void setAssigningAuth(String aa) {
        this.assigningAuth = aa;
    }
    /**
     * Get the Medical record Number
     * @return
     */
    public String getMrn() {
        return mrn;
    }
    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientMRN)) return false;

        final PatientMRN patientMRN = (PatientMRN) o;

        if (assigningAuth != null ? !assigningAuth.equals(patientMRN.assigningAuth) : patientMRN.assigningAuth != null) return false;
        if (mrn != null ? !mrn.equals(patientMRN.mrn) : patientMRN.mrn != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (assigningAuth != null ? assigningAuth.hashCode() : 0);
        result = 29 * result + (mrn != null ? mrn.hashCode() : 0);
        return result;
    }


}
