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

package com.misyshealthcare.connect.ihe.audit;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.PatientQuery;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ParticipantObjectIdTypeCode;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ParticipantObjectRoleCode;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings.ParticipantObjectTypeCode;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.registry.HL7;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.util.StringUtil;

/** Temporary way of getting participant info to the Audit Trail.
 * 
 * This should be replaced by the LogContext, or we could just
 * make a constructor that takes the log context...?
 * @author Josh Flachsbart
 * @version 2.0 - Dec 30, 2005
 */
public class ParticipantObject {
	private String Name;
	protected ParticipantObjectIdTypeCode idTypeCode = ParticipantObjectIdTypeCode.Patient;

	private String Id;
	private PatientID pId;
	private PatientDescriptor patient;
	protected ParticipantObjectTypeCode typeCode;
	protected ParticipantObjectRoleCode role;
	
	public String query; // B64 encoded query.
	protected String documentDesc;  // should be a coded value, but we have no code...
	public String sensitivity;

	public ParticipantObject() {
	}

	/** Constructor for building patient particpant objects.
	 * 
	 * This adds a little info in about the document accessed as well.
	 * 
	 * @param document The descriptor of the document.
	 */
	public ParticipantObject(Document document) {
		this(document.getPatientDescriptor());
		documentDesc = document.getUniqueId();
	}
	
	/** Constructor for building patient particpant objects.
	 * 
	 * @param patient The descriptor of the patient.
	 */
	public ParticipantObject(PatientDescriptor patient) {
		Name = null;
		this.patient = patient;
		
		setId(patient.getPatientId());
		
		typeCode = ParticipantObjectTypeCode.Person;
		role = ParticipantObjectRoleCode.Patient;		
	}
	
	public ParticipantObject(PatientQuery query) {
		if ( StringUtil.goodString(query.getNameFirst()) || StringUtil.goodString( query.getNameLast() ) ||
		     StringUtil.goodString(query.getNameMiddle()) || StringUtil.goodString(query.getNameSuffix()) || 
		     StringUtil.goodString(query.getNameTitle())) {
			patient = new PatientDescriptor();
			patient.setNameFirst( query.getNameFirst() );
			patient.setNameLast( query.getNameLast() );
			patient.setNameMiddle( query.getNameMiddle() );
			patient.setNameSuffix( query.getNameSuffix() );
			patient.setNameTitle( query.getNameTitle() );
	    }
		if (StringUtil.goodString(query.getMisysPatientId())) {
			pId = new PatientID(query.getMisysPatientId());
		} else if (StringUtil.goodString(query.getSystemPatientId()) &&
				   StringUtil.goodString(query.getSystemId())){
			pId = new PatientID();
			pId.addId(query.getSystemId(), query.getSystemPatientId());
		}
	}

	public ParticipantObject(String Name, String Id) {
		this.Name = Name;
		setId(Id);
	}
	
	public ParticipantObject(String Name, PatientID Id) {
		this.Name = Name;
		setId(Id);
	}
	
	/** Sets the id for this PO to be an id that requires an 
	 * asigning authority.  Disables the normal id.
	 * 
	 * @param inId The patient ID class to use to lookup the id.
	 */
	
	public void setId(PatientID inId) {
		Id = null;
		pId = inId;
	}

	/** Sets the id for this PO to be a standard string.
	 * Disables the id lookup class if one is present.
	 * 
	 * @param inId
	 */
	public void setId(String inId) {
		pId = null;
		Id = inId;
	}
	
	/** Looks for a standard string id, then looks us the id through the patient description.
	 * 
	 * Note the the asigning authority is ignored if the standard string 
	 * id is set for this PO.  Otherwise it uses the given class to do a 
	 * lookup.  If null it uses the Local authority.  Local authority is 
	 * only needed when encoding as HL7 and using no assigning authority.
	 * 
	 * @param assigningAuthority Authority to use to look up the id.  
	 * @param localAuthority Authority to use for local id HL7 encoding.
	 * @param encoding The encoding to get the ID in, HL7 or null for no encoding.
	 * @return The retrieved ID, or "No ID Available"
	 */
	public String getId(Identifier assigningAuthority, Identifier localAuthority, String encoding)
	{
		// If we have a default ID, just return it.
		String foundId = Id;
		Identifier authority = null;
		if (foundId == null) {
			// If an AA is given, use it to look up the id. 
			if (assigningAuthority != null && pId != null) {
				foundId = assigningAuthority.getPatientId(pId);
				authority = assigningAuthority;
			}
			// If there is still no id, use local id if there is one.
			if (foundId == null && pId != null && pId.hasLocalUniqueId()) {
				foundId = pId.getLocalUniqueId();
				authority = localAuthority;
			}
			// We don't have an id at all, pick one at random
			if (foundId == null && pId != null) foundId = pId.getId();

			// Apply the encoding here
			if (foundId != null && encoding != null) {
				if (encoding.equalsIgnoreCase("HL7") && (authority != null)) {
					foundId = HL7.toCX(foundId, authority);
				}
				// other encodings here...
			}

			// If there is still no id, there is none.
			if (foundId == null) Id = "No ID Available";
		}
		return foundId;
	}
	
	public String getName(String encoding) {
		// Look for default name first.
		String foundName = Name;
		if (foundName == null && encoding != null) {
			// Get name from patient in specific encoding, if available.
			if (encoding.equalsIgnoreCase("HL7")) {
				foundName = HL7.toXPN(patient);
			}
		}
		// Get generic encoding name from patient.
		if (foundName == null && patient != null) {
			foundName = patient.getAlphabeticalNameString();
		}
		// No name.
		if (foundName == null) foundName = "No name available";
		
		return foundName;
	}
}
