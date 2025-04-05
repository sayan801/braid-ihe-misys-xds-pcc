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


package com.misyshealthcare.connect.ihe.pdq;


import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.net.Identifier;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is a container to store the PDQ continuation information that pointer pointers to.
 *
 * @author Wenzhi Li
 * @version 2.1, Apr 25, 2007
 */
public class ContinuationPointer {
    private String pointer;
    private List<List<PatientDescriptor>> patients = null;
    private List<Identifier> returnDomains = null;
    private long lastRequestTime = System.currentTimeMillis();

    public String getPointer() {
        return pointer;
    }
    public void setPointer(String pointer) {
        this.pointer = pointer;
    }

    public List<List<PatientDescriptor>> getPatients() {
        if (patients == null)
            return new ArrayList<List<PatientDescriptor>>();
        else
            return patients;
    }
    public void setPatients(List<List<PatientDescriptor>> patients) {
        this.patients = patients;
    }

    public List<Identifier> getReturnDomain() {
        if (returnDomains == null)
            return new ArrayList<Identifier>();
        else
            return returnDomains;
    }
    public void setReturnDomain(List<Identifier> domains) {
        this.returnDomains = domains;
    }

    public long getLastRequestTime() {
        return lastRequestTime;
    }
    public void setLastRequestTime(long time) {
        this.lastRequestTime = time;
    }
}
