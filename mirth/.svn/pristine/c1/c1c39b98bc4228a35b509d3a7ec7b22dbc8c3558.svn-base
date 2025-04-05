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
 * The class represents a Medication object.
 *
 * @author Wenzhi Li
 * @version 2.0, Aug 22, 2005
 */
public class Medication {
    private String productName;
    private Calendar date;
    private Calendar lastAdministrationDate;
    private Code code;
    private String sig;
    private Code routeCode;
    private DoseQuantity dose;
    private String frequency;
    private String duration;
    private String refills;
    private Code problemCode;
    private String status;
    private String source;
    //private Provider provider;

    public Medication() {
    }


    public Medication(String productName, Calendar date,
			Calendar lastAdministrationDate, Code code, String sig,
			Code routeCode, DoseQuantity dose, String frequency,
			String duration, String refills, Code problemCode, String status,
			String source) {
		super();
		this.productName = productName;
		this.date = date;
		this.lastAdministrationDate = lastAdministrationDate;
		this.code = code;
		this.sig = sig;
		this.routeCode = routeCode;
		this.dose = dose;
		this.frequency = frequency;
		this.duration = duration;
		this.refills = refills;
		this.problemCode = problemCode;
		this.status = status;
		this.source = source;
	}

	/**
     * Gets the productName value for this Medication.
     *
     * @return productName
     */
    public String getProductName() {
        return productName;
    }


    /**
     * Sets the productName value for this Medication.
     *
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }


    /**
     * Gets the date value for this Medication.
     *
     * @return date
     */
    public Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Medication.
     *
     * @param date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }


    /**
     * Gets the lastAdministrationDate value for this Medication.
     *
     * @return lastAdministrationDate
     */
    public Calendar getLastAdministrationDate() {
        return lastAdministrationDate;
    }


    /**
     * Sets the lastAdministrationDate value for this Medication.
     *
     * @param lastAdministrationDate
     */
    public void setLastAdministrationDate(Calendar lastAdministrationDate) {
        this.lastAdministrationDate = lastAdministrationDate;
    }


    /**
     * Gets the code value for this Medication.
     *
     * @return code
     */
    public Code getCode() {
        return code;
    }


    /**
     * Sets the code value for this Medication.
     *
     * @param code
     */
    public void setCode(Code code) {
        this.code = code;
    }


    /**
     * Gets the sig value for this Medication.
     *
     * @return sig
     */
    public String getSig() {
        return sig;
    }


    /**
     * Sets the sig value for this Medication.
     *
     * @param sig
     */
    public void setSig(String sig) {
        this.sig = sig;
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
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}


	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	/**
     * Gets the duration value for this Medication.
     *
     * @return duration
     */
    public String getDuration() {
        return duration;
    }


    /**
     * Sets the duration value for this Medication.
     *
     * @param duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }


    /**
     * Gets the refills value for this Medication.
     *
     * @return refills
     */
    public String getRefills() {
        return refills;
    }


    /**
     * Sets the refills value for this Medication.
     *
     * @param refills
     */
    public void setRefills(String refills) {
        this.refills = refills;
    }


    /**
     * Gets the problemCode value for this Medication.
     *
     * @return problemCode
     */
    public Code getProblemCode() {
        return problemCode;
    }


    /**
     * Sets the problemCode value for this Medication.
     *
     * @param problemCode
     */
    public void setProblemCode(Code problemCode) {
        this.problemCode = problemCode;
    }


    /**
     * Gets the status value for this Medication.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Medication.
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Gets the source value for this Medication.
     *
     * @return source
     */
    public String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Medication.
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }


    /**
     * Gets the provider value for this Medication.
     *
     * @return provider
     */
    /*public Provider getProvider() {
        return provider;
    }


    *//**
     * Sets the provider value for this Medication.
     *
     * @param provider
     *//*
    public void setProvider(Provider provider) {
        this.provider = provider;
    }*/

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Medication)) return false;
        Medication other = (Medication) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.productName==null && other.getProductName()==null) ||
             (this.productName!=null &&
              this.productName.equals(other.getProductName()))) &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.lastAdministrationDate==null && other.getLastAdministrationDate()==null) ||
             (this.lastAdministrationDate!=null &&
              this.lastAdministrationDate.equals(other.getLastAdministrationDate()))) &&
            ((this.code==null && other.getCode()==null) ||
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.sig==null && other.getSig()==null) ||
             (this.sig!=null &&
              this.sig.equals(other.getSig()))) &&
            ((this.duration==null && other.getDuration()==null) ||
             (this.duration!=null &&
              this.duration.equals(other.getDuration()))) &&
            ((this.refills==null && other.getRefills()==null) ||
             (this.refills!=null &&
              this.refills.equals(other.getRefills()))) &&
            ((this.problemCode==null && other.getProblemCode()==null) ||
             (this.problemCode!=null &&
              this.problemCode.equals(other.getProblemCode()))) &&
            ((this.status==null && other.getStatus()==null) ||
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
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
        if (getProductName() != null) {
            _hashCode += getProductName().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getLastAdministrationDate() != null) {
            _hashCode += getLastAdministrationDate().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getSig() != null) {
            _hashCode += getSig().hashCode();
        }
        if (getDuration() != null) {
            _hashCode += getDuration().hashCode();
        }
        if (getRefills() != null) {
            _hashCode += getRefills().hashCode();
        }
        if (getProblemCode() != null) {
            _hashCode += getProblemCode().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        /*if (getProvider() != null) {
            _hashCode += getProvider().hashCode();
        }*/
        __hashCodeCalc = false;
        return _hashCode;
    }

}
