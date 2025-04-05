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

package com.misyshealthcare.connect.doc.ccd;

import hl7OrgV3.AD;
import hl7OrgV3.BinaryDataEncoding;
import hl7OrgV3.CD;
import hl7OrgV3.CE;
import hl7OrgV3.CS;
import hl7OrgV3.ED;
import hl7OrgV3.EN;
import hl7OrgV3.EnFamily;
import hl7OrgV3.EnGiven;
import hl7OrgV3.EnPrefix;
import hl7OrgV3.EnSuffix;
import hl7OrgV3.II;
import hl7OrgV3.INT;
import hl7OrgV3.IVLPQ;
import hl7OrgV3.IVLTS;
import hl7OrgV3.IVXBPQ;
import hl7OrgV3.IVXBTS;
import hl7OrgV3.ON;
import hl7OrgV3.PN;
import hl7OrgV3.POCDMT000040Act;
import hl7OrgV3.POCDMT000040AssignedAuthor;
import hl7OrgV3.POCDMT000040AssignedCustodian;
import hl7OrgV3.POCDMT000040AssignedEntity;
import hl7OrgV3.POCDMT000040AssociatedEntity;
import hl7OrgV3.POCDMT000040Authenticator;
import hl7OrgV3.POCDMT000040Author;
import hl7OrgV3.POCDMT000040AuthoringDevice;
import hl7OrgV3.POCDMT000040Authorization;
import hl7OrgV3.POCDMT000040ClinicalDocument1;
import hl7OrgV3.POCDMT000040Component1;
import hl7OrgV3.POCDMT000040Component2;
import hl7OrgV3.POCDMT000040Component3;
import hl7OrgV3.POCDMT000040Component4;
import hl7OrgV3.POCDMT000040Component5;
import hl7OrgV3.POCDMT000040Consent;
import hl7OrgV3.POCDMT000040Consumable;
import hl7OrgV3.POCDMT000040Custodian;
import hl7OrgV3.POCDMT000040CustodianOrganization;
import hl7OrgV3.POCDMT000040DataEnterer;
import hl7OrgV3.POCDMT000040DocumentationOf;
import hl7OrgV3.POCDMT000040EncompassingEncounter;
import hl7OrgV3.POCDMT000040Entry;
import hl7OrgV3.POCDMT000040EntryRelationship;
import hl7OrgV3.POCDMT000040Guardian;
import hl7OrgV3.POCDMT000040InfrastructureRootTypeId;
import hl7OrgV3.POCDMT000040LegalAuthenticator;
import hl7OrgV3.POCDMT000040ManufacturedProduct;
import hl7OrgV3.POCDMT000040Material;
import hl7OrgV3.POCDMT000040NonXMLBody;
import hl7OrgV3.POCDMT000040Observation;
import hl7OrgV3.POCDMT000040ObservationRange;
import hl7OrgV3.POCDMT000040Organization;
import hl7OrgV3.POCDMT000040Organizer;
import hl7OrgV3.POCDMT000040Participant1;
import hl7OrgV3.POCDMT000040Participant2;
import hl7OrgV3.POCDMT000040ParticipantRole;
import hl7OrgV3.POCDMT000040Patient;
import hl7OrgV3.POCDMT000040PatientRole;
import hl7OrgV3.POCDMT000040Performer1;
import hl7OrgV3.POCDMT000040Person;
import hl7OrgV3.POCDMT000040PlayingEntity;
import hl7OrgV3.POCDMT000040Procedure;
import hl7OrgV3.POCDMT000040RecordTarget;
import hl7OrgV3.POCDMT000040ReferenceRange;
import hl7OrgV3.POCDMT000040Section;
import hl7OrgV3.POCDMT000040ServiceEvent;
import hl7OrgV3.POCDMT000040StructuredBody;
import hl7OrgV3.POCDMT000040SubstanceAdministration;
import hl7OrgV3.RoleClassManufacturedProduct;
import hl7OrgV3.SC;
import hl7OrgV3.ST;
import hl7OrgV3.SXCMTS;
import hl7OrgV3.StrucDocItem;
import hl7OrgV3.StrucDocList;
import hl7OrgV3.StrucDocParagraph;
import hl7OrgV3.StrucDocTable;
import hl7OrgV3.StrucDocTbody;
import hl7OrgV3.StrucDocTd;
import hl7OrgV3.StrucDocText;
import hl7OrgV3.StrucDocTh;
import hl7OrgV3.StrucDocThead;
import hl7OrgV3.StrucDocTr;
import hl7OrgV3.TEL;
import hl7OrgV3.TS;
import hl7OrgV3.XActClassDocumentEntryAct;
import hl7OrgV3.XActClassDocumentEntryOrganizer;
import hl7OrgV3.XActMoodDocumentObservation;
import hl7OrgV3.XActRelationshipEntry;
import hl7OrgV3.XActRelationshipEntryRelationship;
import hl7OrgV3.XDocumentActMood;
import hl7OrgV3.XDocumentSubstanceMood;
import hl7OrgV3.XServiceEventPerformer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlCursor;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import ca.uhn.hl7v2.model.primitive.ID;

import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.ClinicalStatusCode;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.clinicaldata.AdvanceDirective;
import com.misyshealthcare.connect.base.clinicaldata.Allergy;
import com.misyshealthcare.connect.base.clinicaldata.Battery;
import com.misyshealthcare.connect.base.clinicaldata.ClinicalNotes;
import com.misyshealthcare.connect.base.clinicaldata.Code;
import com.misyshealthcare.connect.base.clinicaldata.DoseQuantity;
import com.misyshealthcare.connect.base.clinicaldata.EffectiveDates;
import com.misyshealthcare.connect.base.clinicaldata.Immunization;
import com.misyshealthcare.connect.base.clinicaldata.LabResult;
import com.misyshealthcare.connect.base.clinicaldata.Measurements;
import com.misyshealthcare.connect.base.clinicaldata.Medication;
import com.misyshealthcare.connect.base.clinicaldata.Procedure;
import com.misyshealthcare.connect.base.clinicaldata.Result;
import com.misyshealthcare.connect.base.clinicaldata.SimpleProblem;
import com.misyshealthcare.connect.base.clinicaldata.Test;
import com.misyshealthcare.connect.base.codemapping.CategoryEnum;
import com.misyshealthcare.connect.base.codemapping.ICodeMappingManager;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.Insurance;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.base.demographicdata.SubscriberNameAddress;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.ObjectEntry;
import com.misyshealthcare.connect.util.Base64EncoderDecoder;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.util.OID;
import com.misyshealthcare.connect.util.StringUtil;

/**
 * This class provides all the building blocks that build each component of a
 * CDA document.
 * 
 * @author Wenzhi Li
 * @version 2.0, Oct 15, 2005
 */
public class BaseBuildingComponent implements TemplateId {
	protected static final Logger log = Logger
			.getLogger(BaseBuildingComponent.class.getName());
	protected static ICodeMappingManager cmm = LibraryConfig.getInstance()
			.getCodeMappingManager();
	protected static DateFormat dfLong = new SimpleDateFormat("yyyyMMddHHmmssZ");
	protected static DateFormat dfLong2 = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssZ");
	protected static DateFormat dfShort = new SimpleDateFormat("MM/dd/yyyy");
	protected static DateFormat dfShort2 = new SimpleDateFormat("yyyyMMdd");
	protected static String cdaExternalSys = "CDA";
	protected static final String OID_ROOT = LibraryConfig.getInstance()
			.getOidRoot();
	protected static final String MISYS_ID = OID
			.getUID(OID.IDType.ORGANIZATION); // TODO: This is a fixed ID

	protected static final String UNKNOWN = "Unknown";
	protected static final String NONE = "None";
	protected static final String LOINC = "LOINC";
	protected static final String IHEActCode = "IHEActCode";

	/**
	 * Builds the first few parts of the document header.
	 * 
	 * @param doc
	 *            the doc
	 * @param metadata
	 *            the metadata of the this document.
	 * @param templateIds
	 *            a list of template ids that the doc will conform to.
	 * @param cs
	 *            the code system of the doc
	 * @param title
	 *            the optional doc title. Set it to null if no title.
	 */
	void buildHeader1(POCDMT000040ClinicalDocument1 doc, MetaData metadata,
			List<Id> templateIds, CodeSystem cs, String title) {
		CS realmCode = doc.addNewRealmCode();
		realmCode.setCode("US");

		// Set the required TypeId, which is the version of CDA Release 2
		POCDMT000040InfrastructureRootTypeId rtId = doc.addNewTypeId();
		rtId.setExtension("POCD_HD000040");
		rtId.setRoot("2.16.840.1.113883.1.3");

		for (Id id : templateIds) {
			II tid = doc.addNewTemplateId();
			if (id.getExtension() != null)
				tid.setExtension(id.getExtension());
			tid.setRoot(id.getRoot());
		}

		// set the required doc ID
		II docId = doc.addNewId();
		Id documentId = new Id(OID_ROOT, OID.getUID(OID.IDType.DOCUMENT));
		buildId(docId, documentId, false);
		metadata.setUniqueDocumentId(documentId);

		// Add required code
		// e.g. <code code='34133-9' codeSystem='2.16.840.1.113883.6.1'
		// codeSystemName='LOINC' displayName='SUMMARIZATION OF EPISODE NOTE'/>
		CE code = doc.addNewCode();
		buildCodeSystem(code, cs);

		// set title
		ST stTitle = doc.addNewTitle();
		if (StringUtil.goodString(title)) {
			XmlBeanUtil.addText(stTitle, title);
		} else {
			stTitle.setNullFlavor("UNK");
		}

		// Set the creation time <effectiveTime value='20050329224411+0500'/>
		TS effectiveTime = doc.addNewEffectiveTime();
		effectiveTime.setValue(dfLong.format(new GregorianCalendar().getTime()));
		metadata.setCreationTime(GregorianCalendar.getInstance());

		// Confidentiality
		SharedEnums.ConfidentialityCode[] confidentialityTypes = metadata
				.getConfidentialityCodes();
		if (confidentialityTypes == null || confidentialityTypes.length == 0) {
			// use default confidentiality code
			CE confidentiality = doc.addNewConfidentialityCode();
			CodeSystem codesystem = new CodeSystem(
					SharedEnums.ConfidentialityCode.Normal.getCodeValue(),
					SharedEnums.ConfidentialityCode.Normal.getCodeSystem(),
					SharedEnums.ConfidentialityCode.Normal.getCodeSystemName(),
					SharedEnums.ConfidentialityCode.Normal.getDisplayName());
			buildCodeSystem(confidentiality, codesystem);
			metadata
					.setConfidentialityCodes(new SharedEnums.ConfidentialityCode[] { SharedEnums.ConfidentialityCode.Normal });
		} else {
			for (SharedEnums.ConfidentialityCode confidentialityCode : confidentialityTypes) {
				CE confidentiality = doc.addNewConfidentialityCode();
				CodeSystem codesystem = new CodeSystem(confidentialityCode
						.getCodeValue(), confidentialityCode.getCodeSystem(),
						confidentialityCode.getCodeSystemName(),
						confidentialityCode.getDisplayName());
				buildCodeSystem(confidentiality, codesystem);
			}
		}

		// Language Code
		CS language = doc.addNewLanguageCode();
		String languageCode = metadata.getLanguageCode();
		if (languageCode != null) {
			language.setCode(languageCode);
		} else {
			language.setCode("en-US");
			metadata.setLanguageCode("en-US");
		}
		// language.setCodeSystem("2.16.840.1.113883.6.121"); CDA does not allow
		// codeSystem for LanguageCode
		// language.setCodeSystemName("IETF3066");

		// SetId and Version Number
		II setId = doc.addNewSetId();
		setId.setRoot(OID_ROOT);
		buildId(setId, new Id(OID_ROOT, OID.getUID(OID.IDType.CDA_SET)));

		INT version = doc.addNewVersionNumber();
		version.setValue(BigInteger.ONE); // resolve version number, defaults
											// to 1
	}

	public void buildRecordTarget(POCDMT000040ClinicalDocument1 doc, Id pid,
			SourcePatientInfo patientInfo, PatientContact[] patientContacts) {
		POCDMT000040RecordTarget rt = doc.addNewRecordTarget();
		POCDMT000040PatientRole pr = rt.addNewPatientRole();
		II id = pr.addNewId();
		buildId(id, new Id(pid.getRoot(), pid.getExtension()));

		Address[] addresses = patientInfo.getAddress();
		if (addresses == null || addresses.length == 0) { // Address is
															// required
			AD addr = pr.addNewAddr();
			addr.setNullFlavor("UNK");
		} else {
			for (Address address : addresses) {
				AD addr = pr.addNewAddr();
				buildAddress(addr, address);
			}
		}

		PhoneNumber[] phones = patientInfo.getPhoneNumber();
		if (phones == null || phones.length == 0) {// TEL is required by CRS
			TEL tel = pr.addNewTelecom();
			buildTelecom(tel, null);
		}
		for (PhoneNumber phone : phones) {
			TEL tel = pr.addNewTelecom();
			buildTelecom(tel, phone);
		}

		Organization org = patientInfo.getOrganization();
		if (org != null) {
			buildOrganzation(pr.addNewProviderOrganization(), org);
		}

		POCDMT000040Patient patient = pr.addNewPatient();
		buildPatient(patient, patientInfo, patientContacts);
	}

	private void buildTelecom(TEL tel, String value, String use) {
		if (!StringUtil.goodString(value)) {
			tel.setNullFlavor("UNK");
		} else {
			// Phone number cannot take white space.
			tel.setValue("tel:" + value.replaceAll(" ", ""));
		}
		// List list = new ArrayList();
		// list.add("WP");
		// TelecommunicationAddressUse.Member.Enum use =
		// TelecommunicationAddressUse.Member.Enum.forString("WP");
		// list.add( use );
		// tel.setUse(list);
		if (StringUtil.goodString(use)) {
			XmlCursor cursor = tel.newCursor();
			cursor.toNextToken();
			cursor.insertAttributeWithValue("use", use);
		}
	}

	private void buildTelecom(TEL tel, PhoneNumber phone) {
		if (phone == null || phone.getNumber() == null) {
			tel.setNullFlavor("UNK");
			return;
		}
		String number = "";
		if (phone.getCountryCode() != null
				&& phone.getCountryCode().trim().length() > 0)
			number += "+" + phone.getCountryCode();
		if (phone.getAreaCode() != null
				&& phone.getAreaCode().trim().length() > 0)
			number += "(" + phone.getAreaCode() + ")";
		number += "-" + phone.getNumber();
		if (phone.getExtension() != null
				&& phone.getExtension().trim().length() > 0) {
			number += " ext " + phone.getExtension();
		}
		tel.setValue("tel:" + number);

		if (phone.getType() != null) {
			PhoneType type = phone.getType();
			XmlCursor cursor = tel.newCursor();
			cursor.toNextToken();
			cursor.insertAttributeWithValue("use", type.getCDAValue());
		}

	}

	private void buildAddress(AD addr, Address address) {
		if (null == address) {
			addr.setNullFlavor("UNK");
		} else {
			String addLines = (StringUtil.goodString(address.getAddLine1()) ? " "
					+ address.getAddLine1()
					: "")
					+ (StringUtil.goodString(address.getAddLine2()) ? " "
							+ address.getAddLine2() : "");
			if (StringUtil.goodString(addLines))
				XmlBeanUtil.addElementWithText(addr, "streetAddressLine",
						addLines);
			if (StringUtil.goodString(address.getAddCity()))
				XmlBeanUtil.addElementWithText(addr, "city", address
						.getAddCity());
			if (StringUtil.goodString(address.getAddState()))
				XmlBeanUtil.addElementWithText(addr, "state", address
						.getAddState());
			if (StringUtil.goodString(address.getAddZip()))
				XmlBeanUtil.addElementWithText(addr, "postalCode", address
						.getAddZip());
			// country is required by schematron testing
			if (address.getAddCountry() != null
					&& address.getAddCountry().length() > 0)
				XmlBeanUtil.addElementWithText(addr, "country", address
						.getAddCountry());
		}
	}

	private void buildOrganzation(POCDMT000040Organization organization,
			Organization org) {
		if (org != null) {
			Id id = org.getId();
			if (id == null) {
				id = new Id(OID_ROOT, OID.getUID(OID.IDType.ORGANIZATION));
				org.setId(id);
			}
			buildId(organization.addNewId(), new Id(id.getRoot(), id
					.getExtension()));
		}
		if (org.getOrganizationName() != null) {
			ON name = organization.addNewName();
			XmlBeanUtil.addText(name, org.getOrganizationName());
		} else {
			ON name = organization.addNewName();
			name.setNullFlavor("UNK");
			log.warn("Oragniazation does not have a valid name.");
		}

		if (org != null) {
			PhoneNumber[] phones = org.getPhoneNumbers();
			if (phones == null) {
				buildTelecom(organization.addNewTelecom(), null);
			} else {
				for (PhoneNumber phone : phones) {
					buildTelecom(organization.addNewTelecom(), phone);
				}
			}

			Address[] addresses = org.getAddress();
			if (addresses == null) {
				buildAddress(organization.addNewAddr(), null);
			} else {
				for (Address add : addresses) {
					buildAddress(organization.addNewAddr(), add);
				}
			}
		}
	}

	private void buildPatient(POCDMT000040Patient patient,
			SourcePatientInfo patientInfo, PatientContact[] patientContacts) {
		// add the patient's name
		// note that a patient can have multiple names, but the addNewXXXX
		// methods work the same, hiding the underlying arrays
		// Similarly, add the name parts to the name
		// To add the content to the name parts, use
		// XmlBeanUtil.addText(<name part object>,<string>);
		PersonName patientName = patientInfo.getPersonName();
		PN newPN = patient.addNewName();
		EnGiven gName = newPN.addNewGiven();
		XmlBeanUtil.addText(gName, patientName.getFirstName());

		EnFamily fName = newPN.addNewFamily();
		XmlBeanUtil.addText(fName, patientName.getLastName());

		EnPrefix prefix = newPN.addNewPrefix();
		XmlBeanUtil.addText(prefix, patientName.getPrefix());

		EnSuffix suff = newPN.addNewSuffix();
		XmlBeanUtil.addText(suff, patientName.getSuffix());

		// Add the gender code to the patient object, using the addNewXXXX
		// method and setting the code and code system.
		// use "2.16.840.1.113883.5.1" for the Code System
		hl7OrgV3.CE ce = patient.addNewAdministrativeGenderCode();
		SharedEnums.SexType eSexType = patientInfo.getGender();
		if (eSexType == null) {
			eSexType = SharedEnums.SexType.UNKNOWN;
		}
		buildCodeSystem(ce, new CodeSystem(eSexType.getCDAValue(),
				"2.16.840.1.113883.5.1", "AdministrativeGender", eSexType
						.getValue()));
		SharedEnums.MartitalStatusType maritalStatus = patientInfo
				.getMaritalStatus();
		if (maritalStatus == null) {
			maritalStatus = SharedEnums.MartitalStatusType.UNKNOWN;
		}
		buildCodeSystem(patient.addNewMaritalStatusCode(), new CodeSystem(
				maritalStatus.getHL7Value(), "2.16.840.1.113883.5.2",
				"MaritalStatus", maritalStatus.getValue()));
		// Add the birthTime to the patient object, using the addNewXXXX
		// method and seting the value
		hl7OrgV3.TS ts = patient.addNewBirthTime();
		if (patientInfo.getBirthdate() == null) {
			ts.setNullFlavor("UNK");
		} else {
			ts.setValue(dfShort2.format(patientInfo.getBirthdate().getTime()));
		}

		// build patient guardian
		buildPatientGuardian(patient, patientContacts);
	}

	private void buildPatientGuardian(POCDMT000040Patient patient,
			PatientContact[] guardians) {
		if (guardians == null || guardians.length == 0)
			return;

		for (PatientContact g : guardians) {
			if (g.getPatientContactType() == SharedEnums.PatientContactType.GUARDIAN) {
				POCDMT000040Guardian guardian = patient.addNewGuardian();
				guardian.setClassCode(g.getPatientContactType().getCdaValue());
				II tid = guardian.addNewTemplateId();
				tid.setRoot(TID_PATIENT_CONTACT);
				buildCodeSystem(guardian.addNewCode(), g
						.getPersonalRelationshipRoleType());
				Address[] addrs = g.getParticipant().getAddress();
				if (addrs == null)
					buildAddress(guardian.addNewAddr(), null);
				else {
					for (Address addr : addrs) {
						buildAddress(guardian.addNewAddr(), addr);
					}
				}
			}
		}
	}

	void buildAuthor(POCDMT000040ClinicalDocument1 doc, Participant user) {
		if (null != user) {
			buildUser(doc, user);
		}
	}

	public void buildAuthor(POCDMT000040ClinicalDocument1 doc,
			Author[] authors, CodeSystem authorDeviceCode) {
		if (authors == null && authors.length == 0)
			return;
		for (Author a : authors) {
			POCDMT000040Author author = doc.addNewAuthor();
			Calendar startTime = a.getStartParticipationTime();
			TS time = author.addNewTime();
			if (startTime != null) {
				time.setValue(dateToString(startTime, dfLong));
			} else {
				// time.setNullFlavor("UNK");
				time.setValue(dateToString(new GregorianCalendar(), dfLong));
			}

			POCDMT000040AssignedAuthor assAuthor = author
					.addNewAssignedAuthor();
			II id = assAuthor.addNewId();
			Participant authorPerson = a.getAuthorPerson();

			// CE code = assAuthor.addNewCode();
			// TODO: set relationship
			// buildCodeSystem(code, new CodeSystem("SELF",
			// "2.16.840.1.113883.5.111", "PersonalRelationshipRoleType",
			// "Self"));

			if (authorPerson != null) {
				Id authorId = null;
				if ( authorPerson.getId() == null) {
					authorId = new Id(OID_ROOT, OID.getUID(OID.IDType.PARTICIPANT));
					authorPerson.setId(authorId);
				} else  {
					authorId = authorPerson.getId();
				}
				buildId(id, authorId);

				Address[] addrs = authorPerson.getAddress();
				if (addrs != null) {
					for (Address ad : addrs) {
						buildAddress(assAuthor.addNewAddr(), ad);
					}
				} else {
					buildAddress(assAuthor.addNewAddr(), null);
				}
				PhoneNumber[] phones = authorPerson.getPhoneNumbers();
				if (phones != null) {
					for (PhoneNumber phone : phones) {
						buildTelecom(assAuthor.addNewTelecom(), phone);
					}
				} else {
					buildTelecom(assAuthor.addNewTelecom(), null);
				}
				PersonName personName = authorPerson.getPersonName();
				if (personName != null)
					buildPersonName(assAuthor.addNewAssignedPerson(),
							authorPerson.getPersonName());
			} else {
				buildAddress(assAuthor.addNewAddr(), null);
				buildTelecom(assAuthor.addNewTelecom(), null);
			}

			// build Organization
			if (a.getOrganization() != null) {
				POCDMT000040Organization org = assAuthor
						.addNewRepresentedOrganization();
				buildOrganzation(org, a.getOrganization());
			}
			// build AuthoringDevice
			if (a.getAuthorDevice() != null) {
				POCDMT000040AuthoringDevice ad = assAuthor
						.addNewAssignedAuthoringDevice();
				buildCodeSystem(ad.addNewCode(), authorDeviceCode);
				// add manufacturerModelName
				SC manufacturer = ad.addNewManufacturerModelName();
				XmlBeanUtil.addText(manufacturer, a.getAuthorDevice()
						.getManufacturerModelName());
				// add device software name
				SC software = ad.addNewSoftwareName();
				XmlBeanUtil.addText(software, a.getAuthorDevice()
						.getSoftwareName());
			}
		}
	}

	private void buildUser(POCDMT000040ClinicalDocument1 doc, Participant user) {
		POCDMT000040Author author = doc.addNewAuthor();
		TS time = author.addNewTime();
		time.setValue(dfLong.format(new GregorianCalendar().getTime()));

		POCDMT000040AssignedAuthor assAuthor = author.addNewAssignedAuthor();
		II id = assAuthor.addNewId();
		buildId(id, user.getId());

		CE code = assAuthor.addNewCode();
		// TODO: set relationship
		buildCodeSystem(code, new CodeSystem("SELF", "2.16.840.1.113883.5.111",
				"PersonalRelationshipRoleType", "Self"));

		Address[] addresses = user.getAddress();
		if (null == addresses) {
			AD addr = assAuthor.addNewAddr();
			addr.setNullFlavor("UNK");
		} else {
			for (int i = 0; i < addresses.length; i++) {
				AD addr = assAuthor.addNewAddr();
				buildAddress(addr, addresses[i]);
			}
		}

		PhoneNumber[] phones = user.getPhoneNumbers();
		if (phones == null) {
			TEL tel = assAuthor.addNewTelecom();
			tel.setNullFlavor("UNK");
		} else {
			for (int i = 0; i < phones.length; i++) {
				TEL tel = assAuthor.addNewTelecom();
				buildTelecom(tel, phones[i]);
			}
		}
		POCDMT000040Person person = assAuthor.addNewAssignedPerson();
		buildPersonName(person, user.getPersonName());
	}

	void buildAuthorDevice(POCDMT000040ClinicalDocument1 doc,
			IConnectionDescription conn, boolean required) {
		ObjectEntry object = conn.getObject("AuthorDevice");
		if (object == null) {
			if (required) {
				log.error("Missing required Author Device.");
				POCDMT000040Author author = doc.addNewAuthor();
				author.setNullFlavor("UNK");
			}
			return;
		}

		POCDMT000040Author author = doc.addNewAuthor();
		TS time = author.addNewTime();
		time.setValue(dfLong.format(new GregorianCalendar().getTime()));

		POCDMT000040AssignedAuthor assAuthor = author.addNewAssignedAuthor();
		II id = assAuthor.addNewId();
		buildId(id, new Id(OID_ROOT, MISYS_ID));

		AD addr = assAuthor.addNewAddr();
		Address address = new Address();
		address.setAddType(SharedEnums.AddressType.WORK);
		address.setAddLine1(object.getStringValue("addressLine1"));
		address.setAddLine2(null);
		address.setAddCity(object.getStringValue("addressCity"));
		address.setAddState(object.getStringValue("addressState"));
		address.setAddCountry(object.getStringValue("addressCountry"));
		address.setAddZip(object.getStringValue("addressZip"));
		buildAddress(addr, address);

		TEL tel = assAuthor.addNewTelecom();
		buildTelecom(tel, object.getStringValue("telephone"), "WP");

		POCDMT000040AuthoringDevice device = assAuthor
				.addNewAssignedAuthoringDevice();
		// build device code
		ObjectEntry.CodeSystem cs = object.getCodeValue("code");
		if (cs != null) {
			CE code = device.addNewCode();
			buildCodeSystem(code, CodeSystem.convertFrom(cs));
		}
		// add manufacturerModelName
		SC manufacturer = device.addNewManufacturerModelName();
		XmlBeanUtil.addText(manufacturer, object
				.getStringValue("manufacturerModelName"));
		// add device software name
		SC software = device.addNewSoftwareName();
		XmlBeanUtil.addText(software, object.getStringValue("softwareName"));

		// Add AssignedOrganization
		String orgId = object.getStringValue("organizationId");
		if (StringUtil.goodString(orgId)) {
			POCDMT000040Organization organization = assAuthor
					.addNewRepresentedOrganization();
			II idOrg = organization.addNewId();
			idOrg.setRoot(orgId);
			// add org name
			String orgName = object.getStringValue("organizationName");
			ON org = organization.addNewName();
			XmlBeanUtil.addText(org, orgName);
			// Add org address
			AD ad = organization.addNewAddr();
			address.setAddLine1(object
					.getStringValue("organizationAddressLine1"));
			address.setAddLine2(null);
			address
					.setAddCity(object
							.getStringValue("organizationAddressCity"));
			address.setAddState(object
					.getStringValue("organizationAddressState"));
			address.setAddCountry(object
					.getStringValue("organizationAddressCountry"));
			address.setAddZip(object.getStringValue("organizationAddressZip"));
			buildAddress(ad, address);
			// add org telecom
			TEL orgTel = organization.addNewTelecom();
			buildTelecom(orgTel, object.getStringValue("telephone"), "WP");
		}
	}

	public void buildDataEnterer(POCDMT000040ClinicalDocument1 doc,
			DataEnterer[] dataEnterers, boolean required) {
		if ((dataEnterers == null || dataEnterers.length == 0) && !required)
			return;

		if ((dataEnterers == null || dataEnterers.length == 0) && required) {
			// log.warn("Missing required DataEnterer");
			POCDMT000040DataEnterer enterer = doc.addNewDataEnterer();
			enterer.setNullFlavor("UNK");
			return;
		}
		for (DataEnterer dataEnterer : dataEnterers) {
			POCDMT000040DataEnterer enterer = doc.addNewDataEnterer();
			// Set Time
			TS time = enterer.addNewTime();
			if (dataEnterer.getTime() == null) {
				// time.setNullFlavor("UNK");
				time.setValue(dfLong.format(new GregorianCalendar().getTime()));
			} else {
				time.setValue(dfLong.format(dataEnterer.getTime().getTime()));
			}
			// Set Assigned Entity Id
			POCDMT000040AssignedEntity entity = enterer.addNewAssignedEntity();
			II id = entity.addNewId();
			if (dataEnterer.getAssignedEntityId() != null)
				buildId(id, dataEnterer.getAssignedEntityId());
			else {
				Id assignedEntityId = new Id(OID_ROOT, OID
						.getUID(OID.IDType.PARTICIPANT));
				dataEnterer.setAssignedEntityId(assignedEntityId);
				buildId(id, assignedEntityId);
			}

			// Set Assigned Person Name
			POCDMT000040Person person = entity.addNewAssignedPerson();
			buildPersonName(person, dataEnterer.getPersonName());
			
			AD ad = entity.addNewAddr();
			ad.setNullFlavor("UNK");
			TEL tel = entity.addNewTelecom();
			tel.setNullFlavor("UNK");
		}
	}

	public void buildCustodian(POCDMT000040ClinicalDocument1 doc,
			Organization custodian, boolean required) {
		if (custodian == null && !required)
			return;

		POCDMT000040Custodian cust = doc.addNewCustodian();
		POCDMT000040AssignedCustodian assCust = cust.addNewAssignedCustodian();
		POCDMT000040CustodianOrganization custOrg = assCust
				.addNewRepresentedCustodianOrganization();
		if (custodian == null) {
			custOrg.setNullFlavor("UNK");
			return;
		}

		if (custodian != null) {
			Id id = custodian.getId();
			if (id == null) {
				id = new Id(OID_ROOT, OID.getUID(OID.IDType.ORGANIZATION));
				custodian.setId(id);
			}
			buildId(custOrg.addNewId(), new Id(id.getRoot(), id.getExtension()));
		}
		if (custodian.getOrganizationName() != null) {
			ON name = custOrg.addNewName();
			XmlBeanUtil.addText(name, custodian.getOrganizationName());
		}
		if (custodian != null) {
			PhoneNumber[] phones = custodian.getPhoneNumbers();
			if (phones == null) {
				buildTelecom(custOrg.addNewTelecom(), null);
			} else {
				for (PhoneNumber phone : phones) {
					buildTelecom(custOrg.addNewTelecom(), phone);
				}
			}

			Address[] addresses = custodian.getAddress();
			if (addresses == null) {
				buildAddress(custOrg.addNewAddr(), null);
			} else {
				for (Address add : addresses) {
					buildAddress(custOrg.addNewAddr(), add);
				}
			}
		}
	}

	// required
	void buildCustodian(POCDMT000040ClinicalDocument1 doc,
			IConnectionDescription conn, boolean required) {
		ObjectEntry object = conn.getObject("Custodian");
		if (object == null) {
			if (required) {
				log.error("Missing required Custodian.");
				POCDMT000040Custodian custodian = doc.addNewCustodian();
				custodian.setNullFlavor("UNK");
			}
			return;
		}

		POCDMT000040Custodian custodian = doc.addNewCustodian();
		POCDMT000040AssignedCustodian assCust = custodian
				.addNewAssignedCustodian();
		POCDMT000040CustodianOrganization org = assCust
				.addNewRepresentedCustodianOrganization();
		II id = org.addNewId();
		buildId(id, new Id(OID_ROOT, MISYS_ID));

		ON name = org.addNewName();
		XmlBeanUtil.addText(name, object.getStringValue("name"));
		TEL tel = org.addNewTelecom();
		buildTelecom(tel, object.getStringValue("telephone"), "WP");

		AD addr = org.addNewAddr();
		Address address = new Address();
		address.setAddType(SharedEnums.AddressType.WORK);
		address.setAddLine1(object.getStringValue("addressLine1"));
		address.setAddCity(object.getStringValue("addressCity"));
		address.setAddState(object.getStringValue("addressState"));
		address.setAddCountry(object.getStringValue("addressCountry"));
		address.setAddZip(object.getStringValue("addressZip"));
		buildAddress(addr, address);
	}

	void buildLegalAuthenticator(POCDMT000040ClinicalDocument1 doc,
			Participant authenticator) {
		if (authenticator == null)
			return;

		POCDMT000040LegalAuthenticator lAuthenticator = doc
				.addNewLegalAuthenticator();
		TS time = lAuthenticator.addNewTime();
		time.setValue(dateToString(Calendar.getInstance(), dfLong));
		CS signatureCode = lAuthenticator.addNewSignatureCode();
		signatureCode.setCode("S");

		POCDMT000040AssignedEntity entity = lAuthenticator
				.addNewAssignedEntity();
		buildAssignedEntity(entity, authenticator);
	}

	void buildAuthenticator(POCDMT000040ClinicalDocument1 doc, Participant p) {
		POCDMT000040Authenticator authenticator = doc.addNewAuthenticator();
		// lAuthenticator.addNewTime(); //TODO: Optional Time
		CS signatureCode = authenticator.addNewSignatureCode();
		signatureCode.setCode("S");

		POCDMT000040AssignedEntity entity = authenticator
				.addNewAssignedEntity();
		buildAssignedEntity(entity, p);
	}

	private void buildAssignedEntity(POCDMT000040AssignedEntity entity,
			Participant p) {
		II id = entity.addNewId();
		if (p.getId() == null) {
			Id participantId = new Id(OID_ROOT, OID
					.getUID(OID.IDType.PARTICIPANT));
			buildId(id, participantId);
		} else
			buildId(id, p.getId());

		Address[] addresses = p.getAddress();
		if (null == addresses) {
			AD addr = entity.addNewAddr();
			addr.setNullFlavor("UNK");
		} else {
			for (int i = 0; i < addresses.length; i++) {
				AD addr = entity.addNewAddr();
				buildAddress(addr, addresses[i]);
			}
		}

		PhoneNumber[] phones = p.getPhoneNumbers();
		if (null == phones) {
			TEL tel = entity.addNewTelecom();
			tel.setNullFlavor("UNK");
		} else {
			for (int i = 0; i < phones.length; i++) {
				TEL tel = entity.addNewTelecom();
				buildTelecom(tel, phones[i]);
			}
		}

		POCDMT000040Person person = entity.addNewAssignedPerson();
		buildPersonName(person, p.getPersonName());
	}

	private void buildId(II id, Id idData) {
		buildId(id, idData, true);
	}

	/**
	 * Builds an OID
	 * 
	 * @param id
	 *            the ID container
	 * @param idData
	 *            the data source of the ID to be built
	 * @param useExtension
	 *            whether to build OID extension
	 */
	private void buildId(II id, Id idData, boolean useExtension) {
		if (useExtension) {
			id.setExtension(idData.getExtension());
			id.setRoot(idData.getRoot());
		} else {
			String root = idData.getRoot() + "." + idData.getExtension();
			id.setRoot(root); 
		}
	}

	private void buildTemplateId(POCDMT000040Section section, String id) {
		II tid = section.addNewTemplateId();
		tid.setRoot(id);
	}

	private void buildTemplateId(POCDMT000040Act act, String id) {
		II tid = act.addNewTemplateId();
		tid.setRoot(id);
	}

	private void buildTemplateId(POCDMT000040Observation observation, String id) {
		II tid = observation.addNewTemplateId();
		tid.setRoot(id);
	}

	private void buildTemplateId(POCDMT000040SubstanceAdministration sa,
			String id) {
		II tid = sa.addNewTemplateId();
		tid.setRoot(id);
	}

	private void buildTemplateId(POCDMT000040Organizer organization, String id) {
		II tid = organization.addNewTemplateId();
		tid.setRoot(id);
	}

	private void buildPersonName(POCDMT000040Person person, PersonName pName) {
		if (pName == null) {
			return;
		}

		PN name = person.addNewName();
		EnGiven firstName = name.addNewGiven();
		if (StringUtil.goodString(pName.getFirstName()))
			XmlBeanUtil.addText(firstName, pName.getFirstName());
		EnGiven middleName = name.addNewGiven();
		if (StringUtil.goodString(pName.getMiddleName()))
			XmlBeanUtil.addText(middleName, pName.getMiddleName());
		EnFamily lastName = name.addNewFamily();
		if (StringUtil.goodString(pName.getLastName()))
			XmlBeanUtil.addText(lastName, pName.getLastName());
		EnPrefix prefix = name.addNewPrefix();
		if (StringUtil.goodString(pName.getPrefix()))
			XmlBeanUtil.addText(prefix, pName.getPrefix());
		EnSuffix suffix = name.addNewSuffix();
		if (StringUtil.goodString(pName.getSuffix()))
			XmlBeanUtil.addText(suffix, pName.getSuffix());
	}

	public void buildPatientContact(POCDMT000040ClinicalDocument1 doc,
			PatientContact[] contacts) {
		if (contacts == null || contacts.length == 0)
			return;
		for (PatientContact contact : contacts) {
			if (contact.getPatientContactType() == SharedEnums.PatientContactType.GUARDIAN)
				continue; // Guardian is built under the patient

			POCDMT000040Participant1 participant = doc.addNewParticipant();
			participant.setTypeCode("IND");
			II tid = participant.addNewTemplateId();
			tid.setRoot(TID_PATIENT_CONTACT);
			if (contact.getEffectiveTime() != null) {
				IVLTS time = participant.addNewTime();
				EffectiveTime effectiveTime = contact.getEffectiveTime();
				if (effectiveTime.getStart() != null)
					time.setValue(dateToString(effectiveTime.getStart(),
							dfShort2));
				// time.addNewLow().setValue(dateToString(effectiveTime.getStart(),
				// dfShort2));
				// if (effectiveTime.getEnd() != null)
				// time.addNewHigh().setValue(dateToString(effectiveTime.getEnd(),
				// dfShort2));
			}
			
			POCDMT000040AssociatedEntity entity = participant
					.addNewAssociatedEntity();
			entity.setClassCode(contact.getPatientContactType().getCdaValue());
			buildAssociatedEntity(entity, contact.getParticipant());
			// add PersonalRelationshipRoleCode to the patientContact
			buildCodeSystem(entity.addNewCode(), contact
					.getPersonalRelationshipRoleType());
		}
	}

	public void buildParticipant(POCDMT000040ClinicalDocument1 doc,
			Participant p) {
		POCDMT000040Participant1 participant = doc.addNewParticipant();
		// II tid = participant.addNewTemplateId();
		// tid.setRoot(TID_PATIENT_CONTACT);
		// participant.addNewTime(); //TODO: Optional Time

		POCDMT000040AssociatedEntity entity = participant
				.addNewAssociatedEntity();
		buildAssociatedEntity(entity, p);
	}

	private void buildAssociatedEntity(POCDMT000040AssociatedEntity entity,
			Participant p) {
		if (p == null) {
			entity.setNullFlavor("UNK");
			return;
		}
		II id = entity.addNewId();
		buildId(id, p.getId());
		Address[] addresses = p.getAddress();
		if (null == addresses) {
			AD addr = entity.addNewAddr();
			addr.setNullFlavor("UNK");
		} else {
			for (int i = 0; i < addresses.length; i++) {
				AD addr = entity.addNewAddr();
				buildAddress(addr, addresses[i]);
			}
		}

		PhoneNumber[] phones = p.getPhoneNumbers();
		TEL tel = entity.addNewTelecom();
		if (phones == null) {
			buildTelecom(tel, null);
		}
		for (int i = 0; i < phones.length; i++) {
			buildTelecom(tel, phones[i]);
		}

		POCDMT000040Person person = entity.addNewAssociatedPerson();
		buildPersonName(person, p.getPersonName());
	}

	void buildGuardian(POCDMT000040ClinicalDocument1 doc, Participant p) {
		;
	}

	// build insurance information, a special kind of Participant
	void buildInsuranceInformation(POCDMT000040ClinicalDocument1 doc,
			Insurance[] insurances) {

		if (null == insurances || insurances.length == 0)
			return;

		for (Insurance insurance : insurances) {
			POCDMT000040Participant1 participant = doc.addNewParticipant();
			participant.setTypeCode("HLD");

			// Set time
			EffectiveDates eDates = insurance.getEffectiveDates();
			if (null != eDates) {
				IVLTS time = participant.addNewTime();
				
				if (null != eDates.getStart()) {
					time.setValue(dfLong.format(eDates.getStart().getTime()));
//					IVXBTS low = time.addNewLow();
//					low.setValue(dfLong.format(eDates.getStart().getTime()));
				}
//				if (null != eDates.getEnd()) {
//					IVXBTS high = time.addNewHigh();
//					high.setValue(dfLong.format(eDates.getEnd().getTime()));
//				}
			}
			// build Assigned Entity
			POCDMT000040AssociatedEntity entity = participant
					.addNewAssociatedEntity();
			entity.setClassCode("POLHOLD");
			II id = entity.addNewId();
			buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.ORGANIZATION)));

			CE code = entity.addNewCode();
			// String fromSystem = pData.getHomeSystem();
			String relation = insurance.getSubscriberRelationToPatient();
			// TODO: need to find out the syntax of the self relation? Assume it
			// is "SELF" by default
			if (relation != null && relation.equalsIgnoreCase("PHFAMDEP")) {
				buildCodeSystem(code, new CodeSystem("PHFAMDEP",
						"2.16.840.1.113883.5.1095", "PolicyHolderRole",
						"Policy holder for a family dependent"));
			} else /*
					 * if (relation ==
					 * SharedEnums.SubscriberToPatientRelationshipType.SELF )
					 */{
				buildCodeSystem(code, new CodeSystem("SELF",
						"2.16.840.1.113883.5.111",
						"PersonalRelationshipRoleType", "Self"));
			}

			AD addr = entity.addNewAddr();
			SubscriberNameAddress sNameAddress = insurance
					.getSubscriberNameAddess();
			Address address = sNameAddress.getAddress();
			buildAddress(addr, address);

			TEL tel = entity.addNewTelecom();
			buildTelecom(tel, null); // TODO: ask whether we can get this
										// info from Misys systems.

			POCDMT000040Person person = entity.addNewAssociatedPerson();
			PersonName pName = new PersonName(sNameAddress.getNameFirst(),
					sNameAddress.getNameLast(), null, sNameAddress
							.getNameTitle(), sNameAddress.getNameSuffix());
			buildPersonName(person, pName);

			// build scope organization
			POCDMT000040Organization org = entity.addNewScopingOrganization();
			ON name = org.addNewName();
			XmlBeanUtil.addText(name, insurance.getName());
			tel = org.addNewTelecom();
			buildTelecom(tel, null); // TODO: ask whether we can get this
										// info from Misys systems.

			AD insAddr = org.addNewAddr();
			Address insAddress = insurance.getAddress();
			buildAddress(insAddr, insAddress);
		}// for
	}

	// required header.

	/**
	 * Builds the header component DocumentOf.
	 * 
	 * @param doc
	 * @param effectiveTime
	 *            the length must be two. The first one is the start time, and
	 *            the second the end time.
	 * @param performers
	 * @param isConsentDoc
	 *            whether to build DocumentOf for consent document
	 */
	void buildDocumentOf(POCDMT000040ClinicalDocument1 doc,
			Calendar serviceStartTime, Calendar serviceStopTime,
			Performer[] performers, boolean isConsentDoc) {
		POCDMT000040DocumentationOf docOf = doc.addNewDocumentationOf();
		if (isConsentDoc)
			docOf.setTypeCode("DOC");
		POCDMT000040ServiceEvent serviceEvent = docOf.addNewServiceEvent();
		if (isConsentDoc) {
			serviceEvent.setClassCode("ACT");
			serviceEvent.setMoodCode("EVN");
			II tid = serviceEvent.addNewTemplateId();
			tid.setRoot(TID_CONSENT_SERVICE_EVENT);
		} else {
			serviceEvent.setClassCode("PCPR");
		}

		II id = serviceEvent.addNewId();
		// BPPC consent doc does not allow ID use extension
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.SERVICE_EVENT)),
				false);

		// TODO: for BPPC, add code
		// buildCodeSystem(code, codeData)
		// TODO: metadata.setEventCodelist()

		// Effective time with low and high are required by CRS
		IVLTS time = serviceEvent.addNewEffectiveTime();
		IVXBTS low = time.addNewLow();
		if (serviceStartTime == null) {
			low.setNullFlavor("UNK");
		} else {
			low.setValue(dfLong.format(serviceStartTime.getTime()));
		}
		IVXBTS high = time.addNewHigh();
		if (serviceStopTime == null) {
			if (serviceStartTime != null) {
				high.setValue(dfLong.format(serviceStartTime.getTime()));
			}else {
				high.setNullFlavor("UNK");				
			}
		} else {
			high.setValue(dfLong.format(serviceStopTime.getTime()));
		}

		// ServiceEvent should have at least one Performer
		if (performers == null || performers.length == 0) {
			log
					.warn("At least one performer for this document service is needed");
			POCDMT000040Performer1 performer1 = serviceEvent.addNewPerformer();
			performer1.setNullFlavor("UNK");
		} else {
			for (Performer performer : performers) {
				POCDMT000040Performer1 performer1 = serviceEvent
						.addNewPerformer();
				buildPerformer(performer1, performer);
			}
		}
	}

	private void buildPerformer(POCDMT000040Performer1 performer,
			Performer perfomerData) {
		performer.setTypeCode(XServiceEventPerformer.PRF);
		II tid = performer.addNewTemplateId();
		tid.setRoot(TID_HEALTHCARE_PROVIDER);

		CE functionalCode = performer.addNewFunctionCode();
		// Code come from ParticipationFunction vocabulary domain
		buildCodeSystem(functionalCode, perfomerData.getFunctionCode());

		if (perfomerData.getStartTime() != null
				|| perfomerData.getEndTime() != null) {
			IVLTS time = performer.addNewTime();
			if (perfomerData.getStartTime() != null) {
				IVXBTS low = time.addNewLow();
				low.setValue(dfLong.format(perfomerData.getStartTime()
						.getTime()));
			}
			if (perfomerData.getEndTime() != null) {
				IVXBTS high = time.addNewHigh();
				high.setValue(dfLong
						.format(perfomerData.getEndTime().getTime()));
			}
		}

		POCDMT000040AssignedEntity entity = performer.addNewAssignedEntity();
		if (null != perfomerData.getCode()) {
			CE code = entity.addNewCode();
			buildCodeSystem(code, perfomerData.getCode());
		}
		buildAssignedEntity(entity, perfomerData);
	}

	private void buildCodeSystem(CD code, CodeSystem codeData) {
		// by default, nullFlavor use "UNK"
		buildCodeSystem(code, codeData, "UNK");
	}

	private void buildCodeSystem(CD code, CodeSystem codeData, String nullFlavor) {
		if (null == codeData || !StringUtil.goodString(codeData.getCode())) {
			// log.info("Null Code");
			code.setNullFlavor(nullFlavor);
			return;
		}
		if (!StringUtil.goodString(codeData.getCodeSystem())) {
			// log.info("Null Code System");
			code.setNullFlavor(nullFlavor);
			return;
		}
		code.setCode(codeData.getCode());
		code.setCodeSystem(codeData.getCodeSystem());
		if (StringUtil.goodString(codeData.getCodeSystemName()))
			code.setCodeSystemName(codeData.getCodeSystemName());
		if (StringUtil.goodString(codeData.getDisplayName()))
			code.setDisplayName(codeData.getDisplayName());
	}

	// required for Discharge and Transfer Summary
	// For Discharge Summary, need to add discharge disposition code
	public void buildComponentOf(POCDMT000040ClinicalDocument1 doc,
			Encounter[] encounters, boolean required,
			boolean requireDischargeCode) {
		if (encounters == null || encounters.length == 0) {
			if (required) {
				POCDMT000040Component1 componentOf = doc.addNewComponentOf();
				componentOf.setNullFlavor("UNK");
				log.warn("No Encounter; cannot build required ComponentOf ");
			}
			return;
		}

		POCDMT000040Component1 componentOf = doc.addNewComponentOf();
		for (Encounter visit : encounters) {
			POCDMT000040EncompassingEncounter encounter = componentOf
					.addNewEncompassingEncounter();
			II id = encounter.addNewId();
			Id visitId = visit.getEncounterId();
			if (visitId == null) {
				visitId = new Id(OID_ROOT, OID.getUID(OID.IDType.ENCOUNTER));
				visit.setEncounterId(visitId);
			}
			buildId(id, visitId);

			IVLTS effictiveTime = encounter.addNewEffectiveTime();
			Calendar lowDate = visit.getStartTime();
			if (null == lowDate) {
				IVXBTS low = effictiveTime.addNewLow();
				low.setNullFlavor("UNK");
			} else {
				IVXBTS low = effictiveTime.addNewLow();
				low.setValue(dfLong.format(lowDate.getTime()));
			}
			Calendar highDate = visit.getEndTime();
			if (null != highDate) {
				IVXBTS high = effictiveTime.addNewHigh();
				high.setValue(dfLong.format(highDate.getTime()));
			}

			if (requireDischargeCode) {
				CE code = encounter.addNewDischargeDispositionCode();
				SharedEnums.DischargeDispositionCode ddc = visit
						.getDischargeDispositionCode();
				CodeSystem cs = new CodeSystem(ddc.getCode(), ddc
						.getCodeSystem(), ddc.getCodeSystemName(), ddc
						.getDescription());
				buildCodeSystem(code, cs);
			}
		}
	}

	// required for Discharge Summary
	void buildComponentOf(POCDMT000040ClinicalDocument1 doc,
			Calendar admissionDate, Calendar dischargeDate) {
		POCDMT000040Component1 componentOf = doc.addNewComponentOf();
		POCDMT000040EncompassingEncounter encounter = componentOf
				.addNewEncompassingEncounter();
		II id = encounter.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.ENCOUNTER)));

		IVLTS effictiveTime = encounter.addNewEffectiveTime();
		if (null == admissionDate) {
			log.warn("Missing admission date");
		} else {
			IVXBTS low = effictiveTime.addNewLow();
			low.setValue(dfShort2.format(admissionDate.getTime()));
		}
		if (null == dischargeDate) {
			log.warn("Missing discharge date");
		} else {
			IVXBTS high = effictiveTime.addNewHigh();
			high.setValue(dfShort2.format(dischargeDate.getTime()));
		}
	}

	// Current used to build Consent Document
	void buildAuthorization(POCDMT000040ClinicalDocument1 doc,
			CodeSystem codeData) {
		POCDMT000040Authorization authorization = doc.addNewAuthorization();
		authorization.setTypeCode("AUTH");
		POCDMT000040Consent consent = authorization.addNewConsent();
		consent.setClassCode("CONS");
		consent.setMoodCode("EVN");

		II tid = consent.addNewTemplateId();
		tid.setRoot(TID_AUTHORIZATION);
		// Add id
		II id = consent.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.CONSCENT)));
		// add code
		CD code = consent.addNewCode();
		buildCodeSystem(code, codeData);
		// add status code
		CS status = consent.addNewStatusCode();
		status.setCode("completed");
	}

	/**
	 * Builds the structrured body without building components. Component
	 * building must happen after this structured body is built.
	 * 
	 * @param doc
	 * @return the structured body to be built.
	 */
	POCDMT000040StructuredBody buildStructuredBody(
			POCDMT000040ClinicalDocument1 doc) {
		POCDMT000040Component2 component = doc.addNewComponent();
		POCDMT000040StructuredBody body = component.addNewStructuredBody();

		II tid = body.addNewTemplateId();
		// Set the CRS template
		tid.setExtension("IMPL_CDAR2_LEVEL1-2REF_US_I2_2005SEP");
		tid.setRoot("2.16.840.1.113883.10");

		return body;
	}

	public void buildAdvancedDirectiveComponent(
			POCDMT000040StructuredBody body, AdvanceDirective[] ads,
			boolean required) {
		if ((null == ads || ads.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_ADVANCE_DIRECTIVE_SECTION);
		buildTemplateId(section, TID_ADVANCE_DIRECTIVES);
		buildTemplateId(section, TID_ADVANCE_DIRECTIVES_STRUCTURED_REFERENCE);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("42348-3", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Advance Directives"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Advance Directives");
		StrucDocText text = section.addNewText();
		// handle empty advance directives
		if ((null == ads || ads.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		String[] theads = new String[3];
		theads[0] = "Description";
		theads[1] = "Comment";
		theads[2] = "Effective Date";
		int size = ads.length;
		String[] desc = new String[size];
		String[] comments = new String[size];
		String[] dates = new String[size];
		for (int i = 0; i < size; i++) {
			desc[i] = ads[i].getDescription();
			comments[i] = ads[i].getComments();
			dates[i] = dateToString(ads[i].getEffectiveDate(), dfShort);
		}
		String idPrefix = "advanceDirective";
		buildTable(text, theads, idPrefix, null, desc, comments, dates);
		for (int i = 0; i < size; i++) {
			buildAdvanceDirectiveEntry(section, ads[i], getId(idPrefix,
					theads[0], i));
		}

	}

	private void buildAdvanceDirectiveEntry(POCDMT000040Section section,
			AdvanceDirective ad, String descriptionId) {
		POCDMT000040Entry entry = section.addNewEntry();
		entry.setTypeCode(XActRelationshipEntry.DRIV);

		POCDMT000040Observation obs = entry.addNewObservation();
		obs.setClassCode("OBS");
		obs.setMoodCode(XActMoodDocumentObservation.EVN);

		buildTemplateId(obs, CCDTID_ADVANCE_DIRECTIVE_OBS);
		buildTemplateId(obs, TID_ADVANCE_DIRECTIVES_ACT);
		buildTemplateId(obs, TID_ADVANCE_DIRECTIVES_OBSERVATION);
		buildId(obs.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.OBSERVATION)));

		CodeSystem code = null;
		if (ad.getCode() != null) {
			code = new CodeSystem(ad.getCode().getValue(), null, ad.getCode()
					.getSystem(), ad.getCode().getDisplayName());
		}
		buildCodeSystem(obs.addNewCode(), code);

		ED text = obs.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue(toReference(descriptionId));

		CS statusCode = obs.addNewStatusCode();
		statusCode.setCode("completed");

		IVLTS effectiveTime = obs.addNewEffectiveTime();
		if (ad.getEffectiveDate() != null) {
			effectiveTime.setValue(dfShort2.format(ad.getEffectiveDate()
					.getTime()));
		} else {
			effectiveTime.setNullFlavor("UNK");
		}

		POCDMT000040EntryRelationship entryrelation = obs
				.addNewEntryRelationship();
		entryrelation.setTypeCode(XActRelationshipEntryRelationship.REFR);
		POCDMT000040Observation observation = entryrelation.addNewObservation();
		observation.setClassCode("OBS");
		observation.setMoodCode(XActMoodDocumentObservation.EVN);
		buildTemplateId(observation, CCDTID_ADVANCE_DIRECTIVE_STATUS_OBS);
		buildCodeSystem(observation.addNewCode(), new CodeSystem("33999-4",
				"2.16.840.1.113883.6.1", "LOINC", "Status"));
		observation.addNewStatusCode().setCode("completed");
		XmlBeanUtil.addValue(observation, new CodeSystem("15240007",
				"2.16.840.1.113883.6.96", "SNOMED CT", "Current and verified"));
	}

	void buildReasonForVisit(POCDMT000040StructuredBody body, String reason,
			boolean required) {
		if (!StringUtil.goodString(reason)) {
			if (required)
				reason = UNKNOWN;
			else
				return;
		}
		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("X-RFVCC", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Reason for Visit"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Reason for Visit/Chief Complaint");
		StrucDocText text = section.addNewText();
		XmlBeanUtil.addText(text, reason);
	}

	// Section 5.4.3.1.1
	public void buildReasonForReferral(POCDMT000040StructuredBody body,
			String reason, boolean required) {
		CodeSystem codeSystem = new CodeSystem("42349-1", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Reason for Referral");
		buildTextSection(body, reason, required, TID_REASON_FOR_REFERRAL,
				codeSystem, "Reason for Referral");
	}

	private void buildTextSection(POCDMT000040StructuredBody body,
			String textMessage, boolean required, String tid,
			CodeSystem codeSystem, String titleMessage) {
		if (!StringUtil.goodString(textMessage)) {
			if (required)
				textMessage = UNKNOWN;
			else
				return;
		}

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		II templateId = section.addNewTemplateId();
		templateId.setRoot(tid);
		CE code = section.addNewCode();
		buildCodeSystem(code, codeSystem);

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, titleMessage);
		StrucDocText text = section.addNewText();
		XmlBeanUtil.addText(text, textMessage);
	}

	// Section 5.4.3.2.1 History of Present Illness
	public void buildHistoryOfPresentalIllness(POCDMT000040StructuredBody body,
			String[] history, boolean required) {
		// CodeSystem codeSystem = new CodeSystem("10164-2",
		// CodeSystem.getCodeSystem(LOINC), LOINC, "History of Present
		// Illness");
		// buildTextSection(body, history, required,
		// TID_HISTORY_PRESENTANT_ILLNESS, codeSystem, "History of Present
		// Illness" );
		if (isEmpty(history) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		II templateId = section.addNewTemplateId();
		templateId.setRoot(TID_HISTORY_PRESENTANT_ILLNESS);
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("10164-2", CodeSystem
				.getCodeSystem(LOINC), LOINC, "History of Present Illness"));
		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "History of Present Illness");
		StrucDocText text = section.addNewText();

		// handle empty History of Present
		if (isEmpty(history) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, history);
	}

	// Section 5.4.3.2.6 History of Outpatient Visits
	public void buildHistoryOfOutpatientVisits(POCDMT000040StructuredBody body,
			String history, boolean required) {
		CodeSystem codeSystem = new CodeSystem("11346-4", CodeSystem
				.getCodeSystem(LOINC), LOINC, "History of Outpatient Visits");
		buildTextSection(body, history, required,
				TID_HISTORY_OF_OUTPATIENT_VISITS, codeSystem,
				"History of Outpatient Visits");
	}

	public void buildImmunizationComponent(POCDMT000040StructuredBody body,
			Immunization[] immunizations, boolean required) {
		if ((immunizations == null || immunizations.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_IMMUNIZATIONS_SECTION);
		buildTemplateId(section, TID_IMMUNIZATIONS);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("11369-6", CodeSystem
				.getCodeSystem(LOINC), LOINC, "History of Immunizations"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "History of Immunizations");
		StrucDocText text = section.addNewText();
		// handle empty allergies
		if ((immunizations == null || immunizations.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		String[] thead = new String[2];
		thead[0] = "Immunization Type"; // Don't change the index of its thead.
		thead[1] = "Immunization Date";

		int size = immunizations.length;
		String[] types = new String[size];
		String[] dates = new String[size];

		for (int i = 0; i < size; i++) {
			Immunization immunization = immunizations[i];
			types[i] = immunization.getImmunizationType();
			dates[i] = dateToString(immunization.getDateTime(), dfShort);
		}
		String idPrefix = "immunization";
		buildTable(text, thead, idPrefix, null, types, dates);
		// Add Entry
		for (int i = 0; i < size; i++) {
			Immunization immunization = immunizations[i];
			buildImmunizationEntry(section, immunization, getId(idPrefix,
					thead[0], i));
		}
	}

	private void buildImmunizationEntry(POCDMT000040Section section,
			Immunization immunization, String immunizationTypeId) {
		// Add Entry
		POCDMT000040Entry entry = section.addNewEntry();
		// Add substanceAdministration
		POCDMT000040SubstanceAdministration sa = entry
				.addNewSubstanceAdministration();
		sa.setClassCode("SBADM");
		sa.setMoodCode(XDocumentSubstanceMood.EVN);
		sa.setNegationInd(false);
		buildTemplateId(sa, CCDTID_MEDICATION_ACTIVITY);
		buildTemplateId(sa, TID_SUBSTANCE_ADMINISTRATION_IMMUNIZATION);
		// Set ID
		II id = sa.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.SUBSTANCE_ADMIN)));
		buildCodeSystem(sa.addNewCode(), new CodeSystem("IMMUNIZ",
				"2.16.840.1.113883.5.4", "ActCode", null));

		ED text = sa.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue(toReference(immunizationTypeId));

		// Set StatusCode
		CS statusCode = sa.addNewStatusCode();
		// The status of all <substanceAdministration> elements must be
		// "completed".
		statusCode.setCode("completed");

		// Set Immunization Date
		SXCMTS effectiveTime = sa.addNewEffectiveTime();
		if (null != immunization.getDateTime()) {
			effectiveTime.setValue(dateToString(immunization.getDateTime(),
					dfLong));
			// XmlBeanUtil.addElement(effectiveTime, "center", "value",
			// dateToString(immunization.getDateTime(), dfLong) );
		} else {
			effectiveTime.setNullFlavor("UNK");
		}
		XmlBeanUtil.addAttribute(effectiveTime, "type", "IVL_TS");

		// Route
		Code routeCode = immunization.getRouteCode();
		if (routeCode != null) {
			buildCodeSystem(sa.addNewRouteCode(), new CodeSystem(routeCode
					.getValue(), CodeSystem
					.getCodeSystem(routeCode.getSystem()), routeCode
					.getSystem(), routeCode.getDisplayName()));
		}
		// Dose
		buildDoseQuantity(sa, immunization.getDose());

		// Consumable
		buildConsumable(sa, immunization.getCode(), immunizationTypeId,
				immunization.getImmunizationType());
	}

	public void buildStudiesSummaryComponent(POCDMT000040StructuredBody body,
			Procedure[] procedures, LabResult[] labResults, boolean required) {
		if (((procedures == null || procedures.length == 0) && (labResults == null || labResults.length == 0))
				&& !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		CE code = section.addNewCode();
		buildTemplateId(section, CCDTID_RESULTS_SECTION);
		buildTemplateId(section, TID_RESULTS);
		buildTemplateId(section, TID_RESULTS_STRUCTURED);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		buildCodeSystem(code, new CodeSystem("30954-2", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Studies Summary"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Studies Summary/Lab Results"); // Constraints by CCD --
														// sectin Results
		StrucDocText text = section.addNewText();
		// handle empty procedure and result
		if (((procedures == null || procedures.length == 0) && (labResults == null || labResults.length == 0))
				&& required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		if (procedures != null && procedures.length > 0) {
			String[] thead = new String[2];
			thead[0] = "Procedure";

			thead[1] = "Observation Date";

			int size = procedures.length;
			String[] names = new String[size];
			String[] dates = new String[size];

			for (int i = 0; i < size; i++) {
				Procedure procedure = procedures[i];
				names[i] = procedure.getName();
				dates[i] = dateToString(procedure.getDate(), dfShort);
			}
			String idPrefix = "procedure";
			buildTable(text, thead, idPrefix, null, names, dates);
			// Add Entry for Procedures
			for (int i = 0; i < size; i++) {
				Procedure procedure = procedures[i];
				buildProcedureEntry(section, procedure, getId(idPrefix,
						thead[0], i));
			}
		}

		// Create Table for each Battery.
		if (labResults != null && labResults.length > 0) {
			for (LabResult lResult : labResults) {
				Test[] tests = lResult.getTest();
				Battery[] batterys = lResult.getBattery();

				if (tests != null && tests.length > 0) {
					for (Test test : tests) {
						Battery battery = new Battery(new Test[] { test });
						battery.setName(test.getName());
						buildResultTable(text, battery);
					}
				}
				if (batterys != null && batterys.length > 0)
					for (Battery battery : batterys)
						if (battery.getTest() != null
								&& battery.getTest().length > 0)
							buildResultTable(text, battery);

			}
			// Add Entry
			for (LabResult lResult : labResults) {
				Test[] tests = lResult.getTest();
				Battery[] batterys = lResult.getBattery();

				if (tests != null && tests.length > 0) {
					for (Test test : tests) {
						Battery battery = new Battery(new Test[] { test });
						if (test.getCodes() != null && test.getCodes().length > 0)
							battery.setCodes(test.getCodes());
						else
							battery.setCode(test.getCode());
						buildResultEntry(section, battery, null);
					}
				}
				if (batterys != null)
					for (Battery battery : batterys)
						if (battery.getTest() != null
								&& battery.getTest().length > 0) {
							buildResultEntry(section, battery, null);
						}
			}
		}
	}

	public void buildHospitalStudiesSummaryComponent(
			POCDMT000040StructuredBody body, Procedure[] procedures,
			boolean required) {
		if ((procedures == null || procedures.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		CE code = section.addNewCode();
		buildTemplateId(section, TID_HOSPITAL_STUDIES_SUMMARY);
		buildTemplateId(section, TID_HOSPITAL_STUDIES_SUMMARY_STRUCTURED);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		buildCodeSystem(code, new CodeSystem("11493-4", CodeSystem
				.getCodeSystem(LOINC), LOINC,
				"Hospital Discharge Studies Summary"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Hospital Discharge Studies Summary"); // Constraints
																			// by
																			// CCD
																			// --
																			// sectin
																			// Results
		StrucDocText text = section.addNewText();
		// handle empty procedure
		if ((procedures == null || procedures.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		String[] thead = new String[2];
		thead[0] = "Procedure";
		thead[1] = "Observation Date";

		int size = procedures.length;
		String[] names = new String[size];
		String[] dates = new String[size];

		for (int i = 0; i < size; i++) {
			Procedure procedure = procedures[i];
			names[i] = procedure.getName();
			dates[i] = dateToString(procedure.getDate(), dfShort);
		}
		String idPrefix = "procedure";
		buildTable(text, thead, idPrefix, null, names, dates);
		// Add Entry
		for (int i = 0; i < size; i++) {
			Procedure procedure = procedures[i];
			buildProcedureEntry(section, procedure,
					getId(idPrefix, thead[0], i));
		}
	}

	private void buildProcedureEntry(POCDMT000040Section section,
			Procedure procedure, String procedureNameId) {
		// Add Entry
		POCDMT000040Entry entry = section.addNewEntry();
		POCDMT000040Procedure proc = entry.addNewProcedure();
		proc.setClassCode("PROC");
		proc.setMoodCode(hl7OrgV3.XDocumentProcedureMood.EVN);
		II tid = proc.addNewTemplateId();
		tid.setRoot(CCDTID_PROCEDURE_ACTIVITY);
		II tid2 = proc.addNewTemplateId();
		tid2.setRoot(TID_PROCEDURE_ENTRY);
		// Set procedure ID
		II id = proc.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.PROCEDURE)));

		Code code = procedure.getCode();
		if (code != null) {
			CodeSystem codeData = new CodeSystem(code.getValue(), CodeSystem
					.getCodeSystem(code.getSystem()), code.getSystem(), code
					.getDisplayName());
			CD cd = proc.addNewCode();
			buildCodeSystem(cd, codeData);
		}
		ED text = proc.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue(toReference(procedureNameId));
		// Set the require StatusCode
		CS statusCode = proc.addNewStatusCode();
		statusCode.setCode(procedure.getStatusCode().getValue());

		// Set procedure Date
		SXCMTS effectiveTime = proc.addNewEffectiveTime();
		if (null != procedure.getDate()) {
			effectiveTime.setValue(dateToString(procedure.getDate(), dfLong));
		} else {
			effectiveTime.setNullFlavor("UNK");
		}

	}

	void buildResultTable(StrucDocText text, Battery battery) {
		if (battery == null || battery.getTest() == null
				|| battery.getTest().length == 0)
			return;

		Test[] tests = battery.getTest();
		String[] thead = new String[5];
		thead[0] = "Test: " + battery.getName();
		thead[1] = "Status";
		thead[2] = "Value"; // with unit //Don't change the index of its thead
		thead[3] = "Observation Date";
		thead[4] = "Interpretation";
		// thead[6] = "Comment";

		int size = 0;
		for (Test test : tests)
			size += test.getResult().length;

		String[] names = new String[size];
		String[] statuses = new String[size];
		String[] values = new String[size];
		String[] dates = new String[size];
		String[] interpretations = new String[size];
		// String[] comments = new String[size];

		int count = 0;
		for (Test test : tests)
			for (int i = 0; i < test.getResult().length; i++) {
				Result result = test.getResult(i);
				names[count] = result.getName();
				statuses[count] = result.getStatus();
				values[count] = result.getValue();
				String unit = result.getUom();
				if (StringUtil.goodString(unit)) {
					values[count] += " " + unit;
				}
				dates[count] = dateToString(result.getDate(), dfShort);
				interpretations[count] = result.getInterpretation();
				// comments[count] = result.getComments();
				count++;
			}
		String idPrefix = null;
		buildTable(text, thead, idPrefix, null, names, statuses, values, dates,
				interpretations);
	}

	private void buildResultEntry(POCDMT000040Section section, Battery battery,
			String valueId) {
		// TODO: need to reevaluate the Result section based on CCD. Some
		// required fields are missing such as Code, Range,

		// Add Entry
		POCDMT000040Entry entry = section.addNewEntry();
		POCDMT000040Organizer org = entry.addNewOrganizer();
		org.setClassCode(XActClassDocumentEntryOrganizer.BATTERY);
		org.setMoodCode("EVN");
		buildTemplateId(org, CCDTID_RESULT_ORGANIZER);

		// Set ID for Organization
		II orgId = org.addNewId();
		buildId(orgId, new Id(OID_ROOT, OID.getUID(OID.IDType.ORGANIZATION)));

		// Set code
		Code batteryCode = battery.getCode();
		CodeSystem codeSystem = null;

		CD code = org.addNewCode();
		if (batteryCode != null) {
			codeSystem = new CodeSystem(batteryCode.getValue(), CodeSystem
					.getCodeSystem(batteryCode.getSystem()), batteryCode
					.getSystem(), battery.getCode().getDisplayName());
			buildCodeSystem(code, codeSystem);
		} else
			buildCodeSystem(code, null);
		// Set status Code
		CS statusCode = org.addNewStatusCode();
		statusCode.setCode(battery.getTest(0).getOrder().getStatus());
		for (Test test : battery.getTest()) {
			if (test != null) {
				for (Result result : test.getResult()) {
					POCDMT000040Component4 component = org.addNewComponent();
					POCDMT000040Observation ob = component.addNewObservation();
					ob.setClassCode("OBS");
					ob.setMoodCode(hl7OrgV3.XActMoodDocumentObservation.EVN);
					// TODO: Set Template ID -- no template, get this section
					// directly from CCD draft of July 26 2006
					buildTemplateId(ob, CCDTID_RESULT_OBSERVATION);
					// Set ID
					II id = ob.addNewId();
					buildId(id, new Id(OID_ROOT, OID
							.getUID(OID.IDType.OBSERVATION)));

					// TODO:set code
					CD obCode = ob.addNewCode();
					if (batteryCode != null) {
						buildCodeSystem(obCode, codeSystem);
					} else
						buildCodeSystem(obCode, null);

					// setvalue
					Map<String, String> map = new HashMap<String, String>();
					map.put("type", "PQ");
					map.put("value", result.getValue());
					if (StringUtil.goodString(result.getUom()))
						map.put("unit", result.getUom());
					XmlBeanUtil.addElementwithAttributes(ob, "value", map);

					// Set StatusCode
					CS rsStatusCode = ob.addNewStatusCode();
					rsStatusCode.setCode(result.getStatus());

					// Set result Date
					SXCMTS effectiveTime = ob.addNewEffectiveTime();
					if (null != result.getDate()) {
						effectiveTime.setValue(dateToString(result.getDate(),
								dfLong));
					}

					// Set the interpretation TODO: need to get code (CCR1.0
					// schema has the code).
					if (result.getInterpretation() != null) {
						CE interpretationCode = ob.addNewInterpretationCode();
						interpretationCode.setDisplayName(result
								.getInterpretation());
						buildCodeSystem(interpretationCode, null);
					}

					// Set Reference Range
					POCDMT000040ReferenceRange range = ob
							.addNewReferenceRange();
					POCDMT000040ObservationRange observationRange = range
							.addNewObservationRange();
					XmlBeanUtil.addElementWithText(observationRange, "text",
							result.getRange());

					// TODO: set comment, no specification so far.
				}
			}
		}
	}

	public void buildActiveProblemComponent(POCDMT000040StructuredBody body,
			SimpleProblem[] problems, boolean required) {
		if ((problems == null || problems.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_PROBLEM_SECTION);
		buildTemplateId(section, TID_ACTIVE_PROBLEM);

		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));

		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("11450-4", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Problem List"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Problems");
		StrucDocText text = section.addNewText();

		// handle empty problems
		if ((problems == null || problems.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildProblemBody(section, text, problems, "problem");
	}

	public void buildAdmissionDiagnosisComponent(
			POCDMT000040StructuredBody body, SimpleProblem[] problems,
			boolean required) {
		if ((problems == null || problems.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		II templateId = section.addNewTemplateId();
		templateId.setRoot(TID_HOSPITAL_ADMISSION_DIAGNOSIS);
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("46241-6", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Hospital Admission DX"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Hospital Admission DX");
		StrucDocText text = section.addNewText();

		// handle empty problems
		if ((problems == null || problems.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildProblemBody(section, text, problems, "AdmissionDX");
	}

	public void buildDischargeDiagnosisComponent(
			POCDMT000040StructuredBody body, SimpleProblem[] problems,
			boolean required) {
		if ((problems == null || problems.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_DISCHARGE_PROBLEM);
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("11535-2", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Hospital Discharge DX"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Hospital Discharge DX");
		StrucDocText text = section.addNewText();

		// handle empty problems
		if ((problems == null || problems.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildProblemBody(section, text, problems, "DischargeDX");
	}

	public void buildResolvedProblemComponent(POCDMT000040StructuredBody body,
			SimpleProblem[] problems, boolean required) {
		if ((problems == null || problems.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_RESOLVED_PROBLEM);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));

		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("11348-0", CodeSystem
				.getCodeSystem(LOINC), LOINC, "History Of Past Illness"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Resolved Problem");
		StrucDocText text = section.addNewText();

		// handle empty resolved problems
		if ((problems == null || problems.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildProblemBody(section, text, problems, "resolvedProblem");
	}

	private void buildProblemBody(POCDMT000040Section section,
			StrucDocText text, SimpleProblem[] problems, String idPrefix) {
		String[] thead = new String[4];
		thead[0] = "Problem"; // Don't change the index of its thead.
		thead[1] = "Date";
		thead[2] = "Status";
		thead[3] = "Comment"; // Don't change the index of its thead.

		int size = problems.length;
		String[] problemNames = new String[size];
		String[] dates = new String[size];
		String[] status = new String[size];
		String[] comment = new String[size];

		for (int i = 0; i < size; i++) {
			SimpleProblem problem = problems[i];
			problemNames[i] = problem.getName();

			dates[i] = dateToString(problem.getStartDate(), dfShort);
			if (problem.getEndDate() != null) {
				dates[i] = dates[i] + " - "
						+ dateToString(problem.getEndDate(), dfShort);
			}

			// TODO: status[i] = mapCodeValue(CategoryEnum.ProblemStatus,
			// problem.getClinicalContext().getSystemId(), problem.getStatus());
			status[i] = problem.getStatus().getValue();
			comment[i] = problem.getComment();
		}
		// Build Table
		buildTable(text, thead, idPrefix, idPrefix, problemNames, dates, status,
				comment);
		// Add Entry
		for (int i = 0; i < size; i++) {
			SimpleProblem problem = problems[i];
			buildProblemConcernEntry(section, problem, getRowId(idPrefix, i), getId(idPrefix,
					thead[0], i), getId(idPrefix, thead[2], i), getId(idPrefix,
					thead[3], i));
		}
	}

	/**
	 * Builds an entry of a problem concern
	 * 
	 * @param section
	 * @param problem
	 * @param rowId 
	 * 			  the ID attribute of each problem row including date, 
	 *            comment etc.	
	 * @param problemId
	 *            the ID attribute the problem td. e.g.
	 *            <td ID="id">Back Pain</td>
	 */
	private void buildProblemConcernEntry(POCDMT000040Section section,
			SimpleProblem problem, String rowId, String problemId, String statusId,
			String commentId) {
		// Add Entry
		POCDMT000040Entry entry = section.addNewEntry();
		// Add Observation
		POCDMT000040Act act = entry.addNewAct();
		act.setClassCode(XActClassDocumentEntryAct.ACT);
		act.setMoodCode(XDocumentActMood.EVN);
		// Set Template ID
		buildTemplateId(act, CCDTID_PROBLEM_ACT);
		buildTemplateId(act, TID_OBSERVATION_CONCERN);
		buildTemplateId(act, TID_OBSERVATION_PROBLEM_OF_CONCERN);
		// Set ID
		II id = act.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.ACT)));

		CD cd = act.addNewCode();
		// code nullFlavor is "NA", see CCD CONF-149.
		buildCodeSystem(cd, null, "NA");

		// Set StatusCode
		// <statusCode code="active|suspended|aborted|completed'/>
		CS actStatusCode = act.addNewStatusCode();
		ClinicalStatusCode status = problem.getStatus();
		actStatusCode.setCode(status.getValue());

		// effectiveTIME
		IVLTS effectiveTime = act.addNewEffectiveTime();
		IVXBTS low = effectiveTime.addNewLow();
		if (StringUtil.goodString(dateToString(problem.getStartDate(), dfLong))) {
			low.setValue(dateToString(problem.getStartDate(), dfLong));
		} else {
			low.setNullFlavor("UNK");
		}
		if (status == ClinicalStatusCode.RESOLVED
				|| status == ClinicalStatusCode.ABORTED) {
			IVXBTS high = effectiveTime.addNewHigh();
			if (StringUtil
					.goodString(dateToString(problem.getEndDate(), dfLong))) {
				high.setValue(dateToString(problem.getEndDate(), dfLong));
			} else {
				high.setNullFlavor("UNK");
			}
		}

		// Add ProblemEntry
		buildProblemEntry(act, problem, rowId, problemId, statusId, commentId);
	}

	/**
	 * Builds an entry of a problem
	 * 
	 * @param section
	 * @param problem
	 * @param rowId 
	 * 			  the ID attribute of each problem row including date, 
	 *            comment etc.	
	 * @param problemId
	 *            the ID attribute the problem td. e.g.
	 *            <td ID="id">Back Pain</td>
	 */
	private void buildProblemEntry(POCDMT000040Act act, SimpleProblem problem,
			String rowId, String problemId, String statusId, String commentId) {
		// Add entry relationship
		POCDMT000040EntryRelationship entryrelation = act
				.addNewEntryRelationship();
		entryrelation.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
		entryrelation.setInversionInd(false);

		// Add Observation
		POCDMT000040Observation observation = entryrelation.addNewObservation();
		observation.setClassCode("OBS");
		observation.setMoodCode(XActMoodDocumentObservation.EVN);
		buildTemplateId(observation, CCDTID_PROBLEM_OBSERVATION);
		buildTemplateId(observation, TID_OBSERVATION_DESCRIBE_PROBLEM);

		// Set ID
		II obsId = observation.addNewId();
		buildId(obsId, new Id(OID_ROOT, OID.getUID(OID.IDType.OBSERVATION)));

		// Don't know how problem is found, use a default one
		CD obsCode = observation.addNewCode();
		buildCodeSystem(obsCode, new CodeSystem("55607006", "2.16.840.1.113883.6.96",
				"SNOMED CT", "Problem"));

		ED text = observation.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue(toReference(rowId));
		// Set StatusCode
		CS cs = observation.addNewStatusCode();
		// <statusCode> shall always have code='completed'.
		cs.setCode("completed");

		IVLTS effectiveTime = observation.addNewEffectiveTime();
		IVXBTS low = effectiveTime.addNewLow();
		if (StringUtil.goodString(dateToString(problem.getStartDate(), dfLong))) {
			low.setValue(dateToString(problem.getStartDate(), dfLong));
		} else {
			low.setNullFlavor("UNK");
		}

		if (StringUtil.goodString(dateToString(problem.getEndDate(), dfLong))) {
			IVXBTS high = effectiveTime.addNewHigh();
			high.setValue(dateToString(problem.getEndDate(), dfLong));
		}
		Code code = problem.getCode();
		// CodeSystem(String code, String codeSystem, String codeSystemName,
		// String displayName)
		CodeSystem problemCode = new CodeSystem(code.getValue(), CodeSystem
				.getCodeSystem(code.getSystem()), code.getSystem(), code
				.getDisplayName());
		XmlBeanUtil.addReferenceValue(observation, problemCode, problemId);

		SharedEnums.ClinicalStatusCode status = problem.getStatus();

		CodeSystem statusCode = new CodeSystem(status.getCode(), status
				.getCodeSystem(), status.getCodeSystemName(), status.getValue());
		buildClinicalStatusRelationship(observation, statusId, statusCode);
		buildCommentRelationship(observation, commentId);
	}

	// See PCC-TF-vol2 2007 Section 6.4.4.4
	private void buildClinicalStatusRelationship(POCDMT000040Observation obs,
			String statusId, CodeSystem status) {
		POCDMT000040EntryRelationship entryrelation = obs
				.addNewEntryRelationship();
		entryrelation.setTypeCode(XActRelationshipEntryRelationship.REFR);
		entryrelation.setInversionInd(false);

		POCDMT000040Observation observation = entryrelation.addNewObservation();
		observation.setClassCode("OBS");
		observation.setMoodCode(XActMoodDocumentObservation.EVN);
		// Add Template IDs
		buildTemplateId(observation, CCDTID_STATUS_OBS);
		buildTemplateId(observation, CCDTID_PROBLEM_STATUS_OBS);
		buildTemplateId(observation, TID_CLINICAL_STATUS_OBSERVATION);

		CD code = observation.addNewCode();
		buildCodeSystem(code, new CodeSystem("33999-4", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Status"));

		// add status
		CS statusCode = observation.addNewStatusCode();
		statusCode.setCode("completed");
		// add text
		ED ed = observation.addNewText();
		TEL ref = ed.addNewReference();
		ref.setValue(toReference(statusId));
		// add value
		XmlBeanUtil.addValue(observation, status);

	}

	private String toReference(String id) {
		return "#" + id;
	}

	// See PCC-TF-vol2-2007 Section 6.4.4.6
	private void buildCommentRelationship(POCDMT000040Observation obs,
			String commentReference) {
		POCDMT000040EntryRelationship entryrelation = obs
				.addNewEntryRelationship();
		entryrelation.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
		entryrelation.setInversionInd(true);

		POCDMT000040Act act = entryrelation.addNewAct();
		act.setClassCode(XActClassDocumentEntryAct.ACT);
		act.setMoodCode(XDocumentActMood.EVN);
		buildTemplateId(act, CCDTID_COMMENT);
		buildTemplateId(act, TID_COMMENT_OBSERVATION);
		CD code = act.addNewCode();
		buildCodeSystem(code, new CodeSystem("48767-8", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Annotation Comment"));

		// point to the comment text
		ED text = act.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue(toReference(commentReference));

		// add status
		CS statusCode = act.addNewStatusCode();
		statusCode.setCode("completed");
	}

	public void buildAllergyComponent(POCDMT000040StructuredBody body,
			Allergy[] allergies, boolean required) {
		if ((allergies == null || allergies.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_ALERTS_SECTION);
		buildTemplateId(section, TID_ALLERGIES_AND_ADVERSE_REACTIONS);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("48765-2", CodeSystem
				.getCodeSystem(LOINC), LOINC,
				"Allergies, Adverse Reactions, Alerts"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Allergies and Adverse Reactions");
		StrucDocText text = section.addNewText();

		// handle empty allergies
		if ((allergies == null || allergies.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}

		String[] thead = new String[4];
		thead[0] = "Allergen"; // Don't change the index of its thead;
								// "Allergen is used for building the id,
		thead[1] = "Reaction"; // Don't change the index of its thead
		thead[2] = "Date";
		int size = allergies.length;
		String[] allergens = new String[size];
		String[] reactions = new String[size];
		String[] dates = new String[size];
		for (int i = 0; i < size; i++) {
			Allergy allergy = allergies[i];
			allergens[i] = allergy.getAgent();
			reactions[i] = allergy.getReaction();
			dates[i] = dateToString(allergy.getReportDate(), dfShort);
		}
		// Build Table
		String idPrefix = "allergy";
		buildTable(text, thead, idPrefix, null, allergens, reactions, dates);
		// Add Entry
		for (int i = 0; i < size; i++) {
			Allergy allergy = allergies[i];
			buildAllergyEntry(section, allergy, getId(idPrefix, thead[0], i),
					getId(idPrefix, thead[1], i));
		}
	}

	private void buildAllergyEntry(POCDMT000040Section section,
			Allergy allergy, String allergenId, String reationId) {
		// Add Entry
		POCDMT000040Entry entry = section.addNewEntry();
		// Add Act
		POCDMT000040Act act = entry.addNewAct();
		act.setClassCode(XActClassDocumentEntryAct.ACT);
		act.setMoodCode(XDocumentActMood.EVN);
		buildTemplateId(act, CCDTID_PROBLEM_ACT);
		buildTemplateId(act, TID_OBSERVATION_CONCERN);
		
		buildTemplateId(act,
				TID_OBSERVATION_ALLERGY_OR_ADVERSE_REACTION_OF_CONCERN);
		buildId(act.addNewId(), new Id(OID_ROOT, OID.getUID(OID.IDType.ACT)));

		CD cd = act.addNewCode();
		// code nullFlavor is "NA", see CCD CONF-149.
		buildCodeSystem(cd, null, "NA");

		// Set StatusCode
		// <statusCode code="active|suspended|aborted|completed'/>
		CS actStatusCode = act.addNewStatusCode();
		actStatusCode.setCode("active"); // TODO: need to change the data
											// model to add allergy status

		// effectiveTIME
		IVLTS effectiveTime = act.addNewEffectiveTime();
		IVXBTS low = effectiveTime.addNewLow();
		if (StringUtil
				.goodString(dateToString(allergy.getReportDate(), dfLong))) {
			low.setValue(dateToString(allergy.getReportDate(), dfLong));
		} else {
			low.setNullFlavor("UNK");
		}

		// Build observation
		POCDMT000040EntryRelationship entryrelation = act
				.addNewEntryRelationship();
		entryrelation.setTypeCode(XActRelationshipEntryRelationship.SUBJ);
		entryrelation.setInversionInd(false);
		POCDMT000040Observation obs = entryrelation.addNewObservation();
		obs.setClassCode("OBS");
		obs.setMoodCode(XActMoodDocumentObservation.EVN);
		buildTemplateId(obs, CCDTID_PROBLEM_OBSERVATION);
		buildTemplateId(obs, CCDTID_ALERT_OBS);
		buildTemplateId(obs, TID_OBSERVATION_DESCRIBE_PROBLEM);
		buildTemplateId(obs,
				TID_OBSERVATION_DESCRIBE_ALLERGY_OR_ADVERSE_REACTION);
		buildId(obs.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.OBSERVATION)));
		// See Page 99 of PCC-TF-2006 <code
		// code='ALG|OINT|DALG|EALG|FALG|DINT|EINT|FINT|DNAINT|ENAINT|FNAINT'
		// codeSystem='2.16.840.1.113883.5.4'
		// codeSystemName='ObservationIntoleranceType'/>
		CD code = obs.addNewCode(); // TODO: Connect does not have Allergy
									// category code.
		buildCodeSystem(code, new CodeSystem("ALG", "2.16.840.1.113883.5.4",
				"ObservationIntoleranceType", "Allergy"));
		// Set StatusCode
		CS statusCode = obs.addNewStatusCode();
		statusCode.setCode("completed");

		IVLTS effectiveTime2 = obs.addNewEffectiveTime();
		IVXBTS low2 = effectiveTime2.addNewLow();
		if (null != allergy.getReportDate()) {
			low2.setValue(dateToString(allergy.getReportDate(), dfLong));
		} else {
			low.setNullFlavor("UNK");
		}
		XmlBeanUtil.addReferenceValue(obs, null, null);

		// Add participant
		POCDMT000040Participant2 participant = obs.addNewParticipant();
		participant.setTypeCode("CSM");
		POCDMT000040ParticipantRole participantRole = participant
				.addNewParticipantRole();
		participantRole.setClassCode("MANU");
		POCDMT000040PlayingEntity playingEntity = participantRole
				.addNewPlayingEntity();
		participantRole.addNewAddr().setNullFlavor("UNK");
		participantRole.addNewTelecom().setNullFlavor("UNK");
		playingEntity.setClassCode("MMAT");

		CodeSystem cs = null;
		if (allergy.getCode() != null)
			cs = new CodeSystem(allergy.getCode().getValue(), null, allergy
					.getCode().getSystem(), allergy.getCode().getDisplayName());
		CE allergyCode = playingEntity.addNewCode();
		buildCodeSystem(allergyCode, cs);
		ED originalText = allergyCode.addNewOriginalText();
		TEL reference = originalText.addNewReference();
		reference.setValue("#" + allergenId);
		// TODO: Link back to problem:
		// buildAllergyEntryRelationship(obs, allergy, reationId);

	}

	// TODO: THis reaction entryreplationship needs to link back to
	// problem(condition) entry.
	private void buildAllergyEntryRelationship(
			POCDMT000040Observation observation, Allergy allergy,
			String reactionId) {
		POCDMT000040EntryRelationship entryRelation = observation
				.addNewEntryRelationship();
		entryRelation.setTypeCode(XActRelationshipEntryRelationship.MFST);
		II tidEntryRelation = entryRelation.addNewTemplateId();
		tidEntryRelation
				.setRoot(TID_OBSERVATION_DESCRIBE_MANIFESTATION_OF_ALLERGY);

		POCDMT000040Observation obs = entryRelation.addNewObservation();
		buildId(obs.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.OBSERVATION)));
		obs.setClassCode("OBS");
		obs.setMoodCode(XActMoodDocumentObservation.EVN);
		buildTemplateId(obs, CCDTID_PROBLEM_OBSERVATION);
		buildTemplateId(obs, TID_OBSERVATION_DESCRIBE_PROBLEM);

		// Set Code
		CD code = obs.addNewCode();
		code.setCode("RXNASSESS");

		CS statusCode = obs.addNewStatusCode();
		statusCode.setCode("completed");
		// TODO: The effectiveTime <low> value should normally be present in
		// Problem Entry.
		// There are exceptions, such as for the case where the patient may be
		// able to report that
		// they had chicken pox, but are unsure when. In this case, the
		// <effectiveTime@gt;
		// element shall have a <low> element with a nullFlavor attribute set to
		// 'UNK'.
		IVLTS effectiveTime = obs.addNewEffectiveTime();
		IVXBTS low = effectiveTime.addNewLow();
		low.setNullFlavor("UNK");

		// TODO: the <value> is the condition that was found. This element is
		// required.
		// While the value of Problem Entry code may be a coded or an un-coded
		// string,
		// the type is always a coded value (xsi:type='CD').

		POCDMT000040EntryRelationship entryRelation2 = obs
				.addNewEntryRelationship();
		entryRelation2.setTypeCode(XActRelationshipEntryRelationship.REFR);
		POCDMT000040Observation obs2 = entryRelation2.addNewObservation();
		obs2.setClassCode("OBS");
		obs2.setMoodCode(XActMoodDocumentObservation.EVN);
		II id = obs2.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.OBSERVATION)));
		CD code2 = obs2.addNewCode();
		code2.setCode("DX");
		CS statusCode2 = obs2.addNewStatusCode();
		statusCode2.setCode("completed");
		XmlBeanUtil.addReferenceValue(obs2, null, reactionId);
	}

	public void buildMedicationComponent(POCDMT000040StructuredBody body,
			Medication[] medications, boolean required) {
		if ((medications == null || medications.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();

		buildTemplateId(section, CCDTID_MEDICATIONS_SECTION);
		buildTemplateId(section, TID_MEDICATIONS);

		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("10160-0", CodeSystem
				.getCodeSystem(LOINC), LOINC, "History of Medication Use"));
		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Medications");
		StrucDocText text = section.addNewText();

		// handle empty medications
		if ((medications == null || medications.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}

		String rowIdBaseName = "order";
		// Add Table
		String idPrefix = "medication";
		buildMedicationBody(section, text, medications,
				XDocumentSubstanceMood.INT, idPrefix, rowIdBaseName);
	}

	public void buildAdministeredMedicationComponent(
			POCDMT000040StructuredBody body, Medication[] medications,
			boolean required) {
		if ((medications == null || medications.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_HOSPITAL_MEDICATIONS);
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("18610-6", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Medications Administered"));
		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Medications Administered");
		StrucDocText text = section.addNewText();

		// handle empty medications
		if ((medications == null || medications.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}

		String rowIdBaseName = "administeredMedicationOrder";
		// Add Table
		String idPrefix = "administeredMedication";
		buildMedicationBody(section, text, medications,
				XDocumentSubstanceMood.EVN, idPrefix, rowIdBaseName);
	}

	public void buildDischargeMedicationComponent(
			POCDMT000040StructuredBody body, Medication[] medications,
			boolean required) {
		if ((medications == null || medications.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_HOSPITAL_DISCHARGE_MEDICATIONS);
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("10183-2", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Hospital Discharge Medications"));
		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Hospital Discharge Medications");
		StrucDocText text = section.addNewText();

		// handle empty medications
		if ((medications == null || medications.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}

		String rowIdBaseName = "dischargeMedicationOrder";
		// Add Table
		String idPrefix = "dischargeMedication";
		buildMedicationBody(section, text, medications,
				XDocumentSubstanceMood.INT, idPrefix, rowIdBaseName);
	}

	public void buildAdmissionMedicationComponent(
			POCDMT000040StructuredBody body, Medication[] medications,
			boolean required) {
		if ((medications == null || medications.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_ADMISSION_MEDICATION_HISTORY);
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("42346-7", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Medications on Admission"));
		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Medications on Admission");
		StrucDocText text = section.addNewText();

		// handle empty medications
		if ((medications == null || medications.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}

		String rowIdBaseName = "admissionMedicationOrder";
		// Add Table
		String idPrefix = "admissionMedication";
		buildMedicationBody(section, text, medications,
				XDocumentSubstanceMood.EVN, idPrefix, rowIdBaseName);
	}

	private void buildMedicationBody(POCDMT000040Section section,
			StrucDocText text, Medication[] medications,
			XDocumentSubstanceMood.Enum mood, String idPrefix,
			String rowIdBaseName) {
		String[] thead = new String[4];
		thead[0] = "Medication"; // Don't change the index of its thead.
		thead[1] = "Sig";
		thead[2] = "OrderDate";
		thead[3] = "Duration";
		int size = medications.length;
		String[] productNames = new String[size];
		String[] sigs = new String[size];
		String[] dates = new String[size];
		String[] durations = new String[size];
		for (int i = 0; i < size; i++) {
			Medication medication = medications[i];
			productNames[i] = medication.getProductName();
			sigs[i] = medication.getSig();
			dates[i] = dateToString(medication.getDate(), dfShort);
			durations[i] = medication.getDuration();
		}
		buildTable(text, thead, idPrefix, rowIdBaseName, productNames, sigs,
				dates, durations);
		// Build Entries
		for (int i = 0; i < size; i++) {
			Medication medication = medications[i];
			buildMedicationEntry(section, medication, mood, rowIdBaseName + "-"
					+ i, getId(idPrefix, thead[0], i));
		}
	}

	private void buildMedicationEntry(POCDMT000040Section section,
			Medication medication, XDocumentSubstanceMood.Enum mood,
			String orderId, String medicationId) {
		// Add Entry
		POCDMT000040Entry entry = section.addNewEntry();
		// Add substanceAdministration
		POCDMT000040SubstanceAdministration sa = entry
				.addNewSubstanceAdministration();
		sa.setClassCode("SBADM");
		sa.setMoodCode(mood);
		// Set Template ID
		buildTemplateId(sa, CCDTID_MEDICATION_ACTIVITY);
		buildTemplateId(sa, TID_SUBSTANCE_ADMINISTRATION_EVENT);
		// TODO: May need to change the ID for complex processing
		buildTemplateId(sa,
				TID_SUBSTANCE_ADMINISTRATION_EVENT_NO_COMPLEX_DOS_PROCESSING);
		// Set ID
		II id = sa.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.SUBSTANCE_ADMIN)));

		// Set Text
		ED text = sa.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue("#" + orderId);
		// Set StatusCode
		CS statusCode = sa.addNewStatusCode();
		// The status of all <substanceAdministration> elements must be
		// "completed".
		statusCode.setCode("completed");
		// effectiveTime
		SXCMTS effectiveTime = sa.addNewEffectiveTime();
		if (null != medication.getDate()) {
			XmlBeanUtil.addElement(effectiveTime, "low", "value", dateToString(
					medication.getDate(), dfLong));
		} else {
			XmlBeanUtil.addElement(effectiveTime, "low", "nullFlavor", "UNK");
		}
		// TODO: We don't have the stop time now
		XmlBeanUtil.addElement(effectiveTime, "high", "nullFlavor", "UNK");
		XmlBeanUtil.addAttribute(effectiveTime, "type", "IVL_TS");
		// Add Frequency:
		String frequencey = medication.getFrequency();
		// TODO: need to parse frequency data.
		
		// Route
		Code routeCode = medication.getRouteCode();
		if (routeCode != null) {
			buildCodeSystem(sa.addNewRouteCode(), new CodeSystem(routeCode
					.getValue(), CodeSystem
					.getCodeSystem(routeCode.getSystem()), routeCode
					.getSystem(), routeCode.getDisplayName()));
		}
		// Dose
		buildDoseQuantity(sa, medication.getDose());
		// Consumable
		buildConsumable(sa, medication.getCode(), medicationId, medication
				.getProductName());
	}

	/**
	 * Builds dose quantity used by SubstanceAdministration of Medication and
	 * Immunization.
	 * 
	 * @param sa
	 * @param dq
	 */
	private void buildDoseQuantity(POCDMT000040SubstanceAdministration sa,
			DoseQuantity dq) {
		if (dq == null)
			return;

		IVLPQ dose = sa.addNewDoseQuantity();
		if (dq.getLow() != null
				&& StringUtil.goodString(dq.getLow().getValue())) {
			IVXBPQ low = dose.addNewLow();
			low.setValue(dq.getLow().getValue());
			if (StringUtil.goodString(dq.getLow().getUnit()))
				low.setUnit(dq.getLow().getUnit());
		}
		// If there is no range, use high == low
		if (dq.getHigh() == null)
			dq.setHigh(dq.getLow());

		if (dq.getHigh() != null
				&& StringUtil.goodString(dq.getHigh().getValue())) {
			IVXBPQ high = dose.addNewHigh();
			high.setValue(dq.getHigh().getValue());
			if (StringUtil.goodString(dq.getHigh().getUnit()))
				high.setUnit(dq.getHigh().getUnit());
		}
	}

	private void buildConsumable(POCDMT000040SubstanceAdministration sa,
			Code medCode, String medicationId, String productName) {
		POCDMT000040Consumable consumable = sa.addNewConsumable();
		consumable.setTypeCode("CSM");
		POCDMT000040ManufacturedProduct product = consumable
				.addNewManufacturedProduct();
		product.setClassCode(RoleClassManufacturedProduct.MANU);

		II tid = product.addNewTemplateId();
		tid.setRoot(CCDTID_PRODUCT);
		tid = product.addNewTemplateId();
		tid.setRoot(TID_SUBSTANCE_ADMINISTRATION_PRODUCT_ENTRY);

		POCDMT000040Material material = product.addNewManufacturedMaterial();
		CE code = material.addNewCode();
		CodeSystem codeSystem = null;
		if (medCode != null) {
			codeSystem = new CodeSystem(medCode.getValue(), CodeSystem
					.getCodeSystem(medCode.getSystem()), medCode.getSystem(),
					medCode.getDisplayName());
		}
		buildCodeSystem(code, codeSystem);
		ED originalText = code.addNewOriginalText();
		TEL reference = originalText.addNewReference();
		reference.setValue("#" + medicationId);
		// add name
		if (productName != null) {
			EN name = material.addNewName();
			XmlBeanUtil.addText(name, productName);
		}
	}

	public void buildVitalSignComponent(POCDMT000040StructuredBody body,
			Measurements[] measurements, boolean required) {
		if ((measurements == null || measurements.length == 0) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_VITAL_SIGNS_SECTION);
		buildTemplateId(section, TID_VITAL_SIGNS);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("8716-3", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Vital Signs"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Vital Signs");
		StrucDocText text = section.addNewText();
		// handle empty measurements
		if ((measurements == null || measurements.length == 0) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		String[] thead = new String[6];
		thead[0] = "Description"; // Don't change the index of its thead.
		thead[1] = "Value";
		// thead[2] = "Unit";
		thead[2] = "Position";
		thead[3] = "Location";
		thead[4] = "Entry Date";
		thead[5] = "comment";

		int size = measurements.length;
		String[] descriptions = new String[size];
		String[] values = new String[size];
		// String[] units = new String[size];
		String[] positions = new String[size];
		String[] locations = new String[size];
		String[] dates = new String[size];
		String[] comments = new String[size];

		for (int i = 0; i < size; i++) {
			Measurements measurement = measurements[i];
			descriptions[i] = measurement.getDescription();
			values[i] = measurement.getValue();
			String unit = measurement.getUnitOfMeasure();
			if (unit != null && unit.trim().length() > 0)
				values[i] = values[i] + unit;
			// units[i] = measurement.getUnitOfMeasure();
			positions[i] = measurement.getPosition();
			locations[i] = measurement.getLocation();
			dates[i] = dateToString(measurement.getEntryDateTime(), dfShort);
			comments[i] = measurement.getComment();
		}
		String idPrefix = "vitalsign";
		// buildTable(text, thead, idPrefix, null, descriptions, values, units,
		// positions, locations, dates, comments);

		buildTable(text, thead, idPrefix, null, descriptions, values,
				positions, locations, dates, comments);
	}

	/**
	 * Build a list of surgery comonents
	 * 
	 * @param body
	 * @param surgicalHistories
	 * @param required
	 */
	public void buildSurgeryComponent(POCDMT000040StructuredBody body,
			String[] surgicalHistories, boolean required) {
		if (isEmpty(surgicalHistories) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_PROCEDURES_SECTION);
		buildTemplateId(section, TID_LIST_OF_SURGERIES);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("47519-4", CodeSystem
				.getCodeSystem(LOINC), LOINC, "History of Procedures"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "History of Procedures");
		StrucDocText text = section.addNewText();

		// handle empty allergies
		if (isEmpty(surgicalHistories) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, surgicalHistories);
	}

	/**
	 * Validate String array
	 * 
	 * @param list
	 * @return <code>boolean</code>
	 */
	private boolean isEmpty(String[] list) {
		boolean flag = false;
		if (list == null || list.length == 0)
			flag = true;
		else {
			for (String item : list)
				if (item != null && item.trim().length() > 0) {
					flag = false;
					return flag;
				}
		}
		return flag;
	}

	public void buildFamilyHistoryComponent(POCDMT000040StructuredBody body,
			String[] familyHistories, boolean required) {
		if (isEmpty(familyHistories) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_FAMILY_HISTORY_SECTION);
		buildTemplateId(section, TID_FAMILY_MEDICAL_HISTORY);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("10157-6", CodeSystem
				.getCodeSystem(LOINC), LOINC,
				"History of Family Member Diseases"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Family History");
		StrucDocText text = section.addNewText();

		// handle empty familyHistories
		if (isEmpty(familyHistories) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, familyHistories);
	}

	public void buildSocialHistoryComponent(POCDMT000040StructuredBody body,
			String[] socialHistories, boolean required) {
		if (isEmpty(socialHistories) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, CCDTID_SOCIAL_HISTORY_SECTION);
		buildTemplateId(section, TID_SOCIAL_HISTORY);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("29762-2", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Social History"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Social History");
		StrucDocText text = section.addNewText();

		// handle empty socialHistories
		if (isEmpty(socialHistories) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, socialHistories);
	}

	public void buildReviewOfSystemComponent(POCDMT000040StructuredBody body,
			String[] reviewOfSystems, boolean required) {
		if (isEmpty(reviewOfSystems) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_REVIEW_OF_SYSTEMS);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("10187-3", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Review of Systems"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Review of Systems");
		StrucDocText text = section.addNewText();

		// handle empty reviewOfSystems
		if (isEmpty(reviewOfSystems) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, reviewOfSystems);
	}

	public void buildPhysicalExamComponent(POCDMT000040StructuredBody body,
			String[] exams, boolean required) {
		if (isEmpty(exams) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_PHYSICAL_EXAM);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("29545-1", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Physical Examination"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Physical Exams");
		StrucDocText text = section.addNewText();

		// handle empty exams
		if (isEmpty(exams) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, exams);
	}

	public void buildExamComponent(POCDMT000040StructuredBody body,
			List<ClinicalNotes> lClinicalNotes, boolean required) {
		if (lClinicalNotes.size() == 0 && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("10210-3", CodeSystem
				.getCodeSystem(LOINC), LOINC,
				"General Status, Physical Findings"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Physical Examination");
		StrucDocText text = section.addNewText();
		// handle empty exam
		if (lClinicalNotes.size() == 0 && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		String[] thead = new String[2];
		thead[0] = "Exam Name";
		thead[1] = "Exam Content";

		int size = lClinicalNotes.size();
		String[] names = new String[size];
		String[] contents = new String[size];

		for (int i = 0; i < size; i++) {
			ClinicalNotes notes = lClinicalNotes.get(i);
			names[i] = notes.getName();
			contents[i] = notes.getContent();
		}
		String idPrefix = "physicalExam";
		buildTable(text, thead, idPrefix, null, names, contents);
	}

	public void buildPlanOfCareComponent(POCDMT000040StructuredBody body,
			String[] careplans, boolean required) {
		if (isEmpty(careplans) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_CARE_PLAN);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("18776-5", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Treatment Plan"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Treatment Plan");
		StrucDocText text = section.addNewText();

		// handle empty careplans
		if (isEmpty(careplans) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, careplans);
	}

	public void buildHosptialCourse(POCDMT000040StructuredBody body,
			String hospitalCourse, boolean required) {
		CodeSystem codeSystem = new CodeSystem("8648-8", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Hospital Course");
		buildTextSection(body, hospitalCourse, required, TID_HOSPTIAL_COURSE,
				codeSystem, "Hospital Course");
	}

	public void buildDischargeDietComponent(POCDMT000040StructuredBody body,
			String[] diets, boolean required) {
		if (isEmpty(diets) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_DISCHARGE_DIET);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("42344-2", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Discharge Diet"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Discharge Diet");
		StrucDocText text = section.addNewText();

		// handle empty exams
		if (isEmpty(diets) && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		buildList(text, diets);
	}

	/**
	 * Builds the non XML body using Base64 encoding on the file.
	 * 
	 * @param doc
	 *            the doc component
	 * @param mediaType
	 *            the content type of the file
	 * @param is
	 *            the InputStream content to be included in the non xml body
	 * @throws CDAException
	 */
	void buildNonXmlBody(POCDMT000040ClinicalDocument1 doc, String mediaType,
			InputStream is) throws CCDException {
		POCDMT000040Component2 component = doc.addNewComponent();
		POCDMT000040NonXMLBody nonXmlBody = component.addNewNonXMLBody();
		ED text = nonXmlBody.addNewText();
		text.setMediaType(mediaType);
		text.setRepresentation(BinaryDataEncoding.B_64);

		String encodedString = null;
		try {
			encodedString = Base64EncoderDecoder.encode(is);
		} catch (IOException e) {
			throw new CCDException("Fail to encode", e);
		}
		XmlBeanUtil.addText(text, encodedString);
	}

	/**
	 * Builds the non XML body using Base64 encoding on the file.
	 * 
	 * @param doc
	 *            the doc component
	 * @param mediaType
	 *            the content type of the file
	 * @param encodedString
	 *            the base64 encoded string content to be included in the non
	 *            xml body
	 * @throws CDAException
	 */
	void buildNonXmlBody(POCDMT000040ClinicalDocument1 doc, String mediaType,
			String encodedString) throws CCDException {
		POCDMT000040Component2 component = doc.addNewComponent();
		POCDMT000040NonXMLBody nonXmlBody = component.addNewNonXMLBody();
		ED text = nonXmlBody.addNewText();
		text.setMediaType(mediaType);
		text.setRepresentation(BinaryDataEncoding.B_64);

		XmlBeanUtil.addText(text, encodedString);
	}

	public void buildPlanOfCare(POCDMT000040StructuredBody body,
			ProposedDisposition disposition, boolean dispositionRequired,
			boolean buildDispositionEntry, TransportMode transportMode,
			boolean transportModeRequired) {
		if ((null == disposition) && !dispositionRequired)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_PLAN_OF_CARE);
		buildTemplateId(section, TID_CARE_PLAN);

		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("18776-5", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Treatment Plan"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Treatment Plan");
		StrucDocText text = section.addNewText();
		// handle empty medications
		String content = null;
		if ((null == disposition) && dispositionRequired) {
			content = NONE;
		} else {
			String[] thead = new String[1];
			thead[0] = "Encounter Disposition"; // Don't change the index of its
												// thead.
			int size = 1;
			String[] dispositions = new String[size];
			for (int i = 0; i < size; i++) {
				dispositions[i] = disposition.getEncounterDisposition();
			}
			// Add Table
			String idPrefix = "proposedDisposition";
			buildTable(text, thead, idPrefix, null, dispositions);

			if (buildDispositionEntry) {
				buildProposedDispositionEntry(section, disposition, getId(
						idPrefix, thead[0], 0));
			}
		}
		XmlBeanUtil.addText(text, content);

		// Add and build Transport Mode

		POCDMT000040Component5 component = section.addNewComponent();
		POCDMT000040Section childsection = component.addNewSection();
		buildModeOfTransport(childsection, transportMode, transportModeRequired);
	}

	public void buildProposedEDDispositionComponent(
			POCDMT000040StructuredBody body, ProposedDisposition disposition,
			boolean required) {
		if (disposition == null && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildTemplateId(section, TID_ENCOUNTER_DISPOSITION);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("11302-7", CodeSystem
				.getCodeSystem(LOINC), LOINC, "ED Disposition"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "ED Disposition");
		StrucDocText text = section.addNewText();

		// handle empty disposition
		if (disposition == null && required) {
			XmlBeanUtil.addText(text, NONE);
			return;
		}
		StrucDocParagraph parag = text.addNewParagraph();
		parag.setID("EDdisposition");
		XmlBeanUtil.addText(parag, disposition.getEncounterDisposition());

		buildProposedDispositionEntry(section, disposition, "EDdisposition");
	}

	private void buildProposedDispositionEntry(POCDMT000040Section section,
			ProposedDisposition disposition, String refId) {
		POCDMT000040Entry entry = section.addNewEntry();
		POCDMT000040Act act = entry.addNewAct();
		act.setClassCode(XActClassDocumentEntryAct.ACT);
		// We only capture INT here.
		act.setMoodCode(XDocumentActMood.INT);
		II tid = act.addNewTemplateId();
		tid.setRoot(TID_PROPOSED_DISPOSITION);

		II id = act.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.ACT)));

		CD code = act.addNewCode();
		SharedEnums.DischargeDispositionCode ddc = disposition
				.getDischargeDispositionCode();
		buildCodeSystem(code, new CodeSystem(ddc.getCode(),
				ddc.getCodeSystem(), ddc.getCodeSystemName(), ddc
						.getDescription()));

		ED text = act.addNewText();
		TEL reference = text.addNewReference();
		reference.setValue("#" + refId);

		CS status = act.addNewStatusCode();
		status.setCode("normal"); // use normal for intended act.
		// add effective time
		IVLTS time = act.addNewEffectiveTime();
		EffectiveTime effectiveTime = disposition.getEffectiveTime();
		if (effectiveTime != null) {
			if (effectiveTime.getStart() != null) {
				IVXBTS low = time.addNewLow();
				low.setValue(dfLong.format(effectiveTime.getStart().getTime()));
			}
			if (effectiveTime.getEnd() != null) {
				IVXBTS high = time.addNewHigh();
				high.setValue(dfLong.format(effectiveTime.getEnd().getTime()));
			}
		}

	}

	public void buildModeOfTransport(POCDMT000040StructuredBody body,
			TransportMode mode, boolean required) {
		if ((null == mode) && !required)
			return;

		POCDMT000040Component3 comp3 = body.addNewComponent();
		POCDMT000040Section section = comp3.addNewSection();
		buildModeOfTransport(section, mode, required);
	}

	// Required for EDR -- See EDR 2006, Section 5.4.3.6.2 Transport Mode
	public void buildModeOfTransport(POCDMT000040Section section,
			TransportMode mode, boolean required) {
		if ((mode == null || mode.getTransportModeCode() == null) && !required)
			return;

		buildTemplateId(section, TID_MODE_OF_TRANSPORT);
		buildId(section.addNewId(), new Id(OID_ROOT, OID
				.getUID(OID.IDType.SECTION)));
		CE code = section.addNewCode();
		buildCodeSystem(code, new CodeSystem("11459-5", CodeSystem
				.getCodeSystem(LOINC), LOINC, "Transport Mode"));

		ST title = section.addNewTitle();
		XmlBeanUtil.addText(title, "Transport Mode");
		StrucDocText text = section.addNewText();
		// handle empty transport mode
		if ((mode == null || mode.getTransportModeCode() == null) && required) {
			log.warn("Missing required transport mode");
			XmlBeanUtil.addText(text, UNKNOWN);
			return;
		}

		String[] thead = new String[2];
		thead[0] = "Transport Mode";
		thead[1] = "Estimated Time of Arrival";
		String[] transportMode = new String[1];
		String[] arrivalTime = new String[1];
		transportMode[0] = mode.getTransportModeCode().getDescription();
		if (mode.getEstimatedTimeOfArrival() != null) {
			arrivalTime[0] = dateToString(mode.getEstimatedTimeOfArrival(), dfLong2);
		}
		// Build Table
		String idPrefix = "transportMode";
		buildTable(text, thead, idPrefix, null, transportMode, arrivalTime);

		// Add Entry
		buildTransportModeEntry(section, mode, getId("transportMode", thead[0],
				0));
	}

	private void buildTransportModeEntry(POCDMT000040Section section,
			TransportMode mode, String transportModeId) {
		POCDMT000040Entry entry = section.addNewEntry();
		POCDMT000040Act act = entry.addNewAct();
		act.setClassCode(hl7OrgV3.XActClassDocumentEntryAct.ACT);
		act.setMoodCode(hl7OrgV3.XDocumentActMood.INT); // Or "EVN"
		// Add TID
		buildTemplateId(act, TID_ESTIMATED_TIME_OF_ARRIVAL);
		// add ID
		II id = act.addNewId();
		buildId(id, new Id(OID_ROOT, OID.getUID(OID.IDType.ACT)));
		// Add code and referece
		CD code = act.addNewCode();
		SharedEnums.TransportModeCode transportModeCode = mode
				.getTransportModeCode();
		CodeSystem codesystem = new CodeSystem(transportModeCode.getCode(),
				transportModeCode.getCodeSystem(), transportModeCode
						.getCodeSystemName(), transportModeCode
						.getDescription());
		buildCodeSystem(code, codesystem);
		ED originalText = code.addNewOriginalText();
		TEL reference = originalText.addNewReference();
		reference.setValue("#" + transportModeId);
		// Add effective time
		IVLTS time = act.addNewEffectiveTime();
		// This is simply not working
		// XmlBeanUtil.addAttribute(time, "type", "IVL_TS");
		IVXBTS high = time.addNewHigh();
		IVXBTS low = time.addNewLow();
		if (mode.getEstimatedTimeOfArrival() == null) {
			high.setNullFlavor("UNK");
			low.setNullFlavor("UNK");
		} else {
			Calendar startTime = null; // TODO: Needs to update data model to
										// add start of transport
			low.setNullFlavor("UNK");
			Calendar arrivalTime = mode.getEstimatedTimeOfArrival();
			if (arrivalTime == null) {
				high.setNullFlavor("UNK");
			} else {
				String sTime = dateToString(arrivalTime, dfLong);
				high.setValue(sTime);

			}
		}
	}

	// The thresholdDate is used to indicate the valid start date. Any date
	// before this date will be treated as invalid.
	private static Calendar thresholdDate = new GregorianCalendar(1900, 1, 1);

	// Converts a calendar object to a string
	private String dateToString(Calendar date, DateFormat format) {
		if (null == date) {
			return "";
		}
		if (date.compareTo(thresholdDate) < 0) {
			// if the dates is before 1/1/1900, then it is not a meaningful
			// date.
			return "";
		}
		return format.format(date.getTime());
	}

	/**
	 * Builds the table for the text part of a component, e.g. Allergy.
	 * 
	 * @param text
	 *            the text part which contains the table
	 * @param theads
	 *            the head columns of the table
	 * @param idPrefix
	 *            the id prefix is the name of each section. e.g. Problem,
	 *            Allergy etc. The parameter is used to build ids
	 * @param rowIdBase
	 *            the row ID base name. e.g. In
	 *            <tr ID="order-1"></tr>, order is the rowIdBase. Set it to
	 *            null, if row ID is not used.
	 * @param tds
	 *            the table tds,
	 */
	public void buildTable(StrucDocText text, String[] theads, String idPrefix,
			String rowIdBase, String[]... tds) {
		StrucDocTable table = text.addNewTable();
		table.setBorder("1");

		// add thead
		StrucDocThead head = table.addNewThead();
		StrucDocTr headtr = head.addNewTr();
		for (int i = 0; i < theads.length; i++) {
			StrucDocTh th = headtr.addNewTh();
			XmlBeanUtil.addText(th, theads[i]);
		}

		StrucDocTbody body = table.addNewTbody();
		if (tds[0].length == 0) {
			// NO data, so build an empty row
			StrucDocTr tr = body.addNewTr();
			for (int j = 0; j < tds.length; j++) {
				StrucDocTd td = tr.addNewTd();
				XmlBeanUtil.addText(td, " ");
			}
			return;
		}

		// i represents row, j represents column td[j][i].
		for (int i = 0; i < tds[0].length; i++) {
			StrucDocTr tr = body.addNewTr();
			if (StringUtil.goodString(rowIdBase))
				tr.setID(rowIdBase + "-" + i);

			for (int j = 0; j < tds.length; j++) {
				StrucDocTd td = tr.addNewTd();
				if (idPrefix != null)
					td.setID(getId(idPrefix, theads[j], i));
				if (!StringUtil.goodString(tds[j][i])) {
					tds[j][i] = " "; // for empty table cell
				}
				XmlBeanUtil.addText(td, tds[j][i]);
			}
		}
	}

	public void buildList(StrucDocText text, String[] contents) {
		StrucDocList list = text.addNewList();
		for (String content : contents) {
			StrucDocItem item = list.addNewItem();
			XmlBeanUtil.addText(item, content);
		}
	}

	private String getId(String idPrefix, String thead, int index) {
		// first remove all the space in the header
		String header = thead.replaceAll(" ", "");
		return idPrefix + "-" + header + "-" + index;
	}
	private String getRowId(String idPrefix, int index) {
		return idPrefix + "-" + index;
	}
	/**
	 * Maps code value from one system to CDA system.
	 * 
	 * @param fromSystem
	 *            the system id whose value is to be mapped.
	 * @param category
	 *            the category of the code.
	 * @param fromValue
	 *            the value to be mapped.
	 * @return the mapped value. <code>Null</code> if the code is not mapped.
	 */
	private String mapCodeValue(CategoryEnum category, String fromSystem,
			String fromValue) {
		String toValue = cmm.getExternalMappingFromCode(category, fromSystem,
				cdaExternalSys, fromValue);
		if (!StringUtil.goodString(toValue)) {
			log
					.warn("Missing Code Mapping. category/fromSystem(value)/toSystem: "
							+ category.getDisplayValue()
							+ "/"
							+ fromSystem
							+ "(" + fromValue + ")/" + cdaExternalSys);
		}
		return toValue;
	}

	public static void main(String[] args) {
		BASE64Encoder encoder = new BASE64Encoder();
		String out = encoder.encode("wonderfull".getBytes());
		System.out.println("OUTPUT:" + out);
		try {
			BufferedInputStream is = new BufferedInputStream(
					new FileInputStream(new File("c:\\temp\\MISYS-2ahUw4.pdf")));
			OutputStream os = new FileOutputStream("c:\\temp\\out.txt");
			encoder.encode(is, os);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use
									// File | Settings | File Templates.
		}

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			BufferedInputStream is = new BufferedInputStream(
					new FileInputStream(new File("c:\\temp\\out.txt")));
			OutputStream os = new FileOutputStream("c:\\temp\\out.pdf");
			decoder.decodeBuffer(is, os);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use
									// File | Settings | File Templates.
		}
	}

}
