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
package com.misyshealthcare.connect.doc.ccd;

import java.io.ByteArrayInputStream;

import javax.mail.util.ByteArrayDataSource;


/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Nov 20, 2007
 */
public class CCDDocument {
	private String content = null;
	private MetaData metadata = null;
	private Error[] errors = null;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content; 
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the metadata
	 */
	public MetaData getMetadata() {
		return metadata;
	}
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}
	/**
	 * @return the errors
	 */
	public Error[] getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Error[] errors) {
		this.errors = errors;
	}
	
}
