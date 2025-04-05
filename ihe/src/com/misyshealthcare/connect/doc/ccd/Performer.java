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
 * The class represent a Performer who provides the care service to a patient.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 13, 2005
 */
public class Performer extends Participant {

    private CodeSystem functionCode;
    private Calendar startTime; //Optional healthcare service start time by this performer
    private Calendar endTime;   //OPtional healthcare service end time by this performer
    private CodeSystem code;

    public Performer() {
        functionCode =  new CodeSystem("PCP", "2.16.840.1.113883.5.88", "ParticipatingFunction", "primary care physician");  //defaults to PCP (Primary Care Physician)
        startTime = null;
        endTime = null;
        //Code defaults to General Physician. This code must come from SOMED CT.
        //<code code='59058001' codeSystem='2.16.840.1.113883.6.96' codeSystemName='SNOMED CT' displayName='General Physician'/>
        code = new CodeSystem("59058001", "2.16.840.1.113883.6.96", "SNOMED CT", "General Physician");
    }


    /**
     * Gets the Function code which must come from ParticipationFunction vocabulary domain. For example,
     * <functionCode code='PCP' codeSystem='2.16.840.1.113883.5.88'/>
     *
     * @return the function code.
     */
    public CodeSystem getFunctionCode() {
        return this.functionCode;
    }

    /**
     * Sets the Function code which must come from ParticipationFunction vocabulary domain. For example,
     * <functionCode code='PCP' codeSystem='2.16.840.1.113883.5.88'/>
     *
     * @param functionCode
     */
    public void setFunctionCode(CodeSystem functionCode) {
        this.functionCode = functionCode;
    }

    /**
     * Gets the start time of the performer service. The start time is optional
     *
     * @return the start time
     */
    public Calendar getStartTime() {
        return  this.startTime;
    }

    /**
     * Sets the start time of the performer servcie. The start time is optional.
     *
     * @param startTime the start time to be set
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of the performer service. The end time is optional.
     *
     * @return the end time
     */
    public Calendar getEndTime() {
        return this.endTime;
    }

    /**
     * Sets th end time of hte performer service. The end time is optional.
     * @param endTime
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the code of this performer. For example
     * <code code='59058001' codeSystem='2.16.840.1.113883.6.96' codeSystemName='SNOMED CT' displayName='General Physician'/>
     *
     * @return the code
     */
    public CodeSystem getCode() {
        return  this.code;
    }

    /**
     * Gets the code of this performer. For example
     * <code code='59058001' codeSystem='2.16.840.1.113883.6.96' codeSystemName='SNOMED CT' displayName='General Physician'/>
     *
     * @param code the code to be set. The code system must be "SNOMED CT.
     */
    public void setCode(CodeSystem code) {
        this.code = code;
    }
}
