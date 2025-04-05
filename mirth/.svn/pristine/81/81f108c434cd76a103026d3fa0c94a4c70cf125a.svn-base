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

import java.util.*;
import javax.xml.soap.*;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.*;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.*;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.PropertySet;


/**
 * This class implements the IHE XDR Document Source Actor. It also implements
 * the com.misyshealthcare.connect.base.IDocumentConsumer interface so that it
 * can be used as document consumer by the DocumentBroker.
 * <p>
 * This class constructs and sends ebMS XML messages in SOAP wrappers as
 * required by the IHE offlineProvide and Register Document Set Transation
 * (ITI-15). See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.15
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.14 (Metadata
 * format)
 * <li> OASIS/ebXML Messaging Services v2.0, April 2002
 * </ul>
 * <p>
 * Note that the first encapsulated message it sends is the XDS required ebRIM
 * message, and as such requires the same connection configuration parameters:
 * <ul>
 * <li> AssigningAuthority (Identifier) required: Specifies the Assigning
 * Authority used by this registry/repository. It is used to encode the
 * appropriate Patient ID for a document submission.
 * <li> LocalAssigningAuthority (Identifier) required: Specifies the Local
 * Assigning Authority used by this actor. It is used to encode the Local Unique
 * ID for a patient in the submission set.
 * <li> XdsClassCode (EnumMap) required: This EnumMap describes the mapping from
 * internal Misys Connect XdsClassCode enum codes to class code values
 * appropriate for this registry/repository. This enum map can, and should be
 * shared with the corresponding Xds Document Consumer.
 * <li> XdsFormatCode (EnumMap) required: This EnumMap describes the mapping
 * from internal Misys Connect XdsFormatCode enum codes to format code values
 * appropriate for this registry/repository. This enum map can, and should be
 * shared with the corresponding Xds Document Consumer.
 * <li> XdsTypeCode (EnumMap) required: This EnumMap describes the mapping from
 * internal Misys Connect XdsTypeCode enum codes to type code values appropriate
 * for this registry/repository. This enum map can, and should be shared with
 * the corresponding Xds Document Consumer.
 * <li> XdsContentCode (EnumMap) required: This EnumMap describes the mapping
 * from internal Misys Connect XdsContentCode enum codes to type code values
 * appropriate for this registry/repository. This enum map can, and should be
 * shared with the corresponding Xds Document Consumer.
 * <li> authorRole (StringMap) optional: This StringMap describes a mapping from
 * internal Misys Connect author role values to author role code values
 * appropriate for this registry/repository. This string map can, and should be
 * shared with the corresponding Xds Document Consumer. If this string map is
 * not supplied, author role codes will be written untranslated into the
 * document metadata.
 * <li> authorSpecialty (StringMap) optional: This StringMap describes a mapping
 * from internal Misys Connect author specialty values to author specialty code
 * values appropriate for this registry/repository. This string map can, and
 * should be shared with the corresponding Xds Document Consumer. If this string
 * map is not supplied, author specialty codes will be written untranslated into
 * the document metadata.
 * <li> facilityCode (StringMap) optional: This StringMap describes a mapping
 * from internal Misys Connect facility code values to healthcare facility code
 * values appropriate for this registry/repository. This string map can, and
 * should be shared with the corresponding Xds Document Consumer. If this map is
 * not supplied, values will be taken as their own codes.
 * <li> practiceCode (StringMap) optional: This StringMap describes a mapping
 * from internal Misys Connect practice code values to practice setting code
 * values appropriate for this registry/repository. This string map can, and
 * should be shared with the corresponding Xds Document Consumer. If this map is
 * not supplied, values will be taken as their own codes.
 * <li> confidentialityCode (StringMap) required: This StringMap describes a
 * mapping from internal Misys Connect confidentiality code values to
 * confidentiality code values appropriate for this registry/repository. This
 * string map can, and should be shared with the corresponding Xds Document
 * Consumer.
 * <li> sourceId {StringMap} required: This StringMap describes a mapping from
 * internal Misys Connect document source code values to source code code values
 * appropriate for this registry/repository. This string map can, and should be
 * shared with the corresponding Xds Document Consumer.
 * <li> contentTypeCode (CodeMap) required: This CodeMap includes all of the
 * ebRS registry codes associated with a ContentType code. The mapping from
 * Misys XdsContentCode enum values to registry ContentType codes is captured in
 * the XdsContentCode enum map. The 'contentTypeCode' CodeMap includes
 * additional informaton about the registry ContentType codes required by the
 * registry. This code map is often supplied by the registry.
 * <li> classCode (CodeMap) required: This CodeMap includes all of the ebRS
 * registry codes associated with a Class code. The mapping from Misys
 * XdsClassCode enum values to registry Class codes is captured in the
 * XdsClassCode enum map. The 'classCode' CodeMap includes additional informaton
 * about the registry Class codes required by the registry. This code map is
 * often supplied by the registry.
 * <li> formatCode (CodeMap) required: This CodeMap includes all of the ebRS
 * registry codes associated with a Format code. The mapping from Misys
 * XdsFormatCode enum values to registry Format codes is captured in the
 * XdsFormatCode enum map. The 'formatCode' CodeMap includes additional
 * informaton about the registry Format codes required by the registry. This
 * code map is often supplied by the registry.
 * <li> practiceSettingCode (CodeMap) required: This CodeMap includes all of the
 * ebRS registry codes associated with a Practice Setting code. The mapping from
 * Misys practiceCode values to registry PracticeSetting codes is captured in
 * the practiceCode string map. The 'practiceSettingCode' CodeMap includes
 * additional informaton about the registry PracticeSetting codes required by
 * the registry. This code map is often supplied by the registry.
 * <li> healthcareFacilityTypeCode (CodeMap) required: This CodeMap includes all
 * of the ebRS registry codes associated with a Healthcare Facility Type code.
 * The mapping from Misys facilityCode values to registry HealthcareFacilityType
 * codes is captured in the facilityCode string map. The
 * 'healthcareFacilityTypeCode' CodeMap includes additional informaton about the
 * registry HealthcareFacilityType codes required by the registry. This code map
 * is often supplied by the registry.
 * <li> typeCode (CodeMap) required: This CodeMap includes all of the ebRS
 * registry codes associated with a Type code. The mapping from Misys
 * XdsTypeCode enum values to registry Type codes is captured in the XdsTypeCode
 * enum map. The 'typeCode' CodeMap includes additional informaton about the
 * registry Type codes required by the registry. This code map is often supplied
 * by the registry.
 * <li> confidentialityCode (CodeMap) required: This CodeMap includes all of the
 * ebRS registry codes associated with a Confidentiality code. The mapping from
 * Misys confidentialityCode values to registry Confidentiality codes is
 * captured in the confidentialityCode string map. The 'confidentialityCode'
 * CodeMap includes additional informaton about the registry Confidentiality
 * codes required by the registry. This code map is often supplied by the
 * registry.
 * <li> eventCode (CodeMap) optional: This CodeMap includes all of the ebRS
 * registry codes associated with an Event code. Misys Connect does not
 * currently use Event codes so this code map may be left out.
 * </ul>
 * 
 * @author Jim Firby, Josh Flachsbart
 * @version 1.0 - Nov 20, 2006
 * @see com.misyshealthcare.connect.base.IDocumentConsumer
 * @see com.misyshealthcare.connect.base.DocumentBroker
 * @see com.misyshealthcare.connect.base.Document
 * @see com.misyshealthcare.connect.base.IDocumentContents
 */
public class XdrDocumentSource extends XdDocumentSource { // TODO make implement something else? implements IDocumentConsumer {

	private static final Logger log = Logger.getLogger(XdsDocumentConsumer.class);

//	private IConnectionDescription connection = null;
//	private XdsDocumentConsumer consumer = null;
//	private IheAuditTrail auditTrail = null;
	private XdrMessenger messenger = null;
	// TODO This is only here for the Connectathon, in real 
	private Map<String, List<XdrIntendedRecipient>> allRecipientGroups = new HashMap();

	public Map<String, List<XdrIntendedRecipient>> getIntendedRecipients() {
		return allRecipientGroups;
	}

	/**
	 * Create a new document source that will send documents to the system
	 * described by the connection.
	 * 
	 * @param connection
	 *            The system to receive new documents
	 */
	public XdrDocumentSource(IConnectionDescription connection,
			IheAuditTrail auditTrail) {
		this(connection, null, auditTrail);
	}

	/**
	 * Create a new document source that will send documents to the system
	 * described by the connection and will allow document replacements that
	 * require querying the registry.
	 * 
	 * TODO figure out if we really want this... might be useful for handling
	 * the responses from the consumer.
	 * 
	 * @param connection
	 *            The system to receive new documents
	 * @param queryConnection
	 *            The system to return document registry IDs when replacing
	 */
	public XdrDocumentSource(IConnectionDescription connection,
			IConnectionDescription queryConnection, IheAuditTrail auditTrail) 
	{
		super(queryConnection, log, auditTrail);
//		this.connection = connection;
//		this.auditTrail = auditTrail;
		messenger = new XdrMessenger(queryConnection, false);
		int maxIrSets = 10;
		String irn = "IntendedRecipients";
		for (int irSet = 1; irSet <= maxIrSets; irSet++) {
			PropertySet irs = queryConnection.getPropertySet(irn + Integer.toString(irSet));
			if (irs == null) break;
			String name = irs.getValue("Name");
			String rn = "Recipient";
			int rnum = 1;
			String recipient = irs.getValue(rn + Integer.toString(rnum));
			List<XdrIntendedRecipient> intendedRecipients = new ArrayList();
			while (recipient != null) {
				intendedRecipients.add(new XdrIntendedRecipient(recipient));
				rnum++;
				recipient = irs.getValue(rn + Integer.toString(rnum));
			}
			getIntendedRecipients().put(name, intendedRecipients);
		}
//		if (queryConnection != null) {
//			consumer = new XdsDocumentConsumer(queryConnection, null);
//		}
	}

	/**
	 * The submitDocument method defined in the IDocumentConsumer interface.
	 * 
	 * @see com.misyshealthcare.connect.base.IDocumentConsumer#submitDocument(Document,
	 *      String, XdsContentCode)
	 */
	public void submitDocument(Document document, String description,
			XdsContentCode contentCode,
			List<XdrIntendedRecipient> intendedRecipients)
			throws DocumentException 
	{
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(document);
		submitDocuments(documents, description, contentCode, intendedRecipients);
	}

	/**
	 * The submitDocuments method defined in the IDocumentConsumer interface.
	 * 
	 * @see com.misyshealthcare.connect.base.IDocumentConsumer#submitDocuments
	 *      (Collection<Document>, String, XdsContentCode)
	 */
	public void submitDocuments(Collection<Document> documents,
			String description, XdsContentCode contentCode,
			List<XdrIntendedRecipient> intendedRecipients)
			throws DocumentException 
	{
		submitDocumentSet(documents, description, contentCode,	intendedRecipients);
	}

	/**
	 * Submit a set of documents to the repository.
	 * 
	 * @param documents
	 *            The documents to be submitted
	 * @param description
	 *            A description of these documents
	 * @param contentCode
	 *            A code describing the contents of these documents
	 * @throws DocumentException
	 *             When there is a problem with the submission
	 */
	private void submitDocumentSet(Collection<Document> documents,
			String description, XdsContentCode contentCode,
			List<XdrIntendedRecipient> intendedRecipients)
			throws DocumentException 
	{
		// Placeholders for the submit metadata
		XdsSubmissionSet submissionSet = null;
		ArrayList<XdsDocumentEntry> documentEntries = new ArrayList<XdsDocumentEntry>();
		// Create the submission set metadata
		try {
			submissionSet = createSubmissionSetMetadata(documents, description,
					contentCode);
			// Add the intended recipients (for offline messaging only)
			submissionSet.setIntendedRecipients(intendedRecipients);
		} catch (IheConfigurationException e) {
			throwBadConfigurationException("accept", e);
		}
		// Create metadata for each document
		try {
			int i = 1;
			for (Document document : documents) {
				XdsDocumentEntry entry = createDocumentMetadata(document);
				if (entry != null) {
					// Create a metadata ID for this document
					entry.setEntryUuid("doc" + i++);
					// Add the actual content
					entry.setContent(document.getContents());
					documentEntries.add(entry);
				}
			}
		} catch (IheConfigurationException e) {
			throwBadConfigurationException("accept", e);
		}
		SOAPMessage xdrSubmission = messenger.createSubmitObjectsRequest(submissionSet, documentEntries);
		messenger.send(xdrSubmission, getMesaLogger(), "submission", getOkayToSend());
		// TODO XDR offline mode has no immediate response.... need to deal with
		// it in at some later point? Just ignore it?
	}
}
