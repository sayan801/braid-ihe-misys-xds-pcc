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
 * The class represents an Allergy object.
 *
 * @author Wenzhi Li
 * @version 2.0, Aug 22, 2005
 */
public class Allergy {
    private String agent;
    private String reaction;
    private Code code;
    private String source;
    private Calendar reportDate;

    public Allergy() {
    }

    public Allergy(
           String agent,
           String reaction,
           Code code,
           String source,
           Calendar reportDate,
           Provider provider) {
           this.agent = agent;
           this.reaction = reaction;
           this.code = code;
           this.source = source;
           this.reportDate = reportDate;
    }


    /**
     * Gets the agent value for this Allergy.
     *
     * @return agent
     */
    public String getAgent() {
        return agent;
    }


    /**
     * Sets the agent value for this Allergy.
     *
     * @param agent
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }


    /**
     * Gets the reaction value for this Allergy.
     *
     * @return reaction
     */
    public String getReaction() {
        return reaction;
    }


    /**
     * Sets the reaction value for this Allergy.
     *
     * @param reaction
     */
    public void setReaction(String reaction) {
        this.reaction = reaction;
    }


    /**
     * Gets the code value for this Allergy.
     *
     * @return code
     */
    public Code getCode() {
        return code;
    }


    /**
     * Sets the code value for this Allergy.
     *
     * @param code
     */
    public void setCode(Code code) {
        this.code = code;
    }


    /**
     * Gets the source value for this Allergy.
     *
     * @return source
     */
    public String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Allergy.
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }


    /**
     * Gets the reportDate value for this Allergy.
     *
     * @return reportDate
     */
    public Calendar getReportDate() {
        return reportDate;
    }


    /**
     * Sets the reportDate value for this Allergy.
     *
     * @param reportDate
     */
    public void setReportDate(Calendar reportDate) {
        this.reportDate = reportDate;
    }


    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Allergy)) return false;
        Allergy other = (Allergy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.agent==null && other.getAgent()==null) ||
             (this.agent!=null &&
              this.agent.equals(other.getAgent()))) &&
            ((this.reaction==null && other.getReaction()==null) ||
             (this.reaction!=null &&
              this.reaction.equals(other.getReaction()))) &&
            ((this.code==null && other.getCode()==null) ||
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.source==null && other.getSource()==null) ||
             (this.source!=null &&
              this.source.equals(other.getSource()))) &&
            ((this.reportDate==null && other.getReportDate()==null) ||
             (this.reportDate!=null &&
              this.reportDate.equals(other.getReportDate())));
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
        if (getAgent() != null) {
            _hashCode += getAgent().hashCode();
        }
        if (getReaction() != null) {
            _hashCode += getReaction().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        if (getReportDate() != null) {
            _hashCode += getReportDate().hashCode();
        }
        
        __hashCodeCalc = false;
        return _hashCode;
    }


}
