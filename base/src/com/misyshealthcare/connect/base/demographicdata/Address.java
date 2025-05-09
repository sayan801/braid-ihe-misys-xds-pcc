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
package com.misyshealthcare.connect.base.demographicdata;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;

/**
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 *
 * @author : Shantanu Paul
 * @version : 1.0  Oct 26, 2005
 */
public class Address {
    private AddressType  addType;
    private String  addLine1;
    private String  addLine2;
    private String  addCity;
    private String  addState;
    private String  addCounty;
    private String  addCountry;
    private String  addZip;

    public AddressType getAddType() {
        return addType;
    }

    public void setAddType(AddressType addType) {
        this.addType = addType;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public String getAddCity() {
        return addCity;
    }

    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    public String getAddState() {
        return addState;
    }

    public void setAddState(String addState) {
        this.addState = addState;
    }

    public String getAddCountry() {
        return addCountry;
    }

    public void setAddCountry(String addCountry) {
        this.addCountry = addCountry;
    }
    
    public String getAddCounty() {
        return addCounty;
    }

    public void setAddCounty(String addCounty) {
        this.addCounty = addCounty;
    }

    public String getAddZip() {
        return addZip;
    }

    public void setAddZip(String addZip) {
        this.addZip = addZip;
    }

  	/**
  	 * Check if this address actually holds an address.
  	 * 
  	 * @return True if this address is populated.
  	 */
    public boolean isEmpty() {
    	return ((addLine1 == null) && (addLine2 == null) && (addCity == null) && (addState == null) && (addZip == null));
    }

}
