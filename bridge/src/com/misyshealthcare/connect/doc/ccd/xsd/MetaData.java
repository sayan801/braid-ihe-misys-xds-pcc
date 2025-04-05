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
 *  MetaData bean class
 */
public class MetaData implements org.apache.axis2.databinding.ADBBean {
    /**
     * field for Authors
     * This was an Array!
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Author[] localAuthors;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAuthorsTracker = false;

    /**
     * field for ClassCode
     */
    protected com.misyshealthcare.connect.base.xsd.XdsClassCode localClassCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localClassCodeTracker = false;

    /**
     * field for Comment
     */
    protected java.lang.String localComment;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCommentTracker = false;

    /**
     * field for ConfidentialityCodes
     * This was an Array!
     */
    protected com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] localConfidentialityCodes;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localConfidentialityCodesTracker = false;

    /**
     * field for CreationTime
     */
    protected java.util.Calendar localCreationTime;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCreationTimeTracker = false;

    /**
     * field for Custodian
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Organization localCustodian;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCustodianTracker = false;

    /**
     * field for DataEnterers
     * This was an Array!
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[] localDataEnterers;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataEnterersTracker = false;

    /**
     * field for Encounters
     * This was an Array!
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Encounter[] localEncounters;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEncountersTracker = false;

    /**
     * field for EventCodes
     * This was an Array!
     */
    protected java.lang.String[] localEventCodes;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEventCodesTracker = false;

    /**
     * field for FacilityTypeCode
     */
    protected java.lang.String localFacilityTypeCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFacilityTypeCodeTracker = false;

    /**
     * field for FormatCode
     */
    protected com.misyshealthcare.connect.base.xsd.XdsFormatCode localFormatCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFormatCodeTracker = false;

    /**
     * field for GlobalPatientId
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Id localGlobalPatientId;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localGlobalPatientIdTracker = false;

    /**
     * field for HomeSystemId
     */
    protected java.lang.String localHomeSystemId;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localHomeSystemIdTracker = false;

    /**
     * field for IntendedRecepients
     * This was an Array!
     */
    protected java.lang.String[] localIntendedRecepients;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIntendedRecepientsTracker = false;

    /**
     * field for LanguageCode
     */
    protected java.lang.String localLanguageCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localLanguageCodeTracker = false;

    /**
     * field for LegalAutheticator
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Participant localLegalAutheticator;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localLegalAutheticatorTracker = false;

    /**
     * field for MimeType
     */
    protected com.misyshealthcare.connect.base.xsd.MimeTypes localMimeType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMimeTypeTracker = false;

    /**
     * field for PatientContacts
     * This was an Array!
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[] localPatientContacts;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPatientContactsTracker = false;

    /**
     * field for Performers
     * This was an Array!
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Performer[] localPerformers;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPerformersTracker = false;

    /**
     * field for PracticeSettingCode
     */
    protected java.lang.String localPracticeSettingCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPracticeSettingCodeTracker = false;

    /**
     * field for ServiceStartTime
     */
    protected java.util.Calendar localServiceStartTime;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localServiceStartTimeTracker = false;

    /**
     * field for ServiceStopTime
     */
    protected java.util.Calendar localServiceStopTime;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localServiceStopTimeTracker = false;

    /**
     * field for SourcePatientId
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Id localSourcePatientId;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSourcePatientIdTracker = false;

    /**
     * field for SourcePatientInfo
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo localSourcePatientInfo;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSourcePatientInfoTracker = false;

    /**
     * field for Title
     */
    protected java.lang.String localTitle;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTitleTracker = false;

    /**
     * field for TypeCode
     */
    protected com.misyshealthcare.connect.base.xsd.XdsTypeCode localTypeCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTypeCodeTracker = false;

    /**
     * field for UniqueDocumentId
     */
    protected com.misyshealthcare.connect.doc.ccd.xsd.Id localUniqueDocumentId;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localUniqueDocumentIdTracker = false;

    /* This type was generated from the piece of schema that had
       name = MetaData
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
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Author[]
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Author[] getAuthors() {
        return localAuthors;
    }

    /**
     * validate the array for Authors
     */
    protected void validateAuthors(
        com.misyshealthcare.connect.doc.ccd.xsd.Author[] param) {
    }

    /**
     * Auto generated setter method
     * @param param Authors
     */
    public void setAuthors(
        com.misyshealthcare.connect.doc.ccd.xsd.Author[] param) {
        validateAuthors(param);

        if (param != null) {
            //update the setting tracker
            localAuthorsTracker = true;
        } else {
            localAuthorsTracker = true;
        }

        this.localAuthors = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.doc.ccd.xsd.Author
     */
    public void addAuthors(com.misyshealthcare.connect.doc.ccd.xsd.Author param) {
        if (localAuthors == null) {
            localAuthors = new com.misyshealthcare.connect.doc.ccd.xsd.Author[] {  };
        }

        //update the setting tracker
        localAuthorsTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localAuthors);
        list.add(param);
        this.localAuthors = (com.misyshealthcare.connect.doc.ccd.xsd.Author[]) list.toArray(new com.misyshealthcare.connect.doc.ccd.xsd.Author[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.XdsClassCode
     */
    public com.misyshealthcare.connect.base.xsd.XdsClassCode getClassCode() {
        return localClassCode;
    }

    /**
     * Auto generated setter method
     * @param param ClassCode
     */
    public void setClassCode(
        com.misyshealthcare.connect.base.xsd.XdsClassCode param) {
        if (param != null) {
            //update the setting tracker
            localClassCodeTracker = true;
        } else {
            localClassCodeTracker = true;
        }

        this.localClassCode = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getComment() {
        return localComment;
    }

    /**
     * Auto generated setter method
     * @param param Comment
     */
    public void setComment(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localCommentTracker = true;
        } else {
            localCommentTracker = true;
        }

        this.localComment = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.ConfidentialityCode[]
     */
    public com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] getConfidentialityCodes() {
        return localConfidentialityCodes;
    }

    /**
     * validate the array for ConfidentialityCodes
     */
    protected void validateConfidentialityCodes(
        com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] param) {
    }

    /**
     * Auto generated setter method
     * @param param ConfidentialityCodes
     */
    public void setConfidentialityCodes(
        com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] param) {
        validateConfidentialityCodes(param);

        if (param != null) {
            //update the setting tracker
            localConfidentialityCodesTracker = true;
        } else {
            localConfidentialityCodesTracker = true;
        }

        this.localConfidentialityCodes = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.base.xsd.ConfidentialityCode
     */
    public void addConfidentialityCodes(
        com.misyshealthcare.connect.base.xsd.ConfidentialityCode param) {
        if (localConfidentialityCodes == null) {
            localConfidentialityCodes = new com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] {
                    
                };
        }

        //update the setting tracker
        localConfidentialityCodesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localConfidentialityCodes);
        list.add(param);
        this.localConfidentialityCodes = (com.misyshealthcare.connect.base.xsd.ConfidentialityCode[]) list.toArray(new com.misyshealthcare.connect.base.xsd.ConfidentialityCode[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getCreationTime() {
        return localCreationTime;
    }

    /**
     * Auto generated setter method
     * @param param CreationTime
     */
    public void setCreationTime(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localCreationTimeTracker = true;
        } else {
            localCreationTimeTracker = true;
        }

        this.localCreationTime = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Organization
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Organization getCustodian() {
        return localCustodian;
    }

    /**
     * Auto generated setter method
     * @param param Custodian
     */
    public void setCustodian(
        com.misyshealthcare.connect.doc.ccd.xsd.Organization param) {
        if (param != null) {
            //update the setting tracker
            localCustodianTracker = true;
        } else {
            localCustodianTracker = true;
        }

        this.localCustodian = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[]
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[] getDataEnterers() {
        return localDataEnterers;
    }

    /**
     * validate the array for DataEnterers
     */
    protected void validateDataEnterers(
        com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[] param) {
    }

    /**
     * Auto generated setter method
     * @param param DataEnterers
     */
    public void setDataEnterers(
        com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[] param) {
        validateDataEnterers(param);

        if (param != null) {
            //update the setting tracker
            localDataEnterersTracker = true;
        } else {
            localDataEnterersTracker = true;
        }

        this.localDataEnterers = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer
     */
    public void addDataEnterers(
        com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer param) {
        if (localDataEnterers == null) {
            localDataEnterers = new com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[] {
                    
                };
        }

        //update the setting tracker
        localDataEnterersTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDataEnterers);
        list.add(param);
        this.localDataEnterers = (com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[]) list.toArray(new com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Encounter[]
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Encounter[] getEncounters() {
        return localEncounters;
    }

    /**
     * validate the array for Encounters
     */
    protected void validateEncounters(
        com.misyshealthcare.connect.doc.ccd.xsd.Encounter[] param) {
    }

    /**
     * Auto generated setter method
     * @param param Encounters
     */
    public void setEncounters(
        com.misyshealthcare.connect.doc.ccd.xsd.Encounter[] param) {
        validateEncounters(param);

        if (param != null) {
            //update the setting tracker
            localEncountersTracker = true;
        } else {
            localEncountersTracker = true;
        }

        this.localEncounters = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.doc.ccd.xsd.Encounter
     */
    public void addEncounters(
        com.misyshealthcare.connect.doc.ccd.xsd.Encounter param) {
        if (localEncounters == null) {
            localEncounters = new com.misyshealthcare.connect.doc.ccd.xsd.Encounter[] {
                    
                };
        }

        //update the setting tracker
        localEncountersTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localEncounters);
        list.add(param);
        this.localEncounters = (com.misyshealthcare.connect.doc.ccd.xsd.Encounter[]) list.toArray(new com.misyshealthcare.connect.doc.ccd.xsd.Encounter[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String[]
     */
    public java.lang.String[] getEventCodes() {
        return localEventCodes;
    }

    /**
     * validate the array for EventCodes
     */
    protected void validateEventCodes(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param EventCodes
     */
    public void setEventCodes(java.lang.String[] param) {
        validateEventCodes(param);

        if (param != null) {
            //update the setting tracker
            localEventCodesTracker = true;
        } else {
            localEventCodesTracker = true;
        }

        this.localEventCodes = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addEventCodes(java.lang.String param) {
        if (localEventCodes == null) {
            localEventCodes = new java.lang.String[] {  };
        }

        //update the setting tracker
        localEventCodesTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localEventCodes);
        list.add(param);
        this.localEventCodes = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getFacilityTypeCode() {
        return localFacilityTypeCode;
    }

    /**
     * Auto generated setter method
     * @param param FacilityTypeCode
     */
    public void setFacilityTypeCode(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localFacilityTypeCodeTracker = true;
        } else {
            localFacilityTypeCodeTracker = true;
        }

        this.localFacilityTypeCode = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.XdsFormatCode
     */
    public com.misyshealthcare.connect.base.xsd.XdsFormatCode getFormatCode() {
        return localFormatCode;
    }

    /**
     * Auto generated setter method
     * @param param FormatCode
     */
    public void setFormatCode(
        com.misyshealthcare.connect.base.xsd.XdsFormatCode param) {
        if (param != null) {
            //update the setting tracker
            localFormatCodeTracker = true;
        } else {
            localFormatCodeTracker = true;
        }

        this.localFormatCode = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Id
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Id getGlobalPatientId() {
        return localGlobalPatientId;
    }

    /**
     * Auto generated setter method
     * @param param GlobalPatientId
     */
    public void setGlobalPatientId(
        com.misyshealthcare.connect.doc.ccd.xsd.Id param) {
        if (param != null) {
            //update the setting tracker
            localGlobalPatientIdTracker = true;
        } else {
            localGlobalPatientIdTracker = true;
        }

        this.localGlobalPatientId = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getHomeSystemId() {
        return localHomeSystemId;
    }

    /**
     * Auto generated setter method
     * @param param HomeSystemId
     */
    public void setHomeSystemId(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localHomeSystemIdTracker = true;
        } else {
            localHomeSystemIdTracker = true;
        }

        this.localHomeSystemId = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String[]
     */
    public java.lang.String[] getIntendedRecepients() {
        return localIntendedRecepients;
    }

    /**
     * validate the array for IntendedRecepients
     */
    protected void validateIntendedRecepients(java.lang.String[] param) {
    }

    /**
     * Auto generated setter method
     * @param param IntendedRecepients
     */
    public void setIntendedRecepients(java.lang.String[] param) {
        validateIntendedRecepients(param);

        if (param != null) {
            //update the setting tracker
            localIntendedRecepientsTracker = true;
        } else {
            localIntendedRecepientsTracker = true;
        }

        this.localIntendedRecepients = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param java.lang.String
     */
    public void addIntendedRecepients(java.lang.String param) {
        if (localIntendedRecepients == null) {
            localIntendedRecepients = new java.lang.String[] {  };
        }

        //update the setting tracker
        localIntendedRecepientsTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localIntendedRecepients);
        list.add(param);
        this.localIntendedRecepients = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getLanguageCode() {
        return localLanguageCode;
    }

    /**
     * Auto generated setter method
     * @param param LanguageCode
     */
    public void setLanguageCode(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localLanguageCodeTracker = true;
        } else {
            localLanguageCodeTracker = true;
        }

        this.localLanguageCode = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Participant
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Participant getLegalAutheticator() {
        return localLegalAutheticator;
    }

    /**
     * Auto generated setter method
     * @param param LegalAutheticator
     */
    public void setLegalAutheticator(
        com.misyshealthcare.connect.doc.ccd.xsd.Participant param) {
        if (param != null) {
            //update the setting tracker
            localLegalAutheticatorTracker = true;
        } else {
            localLegalAutheticatorTracker = true;
        }

        this.localLegalAutheticator = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.MimeTypes
     */
    public com.misyshealthcare.connect.base.xsd.MimeTypes getMimeType() {
        return localMimeType;
    }

    /**
     * Auto generated setter method
     * @param param MimeType
     */
    public void setMimeType(
        com.misyshealthcare.connect.base.xsd.MimeTypes param) {
        if (param != null) {
            //update the setting tracker
            localMimeTypeTracker = true;
        } else {
            localMimeTypeTracker = true;
        }

        this.localMimeType = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[]
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[] getPatientContacts() {
        return localPatientContacts;
    }

    /**
     * validate the array for PatientContacts
     */
    protected void validatePatientContacts(
        com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[] param) {
    }

    /**
     * Auto generated setter method
     * @param param PatientContacts
     */
    public void setPatientContacts(
        com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[] param) {
        validatePatientContacts(param);

        if (param != null) {
            //update the setting tracker
            localPatientContactsTracker = true;
        } else {
            localPatientContactsTracker = true;
        }

        this.localPatientContacts = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.doc.ccd.xsd.PatientContact
     */
    public void addPatientContacts(
        com.misyshealthcare.connect.doc.ccd.xsd.PatientContact param) {
        if (localPatientContacts == null) {
            localPatientContacts = new com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[] {
                    
                };
        }

        //update the setting tracker
        localPatientContactsTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localPatientContacts);
        list.add(param);
        this.localPatientContacts = (com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[]) list.toArray(new com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Performer[]
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Performer[] getPerformers() {
        return localPerformers;
    }

    /**
     * validate the array for Performers
     */
    protected void validatePerformers(
        com.misyshealthcare.connect.doc.ccd.xsd.Performer[] param) {
    }

    /**
     * Auto generated setter method
     * @param param Performers
     */
    public void setPerformers(
        com.misyshealthcare.connect.doc.ccd.xsd.Performer[] param) {
        validatePerformers(param);

        if (param != null) {
            //update the setting tracker
            localPerformersTracker = true;
        } else {
            localPerformersTracker = true;
        }

        this.localPerformers = param;
    }

    /**
     * Auto generated add method for the array for convenience
     * @param param com.misyshealthcare.connect.doc.ccd.xsd.Performer
     */
    public void addPerformers(
        com.misyshealthcare.connect.doc.ccd.xsd.Performer param) {
        if (localPerformers == null) {
            localPerformers = new com.misyshealthcare.connect.doc.ccd.xsd.Performer[] {
                    
                };
        }

        //update the setting tracker
        localPerformersTracker = true;

        java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localPerformers);
        list.add(param);
        this.localPerformers = (com.misyshealthcare.connect.doc.ccd.xsd.Performer[]) list.toArray(new com.misyshealthcare.connect.doc.ccd.xsd.Performer[list.size()]);
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getPracticeSettingCode() {
        return localPracticeSettingCode;
    }

    /**
     * Auto generated setter method
     * @param param PracticeSettingCode
     */
    public void setPracticeSettingCode(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localPracticeSettingCodeTracker = true;
        } else {
            localPracticeSettingCodeTracker = true;
        }

        this.localPracticeSettingCode = param;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getServiceStartTime() {
        return localServiceStartTime;
    }

    /**
     * Auto generated setter method
     * @param param ServiceStartTime
     */
    public void setServiceStartTime(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localServiceStartTimeTracker = true;
        } else {
            localServiceStartTimeTracker = true;
        }

        this.localServiceStartTime = param;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getServiceStopTime() {
        return localServiceStopTime;
    }

    /**
     * Auto generated setter method
     * @param param ServiceStopTime
     */
    public void setServiceStopTime(java.util.Calendar param) {
        if (param != null) {
            //update the setting tracker
            localServiceStopTimeTracker = true;
        } else {
            localServiceStopTimeTracker = true;
        }

        this.localServiceStopTime = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Id
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Id getSourcePatientId() {
        return localSourcePatientId;
    }

    /**
     * Auto generated setter method
     * @param param SourcePatientId
     */
    public void setSourcePatientId(
        com.misyshealthcare.connect.doc.ccd.xsd.Id param) {
        if (param != null) {
            //update the setting tracker
            localSourcePatientIdTracker = true;
        } else {
            localSourcePatientIdTracker = true;
        }

        this.localSourcePatientId = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo getSourcePatientInfo() {
        return localSourcePatientInfo;
    }

    /**
     * Auto generated setter method
     * @param param SourcePatientInfo
     */
    public void setSourcePatientInfo(
        com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo param) {
        if (param != null) {
            //update the setting tracker
            localSourcePatientInfoTracker = true;
        } else {
            localSourcePatientInfoTracker = true;
        }

        this.localSourcePatientInfo = param;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getTitle() {
        return localTitle;
    }

    /**
     * Auto generated setter method
     * @param param Title
     */
    public void setTitle(java.lang.String param) {
        if (param != null) {
            //update the setting tracker
            localTitleTracker = true;
        } else {
            localTitleTracker = true;
        }

        this.localTitle = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.base.xsd.XdsTypeCode
     */
    public com.misyshealthcare.connect.base.xsd.XdsTypeCode getTypeCode() {
        return localTypeCode;
    }

    /**
     * Auto generated setter method
     * @param param TypeCode
     */
    public void setTypeCode(
        com.misyshealthcare.connect.base.xsd.XdsTypeCode param) {
        if (param != null) {
            //update the setting tracker
            localTypeCodeTracker = true;
        } else {
            localTypeCodeTracker = true;
        }

        this.localTypeCode = param;
    }

    /**
     * Auto generated getter method
     * @return com.misyshealthcare.connect.doc.ccd.xsd.Id
     */
    public com.misyshealthcare.connect.doc.ccd.xsd.Id getUniqueDocumentId() {
        return localUniqueDocumentId;
    }

    /**
     * Auto generated setter method
     * @param param UniqueDocumentId
     */
    public void setUniqueDocumentId(
        com.misyshealthcare.connect.doc.ccd.xsd.Id param) {
        if (param != null) {
            //update the setting tracker
            localUniqueDocumentIdTracker = true;
        } else {
            localUniqueDocumentIdTracker = true;
        }

        this.localUniqueDocumentId = param;
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
                    MetaData.this.serialize(parentQName, factory, xmlWriter);
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

        if (localAuthorsTracker) {
            if (localAuthors != null) {
                for (int i = 0; i < localAuthors.length; i++) {
                    if (localAuthors[i] != null) {
                        localAuthors[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authors"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2, "authors",
                                    namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "authors");
                            }
                        } else {
                            xmlWriter.writeStartElement("authors");
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

                        xmlWriter.writeStartElement(prefix2, "authors",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "authors");
                    }
                } else {
                    xmlWriter.writeStartElement("authors");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localClassCodeTracker) {
            if (localClassCode == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "classCode",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "classCode");
                    }
                } else {
                    xmlWriter.writeStartElement("classCode");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localClassCode.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "classCode"), factory, xmlWriter);
            }
        }

        if (localCommentTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "comment", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "comment");
                }
            } else {
                xmlWriter.writeStartElement("comment");
            }

            if (localComment == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localComment);
            }

            xmlWriter.writeEndElement();
        }

        if (localConfidentialityCodesTracker) {
            if (localConfidentialityCodes != null) {
                for (int i = 0; i < localConfidentialityCodes.length; i++) {
                    if (localConfidentialityCodes[i] != null) {
                        localConfidentialityCodes[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "confidentialityCodes"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "confidentialityCodes", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "confidentialityCodes");
                            }
                        } else {
                            xmlWriter.writeStartElement("confidentialityCodes");
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
                            "confidentialityCodes", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "confidentialityCodes");
                    }
                } else {
                    xmlWriter.writeStartElement("confidentialityCodes");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localCreationTimeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "creationTime",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "creationTime");
                }
            } else {
                xmlWriter.writeStartElement("creationTime");
            }

            if (localCreationTime == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localCreationTime));
            }

            xmlWriter.writeEndElement();
        }

        if (localCustodianTracker) {
            if (localCustodian == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "custodian",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "custodian");
                    }
                } else {
                    xmlWriter.writeStartElement("custodian");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localCustodian.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "custodian"), factory, xmlWriter);
            }
        }

        if (localDataEnterersTracker) {
            if (localDataEnterers != null) {
                for (int i = 0; i < localDataEnterers.length; i++) {
                    if (localDataEnterers[i] != null) {
                        localDataEnterers[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "dataEnterers"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "dataEnterers", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "dataEnterers");
                            }
                        } else {
                            xmlWriter.writeStartElement("dataEnterers");
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

                        xmlWriter.writeStartElement(prefix2, "dataEnterers",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "dataEnterers");
                    }
                } else {
                    xmlWriter.writeStartElement("dataEnterers");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localEncountersTracker) {
            if (localEncounters != null) {
                for (int i = 0; i < localEncounters.length; i++) {
                    if (localEncounters[i] != null) {
                        localEncounters[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "encounters"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "encounters", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "encounters");
                            }
                        } else {
                            xmlWriter.writeStartElement("encounters");
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

                        xmlWriter.writeStartElement(prefix2, "encounters",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "encounters");
                    }
                } else {
                    xmlWriter.writeStartElement("encounters");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localEventCodesTracker) {
            if (localEventCodes != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localEventCodes.length; i++) {
                    if (localEventCodes[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "eventCodes", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "eventCodes");
                            }
                        } else {
                            xmlWriter.writeStartElement("eventCodes");
                        }

                        xmlWriter.writeCharacters(localEventCodes[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "eventCodes", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "eventCodes");
                            }
                        } else {
                            xmlWriter.writeStartElement("eventCodes");
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

                        xmlWriter.writeStartElement(prefix2, "eventCodes",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "eventCodes");
                    }
                } else {
                    xmlWriter.writeStartElement("eventCodes");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localFacilityTypeCodeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "facilityTypeCode",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "facilityTypeCode");
                }
            } else {
                xmlWriter.writeStartElement("facilityTypeCode");
            }

            if (localFacilityTypeCode == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localFacilityTypeCode);
            }

            xmlWriter.writeEndElement();
        }

        if (localFormatCodeTracker) {
            if (localFormatCode == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "formatCode",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "formatCode");
                    }
                } else {
                    xmlWriter.writeStartElement("formatCode");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localFormatCode.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "formatCode"), factory, xmlWriter);
            }
        }

        if (localGlobalPatientIdTracker) {
            if (localGlobalPatientId == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "globalPatientId",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "globalPatientId");
                    }
                } else {
                    xmlWriter.writeStartElement("globalPatientId");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localGlobalPatientId.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "globalPatientId"), factory, xmlWriter);
            }
        }

        if (localHomeSystemIdTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "homeSystemId",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "homeSystemId");
                }
            } else {
                xmlWriter.writeStartElement("homeSystemId");
            }

            if (localHomeSystemId == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localHomeSystemId);
            }

            xmlWriter.writeEndElement();
        }

        if (localIntendedRecepientsTracker) {
            if (localIntendedRecepients != null) {
                namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                boolean emptyNamespace = (namespace == null) ||
                    (namespace.length() == 0);
                prefix = emptyNamespace ? null : xmlWriter.getPrefix(namespace);

                for (int i = 0; i < localIntendedRecepients.length; i++) {
                    if (localIntendedRecepients[i] != null) {
                        if (!emptyNamespace) {
                            if (prefix == null) {
                                java.lang.String prefix2 = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix2,
                                    "intendedRecepients", namespace);
                                xmlWriter.writeNamespace(prefix2, namespace);
                                xmlWriter.setPrefix(prefix2, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "intendedRecepients");
                            }
                        } else {
                            xmlWriter.writeStartElement("intendedRecepients");
                        }

                        xmlWriter.writeCharacters(localIntendedRecepients[i]);

                        xmlWriter.writeEndElement();
                    } else {
                        // write null attribute
                        namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace.equals("")) {
                            prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                xmlWriter.writeStartElement(prefix,
                                    "intendedRecepients", namespace);
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                            } else {
                                xmlWriter.writeStartElement(namespace,
                                    "intendedRecepients");
                            }
                        } else {
                            xmlWriter.writeStartElement("intendedRecepients");
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
                            "intendedRecepients", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "intendedRecepients");
                    }
                } else {
                    xmlWriter.writeStartElement("intendedRecepients");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localLanguageCodeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "languageCode",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "languageCode");
                }
            } else {
                xmlWriter.writeStartElement("languageCode");
            }

            if (localLanguageCode == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localLanguageCode);
            }

            xmlWriter.writeEndElement();
        }

        if (localLegalAutheticatorTracker) {
            if (localLegalAutheticator == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2,
                            "legalAutheticator", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "legalAutheticator");
                    }
                } else {
                    xmlWriter.writeStartElement("legalAutheticator");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localLegalAutheticator.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "legalAutheticator"), factory, xmlWriter);
            }
        }

        if (localMimeTypeTracker) {
            if (localMimeType == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "mimeType",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "mimeType");
                    }
                } else {
                    xmlWriter.writeStartElement("mimeType");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localMimeType.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "mimeType"), factory, xmlWriter);
            }
        }

        if (localPatientContactsTracker) {
            if (localPatientContacts != null) {
                for (int i = 0; i < localPatientContacts.length; i++) {
                    if (localPatientContacts[i] != null) {
                        localPatientContacts[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "patientContacts"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "patientContacts", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "patientContacts");
                            }
                        } else {
                            xmlWriter.writeStartElement("patientContacts");
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

                        xmlWriter.writeStartElement(prefix2, "patientContacts",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "patientContacts");
                    }
                } else {
                    xmlWriter.writeStartElement("patientContacts");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localPerformersTracker) {
            if (localPerformers != null) {
                for (int i = 0; i < localPerformers.length; i++) {
                    if (localPerformers[i] != null) {
                        localPerformers[i].serialize(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "performers"), factory, xmlWriter);
                    } else {
                        // write null attribute
                        java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                        if (!namespace2.equals("")) {
                            java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                            if (prefix2 == null) {
                                prefix2 = generatePrefix(namespace2);

                                xmlWriter.writeStartElement(prefix2,
                                    "performers", namespace2);
                                xmlWriter.writeNamespace(prefix2, namespace2);
                                xmlWriter.setPrefix(prefix2, namespace2);
                            } else {
                                xmlWriter.writeStartElement(namespace2,
                                    "performers");
                            }
                        } else {
                            xmlWriter.writeStartElement("performers");
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

                        xmlWriter.writeStartElement(prefix2, "performers",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "performers");
                    }
                } else {
                    xmlWriter.writeStartElement("performers");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            }
        }

        if (localPracticeSettingCodeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "practiceSettingCode",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "practiceSettingCode");
                }
            } else {
                xmlWriter.writeStartElement("practiceSettingCode");
            }

            if (localPracticeSettingCode == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localPracticeSettingCode);
            }

            xmlWriter.writeEndElement();
        }

        if (localServiceStartTimeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "serviceStartTime",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "serviceStartTime");
                }
            } else {
                xmlWriter.writeStartElement("serviceStartTime");
            }

            if (localServiceStartTime == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localServiceStartTime));
            }

            xmlWriter.writeEndElement();
        }

        if (localServiceStopTimeTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "serviceStopTime",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "serviceStopTime");
                }
            } else {
                xmlWriter.writeStartElement("serviceStopTime");
            }

            if (localServiceStopTime == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localServiceStopTime));
            }

            xmlWriter.writeEndElement();
        }

        if (localSourcePatientIdTracker) {
            if (localSourcePatientId == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "sourcePatientId",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "sourcePatientId");
                    }
                } else {
                    xmlWriter.writeStartElement("sourcePatientId");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localSourcePatientId.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "sourcePatientId"), factory, xmlWriter);
            }
        }

        if (localSourcePatientInfoTracker) {
            if (localSourcePatientInfo == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2,
                            "sourcePatientInfo", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "sourcePatientInfo");
                    }
                } else {
                    xmlWriter.writeStartElement("sourcePatientInfo");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localSourcePatientInfo.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "sourcePatientInfo"), factory, xmlWriter);
            }
        }

        if (localTitleTracker) {
            namespace = "http://ccd.doc.connect.misyshealthcare.com/xsd";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "title", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "title");
                }
            } else {
                xmlWriter.writeStartElement("title");
            }

            if (localTitle == null) {
                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
            } else {
                xmlWriter.writeCharacters(localTitle);
            }

            xmlWriter.writeEndElement();
        }

        if (localTypeCodeTracker) {
            if (localTypeCode == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2, "typeCode",
                            namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2, "typeCode");
                    }
                } else {
                    xmlWriter.writeStartElement("typeCode");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localTypeCode.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "typeCode"), factory, xmlWriter);
            }
        }

        if (localUniqueDocumentIdTracker) {
            if (localUniqueDocumentId == null) {
                java.lang.String namespace2 = "http://ccd.doc.connect.misyshealthcare.com/xsd";

                if (!namespace2.equals("")) {
                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                    if (prefix2 == null) {
                        prefix2 = generatePrefix(namespace2);

                        xmlWriter.writeStartElement(prefix2,
                            "uniqueDocumentId", namespace2);
                        xmlWriter.writeNamespace(prefix2, namespace2);
                        xmlWriter.setPrefix(prefix2, namespace2);
                    } else {
                        xmlWriter.writeStartElement(namespace2,
                            "uniqueDocumentId");
                    }
                } else {
                    xmlWriter.writeStartElement("uniqueDocumentId");
                }

                // write the nil attribute
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "nil", "1",
                    xmlWriter);
                xmlWriter.writeEndElement();
            } else {
                localUniqueDocumentId.serialize(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "uniqueDocumentId"), factory, xmlWriter);
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

        if (localAuthorsTracker) {
            if (localAuthors != null) {
                for (int i = 0; i < localAuthors.length; i++) {
                    if (localAuthors[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authors"));
                        elementList.add(localAuthors[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "authors"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "authors"));
                elementList.add(localAuthors);
            }
        }

        if (localClassCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "classCode"));

            elementList.add((localClassCode == null) ? null : localClassCode);
        }

        if (localCommentTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "comment"));

            elementList.add((localComment == null) ? null
                                                   : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localComment));
        }

        if (localConfidentialityCodesTracker) {
            if (localConfidentialityCodes != null) {
                for (int i = 0; i < localConfidentialityCodes.length; i++) {
                    if (localConfidentialityCodes[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "confidentialityCodes"));
                        elementList.add(localConfidentialityCodes[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "confidentialityCodes"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "confidentialityCodes"));
                elementList.add(localConfidentialityCodes);
            }
        }

        if (localCreationTimeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "creationTime"));

            elementList.add((localCreationTime == null) ? null
                                                        : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localCreationTime));
        }

        if (localCustodianTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "custodian"));

            elementList.add((localCustodian == null) ? null : localCustodian);
        }

        if (localDataEnterersTracker) {
            if (localDataEnterers != null) {
                for (int i = 0; i < localDataEnterers.length; i++) {
                    if (localDataEnterers[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "dataEnterers"));
                        elementList.add(localDataEnterers[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "dataEnterers"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "dataEnterers"));
                elementList.add(localDataEnterers);
            }
        }

        if (localEncountersTracker) {
            if (localEncounters != null) {
                for (int i = 0; i < localEncounters.length; i++) {
                    if (localEncounters[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "encounters"));
                        elementList.add(localEncounters[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "encounters"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "encounters"));
                elementList.add(localEncounters);
            }
        }

        if (localEventCodesTracker) {
            if (localEventCodes != null) {
                for (int i = 0; i < localEventCodes.length; i++) {
                    if (localEventCodes[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "eventCodes"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localEventCodes[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "eventCodes"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "eventCodes"));
                elementList.add(null);
            }
        }

        if (localFacilityTypeCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "facilityTypeCode"));

            elementList.add((localFacilityTypeCode == null) ? null
                                                            : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localFacilityTypeCode));
        }

        if (localFormatCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "formatCode"));

            elementList.add((localFormatCode == null) ? null : localFormatCode);
        }

        if (localGlobalPatientIdTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "globalPatientId"));

            elementList.add((localGlobalPatientId == null) ? null
                                                           : localGlobalPatientId);
        }

        if (localHomeSystemIdTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "homeSystemId"));

            elementList.add((localHomeSystemId == null) ? null
                                                        : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localHomeSystemId));
        }

        if (localIntendedRecepientsTracker) {
            if (localIntendedRecepients != null) {
                for (int i = 0; i < localIntendedRecepients.length; i++) {
                    if (localIntendedRecepients[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "intendedRecepients"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localIntendedRecepients[i]));
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "intendedRecepients"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "intendedRecepients"));
                elementList.add(null);
            }
        }

        if (localLanguageCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "languageCode"));

            elementList.add((localLanguageCode == null) ? null
                                                        : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localLanguageCode));
        }

        if (localLegalAutheticatorTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "legalAutheticator"));

            elementList.add((localLegalAutheticator == null) ? null
                                                             : localLegalAutheticator);
        }

        if (localMimeTypeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "mimeType"));

            elementList.add((localMimeType == null) ? null : localMimeType);
        }

        if (localPatientContactsTracker) {
            if (localPatientContacts != null) {
                for (int i = 0; i < localPatientContacts.length; i++) {
                    if (localPatientContacts[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "patientContacts"));
                        elementList.add(localPatientContacts[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "patientContacts"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "patientContacts"));
                elementList.add(localPatientContacts);
            }
        }

        if (localPerformersTracker) {
            if (localPerformers != null) {
                for (int i = 0; i < localPerformers.length; i++) {
                    if (localPerformers[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "performers"));
                        elementList.add(localPerformers[i]);
                    } else {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                "performers"));
                        elementList.add(null);
                    }
                }
            } else {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                        "performers"));
                elementList.add(localPerformers);
            }
        }

        if (localPracticeSettingCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "practiceSettingCode"));

            elementList.add((localPracticeSettingCode == null) ? null
                                                               : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localPracticeSettingCode));
        }

        if (localServiceStartTimeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "serviceStartTime"));

            elementList.add((localServiceStartTime == null) ? null
                                                            : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localServiceStartTime));
        }

        if (localServiceStopTimeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "serviceStopTime"));

            elementList.add((localServiceStopTime == null) ? null
                                                           : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localServiceStopTime));
        }

        if (localSourcePatientIdTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "sourcePatientId"));

            elementList.add((localSourcePatientId == null) ? null
                                                           : localSourcePatientId);
        }

        if (localSourcePatientInfoTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "sourcePatientInfo"));

            elementList.add((localSourcePatientInfo == null) ? null
                                                             : localSourcePatientInfo);
        }

        if (localTitleTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "title"));

            elementList.add((localTitle == null) ? null
                                                 : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    localTitle));
        }

        if (localTypeCodeTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd", "typeCode"));

            elementList.add((localTypeCode == null) ? null : localTypeCode);
        }

        if (localUniqueDocumentIdTracker) {
            elementList.add(new javax.xml.namespace.QName(
                    "http://ccd.doc.connect.misyshealthcare.com/xsd",
                    "uniqueDocumentId"));

            elementList.add((localUniqueDocumentId == null) ? null
                                                            : localUniqueDocumentId);
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
        public static MetaData parse(javax.xml.stream.XMLStreamReader reader)
            throws java.lang.Exception {
            MetaData object = new MetaData();

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

                        if (!"MetaData".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (MetaData) com.misyshealthcare.connect.doc.ccd.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                java.util.ArrayList list1 = new java.util.ArrayList();

                java.util.ArrayList list4 = new java.util.ArrayList();

                java.util.ArrayList list7 = new java.util.ArrayList();

                java.util.ArrayList list8 = new java.util.ArrayList();

                java.util.ArrayList list9 = new java.util.ArrayList();

                java.util.ArrayList list14 = new java.util.ArrayList();

                java.util.ArrayList list18 = new java.util.ArrayList();

                java.util.ArrayList list19 = new java.util.ArrayList();

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "authors").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list1.add(null);
                        reader.next();
                    } else {
                        list1.add(com.misyshealthcare.connect.doc.ccd.xsd.Author.Factory.parse(
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
                                        "authors").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list1.add(null);
                                    reader.next();
                                } else {
                                    list1.add(com.misyshealthcare.connect.doc.ccd.xsd.Author.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone1 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setAuthors((com.misyshealthcare.connect.doc.ccd.xsd.Author[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.doc.ccd.xsd.Author.class,
                            list1));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "classCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setClassCode(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setClassCode(com.misyshealthcare.connect.base.xsd.XdsClassCode.Factory.parse(
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
                            "comment").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setComment(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "confidentialityCodes").equals(reader.getName())) {
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
                                        "confidentialityCodes").equals(
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
                    object.setConfidentialityCodes((com.misyshealthcare.connect.base.xsd.ConfidentialityCode[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.base.xsd.ConfidentialityCode.class,
                            list4));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "creationTime").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setCreationTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "custodian").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setCustodian(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setCustodian(com.misyshealthcare.connect.doc.ccd.xsd.Organization.Factory.parse(
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
                            "dataEnterers").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list7.add(null);
                        reader.next();
                    } else {
                        list7.add(com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer.Factory.parse(
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
                                        "dataEnterers").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list7.add(null);
                                    reader.next();
                                } else {
                                    list7.add(com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone7 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setDataEnterers((com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer.class,
                            list7));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "encounters").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list8.add(null);
                        reader.next();
                    } else {
                        list8.add(com.misyshealthcare.connect.doc.ccd.xsd.Encounter.Factory.parse(
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
                                        "encounters").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list8.add(null);
                                    reader.next();
                                } else {
                                    list8.add(com.misyshealthcare.connect.doc.ccd.xsd.Encounter.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone8 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setEncounters((com.misyshealthcare.connect.doc.ccd.xsd.Encounter[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.doc.ccd.xsd.Encounter.class,
                            list8));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "eventCodes").equals(reader.getName())) {
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
                                        "eventCodes").equals(reader.getName())) {
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
                    object.setEventCodes((java.lang.String[]) list9.toArray(
                            new java.lang.String[list9.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "facilityTypeCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setFacilityTypeCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "formatCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setFormatCode(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setFormatCode(com.misyshealthcare.connect.base.xsd.XdsFormatCode.Factory.parse(
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
                            "globalPatientId").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setGlobalPatientId(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setGlobalPatientId(com.misyshealthcare.connect.doc.ccd.xsd.Id.Factory.parse(
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
                            "homeSystemId").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setHomeSystemId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "intendedRecepients").equals(reader.getName())) {
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
                                        "intendedRecepients").equals(
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
                    object.setIntendedRecepients((java.lang.String[]) list14.toArray(
                            new java.lang.String[list14.size()]));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "languageCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setLanguageCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "legalAutheticator").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setLegalAutheticator(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setLegalAutheticator(com.misyshealthcare.connect.doc.ccd.xsd.Participant.Factory.parse(
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
                            "mimeType").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setMimeType(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setMimeType(com.misyshealthcare.connect.base.xsd.MimeTypes.Factory.parse(
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
                            "patientContacts").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list18.add(null);
                        reader.next();
                    } else {
                        list18.add(com.misyshealthcare.connect.doc.ccd.xsd.PatientContact.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone18 = false;

                    while (!loopDone18) {
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
                            loopDone18 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "patientContacts").equals(
                                        reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list18.add(null);
                                    reader.next();
                                } else {
                                    list18.add(com.misyshealthcare.connect.doc.ccd.xsd.PatientContact.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone18 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setPatientContacts((com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.doc.ccd.xsd.PatientContact.class,
                            list18));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "performers").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        list19.add(null);
                        reader.next();
                    } else {
                        list19.add(com.misyshealthcare.connect.doc.ccd.xsd.Performer.Factory.parse(
                                reader));
                    }

                    //loop until we find a start element that is not part of this array
                    boolean loopDone19 = false;

                    while (!loopDone19) {
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
                            loopDone19 = true;
                        } else {
                            if (new javax.xml.namespace.QName(
                                        "http://ccd.doc.connect.misyshealthcare.com/xsd",
                                        "performers").equals(reader.getName())) {
                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                        "nil");

                                if ("true".equals(nillableValue) ||
                                        "1".equals(nillableValue)) {
                                    list19.add(null);
                                    reader.next();
                                } else {
                                    list19.add(com.misyshealthcare.connect.doc.ccd.xsd.Performer.Factory.parse(
                                            reader));
                                }
                            } else {
                                loopDone19 = true;
                            }
                        }
                    }

                    // call the converter utility  to convert and set the array
                    object.setPerformers((com.misyshealthcare.connect.doc.ccd.xsd.Performer[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                            com.misyshealthcare.connect.doc.ccd.xsd.Performer.class,
                            list19));
                } // End of if for expected property start element

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://ccd.doc.connect.misyshealthcare.com/xsd",
                            "practiceSettingCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setPracticeSettingCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "serviceStartTime").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setServiceStartTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "serviceStopTime").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setServiceStopTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "sourcePatientId").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setSourcePatientId(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setSourcePatientId(com.misyshealthcare.connect.doc.ccd.xsd.Id.Factory.parse(
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
                            "sourcePatientInfo").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setSourcePatientInfo(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setSourcePatientInfo(com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo.Factory.parse(
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
                            "title").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if (!"true".equals(nillableValue) &&
                            !"1".equals(nillableValue)) {
                        java.lang.String content = reader.getElementText();

                        object.setTitle(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "typeCode").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setTypeCode(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setTypeCode(com.misyshealthcare.connect.base.xsd.XdsTypeCode.Factory.parse(
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
                            "uniqueDocumentId").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        object.setUniqueDocumentId(null);
                        reader.next();

                        reader.next();
                    } else {
                        object.setUniqueDocumentId(com.misyshealthcare.connect.doc.ccd.xsd.Id.Factory.parse(
                                reader));

                        reader.next();
                    }
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
