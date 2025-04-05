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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;


/**
 * This class represents a set of PatientDescriptor objects
 * that all correspond to the same patient.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 22, 2005
 */
public class PatientDescriptorSet implements Iterable, Comparable {

	/* Logger for problems and debugging messages */
  private static Logger log = Logger.getLogger(PatientDescriptorSet.class);

  /* The PatientID for the patient represented by all descriptors */
	private PatientID patientId = null;
	/* The patient descriptors for this patient */
	private List<PatientDescriptor> descriptors = null;
	private PatientDescriptor masterPatient;
	
	/**
	 * @return Returns the masterPatient.
	 */
	public PatientDescriptor getMasterPatient() {
		return masterPatient;
	}

	/**
	 * @param masterPatient The masterPatient to set.
	 */
	public void setMasterPatient(PatientDescriptor masterPatient) {
		this.masterPatient = masterPatient;
	}

	/**
	 * Create a new, empty patient descriptor set.  Patients are
	 * added to this set using the 'add' method.
	 * @see PatientDescriptorSet#add
	 */
	public PatientDescriptorSet() {
		this(null);
	}
	
	/**
	 * Create a new patient descriptor set holding the patient descriptor
	 * passed in.  Additional patients are added to this set using the
	 * 'add' method.
	 * 
	 * @param patient The first patient in this set
	 * @see PatientDescriptorSet#add
	 */
	public PatientDescriptorSet(PatientDescriptor patient) {
		descriptors = new Vector<PatientDescriptor>();
		if (patient != null) {
			patientId = patient.getPatientId();
			descriptors.add(patient);
		}
	}
	
	/**
	 * Get the patient ID for all of the patients in this set.
	 * 
	 * @return The patient ID for all the patients in this set
	 */
	public PatientID getPatientId() {
		return patientId;
	}
	
	/**
	 * Get the alphabetical name of the first patient in this set
	 * 
	 * @return The alphabetical name of the patient in this set
	 */
	public String getAlphabeticalNameString() {
		if (descriptors.size() < 1) {
			return null;
		} else if (this.getMasterPatient() != null) {
			return this.getMasterPatient().getAlphabeticalNameString();
		}else{
			return descriptors.get(0).getAlphabeticalNameString();
		}
	}
	
	/* --------------------------------------------------- */
	/* Enforce all descriptors be for the same patient     */
	
	/**
	 * Add a new patient to this set.
	 * 
	 * @param descriptor The PatientDescriptor to add to this set
	 * @return True if this patient could be added, false if the patient being added is not the same patient as in the set
	 */
	public boolean add(PatientDescriptor descriptor) {
		if (patientId == null) {
			// No patients yet, add this one
			patientId = descriptor.getPatientId();
			descriptors.add(descriptor);
			return true;
		} else if (patientId.equals(descriptor.getPatientId())) {
			// Patient compatible with existing patients, add it
			try {
				patientId = patientId.merge(descriptor.getPatientId());
			} catch (PatientException e) {
				// This should never happen, if it does log it
				log.error("Internal inconsistency with Patient IDs", e);
				return false;
			}
			// Sort this patient into the list using the PatientBroker criteria
			PatientDescriptor.insertPatientIntoList(descriptors, descriptor);
			// Put this new merged ID into every patient
			for (PatientDescriptor d: descriptors) {
				d.setPatientId(patientId);
			}
			// Successfull merge
			return true;
		} else {
			// A different patient
			return false;
		}
	}
	
	public boolean merge(PatientDescriptorSet patientSet) {
		if (patientId == null) {
			// We're empty?
			return false;
		} else if (patientId.equals(patientSet.getPatientId())) {
			// This patient set and the one passed in are for the same patient
			try {
				patientId = patientId.merge(patientSet.getPatientId());
			} catch (PatientException e) {
				// This should never happen, if it does log it
				log.error("Internal inconsistency with Patient IDs", e);
				return false;
			}
			// Sort all the new patients into our list using the PatientBroker criteria
			for (PatientDescriptor patient: patientSet.descriptors) {
				PatientDescriptor.insertPatientIntoList(descriptors, patient);
			}
			// Put this new merged ID into every patient
			for (PatientDescriptor d: descriptors) {
				d.setPatientId(patientId);
			}
			// Successfull merge
			return true;
		} else {
			// The passed in set is for a different patient
			return false;
		}
	}

  /* --------------------------------------------------- */
  /* Enable this object to act as a collection iterator  */
	
	/**
	 * @return The number of patients in this set
	 */
	public int size() {
		return descriptors.size();
	}
	
	/**
	 * @return True if there are no patients in this set, False otherwise
	 */
	public boolean isEmpty() {
		return descriptors.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator iterator() {
		return descriptors.iterator();
	}
	public int compareTo(Object o){
		if (o instanceof PatientDescriptorSet){
			PatientDescriptorSet pdsObject = (PatientDescriptorSet) o;
			PatientDescriptor pMaster = pdsObject.getMasterPatient();
			if (pMaster  == null){
				return 1;
			}
			return this.getAlphabeticalNameString().compareToIgnoreCase(pdsObject.getAlphabeticalNameString());
			//return this.masterPatient.getNameLast().compareToIgnoreCase(pMaster.getNameLast());
			
		}
		
		return 0;
	}
}
