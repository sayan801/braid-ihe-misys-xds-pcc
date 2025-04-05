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


package com.misyshealthcare.connect.ihe;

import com.misyshealthcare.connect.base.IPatientXrefManager;
import com.misyshealthcare.connect.base.IPixPatientDataSource;
import com.misyshealthcare.connect.base.PatientIdentifier;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.hl7.HL7Server;
import com.misyshealthcare.connect.ihe.hl7.HL7v25;
import com.misyshealthcare.connect.ihe.hl7.HL7Util;
//import com.misyshealthcare.connect.hiber.util.HiberUtil;
//import com.misyshealthcare.connect.hiber.mpi.ConnectPatient;
import org.apache.log4j.Logger;
//import org.hibernate.Session;
//import org.hibernate.Query;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.app.*;
import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.message.RSP_K23;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.model.v25.datatype.HD;
import ca.uhn.hl7v2.model.v25.datatype.MSG;
import ca.uhn.hl7v2.model.v25.datatype.ID;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.util.Terser;

import java.util.*;

/**
 * This class is the base implementation of IPatientXrefManager.
 *
 * @author Wenzhi Li
 * @version 2.0, Mar 1, 2007
 */
public class PixManager extends HL7Actor implements IPatientXrefManager {
    /* Logger for problems during SOAP exchanges */
    private static Logger log = Logger.getLogger(PixManager.class);

    /* The connection description to this PIX maneger */
    private IConnectionDescription connection = null;

    private HL7Server server = null;

    private IPixPatientDataSource dataSourcePix = null;

   /**
    * Create a new PixManager that will talk to a Pix client over
    * the connection description supplied.
    *
    * @param connection The description of the connection of this PIX manager
    * @throws IheConfigurationException
    */
    public PixManager(IConnectionDescription connection, IheAuditTrail auditTrail) throws IheConfigurationException {
        super(connection, log, auditTrail);
        this.connection = connection;

   }

        /** Must be called once for each actor when the program starts. */
    public void start() {
        //call the super one to initiate standard start process
        super.start();
        //now begin the local start, initiate pix manager server
        LowerLayerProtocol llp = LowerLayerProtocol.makeLLP(); // The transport protocol
        server = new HL7Server(connection, llp, new PipeParser() );
        Application handler = new PixManagerHandler(connection, dataSourcePix);
        server.registerApplication("QBP", "Q23", handler);
        //now start the Pix Manager server
        server.start();
    }

    /** Must be called once for each actor just before the program quits. */
    public void stop() {
        //now end the local stop, stop the pix manager server
        server.stop();

        //call the super one to initiate standard stop process
        super.stop();

    }

    /**
     * Register the patient data manager which handles the patient saving and retriving information.
     *
     * @param pixPatientDataSource
     */
    public void registerPixManagerDataSource(IPixPatientDataSource pixPatientDataSource) {
       dataSourcePix = pixPatientDataSource;
    }

    static class PixManagerHandler implements Application {

        private IConnectionDescription connection = null;
        private static int messageId = 0;
        private IPixPatientDataSource dataSourcePix = null;

        PixManagerHandler(IConnectionDescription connection, IPixPatientDataSource dataSourcePix) {
            this.connection = connection;
            this.dataSourcePix = dataSourcePix;
        }
        public boolean canProcess(Message theIn) {
             if (theIn instanceof QBP_Q21)
                return true;
             else
                return false;
        }

        public Message processMessage(Message msgIn) throws ApplicationException, HL7Exception {

              //String encodedMessage = new PipeParser().encode(msgIn);
              //log.info("Received message:\n" + encodedMessage + "\n\n");

              QBP_Q21 message = null;
              if(msgIn instanceof QBP_Q21) {
                  message = (QBP_Q21)msgIn;
              } else {
                throw new ApplicationException( "Unexpected request to PIX Manager server");
              }
             MSH msh = message.getMSH();
             HD sendingApplication = msh.getSendingApplication();
             HD sendingFacility  = msh.getSendingFacility();
             HD receivingApplication = msh.getReceivingApplication();
             HD receivingFacility = msh.getReceivingFacility();
             String messageControlId = msh.getMessageControlID().getValue();

             MSG msgType = msh.getMessageType();
             ID event = msgType.getTriggerEvent();
             if (event.getValue().equals("Q23") ) {
                 //Q23 is for PIX Query: OK, we will continue process the message
             } else {
                 throw new ApplicationException("Unexpeced HL7 event. The supported event is Q23.");
             }

             //Process QPD
             QPD qpd = message.getQPD();
             String queryTag = qpd.getQueryTag().getValue();
             //Get the Patient ID
             GenericComposite composite = (GenericComposite)qpd.getUserParametersInsuccessivefields().getData();

             PatientIdentifier requestPatientId = HL7v25.extractId(composite );

             //Send Response
             RSP_K23 reply = new RSP_K23();
             String replyMessageControlId = "PIXM_" + messageId++;
             //For the response message, the ReceivingApplication and ReceivingFacility will become the sendingApplication and sendingFacility,
             //Also the sendingApplication and sendingFacility will become the receivingApplication and receivingFacility.
            Identifier misysApplication      = null;
            Identifier misysFacility = null;
            try {
                misysApplication = Configuration.getIdentifier(connection, "ReceivingApplication", true);
            } catch (IheConfigurationException e) {
                throw new ApplicationException("Missing receivingApplication for connection " + connection.getDescription(), e) ;
            }
            try {
                misysFacility = Configuration.getIdentifier(connection, "ReceivingFacility", true);
            } catch (IheConfigurationException e) {
                throw new ApplicationException("Missing receivingFacility for connection " + connection.getDescription(), e) ;
            }

             Identifier idSendingApplication   = new Identifier(sendingApplication.getNamespaceID().getValue(), sendingApplication.getUniversalID().getValue(), sendingApplication.getUniversalIDType().getValue() );
             Identifier idSendingFacility      = new Identifier(sendingFacility.getNamespaceID().getValue(), sendingFacility.getUniversalID().getValue(), sendingFacility.getUniversalIDType().getValue() );
             Identifier idReceivingApplication = new Identifier(receivingApplication.getNamespaceID().getValue(), receivingApplication.getUniversalID().getValue(), receivingApplication.getUniversalIDType().getValue() );
             Identifier idReceivingFacility    = new Identifier(receivingFacility.getNamespaceID().getValue(), receivingFacility.getUniversalID().getValue(), receivingFacility.getUniversalIDType().getValue() );
             HL7v25.populateMSH(reply.getMSH(), "RSP", "K23", replyMessageControlId, misysApplication, misysFacility, idSendingApplication, idSendingFacility );

            Terser inTerser = new Terser( message );
            Terser outTerser = new Terser( reply );
             //We first need to validate ReceivingApplication and ReceivingFacility.
             //Currently we are not validating SendingApplication and SendingFacility
             if (!idReceivingApplication.equals(misysApplication) ) {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=MSH, sequence=1, fieldPosition=5, fieldRepetition=1, componentNubmer=1
                 HL7v25.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1", null, "Unknown Receiving Application");
                 return reply;
             }
             if (!idReceivingFacility.equals(misysFacility) ) {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=MSH, sequence=1, fieldPosition=6, fieldRepetition=1, componentNubmer=1
                 HL7v25.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1", null, "Unknown Receiving Facility");
                 return reply;
             }

             //Process ID in the following 5 cenarios:See ITI Technical Framework Section 3.9.4.2.2.6
             //1. validate request ID domain  (Case #4 in PIX specs request ID Domain is not valid)
             //2. validate request ID itself  (Case #3 in PIX specs request ID is not valid)
             //3. validate return ID domain (Case #5 in PIX specs return ID domain is not valid)
             //4. Found ID            (Case #1 and Case #6 in PIX specs: ID found excluding request ID)
             //5. No ID found         (Case #2 in PIX specs: ID is not found)

             //1. validate ID domain  (Case #4 in PIX specs request ID Domain is not valid)
             Identifier requestDomain = (Identifier)requestPatientId.getIdentifier();
             boolean requestDomainOk = validateRequestDomain( requestDomain, connection);
             if (!requestDomainOk) {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=1, componentNubmer=4
                 HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", "1", "4", "204", "Unknown Key Identifier");
                 return reply;
             }

             //2. validate ID itself  (Case #3 in PIX specs request ID is not valid)
             boolean validPatient = dataSourcePix.isValidPatient( requestPatientId );
             if ( !validPatient ) {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=1, componentNubmer=1
                 HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", "1", "1", "204", "Unknown Key Identifier");
                 return reply;
             }

             //3. TODO: validate return ID domain (Case #5 in PIX specs return ID domain is n

             //4. Found ID (Case #1 and Case #6 in PIX specs: ID found excluding request ID)
             //5. No ID found         (Case #2 in PIX specs: ID is not found)
             //AA (application accept) is returned in MSA-1.
             //OK (data found, no errors) is returned in QAK-2.

             List<PatientIdentifier> ids = dataSourcePix.crossFindAllPatients( requestPatientId );
             Map<Identifier, List<String>> map = new HashMap<Identifier, List<String>>();
             for (PatientIdentifier pid : ids) {
                  Identifier authority = reconcileIdentifier( pid.getIdentifier(), connection );
                  //In case of mulitple IDs for a certain authority, we need to group them together.
                  List<String> idList = map.get( authority );
                  if (idList == null) {
                      idList = new ArrayList<String>();
                      map.put( authority, idList);
                  }
                  idList.add( pid.getIdNumber() );
             }

             if (ids.size() >= 1) {
                 HL7v25.populateMSA(reply.getMSA(), "AA", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "OK");
                 HL7Util.echoQPD(outTerser, inTerser);

                 PID pid = reply.getQUERY_RESPONSE().getPID();
                 Set<Identifier> authorities = map.keySet();
                 int index = 0;
                 for (Identifier authority : authorities) {
                     List<String> idNumbers = map.get( authority );
                     for (String idNumber : idNumbers) {
                         CX patientIdentifier = pid.getPatientIdentifierList(index++);
                         patientIdentifier.getIDNumber().setValue( idNumber );
                         patientIdentifier.getAssigningAuthority().getNamespaceID().setValue( authority.getNamespaceId() );
                         patientIdentifier.getAssigningAuthority().getUniversalID().setValue( authority.getUniversalId() );
                         patientIdentifier.getAssigningAuthority().getUniversalIDType().setValue( authority.getUniversalIdType() );
                         patientIdentifier.getIdentifierTypeCode().setValue("ISO");
                         //For the second reptition, set the Name Type code to be "S"
                         pid.getPatientName(0).getGivenName().setValue("");
                         pid.getPatientName(1).getNameTypeCode().setValue("S");
                     }
                 }
                 return reply;
             } else {
                 HL7v25.populateMSA(reply.getMSA(), "AA", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "NF");
                 HL7Util.echoQPD(outTerser, inTerser);
                 return reply;
             }
        }

        /**
         * Validate whether an ID domain is valid against the connection configuration.
         *
         * @param id The request ID domain to be validated
         * @param description
         * @return <code>true</code> if the idDomain is valid.
         */
        public static boolean validateRequestDomain(Identifier id, IConnectionDescription description) {
             if (id == null) return  false;

             List<Identifier> identifiers = description.getAllIdentifiersByType("domain");
             for (Identifier identifier : identifiers) {
                 if (identifier.equals(id))
                    return true;
             }
             return false;
        }

//
//        /**
//         * Get the connect PID from SystemID and Unique PatientID
//         * @param id
//         * @param identifier
//         * @return the String of Connect Pid
//         */
//        private String getConnectPID(String id, Identifier identifier) {
//            Session session = HiberUtil.getSession();
//            Query query = session.createQuery("select connect_pid from ConnectPatient where unique_pid = :p and systemId = :s");
//            query.setParameter("p", id);
//            query.setParameter("s", HL7.toCXAuth(identifier));
//            List patients = query.list();
//            HiberUtil.closeSession();
//
//            if (patients.size() > 0 )
//                return (String)patients.get(0) ;
//            else
//                return null;
//        }
//
//        /**
//         * Finds all other patient (SystemId and Unique Patient Id) which has the same connect PatientId.
//         *
//         * @param connectPid The connect patient id to find
//         * @return A list of Pair of unique patinet id and system id
//         */
//        private List<Pair> crossFindIds(String connectPid) {
//            Session session = HiberUtil.getSession();
//            Query query = session.createQuery("select unique_pid, systemId from ConnectPatient where connect_pid = :cpid");
//            query.setParameter("cpid", connectPid);
//            Iterator results = query.list().iterator();
//            HiberUtil.closeSession();
//
//            List<Pair> ret = new ArrayList<Pair>();
//            while (results.hasNext()) {
//                Object[] row = (Object[])results.next();
//                Pair pair = new Pair();
//                pair._first = (String)row[0];
//                pair._second = (String)row[1];
//                ret.add( pair );
//            }
//            return ret;
//        }

        /**
         * Reconcile authority with the ConnectionDescritpion configuration. An authority
         * can have Namespace and/or UniversalId/UniversalIdType. For example, in the data source such as
         * database, if an authority is represented by Namespace only, while in the xml configuration, the authority is configured
         * with both Namespace and UnviersalId/UniversalIdType. The authority in the datasource has to be mapped
         * to the authority configured in the xml files.
         *
         * @param authority The authority
         * @param connection
         * @return The authority according the configuration
         */
        private Identifier reconcileIdentifier(Identifier authority, IConnectionDescription connection) {
            List<Identifier> identifiers = connection.getAllIdentifiersByType("domain");
            for (Identifier identifier : identifiers) {
                if ( identifier.equals(authority) ) {
                    return identifier;
                }
            }
            //no identifier is found, just return the orinigal authority
            return authority;
        }



    }




    public static void main(String[] args) throws Exception {

//        Session session = HiberUtil.getSession();
//        Query query = session.createQuery("from ConnectPatient where unique_pid = :p and systemId = :s");
//        query.setParameter("p", "1111");
//        query.setParameter("s", "CPR");
//        List<ConnectPatient> patients = query.list();
//
//        List<PatientIdentifier> ret = new ArrayList<PatientIdentifier>();
//        for (ConnectPatient cp : patients) {
//            query = session.createQuery("from ConnectPatient where connect_pid = :cpid and (unique_pid <> :p or systemId <> :s)");
//            query.setParameter("cpid", cp.getConnectPatientId());
//            query.setParameter("p", "1111");
//            query.setParameter("s", "CPR");
//            Iterator results = query.list().iterator();
//            while (results.hasNext()) {
//                PatientIdentifier pid = new PatientIdentifier();
//                ConnectPatient row = (ConnectPatient)results.next();
//                pid.setIdNumber( row.getUniqueSystemPatientId() );
//                pid.setIdentifier( HL7.getAssigningAuthorityFromCXAuth( row.getSystemId() ) );
//                ret.add( pid );
//
//                System.out.println("id=" + row.getUniqueSystemPatientId() + ", aa=" + row.getSystemId()  );
//            }
//        }
//        HiberUtil.closeSession();

//        Session session = HiberUtil.getSession();
//
//        Query query = session.createQuery("from ConnectPatient a where a.connect_pid in select b.connect_pid from ConnectPatient b where b.unique_pid = :p and b.systemId = :s");
//        query.setParameter("p", "1111");
//        query.setParameter("s", "CPR");
//        List patients = query.list();
//        HiberUtil.closeSession();
//
//        if (patients.size() > 0 )
//            System.out.println("found");
//        else
//            System.out.println("Not found");


//        QBP_Q21 message = new QBP_Q21();
//        MSH msh  = message.getMSH();
//        // MSH-1
//        msh.getFieldSeparator().setValue("|");
//        // MSH-2
//        msh.getEncodingCharacters().setValue("^~\\&");
//        // MSH-3
//        HD hd = msh.getSendingApplication();
//        hd.getNamespaceID().setValue( "sendaName" );
//        hd.getUniversalID().setValue( "sendaU" );
//        hd.getUniversalIDType().setValue( "sendaUIDType" );
//        // MSH-4
//        hd = msh.getSendingFacility();
//        hd.getNamespaceID().setValue( "sendfName" );
//        hd.getUniversalID().setValue( "sendfU" );
//        hd.getUniversalIDType().setValue( "sendfUType" );
//        // MSH-5
//        hd = msh.getReceivingApplication();
//        hd.getNamespaceID().setValue( "reaName" );
//        hd.getUniversalID().setValue( "reaU" );
//        hd.getUniversalIDType().setValue( "reaUType");
//        // MSH-6
//        hd = msh.getReceivingFacility();
//        hd.getNamespaceID().setValue( "refName" );
//        hd.getUniversalID().setValue( "refU" );
//        hd.getUniversalIDType().setValue( "refUType" );
//        // MSH-7
//        msh.getDateTimeOfMessage().getTime().setValue("12345");
//        // MSH-9
//        msh.getMessageType().getMessageCode().setValue("QBP");
//        msh.getMessageType().getTriggerEvent().setValue("Q23");
//        // MSH-10
//        msh.getMessageControlID().setValue("111");
//        // MSH-11
//        msh.getProcessingID().getProcessingID().setValue("P");
//        // MSH-12
//        msh.getVersionID().getVersionID().setValue("2.5");
//
//        QPD qpd = message.getQPD();
//        // QPD-1
//        qpd.getMessageQueryName().getIdentifier().setValue("identifier");
//        qpd.getMessageQueryName().getText().setValue("text");
//        qpd.getMessageQueryName().getNameOfCodingSystem().setValue("codingSystem");
//        // QPD-2
//        qpd.getQueryTag().setValue("QRY_Id");
//        // QPD-3
//        CX cx = new CX(message);
//        cx.getIDNumber().setValue("Pid");
//        cx.getAssigningAuthority().getNamespaceID().setValue("NamespaceId");
//        cx.getAssigningAuthority().getUniversalID().setValue("UniversalId");
//        cx.getAssigningAuthority().getUniversalIDType().setValue("UniversalIdType");
//        cx.getIdentifierTypeCode().setValue("PI");
//        qpd.getUserParametersInsuccessivefields().setData(cx);
//        CX cx2 = new CX(message);
//        cx2.getAssigningAuthority().getNamespaceID().setValue("NamespaceId2");
//        cx2.getAssigningAuthority().getUniversalID().setValue("UniversalId2");
//        cx2.getAssigningAuthority().getUniversalIDType().setValue("UniversalIdType2");
//        qpd.getUserParametersInsuccessivefields().getData().getExtraComponents().getComponent(0).setData(cx2);
//        PipeParser parser = new PipeParser();
//        String str = parser.encode( message );
//        System.out.println(str);

        String msg = "MSH|^~\\&|EHR_MISYS|MISYS|PAT_IDENTITY_X_REF_MGR_IBM1|IBM|20070209210421+0530||QBP^Q23|PIX_2|P|2.5\r" +
                "QPD|QRY_1001^Query for Corresponding Identifiers^IHEDEMO|QRY_PIX_2|348400^^^&1.100.7&ISO^PI\r" +
                "RCP|I";

        Parser p = new GenericParser();
        Message  message = p.parse(msg);
        // The connection hub connects to listening servers
        ConnectionHub connectionHub = ConnectionHub.getInstance();

        // A connection object represents a socket attached to an HL7 server
        Connection connection = connectionHub
                     .attach("localhost", 3600, new PipeParser(), MinLowerLayerProtocol.class);
                 // The initiator is used to transmit unsolicited messages
             Initiator initiator = connection.getInitiator();
             Message response = initiator.sendAndReceive(message);

             PipeParser pipeParser = new PipeParser();
             String responseString = pipeParser.encode(response);
             System.out.println("Received response:\n" + responseString);

              connection.close();

//        try {
//            Message message = pipeParser.parse(msg);
//            if (message instanceof QBP_Q21) {
//                System.out.println("Type= QBP_Q21");
//            } else {
//                System.out.println("Not QBP_Q21");
//            }
//            PixManager.PixManagerHandler handler = new PixManager.PixManagerHandler(null, ConnectPVRegistry.getInstance());
//
//            handler.processMessage( message );
//
//        } catch (HL7Exception e) {
//            e.printStackTrace();
//        }  catch (ApplicationException e) {
//            e.printStackTrace();
//        }

    }
}
