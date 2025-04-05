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

import com.misyshealthcare.connect.base.SharedEnums;


/**
 * The class represents a Procedure object.
 *
 * @author Wenzhi Li
 * @version 2.0, Aug 25, 2005
 */
public class Procedure {
    private Calendar date;
    private String name;
    private Code code;
    private SharedEnums.StatusCode statusCode;
    private String source;
    //private Provider provider;

    public Procedure() {
    }

    public Procedure(
           Calendar date,
           String name,
           Code code,
           SharedEnums.StatusCode statusCode,
           String source,
           Provider provider) {
           this.date = date;
           this.name = name;
           this.code = code;
           this.statusCode = statusCode;
           this.source = source;
           //this.provider = provider;
    }


    /**
     * Gets the date value for this Procedure.
     *
     * @return date
     */
    public Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Procedure.
     *
     * @param date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }


    /**
     * Gets the name value for this Procedure.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Procedure.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the code value for this Procedure.
     *
     * @return code
     */
    public Code getCode() {
        return code;
    }


    /**
     * Sets the code value for this Procedure.
     *
     * @param code
     */
    public void setCode(Code code) {
        this.code = code;
    }


    /**
	 * @return the statusCode
	 */
	public SharedEnums.StatusCode getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(SharedEnums.StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	/**
     * Gets the source value for this Procedure.
     *
     * @return source
     */
    public String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Procedure.
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }


    /**
     * Gets the provider value for this Procedure.
     *
     * @return provider
     *//*
    public Provider getProvider() {
        return provider;
    }


    *//**
     * Sets the provider value for this Procedure.
     *
     * @param provider
     *//*
    public void setProvider(Provider provider) {
        this.provider = provider;
    }*/

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Procedure)) return false;
        Procedure other = (Procedure) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.code==null && other.getCode()==null) ||
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
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
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
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
