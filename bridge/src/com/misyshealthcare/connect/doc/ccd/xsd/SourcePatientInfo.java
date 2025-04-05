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
 *  SourcePatientInfo bean class
 */
public class SourcePatientInfo implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for Address
     * This was an Array!
     */
    protected com.misyshealthcare.connect.base.demographicdata.xsd.Address[] localAddress;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAddressTracker = false;

    /**
     * field for Birthdate
     */
    protected java.util.Calendar localBirthdate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBirthdateTracker = false;

    /**
     * field for Gender
     */
    protected com.misyshealthcare.connect.base.xsd.SexType localGender;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localGenderTracker = false;

    /**
     * field for Organization
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Organization localOrganization;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localOrganizationTracker = false;

    /**
     * field for PersonName
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.PersonName localPersonName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPersonNameTracker = false;

    /**
     * field for PhoneNumber
     * This was an Array!
     */
    protected com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[] localPhoneNumber;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPhoneNumberTracker = false;

    /* This type was generated from the piece of schema that had
       name = SourcePatientInfo
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
     * @return com.misyshealthcare.connect.base.demographicdata.xsd.Address[]
     */
    public com.misyshealthcare.connect.base.demographicdata.xsd.Address[] getAddress() {
        return localAddress;
    }

    /**
     * validate the array for Address
     */
    protected void validateAddress(
        com.misyshealthcare.connect.base.demographicdata.xsd.Address[] param) {
    }

    /**
     * Auto generated setter method
     * @param param Address
     */
    public void setAddress(
        com.misyshealthcare.connect.base.demographicdata.xsd.Address[] param) {
        validateAddress(param);

        if (param != null) {
            //update the setting tracker
            localAddressTracker = true;
        } else {
            localAddressTracker = true;
        }

        this.localAddress = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.base.demographicdata.xsd.Address
     */
    public void addAddress(
        com.misyshealthcare.connect.base.demographicdata.xsd.Address param) {
        if (localAddress == null) {
            localAddress = new com.misyshealthcare.connect.base.demographicdata.xsd.Address[] {
                    
                };
        }

        //update the setting tracker
        localAddressTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localAddress);
        list.add(param);
        this.localAddress = (com.misyshealthcare.connect.base.demographicdata.xsd.Address[]) list.toArray(new com.misyshealthcare.connect.base.demographicdata.xsd.Address[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getBirthdate() {
        return localBirthdate;
    }

    /**
     * Auto generated setter method
     * @param param Birthdate
     */
    public void setBirthdate(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localBirthdateTracker = true;
        } else {
            localBirthdateTracker = true;
        }

        this.localBirthdate = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.SexType
     */
    public com.misyshealthcare.connect.base.xsd.SexType getGender() {
        return localGender;
    }

    /**
     * Auto generated setter method
     * @param param Gender
     */
    public void setGender(com.misyshealthcare.connect.base.xsd.SexType param) {
        if (param != null) {
            //update the setting tracker
            localGenderTracker = true;
        } else {
            localGenderTracker = true;
        }

        this.localGender = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Organization
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Organization getOrganization() {
        return localOrganization;
    }

    /**
     * Auto generated setter method
     * @param param Organization
     */
    public void setOrganization(
        com.misyshealthcare.connect.doc.ccd.xsd.Organization param) {
        if (param != null) {
            //update the setting tracker
            localOrganizationTracker = true;
        } else {
            localOrganizationTracker = true;
        }

        this.localOrganization = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.PersonName
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.PersonName getPersonName() {
        return localPersonName;
    }

    /**
     * Auto generated setter method
     * @param param PersonName
     */
    public void setPersonName(
        com.misyshealthcare.connect.doc.ccd.xsd.PersonName param) {
        if (param != null) {
            //update the setting tracker
            localPersonNameTracker = true;
        } else {
            localPersonNameTracker = true;
        }

        this.localPersonName = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[]
     */
    public com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[] getPhoneNumber() {
        return localPhoneNumber;
    }

    /**
     * validate the array for PhoneNumber
     */
    protected void validatePhoneNumber(
        com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[] param) {
    }

    /**
     * Auto generated setter method
     * @param param PhoneNumber
     */
    public void setPhoneNumber(
        com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[] param) {
        validatePhoneNumber(param);

        if (param != null) {
            //update the setting tracker
            localPhoneNumberTracker = true;
        } else {
            localPhoneNumberTracker = true;
        }

        this.localPhoneNumber = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber
     */
    public void addPhoneNumber(
        com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber param) {
        if (localPhoneNumber == null) {
            localPhoneNumber = new com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[] {
                    
                };
        }

        //update the setting tracker
        localPhoneNumberTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localPhoneNumber);
        list.add(param);
        this.localPhoneNumber = (com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[]) list.toArray(new com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[list.size()]);
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
                    SourcePatientInfo.this.serialize(parentQName, factory,
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

        if (localAddressTracker) {
            if (localAddress != null) {
                for (int i = 0; i < localAddress.length; i++) {
                    if (localAddress[i] != null) {
                        localAddress[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "address"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2, "address",
                                    namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "address");
                            }
                        } else {
                            xmlWriter.writeStartElement("address");
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

                        xmlWriter.writeStartElement(prefix2, "address",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "address");
                    }
                } else {
                    xmlWriter.writeStartElement("address");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localBirthdateTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "birthdate", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "birthdate");
                }
            } else {
                xmlWriter.writeStartElement("birthdate");
            }

            if (localBirthdate == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localBirthdate));
            }

            xmlWriter.writeEndElement();
        }

        if (localGenderTracker) {
            if (localGender == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "gender",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "gender");
                    }
                } else {
                    xmlWriter.writeStartElement("gender");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localGender.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "gender"), factory, xmlWriter);
            }
        }

        if (localOrganizationTracker) {
            if (localOrganization == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "organization",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "organization");
                    }
                } else {
                    xmlWriter.writeStartElement("organization");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localOrganization.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "organization"), factory, xmlWriter);
            }
        }

        if (localPersonNameTracker) {
            if (localPersonName == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "personName",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "personName");
                    }
                } else {
                    xmlWriter.writeStartElement("personName");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localPersonName.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "personName"), factory, xmlWriter);
            }
        }

        if (localPhoneNumberTracker) {
            if (localPhoneNumber != null) {
                for (int i = 0; i < localPhoneNumber.length; i++) {
                    if (localPhoneNumber[i] != null) {
                        localPhoneNumber[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "phoneNumber"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "phoneNumber", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "phoneNumber");
                            }
                        } else {
                            xmlWriter.writeStartElement("phoneNumber");
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

                        xmlWriter.writeStartElement(prefix2, "phoneNumber",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "phoneNumber");
                    }
                } else {
                    xmlWriter.writeStartElement("phoneNumber");
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

        if (localAddressTracker) {
            if (localAddress != null) {
                for (int i = 0; i < localAddress.length; i++) {
                    if (localAddress[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "address"));
                        elementList.add(localAddress[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "address"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "address"));
                elementList.add(localAddress);
            }
        }

        if (localBirthdateTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "birthdate"));

            elementList.add((localBirthdate == null) ? null
                                                     : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localBirthdate));
        }

        if (localGenderTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "gender"));

            elementList.add((localGender == null) ? null : localGender);
        }

        if (localOrganizationTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "organization"));

            elementList.add((localOrganization == null) ? null : localOrganization);
        }

        if (localPersonNameTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "personName"));

            elementList.add((localPersonName == null) ? null : localPersonName);
        }

        if (localPhoneNumberTracker) {
            if (localPhoneNumber != null) {
                for (int i = 0; i < localPhoneNumber.length; i++) {
                    if (localPhoneNumber[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "phoneNumber"));
                        elementList.add(localPhoneNumber[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "phoneNumber"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "phoneNumber"));
                elementList.add(localPhoneNumber);
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
        public static SourcePatientInfo parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            SourcePatientInfo object = new SourcePatientInfo();

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

                        if (!"SourcePatientInfo".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (SourcePatientInfo) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                java.util.ArrayList list1 = new java.util.ArrayList();

                java.util.ArrayList list6 = new java.util.ArrayList();

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "address").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list1.add(null);
                        reader.next();
                    } else {
                        list1.add(com.misyshealthcare.connect.base.demographicdata.xsd.Address.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone1 = false;

                    while (!loopDone1) {
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
                            loopDone1 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "address").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list1.add(null);
                                    reader.next();
                                } else {
                                    list1.add(com.misyshealthcare.connect.base.demographicdata.xsd.Address.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone1 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setAddress((com.misyshealthcare.connect.base.demographicdata.xsd.Address[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.demographicdata.xsd.Address.class,
                            list1));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "birthdate").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setBirthdate(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "gender").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setGender(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setGender(com.misyshealthcare.connect.base.xsd.SexType.Factory.parse(
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
                            "organization").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setOrganization(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setOrganization(com.misyshealthcare.connect.doc.ccd.xsd.Organization.Factory.parse(
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
                            "personName").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setPersonName(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setPersonName(com.misyshealthcare.connect.doc.ccd.xsd.PersonName.Factory.parse(
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
                            "phoneNumber").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list6.add(null);
                        reader.next();
                    } else {
                        list6.add(com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone6 = false;

                    while (!loopDone6) {
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
                            loopDone6 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "phoneNumber").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list6.add(null);
                                    reader.next();
                                } else {
                                    list6.add(com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone6 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setPhoneNumber((com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber.class,
                            list6));
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
