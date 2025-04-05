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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.Document.IheInterface;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.XdsClassCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsFormatCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.ihe.registry.XdsAuthor;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsResponse;
import com.misyshealthcare.connect.ihe.registry.XmlSourceResponse;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.util.Pair;

/**
 */
public class XdrDocumentConsumer extends XdsDocumentActor {

	private static final Logger log = Logger.getLogger(XdrDocumentConsumer.class.getName());

	private static final String RETURN_TYPE_OBJECT = "LeafClass";
	private static final String RETURN_TYPE_REFERENCE = "ObjectRef";

	private IConnectionDescription connection = null;
	private IheAuditTrail auditTrail = null;
	private XdrMessenger messenger = null;

	/**
	 * Construct a new document consumer that will get documents from the
	 * system described in the connection.
	 * 
	 * @param connection The system to get documents from
	 */
	public XdrDocumentConsumer(IConnectionDescription connection, IheAuditTrail auditTrail) {
		super(connection, log, auditTrail);
		this.connection = connection;
		this.auditTrail = auditTrail;
		this.messenger = new XdrMessenger(connection, false);
	}

	public List<Document> getAvailableDocuments(PatientID patientId) {
		// Make the query
		List<SOAPMessage> messages = retrieveAllAvailableSubmitMessages(patientId);
		List<Document> results = new ArrayList<Document>();
		for (SOAPMessage message: messages) {
			// Grab the document metadata returned
			List<XdsDocumentEntry> metadata = extractDocumentEntries(message);
			// Turn each metadata entry into a document
			if (metadata != null) {
				try {
					results.addAll(translateDocumentMetadata(metadata, message));
					// Let them know we got it.
//					SOAPMessage response = messenger.createRegistryResponse(message);
//					messenger.send(response, getMesaLogger(), "response", getOkayToSend());
				} catch (IheConfigurationException e) {
					throwBadConfigurationException("query", e);
				}
			}
		}
		// Log documents we found.
		if (auditTrail != null) {
			for (Document document: results) {
				ParticipantObject logobject = new ParticipantObject(document);
				auditTrail.recordAccessed(logobject);
			}
		}
		// Done
		return results;
	}
	
	private List<SOAPMessage> retrieveAllAvailableSubmitMessages(PatientID localPatientId) {
		// Make sure we have a valid Patient ID
		String patientId = null;
		try {
			patientId = XdsDocumentActor.translatePatientId(connection, localPatientId);
			// Get all the messages available...
			List<SOAPMessage> results = messenger.retrieveAllSoapMessages(null);
			// TODO filter based on patient id, and/or logged in user against intended recipient.
			// that should probably happen in the messenger so the response can be sent telling 
			// the sender that the message was received.
			return results;
		} catch (IheConfigurationException e) {
			throwBadConfigurationException("retrieve", e);
		}
		return null;
	}

	private List<XdsDocumentEntry> extractDocumentEntries(SOAPMessage message) {
		XdsResponse response = messenger.getXdrResponse(message);
		if (response == null) {
			return new Vector<XdsDocumentEntry>();
		} else if (response.isError()) {
			logSoapMessageError(message, response.getErrorMessage());
			return new Vector<XdsDocumentEntry>();
		} else if (response instanceof XmlSourceResponse) {
			// An appropriate response for this action
			return response.getQueryDocumentEntries();
		} else {
			logSoapMessageError(message, "Unexpected XDR source response type.");
			return new Vector<XdsDocumentEntry>();
		}
	}

	private List<Document> translateDocumentMetadata(List<XdsDocumentEntry> metadata, SOAPMessage message) throws IheConfigurationException {
		Vector<Document> documents = new Vector<Document>();
		for (XdsDocumentEntry entry: metadata) {
			Document document = translateSingleDocumentEntry(entry);
			documents.add(document);
		}
		return documents;
	}
}
