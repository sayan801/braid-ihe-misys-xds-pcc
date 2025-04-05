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
package com.misyshealthcare.connect.doc.ccd.mapping;

import com.misyshealthcare.connect.base.clinicaldata.Code;
import com.misyshealthcare.connect.base.clinicaldata.Measurements;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer Measurements object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 11, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapMeasurements {

	/**
	 * Convert array of Measurements into the ccd package's Measurements
	 * @param wsMeasurements
	 * @return array of <code>Measurements</code>
	 */
	public static Measurements[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Measurements[] wsMeasurements) {
		if(wsMeasurements == null) return null;
		
		Measurements[] measurements = new Measurements[wsMeasurements.length];
		for(int measureCount = 0;measureCount < wsMeasurements.length; measureCount++){
			measurements[measureCount] = createMeasurement(wsMeasurements[measureCount]);
		}
		return measurements;
	}

	/**
	 * Convert Measurements into the ccd package's Measurements
	 * @param wsMeasurement
	 * @return <code>Measurements</code>
	 */
	public static Measurements createMeasurement(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Measurements wsMeasurement) {
		if(wsMeasurement == null) return null;
		
		Measurements measurements = new Measurements();
		measurements.setCode(MapCode.process(wsMeasurement.getCode()));
		com.misyshealthcare.connect.base.clinicaldata.xsd.Code[] wsCodes = wsMeasurement.getCodes(); 
		if(wsCodes != null && wsCodes.length > 0){
			Code[] codes = new Code[wsCodes.length];
			int codeCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Code wsCode : wsCodes){
				codes[codeCount++] = MapCode.process(wsCode);
			}
			measurements.setCodes(codes);
		}
		measurements.setComment(wsMeasurement.getComment());
		measurements.setDescription(wsMeasurement.getDescription());
		measurements.setEntryDateTime(wsMeasurement.getEntryDateTime());
		measurements.setLocation(wsMeasurement.getLocation());
		measurements.setPosition(wsMeasurement.getPosition());
		measurements.setSource(wsMeasurement.getSource());
		measurements.setUnitOfMeasure(wsMeasurement.getUnitOfMeasure());
		measurements.setValue(wsMeasurement.getValue());
		
		return measurements;
	}

}
