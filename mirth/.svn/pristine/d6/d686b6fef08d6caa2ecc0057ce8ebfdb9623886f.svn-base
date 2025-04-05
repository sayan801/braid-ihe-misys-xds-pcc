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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.ClinicalStatusCode;
import com.misyshealthcare.connect.base.clinicaldata.Allergy;
import com.misyshealthcare.connect.base.clinicaldata.Code;
import com.misyshealthcare.connect.base.clinicaldata.DoseQuantity;
import com.misyshealthcare.connect.base.clinicaldata.Medication;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.clinicaldata.SimpleProblem;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.ObjectEntry;
import com.misyshealthcare.connect.net.ObjectList;
import com.misyshealthcare.connect.util.StringUtil;

/**
 * This class is used to build all different types of CDA documents.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 14, 2006
 */
public class CDABuilder {

    private static final Logger log = Logger.getLogger(CDABuilder.class);

    public static IConnectionDescription connCDA = null;  //ConnectioNDescription for CDA
    public static IConnectionDescription connDischargeSummary = null;   //ConnectionDescription for Discharge Summary
    public static IConnectionDescription connReferralSummary = null;   //ConnectionDescription for Referral Summary
    public static IConnectionDescription connEDReferer = null;   //ConnectionDescription for EDR
    public static IConnectionDescription connScannedDoc = null;   //ConnectionDescription for ScannedDocument
    public static IConnectionDescription connConsentDoc = null;   //ConnectionDescription for ConsentDocument
    /**
     * The system property to check for the document building file name.
     */
    private static final String CONFIG_FILENAME_SYSTEM_PROPERTY = "misys.cda.config";
    /**
     * The filename to use for document building if none is specified in the system property.
     */
    private static final String DEFAULT_PROPERTY_FILENAME = "/config/documentbuilding/DocumentBuilding.xml";

    static {
        //System.out.println(">>>>>>>>>>>>>>>>> Patched CDABuilder Loaded...");
        File file = null;
        String sysProp = System.getProperty(CONFIG_FILENAME_SYSTEM_PROPERTY);
        if (sysProp != null) {
            file = new File(sysProp); 
            System.out.println("Using File: " + sysProp);
            log.debug("Using File: " + sysProp);
        } else {
            URL url = CDABuilder.class.getResource(DEFAULT_PROPERTY_FILENAME);

            URI uri = null;
            try {
                uri = url.toURI();
            } catch (URISyntaxException e) {
                log.error("Fail to load property: " + DEFAULT_PROPERTY_FILENAME, e);
            }
            file = new File(uri);            
            log.debug("Using Resource: " + DEFAULT_PROPERTY_FILENAME);
        }
        ConnectionFactory.loadConnectionDescriptionsFromFile(file);

        connDischargeSummary = ConnectionFactory.getConnectionDescription("DischargeSummary");
        if (null==connDischargeSummary)
            throw new NullPointerException("Missing Configuration Description for DischargeSummary");

        connReferralSummary = ConnectionFactory.getConnectionDescription("ReferralSummary");
        if (null==connReferralSummary)
            throw new NullPointerException("Missing Configuration Description for ReferralSummary");

        connCDA = ConnectionFactory.getConnectionDescription("CDA");
        if (null==connCDA)
            throw new NullPointerException("Missing Configuration Description for CDA");

        connEDReferer = ConnectionFactory.getConnectionDescription("EDReferral");
        if (null==connEDReferer)
            throw new NullPointerException("Missing Configuration Description for EDReferral");

        connScannedDoc = ConnectionFactory.getConnectionDescription("ScannedDocument");
        if (null==connScannedDoc)
            throw new NullPointerException("Missing Configuration Description for ScannedDocument");

        connConsentDoc = ConnectionFactory.getConnectionDescription("ConsentDocument");
        if (null==connConsentDoc)
            throw new NullPointerException("Missing Configuration Description for ConsentDocument");
    }

    private static CDABuilder instance = null;

    synchronized public static CDABuilder getInstance() {
        if (instance == null) {
            instance = new CDABuilder();
        }
        return instance;
    }

    public CCDDocument buildDischargeSummary(DischargeSummaryData data) {
        return buildDischargeSummary(data, null);
    }
    public CCDDocument buildDischargeSummary(DischargeSummaryData data, String outputFile) {
        try{
            populateDischargeSummaryData( data );
            IDocBuilder builder =  new DischargeSummaryBuilder(data);
            if (StringUtil.goodString(outputFile))
                return builder.build(outputFile);
            else
                return builder.build();
        } catch (CCDException e) {
            log.error("Failed to build Discharge Summary", e);
            throw new DocumentException("Failed to build Discharge Summary", e);
        }
     }

    public CCDDocument buildReferralSummary(ReferralSummaryData data) {
        return buildReferralSummary(data, null);
    }
    public CCDDocument buildReferralSummary(ReferralSummaryData data, String outputFile) {
        try{
            populateReferralSummaryData( data, connReferralSummary );
            IDocBuilder builder =  new ReferralSummaryBuilder(data);
            if (StringUtil.goodString(outputFile))
                return builder.build(outputFile);
            else
                return builder.build();
        } catch (CCDException e) {
            log.error("Failed to build Referral Summary", e);
            throw new DocumentException("Failed to build Referral Summary", e);
        }
    }

    public CCDDocument buildEDReferral(EDReferralData data) {
        return buildEDReferral(data, null);
    }
    public CCDDocument buildEDReferral(EDReferralData data, String outputFile) {
        try{
            populateEDReferralData( data );
            IDocBuilder builder =  new EDReferralBuilder(data);
            if (StringUtil.goodString(outputFile))
                return builder.build(outputFile);
            else
                return builder.build();
        } catch (CCDException e) {
            log.error("Failed to build ED Referral", e);
            throw new DocumentException("Failed to build  ED Referral", e);
        }
    }

    //Populate default discharge summary data.
    private void populateDischargeSummaryData(DischargeSummaryData data) throws CCDException {
        if (data.getAdmissionDate() == null) {
            ObjectEntry dateObject = connDischargeSummary.getObject("AdmissionDate");
            if (dateObject != null) {
                Calendar admissionDate =dateObject.getDateValue("admissionDate");
                data.setAdmissionDate( admissionDate );
            }
        }

        if (data.getDischargeDate() == null) {
            ObjectEntry dateObject = connDischargeSummary.getObject("DischargeDate");
            if (dateObject != null) {
                Calendar dischargeDate =dateObject.getDateValue("dischargeDate");
                data.setDischargeDate( dischargeDate );
            }
        }

        if (data.getActiveProblems() == null || data.getActiveProblems().length == 0) {
            data.setActiveProblems( populateProblems(connDischargeSummary, "ActiveProblem") );
        }
        if (data.getResolvedProblems() == null || data.getResolvedProblems().length==0) {
            data.setResolvedProblems( populateProblems(connDischargeSummary, "ResolvedProblem") );
        }

        if (data.getAllergies() == null || data.getAllergies().length == 0) {
            data.setAllergies( populateAllergies(connDischargeSummary));
        }

        //Set Hospital Course
        if (!StringUtil.goodString(data.getHospitalCourse())) {
            ObjectEntry object = connDischargeSummary.getObject("HospitalCourse");
            if (object != null) {
                data.setHospitalCourse( object.getStringValue("hospitalCourse") );
            }
        }


    }

    //Populate default referral summary data.
    private void populateReferralSummaryData(ReferralSummaryData data, IConnectionDescription conn) throws CCDException {
        //Set Referral Reason
        if (!StringUtil.goodString(data.getReferralReason())) {
            ObjectEntry object = conn.getObject("ReferralReason");
            if (object != null) data.setReferralReason( object.getStringValue("referralReason") );
        }
        //Set History of Present Illness
        /*if (!StringUtil.goodString(data.getHistoryOfPresentIllness())) {
            ObjectEntry object = conn.getObject("HistoryOfPresentIllness");
            if (object != null) data.setHistoryOfPresentIllness( object.getStringValue("historyOfPresentIllness"));
        }*/
        //Set Active Problems
        if (data.getActiveProblems() == null || data.getActiveProblems().length==0) {
            data.setActiveProblems( populateProblems(conn, "ActiveProblem") );
        }
        //Set Current Medications
        if (data.getMedications()==null || data.getMedications().length == 0) {
            data.setMedications( populateMedications(conn, "Medication") );
        }
        //Set Allergies
        if (data.getAllergies()==null || data.getAllergies().length == 0) {
            data.setAllergies( populateAllergies(conn) );
        }
        //set Advance Directive
//        if (data.getAdvanceDirectives().size()==0) {
//            data.addAdvanceDirective( populateAdvanceDirectives(conn) );
//        }
    }

    private void populateEDReferralData(EDReferralData data) throws CCDException {
         populateReferralSummaryData(data, connEDReferer);

        //set Proposed Disposition
        if (data.getProposedDisposition() == null) {
            ObjectEntry disposition = connEDReferer.getObject("ProposedDisposition");
            if (disposition != null) {
                ObjectEntry.CodeSystem code = disposition.getCodeValue("dispositionCode");
                SharedEnums.DischargeDispositionCode dischargeDispositionCode = SharedEnums.DischargeDispositionCode.UNKNOWN;
                if (code != null) {
                    dischargeDispositionCode = SharedEnums.DischargeDispositionCode.fromCode(code.getCode());
                }
                String encounterDisposition = disposition.getStringValue("encounterDisposition");
                Calendar low = disposition.getDateValue("lowEffectiveTime");
                Calendar high = disposition.getDateValue("highEffectiveTime");

                ProposedDisposition pd = new ProposedDisposition();
                pd.setDischargeDispositionCode(dischargeDispositionCode);
                pd.setEffectiveTime(new EffectiveTime(low, high));
                pd.setEncounterDisposition(encounterDisposition);
                data.setProposedDisposition( pd );
            }
        }

        //set transportation mode
         if (data.getTransportMode() == null) {
            ObjectEntry mode = connEDReferer.getObject("TransportMode");
            if (mode != null) {
                ObjectEntry.CodeSystem code = mode.getCodeValue("code");
                SharedEnums.TransportModeCode transportModeCode = SharedEnums.TransportModeCode.UNKNOWN;
                if(code != null) {
                    transportModeCode = SharedEnums.TransportModeCode.fromCode( code.getCode() );
                }
                Calendar arrivalTime = mode.getDateValue("estimatedTimeOfArrival");
                ObjectEntry.Measure deviation = mode.getMeasureValue("standardDeviation");
                TransportMode transportMode = new TransportMode(transportModeCode, arrivalTime);
                data.setTransportMode( transportMode );
            }
        }
    }

    private Allergy[]  populateAllergies(IConnectionDescription conn) {
        ObjectList list = conn.getObjectList("Allergy");
        if (list == null || list.size()==0) return null;

        Allergy[] lAllergies = new Allergy[list.size()];
        for (int i=0; i<list.size(); i++) {
            ObjectEntry entry = list.getListEntry(i);
            String agent = entry.getStringValue("agent");
            String reaction = entry.getStringValue("reaction");
            ObjectEntry.CodeSystem codesystem = entry.getCodeValue("code");
            Code code = null;
            if (codesystem !=null) {
                code = new Code(codesystem.getCode(), null, codesystem.getCodeSystem(), codesystem.getVersion());
            }
            String source = entry.getStringValue("comment");
            Calendar reportDate = entry.getDateValue("reportDate");
            Provider provider = null;

            Allergy allergy = new Allergy(agent, reaction, code, source, reportDate, provider);
            lAllergies[i] = allergy;
        }
        return lAllergies;
    }

    private Medication[]  populateMedications(IConnectionDescription conn, String type) {
        assert type.equals("Medication") || type.equals("DischargeMedication") ;

        ObjectList list = conn.getObjectList(type);
        if (list == null || list.size() == 0) return null;

        Medication[] lMedications = new Medication[list.size()]; 
        for (int i=0; i<list.size(); i++) {
            ObjectEntry entry = list.getListEntry(i);
            String productName = entry.getStringValue("productName");
            Calendar date = entry.getDateValue("orderDate");
            Calendar lastAdministrationDate = null;
            ObjectEntry.CodeSystem codesystem = entry.getCodeValue("code");
            Code code = null;
            if (codesystem !=null) {
                code = new Code(codesystem.getCode(), null, codesystem.getCodeSystem(), codesystem.getVersion());
            }
            String sig = entry.getStringValue("sig");
            DoseQuantity dose = null;
            String frequence = null;
            Code routeCode = null;
            String duration = entry.getStringValue("duration");
            String refills = null;
            Code problemCode = null;
            String status = null;
            String source = null;
            Provider provider = null;
            
            Medication medication = new Medication(productName, date, lastAdministrationDate,
                    code, sig, routeCode, dose, frequence, duration, refills, problemCode, status, source);
            lMedications[i] = medication;
        }
        return lMedications;
    }

//    private List<AdvanceDirective>  populateAdvanceDirectives(IConnectionDescription conn) {
//        List<AdvanceDirective> lAdvanceDirectives = new ArrayList<AdvanceDirective>();
//        ObjectList list = conn.getObjectList("AdvanceDirective");
//        if (list == null) return lAdvanceDirectives;
//
//        for (int i=0; i<list.size(); i++) {
//            ObjectEntry entry = list.getListEntry(i);
//            String directive = entry.getStringValue("directive");
//            String description = entry.getStringValue("description");
//            String documentURL = entry.getStringValue("documentURL");
//            String mimeType = entry.getStringValue("mimeType");
//           // AdvanceDirective ad = new AdvanceDirective(directive, mimeType, documentURL, description);
//            lAdvanceDirectives.add( ad );
//        }
//        return lAdvanceDirectives;
//    }

    private SimpleProblem[] populateProblems(IConnectionDescription description, String type ) throws CCDException {
        assert type.equals("ActiveProblem") || type.equals("ResolvedProblem") ||
               type.equals("AdmissionDiagnosis") || type.equals("DischargeDiagnosis" );
        ObjectList list = description.getObjectList(type);
        if (list == null || list.size() == 0) return null;

        SimpleProblem[] lProblems = new SimpleProblem[list.size()];

        for (int i=0; i<list.size(); i++) {
            ObjectEntry entry = list.getListEntry(i);
            String name = entry.getStringValue("name");
            ObjectEntry.CodeSystem code = entry.getCodeValue("code");
            Calendar startDate = entry.getDateValue("startDate");
            Calendar endDate =  entry.getDateValue("endDate");
            String status = entry.getStringValue("status");
            ClinicalStatusCode statusCode = ClinicalStatusCode.mapValueOf(status);
            Provider provider = null;
            String source = entry.getStringValue("source");
            String comment = entry.getStringValue("comment");
            SimpleProblem problem = new SimpleProblem(name, new Code(code.getCode(), null, code.getCodeSystemName(), code.getVersion() ),
                                                       startDate, endDate, statusCode, source, comment);
            lProblems[i] = problem;
        }
        return lProblems;
    }

    public CCDDocument buildScannedDocument(ScannedDocumentData data) {
       return buildScannedDocument(data, null);
    }

    public CCDDocument buildScannedDocument(ScannedDocumentData data, String outputFile) {
        try {
            populateScannedDocumentData( data );
            IDocBuilder builder = new ScannedDocumentBuilder(data);
            if (StringUtil.goodString(outputFile))
                return builder.build(outputFile);
            else
                return builder.build();
        } catch (CCDException e) {
            log.error("Failed to build Scanned Document", e);
            throw new DocumentException("Failed to build Scanned Document", e);
        }
    }

    private void populateScannedDocumentData(ScannedDocumentData data){
        /*if (data.getDataEnterer() == null) {
            ObjectEntry dataEnterer = CDABuilder.connScannedDoc.getObject("DataEnterer");
            if (dataEnterer != null) {
                Calendar time = dataEnterer.getDateValue("time");
                String fisrtname = dataEnterer.getStringValue("firstName");
                String middleName = dataEnterer.getStringValue("middleName");
                String lastName = dataEnterer.getStringValue("lastName");
                String namePrefix = dataEnterer.getStringValue("namePrefix");
                String nameSuffix = dataEnterer.getStringValue("nameSuffix");
                DataEnterer enterer = new DataEnterer();
                enterer.setTime( time );
                enterer.setPersonName(new PersonName(fisrtname, lastName, middleName, namePrefix, nameSuffix));
                data.setDataEnterer(enterer);
            }
        }*/
    }


    public CCDDocument buildConsentDocument(ConsentDocumentData data) {
       return buildScannedDocument(data, null);
    }
    
    public CCDDocument buildConsentDocument(ConsentDocumentData data, String outputFile) {
        try {
            populateConsentDocumentData( data );
            IDocBuilder builder = new ConsentDocumentBuilder(data);
            if (StringUtil.goodString(outputFile))
                return builder.build(outputFile);
            else
                return builder.build();
        } catch (CCDException e) {
            log.error("Failed to build Consent Document", e);
            throw new DocumentException("Failed to build Consent Document", e);
        }
    }

    private void populateConsentDocumentData(ConsentDocumentData data){
        /*if (data.getDataEnterer() == null) {
            ObjectEntry dataEnterer = CDABuilder.connScannedDoc.getObject("DataEnterer");
            if (dataEnterer != null) {
                Calendar time = dataEnterer.getDateValue("time");
                String fisrtname = dataEnterer.getStringValue("firstName");
                String middleName = dataEnterer.getStringValue("middleName");
                String lastName = dataEnterer.getStringValue("lastName");
                String namePrefix = dataEnterer.getStringValue("namePrefix");
                String nameSuffix = dataEnterer.getStringValue("nameSuffix");
                DataEnterer enterer = new DataEnterer();
                enterer.setTime( time );
                enterer.setPersonName(new PersonName(fisrtname, lastName, middleName, namePrefix, nameSuffix));
                data.setDataEnterer(enterer);
            }
        }*/
    }


    public static void main(String[] args) {
        //Document doc = CDABuilder.getInstance().buildDischargeSummary(new DischargeSummaryData());
        CCDDocument doc = CDABuilder.getInstance().buildEDReferral(new EDReferralData());
    }
}
