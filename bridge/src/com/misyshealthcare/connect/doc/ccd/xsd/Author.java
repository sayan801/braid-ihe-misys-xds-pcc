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
 *  Author bean class
 */
public class Author implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for AuthorDevice
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice localAuthorDevice;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAuthorDeviceTracker = false;

    /**
     * field for AuthorPerson
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Participant localAuthorPerson;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAuthorPersonTracker = false;

    /**
     * field for AuthorRoles
     * This was an Array!
     */
    protected java.lang.String[] localAuthorRoles;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAuthorRolesTracker = false;

    /**
     * field for AuthorSpecialities
     * This was an Array!
     */
    protected java.lang.String[] localAuthorSpecialities;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAuthorSpecialitiesTracker = false;

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
     * field for StartParticipationTime
     */
    protected java.util.Calendar localStartParticipationTime;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStartParticipationTimeTracker = false;

    /* This type was generated from the piece of schema that had
       name = Author
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
     * @return com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice getAuthorDevice() {
        return localAuthorDevice;
    }

    /**
     * Auto generated setter method
     * @param param AuthorDevice
     */
    public void setAuthorDevice(
        com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice param) {
        if (param != null) {
            //update the setting tracker
            localAuthorDeviceTracker = true;
        } else {
            localAuthorDeviceTracker = true;
        }

        this.localAuthorDevice = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Participant
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Participant getAuthorPerson() {
        return localAuthorPerson;
    }

    /**
     * Auto generated setter method
     * @param param AuthorPerson
     */
    public void setAuthorPerson(
        com.misyshealthcare.connect.doc.ccd.xsd.Participant param) {
        if (param != null) {
            //update the setting tracker
            localAuthorPersonTracker = true;
        } else {
            localAuthorPersonTracker = true;
        }

        this.localAuthorPerson = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String[]
     */
    public java.lang.String[] getAuthorRoles() {
        return localAuthorRoles;
    }

    /**
     * validate the array for AuthorRoles
     */
    protected void validateAuthorRoles(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param AuthorRoles
     */
    public void setAuthorRoles(java.lang.String[] param) {
        validateAuthorRoles(param);

        if (param != null) {
            //update the setting tracker
            localAuthorRolesTracker = true;
        } else {
            localAuthorRolesTracker = true;
        }

        this.localAuthorRoles = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addAuthorRoles(java.lang.String param) {
        if (localAuthorRoles == null) {
            localAuthorRoles = new java.lang.String[] {  };
        }

        //update the setting tracker
        localAuthorRolesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localAuthorRoles);
        list.add(param);
        this.localAuthorRoles = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String[]
     */
    public java.lang.String[] getAuthorSpecialities() {
        return localAuthorSpecialities;
    }

    /**
     * validate the array for AuthorSpecialities
     */
    protected void validateAuthorSpecialities(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param AuthorSpecialities
     */
    public void setAuthorSpecialities(java.lang.String[] param) {
        validateAuthorSpecialities(param);

        if (param != null) {
            //update the setting tracker
            localAuthorSpecialitiesTracker = true;
        } else {
            localAuthorSpecialitiesTracker = true;
        }

        this.localAuthorSpecialities = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addAuthorSpecialities(java.lang.String param) {
        if (localAuthorSpecialities == null) {
            localAuthorSpecialities = new java.lang.String[] {  };
        }

        //update the setting tracker
        localAuthorSpecialitiesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localAuthorSpecialities);
        list.add(param);
        this.localAuthorSpecialities = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
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
     * @return java.util.Calendar
     */
    public java.util.Calendar getStartParticipationTime() {
        return localStartParticipationTime;
    }

    /**
     * Auto generated setter method
     * @param param StartParticipationTime
     */
    public void setStartParticipationTime(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localStartParticipationTimeTracker = true;
        } else {
            localStartParticipationTimeTracker = true;
        }

        this.localStartParticipationTime = param;
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
                    Author.this.serialize(parentQName, factory, xmlWriter);
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

        if (localAuthorDeviceTracker) {
            if (localAuthorDevice == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "authorDevice",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "authorDevice");
                    }
                } else {
                    xmlWriter.writeStartElement("authorDevice");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localAuthorDevice.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "authorDevice"), factory, xmlWriter);
            }
        }

        if (localAuthorPersonTracker) {
            if (localAuthorPerson == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "authorPerson",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "authorPerson");
                    }
                } else {
                    xmlWriter.writeStartElement("authorPerson");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localAuthorPerson.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "authorPerson"), factory, xmlWriter);
            }
        }

        if (localAuthorRolesTracker) {
            if (localAuthorRoles != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localAuthorRoles.length; i++) {
                    if (localAuthorRoles[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "authorRoles", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "authorRoles");
                            }
                        } else {
                            xmlWriter.writeStartElement("authorRoles");
                        }

                        xmlWriter.writeCharacters(localAuthorRoles[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "authorRoles", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "authorRoles");
                            }
                        } else {
                            xmlWriter.writeStartElement("authorRoles");
                        }

                        writeAttribute("xsi",
                            "http://www.w3.org/2001/XMLSchema-instance", "nil",
                            "1", xmlWriter);
                        xmlWriter.writeEndElement();
                    }
                }
            } else {
                // write the null attribute
                // write null attribute
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "authorRoles",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "authorRoles");
                    }
                } else {
                    xmlWriter.writeStartElement("authorRoles");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localAuthorSpecialitiesTracker) {
            if (localAuthorSpecialities != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localAuthorSpecialities.length; i++) {
                    if (localAuthorSpecialities[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "authorSpecialities", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "authorSpecialities");
                            }
                        } else {
                            xmlWriter.writeStartElement("authorSpecialities");
                        }

                        xmlWriter.writeCharacters(localAuthorSpecialities[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "authorSpecialities", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "authorSpecialities");
                            }
                        } else {
                            xmlWriter.writeStartElement("authorSpecialities");
                        }

                        writeAttribute("xsi",
                            "http://www.w3.org/2001/XMLSchema-instance", "nil",
                            "1", xmlWriter);
                        xmlWriter.writeEndElement();
                    }
                }
            } else {
                // write the null attribute
                // write null attribute
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2,
                            "authorSpecialities", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "authorSpecialities");
                    }
                } else {
                    xmlWriter.writeStartElement("authorSpecialities");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
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

        if (localStartParticipationTimeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix,
                        "startParticipationTime", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace,
                        "startParticipationTime");
                }
            } else {
                xmlWriter.writeStartElement("startParticipationTime");
            }

            if (localStartParticipationTime == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localStartParticipationTime));
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

        if (localAuthorDeviceTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "authorDevice"));

            elementList.add((localAuthorDevice == null) ? null : localAuthorDevice);
        }

        if (localAuthorPersonTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "authorPerson"));

            elementList.add((localAuthorPerson == null) ? null : localAuthorPerson);
        }

        if (localAuthorRolesTracker) {
            if (localAuthorRoles != null) {
                for (int i = 0; i < localAuthorRoles.length; i++) {
                    if (localAuthorRoles[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authorRoles"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localAuthorRoles[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authorRoles"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "authorRoles"));
                elementList.add(null);
            }
        }

        if (localAuthorSpecialitiesTracker) {
            if (localAuthorSpecialities != null) {
                for (int i = 0; i < localAuthorSpecialities.length; i++) {
                    if (localAuthorSpecialities[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authorSpecialities"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localAuthorSpecialities[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authorSpecialities"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "authorSpecialities"));
                elementList.add(null);
            }
        }

        if (localOrganizationTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "organization"));

            elementList.add((localOrganization == null) ? null : localOrganization);
        }

        if (localStartParticipationTimeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "startParticipationTime"));

            elementList.add((localStartParticipationTime == null) ? null
                                                                  : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localStartParticipationTime));
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
        public static Author parse(javax.xml.stream.XMLStreamReader reader)
            throws java.lang.Exception {
            Author object = new Author();

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

                        if (!"Author".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (Author) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                java.util.ArrayList list3 = new java.util.ArrayList();

                java.util.ArrayList list4 = new java.util.ArrayList();

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "authorDevice").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setAuthorDevice(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setAuthorDevice(com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice.Factory.parse(
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
                            "authorPerson").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setAuthorPerson(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setAuthorPerson(com.misyshealthcare.connect.doc.ccd.xsd.Participant.Factory.parse(
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
                            "authorRoles").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list3.add(null);

                        reader.next();
                    } else {
                        list3.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone3 = false;

                    while (!loopDone3) {
                        // Ensure we are at the EndElement
                        while (!reader.isEndElement()) {
                            reader.next();
                        }

                        // Step out of this element
                        reader.next();

                        // Step to next element event.
                        while (!reader.isStartElement() &&
                                !reader.isEndElement())
                            reader.next();

                        if (reader.isEndElement()) {
                            //two continuous end elements means we are exiting the xml structure
                            loopDone3 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "authorRoles").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list3.add(null);

                                    reader.next();
                                } else {
                                    list3.add(reader.getElementText());
                                }
                            } else {
                                loopDone3 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setAuthorRoles((java.lang.String[]) list3.toArray(
                            new java.lang.String[list3.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "authorSpecialities").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list4.add(null);

                        reader.next();
                    } else {
                        list4.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone4 = false;

                    while (!loopDone4) {
                        // Ensure we are at the EndElement
                        while (!reader.isEndElement()) {
                            reader.next();
                        }

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
                                        "authorSpecialities").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list4.add(null);

                                    reader.next();
                                } else {
                                    list4.add(reader.getElementText());
                                }
                            } else {
                                loopDone4 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setAuthorSpecialities((java.lang.String[]) list4.toArray(
                            new java.lang.String[list4.size()]));
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
                            "startParticipationTime").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setStartParticipationTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
