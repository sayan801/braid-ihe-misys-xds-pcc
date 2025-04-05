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
import java.util.ArrayList;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.util.StringUtil;
import com.misyshealthcare.connect.net.EnumMap;

/**
 * @author Michael Traum
 * @version 2.0, November 22, 2006
 *
 */
public class ConsentDocumentBuilder extends BaseBuilder {

    private ConsentDocumentData data;
    //TODO: set classCode
    protected final CodeSystem docCode = new CodeSystem("34133-9", "2.16.840.1.113883.6.1", "LOINC", "SUMMARIZATION OF EPISODE NOTE");

    private List<CodeSystem> mappedPolicies = new ArrayList<CodeSystem>();
    private MetaDataAgent.Setting metadataSetting = null;

    public ConsentDocumentBuilder(ConsentDocumentData data) throws CCDException {
        super(data);
        this.data = data;

        SharedEnums.ConfidentialityCode[] policies = data.getConsentPolicies();
        if (policies == null || policies.length==0){
        	policies = new SharedEnums.ConfidentialityCode[]{SharedEnums.ConfidentialityCode.Normal};
        }
        for (SharedEnums.ConfidentialityCode policy : policies) {
            //Map the consent policy to the one used in the affinity domain
            EnumMap policyMap = CDABuilder.connConsentDoc.getEnumMap(SharedEnums.ConfidentialityCode.class);
            String value = policyMap.getCodeValue(policy);
            String[] parts = value.split("\\^", 5);
            if (parts.length != 5)
                throw new CCDException("ConfidentialityCode expects 5 code parts in ConsentDocument configuration.");

            CodeSystem affinityDomainPolicy = new CodeSystem(parts[0], parts[2], parts[3], parts[1]);
            mappedPolicies.add(affinityDomainPolicy);
         }
    }

    /**
     * Builds a BPPC Consent document.
     *
     * @return the Document object to be built.
     * @throws CCDException if it fails to build a document.
     */
    public CCDDocument build() throws CCDException {
        if (!isValid() )  return getDocumentWithError(); 

        buildHeader();

        buildNonXmlBody( doc, data.getMediaType().getValue(), data.getFileContent());

        return getDocument(false);
    }

    /**
     * Builds the head part of XDS-MS CDA document
     */
    private void buildHeader() throws CCDException{
        buildHeader1( doc, metadata, getTemplateId(), docCode, getTitle());
        buildRecordTarget( doc, metadata.getSourcePatientId(), metadata.getSourcePatientInfo(), metadata.getPatientContacts() );
        buildAuthor( doc, metadata.getAuthors(), null );
        buildAuthorDevice(doc, CDABuilder.connCDA, false);
        buildDataEnterer(doc, metadata.getDataEnterers(), true);
        if( metadata.getCustodian() != null) 
        	buildCustodian( doc, metadata.getCustodian(), true );
        else
        	buildCustodian( doc, CDABuilder.connCDA, true);
        buildLegalAuthenticator(doc, metadata.getLegalAutheticator());
        buildPatientContact(doc, metadata.getPatientContacts());
        buildDocumentOf( doc, metadata.getServiceStartTime(), metadata.getServiceStopTime(),  metadata.getPerformers(), true );
        buildComponentOf( doc, metadata.getEncounters(), false, false );    	

        //build Consent for each Policy
        for (CodeSystem policy : mappedPolicies) {
            buildAuthorization(doc, policy);
        }
        //update eventCode matadata
        metadata.setEventCodes( toEventCodes(mappedPolicies) );
    }

    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
        super.isValid();
    	if( data.getFileContent() == null) {
    		addError("Miss BPPC Consent document file content");
    	}
    	if (data.getMediaType() == null) {
    		addError("Missing required media type. ");
    	}
        
        if (data.getConsentPolicies()==null || data.getConsentPolicies().length==0) {
            addError("Missing consent policy required for consent document");
        }
        if (metadata.getDataEnterers()==null) {
        	addError("Missing data enterer required for consent document");
        }
        if (metadata.getServiceStartTime() == null) {
        	addError("Missing the start time of this consent document");
        }
      
  		return !hasError();
    }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = super.getTemplateId();
        templateIds.add(new Id(TID_BPPC_TEMPLATE, null));
        return templateIds;
    }
    protected String getTitle() {
    	return getTitle("BPPC Consent Document");
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
	    	metadataSetting.setFormatCode(SharedEnums.XdsFormatCode.BPPC);  //Let's use Scan Pdf by default
	    	metadataSetting.setFacilityTypeCode("Outpatient");
	    	metadataSetting.setPracticeSettingCode("Multidisciplinary");
	    	metadataSetting.setTypeCode(SharedEnums.XdsTypeCode.Summarization_of_Episode_Note);
        }
	    return metadataSetting;	 
    }

    /**
     * Converts a list of CodeSystems to a list of EventCode strings
     * @param codeSystems
     * @return an array EventCode strings
     */
    private String[] toEventCodes(List<CodeSystem> codeSystems) {
        String[] ret = new String[codeSystems.size()];
        for (int i = 0; i <codeSystems.size(); i++) {
            ret[i] = codeSystems.get(i).getCode();  
        }
        return ret;
    }
}
