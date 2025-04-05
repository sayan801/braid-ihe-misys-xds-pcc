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
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.XdsClassCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsFormatCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.ihe.registry.XdsAuthor;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.ihe.registry.XdsSubmissionSet;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.util.OID;
import com.misyshealthcare.connect.util.Pair;


/**
 * 
 * @author Josh Flachsbart
 * @version 1.0 - Dec 10, 2006
 */
public abstract class XdDocumentSource extends XdsDocumentActor {

	private IConnectionDescription connection = null;
	private Logger log = null;
	
	XdDocumentSource(IConnectionDescription connection, Logger logger, IheAuditTrail auditTrail) {
		super(connection, logger, auditTrail);
		this.connection = connection;
		this.log = logger;
	}

	/**
	 * Create an XdsSubmissionSet metadata structure from a collection of Documents.  This consists primarily
	 * of copying information out of the documents and converting it to connection specific codes.
	 * 
	 * This function builds the standard submission set metadata.  Additional metadata elements, e.g. file-
	 * names for output to media, or intended recipient lists for direct transmission.
	 *
	 * @param documents The collection of documents being submitted
	 * @param description A text description of this collection of documents
	 * @param contentCode A code that describes the content of this collection of documents
	 * @return The submission set metadata
	 * @throws IheConfigurationException When the connection configuration is missing translation information
	 */
	XdsSubmissionSet createSubmissionSetMetadata(
			Collection<Document> documents, String description, XdsContentCode contentCode)
	throws IheConfigurationException 
	{
		XdsSubmissionSet submissionSet = new XdsSubmissionSet();
		// Add the comment
		submissionSet.setComments(description);
		// Translate the content type code into this affinity domain
		submissionSet.setContentTypeCode(Configuration.applyEnumMap(connection, contentCode, XdsContentCode.class, true));
		
        //Get unique list of AuthorDescriptors and intended recipients.  
		List<AuthorDescriptor> authors = new ArrayList<AuthorDescriptor>();
		for (Document document : documents) {
			List<AuthorDescriptor> docAuthors = document.getAuthorDescriptors();
			for (AuthorDescriptor docAuthor : docAuthors) {
				if (!authors.contains( docAuthor ) )
					authors.add( docAuthor );
			}
		}
		//Convert AuthorDescriptors to XdsAuthor
		for (AuthorDescriptor authorDescriptor : authors) {
			XdsAuthor author = getXdsAuthor( authorDescriptor );
			submissionSet.addAuthor(author);
		}

		// Make sure the source IDs are all the same
		String docSourceId = null;
		for (Document document: documents) {
			String sid = document.getSourceId();
			if (sid != null) {
				if (docSourceId == null) {
					docSourceId = sid;
				} else if (!sid.equalsIgnoreCase(docSourceId)) {
					log.warn("Document submission set holds documents from different sources: \"" + docSourceId + "\" and \"" + sid + "\"");
				}
			}
		}
		String sourceId = Configuration.applyStringMap(connection, docSourceId, "sourceId", true);
		submissionSet.setSourceId( sourceId );
		// Now check the patient IDs
		PatientID patientId = null;
		for (Document document: documents) {
			PatientID pId = document.getPatientId();
			if (pId == null) {
				throw new DocumentException("Document has no patient ID");
			} else if (patientId == null) {
				patientId = pId;
			} else if (!patientId.equals(pId)) {
				throw new DocumentException("Document submission holds documents for patients with different IDs");
			}
		}
		submissionSet.setPatientId(translatePatientId(connection, patientId));

		// Assign submission set Timestamp
		submissionSet.setSubmissionTime(HL7.toDTM(new Date()));
		// Assign a unique ID.  This ID has a specific form defined in ITI-V2
		submissionSet.setUniqueId( getSubmissionSetId(sourceId) );
		// Done
		return submissionSet;
	}
	
	private String getSubmissionSetId(String sourceId) {
		if (sourceId == null)
			throw new DocumentException("Invalid source Id");

		String ssetid = OID.getUID(OID.IDType.SUBMISSION_SET);
		return sourceId + "." + ssetid;
	}

	/**
	 * Create an XdsDocumentEntry metadata structure for a Document.  This consists primarily of
	 * converting internal Connect enum values and symbolic codes into connection specific codes.
	 * It also requires formatting a number of values into HL7-like message strings.
	 *
	 * @param document The document description to be encoded as metadata
	 * @return The metadata structure
	 * @throws IheConfigurationException When the connection configuration cannot translate required fields
	 */
	XdsDocumentEntry createDocumentMetadata(Document document) throws IheConfigurationException {
		XdsDocumentEntry entry = new XdsDocumentEntry();
		// ** Add the title
		entry.setTitle(document.getTitle());
		// ** Now the author information
		// The author intitution and names are simply encoded into HL7 strings.
		// The author role and specialty are converted from internal symbols to external codes, if appropriate
		List<AuthorDescriptor> authors = document.getAuthorDescriptors();
		for (AuthorDescriptor authorDescriptor : authors) {
			XdsAuthor author = getXdsAuthor( authorDescriptor );
			entry.addAuthor( author );
		}
		// ** The other basic metadata slots
		// These slot values are simply encoded as appropriate HL7 strings
		entry.setCreationTime(HL7.toDTM(document.getCreationTime()));
		entry.setLanguageCode(document.getLanguage());
		entry.setLegalAuthenticator(HL7.toXCN(document.getLegalAuthenticator()));
		entry.setMimeType(document.getMimeType());
		entry.setServiceStartTime(HL7.toDTM(document.getServiceStart()));
		entry.setServiceStopTime(HL7.toDTM(document.getServiceEnd()));
		// ** All the various classification codes
		// Three of these classifications are translated from internal ENUM types to connection Codes
		// Two of these classifications are translated fom internal symbols to connection Codes
		entry.setClassCode(Configuration.applyEnumMap(connection, document.getClassCode(), XdsClassCode.class, true));
		entry.setFormatCode(Configuration.applyEnumMap(connection, document.getFormatCode(), XdsFormatCode.class, true));
		entry.setHealthcareFacilityTypeCode(Configuration.applyStringMap(connection, document.getFacilityCode(), "facilityCode", false));
		entry.setPracticeSettingCode(Configuration.applyStringMap(connection, document.getPracticeCode(), "practiceCode", false));
		entry.setTypeCode(Configuration.applyEnumMap(connection, document.getTypeCode(), XdsTypeCode.class, true));
		// ** Event codes
		List<String> eventCodes = document.getEventCodes();
		for (String eventCode : eventCodes) {
			entry.addEventCode(Configuration.applyStringMap(connection, eventCode, "eventCode", false ));
		}
		// ** The patient ID
		// Select the patient ID for the connection's assigning authority
		PatientID patientId = document.getPatientId();
		entry.setPatientId(translatePatientId(connection, patientId));
		// ** Convert all the patient information
		// The source patient ID is the patient ID in the local assigning authority
		// The rest of the patient information is converted into HL7-like message strings
		if (patientId == null) throw new DocumentException("Document has no patient ID");
		Identifier local = Configuration.getIdentifier(connection, "LocalAssigningAuthority", true);
		entry.setSourcePatientId(HL7.toCX(patientId.getLocalUniqueId(), local));
		PatientDescriptor patient = document.getPatientDescriptor();
		if (patient == null) throw new DocumentException("Document has no patient descriptor");
		ArrayList<String> info = new ArrayList<String>();
		info.add("PID-3|" + entry.getSourcePatientId());
		info.add("PID-5|" + HL7.toXPN(patient));
		info.add("PID-8|" + HL7.toSex(patient.getAdministrativeSex()));
		if (patient.getBirthDateTime() != null)
			info.add("PID-7|" + HL7.toDT(patient.getBirthDateTime()));
		List<Address> addresses = patient.getAddressList();
		if ((addresses != null) && (addresses.size() > 0)) {
			Address preferred = patient.getAddress(AddressType.HOME);
			if (preferred == null) preferred = patient.getAddress(AddressType.WORK);
			if (preferred == null) preferred = addresses.get(0);
			info.add("PID-11|" + HL7.toXAD(preferred));
		}
		entry.setSourcePatientInfo(info);
		// ** Confidentiality code
		// Connect defines document confidentiality in SharedEnum.ConfidentialityCode.  So we map the
		// patient confidentiality into a connection Code
		// Pair<String(ConfidentialityCode), String(ConfidentialityDisplayName)>
		List<Pair> confidentialityStrings = new ArrayList<Pair>();
		List<SharedEnums.ConfidentialityCode> confidCodes = document.getConfidentialityCodes();
		if (confidCodes.size() == 0) {
			//Add the default Normal confidentialityCode if there is no ConfidentialityCode specified
			confidCodes.add(SharedEnums.ConfidentialityCode.Normal);
		}
		for (SharedEnums.ConfidentialityCode confidCode : confidCodes) {
			confidentialityStrings.add(new Pair(Configuration.applyEnumMap(connection, confidCode, SharedEnums.ConfidentialityCode.class, true), null));
		}
		entry.setConfidentialityCodes(confidentialityStrings);
		// ** Finally the unique ID
		// The format of this identifier depends on the type of document, so we just copy it
		entry.setUniqueId(document.getUniqueId());
		// Done
		return entry;
	}

	/**
	 * Convert authorDescriptor to XdsAuthor.
	 *
	 * @param authorDescriptor The AuthorDescriptor to be converted
	 * @return An XdsAuthor object
	 * @throws IheConfigurationException
	 */
	private XdsAuthor getXdsAuthor(AuthorDescriptor authorDescriptor) throws IheConfigurationException  {
		XdsAuthor author = new XdsAuthor();
		Provider authorPerson = authorDescriptor.getAuthorPerson();
		if (authorPerson != null) {
			author.setAuthorPerson( HL7.toXCN(authorPerson) );
		}
		List<String> institutes = authorDescriptor.getAuthorInstitutions();
		for (String authorInstitute : institutes) {
			author.addAuthorInstitution(HL7.toXON( authorInstitute ));
		}
		List<String> roles = authorDescriptor.getAuthorRoles();
		for (String authorRole : roles) {
			author.addAuthorRole(Configuration.applyStringMap(connection, authorRole, "authorRole", false));
		}
		List<String> specialities = authorDescriptor.getAuthorSpecialities();
		for (String authorSpeciality : specialities) {
			author.addAuthorSpeciality(Configuration.applyStringMap(connection, authorSpeciality, "authorSpecialty", false));
		}
        return author;
    }

}
