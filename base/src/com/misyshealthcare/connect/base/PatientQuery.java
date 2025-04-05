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

import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientMRN;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;

/**
 * This class represents a patient query.  To find a set of
 * patients, create a <code>PatientQuery</code> object and
 * fill in the slots with data about the patients to be found.
 * <p>
 * Then, call PatientBroker.findPatients(query), using the query
 * object to get the list of patient descriptors.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 27, 2005
 */
public class PatientQuery {

	/* Required by everything */
	private String misysPatientId = null;
	private String homeSystem = null;
    private String systemPatientId = null;
	
	/* Fields with a single value per patient */	
	private String   nameFirst = null;
	private String   nameLast = null;
	private String   nameMiddle = null;
	private String   nameSuffix = null;
	private String   nameTitle = null;
	private String   ssn = null;
	private String   driverLicense = null;
	private SexType  administrativeSex = null;
	private String   motherMaidenName = null;
	private DateQuery   birthDateTime = null;
	private String systemId = null;
    //To be removed later
    private String gender = null;
	/* Fields with multiple values for patient, but only one in query */
	private PhoneNumber phone = null;
	private Address     address = null;
	private PatientMRN  mrn = null;
	private Provider    provider = null;
	
	/* Fields for Misys UI */
	private int maxPatientCount = -1;
	private boolean homeSystemOnly = false;

    /*The query is to be based on the default Query design properties. By Default, it is true. */
    private boolean useDefaultQueryDesignProperites = true;
    /**Wildcard prefix characters. Applys only to the string fields*/
    private String prefix = null;
    /**Wildcare suffix characters. Applys only to the string fields*/
    private String suffix = null;

	    /*---- GETTER AND SETTERS ----------------------------------------*/
	   
	    /**
	     * Get the string holding the Misys ID of the patient being sought
	     * @return the Misys PatientId
	     */
	    public String getMisysPatientId() {
	        return misysPatientId;
	    }

	    /**
	     * Set the Misys ID of the patient being sought
	     * @param misysPatientId
	     */
	    public void setMisysPatientId(String misysPatientId) {
	        this.misysPatientId = misysPatientId;
	    }

	    /**
		 * @return Returns the homeSystem.
		 */
		public String getHomeSystem() {
			return homeSystem;
		}

		/**
		 * @param homeSystem The homeSystem to set.
		 */
		public void setHomeSystem(String homeSystem) {
			this.homeSystem = homeSystem;
		}

        /**
	     * Get the string holding the system patient ID being sought
	     * @return the PatientId of the System
	     */
	    public String getSystemPatientId() {
	        return systemPatientId;
	    }

	    /**
	     * Set the system patient ID being sought
	     * @param systemPatientId the PatientId of the System
	     */
	    public void setSystemPatientId(String systemPatientId) {
	        this.systemPatientId = systemPatientId;
	    }
	    
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
	    public DateQuery getBirthDateTime() {
	        return birthDateTime;
	    }
	    /**
	     * Set the birth date
	     * @param birthDateTime
	     */
	    public void setBirthDateTime(DateQuery birthDateTime) {
	        this.birthDateTime = birthDateTime;
	    }

			/**
			 * @return Returns the address.
			 */
			public Address getAddress() {
				return address;
			}

			/**
			 * @param address The address to set.
			 */
			public void setAddress(Address address) {
				this.address = address;
			}

			/**
			 * @return Returns the mrn.
			 */
			public PatientMRN getMrn() {
				return mrn;
			}

			/**
			 * @param mrn The mrn to set.
			 */
			public void setMrn(PatientMRN mrn) {
				this.mrn = mrn;
			}

			/**
			 * @return Returns the phone.
			 */
			public PhoneNumber getPhone() {
				return phone;
			}

			/**
			 * @param phone The phone to set.
			 */
			public void setPhone(PhoneNumber phone) {
				this.phone = phone;
			}

			/**
			 * @return Returns the provider.
			 */
			public Provider getProvider() {
				return provider;
			}

			/**
			 * @param provider The provider to set.
			 */
			public void setProvider(Provider provider) {
				this.provider = provider;
			}

			/**
			 * @return Returns the maxPatientCount.
			 */
			public int getMaxPatientCount() {
				return maxPatientCount;
			}

			/**
			 * @param maxPatientCount The maxPatientCount to set.
			 */
			public void setMaxPatientCount(int maxPatientCount) {
				this.maxPatientCount = maxPatientCount;
			}

			/**
			 * @return Returns the homeSystemOnly.
			 */
			public boolean getHomeSystemOnly() {
				return homeSystemOnly;
			}

			/**
			 * @param homeSystemOnly The homeSystemOnly to set.
			 */
			public void setHomeSystemOnly(boolean homeSystemOnly) {
				this.homeSystemOnly = homeSystemOnly;
			}

            public String getSystemId()
            {
                return systemId;
            }

            public void setSystemId(String systemId)
            {
                this.systemId = systemId;
            }

            public String getGender()
            {
                return gender;
            }

            public void setGender(String gender)
            {
                this.gender = gender;
            }

            /**
             *  Set whether to use Connect default query design properties. The query design properties is located in the
             *  file querydesign.properties. The default query design properties specify the query type for each query
             *  field. If the default query design properties is not used, the query type of each field will depend
             *  on whether the query value contains wildcard.
             *
             * @param useDefaultQueryDesignProperties
             */
            public void setUseDefaultQueryDesignProperties(boolean useDefaultQueryDesignProperties) {
                this.useDefaultQueryDesignProperites = useDefaultQueryDesignProperties;
            }

            /**
             *  Get whether to use Connect default query design properties. The query design properties is located in the
             *  file querydesign.properties. The default query design properties specify the query type for each query
             *  field. If the default query design properties is not used, the query type of each field will depend
             *  on whether the query value contains wildcard.
             *
             * @return <code>true</code> if use default query design properties
             */
            public boolean getUseDefaultQueryDesignProperties() {
                return this.useDefaultQueryDesignProperites;
            }

            public void setPrefixWildcard(String prefix) {
                this.prefix = prefix;
            }
            public String getPrefixWildcard() {
                return this.prefix;
            }

            public void setSuffixWildcard(String suffix) {
                this.suffix = suffix;
            }
            public String getSuffixWildcard() {
                return this.suffix;
            }

}
