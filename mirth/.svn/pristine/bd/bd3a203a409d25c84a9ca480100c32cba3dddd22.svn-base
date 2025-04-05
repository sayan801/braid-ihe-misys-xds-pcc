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

import java.util.Calendar;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.doc.ccd.CCDException;
import com.misyshealthcare.connect.doc.ccd.EDReferralData;
import com.misyshealthcare.connect.doc.ccd.ProposedDisposition;
import com.misyshealthcare.connect.doc.ccd.TransportMode;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer EDReferralData object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapEDReferralData {
	
	/**
	 * Convert EDReferralData into the ccd package's EDReferralData
	 * @param wsEDR
	 * @param edRef
	 * @throws CCDException
	 */
	public static void process(com.misyshealthcare.connect.doc.ccd.xsd.EDReferralData wsEDR, EDReferralData edRef) {
		if(wsEDR == null) return;
		
		MapReferralSummaryData.process(wsEDR, edRef);
		edRef.setTransportMode(createTransportMode(wsEDR.getTransportMode()));
		edRef.setProposedDisposition(createDisposition(wsEDR.getProposedDisposition()));
	}
	
	/**
	 * Convert ProposedDisposition into the ccd package's ProposedDisposition
	 * @param proposedDisposition
	 * @return <code>ProposedDisposition</code>
	 */
	public static ProposedDisposition createDisposition(
			com.misyshealthcare.connect.doc.ccd.xsd.ProposedDisposition proposedDisposition) {
		if(proposedDisposition == null) return null;
		
		ProposedDisposition retDis = new ProposedDisposition();
		if(proposedDisposition.getDischargeDispositionCode() != null)
			retDis.setDischargeDispositionCode(SharedEnums.DischargeDispositionCode.valueOf(proposedDisposition.getDischargeDispositionCode().getValue()));
		retDis.setEffectiveTime(TypeTransformerReqMetaData.convertEffeciveTime(proposedDisposition.getEffectiveTime()));
		retDis.setEncounterDisposition(proposedDisposition.getEncounterDisposition());
		return retDis;
	}
	
	/**
	 * Convert TransportMode into the ccd package's TransportMode
	 * @param transportMode
	 * @return <code>TransportMode</code>
	 */
	public static TransportMode createTransportMode(
			com.misyshealthcare.connect.doc.ccd.xsd.TransportMode transportMode) {
		if(transportMode == null) return null;
		
		TransportMode retMode = new TransportMode();
		retMode.setEstimatedTimeOfArrival(convertEstimatedTimeofArrival(transportMode.getEstimatedTimeOfArrival()));
		if(transportMode.getTransportModeCode() != null)
			retMode.setTransportModeCode(SharedEnums.TransportModeCode.valueOf(transportMode.getTransportModeCode().getValue()));
		return retMode;
	}
	
	/**
	 * Convert EstimatedTime into the ccd package's EstimatedTime
	 * @param estimatedTimeOfArrival
	 * @return arrival time
	 */
	public static Calendar convertEstimatedTimeofArrival(
			com.misyshealthcare.connect.doc.ccd.xsd.EstimatedTime estimatedTimeOfArrival) {
		if(estimatedTimeOfArrival == null) return null;
		
		return estimatedTimeOfArrival.getTime();
	}
}
