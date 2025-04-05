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

import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.xsd.Error;
import com.misyshealthcare.connect.doc.ccd.xsd.SeverityType;

/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer CCDDocument object from manipulation layer 
 * 							to webservice response data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapCCDDocument {
	private static Logger log = Logger.getLogger(MapCCDDocument.class);
	
	/**
	 * Convert CCDDocument into the xsd package's CCDDocument
	 * @param ccdDoc
	 * @return <code>CCDDocument</code>
	 */
	public static CCDDocument process(
			com.misyshealthcare.connect.doc.ccd.CCDDocument ccdDoc) {
		if(ccdDoc == null) return null;
		
		CCDDocument wsCCDDoc = new CCDDocument();
		wsCCDDoc.setContent(createDataHandler(ccdDoc.getContent()));
		wsCCDDoc.setErrors(convert(ccdDoc.getErrors()));
		wsCCDDoc.setMetadata(TypeTransformerResMetaData.convertMetaData(ccdDoc.getMetadata()));
		return wsCCDDoc;
	}
	
	/**
	 * Convert array of Errors into the xsd package's Errors
	 * @param errors
	 * @return <code>Error</code>
	 */
	public static Error[] convert(com.misyshealthcare.connect.doc.ccd.Error[] errors) {
		if(errors == null) return null;
		
		Error[]	wsErrors = new Error[errors.length];
		for(int i = 0; i<errors.length; i++){
			wsErrors[i] = convert(errors[i]);
		}
		return wsErrors;
	}
	
	/**
	 * Convert Error into the xsd package's Error
	 * @param error
	 * @return <code>Error</code>
	 */
	public static Error convert(com.misyshealthcare.connect.doc.ccd.Error error) {
		if(error == null) return null;
		
		Error wsError= new Error();
		wsError.setMessage(error.getMessage());
		if(error.getSeverityType() != null)
			wsError.setSeverityType(SeverityType.Factory.fromValue(error.getSeverityType().toString()));
		return wsError;
	}
	
	/**
	 * Convert DataHandler into the xsd package's DataHandler
	 * @param content
	 * @return <code>DataHandler</code>
	 */
	public static DataHandler createDataHandler(String content) {
		if(content == null) return null;
		
		DataSource dataSource = null;
		try {
			dataSource = new ByteArrayDataSource(content,"text/xml");
		} catch (IOException e) {
			log.error("Fail to convert String to DataHandler",e);
		}
		
		return new DataHandler(dataSource);
	}

}
