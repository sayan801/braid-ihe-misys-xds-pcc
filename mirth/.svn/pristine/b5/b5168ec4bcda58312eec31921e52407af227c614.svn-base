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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMText;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.DocumentResponse;
import com.misyshealthcare.connect.util.Base64EncoderDecoder;


/**
 * This class is the response for RetrieveDocumentSetResponse in XDS.b retrieving 
 * document set transaction.
 * <p>
 * An example of RetrieveDocumentSetResponse:
 * <pre>
 * <xdsb:RetrieveDocumentSetResponse xmlns:xdsb="urn:ihe:iti:xds-b:2007">
 *   <rs:RegistryResponse xmlns:rs="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0" status="urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success"/>
 *   <xdsb:DocumentResponse>
 *       <xdsb:RepositoryUniqueId>1.19.6.24.109.42.1</xdsb:RepositoryUniqueId>
 *       <xdsb:DocumentUniqueId>1201118855762.1</xdsb:DocumentUniqueId>
 *       <xdsb:mimeType>text/xml</xdsb:mimeType>
 *       <xdsb:Document>PD94bsbtE</xdsb:Document>
 *   </xdsb:DocumentResponse>
 * </xdsb:RetrieveDocumentSetResponse>
 * </pre>
 * 
 * 
 * @author Wenzhi Li
 * @version 2.1 Jan 24, 2008
 */
public class XmlRetrieveDocumentSetResponse implements XdsResponse {
	private static final Logger log = Logger.getLogger(XmlRetrieveDocumentSetResponse.class.getName());

	private OMElement root = null;
	private boolean isError = false;
	private String errorMessage = null;
	private XmlAxiomRegistryResponse registryResponse = null;
	
	/**
	 * Create a new retrieve document set response object to wrap the XML
	 * Axiom returned from a retrieving document set transaction.
	 * 
	 * @param xml The XML Axiom element of the RetrieveDocumentSetResponse.
	 */
	XmlRetrieveDocumentSetResponse(OMElement xml) {
		root = xml;
		OMElement rs = root.getFirstChildWithName(new QName(XmlAxiomRegistry.XDS_REGISTRY_V3_NAMESPACE, "RegistryResponse"));
		if (rs == null) {
			isError = true;
			errorMessage = "Invalid RetrieveDocumentSetResponse: Missing RegistryReponse";
		}
		registryResponse = new XmlAxiomRegistryResponse(rs);
	}
	
	/**
	 * Use to determine if this respose is success. 
	 * @return True if the response is successful.
	 */
	public boolean isSuccess() {
		return registryResponse.isSuccess();
	}
	
	/**
	 * Check whether this response is an AdHocQUery response.
	 * 
	 * @return False, it is a XmlRetrieveDocumentSetResponse.
	 */
	public boolean isAdhocQueryResponse() {
		return false;
	}

	/**
	 * Get the rim:ObjectRef instances returned in this response, if any.
	 * These instances will be wrapped as XdsObjectReference registry
	 * object classes.
	 * 
	 * @throws UnsupportedOperationException RetrieveDocumentSetResponse does 
	 * not contain QueryObjectRefereences
	 */
	public List<XdsObjectReference> getQueryObjectReferences() {
		throw new UnsupportedOperationException("RetrieveDocumentSetResponse does not contain QueryObjectReference");
	}
	
	/**
	 * Get the "id" component of the rim:ObjectRef instances returned in 
	 * this response, if any.  This call is equivalent to getting all of the
	 * XdsObjectReference objects in this query response and then asking each
	 * for its Id.
	 * 
	 * @throws UnsupportedOperationException XmlRetrieveDocumentSetResponse does not contain QueryRefereenceIds
	 */
	public List<String> getQueryReferenceIds() {
		throw new UnsupportedOperationException("RetrieveDocumentSetResponse does not contain QueryRefereenceId");
	}

	/**
	 * Get the rim:ExtrinsicObject instances returned in this response that
	 * correspond to XDSDocumentEntry objects, if any.  These instances will 
	 * be wrapped as XdsDocumentEntry registry object classes.
	 * 
	 * @throws UnsupportedOperationException XmlRetrieveDocumentSetResponse does not contain DocumentEntry
	 */
	public List<XdsDocumentEntry> getQueryDocumentEntries() {
		throw new UnsupportedOperationException("RetrieveDocumentSetResponse does not contain DocumentEntry");
	}
	
	/**
	 * Get the xdsb:DocumentResponse instances returned in this response that
	 * correspond to DocumentResponse objects, if any.
	 *  
     * @return a List of DocumentResponse objects corresponding to DocumentResponse element
	 */
	public List<DocumentResponse> getDocumentResponses() {
		List<DocumentResponse> ret = new ArrayList<DocumentResponse>();
		Iterator docs = root.getChildrenWithName(new QName(XmlAxiomRegistry.XDS_b, "DocumentResponse"));
		for ( ; docs.hasNext(); ) {
			OMElement doc = (OMElement)docs.next();
			OMElement repositoryId = doc.getFirstChildWithName(new QName(XmlAxiomRegistry.XDS_b, "RepositoryUniqueId"));
			OMElement documentId   = doc.getFirstChildWithName(new QName(XmlAxiomRegistry.XDS_b, "DocumentUniqueId"));
			OMElement mimeType     = doc.getFirstChildWithName(new QName(XmlAxiomRegistry.XDS_b, "mimeType"));
			OMElement document     = doc.getFirstChildWithName(new QName(XmlAxiomRegistry.XDS_b, "Document"));
			String rId = repositoryId.getText();
			DocumentResponse response = new DocumentResponse();
			response.setRepositoryUniqueId( repositoryId.getText() );
			response.setDocumentUniqueId( documentId.getText() );
			response.setMimeType( mimeType.getText() );
			//Get document InputStream
			OMText dataNode = (OMText) document.getFirstOMChild();
			InputStream stream = null;
			try {
				dataNode.setOptimize(true);
				DataHandler handler = (DataHandler) dataNode.getDataHandler();
				try {
					stream = handler.getDataSource().getInputStream();
				} catch (IOException e) {
					log.error("fails to get document content.", e);
					continue; //skip this DocumentResponse
				}
			} catch (Exception e) {
				e.printStackTrace();
				String b64text = dataNode.getText();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();		
	            try {
					Base64EncoderDecoder.decode(b64text, baos);
					stream = new ByteArrayInputStream(baos.toByteArray());
	            } catch (Exception e1) {
					log.error("fails to decode document content", e1);
				}	            
			}
			response.setContentStream( stream );
			ret.add(response);			
		}
		return ret;
 	}
		
	/**
	 * Informing that this is not an error response.
	 * 
	 * @return False.
	 */
	public boolean isError() {
		return isError || registryResponse.isError();
	}
	
	/**
	 * There is no error message, there is no error.
	 * 
	 * @return Null.
	 */
	public String getErrorMessage() {
		if (errorMessage != null)
			return errorMessage;
		else
			return registryResponse.getErrorMessage();
			
	}

}
