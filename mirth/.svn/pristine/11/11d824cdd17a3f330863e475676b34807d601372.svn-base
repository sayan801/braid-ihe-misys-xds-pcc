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
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.datatype.*;
import ca.uhn.hl7v2.model.v231.message.*;
import ca.uhn.hl7v2.model.v231.segment.*;

import com.misyshealthcare.connect.base.IPatientConsumer;
import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.clinicaldata.Visit;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientMRN;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.hl7.HL7v231;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;

/**
 * This class implements the IHE Patient Identity Feed Actor.  It also
 * implements the com.misyshealthcare.connect.base.IPatientConsumer
 * interface so that it can be used as patient consumer by the
 * PatientBroker.
 * <p>
 * This class constructs and sends HL7 v2.3.1 messages as required
 * by the IHE Patient ITI-8 Transation. See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.8
 * <li> HL7 v2.3.1 Standard
 * </ul>
 * A number of connection configuration parameters are used by this
 * actor:
 * <ul>
 * <li> SendingApplication - (Identifier) required: 
 *        Specifies the sending application used by this actor for this connection.
 * <li> SendingFacility - (Identifier) required: 
 *        Specifies the sending facility used by this actor for this connection.
 * <li> ReceivingApplication - (Identifier) required: 
 *        Specifies the receiving application for messages from this actor for this connection.
 * <li> ReceivingFacility - (Identifier) required: 
 *        Specifies the receiving application for messages from this actor for this connection.
 * <li> LocalAssigningAuthority - (Identifier) required: 
 *        Specifies the Local Assigning Authority used by this actor.
 * <li> IPatientConsumer$CreationReason - -(EnumMap) required:
 * <li> SSNAssigningAuthority - (Identifier) optional: 
 *        If specified, the patient SSN will be included in PID.3.1 as a patient identifier 
 *        with the SSN Assigning Authority.  This is the preferred way of sending 
 *        the SSN according to the HL7 spec but it does not seem to be the
 *        one used routinely by other IHE systems.  The SSN will be sent in
 *        PID.19 whether this parameter is specified or not.
 * <li> DefaultRace - (String) optional:
 *        If this parameter is supplied it will be sent in PID.10 of the patient feed.
 *        Normally this will be a code for "Unknown" if it is supplied.
 * <li> DateTimeFormats - (PropertySet) optional:
 *        If the following values are supplied as valid Java SimpleDateFormat strings,
 *        they will be used rather than the default formats for HL7 times:
 *        <ul>
 *        <li> BirthDateTime - (String) optional: Format string for birth dates.  The
 *        default format is "yyyyMMdd".
 *        <li> VisitStartTime - (String) optional: Format string for patient visit start times.  
 *        The default format is "yyyyMMddHHmmssZ".
 *        </ul>
 * </ul>
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 22, 2005
 * @see com.misyshealthcare.connect.base.PatientBroker
 */
public class PatientIdentitySource extends HL7Actor implements
		IPatientConsumer {

	/* Logger for problems during SOAP exchanges */
  private static final Logger log = Logger.getLogger(PatientIdentitySource.class);

  private static int MessageId = 0;

  private IConnectionDescription connection = null;
  private IheAuditTrail auditTrail = null;
  
  /**
   * Construct a new patient identity source to send patient information to
   * the connection supplied.
   * 
   * @param connection The description of the connection to accept the patient identity feed
   */
	public PatientIdentitySource(IConnectionDescription connection, IheAuditTrail auditTrail) {
		super(connection, log, auditTrail);
		this.auditTrail = auditTrail;
		this.connection = connection;
	}

  /* (non-Javadoc)
	 * @see com.misyshealthcare.connect.base.IPatientConsumer#createPatient(com.misyshealthcare.connect.base.PatientDescriptor, com.misyshealthcare.connect.base.IPatientConsumer.CreationReason)
	 */
	public void createPatient(PatientDescriptor patient, CreationReason reason) throws PatientException {
		if (reason == CreationReason.INPATIENT_ADMIT) {
			handlePatientIdentityFeed(patient, "A01", new Date(), reason, null);
		} else if (reason == CreationReason.OUTPATIENT_REGISTER) {
			handlePatientIdentityFeed(patient, "A04", new Date(), reason, null);
		} else if (reason == CreationReason.INPATIENT_PREADMIT) {
			handlePatientIdentityFeed(patient, "A05", new Date(), reason, null);
		} else {
			throw new PatientException("Unexpected reason for creating a new patient '" + reason.toString() + "'");
		}
		if (auditTrail != null) auditTrail.recordCreated(new ParticipantObject(patient));
	}

	/* (non-Javadoc)
	 * @see com.misyshealthcare.connect.base.IPatientConsumer#updatePatient(com.misyshealthcare.connect.base.PatientDescriptor)
	 */
	public void updatePatient(PatientDescriptor patient) throws PatientException {
		handlePatientIdentityFeed(patient, "A08", new Date(), null, null);
		if (auditTrail != null) auditTrail.recordModified(new ParticipantObject(patient));
	}

	/* (non-Javadoc)
	 * @see com.misyshealthcare.connect.base.IPatientConsumer#mergePatients(com.misyshealthcare.connect.base.PatientDescriptor, com.misyshealthcare.connect.base.PatientDescriptor)
	 */
	public void mergePatients(PatientDescriptor patientMain, PatientDescriptor patientOld) throws PatientException {
		handlePatientIdentityFeed(patientMain, "A40", new Date(), null, patientOld);
		if (auditTrail != null) {
			auditTrail.recordModified(new ParticipantObject(patientMain));
			auditTrail.recordDeleted(new ParticipantObject(patientOld));
		}
	}
	
	/**
	 * Create an appropriate HL7 message for the required patient feed, send the
	 * message, and process the result to ensure that it was accepted.
	 * 
	 * @param patient The patient triggering the patient feed message
	 * @param event The triggering event, in HL7 terms (ie. "A01", "A04", etc.)
	 * @param eventTime The time the triggering event occurred
	 * @param reason The reason this event was triggered
	 * @param oldPatient An older version of this patient to be merged (if appropriate)
	 * @throws PatientException When the message cannot be sent or was rejected
	 */
	private void handlePatientIdentityFeed(PatientDescriptor patient, String event, Date eventTime, CreationReason reason, PatientDescriptor oldPatient) throws PatientException {
		// Get the local Patient ID Assigning Authority for this connection
		Identifier authority = null;
		try {
			authority = Configuration.getIdentifier(connection, "LocalAssigningAuthority", true);
		} catch (IheConfigurationException e1) {
			// Missing local assigning authority definition
			throwConnectionException("configuration", e1);
		}
		// Create a patient feed message of the appropriate type
		boolean okay = false;
		try {
			okay = sendPatientIdentityFeed(patient, event, eventTime, reason, authority, oldPatient);
		} catch (IheConfigurationException e) {
			// Bad or missing configuration information
			throwConnectionException("configuration", e);
		} catch (HL7Exception e) {
			// Bad HL7 translation
			throwPatientDataException(patient, "data", e);
		} catch (PatientIdentityException e) {
			// Missing patient ID
			throwPatientDataException(patient, "rejected", e);
		} catch (IOException e) {
			// Cannot communicate properly over the connection
			throwConnectionException("communication", e);
		}
		// If not okay, then something unexpected has occured
		if (!okay)
			throw new PatientException("Cannot send patient identity feed message");
	}
	
	/**
	 * Send an HL7 Patient Identify Feed message to this actor's connection.
	 * 
	 * @param patient The patient demographics to be sent
	 * @param event The message event type: "A04", "A08", etc.
	 * @param eventTime The time this patient event took place: typically now
	 * @param reason The reason this identity feed is being made
	 * @param authority The Misys Assigning Authority identity within this connections' affinity domain
	 * @param oldPatient The old version of this patient if this is a merge
	 * @return True if the consumer accepted this message
	 * @throws IheConfigurationException When this connection is not properly configured to encode messages
	 * @throws PatientException When required patient information is missing
	 * @throws HL7Exception When the patient information cannot be encoded properly into HL7
	 * @throws PatientIdentityException When the patient feed message is rejected
	 * @throws IOException When there is a problem communicating over the socket
	 */
  private boolean sendPatientIdentityFeed(PatientDescriptor patient, String event, Date eventTime, CreationReason reason, Identifier authority, PatientDescriptor oldPatient) throws IheConfigurationException, PatientException, HL7Exception, PatientIdentityException, IOException {
  	Message feedMessage = createHL7IdentityFeedMessage(patient, event, eventTime, reason, authority, oldPatient);
  	if (hasMesaLogger()) {
  		IMesaLogger mesaLogger = getMesaLogger();
  		mesaLogger.writeString("Sending Patient Feed to '" + connection.getDescription() + "' ...");
  		mesaLogger.writeHL7Message(feedMessage);
  	}
  	HL7Channel channel = new HL7Channel(connection);
  	Message reply = channel.sendMessage(feedMessage);
  	if (hasMesaLogger()) {
  		IMesaLogger mesaLogger = getMesaLogger();
  		mesaLogger.writeString("Received response ...");
  		mesaLogger.writeHL7Message(reply);
  	}
  	return processPatientIdentityFeedResponse(reply);
  }
  
  /**
   * Check the response to the patient identity feed to ensure that it was
   * a success.
   * 
   * @param response The response from the patient identity consumer
   * @return True if the feed was accepted
   * @throws PatientIdentityException When the feed was rejected
   */
	private boolean processPatientIdentityFeedResponse(Message response) throws PatientIdentityException {
		// Make sure the response is the right type of message
		ACK message = null;
		if (response instanceof ACK) {
			message = (ACK) response;
		} else {
			logHL7MessageError(message, "Unexpected response from Patient Identity consumer");
			throw new PatientIdentityException("Unexpected response from Patient Identity consumer \"" + connection.getDescription() + "\": " + response.toString());
		}
		// Check the MSA segment ...
		String status = message.getMSA().getAcknowledgementCode().getValue();
		if ((status == null) || (!status.equalsIgnoreCase("AA") && !status.equalsIgnoreCase("CA"))) {
			// The server has rejected our request, or generated an error
			String mtext = message.getMSA().getTextMessage().getValue();
			String code = message.getMSA().getErrorCondition().getIdentifier().getValue();
			String etext = message.getMSA().getErrorCondition().getText().getValue();
			String error = null;
			if (code != null) error = "(" + code + ") " + HL7v231.getErrorString(code);
			if (mtext != null) error = error + " - " + mtext;
			if (etext != null) error = " [" + etext + "]";
            if (error == null) {
               ERR err = message.getERR();
               if (err != null) {
                  // Message = err.getMessage().get;
                   try {
                       ELD eld = err.getErrorCodeAndLocation(0);
                       if (eld != null) {
                           CE ce = eld.getCodeIdentifyingError();
                           if (ce != null) {
                               ST errorcode = ce.getIdentifier();
                               if (errorcode != null) {
                                   error = "(" + errorcode.getValue() + ") " + HL7v231.getErrorString(errorcode.getValue());
                               }
                               ST text = ce.getText();
                               if (text != null) error = error + "-" + text.getValue();
                           }
                       }
                   } catch (HL7Exception e) { //do nothing if we cannot get anything from ERR.
                   }
               }
            }
            if (error == null) error ="Unspecified error";
			error = "Error response from Patient Identity consumer \"" + connection.getDescription() + "\": " + error;
			logHL7MessageError(message, error);
			throw new PatientIdentityException(error);
		}
		// Okay, we're good
		return true;
	}


  /**
   * Create an HL7 Patient Identity Feed message.
   * 
	 * @param patient The patient demographics to be sent
	 * @param event The message event type: "A04", "A08", etc.
	 * @param eventTime The time this patient event took place: typically now
	 * @param reason The reason this identity feed is being made
	 * @param authority The Misys Assigning Authority identity within this connections' affinity domain
	 * @param oldPatient The old version of this patient if this is a merge
   * @return The HL7 message to send
	 * @throws IheConfigurationException When this connection is not properly configured to encode messages
	 * @throws PatientException When required patient information is missing
	 * @throws HL7Exception When the patient information cannot be encoded properly into HL7
   */
	private Message createHL7IdentityFeedMessage(PatientDescriptor patient, String event, Date eventTime, CreationReason reason, Identifier authority, PatientDescriptor oldPatient) throws IheConfigurationException, PatientException, HL7Exception {
		String messageId = "PIF_" + MessageId++;
		// Get the relevant Visit for this patient
		Visit visit = null;
		List<Visit> visits = patient.getVisitList();
		if ((visits != null) && (visits.size() > 0)) {
			visit = visits.get(0);
		}
		// Get the relevent MRN for this patient
		PatientMRN mrn = null;
		List<PatientMRN> mrns = patient.getMrnList();
		if ((mrns != null) && (mrns.size() > 0)) {
			mrn = mrns.get(0);
		}
		// Create the right type of message
		if (oldPatient == null) {
			ADT_A01 message = new ADT_A01();
			// Populate the MSH segment
			HL7v231.populateMSH(message.getMSH(), "ADT", event, messageId, connection);
			// Populate the EVN segment
			populateEVN(message.getEVN(), event, eventTime);
			// Populate the PID segment
			populatePID(message.getPID(), patient, mrn, authority);
			// Populate the PV1 segment
            if(reason == null)
                    reason = CreationReason.INPATIENT_ADMIT;
            populatePV1(message.getPV1(), visit, reason);
/*
            if(reason != null)
                populatePV1(message.getPV1(), visit, reason);
*/
			return message;
		} else {
			ADT_A39 message = new ADT_A39();
            

            // Populate the MSH segment
			HL7v231.populateMSH(message.getMSH(), "ADT", event, messageId, connection);
			// Populate the EVN segment
			populateEVN(message.getEVN(), event, eventTime);
			// Populate the PID segment
			populatePID(message.getPIDPD1MRGPV1().getPID(), patient, mrn, authority);
			// Populate the PV1 segment
/*
            if(reason == null)
                    reason = CreationReason.INPATIENT_ADMIT;
            populatePV1(message2.getPV1(), visit, reason);
*/


            if(reason != null)
                populatePV1(message.getPIDPD1MRGPV1().getPV1(), visit, reason);

			// Populate the MRG segment
			populateMRG(message.getPIDPD1MRGPV1().getMRG(), oldPatient, authority);
			return message;
		}
	}

	/**
	 * Populate the EVN segment of the Patient Identity Feed message.
	 * <p>
	 * See IHE ITI-TF vol 2, section 3.8 and HL7 v2.3.1
	 * 
	 * @param evn The EVN segment of the message
	 * @param event The message event type: "A04", "A08", etc.
	 * @param eventTime The time this patient event took place: typically now
	 * @throws DataTypeException When the EVN values cannot be properly encoded into HL7
	 */
	private void populateEVN(EVN evn, String event, Date eventTime) throws DataTypeException, IheConfigurationException {
        // EVN-1
        evn.getEventTypeCode().setValue(event);
        // EVN-2, default to now
        String format = Configuration.getPropertySetValue(connection, "DateTimeFormats", "EventTimeFormat", false);
        log.warn("FORMAT : " + format);
        String recordedTimeString = HL7v231.formatDateTime(new Date(), format);
               // HL7v231.formatDateTime(new Date());
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(recordedTimeString);
        // EVN-6, event time, if known
        if (eventTime != null) {
            String eventTimeString = HL7v231.formatDateTime(eventTime, format);
            evn.getEventOccurred().getTimeOfAnEvent().setValue(eventTimeString);
        }
    }
	
	/**
	 * Populate the PID segment of the Patient Identity Feed message.
	 * <p>
	 * See IHE ITI-TF vol 2, section 3.8 and HL7 v2.3.1
	 *
	 * @param pid The PID segment to be populated
	 * @param patient The patient demographic data
	 * @param mrn The MRN this patient, if there is one
	 * @param authority The Misys Assigning Authority identifier for this affinity domain
	 * @throws IheConfigurationException When this connection is not properly configured to encode messages
	 * @throws PatientException When required patient information is missing
	 * @throws HL7Exception When the patient information cannot be encoded properly into HL7
	 */
	private void populatePID(PID pid, PatientDescriptor patient, PatientMRN mrn, Identifier authority) throws IheConfigurationException, PatientException, HL7Exception {
		// Get the patient identifier
		PatientID idGroup = patient.getPatientId();
		if (idGroup == null) 
			throwPatientException("Patient has no ID");
		String patientId = idGroup.getLocalUniqueId();
		if (patientId == null)
			throwPatientException("Patient has no Misys unique ID");
		// PID-2 - Deprecated ID, our authority makes this ID > 20 characters, the allowed limit
		//populateCX(pid.getPatientID(), patientId, authority);
		// PID-3 - Preferred ID (and SSN as well, see the HL7 v2.3.1 spec)
		populateCX(pid.getPatientIdentifierList(0), patientId, authority);
		if (patient.getSsn() != null) {
			// Look up the SSN oid.  If it isn't there, do not include SSN in this list
			Identifier ssnAuthority = Configuration.getIdentifier(connection, "SSNAssigningAuthority", false);
			if (ssnAuthority != null) {
				populateCX(pid.getPatientIdentifierList(1), patient.getSsn(), ssnAuthority);
			}
		}
		// PID-5 - Patient legal name
		XPN xpn = pid.getPatientName(0);
		populateXPN(xpn, patient, idGroup.getLocalUniqueId());
		// PID-6 - Mother's maiden name, we have only last name
		if (patient.getMotherMaidenName() == null) 
			throwPatientException("Patient '" + idGroup.getLocalUniqueId() + "' has no mother's maiden name.");
		xpn = pid.getMotherSMaidenName(0);
		xpn.getFamilyLastName().getFamilyName().setValue(patient.getMotherMaidenName());
		// PID-7 - Birth date
		if (patient.getBirthDateTime() == null) 
			throwPatientException("Patient '" + idGroup.getLocalUniqueId() + "' has no birth date.");
		String format = Configuration.getPropertySetValue(connection, "DateTimeFormats", "BirthDateTime", false);
		pid.getDateTimeOfBirth().getTimeOfAnEvent().setValue(HL7v231.formatDate(patient.getBirthDateTime(), format));
		// PID-8 - Gender
		String gender = "U";
		if (patient.getAdministrativeSex() == SexType.MALE) gender = "M";
		else if (patient.getAdministrativeSex() == SexType.FEMALE) gender = "F";
		else if (patient.getAdministrativeSex() == SexType.OTHER) gender = "O";
		pid.getSex().setValue(gender);
		// PID-10 - Race (supposed to be optional but required by MESA test 10512)
		String race = connection.getProperty("DefaultRace");
		if (race != null) pid.getRace(0).getIdentifier().setValue(race);
		// PID-11 - Addresses
		int i = 0;
		List<Address> addrs = patient.getAddressList();
		for(Address addr : addrs) {
			populateXAD(pid.getPatientAddress(i++), addr, patientId);
		}
		// PID-13 - Home phone
		i = 0;
		PhoneNumber phone = patient.getPhoneNumber(PhoneType.HOME);
		if (phone != null) populateXTN(pid.getPhoneNumberHome(i++), phone, patientId);
		phone = patient.getPhoneNumber(PhoneType.CELL);
		if (phone != null) populateXTN(pid.getPhoneNumberHome(i++), phone, patientId);
		phone = patient.getPhoneNumber(PhoneType.EMERGENCY);
		if (phone != null) populateXTN(pid.getPhoneNumberHome(i++), phone, patientId);
		// PID-14 - Work phone
		i = 0;
		phone = patient.getPhoneNumber(PhoneType.WORK);
		if (phone != null) populateXTN(pid.getPhoneNumberBusiness(i++), phone, patientId);
		phone = patient.getPhoneNumber(PhoneType.SERVICE);
		if (phone != null) populateXTN(pid.getPhoneNumberBusiness(i++), phone, patientId);
		phone = patient.getPhoneNumber(PhoneType.FAX);
		if (phone != null) populateXTN(pid.getPhoneNumberBusiness(i++), phone, patientId);
		// PID-18 - Patient Account Number (Optional)
		if (mrn != null) {
			String mrnNo = mrn.getMrn();
			if (mrnNo != null) {
				pid.getPatientAccountNumber().getID().setValue(clip(mrnNo, 20));
				// TODO - This assigning authority for the account number is incomplete but all that is available
				String mrnAuthority = mrn.getAssigningAuth();
				if (mrnAuthority != null)
					pid.getPatientAccountNumber().getAssigningAuthority().getNamespaceID().setValue(mrnAuthority);
			}
		}
		// PID-19 - SSN
		if (patient.getSsn() != null) 
			pid.getSSNNumberPatient().setValue(clip(patient.getSsn(), 16));
		// PID-20 - Driver's License
		if (patient.getDriverLicense() != null) 
			pid.getDriverSLicenseNumberPatient().getDriverSLicenseNumber().setValue(clip(patient.getDriverLicense(), 25));
		// Done
	}
	
	/**
	 * Populate the PV1 segment of the patient feed.
	 * <p>
	 * See IHE ITI-TF vol 2, section 3.8 and HL7 v2.3.1
	 *
	 * @param pv1 The PV1 segment to populate
	 * @param visit The visit that generated this patient feed
	 * @param reason The reason for this patient feed
	 * @throws IheConfigurationException When this connection is not properly configured to translate Patient Feed messages
	 * @throws HL7Exception When the visit cannot be encoded as a valid HL7 message
	 */
	private void populatePV1(PV1 pv1, Visit visit, CreationReason reason) throws IheConfigurationException, HL7Exception {
		// PV1-2 - This is a user defined field (ie. Affinity Domain specific)
		String code = Configuration.applyEnumMap(connection, reason, CreationReason.class, true);
		pv1.getPatientClass().setValue(clip(code, 1));
		// PV1-8 - Referring doctor (optional)
		if (visit != null) {
			List<Provider> providers = visit.getProviderList();
			if ((providers != null) && (providers.size() > 0)) {
				int i = 0;
				for (Provider provider: providers) {
					if ((provider.getProviderId() != null) || (provider.getProvNameFirst() != null) ||
							(provider.getProvNameMiddle() != null) || (provider.getProvNameLast() != null)) {
						XCN xcn = pv1.getReferringDoctor(i++);
						if (provider.getProviderId() != null) xcn.getIDNumber().setValue(provider.getProviderId());
						if (provider.getProvNameFirst() != null) xcn.getGivenName().setValue(provider.getProvNameFirst());
						if (provider.getProvNameMiddle() != null) xcn.getMiddleInitialOrName().setValue(provider.getProvNameMiddle());
						if (provider.getProvNameLast() != null) xcn.getFamilyLastName().getFamilyName().setValue(provider.getProvNameLast());
						if (provider.getProvNameTitle() != null) xcn.getPrefixEgDR().setValue(provider.getProvNameTitle());
						if (provider.getProvNameSuffix() != null) xcn.getSuffixEgJRorIII().setValue(provider.getProvNameSuffix());
					}
				}
			}
		}
		// PV1-19 - Visit number (optional)
		if (visit != null) {
			// TODO - This is not strictly correct.  It should be a number or an ID with an assigning authority
			String visitId = visit.getVisitId();
			if (visitId != null) pv1.getVisitNumber().getID().setValue(clip(visitId, 20));
		}
		// PV1-44 - Admit time (optional)
		if (visit != null) {
			if ((reason == CreationReason.OUTPATIENT_REGISTER) && (visit.getVisitStartTimestamp() != null)) {
				String format = Configuration.getPropertySetValue(connection, "DateTimeFormats", "VisitStartTime", false);
				String theTime = HL7v231.formatDateTime(visit.getVisitStartTimestamp(), format);
				pv1.getAdmitDateTime().getTimeOfAnEvent().setValue(theTime);
			}
		}
	}
	
	/**
	 * Populate the MRG segment of the patient feed if this is a merge
	 * message.
	 * <p>
	 * See IHE ITI-TF vol 2, section 3.8 and HL7 v2.3.1
	 *
	 * @param mrg The MRG segment to populate
	 * @param patient The patient demographic data for the old patient
	 * @param authority The Misys Assigning Authority identifier for this affinity domain
	 * @throws PatientException When the old patient is missing required information
	 * @throws IheConfigurationException When this connection is not properly configured to translate Patient Feed messages
	 * @throws HL7Exception When the visit cannot be encoded as a valid HL7 message
	 */
	private void populateMRG(MRG mrg, PatientDescriptor patient, Identifier authority) throws PatientException, IheConfigurationException, HL7Exception {
		// MRG-1 - The old patient identifier
		PatientID idGroup = patient.getPatientId();
		if (idGroup == null) 
			throwPatientException("Patient has no ID");
		String patientId = idGroup.getLocalUniqueId();
		if (patientId == null)
			throwPatientException("Patient has no Misys unique ID");
		populateCX(mrg.getPriorPatientIdentifierList(0), patientId, authority);
		// MRG-4 - The old patient ID, external version (?)
		   // I don't really know the difference between this and the patient ID above
		// MRG-7 - Prior patient name
		populateXPN(mrg.getPriorPatientName(0), patient, patientId);
	}
	
	/**
	 * Populate an XPN component with a patient name.
	 * 
	 * @param xpn The XPN component to populate
	 * @param patient The patient
	 * @param id The Misys Connect ID for the patient, for debugging only
	 * @throws PatientException If this patient has no name
	 * @throws DataTypeException When the name cannot be encoded into valid HL7
	 */
	private void populateXPN(XPN xpn, PatientDescriptor patient, String id) throws DataTypeException, PatientException {
		if ((patient.getNameFirst() == null) && (patient.getNameMiddle() == null) && (patient.getNameLast() == null))
			throwPatientException("Patient '" + id + "' has no name.");
		if (patient.getNameFirst() != null) xpn.getGivenName().setValue(patient.getNameFirst());
		if (patient.getNameMiddle() != null) xpn.getMiddleInitialOrName().setValue(patient.getNameMiddle());
		if (patient.getNameLast() != null) xpn.getFamilyLastName().getFamilyName().setValue(patient.getNameLast());
		if (patient.getNameTitle() != null) xpn.getPrefixEgDR().setValue(patient.getNameTitle());
		if (patient.getNameSuffix() != null) xpn.getSuffixEgJRorIII().setValue(patient.getNameSuffix());
		//xpn.getNameTypeCode().setValue("L");
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
		cx.getID().setValue(id);
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
	 * Populate an XAD component with an address.
	 * 
	 * @param xad The XAD component to populate
	 * @param address The address to get the data from
	 * @param patientId The patient Misys ID, used for debugging messages
	 * @throws DataTypeException When the component cannot be encoded in HL7
	 * @throws PatientException When the address to be encoded holds no data
	 */
	private void populateXAD(XAD xad, Address address, String patientId) throws DataTypeException, PatientException {
		boolean okay = false;
		// Fill in all the address parts
		if (address.getAddLine1() != null) {
			xad.getStreetAddress().setValue(address.getAddLine1());
			okay = true;
		}
		if (address.getAddLine2() != null) {
			xad.getOtherDesignation().setValue(address.getAddLine2());
			okay = true;
		}
		if (address.getAddCity() != null) {
			xad.getCity().setValue(address.getAddCity());
			okay = true;
		}
		if (address.getAddState() != null) {
			xad.getStateOrProvince().setValue(address.getAddState());
			okay = true;
		}
		if (address.getAddZip() != null) {
			xad.getZipOrPostalCode().setValue(address.getAddZip());
			okay = true;
		}
		if (address.getAddCountry() != null) {
			xad.getCountry().setValue(address.getAddCountry());
			okay = true;
		}
		// If the address is completely empty, throw an exception
		if (!okay) {
			throwPatientException("Patient '" + patientId + "' has an empty address.");
		}
		// The address type is optional
		if (address.getAddType() == AddressType.HOME) xad.getAddressType().setValue("H");
		if (address.getAddType() == AddressType.WORK) xad.getAddressType().setValue("O");
	}
	
	/**
	 * Populate an XTN component with a phone number.
	 * <p>
	 * This method uses a combined interpretation of the v2.3.1 and the v2.5 spec to
	 * require only digits in phone numbers and impose limits on the field lengths.
	 * 
	 * @param xtn The XTN component to populate
	 * @param phone The phone number to put into the component
	 * @param patientId The Misys ID for the patient, used for debugging messages
	 * @throws DataTypeException When the component cannot be encoded in HL7
	 * @throws PatientException When the phone number contains no data
	 */
	private void populateXTN(XTN xtn, PhoneNumber phone, String patientId) throws DataTypeException, PatientException {
		boolean okay = false;
		// First the separate pieces
		String country = null;
		if (phone.getCountryCode() != null) {
			country = clipNumber(phone.getCountryCode(), 3);
			xtn.getCountryCode().setValue(country);
			okay = true;
		}
		String area = null;
		if (phone.getAreaCode() != null) {
			area = clipNumber(phone.getAreaCode(), 5);
			xtn.getAreaCityCode().setValue(area);
			okay = true;
		}
		String number = null;
		if (phone.getNumber() != null) {
			number = clipNumber(phone.getNumber(), 9);
			xtn.getPhoneNumber().setValue(number);
			okay = true;
		}
		String extension = null;
		if (phone.getExtension() != null) {
			extension = clipNumber(phone.getExtension(), 5);
			xtn.getExtension().setValue(extension);
			okay = true;
		}
		if (phone.getNote() != null) xtn.getAnyText().setValue(phone.getNote());
		// If the phone number is completely empty, throw an exception
		if (!okay) {
			throwPatientException("Patient '" + patientId + "' has an empty phone number.");
		}
		// Next the telecommunications types (adapted fom our ENUM types)
		if (phone.getType() == PhoneType.HOME) {
			xtn.getTelecommunicationUseCode().setValue("PRN");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == PhoneType.WORK) {
			xtn.getTelecommunicationUseCode().setValue("WPN");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == PhoneType.CELL) {
			xtn.getTelecommunicationUseCode().setValue("PRN");
			xtn.getTelecommunicationEquipmentType().setValue("CP");
		} else if (phone.getType() == PhoneType.EMERGENCY) {
			xtn.getTelecommunicationUseCode().setValue("EMR");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == PhoneType.SERVICE) {
			xtn.getTelecommunicationUseCode().setValue("ASN");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == PhoneType.FAX) {
			xtn.getTelecommunicationUseCode().setValue("WPN");
			xtn.getTelecommunicationEquipmentType().setValue("FX");
		}
		// Finally, a formatted phone number if possible
		String formatted = HL7v231.formatPhoneNumber(country, area, number, extension, phone.getNote());
		if (formatted != null) xtn.get9999999X99999CAnyText().setValue(formatted);
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
	 * Throw a new patient exception and log it as well.
	 * 
	 * @param message
	 * @throws PatientException
	 */
	private void throwPatientException(String message) throws PatientException {
		log.error(message);
		throw new PatientException(message);
	}
	
	/**
	 * Throw an exception because the connection is not working correctly.
	 * 
	 * @param reason The reason for the exception
	 * @param e The reason for the exception
	 * @throws PatientException The exception being thrown
	 */
	private void throwConnectionException(String reason, Throwable e) throws PatientException {
		String message = null;
		if (reason.equals("configuration")) {
			message = "Connection \"" + connection.getDescription() + "\" is not properly configured to send patient identity feeds";
		} else {
			message = "Cannot communicate over connection \"" + connection.getDescription() + "\"";
		}
		if (e != null) {
			log.error(message, e);
			throw new PatientException(message, e);
		} else {
			log.error(message);
			throw new PatientException(message);			
		}
	}

	/**
	 * Throw an exception because the patient data is not correct.
	 * 
	 * @param patient The patient data
	 * @param e The reason for the exception
	 * @throws PatientException The exception being thrown
	 */
	private void throwPatientDataException(PatientDescriptor patient, String reason, Throwable e) throws PatientException {
		String name = null;
		if (patient != null) {
			PatientID id = patient.getPatientId();
			if (id != null) name = id.getLocalUniqueId();
		}
		String message = "Unexpected error";
		if (reason.equals("data")) {
			if (name == null) {
				message = "Patient has no Misys unique ID in patient identity feed";
			} else {
				message = "Patient with Misys ID '" + name + "' holds data that cannot be formatted for patient identity feed";
			}
		} else {
			message = "Patient feed with Misys ID '" + name + "' was rejected at the identity consumer \"" + connection.getDescription() + "\"";
		}
		if (e != null) {
			log.error(message, e);
			throw new PatientException(message, e);
		} else {
			log.error(message);
			throw new PatientException(message);			
		}
	}
	
	/**
	 * Get the custom mesa test interface for this actor.  The mesa test
	 * interface allows A04 messages to be sent with ID assigning authorities
	 * that are not in the configuration file.
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
	 * @version 2.0 - Dec 12, 2005
	 */
	public class MesaInterface {
		
		/**
		 * Send an A04 patient feed message using the supplied authority as the
		 * assigning authority for the patient ID.
		 * 
		 * @param patient The patient demographics to send
		 * @param authority The ID assigning authority.  This overrides the LocalAssigningAuthority included in the configuration
		 * @return True if the patient feed was accepted without error
		 * @throws IheConfigurationException When the connection is not configured properly
		 * @throws PatientException When there is a problem with the patient deomgraphics
		 * @throws HL7Exception When there is a problem encoding and decoding HL7 information
		 * @throws PatientIdentityException When the patient does not have a valid ID
		 * @throws IOException When there is a problem communicating with the patient identity consumer
		 */
		public boolean sendA04PatientFeed(PatientDescriptor patient, Identifier authority) throws IheConfigurationException, PatientException, HL7Exception, PatientIdentityException, IOException {
			return sendPatientIdentityFeed(patient, "A04", new Date(), CreationReason.OUTPATIENT_REGISTER, authority, null);
		}
		
	}

	
//	/**
//	 * A simple test program.
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// Create a sample connection
//		StandardConnectionDescription conn = new StandardConnectionDescription();
//		conn.setName("Test");
//		conn.addIdentifier("SendingApplication", new Identifier("Misys", "1.2.3", "ISO"));
//		conn.addIdentifier("SendingFacility", new Identifier("Misys", "1.2.3", "ISO"));
//		conn.addIdentifier("ReceivingApplication", new Identifier("MESA", "4.5.6", "ISO"));
//		conn.addIdentifier("ReceivingFacility", new Identifier("MESA", "4.5.6", "ISO"));
//		conn.addIdentifier("AssigningAuthority", new Identifier("Misys", "1.2.3", "ISO"));
//		try {
//			EnumMap emap = new EnumMap("IPatientConsumer$CreationReason");
//			emap.addEntry("OUTPATIENT_REGISTER", "O");
//			conn.addEnumMap(emap);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		// Create a sample patient
//		PatientID pid = new PatientID("98765");
//		pid.addId("Misys", "MI-1234");
//		PatientDescriptor pd = new PatientDescriptor(pid);
//		pd.setNameLast("EPSILON");
//		pd.setNameFirst("ELLIE");
//		pd.setMotherMaidenName("ZIMMER");
//		Calendar cal = GregorianCalendar.getInstance();
//		cal.set(1938, 2, 24);
//		pd.setBirthDateTime(cal.getTime());
//		pd.setAdministrativeSex(SexType.FEMALE);
//		Address address = new Address();
//		address.setAddType(AddressType.OTHER);
//		address.setAddLine1("1 PINETREE");
//		address.setAddCity("WEBSTER");
//		address.setAddState("MO");
//		address.setAddZip("63119");
//		pd.addAddress(address);
//		PhoneNumber phone = new PhoneNumber();
//		phone.setType(PhoneType.HOME);
//		phone.setNumber("555-1234");
//		phone.setAreaCode("314");
//		pd.addPhoneNumber(phone);
//		phone.setType(PhoneType.WORK);
//		phone.setNumber("555-4444");
//		pd.addPhoneNumber(phone);
//		pd.setSsn("444-70-9999");
//		pd.setDriverLicense("MODOTJ432");
//		
//		PatientIdentitySource pis = new PatientIdentitySource(conn);
//		try {
//			Message message = pis.createHL7IdentityFeedMessage(pd, "A04", new Date(), CreationReason.OUTPATIENT_REGISTER, Configuration.getIdentifier(conn, "AssigningAuthority", true));
//			System.out.println(PipeParser.encode(message, new EncodingCharacters('|', "^~\\&")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
