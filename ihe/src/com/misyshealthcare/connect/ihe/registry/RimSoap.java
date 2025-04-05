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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.ihe.XdsDocumentConsumer;
import com.misyshealthcare.connect.net.CodeSet;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.XmlUtil;

/**
 * This class implements builders for the various SOAP message contents
 * objects needed in submissions to XDS registry and repository connections.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 12, 2005
 */
public class RimSoap {

    private static final Logger myLog =  Logger.getLogger(XdsDocumentConsumer.class);

    private static final String XDS_RIM_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1";
    private static final String XDS_RIM_V3_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0";
    
    //private static final int RIM_SHORT_NAME = 64;
    private static final int RIM_LONG_NAME = 128;
    /*Whether to use Rim V2.1 or V3.0. By default it is Rim V2.1.*/
    private static String rimNameSpace = XDS_RIM_NAMESPACE;

    private SOAPElement root = null;
    private String id = null;
    private IConnectionDescription connection = null;
    private Logger log = null;

    /**
     * Create a new XML builder.
     *
     * @param id The ID to assign to the ExtrinsicObject being built
     * @param connection The connection description for where this XML will be sent
     * @throws SOAPException When no SOAP implementation can be found
     */
    RimSoap(String id, IConnectionDescription connection, Logger log) throws SOAPException {
        this.id = id;
        this.connection = connection;
        this.log = log;
    }

    /**
     * Get a new instance of an XML builder for ebRIM XDSDocumentEntry metadata.
     *
     * @param id The id for the ExtrinsicObject representing the metadata
     * @param connection The connection to which this metadata will be sent
     * @param log The log to use when reporting warnings and errors
     * @return The ebRIM XML builder
     * @throws SOAPException When an appropriate SOAP implementation cannot be found
     */
    public static RimSoap newXdsDocumentEntryBuilder(String id, IConnectionDescription connection, Logger log) throws SOAPException {
        RimSoap xml = new RimSoap(id, connection, log);
    	SOAPFactory factory = XmlUtil.newSOAPFactoryInstance(XmlUtil.SunJDK6SOAPFactory);
    	
		xml.root = factory.createElement("ExtrinsicObject", "rim", rimNameSpace);
        xml.root.setAttribute("id", id);
        return xml;
    }

    /**
     * Get a new instance of an XML builder for ebRIM XDSSubmissionSet metadata.
     *
     * @param id The id for the RegistryPackage representing the metadata
     * @param connection The connection to which this metadata will be sent
     * @param log The log to use when reporting warnings and errors
     * @return The ebRIM XML builder
     * @throws SOAPException When an appropriate SOAP implementation cannot be found
     */
    public static RimSoap newXdsSubmissionSetBuilder(String id, IConnectionDescription connection, Logger log) throws SOAPException {
        RimSoap xml = new RimSoap(id, connection, log); 
    	SOAPFactory factory = XmlUtil.newSOAPFactoryInstance(XmlUtil.SunJDK6SOAPFactory);
    	xml.root = factory.createElement("RegistryPackage", "rim", rimNameSpace);

        xml.root.setAttribute("id", id);
        try { xml.addName("Submission Set", true); }
        catch (XdsRimException e) {}
        return xml;
    }

    /**
     * Get the SOAPElement built by this builder.
     *
     * @return The XML DOM document
     */
    public SOAPElement getSoapElement() {
        return root;
    }

    /**
     * Add an attribute to the root of this ebRIM object.
     *
     * @param name The name of the attribute
     * @param value The value of the attribute
     * @param isRequired True if this attribute is required to be present
     * @throws XdsRimException When a required attribute has the value null
     */
    public void addAttribute(String name, String value, boolean isRequired) throws XdsRimException {
        if (value != null) {
            root.setAttribute(name, value);
        } else if (isRequired) {
            throw new XdsRimException("Required attribute \"" + name + "\" not supplied.");
        }
    }

    /**
     * Add a name to this ebRIM object.
     *
     * @param name The name to add
     * @param isRequired True if this name is required to be present
     * @throws XdsRimException When a required name has the value null
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public void addName(String name, boolean isRequired) throws XdsRimException, SOAPException {
        if (name != null) {
            addRimNameElement(root, name, rimNameSpace);
        } else if (isRequired) {
            throw new XdsRimException("Required Name not supplied.");
        }
    }

    /**
     * Add a description to this ebRIM object.
     *
     * @param comment The description to add
     * @param isRequired True if this description is required to be present
     * @throws XdsRimException When a required description has the value null
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public void addDescription(String comment, boolean isRequired) throws XdsRimException, SOAPException {
        if (comment != null) {
            addRimDescriptionElement(root, comment, rimNameSpace);
        } else if (isRequired) {
            throw new XdsRimException("Required Description not supplied.");
        }
    }

    /**
     * Add a single valued slot to this ebRIM object.
     *
     * @param name The slot name
     * @param value The slot value
     * @param isRequired True if this slot must have a value in this ebRIM object
     * @throws XdsRimException If this slot is required but has a null value
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public void addSlot(String name, String value, boolean isRequired) throws XdsRimException, SOAPException {
        if (value != null) {
            addRimSlotElement(root, name, value, rimNameSpace);
        } else if (isRequired) {
            throw new XdsRimException("Required slot \"" + name + "\" not supplied.");
        }
    }

    /**
     * Add a multi-valued slot to this ebRIM.
     *
     * @param name The slot name
     * @param values The slots values
     * @param isRequired True if this slot must have a value in this ebRIM object
     * @throws XdsRimException If this slot is required but has no value
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public void addSlot(String name, List<String> values, boolean isRequired)  throws XdsRimException, SOAPException {
        if (values != null) {
            addRimSlotElement(root, name, values, rimNameSpace);
        } else if (isRequired) {
            throw new XdsRimException("Required slot \"" + name + "\" not supplied.");
        }
    }

    /**
     * Add a classification to this ebRIM object.
     *
     * @param codeType The name of the code type to use for this classification
     * @param value The code value
     * @param classificationScheme The classification scheme for this code type
     * @param isRequired True if this classification must be in this ebRIM object
     * @throws XdsRimException When the classification value does correspond to a known code
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public void addClassification(String codeType, String value, String classificationScheme, boolean isRequired) throws XdsRimException, SOAPException {
        if (value == null) {
            if (isRequired)
                throw new XdsRimException("Required classification \"" + codeType + "\" not supplied.");
        } else {
            CodeSet scheme = connection.getCodeSet(codeType);
            if (!classificationScheme.equals(scheme.getClassificationScheme())) {
                if (log != null) log.error("Error coding classification value using code set: " + codeType);
                throw new XdsRimException("Code set \"" + codeType + "\" does not exist.");
            } else {
                if (!scheme.containsCode(value))
                    throw new XdsRimException("Code set \"" + codeType + "\" does not hold value \"" + value + "\".");
                String displayName = scheme.getDisplayName(value);
                String codingScheme = scheme.getCodingScheme(value);
                addRimClassificationElement(root, id, value, classificationScheme, displayName, codingScheme, rimNameSpace);
            }
        }
    }

    /**
     * Add a multi-valued classification to this ebRIM object.
     *
     * @param codeType The name of the code type to use for this classification
     * @param values A collection of code values
     * @param classificationScheme The classification scheme for this code type
     * @param isRequired True if this classification must be in this ebRIM object
     * @throws XdsRimException  When the classification value does correspond to a known code
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public void addClassification(String codeType, Collection<String> values, String classificationScheme, boolean isRequired) throws XdsRimException, SOAPException {
        if (values != null) {
            for (String value: values) {
                addClassification(codeType, value, classificationScheme, isRequired);
            }
        } else if (isRequired) {
            throw new XdsRimException("Required classification \"" + codeType + "\" not supplied.");
        }
    }

    public void addClassification(String mainField, String mainValue, Map<String, List<String>> otherFields,
                                  String classificationScheme, boolean isRequired) throws XdsRimException, SOAPException {
        if( mainField != null && mainValue != null) {
            addRimClassificationElement(root, id, classificationScheme, mainField, mainValue, otherFields);
        } else if (isRequired) {
            throw new XdsRimException("Required classification \"" + mainField + "\" not supplied.");
        }
    }

    /**
     * Add an external identifier to this ebRIM object.
     *
     * @param name The name of the identifier
     * @param value The value
     * @param identificationScheme The identification scheme this identifier belongs to
     * @param isRequired True if this identifier must be present in this ebRIM object
     * @throws XdsRimException When this identifier is required but has the value null
     * @throws SOAPException  If a SOAP implementation cannot be found
     */
    public void addExternalIdentifier(String name, String value, String identificationScheme, boolean isRequired) throws XdsRimException, SOAPException {
        if (value != null) {
            addRimExternalIdentifierElement(root, value, identificationScheme, name, rimNameSpace);
        } else if (isRequired) {
            throw new XdsRimException("Required external identifier \"" + name + "\" not supplied.");
        }
    }

    /* -------------------------------------------------------------------------------------- */

    /**
     * Add a new ebXML DOM element of the given type and add it to the parent as
     * a child element.
     *
     * @param parent The parent element this should be added to
     * @param tagName The element tag name
     * @param rimNameSpace the rim name space of the root element
     * @return The created element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimElement(SOAPElement parent, String tagName, String rimNameSpace) throws SOAPException {
        SOAPElement newElement = parent.addChildElement(tagName, "rim", rimNameSpace);
        return newElement;
    }

    /**
     * Add a new ebXML Name element to the parent element.
     *
     * @param parent The parent element this should be added to
     * @param name The name data to put in the element
     * @param rimNameSpace the rim name space of the root element
     * @return The new name element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimNameElement(SOAPElement parent, String name, String rimNameSpace) throws SOAPException {
        SOAPElement nameElement = addRimElement(parent, "Name", rimNameSpace);
        SOAPElement localized = addRimElement(nameElement, "LocalizedString", rimNameSpace);
        localized.setAttribute("value", trimRimString(name, RIM_LONG_NAME));
        return nameElement;
    }

    /**
     * Add a new ebXML Description element to the parent element.
     *
     * @param parent The parent element this should be added to
     * @param comment The comment data to put in the element
     * @param rimNameSpace the rim name space of the root element
     * @return The new name element
     * @throws SOAPException  If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimDescriptionElement(SOAPElement parent, String comment, String rimNameSpace) throws SOAPException {
        SOAPElement descElement = addRimElement(parent, "Description", rimNameSpace);
        SOAPElement localized = addRimElement(descElement, "LocalizedString", rimNameSpace);
        localized.setAttribute("value", trimRimString(comment, RIM_LONG_NAME));
        return descElement;
    }

    /**
     * Add a new ebXML Slot element to the parent element.
     *
     * @param parent The parent element this should be added to
     * @param name The name of the slot
     * @param value The value of the slot
     * @param rimNameSpace the rim name space of the root element
     * @return The new slot element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimSlotElement(SOAPElement parent, String name, String value, String rimNameSpace) throws SOAPException {
        SOAPElement slotElement = addRimElement(parent, "Slot", rimNameSpace);
        slotElement.setAttribute("name", name);
        SOAPElement valueList = addRimElement(slotElement, "ValueList", rimNameSpace);
        SOAPElement valueNode = addRimElement(valueList, "Value", rimNameSpace);
        valueNode.addTextNode(trimRimString(value, RIM_LONG_NAME)); // in V2.5, SHORT_NAME in V2.0
        return slotElement;
    }

    /**
     * Add a new ebXML Slot element to the parent element.
     *
     * @param parent The parent element this should be added to
     * @param name The name of the slot
     * @param values The collection of values to put into the slot value list
     * @param rimNameSpace the rim name space of the root element
     * @return The new slot element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimSlotElement(SOAPElement parent, String name, List<String> values, String rimNameSpace) throws SOAPException {
        SOAPElement slotElement = addRimElement(parent, "Slot", rimNameSpace);
        slotElement.setAttribute("name", name);
        SOAPElement valueList = addRimElement(slotElement, "ValueList", rimNameSpace);
        for (String value: values) {
        	if (value == null) continue;
            SOAPElement valueNode = addRimElement(valueList, "Value", rimNameSpace);
            valueNode.addTextNode(trimRimString(value, RIM_LONG_NAME)); // in V2.5, SHORT_NAME in V2.0
        }
        return slotElement;
    }

    /**
     * Add a new ebXML Classification element to the parent element.
     *
     * @param parent The parent element this should be added to
     * @param id The id of the registry object being classified
     * @param value The classification code asigned ot the registry object
     * @param scheme The classification scheme/axis that the code applies to
     * @param display A human-friendly display name for the code value
     * @param codingScheme The coding scheme from which the code value is drawn
     * @param rimNameSpace the rim name space of the root element
     * @return The new classification element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimClassificationElement(SOAPElement parent, String id, String value,
                       String scheme, String display, String codingScheme, String rimNameSpace) throws SOAPException {
        SOAPElement classElement = addRimElement(parent, "Classification", rimNameSpace);
        classElement.setAttribute("classificationScheme", scheme);
        classElement.setAttribute("classifiedObject", id);
        classElement.setAttribute("nodeRepresentation", value);
        addRimNameElement(classElement, display, rimNameSpace);
        addRimSlotElement(classElement, "codingScheme", codingScheme, rimNameSpace);
        return classElement;
    }

    public static SOAPElement addRimClassificationElement(SOAPElement parent, String id, String scheme,
             String mainField, String mainValue, Map<String, List<String>> otherFields) throws SOAPException  {

        SOAPElement classElement = addRimElement(parent, "Classification", rimNameSpace);
        classElement.setAttribute("classificationScheme", scheme);
        classElement.setAttribute("classifiedObject", id);
        classElement.setAttribute("nodeRepresentation", "");

        addRimSlotElement(classElement, mainField, mainValue, rimNameSpace);

        Set<Map.Entry<String, List<String>>> entries = otherFields.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            if (values != null && values.size() > 0) {
                addRimSlotElement(classElement, key, values, rimNameSpace);
            }
        }
        return classElement;
    }

    /**
     * Add a new ebXML Classification element to the parent element.
     *
     * @param parent The parent element to get the classification
     * @param id The id of the ebRIM object being classified
     * @param ref The registry reference of the internal classification scheme
     * @param rimNameSpace the rim name space of the root element
     * @return The new classification element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimClassificationElement(SOAPElement parent, String id, String ref, String rimNameSpace) throws SOAPException {
        SOAPElement classElement = addRimElement(parent, "Classification", rimNameSpace);
        classElement.setAttribute("classifiedObject", id);
        classElement.setAttribute("classificationNode", ref);
        return classElement;
    }

    /**
     * Add a new ebXML ExternalIdentifier element to the parent element.
     *
     * @param parent The parent element this should be added to
     * @param value The value to give to this identifier
     * @param scheme The identification scheme that this identifier is drawn from
     * @param display A human-friendly display name for the type of this identifier
     * @param rimNameSpace the rim name space of the root element
     * @return The new external identifier element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimExternalIdentifierElement(SOAPElement parent, String value, String scheme,
                                                              String display, String rimNameSpace) throws SOAPException {
        SOAPElement identifierElement = addRimElement(parent, "ExternalIdentifier", rimNameSpace);
        identifierElement.setAttribute("identificationScheme", scheme);
        identifierElement.setAttribute("value", value);
        addRimNameElement(identifierElement, display, rimNameSpace);
        return identifierElement;
    }

    /**
     * Add a new ebXML Association element that connects a document entry to a submission set.
     *
     * @param parent The parent element this should be added to
     * @param setId The id of the submission set
     * @param docId The id of the document entry
     * @param rimNameSpace the rim name space of the root element
     * @return The new association element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimSubmissionDocumentAssociationElement(SOAPElement parent,
                                                                         String setId, String docId, String rimNameSpace) throws SOAPException {
        SOAPElement assocElement = addRimElement(parent, "Association", rimNameSpace);
        assocElement.setAttribute("associationType", "HasMember");
        assocElement.setAttribute("sourceObject", setId);
        assocElement.setAttribute("targetObject", docId);
        addRimSlotElement(assocElement, "SubmissionSetStatus", "Original", rimNameSpace);
        return assocElement;
    }

    /**
     * Add a new ebXML Association element that connects a document entry to the document it is replacing.
     *
     * @param parent The parent element this should be added to
     * @param newId The id of the new document
     * @param oldId The id of the document being replaced
     * @param rimNameSpace the rim name space of the root element
     * @return The new association element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimDocumentReplacementAssociationElement(SOAPElement parent,
                                                                          String newId, String oldId, String rimNameSpace) throws SOAPException {
        SOAPElement assocElement = addRimElement(parent, "Association", rimNameSpace);
        assocElement.setAttribute("associationType", "RPLC");
        assocElement.setAttribute("sourceObject", newId);
        assocElement.setAttribute("targetObject", oldId);
        return assocElement;
    }

    /**
     * Add a new ebXML ObjectRef element to the parent
     *
     * @param parent The parent element this should be added to
     * @param id The id of the object reference
     * @param rimNameSpace the rim name space of the root element
     * @return The new object reference element
     * @throws SOAPException If a SOAP implementation cannot be found
     */
    public static SOAPElement addRimObjectRef(SOAPElement parent, String id, String rimNameSpace) throws SOAPException {
        SOAPElement objectRef = addRimElement(parent, "ObjectRef", rimNameSpace);
        objectRef.setAttribute("id", id);
        return objectRef;
    }

    /**
     * Trim a string to the length specified in the RIM V.2 spec.
     *
     * @param input The input string
     * @param size The maximum allowed length
     * @return The input, trimmed to the specified length, if necssary
     */
    private static String trimRimString(String input, int size) {
        if (input == null) return null;
        if (input.length() <= size) {
            return input;
        } else {
            myLog.warn("Trimming ebXML RIM string to " + size + "characters: '" + input + "'");
            return input.substring(0, size);
        }
    }

}
