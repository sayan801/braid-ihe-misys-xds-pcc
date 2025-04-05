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

import com.misyshealthcare.connect.base.clinicaldata.Allergy;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer Allergy object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapAllergys {

	/**
	 * Convert array of Allergys into the ccd package's Allergys
	 * @param wsAllergys
	 * @return array of <code>com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy</code>
	 */
	public static Allergy[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy[] wsAllergys) {
		if(wsAllergys == null) return null;
		
		Allergy[] allergys = new Allergy[wsAllergys.length];
		for(int allergyCount = 0;allergyCount < wsAllergys.length; allergyCount++){
			allergys[allergyCount] = createAllergy(wsAllergys[allergyCount]);
		}
		return allergys;
	}

	/**
	 * Convert Allergy into the ccd package's Allergy
	 * @param wsAllergy
	 * @return <code>com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy</code>
	 */
	public static Allergy createAllergy(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy wsAllergy) {
		if(wsAllergy == null)
			return null;
		
		Allergy allergy = new Allergy();
		allergy.setAgent(wsAllergy.getAgent());
		allergy.setCode(MapCode.process(wsAllergy.getCode()));
		allergy.setReaction(wsAllergy.getReaction());
		allergy.setReportDate(wsAllergy.getReportDate());
		allergy.setSource(wsAllergy.getSource());
		return allergy;
	}
	

}
