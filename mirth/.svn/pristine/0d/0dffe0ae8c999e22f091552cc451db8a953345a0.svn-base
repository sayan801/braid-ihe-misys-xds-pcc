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
package com.misyshealthcare.connect.ihe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.*;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.registry.*;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.StringUtil;
import com.misyshealthcare.connect.util.Pair;
import com.misyshealthcare.connect.util.XmlUtil;

/**
 * This class implements the IHE XDS Document Consumer Actor.  
 * It also implements the com.misyshealthcare.connect.base.IDocumentSource
 * interface so that it can be used as document source by the
 * DocumentBroker.
 * <p>
 * This class constructs and sends ebRIM XML messages in SOAP wrappers as required
 * by the IHE Query Registry Transation (ITI-16). See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.16
 * <li> OASIS/ebXML Registry Information Model v2.0, December 2001
 * <li> OASIS/ebXML Registry Services Specification v2.0, December 2001
 * </ul>
 * A number of connection configuration parameters are used by this
 * actor:
 * <ul>
 * <li> LocalAssigningAuthority (Identifier) optional: 
 *        Specifies the Local Assigning Authority used by this actor. It is used to
 *        see if a returned Patient ID happens to be a local unique ID.  This won't usually
 *        be the case, but it might be.  If it is not supplied, no document will ever
 *        be returned with a known local unique ID. (It is not a good idea to
 *        leave this value unspecified).
 * <li> XdsClassCode (EnumMap) optional: 
 *        This EnumMap describes the mapping from internal Misys Connect XdsClassCode enum codes
 *        to class code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Source.  If this enum map is not
 *        supplied, all document class codes will be 'UNKNOWN'. (It is not a good idea to
 *        leave this value unspecified).
 * <li> XdsFormatCode (EnumMap) optional: 
 *        This EnumMap describes the mapping from internal Misys Connect XdsFormatCode enum codes
 *        to format code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Source.  If this enum map is not
 *        supplied, all document format codes will be 'UNKNOWN'. (It is not a good idea to
 *        leave this value unspecified).
 * <li> XdsTypeCode (EnumMap) optional: 
 *        This EnumMap describes the mapping from internal Misys Connect XdsTypeCode enum codes
 *        to type code values appropriate for this registry/repository.  This enum map can, and 
 *        should be shared with the corresponding Xds Document Source.  If this enum map is not
 *        supplied, all document type codes will be 'UNKNOWN'. (It is not a good idea to
 *        leave this value unspecified).
 * <li> authorRole (StringMap) optional: 
 *        This StringMap describes a mapping from internal Misys Connect author role values
 *        to author role code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Source.  If this string
 *        map is not supplied, author role codes will be taken untranslated from the document
 *        metadata.
 * <li> authorSpecialty (StringMap) optional: 
 *        This StringMap describes a mapping from internal Misys Connect author specialty values
 *        to author specialty code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Source.  If this string
 *        map is not supplied, author specialty codes will be taken untranslated from the document
 *        metadata.
 * <li> facilityCode (StringMap) optional:
 *        This StringMap describes a mapping from internal Misys Connect facility code values
 *        to healthcare facility code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Source.  If this string
 *        map is not supplied, healthcare facility codes will be taken untranslated from the document
 *        metadata.
 * <li> practiceCode (StringMap) optional:
 *        This StringMap describes a mapping from internal Misys Connect practice code values
 *        to practice setting code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Source.  If this string
 *        map is not supplied, practice setting codes will be taken untranslated from the document
 *        metadata.
 * <li> confidentialityCode (StringMap) optional:
 *        This StringMap describes a mapping from internal Misys Connect confidentiality code values
 *        to confidentiality code values appropriate for this registry/repository.  This string map 
 *        can, and should be shared with the corresponding Xds Document Source.  If this string
 *        map is not supplied, confidentiality codes will be taken directly from the document
 *        metadata and translated to a boolean value.  Ie. only if the raw value is "true" will the
 *        patient be marked as confidential.
 * </ul>
 *        
 * @author Jim Firby
 * @version 2.0 - Nov 1, 2005
 * @see com.misyshealthcare.connect.base.IDocumentSource
 * @see com.misyshealthcare.connect.base.DocumentBroker
 * @see com.misyshealthcare.connect.base.Document
 * @see com.misyshealthcare.connect.base.IDocumentContents
 */
public class XdsDocumentConsumer extends XdsDocumentActor implements IDocumentSource {

  private static final Logger log = Logger.getLogger(XdsDocumentConsumer.class.getName());

  private static final String RETURN_TYPE_OBJECT = "LeafClass";
  private static final String RETURN_TYPE_REFERENCE = "ObjectRef";

  private IConnectionDescription connection = null;
  private XdsMessenger messenger = null;
  private IheAuditTrail auditTrail = null;

  /**
   * Construct a new document consumer that will get documents from the
   * system described in the connection.
   * 
   * @param connection The system to get documents from
   */
  public XdsDocumentConsumer(IConnectionDescription connection, IheAuditTrail auditTrail) {
      super(connection, log, auditTrail);
      this.connection = connection;
      this.auditTrail = auditTrail;
      this.messenger = new XdsMessenger(connection, true);
  }

    /* (non-Javadoc)
      * @see com.misyshealthcare.connect.base.IDocumentSource#findDocuments(com.misyshealthcare.connect.base.DocumentQuery)
      */
    public List<Document> findDocuments(DocumentQuery query) throws DocumentException {
        //first, make sure all the query parameters are valid.
        String message = query.verifyQueryParameters();
        if (StringUtil.goodString(message))
            throw new DocumentException( message );

        // Make the query
        SOAPMessage response = sendXdsDocumentQuery(query, true);
        // Grab the document metadata returned
        List<XdsDocumentEntry> metadata = extractDocumentEntries(response);
        // Turn each metadata entry into a document
        List<Document> results = null;
        if (metadata == null) {
            results = new ArrayList<Document>();
        } else {
            try {
                results = translateDocumentMetadata(metadata);
            } catch (IheConfigurationException e) {
                throwBadConfigurationException("query", e);
            }
        }

        // Done
        return results;
    }


    /**
     * Look up the XDS registry UUID for the XdsDocumentEntry cooresponding to a
     * particular document.  The document is indexed by its globally unique ID.
     * <p>
     * This method is used by the XdsDocumentSource actor when it is replacing
     * a document.
     *
     * @param documentId The globally unique ID for this document
     * @return The UUID for the metadata for this document in the registry
     * @see com.misyshealthcare.connect.base.Document#getUniqueId()
     */
    String findOneDocumentRegistryId(String documentId) {
        if (!StringUtil.goodString(documentId))
            return null;

        DocumentQuery query = new DocumentQuery();
        query.setDocumentId(documentId);
        SOAPMessage response = sendXdsDocumentQuery(query, false);
        List<String> docIds = extractDocumentIDs(response);
        if ((docIds == null) || (docIds.size() < 1)) {
            return null;
        } else if (docIds.size() == 1) {
            return docIds.get(0);
        } else {
            log.warn("Repository query on document unique ID returned " + docIds.size() + " answers, should be 1.");
            return docIds.get(0);
        }
    }

    /**
     * Turn a SQL query into a SOAP message and send it to the repository/registry.
     * Ask the registry to respond respond with either entire document metadata
     * objects or just registry UUIDs.
     *
     * @param query The SQL query that picks out the documents of interest
     * @param returnObjects True if document metadata should be returned, false if just the UUIDs are needed
     * @return The SOAP reply from the registry
     */
    private SOAPMessage sendXdsDocumentQuery(DocumentQuery query, boolean returnObjects) {
        if (returnObjects) {
            return sendXdsDocumentQuery(query, RETURN_TYPE_OBJECT, "true");
        } else {
            return sendXdsDocumentQuery(query, RETURN_TYPE_REFERENCE, "false");
        }
    }

    /**
     * Turn a SQL query into a SOAP message and send it to the repository/registry.
     * Ask the registry to respond respond with either entire document metadata
     * objects or just registry UUIDs.
     *
     * @param query The SQL query to process at the registry
     * @param returnType Whether to return objects or object IDs
     * @param returnObjects Whether to return fleshed out objects or just headers
     * @return The SOAP response to the query
     */
    private SOAPMessage sendXdsDocumentQuery(DocumentQuery query, String returnType, String returnObjects) {
        // Make sure we have a valid Patient ID
        String patientId = null;
        try {
            patientId = XdsDocumentActor.translatePatientId(connection, query.getPatientId());
            // Create the SOAP message encapsulating the query
            SOAPMessage xdsQuery = messenger.createQueryRequest(query, patientId, returnType, returnObjects);
            
            // Audit Log the query regardless whether it is successful or not.
            if (auditTrail != null) {
            	try {
            		Pair queryId = XmlRegistry.getStoredQueryId(query, patientId);
	            	ParticipantObject queryobject = new ParticipantObject((String)queryId._second, (String)queryId._first);					
	            	queryobject.query = XmlUtil.formatNode(xdsQuery.getSOAPBody());
					 
	            	ParticipantObject patientobject = new ParticipantObject(null, query.getPatientId()); 
	                auditTrail.documentStoredQuery(connection, patientobject, queryobject);
            	} catch (SOAPException e) {
					log.error("Invalid SOAP request messag", e);
				}
            }

            // Write the message to the MESA test log (moved into messenger)
            // Send the message to the repository and read the response
            SOAPMessage result = messenger.send(xdsQuery, getMesaLogger(), "query", getOkayToSend());
            // Write the response to the MESA test log (moved into messenger)
            // Return the result
            return result;
        } catch (IheConfigurationException e) {
            throwBadConfigurationException("query", e);
        }
        return null;
    }


    /**
     * Extract all of the registry entry UUIDs from the SOAP response returned
     * by a registry query.
     *
     * @param message The SOAP response message
     * @return A list of registry entry UUIDs
     */
    private List<String> extractDocumentIDs(SOAPMessage message) {
        XdsResponse response = messenger.getXdsResponse(message);
        if (response == null) {
            return new Vector<String>();
        } else if (response.isError()) {
            logSoapMessageError(message, response.getErrorMessage());
            return new Vector<String>();
        } else if (response.isAdhocQueryResponse()) {
            // An appropriate response for this action
          return response.getQueryReferenceIds();
        } else {
            logSoapMessageError(message, "Unexpected registry query response type.");
            return new Vector<String>();
        }
    }

    /**
     * Extract all of the XDSDocumentEntry metadata objects from a SOAP response to
     * a registry query.
     *
     * @param message The SOAP response message
     * @return A list of XDSDocumentEntry metadata objects
     */
    private List<XdsDocumentEntry> extractDocumentEntries(SOAPMessage message) {
        XdsResponse response = messenger.getXdsResponse(message);
        if (response == null) {
            return new Vector<XdsDocumentEntry>();
        } else if (response.isError()) {
            logSoapMessageError(message, response.getErrorMessage());
            return new Vector<XdsDocumentEntry>();
        } else if (response.isAdhocQueryResponse()) {
            // An appropriate response for this action
          return response.getQueryDocumentEntries();
        } else {
            logSoapMessageError(message, "Unexpected registry/Adhoc query response type.");
            return new Vector<XdsDocumentEntry>();
        }
    }

    /**
     * Translate a list of XdsDOcumentEntry metadata objects into a list
     * of Misys Document objects. Registry and IHE affinity domain codes are
     * translated into Misys codes.
     * <p>
     * This method does essentially the inverse
     * of XdsDocumentSource which translates from Document objects to XdsDocumentEntry
     * metadata objects.
     *
     * @param metadata The XdsDOcumentEntry metadata objects
     * @return The Document objects
     * @throws IheConfigurationException When the connection is not properly configured to translate codes
     * @see com.misyshealthcare.connect.ihe.XdsDocumentSource
     */
    private List<Document> translateDocumentMetadata(List<XdsDocumentEntry> metadata) throws IheConfigurationException {
        Vector<Document> documents = new Vector<Document>();
        for (XdsDocumentEntry entry: metadata) {
        	Document document = translateSingleDocumentEntry(entry);
        	documents.add(document);
        }
        return documents;
    }

    /**
     * Get the MESA test inteface.  This interface enables a few lower-level
     * calls required by certain of the 2005 MESA tests.
     *
     * @return The mesa test interface
     */
    public MesaInterface getMesaInterface() {
        return new MesaInterface();
    }

    /**
     * The XdsDocumentConsumer MESA test interface.
     *
     * @author Jim Firby
     * @version 2.0 - Dec 18, 2005
     */
    public class MesaInterface {

        /**
         * Send a SQL query string to the registry as an ebRIM XML SOAP message.
         *
         * @param query The SQL query identifying the documents of interest
         * @param returnType The return type; either "LeafClass" or "ObjectRef"
         * @param returnObjects Whether to return the whole registry object or just their UUIDs
         * @return the SOAPMessage
         */
        public SOAPMessage sendXdsRegistryQuery(DocumentQuery query, String returnType, String returnObjects) {
            return sendXdsDocumentQuery(query, returnType, returnObjects);
        }

        /**
         * Extract the registry object UUIDs from a query response.
         *
         * @param message The query response message
         * @return The list of UUIDs contained in the response
         */
        public List<String> extractXdsRegistryObjectUUIDs(SOAPMessage message) {
            return extractDocumentIDs(message);
        }

        /**
         * Extract the registry metadata contained in the query response.
         *
         * @param message The query response message
         * @return The list of XdsDocumentEntry metadata objects in the response
         */
        public List<XdsDocumentEntry> extractXdsDocumentEntryMetadata(SOAPMessage message) {
            return extractDocumentEntries(message);
        }

    }

}
