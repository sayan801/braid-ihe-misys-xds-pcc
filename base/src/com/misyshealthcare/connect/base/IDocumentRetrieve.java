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

import java.util.List;

/**
 * This interface defines functions for retrieving 
 * document contents from an XDS repository. 
 * 
 * @author Wenzhi Li
 * @version 2.1 Jan 23, 2008
 */
public interface IDocumentRetrieve {
	/**
	 * Start this document retriever.  Do any initialization and logging that
	 * might be needed.
	 */
	public void start();
	
	/**
	 * Stop this document retriever.  Do any deinitialization and logging that
	 * might be needed.
	 *
	 */
	public void stop();	
	/**
	 * Get the contents of the supplied documents from document repository.
	 * 
	 * @return The DocumentResponse representing the document content.
	 * @throws DocumentException if retrieving document fails
	 */
	public List<DocumentResponse> retrieveDocumentContents(List<DocumentRequest> requests) throws DocumentException;
	
}
