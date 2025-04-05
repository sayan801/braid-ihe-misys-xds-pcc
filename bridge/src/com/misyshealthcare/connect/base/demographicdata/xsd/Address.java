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
package com.misyshealthcare.connect.base.demographicdata.xsd;


/**
 *  Address bean class
 */
public class Address implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for AddCity
     */
    protected java.lang.String localAddCity;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddCityTracker = false;

    /**
     * field for AddCountry
     */
    protected java.lang.String localAddCountry;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddCountryTracker = false;

    /**
     * field for AddLine1
     */
    protected java.lang.String localAddLine1;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddLine1Tracker = false;

    /**
     * field for AddLine2
     */
    protected java.lang.String localAddLine2;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddLine2Tracker = false;

    /**
     * field for AddState
     */
    protected java.lang.String localAddState;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddStateTracker = false;

    /**
     * field for AddType
     */
    protected com.misyshealthcare.connect.base.xsd.AddressType localAddType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddTypeTracker = false;

    /**
     * field for AddZip
     */
    protected java.lang.String localAddZip;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddZipTracker = false;

    /**
     * field for Empty
     */
    protected boolean localEmpty;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEmptyTracker = false;

    /* This type was generated from the piece of schema that had
       name = Address
       Namespace URI = http://demographicdata.base.connect.misyshealthcare.com/xsd
       Namespace Prefix = ns3
     */
    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if (namespace.equals(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd")) {
            return "ns3";
        }

        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getAddCity() {
        return localAddCity;
    }

    /**
     * Auto generated setter method
     * @param param AddCity
     */
    public void setAddCity(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localAddCityTracker = true;
        } else {
            localAddCityTracker = true;
        }

        this.localAddCity = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getAddCountry() {
        return localAddCountry;
    }

    /**
     * Auto generated setter method
     * @param param AddCountry
     */
    public void setAddCountry(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localAddCountryTracker = true;
        } else {
            localAddCountryTracker = true;
        }

        this.localAddCountry = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getAddLine1() {
        return localAddLine1;
    }

    /**
     * Auto generated setter method
     * @param param AddLine1
     */
    public void setAddLine1(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localAddLine1Tracker = true;
        } else {
            localAddLine1Tracker = true;
        }

        this.localAddLine1 = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getAddLine2() {
        return localAddLine2;
    }

    /**
     * Auto generated setter method
     * @param param AddLine2
     */
    public void setAddLine2(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localAddLine2Tracker = true;
        } else {
            localAddLine2Tracker = true;
        }

        this.localAddLine2 = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getAddState() {
        return localAddState;
    }

    /**
     * Auto generated setter method
     * @param param AddState
     */
    public void setAddState(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localAddStateTracker = true;
        } else {
            localAddStateTracker = true;
        }

        this.localAddState = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.AddressType
     */
    public com.misyshealthcare.connect.base.xsd.AddressType getAddType() {
        return localAddType;
    }

    /**
     * Auto generated setter method
     * @param param AddType
     */
    public void setAddType(
        com.misyshealthcare.connect.base.xsd.AddressType param) {
        if (param != null) {
            //update the setting tracker
            localAddTypeTracker = true;
        } else {
            localAddTypeTracker = true;
        }

        this.localAddType = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getAddZip() {
        return localAddZip;
    }

    /**
     * Auto generated setter method
     * @param param AddZip
     */
    public void setAddZip(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localAddZipTracker = true;
        } else {
            localAddZipTracker = true;
        }

        this.localAddZip = param;
    }

    /**
     * Auto generated getter method
     * @return boolean
     */
    public boolean getEmpty() {
        return localEmpty;
    }

    /**
     * Auto generated setter method
     * @param param Empty
     */
    public void setEmpty(boolean param) {
        // setting primitive attribute tracker to true
        if (false) {
            localEmptyTracker = false;
        } else {
            localEmptyTracker = true;
        }

        this.localEmpty = param;
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
                    Address.this.serialize(parentQName, factory, xmlWriter);
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

        if (localAddCityTracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "addCity", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "addCity");
                }
            } else {
                xmlWriter.writeStartElement("addCity");
            }

            if (localAddCity == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localAddCity);
            }

            xmlWriter.writeEndElement();
        }

        if (localAddCountryTracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "addCountry", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "addCountry");
                }
            } else {
                xmlWriter.writeStartElement("addCountry");
            }

            if (localAddCountry == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localAddCountry);
            }

            xmlWriter.writeEndElement();
        }

        if (localAddLine1Tracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "addLine1", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "addLine1");
                }
            } else {
                xmlWriter.writeStartElement("addLine1");
            }

            if (localAddLine1 == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localAddLine1);
            }

            xmlWriter.writeEndElement();
        }

        if (localAddLine2Tracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "addLine2", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "addLine2");
                }
            } else {
                xmlWriter.writeStartElement("addLine2");
            }

            if (localAddLine2 == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localAddLine2);
            }

            xmlWriter.writeEndElement();
        }

        if (localAddStateTracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "addState", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "addState");
                }
            } else {
                xmlWriter.writeStartElement("addState");
            }

            if (localAddState == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localAddState);
            }

            xmlWriter.writeEndElement();
        }

        if (localAddTypeTracker) {
            if (localAddType == null) {
                java.lang.String namespace2 = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "addType",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "addType");
                    }
                } else {
                    xmlWriter.writeStartElement("addType");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localAddType.serialize(new javax.xml.namespace.QName(
                        "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                        "addType"), factory, xmlWriter);
            }
        }

        if (localAddZipTracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "addZip", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "addZip");
                }
            } else {
                xmlWriter.writeStartElement("addZip");
            }

            if (localAddZip == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localAddZip);
            }

            xmlWriter.writeEndElement();
        }

        if (localEmptyTracker) {
            namespace = "http://demographicdata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "empty", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "empty");
                }
            } else {
                xmlWriter.writeStartElement("empty");
            }

            if (false) {
                throw new org.apache.axis2.databinding.ADBException(
                    "empty cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localEmpty));
            }

            xmlWriter.writeEndElement();
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

        if (localAddCityTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addCity"));

            elementList.add((localAddCity == null) ? null
                                                   : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localAddCity));
        }

        if (localAddCountryTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addCountry"));

            elementList.add((localAddCountry == null) ? null
                                                      : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localAddCountry));
        }

        if (localAddLine1Tracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addLine1"));

            elementList.add((localAddLine1 == null) ? null
                                                    : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localAddLine1));
        }

        if (localAddLine2Tracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addLine2"));

            elementList.add((localAddLine2 == null) ? null
                                                    : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localAddLine2));
        }

        if (localAddStateTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addState"));

            elementList.add((localAddState == null) ? null
                                                    : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localAddState));
        }

        if (localAddTypeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addType"));

            elementList.add((localAddType == null) ? null : localAddType);
        }

        if (localAddZipTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "addZip"));

            elementList.add((localAddZip == null) ? null
                                                  : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localAddZip));
        }

        if (localEmptyTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                    "empty"));

            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localEmpty));
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
        public static Address parse(javax.xml.stream.XMLStreamReader reader)
            throws java.lang.Exception {
            Address object = new Address();

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

                        if (!"Address".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (Address) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addCity").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setAddCity(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));
                    } else {
                        reader.getElementText(); // throw away text nodes if any.
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addCountry").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setAddCountry(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));
                    } else {
                        reader.getElementText(); // throw away text nodes if any.
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addLine1").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setAddLine1(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));
                    } else {
                        reader.getElementText(); // throw away text nodes if any.
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addLine2").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setAddLine2(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));
                    } else {
                        reader.getElementText(); // throw away text nodes if any.
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addState").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setAddState(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));
                    } else {
                        reader.getElementText(); // throw away text nodes if any.
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addType").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setAddType(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setAddType(com.misyshealthcare.connect.base.xsd.AddressType.Factory.parse(
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
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "addZip").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setAddZip(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));
                    } else {
                        reader.getElementText(); // throw away text nodes if any.
                    }

                    reader.next();
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://demographicdata.base.connect.misyshealthcare.com/xsd",
                            "empty").equals(reader.getName())) {
                    java.lang.String content = reader.getElementText();

                    object.setEmpty(org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(
                            content));

                    reader.next();
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
