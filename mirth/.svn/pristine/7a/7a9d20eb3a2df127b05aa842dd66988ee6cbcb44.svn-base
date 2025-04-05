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

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.misyshealthcare.connect.base.DocumentResponse;


/**
 * This class corresponds to an ebRim message from a document source (e.g. XDR Doc Source).
 * 
 * @author Josh Flachsbart
 * @version 1.0 - Dec 15, 2006
 */
public class XmlSourceResponse  implements XdsResponse {
	
	private static final Logger log = Logger.getLogger(XmlSourceResponse.class.getName());

	private Element root = null;
	private boolean isError = false;
	private String errorMessage = null;
	
	/**
	 * Create a new registry response object to wrap the XML
	 * DOM returned as the first attachment of a XDR submission.
	 * 
	 * @param xml The XML DOM element of the RegsitryResponse.
	 */
	XmlSourceResponse(Element xml) {
		root = xml;
	}
	
	/**
	 * Create a new registry response object to wrap the XML
	 * DOM generated from the metadata attachment of an XDR submission.
	 * 
	 * @param xml The XML DOM element of the RegsitryResponse.
	 */
	XmlSourceResponse(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			root = builder.parse(new InputSource(new StringReader(xml))).getElementById("SubmissionResponse");
		} catch (ParserConfigurationException e) {
			isError = true;
			errorMessage = "Problem building parser to read xml source request.";
		} catch (SAXException e) {
			isError = true;
			errorMessage = "Problem parsing xml source request.  Possibly malformed xml?";
		} catch (IOException e) {
			isError = true;
			errorMessage = "I/O error on string.  Shouldn't be able to get here.";			
		}
	}
	
	/**
	 * Use to determine if this respose is success. 
	 * @return True if the response is successful.
	 */
	public boolean isSuccess() {
		return !isError;
	}
	
	/**
	 * Check whether this response is an AdHocQUery response.
	 * 
	 * @return False, it is a source response.
	 */
	public boolean isAdhocQueryResponse() {
		return false;
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
		NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_RIM_NAMESPACE, "ObjectRef");
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
		NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_RIM_NAMESPACE, "ObjectRef");
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
		NodeList results = root.getElementsByTagNameNS(XmlRegistry.XDS_RIM_NAMESPACE, "ExtrinsicObject");
        if(results.getLength() == 0)
            results = root.getElementsByTagName("ExtrinsicObject");
        Vector<XdsDocumentEntry> objects = new Vector<XdsDocumentEntry>();
		for (int i=0; i<results.getLength(); i++) {
			Node result = results.item(i);
			if (result instanceof Element) {
				XdsDocumentEntry doc;
				try {
					doc = new XdsDocumentEntry((Element) result, XmlRegistry.XDS_RIM_NAMESPACE);
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
		return isError;
	}
	
	/**
	 * There is no error message, there is no error.
	 * 
	 * @return Null.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
