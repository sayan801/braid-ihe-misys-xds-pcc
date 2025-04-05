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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.doc.ccd.CCDException;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentData;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentData.MediaType;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer ScannedDocumentData  object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 12, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapScannedDocumentData {
	private static Logger log = Logger.getLogger(MapScannedDocumentData.class);
	private static String tempFolder = System.getProperty("user.home")+File.separator+"IHECCD"+File.separator;
	/**
	 * Convert ScannedDocumentData into the ccd package's ScannedDocumentData
	 * @param wsScanDoc
	 * @param scanDoc
	 * @throws CCDException
	 */
	public static void process(com.misyshealthcare.connect.doc.ccd.xsd.ScannedDocumentData wsScanDoc, ScannedDocumentData scanDoc) {
		if(wsScanDoc == null) {
			return;
		}
			
		scanDoc.setFileContent(getFileContent(wsScanDoc.getFileContent()));
		scanDoc.setMediaType(convertMediaType(wsScanDoc.getMediaType()));
		scanDoc.setMetadata(MapMetaData.process(wsScanDoc.getMetadata()));
	}
	
	/**
	 * Get content from DataHandler in the form of InputStream
	 * @param fileContent
	 * @return <code>InputStream</code>
	 * @throws CCDException 
	 */
	public static InputStream getFileContent(DataHandler fileContent) {
		InputStream content = null;
		try {
			if(fileContent != null)
				content = fileContent.getInputStream();
			if(log.isDebugEnabled()){
				Calendar cal = Calendar.getInstance();
				File file = new File(tempFolder+"ScannedDocument"+cal.get(Calendar.HOUR)+"-"+cal.get(Calendar.MINUTE)+"-"+cal.get(Calendar.SECOND)+".pdf");
				//create parent fold first
	            String parent = file.getParent();
	            File parentDir = new File(parent);
	            if(!parentDir.exists()) parentDir.mkdirs();
	            
				FileOutputStream fos = new FileOutputStream(file);
				int length = content.available();
				log.info("file lenght="+length);
				fileContent.writeTo(fos);
				fos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Fail to read file content from request:",e);
		}
		return content;
	}

	/**
	 * Convert MediaType into the ccd package's MediaType
	 * @param wsMediaType
	 * @return <code>MediaType</code>
	 */
	public static MediaType convertMediaType(
			com.misyshealthcare.connect.doc.ccd.xsd.MediaType wsMediaType) {
		if(wsMediaType == null) return null;
		
		String type = wsMediaType.getValue();
		MediaType  mediaType = null;
		if(type == null)
			log.error("Media type is null");
		else
			mediaType = ScannedDocumentData.MediaType.valueOf(type);
		
		return mediaType;
	}
}
