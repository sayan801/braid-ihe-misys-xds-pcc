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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.SubmissionSetMetaData;
import com.misyshealthcare.connect.doc.ccd.Error.SeverityType;
import com.misyshealthcare.connect.doc.ccd.xsd.Error;
import com.misyshealthcare.connect.doc.ccd.xsd.SubmissionSet;

/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer SubmissionSet object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 21, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapSubmissionSet {
	private static Logger log = Logger.getLogger(MapSubmissionSet.class);
	
	/**
	 * Convert SubmissionSet into the ccd package's SubmissionSet
	 * @param wsSubmissionSet
	 * @return <code>SubmissionSet</code>
	 */
	public static com.misyshealthcare.connect.doc.ccd.SubmissionSet process(
			SubmissionSet wsSubmissionSet) {
		if(wsSubmissionSet == null) return null;
		
		com.misyshealthcare.connect.doc.ccd.SubmissionSet set = new com.misyshealthcare.connect.doc.ccd.SubmissionSet();
		set.setDocuments(convertCCDDocuments(wsSubmissionSet.getDocuments()));
		set.setSubmissionSetMetaData(convertSubmissionMetaData(wsSubmissionSet.getSubmissionSetMetaData()));
		
		return set;
	}

	/**
	 * Convert SubmissionMetaData into the ccd package's SubmissionMetaData
	 * @param wsSubmissionSetMetaData
	 * @return <code>SubmissionSetMetaData</code>
	 */
	public static SubmissionSetMetaData convertSubmissionMetaData(
			com.misyshealthcare.connect.doc.ccd.xsd.SubmissionSetMetaData wsSubmissionSetMetaData) {
		if(wsSubmissionSetMetaData == null) return null;
		
		SubmissionSetMetaData metaData = new SubmissionSetMetaData();
		metaData.setComments(wsSubmissionSetMetaData.getComments());
		if(wsSubmissionSetMetaData.getContentCode() != null)
			metaData.setContentCode(XdsContentCode.valueOf(wsSubmissionSetMetaData.getContentCode().getValue()));
		metaData.setTitle(wsSubmissionSetMetaData.getTitle());
		
		return metaData;
	}

	/**
	 * Convert array of CCDDocuments into the ccd package's CCDDocuments
	 * @param wsDocuments
	 * @return <code>CCDDocument[]</code>
	 */
	public static CCDDocument[] convertCCDDocuments(
			com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument[] wsDocuments) {
		if(wsDocuments == null) return null;
		
		CCDDocument[] docs = new CCDDocument[wsDocuments.length];
		for(int count = 0; count < wsDocuments.length; count++){
			docs[count] = convertCCDDocument(wsDocuments[count]);
		}
		return docs;
	}

	/**
	 * Convert CCDDocument into the ccd package's CCDDocument
	 * @param wsDocument
	 * @return <code>CCDDocument</code>
	 */
	public static CCDDocument convertCCDDocument(
			com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument wsDocument) {
		if(wsDocument == null) return null;
		
		CCDDocument doc = new CCDDocument();
		DataHandler wsContent = wsDocument.getContent();
		if(wsContent != null){
			InputStream stream;
			try {
				stream = wsContent.getInputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(stream));
				StringBuffer buffer = new StringBuffer();
				String line;
				while ((line = in.readLine()) != null) {
					buffer.append(line);
				}
				doc.setContent(buffer.toString());
			} catch (IOException e) {
				log.error("Fail to read content", e);
			}
		}
		
		doc.setErrors(convertErrors(wsDocument.getErrors()));
		doc.setMetadata(MapMetaData.process(wsDocument.getMetadata()));
		
		return doc;
	}

	/**
	 * Convert array of Errors into the ccd package's Errors
	 * @param wsErrors
	 * @return <code>Error[]</code>
	 */
	public static com.misyshealthcare.connect.doc.ccd.Error[] convertErrors(
			Error[] wsErrors) {
		if(wsErrors == null) return null;
		
		com.misyshealthcare.connect.doc.ccd.Error[] errors = new com.misyshealthcare.connect.doc.ccd.Error[wsErrors.length];
		for(int count = 0; count < wsErrors.length; count++){
			errors[count] = convertError(wsErrors[count]);
		}
		return errors;
	}

	/**
	 * Convert Error into the ccd package's Error
	 * @param wsError
	 * @return <code>Error</code>
	 */
	public static com.misyshealthcare.connect.doc.ccd.Error convertError(
			Error wsError) {
		if(wsError == null) return null;
		
		SeverityType type = null;
		if(wsError.getSeverityType() != null)
			type = SeverityType.valueOf(wsError.getSeverityType().getValue());
		
		com.misyshealthcare.connect.doc.ccd.Error error = new com.misyshealthcare.connect.doc.ccd.Error(type,wsError.getMessage());
		
		return error;
	}

}
