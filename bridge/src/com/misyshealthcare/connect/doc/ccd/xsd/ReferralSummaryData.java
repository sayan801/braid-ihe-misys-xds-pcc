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
 *  ReferralSummaryData bean class
 */
public class ReferralSummaryData extends com.misyshealthcare.connect.doc.ccd.xsd.XDSMSData
    implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for FamilyHistories
     * This was an Array!
     */
    protected java.lang.String[] localFamilyHistories;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFamilyHistoriesTracker = false;

    /**
     * field for Immunizations
     * This was an Array!
     */
    protected com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[] localImmunizations;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localImmunizationsTracker = false;

    /**
     * field for LabResults
     * This was an Array!
     */
    protected com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[] localLabResults;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localLabResultsTracker = false;

    /**
     * field for ReferralReason
     */
    protected java.lang.String localReferralReason;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localReferralReasonTracker = false;

    /**
     * field for SocialHistories
     * This was an Array!
     */
    protected java.lang.String[] localSocialHistories;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSocialHistoriesTracker = false;

    /**
     * field for SurgicalHistories
     * This was an Array!
     */
    protected java.lang.String[] localSurgicalHistories;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSurgicalHistoriesTracker = false;

    /* This type was generated from the piece of schema that had
       name = ReferralSummaryData
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
     * @return java.lang.String[]
     */
    public java.lang.String[] getFamilyHistories() {
        return localFamilyHistories;
    }

    /**
     * validate the array for FamilyHistories
     */
    protected void validateFamilyHistories(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param FamilyHistories
     */
    public void setFamilyHistories(java.lang.String[] param) {
        validateFamilyHistories(param);

        if (param != null) {
            //update the setting tracker
            localFamilyHistoriesTracker = true;
        } else {
            localFamilyHistoriesTracker = true;
        }

        this.localFamilyHistories = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addFamilyHistories(java.lang.String param) {
        if (localFamilyHistories == null) {
            localFamilyHistories = new java.lang.String[] {  };
        }

        //update the setting tracker
        localFamilyHistoriesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localFamilyHistories);
        list.add(param);
        this.localFamilyHistories = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[]
     */
    public com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[] getImmunizations() {
        return localImmunizations;
    }

    /**
     * validate the array for Immunizations
     */
    protected void validateImmunizations(
        com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[] param) {
    }

    /**
     * Auto generated setter method
     * @param param Immunizations
     */
    public void setImmunizations(
        com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[] param) {
        validateImmunizations(param);

        if (param != null) {
            //update the setting tracker
            localImmunizationsTracker = true;
        } else {
            localImmunizationsTracker = true;
        }

        this.localImmunizations = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization
     */
    public void addImmunizations(
        com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization param) {
        if (localImmunizations == null) {
            localImmunizations = new com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[] {
                    
                };
        }

        //update the setting tracker
        localImmunizationsTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localImmunizations);
        list.add(param);
        this.localImmunizations = (com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[]) list.toArray(new com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[]
     */
    public com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[] getLabResults() {
        return localLabResults;
    }

    /**
     * validate the array for LabResults
     */
    protected void validateLabResults(
        com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[] param) {
    }

    /**
     * Auto generated setter method
     * @param param LabResults
     */
    public void setLabResults(
        com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[] param) {
        validateLabResults(param);

        if (param != null) {
            //update the setting tracker
            localLabResultsTracker = true;
        } else {
            localLabResultsTracker = true;
        }

        this.localLabResults = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult
     */
    public void addLabResults(
        com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult param) {
        if (localLabResults == null) {
            localLabResults = new com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[] {
                    
                };
        }

        //update the setting tracker
        localLabResultsTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localLabResults);
        list.add(param);
        this.localLabResults = (com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[]) list.toArray(new com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getReferralReason() {
        return localReferralReason;
    }

    /**
     * Auto generated setter method
     * @param param ReferralReason
     */
    public void setReferralReason(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localReferralReasonTracker = true;
        } else {
            localReferralReasonTracker = true;
        }

        this.localReferralReason = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String[]
     */
    public java.lang.String[] getSocialHistories() {
        return localSocialHistories;
    }

    /**
     * validate the array for SocialHistories
     */
    protected void validateSocialHistories(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param SocialHistories
     */
    public void setSocialHistories(java.lang.String[] param) {
        validateSocialHistories(param);

        if (param != null) {
            //update the setting tracker
            localSocialHistoriesTracker = true;
        } else {
            localSocialHistoriesTracker = true;
        }

        this.localSocialHistories = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addSocialHistories(java.lang.String param) {
        if (localSocialHistories == null) {
            localSocialHistories = new java.lang.String[] {  };
        }

        //update the setting tracker
        localSocialHistoriesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localSocialHistories);
        list.add(param);
        this.localSocialHistories = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String[]
     */
    public java.lang.String[] getSurgicalHistories() {
        return localSurgicalHistories;
    }

    /**
     * validate the array for SurgicalHistories
     */
    protected void validateSurgicalHistories(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param SurgicalHistories
     */
    public void setSurgicalHistories(java.lang.String[] param) {
        validateSurgicalHistories(param);

        if (param != null) {
            //update the setting tracker
            localSurgicalHistoriesTracker = true;
        } else {
            localSurgicalHistoriesTracker = true;
        }

        this.localSurgicalHistories = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addSurgicalHistories(java.lang.String param) {
        if (localSurgicalHistories == null) {
            localSurgicalHistories = new java.lang.String[] {  };
        }

        //update the setting tracker
        localSurgicalHistoriesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localSurgicalHistories);
        list.add(param);
        this.localSurgicalHistories = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
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
                    ReferralSummaryData.this.serialize(parentQName, factory,
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
                "type", namespacePrefix + ":ReferralSummaryData", xmlWriter);
        } else {
            writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
                "type", "ReferralSummaryData", xmlWriter);
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

        if (localActiveProblemsTracker) {
            if (localActiveProblems != null) {
                for (int i = 0; i < localActiveProblems.length; i++) {
                    if (localActiveProblems[i] != null) {
                        localActiveProblems[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "activeProblems"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "activeProblems", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "activeProblems");
                            }
                        } else {
                            xmlWriter.writeStartElement("activeProblems");
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

                        xmlWriter.writeStartElement(prefix2, "activeProblems",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "activeProblems");
                    }
                } else {
                    xmlWriter.writeStartElement("activeProblems");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localAdvanceDirectivesTracker) {
            if (localAdvanceDirectives != null) {
                for (int i = 0; i < localAdvanceDirectives.length; i++) {
                    if (localAdvanceDirectives[i] != null) {
                        localAdvanceDirectives[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "advanceDirectives"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "advanceDirectives", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "advanceDirectives");
                            }
                        } else {
                            xmlWriter.writeStartElement("advanceDirectives");
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

                        xmlWriter.writeStartElement(prefix2,
                            "advanceDirectives", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "advanceDirectives");
                    }
                } else {
                    xmlWriter.writeStartElement("advanceDirectives");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localAllergiesTracker) {
            if (localAllergies != null) {
                for (int i = 0; i < localAllergies.length; i++) {
                    if (localAllergies[i] != null) {
                        localAllergies[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "allergies"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "allergies", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "allergies");
                            }
                        } else {
                            xmlWriter.writeStartElement("allergies");
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

                        xmlWriter.writeStartElement(prefix2, "allergies",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "allergies");
                    }
                } else {
                    xmlWriter.writeStartElement("allergies");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localHistoryOfPresentIllnessTracker) {
            if (localHistoryOfPresentIllness != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localHistoryOfPresentIllness.length; i++) {
                    if (localHistoryOfPresentIllness[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "historyOfPresentIllness", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "historyOfPresentIllness");
                            }
                        } else {
                            xmlWriter.writeStartElement(
                                "historyOfPresentIllness");
                        }

                        xmlWriter.writeCharacters(localHistoryOfPresentIllness[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "historyOfPresentIllness", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "historyOfPresentIllness");
                            }
                        } else {
                            xmlWriter.writeStartElement(
                                "historyOfPresentIllness");
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
                            "historyOfPresentIllness", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "historyOfPresentIllness");
                    }
                } else {
                    xmlWriter.writeStartElement("historyOfPresentIllness");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localInsurancesTracker) {
            if (localInsurances != null) {
                for (int i = 0; i < localInsurances.length; i++) {
                    if (localInsurances[i] != null) {
                        localInsurances[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "insurances"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "insurances", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "insurances");
                            }
                        } else {
                            xmlWriter.writeStartElement("insurances");
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

                        xmlWriter.writeStartElement(prefix2, "insurances",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "insurances");
                    }
                } else {
                    xmlWriter.writeStartElement("insurances");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localMeasurementsTracker) {
            if (localMeasurements != null) {
                for (int i = 0; i < localMeasurements.length; i++) {
                    if (localMeasurements[i] != null) {
                        localMeasurements[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "measurements"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "measurements", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "measurements");
                            }
                        } else {
                            xmlWriter.writeStartElement("measurements");
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

                        xmlWriter.writeStartElement(prefix2, "measurements",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "measurements");
                    }
                } else {
                    xmlWriter.writeStartElement("measurements");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localMedicationsTracker) {
            if (localMedications != null) {
                for (int i = 0; i < localMedications.length; i++) {
                    if (localMedications[i] != null) {
                        localMedications[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "medications"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "medications", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "medications");
                            }
                        } else {
                            xmlWriter.writeStartElement("medications");
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

                        xmlWriter.writeStartElement(prefix2, "medications",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "medications");
                    }
                } else {
                    xmlWriter.writeStartElement("medications");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localPhysicalExamsTracker) {
            if (localPhysicalExams != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localPhysicalExams.length; i++) {
                    if (localPhysicalExams[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "physicalExams", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "physicalExams");
                            }
                        } else {
                            xmlWriter.writeStartElement("physicalExams");
                        }

                        xmlWriter.writeCharacters(localPhysicalExams[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "physicalExams", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "physicalExams");
                            }
                        } else {
                            xmlWriter.writeStartElement("physicalExams");
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

                        xmlWriter.writeStartElement(prefix2, "physicalExams",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "physicalExams");
                    }
                } else {
                    xmlWriter.writeStartElement("physicalExams");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localPlanOfCaresTracker) {
            if (localPlanOfCares != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localPlanOfCares.length; i++) {
                    if (localPlanOfCares[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "planOfCares", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "planOfCares");
                            }
                        } else {
                            xmlWriter.writeStartElement("planOfCares");
                        }

                        xmlWriter.writeCharacters(localPlanOfCares[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "planOfCares", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "planOfCares");
                            }
                        } else {
                            xmlWriter.writeStartElement("planOfCares");
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

                        xmlWriter.writeStartElement(prefix2, "planOfCares",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "planOfCares");
                    }
                } else {
                    xmlWriter.writeStartElement("planOfCares");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localProceduresTracker) {
            if (localProcedures != null) {
                for (int i = 0; i < localProcedures.length; i++) {
                    if (localProcedures[i] != null) {
                        localProcedures[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "procedures"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "procedures", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "procedures");
                            }
                        } else {
                            xmlWriter.writeStartElement("procedures");
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

                        xmlWriter.writeStartElement(prefix2, "procedures",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "procedures");
                    }
                } else {
                    xmlWriter.writeStartElement("procedures");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localResolvedProblemsTracker) {
            if (localResolvedProblems != null) {
                for (int i = 0; i < localResolvedProblems.length; i++) {
                    if (localResolvedProblems[i] != null) {
                        localResolvedProblems[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "resolvedProblems"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "resolvedProblems", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "resolvedProblems");
                            }
                        } else {
                            xmlWriter.writeStartElement("resolvedProblems");
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

                        xmlWriter.writeStartElement(prefix2,
                            "resolvedProblems", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "resolvedProblems");
                    }
                } else {
                    xmlWriter.writeStartElement("resolvedProblems");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localReviewOfSystemsTracker) {
            if (localReviewOfSystems != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localReviewOfSystems.length; i++) {
                    if (localReviewOfSystems[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "reviewOfSystems", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "reviewOfSystems");
                            }
                        } else {
                            xmlWriter.writeStartElement("reviewOfSystems");
                        }

                        xmlWriter.writeCharacters(localReviewOfSystems[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "reviewOfSystems", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "reviewOfSystems");
                            }
                        } else {
                            xmlWriter.writeStartElement("reviewOfSystems");
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

                        xmlWriter.writeStartElement(prefix2, "reviewOfSystems",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "reviewOfSystems");
                    }
                } else {
                    xmlWriter.writeStartElement("reviewOfSystems");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localFamilyHistoriesTracker) {
            if (localFamilyHistories != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localFamilyHistories.length; i++) {
                    if (localFamilyHistories[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "familyHistories", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "familyHistories");
                            }
                        } else {
                            xmlWriter.writeStartElement("familyHistories");
                        }

                        xmlWriter.writeCharacters(localFamilyHistories[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "familyHistories", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "familyHistories");
                            }
                        } else {
                            xmlWriter.writeStartElement("familyHistories");
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

                        xmlWriter.writeStartElement(prefix2, "familyHistories",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "familyHistories");
                    }
                } else {
                    xmlWriter.writeStartElement("familyHistories");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localImmunizationsTracker) {
            if (localImmunizations != null) {
                for (int i = 0; i < localImmunizations.length; i++) {
                    if (localImmunizations[i] != null) {
                        localImmunizations[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "immunizations"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "immunizations", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "immunizations");
                            }
                        } else {
                            xmlWriter.writeStartElement("immunizations");
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

                        xmlWriter.writeStartElement(prefix2, "immunizations",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "immunizations");
                    }
                } else {
                    xmlWriter.writeStartElement("immunizations");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localLabResultsTracker) {
            if (localLabResults != null) {
                for (int i = 0; i < localLabResults.length; i++) {
                    if (localLabResults[i] != null) {
                        localLabResults[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "labResults"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "labResults", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "labResults");
                            }
                        } else {
                            xmlWriter.writeStartElement("labResults");
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

                        xmlWriter.writeStartElement(prefix2, "labResults",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "labResults");
                    }
                } else {
                    xmlWriter.writeStartElement("labResults");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localReferralReasonTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "referralReason",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "referralReason");
                }
            } else {
                xmlWriter.writeStartElement("referralReason");
            }

            if (localReferralReason == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localReferralReason);
            }

            xmlWriter.writeEndElement();
        }

        if (localSocialHistoriesTracker) {
            if (localSocialHistories != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localSocialHistories.length; i++) {
                    if (localSocialHistories[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "socialHistories", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "socialHistories");
                            }
                        } else {
                            xmlWriter.writeStartElement("socialHistories");
                        }

                        xmlWriter.writeCharacters(localSocialHistories[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "socialHistories", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "socialHistories");
                            }
                        } else {
                            xmlWriter.writeStartElement("socialHistories");
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

                        xmlWriter.writeStartElement(prefix2, "socialHistories",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "socialHistories");
                    }
                } else {
                    xmlWriter.writeStartElement("socialHistories");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localSurgicalHistoriesTracker) {
            if (localSurgicalHistories != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localSurgicalHistories.length; i++) {
                    if (localSurgicalHistories[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "surgicalHistories", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "surgicalHistories");
                            }
                        } else {
                            xmlWriter.writeStartElement("surgicalHistories");
                        }

                        xmlWriter.writeCharacters(localSurgicalHistories[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "surgicalHistories", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "surgicalHistories");
                            }
                        } else {
                            xmlWriter.writeStartElement("surgicalHistories");
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
                            "surgicalHistories", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "surgicalHistories");
                    }
                } else {
                    xmlWriter.writeStartElement("surgicalHistories");
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
                "ReferralSummaryData"));

        if (localMetadataTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "metadata"));

            elementList.add((localMetadata == null) ? null : localMetadata);
        }

        if (localActiveProblemsTracker) {
            if (localActiveProblems != null) {
                for (int i = 0; i < localActiveProblems.length; i++) {
                    if (localActiveProblems[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "activeProblems"));
                        elementList.add(localActiveProblems[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "activeProblems"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "activeProblems"));
                elementList.add(localActiveProblems);
            }
        }

        if (localAdvanceDirectivesTracker) {
            if (localAdvanceDirectives != null) {
                for (int i = 0; i < localAdvanceDirectives.length; i++) {
                    if (localAdvanceDirectives[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "advanceDirectives"));
                        elementList.add(localAdvanceDirectives[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "advanceDirectives"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "advanceDirectives"));
                elementList.add(localAdvanceDirectives);
            }
        }

        if (localAllergiesTracker) {
            if (localAllergies != null) {
                for (int i = 0; i < localAllergies.length; i++) {
                    if (localAllergies[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "allergies"));
                        elementList.add(localAllergies[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "allergies"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "allergies"));
                elementList.add(localAllergies);
            }
        }

        if (localHistoryOfPresentIllnessTracker) {
            if (localHistoryOfPresentIllness != null) {
                for (int i = 0; i < localHistoryOfPresentIllness.length; i++) {
                    if (localHistoryOfPresentIllness[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "historyOfPresentIllness"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localHistoryOfPresentIllness[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "historyOfPresentIllness"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "historyOfPresentIllness"));
                elementList.add(null);
            }
        }

        if (localInsurancesTracker) {
            if (localInsurances != null) {
                for (int i = 0; i < localInsurances.length; i++) {
                    if (localInsurances[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "insurances"));
                        elementList.add(localInsurances[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "insurances"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "insurances"));
                elementList.add(localInsurances);
            }
        }

        if (localMeasurementsTracker) {
            if (localMeasurements != null) {
                for (int i = 0; i < localMeasurements.length; i++) {
                    if (localMeasurements[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "measurements"));
                        elementList.add(localMeasurements[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "measurements"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "measurements"));
                elementList.add(localMeasurements);
            }
        }

        if (localMedicationsTracker) {
            if (localMedications != null) {
                for (int i = 0; i < localMedications.length; i++) {
                    if (localMedications[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "medications"));
                        elementList.add(localMedications[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "medications"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "medications"));
                elementList.add(localMedications);
            }
        }

        if (localPhysicalExamsTracker) {
            if (localPhysicalExams != null) {
                for (int i = 0; i < localPhysicalExams.length; i++) {
                    if (localPhysicalExams[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "physicalExams"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localPhysicalExams[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "physicalExams"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "physicalExams"));
                elementList.add(null);
            }
        }

        if (localPlanOfCaresTracker) {
            if (localPlanOfCares != null) {
                for (int i = 0; i < localPlanOfCares.length; i++) {
                    if (localPlanOfCares[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "planOfCares"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localPlanOfCares[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "planOfCares"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "planOfCares"));
                elementList.add(null);
            }
        }

        if (localProceduresTracker) {
            if (localProcedures != null) {
                for (int i = 0; i < localProcedures.length; i++) {
                    if (localProcedures[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "procedures"));
                        elementList.add(localProcedures[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "procedures"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "procedures"));
                elementList.add(localProcedures);
            }
        }

        if (localResolvedProblemsTracker) {
            if (localResolvedProblems != null) {
                for (int i = 0; i < localResolvedProblems.length; i++) {
                    if (localResolvedProblems[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "resolvedProblems"));
                        elementList.add(localResolvedProblems[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "resolvedProblems"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "resolvedProblems"));
                elementList.add(localResolvedProblems);
            }
        }

        if (localReviewOfSystemsTracker) {
            if (localReviewOfSystems != null) {
                for (int i = 0; i < localReviewOfSystems.length; i++) {
                    if (localReviewOfSystems[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "reviewOfSystems"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localReviewOfSystems[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "reviewOfSystems"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "reviewOfSystems"));
                elementList.add(null);
            }
        }

        if (localFamilyHistoriesTracker) {
            if (localFamilyHistories != null) {
                for (int i = 0; i < localFamilyHistories.length; i++) {
                    if (localFamilyHistories[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "familyHistories"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localFamilyHistories[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "familyHistories"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "familyHistories"));
                elementList.add(null);
            }
        }

        if (localImmunizationsTracker) {
            if (localImmunizations != null) {
                for (int i = 0; i < localImmunizations.length; i++) {
                    if (localImmunizations[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "immunizations"));
                        elementList.add(localImmunizations[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "immunizations"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "immunizations"));
                elementList.add(localImmunizations);
            }
        }

        if (localLabResultsTracker) {
            if (localLabResults != null) {
                for (int i = 0; i < localLabResults.length; i++) {
                    if (localLabResults[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "labResults"));
                        elementList.add(localLabResults[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "labResults"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "labResults"));
                elementList.add(localLabResults);
            }
        }

        if (localReferralReasonTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "referralReason"));

            elementList.add((localReferralReason == null) ? null
                                                          : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localReferralReason));
        }

        if (localSocialHistoriesTracker) {
            if (localSocialHistories != null) {
                for (int i = 0; i < localSocialHistories.length; i++) {
                    if (localSocialHistories[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "socialHistories"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localSocialHistories[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "socialHistories"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "socialHistories"));
                elementList.add(null);
            }
        }

        if (localSurgicalHistoriesTracker) {
            if (localSurgicalHistories != null) {
                for (int i = 0; i < localSurgicalHistories.length; i++) {
                    if (localSurgicalHistories[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "surgicalHistories"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localSurgicalHistories[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "surgicalHistories"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "surgicalHistories"));
                elementList.add(null);
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
        public static ReferralSummaryData parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            ReferralSummaryData object = new ReferralSummaryData();

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

                        if (!"ReferralSummaryData".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (ReferralSummaryData) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                java.util.ArrayList list2 = new java.util.ArrayList();

                java.util.ArrayList list3 = new java.util.ArrayList();

                java.util.ArrayList list4 = new java.util.ArrayList();

                java.util.ArrayList list5 = new java.util.ArrayList();

                java.util.ArrayList list6 = new java.util.ArrayList();

                java.util.ArrayList list7 = new java.util.ArrayList();

                java.util.ArrayList list8 = new java.util.ArrayList();

                java.util.ArrayList list9 = new java.util.ArrayList();

                java.util.ArrayList list10 = new java.util.ArrayList();

                java.util.ArrayList list11 = new java.util.ArrayList();

                java.util.ArrayList list12 = new java.util.ArrayList();

                java.util.ArrayList list13 = new java.util.ArrayList();

                java.util.ArrayList list14 = new java.util.ArrayList();

                java.util.ArrayList list15 = new java.util.ArrayList();

                java.util.ArrayList list16 = new java.util.ArrayList();

                java.util.ArrayList list18 = new java.util.ArrayList();

                java.util.ArrayList list19 = new java.util.ArrayList();

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
                            "activeProblems").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list2.add(null);
                        reader.next();
                    } else {
                        list2.add(com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone2 = false;

                    while (!loopDone2) {
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
                            loopDone2 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "activeProblems").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list2.add(null);
                                    reader.next();
                                } else {
                                    list2.add(com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone2 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setActiveProblems((com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem.class,
                            list2));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "advanceDirectives").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list3.add(null);
                        reader.next();
                    } else {
                        list3.add(com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone3 = false;

                    while (!loopDone3) {
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
                            loopDone3 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "advanceDirectives").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list3.add(null);
                                    reader.next();
                                } else {
                                    list3.add(com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone3 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setAdvanceDirectives((com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.AdvanceDirective.class,
                            list3));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "allergies").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list4.add(null);
                        reader.next();
                    } else {
                        list4.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy.Factory.parse(
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
                                        "allergies").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list4.add(null);
                                    reader.next();
                                } else {
                                    list4.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone4 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setAllergies((com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.Allergy.class,
                            list4));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "historyOfPresentIllness").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list5.add(null);

                        reader.next();
                    } else {
                        list5.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone5 = false;

                    while (!loopDone5) {
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
                            loopDone5 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "historyOfPresentIllness").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list5.add(null);

                                    reader.next();
                                } else {
                                    list5.add(reader.getElementText());
                                }
                            } else {
                                loopDone5 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setHistoryOfPresentIllness((java.lang.String[]) list5.toArray(
                            new java.lang.String[list5.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "insurances").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list6.add(null);
                        reader.next();
                    } else {
                        list6.add(com.misyshealthcare.connect.base.demographicdata.xsd.Insurance.Factory.parse(
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
                                        "insurances").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list6.add(null);
                                    reader.next();
                                } else {
                                    list6.add(com.misyshealthcare.connect.base.demographicdata.xsd.Insurance.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone6 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setInsurances((com.misyshealthcare.connect.base.demographicdata.xsd.Insurance[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.demographicdata.xsd.Insurance.class,
                            list6));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "measurements").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list7.add(null);
                        reader.next();
                    } else {
                        list7.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Measurements.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone7 = false;

                    while (!loopDone7) {
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
                            loopDone7 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "measurements").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list7.add(null);
                                    reader.next();
                                } else {
                                    list7.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Measurements.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone7 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setMeasurements((com.misyshealthcare.connect.base.clinicaldata.xsd.Measurements[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.Measurements.class,
                            list7));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "medications").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list8.add(null);
                        reader.next();
                    } else {
                        list8.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Medication.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone8 = false;

                    while (!loopDone8) {
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
                            loopDone8 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "medications").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list8.add(null);
                                    reader.next();
                                } else {
                                    list8.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Medication.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone8 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setMedications((com.misyshealthcare.connect.base.clinicaldata.xsd.Medication[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.Medication.class,
                            list8));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "physicalExams").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list9.add(null);

                        reader.next();
                    } else {
                        list9.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone9 = false;

                    while (!loopDone9) {
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
                            loopDone9 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "physicalExams").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list9.add(null);

                                    reader.next();
                                } else {
                                    list9.add(reader.getElementText());
                                }
                            } else {
                                loopDone9 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setPhysicalExams((java.lang.String[]) list9.toArray(
                            new java.lang.String[list9.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "planOfCares").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list10.add(null);

                        reader.next();
                    } else {
                        list10.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone10 = false;

                    while (!loopDone10) {
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
                            loopDone10 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "planOfCares").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list10.add(null);

                                    reader.next();
                                } else {
                                    list10.add(reader.getElementText());
                                }
                            } else {
                                loopDone10 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setPlanOfCares((java.lang.String[]) list10.toArray(
                            new java.lang.String[list10.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "procedures").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list11.add(null);
                        reader.next();
                    } else {
                        list11.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone11 = false;

                    while (!loopDone11) {
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
                            loopDone11 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "procedures").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list11.add(null);
                                    reader.next();
                                } else {
                                    list11.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone11 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setProcedures((com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.Procedure.class,
                            list11));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "resolvedProblems").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list12.add(null);
                        reader.next();
                    } else {
                        list12.add(com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone12 = false;

                    while (!loopDone12) {
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
                            loopDone12 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "resolvedProblems").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list12.add(null);
                                    reader.next();
                                } else {
                                    list12.add(com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone12 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setResolvedProblems((com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.SimpleProblem.class,
                            list12));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "reviewOfSystems").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list13.add(null);

                        reader.next();
                    } else {
                        list13.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone13 = false;

                    while (!loopDone13) {
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
                            loopDone13 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "reviewOfSystems").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list13.add(null);

                                    reader.next();
                                } else {
                                    list13.add(reader.getElementText());
                                }
                            } else {
                                loopDone13 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setReviewOfSystems((java.lang.String[]) list13.toArray(
                            new java.lang.String[list13.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "familyHistories").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list14.add(null);

                        reader.next();
                    } else {
                        list14.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone14 = false;

                    while (!loopDone14) {
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
                            loopDone14 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "familyHistories").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list14.add(null);

                                    reader.next();
                                } else {
                                    list14.add(reader.getElementText());
                                }
                            } else {
                                loopDone14 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setFamilyHistories((java.lang.String[]) list14.toArray(
                            new java.lang.String[list14.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "immunizations").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list15.add(null);
                        reader.next();
                    } else {
                        list15.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone15 = false;

                    while (!loopDone15) {
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
                            loopDone15 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "immunizations").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list15.add(null);
                                    reader.next();
                                } else {
                                    list15.add(com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone15 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setImmunizations((com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.Immunization.class,
                            list15));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "labResults").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list16.add(null);
                        reader.next();
                    } else {
                        list16.add(com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone16 = false;

                    while (!loopDone16) {
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
                            loopDone16 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "labResults").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list16.add(null);
                                    reader.next();
                                } else {
                                    list16.add(com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone16 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setLabResults((com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult.class,
                            list16));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "referralReason").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setReferralReason(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "socialHistories").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list18.add(null);

                        reader.next();
                    } else {
                        list18.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone18 = false;

                    while (!loopDone18) {
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
                            loopDone18 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "socialHistories").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list18.add(null);

                                    reader.next();
                                } else {
                                    list18.add(reader.getElementText());
                                }
                            } else {
                                loopDone18 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setSocialHistories((java.lang.String[]) list18.toArray(
                            new java.lang.String[list18.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "surgicalHistories").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list19.add(null);

                        reader.next();
                    } else {
                        list19.add(reader.getElementText());
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone19 = false;

                    while (!loopDone19) {
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
                            loopDone19 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "surgicalHistories").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list19.add(null);

                                    reader.next();
                                } else {
                                    list19.add(reader.getElementText());
                                }
                            } else {
                                loopDone19 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setSurgicalHistories((java.lang.String[]) list19.toArray(
                            new java.lang.String[list19.size()]));
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
