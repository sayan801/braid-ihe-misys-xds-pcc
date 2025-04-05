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

import java.util.List;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.util.StringUtil;

/**
 * The document builder to build a CDA wrapped ScannedDocument(such as PDF/A).
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 25, 2005
 */
public class ScannedDocumentBuilder extends BaseBuilder {

    private ScannedDocumentData data;
    //TODO: set classCode
    protected final CodeSystem docCode = new CodeSystem("34133-9", "2.16.840.1.113883.6.1", "LOINC", "SUMMARIZATION OF EPISODE NOTE");
    private MetaDataAgent.Setting metadataSetting = null;

    public ScannedDocumentBuilder(ScannedDocumentData data) {
        super(data);
        this.data = data;
    }

    /**
     * Builds a CDA wrapped Scanned Document.
     *
     * @return the Document object to be built.
     * @throws CDAException if it fails to build a document.
     */
    public CCDDocument build() throws CCDException {
        if (!isValid() ) 
        	return getDocumentWithError(); 

        buildHeader();

        buildNonXmlBody( doc, data.getMediaType().getValue(), data.getFileContent());

        return getDocument(false);
    }

    /**
     * Builds the head part of XDS-MS CDA document
     */
    private void buildHeader() {
        buildHeader1( doc, metadata, getTemplateId(), docCode, getTitle());
        buildRecordTarget( doc, metadata.getSourcePatientId(), metadata.getSourcePatientInfo(), metadata.getPatientContacts() );
        CodeSystem cs = null;
        if (data.getMediaType() == ScannedDocumentData.MediaType.PDF) {
        	cs = new CodeSystem("CAPTURE", "1.2.840.10008.2.16.4", null, "Image Capture");
        } else if (data.getMediaType() == ScannedDocumentData.MediaType.TEXT) {
        	cs = new CodeSystem("WST", "1.2.840.10008.2.16.4", null, "Workstation");        	
        }
        buildAuthor( doc, metadata.getAuthors(), cs);
        buildAuthorDevice(doc, CDABuilder.connCDA, false);
        buildDataEnterer(doc, metadata.getDataEnterers(), true);
        if( metadata.getCustodian() != null) 
        	buildCustodian( doc, metadata.getCustodian(), true );
        else
        	buildCustodian( doc, CDABuilder.connCDA, true);
        buildLegalAuthenticator(doc, metadata.getLegalAutheticator());
        buildPatientContact(doc, metadata.getPatientContacts());
        buildDocumentOf( doc, metadata.getServiceStartTime(), metadata.getServiceStopTime(),  metadata.getPerformers(), false );
        buildComponentOf( doc, metadata.getEncounters(), false, false );    	
   }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = super.getTemplateId();
        return templateIds;
    }
    
    protected String getTitle() {
    	return getTitle("Scanned Document");
    }
    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
    	super.isValid();
    	//make the file content is valid
    	if( data.getFileContent() == null) {
    		addError("Miss scanned document file content");
    	}
    	if (data.getMediaType() == null) {
    		addWarning("Missing required media type. ");
    	}
        //DataEnterers is required
        if (null == metadata.getDataEnterers()) {
           addError("Missing data enterers required for scanned document");
        }
        if (metadata.getServiceStartTime() == null) {
            addError("Missing service start time required for scanned document");
        }
        boolean foundManufacturer = false;
        boolean foundSoftwareName = false;
        boolean foundOrgName = false;
        Author[] authors = metadata.getAuthors();
        if (authors == null || authors.length==0) {
            addError("Missing Scanner Author required for scanned document");
        } else {
        	for (Author author : authors) {
        		AuthorDevice ad = author.getAuthorDevice();
        		if (ad == null) continue;
        		else {
        			if(StringUtil.goodString(ad.getManufacturerModelName()))  foundManufacturer = true;
        			if(StringUtil.goodString(ad.getSoftwareName()))  foundSoftwareName  = true;
         		}
        		if(author.getOrganization() != null && StringUtil.goodString(author.getOrganization().getOrganizationName())) foundOrgName = true; 
        	}
        }
        if (!foundManufacturer) {
            addError("Missing scanner manufacturer model name required for scanned document");
        }
        if (!foundSoftwareName) {
            addError("Missing scanner software name required for scanned document");
        }
        if (!foundOrgName) {
            addError("Missing scanning organization name required for scanned document");
        }
        return !hasError();
    }

    /**
     * Gets the metadata setting of this builder.
     *
     * @return <code>MetaDataAgent.Setting</code>
     */
    synchronized MetaDataAgent.Setting getMetadataSetting() {
        if (metadataSetting==null) {
	    	//These are default metadata setting
	    	metadataSetting = new MetaDataAgent.Setting();
	    	metadataSetting.setClassCode(SharedEnums.XdsClassCode.Summarization_of_Episode);
	        if (data.getMediaType() == ScannedDocumentData.MediaType.PDF)
	        	metadataSetting.setFormatCode(SharedEnums.XdsFormatCode.IHE_SCAN_PDF_1X);
	        else if (data.getMediaType() == ScannedDocumentData.MediaType.TEXT)
	        	metadataSetting.setFormatCode(SharedEnums.XdsFormatCode.IHE_SCAN_TEXT_1X);
	    	metadataSetting.setFacilityTypeCode("Outpatient");
	    	metadataSetting.setPracticeSettingCode("Multidisciplinary");
	    	metadataSetting.setTypeCode(SharedEnums.XdsTypeCode.Summarization_of_Episode_Note);
        }
	    return metadataSetting;	    
    }



}
