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


package com.misyshealthcare.connect.doc.ccd;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;

import com.misyshealthcare.connect.base.SharedEnums.XdsClassCode;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.util.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hl7OrgV3.ClinicalDocumentDocument1;

import javax.xml.namespace.QName;

/**
 * A utility class assisting XmlBeans to create xml elments, attributes and text.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 15, 2005
 */
public class XmlBeanUtil {
    static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";
    static final String NAME_SPACE = "urn:hl7-org:v3";
    public static final String PCC_NAME_SPACE = "urn:oid:1.3.6.1.4.1.19376.1.5.3.4";
    /**
     * Adds text to an element.  
     *
     * @param xmlBean the Xmlobject to which the text is to be added.
     * @param text the text content
     */
    public static void addText(XmlObject xmlBean, String text) {
        XmlCursor cursor = xmlBean.newCursor();
        cursor.toLastAttribute();
        cursor.toNextToken();
        cursor.insertChars(text);
        // Speed the cursor's garbage collection and return the updated XML.
        cursor.dispose();
        
    }

    /**
     * Adds an attribute to an element.
     *
     * @param xmlBean the XmlObject to which the attribute is to be added
     * @param name the name of the attribute
     * @param value the value of the attribute
     */
    public static void addAttribute(XmlObject xmlBean, String name, String value) {
        XmlCursor cursor = xmlBean.newCursor();
        cursor.toNextToken();
        cursor.insertAttributeWithValue(name, XSI, value);

        // Speed the cursor's garbage collection and return the updated XML.
        cursor.dispose();
    }


    /**
     * Addes an element.
     *
     * @param xmlBean the XmlObject to which the element is to be added.
     * @param qname the qname of the element
     * @param attributes the array of attribute name and attribute value. Each attribute takes two array elements.
     */
    public static void addElement(XmlObject xmlBean, QName qname, String ... attributes ) {
        XmlCursor cursor = xmlBean.newCursor();
        cursor.toEndToken();
        cursor.beginElement(qname);

        for (int i=0; i<attributes.length; i=i+2){
            String attributeName = attributes[i];
            String attributeValue = attributes[i+1];
            cursor.insertAttributeWithValue(attributeName, attributeValue);
        }
        // Speed the cursor's garbage collection and return the updated XML.
        cursor.dispose();
    }
    
    public static void addElementWithText(XmlObject xmlBean, String elementName, String text) {
    	XmlCursor cursor = xmlBean.newCursor();
    	cursor.toEndToken();
    	cursor.insertElementWithText(new QName(NAME_SPACE, elementName), text);
    	cursor.dispose();
    }
    
    public static void addAttributes(XmlObject xmlBean, Map<String,String> attributes) {
    	XmlCursor cursor = xmlBean.newCursor();
        cursor.toNextToken();
        
    	if(attributes != null && attributes.size() > 0){
        	Set<String> keys = attributes.keySet();
        	Iterator<String> iterator = keys.iterator();
        	while(iterator.hasNext()){
        		String key = iterator.next();
        		cursor.insertAttributeWithValue(key, attributes.get(key));
        	}
        }
    	cursor.dispose();
    }
    public static void addElementwithAttributes(XmlObject xmlBean, String elementName, Map<String,String> map) {
	    XmlCursor cursor = xmlBean.newCursor();
	    cursor.toEndToken();
	    cursor.beginElement(elementName, NAME_SPACE);
	    if(map != null && map.size() > 0){
	    	Set<String> keys = map.keySet();
	    	Iterator<String> iterator = keys.iterator();
	    	while(iterator.hasNext()){
	    		String key = iterator.next();
	    		if("type".equals(key))
	    			cursor.insertAttributeWithValue(key, XSI, map.get(key));
	    		else
	    			cursor.insertAttributeWithValue(key, map.get(key));
	    	}
	    }
	    // Speed the cursor's garbage collection and return the updated XML.
	    cursor.dispose();
	}
    /**
     * Addes an element.
     *
     * @param xmlBean the XmlObject to which the element is to be added.
     * @param elementName  the element name
     * @param attributes the array of attribute name and attribute value. Each attribute takes two array elements.
     */
    public static void addElement(XmlObject xmlBean, String elementName, String ... attributes ) {
       QName qname = new QName(NAME_SPACE, elementName);
       addElement(xmlBean, qname, attributes);
    }

    /**
     * Adds a reference value element. The value element is of ANY type in RIM V3, so it needs special handling here.
     *
     * @param xmlBean  the XmlObject to which this value element is to be added 
     * @param code  the {@link CodeSystem} of the value
     * @param reference the reference id that this value element contains
     */
    public static void addReferenceValue(XmlObject xmlBean, CodeSystem code, String reference) {
        XmlCursor cursor = xmlBean.newCursor();
        cursor.toEndToken();
        cursor.beginElement("value", NAME_SPACE);
        cursor.insertAttributeWithValue("type", XSI, "CD" );

        if (null != code && StringUtil.goodString(code.getCode()) &&
            	StringUtil.goodString(code.getCodeSystem() ) ){
             cursor.insertAttributeWithValue("code", code.getCode());

            if ( StringUtil.goodString(code.getDisplayName()) )
                cursor.insertAttributeWithValue("displayName", code.getDisplayName());

            cursor.insertAttributeWithValue("codeSystem", code.getCodeSystem());

            if ( StringUtil.goodString(code.getCodeSystemName()) )
                cursor.insertAttributeWithValue("codeSystemName", code.getCodeSystemName());
        }
        if (StringUtil.goodString(reference)) {
            cursor.insertElement("originalText", NAME_SPACE);
            cursor.toPrevToken();
            cursor.insertElement("reference", NAME_SPACE);
            cursor.toPrevToken();
            cursor.insertAttributeWithValue("value", "#" + reference);
        }
        // Speed the cursor's garbage collection and return the updated XML.
        cursor.dispose();
    }

    public static void addValue(XmlObject xmlBean, CodeSystem code) {
        XmlCursor cursor = xmlBean.newCursor();
        cursor.toEndToken();
        cursor.beginElement("value", NAME_SPACE);
        cursor.insertAttributeWithValue("type", XSI, "CE" );

        if (null != code && StringUtil.goodString(code.getCode()) &&
        	            	StringUtil.goodString(code.getCodeSystem() ) ){
            cursor.insertAttributeWithValue("code", code.getCode());

            if ( StringUtil.goodString(code.getDisplayName()) )
                cursor.insertAttributeWithValue("displayName", code.getDisplayName());

            cursor.insertAttributeWithValue("codeSystem", code.getCodeSystem());

            if ( StringUtil.goodString(code.getCodeSystemName()) )
                cursor.insertAttributeWithValue("codeSystemName", code.getCodeSystemName());
        }
        // Speed the cursor's garbage collection and return the updated XML.
        cursor.dispose();
    }
    
    /**
     * Gets the xml print options.
     *
     * @param prettyPrint whether the xml is printed in pretty format or concise format
     * @return an <code>XmlOptions</code> object
     */
    public static XmlOptions getXmlOptions(boolean prettyPrint) {
        //Serialize
        XmlOptions opts = new XmlOptions();
        HashMap nsMap = new HashMap();
        nsMap.put(NAME_SPACE, "");
        nsMap.put(XSI,        "xsi");
        opts.setSavePrettyPrint();
        opts.setSaveAggressiveNamespaces();
        opts.setSaveSuggestedPrefixes(nsMap);
        opts.setSaveOuter();
        return opts;
    }



    /**
     * Converts the built doc to an XML String
     *
     * @return the XML String
     */
    public static String toXml(ClinicalDocumentDocument1 doc1) {
       return toXml( doc1, true );
    }
    /**
     * Converts the built doc to an XML String
     *
     * @param addStyleSheet whether to add stylesheet link at the begging of the xml file.
     * @return the XML String
     */
    public static String toXml(ClinicalDocumentDocument1 doc1, boolean addStyleSheet) {
        String styleSheet = LibraryConfig.getInstance().getStyleSheetLocation();

        XmlOptions opts = XmlBeanUtil.getXmlOptions(true);
        XmlCursor cursor = doc1.newCursor();
        if (cursor.toFirstChild()) {
          cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation"), "urn:hl7-org:v3 CDA.xsd");
        }

        String sXml = doc1.xmlText(opts);
        StringBuffer xmlOutput = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?>\n");
        if (addStyleSheet) {
            xmlOutput.append("<?xml-stylesheet type='text/xsl' href='" + styleSheet + "'?>\n");
        }
        xmlOutput.append(sXml);

        return xmlOutput.toString();
    }
}
