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

import java.util.List;

import com.misyshealthcare.connect.base.DocumentResponse;

/**
 * This interface represents a Xml response.
 *
 * @author Wenzhi Li
 * @version 2.1, Dec 12, 2006
 * @see XmlRegistryResponse
 * @see XmlAdhocQueryResponse
 */
public interface XdsResponse {

	/**
	 * Use to determine if this respose is success. 
	 * @return True if the response is successful.
	 */
	public boolean isSuccess();	
	/**
	 * Use to determine if there was a problem with the response.
	 * @return True if there was an error in the response submission.
	 */
	public boolean isError();
	
	/**
	 * Use to get an error message describing the problem with the response.
	 * @return A description of the problem with this response.  Null if not error.
	 */
	public String getErrorMessage();
	
	/**
     * Check whether this response is an AdHocQUery response.
     *
     * @return True if this response is an AdHocQuery response.
     */
    public boolean isAdhocQueryResponse() ;

    /**
     * Get the rim:ObjectRef instances returned in this response, if any.
     * These instances will be wrapped as XdsObjectReference registry
     * object classes.
     *
     * @return The registry object references returned in this query response
     */
    public List<XdsObjectReference> getQueryObjectReferences();

    /**
     * Get the "id" component of the rim:ObjectRef instances returned in
     * this response, if any.  This call is equivalent to getting all of the
     * XdsObjectReference objects in this query response and then asking each
     * for its Id.
     *
     * @return The registry object ids returned in this query response
     */
    public List<String> getQueryReferenceIds();

    /**
     * Get the rim:ExtrinsicObject instances returned in this response that
     * correspond to XDSDocumentEntry objects, if any.  These instances will
     * be wrapped as XdsDocumentEntry registry object classes.
     *
     * @return The XdsDocumentEntry instances returned in this query response
     */
    public List<XdsDocumentEntry> getQueryDocumentEntries();
    
	/**
	 * Get the xdsb:DocumentResponse instances returned in this response that
	 * correspond to DocumentResponse objects, if any.
	 *  
     * @return a List of DocumentResponse objects corresponding to DocumentResponse element
	 */
	public List<DocumentResponse> getDocumentResponses();

}
