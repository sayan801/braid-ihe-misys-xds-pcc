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
import com.misyshealthcare.connect.doc.ccd.DischargeSummaryData;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer DischargeSummaryData object from webservice request data object
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapDischargeSummaryData {
	/**
	 * Convert DischargeSummaryData into the ccd package's DischargeSummaryData
	 * @param wsDisSum
	 * @param disSum
	 * @throws CCDException
	 */
	public static void process(
			com.misyshealthcare.connect.doc.ccd.xsd.DischargeSummaryData wsDisSum, DischargeSummaryData disSum) {
		if(wsDisSum == null) return;
		
		MapXDSMSData.process(wsDisSum, disSum);
		
		disSum.setDischargeDiagnosis(MapSimpleProblems.process(wsDisSum.getDischargeDiagnosis()));
		disSum.setAdmittingDiagnosis(MapSimpleProblems.process(wsDisSum.getAdmittingDiagnosis()));
		disSum.setHospitalCourse(wsDisSum.getHospitalCourse());
		disSum.setAdmissionDate(wsDisSum.getAdmissionDate());
		disSum.setDischargeDate(wsDisSum.getDischargeDate());
		disSum.setAdministeredMedications(MapMedications.process(wsDisSum.getAdministeredMedications()));
		disSum.setAdmissionMedications(MapMedications.process(wsDisSum.getAdmissionMedications()));
		disSum.setDischargeDiets(wsDisSum.getDischargeDiets());
	}

}
