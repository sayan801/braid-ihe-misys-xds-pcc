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
package com.misyshealthcare.connect.doc.ccd.mapping;

import com.misyshealthcare.connect.base.demographicdata.xsd.Address;
import com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber;
import com.misyshealthcare.connect.base.xsd.AddressType;
import com.misyshealthcare.connect.base.xsd.ConfidentialityCode;
import com.misyshealthcare.connect.base.xsd.MimeTypes;
import com.misyshealthcare.connect.base.xsd.PatientContactType;
import com.misyshealthcare.connect.base.xsd.PhoneType;
import com.misyshealthcare.connect.base.xsd.SexType;
import com.misyshealthcare.connect.base.xsd.XdsClassCode;
import com.misyshealthcare.connect.base.xsd.XdsFormatCode;
import com.misyshealthcare.connect.base.xsd.XdsTypeCode;
import com.misyshealthcare.connect.doc.ccd.xsd.Author;
import com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice;
import com.misyshealthcare.connect.doc.ccd.xsd.CodeSystem;
import com.misyshealthcare.connect.doc.ccd.xsd.EffectiveTime;
import com.misyshealthcare.connect.doc.ccd.xsd.Id;
import com.misyshealthcare.connect.doc.ccd.xsd.MetaData;
import com.misyshealthcare.connect.doc.ccd.xsd.Organization;
import com.misyshealthcare.connect.doc.ccd.xsd.Participant;
import com.misyshealthcare.connect.doc.ccd.xsd.PatientContact;
import com.misyshealthcare.connect.doc.ccd.xsd.Performer;
import com.misyshealthcare.connect.doc.ccd.xsd.PersonName;
import com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo;

/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   This class is used to Transfer MetaData object from manipulation layer 
 * 							to webservice response data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 13, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class TypeTransformerResMetaData {
	
	/**
	 * Convert MetaData into the xsd package's MetaData
	 * @param metadata
	 * @return <code>MetaData</code>
	 */
	public static MetaData convertMetaData(
			com.misyshealthcare.connect.doc.ccd.MetaData metadata) {
		if(metadata == null) return null;
		
		MetaData wsMetadata = new MetaData();
		wsMetadata.setAuthors(TypeTransformerResMetaData.convertAuthors(metadata.getAuthors()));
		wsMetadata.setComment(metadata.getComment());
		wsMetadata.setConfidentialityCodes(TypeTransformerResMetaData.convertConfidentialityCodes(metadata.getConfidentialityCodes()));
		wsMetadata.setCreationTime(metadata.getCreationTime());
		wsMetadata.setClassCode(TypeTransformerResMetaData.convertClassCode(metadata.getClassCode()));
		wsMetadata.setComment(metadata.getComment());
		wsMetadata.setFacilityTypeCode(metadata.getFacilityTypeCode());
		wsMetadata.setLanguageCode(metadata.getLanguageCode());
		if(metadata.getMimeType() != null)
			wsMetadata.setMimeType(MimeTypes.Factory.fromString(metadata.getMimeType().toString(),null));
		wsMetadata.setPracticeSettingCode(metadata.getPracticeSettingCode());
		wsMetadata.setServiceStartTime(metadata.getServiceStartTime());
		wsMetadata.setServiceStopTime(metadata.getServiceStopTime());
		wsMetadata.setSourcePatientId(TypeTransformerResMetaData.convertId(metadata.getSourcePatientId()));
		wsMetadata.setTypeCode(TypeTransformerResMetaData.convertTypeCode(metadata.getTypeCode()));
		wsMetadata.setTitle(metadata.getTitle());
		wsMetadata.setSourcePatientInfo(TypeTransformerResMetaData.convertSourcePatientInfo(metadata.getSourcePatientInfo()));
		wsMetadata.setPatientContacts(TypeTransformerResMetaData.creatPatientContacts(metadata.getPatientContacts()));
		wsMetadata.setLegalAutheticator(TypeTransformerResMetaData.convertParticipant(metadata.getLegalAutheticator()));
		wsMetadata.setEventCodes(metadata.getEventCodes());
		if(metadata.getFormatCode() != null)
			wsMetadata.setFormatCode(XdsFormatCode.Factory.fromString(metadata.getFormatCode().toString(),null));
		wsMetadata.setUniqueDocumentId(TypeTransformerResMetaData.convertId(metadata.getUniqueDocumentId()));
		wsMetadata.setLegalAutheticator(TypeTransformerResMetaData.convertParticipant(metadata.getLegalAutheticator()));
		wsMetadata.setCustodian(TypeTransformerResMetaData.convertOrganization(metadata.getCustodian()));
		wsMetadata.setPerformers(TypeTransformerResMetaData.convertPerformers(metadata.getPerformers()));
		wsMetadata.setHomeSystemId(metadata.getHomeSystemId());
		
		return wsMetadata;
	}
	
	public static Performer[] convertPerformers(
			com.misyshealthcare.connect.doc.ccd.Performer[] performers) {
		if(performers == null) return null;
		
		Performer[] wsPerformers = new Performer[performers.length];
		for(int count = 0; count < performers.length; count++){
			wsPerformers[count] = convertPerformer(performers[count]);
		}
		return wsPerformers;
	}

	private static Performer convertPerformer(
			com.misyshealthcare.connect.doc.ccd.Performer performer) {
		if(performer == null) return null;
		
		Performer wsPerformer = new Performer();
		wsPerformer.setAddress(convertAddresses(performer.getAddress()));
		wsPerformer.setCode(convertCodeSystem(performer.getCode()));
		wsPerformer.setEndTime(performer.getEndTime());
		wsPerformer.setFunctionCode(convertCodeSystem(performer.getFunctionCode()));
		wsPerformer.setId(convertId(performer.getId()));
		wsPerformer.setPersonName(convertPersonName(performer.getPersonName()));
		wsPerformer.setPhoneNumbers(convertPhoneNumbers(performer.getPhoneNumbers()));
		wsPerformer.setStartTime(performer.getStartTime());
		
		return wsPerformer;
	}

	/**
	 * Convert PatientContact into the xsd package's PatientContact
	 * @param patientCont
	 * @return <code>PatientContact</code>
	 */
	public static PatientContact creatPatientContact(com.misyshealthcare.connect.doc.ccd.PatientContact patientCont){
		if(patientCont == null) return null;
		
		PatientContact wsPatientCont = new PatientContact();
		wsPatientCont.setEffectiveTime(convertEffeciveTime(patientCont.getEffectiveTime()));
		wsPatientCont.setParticipant(convertParticipant(patientCont.getParticipant()));
		wsPatientCont.setPatientContactType(convertPatientContactType(patientCont.getPatientContactType()));
		wsPatientCont.setPersonalRelationshipRoleType(convertCodeSystem(patientCont.getPersonalRelationshipRoleType()));
		
		return wsPatientCont;
	}
	
	/**
	 * Convert array of PatientContacts into the xsd package's PatientContacts
	 * @param patientContacts
	 * @return <code>PatientContact</code>
	 */
	public static PatientContact[] creatPatientContacts(
			com.misyshealthcare.connect.doc.ccd.PatientContact[] patientContacts) {
		if(patientContacts == null) return null; 
		
		PatientContact[] wsPatientConts = new  PatientContact[patientContacts.length];
		for(int count =0; count < patientContacts.length; count++){
			wsPatientConts[count] = creatPatientContact(patientContacts[count]);
		}
		return wsPatientConts;
	}

	/**
	 * Convert CodeSystem into the xsd package's CodeSystem
	 * @param prnlRelRoleType
	 * @return <code>CodeSystem</code>
	 */
	public static CodeSystem convertCodeSystem(
			com.misyshealthcare.connect.doc.ccd.CodeSystem prnlRelRoleType) {
		if(prnlRelRoleType == null)		return null;
		
		CodeSystem wsCodeSystem = new CodeSystem();
		wsCodeSystem.setCode(prnlRelRoleType.getCode());
		wsCodeSystem.setCodeSystem(prnlRelRoleType.getCodeSystem());
		wsCodeSystem.setCodeSystemName(prnlRelRoleType.getCodeSystemName());
		wsCodeSystem.setDisplayName(prnlRelRoleType.getDisplayName());
		return wsCodeSystem;
	}

	/**
	 * Convert PatientContactType into the xsd package's PatientContactType
	 * @param patientContactType
	 * @return <code>PatientContactType</code>
	 */
	public static PatientContactType convertPatientContactType(
			com.misyshealthcare.connect.base.SharedEnums.PatientContactType patientContactType) {
		if(patientContactType == null)
			return null;
		else
			return PatientContactType.Factory.fromString(patientContactType.toString(),null);
	}

	/**
	 * Convert EffectiveTime into the xsd package's EffectiveTime
	 * @param effectiveTime
	 * @return <code>EffectiveTime</code>
	 */
	public static EffectiveTime convertEffeciveTime(
			com.misyshealthcare.connect.doc.ccd.EffectiveTime effectiveTime) {
		if(effectiveTime == null)
			return null;
		
		EffectiveTime wsEffectiveTime = new EffectiveTime();
		wsEffectiveTime.setStart(effectiveTime.getStart());
		wsEffectiveTime.setEnd(effectiveTime.getEnd());
		return wsEffectiveTime;
	}

	/**
	 * Convert SourcePatientInfo into the xsd package's SourcePatientInfo
	 * @param sourcePatientInfo
	 * @return <code>SourcePatientInfo</code>
	 */
	public static SourcePatientInfo convertSourcePatientInfo(
			com.misyshealthcare.connect.doc.ccd.SourcePatientInfo sourcePatientInfo) {
		if(sourcePatientInfo == null)			return null;
		
		SourcePatientInfo info = new SourcePatientInfo();
		info.setAddress(convertAddresses(sourcePatientInfo.getAddress()));
		info.setBirthdate(sourcePatientInfo.getBirthdate());
		if(sourcePatientInfo.getGender() != null)
			info.setGender(SexType.Factory.fromString(sourcePatientInfo.getGender().toString(),null));
		info.setOrganization(convertOrganization(sourcePatientInfo.getOrganization()));
		info.setPersonName(convertPersonName(sourcePatientInfo.getPersonName()));
		info.setPhoneNumber(convertPhoneNumbers(sourcePatientInfo.getPhoneNumber()));
		
		return info;
	}

	/**
	 * Convert Organization into the xsd package's Organization
	 * @param organization
	 * @return <code>Organization</code>
	 */
	public static Organization convertOrganization(
			com.misyshealthcare.connect.doc.ccd.Organization organization) {
		if(organization == null)  return null;
		
		Organization org = new Organization();
		org.setOrganizationName(organization.getOrganizationName());
		org.setAddress(convertAddresses(organization.getAddress()));
		org.setId(convertId(organization.getId()));
		org.setPhoneNumbers(convertPhoneNumbers(organization.getPhoneNumbers()));
		
		return org;
	}

	/**
	 * Convert XdsTypeCode into the xsd package's XdsTypeCode
	 * @param typeCode
	 * @return <code>XdsTypeCode</code>
	 */
	public static XdsTypeCode convertTypeCode(
			com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode typeCode) {
		if(typeCode == null)
			return null;
		else
			return XdsTypeCode.Factory.fromString(typeCode.toString(),null);
	}

	/**
	 * Convert XdsClassCode into the xsd package's XdsClassCode
	 * @param classCode
	 * @return <code>XdsClassCode</code>
	 */
	public static XdsClassCode convertClassCode(
			com.misyshealthcare.connect.base.SharedEnums.XdsClassCode classCode) {
		if(classCode == null)
			return null;
		else
			return XdsClassCode.Factory.fromString(classCode.toString(),null);
	}

	/**
	 * Convert array of ConfidentialityCodes into the xsd package's ConfidentialityCodes
	 * @param confidentialityCodes
	 * @return <code>ConfidentialityCode</code>
	 */
	public static ConfidentialityCode[] convertConfidentialityCodes(
			 com.misyshealthcare.connect.base.SharedEnums.ConfidentialityCode[] confidentialityCodes) {
		if(confidentialityCodes == null) return null;
		
		ConfidentialityCode[] wsConfidentialityCodes = new ConfidentialityCode[confidentialityCodes.length];		
		for(int codeCount = 0;codeCount < confidentialityCodes.length; codeCount++){
			if(confidentialityCodes[codeCount].getCode() != null)
				wsConfidentialityCodes[codeCount] = ConfidentialityCode.Factory.fromString(confidentialityCodes[codeCount].toString(),null);
		}
		return wsConfidentialityCodes;
	}

	/**
	 * Convert array of Authors into the xsd package's Authors
	 * @param authors
	 * @return <code>Author</code>
	 */
	public static Author[] convertAuthors(
			com.misyshealthcare.connect.doc.ccd.Author[] authors) {
		if(authors == null) return null;
		Author[] wsAuthors = new Author[authors.length];
			
		for(int authorCount = 0;authorCount<authors.length;authorCount++){
			wsAuthors[authorCount] = convertAuthor(authors[authorCount]); 
		}
		return wsAuthors;
	}
	
	/**
	 * Convert Author into the xsd package's Author
	 * @param author
	 * @return <code>Author</code>
	 */
	private static Author convertAuthor(
			com.misyshealthcare.connect.doc.ccd.Author author) {
		if(author == null) return null;
		
		Author wsAuthor = new Author();
		wsAuthor.setAuthorPerson(convertParticipant(author.getAuthorPerson()));
		wsAuthor.setAuthorRoles(author.getAuthorRoles());
		wsAuthor.setAuthorSpecialities(author.getAuthorSpecialities());
		wsAuthor.setStartParticipationTime(author.getStartParticipationTime());
		wsAuthor.setAuthorDevice(convertAuthorDevice(author.getAuthorDevice()));
		wsAuthor.setOrganization(convertOrganization(author.getOrganization()));
		
		return wsAuthor;
	}

	private static AuthorDevice convertAuthorDevice(
			com.misyshealthcare.connect.doc.ccd.AuthorDevice authorDevice) {
		if(authorDevice == null) return null;
		
		AuthorDevice wsDevice = new AuthorDevice();
		wsDevice.setManufacturerModelName(authorDevice.getManufacturerModelName());
		wsDevice.setSoftwareName(authorDevice.getSoftwareName());
		
		return wsDevice;
	}

	/**
	 * Convert Participant into the xsd package's Participant
	 * @param participant
	 * @return <code>Participant</code>
	 */
	public static Participant convertParticipant(
			com.misyshealthcare.connect.doc.ccd.Participant participant) {
		if(participant == null)	return null;
		
		Participant wsParticipant = new Participant();
		wsParticipant.setAddress(convertAddresses(participant.getAddress()));
		wsParticipant.setId(convertId(participant.getId()));
		wsParticipant.setPersonName(convertPersonName(participant.getPersonName()));
		wsParticipant.setPhoneNumbers(convertPhoneNumbers(participant.getPhoneNumbers()));
		return wsParticipant;
	}

	/**
	 * Convert array of PhoneNumbers into the xsd package's PhoneNumbers
	 * @param phoneNumbers
	 * @return <code>PhoneNumber</code>
	 */
	public static PhoneNumber[] convertPhoneNumbers(
			com.misyshealthcare.connect.base.demographicdata.PhoneNumber[] phoneNumbers) {
		if(phoneNumbers == null) return null;
		
		PhoneNumber[] wsPhoneNumbers = new PhoneNumber[phoneNumbers.length];
		for(int phoneCount = 0;phoneCount<phoneNumbers.length;phoneCount++){
			wsPhoneNumbers[phoneCount] = createPhoneNumber(phoneNumbers[phoneCount]);
		}
		return wsPhoneNumbers;
	}

	/**
	 * Convert PhoneNumber into the xsd package's PhoneNumber
	 * @param phoneNumber
	 * @return <code>PhoneNumber</code>
	 */
	public static PhoneNumber createPhoneNumber(
			com.misyshealthcare.connect.base.demographicdata.PhoneNumber phoneNumber) {
		if(phoneNumber == null) return null;
		
		PhoneNumber wsPhoneNumber = new PhoneNumber();
		wsPhoneNumber.setAreaCode(phoneNumber.getAreaCode());
		wsPhoneNumber.setCountryCode(phoneNumber.getCountryCode());
		wsPhoneNumber.setExtension(phoneNumber.getExtension());
		wsPhoneNumber.setNote(phoneNumber.getNote());
		wsPhoneNumber.setNumber(phoneNumber.getNumber());
		if(phoneNumber.getType() != null)
			wsPhoneNumber.setType(PhoneType.Factory.fromString(phoneNumber.getType().toString(),null));
		
		return wsPhoneNumber;
	}

	/**
	 * Convert PersonName into the xsd package's PersonName
	 * @param personName
	 * @return <code>PersonName</code>
	 */
	public static PersonName convertPersonName(
			com.misyshealthcare.connect.doc.ccd.PersonName personName) {
		if(personName == null) return null;
		
		PersonName wsPersonName = new PersonName();
		wsPersonName.setFirstName(personName.getFirstName());
		wsPersonName.setLastName(personName.getLastName());
		wsPersonName.setMiddleName(personName.getMiddleName());
		wsPersonName.setPrefix(personName.getPrefix());
		wsPersonName.setSuffix(personName.getSuffix());
		return wsPersonName;
	}

	/**
	 * Convert Id into the xsd package's Id
	 * @param id
	 * @return <code>Id</code>
	 */
	public static Id convertId(com.misyshealthcare.connect.doc.ccd.Id id) {
		if(id == null)
			return null;
		Id wsId = new Id();
		wsId.setRoot(id.getRoot());
		wsId.setExtension(id.getExtension());
		return wsId;
	}

	/**
	 * Convert Address into the xsd package's Address
	 * @param address
	 * @return <code>Address</code>
	 */
	public static Address convertAddress(com.misyshealthcare.connect.base.demographicdata.Address address){
		if(address == null) return null;
		
		Address wsAddress = new Address();
		wsAddress.setAddCity(address.getAddCity());
		wsAddress.setAddCountry(address.getAddCountry());
		wsAddress.setAddLine1(address.getAddLine1());
		wsAddress.setAddLine2(address.getAddLine2());
		wsAddress.setAddState(address.getAddState());
		if(address.getAddType() != null)
			wsAddress.setAddType(AddressType.Factory.fromString(address.getAddType().toString(),null));
		wsAddress.setAddZip(address.getAddZip());
		
		return wsAddress;
	}
	
	/**
	 * Convert array of Addresses into the xsd package's Addresses
	 * @param addresses
	 * @return <code>Address</code>
	 */
	public static Address[] convertAddresses(
			com.misyshealthcare.connect.base.demographicdata.Address[] addresses) {
		if(addresses == null) return null;
		
		Address[] wsAddresses = new Address[addresses.length];			
		for(int addrCount = 0;addrCount<addresses.length;addrCount++){
			wsAddresses[addrCount] = convertAddress(addresses[addrCount]);
		}
		return wsAddresses;
	}
}
