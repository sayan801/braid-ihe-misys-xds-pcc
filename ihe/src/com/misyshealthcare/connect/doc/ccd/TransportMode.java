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

import com.misyshealthcare.connect.base.SharedEnums;

/**
 * The data container for transport mode and estimated time of arrival.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 24, 2006
 */
public class TransportMode {
    private SharedEnums.TransportModeCode transportModeCode = null;
    private Calendar estimatedTimeOfArrival = null;

    /**
	 * 
	 */
	public TransportMode() {
		// FIXME Auto-generated constructor stub
	}
	public TransportMode(SharedEnums.TransportModeCode mode, Calendar arrivalTime) {
        this.transportModeCode = mode;
        this.estimatedTimeOfArrival = arrivalTime;
    }
    public SharedEnums.TransportModeCode getTransportModeCode() {
        return this.transportModeCode;
    }
    public Calendar getEstimatedTimeOfArrival() {
        return this.estimatedTimeOfArrival;
    }
	/**
	 * @param estimatedTimeOfArrival The estimatedTimeOfArrival to set.
	 */
	public void setEstimatedTimeOfArrival(Calendar estimatedTimeOfArrival) {
		this.estimatedTimeOfArrival = estimatedTimeOfArrival;
	}
	/**
	 * @param transportModeCode The transportModeCode to set.
	 */
	public void setTransportModeCode(SharedEnums.TransportModeCode transportModeCode) {
		this.transportModeCode = transportModeCode;
	}

}
