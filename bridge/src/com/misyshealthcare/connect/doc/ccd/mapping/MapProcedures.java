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

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.clinicaldata.Procedure;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer Procedures object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 11, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapProcedures {

	/**
	 * Convert array of Procedures into the ccd package's Procedures
	 * @param wsProcedures
	 * @return <code>Procedure</code>
	 */
	public static Procedure[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure[] wsProcedures) {
		Procedure[] procedures = null;
		if(wsProcedures != null && wsProcedures.length > 0){
			procedures = new Procedure[wsProcedures.length];
			int procedureCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure wsProcedure : wsProcedures){
				procedures[procedureCount++] = createProcedure(wsProcedure);
			}
		}
		return procedures;
	}

	/**
	 * Convert Procedure into the ccd package's Procedure
	 * @param wsProcedure
	 * @return <code>Procedure</code>
	 */
	public static Procedure createProcedure(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure wsProcedure) {
		if(wsProcedure == null) return null;
		
		Procedure procedure = new Procedure();
		procedure.setCode(MapCode.process(wsProcedure.getCode()));
		procedure.setDate(wsProcedure.getDate());
		procedure.setName(wsProcedure.getName());
		procedure.setSource(wsProcedure.getSource());
		if(wsProcedure.getStatusCode() != null)
				procedure.setStatusCode(SharedEnums.StatusCode.valueOf(wsProcedure.getStatusCode().getValue()));
		
		return procedure;
	}
	

}
