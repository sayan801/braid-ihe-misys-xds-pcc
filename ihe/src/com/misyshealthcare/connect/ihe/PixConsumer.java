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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.message.RSP_K23;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.QPD;
import ca.uhn.hl7v2.model.v25.segment.RCP;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;

import com.misyshealthcare.connect.base.IPatientXref;
import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.hl7.HL7v25;
import com.misyshealthcare.connect.ihe.pix.PixCommunicationException;
import com.misyshealthcare.connect.ihe.pix.PixIdentifierException;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.net.PropertySet;

/**
 * This class implements the IHE PIX Cross Reference Consumer Actor.  
 * It also implements the com.misyshealthcare.connect.base.IPatientXref
 * interface so that it can be used as patient ID cross reference by the
 * PatientBroker.
 * <p>
 * This class constructs and sends HL7 v2.5 messages as required
 * by the IHE PIX Query Transation (ITI-9). See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.9
 * <li> HL7 v2.5 Standard
 * </ul>
 * A number of connection configuration parameters are used by this
 * actor:
 * <ul>
 * <li> SendingApplication (Identifier) required: 
 *        Specifies the sending application used by this actor for this connection.
 * <li> SendingFacility (Identifier) required: 
 *        Specifies the sending facility used by this actor for this connection.
 * <li> ReceivingApplication (Identifier) required: 
 *        Specifies the receiving application for messages from this actor for this connection.
 * <li> ReceivingFacility (Identifier) required: 
 *        Specifies the receiving application for messages from this actor for this connection.
 * <li> LocalAssigningAuthority (Identifier) optional: 
 *        Specifies the Local Assigning Authority used by this actor. It is used to identify the
 *        Local Assigning Authority to the PIX manager and to
 *        pull out the Local Unique ID from the returned patient IDs.  If it is not supplied, no
 *        Local ID is pulled out of the returned identifier list.  (It is not a good idea to
 *        leave this value unspecified). Either this parameter or the AssigningAuthority
 *        must be supplied.
 * <li> AssigningAuthority (Identifier) optional:
 *        Specifies the preferred ID to send to this PIX manager when trying to cross reference
 *        a patient. If this parameter is not specified then the actor cannot cross reference a
 *        patient that has no Local Unique ID.  Either this parameter or the LocalAssigningAuthority
 *        must be supplied.
  * <li> PixQueryName - (PropertySet) required:
 *        This parameter specifies the query name information to be included in the HL7
 *        message.  The required parts are:
 *        <ul>
 *        <li> identifier - (String) required: A symbolic identifier for the query
 *        <li> text - (String) required: A text string given the query name
 *        <li> codingsystem - (String) required: The name of the coding system for this identifier
 *        </ul>
 * </ul>
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 20, 2005
 * @see com.misyshealthcare.connect.base.IPatientXref
 * @see com.misyshealthcare.connect.base.PatientBroker
 * @see com.misyshealthcare.connect.base.PatientID
 */
public class PixConsumer extends HL7Actor implements IPatientXref {

	/* Logger for problems during SOAP exchanges */
  private static final Logger log = Logger.getLogger(PixConsumer.class);

  /* A counter needed to give each PIX HL7 query a unique ID */
  private static int MessageId = 0;
  
  /* The connection description to this PIX maneger */
  private IConnectionDescription connection = null; 
  
  /* The Local Assigning Authority used by this PIX manager */
  private Identifier localAuthority = null;
  /* The "preferred" Assigning Authority for queries to this PIX manager */
  private Identifier assigningAuthority = null;
 
  /* A list for gathering returned ID strings for MESA test logs */
  private List<String> mesaIdList = null;
  
  private IheAuditTrail auditTrail = null;

  /**
   * Create a new PixConsumer that will talk to the Pix server over
   * the connection description supplied.
   * 
   * @param connection The description of the connection to the server
   * @throws IheConfigurationException 
   */
  public PixConsumer(IConnectionDescription connection, IheAuditTrail auditTrail) throws IheConfigurationException {
  	super(connection, log, auditTrail);
  	this.connection = connection;
  	// Grab out some important configuration parameters
  	assigningAuthority = Configuration.getIdentifier(connection, "AssigningAuthority", false);
  	localAuthority = Configuration.getIdentifier(connection, "LocalAssigningAuthority", false);
  	// Check for configuration errors
  	if ((assigningAuthority == null) && (localAuthority == null)) {
  		throw new IheConfigurationException("PIX consumer using connection '" + connection.getDescription() + "' requires an Assiging Authority.");
  	}
   this.auditTrail = auditTrail;
  }
  
  /**
   * Make a PIX query and process the results.  If a local Unique ID is found it will be
   * returned.  If other IDs are found, they will be added to the patientId object.
   */
	public String getPatientIds(PatientID patientId) throws PatientException {
		// First, select an ID to query about
		String qId = null;
		Identifier qAuthority = null;
		if (patientId.hasLocalUniqueId(false) && (localAuthority != null)) {
			// Our local ID is the best, if we have it
			qId = patientId.getLocalUniqueId(false);
			qAuthority = localAuthority;
		} else if (assigningAuthority != null) {
			// We need to pick an ID and try it out
			// This is a little tricky because not all PIX servers know about all Assigning Authorities
			// Also, right now our model of assigning authorities inside the PatientID is weak
			// So, we'll ask about a preferred assigning authority (this will typically be the PDQ's authority)
			// and that's all
			qId = assigningAuthority.getPatientId(patientId, false);
			qAuthority = assigningAuthority;
		}
		if (qId == null) return null;
		// Okay, we have an ID and Assigning Authority to try, send the message
		Message reply = null;
		try {
			reply = sendPixQuery(qId, qAuthority);
		} catch (DataTypeException e) {
			// The query could not be formatted properly in HL7 format
			String message = "Cannot build PIX query for connection \"" + connection.getDescription() + "\"";
			log.error(message, e);
			throw new PatientException(message, e);
		} catch (IheConfigurationException e) {
			// A connection configuration error
			String message = "Configuration error with PIX connection \"" + connection.getDescription() + "\"";
			log.error(message, e);
			throw new PatientException(message, e);
		} catch (IOException e) {
			// A connection configuration error
			String message = "Problem communicating with PIX server on connection \"" + connection.getDescription() + "\"";
			log.error(message, e);
			throw new PatientException(message, e);
		}

		// Got a reply, process it by adding new IDs to Patient ID object
		// If we find a local unique ID, we can't add it to the Patient ID, so just return it
		try {  
			String localPid =  processPixQueryResponse(reply, patientId);
	        if (auditTrail != null) {
	            try {
					ParticipantObject logpatient = new ParticipantObject(null,  HL7.toCX(qId, qAuthority));
				    Parser pipeParser = new PipeParser();
				    String encodedMessage = pipeParser.encode(reply);
					logpatient.query = encodedMessage;
		            auditTrail.patientQueryIssued(connection, logpatient, true); 
	            } catch (HL7Exception e) {
	    			log.error("Unable to log patient query", e);
				}
	        }
	        return localPid;
		} catch (PixCommunicationException e) {
			// PIX server returned an error
			String message = "PIX server returned error on connection \"" + connection.getDescription() + "\"";
			log.error(message, e);
			throw new PatientException(message, e);
		} catch (PixIdentifierException e) {
			// PIX server did not recognize ID or Authority.  This should never happen.
			String message = "PIX server did not recognize ID '" + qId + "' or '" + qAuthority.getAuthorityNameString() + "' on connection \"" + connection.getDescription() + "\"";
			log.warn(message, e);
			return null;
		}
	}
	
  
  /**
   * Send a PixQuery message to the Pix server and get the response.
   * 
   * @param patientId The ID of the patient to cross-reference with the server
   * @param authority The assigning authority this ID belongs to
   * @return The HL7 message received in response to the query
   * @throws DataTypeException When there is something wrong with the query formatting
   * @throws IheConfigurationException When the connection is not configured to allow message creation
   * @throws IOException When there is a communication error over the socket
   */
  private Message sendPixQuery(String patientId, Identifier authority) throws DataTypeException, IheConfigurationException, IOException {
  	Message pixQuery = createPixQuery(patientId, authority);
  	if (hasMesaLogger()) {
  		IMesaLogger mesaLogger = getMesaLogger();
  		mesaLogger.writeString("Sending PIX query to '" + connection.getDescription() + "' ...");
  		mesaLogger.writeHL7Message(pixQuery);
  	}
  	HL7Channel channel = new HL7Channel(connection);
  	Message reply = channel.sendMessage(pixQuery);
  	if (hasMesaLogger()) {
  		IMesaLogger mesaLogger = getMesaLogger();
  		mesaLogger.writeString("Received response ...");
  		mesaLogger.writeHL7Message(reply);
  	}
  	return reply;
  }
	
  /**
   * Create a new HL7 PixQuery message.
   * 
   * @param patientId The patient ID to be cross-referenced
   * @param authority The assigning authority that the ID comes from
   * @return The HL7 query message
   * @throws DataTypeException When the query is not formatted properly
   * @throws IheConfigurationException When the connection is not configured to enable proper message creation
   */
	private Message createPixQuery(String patientId, Identifier authority) throws DataTypeException, IheConfigurationException {
		String messageId = "PIX_" + MessageId++;
		QBP_Q21 message = new QBP_Q21();
		HL7v25.populateMSH(message.getMSH(), "QBP", "Q23", messageId, connection);
		populatePixQuery(message, messageId, patientId, authority);
		return message;
	}
	
	/**
	 * Fill in the HL7 segments in the Pix Query
	 * 
	 * @param message The HL7 message to populate
	 * @param queryId The HL7 message query ID
	 * @param patientId The patient ID to be cross-referenced
	 * @param authority The assigning authority that the ID comes from
   * @throws DataTypeException When the query is not formatted properly
   * @throws IheConfigurationException When the connection is not configured to enable proper message creation
	 */
	private void populatePixQuery(QBP_Q21 message, String queryId, String patientId, Identifier authority) throws DataTypeException, IheConfigurationException {
		// QPD Segment
		QPD qpd = message.getQPD();
		// QPD-1
		PropertySet code = Configuration.getPropertySet(connection, "PixQueryName", true);
		qpd.getMessageQueryName().getIdentifier().setValue(code.getValue("identifier"));
		qpd.getMessageQueryName().getText().setValue(code.getValue("text"));
		qpd.getMessageQueryName().getNameOfCodingSystem().setValue(code.getValue("codingSystem"));
		// QPD-2
		qpd.getQueryTag().setValue("QRY_" + queryId);
		// QPD-3
		CX cx = new CX(message);
		cx.getIDNumber().setValue(patientId);
		cx.getAssigningAuthority().getNamespaceID().setValue(authority.getNamespaceId());
		cx.getAssigningAuthority().getUniversalID().setValue(authority.getUniversalId());
		cx.getAssigningAuthority().getUniversalIDType().setValue(authority.getUniversalIdType());
		cx.getIdentifierTypeCode().setValue("PI");
		qpd.getUserParametersInsuccessivefields().setData(cx);
		// RCP Segment
		RCP rcp = message.getRCP();
        //RCP priority shall always be I, see IHE ITI TF 2.0 section 3.9.4.1.2.3
        rcp.getQueryPriority().setValue("I");
	}
	
	/**
	 * Process the HL7 response to the Pix Query and add new patient IDs to the patient ID set.
	 * 
	 * @param response The HL7 response to the query
	 * @param patientId The patient ID structure to hold the new IDs
	 * @return The local unique ID returned for this patient
	 * @throws PixCommunicationException When there was an error handling the Pix Query
	 * @throws PixIdentifierException When the PIX server was asked about a patient that it knows nothing about
	 */
	private String processPixQueryResponse(Message response, PatientID patientId) throws PixCommunicationException, PixIdentifierException {
		// Make sure the response is the right type of message
		RSP_K23 message = null;
		if (response instanceof RSP_K23) {
			message = (RSP_K23) response;
		} else {
			String error = "Unexpected response from PIX server \"" + connection.getDescription() + "\"";
			logHL7MessageError(response, error);
			throw new PixCommunicationException(error + ": " + response.toString());
		}
		// There are two places an error can be signaled, in the MSA segment ...
		String status = message.getMSA().getAcknowledgmentCode().getValue();
		if ((status == null) || (!status.equalsIgnoreCase("AA") && !status.equalsIgnoreCase("CA"))) {
			// The server has rejected our request, or generated an error
			throwPixException(message.getERR());
		}
		// And in the QAK segment
		status = message.getQAK().getQueryResponseStatus().getValue();
		if (status.equalsIgnoreCase("OK")) {
			// Everything okay, process the results
			return processPixQueryIds(message.getQUERY_RESPONSE().getPID(), patientId);
		} else if (status.equalsIgnoreCase("NF")) {
			// Everything okay, but no results to report
			return null;
		} else {
			// An error occured
			throwPixException(message.getERR());
			return null;
		}
	}
	
	/**
	 * Fill in the Patient ID structure with the new patient IDs included in the the
	 * Pix Query response.
	 * 
	 * @param pid The PID segment of the PIX Query response
	 * @param patientId The patient ID object to add the new IDs to
	 * @return The local unique ID for this patient if it is found
	 */
	private String processPixQueryIds(PID pid, PatientID patientId) {
		String localId = null;
		CX[] idList = pid.getPatientIdentifierList();
		if (idList != null) {
			for (int i=0; i<idList.length; i++) {
				// Decode the returned ID
				CX theId = idList[i];
				String id = theId.getIDNumber().getValue();
				String namespaceId = theId.getAssigningAuthority().getNamespaceID().getValue();
				String universalId = theId.getAssigningAuthority().getUniversalID().getValue();
				String universalIdType = theId.getAssigningAuthority().getUniversalIDType().getValue();
				// Save a message for the MESA test log, if we are using one
				if (mesaIdList != null) {
					mesaIdList.add(" " + (i+1) + ") " + id + " in " + namespaceId + "&" + universalId + "&" + universalIdType);
				}
				Identifier authority = new Identifier(namespaceId, universalId, universalIdType);
				// See if this a local unique ID
				if ((localAuthority != null) && localAuthority.equals(authority)) {
					// This is a local unique ID, save it to be returned
					localId = id;
				} else {
					// This is not a local unique ID, stuff it into the ID object
					authority.addPatientId(patientId, id);
				}
			}
		}
		return localId;
	}
	
	/**
	 * Throw an appropriate Pix Exception in response to a Pix Query error response.
	 * 
	 * @param err The ERR segment of the Pix Query response
	 * @throws PixCommunicationException When there was a problem handling the Pix Query
	 * @throws PixIdentifierException When the Pix server has never heard of the patient being cross-referenced
	 */
	private void throwPixException(ERR err) throws PixCommunicationException, PixIdentifierException {
		if (err == null) {
			throw new PixCommunicationException("Unspecified error returned by PIX server \"" + connection.getDescription() + "\"");
		} else {
			String code = err.getHL7ErrorCode().getIdentifier().getValue();
			if ((code != null) && code.equals("204")) {
				throw new PixIdentifierException("Identifier or domain not recognized by PIX server \"" + connection.getDescription() + "\"");
			} else {
				throw new PixCommunicationException(HL7v25.getErrorString(err, "PIX"));
			}
		}
	}
	
	/**
	 * Get the custom mesa test interface for this actor.  The mesa test
	 * interface allows PIX queries to be sent for a specific ID and
	 * Assigning Authority.
	 * 
	 * @return The mesa test interface
	 */
	public MesaInterface getMesaInterface() {
		return new MesaInterface();
	}
	
	/**
	 * This class returns an interface for use by MESA test programs.  It enables
	 * the non-mesa testing public interface to be cleaner.
	 * 
	 * @author Jim Firby
	 * @version 2.0 - Dec 26, 2005
	 */
	public class MesaInterface {
		
		/**
		 * Make a PIX query for a specific ID and Assigning Authority.
		 * 
		 * @param patientId The ID of the patient to cross reference
		 * @param authority The Assigning Authority for the ID
		 * @return A list of alternate ID/Assigning Authority strings returned by the PIX query
		 * @throws DataTypeException When values cannot be encoded into HL7
		 * @throws IheConfigurationException When this connection is not configured properly for PIX queries
		 * @throws IOException When there is a problem communicating with the PIX server
		 * @throws PixCommunicationException When the PIX server returnes an error
		 * @throws PixIdentifierException When the requested ID or Authority is no recognized by the PIX server
		 */
	  public List<String> doPixQuery(String patientId, Identifier authority) throws DataTypeException, IheConfigurationException, IOException, PixCommunicationException, PixIdentifierException {
	  	ArrayList<String> result = new ArrayList<String>();
	  	mesaIdList = result;
	  	Message reply = sendPixQuery(patientId, authority);
	  	PatientID pid = new PatientID();
	  	processPixQueryResponse(reply, pid);
	  	mesaIdList = null;
	  	return result;
	  }
	  
	}
	
	

//	/**
//	 * A simple test program.
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		StandardConnectionDescription conn = new StandardConnectionDescription();
//		conn.addIdentifier("SendingApplication", new Identifier("Misys", "1.2.3", "ISO"));
//		conn.addIdentifier("SendingFacility", new Identifier("Misys", "1.2.3", "ISO"));
//		conn.addIdentifier("ReceivingApplication", new Identifier("MESA", "4.5.6", "ISO"));
//		conn.addIdentifier("ReceivingFacility", new Identifier("MESA", "4.5.6", "ISO"));
//		PropertySet ps = new PropertySet("PixQueryName");
//		ps.addValue("identifier", "QRY_1001");
//		ps.addValue("text", "Query for Corresponding Identifiers");
//		ps.addValue("codingSystem", "IHEDEMO");
//		conn.addPropertySet(ps);
//		PixConsumer pix = new PixConsumer(conn);
//		try {
//			Identifier authority = new Identifier("IHEDEMO", "1.2.3.345.6", "ISO");
//			Message message = pix.createPixQuery("foo", authority);
//			System.out.println(PipeParser.encode(message, new EncodingCharacters('|', "^~\\&")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
