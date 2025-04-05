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

import com.misyshealthcare.connect.base.clinicaldata.Immunization;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer Immunizations object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapImmunizations {

	/**
	 * Convert array of Immunizations into the ccd package's Immunizations
	 * @param wsImmunizations
	 * @return array of <code>Immunization</code>
	 */
	public static Immunization[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[] wsImmunizations) {
		if (wsImmunizations == null) return null;

		Immunization[] immunizations = new Immunization[wsImmunizations.length];
		for (int count = 0; count < wsImmunizations.length; count++) {
			immunizations[count] = createImmunization(wsImmunizations[count]);
		}
		return immunizations;
	}

	/**
	 * Convert Immunization into the ccd package's Immunization
	 * @param wsImmunization
	 * @return <code>Immunization</code>
	 */
	public static Immunization createImmunization(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization wsImmunization) {
		if(wsImmunization == null) return null;
		
		Immunization immunization = new Immunization();
		immunization.setAdministeredBy(wsImmunization.getAdministeredBy());
		immunization.setComment(wsImmunization.getComment());
		immunization.setDateTime(wsImmunization.getDateTime());
		immunization.setImmunizationType(wsImmunization.getImmunizationType());
		immunization.setReference(wsImmunization.getReference());
		immunization.setSource(wsImmunization.getSource());
		immunization.setCode(MapCode.process(wsImmunization.getCode()));
		immunization.setDose(TypeTransfromerRequest.convertDose(wsImmunization.getDose()));
		immunization.setRouteCode(MapCode.process(wsImmunization.getRouteCode()));
		
		return immunization;
	}
}
