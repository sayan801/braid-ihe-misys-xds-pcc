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
package com.misyshealthcare.connect.base.clinicaldata.xsd;


/**
 *  Medication bean class
 */
public class Medication implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for Code
     */
    protected com.misyshealthcare.connect.base.clinicaldata.xsd.Code localCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodeTracker = false;

    /**
     * field for Date
     */
    protected java.util.Calendar localDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateTracker = false;

    /**
     * field for Dose
     */
    protected com.misyshealthcare.connect.base.clinicaldata.xsd.DoseQuantity localDose;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDoseTracker = false;

    /**
     * field for Duration
     */
    protected java.lang.String localDuration;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDurationTracker = false;

    /**
     * field for Frequency
     */
    protected java.lang.String localFrequency;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFrequencyTracker = false;

    /**
     * field for LastAdministrationDate
     */
    protected java.util.Calendar localLastAdministrationDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localLastAdministrationDateTracker = false;

    /**
     * field for ProblemCode
     */
    protected com.misyshealthcare.connect.base.clinicaldata.xsd.Code localProblemCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localProblemCodeTracker = false;

    /**
     * field for ProductName
     */
    protected java.lang.String localProductName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localProductNameTracker = false;

    /**
     * field for Refills
     */
    protected java.lang.String localRefills;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRefillsTracker = false;

    /**
     * field for RouteCode
     */
    protected com.misyshealthcare.connect.base.clinicaldata.xsd.Code localRouteCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRouteCodeTracker = false;

    /**
     * field for Sig
     */
    protected java.lang.String localSig;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSigTracker = false;

    /**
     * field for Source
     */
    protected java.lang.String localSource;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSourceTracker = false;

    /**
     * field for Status
     */
    protected java.lang.String localStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatusTracker = false;

    /* This type was generated from the piece of schema that had
       name = Medication
       Namespace URI = http://clinicaldata.base.connect.misyshealthcare.com/xsd
       Namespace Prefix = ns4
     */
    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if (namespace.equals(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd")) {
            return "ns4";
        }

        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.clinicaldata.xsd.Code
     */
    public com.misyshealthcare.connect.base.clinicaldata.xsd.Code getCode() {
        return localCode;
    }

    /**
     * Auto generated setter method
     * @param param Code
     */
    public void setCode(
        com.misyshealthcare.connect.base.clinicaldata.xsd.Code param) {
        if (param != null) {
            //update the setting tracker
            localCodeTracker = true;
        } else {
            localCodeTracker = true;
        }

        this.localCode = param;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDate() {
        return localDate;
    }

    /**
     * Auto generated setter method
     * @param param Date
     */
    public void setDate(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localDateTracker = true;
        } else {
            localDateTracker = true;
        }

        this.localDate = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.clinicaldata.xsd.DoseQuantity
     */
    public com.misyshealthcare.connect.base.clinicaldata.xsd.DoseQuantity getDose() {
        return localDose;
    }

    /**
     * Auto generated setter method
     * @param param Dose
     */
    public void setDose(
        com.misyshealthcare.connect.base.clinicaldata.xsd.DoseQuantity param) {
        if (param != null) {
            //update the setting tracker
            localDoseTracker = true;
        } else {
            localDoseTracker = true;
        }

        this.localDose = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDuration() {
        return localDuration;
    }

    /**
     * Auto generated setter method
     * @param param Duration
     */
    public void setDuration(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localDurationTracker = true;
        } else {
            localDurationTracker = true;
        }

        this.localDuration = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getFrequency() {
        return localFrequency;
    }

    /**
     * Auto generated setter method
     * @param param Frequency
     */
    public void setFrequency(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localFrequencyTracker = true;
        } else {
            localFrequencyTracker = true;
        }

        this.localFrequency = param;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getLastAdministrationDate() {
        return localLastAdministrationDate;
    }

    /**
     * Auto generated setter method
     * @param param LastAdministrationDate
     */
    public void setLastAdministrationDate(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localLastAdministrationDateTracker = true;
        } else {
            localLastAdministrationDateTracker = true;
        }

        this.localLastAdministrationDate = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.clinicaldata.xsd.Code
     */
    public com.misyshealthcare.connect.base.clinicaldata.xsd.Code getProblemCode() {
        return localProblemCode;
    }

    /**
     * Auto generated setter method
     * @param param ProblemCode
     */
    public void setProblemCode(
        com.misyshealthcare.connect.base.clinicaldata.xsd.Code param) {
        if (param != null) {
            //update the setting tracker
            localProblemCodeTracker = true;
        } else {
            localProblemCodeTracker = true;
        }

        this.localProblemCode = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getProductName() {
        return localProductName;
    }

    /**
     * Auto generated setter method
     * @param param ProductName
     */
    public void setProductName(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localProductNameTracker = true;
        } else {
            localProductNameTracker = true;
        }

        this.localProductName = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getRefills() {
        return localRefills;
    }

    /**
     * Auto generated setter method
     * @param param Refills
     */
    public void setRefills(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localRefillsTracker = true;
        } else {
            localRefillsTracker = true;
        }

        this.localRefills = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.clinicaldata.xsd.Code
     */
    public com.misyshealthcare.connect.base.clinicaldata.xsd.Code getRouteCode() {
        return localRouteCode;
    }

    /**
     * Auto generated setter method
     * @param param RouteCode
     */
    public void setRouteCode(
        com.misyshealthcare.connect.base.clinicaldata.xsd.Code param) {
        if (param != null) {
            //update the setting tracker
            localRouteCodeTracker = true;
        } else {
            localRouteCodeTracker = true;
        }

        this.localRouteCode = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getSig() {
        return localSig;
    }

    /**
     * Auto generated setter method
     * @param param Sig
     */
    public void setSig(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localSigTracker = true;
        } else {
            localSigTracker = true;
        }

        this.localSig = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getSource() {
        return localSource;
    }

    /**
     * Auto generated setter method
     * @param param Source
     */
    public void setSource(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localSourceTracker = true;
        } else {
            localSourceTracker = true;
        }

        this.localSource = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getStatus() {
        return localStatus;
    }

    /**
     * Auto generated setter method
     * @param param Status
     */
    public void setStatus(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localStatusTracker = true;
        } else {
            localStatusTracker = true;
        }

        this.localStatus = param;
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
                    Medication.this.serialize(parentQName, factory, xmlWriter);
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

        if (localCodeTracker) {
            if (localCode == null) {
                java.lang.String namespace2 = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "code", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "code");
                    }
                } else {
                    xmlWriter.writeStartElement("code");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localCode.serialize(new javax.xml.namespace.QName(
                        "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                        "code"), factory, xmlWriter);
            }
        }

        if (localDateTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "date", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "date");
                }
            } else {
                xmlWriter.writeStartElement("date");
            }

            if (localDate == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDate));
            }

            xmlWriter.writeEndElement();
        }

        if (localDoseTracker) {
            if (localDose == null) {
                java.lang.String namespace2 = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "dose", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "dose");
                    }
                } else {
                    xmlWriter.writeStartElement("dose");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localDose.serialize(new javax.xml.namespace.QName(
                        "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                        "dose"), factory, xmlWriter);
            }
        }

        if (localDurationTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "duration", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "duration");
                }
            } else {
                xmlWriter.writeStartElement("duration");
            }

            if (localDuration == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localDuration);
            }

            xmlWriter.writeEndElement();
        }

        if (localFrequencyTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "frequency", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "frequency");
                }
            } else {
                xmlWriter.writeStartElement("frequency");
            }

            if (localFrequency == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localFrequency);
            }

            xmlWriter.writeEndElement();
        }

        if (localLastAdministrationDateTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix,
                        "lastAdministrationDate", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace,
                        "lastAdministrationDate");
                }
            } else {
                xmlWriter.writeStartElement("lastAdministrationDate");
            }

            if (localLastAdministrationDate == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localLastAdministrationDate));
            }

            xmlWriter.writeEndElement();
        }

        if (localProblemCodeTracker) {
            if (localProblemCode == null) {
                java.lang.String namespace2 = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "problemCode",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "problemCode");
                    }
                } else {
                    xmlWriter.writeStartElement("problemCode");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localProblemCode.serialize(new javax.xml.namespace.QName(
                        "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                        "problemCode"), factory, xmlWriter);
            }
        }

        if (localProductNameTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "productName", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "productName");
                }
            } else {
                xmlWriter.writeStartElement("productName");
            }

            if (localProductName == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localProductName);
            }

            xmlWriter.writeEndElement();
        }

        if (localRefillsTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "refills", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "refills");
                }
            } else {
                xmlWriter.writeStartElement("refills");
            }

            if (localRefills == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localRefills);
            }

            xmlWriter.writeEndElement();
        }

        if (localRouteCodeTracker) {
            if (localRouteCode == null) {
                java.lang.String namespace2 = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "routeCode",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "routeCode");
                    }
                } else {
                    xmlWriter.writeStartElement("routeCode");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localRouteCode.serialize(new javax.xml.namespace.QName(
                        "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                        "routeCode"), factory, xmlWriter);
            }
        }

        if (localSigTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "sig", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "sig");
                }
            } else {
                xmlWriter.writeStartElement("sig");
            }

            if (localSig == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localSig);
            }

            xmlWriter.writeEndElement();
        }

        if (localSourceTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "source", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "source");
                }
            } else {
                xmlWriter.writeStartElement("source");
            }

            if (localSource == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localSource);
            }

            xmlWriter.writeEndElement();
        }

        if (localStatusTracker) {
            namespace = "http://clinicaldata.base.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "status", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "status");
                }
            } else {
                xmlWriter.writeStartElement("status");
            }

            if (localStatus == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localStatus);
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

        if (localCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "code"));

            elementList.add((localCode == null) ? null : localCode);
        }

        if (localDateTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "date"));

            elementList.add((localDate == null) ? null
                                                : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localDate));
        }

        if (localDoseTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "dose"));

            elementList.add((localDose == null) ? null : localDose);
        }

        if (localDurationTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "duration"));

            elementList.add((localDuration == null) ? null
                                                    : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localDuration));
        }

        if (localFrequencyTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "frequency"));

            elementList.add((localFrequency == null) ? null
                                                     : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localFrequency));
        }

        if (localLastAdministrationDateTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "lastAdministrationDate"));

            elementList.add((localLastAdministrationDate == null) ? null
                                                                  : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localLastAdministrationDate));
        }

        if (localProblemCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "problemCode"));

            elementList.add((localProblemCode == null) ? null : localProblemCode);
        }

        if (localProductNameTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "productName"));

            elementList.add((localProductName == null) ? null
                                                       : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localProductName));
        }

        if (localRefillsTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "refills"));

            elementList.add((localRefills == null) ? null
                                                   : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localRefills));
        }

        if (localRouteCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "routeCode"));

            elementList.add((localRouteCode == null) ? null : localRouteCode);
        }

        if (localSigTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "sig"));

            elementList.add((localSig == null) ? null
                                               : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localSig));
        }

        if (localSourceTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "source"));

            elementList.add((localSource == null) ? null
                                                  : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localSource));
        }

        if (localStatusTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                    "status"));

            elementList.add((localStatus == null) ? null
                                                  : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localStatus));
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
        public static Medication parse(javax.xml.stream.XMLStreamReader reader)
            throws java.lang.Exception {
            Medication object = new Medication();

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

                        if (!"Medication".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (Medication) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "code").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setCode(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setCode(com.misyshealthcare.connect.base.clinicaldata.xsd.Code.Factory.parse(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "date").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "dose").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setDose(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setDose(com.misyshealthcare.connect.base.clinicaldata.xsd.DoseQuantity.Factory.parse(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "duration").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setDuration(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "frequency").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setFrequency(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "lastAdministrationDate").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setLastAdministrationDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "problemCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setProblemCode(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setProblemCode(com.misyshealthcare.connect.base.clinicaldata.xsd.Code.Factory.parse(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "productName").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setProductName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "refills").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setRefills(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "routeCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setRouteCode(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setRouteCode(com.misyshealthcare.connect.base.clinicaldata.xsd.Code.Factory.parse(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "sig").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setSig(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "source").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setSource(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "http://clinicaldata.base.connect.misyshealthcare.com/xsd",
                            "status").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setStatus(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
