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

package com.misyshealthcare.connect.base.clinicaldata;


/**
 * <Descriptions of the class>
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 2, 2005
 */
public class LabResult  {
    private Test[] test;
    private Battery[] battery;

    public LabResult() {
    }

    public LabResult(
           Test[] test,
           Battery[] battery) {
           this.test = test;
           this.battery = battery;
    }


    /**
     * Gets the test value for this LabResult
     *
     * @return test
     */
    public Test[] getTest() {
        return test;
    }


    /**
     * Sets the test value for this LabResults.
     *
     * @param test
     */
    public void setTest(Test[] test) {
        this.test = test;
    }

    public Test getTest(int i) {
        return this.test[i];
    }

    public void setTest(int i, Test _value) {
        this.test[i] = _value;
    }


    /**
     * Gets the battery value for this LabResults.
     *
     * @return battery
     */
    public Battery[] getBattery() {
        return battery;
    }


    /**
     * Sets the battery value for this LabResults.
     *
     * @param battery
     */
    public void setBattery(Battery[] battery) {
        this.battery = battery;
    }

    public Battery getBattery(int i) {
        return this.battery[i];
    }

    public void setBattery(int i, Battery _value) {
        this.battery[i] = _value;
    }

}
