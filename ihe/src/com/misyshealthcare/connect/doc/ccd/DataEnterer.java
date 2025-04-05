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

/**
 * The container class for data enterer.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 26, 2006
 */
public class DataEnterer {
    private Calendar time = null;
    private PersonName personName = null;
    private Id assignedEntityId = null;

    public Calendar getTime() {
        return this.time;
    }
    public void setTime(Calendar time) {
        this.time = time;
    }

    public Id getAssignedEntityId() {
        return this.assignedEntityId;
    }
    public void setAssignedEntityId(Id assignedEntityId) {
        this.assignedEntityId = assignedEntityId;
    }

    public PersonName getPersonName() {
        return this.personName;
    }
    public void setPersonName(PersonName personName) {
        this.personName = personName;
    }


}
