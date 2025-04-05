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

import java.util.Collection;

import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;

/**
 * This class represents a consumer of IHE documents.  Typically
 * all document consumers will be external IHE sources.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 30, 2005
 */
public interface IDocumentConsumer {
	
	/**
	 * Start this document consumer.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();
	
	/**
	 * Stop this document consumer.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();
	
	/**
	 * Submit a new document to this consumer.
	 * 
	 * @param document The complete document to submit, metadata and content
	 * @param description Description of this document
	 * @param contentCode The content code for this document
	 * @throws DocumentException When this consumer has a problem submitting the document
	 */
	public void submitDocument(Document document, String description, XdsContentCode contentCode) throws DocumentException;

	/**
	 * Submit a set of documents to this consumer.
	 * 
	 * @param documents The collection of documents to submit, metadata and content
	 * @param description Description of this document set
	 * @param contentCode The content code for this document set
	 * @throws DocumentException When this consumer has a problem submitting the documents
	 */
	public void submitDocuments(Collection<Document> documents, String description, XdsContentCode contentCode) throws DocumentException;

}
