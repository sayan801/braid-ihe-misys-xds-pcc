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

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.IDocumentContents;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.SecureConnectionDescription;
import com.misyshealthcare.connect.net.StandardConnectionDescription;

/**
 * This class holds everything needed to retrieve the contents of a
 * document from an IHE repository.
 * <p>
 * This class constructs and sends ebRIM XML messages in SOAP wrappers as required
 * by the IHE Retrieve Document Transation (ITI-17). See references:
 * <ul>
 * <li> IHE ITI Technical Framework Vol.2, August 2005, Section 3.17
  * </ul>
 * Document queries return XdsDocumentEntry metadata objects.  The metadata
 * contains a description of a document and a URI that can be used to retrieve
 * the document itself.  In non-secure networking situations, the URI can
 * be used directly to retrieve the document using a standard HTTP GET.  However,
 * in secure environments, a TLS connection must be set up with the document
 * server before the HTTP GET can be issued.  Since TLS is not the same as SSL,
 * the default HTTP agent cannot be used to create the connection.  This
 * code encapsulates the URI and the Connection description for the server.  It
 * then creates an appropriate connection using TLS (or not) and does the HTTP
 * GET to get the contents when it is requested.
 * <p>
 * This class is used anonymously inside a Document to hold a pointer to
 * the contents.  It should never be created outside the Xds Document Consumer.
 * <p>
 * This is where any caching of the document contents would be done.
 * 
 * @author Jim Firby
 * @version 2.0 - Dec 18, 2005
 * @see com.misyshealthcare.connect.base.IDocumentContents
 * @see com.misyshealthcare.connect.ihe.XdsDocumentConsumer
 */
public class XdsDocumentContents implements IDocumentContents {
	
	/* The logger for error and debugging messages */
	private final static Logger log = Logger.getLogger(XdsDocumentContents.class);

	//TODO - There should probably be configuration parameters that map URI type to access method
	private String documentUri = null;
	private IConnectionDescription connection = null;
	private IheAuditTrail auditTrail = null;
	private Document document = null;
	
	private IMesaLogger mesaLogger = null;
	
	/**
	 * Create a new potential document content stream.
	 * 
	 * @param documentUri The URI pointing to the document contents
	 * @param connection The connection that should be used to retrieve the document
	 * @param document The document object that contains the document metadata
	 * @param auditTrail The audit trail for the XdsDocumentConsumer that created this content stream
	 * @param mesaLogger The MESA test logger, if there is one
	 */
	XdsDocumentContents(String documentUri, IConnectionDescription connection, Document document, IheAuditTrail auditTrail, IMesaLogger mesaLogger) {
		this.documentUri = documentUri;
		this.connection = connection;
		this.document = document;
		this.mesaLogger = mesaLogger;
		this.auditTrail = auditTrail;

        // Synthesize a connect for this fetch
		if (connection instanceof SecureConnectionDescription ) {
            // just a hack here
            if(documentUri != null && documentUri.startsWith("http:"))  {
                StandardConnectionDescription urlConn = new StandardConnectionDescription();
                urlConn.setHostname(HttpChannel.getUrlHost(documentUri));
                urlConn.setPort(HttpChannel.getUrlPort(documentUri));
                urlConn.setUrlPath(HttpChannel.getUrlPath(documentUri));
                this.connection = urlConn;
                
            }
            else {
            SecureConnectionDescription xdsConn = (SecureConnectionDescription) connection;
			SecureConnectionDescription urlConn = new SecureConnectionDescription();
			urlConn.setHostname(HttpChannel.getUrlHost(documentUri));
			urlConn.setPort(HttpChannel.getUrlPort(documentUri));
			urlConn.setUrlPath(HttpChannel.getUrlPath(documentUri));
			urlConn.setKeyStore(xdsConn.getKeyStoreString());
			urlConn.setKeyStorePassword(xdsConn.getKeyStorePassword());
			urlConn.setTrustStore(xdsConn.getTrustStoreString());
			urlConn.setTrustStorePassword(xdsConn.getTrustStorePassword());
			this.connection = urlConn;
            }
        } else {
			StandardConnectionDescription urlConn = new StandardConnectionDescription();
			urlConn.setHostname(HttpChannel.getUrlHost(documentUri));
			urlConn.setPort(HttpChannel.getUrlPort(documentUri));
			urlConn.setUrlPath(HttpChannel.getUrlPath(documentUri));
			this.connection = urlConn;			
		}
	}


    /**
	 * Get the contents for this document as an Input Stream.
	 * 
	 * @return The contents as a byte stream
	 * @see com.misyshealthcare.connect.base.IDocumentContents#getDocumentContentStream()
	 */
	public InputStream getDocumentContentStream() throws DocumentException {
		// Create a communication channel
		HttpChannel channel = new HttpChannel(connection);
		// Log this if doing a MESA test
		if (mesaLogger != null) {
			//String path = channel.getUrlPath(documentUri);
			mesaLogger.writeString("Getting document content from '" + documentUri + "'.");
		}
		// Actually fetch the document contents
		try {
			// Get the response
			InputStream result = channel.get(documentUri);
			// Log it
			if (auditTrail != null) {
				ParticipantObject patientobject = new ParticipantObject(document);
				ParticipantObject documentobject = new ParticipantObject(document.getTitle(), document.getUniqueId());
				auditTrail.documentRetrieved(connection, patientobject, documentobject, false);
			}
			// Return the stream
			return result;
		} catch (IOException e) {
			// An error talking to the HTTP server
			log.error("Cannot get document, URI: '" + documentUri + "'", e);
			throw new DocumentException("Cannot get document, URI: '" + documentUri + "'", e);
		}
	}
	
}
