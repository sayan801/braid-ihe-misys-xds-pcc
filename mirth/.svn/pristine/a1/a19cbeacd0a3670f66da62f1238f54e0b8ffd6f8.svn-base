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
package com.misyshealthcare.connect.base.codemapping;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Handles all code mapping. Pulls data from "codemapping.xml", which is in the following form:
 * <pre>
 * <CodeMapping>
 *	<Mapping fromSystem="test" toSystem="ihe">
 *		<!--  Category values must be valid values from CategoryEnum -->
 *		<Category value="Gender">
 *			<Value from="Male" to="M"/>
 *			<Value from="Female" to="F"/>
 *		</Category>
 *	</Mapping>
 * </CodeMapping>
 * </pre>
 *
 * @author Michael Traum
 * @version 2.0, Apr 4, 2007
 *
 */
public class CodeMappingManager implements ICodeMappingManager {
	
	/* Logger for debugging messages */
	private static final Logger log = Logger.getLogger(CodeMappingManager.class);
	
	/* Singleton instance */
	private static CodeMappingManager instance = null;
	
	/* HashMap<fromSystem, HashMap<toSystem, HashMap<Category, HashMap<fromValue, toValue>>>> */
	private HashMap<String, HashMap<String, HashMap<String, HashMap<String, String>>>> mappings = new HashMap();
	
	/** Private default constructor */
	private CodeMappingManager() {
		URL codeMappingFile = CodeMappingManager.class.getResource("/codemapping.xml");
		if (codeMappingFile != null) {
			loadMappings(codeMappingFile.getFile());
		}
	}
	
	/**
	 * Get the singleton instance for this class.
	 * 
	 * @return The singleton ConfigurationLoader
	 */
	public static synchronized CodeMappingManager getInstance() {
		if (instance == null) instance = new CodeMappingManager();
		return instance;
	}

	/**
	 * Perform a lookup to map a value from one to another base on category and the systems
	 * 
	 * @param category The category of code mapping
	 * @param fromSystemId The system to map from
	 * @param toSystemId The system to map to
	 * @param value The value to look up for mapping
	 * @return The mapped value or the original value if no mapped value was found
	 */
	public String getExternalMappingFromCode(CategoryEnum category,
			String fromSystemId, String toSystemId, String value) {
		HashMap<String, HashMap<String, HashMap<String, String>>> fromSystem = mappings.get(fromSystemId);
		if (fromSystem == null)
			return value;
		
		HashMap<String, HashMap<String, String>> toSystem = fromSystem.get(toSystemId);
		if (toSystem == null)
			return value;
		
		HashMap<String, String> cat = toSystem.get(category.getDisplayValue());
		if (cat == null)
			return value;
		
		String mappedValue = cat.get(value);
		if (mappedValue == null)
			return value;
		
		return mappedValue;
	}
	
	/**
	 * Load the supplied configuration file and
	 * create all of the IHE actors that it defines.
	 * 
	 * @param filename The name of the configuration file
	 * @return True if the configuration file was processed successfully
	 */
	private boolean loadMappings(String filename) {
		if (filename == null) return false;
		return loadMappings(new File(filename));
	}
	
	/**
	 * Load the supplied mappings file and cache the values.
	 * 
	 * @param file The mappings file
	 * @return True if the mappings file was processed successfully
	 */
	public boolean loadMappings(File file) {
		if (file == null) {
			log.error("No file given to configuration loader");
			return false;
		} else if (!file.exists()) {
			log.error("The configuration file \"" + file.getAbsolutePath() + "\" does not exist");
			return false;
		}
		
		// Create a builder factory and a builder, and get the mappings document.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		Document mappingsDocument = null;
		try {
			mappingsDocument = factory.newDocumentBuilder().parse(file);
		} catch (SAXException e) {
			// An XML exception
			log.error("Invalid XML in configuration file '" + file.getAbsolutePath() + "'", e);
			return false;
		} catch (IOException e) {
			// A problem reading the file
			log.error("Cannot read configuration file '" + file.getAbsolutePath() + "'", e);
			return false;
		} catch (ParserConfigurationException e) {
			// No XML implementation
			log.error("No XML implementation to process configuration file '" + file.getAbsolutePath() + "'", e);
			return false;
		}
		
		// Get the list of XML elements in the mappings file
		NodeList mappingElements = mappingsDocument.getDocumentElement().getChildNodes();
		for (int elementIndex1 = 0; elementIndex1 < mappingElements.getLength(); elementIndex1++) {
			Node mappingElement = mappingElements.item(elementIndex1);
			if (mappingElement instanceof Element) {
				String name = mappingElement.getNodeName();
				if (name.equalsIgnoreCase("Mapping")) {
					/* HashMap<fromSystem, HashMap<toSystem, HashMap<Category, HashMap<fromValue, toValue>>>> */
					String fromSystemString = getAttributeValue(mappingElement, "fromSystem");
					String toSystemString = getAttributeValue(mappingElement, "toSystem");
					HashMap<String, HashMap<String, HashMap<String, String>>> toSystem;
					if (mappings.get(fromSystemString) == null) {
						toSystem = new HashMap<String, HashMap<String, HashMap<String, String>>>();
					}
					else {
						toSystem = mappings.get(fromSystemString);
					}
					
					for (int elementIndex2 = 0; elementIndex2 < mappingElement.getChildNodes().getLength(); elementIndex2++) {
						Node categoryElement = mappingElement.getChildNodes().item(elementIndex2);
						String catName = categoryElement.getNodeName();
						if (catName.equalsIgnoreCase("Category")) {
							String categoryString = getAttributeValue(categoryElement, "value");
							HashMap<String, HashMap<String, String>> categoryMap;
							if (toSystem.get(categoryString) == null) {
								categoryMap = new HashMap<String, HashMap<String, String>>();
							}
							else {
								categoryMap = toSystem.get(categoryString);
							}
							HashMap fromValue = new HashMap<String, String>();
							for (int elementIndex3 = 0; elementIndex3 < categoryElement.getChildNodes().getLength(); elementIndex3++) {
								Node valueElement = categoryElement.getChildNodes().item(elementIndex3);
								String fromValueString = getAttributeValue(valueElement, "from");
								String toValueString = getAttributeValue(valueElement, "to");
								fromValue.put(fromValueString, toValueString);
							}
							categoryMap.put(categoryString, fromValue);
							toSystem.put(toSystemString, categoryMap);
						}
					}
					mappings.put(fromSystemString, toSystem);
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Get an atribute value
	 * 
	 * @param node The XML DOM node holding the attribute
	 * @param name The name of the attribute
	 * @return The value of the attribute
	 */
	private String getAttributeValue(Node node, String name) {
		NamedNodeMap attributes = node.getAttributes();
		if (attributes == null) return null;
		Node attribute = attributes.getNamedItem(name);
		if (attribute == null) return null;
		return attribute.getNodeValue();
	}

}
