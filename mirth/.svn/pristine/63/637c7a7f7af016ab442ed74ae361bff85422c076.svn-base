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

import com.misyshealthcare.connect.doc.ccd.CCDException;

/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer XDSMSData object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapXDSMSData {

	/**
	 * Convert XDSMSData into the ccd package's XDSMSData
	 * @param wsData
	 * @param data
	 * @throws CCDException
	 */
	public static void process(com.misyshealthcare.connect.doc.ccd.xsd.XDSMSData wsData, com.misyshealthcare.connect.doc.ccd.XDSMSData data) {
		if(wsData == null) return;
		
		data.setHistoryOfPresentIllness(wsData.getHistoryOfPresentIllness());
		data.setActiveProblems(MapSimpleProblems.process(wsData.getActiveProblems()));
		data.setResolvedProblems(MapSimpleProblems.process(wsData.getResolvedProblems()));
		data.setAllergies(MapAllergys.process(wsData.getAllergies()));
		data.setMedications(MapMedications.process(wsData.getMedications()));
		data.setPhysicalExams(wsData.getPhysicalExams());
		data.setReviewOfSystems(wsData.getReviewOfSystems());
		data.setPlanOfCares(wsData.getPlanOfCares());
		data.setMeasurements(MapMeasurements.process(wsData.getMeasurements()));
		data.setAdvanceDirectives(MapAdvanceDirectives.process(wsData.getAdvanceDirectives()));
		
		//data.setClinicalNotes(new MapClinicalNotes().process(wsData.getClinicalNotes()));
		data.setProcedures(MapProcedures.process(wsData.getProcedures()));
		data.setInsurances(new MapInsurances().process(wsData.getInsurances()));
		
		MapCCDData.process(wsData, data);
	}
}
