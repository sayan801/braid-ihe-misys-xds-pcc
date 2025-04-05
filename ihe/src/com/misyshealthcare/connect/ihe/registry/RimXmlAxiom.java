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
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;

/**
 * This class implements a number of helper methods to parse
 * ebRIM XML elements with Axiom parser.
 * 
 * @author LiW
 * @version 2.1 Jan 12, 2008
 */
public class RimXmlAxiom {

	/**
	 * Get an ebRIM Axiom Element from inside an XML Axiom Element.
	 * 
	 * @param root The root XML Axiom element
	 * @param tagName The name of the desired element
     * @param rimNameSpace the rim name space of the root element
	 * @return The desired element, if found
	 */
	public static OMElement getRimElement(OMElement root, String tagName, String rimNameSpace) {
		return root.getFirstChildWithName(new QName(rimNameSpace, tagName));
	}
	
	/**
	 * Get all of the ebRIM Slot Elements from inside an XML Axiom Element.
	 * 
	 * @param root The root XML Axiom element
     * @param rimNameSpace the rim name space of the root element
	 * @return The slot elements, if found
	 */
	public static Iterator getRimSlots(OMElement root, String rimNameSpace) {
		return root.getChildrenWithName(new QName(rimNameSpace, "Slot")); 
	}

	/**
	 * Get all of the ebRIM Classification Elements from inside an XML Axiom Element.
	 * 
	 * @param root The root XML Axiom element
     * @param rimNameSpace the rim name space of the root element
	 * @return The classification elements, if found
	 */
	public static Iterator getRimClassifications(OMElement root, String rimNameSpace) {
		return root.getChildrenWithName(new QName(rimNameSpace, "Classification")); 
	}

	/**
	 * Get all of the ebRIM ExternalIdentifier Elements from inside an XML Axiom Element.
	 * 
	 * @param root The root XML Axiom element
     * @param rimNameSpace the rim name space of the root element
	 * @return The external identifier elements, if found
	 */
	public static Iterator getRimExternalIdentifiers(OMElement root, String rimNameSpace) {
		return root.getChildrenWithName(new QName(rimNameSpace, "ExternalIdentifier")); 
	}

	/**
	 * A utility function for getting the value of an attribute.
	 * 
	 * @param root The Axiom element that has the attribute
	 * @param name The name of the attribute
	 * @return The value of the attribute
	 */
	public static String getAttributeValue(OMElement root, String name) {
		return root.getAttributeValue(new QName(name));
	}
	
	/**
	 * Get the value of an ebRIM Slot object
	 * 
	 * @param slot The ebRIM slot object (as an XML Axiom)
     * @param rimNameSpace the rim name space of the root element
	 * @return The first value in the slot
	 */
	public static String getRimSlotValue(OMElement slot, String rimNameSpace) {
		if (slot != null) {
			OMElement valueList = getRimElement(slot, "ValueList", rimNameSpace);
			if (valueList != null) {
				OMElement value = getRimElement(valueList, "Value", rimNameSpace);
				if (value != null) {
					return value.getText();
				}
			}			 
		}
		return null;
	}
	
	/**
	 * Get all the values of an ebRIM Slot object
	 * 
	 * @param slot The ebRIM slot object (as an XML Axiom)
     * @param rimNameSpace the rim name space of the root element
	 * @return The values in the slot
	 */
	public static List<String> getRimSlotValues(OMElement slot, String rimNameSpace) {
		ArrayList<String> results = new ArrayList<String>();
		if (slot != null) {
			OMElement valueList = getRimElement(slot, "ValueList", rimNameSpace);
			if (valueList != null) {
				Iterator values = valueList.getChildrenWithName(new QName(rimNameSpace, "Value")); 
				for( ;values.hasNext(); ) {
                	OMElement value = (OMElement)values.next();
                	String val = value.getText();
                	if (val != null) {
                		results.add(val);
                	}
                }
			}
		}
		return results;		
	}
	
	/**
	 * Get the value of an ebRIM ExternalIdentifier object
	 * 
	 * @param identifier The ebRIM external identifier object (as an XML Axiom)
	 * @return The value of the identifier
	 */
	public static String getRimExternalIdentifierValue(OMElement identifier) {
		if (identifier != null) {
			return getAttributeValue(identifier, "value");
		}
		return null;
	}
	
	/**
	 * Get the code value of an ebRIM Classification object
	 * 
	 * @param classification The ebRIM classification object (as an XML Axiom)
	 * @return The code value of the classification
	 */
	public static String getRimClassificationCode(OMElement classification) {
		if (classification != null) {
			return getAttributeValue(classification, "nodeRepresentation");
		} else {
			return null;
		}
	}
	
	/**
	 * Get the display name for the code value of an ebRIM Classification object
	 * 
	 * @param classification The ebRIM classification object (as an XML Axiom)
     * @param rimNameSpace the rim name space of the root element
	 * @return The display name for the code value of the classification
	 */
	public static String getRimClassificationCodeDisplayName(OMElement classification, String rimNameSpace) {
		if (classification != null) {
			OMElement name = getRimElement(classification, "Name", rimNameSpace);
			if (name != null) {
				OMElement value = getRimElement(name, "LocalizedString", rimNameSpace);
				if (value != null) {
					return getAttributeValue(value, "value");
				}
			}
		}
		return null;
	}

}
