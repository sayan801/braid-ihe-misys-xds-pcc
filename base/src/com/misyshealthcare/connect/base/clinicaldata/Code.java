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
 * This class represents clinical object code
 *
 * @author Wenzhi Li
 * @version 2.0, 06/01/05
 */
public class Code {
    private String value;
    private String displayName;
    private String system;
    private String version;

    public Code(){}


    public Code(String value, String displayName, String system, String version) {
		super();
		this.value = value;
		this.displayName = displayName;
		this.system = system;
		this.version = version;
	}



	/**
     * Gets the value for this Code.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value for this Code.
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}


	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	/**
     * Gets the system value for this Code.
     *
     * @return system
     */
    public String getSystem() {
        return system;
    }

    /**
     * Sets the system value for this Code.
     *
     * @param system
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * Gets the version value for this Code.
     *
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version value for this Code.
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
}
