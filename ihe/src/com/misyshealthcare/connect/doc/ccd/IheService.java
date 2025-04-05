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


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.activation.CommandMap;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentBroker;
import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.util.CommandMapUtil;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.util.StringUtil;



/**
 * This class provides IHE Services for CCD document creation and submission. 
 * Currently, the types of CCD documents that can be generated are ReferralSummary, DischargeSummary,
 * and EDR(EmergencyDepartmentReferral). In addition, it can generate XDS-Scanned Document and
 * BPPC Consent Document. 
 * <p>
 * When a document is created, a CCDDocument object including the document metadata 
 * and the document xml content is returned. From the returned CCDDocument, a 
 * SubmissionSet object can be constructed, and subsequently submitted to an XDS repository.   
 * 
 * @author Wenzhi Li
 * @version 3.0, Nov 27, 2007
 * @see ReferralSummaryBuilder
 * @see EDReferralBuilder
 * @see DischargeSummaryBuilder
 * @see ScannedDocumentBuilder
 * @see ConsentDocumentBuilder
 */
public class IheService {
    private static final Logger log = Logger.getLogger( IheService.class.getName() );
    private static String tempFolder = System.getProperty("user.home")+File.separator+"IHECCD"+File.separator;
	//added to reset the default command map. submitDocuments relies on SUN SAAJ attachment
	//which updated MailcapCommandMap that is incompatible with Axis2	   
    static {
	    CommandMap.setDefaultCommandMap(null);  
    	CommandMap cm = CommandMap.getDefaultCommandMap();
    	CommandMapUtil.addMailmap( cm );
    }    
        
    /**
     * Hack to allow outside apps to set test patient id in all XDS
     * sources and consumers.
     *
     * @param patientId The fully qualified id of the patient to set.
     */
    public static void setTestPatientId(String patientId) {
    	DocumentBroker.getInstance().setTestPatientId(patientId);
    }
	 
    /**
     * Builds a Referral Summary document.
     * 
     * @param data
     * @return a CCDDocument
     */
    public static CCDDocument buildReferralSummary(ReferralSummaryData data) {
         IDocBuilder builder =  new ReferralSummaryBuilder(data);
         CCDDocument doc = null;
         try {
        	 if(log.isDebugEnabled()){
          		doc = builder.build(tempFolder+"ReferralSummary.xml");
         	 }
          	else
          		doc = builder.build();
		} catch (CCDException e) {		
			doc = handleBuilderException(e, "Referral Summary");   	 
		}
        return doc;
	 }

     /**
      * Builds a Discharge Summary document.
      * 
      * @param data
      * @return a CCDDocument
      */
     public static CCDDocument buildDischargeSummary(DischargeSummaryData data) {
         IDocBuilder builder =  new DischargeSummaryBuilder(data);
         CCDDocument doc = null;
         try {
        	 if(log.isDebugEnabled()){
         		doc = builder.build(tempFolder+"DischargeSummary.xml");
        	 }
         	else
         		doc = builder.build();
		} catch (CCDException e) {		
			doc = handleBuilderException(e, "Discharge Summary");   	 
		}
        return doc;
     }
	 
     /**
      * Builds Emergency Department Referral document.
      *  
      * @param data
      * @return a CCDDocument
      */
     public static CCDDocument buildEDReferral(EDReferralData data) {
         IDocBuilder builder =  new EDReferralBuilder(data);
         CCDDocument doc = null;
         try {
        	 if(log.isDebugEnabled()){
         		doc = builder.build(tempFolder+"EDReferralSummary.xml");
        	 }
         	else
         		doc = builder.build();
		} catch (CCDException e) {		
			doc = handleBuilderException(e, "ED Referral");   	 
		}
        return doc;
     }
     
     /**
      * Builds a Scanned document.
      * 
      * @param data
      * @return a CCDDocument
      */
     public static CCDDocument buildScannedDocument(ScannedDocumentData data) {
         IDocBuilder builder =  new ScannedDocumentBuilder(data);
         CCDDocument doc = null;
         try {
        	 if(log.isDebugEnabled()){
         		doc = builder.build(tempFolder+"ScannedDocument.xml");
        	 }
         	else
         		doc = builder.build();
		} catch (CCDException e) {		
			doc = handleBuilderException(e, "Scanned Document");   	 
		}
        return doc;
     }
     
     /**
      * Builds a BPPC consent document.
      * 
      * @param data
      * @return a CCDDocument
      */
     public static CCDDocument buildConsentDocument(ConsentDocumentData data) {
         CCDDocument doc = null;
         try {
             IDocBuilder builder =  new ConsentDocumentBuilder(data);
        	 if(log.isDebugEnabled()){
         		doc = builder.build(tempFolder+"ConsentDocument.xml");
        	 }
         	else
         		doc = builder.build();
		} catch (CCDException e) {		
			doc = handleBuilderException(e, "BPPC Consent Document");   	 
		}
        return doc;
     }
     
     private static CCDDocument handleBuilderException(CCDException e, String type) {
			e.printStackTrace();
			log.error("Fail to build " + type + ": " + e.getMessage());			
			CCDDocument doc = new CCDDocument();
			Error error = new Error(Error.SeverityType.ERROR, e.getMessage());
			doc.setErrors(new Error[]{error});
			return doc;
     }
 
     /**
      * Submits a document set to IHE Repository and Registry.
      * 
      * @param submissionSet
      * @return an array of Errors if any
      */
     public static Error[] submitDocuments(SubmissionSet submissionSet) {
    	 //verify submission set data
    	 Error[] errors = verifySubmissionSet(submissionSet);
    	 if (errors != null) {
    		 return errors;
    	 }    	 
         CCDDocument[] ccddocs = submissionSet.getDocuments();
    	 if (ccddocs == null || ccddocs.length==0) {
    		 return new Error[]{new Error(Error.SeverityType.ERROR, "Missing document to be submitted.")};
    	 }
    	 List<Document> documents = new ArrayList<Document>();
    	 for (CCDDocument ccddoc : ccddocs) {
    		 String content = ccddoc.getContent();
    		 MetaData md = ccddoc.getMetadata();
    		 
        	 if (content == null) {
        		 return new Error[]{new Error(Error.SeverityType.ERROR, "Cannot submit an empty document.")};
        	 }
        	 
    	  	 Document doc = null;
			 try {
			 	doc = generateDocument(md);
			 } catch (PatientException e) {
				e.printStackTrace();
				return new Error[]{new Error(Error.SeverityType.ERROR, e.getMessage())};
			 }
    	 	 doc.setContents( content );
             documents.add( doc );
    	 }
    	 SubmissionSetMetaData ssMd = submissionSet.getSubmissionSetMetaData();
         DocumentBroker.getInstance().submitDocuments(documents, ssMd.getComments(), ssMd.getContentCode());
         return null;
     }
     
     private static Error[] verifySubmissionSet(SubmissionSet submissionSet) {
    	 List<Error> errors = new ArrayList<Error>();
    	 if (submissionSet == null) {
    		 errors.add(new Error(Error.SeverityType.ERROR, "Cannot submit empty submission set."));    		 
    	 }
    	 if (submissionSet.getDocuments()==null || submissionSet.getDocuments().length==0) {
    		 errors.add(new Error(Error.SeverityType.ERROR, "Cannot submit empty document."));    		     		 
    	 }
    	 if (submissionSet.getSubmissionSetMetaData() == null) {
    		 errors.add(new Error(Error.SeverityType.ERROR, "Missing submission set metadata."));    		     		 
    	 }
    	 if(submissionSet.getSubmissionSetMetaData() != null && submissionSet.getSubmissionSetMetaData().getContentCode() == null){
    		 errors.add(new Error(Error.SeverityType.ERROR, "Missing content code."));
    	 }
    	 
    	 CCDDocument[] docs = submissionSet.getDocuments();
    	 for (CCDDocument doc : docs) {
    		 MetaData md = doc.getMetadata();
    		 if (md.getCreationTime() == null)
        		errors.add(new Error(Error.SeverityType.ERROR, "Missing document creation time."));
    		  
    		 if (md.getUniqueDocumentId()==null || md.getUniqueDocumentId().getExtension()==null || 
    		     md.getUniqueDocumentId().getRoot()==null)    		 
         		errors.add(new Error(Error.SeverityType.ERROR, "Provide unique document id with root and extension."));    		     		 
    			 
    	 }
    	 
    	 if (errors.size() > 0) 
    		 return errors.toArray(new Error[errors.size()]);
    	 else 
    		 return null;
     }
     
     private static Document generateDocument(MetaData md) throws PatientException {
		 Document doc = new Document();
	 	 //author
		 if (md.getAuthors() != null && md.getAuthors().length > 0) {
			 doc.setAuthorDescriptors(convertAuthors(md.getAuthors()));
		 }
		 //class code
		 doc.setClassCode( md.getClassCode() );
		 //confidentiality code
		 SharedEnums.ConfidentialityCode[] ccodes = md.getConfidentialityCodes();
		 if (ccodes != null && ccodes.length >0) {
			 doc.setConfidentialityCodes(Arrays.asList( ccodes ));
		 }
		 //Creation Time
		 doc.setCreationTime( md.getCreationTime().getTime() );
		 //Event Code list
		 String[] eventCodes = md.getEventCodes();
		 if (eventCodes!=null && eventCodes.length>0) {
			 doc.setEventCodes( Arrays.asList( eventCodes ) );
		 }
		 //Facility code
		 doc.setFacilityCode( md.getFacilityTypeCode() );
		 //Format Code
		 doc.setFormatCode( md.getFormatCode() );
		 //Language Code
		 doc.setLanguage( md.getLanguageCode() );
		 //Legal Authenticator
		 Participant la = md.getLegalAutheticator();
		 doc.setLegalAuthenticator(convertParticipant(la) );
		 //Mime Type
		 if( md.getMimeType() != null ) {
			 doc.setMimeType( md.getMimeType().getValue() );
		 }
		 //set Patient Descriptor
		 doc.setPatientDescriptor( convertPatientInfo(md.getSourcePatientId(), md.getHomeSystemId(), md.getSourcePatientInfo()));
		 doc.setPatientId(doc.getPatientDescriptor().getPatientId());
		 doc.setPracticeCode( md.getPracticeSettingCode() );
		 
		 if (md.getServiceStopTime() != null)
			 doc.setServiceEnd( md.getServiceStopTime().getTime() );
		 if (md.getServiceStartTime() != null)
			 doc.setServiceStart( md.getServiceStartTime().getTime());
		 
		 doc.setSourceId( md.getHomeSystemId() );
		 doc.setTitle( md.getTitle() );
		 doc.setTypeCode( md.getTypeCode() );		 
		 doc.setUniqueId( md.getUniqueDocumentId().getRoot() + "." + md.getUniqueDocumentId().getExtension());
		 //check if there is a parent document to be replaced
		 Id parentId = md.getParentDocumentId();
		 if (parentId != null) {
			 doc.setReplacesId( parentId.getRoot() + "^" + parentId.getExtension() );
		 }
		 return doc;
     }
     
     private static PatientDescriptor convertPatientInfo(Id sourcePatientid, String systemName, SourcePatientInfo patient) throws PatientException {
    	 PatientDescriptor ret = new PatientDescriptor();
    	 Address[] addrs = patient.getAddress();    	 
    	 if (addrs != null && addrs.length >0) {
    		 ret.setAddressList(Arrays.asList(addrs));
    	 }
    	 ret.setAdministrativeSex( patient.getGender() );
    	 ret.setBirthDateTime( patient.getBirthdate().getTime() );
    	 ret.setNameFirst( patient.getPersonName().getFirstName() );
    	 ret.setNameLast( patient.getPersonName().getLastName() );
    	 ret.setNameMiddle( patient.getPersonName().getMiddleName() );
    	 ret.setNameSuffix( patient.getPersonName().getSuffix() );
    	 ret.setNameTitle( patient.getPersonName().getPrefix() );
    	
    	 LibraryConfig.IPatientIdConverter idConverter = LibraryConfig.getInstance().getPatientIdConverter();
		 String localPatientId = null;
    	 if(idConverter!=null) {
      	   localPatientId = idConverter.getPixLocalPatientId(sourcePatientid.getExtension(), systemName);
    	 } else {
    	   localPatientId = sourcePatientid.getExtension();
    	 }
    	 PatientID pid = new PatientID(localPatientId);    	 
    	 pid.addId(sourcePatientid.getRoot(), sourcePatientid.getExtension());
    	 ret.setPatientId(pid);
    	 PhoneNumber[] phones = patient.getPhoneNumber();
    	 if (phones==null && phones.length>0) {
    		 ret.setPatientId(pid);
    	 }
    	 ret.setPhoneList( Arrays.asList(phones) ) ;
    	 ret.setUniquePatientId( sourcePatientid.getExtension() );
    	 return ret;
     }
     
     private static List<AuthorDescriptor> convertAuthors(Author[] authors) {
    	 if (authors == null || authors.length==0)
    		 return new ArrayList<AuthorDescriptor>();
    	 
    	 List<AuthorDescriptor> ret = new ArrayList<AuthorDescriptor>();
    	 for (Author author : authors) {
    		 ret.add( convertAuthor(author) );
    	 }
    	 return ret;
     }
	 private static AuthorDescriptor convertAuthor(Author author) {
		 AuthorDescriptor ret = new AuthorDescriptor();
		 if( author.getOrganization() != null) { 
			 Organization org = author.getOrganization();
			 String orgName = org.getOrganizationName();
			 if(StringUtil.goodString(orgName)) {
			     List<String> orgs = new ArrayList<String>();
			     orgs.add(orgName);
				 ret.setAuthorInstitutions( orgs );
			 }
		 }
		 if (author.getAuthorPerson() != null) {
			 ret.setAuthorPerson( convertParticipant(author.getAuthorPerson()));
		 }
		 if (author.getAuthorRoles() != null) {
			 ret.setAuthorRoles( Arrays.asList(author.getAuthorRoles()));
		 }
		 if (author.getAuthorSpecialities() != null) {
			 ret.setAuthorSpecialities( Arrays.asList(author.getAuthorSpecialities()));
		 }
		 return ret;
     }
	 
	 private static Provider convertParticipant(Participant participant) {
		 if (participant == null) return null;
		 
		 Provider ret = new Provider();
		 Address[] addrs = participant.getAddress();
		 if (addrs != null && addrs.length > 0) { 
			 ret.setProvAddressList( Arrays.asList(addrs) );
		 }
		 ret.setProviderId(participant.getId().getRoot()+ "." + participant.getId().getExtension());
		 PersonName pn = participant.getPersonName();
		 if (pn != null) {
			 ret.setProvNameFirst(pn.getFirstName());
			 ret.setProvNameLast(pn.getLastName());
			 ret.setProvNameMiddle(pn.getMiddleName());
			 ret.setProvNameSuffix(pn.getSuffix());
			 ret.setProvNameTitle(pn.getPrefix());
		 }	
		 return ret;
	 }
		 
	 
}