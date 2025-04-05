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
package com.misyshealthcare.connect.base;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.misyshealthcare.connect.base.SharedEnums.XdsClassCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsFormatCode;
import com.misyshealthcare.connect.base.SharedEnums.XdsTypeCode;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;

/**
 * This class represents the metadata and contents for an
 * IHE document.  It is used for both representing documents
 * retreived from a repository and documents that are to be
 * submitted to a repository.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 19, 2005
 */
public class Document {
	
	/* The data fields needed in a document description */
	private PatientID patientId = null;                 // The patient ID this document relates to
	private PatientDescriptor patientDescriptor = null; // The descriptor of the patient this document relates to

	/* The content object supplying the source document from inside Misys Connect */
	private Object contents = null;
	
	private Date creationTime = null;
	private String mimeType = null;   // The MIME type for this document

    private List<AuthorDescriptor> authors = new ArrayList<AuthorDescriptor>();

    private Provider legalAuthenticator = null;
	
	private String title = null;
	private Date serviceStart = null;
	private Date serviceEnd = null;

	private XdsClassCode classCode = null;
	private XdsFormatCode formatCode = null;
	private String facilityCode = null;
	private String practiceCode = null;
	private XdsTypeCode typeCode = null;
	private List<SharedEnums.ConfidentialityCode> confidCodes = new ArrayList<SharedEnums.ConfidentialityCode>();
    private List<String> eventCodes = new ArrayList<String>();
    private String nativeTypeCode = null;
	private String nativeFormatCode = null;
	private String nativeClassCode = null;
	
	private String sourceId = null;
	private String uniqueId = null;
	private String replacesId = null;
	private String uri = null; // Read only
	
	private String language = "en-us";
	
	private String description = null; // Used by the UI
	
	/**
	 * Create a new, empty <code>Document</code>.
	 */
	public Document() {
		super();
	}
	
	/**
	 * Create a new <code>Document</code> for the given
	 * patient.
	 * 
	 * @param patient The descriptor of the patient this document is about
	 */
	public Document(PatientDescriptor patient) {
		patientDescriptor = patient;
		if (patient != null) {
			patientId = patient.getPatientId();
		}
	}
	
	/**
	 * Get the PatientID for the patient associated with
	 * this document.
	 * 
	 * @return The patient ID for this document
	 */
	public PatientID getPatientId() {
		return patientId;
	}
	
	/**
	 * Set the PatientID for the patient associated with
	 * this document.
	 * 
	 * @param pid The new PatientID
	 * @throws DocumentException When the patient descriptor has a different patient ID
	 */
	public void setPatientId(PatientID pid) throws DocumentException {
		if ((pid == null) || (patientDescriptor == null) || (pid.equals(patientDescriptor.getPatientId()))) {
			patientId = pid;
		} else {
			throw new DocumentException("Cannot set a patient ID that does not match the patient descriptor");			
		}
	}
	
	/**
	 * Get the PatientDescriptor for the patient associated with
	 * this document.
	 * 
	 * @return The PatientDescriptor for this document
	 */
	public PatientDescriptor getPatientDescriptor() {
		return patientDescriptor;
	}
	
	/**
	 * Set the PatientDescriptor for the patient associated with
	 * this document.
	 * 
	 * @param descriptor The new PatientDescriptor
	 * @throws DocumentException When this PatientDescriptor does not match this document's PatientID
	 */
	public void setPatientDescriptor(PatientDescriptor descriptor) throws DocumentException {
		if (descriptor == null) {
			// Setting it to null, okay
			patientDescriptor = null;
		} else if ((patientId == null) || (patientId.equals(descriptor.getPatientId()))) {
			// Setting it to something equivalent, okay
			patientId = descriptor.getPatientId();
			patientDescriptor = descriptor;
		} else {
			// The descriptor is for a different patient
			throw new DocumentException("Cannot set a patient descriptor that does not match the patient ID");
		}
	}

	/**
	 * Check whether this document has a content object associated
	 * with it.  Descriptors returned by findDocument will typically
	 * not have contents, but they will have a URL.
	 * 
	 * @return True is this descriptor has an associated content
	 * @see DocumentBroker#findDocuments
	 */
	public boolean hasContents() {
    return (contents != null);
	}
	
	/**
	 * Get the content object associated with this document.
	 * 
	 * @return Returns the content object associated with this document.
	 */
	public Object getContents() {
		return contents;
	}

	/**
	 * Set the content object associated with this document.
	 * 
	 * @param contents The content object to associate with this document
	 */
	public void setContents(Object contents) {
		this.contents = contents;
	}

	/**
	 * Get the date and time this document was created.
	 * 
	 * @return The creation timestamp
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * Set the date and time this document was created.
	 * 
	 * @param creationTimestamp The creation timestamp
	 */
	public void setCreationTime(Date creationTimestamp) {
		this.creationTime = creationTimestamp;
	}

	/**
	 * @return Returns the mimeType.
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType The mimeType to set.
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

    /**
     * Get the list of AuthorDescriptor
     *
     * @return a List of AuthorDescriptor
     */
    public List<AuthorDescriptor> getAuthorDescriptors() {
        return this.authors;
    }

    /**
     * Set the list of AuthorDescriptor to the given list
     *
     * @param authors The list of AuthorDescriptors to be set
     */
    public void setAuthorDescriptors(List<AuthorDescriptor> authors) {
        this.authors = authors;
    }

    /**
     * Add an AuthorDescriptor to the existing AuthorDescriptor list.
     *
     * @param author The AuthorDescriptor to be added
     */
    public void addAuthorDescriptor(AuthorDescriptor author) {
        this.authors.add(author);
    }

	/**
	 * @return Returns the classCode.
	 */
	public XdsClassCode getClassCode() {
		return classCode;
	}

	/**
	 * @param classCode The classCode to set.
	 */
	public void setClassCode(XdsClassCode classCode) {
		this.classCode = classCode;
	}

	/**
	 * @return Returns the facilityCode.
	 */
	public String getFacilityCode() {
		return facilityCode;
	}

	/**
	 * @param facilityCode The facilityCode to set.
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	/**
	 * The format code for a document will be selected from the
	 * predefined ENUM set.  If there is no match in the known
	 * set, this value will be null.
	 *
	 * @return Returns the formatCode.
	 */
	public XdsFormatCode getFormatCode() {
		return formatCode;
	}

	/**
	 * The format code for a document must be selected from the
	 * predefined ENUM set.  Any number of different types of documents
	 * might be created for any Misys home system.
	 * 
	 * @param formatCode The formatCode to set.
	 */
	public void setFormatCode(XdsFormatCode formatCode) {
		this.formatCode = formatCode;
	}

	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return Returns the practiceCode.
	 */
	public String getPracticeCode() {
		return practiceCode;
	}

	/**
	 * @param practiceCode The practiceCode to set.
	 */
	public void setPracticeCode(String practiceCode) {
		this.practiceCode = practiceCode;
	}

	/**
	 * @return Returns the serviceStart.
	 */
	public Date getServiceStart() {
		return serviceStart;
	}

	/**
	 * @param serviceStart The serviceStart to set.
	 */
	public void setServiceStart(Date serviceStart) {
		this.serviceStart = serviceStart;
	}

	/**
	 * @return Returns the serviceStop.
	 */
	public Date getServiceEnd() {
		return serviceEnd;
	}

	/**
	 * @param serviceStop The serviceStop to set.
	 */
	public void setServiceEnd(Date serviceStop) {
		this.serviceEnd = serviceStop;
	}

	/**
	 * @return Returns the signingPerson.
	 */
	public Provider getLegalAuthenticator() {
		return legalAuthenticator;
	}

	/**
	 * @param signingPerson The signingPerson to set.
	 */
	public void setLegalAuthenticator(Provider signingPerson) {
		this.legalAuthenticator = signingPerson;
	}

	/**
	 * @return Returns the sourceId.
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId The sourceId to set.
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the typeCode.
	 */
	public XdsTypeCode getTypeCode() {
		return typeCode;
	}

    /**
     * Get a list of confidentiality codes.
     *
     * @return a list of cofidentiality codes
     */
    public List<SharedEnums.ConfidentialityCode> getConfidentialityCodes() {
        return this.confidCodes;
    }

    /**
     * Set a list of confidentiality codes.
     *
     * @param confidentialityCodes a list of confidentiality codes to be set
     */
    public void setConfidentialityCodes(List<SharedEnums.ConfidentialityCode> confidentialityCodes) {
        this.confidCodes = confidentialityCodes;
    }

    /**
     * Get a list of event codes of this document
     *
     * @return a list of event codes
     */
    public List<String> getEventCodes() {
        return this.eventCodes;
    }

    /**
     * Set a list of event codes
     *
     * @param eventCodes a list of event codes to be set
     */
    public void setEventCodes(List<String> eventCodes) {
        this.eventCodes = eventCodes;
    }

    /**
	 * @param typeCode The typeCode to set.
	 */
	public void setTypeCode(XdsTypeCode typeCode) {
		this.typeCode = typeCode;
	}
	
	/**
	 * @return Returns the uniqueId.
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId The uniqueId to set.
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * @return Returns the replacesId.
	 */
	public String getReplacesId() {
		return replacesId;
	}

	/**
	 * @param replacesId The replacesId to set.
	 */
	public void setReplacesId(String replacesId) {
		this.replacesId = replacesId;
	}

	/**
	 * @return Returns the uri.
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		if (description != null) {
			return description;
		} else if (title != null) {
			return title;
		} else if(authors.size() > 0){
            List<String> institutes = authors.get(0).getAuthorInstitutions();
            if (institutes.size() > 0) {
                return institutes.get(0);
            } else {
                return "";
            }
        }
        else {
			return "";
		}
	}

	/**
	 * Get the native format code for a document returned from a depository.
	 * The native format code is untranslated to internal Misys enum types.
	 * 
	 * @return The native format code, untranslated
	 */
	public String getNativeFormatCode() {
		return nativeFormatCode;
	}
	
	/**
	 * Get the native type code for a document returned from a depository.
	 * The native type code is untranslated to internal Misys enum types.
	 * 
	 * @return The native type code, untranslated
	 */
	public String getNativeTypeCode() {
		return nativeTypeCode;
	}
	
	/**
	 * Get the native class code for a document returned from a depository.
	 * The native class code is untranslated to internal Misys enum types.
	 * 
	 * @return The native class code, untranslated
	 */
	public String getNativeClassCode() {
		return nativeClassCode;
	}
	
	/**
	 * Return the contents of this document as a stream of
	 * bytes.
	 * 
	 * @return The content bytes of this document
	 */
	public InputStream getContentStream() {
		if (contents instanceof IDocumentContents) {
			return ((IDocumentContents) contents).getDocumentContentStream();
		} else {
			return null;
		}
	}
	
	/**
	 * Get an IHE interface that can be used to set some of the
	 * read-only properties.  This should be used only by the IHE
	 * actors.
	 * 
	 * @return An IHE interface object
	 */
	public IheInterface iheInterface() {
		return new IheInterface();
	}
	
	public class IheInterface {
		
		/**
		 * @param iheUri The uri to set.
		 */
		public void setUri(String iheUri) {
			uri = iheUri;
		}

		/**
		 * @param iheDescription The description to set.
		 */
		public void setDescription(String iheDescription) {
			description = iheDescription;
		}
		
		/**
		 * Set the native format code.  For use only in document sources.
		 * 
		 * @param iheCode The native format code
		 */
		public void setNativeFormatCode(String iheCode) {
			nativeFormatCode = iheCode;
		}

		/**
		 * Set the native type code.  For use only in document sources.
		 * 
		 * @param iheCode The native type code
		 */
		public void setNativeTypeCode(String iheCode) {
			nativeTypeCode = iheCode;
		}
		
		/**
		 * Set the native class code.  For use only in document sources.
		 * 
		 * @param iheCode The native class code
		 */
		public void setNativeClassCode(String iheCode) {
			nativeClassCode = iheCode;
		}
		
	}
	
}


