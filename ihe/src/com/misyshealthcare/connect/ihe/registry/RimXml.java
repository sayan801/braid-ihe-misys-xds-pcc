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

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class implements a number of helper methods to parse
 * ebRIM XML DOM elements.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 10, 2005
 */
public class RimXml {

	/**
	 * Get an ebRIM DOM Element from inside an XML DOM Element.
	 * 
	 * @param root The root XML DOM element
	 * @param tagName The name of the desired element
     * @param rimNameSpace the rim name space of the root element
	 * @return The desired element, if found
	 */
	public static Element getRimElement(Element root, String tagName, String rimNameSpace) {
		NodeList nodes = root.getElementsByTagNameNS(rimNameSpace, tagName);
		if (nodes.getLength() > 0) {
			Node node = nodes.item(0);
			if ((node != null) && (node instanceof Element)) {
				return (Element) node;
			}
		}
		return null;
	}
	
	/**
	 * Get all of the ebRIM Slot Elements from inside an XML ROM Element.
	 * 
	 * @param root The root XML DOM element
     * @param rimNameSpace the rim name space of the root element
	 * @return The slot elements, if found
	 */
	public static NodeList getRimSlots(Element root, String rimNameSpace) {
		return root.getElementsByTagNameNS(rimNameSpace, "Slot");
	}

	/**
	 * Get all of the ebRIM Classification Elements from inside an XML ROM Element.
	 * 
	 * @param root The root XML DOM element
     * @param rimNameSpace the rim name space of the root element
	 * @return The classification elements, if found
	 */
	public static NodeList getRimClassifications(Element root, String rimNameSpace) {
		return root.getElementsByTagNameNS(rimNameSpace, "Classification");
	}

	/**
	 * Get all of the ebRIM ExternalIdentifier Elements from inside an XML ROM Element.
	 * 
	 * @param root The root XML DOM element
     * @param rimNameSpace the rim name space of the root element
	 * @return The external identifier elements, if found
	 */
	public static NodeList getRimExternalIdentifiers(Element root, String rimNameSpace) {
		return root.getElementsByTagNameNS(rimNameSpace, "ExternalIdentifier");
	}

	/**
	 * A utility function for getting the value of an attribute.
	 * 
	 * @param root The DOM element that has the attibute
	 * @param name The name of the attribute
	 * @return The value of the attribute
	 */
	public static String getAttributeValue(Element root, String name) {
		NamedNodeMap attributes = root.getAttributes();
		if (attributes == null) return null;
		Node attribute = attributes.getNamedItem(name);
		if (attribute == null) return null;
		return attribute.getNodeValue();
	}
	
	/**
	 * Get the value of an ebRIM Slot object
	 * 
	 * @param slot The ebRIM slot object (as an XML DOM)
     * @param rimNameSpace the rim name space of the root element
	 * @return The first value in the slot
	 */
	public static String getRimSlotValue(Element slot, String rimNameSpace) {
		if (slot != null) {
			Element valueList = getRimElement(slot, "ValueList", rimNameSpace);
			if (valueList != null) {
				Element value = getRimElement(valueList, "Value", rimNameSpace);
				if (value != null) {
					Node text = value.getFirstChild();
 //                   if (text.getNodeType() == Node.TEXT_NODE) {
                    if (text != null && text.getNodeType() == Node.TEXT_NODE) {
						return text.getNodeValue();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Get all the values of an ebRIM Slot object
	 * 
	 * @param slot The ebRIM slot object (as an XML DOM)
     * @param rimNameSpace the rim name space of the root element
	 * @return The values in the slot
	 */
	public static List<String> getRimSlotValues(Element slot, String rimNameSpace) {
		if (slot != null) {
			Element valueList = getRimElement(slot, "ValueList", rimNameSpace);
			if (valueList != null) {
				NodeList values = valueList.getElementsByTagNameNS(rimNameSpace, "Value");
				if (values != null) {
					ArrayList<String> results = new ArrayList<String>();
					for (int i=0; i<values.getLength(); i++) {
						Node value = values.item(i);
						if (value instanceof Element) {
							if (value != null) {
								Node text = value.getFirstChild();
                                // changed to let null values through
                                if (text != null && text.getNodeType() == Node.TEXT_NODE) {
									String val = text.getNodeValue();
									if (val != null) {
										results.add(val);
									}
								}
							}
						}
					}
					return results;
				}
			}
		}
		return null;		
	}
	
	/**
	 * Get the value of an ebRIM ExternalIdentifier object
	 * 
	 * @param identifier The ebRIM external identifier object (as an XML DOM)
	 * @return The value of the indentifier
	 */
	public static String getRimExternalIdentifierValue(Element identifier) {
		if (identifier != null) {
			return getAttributeValue(identifier, "value");
		}
		return null;
	}
	
	/**
	 * Get the code value of an ebRIM Classification object
	 * 
	 * @param classification The ebRIM classification object (as an XML DOM)
	 * @return The code value of the classification
	 */
	public static String getRimClassificationCode(Element classification) {
		if (classification != null) {
			return getAttributeValue(classification, "nodeRepresentation");
		} else {
			return null;
		}
	}
	
	/**
	 * Get the display name for the code value of an ebRIM Classification object
	 * 
	 * @param classification The ebRIM classification object (as an XML DOM)
     * @param rimNameSpace the rim name space of the root element
	 * @return The display name for the code value of the classification
	 */
	public static String getRimClassificationCodeDisplayName(Element classification, String rimNameSpace) {
		if (classification != null) {
			Element name = getRimElement(classification, "Name", rimNameSpace);
			if (name != null) {
				Element value = getRimElement(name, "LocalizedString", rimNameSpace);
				if (value != null) {
					return getAttributeValue(value, "value");
				}
			}
		}
		return null;
	}

}
