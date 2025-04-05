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
 * The class represents a Immunization object.
 *
 * @author Wenzhi Li
 * @version 2.0, Aug 23, 2005
 */
public class Immunization {

    private Calendar dateTime;
    private String immunizationType;
    private Code code;
    private Code routeCode;
    private DoseQuantity dose;
    private String administeredBy;
    private String comment;
    private String reference;
    private String source;

    public Immunization() {
    }


    public Immunization(Calendar dateTime, String immunizationType, Code code,
			Code routeCode, DoseQuantity dose, String administeredBy,
			String comment, String reference, String source) {
		super();
		this.dateTime = dateTime;
		this.immunizationType = immunizationType;
		this.code = code;
		this.routeCode = routeCode;
		this.dose = dose;
		this.administeredBy = administeredBy;
		this.comment = comment;
		this.reference = reference;
		this.source = source;
	}

	/**
     * Gets the dateTime value for this Immunization.
     *
     * @return dateTime
     */
    public Calendar getDateTime() {
        return dateTime;
    }


    /**
     * Sets the dateTime value for this Immunization.
     *
     * @param dateTime
     */
    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * Gets the immunizationType value for this Immunization.
     *
     * @return immunizationType
     */
    public String getImmunizationType() {
        return immunizationType;
    }


    /**
     * Sets the immunizationType value for this Immunization.
     *
     * @param immunizationType
     */
    public void setImmunizationType(String immunizationType) {
        this.immunizationType = immunizationType;
    }


    /**
     * Gets the code value for this Immunization.
     *
     * @return code
     */
    public Code getCode() {
        return code;
    }


    /**
     * Sets the code value for this Immunization.
     *
     * @param code
     */
    public void setCode(Code code) {
        this.code = code;
    }


    /**
	 * @return the routeCode
	 */
	public Code getRouteCode() {
		return routeCode;
	}


	/**
	 * @param routeCode the routeCode to set
	 */
	public void setRouteCode(Code routeCode) {
		this.routeCode = routeCode;
	}


	/**
	 * @return the dose
	 */
	public DoseQuantity getDose() {
		return dose;
	}


	/**
	 * @param dose the dose to set
	 */
	public void setDose(DoseQuantity dose) {
		this.dose = dose;
	}


	/**
     * Gets the administeredBy value for this Immunization.
     *
     * @return administeredBy
     */
    public String getAdministeredBy() {
        return administeredBy;
    }


    /**
     * Sets the administeredBy value for this Immunization.
     *
     * @param administeredBy
     */
    public void setAdministeredBy(String administeredBy) {
        this.administeredBy = administeredBy;
    }


    /**
     * Gets the comment value for this Immunization.
     *
     * @return comment
     */
    public String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this Immunization.
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * Gets the reference value for this Immunization.
     *
     * @return reference
     */
    public String getReference() {
        return reference;
    }


    /**
     * Sets the reference value for this Immunization.
     *
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }


    


    /**
     * Gets the source value for this Immunization.
     *
     * @return source
     */
    public String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Immunization.
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Immunization)) return false;
        Immunization other = (Immunization) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.dateTime==null && other.getDateTime()==null) ||
             (this.dateTime!=null &&
              this.dateTime.equals(other.getDateTime()))) &&
            ((this.immunizationType==null && other.getImmunizationType()==null) ||
             (this.immunizationType!=null &&
              this.immunizationType.equals(other.getImmunizationType()))) &&
            ((this.code==null && other.getCode()==null) ||
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.administeredBy==null && other.getAdministeredBy()==null) ||
             (this.administeredBy!=null &&
              this.administeredBy.equals(other.getAdministeredBy()))) &&
            ((this.comment==null && other.getComment()==null) ||
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.reference==null && other.getReference()==null) ||
             (this.reference!=null &&
              this.reference.equals(other.getReference()))) &&
            ((this.source==null && other.getSource()==null) ||
             (this.source!=null &&
              this.source.equals(other.getSource())));
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
        if (getDateTime() != null) {
            _hashCode += getDateTime().hashCode();
        }
        if (getImmunizationType() != null) {
            _hashCode += getImmunizationType().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getAdministeredBy() != null) {
            _hashCode += getAdministeredBy().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getReference() != null) {
            _hashCode += getReference().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
