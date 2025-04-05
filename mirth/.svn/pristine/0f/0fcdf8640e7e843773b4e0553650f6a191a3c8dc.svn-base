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

import com.misyshealthcare.connect.base.clinicaldata.Medication;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer Medication object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 11, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapMedications {

	/**
	 * Convert array of Medications into the ccd package's Medications
	 * @param wsMedications
	 * @return array of <code>Medication</code>
	 */
	public static Medication[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Medication[] wsMedications) {
		Medication[] medications = null;
		if(wsMedications != null && wsMedications.length > 0){
			medications = new Medication[wsMedications.length];
			int medicationCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Medication wsMedication : wsMedications){
				medications[medicationCount++] = createMedication(wsMedication);
			}
		}
		return medications;
	}

	/**
	 * Convert Medication into the ccd package's Medication
	 * @param wsMedication
	 * @return <code>Medication</code>
	 */
	public static Medication createMedication(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Medication wsMedication) {
		if(wsMedication == null)
			return null;
		Medication medication = new Medication();
		medication.setCode(MapCode.process(wsMedication.getCode()));
		medication.setDate(wsMedication.getDate());
		medication.setDuration(wsMedication.getDuration());
		medication.setLastAdministrationDate(wsMedication.getLastAdministrationDate());
		medication.setProblemCode(MapCode.process(wsMedication.getProblemCode()));
		medication.setProductName(wsMedication.getProductName());
		medication.setRefills(wsMedication.getRefills());
		medication.setSig(wsMedication.getSig());
		medication.setSource(wsMedication.getSource());
		medication.setStatus(wsMedication.getStatus());
		medication.setDose(TypeTransfromerRequest.convertDose(wsMedication.getDose()));
		medication.setFrequency(wsMedication.getFrequency());
		medication.setRouteCode(MapCode.process(wsMedication.getRouteCode()));
		
		return medication;
	}
}
