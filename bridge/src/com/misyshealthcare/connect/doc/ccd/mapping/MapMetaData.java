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
import com.misyshealthcare.connect.doc.ccd.MetaData;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer MetaData object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapMetaData {

	/**
	 * Convert MetaData into the ccd package's MetaData
	 * @param wsMetadata
	 * @return <code>MetaData</code>
	 */
	public static MetaData process(
			com.misyshealthcare.connect.doc.ccd.xsd.MetaData wsMetadata) {
		if(wsMetadata == null) return null;
		
		MetaData data = new MetaData();
		data.setAuthors(TypeTransformerReqMetaData.convertAuthors(wsMetadata.getAuthors()));
		data.setComment(wsMetadata.getComment());
		data.setConfidentialityCodes(TypeTransformerReqMetaData.convertConfidentialityCodes(wsMetadata.getConfidentialityCodes()));
		data.setCreationTime(wsMetadata.getCreationTime());
		data.setClassCode(TypeTransformerReqMetaData.convertClassCode(wsMetadata.getClassCode()));
		data.setComment(wsMetadata.getComment());
		data.setFacilityTypeCode(wsMetadata.getFacilityTypeCode());
		data.setLanguageCode(wsMetadata.getLanguageCode());
		if(wsMetadata.getMimeType() != null && wsMetadata.getMimeType().getValue() != null)
			data.setMimeType(SharedEnums.MimeTypes.valueOf(wsMetadata.getMimeType().getValue()));
		data.setPracticeSettingCode(wsMetadata.getPracticeSettingCode());
		data.setServiceStartTime(wsMetadata.getServiceStartTime());
		data.setServiceStopTime(wsMetadata.getServiceStopTime());
		data.setSourcePatientId(TypeTransformerReqMetaData.convertId(wsMetadata.getSourcePatientId()));
		data.setTypeCode(TypeTransformerReqMetaData.convertTypeCode(wsMetadata.getTypeCode()));
		data.setTitle(wsMetadata.getTitle());
		data.setSourcePatientInfo(TypeTransformerReqMetaData.convertSourcePatientInfo(wsMetadata.getSourcePatientInfo()));
		data.setPatientContacts(TypeTransformerReqMetaData.creatPatientContacts(wsMetadata.getPatientContacts()));
		data.setLegalAutheticator(TypeTransformerReqMetaData.convertParticipant(wsMetadata.getLegalAutheticator()));
		data.setEventCodes(wsMetadata.getEventCodes());
		data.setLegalAutheticator(TypeTransformerReqMetaData.convertParticipant(wsMetadata.getLegalAutheticator()));
		data.setCustodian(TypeTransformerReqMetaData.convertOrganization(wsMetadata.getCustodian()));
		data.setPerformers(TypeTransformerReqMetaData.convertPerformers(wsMetadata.getPerformers()));
		data.setEncounters(TypeTransformerReqMetaData.convertEncounters(wsMetadata.getEncounters()));
		data.setDataEnterers(TypeTransformerReqMetaData.convertDataEnterers(wsMetadata.getDataEnterers()));
		data.setUniqueDocumentId(TypeTransformerReqMetaData.convertId(wsMetadata.getUniqueDocumentId()));
		data.setHomeSystemId(wsMetadata.getHomeSystemId());
		if(wsMetadata.getFormatCode() != null)
			data.setFormatCode(SharedEnums.XdsFormatCode.valueOf(wsMetadata.getFormatCode().getValue()));
		return data;
	}
}
