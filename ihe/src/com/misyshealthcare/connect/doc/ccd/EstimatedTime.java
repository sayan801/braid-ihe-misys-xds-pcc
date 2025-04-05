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

import java.util.Calendar;

/**
 * The class contains an estimated time, which is represented by a center time with
 * a standard deviation.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 24, 2006
 */
public class EstimatedTime {
    private Calendar time = null;
    private int standardDeviation = 0;
    private String unit = null;

    public EstimatedTime(Calendar time, int standardDeviation, String unit) {
        this.time = time;
        this.standardDeviation = standardDeviation;
        this.unit = unit;
    }
    

    /**
	 * @param standardDeviation The standardDeviation to set.
	 */
	public void setStandardDeviation(int standardDeviation) {
		this.standardDeviation = standardDeviation;
	}


	/**
	 * @param time The time to set.
	 */
	public void setTime(Calendar time) {
		this.time = time;
	}


	/**
	 * @param unit The unit to set.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}


	/**
	 * 
	 */
	public EstimatedTime() {
		// FIXME Auto-generated constructor stub
	}


	public Calendar getTime() {
        return this.time;
    }
    public int getStandardDeviation() {
        return this.standardDeviation;
    }
    public String getUnit() {
        return this.unit;
    }

}
