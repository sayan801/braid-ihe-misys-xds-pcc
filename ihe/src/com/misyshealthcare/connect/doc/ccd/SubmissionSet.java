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

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 4, 2007
 */
public class SubmissionSet {
	CCDDocument[] documents = null;
	SubmissionSetMetaData submissionSetMetaData = null;
	/**
	 * @return the documents
	 */
	public CCDDocument[] getDocuments() {
		return documents;
	}
	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(CCDDocument[] documents) {
		this.documents = documents;
	}
	/**
	 * @return the submissionSetMetaData
	 */
	public SubmissionSetMetaData getSubmissionSetMetaData() {
		return submissionSetMetaData;
	}
	/**
	 * @param submissionSetMetaData the submissionSetMetaData to set
	 */
	public void setSubmissionSetMetaData(SubmissionSetMetaData submissionSetMetaData) {
		this.submissionSetMetaData = submissionSetMetaData;
	}
}
