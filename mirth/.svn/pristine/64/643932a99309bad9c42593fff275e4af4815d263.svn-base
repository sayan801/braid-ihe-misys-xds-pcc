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

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.misyshealthcare.connect.base.DocumentResponse;

import java.util.List;
import java.util.Vector;

/**
 * This class corresponds to a query response from an ebRIM registry. This class is only used by
 * StoredQuery ebXml v3.0.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 6, 2006
 */
public class XmlAdhocQueryResponse implements XdsResponse {
    private static final Logger log = Logger.getLogger(XmlAdhocQueryResponse.class.getName());
    private static final String SUCCESS_STATUS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";
    private Element root = null;

    /**
     * Create a new Adhoc Query response object to wrap the XML
     * DOM returned in a registry query SOAP message.
     *
     * @param xml The XML DOM element of the RegsitryResponse.
     */
    XmlAdhocQueryResponse(Element xml) {
        root = xml;
    }

    /**
     * Check whether this registry response indicates success.
     *
     * @return True if the registry operation generating this response executed without error.
     */
    public boolean isSuccess() {
        // See if our "status" attribute is "Success"
        NamedNodeMap attributes = root.getAttributes();
        if (attributes == null) return false;
        Node attribute = attributes.getNamedItem("status");
        if (attribute == null) return false;
        String value = attribute.getNodeValue();
        return ((value != null) && value.equalsIgnoreCase( SUCCESS_STATUS ));
    }

    /**
     * Check whether this response is an AdHocQUery response.
     *
     * @return True if this response is an AdHocQuery response.
     */
    public boolean isAdhocQueryResponse() {
        // See if we hold an AdhocQueryResponse
        String rootName = root.getNodeName();
        return (rootName != null) && rootName.contains("AdhocQueryResponse");
	//	NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_QUERY_V3_NAMESPACE, "AdhocQueryResponse");
		//return ((results != null) && (results.getLength() > 0));
	}

    /**
     * Get the rim:ObjectRef instances returned in this response, if any.
     * These instances will be wrapped as XdsObjectReference registry
     * object classes.
     *
     * @return The registry object references returned in this query response
     */
    public List<XdsObjectReference> getQueryObjectReferences() {
        // So far, we only know about SQLQuery results
        NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_RIM_V3_NAMESPACE, "ObjectRef");
        Vector<XdsObjectReference> objects = new Vector<XdsObjectReference>();
        for (int i=0; i<results.getLength(); i++) {
            Node result = results.item(i);
            if (result instanceof Element) {
                objects.add(new XdsObjectReference((Element) result));
            }
        }
        return objects;
    }

    /**
     * Get the "id" component of the rim:ObjectRef instances returned in
     * this response, if any.  This call is equivalent to getting all of the
     * XdsObjectReference objects in this query response and then asking each
     * for its Id.
     *
     * @return The registry object ids returned in this query response
     */
    public List<String> getQueryReferenceIds() {
        // So far, we only know about SQLQuery results
        NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_RIM_V3_NAMESPACE, "ObjectRef");
        Vector<String> objects = new Vector<String>();
        for (int i=0; i<results.getLength(); i++) {
            Node result = results.item(i);
            if (result instanceof Element) {
                XdsObjectReference ref = new XdsObjectReference((Element) result);
                objects.add(ref.getId());
            }
        }
        return objects;
    }

    /**
     * Get the rim:ExtrinsicObject instances returned in this response that
     * correspond to XDSDocumentEntry objects, if any.  These instances will
     * be wrapped as XdsDocumentEntry registry object classes.
     *
     * @return The XdsDocumentEntry instances returned in this query response
     */
    public List<XdsDocumentEntry> getQueryDocumentEntries() {
        // So far, we only know about SQLQuery results
        NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_RIM_V3_NAMESPACE, "ExtrinsicObject");
        if(results.getLength() == 0)
            results = root.getElementsByTagName("ExtrinsicObject");
        Vector<XdsDocumentEntry> objects = new Vector<XdsDocumentEntry>();
        for (int i=0; i<results.getLength(); i++) {
            Node result = results.item(i);
            if (result instanceof Element) {
                XdsDocumentEntry doc;
                try {
                    doc = new XdsDocumentEntry((Element) result, XmlRegistry.XDS_RIM_V3_NAMESPACE);
                    objects.add(doc);
                } catch (XdsRimException e) {
                    // Not and XdsDocumentEntry
                    log.warn("Unexpected ExtrinsicObject in query response.", e);
                }
            }
        }
        return objects;
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
	 * @return False.
	 */
	public boolean isError() {
		return false;
	}
	
	/**
	 * There is no error message, there is no error.
	 * 
	 * @return Null.
	 */
	public String getErrorMessage() {
		return null;
	}

}
