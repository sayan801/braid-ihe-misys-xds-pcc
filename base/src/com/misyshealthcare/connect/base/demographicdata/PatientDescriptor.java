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
package com.misyshealthcare.connect.base.demographicdata;

import java.util.*;

import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.clinicaldata.Visit;
//import com.misyshealthcare.connect.sysreg.RegistrationException;
//import com.misyshealthcare.connect.sysreg.SystemRegistry;
//import com.misyshealthcare.connect.util.StringUtil;

/**
 * This class represents a patient throughout the Connect
 * system.  It is returned by Patient Sources and is used
 * for passing patient information out to external IHE 
 * systems.
 * <p>
 * Also, the object will contain <code>PatientId</code> which contains
 * a Map of all the IDs for this Patient. This will include all Misys system IDs
 * if coming from teh ConnectPVR OR all teh PDQ server(s) IDs if coming from IHE
 * <p>
 * An additional field called "homeSystem" will specify where this Patient actually
 * came from - use this to get the appropriate "System specific ID" from the
 * <code>Patient</code> object.
 * 
 * @author Jim Firby, Shantanu Paul
 * @version 2.0 -  Oct 26, 2005
 **/
public class PatientDescriptor {

	/* Required by everything */
	private PatientID patientId = null;
	
	/* Fields needed by Misys Connect */
	private String homeSystem = null;

	/* Fields required by IHE profiles */	
    private String      nameFirst = null;
    private String      nameLast = null;
    private String      nameMiddle = null;
    private String      nameSuffix = null;
    private String      nameTitle = null;
    private String      ssn = null;
    private String      driverLicense = null;
    private SexType     administrativeSex = null;
    private String      motherMaidenName = null;
    private Date        birthDateTime = null;
    private List<PhoneNumber> phoneList = new ArrayList<PhoneNumber>();
    private List<Address>  addressList = new ArrayList<Address>();
    private List<Visit>           visitList = new ArrayList<Visit>();
    private List<PatientMRN>      mrnList = new ArrayList<PatientMRN>();
    private List<Insurance>	insuranceList = new ArrayList<Insurance>();
    private boolean     confidential = false;
    private boolean     deleted      = false;
    private String      facilityName = null;
    private Calendar    lastUpdatedDate = new GregorianCalendar();

    private String      uniquePatientId;

    /* ------ Constructors ------------ */
    
    /**
     * Create a new, empty patient descriptor.
     */
    public PatientDescriptor() {
    	super();
    }
    
    /**
     * Create a new patient descriptor for the patient with
     * the given ID.
     * 
     * @param id The ID for the patient descriptor
     */
    public PatientDescriptor(PatientID id) {
    	super();
    	patientId = id;
    }

    /*---- GETTER AND SETTERS ----------------------------------------*/
   
    /**
     * Get the <code>PatientId</code> object
     * @return the PatientId object
     */
    public PatientID getPatientId() {
        return patientId;
    }

    /**
     * Set the <code>PatientId</code> object
     * @param patientId
     */
    public void setPatientId(PatientID patientId) {
        this.patientId = patientId;
    }

    public String getUniquePatientId() {
        return uniquePatientId;
    }

    public void setUniquePatientId(String uniquePatientId) {
        this.uniquePatientId = uniquePatientId;
    }

    /**
     * Get the "home system" for this object - this is the system
     * from where this <code>PatientDescriptor</code> originated
     *
     * The home system returned from this method can be used to get the
     * system specific ID from the PatientId object
     * @return the home system
     */
    public String getHomeSystem() {
        return homeSystem;
    }
    /**
     * Set the "home system" i.e. the system from where this object
     * originated.
     * @param misysHomeSystem
     */
    public void setHomeSystem(String misysHomeSystem) {
        this.homeSystem = misysHomeSystem;
    }

    
    /**
     * Get the "home system" for this object - this is the system
     * from where this <code>PatientDescriptor</code> originated
     *
     * The home system returned from this method can be used to get the
     * system specific ID from the PatientId object
     * @return the home system
     */
    // TODO make sure this method isn't used...
 /*   public String getHomeSystemName() {
        try {
			String sName = SystemRegistry.getInstance().getSystemName(homeSystem);
			if (sName == null || sName.trim().equals("")){
				return homeSystem;
			}
			return sName;
		} catch (RegistrationException e) {
			return homeSystem;
		}
    }*/
    
    
    /**
     * Get the First Name of the Patient
     * @return
     */
    public String getNameFirst() {
        return nameFirst;
    }
    /**
     * Set the First Name of the Patient
     * @param nameFirst
     */
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }
    /**
     * Get the Last Name of the Patient
     * @return
     */
    public String getNameLast() {
        return nameLast;
    }
    /**
     * Set the last Name of the Patient
     * @param nameLast
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }
    /**
     * Get the middle name of the Patient
     * @return
     */
    public String getNameMiddle() {
        return nameMiddle;
    }
    /**
     * Set the middle name of the Patient
     * @param nameMiddle
     */
    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }
    /**
     * Get the name suffix for the Patient
     * @return
     */
    public String getNameSuffix() {
        return nameSuffix;
    }
    /**
     * Set the name suffix
     * @param nameSuffix
     */
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }
    /**
     * Get the name title
     * @return
     */
    public String getNameTitle() {
        return nameTitle;
    }
    /**
     * Set the name title
     * @param nameTitle
     */
    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }
    /**
     * Get the Social Security Number
     * @return
     */
    public String getSsn() {
        return ssn;
    }
    /**
     * Set the Social Security Number
     * @param ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    /**
     * Get the Driver's License number
     * @return
     */
    public String getDriverLicense() {
        return driverLicense;
    }
    /**
     * Set the Driver's License number
     * @param driverLicense
     */
    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }
 
    /**
     * Get the gender (administrative Sex)
     * @return
     */
    public SexType getAdministrativeSex() {
        return administrativeSex;
    }
    /**
     * Set the gender
     * @param administrativeSex
     */
    public void setAdministrativeSex(SexType administrativeSex) {
        this.administrativeSex = administrativeSex;
    }
    /**
     * Get mother's maiden name
     * @return
     */
    public String getMotherMaidenName() {
        return motherMaidenName;
    }
    /**
     * Set mother's maiden name
     * @param motherMaidenName
     */
    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }
    /**
     * Get the birthdate
     * @return
     */
    public Date getBirthDateTime() {
        return birthDateTime;
    }
    /**
     * Set the birth date
     * @param birthDateTime
     */
    public void setBirthDateTime(Date birthDateTime) {
        this.birthDateTime = birthDateTime;
    }
    /**
     * Get a list of addresses for this Patient
     * @return a list of <code>Address</code> objects
     */
    public List<Address> getAddressList() {
        return addressList;
    }
    /**
     * Set the addresses for this Patient
     * @param addressList
     */
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
    /**
     * Get a list of visits
     * @return a list of <code>Visit</code> objects
     */
    public List<Visit> getVisitList() {
        return visitList;
    }
    /**
     * Set teh visits for this patient
     * @param visitList
     */
    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }
    /**
     * Get a list of insurance
     * @return a list of <code>Insurance</code> objects
     */
    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }
    /**
     * Set the insurance for this patient
     * @param insuranceList
     */
    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }
    /**
     * Get a list of Medical Record Numbers (MRN) for the patient
     * @return a list of <code>PatientMRN</code> objects
     */
    public List<PatientMRN> getMrnList() {
        return mrnList;
    }
    /**
     * Set a list of MRNs for this Patient
     * @param mrnList
     */
    public void setMrnList(List<PatientMRN> mrnList) {
        this.mrnList = mrnList;
    }
    /**
     * Indicates whether this patient's records are confidential
     * @return true if yes, false otherwise
     */
    public boolean isConfidential() {
        return confidential;
    }
    /**
     * Set the confidentiality
     * @param confidential
     */
    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }
    /**
     * Indicates whether this patient is marked as deleted
     * @return
     */
    public boolean isDeleted() {
        return deleted;
    }
    /**
     * Set teh deletion flag
     * @param deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Get the last updated date for this patient record
     * @return
     */
    public Calendar getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Set the last updated date of this patient record
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Calendar lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /* ---- SOME HELPER METHODS ----------------------------------------- */
    /**
     * Get the age of this patient at this moment in time as the number of days old.
     *
     * @return The age of this patient in days
     */
    public int getAgeInDays() {
        if (birthDateTime == null) {
        	return 0;
        }
        
        Calendar now = Calendar.getInstance();
    	Calendar calBirthDateTime = Calendar.getInstance();
    	calBirthDateTime.setTime(birthDateTime);
    	if (now.before(calBirthDateTime)) {
    		return 0;
    	}
    	
        long daysSinceBirth = (now.getTimeInMillis() - calBirthDateTime.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        return (int)daysSinceBirth;
    }
    /**
     * Add a mrn to the list of MRNs
     * @param mrn - the MRN to be added
     */
    public void addPatientMRN(PatientMRN mrn) {
        if(mrn != null && !mrnList.contains(mrn)) {
            mrnList.add(mrn);
        }
    }
    /**
     * Remove a MRN from the list of MRNs
     * @param mrn - the MRN to be removed
     */
    public void removePatientMRN(PatientMRN mrn) {
        if(mrnList.contains(mrn))
            mrnList.remove(mrn);
    }
    /**
     * Add a Insurance to the list of Insurances
     * @param insurance - the Insurance to be added
     */
    public void addInsurance(Insurance insurance) {
        if(insurance != null && !insuranceList.contains(insurance)) {
        	insuranceList.add(insurance);
        }
    }
    /**
     * Remove a Insurance from the list of Insurances
     * @param insurance - the Insurance to be removed
     */
    public void removeInsurance(Insurance insurance) {
        if(insuranceList.contains(insurance))
        	insuranceList.remove(insurance);
    }
    /**
     * Add a visit to the visit list
     * The visit would be added only if teh visit does not already exist
     * 2 visits are considered same if they have the same <b>systemId</b> and
     * same <b>visitId</b>
     *
     * @param visit - the visit to be added
     */
    public void addVisit(Visit visit) {
         if(visit != null && !visitList.contains(visit))
             visitList.add(visit);
    }
    /**
     * Remove a visit from the list
     * @param systemId - the systemId of the visit to be removed
     * @param visitId - the visitId of the visit to be removed
     */
    public void removeVisit(String systemId, String visitId) {
        for(Visit v: visitList){
            if(v.getSystemId().equals(systemId) && v.getVisitId().equals(visitId))
                visitList.remove(v);
        }
    }


	/* --------------------------------------------------------------- */
	/* Misys helper functions */
	
	/**
	 * Get the full name of this patient by appending together
	 * all of the name pieces.
	 * 
	 * @return The full name of this patient as a string
	 */
	public String getNameString() {
		return Formatter.getNameString(this);
	}
	
	/**
	 * Get the full name of this patient by appending together
	 * all of the name pieces in a form suitable for putting in
	 * an alpahbetical list (ie. last name first).
	 * 
	 * @return The full name of this patient, alphabetized
	 */
	public String getAlphabeticalNameString() {
		return Formatter.getAlphabeticalNameString(this);
	}

	/**
	 * Get a full address of this patient by appending together
	 * all of the address pieces.
	 * 
	 * @param type The particular address to format
	 * @return The full address as a string
	 */
	public String getAddressString(AddressType type) {
		Address address = getAddress(type);
		if (address == null) return "";
		return Formatter.getAddressString(address);
	}
	
	/**
	 * Get a phone number for this patient formatted as a string.
	 * 
	 * @param type The particular phone number to format
	 * @return The phone number as a string
	 */
	public String getPhoneNumberString(PhoneType type) {
		PhoneNumber phone = getPhoneNumber(type);
		if (phone == null) return "";
		return Formatter.getPhoneString(phone);
	}
	
	/* -------------------------------------------------------------- */
	/* Getters and Setters */

  /**
   * Add an address to this patient.
   *
   * @param address The address to add
   */
  public void addAddress(Address address) {
      // Get the address type
      if (address == null) return;
      AddressType type = address.getAddType();
      // Remove the old entry
      removeAddress(type);
      // Add the new entry
      if (addressList == null) addressList = new ArrayList<Address>();
      addressList.add(address);
  }

	/**
	 * Remove the address entry of this type.
	 * 
	 * @param type The address type (Ie. "Home", "Work")
	 */
	public void removeAddress(AddressType type) {
		if (addressList == null) return;
		// Remove the old entry for this type of address
		for (int i=0; i<addressList.size(); i++) {
			Address next = addressList.get(i);
			AddressType nextType = next.getAddType();
			if (nextType == null) {
				if (type == null) {
					addressList.remove(i);
					break;
				}
			} else if ((type != null) && nextType.equals(type)) {
				addressList.remove(i);
				break;
			}
		}		
	}
	
	/**
	 * Get a particular address entry from this patient.
	 * 
	 * @param type The address type (Ie. "Home", "Work")
	 * @return The address entry of the given type, if present
	 */
	public Address getAddress(AddressType type) {
		if (addressList == null) return null;
		// Remove the old entry for this type of address
		for (int i=0; i<addressList.size(); i++) {
			Address next = addressList.get(i);
			AddressType nextType = next.getAddType();
			if (nextType == null) {
				if (type == null) return next;
			} else if ((type != null) && nextType.equals(type)) {
				return next;
			}
		}
		// Couldn't find a match
		return null;
	}

	/**
	 * Return the phone number list for this patient.
	 * 
	 * @return The phone number list
	 */
	public List<PhoneNumber> getPhoneList() {
		return phoneList;
	}
	
	/**
	 * Set the phone number list for this patient.
	 * 
	 * @param phoneList The new phone number list
	 */
	public void setPhoneList(List<PhoneNumber> phoneList) {
		this.phoneList = phoneList;
	}
	
	/**
	 * Add a new phone number to the list for this patient.
	 * If there is already a number with the same type it will
	 * be replaces.
	 * 
	 * @param phone The new phone number
	 */
	public void addPhoneNumber(PhoneNumber phone) {
    // Get the phone number
    if (phone == null) return;
    PhoneType type = phone.getType();
    // Remove the old entry
    removePhoneNumber(type);
    // Add the new entry
    if (phoneList == null) phoneList = new ArrayList<PhoneNumber>();
    phoneList.add(phone);
	}

	/**
	 * Remove a particular type of phone number from the
	 * list for this patient.
	 * 
	 * @param type The type of phone number to remove
	 */
	public void removePhoneNumber(PhoneType type) {
		if (phoneList == null) return;
		// Remove the old entry for this type of address
		for (int i=0; i<phoneList.size(); i++) {
			PhoneNumber next = phoneList.get(i);
			PhoneType nextType = next.getType();
			if (nextType == null) {
				if (type == null) {
					phoneList.remove(i);
					break;
				}
			} else if ((type != null) && nextType.equals(type)) {
				phoneList.remove(i);
				break;
			}
		}		
	}

	/**
	 * Get a particular phone number from the list for this
	 * patient.
	 * 
	 * @param type The type of phone number to get
	 * @return The phone number
	 */
	public PhoneNumber getPhoneNumber(PhoneType type) {
		if (phoneList == null) return null;
		// Remove the old entry for this type of address
		for (int i=0; i<phoneList.size(); i++) {
			PhoneNumber next = phoneList.get(i);
			PhoneType nextType = next.getType();
			if (nextType == null) {
				if (type == null) return next;
			} else if ((type != null) && nextType.equals(type)) {
				return next;
			}
		}
		// Couldn't find a match
		return null;
	}

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
    
	/**
	 * Sort a new patient into a patient descriptor list.  Sort the list
	 * alphabetically by name.  Patients with no name get
	 * added to the end.
	 * 
	 * @param patients The current sorted list of patients
	 * @param patient The new patient to sort into the list
	 * @return The new list of sorted patients
	 */
	static public List<PatientDescriptor> insertPatientIntoList(List<PatientDescriptor> patients, PatientDescriptor patient) {
		// If this patient is already in the list, don't add it
		if (patients.contains(patient)) return patients;
		// Sort this patient in based on name
		String patientName = patient.getAlphabeticalNameString();
		if (patientName == null) {
			// Don't know name, just add to the end
			patients.add(patient);
		} else {
			boolean found = false;
			int i = 0;
			while (i < patients.size()) {
				// Find the right place to put this patient
				String listName = patients.get(i).getAlphabeticalNameString();
				if (listName == null) {
					found = true;
					break;
				} else if (patientName.compareToIgnoreCase(listName) < 0) {
					found = true;
					break;
				}
				i = i + 1;
			}
			// Squeeze it in
			if (found && (i <= patients.size())) {
				patients.add(i, patient);
			} else {
				patients.add(patient);
			}
		}
		return patients;
	}


}
