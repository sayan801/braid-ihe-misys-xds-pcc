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
 * The class represents a Problem.
 *
 * @author Wenzhi Li
 * @version 2.0, 06/05/05
 * @see ISimpleProblem
 */
public class SimpleProblem {

    private String name;
    private Code code;
    private Calendar startDate;
    private Calendar endDate;
    private SharedEnums.ClinicalStatusCode status;
    private String source;
    //private Provider provider;
    private String comment;

    public SimpleProblem(
           String name,
           Code code,
           java.util.Calendar startDate,
           java.util.Calendar endDate,
           SharedEnums.ClinicalStatusCode status,
           //Provider provider,
           String source,
           String comment) {
           this.name = name;
           this.code = code;
           this.startDate = startDate;
           this.endDate = endDate;
           this.status = status;
           //this.provider = provider;
           this.source = source;
           this.comment = comment;
    }

    /**
     * Gets the name value for this Problem.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name value for this Problem.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the code value for this Problem.
     *
     * @return the code
     */
    public Code getCode() {
        return code;
    }

    /**
     * Sets the code for this Problem.
     *
     * @param code
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * Gets the start date for this Problem.
     *
     * @return the start date
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date for this Problem.
     *
     * @param startDate the start date
     */
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date for this Problem.
     *
     * @return the end date
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date for this Problem.
     *
     * @param endDate the end date
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the status for this Problem
     *
     * @return the status
     */
    public SharedEnums.ClinicalStatusCode getStatus() {
        return status;
    }

    /**
     * Sets the status for this Problem
     *
     * @param status
     */
    public void setStatus(SharedEnums.ClinicalStatusCode status){
        this.status = status;
    }

    /**
     * Gets the source for this Problem.
     *
     * @return the soruce
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source for this Problem.
     *
     * @param source
     */
    public void setSource(String source){
        this.source = source;
    }

     /**
     * Gets the provider for this Problem.
     *
     * @return the provider
     */
    /*public Provider getProvider() {
        return provider;
    }

    *//**
     * Sets the provider for this Problem.
     *
     * @param provider
     *//*
    public void setProvider(Provider provider){
        this.provider = provider;
    }*/

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
