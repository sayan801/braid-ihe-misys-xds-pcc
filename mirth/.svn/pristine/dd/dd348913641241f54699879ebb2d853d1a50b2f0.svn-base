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

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.DocumentResponse;

/**
 * This classes handles xml RegistryResponse with XML Axiom and 
 * ebXml Registry v3.0.
 * 
 * @author Wenzhi Li
 * @version 2.1 Jan 17, 2008
 */
public class XmlAxiomRegistryResponse  implements XdsResponse {
	
	private static final Logger log = Logger.getLogger(XmlAxiomRegistryResponse.class.getName());
    private static final String SUCCESS_STATUS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";
    private static final String FAILURE_STATUS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Failure";

    private OMElement root = null;
	
	/**
	 * Create a new registry response object to wrap the XML
	 * Axiom returned in a registry response message.
	 * 
	 * @param xml The XML Axiom element of the RegsitryResponse.
	 */
	XmlAxiomRegistryResponse(OMElement xml) {
		root = xml;
	}
	
	/**
	 * Check whether this registry response indicates success.
	 * 
	 * @return True if the registry operation generating this response executed without error.
	 */
	public boolean isSuccess() {
		// See if our "status" attribute is "Success"
		String value = root.getAttributeValue(new QName("status"));
		return (value != null) && value.equals(SUCCESS_STATUS);
	}
	
	/**
	 * Check whether this response is an AdHocQUery response.
	 * 
	 * @return True if this response is an AdHocQuery response.
	 */
	public boolean isAdhocQueryResponse() {
		return false;
	}

	/**
	 * Get the rim:ObjectRef instances returned in this response, if any.
	 * These instances will be wrapped as XdsObjectReference registry
	 * object classes.
	 * 
	 * @throws UnsupportedOperationException XmlRetrieveDocumentSetResponse does 
	 * not contain QueryObjectRefereences
	 */
	public List<XdsObjectReference> getQueryObjectReferences() {
		throw new UnsupportedOperationException("RegistryResponse does not contain QueryObjectReference");
	}
	
	/**
	 * Get the "id" component of the rim:ObjectRef instances returned in 
	 * this response, if any.  This call is equivalent to getting all of the
	 * XdsObjectReference objects in this query response and then asking each
	 * for its Id.
	 * 
     * @throws UnsupportedOperationException RegistryResponse does not contain QueryReferenceId
	 */
	public List<String> getQueryReferenceIds() {
		throw new UnsupportedOperationException("RegistryResponse does not contain QueryReferenceId");
	}

	/**
	 * Get the rim:ExtrinsicObject instances returned in this response that
	 * correspond to XDSDocumentEntry objects, if any.  These instances will 
	 * be wrapped as XdsDocumentEntry registry object classes.
	 * 
     * @throws UnsupportedOperationException RegistryResponse does not contain DocumentEntry
	 */
	public List<XdsDocumentEntry> getQueryDocumentEntries() {
		throw new UnsupportedOperationException("RegistryResponse does not contain DocumentEntry");
	}
	
	/**
	 * Get the xdsb:DocumentResponse instances returned in this response that
	 * correspond to DocumentResponse objects, if any.
	 *  
     * @throws  UnsupportedOperationException("RegistryResponse does not contain DocumentEntry");
	 */
	public List<DocumentResponse> getDocumentResponses() {
		throw new UnsupportedOperationException("RegistryResponse does not contain DocumentResponse");
	}
	
	/**
	 * Informing that this is not an error response.
	 * 
	 * @return <code>true</code> if message fails.
	 */
	public boolean isError() {
		// See if our "status" attribute is "Failure"
		String value = root.getAttributeValue(new QName("status"));
		return (value != null) && value.equals(FAILURE_STATUS);
	}
	
	/**
	 * Get the error message if there is an error., there is no error.
	 * 
	 * @return the error string. Return null if there is no error. 
	 */
	public String getErrorMessage() {
		String ret = null;
		Iterator errorlist = root.getChildrenWithName(new QName(XmlAxiomRegistry.XDS_REGISTRY_V3_NAMESPACE, "RegistryErrorList")); 
		for ( ;errorlist.hasNext();) {
			OMElement errElem = (OMElement)errorlist.next();
			Iterator errors = errElem.getChildrenWithName(new QName(XmlAxiomRegistry.XDS_REGISTRY_V3_NAMESPACE, "RegistryError"));
			for( ;errors.hasNext();){
				OMElement error = (OMElement)errors.next();
				String errorCode = error.getAttributeValue(new QName("errorCode"));
				String errorContext = error.getAttributeValue(new QName("codeContext"));
				if (ret == null)
					ret = errorCode + ": " + errorContext;
				else 
					ret += "; " + errorCode + ": " + errorContext;
			}
		}
		return ret;
	}

}
