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

import com.misyshealthcare.connect.base.SharedEnums;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 4, 2007
 */
public class SubmissionSetMetaData {
	private SharedEnums.XdsContentCode contentCode = null;
	private String comments = null;
	private String title = null;
	/**
	 * @return the contentCode
	 */
	public SharedEnums.XdsContentCode getContentCode() {
		return contentCode;
	}
	/**
	 * @param contentCode the contentCode to set
	 */
	public void setContentCode(SharedEnums.XdsContentCode contentCode) {
		this.contentCode = contentCode;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
