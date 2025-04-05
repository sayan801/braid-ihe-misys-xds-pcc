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
package com.misyshealthcare.connect.doc.ccd.xsd;


/**
 *  ConsentDocumentData bean class
 */
public class ConsentDocumentData extends com.misyshealthcare.connect.doc.ccd.xsd.ScannedDocumentData
    implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for ConsentPolicies
     * This was an Array!
     */
    protected com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] localConsentPolicies;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localConsentPoliciesTracker = false;

    /* This type was generated from the piece of schema that had
       name = ConsentDocumentData
       Namespace URI = http://ccd.doc.connect.misyshealthcare.com/xsd
       Namespace Prefix = ns1
     */
    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if (namespace.equals("http://ccd.doc.connect.misyshealthcare.com/xsd")) {
            return "ns1";
        }

        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.ConfidentialityCode[]
     */
    public com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] getConsentPolicies() {
        return localConsentPolicies;
    }

    /**
     * validate the array for ConsentPolicies
     */
    protected void validateConsentPolicies(
        com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] param) {
    }

    /**
     * Auto generated setter method
     * @param param ConsentPolicies
     */
    public void setConsentPolicies(
        com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] param) {
        validateConsentPolicies(param);

        if (param != null) {
            //update the setting tracker
            localConsentPoliciesTracker = true;
        } else {
            localConsentPoliciesTracker = true;
        }

        this.localConsentPolicies = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.base.xsd.ConfidentialityCode
     */
    public void addConsentPolicies(
        com.misyshealthcare.connect.base.xsd.ConfidentialityCode param) {
        if (localConsentPolicies == null) {
            localConsentPolicies = new com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] {
                    
                };
        }

        //update the setting tracker
        localConsentPoliciesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localConsentPolicies);
        list.add(param);
        this.localConsentPolicies = (com.misyshealthcare.connect.base.xsd.ConfidentialityCode[]) list.toArray(new com.misyshealthcare.connect.base.xsd.ConfidentialityCode[list.size()]);
    }

    /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
    public static boolean isReaderMTOMAware(
        javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;

        try {
            isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                        org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        } catch (java.lang.IllegalArgumentException e) {
            isReaderMTOMAware = false;
        }

        return isReaderMTOMAware;
    }

    /**
     *
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName,
        final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {
        org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                parentQName) {
                public void serialize(
                    org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                    throws javax.xml.stream.XMLStreamException {
                    ConsentDocumentData.this.serialize(parentQName, factory,
                        xmlWriter);
                }
            };

        return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
            factory, dataSource);
    }

    public void serialize(final javax.xml.namespace.QName parentQName,
        final org.apache.axiom.om.OMFactory factory,
        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException,
            org.apache.axis2.databinding.ADBException {
        java.lang.String prefix = null;
        java.lang.String namespace = null;

        prefix = parentQName.getPrefix();
        namespace = parentQName.getNamespaceURI();

        if (namespace != null) {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace,
                    parentQName.getLocalPart());
            } else {
                if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(),
                    namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        } else {
            xmlWriter.writeStartElement(parentQName.getLocalPart());
        }

        java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                "http://ccd.doc.connect.misyshealthcare.com/xsd");

        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
            writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
                "type", namespacePrefix + ":ConsentDocumentData", xmlWriter);
        } else {
            writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
                "type", "ConsentDocumentData", xmlWriter);
        }

        if (localMetadataTracker) {
            if (localMetadata == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "metadata",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "metadata");
                    }
                } else {
                    xmlWriter.writeStartElement("metadata");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localMetadata.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "metadata"), factory, xmlWriter);
            }
        }

        if (localMediaTypeTracker) {
            if (localMediaType == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "mediaType",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "mediaType");
                    }
                } else {
                    xmlWriter.writeStartElement("mediaType");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localMediaType.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "mediaType"), factory, xmlWriter);
            }
        }

        if (localFileContentTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "fileContent", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "fileContent");
                }
            } else {
                xmlWriter.writeStartElement("fileContent");
            }

            if (localFileContent != null) {
                xmlWriter.writeDataHandler(localFileContent);
            }

            xmlWriter.writeEndElement();
        }

        if (localConsentPoliciesTracker) {
            if (localConsentPolicies != null) {
                for (int i = 0; i < localConsentPolicies.length; i++) {
                    if (localConsentPolicies[i] != null) {
                        localConsentPolicies[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "consentPolicies"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "consentPolicies", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "consentPolicies");
                            }
                        } else {
                            xmlWriter.writeStartElement("consentPolicies");
                        }

                        // write the nil attribute
                        writeAttribute("xsi",
                            "http://www.w3.org/2001/XMLSchema-instance", "nil",
                            "1", xmlWriter);
                        xmlWriter.writeEndElement();
                    }
                }
            } else {
                // write null attribute
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "consentPolicies",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "consentPolicies");
                    }
                } else {
                    xmlWriter.writeStartElement("consentPolicies");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        xmlWriter.writeEndElement();
    }

    /**
     * Util method to write an attribute with the ns prefix
     */
    private void writeAttribute(java.lang.String prefix,
        java.lang.String namespace, java.lang.String attName,
        java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        if (xmlWriter.getPrefix(namespace) == null) {
            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
        }

        xmlWriter.writeAttribute(namespace, attName, attValue);
    }

    /**
     * Util method to write an attribute without the ns prefix
     */
    private void writeAttribute(java.lang.String namespace,
        java.lang.String attName, java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        if (namespace.equals("")) {
            xmlWriter.writeAttribute(attName, attValue);
        } else {
            registerPrefix(xmlWriter, namespace);
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }
    }

    /**
     * Util method to write an attribute without the ns prefix
     */
    private void writeQNameAttribute(java.lang.String namespace,
        java.lang.String attName, javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String attributeNamespace = qname.getNamespaceURI();
        java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

        if (attributePrefix == null) {
            attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
        }

        java.lang.String attributeValue;

        if (attributePrefix.trim().length() > 0) {
            attributeValue = attributePrefix + ":" + qname.getLocalPart();
        } else {
            attributeValue = qname.getLocalPart();
        }

        if (namespace.equals("")) {
            xmlWriter.writeAttribute(attName, attributeValue);
        } else {
            registerPrefix(xmlWriter, namespace);
            xmlWriter.writeAttribute(namespace, attName, attributeValue);
        }
    }

    /**
     *  method to handle Qnames
     */
    private void writeQName(javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String namespaceURI = qname.getNamespaceURI();

        if (namespaceURI != null) {
            java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

            if (prefix == null) {
                prefix = generatePrefix(namespaceURI);
                xmlWriter.writeNamespace(prefix, namespaceURI);
                xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
                xmlWriter.writeCharacters(prefix + ":" +
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            } else {
                // i.e this is the default namespace
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        } else {
            xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    qname));
        }
    }

    private void writeQNames(javax.xml.namespace.QName[] qnames,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        if (qnames != null) {
            // we have to store this data until last moment since it is not possible to write any
            // namespace data after writing the charactor data
            java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
            java.lang.String namespaceURI = null;
            java.lang.String prefix = null;

            for (int i = 0; i < qnames.length; i++) {
                if (i > 0) {
                    stringToWrite.append(" ");
                }

                namespaceURI = qnames[i].getNamespaceURI();

                if (namespaceURI != null) {
                    prefix = xmlWriter.getPrefix(namespaceURI);

                    if ((prefix == null) || (prefix.length() == 0)) {
                        prefix = generatePrefix(namespaceURI);
                        xmlWriter.writeNamespace(prefix, namespaceURI);
                        xmlWriter.setPrefix(prefix, namespaceURI);
                    }

                    if (prefix.trim().length() > 0) {
                        stringToWrite.append(prefix).append(":")
                                     .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                } else {
                    stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qnames[i]));
                }
            }

            xmlWriter.writeCharacters(stringToWrite.toString());
        }
    }

    /**
     * Register a namespace prefix
     */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String prefix = xmlWriter.getPrefix(namespace);

        if (prefix == null) {
            prefix = generatePrefix(namespace);

            while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
            }

            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
        }

        return prefix;
    }

    /**
     * databinding method to get an XML representation of this object
     *
     */
    public javax.xml.stream.XMLStreamReader getPullParser(
        javax.xml.namespace.QName qName)
        throws org.apache.axis2.databinding.ADBException {
        java.util.ArrayList elementList = new java.util.ArrayList();
        java.util.ArrayList attribList = new java.util.ArrayList();

        attribList.add(new javax.xml.namespace.QName(
                "http://www.w3.org/2001/XMLSchema-instance", "type"));
        attribList.add(new javax.xml.namespace.QName(
                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                "ConsentDocumentData"));

        if (localMetadataTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "metadata"));

            elementList.add((localMetadata == null) ? null : localMetadata);
        }

        if (localMediaTypeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "mediaType"));

            elementList.add((localMediaType == null) ? null : localMediaType);
        }

        if (localFileContentTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "fileContent"));

            elementList.add(localFileContent);
        }

        if (localConsentPoliciesTracker) {
            if (localConsentPolicies != null) {
                for (int i = 0; i < localConsentPolicies.length; i++) {
                    if (localConsentPolicies[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "consentPolicies"));
                        elementList.add(localConsentPolicies[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "consentPolicies"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "consentPolicies"));
                elementList.add(localConsentPolicies);
            }
        }

        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
            elementList.toArray(), attribList.toArray());
    }

    /**
     *  Factory class that keeps the parse method
     */
    public static class Factory {
        /**
         * static method to create the object
         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
         *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
         * Postcondition: If this object is an element, the reader is positioned at its end element
         *                If this object is a complex type, the reader is positioned at the end element of its outer element
         */
        public static ConsentDocumentData parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            ConsentDocumentData object = new ConsentDocumentData();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix = "";
            java.lang.String namespaceuri = "";

            try {
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.getAttributeValue(
                            "http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                    java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "type");

                    if (fullTypeName != null) {
                        java.lang.String nsPrefix = null;

                        if (fullTypeName.indexOf(":") > -1) {
                            nsPrefix = fullTypeName.substring(0,
                                    fullTypeName.indexOf(":"));
                        }

                        nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                        java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                    ":") + 1);

                        if (!"ConsentDocumentData".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (ConsentDocumentData) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                java.util.ArrayList list4 = new java.util.ArrayList();

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "metadata").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setMetadata(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setMetadata(com.misyshealthcare.connect.doc.ccd.xsd.MetaData.Factory.parse(
                                reader));

                        reader.next();
                    }
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "mediaType").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setMediaType(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setMediaType(com.misyshealthcare.connect.doc.ccd.xsd.MediaType.Factory.parse(
                                reader));

                        reader.next();
                    }
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "fileContent").equals(reader.getName())) {
                    reader.next();

                    if (isReaderMTOMAware(reader) &&
                            java.lang.Boolean.TRUE.equals(reader.getProperty(
                                    org.apache.axiom.om.OMConstants.IS_BINARY))) {
                        //MTOM aware reader - get the datahandler directly and put it in the object
                        object.setFileContent((javax.activation.DataHandler) reader.getProperty(
                                org.apache.axiom.om.OMConstants.DATA_HANDLER));
                    } else {
                        if ((reader.getEventType() == javax.xml.stream.XMLStreamConstants.START_ELEMENT) &&
                                reader.getName()
                                          .equals(new javax.xml.namespace.QName(
                                        org.apache.axiom.om.impl.MTOMConstants.XOP_NAMESPACE_URI,
                                        org.apache.axiom.om.impl.MTOMConstants.XOP_INCLUDE))) {
                            java.lang.String id = org.apache.axiom.om.util.ElementHelper.getContentID(reader,
                                    "UTF-8");
                            object.setFileContent(((org.apache.axiom.soap.impl.builder.MTOMStAXSOAPModelBuilder) ((org.apache.axiom.om.impl.llom.OMStAXWrapper) reader).getBuilder()).getDataHandler(
                                    id));
                            reader.next();

                            reader.next();
                        } else if (reader.hasText()) {
                            //Do the usual conversion
                            java.lang.String content = reader.getText();
                            object.setFileContent(org.apache.axis2.databinding.utils.ConverterUtil.convertToBase64Binary(
                                    content));

                            reader.next();
                        }
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "consentPolicies").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list4.add(null);
                        reader.next();
                    } else {
                        list4.add(com.misyshealthcare.connect.base.xsd.ConfidentialityCode.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone4 = false;

                    while (!loopDone4) {
                        // We should be at the end element, but make sure
                        while (!reader.isEndElement())
                            reader.next();

                        // Step out of this element
                        reader.next();

                        // Step to next element event.
                        while (!reader.isStartElement() &&
                                !reader.isEndElement())
                            reader.next();

                        if (reader.isEndElement()) {
                            //two continuous end elements means we are exiting the xml structure
                            loopDone4 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "consentPolicies").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list4.add(null);
                                    reader.next();
                                } else {
                                    list4.add(com.misyshealthcare.connect.base.xsd.ConfidentialityCode.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone4 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setConsentPolicies((com.misyshealthcare.connect.base.xsd.ConfidentialityCode[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.xsd.ConfidentialityCode.class,
                            list4));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement()) {
                    // A start element we are not expecting indicates a trailing invalid property
                    throw new org.apache.axis2.databinding.ADBException(
                        "Unexpected subelement " + reader.getLocalName());
                }
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }
    } //end of factory class
}
