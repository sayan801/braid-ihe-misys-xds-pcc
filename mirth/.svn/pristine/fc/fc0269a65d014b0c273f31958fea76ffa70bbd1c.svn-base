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

import com.misyshealthcare.connect.base.demographicdata.Formatter;

/**
 * The object to represent a person's name. This object is used to construct document.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 17, 2005
 */
public class PersonName {
    private String firstName = null;
    private String middleName = null;
    private String lastName = null;
    private String prefix = null;
    private String suffix = null;

    /**
     * Default construtor
     */
    public PersonName() {
    }

    /**
     * Constructor
     *
     * @param first the first name
     * @param last the last name
     * @param middle the middle name
     * @param prefix the name prefix
     * @param suffix the name suffix
     */
    public PersonName(String first, String last, String middle, String prefix, String suffix) {
        this.firstName = first;
        this.lastName = last;
        this.middleName = middle;
        this.prefix   = prefix;
        this.suffix   = suffix;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name.
     *
     * @param first the first name to be set
     */
    public void setFirstName(String first) {
        this.firstName = first;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }


    /**
     * Sets the last name.
     *
     * @param last the last name to be set
     */
    public void setLastName(String last) {
        this.lastName = last;
    }

    /**
     * Gets the middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return this.middleName;
    }


    /**
     * Sets the middle name.
     *
     * @param middle the middle name to be set
     */
    public void setMiddleName(String middle) {
        this.middleName = middle;
    }

    /**
     * Gets the name prefix.
     *
     * @return the name prefix
     */
    public String getPrefix() {
        return this.prefix;
    }


    /**
     * Sets the prefix
     *
     * @param prefix the  name prefix to be set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the name suffix.
     *
     * @return the name suffix
     */
    public String getSuffix() {
        return this.suffix;
    }
    

    /**
     * Sets the suffix
     *
     * @param suffix the  name suffix to be set
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Returns a formatted string with this person's name
     * @return String representing person's name
     */
    public String getFormattedName(){
    	return Formatter.getNameString(this.prefix, this.firstName, "", this.lastName, this.suffix);
    }
}
