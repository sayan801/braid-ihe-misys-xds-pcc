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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.Pair;
import com.misyshealthcare.connect.util.StringUtil;
import com.misyshealthcare.connect.util.XmlUtil;

/**
 * This class represents the interface between the ebXML registry and the
 * SOAP messaging layer of the IHE Services for Connect.  This class will
 * likely get replaced by a standards-based libary at some time in the
 * future.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 6, 2005
 */
public class XmlRegistry {

    /* The standard ebXML namespaces */
    static final String XDS_REGISTRY_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:registry:xsd:2.1";
    static final String XDS_QUERY_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:query:xsd:2.1" ;
    static final String XDS_RIM_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1";
    static final String XDS_REGISTRY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0";
    static final String XDS_QUERY_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0" ;
    static final String XDS_RIM_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0";
    static final String EBXML_MSG_NAMESPACE = "http://www.oasis-open.org/committees/ebxml-msg/schema/msg-header-2_0.xsd";
    static final String XLINK_NAMESPACE = "http://www.w3.org/1999/xlink";

    /*IDs for the registry stored query */
    private static final String FIND_DOCUMNETS_ID       = "urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d";
    private static final String FIND_SUBMISSION_SETS_ID = "urn:uuid:f26abbcb-ac74-4422-8a30-edb644bbc1a9";
    private static final String FIND_FOLDERS_ID         = "urn:uuid:958f3006-baad-4929-a4de-ff1114824431";
    private static final String GET_ALL_ID              = "urn:uuid:10b545ea-725c-446d-9b95-8aeb444eddf3";
    private static final String GET_DOCUMENTS_ID        = "urn:uuid:5c4f972b-d56b-40ac-a5fc-c8ca9b40b9d4";
    private static final String GET_FOLDERS_ID          = "urn:uuid:5737b14c-8a1a-4539-b659-e03a34a5e1e4";
    private static final String GET_ASSOCIATIONS_ID     = "urn:uuid:a7ae438b-4bc2-4642-93e9-be891f7bb155";
    private static final String GET_DOCUMENTS_AND_ASSOCIATIONS_ID = "urn:uuid:bab9529a-4a10-40b3-a01f-f68a615d247a";
    private static final String GET_SUBMISSION_SETS_ID  = "urn:uuid:51224314-5390-4169-9b91-b1980040715a";
    private static final String GET_SUBMISSIONSET_AND_CONTENTS_ID = "urn:uuid:e8e3cb2c-e39c-46b9-99e4-c12f57260b83";
    private static final String GET_FOLDER_AND_CONTENTS_ID = "urn:uuid:b909a503-523d-4517-8acf-8e5834dfc4c7";
    private static final String GET_FOLDERS_FOR_DOCUMENT = "urn:uuid:10cae35a-c7f9-4cf5-b61e-fc3278ffb578";
    private static final String GET_RELATED_DOCUMNETS_ID = "urn:uuid:d90e5407-b356-4d91-a89f-873917b4b0e6";

    /**
     * Get a new XmlRegistryResponse object initialized with the XML
     * DOM information supplied.
     *
     * @param message The SOAP message body holding the registry response
     * @return The registry response as a Java object
     */
    public XmlRegistryResponse getRegistryResponse(SOAPBody message) {
        // Pull out the response
        NodeList responses = message.getElementsByTagNameNS(XDS_REGISTRY_NAMESPACE, "RegistryResponse");
        if (responses.getLength() != 1) return null;
        // Make a response object
        Node response = responses.item(0);
        if (response instanceof Element) {
            return new XmlRegistryResponse((Element) response);
        } else {
            return null;
        }
    }

    /**
     * Get a new XmlAdhocQueryResponse object initialized with the XML
     * DOM information supplied.
     *
     * @param message The SOAP message body holding the registry response
     * @return The Adhoc Query response as a Java object
     */
    public XmlAdhocQueryResponse getAdhocQueryResponse(SOAPBody message) {
        //pull out the query response
        NodeList responses = message.getElementsByTagNameNS(XmlRegistry.XDS_QUERY_V3_NAMESPACE, "AdhocQueryResponse");
        Node response = responses.item(0);
        if (response instanceof Element) {
            return new XmlAdhocQueryResponse((Element) response);
        } else {
            return null;
        }
    }

    /**
     * Get a new XmlAdhocQueryResponse object initialized with the XML
     * DOM information supplied.
     *
     * @param message The SOAP message body holding the registry response
     * @return The Adhoc Query response as a Java object
     */
    public XmlSourceResponse getSubmissionResponse(SOAPMessage message) {
        XmlSourceResponse response = null;
        try {
            NodeList manifest = message.getSOAPBody().getElementsByTagNameNS(XmlRegistry.EBXML_MSG_NAMESPACE, "Manifest");
            if (manifest.getLength() > 0) {
                NodeList responses = message.getSOAPBody().getElementsByTagNameNS(XmlRegistry.XDS_REGISTRY_NAMESPACE, "SubmitObjectsRequest");
                Node nodeResponse = responses.item(0);
                if (response instanceof Element) {
                    response = new XmlSourceResponse((Element) nodeResponse);
                }
            } else {
                MimeHeaders headers = new MimeHeaders();
                headers.setHeader("content-id", "Metadata");
                Iterator metadataList = message.getAttachments(headers);
                if (! metadataList.hasNext()) throw new SOAPException("SOAP message missing submission set metadata.");
                AttachmentPart metadata = (AttachmentPart) metadataList.next();
                Object data = metadata.getContent();
                if (data instanceof String) {
                    response = new XmlSourceResponse((String) data);
                }
            }
        } catch (SOAPException e) {
            // Log something?  the next level up will know something bad happened...
            response = null;
        }
        return response;
    }

    /**
     * Add a new ebXML AdhocQueryRequest to an existing SOAP message
     * body.
     *
     * @param body The SOAP message body
     * @param query The text of the SQL query
     * @param returnType The return type of registry references
     * @param returnObjects Whether or not registry objects should be expanded out on return
     * @throws SOAPException When the SOAP body cannot be extended
     */
    public void addSqlQuery(SOAPBody body, String query, String returnType, String returnObjects) throws SOAPException {
        SOAPFactory contentFactory = SOAPFactory.newInstance();
        Name reqName = contentFactory.createName("AdhocQueryRequest", "", XDS_QUERY_NAMESPACE);
        SOAPElement request = body.addBodyElement(reqName);
        request.addNamespaceDeclaration("rs", XDS_REGISTRY_NAMESPACE);
        request.addNamespaceDeclaration("rim", XDS_RIM_NAMESPACE);
        request.addNamespaceDeclaration("q", XDS_QUERY_NAMESPACE);
        // Specify the return types from the query
        SOAPElement responseOption = request.addChildElement("ResponseOption");
        responseOption.addAttribute(contentFactory.createName("returnType"), returnType);
        responseOption.addAttribute(contentFactory.createName("returnComposedObjects"), returnObjects);
        // Add in the query itself
        SOAPElement sql = request.addChildElement("SQLQuery");
        sql.addTextNode(query);
    }

    public void addAdhocQueryV3(SOAPBody body, DocumentQuery query, String patientId, String returnType,
                                String returnObjects, IConnectionDescription connection) throws SOAPException, IheConfigurationException {
        SOAPFactory contentFactory = SOAPFactory.newInstance();
        Name reqName = contentFactory.createName("AdhocQueryRequest", "", XDS_QUERY_V3_NAMESPACE);
        SOAPElement request = body.addBodyElement(reqName);
        request.addNamespaceDeclaration("rs", XDS_REGISTRY_V3_NAMESPACE);
        request.addNamespaceDeclaration("rim", XDS_RIM_V3_NAMESPACE);
        // Specify the return types from the query
        SOAPElement responseOption = request.addChildElement("ResponseOption");
        responseOption.addAttribute(contentFactory.createName("returnType"), returnType);
        responseOption.addAttribute(contentFactory.createName("returnComposedObjects"), returnObjects);
        // Add in the query itself
        SOAPElement adhocQuery = request.addChildElement("AdhocQuery", "rim");
        adhocQuery.addAttribute(contentFactory.createName("id"),  (String)getStoredQueryId(query, patientId)._first);
        QueryBuilder.addFindAllDocumentsStoredQuery(adhocQuery, query, patientId);
        //Add confidentialityCode Query
        QueryBuilder.addConfidentialityCodes( adhocQuery, query, connection);
        //Add formatCode Query
        QueryBuilder.addFormatCodes( adhocQuery, query, connection);
    }

    /**
     * Get the stored query UUID based on the DocumentQuery object.
     * 
     * @param query
     * @param patiendId
     * @return a Pair<String(stored query UUID), String(query name)>.
     */
    public static Pair getStoredQueryId(DocumentQuery query, String patiendId) {
          if (StringUtil.goodString(query.getDocumentId())  ) {
              if (query.getDocumentRelationship() != null) return new Pair(GET_RELATED_DOCUMNETS_ID, "Get Related Documents");
              else return new Pair(GET_DOCUMENTS_ID, "Get Documents");
          }
          if (StringUtil.goodString(query.getEntryUUID()))  return new Pair(GET_DOCUMENTS_ID, "Get Documents");

          if (StringUtil.goodString(patiendId)) return new Pair(FIND_DOCUMNETS_ID, "Find Documents");

          //should not reache here
          throw new DocumentException("Unexpected document query.");
    }

    /**
     * Add a new SubmitObjectsRequest to an existing SOAP message
     * body.  If no body is given it will create the metadata element
     * from scratch.
     *
     * @param body The SOAP message body
     * @param submissionSet The xds submission set for the request
     * @param documentEntries The xds document entries for the request
     * @param connection The connection that will be used for the request
     * @throws SOAPException When the SOAP body cannot be extended
     * @throws XdsRimException When the metadata cannot be encoded into a valid ebRIM XML
     */
    public SOAPElement addDocumentMetadata(SOAPBody body, XdsSubmissionSet submissionSet, List<XdsDocumentEntry> documentEntries, IConnectionDescription connection) throws SOAPException, XdsRimException {   	
    	SOAPFactory contentFactory = XmlUtil.newSOAPFactoryInstance(XmlUtil.SunJDK6SOAPFactory);
    	Name reqName = contentFactory.createName("SubmitObjectsRequest", "rs", XDS_REGISTRY_NAMESPACE);        
        SOAPElement request = null;
        if (body == null) request = contentFactory.createElement(reqName);
        else request = body.addBodyElement(reqName);
        request.addNamespaceDeclaration("rim", XDS_RIM_NAMESPACE);
        // Escape for some MESA tests
        if (submissionSet == null) return null;
        // Create the leaf objects node to enclose the metadata elements
        SOAPElement objectList = RimSoap.addRimElement(request, "LeafRegistryObjectList", XDS_RIM_NAMESPACE);
        // Gather up all of the external registry object references
        ArrayList<String> refs = new ArrayList<String>();
        Collection<String> objRefs = submissionSet.getObjectReferences();
        for (String ref: objRefs) if (!refs.contains(ref)) refs.add(ref);
        for (XdsDocumentEntry doc: documentEntries) {
            objRefs = doc.getObjectReferences();
            for (String ref: objRefs) if (!refs.contains(ref)) refs.add(ref);
        }
        // Add all of the external references to registry objects to the metadata
        for (String ref: refs) {
            RimSoap.addRimObjectRef(objectList, ref, XDS_RIM_NAMESPACE);
        }
        // Add each document entry to the metadata
        for (XdsDocumentEntry doc: documentEntries) {
            SOAPElement metadata = doc.getAsXmlDom(doc.getEntryUuid(), connection);
            objectList.addChildElement(metadata);
        }
        // Now add the submission set
        String setId = "SubmissionSet";
        objectList.addChildElement(submissionSet.getAsXmlDom(setId, connection));
        RimSoap.addRimClassificationElement(objectList, setId, submissionSet.getClassificationNode(), XDS_RIM_NAMESPACE);
        // Link the document entries to the submission set
        for (XdsDocumentEntry doc: documentEntries) {
            RimSoap.addRimSubmissionDocumentAssociationElement(objectList, setId, doc.getEntryUuid(), XDS_RIM_NAMESPACE);
        }
        // Finally, if any documents are replacements, link them to their parent documents
        for (XdsDocumentEntry doc: documentEntries) {
            if (doc.getParentDocumentUUID() != null) {
                RimSoap.addRimDocumentReplacementAssociationElement(objectList, doc.getEntryUuid(), doc.getParentDocumentUUID(), XDS_RIM_NAMESPACE);
            }
        }
        return request;
    }

    
    /**
     * Add a new SubmitObjectsRequest to an existing SOAP message
     * body.  Use for offline SMTP ebXML messaging.
     *
     * @param body The SOAP message 
     * @param submissionSet The xds submission set for the request
     * @param documentEntries The xds document entries for the request
     * @param connection The connection that will be used for the request
     * @throws SOAPException When the SOAP body cannot be extended
     * @throws XdsRimException When the metadata cannot be encoded into a valid ebRIM XML
     */
	public void addSmtpDocumentHeader(SOAPMessage message, XdsSubmissionSet submissionSet, List<XdsDocumentEntry> documentEntries, IConnectionDescription connection) throws SOAPException, XdsRimException {
		SOAPFactory contentFactory = SOAPFactory.newInstance();
		Name versionName = contentFactory.createName("version", "eb", EBXML_MSG_NAMESPACE);
		String fromDoc = connection.getProperty("OfflineFromAddress");//"doctor@from.com";
		String toDoc = connection.getProperty("OfflineToAddress");//"doctor@to.com";
		String version = "2.0";

		// Create message envelope header. (MessageHeader)
		Name headerName = contentFactory.createName("MessageHeader", "eb", EBXML_MSG_NAMESPACE);
		SOAPHeaderElement header = message.getSOAPHeader().addHeaderElement(headerName);
		header.setMustUnderstand(true);
		header.addAttribute(versionName, version);
		EbXmlMsgSoap.fillEbSoapHeaderElement(submissionSet.getComments(), fromDoc, toDoc, header, null, false, EBXML_MSG_NAMESPACE);
		
		// Create message envelope body.  (Manifest)
		Name reqName = contentFactory.createName("Manifest", "eb", EBXML_MSG_NAMESPACE);
		SOAPElement manifest = message.getSOAPBody().addBodyElement(reqName);
		manifest.addAttribute(versionName, version);
		manifest.addNamespaceDeclaration("xlink", XLINK_NAMESPACE);
		// Create the leaf objects node to enclose the metadata elements
		EbXmlMsgSoap.addFirstEbReferenceElement(manifest, EBXML_MSG_NAMESPACE);
		// Add each document entry to the metadata
		for (XdsDocumentEntry doc: documentEntries) {
			EbXmlMsgSoap.addEbReferenceElement(manifest, doc.getTitle(), doc.getEntryUuid(), EBXML_MSG_NAMESPACE);
        }	
	}
	
	public void addSmtpResponseHeaders(SOAPMessage response, SOAPMessage message) throws SOAPException {
		SOAPFactory contentFactory = SOAPFactory.newInstance();

		// Read response elements from message
		SOAPElement soapHeader = EbXmlMsgSoap.extractSOAPElement(message.getSOAPHeader(), "MessageHeader", EBXML_MSG_NAMESPACE);
		String toDoc = EbXmlMsgSoap.extractEbPartyName(soapHeader, "To", EBXML_MSG_NAMESPACE);
		String fromDoc = EbXmlMsgSoap.extractEbPartyName(soapHeader, "From", EBXML_MSG_NAMESPACE);
		String conversationId = EbXmlMsgSoap.extractSOAPElementText(soapHeader, "ConversationId", EBXML_MSG_NAMESPACE);
		String version = "2.0";

		// Create SOAP envelope
		// Create message envelope header. (MessageHeader)
		Name versionName = contentFactory.createName("version", "eb", EBXML_MSG_NAMESPACE);
		Name headerName = contentFactory.createName("MessageHeader", "eb", EBXML_MSG_NAMESPACE);
		SOAPHeaderElement header = response.getSOAPHeader().addHeaderElement(headerName);
		header.setMustUnderstand(false);
		header.addAttribute(versionName, version);
		EbXmlMsgSoap.fillEbSoapHeaderElement("Document Recipient Response", toDoc, fromDoc, header, conversationId, true, EBXML_MSG_NAMESPACE);
		
		// Create message envelope body.  (Manifest)
		Name manName = contentFactory.createName("Manifest", "eb", EBXML_MSG_NAMESPACE);
		SOAPElement manifest = response.getSOAPBody().addBodyElement(manName);
		manifest.addAttribute(versionName, version);
		manifest.addNamespaceDeclaration("xlink", XLINK_NAMESPACE);
		// Create the leaf objects node to enclose the metadata elements
		EbXmlMsgSoap.addFirstEbReferenceElement(manifest, EBXML_MSG_NAMESPACE);
		
		return;
	}
}
