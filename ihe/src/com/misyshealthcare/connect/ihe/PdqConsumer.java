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
import ca.uhn.hl7v2.model.Structure;

import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.DLN;
import ca.uhn.hl7v2.model.v25.datatype.HD;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.datatype.XPN;
import ca.uhn.hl7v2.model.v25.datatype.XTN;
import ca.uhn.hl7v2.model.v25.group.RSP_K21_QUERY_RESPONSE;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.message.QCN_J01;
import ca.uhn.hl7v2.model.v25.message.RSP_K21;
//import ca.uhn.hl7v2.model.v25.message.RSP_K22;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.QPD;
import ca.uhn.hl7v2.model.v25.segment.RCP;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;



import com.misyshealthcare.connect.base.IPatientSource;
import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.PatientQuery;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.hl7.HL7v231;
import com.misyshealthcare.connect.ihe.hl7.HL7v25;
import com.misyshealthcare.connect.ihe.pdq.PdqCommunicationException;
import com.misyshealthcare.connect.ihe.pdq.PdqException;
import com.misyshealthcare.connect.ihe.pdq.PdqQueryException;
import com.misyshealthcare.connect.ihe.pdq.QueryBuilder;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.net.PropertySet;

/**
 * This class implements the IHE Patient Demographics Consumer Actor.  
 * It also implements the com.misyshealthcare.connect.base.IPatientSource
 * interface so that it can be used as patient source by the
 * PatientBroker.
 * <p>
 * This class constructs and sends HL7 v2.5 messages as required
 * by the IHE Patient Demographics Query Transation (ITI-21). See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.21
 * <li> HL7 v2.5 Standard
 * <li> Amendment to the Technical Framework specifying the continuation
 *      cancel message as QCN_J01 (November 2005)
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
 *        Specifies the Local Assigning Authority used by this actor. It is used to
 *        pull out the Local Unique ID from the returned patient IDs.  If it is not supplied, no
 *        Local ID is pulled out of the returned identifier list.  (It is not a good idea to
 *        leave this value unspecified).
 * <li> AssigningAuthority (Identifier) optional:
 *        Specifies that this actor only return patient IDs from this assigning authority (and
 *        the LocalAssigningAuthority if it is defined).  If this parameter is not specified then the
 *        actor will ask for all known IDs from the PDQ server.
 * <li> MaximumChunkSize - (Property int) optional:
 *        Specifies the maximum number of patients that should be returned by a single
 *        query.  The actor will keep making continuation queries off this size until
 *        the desired number of patients are retrieved.  By default the chunk size is
 *        unlimited.
 * <li> KeepContinuationAlive - (Property boolean) optional:
 *        If multiple contiuations are required they will all be made over a single
 *        socket if this paoperty is "true".  If "false", multiple socket connections
 *        will be made.  By default this parameter is "true".
 * <li> PdqQueryName - (PropertySet) required:
 *        This parameter specifies the query name information to be included in the HL7
 *        message.  The required parts are:
 *        <ul>
 *        <li> identifier - (String) required: A symbolic identifier for the query
 *        <li> text - (String) required: A text string given the query name
 *        <li> codingsystem - (String) required: The name of the coding system for this identifier
 *        </ul>
 * <li> QueryFields - (PropertySet) optional:
 *        This property set specifies which HL7 parameters should be excluded from a
 *        query even if a value is supplied.  Some PDQ servers may not properly handle
 *        some fields and they should simply not be sent.  Each entry name specifies a
 *        field id like "PID.3.1" or "PID.5.2" and the entry value is "false" to exclude
 *        the value and "true" to include it (if it has a value in the query).  The
 *        default for all fields is "true".
 * <li> QueryProperties - (PropertySet) optional:
 *        This set of properties specifies global characteristics for queries to the PDQ
 *        server over this connection.  The allowed parts are:
 *        <ul>
 *        <li> Uppercase - (boolean) optional: If this value is "true" all values sent are
 *        converted to uppercase.  The default is false (meaning no conversion).
 *        <li> Lowercase - (boolean) optional: If this value is "true" all values sent are
 *        converted to lowercase.  The default is false (meaning no conversion).  If both Uppercase
 *        and Lowercase are supplied, uppercase will be used.
 *        <li> WildcardAfter - (String) optional: Specifies the wildcard value this connection
 *        expects at the end of a value.  If this value is not supplied no wildcard will be used
 *        at the end of a value, no matter what the user or Misys Connect expects.
 *        <li> WildcardBefore - (String) optional: Specifies the wildcard value this connection
 *        expects at the end of a value.  If this value is not supplied no wildcard will be used
 *        at the beginning of a value, no matter what the user or Misys Connect expects.
 *        <li> WildcardAfterPrefix - (String) optional: Specifies a value to add to the beginning of
 *        a value when the after wildcard is added to the end (seldom used).
 *        <li> WildcardBeforeSuffix - (String) optional: Specifies a value to add to the end of
 *        a value when the before wildcard is added to the beginning (seldom used).
 *        <li> WildcardBothPrefix - (String) optional: Specifies a value to add to the beginning of
 *        a value if it should have wildcards at both ends.  This value overrides WildcardBefore (seldom used).
 *        <li> WildcardBothSuffix - (String) optional: Specifies a value to add to the end of
 *        a value if it should have wildcards at both ends.  This value overrides WildcardAfter (seldom used).
 *        <ul>
 * <li> QueryFieldMisysNames - (PropertySet) optional:
 *        This property set maps field ids (ie. PID.3,1 and PID.5.2) to the field names for these values
 *        used in the Misys Connect Query Design Properties file.  Each field has a built-in default but
 *        these can be overridden using this property set.  This is used to look up default wildcard
 *        usage in Misys Connect.
 * </ul>
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 23, 2005
 * @see com.misyshealthcare.connect.base.IPatientSource
 * @see com.misyshealthcare.connect.base.PatientBroker
 * @see com.misyshealthcare.connect.base.demographicdata.PatientDescriptor
 */
public class PdqConsumer extends HL7Actor implements IPatientSource {

	/* Logger for problems during SOAP exchanges */
  private static final Logger log = Logger.getLogger(PatientIdentitySource.class);
  
  /* The QueryBuilder object for this actor */
  private QueryBuilder queryBuilder = null;
  /* The IHE Audit Trail for this actor. */
  private IheAuditTrail auditTrail = null;

  /* The connection that this PDQ consumer should use when making queries */
  private IConnectionDescription connection = null; 
  /* The largest single query we should make, <1 means any size */
  private int maximumChunkSize = -1;
  /* If we have to make multiple queries to get the number we want, should we use one socket */
  private boolean keepAlive = true;
  
  /* The Local Assigning Authority for this connection */
  private Identifier localAssigningAuthority = null;
  /* An Assigning Authority of interest beside the Local authority */
  private Identifier filterAssigningAuthority = null;

  /* An increment to ensure unique query message IDs for HL7 */
  private static int MessageId = 0;

  /**
   * Create a new PDQ Consumer that will make queries to the supplied
   * connection.
   * 
   * @param connection The connection to the PDQ server
   */
  public PdqConsumer(IConnectionDescription connection, IheAuditTrail auditTrail) {
		super(connection, log, auditTrail);
		this.connection = connection;
		this.auditTrail = auditTrail;
		// Create a query builder
		queryBuilder = new QueryBuilder(connection);
		// Grab out the maximum single query chunk size for this connection
		String chunkString = connection.getProperty("MaximumChunkSize");
		if (chunkString != null) {
			try {
				maximumChunkSize = Integer.parseInt(chunkString);
			} catch (Exception e) {}
		}
		// Get whether or not we should use a single connection for continuations
		String keepAliveString = connection.getProperty("KeepContinuationAlive");
		if (keepAliveString != null) {
			keepAlive = Boolean.parseBoolean(keepAliveString);
		}
		// Grab out the Local assigning authority if one is specified
		localAssigningAuthority = connection.getIdentifier("LocalAssigningAuthority");
		// Grab out the restriction assigning authority if one is specified
		filterAssigningAuthority = connection.getIdentifier("AssigningAuthority");		
	}
  
	/**
	 * Find all of the patients from this Patient Source that match the supplied
	 * patient query parameters.
	 * @see com.misyshealthcare.connect.base.IPatientSource#findPatients(PatientQuery)
	 */
	public List<PatientDescriptor> findPatients(PatientQuery query) throws PatientException {
		// If we are only supposed to use the home system, bail since home system has no meaning to the IHE
		if (query.getHomeSystemOnly()) return new ArrayList<PatientDescriptor>();
		// Get the query chunk size
		int biggestChunk = query.getMaxPatientCount();
		if (biggestChunk < 1) {
			biggestChunk = maximumChunkSize;
		} else if ((maximumChunkSize > 0) && (maximumChunkSize < biggestChunk)) {
			biggestChunk = maximumChunkSize;
		}
		// Create the query and send it
		QBP_Q21 message = null;
		try {
			// Creat a base query, then populate it
			message = createPdqQuery(biggestChunk);
			if (queryBuilder.populatePdqQuery(message, query)) {
				// See if we are restricting the ID assigning authority to be returned
				if (filterAssigningAuthority != null) {
					queryBuilder.restrictQueryDomain(message, filterAssigningAuthority);
					// Make sure we get back any Local IDs too!
					if (localAssigningAuthority != null) {
						queryBuilder.restrictQueryDomain(message, localAssigningAuthority);
					}
				}
				
				// Okay, make the query and return the results
				List<PatientDescriptor> patients = sendPdqQuery(message, query.getMaxPatientCount(), keepAlive);
				// Let them know who we looked up.
		        if (auditTrail != null) {
		            try {    
						ParticipantObject logpatient = new ParticipantObject(query);
					    Parser pipeParser = new PipeParser();
					    String encodedMessage = pipeParser.encode(message);
						logpatient.query = encodedMessage;
			            auditTrail.patientQueryIssued(connection, logpatient, false); 
		            } catch (HL7Exception e) {
		    			log.error("Unable to log patient query", e);
					}
		        }
				return patients;
			}
		} catch (Exception e) {
			// We could have failed for a lot of reasons, make a note
			String error = "Unable to make Patient Demographics Query to '" + connection.getDescription() + "'";
			log.error(error, e);
			throw new PatientException(error, e);
		}
		// Failed to make a successful query
		return new ArrayList<PatientDescriptor>();
	}
	
	/**
	 * Send a fully configured PDQ query to the server and process the response.
	 * <p>
	 * This method keeps making continuation queries (if possible) until 'count'
	 * patients have been retrieved.  If 'keepOpen' is true then all of these
	 * continuation queries use a single socket that is not closed until the final
	 * query is made.
	 *
	 * @param query The HL7 PDQ query to send
	 * @param count The maximum number of patients to be returned
	 * @param keepOpen If continuation calls are required, use the same socket for each one
	 * @return Descriptors of the patients returned by the PDQ query
	 * @throws IheConfigurationException When the connection is not configured properly
	 * @throws IOException When there is a problem communicating with the PDQ server
	 * @throws PdqCommunicationException When the PDQ server responds in an unexpected way
	 * @throws PdqQueryException When there is a problem processing the PDQ response
	 * @throws HL7Exception When a value cannot be encoded to or decoded from HL7
	 */
	private List<PatientDescriptor> sendPdqQuery(Message query, int count, boolean keepOpen) throws IheConfigurationException, IOException, PdqCommunicationException, PdqQueryException, HL7Exception {		
		// An array for collecting all of the patients across continuations
		ArrayList<PatientDescriptor> patients = new ArrayList<PatientDescriptor>();
		// Create a channel for communicating with the PDQ server
		HL7Channel channel = new HL7Channel(connection);
		// Make the query and follow-up with enough continuations to get the desired number of patients
		if (query instanceof QBP_Q21) {
			// Send the query
			Message reply = sendOnePdqQuery(query, channel, keepOpen);
			// Process the response
			if (reply == null) return null;
			String continuationPointer = processPdqQueryResponse(reply, patients);
			// If there are more patients, loop until we have enough
			while ((continuationPointer != null) && ((count <= 0) || (patients.size() < count))) {
				// Massage the query to make it a continuation request
				Message continuation = createContinuationQuery((QBP_Q21) query, continuationPointer);
				// Send it
				reply = sendOnePdqQuery(continuation, channel, keepOpen);
				// Process the response
				continuationPointer = processPdqQueryResponse(reply, patients);
			}
			// Send cancel message if we don't want any more
			if (continuationPointer != null) {
				Message cancel = createContinuationCancel((QBP_Q21) query);
				try {
					reply = sendOnePdqQuery(cancel, channel, keepOpen);
					processPdqAckResponse(reply);
				} catch (PdqException e) {
					// Log a warning and then ignore
					log.warn("Trouble cancelling PDQ query continuation", e);
				}
			}
		}
		// Close the channel
		channel.close();
		// Return whatever we found
		return patients;
	}
	
	/**
	 * Send a single PDQ query to the PDQ server.
	 * 
	 * @param query The HL7 PDQ query to send
	 * @param channel The HL7 communication channel to use for the query
	 * @param keepOpen True if this channel should be kept open after the query
	 * @return The HL7 response to the query
	 * @throws DataTypeException When there is a problem encoding the HL7 message parameters
	 * @throws IheConfigurationException When the connection is not configured properly
	 * @throws IOException When there is a problem communicating with the PDQ server
	 */
  private Message sendOnePdqQuery(Message query, HL7Channel channel, boolean keepOpen) throws DataTypeException, IheConfigurationException, IOException {
  	if (hasMesaLogger()) {
  		IMesaLogger mesaLogger = getMesaLogger();
  		if (channel.isOpen()) {
  	 		mesaLogger.writeString("Sending PDQ query to open connection '" + connection.getDescription() + "' ...");
  		} else {
  			mesaLogger.writeString("Sending PDQ query to '" + connection.getDescription() + "' ...");
  		}
  		mesaLogger.writeHL7Message(query);
  	}
  	//HL7Channel channel = new HL7Channel(connection);
  	Message reply = channel.sendMessage(query, keepOpen);
  	if (hasMesaLogger()) {
  		IMesaLogger mesaLogger = getMesaLogger();
  		mesaLogger.writeString("Received response ...");
  		mesaLogger.writeHL7Message(reply);
  	}
  	return reply;
  }

  /**
   * Create a new PDQ query but do not populate the segments describing the
   * actual patient query parameters.
   * 
   * @param chunkSize The maximum number of patients to return in a single call
   * @return The HL7 query message
   * @throws DataTypeException When there is a problem encoding values into HL7
   * @throws IheConfigurationException When the connection is not configured properly
   */
  private QBP_Q21 createPdqQuery(int chunkSize) throws DataTypeException, IheConfigurationException {
		String messageId = getMessageId();
		QBP_Q21 message = new QBP_Q21();
		// Add the MSH segment
		HL7v25.populateMSH(message.getMSH(), "QBP", "Q22", messageId, connection);
		// Add the QPD segment
		populateQPD(message.getQPD(), messageId);
		// Add the RCP segment
		populateRCP(message.getRCP(), chunkSize);
		return message;
	}
  
  /**
   * Generate a new, unique PDQ HL7 message ID.
   * 
   * @return A new HL7 message ID
   */
  private String getMessageId() {
  	return "PDQ_" + MessageId++;
  }
	
  /**
   * Populate the QPD segment of a query message.  This method fills in
   * everything except the patient query parameters.
   * 
   * @param qpd The QPD segment to populate
   * @param queryId The HL7 ID for this query (constant across continuations)
   * @throws IheConfigurationException When this connection is not configured properly
   * @throws DataTypeException When there is a problem encoding HL7 values
   */
  private void populateQPD(QPD qpd, String queryId) throws IheConfigurationException, DataTypeException {
		// QPD-1
		PropertySet code = Configuration.getPropertySet(connection, "PdqQueryName", true);
		qpd.getMessageQueryName().getIdentifier().setValue(code.getValue("identifier"));
		qpd.getMessageQueryName().getText().setValue(code.getValue("text"));
		qpd.getMessageQueryName().getNameOfCodingSystem().setValue(code.getValue("codingSystem"));
		// QPD-2
		qpd.getQueryTag().setValue("QRY_" + queryId);
		// QPD-3 - Filled in by other functions
		
	}
	
  /**
   * Populate the RCP segment of a PDQ query.
   * 
   * @param rcp The RCP segment to populate
   * @param count The maximum number of patients to return in this query
   * @throws DataTypeException When there is a problem encoding HL7 values
   */
	private void populateRCP(RCP rcp, int count) throws DataTypeException {
		// RCP-1
		rcp.getQueryPriority().setValue("I");
		// RCP-2 (Optional) The number of answers to return
		if (count > 0) {
			rcp.getQuantityLimitedRequest().getQuantity().setValue(Integer.toString(count));
			rcp.getQuantityLimitedRequest().getUnits().getIdentifier().setValue("RD");
		}
	}
	
	/**
	 * Modify a PDQ query to become a continuation query.  This method
	 * changes the message ID (which must be different for every query) and adds the
	 * continuation pointer from the previous query response.
	 * 
	 * @param message The original HL7 PDQ query message
	 * @param continuationPointer  The next continuation pointer
	 * @return The original message modified to be a continuation query
	 * @throws DataTypeException The 
	 */
	private QBP_Q21 createContinuationQuery(QBP_Q21 message, String continuationPointer) throws DataTypeException {
		// First, update the message header
		HL7v25.updateMSH(message.getMSH(), getMessageId());
		// Next, update the DSC
		message.getDSC().getContinuationPointer().setValue(continuationPointer);
		message.getDSC().getContinuationStyle().setValue("I");
		// Done
		return message;
	}
	
	/**
	 * Create a new HL7 PDQ query cancel message from the original PDQ query message.
	 * 
	 * @param query The query to be cancelled
	 * @return The HL7 cancel message needed to cancel the query
	 * @throws DataTypeException When there is a problem encoding HL7 values
	 * @throws IheConfigurationException When the connection is not configured properly
	 */
  private QCN_J01 createContinuationCancel(QBP_Q21 query) throws DataTypeException, IheConfigurationException {
		String messageId = getMessageId();
		QCN_J01 message = new QCN_J01();
		// Add the MSH segment
		HL7v25.populateMSH(message.getMSH(), "QCN", "J01", messageId, connection);
		// Add QID.1
		message.getQID().getQueryTag().setValue(query.getQPD().getQueryTag().getValue());
		// Add QID.2
		CE ceNew = message.getQID().getMessageQueryName();
		CE ceOld = query.getQPD().getMessageQueryName();
		ceNew.getIdentifier().setValue(ceOld.getIdentifier().getValue());
		ceNew.getText().setValue(ceOld.getText().getValue());
		ceNew.getNameOfCodingSystem().setValue(ceOld.getNameOfCodingSystem().getValue());
		// Done
		return message;
	}

	/**
	 * Process a PDQ query response and extract the patient demographics information into
	 * a list of Patient Descriptors.  This method adds each new patient returned by the PDQ
	 * to the supplied list of patients.
	 * 
	 * @param response The HL7 query response
	 * @param patients The list of Patient Descriptors the new patients should be added to
	 * @return The continuation pointer needed to get the next chunk of patients, if there is one
	 * @throws PdqCommunicationException When the response is not HL7, or unexpected
	 * @throws PdqQueryException When the response holds an HL7 error
	 * @throws HL7Exception When there is a problem decoding the response
	 */
	private String processPdqQueryResponse(Message response, List<PatientDescriptor> patients) throws PdqCommunicationException, PdqQueryException, HL7Exception {
		// This is a bit messed up because HAPI parses the response into a RSP_K21 instead of an RSP_K22
		//  The only difference is that in K22 the QUERY_RESPONSE segment is allowed to repeat
		// Make sure the response is the right type of message
		RSP_K21 message = null;
		if (response instanceof RSP_K21) {
			message = (RSP_K21) response;
		} else {
			logHL7MessageError(response, "Unexpected response from PDQ server");
			throw new PdqCommunicationException("Unexpected response from PDQ server: " + response.toString());
		}
		// There are two places an error can be signaled, in the MSA segment ...
		String status = message.getMSA().getAcknowledgmentCode().getValue();
		if ((status == null) || (!status.equalsIgnoreCase("AA") && !status.equalsIgnoreCase("CA"))) {
			// The server has rejected our request, or generated an error
			throwPdqException(message.getERR());
		}
		// And in the QAK segment
		status = message.getQAK().getQueryResponseStatus().getValue();
		if (status.equalsIgnoreCase("OK")) {
			// Everything okay, process the results
			// Because we don't get repetitions for free, look at all segments
			//  returned and pull out the ones that are PIDs.  They may have
			//  non-standard names.
			ArrayList<PID> pids = new ArrayList<PID>();
			RSP_K21_QUERY_RESPONSE qr = message.getQUERY_RESPONSE();
			String[] segNames = qr.getNames();
			for (int i=0; i<segNames.length; i++) {
				Structure[] segments = qr.getAll(segNames[i]);
				for (int j=0; j<segments.length; j++) {
					if (segments[j] instanceof PID) {
						pids.add((PID) segments[j]);
					}
				}
			}
			processPdqResponsePatients(pids, patients);
			// Extract the continutation pointer, if there is one
			String continuationPointer = null;
			if (message.getDSC() != null) {
				continuationPointer = message.getDSC().getContinuationPointer().getValue();
			}
			return continuationPointer;
		} else if (status.equalsIgnoreCase("NF")) {
			// Everything okay, but no results to report
			return null;
		} else {
			// An error occured
			throwPdqException(message.getERR());
			return null;
		}

	}
	
	/**
	 * Process a list of HL7 PID segments to extract the patient demographics from each one.
	 * 
	 * @param pids The list of PID segments
	 * @param patients The list to extend with new patient demographics descriptors
	 * @throws HL7Exception When there is a problem decoding the HL7
	 */
	private void processPdqResponsePatients(ArrayList<PID> pids, List<PatientDescriptor> patients) throws HL7Exception {
		for (PID pid: pids) {
			PatientDescriptor patient = processPdqResponsePID((PID) pid);
			if (patient != null) patients.add(patient);
		}
	}
	
	/**
	 * Process a single HL7 PID segment to extract the deomgraphics for a single patient.
	 * 
	 * @param pid The PID segment to process
	 * @return The demographics for the patient encoded in the PID
	 * @throws HL7Exception When there is a problem decoding the HL7
	 */
	private PatientDescriptor processPdqResponsePID(PID pid) throws HL7Exception {
		PatientDescriptor patient = new PatientDescriptor();
		// PID-3 - Id List
		Identifier misysAuthority = localAssigningAuthority;
		PatientID patientId = null;
		CX[] ids = pid.getPatientIdentifierList();
		// Look for the Local ID, saving authorities along the way
		ArrayList<Identifier> authorities = new ArrayList<Identifier>();
		for (int i=0; i<ids.length; i++) {
			CX id = ids[i];
			String idValue = id.getIDNumber().getValue();
			HD aa = id.getAssigningAuthority();
			if ((idValue != null) && (aa != null)) {
				Identifier authority = new Identifier(
						aa.getNamespaceID().getValue(), aa.getUniversalID().getValue(), aa.getUniversalIDType().getValue());
				if (authority.equals(misysAuthority)) {
					patientId = new PatientID(idValue);
					authorities.add(null);
				} else {
					authorities.add(authority);
				}
			}
		}
		// If Local ID not found, oh well
		if (patientId == null) patientId = new PatientID();
		// Now add all translations into other domains
		for (int i=0; i<ids.length; i++) {
			String id = ids[i].getIDNumber().getValue();
			if (id != null) {
				Identifier authority = authorities.get(i);
				if (authority != null) authority.addPatientId(patientId, id);
			}
		}
		// Finally, pop this ID into the patient
		patient.setPatientId(patientId);
		// PID-5 - Name
		XPN xpn =	pid.getPatientName(0);
		if (xpn != null) {
			patient.setNameFirst(xpn.getGivenName().getValue());
			patient.setNameMiddle(xpn.getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
			patient.setNameLast(xpn.getFamilyName().getSurname().getValue());
			patient.setNameTitle(xpn.getPrefixEgDR().getValue());
			patient.setNameSuffix(xpn.getSuffixEgJRorIII().getValue());
		}
		// PID-6 - Mother's maiden name
		xpn =	pid.getMotherSMaidenName(0);
		if (xpn != null) {
			patient.setMotherMaidenName(xpn.getFamilyName().getSurname().getValue());
		}
		// PID-7 - Birth Date
		patient.setBirthDateTime(HL7v25.unformatDTM(pid.getDateTimeOfBirth().getTime(), false));
		// PID-8 - Gender
		String gender = pid.getAdministrativeSex().getValue();
		if (gender != null) {
			if (gender.equalsIgnoreCase("M")) patient.setAdministrativeSex(SexType.MALE);
			else if (gender.equalsIgnoreCase("F")) patient.setAdministrativeSex(SexType.FEMALE);
			else if (gender.equalsIgnoreCase("O")) patient.setAdministrativeSex(SexType.OTHER);
			else patient.setAdministrativeSex(SexType.UNKNOWN);
		}
		// PID-ll - Addresses
		XAD[] xads = pid.getPatientAddress();
		if (xads != null) {
			for (int i=0; i<xads.length; i++) {
				XAD xad = xads[i];
				Address address = new Address();
				address.setAddLine1(xad.getStreetAddress().getStreetOrMailingAddress().getValue());
				address.setAddLine2(xad.getOtherDesignation().getValue());
				address.setAddCity(xad.getCity().getValue());
				address.setAddState(xad.getStateOrProvince().getValue());
				address.setAddZip(xad.getZipOrPostalCode().getValue());
				address.setAddType(AddressType.UNKNOWN);
				String type = xad.getAddressType().getValue();
				if (type != null) {
					if (type.equalsIgnoreCase("H")) address.setAddType(AddressType.HOME);
					else if (type.equalsIgnoreCase("W")) address.setAddType(AddressType.WORK);
				}
				if (!address.isEmpty()) patient.addAddress(address);
			}
		}
		// PID-13 - Home Phone
		XTN[] xtns = pid.getPhoneNumberHome();
		if (xtns != null) {
			for (int i=0; i<xtns.length; i++) {
				PhoneNumber phone = unpackPhoneNumber(xtns[i]);
				if (phone != null) patient.addPhoneNumber(phone);
			}
		}
		// PID-14 - Work Phone
		xtns = pid.getPhoneNumberBusiness();
		if (xtns != null) {
			for (int i=0; i<xtns.length; i++) {
				PhoneNumber phone = unpackPhoneNumber(xtns[i]);
				if (phone != null) patient.addPhoneNumber(phone);
			}
		}
		// PID-18 - Account number (we don't really have anywhere to put this)
		//  In the PatientFeedSource we send the first MRN associated with a patient
		//  However, this might be from non-Misys source and will have no meaning as an MRN
		//  (See PatientIdentitySource and PDQ QueryBuilder
		// TODO - Decide whether we want to use 'foreign' MRNs
		// PID-19 - SSN
		patient.setSsn(pid.getSSNNumberPatient().getValue());
		// PID-20 - Driver's license
		DLN dln =	pid.getDriverSLicenseNumberPatient();
		if (dln != null) {
			String number = dln.getLicenseNumber().getValue();
			String state = dln.getIssuingStateProvinceCountry().getValue();
			if ((number != null) && (state != null) && (!state.trim().equals(""))) {
				number = number + "(" + state.trim() + ")";
			}
			patient.setDriverLicense(number);
		}
		// Done
		return patient;
	}
	
	/**
	 * Unpack the information in an HL7 XTN phone number structure into
	 * a PhoneNumber.
	 * 
	 * @param xtn The HL7 phone number to unpack
	 * @return The PhoneNumber holding the information
	 */
	private PhoneNumber unpackPhoneNumber(XTN xtn) {
		PhoneNumber phone = new PhoneNumber();
		phone.setCountryCode(xtn.getCountryCode().getValue());
		phone.setAreaCode(xtn.getAreaCityCode().getValue());
		phone.setNumber(xtn.getLocalNumber().getValue());
		phone.setExtension(xtn.getExtension().getValue());
		phone.setNote(xtn.getAnyText().getValue());
		String usage = xtn.getTelecommunicationUseCode().getValue();
		phone.setType(PhoneType.UNKNOWN);
		if (usage != null) {
			if (usage.equalsIgnoreCase("PRN")) phone.setType(PhoneType.HOME);
			else if (usage.equalsIgnoreCase("WPN")) phone.setType(PhoneType.WORK);
			else if (usage.equalsIgnoreCase("EMR")) phone.setType(PhoneType.EMERGENCY);
			else if (usage.equalsIgnoreCase("ASN")) phone.setType(PhoneType.SERVICE);
		}
		String equipment = xtn.getTelecommunicationEquipmentType().getValue();
		if (equipment != null) {
			if (equipment.equalsIgnoreCase("CP")) phone.setType(PhoneType.CELL);
			else if (equipment.equalsIgnoreCase("FX")) phone.setType(PhoneType.FAX);
		}
		if (phone.isEmpty()) {
			HL7v231.parsePhoneNumber(phone, xtn.getUnformattedTelephoneNumber().getValue());
		}
		if (phone.isEmpty()) {
			return null;
		} else {
			return phone;
		}
	}
	
	/**
	 * Process an HL7 ACK message from the PDQ server.  This response will be returned
	 * when a cancel message is sent.
	 * 
	 * @param response The HL7 response that should be an ACK
	 * @return True if the ACK says the cancel message succeeded
	 * @throws PdqCommunicationException When the message is not an ACK
	 * @throws PdqQueryException When the message holds an error
	 */
	private boolean processPdqAckResponse(Message response) throws PdqCommunicationException, PdqQueryException {
		// Make sure the response is the right type of message
		ACK message = null;
		if (response instanceof ACK) {
			message = (ACK) response;
		} else {
			String error = "Unexpected response from PDQ server \"" + connection.getDescription() + "\"";
			logHL7MessageError(message, error);
			throw new PdqCommunicationException(error + ": " + response.toString());
		}
		// Check the MSA segment ...
		String status = message.getMSA().getAcknowledgmentCode().getValue();
		if ((status == null) || (!status.equalsIgnoreCase("AA") && !status.equalsIgnoreCase("CA"))) {
			// The server has rejected our request, or generated an error
			throwPdqException(message.getERR());
		}
		// Okay, we're good
		return true;
	}

	/**
	 * Throw an appropriate PDQ Exception in response to a PDQ Query error response.
	 * 
	 * @param err The ERR segment of the PDQ Query response
	 * @throws PdqCommunicationException When there was a problem handling the PDQ Query
	 * @throws PdqQueryException When the PDQ server has never heard of the patient domain being cross-referenced
	 */
	private void throwPdqException(ERR err) throws PdqCommunicationException, PdqQueryException {
		if (err == null) {
			throw new PdqCommunicationException("Unspecified error returned by PDQ server \"" + connection.getDescription() + "\"");
		} else {
			String code = err.getHL7ErrorCode().getIdentifier().getValue();
			if ((code != null) && code.equals("204")) {
				throw new PdqQueryException("Identifier or domain not recognized by PDQ server \"" + connection.getDescription() + "\"");
			} else {
				throw new PdqCommunicationException(HL7v25.getErrorString(err, "PDQ"));
			}
		}
	}
	
	/**
	 * Get the custom mesa test interface for this actor.  The mesa test
	 * interface allows the query chunk size and the filtering assigning
	 * authority to be set and override the values in the configuration
	 * files for the connection.
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
	 * @version 2.0 - Dec 13, 2005
	 */
	public class MesaInterface {
		
		/**
		 * Set the maximum query chunk size for this actor.
		 * 
		 * @param chunkSize The new maximum chunk size
		 */
		public void setMaximumChunkSize(int chunkSize) {
			maximumChunkSize = chunkSize;
		}
		
		/**
		 * Set the Assigning Authority filter for this actor.
		 * 
		 * @param filter Restrict the query to IDs from this assigning authority
		 */
		public void setFilterAssigningAuthority(Identifier filter) {
			filterAssigningAuthority = filter;
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
//		PropertySet ps = new PropertySet("PdqQueryName");
//		ps.addValue("identifier", "PDQ_1001");
//		ps.addValue("text", "Deomgraphics Query");
//		ps.addValue("codingSystem", "IHEDEMO");
//		conn.addPropertySet(ps);
//		PdqConsumer pdq = new PdqConsumer(conn);
//		try {
//			Identifier authority = new Identifier("IHEDEMO", "1.2.3.345.6", "ISO");
//			QBP_Q21 message = pdq.createPdqQuery(10);
//			pdq.addPatientNameQuery(message, "Frank", null, "Jones");
//			pdq.addPatientIdQuery(message, "Misys-123");
//			pdq.restrictQueryDomain(message, null, "1.2.3.345.6", "ISO");
//			pdq.restrictQueryDomain(message, "IHE", null, null);
//			System.out.println(PipeParser.encode(message, new EncodingCharacters('|', "^~\\&")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
