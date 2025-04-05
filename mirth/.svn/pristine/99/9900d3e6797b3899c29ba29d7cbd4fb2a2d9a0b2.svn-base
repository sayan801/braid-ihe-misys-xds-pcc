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

package com.misyshealthcare.connect.ihe.audit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.audit.ActiveParticipant;
import com.misyshealthcare.connect.base.audit.IAuditTrail;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ActiveParticipantIds;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.AuditEventIds;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.AuditSourceType;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.AuditTypeCodes;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.EventActionCode;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.NetworkAccessPointType;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ParticipantObjectIdTypeCode;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ParticipantObjectRoleCode;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ParticipantObjectTypeCode;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.SecurityAlertType;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.SuccessCode;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
//import com.misyshealthcare.connect.ihe.TestLogContext;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.net.StandardConnectionDescription;
import com.misyshealthcare.connect.util.LibraryConfig;

/**
 * The base implementation of an audit message class.  
 * 
 * Each IHE Actor requires its own implementation of this base class.
 * This base class, <em>can</em> be used independently, but that is not
 * the standard method, and will not be ATNA compliant. <p />
 * 
 * Implementations of the audit log class for specific IHE actors must be
 * instantiated when the actor starts, and stop must be called when the 
 * actor ends, and that instation must not be used again.  If the same actor 
 * restarts, a new instance must be made.  Calling stop will cause that 
 * specific instance of the audit trail to cease functioning. <p />
 * 
 * The following ATNA required messages are currently implemented by the given classes.
 * 
 * <dl>
 * <dt>Actor-start-stop
 * <dd>All actors implement this via the base class.  It is generally unnecessary to re implement it for the different actors.  Dicom Supp 95 "Application Activity"
 * <dt>Node-authentication-failure
 * <dd>Only for secure node TLS failure, not for user login failure.  Secure Node Actor.  Dicom Sup 95 "Security Alert"
 * <dt>Patient-record-event
 * <dd>Patient record created modified or accessed.  Document Consumer and Document Source.  Dicom Sup 95 "Patient Record"
 * <dt>Import
 * <dd>Generally used whenever a document is sent somewhere.  Document Source.  Dicom Sup 95 "Data Import"
 * <dt>Export
 * <dd>Patient record created recieved from somewhere.  Document Consumer.  Dicom Sup 95 "Data Export"
 * <dt>Procedure-record-event
 * <dd>Procedure record created accessed modified or deleted.  Document Consumer and Document Source.  Dicom Sup 95 "Procedure Record"
 * <dt>Security-administration
 * <dd>Change of security roles, user accounts, authentication ability, and other configuration changes.  In addition, user authentication, failure, and signoff.  Secure Node. Dicom Sup 95 "Security Alert"
 * </dl>
 *
 * @see AuditObjectFactory
 * @author Josh Flachsbart
 * @version 2.0 - Oct 27, 2005
 */
public class IheAuditTrail implements IAuditTrail {
	
	static Logger LOG = Logger.getLogger(IheAuditTrail.class);
	private String actorName;
	private List<IMessageTransmitter> messengers = null;

	/** Handles creation of a logging instance for a given actor.
	 * 
	 * Must be called by implementing sub-classes with the appropriate
	 * actor name.  <em>Can</em> be used directlly, but will not produce
	 * ATNA compliant messages.
	 * 
	 * @param actorName Name of the ATNA actor that this will generate an audit trail for.
	 */
	public IheAuditTrail(String actorName, Iterable<IConnectionDescription> repositories) {
		this.actorName = actorName;
		// Prepare audit streams:
		messengers = new ArrayList<IMessageTransmitter>();
		for (IConnectionDescription repository: repositories) {
			AuditTrailDescription description = new AuditTrailDescription(repository);
			String type = description.getType();
			if (type.equalsIgnoreCase(AuditTrailDescription.BSD)) {
				messengers.add(new BsdMessenger(description));
                //messengers.add(new Log4JMessenger(description));
            } else if (type.equalsIgnoreCase(AuditTrailDescription.LOG4J)) {
				messengers.add(new Log4JMessenger(description));
			} else if (type.equalsIgnoreCase(AuditTrailDescription.RELIABLE)) {
				messengers.add(new RSyslogMessenger(description));
			}
		}
	}
	
	/** Add the required elements and format the message in XML and send it.
	 * 
	 * To send a message, add additional information that is required by the
	 * message, then call format and log to do the rest.  It will add the 
	 * audit source id, and active participant id for each audit repository
	 * (since they might have different local info, e.g. if they are in 
	 * different RHIOs) and send the message.
	 * 
	 * @param factory
	 * @param requestor
	 */
	private void formatAndLog(IMessageTransmitter messenger, AuditObjectFactory factory, boolean requestor,
                              ActiveParticipantIds ourRole, AuditSourceType ... sourceTypes) throws JAXBException {
		AuditTrailDescription desc = messenger.getAuditTrailDescription();

        // add ourselves as an active participant:
		ActiveParticipant us = new ActiveParticipant(desc.getApplicationName()+"@" + desc.getName(), actorName, desc.getIp());
		us.setRequestor(requestor);
		us.role = ourRole; 
		us.setAccessPointTypeCode(NetworkAccessPointType.IPAddress);
		us.setAuditSourceType(AuditSourceType.WebServer);
		factory.addActiveParticipant(us);

        // add the source id:
        Set<AuditSourceType> auditSourceTypes = new HashSet<AuditSourceType>();
        if (sourceTypes != null) {
            for (AuditSourceType sourceType : sourceTypes) {
                if (sourceType != null)
                    auditSourceTypes.add( sourceType );
            }
        }                               
        auditSourceTypes.add( AuditSourceType.WebServer );
        factory.addAuditSourceId( desc.getEnterpriseSiteId(), desc.getAuditSourceId(), auditSourceTypes);

        // send the message.
		factory.sendAuditMessage();
	}

	/////**************   DATA HELPER FUNCTIONS   *********************

	public ActiveParticipant getUser() {
		ActiveParticipant doctor = null;
		LibraryConfig.ILogContext context = LibraryConfig.getInstance().getLogContext();

        if (context != null) {
			doctor = new ActiveParticipant();
			doctor.role = ActiveParticipantIds.Source;
			doctor.setRequestor(true);
			doctor.setUserId(context.getUserId());
			doctor.setAltUserId(context.getUserSystem());
			doctor.setUserName(context.getUserName());
			doctor.setAccessPointId(context.getClientAddress());
			doctor.setAccessPointTypeCode(NetworkAccessPointType.IPAddress);
			doctor.setAuditSourceType(AuditSourceType.EndUserGui);
		}
		return doctor;
	}
	
	public ActiveParticipant getMedia(ActiveParticipantIds role, String mediaDescription) {
		ActiveParticipant media = new ActiveParticipant();
		media.role = role;
		media.setRequestor(false);
		media.setUserId(mediaDescription);
		media.setAltUserId(null);
		media.setAccessPointId(null);
		media.setAuditSourceType(null);
		return media;
	}
	
	/////**************   DICOM MESSAGES   *********************

	/** DICOM Supp 95 message A.1.3.1 (p. 15): Application Activity <p />
	 * 
	 * Requires an event id with the parameters below and a single participant
	 * which is the application and a single audit source.  The participant and
	 * audit source are provided by the format and log function. <p />
	 * 
	 * Note that we could add the application launcher to this message.
	 * 
	 * @param message The type of application activity that it is.  Generally start or stop.
	 */
	protected void applicationActivity(AuditTypeCodes message) throws JAXBException {
		for (IMessageTransmitter messenger: messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			factory.setEventIdType(new EventId(AuditEventIds.ApplicationActivity, message, EventActionCode.Execute, SuccessCode.Success));
			formatAndLog(messenger, factory, false, ActiveParticipantIds.Application);
		}
	}
	
	/** DICOM Supp 95 message A.1.3.11 (p. 29): Patient Record <p />
	 * 
	 * This can not be used to record the use of DICOM SOP instances. <p />
	 * 
	 * Requires an event id with the parameters below.  Also requires the 
	 * doctor as an active participant, if known, and a description of the 
	 * documents accessed as a participant object.  This is the patient id
	 * and additional information about the document affected if available.
	 * The patient info is required. <p />
	 * 
	 * In addition, a single participant which is the application and a single 
	 * audit source.  The participant and audit source are provided by the 
	 * format and log function.
	 * 
	 * @param doctor Information about the doctor.  null if not available.
	 * @param patient needs to change.  This is the information about the patient.
	 * @param action What they did with the patient record.
	 */
	protected void patientRecord(ActiveParticipant doctor, ParticipantObject patient, EventActionCode action)
		throws JAXBException 
	{
		for (IMessageTransmitter messenger: messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			// Note, AuditTypeCode is not used for patient record events.
			factory.setEventIdType(new EventId(AuditEventIds.PatientRecord, (AuditTypeCodes) null, action, SuccessCode.Success));
			factory.addActiveParticipant(doctor);
			factory.addParticipantObject(patient);

            formatAndLog(messenger, factory, false, ActiveParticipantIds.Application, doctor.getAuditSourceType());
		}
	}
	
	/**
	 * The audit log for document record such as document submission, document retrieval and 
	 * registry stored query.
	 * 
	 * @param destination The description of the connection used to issue the document record.
	 * @param eventId The EventId of on the document record. 
	 * @param patient The patient participant object to be logged.
	 * @param logobject The document, submissionSet or query object to be logged.
	 * @throws JAXBException
	 */
	protected void documentRecord(ActiveParticipant destination, EventId eventId, ParticipantObject patient, ParticipantObject logobject)
		throws JAXBException 
	{
		for (IMessageTransmitter messenger: messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			factory.setEventIdType(eventId);
	
			if (destination == null)
				throw new JAXBException(
						"Destination not allowed to be null for document transactions.");
			destination.setRequestor(false);
			factory.addActiveParticipant(destination);
			
	        ActiveParticipant user = getUser();
	        factory.addActiveParticipant( user );
	
	        if (patient != null) {
				patient.typeCode = ParticipantObjectTypeCode.Person;
				patient.role = ParticipantObjectRoleCode.Patient;
				patient.idTypeCode = ParticipantObjectIdTypeCode.Patient;
		        factory.addParticipantObject(patient);
	        }			
			factory.addParticipantObject(logobject);
	
	        formatAndLog(messenger, factory, true, ActiveParticipantIds.Source, destination.getAuditSourceType(), user.getAuditSourceType());
		}
	}	
	
	
	/** DICOM Supp 95 message A.1.3.4 (p. 19): Data Export <p />
	 * 
	 * This should be used when data leaves control of the system. (E.g. xdm/xdr) <p />
	 * 
	 * Requires an event id with the parameters below.  Also requires the 
	 * doctor as an active participant, if known, and a description of the 
	 * documents accessed as a participant object.  This is the patient id
	 * and additional information about the document affected if available.
	 * The patient info is required. <p />
	 * 
	 * In addition, a single participant which is the application and a single 
	 * audit source.  The participant and audit source are provided by the 
	 * format and log function.
	 * 
	 * @param doctor Information about the doctor.  null if not available.
	 * @param patient needs to change.  This is the information about the patient.
	 * @param action What they did with the patient record.
	 */
	protected void dataExport(ActiveParticipant doctor, ActiveParticipant media, ParticipantObject patient)
		throws JAXBException 
	{
		for (IMessageTransmitter messenger: messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			// Note, AuditTypeCode is not used for data export.
			factory.setEventIdType(new EventId(AuditEventIds.Export, (AuditTypeCodes) null, EventActionCode.Read, SuccessCode.Success));
			factory.addActiveParticipant(media);
			factory.addActiveParticipant(doctor);
			factory.addParticipantObject(patient);

            formatAndLog(messenger, factory, false, ActiveParticipantIds.Application, doctor.getAuditSourceType());
		}
	}
	
	/** DICOM Supp 95 message A.1.3.4 (p. 19): Data Import <p />
	 * 
	 * This should be used when data was not in control of the system. (E.g. xdm/xdr) <p />
	 * 
	 * Requires an event id with the parameters below.  Also requires the 
	 * doctor as an active participant, if known, and a description of the 
	 * documents accessed as a participant object.  This is the patient id
	 * and additional information about the document affected if available.
	 * The patient info is required. <p />
	 * 
	 * In addition, a single participant which is the application and a single 
	 * audit source.  The participant and audit source are provided by the 
	 * format and log function.
	 * 
	 * @param doctor Information about the doctor.  null if not available.
	 * @param patient needs to change.  This is the information about the patient.
	 * @param action What they did with the patient record.
	 */
	protected void dataImport(ActiveParticipant doctor, ActiveParticipant media, ParticipantObject patient)
		throws JAXBException 
	{
		for (IMessageTransmitter messenger: messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			// Note, AuditTypeCode is not used for data export.
			factory.setEventIdType(new EventId(AuditEventIds.Import, (AuditTypeCodes) null, EventActionCode.Create, SuccessCode.Success));
			factory.addActiveParticipant(doctor);
			factory.addActiveParticipant(media);
			factory.addParticipantObject(patient);

			formatAndLog(messenger, factory, false, ActiveParticipantIds.Application, doctor.getAuditSourceType());
		}
	}
	
	/** DICOM Supp95 message A1.3.13 (p. 33): Query <p />
	 * 
 	 * @param user User authenticating.  Must not be null.
	 * @param isLogin True if user is logging in false if logging out.
	 * @param success Whether the loging was successful.
	 */
	protected void query(ActiveParticipant destination, ParticipantObject patient, ParticipantObject query) throws JAXBException {
		for (IMessageTransmitter messenger : messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			
			factory.setEventIdType(new EventId(AuditEventIds.Query,
					(AuditTypeCodes) null, EventActionCode.Execute,
					SuccessCode.Success));
			if (destination == null)
				throw new JAXBException(
						"Destination not allowed to be null for authentication logging.");
			destination.setRequestor(false);
			factory.addActiveParticipant(destination);
			
            ActiveParticipant user = getUser();
            factory.addActiveParticipant( user );
             
			if (query != null) {
				factory.addParticipantObject(query);
			}
			if (patient != null) {
				factory.addParticipantObject(patient);
			}
			formatAndLog(messenger, factory, true, ActiveParticipantIds.Source, destination.getAuditSourceType(), user.getAuditSourceType());
		}
	}
	
	/** DICOM Supp95 message A1.3.14 (p. 34): Security Alert <p />
	 * 
	 * This requires a single event id, the comprimised server if known, the
	 * reporting server (given by format and log) the identity of the reporting
	 * user (assumed to be machine only and therefore unknown) and the offending
	 * participants, if known.  We are generally the offending so we just leave
	 * ourselves out since we are already in there, however this is a spot for
	 * improvement in the future. <p />
	 * 
	 * In addition there appears to be a Participant object, but it is poorly defined
	 * and thus is not included here.  This should be changed in the future.
	 * 
	 * @param success Major error means that security has been comprimised.  
	 * Success means an informative alert only.  Others mean mitigation was possible.
	 * @param otherServer The comprimised server, if known.
	 */
	protected void securityAlert(SuccessCode success, ActiveParticipant otherServer) throws JAXBException {
		for (IMessageTransmitter messenger : messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);		
			factory.setEventIdType(new EventId(AuditEventIds.SecurityAlert, SecurityAlertType.NodeAuthentication, EventActionCode.Execute, success));
			if (otherServer != null) {
				otherServer.setRequestor(false);
				factory.addActiveParticipant(otherServer);
			}
			// TODO Find out if participant object is required and add it if it is.
			formatAndLog(messenger, factory, true, ActiveParticipantIds.Application, (otherServer!=null)?otherServer.getAuditSourceType() : null );
		}
	}
	
	/** DICOM Supp95 message A1.3.15 (p. 36): User Authentication <p />
	 * 
	 * This message records users logging into the system, as opposed to 
	 * security alerts which are for node's authenticating themselves.  The single
	 * event id is a login or logout, and whether it succeeded.  The user attempting
	 * to authenticate is a required active participant.  The enterprise wide
	 * authentication node (e.g. kerberos) is optional, but the actual authentication
	 * node is mandatory though included by format and log.
	 * 
 	 * @param user User authenticating.  Must not be null.
	 * @param isLogin True if user is logging in false if logging out.
	 * @param success Whether the loging was successful.
	 */
	protected void userAuthentication(ActiveParticipant user, AuditTypeCodes type, SuccessCode success) throws JAXBException {
		for (IMessageTransmitter messenger : messengers) {
			AuditObjectFactory factory = new AuditObjectFactory(messenger);
			factory.setEventIdType(new EventId(AuditEventIds.UserAuthentication, type, EventActionCode.Execute, success));
			if (user == null) user = getUser();
			if (user == null) throw new JAXBException("User not allowed to be null for authentication logging.");
			user.setRequestor(true);
			factory.addActiveParticipant(user);
			formatAndLog(messenger, factory, false, ActiveParticipantIds.Application, user.getAuditSourceType());
		}
	}	
	
	/////**************   ACTUAL MESSAGES   *********************
	
	/**	Sends actor start log message.  Must be called when actor is started. */
	public void start() {
		try {
			applicationActivity(AuditTypeCodes.ApplicationStart);
		} catch (JAXBException e) {
			LOG.error("Unable to log actor start for: " + actorName, e);
		}
	}
	
	/**	Sends actor stop log message.  Must be called when actor is finished. */
	public void stop() {
		try {
			applicationActivity(AuditTypeCodes.ApplicationStop);
		} catch (JAXBException e) {
			LOG.error("Unable to log actor stop for: " + actorName, e);
		}
	}
	
	/** Call when the node fails to authenticate itself with another node. 
	 * 
	 * Generally you don't log successes since there can be many of those.
	 * 
	 * Described in DICOM Supp95 A 1.3.14 as Security Alert.
	 * Described in ITI TF-2 p. 172 as Node-authentication-failure.
	 */
	public void nodeAuthenticationFailure(SuccessCode success, IConnectionDescription otherServer) {
		try {
			ActiveParticipant otherServerAP = new ActiveParticipant(otherServer);
			otherServerAP.role = ActiveParticipantIds.Destination;
			this.securityAlert(success, otherServerAP);
		} catch (JAXBException e) {
			LOG.error("Unable to log node authentication.", e);
		}
	}

	/** Call when a user authenticates himself. 
	 * 
	 * Described in DICOM Supp95 A 1.3.15 as User Authentication.
	 * Described in ITI TF-2 p. 172 as Node-authentication-failure.
	 */
	public void userLogin(SuccessCode success, ActiveParticipant user) {
		try {
			if (user == null) user = getUser();
			if (user != null) {
                user.role = ActiveParticipantIds.Source;
                user.setRequestor(true);
                this.userAuthentication(user, AuditTypeCodes.Login, success);
            }
		} catch (JAXBException e) {
			LOG.error("Unable to log user authentication.", e);
		}
	}

	/** Call when a user logs out. 
	 * 
	 * Described in DICOM Supp95 A 1.3.15 as User Authentication.
	 * Described in ITI TF-2 p. 172 as Node-authentication-failure.
	 */
	public void userLogout(SuccessCode success, ActiveParticipant user) {
		try {
			if (user == null) user = getUser();
			user.role = ActiveParticipantIds.Source;
			this.userAuthentication(user, AuditTypeCodes.Logout, success);
		} catch (JAXBException e) {
			LOG.error("Unable to log user authentication.", e);
		}
	}
	
	/** Call when a query is made.
	 * 
	 * Described in DICOM Supp95 A 1.3.13 as Query.
	 * Described in ITI TF-2 p. 173 as Query Information
	 * 
	 * @param destination The description of the connection used to issue the query.
	 * @param patient The patient participant object to be logged.
	 * @param isPixQuery <code>true</code> if this is a PIX query; <code>false</code> if it's a PDQ query 
	 */
	public void patientQueryIssued(IConnectionDescription destination, ParticipantObject patient, boolean isPixQuery) {
		try {
			patient.typeCode = ParticipantObjectTypeCode.SystemObject;
			patient.role = ParticipantObjectRoleCode.Query;
			if(isPixQuery)
				patient.idTypeCode = ParticipantObjectIdTypeCode.PIXQuery;
			else
				patient.idTypeCode = ParticipantObjectIdTypeCode.PDQQuery;
			 
			this.query(new ActiveParticipant(destination), patient, null);
		} catch (JAXBException e) {
			LOG.error("Unable to log patient query.", e);
		}
	}

	/** Call when a record is imported from external media.
	 * 
	 * Described in DICOM Supp95 A 1.3.5 as Data Import.
	 * 
	 * @param patient The patient or document participant object.
	 * @param mediaDesc A string describing the media, e.g. the source e-mail address, or "USB Media", etc...
	 */
	public void recordImported(ParticipantObject patient, String mediaDesc) {
		try {
			ActiveParticipant user = getUser();
			user.role = ActiveParticipantIds.Destination;
			ActiveParticipant media = getMedia(ActiveParticipantIds.SourceMedia, mediaDesc);
			patient.role = ParticipantObjectRoleCode.Patient;
			patient.typeCode = ParticipantObjectTypeCode.Person;
			
			this.dataImport(user, media, patient);
		} catch (JAXBException e) {
			LOG.error("Unable to log record import.", e);
		}
	}
	
	/** Call when a record is exported to external media.
	 * 
	 * Described in DICOM Supp95 A 1.3.4 as Data Export.
	 * 
	 * @param patient The patient or document participant object.
	 * @param mediaDesc A string describing the media, e.g. the destination e-mail address, or "USB Media", etc...
	 */
	public void recordExported(ParticipantObject patient, String mediaDesc) {
		try {
			ActiveParticipant user = getUser();
			ActiveParticipant media = getMedia(ActiveParticipantIds.DestinationMedia, mediaDesc);
			patient.role = ParticipantObjectRoleCode.Patient;
			patient.typeCode = ParticipantObjectTypeCode.Person;
			
			this.dataExport(user, media, patient);
		} catch (JAXBException e) {
			LOG.error("Unable to log record export.", e);
		}
	}
	
	/////**************   ILL DEFINED MESSAGES   *********************
	
	/** Call to log a document submission and registration event. 
	 * 
	 */
	public void recordCreated(ParticipantObject patient) {
		try {
			// Probably should fill in here and not rely on the defaults for the patient.
			patientRecord(getUser(), patient, EventActionCode.Create);
		} catch (JAXBException e) {
			LOG.error("Unable to log patient creation.", e);
		}
	}

	/** Call to log a patient record find and deletion event. 
	 * 
	 */
	public void recordDeleted(ParticipantObject patient) {
		try {
			// Probably should fill in here and not rely on the defaults for the patient.
			patientRecord(getUser(), patient, EventActionCode.Delete);
		} catch (JAXBException e) {
			LOG.error("Unable to log patient removal.", e);
		}
	}

	/** Call to log a patient record find and modification event. 
	 */
	public void recordModified(ParticipantObject patient) {
		try {
			// Probably should fill in here and not rely on the defaults for the patient.
			patientRecord(getUser(), patient, EventActionCode.Update);
		} catch (JAXBException e) {
			LOG.error("Unable to log patient modification.", e);
		}
	}

	/** Call to log a request to view a patient record. 
	 * 
	 */
	public void recordAccessed(ParticipantObject patient) {
		try {
			// Probably should fill in here and not rely on the defaults for the patient.
			patientRecord(getUser(), patient, EventActionCode.Read);
		} catch (JAXBException e) {
			LOG.error("Unable to log patient retrieval.", e);
		}
	}

	/**
	 * Call when documents are submitted.
	 * 
	 * @param destination The description of the connection used to issue the document submission.
	 * @param patient The patient participant object to be logged.
	 * @param submissionSet The submissionSet pariticpant object to be logged.
	 */
	public void documentSubmitted(IConnectionDescription destination, ParticipantObject patient, ParticipantObject submissionSet, boolean isXdsb) {
		try {
			AuditTypeCodes auditTypeCode = AuditTypeCodes.ProvideAndRegisterDocumentSet;
			if (isXdsb) auditTypeCode = AuditTypeCodes.ProvideAndRegisterDocumentSet_b;
				
			EventId eventId = new EventId(AuditEventIds.Export, auditTypeCode, EventActionCode.Read, SuccessCode.Success);
			submissionSet.typeCode = ParticipantObjectTypeCode.SystemObject;
	        submissionSet.role = ParticipantObjectRoleCode.Job; 
	        submissionSet.idTypeCode = ParticipantObjectIdTypeCode.SubmissionSet; 
			this.documentRecord(new ActiveParticipant(destination), eventId, patient, submissionSet);
		} catch (JAXBException e) {
			LOG.error("Unable to log document submission.", e);
		}
	}	
	/**
	 * Call when a document retrieve is made.
	 * 
	 * @param destination The description of the connection used to issue the document retrieval.
	 * @param patient The patient participant object to be logged.
	 * @param document The document pariticpant object to be logged.
	 */
	public void documentRetrieved(IConnectionDescription destination, ParticipantObject patient, ParticipantObject document, boolean isRetrieveDocumentSet) {
		try {
			AuditTypeCodes typeCode = AuditTypeCodes.RetrieveDocument;
			if (isRetrieveDocumentSet)
				typeCode = AuditTypeCodes.RetrieveDocumentSet;
			
			EventId eventId = new EventId(AuditEventIds.Import, typeCode, EventActionCode.Read, SuccessCode.Success);
			document.typeCode = ParticipantObjectTypeCode.SystemObject;
			document.role = ParticipantObjectRoleCode.Report; 
			document.idTypeCode = ParticipantObjectIdTypeCode.ReportNumber; 
			this.documentRecord(new ActiveParticipant(destination), eventId, patient, document);
		} catch (JAXBException e) {
			LOG.error("Unable to log document retrieval.", e);
		}
	}	
	/** Call when a registry stored query is made.
	 * 
	 * Described in ITI stored query
	 * 
	 * @param destination The description of the connection used to issue the query.
	 * @param patient The patient participant object to be logged.
	 * @param query The query pariticpant object to be logged.
	 */	
	public void documentStoredQuery(IConnectionDescription destination, ParticipantObject patient, ParticipantObject query) {
		try {
			EventId eventId = new EventId(AuditEventIds.Query, AuditTypeCodes.RegistryStoredQuery, EventActionCode.Execute, SuccessCode.Success);

			query.typeCode = ParticipantObjectTypeCode.SystemObject;
			query.role = ParticipantObjectRoleCode.Query;
			query.idTypeCode = ParticipantObjectIdTypeCode.RegistryStoredQuery;				 			
			
			this.documentRecord(new ActiveParticipant(destination), eventId, patient, query);
		} catch (JAXBException e) {
			LOG.error("Unable to log document stored query.", e);
		}
	}	
			
	/////**************   TESTING MESSAGES   *********************
		
	
	public static void main(String[] args) {
		// User
//		LogContext context = new LogContext();
//		context.setClientAddress("10.0.1.101");
//		context.setUserId("jones@sunroom.hosp.org");
//		context.setUserName("Dr. Jones");
//		LogManager.setLogContext(context);
//		LibraryConfig.getInstance().setLogContext(new TestLogContext());
		
		// Other Server
		StandardConnectionDescription otherServer = new StandardConnectionDescription();
		otherServer.setHostname("kitchen.hosp.org");
		otherServer.setName("OtherServer");
		// Patient
		PatientID pId = new PatientID();
		Identifier aa = new Identifier("Test", "1.2.3.4.5", "ISO");
		aa.addPatientId(pId, "misys-id-02344321183");
		PatientDescriptor pD = new PatientDescriptor(pId);
		pD.setNameFirst("Susan");
		pD.setNameLast("Formaldehyde");
		ParticipantObject patient = new ParticipantObject(pD);
		// Document
		Document doc = new Document();
		doc.setPatientDescriptor(pD);
		doc.setUniqueId("My Big Unique Id");
		ParticipantObject patientdocument =  new ParticipantObject(doc);
		//ParticipantObject patient = new ParticipantObject("Susan Formaldehyde", "misys-id-02344321183");
		// Repositories
		ConnectionFactory.loadConnectionDescriptionsFromFile("J:\\workspace\\connect_refactor_connect\\src\\connectathon\\AuditRepositoryConnections.xml");
		ArrayList<IConnectionDescription> repositories = new ArrayList<IConnectionDescription>();
		repositories.add(ConnectionFactory.getConnectionDescription("log4j_audittrail"));
		//repositories.add(new AuditTrailDescription("MISYS", "hosp.misyshealthcare.com", AuditTrailDescription.LOG4J, "10.0.1.101", "Sun room", "Big Hospital"));

		IheAuditTrail log = new IheAuditTrail("Fake Actor", repositories);
		log.start();
		log.nodeAuthenticationFailure(SuccessCode.MinorFailure, otherServer);
		log.userLogin(SuccessCode.MinorFailure, log.getUser());
		log.userLogin(SuccessCode.Success, log.getUser());
		log.patientQueryIssued(otherServer, patient, true);
		log.recordCreated(patientdocument);
		log.recordDeleted(patientdocument);
		log.recordModified(patientdocument);
		log.recordAccessed(patient);
		log.recordAccessed(patientdocument);
		log.recordImported(patient, "USB Media");
		log.recordExported(patient, "USB Media");
		log.userLogout(SuccessCode.Success, log.getUser());
		log.stop();
	}
}
