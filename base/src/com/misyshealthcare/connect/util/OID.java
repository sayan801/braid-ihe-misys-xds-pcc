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
package com.misyshealthcare.connect.util;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * This class generates globally unique OIDs for use in
 * IHE documents.
 * 
 * @author Jim Firby, Wenzhi Li
 * @version 2.0 - Nov 15, 2005
 */
public class OID {

    private static boolean inited = false;
    private static String base = null;
    private static long time = 0;
    private final static Logger log = Logger.getLogger(OID.class);
    private static String oidSource = "" ;
    /**
     * Initialize the base and time.  This method is
     * only called once.
     */
    private static synchronized void init() {
        if (inited) return;
        base = LibraryConfig.getInstance().getOidRoot();
        time = (new Date()).getTime();
        inited = true;
    }

    public void registerIdSource() {

    }

    /**
     * Gets a new globally unique document ID which starts with an OID base(or root).
     *
     * @return the unique document UID.
     */
    public static String getDocumentUID() {
        return getBase() + "." + getUID(IDType.DOCUMENT);
    }

    /**
     * Gets a new globally unique SubmissionSet ID which starts with an OID base(or root).
     *
     * @return the unique submission set UID.
     */
    public static String getSubmissionSetUID() {
        return getBase() + "." + getUID(IDType.SUBMISSION_SET);
    }

    /**
     * Get the base OID used in all IDs.  This is taken from the ConfigManager.
     *
     * @return The base OID
     */
    public static String getBase() {
        if (!inited) init();
        return base;
    }

    /**
     * Gets a new globally unique document UID without the specified
     * base prefix.  The total lenght of the ID part must not exceed 15,
     * as required by patient ID in PIX/PDQ
     *
     * @param type The type of ID to be created.
     * @return the new globally unique OID, no base.
     */
    public static synchronized String getUID(IDType type) {
        if (!inited) init();
        String id = LibraryConfig.getInstance().getOidSource().generateId();
        //The total lenght of the ID part must not exceed 15, as required by patient ID in PIX/PDQ
        //reserve one for ".", and one for IDType
        if (id.length() > 13)
            log.warn("ID exceeds max length 13. Error might be caused.");
        return id + "." + type.getValue();
    }

    /**
     * The enumeration of ID types.  The type is not meant to provide semantic interpretation, but instead
     * to provide some visual information and easy management.
     */
    public enum IDType {
        //IMPORTANT: once the type is registred here, do not change the value.
        DOCUMENT       { String getValue(){ return "1";}}, //For document such as a CDA, DICOM
        SUBMISSION_SET { String getValue(){ return "2";}}, //For document submission set
        CDA_SET        { String getValue(){ return "3";}}, //For CDA set ID, used for revision purpose
        PARTICIPANT    { String getValue(){ return "4";}}, //For participants such as author, informant and performer etc.
        ORGANIZATION   { String getValue(){ return "5";}}, //For organizations such as insurance, software vendor etc.
        ENCOUNTER      { String getValue(){ return "6";}}, //For patient encounters/visits
        OBSERVATION    { String getValue(){ return "7";}}, //For the observation of Act components
        SUBSTANCE_ADMIN{ String getValue(){ return "8";}}, //For the medication substance administration.
        PROCEDURE      { String getValue(){ return "9";}}, //For procedure.
        ACT            { String getValue(){ return "10";}}, //For Act component.
        SERVICE_EVENT  { String getValue(){ return "11";}}, //For DocumentOf Service Event header.
        CONSCENT       { String getValue(){ return "12";}}, //For Authorization Consent header.
        SECTION        { String getValue(){ return "13";}}; //For CDA Section.

        abstract String getValue();
    }

    /*
      * Demonstraton and test
      */
    public static void main(String args[]) {

        for (int i=0; i< 5; i++) {
            String submUid = OID.getSubmissionSetUID();
            System.out.println("UID=" + submUid + ",  Length=" + submUid.length());

            String docUid = OID.getDocumentUID();
            System.out.println("UID=" + docUid + ",  Length=" + docUid.length());
        }
    }

    /**
     * The interface that provides the ID part of an OID.
     **/
    public interface OidSource {
        /**
         * Generate a unique id to be used as the id part of an OID. The max length of id is 13.
         *
         * @return the id whose length is less than or equal to 13 chars.
         */
        public String generateId();
    };
}
