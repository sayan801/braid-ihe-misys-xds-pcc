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
package com.misyshealthcare.connect.doc.ccd;


/**
 *  IheServiceMessageReceiverInOut message receiver
 */
public class IheServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutSyncMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            IheServiceSkeleton skel = (IheServiceSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(
                            op.getName().getLocalPart())) != null)) {
                if ("buildConsentDocument".equals(methodName)) {
                    com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse buildConsentDocumentResponse1 =
                        null;
                    com.misyshealthcare.connect.doc.ccd.BuildConsentDocument wrappedParam =
                        (com.misyshealthcare.connect.doc.ccd.BuildConsentDocument) fromOM(msgContext.getEnvelope()
                                                                                                    .getBody()
                                                                                                    .getFirstElement(),
                            com.misyshealthcare.connect.doc.ccd.BuildConsentDocument.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    buildConsentDocumentResponse1 = skel.buildConsentDocument(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            buildConsentDocumentResponse1, false);
                } else
                 if ("buildScannedDocument".equals(methodName)) {
                    com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse buildScannedDocumentResponse3 =
                        null;
                    com.misyshealthcare.connect.doc.ccd.BuildScannedDocument wrappedParam =
                        (com.misyshealthcare.connect.doc.ccd.BuildScannedDocument) fromOM(msgContext.getEnvelope()
                                                                                                    .getBody()
                                                                                                    .getFirstElement(),
                            com.misyshealthcare.connect.doc.ccd.BuildScannedDocument.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    buildScannedDocumentResponse3 = skel.buildScannedDocument(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            buildScannedDocumentResponse3, false);
                } else
                 if ("submitDocuments".equals(methodName)) {
                    com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse submitDocumentsResponse5 =
                        null;
                    com.misyshealthcare.connect.doc.ccd.SubmitDocuments wrappedParam =
                        (com.misyshealthcare.connect.doc.ccd.SubmitDocuments) fromOM(msgContext.getEnvelope()
                                                                                               .getBody()
                                                                                               .getFirstElement(),
                            com.misyshealthcare.connect.doc.ccd.SubmitDocuments.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    submitDocumentsResponse5 = skel.submitDocuments(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            submitDocumentsResponse5, false);
                } else
                 if ("buildEDReferral".equals(methodName)) {
                    com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse buildEDReferralResponse7 =
                        null;
                    com.misyshealthcare.connect.doc.ccd.BuildEDReferral wrappedParam =
                        (com.misyshealthcare.connect.doc.ccd.BuildEDReferral) fromOM(msgContext.getEnvelope()
                                                                                               .getBody()
                                                                                               .getFirstElement(),
                            com.misyshealthcare.connect.doc.ccd.BuildEDReferral.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    buildEDReferralResponse7 = skel.buildEDReferral(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            buildEDReferralResponse7, false);
                } else
                 if ("buildReferralSummary".equals(methodName)) {
                    com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse buildReferralSummaryResponse9 =
                        null;
                    com.misyshealthcare.connect.doc.ccd.BuildReferralSummary wrappedParam =
                        (com.misyshealthcare.connect.doc.ccd.BuildReferralSummary) fromOM(msgContext.getEnvelope()
                                                                                                    .getBody()
                                                                                                    .getFirstElement(),
                            com.misyshealthcare.connect.doc.ccd.BuildReferralSummary.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    buildReferralSummaryResponse9 = skel.buildReferralSummary(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            buildReferralSummaryResponse9, false);
                } else
                 if ("buildDischargeSummary".equals(methodName)) {
                    com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse buildDischargeSummaryResponse11 =
                        null;
                    com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary wrappedParam =
                        (com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary) fromOM(msgContext.getEnvelope()
                                                                                                     .getBody()
                                                                                                     .getFirstElement(),
                            com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary.class,
                            getEnvelopeNamespaces(msgContext.getEnvelope()));

                    buildDischargeSummaryResponse11 = skel.buildDischargeSummary(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            buildDischargeSummaryResponse11, false);
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildReferralSummary param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildReferralSummary.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildEDReferral param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildEDReferral.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildConsentDocument param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildConsentDocument.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildScannedDocument param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildScannedDocument.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.SubmitDocuments param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.SubmitDocuments.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type, java.util.Map extraNamespaces)
        throws org.apache.axis2.AxisFault {
        try {
            if (com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildReferralSummary.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildReferralSummary.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildEDReferral.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildEDReferral.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildConsentDocument.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildConsentDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildScannedDocument.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildScannedDocument.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.SubmitDocuments.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.SubmitDocuments.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse.class.equals(
                        type)) {
                return com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(
        org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();

        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }

        return returnMap;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
