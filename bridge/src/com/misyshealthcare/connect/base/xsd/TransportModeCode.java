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
package com.misyshealthcare.connect.base.xsd;


/**
 *  TransportModeCode bean class
 */
public class TransportModeCode implements org.apache.axis2.databinding.ADBBean {
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://base.connect.misyshealthcare.com/xsd",
            "TransportModeCode", "ns2");
    private static java.util.HashMap _table_ = new java.util.HashMap();
    public static final java.lang.String _FIXED_WING_AIR_AMBULANCE = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "FIXED_WING_AIR_AMBULANCE");
    public static final java.lang.String _GROUND_AMBULANCE = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "GROUND_AMBULANCE");
    public static final java.lang.String _HELICOPTER_AMBULANCE = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "HELICOPTER_AMBULANCE");
    public static final java.lang.String _OTHER = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "OTHER");
    public static final java.lang.String _OTHER_AMBULANCE = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "OTHER_AMBULANCE");
    public static final java.lang.String _OTHER_WALKIN = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "OTHER_WALKIN");
    public static final java.lang.String _UNKNOWN = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "UNKNOWN");
    public static final java.lang.String _WALKIN_FOLLOWING_NONAMBULANCE = org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "WALKIN_FOLLOWING_NONAMBULANCE");
    public static final java.lang.String _WALKIN_FOLLOWING_TRANSPORT_VIA_PRIVATE_TRANSPORTATION =
        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "WALKIN_FOLLOWING_TRANSPORT_VIA_PRIVATE_TRANSPORTATION");
    public static final java.lang.String _WALKIN_FOLLOWING_TRANSPORT_VIA_PUBLIC_TRANSPORTATION =
        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
            "WALKIN_FOLLOWING_TRANSPORT_VIA_PUBLIC_TRANSPORTATION");
    public static final TransportModeCode FIXED_WING_AIR_AMBULANCE = new TransportModeCode(_FIXED_WING_AIR_AMBULANCE,
            true);
    public static final TransportModeCode GROUND_AMBULANCE = new TransportModeCode(_GROUND_AMBULANCE,
            true);
    public static final TransportModeCode HELICOPTER_AMBULANCE = new TransportModeCode(_HELICOPTER_AMBULANCE,
            true);
    public static final TransportModeCode OTHER = new TransportModeCode(_OTHER,
            true);
    public static final TransportModeCode OTHER_AMBULANCE = new TransportModeCode(_OTHER_AMBULANCE,
            true);
    public static final TransportModeCode OTHER_WALKIN = new TransportModeCode(_OTHER_WALKIN,
            true);
    public static final TransportModeCode UNKNOWN = new TransportModeCode(_UNKNOWN,
            true);
    public static final TransportModeCode WALKIN_FOLLOWING_NONAMBULANCE = new TransportModeCode(_WALKIN_FOLLOWING_NONAMBULANCE,
            true);
    public static final TransportModeCode WALKIN_FOLLOWING_TRANSPORT_VIA_PRIVATE_TRANSPORTATION =
        new TransportModeCode(_WALKIN_FOLLOWING_TRANSPORT_VIA_PRIVATE_TRANSPORTATION,
            true);
    public static final TransportModeCode WALKIN_FOLLOWING_TRANSPORT_VIA_PUBLIC_TRANSPORTATION =
        new TransportModeCode(_WALKIN_FOLLOWING_TRANSPORT_VIA_PUBLIC_TRANSPORTATION,
            true);

    /**
     * field for TransportModeCode
     */
    protected java.lang.String localTransportModeCode;

    // Constructor
    protected TransportModeCode(java.lang.String value, boolean isRegisterValue) {
        localTransportModeCode = value;

        if (isRegisterValue) {
            _table_.put(localTransportModeCode, this);
        }
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if (namespace.equals("http://base.connect.misyshealthcare.com/xsd")) {
            return "ns2";
        }

        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    public java.lang.String getValue() {
        return localTransportModeCode;
    }

    public boolean equals(java.lang.Object obj) {
        return (obj == this);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public java.lang.String toString() {
        return localTransportModeCode.toString();
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
                MY_QNAME) {
                public void serialize(
                    org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                    throws javax.xml.stream.XMLStreamException {
                    TransportModeCode.this.serialize(MY_QNAME, factory,
                        xmlWriter);
                }
            };

        return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
            factory, dataSource);
    }

    public void serialize(final javax.xml.namespace.QName parentQName,
        final org.apache.axiom.om.OMFactory factory,
        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException,
            org.apache.axis2.databinding.ADBException {
        //We can safely assume an element has only one type associated with it
        java.lang.String namespace = parentQName.getNamespaceURI();
        java.lang.String localName = parentQName.getLocalPart();

        if (!namespace.equals("")) {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                xmlWriter.writeStartElement(prefix, localName, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            } else {
                xmlWriter.writeStartElement(namespace, localName);
            }
        } else {
            xmlWriter.writeStartElement(localName);
        }

        if (localTransportModeCode == null) {
            throw new org.apache.axis2.databinding.ADBException(
                "Value cannot be null !!");
        } else {
            xmlWriter.writeCharacters(localTransportModeCode);
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
        //We can safely assume an element has only one type associated with it
        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(MY_QNAME,
            new java.lang.Object[] {
                org.apache.axis2.databinding.utils.reader.ADBXMLStreamReader.ELEMENT_TEXT,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localTransportModeCode)
            }, null);
    }

    /**
     *  Factory class that keeps the parse method
     */
    public static class Factory {
        public static TransportModeCode fromValue(java.lang.String value)
            throws java.lang.IllegalArgumentException {
            TransportModeCode enumeration = (TransportModeCode) _table_.get(value);

            if (enumeration == null) {
                throw new java.lang.IllegalArgumentException();
            }

            return enumeration;
        }

        public static TransportModeCode fromString(java.lang.String value,
            java.lang.String namespaceURI)
            throws java.lang.IllegalArgumentException {
            try {
                return fromValue(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        value));
            } catch (java.lang.Exception e) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        /**
         * static method to create the object
         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
         *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
         * Postcondition: If this object is an element, the reader is positioned at its end element
         *                If this object is a complex type, the reader is positioned at the end element of its outer element
         */
        public static TransportModeCode parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            TransportModeCode object = null;

            // initialize a hash map to keep values
            java.util.Map attributeMap = new java.util.HashMap();
            java.util.List extraAttributeList = new java.util.ArrayList();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix = "";
            java.lang.String namespaceuri = "";

            try {
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                while (!reader.isEndElement()) {
                    if (reader.isStartElement() || reader.hasText()) {
                        java.lang.String content = reader.getElementText();

                        if (content.indexOf(":") > 0) {
                            // this seems to be a Qname so find the namespace and send
                            prefix = content.substring(0, content.indexOf(":"));
                            namespaceuri = reader.getNamespaceURI(prefix);
                            object = TransportModeCode.Factory.fromString(content,
                                    namespaceuri);
                        } else {
                            // this seems to be not a qname send and empty namespace incase of it is
                            // check is done in fromString method
                            object = TransportModeCode.Factory.fromString(content,
                                    "");
                        }
                    } else {
                        reader.next();
                    }
                } // end of while loop
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }
    } //end of factory class
}
