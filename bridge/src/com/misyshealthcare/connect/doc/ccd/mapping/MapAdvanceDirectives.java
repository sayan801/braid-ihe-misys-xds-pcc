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

import com.misyshealthcare.connect.base.clinicaldata.AdvanceDirective;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer AdvanceDirective object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapAdvanceDirectives {

	/**
	 * Convert array of AdvanceDirectives into the ccd package's AdvanceDirectives
	 * @param wsAdvanceDirectives
	 * @return array of <code>com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective</code>
	 */
	public static AdvanceDirective[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective[] wsAdvanceDirectives) {
		
		if(wsAdvanceDirectives == null) return null;
		
		AdvanceDirective[] advanceDirectives = new AdvanceDirective[wsAdvanceDirectives.length];
		for(int directiveCount = 0; directiveCount < wsAdvanceDirectives.length;directiveCount++){
			advanceDirectives[directiveCount] = createAdvanceDirective(wsAdvanceDirectives[directiveCount]); 
		}
		return advanceDirectives;
	}

	/**
	 * Convert AdvanceDirective into the ccd package's AdvanceDirective
	 * @param wsAdvanceDirective
	 * @return <code>com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective</code>
	 */
	public static AdvanceDirective createAdvanceDirective(
			com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective wsAdvanceDirective) {
		if(wsAdvanceDirective == null)
			return null;
		AdvanceDirective directive = new AdvanceDirective(wsAdvanceDirective.getDescription(),MapCode.process(wsAdvanceDirective.getCode()), wsAdvanceDirective.getEffectiveDate(),wsAdvanceDirective.getStatus(),wsAdvanceDirective.getComments(),wsAdvanceDirective.getSource());
				
		return directive;
	}
}
