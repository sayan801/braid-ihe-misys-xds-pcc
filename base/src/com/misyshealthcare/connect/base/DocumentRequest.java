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
package com.misyshealthcare.connect.base;

/**
 * This is the container class for retrieving a document content from
 * an XDS repository.
 * 
 * @author Wenzhi Li
 * @version 2.1 Jan 23, 2008
 */
public class DocumentRequest {
	private String documentUniqueId;
	private String repositoryUniqueId;
	
	public DocumentRequest(String documentUniqueId, String repositoryUniqueId) {
		super();
		this.documentUniqueId = documentUniqueId;
		this.repositoryUniqueId = repositoryUniqueId;
	}
	/**
	 * @return the documentUniqueId
	 */
	public String getDocumentUniqueId() {
		return documentUniqueId;
	}
	/**
	 * @param documentUniqueId the documentUniqueId to set
	 */
	public void setDocumentUniqueId(String documentUniqueId) {
		this.documentUniqueId = documentUniqueId;
	}
	/**
	 * @return the repositoryUniqueId
	 */
	public String getRepositoryUniqueId() {
		return repositoryUniqueId;
	}
	/**
	 * @param repositoryUniqueId the repositoryUniqueId to set
	 */
	public void setRepositoryUniqueId(String repositoryUniqueId) {
		this.repositoryUniqueId = repositoryUniqueId;
	}
	
}
