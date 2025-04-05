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

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.IDocumentContents;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.Document.IheInterface;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.XdsClassCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsFormatCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.ihe.registry.XdsAuthor;
import com.misyshealthcare.connect.ihe.registry.XdsDocumentEntry;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.net.StandardConnectionDescription;
import com.misyshealthcare.connect.util.Pair;

/**
 * This is the base class for an XDS Actor.  It includes a number
 * of SOAP messaging methods that are useful for all actors.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 7, 2005
 */
public abstract class XdsDocumentActor {

    /* The debug logger this actor should use */
    private Logger log = null;

  /* The description of the connection this actor is using */
  private IConnectionDescription connection = null;

    /* The IHE Audit Trail for this actor. */
    private IheAuditTrail auditTrail = null;

    /* The logger used for capturing MESA test output */
  private IMesaLogger mesaLogger = null;

  /* True if it is okay to sedn SOAP messages to this actor */
  private boolean doSendMessage = true;

  /**
   * Build a new document actor that will talk to the given
   * connection and log to the given logger.
   * 
   * @param connection The description of the connection this actor should use
   * @param logger The logger this actor should use
   * @param auditTrail The IHE auditTrail this actor should use
   * @param xdMessenger The messenger to use for this actor.
   */
  XdsDocumentActor(IConnectionDescription connection, Logger logger, IheAuditTrail auditTrail) {
      log = logger;
      this.connection = connection;
      this.auditTrail = auditTrail;
        // For debugging purposes, allow submit to be inhibited
        if (connection != null) {
            String temp = connection.getProperty("DoNotSubmit");
            if (temp != null) doSendMessage = !Boolean.parseBoolean(temp);
        }
  }

  /**
   * Get whether it is okay to actually send SOAP messages.  This value is
   * used for debugging.
   * 
   * @return Whether it is okay to send messages
   */
  boolean getOkayToSend() {
      return doSendMessage;
  }

    /** Must be called once for each actor when the program starts. */
    public void start() {
        if (auditTrail != null) auditTrail.start();
    }

    /** Must be called once for each actor just before the program quits. */
    public void stop() {
        if (auditTrail != null) auditTrail.stop();
    }

    /**
     * Translate a patient ID into the the Assigning Authority for this connection.
     *
     * @param connection The connection being submitted to
     * @param patientId The patient ID class
     * @return The patient ID in the assigning authority for this connection
     * @throws IheConfigurationException When the assigning authority for this connection is not known
     * @throws DocumentException When the patient ID cannot be found for the assigning authority
     */
    public static String translatePatientId(IConnectionDescription connection, PatientID patientId) throws IheConfigurationException, DocumentException {
        if (patientId == null) return null;
        // Check for an overriding debug Patient ID
        String testPid = connection.getProperty("TestPatientId");
        if (testPid != null) return testPid;
        // Get the assigning authority definition for this connection
        Identifier authority = Configuration.getIdentifier(connection, "AssigningAuthority", true);
        // Look up the appropriate ID
        String pid = authority.getPatientId(patientId);
        if (pid == null)
            throw new DocumentException("Cannot find ID for patient in Assigning Authority '" + authority.getAuthorityNameString() + "'");
        // Convert it to HL7 form
        return HL7.toCX(pid, authority);
    }

    /**
     * Hack for allowing non lookup patient Id to be set
     * arbitrarily by outside applications.
     *
     * @param patientId The fully qualified patient id.
     */
    public void setTestPatientId(String patientId) {
    	if (connection instanceof StandardConnectionDescription) {
    		((StandardConnectionDescription)connection).setProperty("TestPatientID", patientId);
    	} else {
    		log.error("Unable to set test patient id in connection description.");
    	}
    }    

    /**
     * Throw an exception because the connection is not configured correctly.
     *
     * @param action The action being attempted on this document source
     * @param e The reason for the exception
     * @throws DocumentException The exception being thrown
     */
    void throwBadConfigurationException(String action, Throwable e) throws DocumentException {
        String name = null;
        if (connection != null) {
            name = connection.getDescription();
        } else {
            name = "Unnamed";
        }
        String message = "Connection \"" + name + "\" is not properly configured to " + action + " documents";
        if (e != null) {
            log.error(message, e);
            throw new DocumentException(message, e);
        } else {
            log.error(message);
            throw new DocumentException(message);
        }
    }

    /**
     * Log a SOAP message and description to the error log.
     *
     * @param message The SOAP message
     * @param description A description of the problem with the message
     */
    void logSoapMessageError(Object message, String description) {
        if (mesaLogger != null) {
            // Just log the description
            mesaLogger.writeString(description);
        } else {
            // Log the description and the SOAP message itself
            log.error(description);
            log.error(message);
        }
    }
 

    /**
     * Check whether this actor has a MESA test logger.
     *
     * @return True if there is a defined MESA test logger.
     */
  boolean hasMesaLogger() {
      return (mesaLogger != null);
  }

  /**
   * Get the MESA test logger.
   * 
   * @return The MESA test logger.
   */
  public IMesaLogger getMesaLogger() {
      return mesaLogger;
  }

    /**
     * Set the custom logger for MESA test messages.  This is
     * only used by MESA testing programs.
     *
     * @param stream The logger for MESA messages
     */
    public void setMesaLogger(IMesaLogger logger) {
        mesaLogger = logger;
    }
    
    /**
     * Translate a XdsDOcumentEntry metadata object into a Misys Document object. 
     * Registry and IHE affinity domain codes are translated into Misys codes.
     * <p>
     * This method does essentially the inverse
     * of XdsDocumentSource which translates from Document objects to XdsDocumentEntry
     * metadata objects.
     *
     * @param entry The XdsDOcumentEntry metadata object
     * @return The Document object
     * @throws IheConfigurationException When the connection is not properly configured to translate codes
     * @see com.misyshealthcare.connect.ihe.XdsDocumentSource
     */
	public Document translateSingleDocumentEntry(XdsDocumentEntry entry) throws IheConfigurationException {
		Document document = new Document();
		// ** Get the title
		document.setTitle(entry.getTitle());
		// ** Now the author information
		// The author intitution and names are encoded as HL7 strings.
		// The author role and specialty are converted from external codes to internal symbols, if appropriate
		List<XdsAuthor> authorEntries = entry.getAuthors();
		List<AuthorDescriptor> authorDescriptors = new ArrayList<AuthorDescriptor>();
		for (XdsAuthor authorEntry : authorEntries) {
		    AuthorDescriptor authorDescriptor = new AuthorDescriptor();
		    String authorPerson = authorEntry.getAuthorPerson();
		    authorDescriptor.setAuthorPerson(HL7.fromXCN(authorPerson));
		    List<String> institutes = authorEntry.getAuthorInstitutions();
		    for (String institute : institutes) {
		        authorDescriptor.addAuthorInstitution( HL7.fromXON(institute) );
		    }
		    List<String> roles = authorEntry.getAuthorRoles();
		    for (String role : roles) {
		        authorDescriptor.addAuthorRole( Configuration.reverseStringMap(connection, role, "authorRole") );
		    }
		    List<String> specialities = authorEntry.getAuthorSpecialities();
		    for (String speciality : specialities) {
		        authorDescriptor.addAuthorSpeciality(Configuration.reverseStringMap(connection, speciality, "authorSpecialty"));
		    }
            authorDescriptors.add( authorDescriptor );
        }
        document.setAuthorDescriptors( authorDescriptors );
		// ** The other basic metadata slots
		// These slot values are simply encoded as appropriate HL7 strings
		document.setCreationTime(HL7.fromDTM(entry.getCreationTime()));
		document.setLanguage(entry.getLanguageCode());
		document.setLegalAuthenticator(HL7.fromXCN(entry.getLegalAuthenticator()));
		document.setMimeType(entry.getMimeType());
		document.setServiceStart(HL7.fromDTM(entry.getServiceStartTime()));
		document.setServiceEnd(HL7.fromDTM(entry.getServiceStopTime()));
		// ** All the various classification codes
		// Three of these classifications are translated to internal ENUM types from connection Codes
		Enum code = Configuration.reverseEnumMap(connection, entry.getClassCode(), XdsClassCode.class);
		if (code == null) code = XdsClassCode.UNKNOWN;
		document.setClassCode((XdsClassCode) code);
		code = Configuration.reverseEnumMap(connection, entry.getFormatCode(), XdsFormatCode.class);
		if (code == null) code = XdsFormatCode.UNKNOWN;
		document.setFormatCode((XdsFormatCode) code);
		code = Configuration.reverseEnumMap(connection, entry.getTypeCode(), XdsTypeCode.class);
		if (code == null) code = XdsTypeCode.UNKNOWN;
		document.setTypeCode((XdsTypeCode) code);
		// Two of these classifications are translated to internal symbols from connection Codes
		String symbol = Configuration.reverseStringMap(connection, entry.getHealthcareFacilityTypeCode(), "facilityCode");
		if (symbol == null) symbol = entry.getHealthcareFacilityTypeCode();
		document.setFacilityCode(symbol);
		symbol = Configuration.reverseStringMap(connection, entry.getPracticeSettingCode(), "practiceCode");
		if (symbol == null) symbol = entry.getPracticeSettingCode();
		document.setPracticeCode(symbol);
		// ** Event codes
		// Right now Misys Connect ignores event codes in Documents
		// ** Patient ID
		PatientID pid = null;
		Identifier localAuthority = Configuration.getIdentifier(connection, "LocalAssigningAuthority", false);
		if (localAuthority == null) {
		    // Can't be a local ID, just push it into a new PatientID object
		    pid = new PatientID();
		    HL7.fromCX(pid, entry.getPatientId());
		} else {
		    // Could be a local ID, check it out
		    String mId = HL7.getIdFromCX(entry.getPatientId());
		    Identifier mAuthority = HL7.getAssigningAuthorityFromCX(entry.getPatientId());
		    if ((mAuthority != null) && (mId != null)) {
		        if (mAuthority.equals(localAuthority)) {
		            // It is a local ID
		            pid = new PatientID(mId);
		        } else {
		            // It isn't a local ID
		            pid = new PatientID();
		            mAuthority.addPatientId(pid, mId);
		        }
		    }
		}
		document.setPatientId(pid);
		// ** Convert all the patient information
		// The rest of the patient information must be decoded from HL7-like message strings
		PatientDescriptor patient = new PatientDescriptor(pid);
		document.setPatientDescriptor(patient);
		populatePatientDescriptor(patient, entry.getSourcePatientInfo());
		// ** Confidentiality code
		// Connect defines document confidentiality as in SharedEnums.ConfidentialityCode.  So we map the
		// patient confidentiality into a connection Code
		List<Pair> confidentialityStrings = entry.getConfidentialityCodes();
		List<SharedEnums.ConfidentialityCode> confidentialityCodes = new ArrayList<SharedEnums.ConfidentialityCode>();
		for (Pair confidentialityPair : confidentialityStrings) {
		    Enum confidentialityCode = Configuration.reverseEnumMap(connection, (String)confidentialityPair._first, SharedEnums.ConfidentialityCode.class);
		    confidentialityCodes.add( (SharedEnums.ConfidentialityCode) confidentialityCode );
		}
		document.setConfidentialityCodes( confidentialityCodes );
		// ** Finally the unique ID  
		// The format of this identifier depends on the type of document, so we just copy it
		document.setUniqueId(entry.getUniqueId());
		// ** Now a few read-only properties
		IheInterface iheInterface = document.iheInterface();
		iheInterface.setNativeClassCode(entry.getClassCode());
		iheInterface.setNativeFormatCode(entry.getFormatCode());
		iheInterface.setNativeTypeCode(entry.getTypeCode());
		iheInterface.setUri(entry.getUri());
		// ** Create a nice description for the user
		String facilityCode = entry.getHealthcareFacilityTypeCodeDisplayName();
		String typeCode = entry.getTypeCodeDisplayName();
		String description = null;
		if ((facilityCode != null) && (typeCode != null)) {
		    if (facilityCode.equalsIgnoreCase(typeCode))	{
		        description = facilityCode;
		    } else {
		        description = facilityCode + ", " + typeCode;
		    }
		} else if (facilityCode != null) {
		    description = facilityCode;
		} else if (typeCode != null) {
		    description = typeCode;
		}
		iheInterface.setDescription(description);
		// ** Finally, create an object that knows how to retrieve the contents
		//    of this document using the right type of connection	
		IDocumentContents contents = null;
		//if URI is provided then use XSD.a, otherwise do XDS.b retrieving doc set
		if (entry.getUri() != null)
			contents = new XdsDocumentContents(entry.getUri(), connection, document, auditTrail, getMesaLogger());
		else 
			contents = new XdsbDocumentContents(entry.getUniqueId(), entry.getRepositoryId(), document, auditTrail, getMesaLogger());
		document.setContents(contents);
		// ** Done
		return document;
	}
	
    /**
     * Populate the patient descriptor for a Document with the data returned
     * in the XdsDocumentEntry metadata 'sourcePatientInfo' slot.
     * <p>
     * This method only translates the minimum number of information PID
     * components required to be supported by a registry.  A given registry
     * might support more PID components and this method will have to be
     * extended to decode them.
     *
     * @param patient The patient descriptor to be populated
     * @param info The information strings held in the source Patient Info metadata slot
     */
    private void populatePatientDescriptor(PatientDescriptor patient, Collection<String> info) {
        for (String pid: info) {
            if (pid != null) {
                String[] segments = pid.split("\\|");
                if (segments.length != 2) {
                    log.warn("Invalid patient info segment '" + pid + "' found in document metadata");
                } else {
                    String pidID = segments[0];
                    String pidData = segments[1];
                    if (pidID.equalsIgnoreCase("PID-5")) {
                        // Name
                        HL7.fromXPN(patient, pidData);
                    } else if (pidID.equalsIgnoreCase("PID-8")) {
                        // Gender
                        patient.setAdministrativeSex(HL7.fromSex(pidData));
                    } else if (pidID.equalsIgnoreCase("PID-7")) {
                        // Birth date
                        patient.setBirthDateTime(HL7.fromDT(pidData));
                    } else if (pidID.equalsIgnoreCase("PID-11")) {
                        // Address
                        Address address = HL7.fromXAD(pidData);
                        if (address != null) {
                            address.setAddType(AddressType.UNKNOWN);
                            patient.addAddress(address);
                        }
                    } else if (!pidID.equalsIgnoreCase("PID-3")) {
                        // Something else other than PID-3 (PID-3 should be ignored)
                        log.warn("Ignoring patient info segment '" + pid + "'");
                    }
                }
            }
        }
    }

}
