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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptorSet;

/**
 * This class presents a virtual interface to a collection of
 * patient information sources.  Some of these sources will be
 * external IHE sources.
 * <p>
 * This class presents a single global <code>PatientBroker</code>
 * instance to the Misys Connect 2.0 code.  That way it need
 * not be passed around in global web state.  It can simply be
 * initialized and then requested from any code whenever necessary.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 22, 2005
 */
public class PatientBroker implements IPatientXrefSource {
	
	/* A single instance for this class */
	private static PatientBroker singleton = null;
	
	/* Log for problems encountered by the PatientBroker */
	private static final Logger log = Logger.getLogger(PatientBroker.class);
	
	/* A list of all known sources of patient information */
	private Vector<IPatientSource> sources = new Vector<IPatientSource>();
	/* A list of all known consumers of patient information */
	private Vector<IPatientConsumer> consumers = new Vector<IPatientConsumer>();
	
	/* A list of all known sources of patient ID cross-reference */
	private Vector<IPatientXref> xrefs = new Vector<IPatientXref>();

	/**
	 * A private constructor for creating the singleton instance.
	 */
	private PatientBroker() {
		super();
	}
	
	/**
	 * Get the single global instance of the <code>PatientBroker</code>.
	 * 
	 * @return The patient broker
	 */
	public static synchronized PatientBroker getInstance() {
		if (singleton == null) {
			singleton = new PatientBroker();
			// PatientID always uses the most recent patient broker singleton:
			IPatientXrefFactory.getInstance().setSource(singleton);
		}
		return singleton;
	}
	
	/**
	 * Register a new source of patient information.  This method
	 * is typically called when Misys Connect is initialized to
	 * load up all of the known sources of patient information.
	 * A sources is started when it is registered.
	 * 
	 * @param source A source of patient information
	 * @return True if this source was successfully added
	 */
	public synchronized boolean registerPatientSource(IPatientSource source) {
		// If the source is new, add it to the list
		if ((source != null) && (!sources.contains(source))) {
			source.start();
			sources.add(source);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Unregister the active patient sources specified by the controller.  If the
	 * controller is null unregister all sources.  A source is stopped when it is
	 * unregistered.
	 * 
	 * @param controller The controller specifying which sources to unregister
	 * @return 'true' if any sources were actually unregistered
	 */
	public synchronized boolean unregisterPatientSources(IBrokerController controller) {
		ArrayList<IPatientSource> removed = new ArrayList<IPatientSource>();
		// Find all the sources to remove
		for (IPatientSource actor: sources) {
			if ((controller == null) || controller.shouldUnregister(actor)) {
				removed.add(actor);
			}
		}
		if (removed.isEmpty()) return false;
		// Remove them
		sources.removeAll(removed);
		// Stop them all too
		for (IPatientSource actor: removed) actor.stop();
		return true;
	}

	/**
	 * Get the number of patient sources known to this broker.  Used
	 * for JUnit tests.
	 * 
	 * @return The number of patient sources known to this broker
	 */
	public int patientSourceCount() {
		return sources.size();
	}
	
	/**
	 * Register a new consumer of patient information.  This method
	 * is typically called when Misys Connect is initialized to
	 * load up all of the known consumers of patient information.
	 * A patient consumer is started when it is registered.
	 * 
	 * @param consumer A consumer of patient information
	 * @return True if this consumer was successfully added
	 */
	public synchronized boolean registerPatientConsumer(IPatientConsumer consumer) {
		// If the consumer is new, add it to the list
		if ((consumer != null) && (!consumers.contains(consumer))) {
			consumer.start();
			consumers.add(consumer);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Unregister the active patient consumers specified by the controller.  If the
	 * controller is null unregister all consumers.  A consumer is stopped when it is
	 * unregistered.
	 * 
	 * @param controller The controller specifying which consumers to unregister
	 * @return 'true' if any consumers were actually unregistered
	 */
	public synchronized boolean unregisterPatientConsumers(IBrokerController controller) {
		ArrayList<IPatientConsumer> removed = new ArrayList<IPatientConsumer>();
		// Find all the sources to remove
		for (IPatientConsumer actor: consumers) {
			if ((controller == null) || controller.shouldUnregister(actor)) {
				removed.add(actor);
			}
		}
		if (removed.isEmpty()) return false;
		// Remove them
		consumers.removeAll(removed);
		// Stop them all too
		for (IPatientConsumer actor: removed) actor.stop();
		return true;
	}

	/**
	 * Get the number of patient consumers known to this broker.  Used
	 * for JUnit tests.
	 * 
	 * @return The number of patient consumers known to this broker
	 */
	public int patientConsumerCount() {
		return consumers.size();
	}
	
	/**
	 * Register a new patient ID crossreference manager.  This method
	 * is typically called when Misys Connect is initialized to
	 * load up all of the known xref sources for patient identity.
	 * An xref is started when it is registered.
	 * 
	 * @param xref An xref for patient identity
	 * @return True if this consumer was successfully added
	 * @see com.misyshealthcare.connect.base.PatientID
	 */
	public synchronized boolean registerPatientXref(IPatientXref xref) {
		// If the xref is new, add it to the list
		if ((xref != null) && (!xrefs.contains(xref))) {
			xref.start();
			xrefs.add(xref);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Unregister the active patient xrefs specified by the controller.  If the
	 * controller is null unregister all xrefs.  An xref is stopped when it is
	 * unregistered.
	 * 
	 * @param controller The controller specifying which xrefs to unregister
	 * @return 'true' if any xrefs were actually unregistered
	 */
	public synchronized boolean unregisterPatientXrefs(IBrokerController controller) {
		ArrayList<IPatientXref> removed = new ArrayList<IPatientXref>();
		// Find all the sources to remove
		for (IPatientXref actor: xrefs) {
			if ((controller == null) || controller.shouldUnregister(actor)) {
				removed.add(actor);
			}
		}
		if (removed.isEmpty()) return false;
		// Remove them
		xrefs.removeAll(removed);
		// Stop them all too
		for (IPatientXref actor: removed) actor.stop();
		return true;
	}

	/**
	 * Get the list of patient ID xref sources.  This method is used by the
	 * PatientID object.
	 * 
	 * @return A synchronized list of xref objects
	 */
	public Vector<IPatientXref> getPatientXrefs() {
		return xrefs;
	}
	
	/**
	 * Get the number of patient cross reference services known to this broker.  Used
	 * for JUnit tests.
	 * 
	 * @return The number of patient cross reference services known to this broker
	 */
	public int patientXrefCount() {
		return xrefs.size();
	}


	/**
	 * Return a list of all the patient descriptors from all sources
	 * that match the query.
	 * 
	 * @param query The profile of the patients being sought
	 * @return The list of patient descriptors that match the query
	 */
	public synchronized List<PatientDescriptor> findPatients(PatientQuery query) {
		// The resulting patient list
		List<PatientDescriptor> result = null;
		if (sources.isEmpty()) {
			// No sources to check
			result = new Vector<PatientDescriptor>();
		} else {
			// Try to find matches in every known source
			result = new Vector<PatientDescriptor>();
			for (IPatientSource source: sources) {
				List<PatientDescriptor> patients = findSourcePatients(source, query);
				// For every patient found, fit it into the list
				for (PatientDescriptor patient: patients) {
					// Sort the descriptors as they are inserted
					PatientDescriptor.insertPatientIntoList(result, patient);
				}
			}
		}
		// Enforce the maximum patient count
		int maxCount = query.getMaxPatientCount();
		if ((maxCount < 1) || (result.size() <= maxCount)) {
			// No limit
			return result;
		} else {
			return result.subList(0, maxCount);
		}
	}

	/**
	 * Find all of the patients matching the profile in the supplied
	 * query and return a fully fleshed out descriptor set for each 
	 * patient found.  Each descriptor set holds a collection of descriptors
	 * for the same patient that come from different sources.
	 * 
	 * @param query The profile of the patients being sought
	 * @return A list of descriptor sets, one for each patient found
	 */
	public synchronized List<PatientDescriptorSet> findPatientSets(PatientQuery query) {
		List<PatientDescriptorSet> vpds = new Vector<PatientDescriptorSet>();
		// Try to find matches in every known source
		for (IPatientSource source: sources) {
			List<PatientDescriptor> patients = findSourcePatients(source, query);
			// For every patient found, fit it into a descriptor set
			for (PatientDescriptor patient: patients) {
				boolean found = false;
				// Try and merge this patient into an existing set
				for (PatientDescriptorSet pds: vpds) {
					if (pds.add(patient)) {
						found = true;
						break;
					}
				}
				// If the patient doesn't fit in any set, make a new one
				if (!found) {
					vpds.add(new PatientDescriptorSet(patient));
				}
			}
		}
		// Make sure all possible merges have taken place (the above will miss some)
		// This algorithm could get a little slow for large patient lists.
		boolean keepMerging = (vpds.size() > 1);
		while (keepMerging)	{
			keepMerging = false;
			for (int i=0; i<vpds.size()-1; i++) {
				PatientDescriptorSet ipds = vpds.get(i);
				if (ipds != null) {
					for (int j=i+1; j<vpds.size(); j++) {
						PatientDescriptorSet jpds = vpds.get(j);
						if (jpds != null) {
							if (ipds.merge(jpds)) {
								vpds.set(j, null);
								keepMerging = true;
							}
						}
					}
				}
			}
		}
		// Sort the results and remove nulls (must wait until all patients are processed to get the right names)
		vpds = sortPatientDescriptorSets(vpds);
		// Enforce the maximum patient count
		int maxCount = query.getMaxPatientCount();
 		if ((maxCount < 1) || (vpds.size() <= maxCount)) {
			// No limit
			return sortPatientDescriptorSets(vpds);
		} else {
			return sortPatientDescriptorSets(vpds.subList(0, maxCount));
		}
	}
	
	/**
	 * Sort a list of patient descriptor sets so that they are arranged
	 * alphabetially.  This arrangement is only approximate because a set
	 * may contain multiple entries for the same patient under different
	 * names.
	 * 
	 * @param patientSets The patient sets to be sorted
	 * @return The patient sets sorted alphabetically
	 */
	private List<PatientDescriptorSet> sortPatientDescriptorSets(List<PatientDescriptorSet> patientSets) {
		// If only one no sorting necessary
		if ((patientSets == null) || (patientSets.size() < 2))
			return patientSets;
		// Need to build a sorted list
		List<PatientDescriptorSet> result = new Vector<PatientDescriptorSet> ();
		// Sort each patient set into result based on name
		for (PatientDescriptorSet patientSet: patientSets) {
			if (patientSet != null) {
				String patientName = patientSet.getAlphabeticalNameString();
				if (patientName == null) {
					// Don't know name, just add to the end
					result.add(patientSet);
				} else {
					boolean found = false;
					int i = 0;
					while (i < result.size()) {
						// Find the right place to put this patient
						String listName = result.get(i).getAlphabeticalNameString();
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
					if (found && (i <= patientSet.size())) {
						result.add(i, patientSet);
					} else {
						result.add(patientSet);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Find all the patients from the given source that satisfy the query.
	 * Generate an informative log message if there is an error.
	 * 
	 * @param source The patient source
	 * @param query The patient query parameters
	 * @return A list of patients from the source that match the query
	 */
	private List<PatientDescriptor> findSourcePatients(IPatientSource source, PatientQuery query) {
		// The result to return
		List<PatientDescriptor> result = null;
		try {
			// Make the query and catch any errors
			result = source.findPatients(query);
		} catch (PatientException e) {
			// Log the error if there is one
			log.error("Cannot complete patient query to patient source " + source.getName(), e);
		}
		// If there was an error, return the empty list
		if (result == null) result = new Vector<PatientDescriptor>();
		// Done
		return result;
	}
	
	/**
	 * Create a new patient with all external patient information
	 * sources.  This method sends the patient information contained
	 * in the descriptor to those systems.
	 * <p>
	 * The reason for the patient creation is something like:
	 * "Outpatient Visit" or "Inpatient Admission". 
	 * 
	 * @param patient The description of the patient to be created
	 * @param reason The reason for the creation
	 * @throws PatientException When one of the registered consumers has trouble creating the patient
	 */
	public synchronized void createPatient(PatientDescriptor patient, IPatientConsumer.CreationReason reason) throws PatientException {
		//TODO Decide if this should cycle through all consumers that work before signalling an error
		for (IPatientConsumer consumer: consumers) {
			consumer.createPatient(patient, reason);
		}
	}

	/**
	 * Update the patient's demographics to match the new descriptor
	 * passed in.
	 * 
	 * @param patient The new demographics for the patient
	 * @throws PatientException When one of the registered consumers has trouble updating the patient
	 */
	public synchronized void updatePatient(PatientDescriptor patient) throws PatientException {
		//TODO Decide if this should cycle through all consumer that work before signalling an error
		for (IPatientConsumer consumer: consumers) {
			consumer.updatePatient(patient);
		}
	}
	
	/**
	 * Merge two patients together because they have been found to be
	 * the same patient.  The first argument describes the deomgraphics and
	 * IDs to use for the patient from now on.  The second argument described
	 * the IDs and demographics of the duplicate record for this patient.
	 * 
	 * @param patientMain The patient with the duplicate demographics entry
	 * @param patientOld The duplicate entry to be replaced
	 * @throws PatientException When one the registered consumers has trouble merging the patients
	 */
	public synchronized  void mergePatients(PatientDescriptor patientMain, PatientDescriptor patientOld) throws PatientException {
		//TODO Decide if this should cycle through all consumer that work before signalling an error
		for (IPatientConsumer consumer: consumers) {
			consumer.mergePatients(patientMain, patientOld);
		}		
	}
}
