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

import hl7OrgV3.ClinicalDocumentDocument1;
import hl7OrgV3.POCDMT000040ClinicalDocument1;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.util.StringUtil;

/**
 * This is the base class of all the document builders.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 15, 2006
 */
abstract public class BaseBuilder extends BaseBuildingComponent implements IDocBuilder {
    protected CCDData data;
    protected MetaData metadata;

    /*A list errors to return back to caller*/
    protected List<Error> errors = new ArrayList<Error>();
    
    protected ClinicalDocumentDocument1 doc1;
    protected POCDMT000040ClinicalDocument1 doc;

    /** default constructor */
    BaseBuilder(){}

    /**
     * Constructor
     * @param data
     */
    BaseBuilder(CCDData data) {
        this.data = data;
        doc1 = ClinicalDocumentDocument1.Factory.newInstance();
        doc = doc1.addNewClinicalDocument();
        metadata = data.getMetadata();
    }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = new ArrayList<Id>();
        templateIds.add( new Id("2.16.840.1.113883.10", "IMPL_CDAR2_LEVEL1") );
        templateIds.add( new Id("2.16.840.1.113883.10", "IMPL_CDAR2_LEVEL2") );
        templateIds.add( new Id(TID_MEDICAL_DOC_TEMPLATE, null) );
        templateIds.add( new Id(HL7_GENERAL_HEADER_CONSTRAINTS, null) );        
        return templateIds;
    }
    
    protected String getTitle(String documentType) {
    	String patientName = data.getMetadata().getSourcePatientInfo().getPersonName().getFormattedName();
        String title = data.getMetadata().getTitle();
        String ret  = StringUtil.goodString(title) ?  title : documentType + " for " + patientName ;
        metadata.setTitle(ret);
        return ret;
    }

    /**
     * Get a CCDDocument object with Error only. No document file content would
     * be populated. 
     * 
     * @return a CCDDocument object with Error.
     */
    protected CCDDocument getDocumentWithError() {
    	CCDDocument ccdDocument = new CCDDocument();
    	ccdDocument.setErrors( errors.toArray(new Error[errors.size()]));
    	return ccdDocument;     	
    }
    
    /**
     * Gets the CCDDocument of this document.  This method 
     * can only be called after the build is called. 
     *
     * @param whether to add stylesheet to the CCD document. 
     * @return the Document containing the metadata
     * @see ReferralSummaryBuilder#build()
     */
    protected CCDDocument getDocument(boolean addStyleSheet) {
        CCDDocument ccdDocument = new CCDDocument(); 
        ccdDocument.setContent(getDocumentString( addStyleSheet ) );
    	ccdDocument.setErrors( errors.toArray(new Error[errors.size()]));
    	processMetadata();
        ccdDocument.setMetadata( metadata );

        return ccdDocument;
    }
    
    /**
     * This method set the default metadata for the given 
     * type of ccd document.
     */
    private void processMetadata() {
    	MetaDataAgent.Setting setting = getMetadataSetting();
    	//Set class code
    	if(metadata.getClassCode()==null || 
    	   metadata.getClassCode()==SharedEnums.XdsClassCode.UNKNOWN) {
        	metadata.setClassCode(setting.getClassCode());    		
    	}
    	//set format code
    	metadata.setFormatCode(setting.getFormatCode());
    	//Set HealthCare FacilityType code
    	if(metadata.getFacilityTypeCode()==null) {
    		metadata.setFacilityTypeCode(setting.getFacilityTypeCode());
    	}
        //Set fixed MimeType  "text/xml"
    	metadata.setMimeType(SharedEnums.MimeTypes.XML);
    	//set Practice Setting
    	if (metadata.getPracticeSettingCode()==null) {
        	metadata.setPracticeSettingCode(setting.getPracticeSettingCode());    		
    	}
    	//set Type Code
    	if (metadata.getTypeCode()==null || 
    	    metadata.getTypeCode()==SharedEnums.XdsTypeCode.UNKNOWN) {
    		metadata.setTypeCode( setting.getTypeCode() );
    	}
    	 
    }

    /**
     * Gets the metadata setting of this builder.
     *
     * @return <code>MetaDataAgent.Setting</code>
     */
    abstract MetaDataAgent.Setting getMetadataSetting();

    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
    	//MetaData is required
    	if (data == null) {
    		addError("Missing document data");
    	}
    	if (metadata == null) {
    		addError("Missing MetaData" ); 
    		return false;
    	}    	
    	SourcePatientInfo patientInfo = metadata.getSourcePatientInfo();
    	if (patientInfo == null) {
    		addError("Missing sourcePatientInfo");
    		return false;
    	}    
    	PersonName patientName = patientInfo.getPersonName();
    	if (patientName == null) {
    		addError("Missing Patient Name");
    		return false;
    	}
    	
    	//Require at least one performer
    	if(metadata.getPerformers() == null || metadata.getPerformers().length==0) {
    		addWarning("At least one performer for this document service is needed");
    	}
    	
    	//verify patient
    	if(metadata.getSourcePatientId()==null || metadata.getSourcePatientId().getRoot()==null
    			|| metadata.getSourcePatientId().getExtension() == null) {
    		addError("Missing Source PatientId. Provides both Root and Exetension");    		
    	}
    	if(metadata.getSourcePatientInfo()==null) {
    		addError("Missing SoucePatientInfo");
    	}
    	if(metadata.getSourcePatientInfo().getPersonName()==null) {
    		addError("Missing souce PatientName");
    	}
    	
    	//if available, PatientContact must have a PatientContactType 
    	PatientContact[] pcs = metadata.getPatientContacts();
    	if (pcs != null) {
	    	for (PatientContact pc : pcs) {
	    		if (pc.getPatientContactType() == null)
	    			addError("PatientContact must have a PatientContact Type");
	    		if (pc.getParticipant()==null)
	    			addError("PatientContact must have a Participant");
	    	}
    	}
    	//Now, handle all errors.
    	if(hasError()) return false;
    	else return true;
    		
        //Patient is required
//        if (null == data.getPatient())
//            throw new CCDException("Null or invalid patient");
//        if (null == data.getPatient().getPatientId())
//            throw new CCDException("Null or invalid patient id");
    }
    
    /**
     * Adds an error message
     * 
     * @param errorMessage
     */
    protected void addError(String errorMessage) {
    	log.error(errorMessage);
		errors.add(new Error(Error.SeverityType.ERROR, errorMessage));
    }
    /**
     * Adds an warning message
     * 
     * @param warningMessage
     */
    protected void addWarning(String warningMessage) {
    	log.warn(warningMessage);
		errors.add(new Error(Error.SeverityType.WARNING, warningMessage));
    }
    /**
     * Check whether the list of ERROR object contains real error whose
     * severity is ERROR.
     * 
     * @return
     */
    protected boolean hasError() {
    	for (Error error : errors) {
    		if (error.getSeverityType() == Error.SeverityType.ERROR)
    			return true;
    	}
    	return false;
    }
    /**
     * Builds a Referral Summary document, which conforms to the IHE XDS-MS standard. The document is
     * also output to a file.
     *
     * @param fileName the file name of the CCD document to be built
     * @return the CCDDocument object to be built.
     * @throws CCDException if it fails to build a document,
     */
    public CCDDocument build(String fileName) throws CCDException {
        PrintWriter writer = null;
        CCDDocument document = null;
        try {
            File file = new File(fileName);

            //create parent fold first
            String parent = file.getParent();
            File parentDir = new File(parent);
            if(!parentDir.exists()) parentDir.mkdirs();

            writer = new PrintWriter(file);
            document = build();
            if(document.getContent() != null)
            	writer.write((String)document.getContent());
            writer.flush();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            //throw new CCDException("Fail to build CDA document.", e);
        }
        finally {
            if (null != writer) {
                writer.close();
            }
        }
        return document;
    }

    protected void logDocument(String docType, boolean addStyleSheet) {
        String xmlDoc = XmlBeanUtil.toXml(doc1, addStyleSheet);
        log.debug( "The "+ docType +" Doc created:");
        log.debug( xmlDoc );
    }
    
    protected String getDocumentString(boolean addStyleSheet) {
        String xmlDoc = XmlBeanUtil.toXml(doc1, addStyleSheet);
        //log.debug( xmlDoc ); IheService class stores document to User Home directory with document name.
        return xmlDoc;
    }


}
