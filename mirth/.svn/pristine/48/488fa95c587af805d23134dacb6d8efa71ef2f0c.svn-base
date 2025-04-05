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

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.base.DocumentRequest;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class represents the interface between the ebXML registry and the
 * SOAP messaging layer of the IHE Services for Connect.  This class will
 * likely get replaced by a standards-based libary at some time in the
 * future.
 * 
 * @author LiW
 * @version 2.1 - Jan 12, 2008
 */
public class XmlAxiomRegistry {

    /* The standard ebXML namespaces */
    static final String XDS_REGISTRY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0";
    static final String XDS_QUERY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0" ;
    static final String XDS_RIM_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0";
    static final String XDS_LCM_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0";
    static final String XDS_b                = "urn:ihe:iti:xds-b:2007";
    static final String XMLSCHEMA_INSTANCE_XSI = "http://www.w3.org/2001/XMLSchema-instance";
    static final String XDS_b_REPOSITORY_SCHEMA_LOCATION = "urn:ihe:iti:xds-b:2007 ../schema/IHE/XDS.b_DocumentRepository.xsd";
   
    /**
     * Get a new XmlRegistryResponse object initialized with the XML
     * Axiom information supplied.
     *
     * @param message The message holding the registry response
     * @return The registry response as a Java object
     */
    public XmlAxiomRegistryResponse getRegistryResponse(OMElement message) {
    	if(!message.getLocalName().equals("RegistryResponse"))
    		return null;
    	else
    		return new XmlAxiomRegistryResponse(message);
    	
    }
    
    public XmlRetrieveDocumentSetResponse getRetrieveDocumentSetResponse(OMElement message) {
        // Pull out the response
    	if(!message.getLocalName().equals("RetrieveDocumentSetResponse"))
    		return null;
    	else
    		return new XmlRetrieveDocumentSetResponse(message);
    }
    


    /**
     * Create a new SubmitObjectsRequest as an Axiom OMElement 
     *
     * @param submissionSet The xds submission set for the request
     * @param documentEntries The xds document entries for the request
     * @param connection The connection that will be used for the request
     * @throws XdsRimException When the metadata cannot be encoded into a valid ebRIM XML
     */
    public OMElement createDocumentMetadata(XdsSubmissionSet submissionSet, List<XdsDocumentEntry> documentEntries, IConnectionDescription connection) throws XdsRimException {
		OMFactory fac =OMAbstractFactory.getOMFactory();
        OMNamespace nsXdsb = fac.createOMNamespace(XDS_b, null);
        OMNamespace nsLcm = fac.createOMNamespace(XDS_LCM_V3_NAMESPACE, "lcm");
        OMNamespace nsRim = fac.createOMNamespace(XDS_RIM_V3_NAMESPACE, "rim");
        OMNamespace nsXsi = fac.createOMNamespace(XMLSCHEMA_INSTANCE_XSI, "xsi");
        OMNamespace nsRs  = fac.createOMNamespace(XDS_REGISTRY_V3_NAMESPACE, "rs");
        OMElement root = fac.createOMElement("ProvideAndRegisterDocumentSetRequest", nsXdsb);
        root.declareNamespace(nsRim);
        root.declareNamespace(nsLcm);
        root.declareNamespace(nsXsi);
        root.declareNamespace(nsRs);
        root.addAttribute("schemaLocation", XDS_b_REPOSITORY_SCHEMA_LOCATION, nsXsi);
        
        OMElement submitObjectsRequest = fac.createOMElement("SubmitObjectsRequest ", nsLcm);        
        root.addChild( submitObjectsRequest );
        OMElement registryObjectList = fac.createOMElement("RegistryObjectList", nsRim);
        submitObjectsRequest.addChild( registryObjectList );
         
        // Add each document entry to the metadata
        for (XdsDocumentEntry doc: documentEntries) {
            OMElement metadata = doc.getAsXmlAxiom(doc.getEntryUuid(), doc.getMimeType(), connection);
            registryObjectList.addChild(metadata);
        }
        // Now add the submission set
        String setId = "SubmissionSet";
        registryObjectList.addChild(submissionSet.getAsXmlAxiom(setId, connection));
        RimAxiom.addRimClassificationElement(registryObjectList, "cl-ss0", setId, submissionSet.getClassificationNode(), nsRim);
        // Link the document entries to the submission set
        for (XdsDocumentEntry doc: documentEntries) {
            RimAxiom.addRimSubmissionDocumentAssociationElement(registryObjectList, setId, doc.getEntryUuid(), nsRim);
        }
        // Finally, if any documents are replacements, link them to their parent documents
        for (XdsDocumentEntry doc: documentEntries) {
            if (doc.getParentDocumentUUID() != null) {
                RimAxiom.addRimDocumentReplacementAssociationElement(registryObjectList, doc.getEntryUuid(), doc.getParentDocumentUUID(), nsRim);
            }
        }
        return root;
    }

    public OMElement createRetrieveDocumentSetRequest(List<DocumentRequest> docRequests) {
		OMFactory fac =OMAbstractFactory.getOMFactory();
        OMNamespace nsXdsb = fac.createOMNamespace(XDS_b, "xdsb");
        OMNamespace nsXsi = fac.createOMNamespace(XMLSCHEMA_INSTANCE_XSI, "xsi");
        OMElement root = fac.createOMElement("RetrieveDocumentSetRequest", nsXdsb);
        root.declareNamespace(nsXsi);
        root.addAttribute("schemaLocation", XDS_b_REPOSITORY_SCHEMA_LOCATION, nsXsi);
        
        for (DocumentRequest request : docRequests) {
	        OMElement documentRequest = fac.createOMElement("DocumentRequest", nsXdsb); 
	        root.addChild( documentRequest );
	        OMElement repositoryId = fac.createOMElement("RepositoryUniqueId", nsXdsb);
	        repositoryId.addChild( fac.createOMText(request.getRepositoryUniqueId()) );
	        documentRequest.addChild( repositoryId );
	        OMElement documentId = fac.createOMElement("DocumentUniqueId", nsXdsb);
	        documentId.addChild( fac.createOMText(request.getDocumentUniqueId()) );
	        documentRequest.addChild( documentId );
        }
        return root;
    }
}
