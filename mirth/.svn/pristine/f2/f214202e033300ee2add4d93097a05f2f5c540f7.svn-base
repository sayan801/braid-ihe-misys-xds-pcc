
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
 * The base class for Test and Battery.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 2, 2005
 */
public class TestBase {
    private String name;
    private Code code;
    private Order order;
    private String category;
    private Code[] codes;	//To support EMR9.0

    public TestBase() {
    }


    
    //To support EMR9.0
    public TestBase(
            String name,
            Code[] codes,
            Order order,
            String category) {
            this.name = name;
            this.codes = codes;
            this.order = order;
            this.category = category;
            if(codes != null && codes.length > 0){
                this.code = codes[0];
            }
     }

    /**
     * Gets the name value for this TestBase.
     *
     * @return name                    
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this TestBase.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the code value for this TestBase.
     *
     * @return code
     */
    public Code getCode() {
        return code;
    }


    /**
     * Sets the code value for this TestBase.
     *
     * @param code
     */
    public void setCode(Code code) {
        this.code = code;
    }


    /**
     * Gets the order value for this TestBase.
     *
     * @return order
     */
    public Order getOrder() {
        return order;
    }


    /**
     * Sets the order value for this TestBase.
     *
     * @param order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the value of the category property.
     *
     * @return category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the value of the category property.
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }
	
	/**
     * Gets the Code array value for this TestBase.
     *
     * @return Code[]
     */
    public Code[] getCodes()
    {
        return codes;
    }
	
	/**
     * Sets the Code array value for this TestBase.
     *
     * @param Code[]
     */
    public void setCodes(Code[] codes)
    {
        this.codes = codes;
        if(codes != null && codes.length > 0){
            this.code = codes[0];
        }
    }

}

