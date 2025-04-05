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

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.log4j.Logger;
import hl7OrgV3.ClinicalDocumentDocument1;
import com.misyshealthcare.connect.base.*;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;
import java.io.File;
import java.io.IOException;

/**
 * This class serves as an agent to buid metadata for a CDA document.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 20, 2005
 */
public class MetaDataAgent {
    private static final Logger log = Logger.getLogger(MetaDataAgent.class.getName());
    private final String nsDeclare = "declare namespace ns='urn:hl7-org:v3';";
    private XmlCursor pathCursor = null;
    private ClinicalDocumentDocument1 doc1 = null;
    Setting setting = null;
    /**
     * Constructor
     * @param doc1
     */
    private MetaDataAgent(ClinicalDocumentDocument1 doc1) {
        this.doc1 = doc1;
        this.pathCursor = doc1.newCursor();
    }

    //Factor to create MetaDataAgent object
    public static class Factory {
        public static MetaDataAgent getFromDoc(ClinicalDocumentDocument1 doc1) {
            return new MetaDataAgent(doc1);
        }
        public static MetaDataAgent parse(String fileName) throws CCDException {
            try {
                ClinicalDocumentDocument1 doc1 = ClinicalDocumentDocument1.Factory.parse(fileName);
                return new MetaDataAgent(doc1);
            } catch (XmlException e) {
                throw new CCDException(e);
            }
        }
        public static MetaDataAgent parse(File fileName) throws CCDException {
            try {
                ClinicalDocumentDocument1 doc1 = ClinicalDocumentDocument1.Factory.parse(fileName);
                return new MetaDataAgent(doc1);
            } catch (XmlException e) {
                throw new CCDException(e);
            } catch(IOException e) {
                throw new CCDException(e);
            }
        }
    }

    static class Setting {
        SharedEnums.XdsFormatCode formatCode;
        SharedEnums.XdsClassCode classCode;
        SharedEnums.XdsTypeCode  typeCode;
        SharedEnums.ConfidentialityCode[] confidentialityCodes = null;
        String facilityTypeCode = null;
        String practiceSettingCode = null;
        
        String[] eventCodes = null;


        SharedEnums.XdsFormatCode getFormatCode() {
            return formatCode;
        }
        void setFormatCode(SharedEnums.XdsFormatCode format) {
            formatCode = format;
        }
        SharedEnums.XdsClassCode getClassCode() {
            return classCode;
        }
        void setClassCode(SharedEnums.XdsClassCode classcode) {
            classCode = classcode;
        }
        SharedEnums.XdsTypeCode getTypeCode() {
            return typeCode;
        }
        void setTypeCode(SharedEnums.XdsTypeCode typecode) {
            typeCode = typecode;
        }
        SharedEnums.ConfidentialityCode[] getConfidentialityCodes() {
            return confidentialityCodes;
        }
        void setConfidentialityCodes(SharedEnums.ConfidentialityCode[] confidentialityCodes) {
            this.confidentialityCodes = confidentialityCodes;
        }
        String[] getEventCodes() {
            return eventCodes;
        }
        void setEventCodes(String[] eventCodes) {
            this.eventCodes = eventCodes;
        }

		/**
		 * @return the facilityTypeCode
		 */
		String getFacilityTypeCode() {
			return facilityTypeCode;
		}
		/**
		 * @param facilityTypeCode the facilityTypeCode to set
		 */
		void setFacilityTypeCode(String facilityTypeCode) {
			this.facilityTypeCode = facilityTypeCode;
		}
		/**
		 * @return the practiceSettingCode
		 */
		String getPracticeSettingCode() {
			return practiceSettingCode;
		}
		/**
		 * @param practiceSettingCode the practiceSettingCode to set
		 */
		void setPracticeSettingCode(String practiceSettingCode) {
			this.practiceSettingCode = practiceSettingCode;
		}

		
    }

    /**
     * Sets the metadata setting
     *
     * @param setting
     */
    void setSetting(Setting setting) {
        this.setting = setting;
    }
    /**
     * Process a CDA document to retrive all its metadata.
     *
     * @return a Document instance containing all the metadata
     */
    public CCDDocument process() {
  //TODO: 
    	return null;
//        Document doc = new Document();
//        AuthorDescriptor author = new AuthorDescriptor();
//        author.setAuthorPerson(getAuthorPerson());
//        author.addAuthorInstitution(getAuthorInstitution());
//        doc.addAuthorDescriptor( author );
//        doc.setClassCode(this.setting.getClassCode());
//        doc.setCreationTime(getCreationTime());
//        doc.setFacilityCode("EMR");       //TODO: need a way to find out facility code
//        doc.setPracticeCode("Cardiology"); //TODO: need to a way to find out Practice Code
//        doc.setFormatCode(this.setting.getFormatCode());
//        doc.setConfidentialityCodes(this.setting.getConfidentialityCodes());
//        doc.setEventCodes(this.setting.getEventCodes());        
//        doc.setLanguage(getLanguage());
//        doc.setMimeType(MIME_TYPE);
//        //doc.setMisysPatientId();
//        doc.setPatientDescriptor(getPatientDescriptor());
//        doc.setServiceStart(getServiceStartTime());
//        doc.setServiceEnd(getServiceEndTime());
//        doc.setSourceId(this.setting.getHomeSystemId());
//        doc.setTitle(getTitle());
//        doc.setTypeCode(this.setting.getTypeCode());
//        doc.setUniqueId(getDocId());
//
//        //StyleSheet does not make sense for Scanned and Consent documents.
//        if (this.setting.getFormatCode() == SharedEnums.XdsFormatCode.IHE_SCAN_PDF_1X ||
//            this.setting.getFormatCode() == SharedEnums.XdsFormatCode.IHE_SCAN_TEXT_1X ||
//            this.setting.getFormatCode() == SharedEnums.XdsFormatCode.BPPC   )
//            doc.setContents(XmlBeanUtil.toXml(doc1, false));
//        else
//            doc.setContents(XmlBeanUtil.toXml(doc1, true));
//        return doc;
    }

    /**
     * Speeds the cursor's garbage collection and returns the updated XML.
     */
    void dispose() {
        pathCursor.dispose();
    }

    //Select a path, and retrieve its value
    private String selectPath(String path) {
        String ret = null;

        pathCursor.push();

        pathCursor.selectPath(nsDeclare + "$this/ns:ClinicalDocument/" + path);
        if (pathCursor.getSelectionCount() > 0) {
            pathCursor.toNextSelection();
            ret = pathCursor.getTextValue();
            log.debug("path=" + path + ", value="  + ret );
        }

        pathCursor.pop();

        return ret;
    }


    /**
     * Gets the author institution
     *
     * @return the inistitution
     */
    public String getAuthorInstitution() {
        return selectPath("ns:author/ns:assignedAuthor/ns:representedOrganization/ns:name");
    }

    /**
     * Gets the authors of the CDA documents
     *
     * @return the Provider representing the author
     */
    public Provider getAuthorPerson() {
        Provider provider = new Provider();

        String path = "ns:author/ns:assignedAuthor/ns:assignedPerson/ns:name/";
        provider.setProvNameFirst (selectPath(path + "ns:given"));
        provider.setProvNameMiddle(selectPath(path + "ns:middle"));
        provider.setProvNameLast  (selectPath(path + "ns:family"));
        provider.setProvNameSuffix(selectPath(path + "ns:suffix"));
        provider.setProvNameTitle (selectPath(path + "ns:prefix" ));
        provider.setProviderId(selectPath("ns:author/ns:id/@extension"));

        return provider;
    }

    /**
     * Gets the creation time of this CDA document.
     *
     * @return the time in <code>Date</code>
     */
    public Date getCreationTime() {
        String time = selectPath("ns:effectiveTime/@value");
        Date date = null;
        try {
            date = BaseBuildingComponent.dfLong.parse(time);
        } catch (ParseException e) {
            log.error(e);
        }
        return date;
    }

    /**
     * Gets the language code of this CDA document.
     *
     * @return the language code in format such as "en-US"
     */
    public String getLanguage() {
        return selectPath("ns:languageCode/@code");
    }
    /**
     * Gets the document unique ID.
     *
     * @return the ID conforms to IHE metadata format root^extension
     */
    public String getDocId() {
        String root = selectPath("ns:id/@root");
        String extension = selectPath("ns:id/@extension");
        if (StringUtil.goodString(extension)) return root + "^" + extension;
        else return root;
    }

    /**
     * Gets the patient ID
     *
     * @return the source patient ID
     */
    public String getSourcePatientId() {
        //concat($patID/@extension,"^^^&", $patID/@root, "&ISO")
        return selectPath("ns:recordTarget/ns:patientRole/ns:id/@extension") + "^^^&" +
               selectPath("ns:recordTarget/ns:patientRole/ns:id/@root") + "&ISO";
    }

    /**
     * Gets the <tt>PatientDescriptor</tt> from this CDA document
     *
     * @return the <tt>PatientDescriptor</tt>
     */
    PatientDescriptor getPatientDescriptor() {
        PatientDescriptor pd = new PatientDescriptor();

        //set PatientID
        String path = "ns:recordTarget/ns:patientRole/";
        String misysUniqueId = selectPath(path + "ns:id/@extension");
        PatientID pid = new PatientID(misysUniqueId);
        //pid.addId(this.setting.getHomeSystemId(), this.setting.getHomeSystemPatientId());
        pd.setPatientId(pid);
        //pd.setHomeSystem(this.setting.getHomeSystemId());
        path = "ns:recordTarget/ns:patientRole/ns:patient/";
        pd.setNameFirst (selectPath(path + "ns:name/ns:given"));
        pd.setNameMiddle(selectPath(path + "ns:name/ns:middle"));
        pd.setNameLast  (selectPath(path + "ns:name/ns:family"));
        pd.setNameSuffix(selectPath(path + "ns:name/ns:suffix"));
        pd.setNameTitle (selectPath(path + "ns:name/ns:prefix" ));

        String sex = selectPath( path + "ns:administrativeGenderCode/@code");
        pd.setAdministrativeSex(SharedEnums.SexType.UNKNOWN.cdaValueOf(sex) );
         String birthdate = selectPath(path + "ns:birthTime/@value");
        Date birth = null;
        try {
            if (birthdate != null)
              birth = BaseBuildingComponent.dfShort2.parse(birthdate);
        } catch (ParseException e) {
            log.error(e);
        }
        if (null != birth) pd.setBirthDateTime(birth);

        String addressPath = "ns:recordTarget/ns:patientRole/ns:addr";
        String addr = selectPath(addressPath);
        if (StringUtil.goodString(addr)) {
            Address address = new Address();
            address.setAddLine1  (selectPath(addressPath + "/ns:streetAddressLine"));
            address.setAddCity   (selectPath(addressPath + "/ns:city"));
            address.setAddState  (selectPath(addressPath + "/ns:state"));
            address.setAddCountry(selectPath(addressPath + "/ns:country"));
            address.setAddZip    (selectPath(addressPath + "/ns:postalCode"));
            List<Address> lAddresses = new ArrayList<Address>();
            lAddresses.add(address);
            pd.setAddressList(lAddresses);
        }

        String number = selectPath("ns:recordTarget/ns:patientRole/ns:telecom/@value");
        if (StringUtil.goodString(number)) {
            PhoneNumber phone = new PhoneNumber();
            SharedEnums.PhoneType type = SharedEnums.PhoneType.UNKNOWN.cdaValueOf(selectPath("ns:recordTarget/ns:patientRole/ns:telecom/@use"));
            phone.setType( type );
            String phoneNumber = number.replaceAll("tel:", "");
            phone.setAreaCode(parsePhoneArea(phoneNumber));
            phone.setNumber(parsePhoneNumber(phoneNumber));
            List<PhoneNumber> lPhones = new ArrayList<PhoneNumber>();
            lPhones.add(phone);
            pd.setPhoneList(lPhones);
        }

        return pd;
    }

    /**
     * Gets the area code of a phone number.
     *
     * @param phoneNumber the phone number is of the format (xxx)xxx-xxx
     * @return the phone area code
     */
    private String parsePhoneArea(String phoneNumber) {
        int left = phoneNumber.indexOf("(");
        int right = phoneNumber.indexOf(")");
        if (left == -1 || right == -1)
            return null;

        return phoneNumber.substring(left+1, right);
    }
    private String parsePhoneNumber(String phoneNumber) {
        int left = phoneNumber.indexOf("(");
        int right = phoneNumber.indexOf(")");
        if (left == -1 || right == -1)
            return phoneNumber;

        return phoneNumber.substring(right+1);

    }
    /**
     * Gets the start time of the service that this CDA document describes
     *
     * @return the start time
     */
    public Date getServiceStartTime() {
        String start = selectPath("ns:documentationOf/ns:serviceEvent/ns:effectiveTime/ns:low/@value");
        Date date = null;

        if (StringUtil.goodString(start)) {
            try {
                date = BaseBuildingComponent.dfLong.parse(start);
            } catch (ParseException e) {
                log.error(e);
            }
        }
        return date;
    }

    /**
     * Gets the end time of the service that this CDA document describes
     *
     * @return the end time
     */
    public Date getServiceEndTime() {
        String end = selectPath("ns:documentationOf/ns:serviceEvent/ns:effectiveTime/ns:high/@value");
        Date date = null;

        if (StringUtil.goodString(end)) {
            try {
                date = BaseBuildingComponent.dfLong.parse(end);
            } catch (ParseException e) {
                log.error(e);
            }
        }
        return date;
    }

    /**
     * Gets the title code of this CDA document.
     *
     * @return the document title
     */
    public String getTitle() {
        return selectPath("ns:title");
    }
}
