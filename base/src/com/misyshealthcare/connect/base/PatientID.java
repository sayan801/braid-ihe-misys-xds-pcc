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

import java.util.*;

import org.apache.log4j.Logger;

/**
 * This class represents a Patient ID.  It is designed to hide
 * all of the translation that has to happen between Patient IDs
 * in different Assigning Authority domains.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 19, 2005
 */
public class PatientID {
	
	/* Log for problems encountered in this object */
	private static Logger log = Logger.getLogger(PatientID.class);

	/* Unique ID for this patient within local assigning authority */
	private String localId = null;

	/* Table holding a map of ID List<system, ID List> for this patient in other systems */
	private Hashtable<String, List<String>> idTable = null;

	/* Table holding the set of xref sources that we have looked at to find this patient */
	private List<IPatientXref> myXrefs = null;
	
	/* Default all requests to the LocalUniqueID if no xref sources are known */
	private static boolean defaultToLocalId = false;
	
	/**
	 * Create a new, empty PatientId object.
	 */
	public PatientID() {
		this(null, null, null);
	}
	
	/**
	 * Create a new PatientId object with the given local 
	 * unique patient ID.
	 * 
	 * @param localId The local unique patient ID
	 */
	public PatientID(String localId) {
		this(localId, null, null);
	}
	
	/**
	 * Create a new PatientId object representing a patient
	 * from a local system, for example the one that has been matched in the
	 * Misys Connect database.
	 * 
	 * @param localId The local unique patient ID
	 * @param homeSystem The ID of this patient's Misys home system
	 * @param homeId The patient ID in their Misys home system
	 */
	public PatientID(String localId, String homeSystem, String homeId) {
		// Save the local Unique ID
		this.localId = localId;
		// Save the homeSystem ID
		idTable = new Hashtable<String, List<String>>();
		if (homeSystem != null) {
            List homeIds = new ArrayList<String>();
            homeIds.add(homeId);
            idTable.put(homeSystem, homeIds);
		}
	}

	/* ------------------------------------------------------- */
	/* Methods for dealing with the local unique ID    */
	
	/**
	 * Check whether this Patient ID includes a translation to
	 * a local unique patient ID.
	 * <p>
	 * If the local unique patient ID is not known, check all Patient ID Xref Sources to
	 * see if one of them knows it.
	 * 
	 * @return True if this Patient has a local unique patient ID
	 */
	public boolean hasLocalUniqueId() {
		return hasLocalUniqueId(true);
	}
	
	/**
	 * Check whether this Patietn ID includes a translation to a local unique 
	 * patient ID.
	 * 
	 * @param allowCrossReference if cross reference is allowed to look up via PIX query.
	 * @return
	 */
	public boolean hasLocalUniqueId(boolean allowCrossReference) {
		if (localId != null) {
			// If we know it, say so
			return true;
		} else if (allowCrossReference) {
			// If we don't know it, see if we can look it up
			return xrefPatientId(true, null, null);
		} else {
			return false;
		}
	}
	
	/**
	 * Get the local unique ID for this patient, if it
	 * is known.  
	 * <p>
	 * If the local unique ID is not known, check all Patient ID Xref Sources to
	 * see if one of them knows it.
	 * <p>
	 * If nobody knows the local unique ID, this method will return null.
	 * 
	 * @return The local unique ID
	 */
	public String getLocalUniqueId() {
		return getLocalUniqueId(true);
	}
	
	/**
	 *  Get the local unique ID for this patient.
	 *  
	 * @param allowCrossReference if cross reference is allowed to look up via PIX query.
	 * @return the local uniqe id
	 */
	public String getLocalUniqueId(boolean allowCrossReference) {
		// If we don't know it, see if we can look it up
		if ((localId == null) && allowCrossReference) xrefPatientId(true, null, null);
		// Return it
		return localId;
	}
	
	/* --------------------------------------------------------- */
	/* Methods for saving and getting other IDs for this patient */
	
	/**
	 *  Default all requests to the LocalUniqueID if no xref sources are known 
     */
	public static void setDefaultToLocalId(boolean value) {
		defaultToLocalId = value;
	}

	/**
	 * Save the ID for this patient in the given home system or
	 * Assigning Authority domain. Each home system may have a list of ids, so
     * this method adds the new homeId to its Id list.
	 * 
	 * @param homeSystem The home system assigning this ID
	 * @param homeId The patient ID for this patient in the home system. If homeId is null,
     *  the method will remove the id list entry for the given homeSysetm.
	 */
	public synchronized void addId(String homeSystem, String homeId) {
		if (homeSystem != null) {
			if (homeId != null) {
                List<String> ids = idTable.get(homeSystem);
                if (null == ids) {
                    ids = new ArrayList<String>();
                    idTable.put(homeSystem, ids);
                }
                ids.add( homeId );
			} else {
				idTable.remove(homeSystem);
			}
		}
	}

    /**
     * Get a single ID for this patient in the given system or Assignig Authority domain. If
     * there are a list of IDs avaiable for the system, then we believe the last added id is the
     * most reliable id.
     *
     * @param homeSystem the home system or Assigning Authority
     * @return Return <code>Null</code> if there is no id avaiable. Otherwise return the last id in the id list
     *  of the home system.
     */
    public String getId(String homeSystem) {
        List<String> ids = getIdList(homeSystem);
        if (ids.size() == 0)
            return null;

        return ids.get(ids.size()-1);

    }
    /**
	 * Get the ID list for this patient in the given system or
	 * Assigning Authority domain, if it is known.  
	 * <p>
	 * If the ID is not known, check all Patient ID Xref Sources to
	 * see if one of them knows it.
	 * <p>
	 * If it is not known by any source, this method will return an empty list.
	 *
     * @param homeSystem the home system or Assigning Authority
	 * @return The ID list for the given system or Assigning Authority. Return an empty
     *        ID list if the homeSystem is invalid or it doesn't have an ID.
	 */
	public List<String> getIdList(String homeSystem) {
        return getIdList(homeSystem, null);
    }
	
	/**
	 * Check whether an ID is known for this patient in the given
	 * system or Assigning Authority domain.
	 * <p>
	 * If the ID is not known, check all Patient ID Xref Sources to
	 * see if one of them knows it.
	 * 
	 * @param homeSystem The ID of the home system
	 * @return True if there is an ID for this patient in the given home system
	 */
	public boolean hasId(String homeSystem) {
		if (idTable.containsKey(homeSystem)) {
			// If we know the ID in this domain, say so
			return true;
		} else {
			// If not, see if we can look it up
			return xrefPatientId(false, homeSystem, null);
		}
	}
	
	/**
	 * Check whether an ID is known for this patient in either of
	 * the two given systems or Assigning Authority domains.  This
	 * method is used by some of the IHE actors that have two different
	 * ways to refer to the same Assigning Authorities because it is
	 * a little more efficient than calling getId(domain) twice.
	 * <p>
	 * If the ID is not known, check all Patient ID Xref Sources to
	 * see if one of them knows it.
	 * 
     * @param domain1 The ID of the domain1
     * @param domain2 The ID of the domain2
	 * @return The list of patient IDs for domain1 or domain2; return an empty list if
     *      the both domains are invalid or they have no patient IDs.
	 */
	public List<String> getIdList(String domain1, String domain2) {
		return getIdList(domain1, domain2, true);
	}

	/**
	 * Check whether an ID is known for this patient in either of
	 * the two given systems or Assigning Authority domains.  This
	 * method is used by some of the IHE actors that have two different
	 * ways to refer to the same Assigning Authorities because it is
	 * a little more efficient than calling getId(domain) twice.
	 * <p>
	 * If the ID is not known, check all Patient ID Xref Sources to
	 * see if one of them knows it.
	 *
     * @param domain1 The ID of the domain1
     * @param domain2 The ID of the domain2
     * @param allowCrossReference whether to allow cross referece
	 * @return The list of patient IDs for domain1 or domain2; return an empty list if
     *      the both domains are invalid or they have no patient IDs.
	 */
     public List<String> getIdList(String domain1, String domain2, boolean allowCrossReference) {
		if ((domain1 == null) && (domain2 == null)) {
			// Didn't pass anything in
            log.warn("Domain is not specified");
            return new ArrayList<String>();
		} else if ((domain1 != null) && (idTable.containsKey(domain1))) {
			// We know it under the first name
			return idTable.get(domain1);
		} else if ((domain2 != null) && (idTable.containsKey(domain2))) {
			// We know it under the second name
			return idTable.get(domain2);
		} else if (allowCrossReference && (xrefPatientId(false, domain1, domain2))) {
			// We didn't know it, but we were able to look it up
			if ((domain1 != null) && (idTable.containsKey(domain1))) {
				return idTable.get(domain1);
			} else if ((domain2 != null) && (idTable.containsKey(domain2))){
				return idTable.get(domain2);
			} else {
				// This should never happen
                log.warn("Could not find the patient Id for the domain:" + domain1==null ? domain2 : domain1 );
				return new ArrayList<String>();
			}
		} else {
			// We can't find it anywhere
            log.warn("Could not find the patient Id for the domain:" + domain1==null ? domain2 : domain1 );
			return new ArrayList<String>();
		}
	}
	
	/**
	 * Get <i>some</i> ID for this patient, if any are known.
	 * This method is used to extract a default ID for some of the ATNA logging messages.
	 * It should NOT be used in general.
	 * 
	 * @return One of the IDs for this patient
	 */
	public String getId() {
		if (localId != null) return localId;
		if (idTable.isEmpty()) return null;
		Enumeration<List<String>> ids = idTable.elements();
        //Just return the next available id.
        while (ids.hasMoreElements()) {
            List<String> idList = ids.nextElement();
            return idList.get(idList.size()-1);
        }
        return null;
    }

    /* ------------------------------------------------------ */
	/* Methods for doing cross-referencing                    */
	
	/**
	 * Try all known Patient ID Xref Sources to see if any of them know the
	 * patient ID for this patient in one of the given domains.  This method
	 * tries to be efficient about never checking the same source twice.
	 * 
	 * @param wantLocalUniqueId True if the local unique ID is being sought
	 * @param domain1 A domain in which this ID is being sought 
	 * @param domain2 An alternate domain in which this ID is being sought 
	 * @return True if the ID was found in one of the desired domains
	 */
	private synchronized boolean xrefPatientId(boolean wantLocalUniqueId, String domain1, String domain2) {
		// Get a list of patient ID xref sources
		// TODO fix this in a better way.
		IPatientXrefFactory factory = IPatientXrefFactory.getInstance();
		List<IPatientXref> xrefs = factory.getPatientXrefs();
		// Check them all to see what we can find
		if (xrefs.isEmpty() || ((myXrefs != null)  && (myXrefs.size() == xrefs.size()))) {
			// We've already looked everywhere and we don't know it
			if (xrefs.isEmpty() && defaultToLocalId && (localId != null)) {
				// We are supposed to default all domains to the local ID (used in the Connectathon)
				if (domain1 != null) addId(domain1, localId);
				if (domain2 != null) addId(domain2, localId);
				return true;
			} else {
				return false;
			}
		} else {
			// Check the xref sources until we find it
			boolean found = false;
			if (myXrefs == null) myXrefs = new Vector<IPatientXref>();
			for (IPatientXref xref: xrefs) {
				if (!myXrefs.contains(xref)) {
					// Haven't checked this one yet
					try {
						// Do the xref itself
						String uniqueId = xref.getPatientIds(this);
						// Record that we've talked to this source
						myXrefs.add(xref);
						// Now, see if this source knew the patient's local unique ID
						if ((uniqueId != null) && !uniqueId.equals("")) {
							// It did know the local unique ID
							if (localId == null) {
								// We didn't know it already
								// Save it away
								localId = uniqueId;
							} else if (!match(localId, uniqueId)) {
								// We already knew it but this is different
								// Assume that a patient merge has occured but log a warning
								log.warn("Patient ID xref source '" + xref.getName() + "' believes local patient '" + localId + "' now has local ID '" + uniqueId + "'");
								localId = uniqueId;
							}
							// If all we want is the local unique ID,  we're done
							if (wantLocalUniqueId) found = true;
						}
						// If this xref source gave us what we want, we're done
						if ((domain1 != null) && idTable.containsKey(domain1)) found = true;
						if ((domain2 != null) && idTable.containsKey(domain2)) found = true;
					} catch (PatientException e) {
						// Unable to use this source, just log an error
						log.error("Unable to use patient ID xref source '" + xref.getName() + "'", e);
					}
				}
				// If we found what we want, stop looking
				if (found) break;
			}
			// Either we found what we want, or we've checked everything
			return found;
		}
	}


    /* ------------------------------------------------------ */
	/* Methods for comparing and merging Patient ID objects   */

	/**
	 * Return true if two PatientId objects refer to the same patient
	 */
	public boolean equals(Object other) {
		if (other instanceof PatientID) {
			PatientID pid = (PatientID) other;
			if (this == pid) {
				// They are the same object
				return true;
			} else if (match(localId, pid.getLocalUniqueId())) {
				// If they have the same local unique ID they are the same
				return true;
			} else if ((localId != null) && (pid.getLocalUniqueId() != null)) {
				// They can't be equal
				return false;
			} else {
				// If they have any overlapping ID they are the same
				// First check if the other has any of our keys
				Enumeration<String> keys = idTable.keys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					List<String> thisIds = getIdList(key);
					List<String> otherIds = pid.getIdList(key);
                    log.info("Local Unique ID: " + localId + "\nKey: " + key + "\nThis IDs: " + listToString(thisIds) + "\nOther IDs: " + listToString(otherIds));
					//if we found any intersection, we think they are equal.
                    if (intersect(thisIds, otherIds)) return true;
				}
				// Second check if we have any of the other's keys (xref might make this happen)
				keys = pid.idTable.keys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					List<String> thisIds = getIdList(key);
					List<String> otherIds = pid.getIdList(key);
					log.info("Local Unique ID: " + localId + "\nKey: " + key + "\nThis IDs: " + listToString(thisIds) + "\nOther IDs: " + listToString(otherIds));
                    //if we found any intersection, we think they are  equal.
                    if (intersect(thisIds, otherIds)) return true;
 				}
			}
		}
		// If we get here, they have no overlapping IDs
		return false;
	}

    /**
     * Tests whether two id lists have the common id intersection.
     *
     * @param ids1 the first id list
     * @param ids2 the second id list
     * @return <code>true</code> if the two id lists have intersection; <code>false</code> otherwise
     */
    private boolean intersect(List<String> ids1, List<String> ids2) {
        for (String id1 : ids1) {
            if (ids2.contains(id1))
                return true;
        }
        return false;
    }

    /**Formats the ID list to a comma separated string
     *
     * @param ids the ID list to be formated
     * @return  The String of all the IDs delimited by commas
     */
    public String listToString(List<String> ids) {
        String ret = "";
        if (null != ids) {
            for (int i=0; i<ids.size(); i++) {
                if (i != 0)
                    ret += ", ";

                ret += ids.get(i);
            }
        }
        return ret;
    }

    /**
	 * Merge another PatientID with this one.  Return a PatientID that
	 * includes all of the known IDs from both objects.  Neither PatientID
	 * will be altered but the PatientID returned might not be a copy.
	 * 
	 * @param other The PatientID to merge with this one
	 * @return A PatientID that holds all known IDs from this and the other object.  The PatientID object returned might be this one.
	 * @throws PatientException When there is a conflict and the merge cannot take place
	 */
	public PatientID merge(PatientID other) throws PatientException {
		if (this == other) {
			// No need to merge
			return this;
		} else if (match(localId, other.getLocalUniqueId()) && idTable.isEmpty() && other.idTable.isEmpty()) {
			// No need to copy
			return this;
		} else {
			log.info("Local unique ID: " + localId + "\nOther ID: " + other.getLocalUniqueId());
			// Get the local unique ID and create a new PatientID
			String mid = mergeIds(localId, other.getLocalUniqueId());
			PatientID mergeId = new PatientID(mid);
			// Add all of the known IDs from this ID
			Enumeration<String> keys = idTable.keys();
			if (keys != null) {
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					List<String> ids1 = idTable.get(key);
					List<String> ids2 = other.idTable.get(key);
                    log.info("Local unique ID: " + localId + "\nKey: " + key + "\nThis IDs: " + listToString(ids1) + "\nOther IDs: " + listToString(ids2));
                    //First add all the ids in the ids1
                    for (String id1 : ids1) {
                        mergeId.addId(key, id1);
                    }
                    //ignore ids2 if there is no key in the other table
                    if (ids2 == null)
                        continue;
                    //Then add the new ids in the ids2
                    for(String id2 : ids2) {
                        if (!ids1.contains(id2))
                            mergeId.addId(key, id2);
                    }
				}
			}
			// Add all of the known IDs for the unique keys from the other table
			keys = other.idTable.keys();
			if (keys != null) {
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();

                    //filter out the key which exists in this table; its ids have already been added from above.
                    if (idTable.containsKey(key))
                        continue;

                    List<String> ids2 = other.idTable.get(key);
                    log.info("Local unique ID: " + localId + "\nKey: " + key + "\nOther IDs: " + listToString(ids2));
                    for (String id2 : ids2) {
                        mergeId.addId(key, id2);
                    }
				}
			}
			// Done
			return mergeId;
		}
	}
	
	/**
	 * Return the best merged value for two IDs.
	 * @param id1 One ID to merge
	 * @param id2 The other ID to merge
	 * @return The best merged ID
	 * @throws PatientException When the IDs conflict (ie. have non-equivalent values)
	 */
	private static String mergeIds(String id1, String id2) throws PatientException {
		if (match(id1, id2)) {
			return id1;
		} else if (id1 == null) {
			return id2;
		} else if (id2 == null) {
			return id1;
			//TODO: HACK!!!
		} else{
			throw new PatientException("Cannot merge PatientIDs with conflicting IDs");
		}
	}
	
	/**
	 * Check whether two IDs are equivalent.  To be equivalent
	 * they must be identical, non-null strings.
	 * 
	 * @param id1 One of the IDs
	 * @param id2 The other ID
	 * @return True if they are equivalent
	 */
	private static boolean match(String id1, String id2) {
    //TODO Check if Ids are case sensitive or not
		if ((id1 == null) || (id2 == null)) {
			return false;
		} else {
			return id1.equals(id2);
		}
	}
	
	public Set<String> getHomeSystems(){
		return idTable.keySet();
	}
	
}
