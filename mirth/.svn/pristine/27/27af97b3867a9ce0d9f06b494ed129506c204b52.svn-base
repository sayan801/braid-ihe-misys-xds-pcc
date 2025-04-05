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

import java.util.Calendar;

/**
 * <Descriptions of the class>
 *
 * @author Wenzhi Li
 * @version 2.0, Aug 26, 2005
 */
public class EffectiveDates {
    private Calendar start;
    private Calendar end;

    public EffectiveDates() {
    }

    public EffectiveDates(
           Calendar start,
           Calendar end) {
           this.start = start;
           this.end = end;
    }


    /**
     * Gets the start value for this EffectiveDates.
     *
     * @return start
     */
    public Calendar getStart() {
        return start;
    }


    /**
     * Sets the start value for this EffectiveDates.
     *
     * @param start
     */
    public void setStart(Calendar start) {
        this.start = start;
    }


    /**
     * Gets the end value for this EffectiveDates.
     *
     * @return end
     */
    public Calendar getEnd() {
        return end;
    }


    /**
     * Sets the end value for this EffectiveDates.
     *
     * @param end
     */
    public void setEnd(Calendar end) {
        this.end = end;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EffectiveDates)) return false;
        EffectiveDates other = (EffectiveDates) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.start==null && other.getStart()==null) ||
             (this.start!=null &&
              this.start.equals(other.getStart()))) &&
            ((this.end==null && other.getEnd()==null) ||
             (this.end!=null &&
              this.end.equals(other.getEnd())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getStart() != null) {
            _hashCode += getStart().hashCode();
        }
        if (getEnd() != null) {
            _hashCode += getEnd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
