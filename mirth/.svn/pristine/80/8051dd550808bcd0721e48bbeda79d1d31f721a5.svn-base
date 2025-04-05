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

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.net.StringMap;
import com.misyshealthcare.connect.net.ObjectEntry;

/**
 * The class represents a Code System which corresponds to the code element in a CDA document.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 20, 2005
 */
public class CodeSystem {

    private static final Logger log = Logger.getLogger(CodeSystem.class);

    private String code = null;
    private String codeSystem = null;
    private String codeSystemName = null;
    private String displayName = null;

    /**The mapping between codeSystemName and codeSytem*/
//    private static Map<String, String> mapCode = new HashMap();
//    static {
//        mapCode.put("IHE PCC Template Identifiers","1.3.6.1.4.1.19376.1.5.3.1");
//        mapCode.put("IHEActCode",                  "1.3.6.1.4.1.19376.1.5.3.1");
//        mapCode.put("AdministrativeGender",        "2.16.840.1.113883.5.1");
//        mapCode.put("PersonalRelationshipRoleType","2.16.840.1.113883.5.111" );
//        mapCode.put("RouteOfAdministration",       "2.16.840.1.113883.5.112");  //See the HL7 RouteOfAdministration Vocabulary
//        mapCode.put("SeverityObservation",         "2.16.840.1.113883.5.1063"); //See the HL7 SeverityObservation Vocabulary
//        mapCode.put("PolicyHolderRole",            "2.16.840.1.113883.5.1095");
//        mapCode.put("LOINC",     "2.16.840.1.113883.6.1");
//        mapCode.put("ICD9",      "2.16.840.1.113883.6.2");
//        mapCode.put("ICD-9",     "2.16.840.1.113883.6.2");
//        mapCode.put("ICD-9CM",   "2.16.840.1.113883.6.2");
//        mapCode.put("MEDCIN",    "2.16.840.1.113883.6.26");  //A classification system from MEDICOMP Systems.
//        mapCode.put("SNOMED",    "2.16.840.1.113883.6.96");
//        mapCode.put("SNOMED-CT", "2.16.840.1.113883.6.96");
//        mapCode.put("RxNorm",    "2.16.840.1.113883.6.88");
//        mapCode.put("FDDC",      "2.16.840.1.113883.6.63"); //First DataBank Drug Codes
//        mapCode.put("C42",       "2.16.840.1.113883.6.12"); //Current Procedure Terminology 4 (CPT-4) codes.
//    }

    /**
     * Constructor
     *
     * @param code
     * @param codeSystem
     * @param codeSystemName
     * @param displayName
     */
    public CodeSystem(String code, String codeSystem, String codeSystemName, String displayName) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.codeSystemName = codeSystemName;
        this.displayName = displayName;
        if (this.codeSystem == null && this.codeSystemName != null )
            this.codeSystem = getCodeSystem(this.codeSystemName);

    }

    /**
     * Gets the code system from the code system name.
     *
     * @param codeSystemName
     * @return the code system
     */
    public static String getCodeSystem(String codeSystemName) {
        StringMap codeSystemMap = CDABuilder.connCDA.getStringMap("CodeSystems");
        if (null==codeSystemMap)
            throw new NullPointerException("Missing CodeSystems mapping in CDA Configuration Description.");

        String codeSystem = codeSystemMap.getCodeValue(codeSystemName);
        if (codeSystem == null) {
            log.error("Cannot find the code system for " + codeSystemName);
        }
        return codeSystem;
    }

    public static CodeSystem convertFrom(ObjectEntry.CodeSystem cs) {
        return new CodeSystem(cs.getCode(), cs.getCodeSystem(), cs.getCodeSystemName(), cs.getDisplayName());
    }

    /**
     * Gets the code value
     *
     * @return the code value
     * @see #getDisplayName
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the code system.
     *
     * @return the code system
     * @see #getCodeSystemName()
     */
    public String getCodeSystem() {
        return this.codeSystem;
    }

    /**
     * Gets the code system name.
     *
     * @return the code system name
     * @see #getCodeSystem()
     */
    public String getCodeSystemName() {
        return this.codeSystemName;
    }

    /**
     * Gets the display name of the code value.
     *
     * @return the display name of the code
     * @see #getCode()
     */
    public String getDisplayName() {
        return this.displayName;
    }
}
