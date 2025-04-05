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
package com.misyshealthcare.connect.ihe.registry;

import java.util.ArrayList;
import java.util.List;

import com.misyshealthcare.connect.base.DocumentResponse;

//import org.apache.log4j.Logger;


/**
 * This class corresponds to an invalid response from an ebRIM registry.
 * 
 * @author Josh Flachsbart
 * @version 1.0 - Dec 13, 2006
 */
public class XmlErrorResponse  implements XdsResponse {
	
//	private static final Logger log = Logger.getLogger(XmlErrorResponse.class.getName());

	private String errorMessage = null;
	
	/**
	 * Create a new registry response object to pass back an error message.
	 * 
	 * @param errorMessage The problem with this registry response.
	 */
	public XmlErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	/**
	 * Informing that this is an error response.
	 * 
	 * @return True.
	 */
	public boolean isError() {
		return true;
	}
	
	/**
	 * Get the reason for the invalid response.
	 * 
	 * @return Description of the response.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Invalid response, didn't succeed.
	 * 
	 * @return False.
	 */
	public boolean isSuccess() {
		return false;
	}
	
	/**
	 * Invalid response always false.
	 * 
	 * @return False.
	 */
	public boolean isAdhocQueryResponse() {
		return false;
	}

	/**
	 * Get an empty list.  Invalid response produces no results.
	 * 
	 * @return An empty list.
	 */
	public List<XdsObjectReference> getQueryObjectReferences() {
		return new ArrayList<XdsObjectReference>();
	}
	
	/**
	 * Get an empty list.  Invalid response produces no results.
	 * 
	 * @return An empty list.
	 */
	public List<String> getQueryReferenceIds() {
		return new ArrayList<String>();
	}

	/**
	 * Get an empty list.  Invalid response produces no results.
	 * 
	 * @return An empty list.
	 */
	public List<XdsDocumentEntry> getQueryDocumentEntries() {
		return new ArrayList<XdsDocumentEntry>();
	}
	
	/**
	 * Get the xdsb:DocumentResponse instances returned in this response that
	 * correspond to DocumentResponse objects, if any.
	 *  
	 * @return An empty list.
	 */
	public List<DocumentResponse> getDocumentResponses() {
		return new ArrayList<DocumentResponse>();
	}	
}
