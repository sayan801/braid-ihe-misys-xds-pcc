
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
 * This class describes lab battery.
 *
 * @author Wenzhi Li
 * @version 2.0, Sep 28, 2005
 */
public class Battery extends TestBase  {
    private Test[] test;

    public Battery() {
    }

    public Battery(
           Test[] test) {
           this.test = test;
    }


    /**
     * Gets the test value for this Battery.
     *
     * @return test
     */
    public Test[] getTest() {
        return test;
    }


    /**
     * Sets the test value for this Battery.
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

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Battery)) return false;
        Battery other = (Battery) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.test==null && other.getTest()==null) ||
             (this.test!=null &&
              java.util.Arrays.equals(this.test, other.getTest())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getTest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTest());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTest(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
