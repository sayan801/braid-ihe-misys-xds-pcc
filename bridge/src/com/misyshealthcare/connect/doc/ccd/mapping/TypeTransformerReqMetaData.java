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

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.ConfidentialityCode;
import com.misyshealthcare.connect.base.SharedEnums.PatientContactType;
import com.misyshealthcare.connect.base.SharedEnums.XdsClassCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.AuthorDevice;
import com.misyshealthcare.connect.doc.ccd.CodeSystem;
import com.misyshealthcare.connect.doc.ccd.DataEnterer;
import com.misyshealthcare.connect.doc.ccd.EffectiveTime;
import com.misyshealthcare.connect.doc.ccd.Encounter;
import com.misyshealthcare.connect.doc.ccd.Id;
import com.misyshealthcare.connect.doc.ccd.Organization;
import com.misyshealthcare.connect.doc.ccd.Participant;
import com.misyshealthcare.connect.doc.ccd.PatientContact;
import com.misyshealthcare.connect.doc.ccd.Performer;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.SourcePatientInfo;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer Request MetaData information from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 10, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class TypeTransformerReqMetaData {
	
	/**
	 * Convert PatientContact into the ccd package's PatientContact
	 * @param wsPatientCont
	 * @return <code>PatientContact</code>
	 */
	public static PatientContact creatPatientContacts(com.misyshealthcare.connect.doc.ccd.xsd.PatientContact wsPatientCont){
		if(wsPatientCont == null) return null;
		
		PatientContact patientCont = new PatientContact();
		patientCont.setEffectiveTime(convertEffeciveTime(wsPatientCont.getEffectiveTime()));
		patientCont.setParticipant(convertParticipant(wsPatientCont.getParticipant()));
		patientCont.setPatientContactType(convertPatientContactType(wsPatientCont.getPatientContactType()));
		patientCont.setPersonalRelationshipRoleType(convertCodeSystem(wsPatientCont.getPersonalRelationshipRoleType()));
		
		return patientCont;
	}
	
	/**
	 * Convert array of PatientContact into the ccd package's PatientContact
	 * @param wsPatientContacts
	 * @return <code>PatientContact</code>
	 */
	public static PatientContact[] creatPatientContacts(
			com.misyshealthcare.connect.doc.ccd.xsd.PatientContact[] wsPatientContacts) {
		if(wsPatientContacts == null) return null; 
		
		PatientContact[] patientConts = new  PatientContact[wsPatientContacts.length];
		for(int count =0; count < wsPatientContacts.length; count++){
			patientConts[count] = creatPatientContacts(wsPatientContacts[count]);
		}
		return patientConts;
	}

	/**
	 * Convert CodeSystem into the ccd package's CodeSystem
	 * @param wsPrnlRelRoleType
	 * @return <code>CodeSystem</code>
	 */
	public static CodeSystem convertCodeSystem(
			com.misyshealthcare.connect.doc.ccd.xsd.CodeSystem wsPrnlRelRoleType) {
		if(wsPrnlRelRoleType == null)		return null;
		
		CodeSystem codeSystem = new CodeSystem(wsPrnlRelRoleType.getCode(),wsPrnlRelRoleType.getCodeSystem(),wsPrnlRelRoleType.getCodeSystemName(),wsPrnlRelRoleType.getDisplayName());
		return codeSystem;
	}

	/**
	 * Convert PatientContactType into the enum PatientContactType
	 * @param wsPatientContactType
	 * @return <code>PatientContactType</code>
	 */
	public static PatientContactType convertPatientContactType(
			com.misyshealthcare.connect.base.xsd.PatientContactType wsPatientContactType) {
		if(wsPatientContactType != null && wsPatientContactType.getValue() != null)
			return PatientContactType.valueOf(wsPatientContactType.getValue());
		else
			return null;
	}

	/**
	 * Convert EffectiveTime into the ccd package's EffectiveTime
	 * @param effectiveTime
	 * @return <code>EffectiveTime</code>
	 */
	public static EffectiveTime convertEffeciveTime(
			com.misyshealthcare.connect.doc.ccd.xsd.EffectiveTime effectiveTime) {
		if(effectiveTime == null)
			return null;
		else
			return new EffectiveTime(effectiveTime.getStart(),effectiveTime.getEnd());
	}

	/**
	 * Convert SourcePatientInfo into the ccd package's SourcePatientInfo
	 * @param wsSourcePatientInfo
	 * @return <code>SourcePatientInfo</code>
	 */
	public static SourcePatientInfo convertSourcePatientInfo(
			com.misyshealthcare.connect.doc.ccd.xsd.SourcePatientInfo wsSourcePatientInfo) {
		if(wsSourcePatientInfo == null)			return null;
		
		SourcePatientInfo info = new SourcePatientInfo();
		info.setAddress(convertAddresses(wsSourcePatientInfo.getAddress()));
		info.setBirthdate(wsSourcePatientInfo.getBirthdate());
		if(wsSourcePatientInfo.getGender() != null && wsSourcePatientInfo.getGender().getValue() != null)
			info.setGender(SharedEnums.SexType.valueOf(wsSourcePatientInfo.getGender().getValue()));
		info.setOrganization(convertOrganization(wsSourcePatientInfo.getOrganization()));
		info.setPersonName(convertPersonName(wsSourcePatientInfo.getPersonName()));
		info.setPhoneNumber(convertPhoneNumbers(wsSourcePatientInfo.getPhoneNumber()));
		
		return info;
	}

	/**
	 * Convert Organization into the ccd package's Organization
	 * @param wsOrganization
	 * @return <code>Organization</code>
	 */
	public static Organization convertOrganization(
			com.misyshealthcare.connect.doc.ccd.xsd.Organization wsOrganization) {
		if(wsOrganization == null)  return null;
		
		Organization org = new Organization();
		org.setOrganizationName(wsOrganization.getOrganizationName());
		org.setAddress(convertAddresses(wsOrganization.getAddress()));
		org.setId(convertId(wsOrganization.getId()));
		org.setPhoneNumbers(convertPhoneNumbers(wsOrganization.getPhoneNumbers()));
		
		return org;
	}

	/**
	 * Convert XdsTypeCode into the enum XdsTypeCode
	 * @param typeCode
	 * @return <code>XdsTypeCode</code>
	 */
	public static XdsTypeCode convertTypeCode(
			com.misyshealthcare.connect.base.xsd.XdsTypeCode typeCode) {
		if(typeCode != null && typeCode.getValue() != null)
			return SharedEnums.XdsTypeCode.valueOf(typeCode.getValue());
		else
			return null;
	}

	/**
	 * Convert XdsClassCode into the enum XdsClassCode
	 * @param wsClassCode
	 * @return <code>XdsClassCode</code>
	 */
	public static XdsClassCode convertClassCode(
			com.misyshealthcare.connect.base.xsd.XdsClassCode wsClassCode) {
		if(wsClassCode != null && wsClassCode.getValue() != null)
			return SharedEnums.XdsClassCode.valueOf(wsClassCode.getValue());
		else
			return null;
	}

	/**
	 * Convert array of ConfidentialityCodes into the ccd package's ConfidentialityCodes
	 * @param wsConfidentialityCodes
	 * @return <code>ConfidentialityCode</code>
	 */
	public static ConfidentialityCode[] convertConfidentialityCodes(
			 com.misyshealthcare.connect.base.xsd.ConfidentialityCode[] wsConfidentialityCodes) {
		if(wsConfidentialityCodes == null) return null;
		
		ConfidentialityCode[] confidentialityCodes = new ConfidentialityCode[wsConfidentialityCodes.length];		
		for(int codeCount = 0;codeCount < wsConfidentialityCodes.length; codeCount++){
			if(wsConfidentialityCodes != null && wsConfidentialityCodes[codeCount].getValue() != null)
				confidentialityCodes[codeCount] = ConfidentialityCode.valueOf(wsConfidentialityCodes[codeCount].getValue());
		}
		return confidentialityCodes;
	}

	/**
	 * Convert array of Authors into the ccd package's Authors
	 * @param wsAuthors
	 * @return <code>Author</code>
	 */
	public static Author[] convertAuthors(
			com.misyshealthcare.connect.doc.ccd.xsd.Author[] wsAuthors) {
		if(wsAuthors == null) return null;
		Author[] authors = new Author[wsAuthors.length];
			
		for(int authorCount = 0;authorCount<wsAuthors.length;authorCount++){
			authors[authorCount] = convertAuthor(wsAuthors[authorCount]); 
		}
		return authors;
	}
	
	/**
	 * Convert Author into the ccd package's Author
	 * @param wsAuthor
	 * @return <code>Author</code>
	 */
	public static Author convertAuthor(
			com.misyshealthcare.connect.doc.ccd.xsd.Author wsAuthor) {
		if(wsAuthor == null) return null;
		
		Author author = new Author();
		author.setAuthorPerson(convertParticipant(wsAuthor.getAuthorPerson()));
		author.setAuthorRoles(wsAuthor.getAuthorRoles());
		author.setAuthorSpecialities(wsAuthor.getAuthorSpecialities());
		author.setStartParticipationTime(wsAuthor.getStartParticipationTime());
		author.setAuthorDevice(convertAuthorDevice(wsAuthor.getAuthorDevice()));
		author.setOrganization(convertOrganization(wsAuthor.getOrganization()));
		
		return author;
	}

	private static AuthorDevice convertAuthorDevice(
			com.misyshealthcare.connect.doc.ccd.xsd.AuthorDevice wsAuthorDevice) {
		if(wsAuthorDevice == null) return null;
		
		AuthorDevice device = new AuthorDevice();
		device.setManufacturerModelName(wsAuthorDevice.getManufacturerModelName());
		device.setSoftwareName(wsAuthorDevice.getSoftwareName());
		
		return device;
	}

	/**
	 * Convert Participant into the ccd package's Participant
	 * @param wsParticipant
	 * @return <code>Participant</code>
	 */
	public static Participant convertParticipant(
			com.misyshealthcare.connect.doc.ccd.xsd.Participant wsParticipant) {
		if(wsParticipant == null)	return null;
		
		Participant participant = new Participant();
		participant.setAddress(convertAddresses(wsParticipant.getAddress()));
		participant.setId(convertId(wsParticipant.getId()));
		participant.setPersonName(convertPersonName(wsParticipant.getPersonName()));
		participant.setPhoneNumbers(convertPhoneNumbers(wsParticipant.getPhoneNumbers()));
		return participant;
	}

	/**
	 * Convert array of PhoneNumbers into the ccd package's PhoneNumbers
	 * @param wsPhoneNumbers
	 * @return <code>PhoneNumber</code>
	 */
	public static PhoneNumber[] convertPhoneNumbers(
			com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber[] wsPhoneNumbers) {
		if(wsPhoneNumbers == null) return null;
		
		PhoneNumber[] phoneNumbers = new PhoneNumber[wsPhoneNumbers.length];
		for(int phoneCount = 0;phoneCount<wsPhoneNumbers.length;phoneCount++){
			phoneNumbers[phoneCount] = createPhoneNumber(wsPhoneNumbers[phoneCount]);
		}
		return phoneNumbers;
	}

	/**
	 * Convert PhoneNumber into the ccd package's PhoneNumber
	 * @param wsPhoneNumber
	 * @return <code>PhoneNumber</code>
	 */
	private static PhoneNumber createPhoneNumber(
			com.misyshealthcare.connect.base.demographicdata.xsd.PhoneNumber wsPhoneNumber) {
		if(wsPhoneNumber == null) return null;
		
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setAreaCode(wsPhoneNumber.getAreaCode());
		phoneNumber.setCountryCode(wsPhoneNumber.getCountryCode());
		phoneNumber.setExtension(wsPhoneNumber.getExtension());
		phoneNumber.setNote(wsPhoneNumber.getNote());
		phoneNumber.setNumber(wsPhoneNumber.getNumber());
		if(wsPhoneNumber.getType() != null && wsPhoneNumber.getType().getValue() != null)
			phoneNumber.setType(SharedEnums.PhoneType.valueOf(wsPhoneNumber.getType().getValue()));
		
		return phoneNumber;
	}

	/**
	 * Convert PersonName into the ccd package's PersonName
	 * @param wsPersonName
	 * @return <code>PersonName</code>
	 */
	public static PersonName convertPersonName(
			com.misyshealthcare.connect.doc.ccd.xsd.PersonName wsPersonName) {
		if(wsPersonName == null) return null;
		
		PersonName personName = new PersonName();
		personName.setFirstName(wsPersonName.getFirstName());
		personName.setLastName(wsPersonName.getLastName());
		personName.setMiddleName(wsPersonName.getMiddleName());
		personName.setPrefix(wsPersonName.getPrefix());
		personName.setSuffix(wsPersonName.getSuffix());
		return personName;
	}

	/**
	 * Convert Id into the ccd package's Id
	 * @param wsId
	 * @return <code>Id</code>
	 */
	public static Id convertId(com.misyshealthcare.connect.doc.ccd.xsd.Id wsId) {
		if(wsId == null)
			return null;
		Id id = new Id(wsId.getRoot(),wsId.getExtension());
		return id;
	}

	/**
	 * Convert Address into the ccd package's Address
	 * @param wsAddress
	 * @return <code>Address</code>
	 */
	public static Address convertAddress(com.misyshealthcare.connect.base.demographicdata.xsd.Address wsAddress){
		if(wsAddress == null) return null;
		
		Address address = new Address();
		address.setAddCity(wsAddress.getAddCity());
		address.setAddCountry(wsAddress.getAddCountry());
		address.setAddLine1(wsAddress.getAddLine1());
		address.setAddLine2(wsAddress.getAddLine2());
		address.setAddState(wsAddress.getAddState());
		if(wsAddress.getAddType() != null)
			address.setAddType(SharedEnums.AddressType.valueOf(wsAddress.getAddType().getValue()));
		address.setAddZip(wsAddress.getAddZip());
		
		return address;
	}
	
	/**
	 * Convert array of Addresses into the ccd package's Addresses
	 * @param wsAddresses
	 * @return <code>Address</code>
	 */
	public static Address[] convertAddresses(
			com.misyshealthcare.connect.base.demographicdata.xsd.Address[] wsAddresses) {
		if(wsAddresses == null) return null;
		
		Address[] addresses = new Address[wsAddresses.length];			
		for(int addrCount = 0;addrCount<wsAddresses.length;addrCount++){
			addresses[addrCount] = convertAddress(wsAddresses[addrCount]);
		}
		return addresses;
	}

	/**
	 * Convert array of Performers into the ccd package's Performers
	 * @param performers
	 * @return <code>Performer</code>
	 */
	public static Performer[] convertPerformers(
			com.misyshealthcare.connect.doc.ccd.xsd.Performer[] wsPerformers) {
		if(wsPerformers == null) return null;
		
		Performer[] performers = new Performer[wsPerformers.length];
		for(int count = 0; count < wsPerformers.length; count++){
			performers[count] = convertPerformer(wsPerformers[count]);
		}
		return performers;
	}

	public static Performer convertPerformer(
			com.misyshealthcare.connect.doc.ccd.xsd.Performer wsPerformer) {
		if(wsPerformer == null) return null;
		
		Performer performer = new Performer();
		performer.setAddress(convertAddresses(wsPerformer.getAddress()));
		performer.setCode(convertCodeSystem(wsPerformer.getCode()));
		performer.setEndTime(wsPerformer.getEndTime());
		performer.setFunctionCode(convertCodeSystem(wsPerformer.getFunctionCode()));
		performer.setId(convertId(wsPerformer.getId()));
		performer.setPersonName(convertPersonName(wsPerformer.getPersonName()));
		performer.setPhoneNumbers(convertPhoneNumbers(wsPerformer.getPhoneNumbers()));
		performer.setStartTime(wsPerformer.getStartTime());
		
		return performer;
	}

	public static Encounter[] convertEncounters(
			com.misyshealthcare.connect.doc.ccd.xsd.Encounter[] wsEncounters) {
		if(wsEncounters == null) return null;
		
		Encounter[] encounters = new Encounter[wsEncounters.length];
		for(int count = 0; count < wsEncounters.length; count++){
			encounters[count] = createEncounter(wsEncounters[count]);
		}
		return encounters;
	}

	public static Encounter createEncounter(
			com.misyshealthcare.connect.doc.ccd.xsd.Encounter wsEncounter) {
		if(wsEncounter == null) return null;
		Encounter encounter = new Encounter();
		if(wsEncounter.getDischargeDispositionCode() != null){
			String disCode = wsEncounter.getDischargeDispositionCode().getValue();
			if(disCode != null && disCode.trim().length() > 0)
				encounter.setDischargeDispositionCode(SharedEnums.DischargeDispositionCode.valueOf(disCode));
		}
		encounter.setEncounterId(convertId(wsEncounter.getEncounterId()));
		encounter.setEndTime(wsEncounter.getEndTime());
		encounter.setStartTime(wsEncounter.getStartTime());
		return encounter;
	}
	
	/**
	 * Convert array of DataEnterers into the ccd package's DataEnterers
	 * @param wsDataEnterer
	 * @return <code>DataEnterer</code>
	 */
	public static DataEnterer[] convertDataEnterers(
			com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer[] wsDataEnterer) {
		if(wsDataEnterer == null) return null;
		
		DataEnterer[] dataEnterers = new DataEnterer[wsDataEnterer.length];
		for(int count = 0; count < wsDataEnterer.length; count++){
			dataEnterers[count] = convertDataEnterer(wsDataEnterer[count]);
		}
		return dataEnterers;
	}
	
	/**
	 * Convert DataEnterer into the ccd package's DataEnterer
	 * @param wsDataEnterer
	 * @return <code>DataEnterer</code>
	 */
	public static DataEnterer convertDataEnterer(
			com.misyshealthcare.connect.doc.ccd.xsd.DataEnterer wsDataEnterer) {
		if(wsDataEnterer == null) return null;
		
		DataEnterer data = new DataEnterer();
		data.setAssignedEntityId(TypeTransformerReqMetaData.convertId(wsDataEnterer.getAssignedEntityId()));
		data.setPersonName(TypeTransformerReqMetaData.convertPersonName(wsDataEnterer.getPersonName()));
		data.setTime(wsDataEnterer.getTime());
		
		return data;
	}
}
