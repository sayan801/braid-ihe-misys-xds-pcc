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

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.doc.ccd.mapping.MapCCDDocument;
import com.misyshealthcare.connect.doc.ccd.mapping.MapConsentDocumentData;
import com.misyshealthcare.connect.doc.ccd.mapping.MapDischargeSummaryData;
import com.misyshealthcare.connect.doc.ccd.mapping.MapEDReferralData;
import com.misyshealthcare.connect.doc.ccd.mapping.MapReferralSummaryData;
import com.misyshealthcare.connect.doc.ccd.mapping.MapScannedDocumentData;
import com.misyshealthcare.connect.doc.ccd.mapping.MapSubmissionSet;

/**
 *  IheServiceSkeleton java skeleton for the CCD document creation and submission
 */
public class IheServiceSkeleton {
	private Logger log = Logger.getLogger(IheServiceSkeleton.class);
	
    /**
     * This is used to build the ConsentDocument.
     * @param buildConsentDocument
     * @return <code>BuildConsentDocumentResponse</code>
     */
    public com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse buildConsentDocument(
        com.misyshealthcare.connect.doc.ccd.BuildConsentDocument buildConsentDocument) {
    	com.misyshealthcare.connect.doc.ccd.xsd.ConsentDocumentData wsConsDoc = buildConsentDocument.getData();
    	com.misyshealthcare.connect.doc.ccd.ConsentDocumentData consDoc = new ConsentDocumentData();
    	
		MapConsentDocumentData.process(wsConsDoc, consDoc);
    	log.info("Mapping for Consent Document request completed.");
    	
    	com.misyshealthcare.connect.doc.ccd.CCDDocument ccdDoc = null;
    	ccdDoc = IheService.buildConsentDocument(consDoc);
    	
		//Convert com.misyshealthcare.connect.doc.ccd.CCDDocument to com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument
    	com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument ccdDocument = MapCCDDocument.process(ccdDoc);
    	com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse consRes = new com.misyshealthcare.connect.doc.ccd.BuildConsentDocumentResponse();
    	log.info("returned Consent Document response.");
    	consRes.set_return(ccdDocument);
    	
        return consRes;
    }

    /**
     * This is used to build the ScannedDocument.
     * @param buildScannedDocument
     * @return <code>BuildScannedDocumentResponse</code>
     */
    public com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse buildScannedDocument(
        com.misyshealthcare.connect.doc.ccd.BuildScannedDocument buildScannedDocument) {
    	com.misyshealthcare.connect.doc.ccd.xsd.ScannedDocumentData wsScanDoc = buildScannedDocument.getData();
    	com.misyshealthcare.connect.doc.ccd.ScannedDocumentData scanDoc = new ScannedDocumentData();
    	
		MapScannedDocumentData.process(wsScanDoc,scanDoc);
    	log.info("Mapping for Scanned Document request completed.");
    	
    	com.misyshealthcare.connect.doc.ccd.CCDDocument ccdDoc = null;
    	ccdDoc = IheService.buildScannedDocument(scanDoc);
    	
		//Convert com.misyshealthcare.connect.doc.ccd.CCDDocument to com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument
    	com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument ccdDocument = MapCCDDocument.process(ccdDoc);
    	com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse scanRes = new com.misyshealthcare.connect.doc.ccd.BuildScannedDocumentResponse();
    	scanRes.set_return(ccdDocument);
    	log.info("returned ScannedDocument response.");
        return scanRes;
    }

    /**
     * Auto generated method signature
     * @param submitDocuments
     */
    public com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse submitDocuments(
        com.misyshealthcare.connect.doc.ccd.SubmitDocuments submitDocuments) {
    	com.misyshealthcare.connect.doc.ccd.xsd.SubmissionSet wsSubmissionSet = submitDocuments.getSubmissionSet();
    	SubmissionSet submissionSet = new SubmissionSet();
    	
    	submissionSet = MapSubmissionSet.process(wsSubmissionSet);
    	log.info("Mapping for Submit Documents request completed.");
    	
    	Error[] errors = IheService.submitDocuments(submissionSet);
    	
    	com.misyshealthcare.connect.doc.ccd.xsd.Error[] wsErrors =  MapCCDDocument.convert(errors);
    	com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse res = new com.misyshealthcare.connect.doc.ccd.SubmitDocumentsResponse();
    	res.set_return(wsErrors);
    	log.info("returned SubmitDocuments response.");
    	return res;
    }

    /**
     * This is used to build the EDReferral.
     * @param buildEDReferral
     * @return <code>EDReferral</code>
     */
    public com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse buildEDReferral(
        com.misyshealthcare.connect.doc.ccd.BuildEDReferral buildEDReferral) {
    	com.misyshealthcare.connect.doc.ccd.xsd.EDReferralData wsEDR = buildEDReferral.getData();
    	com.misyshealthcare.connect.doc.ccd.EDReferralData edReferralData = new EDReferralData();

    	MapEDReferralData.process(wsEDR, edReferralData);
    	log.info("Mapping for EDReferral Summary Document request completed.");
    	
    	com.misyshealthcare.connect.doc.ccd.CCDDocument ccdDoc = null;
    	ccdDoc = IheService.buildEDReferral(edReferralData);
    	
		//Convert com.misyshealthcare.connect.doc.ccd.CCDDocument to com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument
    	com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument ccdDocument = MapCCDDocument.process(ccdDoc);
		com.misyshealthcare.connect.doc.ccd.BuildEDReferralResponse edrRes = new BuildEDReferralResponse();
		edrRes.set_return(ccdDocument);
		log.info("retuned EDReferral response.");
        return edrRes;
    }

    /**
     * This is used to build the ReferralSummary.
     * @param buildReferralSummary
     * @return <code>BuildReferralSummaryResponse</code>
     */
    public com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse buildReferralSummary(
        com.misyshealthcare.connect.doc.ccd.BuildReferralSummary buildReferralSummary) {
    	com.misyshealthcare.connect.doc.ccd.xsd.ReferralSummaryData wsReferral = buildReferralSummary.getData();
    	//Convert com.misyshealthcare.connect.doc.ccd.xsd.ReferralSummaryData to com.misyshealthcare.connect.doc.ccd.ReferralSummaryData
    	com.misyshealthcare.connect.doc.ccd.ReferralSummaryData referralData = new ReferralSummaryData();
    	
		MapReferralSummaryData.process(wsReferral,referralData);
		log.info("Mapping for Referral Summary Document request completed.");
    	
    	com.misyshealthcare.connect.doc.ccd.CCDDocument ccdDoc = null;
    	ccdDoc = IheService.buildReferralSummary(referralData);
    	
		//Convert com.misyshealthcare.connect.doc.ccd.CCDDocument to com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument
    	com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument ccdDocument = MapCCDDocument.process(ccdDoc);
		com.misyshealthcare.connect.doc.ccd.BuildReferralSummaryResponse brsRes = new BuildReferralSummaryResponse();
		brsRes.set_return(ccdDocument);
		log.info("returned Referral Summary response.");
		
        return brsRes;
    }

    /**
     * This is used to build the DischargeSummary.
     * @param buildDischargeSummary
     * @return <code>BuildDischargeSummaryResponse</code>
     */
    public com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse buildDischargeSummary(
        com.misyshealthcare.connect.doc.ccd.BuildDischargeSummary buildDischargeSummary) {
    	com.misyshealthcare.connect.doc.ccd.xsd.DischargeSummaryData wsDisSum = buildDischargeSummary.getData();
    	com.misyshealthcare.connect.doc.ccd.DischargeSummaryData disSum = new DischargeSummaryData();
		MapDischargeSummaryData.process(wsDisSum, disSum);
		log.info("Mapping for Discharge Summary Document request completed.");
    	
    	com.misyshealthcare.connect.doc.ccd.CCDDocument ccdDoc = null;
    	ccdDoc = IheService.buildDischargeSummary(disSum);
    	
    	//Convert com.misyshealthcare.connect.doc.ccd.CCDDocument to com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument
    	com.misyshealthcare.connect.doc.ccd.xsd.CCDDocument ccdDocument = MapCCDDocument.process(ccdDoc);
    	com.misyshealthcare.connect.doc.ccd.BuildDischargeSummaryResponse discRes = new BuildDischargeSummaryResponse();
    	discRes.set_return(ccdDocument);
    	log.info("returned DischargeSummary response.");
        return discRes;
    }
}
