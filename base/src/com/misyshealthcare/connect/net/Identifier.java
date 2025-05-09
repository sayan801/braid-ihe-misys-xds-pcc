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
package com.misyshealthcare.connect.net;

import com.misyshealthcare.connect.base.PatientID;

import java.util.List;

/**
 * This class represents an ID assigning authority in an IHE affinity domain.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 20, 2005
 */
public class Identifier {

    private String namespaceId = null;
    private String universalId = null;
    private String universalIdType = null;

    private String universalKey = null;

    /**
     * Create a new assigning authority identifier.  An assigning authority
     * may have a symbolic namespace name, or a universal ID and a universal ID type,
     * or both.
     *
     * @param namespace The symbokic name for the assigning authority
     * @param universal The universal ID for the assigning authority
     * @param universalType The universal ID type
     */
    public Identifier(String namespace, String universal, String universalType) {
        if (namespace != null) namespaceId = namespace.trim();
        if (universal != null) universalId = universal.trim();
        if (universalType != null) universalIdType = universalType.trim();
        if (universalId != null) {
            universalKey = universalId;
            if (universalIdType != null)
                universalKey = universalKey + "&" + universalIdType;
        }
    }

    /**
     * Get the symbol namespace ID.
     *
     * @return The symbolic namespace ID
     */
    public String getNamespaceId() {
        return namespaceId;
    }

    /**
     * Set the symbol namespace ID.
     *
     * @param namespace The symbolic namespace ID
     */
    public void setNamespaceId(String namespace) {
        if (namespace != null) 
        	this.namespaceId = namespace.trim();
        else
        	this.namespaceId = null;
    }

    /**
     * Get the universal ID for this assigning authority.
     *
     * @return The universal ID
     */
    public String getUniversalId() {
        return universalId;
    }

    /**
     * Set the universal ID for this assigning authority.
     *
     * @param universal The universal ID
     */
    public void setUniversalId(String universal) {
        if (universal != null) 
        	universalId = universal.trim();
        else
        	universalId = null;
        
        if (universalId != null) {
            universalKey = universalId;
            if (universalIdType != null)
                universalKey = universalKey + "&" + universalIdType;
        }
    }

    /**
     * Get the type of the universal ID for this assigning authority.
     *
     * @return The universal ID type
     */
    public String getUniversalIdType() {
        return universalIdType;
    }

    /**
     * Set the type of the universal ID for this assigning authority.
     *
     * @param univeralIdType The universal ID type
     */
    public void setUniversalIdType(String universalIdType) {
        if (universalIdType != null) 
        	this.universalIdType = universalIdType.trim();
        else
        	this.universalIdType = null;
        
        if (universalId != null) {
            universalKey = universalId;
            if (universalIdType != null)
                universalKey = universalKey + "&" + universalIdType;
        }
    }

    /**
     * Get the value of the Patient ID in this assigning authority.
     *
     * @param patientId The patient ID object holding all possible IDs
     * @return The patient ID for this assigning authority
     */
    public String getPatientId(PatientID patientId) {
        return getPatientId(patientId, true);
    }

    public String getPatientId(PatientID patientId, boolean allowCrossReference) {
        // Look up the ID using the namespace
        if ((namespaceId != null) || (universalKey != null)) {
            List<String> pids = patientId.getIdList(namespaceId, universalKey, allowCrossReference);
            if (pids.isEmpty())
                return null;
            else //grab the most recent id
                return pids.get(pids.size()-1);
        }
        // Can't find it
        return null;
    }

    /**
     * Add a new patient ID associated with this assigning authority
     * to a patient ID object.
     *
     * @param patientId The patient ID object
     * @param newId The new ID associated with this assigning authority
     */
    public void addPatientId(PatientID patientId, String newId) {
        // Save the ID using the namespace
        if (namespaceId != null) {
            patientId.addId(namespaceId, newId);
        }
        // Save the id using the universalId and type
        if (universalKey != null) {
            patientId.addId(universalKey, newId);
        }
    }

    /**
     * Get a name for this assigning authority.  Used in debugging
     * and log messages.
     *
     * @return A human-readable name for this assigning authority
     */
    public String getAuthorityNameString() {
        StringBuffer sb = new StringBuffer();
        if (namespaceId != null) sb.append(namespaceId);
        if (universalId != null) {
            sb.append('&');
        	sb.append(universalId);
            if (universalIdType != null) {
                sb.append('&');
            	sb.append(universalIdType);
            }
        }
        return sb.toString();
    }

    /**
     * See if two identifiers represent the same assigning authority.
     */
    public boolean equals(Object other) {
        if (other instanceof Identifier) {
            Identifier id = (Identifier) other;
            if ((id.getNamespaceId() != null) && (namespaceId != null)) {
                return namespaceId.equalsIgnoreCase(id.getNamespaceId());
            }
            if ((id.getUniversalId() != null) && (id.getUniversalIdType() != null)) {
                if ((universalId != null) && (universalIdType != null)) {
                    return (universalId.equalsIgnoreCase(id.getUniversalId()) &&
                            universalIdType.equalsIgnoreCase(id.getUniversalIdType()));
                }
            }
        }
        return false;
    }

    /**
     * Calcualte the hashCode of this Identifier object
     *
     * @return The hashCode
     */
    public int hashCode() {
        int result = 17;
        if (namespaceId != null)
            result = 37*result +  namespaceId.hashCode();
        if (universalId != null)
            result = 37*result +  universalId.hashCode();
        if (universalIdType != null)
            result = 37*result + universalIdType.hashCode();

        return result;
    }

}
