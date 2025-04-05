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
package com.misyshealthcare.connect.ihe.hl7;

import java.text.SimpleDateFormat;
import java.util.Date;
import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v25.datatype.DTM;
import ca.uhn.hl7v2.model.v25.datatype.HD;
import ca.uhn.hl7v2.model.v25.datatype.ERL;
import ca.uhn.hl7v2.model.v25.datatype.ELD;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.util.Terser;

import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.base.PatientIdentifier;

/**
 * @author Jim Firby
 * @version 2.0 - Nov 22, 2005
 */
public class HL7v25 {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
    public static final String birhtdateFormat = "yyyyMMdd";
    /**
   * Populate an HL7 v2.5 MSH segment according to the IHE standard
   *
   * @param msh The MSH segment
   * @param type The type of message the segment belongs to
   * @param event The event that triggered this message
   * @param id The message control ID for this message
   * @throws DataTypeException When supplied data is inappropriate
   * @throws IheConfigurationException When the connection to which this will be sent if not configured properly
   */
    public static void populateMSH(MSH msh, String type, String event, String id, IConnectionDescription connection) throws DataTypeException, IheConfigurationException {
        // MSH-1
        msh.getFieldSeparator().setValue("|");
        // MSH-2
        msh.getEncodingCharacters().setValue("^~\\&");
        // MSH-3
        Identifier identifier = Configuration.getIdentifier(connection, "SendingApplication", true);
        HD hd = msh.getSendingApplication();
        hd.getNamespaceID().setValue(identifier.getNamespaceId());
        hd.getUniversalID().setValue(identifier.getUniversalId());
        hd.getUniversalIDType().setValue(identifier.getUniversalIdType());
        // MSH-4
        identifier = Configuration.getIdentifier(connection, "SendingFacility", true);
        hd = msh.getSendingFacility();
        hd.getNamespaceID().setValue(identifier.getNamespaceId());
        hd.getUniversalID().setValue(identifier.getUniversalId());
        hd.getUniversalIDType().setValue(identifier.getUniversalIdType());
        // MSH-5
        identifier = Configuration.getIdentifier(connection, "ReceivingApplication", true);
        hd = msh.getReceivingApplication();
        hd.getNamespaceID().setValue(identifier.getNamespaceId());
        hd.getUniversalID().setValue(identifier.getUniversalId());
        hd.getUniversalIDType().setValue(identifier.getUniversalIdType());
        // MSH-6
        identifier = Configuration.getIdentifier(connection, "ReceivingFacility", true);
        hd = msh.getReceivingFacility();
        hd.getNamespaceID().setValue(identifier.getNamespaceId());
        hd.getUniversalID().setValue(identifier.getUniversalId());
        hd.getUniversalIDType().setValue(identifier.getUniversalIdType());
        // MSH-7
        msh.getDateTimeOfMessage().getTime().setValue(formatter.format(new Date()));
        // MSH-9
        msh.getMessageType().getMessageCode().setValue(type);
        msh.getMessageType().getTriggerEvent().setValue(event);
        // MSH-10
        msh.getMessageControlID().setValue(id);
        // MSH-11
        msh.getProcessingID().getProcessingID().setValue("P");
        // MSH-12
        msh.getVersionID().getVersionID().setValue("2.5");
    }

    /**
   * Populate an HL7 v2.5 MSH segment according to the IHE standard
   *
   * @param msh The MSH segment
   * @param type The type of message the segment belongs to
   * @param event The event that triggered this message
   * @param id The message control ID for this message
   * @param sendingApplication The sending application
   * @param sendingFacility The sending facility
   * @param receivingApplication The receiving application
   * @param receivingFacility The receiving facility
   * @throws DataTypeException When supplied data is inappropriate
   */
    public static void populateMSH(MSH msh, String type, String event, String id, Identifier sendingApplication, Identifier sendingFacility,
                                   Identifier receivingApplication, Identifier receivingFacility) throws DataTypeException {
        // MSH-1
        msh.getFieldSeparator().setValue("|");
        // MSH-2
        msh.getEncodingCharacters().setValue("^~\\&");
        // MSH-3
        HD hd = msh.getSendingApplication();
        hd.getNamespaceID().setValue( sendingApplication.getNamespaceId() );
        hd.getUniversalID().setValue( sendingApplication.getUniversalId() );
        hd.getUniversalIDType().setValue( sendingApplication.getUniversalIdType() );
        // MSH-4
        hd = msh.getSendingFacility();
        hd.getNamespaceID().setValue( sendingFacility.getNamespaceId() );
        hd.getUniversalID().setValue( sendingFacility.getUniversalId() );
        hd.getUniversalIDType().setValue( sendingFacility.getUniversalIdType() );
        // MSH-5
        hd = msh.getReceivingApplication();
        hd.getNamespaceID().setValue( receivingApplication.getNamespaceId() );
        hd.getUniversalID().setValue( receivingApplication.getUniversalId() );
        hd.getUniversalIDType().setValue( receivingApplication.getUniversalIdType() );
        // MSH-6
        hd = msh.getReceivingFacility();
        hd.getNamespaceID().setValue( receivingFacility.getNamespaceId() );
        hd.getUniversalID().setValue( receivingFacility.getUniversalId() );
        hd.getUniversalIDType().setValue( receivingFacility.getUniversalIdType());
        // MSH-7
        msh.getDateTimeOfMessage().getTime().setValue(formatter.format(new Date()));
        // MSH-9
        msh.getMessageType().getMessageCode().setValue(type);
        msh.getMessageType().getTriggerEvent().setValue(event);
        // MSH-10
        msh.getMessageControlID().setValue(id);
        // MSH-11
        msh.getProcessingID().getProcessingID().setValue("P");
        // MSH-12
        msh.getVersionID().getVersionID().setValue("2.5");
    }

    /**
     * Update an HL7 v2.5 message header for a repeat use.
     *
     * @param msh The message header to update
     * @param id The new message control id for this header
     * @throws DataTypeException When the date/time cannot be set
     */
    public static void updateMSH(MSH msh, String id) throws DataTypeException {
        // MSH-7
        msh.getDateTimeOfMessage().getTime().setValue(formatter.format(new Date()));
        // MSH-10
        msh.getMessageControlID().setValue(id);
    }

    /**
     * Create a human-readable string our of an HL7 ERR segment.
     *
     * @param err The ERR segment
     * @param type use either "PIX" or "PDQ";
     * @return The error string
     */
    public static String getErrorString(ERR err, String type) {
        if (err != null) {
            String code = err.getHL7ErrorCode().getIdentifier().getValue();
            String text = err.getHL7ErrorCode().getText().getValue();
            String user = err.getUserMessage().getValue();
            String severity = err.getSeverity().getValue();
            if (text == null) {
                if (code == null) text = "Unspecified error";
                else if (code.equals("100")) text = "Segment sequence error";
                else if (code.equals("101")) text = "Required segment missing";
                else if (code.equals("102")) text = "Data type error";
                else if (code.equals("103")) text = "Table value not found";
                else if (code.equals("200")) text = "Unsupported message type";
                else if (code.equals("201")) text = "Unsupported event code";
                else if (code.equals("202")) text = "Unsupported processing id";
                else if (code.equals("203")) text = "Unsupported version id";
                else if (code.equals("204")) text = "Unknown key identifier";
                else if (code.equals("205")) text = "Duplicate key identifier";
                else if (code.equals("206")) text = "Application record locked";
                else if (code.equals("207")) text = "Application internal error";
                else	text = "Unspecified error";
            }
            String error = null;
            if ((severity == null) || (severity.equalsIgnoreCase("E"))) error = type + " Error ";
            else if (severity.equalsIgnoreCase("W")) error = type + " Warning ";
            else error = "";
            if (code != null) error = error + "(" + code + ") ";
            if (text != null) error = error + text;
            if (user != null) error = error + ": " + user;
            return error;
        } else {
            return "Unknown error";
        }
    }

    /**
     * Turn an HL7 v2.5 DTM structure into a Java Date object.
     *
     * @param dtm The DTM structure
     * @param doTime True if the time should be included in the result
     * @return The Date object holding the DTM date/time
     * @throws DataTypeException When there is a problem extracting the HL7 data from the DTM
     */
    public static Date unformatDTM(DTM dtm, boolean doTime) throws DataTypeException {
        if (dtm == null) return null;
        // Convert to a Date
        if (doTime) {
            return HL7v231.buildDateFromInts(
                    dtm.getYear(), dtm.getMonth(), dtm.getDay(),
                    dtm.getHour(), dtm.getMinute(), dtm.getSecond(),
                    dtm.getGMTOffset(), true);
        } else {
            return HL7v231.buildDateFromInts(
                    dtm.getYear(), dtm.getMonth(), dtm.getDay(),
                    0, 0, 0, 0, false);
        }
    }

    /**
     * Populate MSA segment, used by, for example, PIX Query response
     *
     * @param msa The Message Acknowledgment segment
     * @param acknowledgmentCode The two letter of acknowledgment code
     * @param messageControlId The message control Id
     * @throws DataTypeException When MSA segment values cannot be set
     */
    public static void populateMSA(MSA msa, String acknowledgmentCode, String messageControlId) throws DataTypeException {
         //MSA-1
         msa.getAcknowledgmentCode().setValue(acknowledgmentCode);
         //MSA-2
         msa.getMessageControlID().setValue( messageControlId );
    }

    /**
     * Populate QAK segment.
     *
     * @param qak  The query acknowledgment segment
     * @param queryTag The query tag
     * @param responseStatus The response status
     * @throws DataTypeException When QAK segment values cannot be set.
     */
    public static void populateQAK(QAK qak, String queryTag, String responseStatus) throws DataTypeException {
         //QAK-1
         qak.getQueryTag().setValue( queryTag );
         //QAK-2
         qak.getQueryResponseStatus().setValue( responseStatus);
    }

    /**
     * Populate DSC segment
     *
     * @param dsc The DSC segment to be populated
     * @param pointer The pointer id
     * @throws DataTypeException When DSC segment values cannot be set.
     */
    public static void populateDSC(DSC dsc, String pointer) throws DataTypeException {
        //DSC-1
        dsc.getContinuationPointer().setValue(pointer);
        //DSC-2
        dsc.getContinuationStyle().setValue("I");
    }

    /**
     * Populate ERR segment.
     *
     * @param err The ERR segment to be populated
     * @param segmentId The id of the segment that caused the error
     * @param sequence The sequence of the segment
     * @param fieldPosition The field position where the error is
     * @param fieldRepetition The repetition of the error field
     * @param componentNumber The component number in the error field
     * @param hl7ErrorCode The HL7 error code
     * @param hl7ErrorText The HL7 error text
     * @throws DataTypeException When ERR segment values cannot be set.
     * @throws HL7Exception When HL7 related issue happens
     */
    public static void populateERR(ERR err, String segmentId, String sequence, String fieldPosition, String fieldRepetition,
                                   String componentNumber, String hl7ErrorCode, String hl7ErrorText) throws DataTypeException, HL7Exception {
        ERL erl = err.getErrorLocation(0);
        erl.getSegmentID().setValue( segmentId );
        erl.getSegmentSequence().setValue( sequence );
        erl.getFieldPosition().setValue(fieldPosition);
        erl.getFieldRepetition().setValue(fieldRepetition);
        erl.getComponentNumber().setValue(componentNumber);
        err.getHL7ErrorCode().getIdentifier().setValue( hl7ErrorCode );
        err.getHL7ErrorCode().getText().setValue( hl7ErrorText );
    }

    /**
     * Extract ID with assigning authority from a GenericComposite.
     *
     * @param composite
     * @return the Patient Identifier
     * @throws DataTypeException
     */
    public static PatientIdentifier extractId(GenericComposite composite) throws DataTypeException {
        PatientIdentifier ret = new PatientIdentifier();
        //Get ID number
        Varies varies = (Varies)composite.getComponent(0);
        if (varies != null)
            ret.setIdNumber( ((GenericPrimitive)varies.getData()).getValue() );

        //Get assigning authority
        Identifier identifier = null;
        varies = (Varies)composite.getComponent(3);
        if (varies != null) {
            Type data = varies.getData();
            if (data instanceof GenericPrimitive) {
                //If the type is GenericPrimitive, only namespace is provided
                String namespaceId = ((GenericPrimitive)data).getValue();
                identifier = new Identifier(namespaceId, null, null);
            } else {
                varies =  (Varies)((GenericComposite)data).getComponent(0);
                String namespaceId = ((GenericPrimitive)varies.getData()).getValue();

                varies =  (Varies)((GenericComposite)data).getComponent(1);
                String universalId = ((GenericPrimitive)varies.getData()).getValue();

                varies =  (Varies)((GenericComposite)data).getComponent(2);
                String universalIdType = ((GenericPrimitive)varies.getData()).getValue();

                identifier = new Identifier(namespaceId, universalId, universalIdType);
            }
        }
        ret.setIdentifier( identifier );
        return ret;
    }

    /**
     * Extract assigning authority from a GenericComposite. The GenericComposite has to be either PDQ-8 (in PDQ Query)
     * or PID-4 (in PIX Query).
     *
     * @param composite
     * @return the Identifier
     * @throws DataTypeException if there is any data type error
     */
    public static Identifier extractAssigningAuthority(GenericComposite composite) throws DataTypeException {
            //Get assigning authority
            Identifier identifier = null;
            Varies varies = (Varies)composite.getComponent(3);
            if (varies == null) return null;

            Type data = varies.getData();
            if (data instanceof GenericPrimitive) {
                //If the type is GenericPrimitive, only namespace is provided
                String namespaceId = ((GenericPrimitive)data).getValue();
                identifier = new Identifier(namespaceId, null, null);
            } else {
                varies =  (Varies)((GenericComposite)data).getComponent(0);
                String namespaceId = ((GenericPrimitive)varies.getData()).getValue();

                varies =  (Varies)((GenericComposite)data).getComponent(1);
                String universalId = ((GenericPrimitive)varies.getData()).getValue();

                varies =  (Varies)((GenericComposite)data).getComponent(2);
                String universalIdType = ((GenericPrimitive)varies.getData()).getValue();

                identifier = new Identifier(namespaceId, universalId, universalIdType);
            }

            return identifier;
        }
    
}
