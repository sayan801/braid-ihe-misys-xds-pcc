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

import com.misyshealthcare.connect.base.SharedEnums;

/**
 * The data container for Proposed Disposition used by EDR.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 29, 2006
 */
public class ProposedDisposition {
    private String encounterDisposition = null;
    private EffectiveTime effectiveTime = null;
    private SharedEnums.DischargeDispositionCode dischargeDispositionCode = null;

    public String getEncounterDisposition() {
        return this.encounterDisposition;
    }
    public void setEncounterDisposition(String disposition) {
        this.encounterDisposition = disposition;
    }

    public EffectiveTime getEffectiveTime() {
        return this.effectiveTime;
    }
    public void setEffectiveTime(EffectiveTime effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public SharedEnums.DischargeDispositionCode getDischargeDispositionCode(){
        return this.dischargeDispositionCode;
    }
    public void setDischargeDispositionCode(SharedEnums.DischargeDispositionCode dischargeDispositionCode){
        this.dischargeDispositionCode = dischargeDispositionCode;
    }
}
