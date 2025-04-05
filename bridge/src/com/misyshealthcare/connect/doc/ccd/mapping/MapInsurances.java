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

import com.misyshealthcare.connect.base.clinicaldata.EffectiveDates;
import com.misyshealthcare.connect.base.demographicdata.Insurance;
import com.misyshealthcare.connect.base.demographicdata.SubscriberNameAddress;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer CCDDocument object from webservice 
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 18, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapInsurances {

	/**
	 * Convert array of Insurances into the demographicdata package's Insurances
	 * @param wsInsurances
	 * @return <code>Insurance</code>
	 */
	public Insurance[] process(com.misyshealthcare.connect.base.demographicdata.xsd.Insurance[] wsInsurances) {
		if(wsInsurances == null) return null;
		
		Insurance[] insurances = new Insurance[wsInsurances.length];
		for(int count = 0; count < wsInsurances.length; count++){
			insurances[count] = createInsurance(wsInsurances[count]);
		}
		
		return insurances;
	}

	/**
	 * Convert Insurance into the demographicdata package's Insurance
	 * @param wsInsurance
	 * @return <code>Insurance</code>
	 */
	public Insurance createInsurance(
			com.misyshealthcare.connect.base.demographicdata.xsd.Insurance wsInsurance) {
		if(wsInsurance == null) return null;
		
		Insurance insurance = new Insurance();
		insurance.setAddress(TypeTransformerReqMetaData.convertAddress(wsInsurance.getAddress()));
		insurance.setEffectiveDates(convertEffeciveDates(wsInsurance.getEffectiveDates()));
		insurance.setGroupNumber(wsInsurance.getGroupNumber());
		insurance.setName(wsInsurance.getName());
		insurance.setPlan(wsInsurance.getPlan());
		insurance.setSubscriberDateOfBirth(wsInsurance.getSubscriberDateOfBirth());
		insurance.setSubscriberMemberId(wsInsurance.getSubscriberMemberId());
		insurance.setSubscriberNameAddess(createSubScriberNameAddress(wsInsurance.getSubscriberNameAddess()));
		insurance.setSubscriberRelationToPatient(wsInsurance.getSubscriberRelationToPatient());
		insurance.setType(wsInsurance.getType());
		
		return insurance;
	}

	/**
	 * Convert SubscriberNameAddress into the demographicdata package's SubscriberNameAddress
	 * @param wsSubscriberNameAddess
	 * @return <code>SubscriberNameAddress</code>
	 */
	public SubscriberNameAddress createSubScriberNameAddress(
			com.misyshealthcare.connect.base.demographicdata.xsd.SubscriberNameAddress wsSubscriberNameAddess) {
		if(wsSubscriberNameAddess == null) return null;
		
		SubscriberNameAddress subcriberNameAddress = new SubscriberNameAddress();
		subcriberNameAddress.setAddress(TypeTransformerReqMetaData.convertAddress(wsSubscriberNameAddess.getAddress()));
		subcriberNameAddress.setNameFirst(wsSubscriberNameAddess.getNameFirst());
		subcriberNameAddress.setNameLast(wsSubscriberNameAddess.getNameLast());
		subcriberNameAddress.setNameMiddle(wsSubscriberNameAddess.getNameMiddle());
		subcriberNameAddress.setNameSuffix(wsSubscriberNameAddess.getNameSuffix());
		subcriberNameAddress.setNameTitle(wsSubscriberNameAddess.getNameTitle());
		
		return subcriberNameAddress;
	}

	/**
	 * Convert EffectiveDates into the demographicdata package's EffectiveDates
	 * @param wsEffectiveDates
	 * @return <code>EffectiveDates</code>
	 */
	public EffectiveDates convertEffeciveDates(
			com.misyshealthcare.connect.base.clinicaldata.xsd.EffectiveDates wsEffectiveDates) {
		if(wsEffectiveDates == null) return null;
		
		EffectiveDates dates = new EffectiveDates();
		dates.setEnd(wsEffectiveDates.getEnd());
		dates.setStart(wsEffectiveDates.getStart());
		
		return dates;
	}

}
