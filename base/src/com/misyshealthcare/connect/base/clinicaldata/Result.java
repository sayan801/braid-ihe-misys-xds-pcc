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
 * The class describes an instance of lab result
 *
 * @author Wenzhi Li
 * @version 2.0, Sep 28, 2005
 */
public class Result {
    private String type;
    private String value;
    private String uom;
    private String status;
    private java.util.Calendar date;
    private String comments;
    private String interpretation;
    private String name;
    private String range;
    private Code[] codes;				//To support EMR9.0
    private Attachment[] attachments;	//To support EMR9.0
    private String provider;            //To support EMR9.0

    public Result() {
    }

    public Result(
           String type,
           String value,
           String uom,
           String status,
           java.util.Calendar date,
           String comments,
           String interpretation,
           String name,
           String range) {
           this.type = type;
           this.value = value;
           this.uom = uom;
           this.status = status;
           this.date = date;
           this.comments = comments;
           this.interpretation = interpretation;
           this.name = name;
           this.range = range;
    }
    
    // To support EMR9.0
    public Result(
            String type,
            String value,
            String uom,
            String status,
            java.util.Calendar date,
            String comments,
            String interpretation,
            String name,
            String range,
            Code[] codes,
            Attachment[] attachments,
            String provider) {
            this.type = type;
            this.value = value;
            this.uom = uom;
            this.status = status;
            this.date = date;
            this.comments = comments;
            this.interpretation = interpretation;
            this.name = name;
            this.range = range;
            this.codes = codes;
            this.attachments = attachments;
            this.provider = provider;
     }


    /**
     * Gets the type value for this Result.
     *
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * Sets the type value for this Result.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Gets the value value for this Result.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }


    /**
     * Sets the value value for this Result.
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }


    /**
     * Gets the uom value for this Result.
     *
     * @return uom
     */
    public String getUom() {
        return uom;
    }


    /**
     * Sets the uom value for this Result.
     *
     * @param uom
     */
    public void setUom(String uom) {
        this.uom = uom;
    }


    /**
     * Gets the status value for this Result.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Result.
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Gets the date value for this Result.
     *
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Result.
     *
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the comments value for this Result.
     *
     * @return comments
     */
    public String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this Result.
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * Gets the interpretation value for this Result.
     *
     * @return interpretation
     */
    public String getInterpretation() {
        return interpretation;
    }


    /**
     * Sets the interpretation value for this Result.
     *
     * @param interpretation
     */
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }


    /**
     * Gets the name value for this Result.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Result.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the range value for this Result.
     *
     * @return range
     */
    public String getRange() {
        return range;
    }


    /**
     * Sets the range value for this Result.
     *
     * @param range
     */
    public void setRange(String range) {
        this.range = range;
    }
    
    /**
     * Gets the Code array value for this Codes.
     *
     * @return range
     */
    public Code[] getCodes()
    {
        return codes;
    }

    /**
     * Sets the Code array value for this Codes.
     *
     * @param range
     */
    public void setCodes(Code[] codes)
    {
        this.codes = codes;
    }
    
    /**
     * Gets the Attachment array value for this Attachments.
     *
     * @return range
     */
    public Attachment[] getAttachments()
    {
        return attachments;
    }

    /**
     * Sets the Attachment array value for this Attachments.
     *
     * @param attachments
     */
    public void setAttachments(Attachment[] attachments)
    {
        this.attachments = attachments;
    }


    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Result)) return false;
        Result other = (Result) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.type==null && other.getType()==null) ||
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.value==null && other.getValue()==null) ||
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.uom==null && other.getUom()==null) ||
             (this.uom!=null &&
              this.uom.equals(other.getUom()))) &&
            ((this.status==null && other.getStatus()==null) ||
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.comments==null && other.getComments()==null) ||
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            ((this.interpretation==null && other.getInterpretation()==null) ||
             (this.interpretation!=null &&
              this.interpretation.equals(other.getInterpretation()))) &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.range==null && other.getRange()==null) ||
             (this.range!=null &&
              this.range.equals(other.getRange())));
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getUom() != null) {
            _hashCode += getUom().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        if (getInterpretation() != null) {
            _hashCode += getInterpretation().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getRange() != null) {
            _hashCode += getRange().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    public String getProvider()
    {
        return provider;
    }

    public void setProvider(String provider)
    {
        this.provider = provider;
    }
}

