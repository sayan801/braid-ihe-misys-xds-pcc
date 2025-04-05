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

import com.misyshealthcare.connect.base.*;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientMRN;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.ihe.hl7.HL7Server;
import com.misyshealthcare.connect.ihe.hl7.HL7v25;
import com.misyshealthcare.connect.ihe.hl7.HL7Util;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.ihe.pdq.ContinuationPointer;
import com.misyshealthcare.connect.util.StringUtil;
import com.misyshealthcare.connect.util.DateUtil;
import org.apache.log4j.Logger;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.message.RSP_K21;
import ca.uhn.hl7v2.model.v25.message.QCN_J01;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.model.v25.datatype.*;
import ca.uhn.hl7v2.model.v25.group.RSP_K21_QUERY_RESPONSE;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.util.Terser;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * @author Wenzhi Li
 * @version 2.1, Mar 27, 2007
 */
public class PatientDemographicsSupplier extends HL7Actor implements IPatientDemographicsSupplier {
    /* Logger for problems during SOAP exchanges */
    private static Logger log = Logger.getLogger(PatientDemographicsSupplier.class);

    /* The connection description to this PD supplier */
    private IConnectionDescription connection = null;

    private HL7Server server = null;

    private IPdqPatientDataSource dataSourcePdq = null;

   /**
    * Create a new PdSupplier that will talk to a PDQ consumer over
    * the connection description supplied.
    *
    * @param connection The description of the connection of this PD Supplier
    * @throws com.misyshealthcare.connect.ihe.configuration.IheConfigurationException
    */
    public PatientDemographicsSupplier(IConnectionDescription connection, IheAuditTrail auditTrail) throws IheConfigurationException {
        super(connection, log, auditTrail);
        this.connection = connection;

   }

    /** Must be called once for each actor when the program starts. */
    public void start() {
        //call the super one to initiate standard start process
        super.start();
        //now begin the local start, initiate pdq supplier
        LowerLayerProtocol llp = LowerLayerProtocol.makeLLP(); // The transport protocol
        server = new HL7Server(connection, llp, new PipeParser() );
        Application handler = new PdSupplierHandler(connection, dataSourcePdq);
        server.registerApplication("QBP", "Q22", handler); //PDQ Query message
        server.registerApplication("QCN", "J01", handler); //Query Cancel message
        //now start the Pdq Supplier server
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
     * Register the PDQ patient data source which handles the patient information.
     *
     * @param pdqDataSource
     */
    public void registerPdqPatientDataSource(IPdqPatientDataSource pdqDataSource) {
        dataSourcePdq = pdqDataSource;
    }

   static class PdSupplierHandler implements Application {

        private IConnectionDescription connection = null;
        private static long messageId = System.currentTimeMillis();
        private IPdqPatientDataSource dataSourcePdq = null;
        //Used to store continuation pointer
        private static Hashtable<String, ContinuationPointer> dscMap = new Hashtable<String, ContinuationPointer>();

        PdSupplierHandler(IConnectionDescription connection, IPdqPatientDataSource dataSourcePdq) {
            this.connection = connection;
            this.dataSourcePdq = dataSourcePdq;
        }
        public boolean canProcess(Message theIn) {
             if (theIn instanceof QBP_Q21 || theIn instanceof QCN_J01)
                return true;
             else
                return false;
        }

        public Message processMessage(Message msgIn) throws ApplicationException, HL7Exception {

             //String encodedMessage = new PipeParser().encode(msgIn);
             //log.info("Received message:\n" + encodedMessage + "\n\n");

            if (msgIn instanceof QCN_J01) {
                 return processQCN_JO1((QCN_J01)msgIn);
             } else if(!(msgIn instanceof QBP_Q21)) {
                 throw new ApplicationException( "Unexpected request to PDQ Supplier server");
             }
             //Now, it must be QBP_Q21 message
             QBP_Q21 message = (QBP_Q21)msgIn;
             MSH msh = message.getMSH();

             RSP_K21 reply = new RSP_K21();
             processMSH(msh, reply.getMSH(), "RSP", "K22",  getReceivingApplication(), getReceivingFacility());

             //Process QPD
             QPD qpd = message.getQPD();
             String queryTag = qpd.getQueryTag().getValue();

             String messageControlId = msh.getMessageControlID().getValue();

             Terser inTerser = new Terser( message );
             Terser outTerser = new Terser( reply );

             MSG msgType = msh.getMessageType();
             ID event = msgType.getTriggerEvent();
             if (event.getValue().equals("Q22") ) {
                 //Q22 is for PDQ Query: OK, we will continue process the message
             } else {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=MSH, sequence=1, fieldPosition=9, fieldRepetition=1, componentNubmer=2
                 HL7v25.populateERR(reply.getERR(), "MSH", "1", "9", "1", "2", "201", "Unsupported event code");
                 return reply;
             }

             //Process RCP segment
             int recordRequestNumber = getRecordRequestNumber(message);

             //Process DSC
             String pointer = getContinuationPointer(message);

             //We first need to validate ReceivingApplication and ReceivingFacility.
             //Currently we are not validating SendingApplication and SendingFacility
            HD receivingApplication = msh.getReceivingApplication();
            HD receivingFacility = msh.getReceivingFacility();
            Identifier idReceivingApplication = new Identifier(receivingApplication.getNamespaceID().getValue(), receivingApplication.getUniversalID().getValue(), receivingApplication.getUniversalIDType().getValue() );
            Identifier idReceivingFacility    = new Identifier(receivingFacility.getNamespaceID().getValue(), receivingFacility.getUniversalID().getValue(), receivingFacility.getUniversalIDType().getValue() );
             if (!idReceivingApplication.equals(getReceivingApplication()) ) {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=MSH, sequence=1, fieldPosition=5, fieldRepetition=1, componentNubmer=1
                 HL7v25.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1", "204", "Unknown Key Identifier");
                 return reply;
             }
             if (!idReceivingFacility.equals(getReceivingFacility()) ) {
                 HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                 HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                 HL7Util.echoQPD(outTerser, inTerser);
                 //segmentId=MSH, sequence=1, fieldPosition=6, fieldRepetition=1, componentNubmer=1
                 HL7v25.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1", "204", "Unknown Key Identifier");
                 return reply;
             }

             //Process ID in the following cenarios:See ITI Technical Framework Section 3.21.4.2.2.8
             //0. if use DSC (Continuation pointer), we retrieve it from the memory
             //1. validate the return domains. If there is a domain not recognized, return an error message.
             //        (Case #3 The Patient Demographics Supplier Actor does not recognize one or more of the
             //         domains in QPD-8-What Domains Returned.)
             //2. If the return domain (QPD-8) is not specified, return all found records. If it exceeds the max num needed,
             //  then just return that number, and construct a DSC segment (Case #1).
             //3. If one or more domains are specified, and there is at least one patient record found, each found
             //   patient (the PID segment) will have the PID-3 field containing each id repetition for each domain, even
             //   if the id in that domain is null (Case #2).

             List<Identifier> returnDomains = null;
             List<List<PatientDescriptor>>  finalPatients = null;
             String newPointer = null;
             //0. use DSC
             if (StringUtil.goodString(pointer)) {
                 //Get the patients from Cache Pointer
                 if ( !dscMap.containsKey(pointer) ) {
                     HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                     HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                     HL7Util.echoQPD(outTerser, inTerser);
                     //segmentId=DSC, sequence=1, fieldPosition=1, fieldRepetition=1, componentNubmer has to be empty
                     HL7v25.populateERR(reply.getERR(), "DSC", "1", "1", "1", null, null, "Unknown Continuation Pointer");
                     return reply;
                 }
                 ContinuationPointer cp = dscMap.get( pointer );
                 returnDomains = cp.getReturnDomain();
                 List<List<PatientDescriptor>> allPatients = cp.getPatients();

                 if (recordRequestNumber < 0 || recordRequestNumber >= allPatients.size() ) {
                    finalPatients = allPatients;
                    //remove continuation pointer if no more patients available
                    dscMap.remove( pointer );
                 }
                 else {
                    finalPatients = allPatients.subList(0, recordRequestNumber);
                    List<List<PatientDescriptor>> remainingPatients = allPatients.subList(recordRequestNumber, allPatients.size());
                    cp.setPatients(remainingPatients);
                    cp.setLastRequestTime(System.currentTimeMillis());
                    newPointer = pointer;
                 }
             }
             else {
                //Get the patients from data source
                //1. validate the return domains
                returnDomains = getReturnDomain(qpd, reply, messageControlId, queryTag, outTerser, inTerser);
                //If the returnDomains is null, the reply will contain error message
                if (returnDomains == null)
                    return reply;
                //2. and 3.
                List<List<PatientDescriptor>> allPatients = getFinalPatients(qpd, reply, messageControlId, queryTag, outTerser, inTerser, returnDomains);
                if (recordRequestNumber == -1 || recordRequestNumber >= allPatients.size()) {
                    finalPatients = allPatients;
                } else {
                    finalPatients = allPatients.subList(0, recordRequestNumber);
                    //add continuation pointer
                    List<List<PatientDescriptor>> remainingPatients = allPatients.subList(recordRequestNumber, allPatients.size());
                    String pointerControlId = queryTag  + "_" + messageId;
                    ContinuationPointer cp = new ContinuationPointer();
                    cp.setPointer(pointerControlId);
                    cp.setReturnDomain( returnDomains );
                    cp.setPatients( remainingPatients );
                    cp.setLastRequestTime(System.currentTimeMillis());
                    dscMap.put(pointerControlId, cp);
                    newPointer = pointerControlId;
                }
             }

             HL7v25.populateMSA(reply.getMSA(), "AA", messageControlId);
             HL7v25.populateQAK(reply.getQAK(), queryTag, "OK");  //Note: IHE spec does not use "NF" for not found record.
             HL7Util.echoQPD(outTerser, inTerser);
             if (finalPatients.size() >= 1) {
                boolean useFirstPID = true;
                for (List<PatientDescriptor> patientRecord : finalPatients) {
                    //We grab the first patient descriptor as the patient demograhpics.
                    PatientDescriptor patient = patientRecord.get(0);
                    RSP_K21_QUERY_RESPONSE qr = reply.getQUERY_RESPONSE();
                    //RSP_K21 only contains one PID, so we need to add our own PID segment if more than one are needed
                    PID pid = null;
                    if (useFirstPID) {
                        pid = qr.getPID();   
                        useFirstPID = false;
                    }
                    else {
                        //Add a new PID segment
                        String name = qr.add(PID.class, false, true);
                        pid = (PID)qr.get(name);
                    }
                    List<Identifier> processedDomains = new ArrayList<Identifier>();
                    try {
                        populatePID(pid, patient, processedDomains);
                        //For subsequent patient record, we only retrieve its patient id
                        int idIndex = 1;
                        for (int i=1; i<patientRecord.size(); i++) { //has to start with the second one, the first was handled above
                            PatientDescriptor pd = (PatientDescriptor)patientRecord.get(i);
                            String system = pd.getHomeSystem();
                            if (!StringUtil.goodString(system)) continue;  //ignore it if system is null
                            String patientId = pd.getUniquePatientId();
                            Identifier authority = HL7.getAssigningAuthorityFromCXAuth( system );
                            authority = reconcileIdentifier( authority, connection );
                            processedDomains.add( authority );
                            // PID-3 - Preferred ID
                            populateCX(pid.getPatientIdentifierList(idIndex++), patientId, authority);
                        }

                        //finally, we need to add the entry for those return domains that have no patient id.
                        for (Identifier authority : returnDomains) {
                            if ( processedDomains.contains( authority ) )
                                continue;
                            //for non-processed domain, we need just add empty patient id
                            populateCX(pid.getPatientIdentifierList(idIndex++), null, authority);
                        }
                    } catch (IheConfigurationException e) {
                        throw new ApplicationException("Fail to populate PID segment", e);
                    } catch (PatientException e) {
                        throw new ApplicationException("Fail to populate PID segment", e);
                    }
                }//end for
             }//end if found patient
             //Populate DSC segment if appropriate
             if (newPointer != null)
                HL7v25.populateDSC(reply.getDSC(), newPointer);

            return reply;
        }

       /**
        * Process QCN_J01 cancel query message
        *
        * @param qcnMsg
        * @return
        * @throws DataTypeException
        * @throws ApplicationException
        */
       private Message processQCN_JO1(QCN_J01 qcnMsg) throws DataTypeException, ApplicationException {
             MSH requestMsh = qcnMsg.getMSH();
             ACK reply = new ACK();
             MSH replyMsh = reply.getMSH();
             processMSH(requestMsh, replyMsh, "ACK", null, getReceivingApplication(), getReceivingFacility());
             String messageControlId = requestMsh.getMessageControlID().getValue();
             HL7v25.populateMSA(reply.getMSA(), "AA", messageControlId);

             //Remove the query result for this query tag
             QID qid = qcnMsg.getQID();
             String queryTag = qid.getQueryTag().getValue();

             long timeout = 600000; //defaults to 600000 millieseconds (10 minutes)
             try {
                 timeout = Long.parseLong( Configuration.getPropertySetValue(connection, "QueryProperties", "ContinuationPointerTimeout", false) );
             } catch (IheConfigurationException e) {
                 throw new ApplicationException(e);
             }

             Set<String> keys = dscMap.keySet();
             for (String key : keys) {
                 if (key.startsWith(queryTag) ){
                     dscMap.remove(key);
                 }
                 //Also, clean up timed out entries. Set time out to be 10 minutes.
                 ContinuationPointer cp = dscMap.get(key);

                 if (cp != null) {
                     long lastTime = cp.getLastRequestTime();
                     if ((System.currentTimeMillis() - lastTime) > 600000 )
                        dscMap.remove(key);
                 }
             }

             return reply;
       }

       private Identifier getReceivingApplication() throws ApplicationException {
            Identifier receivingApplication  = null;
            try {
                receivingApplication = Configuration.getIdentifier(connection, "ReceivingApplication", true);
            } catch (IheConfigurationException e) {
                throw new ApplicationException("Missing receivingApplication for connection " + connection.getDescription(), e) ;
            }
            return receivingApplication;
       }

       private Identifier getReceivingFacility() throws ApplicationException {
            Identifier receivingFacility = null;
            try {
                receivingFacility = Configuration.getIdentifier(connection, "ReceivingFacility", true);
            } catch (IheConfigurationException e) {
                throw new ApplicationException("Missing receivingFacility for connection " + connection.getDescription(), e) ;
            }
            return receivingFacility;
       }

       /**
        * Process the MSH segment.
        *
        * @param requestMsh The request MSH segment
        * @param replyMsh The reply MSH segment
        * @param messageType The message type
        * @param event  The event id
        * @param receivingApplication It is expected to be misysApplication
        * @param receivingFacility  It is expected to be misysFacility
        * @throws ApplicationException
        * @throws DataTypeException
        */
       private void processMSH(MSH requestMsh, MSH replyMsh, String messageType, String event, Identifier receivingApplication, Identifier receivingFacility) throws ApplicationException, DataTypeException {
           HD sendingApplication = requestMsh.getSendingApplication();
           HD sendingFacility  = requestMsh.getSendingFacility();

            Identifier idSendingApplication   = new Identifier(sendingApplication.getNamespaceID().getValue(), sendingApplication.getUniversalID().getValue(), sendingApplication.getUniversalIDType().getValue() );
            Identifier idSendingFacility      = new Identifier(sendingFacility.getNamespaceID().getValue(), sendingFacility.getUniversalID().getValue(), sendingFacility.getUniversalIDType().getValue() );

            String replyMessageControlId = "PDQSupplier_" + messageId++;

            //For the response message, the ReceivingApplication and ReceivingFacility will become the sendingApplication and sendingFacility,
            //Also the sendingApplication and sendingFacility will become the receivingApplication and receivingFacility.
            HL7v25.populateMSH(replyMsh, messageType, event, replyMessageControlId, receivingApplication, receivingFacility, idSendingApplication, idSendingFacility );

       }

       /**
        * Get the record request number from RCP segment.
        *
        * @param message The QBP_Q21 request message
        * @return the record number for the request. <code>-1</code> if there is no record number limit.
        */
       private int getRecordRequestNumber(QBP_Q21 message) {
             RCP rcp = message.getRCP();
             String number = rcp.getQuantityLimitedRequest().getQuantity().getValue();
             int recordRequestNumber = -1;  //-1 indicates no limit
             if (StringUtil.goodString(number)) {
                 try {
                     recordRequestNumber = Integer.parseInt(number);
                 } catch (NumberFormatException e) {
                    //just ignore RCP
                 }
             }
             return recordRequestNumber;
       }

       /**
        * Get the continuation pointer from DSC segment.
        *
        * @param message The QBP_Q21 request message
        * @return The continuation pointer
        */
       private String getContinuationPointer(QBP_Q21 message) {
             DSC dsc = message.getDSC();
             String pointer = dsc.getContinuationPointer().getValue();
             return pointer;
       }

       /**
        * Get the value of a given component
        *
        * @param data
        * @param componentNum
        * @return
        * @throws DataTypeException
        */
        private Varies getComponentValue(GenericComposite data, int componentNum) throws DataTypeException {
                return (Varies)data.getComponent(componentNum);
            }

       /**
        * Get the return domain from the original PDQ request.
        *
        * @param qpd the request QPD segment
        * @param reply the response message
        * @param messageControlId the messageControlId for the response message
        * @param queryTag the queryTag of the request message
        * @param outTerser the out reponse message terser
        * @param inTerser the in request message terser
        * @return return a list of returnDomain if not error. Otherwise, return null if there is an invalid domain or request syntax cannot be recognized.
        * @throws HL7Exception
        */
       private List<Identifier> getReturnDomain(QPD qpd, RSP_K21 reply, String messageControlId, String queryTag, Terser outTerser, Terser inTerser ) throws HL7Exception {
           //1. validate the return domains.
          //Get the return domains
          Type[] typeDomains = qpd.getField(8);
          List<Identifier> returnDomains = new ArrayList<Identifier>();
          for (int i=0; i<typeDomains.length; i++) {
              Varies domain = (Varies)typeDomains[i];
              Type data = domain.getData();
              if (data instanceof GenericComposite) {
                  Identifier aa = HL7v25.extractAssigningAuthority( (GenericComposite)data );
                  boolean validDomain = validateRequestDomain( aa, connection);
                  if (validDomain) {
                      Identifier reconciledDomain = reconcileIdentifier(aa, connection);
                      returnDomains.add( reconciledDomain );
                  }
                  else {
                      //if a domain is invalid, return an error message
                      HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                      HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                      HL7Util.echoQPD(outTerser, inTerser);
                      //segmentId=QPD, sequence=1, fieldPosition=8, fieldRepetition=i+1, componentNubmer has to be empty
                      HL7v25.populateERR(reply.getERR(), "QPD", "1", "8", Integer.toString(i+1), null, "204", "Unknown Key Identifier");
                      return null;
                  }
              } else {
                  //return "Data type error"
                  HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                  HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                  HL7Util.echoQPD(outTerser, inTerser);
                  //segmentId=QPD, sequence=1, fieldPosition=8, fieldRepetition=null, componentNubmer=null
                  HL7v25.populateERR(reply.getERR(), "QPD", "1", "8", null, null, "102", "Data type error");
                  return null;
              }
           }
           return returnDomains;
       }

       /**
        * Get a list of final patients that need to be returned to the PDQ consumer.
        *
        * @param qpd the request QPD segment
        * @param reply the response message
        * @param messageControlId the messageControlId for the response message
        * @param queryTag the queryTag of the request message
        * @param outTerser the out reponse message terser
        * @param inTerser the in request message terser
        * @param returnDomains the list of returnDomain the PDQ consumer is insterested
        * @return return a list of patients if not error. Otherwise, return null if the request syntax cannot be recognized.
        * @throws HL7Exception
        * @throws ApplicationException
        */
       private List<List<PatientDescriptor>> getFinalPatients(QPD qpd, RSP_K21 reply, String messageControlId, String queryTag,
                                                              Terser outTerser, Terser inTerser, List<Identifier> returnDomains) throws ApplicationException, HL7Exception {
           //hanlde case 2 and 3 together
           //2 If the return domain (QPD-8) is not specified, return all found records. If it exceeds the max num needed,
           //  then just return that number, and construct a DSC segment (Case #1).
           //3. If one or more domains are specified, and there is at least one patient record found, each found
           //   patient (the PID segment) will have the PID-3 field containing each id repetition for each domain, even
           //   if the id in that domain is null (Case #2).

           //First, process query parameters
           Type[] queries = qpd.getField(3);  //The third one is the query parameters
           HashMap<String, String> parameters = new HashMap<String, String>();
           for (int i=0; i < queries.length; i++) {
               Varies parameter = (Varies)queries[i];
               Type data = parameter.getData();
               if (data instanceof GenericComposite) {
                   //The first component is parameter name
                   Varies field = getComponentValue((GenericComposite)data, 0);
                   String sField = ((GenericPrimitive)field.getData()).getValue();
                   //The sencod component is parameter value
                   Varies value = getComponentValue((GenericComposite)data, 1);
                   String sValue = ((GenericPrimitive)value.getData()).getValue();
                   parameters.put( sField, sValue );
               } else {
                  //return "Data type error"
                  HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
                  HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
                  HL7Util.echoQPD(outTerser, inTerser);
                  //segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=null, componentNubmer=null
                  HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", null, null, "102", "Data type error");
                  return null;
               }
           }
           PatientQuery query = processQuery(parameters);

           List<List<PatientDescriptor>> allPatients = dataSourcePdq.findPatientsFromQuery( query );

           List<List<PatientDescriptor>> finalPatients = new ArrayList<List<PatientDescriptor>>();
           if (returnDomains.size() == 0) {
               //If no return domain is specified, we consider all patients
              finalPatients = allPatients;
           }
           else {
               //Find a list of final patients that have ids in the return domain.
               for (List<PatientDescriptor> lpatients : allPatients) {
                   List<PatientDescriptor> filteredPatientDescriptors = new ArrayList<PatientDescriptor>();
                   for (PatientDescriptor patient : lpatients) {
                      String system = patient.getHomeSystem();
                      Identifier authority = HL7.getAssigningAuthorityFromCXAuth( system );
                      if (!StringUtil.goodString(system))
                          continue;
                      authority = reconcileIdentifier( authority, connection );
                      if (returnDomains.contains( authority )) {
                          filteredPatientDescriptors.add( patient );
                      }
                   }
                   //We don't want an empty list of patientDescriptors
                   if (filteredPatientDescriptors.size() > 0)
                      finalPatients.add( filteredPatientDescriptors );
               }
           }
           return finalPatients;
       }

       /**
        * Populate PatientQuery object with the query request parameters.
        *
        * @param parameters The query parameter
        * @return The PatientQuery object
        */
        private PatientQuery  processQuery(HashMap<String, String> parameters) throws ApplicationException {
            PatientQuery ret = new PatientQuery();
           try {
               ret.setPrefixWildcard( Configuration.getPropertySetValue(connection, "QueryProperties", "WildcardBefore", false) );
               ret.setSuffixWildcard( Configuration.getPropertySetValue(connection, "QueryProperties", "WildcardAfter", false) );
           } catch (IheConfigurationException e) {
               throw new ApplicationException(e);
           }
           ret.setUseDefaultQueryDesignProperties(false);
            Address address = null;
            PhoneNumber phone = null;
            PatientMRN  mrn = null;
            Set<String> keys = parameters.keySet();
            for (String key : keys) {
                String value = parameters.get( key );
                // PID-3 - Patient ID
                if (key.equalsIgnoreCase("@PID.3.1"))
                    ret.setSystemPatientId( value );
                else if (key.equalsIgnoreCase("@PID.3.4"))
                    ret.setHomeSystem( value );
                // PID-5 - Patient Name
                else if (key.equalsIgnoreCase("@PID.5.1"))
                    ret.setNameLast( value );
                else if (key.equalsIgnoreCase("@PID.5.2"))
                    ret.setNameFirst( value );
                else if (key.equalsIgnoreCase("@PID.5.3"))
                    ret.setNameMiddle( value );
                else if (key.equalsIgnoreCase("@PID.5.4"))
                    ret.setNameSuffix( value );
                else if (key.equalsIgnoreCase("@PID.5.5"))
                    ret.setNameTitle( value );
                // PID-6 - Maiden Name
                else if (key.equalsIgnoreCase("@PID.6") )
                    ret.setMotherMaidenName( value );
                else if (key.equalsIgnoreCase("@PID.6.1"))
                    ret.setMotherMaidenName( value );
                // PID-7 - Birth date
                else if (key.equalsIgnoreCase("@PID.7") )  {
                    try {
                        String birthdateFormat = HL7v25.birhtdateFormat;
                        String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
                        if (StringUtil.goodString(formatFromProperty)) {
                            birthdateFormat = formatFromProperty;
                        }
                        Calendar birthdate = DateUtil.parseCalendar(value, birthdateFormat);
                        ret.setBirthDateTime(new DateQuery(birthdate));
                    } catch (IheConfigurationException e) {
                        throw new ApplicationException(e);
                    } catch (ParseException e) {
                        throw new ApplicationException(e);
                    }
                }
                // PID-8 - Gender
                else if (key.equalsIgnoreCase("@PID.8"))
                   ret.setAdministrativeSex( SharedEnums.SexType.getByString( value ) );
                // PID-11 - Address
                else if (key.equalsIgnoreCase("@PID.11.1")) {
                    if (address == null ) address = new Address();
                    address.setAddLine1( value );
                }
                else if (key.equalsIgnoreCase("@PID.11.2")) {
                    if (address == null ) address = new Address();
                    address.setAddLine2( value );
                }
                else if (key.equalsIgnoreCase("@PID.11.3")) {
                    if (address == null ) address = new Address();
                    address.setAddCity( value );
                }
                else if (key.equalsIgnoreCase("@PID.11.4")) {
                    if (address == null ) address = new Address();
                    address.setAddState( value );
                }
                else if (key.equalsIgnoreCase("@PID.11.5")) {
                    if (address == null ) address = new Address();
                    address.setAddZip( value );
                }
                else if (key.equalsIgnoreCase("@PID.11.6")) {
                    if (address == null ) address = new Address();
                    address.setAddCountry( value );
                }
                // PID-14 - Phone Number
//    Commented out, there is no specific format for this field
//                else if (key.equalsIgnoreCase("@PID.14.1")) {
//                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.WORK);
//                    phone.setNumber( value );
//                }
                else if (key.equalsIgnoreCase("@PID.14.5")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.WORK);
                    phone.setCountryCode( value );
                }
                else if (key.equalsIgnoreCase("@PID.14.6")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.WORK);
                    phone.setAreaCode( value );
                }
                else if (key.equalsIgnoreCase("@PID.14.7")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.WORK);
                    phone.setNumber( value );
                }
               else if (key.equalsIgnoreCase("@PID.14.8")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.WORK);
                    phone.setExtension( value );
                }
                else if (key.equalsIgnoreCase("@PID.14.9")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.WORK);
                    phone.setNote( value );
                }
                else if (key.equalsIgnoreCase("@PID.13.5")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.HOME);
                    phone.setCountryCode( value );
                }
                else if (key.equalsIgnoreCase("@PID.13.6")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.HOME);
                    phone.setAreaCode( value );
                }
                else if (key.equalsIgnoreCase("@PID.13.7")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.HOME);
                    phone.setNumber( value );
                }
                else if (key.equalsIgnoreCase("@PID.13.8")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.HOME);
                    phone.setExtension( value );
                }
                else if (key.equalsIgnoreCase("@PID.13.9")) {
                    if (phone == null) phone = new PhoneNumber(SharedEnums.PhoneType.HOME);
                    phone.setNote( value );
                }
                // PID-18 - Patient Account Number
                else if (key.equalsIgnoreCase("@PID.18.1")) {
                    if (mrn == null) mrn = new PatientMRN(null, value);
                    else mrn.setMrn(value);
                }
                else if (key.equalsIgnoreCase("@PID.18.4")) {
                    if (mrn == null) mrn = new PatientMRN(value, null);
                    else mrn.setAssigningAuth(value);
                }
                //PID-19 SSN
                else if (key.equalsIgnoreCase("@PID.19")) {
                    ret.setSsn( value );
                }
                //PID-20 Driver's License
                else if (key.equalsIgnoreCase("@PID.20") || key.equalsIgnoreCase("@PID.20.1")) {
                    ret.setDriverLicense( value );
                }
            } //end for

            if (address != null) ret.setAddress( address );
            if (phone != null) ret.setPhone( phone );
            if (mrn != null) ret.setMrn( mrn );
            return ret;
        }

       /**
        * Populate the PID segment of the Patient Demographics Query Response.
        * <p>
        * See IHE ITI-TF vol 2, section 3.21.4.2.2.5 and HL7 v2.5 chapter 3
        *
        * @param pid The PID segment to be populated
        * @param patient The patient demographic data
        * @param processedDomainsmains A list of domains whose patient record has been processed
        * @throws IheConfigurationException When this connection is not properly configured to encode messages
        * @throws PatientException When required patient information is missing
        * @throws HL7Exception When the patient information cannot be encoded properly into HL7
        */
        private void populatePID(PID pid, PatientDescriptor patient, List<Identifier> processedDomainsmains) throws HL7Exception, IheConfigurationException, PatientException {
            // Get the patient identifier
            String system = patient.getHomeSystem();
            Identifier authority = HL7.getAssigningAuthorityFromCXAuth( system );
            if (!StringUtil.goodString(system))
                throwPatientException("Patient has no valid system name.");
            String patientId = patient.getUniquePatientId();
            authority = reconcileIdentifier( authority, connection );
            processedDomainsmains.add( authority );

            // PID-2 - Deprecated ID, our authority makes this ID > 20 characters, the allowed limit
            //populateCX(pid.getPatientID(), patientId, authority);
            // PID-3 - Preferred ID
            populateCX(pid.getPatientIdentifierList(0), patientId, authority);
            // PID-5 - Patient legal name
            XPN xpn = pid.getPatientName(0);
            populateXPN(xpn, patient, patientId, system);
            // PID-6 - Mother's maiden name, we have only last name
            if (StringUtil.goodString(patient.getMotherMaidenName())) {
                xpn = pid.getMotherSMaidenName(0);
                xpn.getFamilyName().getSurname().setValue(patient.getMotherMaidenName());
            }
            // PID-7 - Birth date
            if (patient.getBirthDateTime() != null) {
                String birthdateFormat = HL7v25.birhtdateFormat;
                String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
                if (StringUtil.goodString(formatFromProperty)) {
                    birthdateFormat = formatFromProperty;
                }
                pid.getDateTimeOfBirth().getTime().setValue(new SimpleDateFormat(birthdateFormat).format(patient.getBirthDateTime()) );
            }
            // PID-8 - Gender
            String gender = "U";
            if (patient.getAdministrativeSex() == SharedEnums.SexType.MALE) gender = "M";
            else if (patient.getAdministrativeSex() == SharedEnums.SexType.FEMALE) gender = "F";
            else if (patient.getAdministrativeSex() == SharedEnums.SexType.OTHER) gender = "O";
            pid.getAdministrativeSex().setValue(gender);
            // PID-11 - Addresses
            int i = 0;
            Address addr = patient.getAddress(SharedEnums.AddressType.HOME);
            if (addr != null) populateXAD(pid.getPatientAddress(i++), addr);
            addr = patient.getAddress(SharedEnums.AddressType.WORK);
            if (addr != null) populateXAD(pid.getPatientAddress(i++), addr);
            addr = patient.getAddress(SharedEnums.AddressType.OTHER);
            if (addr != null) populateXAD(pid.getPatientAddress(i++), addr);
            // PID-13 - Home phone
            i = 0;
            PhoneNumber phone = patient.getPhoneNumber(SharedEnums.PhoneType.HOME);
            if (phone != null) populateXTN(pid.getPhoneNumberHome(i++), phone);
            phone = patient.getPhoneNumber(SharedEnums.PhoneType.CELL);
            if (phone != null) populateXTN(pid.getPhoneNumberHome(i++), phone);
            phone = patient.getPhoneNumber(SharedEnums.PhoneType.EMERGENCY);
            if (phone != null) populateXTN(pid.getPhoneNumberHome(i++), phone);
            // PID-14 - Work phone
            i = 0;
            phone = patient.getPhoneNumber(SharedEnums.PhoneType.WORK);
            if (phone != null) populateXTN(pid.getPhoneNumberBusiness(i++), phone);
            phone = patient.getPhoneNumber(SharedEnums.PhoneType.SERVICE);
            if (phone != null) populateXTN(pid.getPhoneNumberBusiness(i++), phone);
            phone = patient.getPhoneNumber(SharedEnums.PhoneType.FAX);
            if (phone != null) populateXTN(pid.getPhoneNumberBusiness(i++), phone);
            //PID-18 Patient account number
            // not known from PatientDescriptor
            List<PatientMRN> mrns = patient.getMrnList();
            if (mrns.size() > 0) {
                //PID segment only supports one PAN.
                populateCX(pid.getPatientAccountNumber(), mrns.get(0).getMrn(), HL7.getAssigningAuthorityFromCXAuth(mrns.get(0).getAssigningAuth()) );
            }
            // PID-19 - SSN
            if (patient.getSsn() != null)
                pid.getSSNNumberPatient().setValue(clip(patient.getSsn(), 16));
            // PID-20 - Driver's License
            if (patient.getDriverLicense() != null)
                pid.getDriverSLicenseNumberPatient().getLicenseNumber().setValue(clip(patient.getDriverLicense(), 25));
            // Done
        }

        /**
         * Populate a CX component with an ID and assigning authority.
         *
         * @param cx The CX component to populate
         * @param id The id to put in
         * @param authority The assigning authority for that id
         * @throws DataTypeException When the component cannot be encoded in HL7
         * @throws IheConfigurationException When this connection is not properly configured to translate Patient Feed messages
         */
        private void populateCX(CX cx, String id, Identifier authority) throws DataTypeException, IheConfigurationException {
            // PID 3.1 -- The id
            cx.getIDNumber().setValue(id);
            // PID 3.4 -- The assigning authority
            boolean okay = false;
            HD hd = cx.getAssigningAuthority();
            if (authority.getNamespaceId() != null) {
                hd.getNamespaceID().setValue(authority.getNamespaceId());
                okay = true;
            }
            if (authority.getUniversalId() != null) {
                hd.getUniversalID().setValue(authority.getUniversalId());
                if (authority.getUniversalIdType() != null) {
                    hd.getUniversalIDType().setValue(authority.getUniversalIdType());
                    okay = true;
                }
            }
            // If the assigning authority does not enough pieces, throw an exception
            if (!okay)
                throw new IheConfigurationException("Invalid Assigning Authority identifer encountered by connection \"" + connection.getDescription() + "\"");
            // PID 3.5 -- The id type code, see HL7 table 0203 - "PI" stands for Patient Internal Identifier
            cx.getIdentifierTypeCode().setValue("PI");
        }

        /**
         * Populate an XPN component with a patient name.
         *
         * @param xpn The XPN component to populate
         * @param patient The patient
         * @param pid The patient ID for the patient, for debugging only
         * @param system the system (assigning authority) of the patient, for debugging only
         * @throws PatientException If this patient has no name
         * @throws DataTypeException When the name cannot be encoded into valid HL7
         */
        private void populateXPN(XPN xpn, PatientDescriptor patient, String pid, String system) throws DataTypeException, PatientException {
            if ((patient.getNameFirst() == null) && (patient.getNameMiddle() == null) && (patient.getNameLast() == null))
                throwPatientException("Patient '" + pid + "' in System '" + system + "' has no name.");
            if (patient.getNameFirst() != null) xpn.getGivenName().setValue(patient.getNameFirst());
            if (patient.getNameMiddle() != null) xpn.getSecondAndFurtherGivenNamesOrInitialsThereof().setValue(patient.getNameMiddle());
            if (patient.getNameLast() != null) xpn.getFamilyName().getSurname().setValue(patient.getNameLast());
            if (patient.getNameTitle() != null) xpn.getPrefixEgDR().setValue(patient.getNameTitle());
            if (patient.getNameSuffix() != null) xpn.getSuffixEgJRorIII().setValue(patient.getNameSuffix());
            //xpn.getNameTypeCode().setValue("L");
        }

        /**
         * Populate an XAD component with an address.
         *
         * @param xad The XAD component to populate
         * @param address The address to get the data from
         * @throws DataTypeException When the component cannot be encoded in HL7
         */
        private void populateXAD(XAD xad, Address address) throws DataTypeException {
            // Fill in all the address parts
            if (address.getAddLine1() != null) {
                xad.getStreetAddress().getStreetOrMailingAddress().setValue(address.getAddLine1());
            }
            if (address.getAddLine2() != null) {
                xad.getOtherDesignation().setValue(address.getAddLine2());
            }
            if (address.getAddCity() != null) {
                xad.getCity().setValue(address.getAddCity());
            }
            if (address.getAddState() != null) {
                xad.getStateOrProvince().setValue(address.getAddState());
            }
            if (address.getAddZip() != null) {
                xad.getZipOrPostalCode().setValue(address.getAddZip());
            }
            if (address.getAddCountry() != null) {
                xad.getCountry().setValue(address.getAddCountry());
            }
            // The address type is optional
            if (address.getAddType() == SharedEnums.AddressType.HOME) xad.getAddressType().setValue("H");
            if (address.getAddType() == SharedEnums.AddressType.WORK) xad.getAddressType().setValue("O");
        }

       /**
        * Populate an XTN component with a phone number.
        * <p>
        * This method uses a combined interpretation of the v2.3.1 and the v2.5 spec to
        * require only digits in phone numbers and impose limits on the field lengths.
        *
        * @param xtn The XTN component to populate
        * @param phone The phone number to put into the component
        * @throws DataTypeException When the component cannot be encoded in HL7
        */
       private void populateXTN(XTN xtn, PhoneNumber phone) throws DataTypeException{
           // First the separate pieces
           String country = null;
           if (phone.getCountryCode() != null) {
               country = clipNumber(phone.getCountryCode(), 3);
               xtn.getCountryCode().setValue(country);
           }
           String area = null;
           if (phone.getAreaCode() != null) {
               area = clipNumber(phone.getAreaCode(), 5);
               xtn.getAreaCityCode().setValue(area);
           }
           String number = null;
           if (phone.getNumber() != null) {
               number = clipNumber(phone.getNumber(), 9);
               xtn.getLocalNumber().setValue(number);
           }
           String extension = null;
           if (phone.getExtension() != null) {
               extension = clipNumber(phone.getExtension(), 5);
               xtn.getExtension().setValue(extension);
           }
           if (phone.getNote() != null) xtn.getAnyText().setValue(phone.getNote());
           // Next the telecommunications types (adapted fom our ENUM types)
           if (phone.getType() == SharedEnums.PhoneType.HOME) {
               xtn.getTelecommunicationUseCode().setValue("PRN");
               xtn.getTelecommunicationEquipmentType().setValue("PH");
           } else if (phone.getType() == SharedEnums.PhoneType.WORK) {
               xtn.getTelecommunicationUseCode().setValue("WPN");
               xtn.getTelecommunicationEquipmentType().setValue("PH");
           } else if (phone.getType() == SharedEnums.PhoneType.CELL) {
               xtn.getTelecommunicationUseCode().setValue("PRN");
               xtn.getTelecommunicationEquipmentType().setValue("CP");
           } else if (phone.getType() == SharedEnums.PhoneType.EMERGENCY) {
               xtn.getTelecommunicationUseCode().setValue("EMR");
               xtn.getTelecommunicationEquipmentType().setValue("PH");
           } else if (phone.getType() == SharedEnums.PhoneType.SERVICE) {
               xtn.getTelecommunicationUseCode().setValue("ASN");
               xtn.getTelecommunicationEquipmentType().setValue("PH");
           } else if (phone.getType() == SharedEnums.PhoneType.FAX) {
               xtn.getTelecommunicationUseCode().setValue("WPN");
               xtn.getTelecommunicationEquipmentType().setValue("FX");
           }
       }

        /**
         * Extract only digits from a string and then clip it to a specific length.
         *
         * @param value The string to make numeric and clip
         * @param size The maximum length allowed
         * @return The clipped string
         */
        private String clipNumber(String value, int size) {
            try {
                Integer.parseInt(value);
                return clip(value, size);
            } catch (NumberFormatException e) {
                log.warn("Extracting digits from non-numeric value '" + value + "'");
                StringBuffer sb = new StringBuffer();
                for (int i=0; i<value.length(); i++) {
                    char c = value.charAt(i);
                    if (Character.isDigit(c)) sb.append(c);
                }
                return sb.toString();
            }
        }

       /**
        * Clip a string to a specific length.
        *
        * @param value The string to clip
        * @param size The maximum length allowed
        * @return The clipped string
        */
       private String clip(String value, int size) {
           if (value == null) return null;
           if (value.length() <= size) return value;
           log.warn("Clipping the value '" + value + "' to length " + size);
           return value.substring(0, size);
       }


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
       public static Identifier reconcileIdentifier(Identifier authority, IConnectionDescription connection) {
           List<Identifier> identifiers = connection.getAllIdentifiersByType("domain");
           for (Identifier identifier : identifiers) {
               if ( identifier.equals(authority) ) {
                   return identifier;
               }
           }
           //no identifier is found, just return the original authority
           return authority;
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

    /**
     * Throw a new patient exception and log it as well.
     *
     * @param message
     * @throws PatientException
     */
    private void throwPatientException(String message) throws PatientException {
        log.error(message);
        throw new PatientException(message);
    }
    }

    public static void main(String[] args) throws Exception {

//        String msg = "MSH|^~\\&|EHR_MISYS|MISYS|PAT_IDENTITY_X_REF_MGR_IBM1|IBM|20060817212747-0400||QBP^Q22|PDQ_0|P|2.5\r" +
//                "QPD|QRY_PDQ_1001^Query By Name^IHEDEMO|QRY_PDQ_0|@PID.5.1^DEPINTO~@PID.5.2^JOE\r" +
//                "RCP|I";

//        String msg = "MSH|^~\\&|CLINREG|WESTCLIN|HOSPMPI|HOSP|199912121135-0600||QBP^Q22^QBP_Q21|1|D|2.5\r" +
//                "QPD|Q22^Find Candidates^HL7nnn|111069|@PID.5.1^SMITH~@PID.5.2^JOHN~@PID.8^M|80|MATCHWARE|1.2||^^^METRO HOSPITAL~^^^SOUTH LAB|\r" +
//                "RCP|I|20^RD";

        String msg = "MSH|^~\\&|OTHER_KIOSK|HIMSSSANDIEGO|EHR_MISYS|MISYS|20060821150004-0500||RSP^K22|115619040409488ibmod|P|2.5||1940933422:1156190185984|\r" +
                "MSA|AA|PDQ_6|\r" +
                "QAK|QRY_PDQ_0|OK|\r" +
                "QPD|QRY_PDQ_1001^Query By Name^IHEDEMO|QRY_PDQ_0|@PID.5.2^JO*\r" +
                "PID|||JD12294^^^HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO||Doe^John||||||900 Main St^^Green Bay^WI^23221||^PRN^PH|\r" +
                "QRI|174.0\r" +
                "PID|||12345678^^^HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO||DePinto^Joe^V^Jr|Wang|19580325|M|||||^PRN^PH^^^716^3856235|\r" +
                "QRI|174.0\r" +
                "PID|||12345679^^^HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO||DePinto^Joe^V^Jr|Wang|19580325|M|||||^PRN^PH^^^716^3856235|\r" +
                "QRI|174.0\r" +
                "DSC|2058980185:1156190210438|I|";
        PipeParser pipeParser = new PipeParser();
        try {
            Message message = pipeParser.parse(msg);
            if (message instanceof QBP_Q21) {
                System.out.println("Type= QBP_Q21");
            } else {
                System.out.println("Not QBP_Q21");
            }
            Terser in = new Terser(message);
            RSP_K21 outMessage = new RSP_K21();
            Terser out = new Terser(outMessage);
            MSH msh = outMessage.getMSH();
        // MSH-1
        msh.getFieldSeparator().setValue("|");
        // MSH-2
        msh.getEncodingCharacters().setValue("^~\\&");
        // MSH-3
        HD hd = msh.getSendingApplication();
        hd.getNamespaceID().setValue( "Sendapp" );

        // MSH-11
        msh.getProcessingID().getProcessingID().setValue("P");
        // MSH-12
        msh.getVersionID().getVersionID().setValue("2.5");

            RSP_K21_QUERY_RESPONSE qr = outMessage.getQUERY_RESPONSE();
//            PID pid = qr.getPID();
//            XPN pn = pid.getPatientName(0);
//            pn.getFamilyName().getSurname().setValue("Pin");
//            pn.getGivenName().setValue("John");

            String pidName = qr.add(PID.class, false, true);
            PID pid2 = (PID)qr.get( pidName );
            pid2.getPatientName(0).getGivenName().setValue("FirstName2");

            Structure s = qr.get("PID2");

//            PID pid = (PID)out.getSegment("/.PID");
//            out.set(pid, 1, 0, 1, 1, "happy");
//            PID pid2 = (PID)out.getSegment("/.PID(1)");
//            out.set(pid2, 1, 0, 1, 1, "happy2");



             HL7Util.echoQPD(out, in);

           // String ret= pipeParser.encode( (PID)out.getSegment("PID"), new EncodingCharacters('|', "^~\\&" ) );
             String ret= pipeParser.encode(  outMessage );
            System.out.println(" ret" + ret);

//            PdSupplier.PdSupplierHandler handler = new PdSupplierHandler(null, null);
//            handler.processMessage( message );
//
//            handler.processMessage( message );

        } catch (HL7Exception e) {
            e.printStackTrace();
        }
//        catch (ApplicationException e) {
//            e.printStackTrace();
//        }


    }
}
