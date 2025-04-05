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
package com.misyshealthcare.connect.ihe.registry;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.DateQuery;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.base.SharedEnums;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.StringUtil;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.List;
import java.util.ArrayList;

/**
 * This class creates the ebXML/ebRIM SQL query strings need to
 * get information the Xds Registry.
 * 
 * @author Jim Firby
 * @version 2.0 - Dec 15, 2005
 */
public class QueryBuilder {

  private final static Logger log = Logger.getLogger(QueryBuilder.class);

  /**
     * Create an Xds Registry SQL query to get document metadata about
     * documents that match the DocumentQuery.
     *
     * @param query The query describing the desired documents
     * @param patientId The patientId the desired documents are about
     * @param connection The connection to the registry
     * @return The SQL query as a string
     * @throws DocumentException When the query cannot be encoded into SQL
     * @see com.misyshealthcare.connect.base.DocumentQuery
     */
    public static String createDocumentQueryString(DocumentQuery query, String patientId, IConnectionDescription connection) throws DocumentException {
        //TODO - Is there a way to include the maximum number to return into these queries?
        // Check the patient ID for this repository query
        if (patientId == null) {
            throw new DocumentException("Document query contains no patient ID");
        }

        //GetDocuments Query: Get Document by Id
        String documentId = query.getDocumentId();
        if (StringUtil.goodString(documentId)) {
            //check if this is a getRelatedDocument query
            SharedEnums.DocumentRelationship docRelationship = query.getDocumentRelationship();
            if (docRelationship != null) {
                log.debug("GetRelatedDocument query: document ID='" + documentId + ", relationship=" + docRelationship.getValue());
                return createGetRelatedDocumentQueryString(documentId, docRelationship);
            } else {
                log.debug("GetDocuments on unique Id query: document ID='" + documentId);
                return createDocumentIdQueryString(documentId);
            }
        }
        //GetDocuments by entryUUID is not implemented

        //FindDocuments Query: Find Documents by patient Id, and dates
        // Log the query
        log.debug("FindDocuments query: patient ID='" + patientId + "' from " + query.getStartTime() + " to " + query.getEndTime());
        // Get the start and end dates
        String dateStart = formatQueryDate(query.getStartTime(), false);
        String dateEnd = formatQueryDate(query.getEndTime(), true);
        // Create the query
        if ((dateStart == null) && (dateEnd == null)) {
            return createFindAllDocumentsByIdQuery(patientId);
        } else {
            return createFindAllDocumentsByIdAndDateQuery(patientId, dateStart, dateEnd);
        }
    }

    /**
     * Format a DateQuery object into an appropriate HL7 date string to
     * compare against values in the registry.  All dates are treated
     * like numbers so make sure that there is exactly 8 digits and that
     * end dates are rounded up to the next day.
     *
     * @param date The date query to encode
     * @param isEnd True if this is an end date and should be rounded up
     * @return The encoded date string
     * @see com.misyshealthcare.connect.base.DateQuery
     */
    private static String formatQueryDate(DateQuery date, boolean isEnd) {
        if (date == null) return null;
        // Get out the values
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        // Adjust values to capture partial end dates correctly (ie. just the year or year and month)
        if (year <= 1000) return null;
        if (month <= 0) {
            if (isEnd) year = year + 1;
            day = 0;
        } else if (day <= 0) {
            if (isEnd) month = month + 1;
            if (month > 12) {
                year = year + 1;
                month = 0;
            }
        }
        // Roll day up one to capture end dates correctly (if a day is included in the query)
        if ((isEnd) && (day > 0)) {
            day = day + 1;
            if ((month == 2) && (day > 28)) {
                month = 3;
                day = 0;
            } else if ((day > 30) && ((month == 9) || (month == 4) || (month == 6) || (month == 11))) {
                month = month + 1;
                day = 0;
            }
        }
        // Format the values
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        if (month <= 0) {
            sb.append("0000");
        } else {
            if (month < 10) sb.append("0");
            sb.append(month);
            if (day <= 0) {
                sb.append("00");
            } else {
                if (day < 10) sb.append("0");
                sb.append(day);
            }
        }
        // Done
        return sb.toString();
    }

    /**
     * Create a SQL query to find all documents in the repository for a particular
     * patient.  This query has no restrictions on it.
     *
     * @param patientId The patient ID including the repository assigning authority
     * @return The SQL query
     */
    private static String createFindAllDocumentsByIdQuery(String patientId) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
      sb.append("SELECT eo.id FROM ExtrinsicObject eo, ExternalIdentifier ei\n");
      //  sb.append("SELECT * FROM ExtrinsicObject eo, ExternalIdentifier ei\n");
        // Get only XdsDocumentEntry extrinsic objects
      sb.append("WHERE eo.objectType='");
        sb.append(XdsDocumentEntry.getXdsDocumentEntryObjectType());
        sb.append("' AND\n");
      // The document patient ID is for the desired patient
      sb.append(" eo.id = ei.registryobject AND\n");
      sb.append(" ei.identificationScheme='");
        sb.append(XdsDocumentEntry.getPatientIdIdentificationScheme());
        sb.append("' AND\n");
      sb.append(" ei.value='");
        sb.append(patientId);
        sb.append("' AND\n");
      // The document is approved, ie. currently valid
      sb.append(" eo.status = 'Approved'\n");
      return sb.toString();
    }

    public static void addFindAllDocumentsStoredQuery(SOAPElement parent, DocumentQuery query, String patientId) throws SOAPException {
        //GetDocuments Query: Get Document by Id
        String documentId = query.getDocumentId();
        if (StringUtil.goodString(documentId)) {
            SharedEnums.DocumentRelationship docRelationship = query.getDocumentRelationship();
            if (docRelationship != null) {
                log.debug("GetRelatedDocument query: document ID='" + documentId + ", relationship=" + docRelationship.getValue());
                RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryUniqueId",  "('" + documentId + "')", XmlRegistry.XDS_RIM_V3_NAMESPACE);
                RimSoap.addRimSlotElement(parent, "$AssociationTypes",  "('" + docRelationship.getValue() + "')", XmlRegistry.XDS_RIM_V3_NAMESPACE);
                return;
            } else {
                log.debug("GetDocuments on unique Id query: document ID='" + documentId);
                RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryUniqueId",  "('" + documentId + "')", XmlRegistry.XDS_RIM_V3_NAMESPACE);
                return;
            }
        }
        String uuid = query.getEntryUUID();
        if(StringUtil.goodString(uuid)) {
            log.debug("GetDocuments on entryUUID query: UUID=" + uuid);
            RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryEntryUUID",  "('" + uuid + "')", XmlRegistry.XDS_RIM_V3_NAMESPACE);
            return;
        }

        //FindDocuments Query: Find Documents by patient Id, and dates etc
        if (StringUtil.goodString(patientId)) {
            RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryPatientId",  "'" + patientId + "'", XmlRegistry.XDS_RIM_V3_NAMESPACE);
            RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryStatus",  "('urn:oasis:names:tc:ebxml-regrep:StatusType:Approved')", XmlRegistry.XDS_RIM_V3_NAMESPACE);
            String message = "FindDocuments query: patient ID='" + patientId + "'";
            String dateStart = formatQueryDate(query.getStartTime(), false);
            if (dateStart != null) {
                RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryCreationTimeFrom",  dateStart, XmlRegistry.XDS_RIM_V3_NAMESPACE);
                message += " from " + query.getStartTime();
            }
            String dateEnd = formatQueryDate(query.getEndTime(), true);
            if (dateEnd != null) {
                RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryCreationTimeTo",  dateEnd, XmlRegistry.XDS_RIM_V3_NAMESPACE);
                message += " to " + query.getEndTime();
            }
            log.debug( message );
        }
    }

    public static void addConfidentialityCodes(SOAPElement parent, DocumentQuery query, IConnectionDescription connection) throws SOAPException, IheConfigurationException  {
        if (query.getConfidentialityCodes().size() == 0) return;

        List<SharedEnums.ConfidentialityCode> confidCodes = query.getConfidentialityCodes();
        List<String> policyCodes = new ArrayList<String>();
        for (SharedEnums.ConfidentialityCode code : confidCodes) {
            String value = Configuration.applyEnumMap(connection, code, SharedEnums.ConfidentialityCode.class, true);
            policyCodes.add( value );
        }

        if (policyCodes.size() > 0) {
            String confidentialities = StringUtil.toString( policyCodes, ",", "'" );
            RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryConfidentialityCode", "(" + confidentialities + ")", XmlRegistry.XDS_RIM_V3_NAMESPACE);
            log.debug( "  ConfidentialityCode="  + StringUtil.toString(policyCodes, ", ") );
        }
    }

    public static void addFormatCodes(SOAPElement parent, DocumentQuery query, IConnectionDescription connection) throws SOAPException, IheConfigurationException {
        if (query.getFormatCodes().size() == 0) return;

        List<SharedEnums.XdsFormatCode> formatCodes = query.getFormatCodes();
        List<String> sFormatCodes = new ArrayList<String>();
        for (SharedEnums.XdsFormatCode code : formatCodes) {
            String value = Configuration.applyEnumMap(connection, code, SharedEnums.XdsFormatCode.class, true);
            sFormatCodes.add( value );
        }
        if (sFormatCodes.size() > 0) {
            String formatcodes = StringUtil.toString(sFormatCodes, ",", "'");
            RimSoap.addRimSlotElement(parent, "$XDSDocumentEntryFormatCode", "(" + formatcodes + ")", XmlRegistry.XDS_RIM_V3_NAMESPACE);
            log.debug( "  FormatCode="  + StringUtil.toString(sFormatCodes, ", ") );
        }



    }

    /**
     * Create a SQL query to find all documents in the repository for a particular
     * patient.  This query can be restricted to a time window.
     *
     * @param patientId The patient ID including the repository assigning authority
     * @param dateStart The creation date of the earliest document of interest
     * @param dateEnd The creation date of the latest document of interest
     * @return The SQL query
     */
    private static String createFindAllDocumentsByIdAndDateQuery(String patientId, String dateStart, String dateEnd) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
      sb.append("SELECT eo.id FROM ExtrinsicObject eo, ExternalIdentifier ei, Slot ed\n");
       // sb.append("SELECT * FROM ExtrinsicObject eo, ExternalIdentifier ei, Slot ed\n");
      // Get only XdsDocumentEntry extrinsic objects
      sb.append("WHERE eo.objectType='");
          sb.append(XdsDocumentEntry.getXdsDocumentEntryObjectType());
          sb.append("' AND\n");
      // The document patient ID is for the desired patient
      sb.append(" eo.id = ei.registryobject AND\n");
      sb.append(" ei.identificationScheme='");
        sb.append(XdsDocumentEntry.getPatientIdIdentificationScheme());
        sb.append("' AND\n");
      sb.append(" ei.value='");
        sb.append(patientId);
        sb.append("' AND\n");
      // The document date falls into the right range
      sb.append(" eo.id = ed.parent AND\n");
      sb.append(" ed.name='creationTime' AND\n");
      if (dateStart != null) {
          sb.append(" ed.value >= '");
          sb.append(dateStart);
          sb.append("' AND\n");
      }
      if (dateEnd != null) {
          sb.append(" ed.value <= '");
          sb.append(dateEnd);
          sb.append("' AND\n");
      }
      // The document is approved, ie. currently valid
      sb.append(" eo.status = 'Approved'\n");
      return sb.toString();
    }

    /**
     * Create an Xds Registry SQL query to get the internal registry
     * UUID for the document with the given globally unique document ID.
     *
     * @param documentId The document unique ID
     * @return The registry UUID of the document with that ID
     */
    private static String createDocumentIdQueryString(String documentId) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
      sb.append("SELECT eo.id FROM ExtrinsicObject eo, ExternalIdentifier ei\n");
      // Get only XdsDocumentEntry extrinsic objects
      sb.append("WHERE eo.objectType='");
          sb.append(XdsDocumentEntry.getXdsDocumentEntryObjectType());
          sb.append("' AND\n");
      // The document patient ID is for the desired patient
      sb.append(" eo.id = ei.registryobject AND\n");
      sb.append(" ei.identificationScheme='");
        sb.append(XdsDocumentEntry.getDocumentIdIdentificationScheme());
        sb.append("' AND\n");
          sb.append(" ei.value='");
        sb.append(documentId);
        sb.append("' AND\n");
        // The document is approved, ie. currently valid
      sb.append(" eo.status = 'Approved'\n");
      return sb.toString();
    }

    /**
     * Creates an Xds Registry Query to GetRelatedDocument (e.g.e The Replacement doc).
     *
     * @param documentId the id of the document that relates its predecessor.
     * @return the query of get the related doc.
     */
    public static String createGetRelatedDocumentQueryString(String documentId, SharedEnums.DocumentRelationship relation) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("SELECT rplc.id FROM ExtrinsicObject rplc, ExtrinsicObject doc, Association a\n");
        //select based on the unique document id.
        sb.append("WHERE doc.id IN (\n");
        sb.append("SELECT doc.id FROM ExtrinsicObject doc, ExternalIdentifier uniqId\n");
        sb.append(" WHERE uniqId.registryobject = doc.id AND\n");
        sb.append(" uniqId.identificationScheme = '");
        sb.append(XdsDocumentEntry.getDocumentIdIdentificationScheme());
        sb.append("' AND\n");
        sb.append(" uniqId.value='");
        sb.append(documentId);
        sb.append("') AND \n");
        //Association is RPLC
        sb.append(" (a.associationType = '" + relation.getValue()+ "' AND a.targetObject = rplc.id AND a.sourceObject = doc.id)\n");
        return sb.toString();
    }
}
