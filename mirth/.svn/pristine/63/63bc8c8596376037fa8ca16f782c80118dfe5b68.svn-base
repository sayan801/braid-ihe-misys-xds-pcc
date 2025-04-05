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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.DocumentRequest;
import com.misyshealthcare.connect.base.DocumentResponse;
import com.misyshealthcare.connect.base.IDocumentContents;
import com.misyshealthcare.connect.base.IDocumentRetrieve;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader.IheBrokerController;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class holds everything needed to retrieve the contents of a
 * document from an IHE repository.
 * <p>
 * This class constructs and sends ebRIM XML messages in SOAP wrappers as required
 * by the IHE Retrieve Document Transation (ITI-17). See references:
 * <ul>
 * <li> IHE ITI Supplement XDS-2.pdf, Section 3.43
  * </ul>
 * Document queries return XdsDocumentEntry metadata objects.  The metadata
 * contains a description of a document including a document unique id and  
 * a document repository id that can be used to retrieve
 * the document itself.  In non-secure networking situations, 
 * <p>
 * This class is used anonymously inside a Document to hold a pointer to
 * the contents.  It should never be created outside the Xds Document Consumer.
 * <p>
 * This is where any caching of the document contents would be done.
 * 
 * @author Wenzhi Li
 * @version 2.1 Jan 23, 2008
 * @see com.misyshealthcare.connect.base.IDocumentContents
 * @see com.misyshealthcare.connect.ihe.XdsDocumentConsumer
 */
public class XdsbDocumentContents implements IDocumentContents {
	
	/* The logger for error and debugging messages */
	private final static Logger log = Logger.getLogger(XdsbDocumentContents.class);

	private String documentUniqueId = null;
	private String repositoryUniqueId = null;
	private IheAuditTrail auditTrail = null;
	private Document document = null;
    private static Vector<IDocumentRetrieve> repositories = new Vector<IDocumentRetrieve>();

	private IMesaLogger mesaLogger = null;
	
	/**
	 * Create a new potential document content stream.
	 * 
	 * @param documentUniqueId The document unique id
	 * @param repositoryUniqueId The repository unique id pointing to the XDS repository that stores this document
	 * @param connection The connection that should be used to retrieve the document
	 * @param document The document object that contains the document metadata
	 * @param auditTrail The audit trail for the XdsDocumentConsumer that created this content stream
	 * @param mesaLogger The MESA test logger, if there is one
	 */
	XdsbDocumentContents(String documentUniqueId, String repositoryUniqueId,  Document document, IheAuditTrail auditTrail, IMesaLogger mesaLogger) {
		this.documentUniqueId = documentUniqueId;
		this.repositoryUniqueId = repositoryUniqueId;
		this.document = document;
		this.mesaLogger = mesaLogger;
		this.auditTrail = auditTrail;

	}

    /**
     * Register a new repository of documents.  This method
     * is typically called when XDS repository actor is initialized to
     * load up all of the known repositories of IHE documents.  
     *
     * @param repository An XDS repository 
     * @return True if this repository was successfully added
     */
    public synchronized static boolean registerDocumentRepository(IDocumentRetrieve repository) {
        // If the source is new, add it to the list
        if ((repositories != null) && (!repositories.contains(repository))) {
        	repositories.add(repository);
            return true;
        } else {
            return false;
        }
    }	
    
    /**
     * Unregister all the document repository actors  from the XdsbDocumentContents.
     * specified by the controller.  If the controller is null unregister all consumers. 
     *
     * @param controller The controller specifying which repository to unregister
     * @return 'true' if any repositories were actually unregistered
     */
    public synchronized static boolean unregisterDocumentRepository(IheBrokerController controller) {
        ArrayList<IDocumentRetrieve> removed = new ArrayList<IDocumentRetrieve>();
        // Find all the repositories to remove
        for (IDocumentRetrieve repository: repositories) {
            if ((controller == null) || controller.shouldUnregister(repository)) {
                removed.add(repository);
            }
        }
 
        if (removed.isEmpty()) return false;
        // Remove them
        repositories.removeAll(removed);
        return true;
    }

    /**
	 * Get the contents for this document as an Input Stream.
	 * 
	 * @return The contents as a byte stream
	 * @see com.misyshealthcare.connect.base.IDocumentContents#getDocumentContentStream()
	 */
	public InputStream getDocumentContentStream() throws DocumentException {		
		List<DocumentRequest> docRequests = new ArrayList<DocumentRequest>();
		docRequests.add( new DocumentRequest(documentUniqueId, repositoryUniqueId));
		
		if (repositories.size() == 0) {
			log.error( "No XDS Repository is configured for retrieving document set." );
			return null;
		}
			
		for (IDocumentRetrieve repository : repositories) {
			try{
				List<DocumentResponse> docs = repository.retrieveDocumentContents(docRequests);
				//Only one document should exist. So just return it if found
				if (docs.size() > 0) {
					return docs.get(0).getContentStream();
				}
			}catch(DocumentException e) {
				//if fails, continue to try next. This could be mismatched repositoryUniqueId
				continue;
			}
	 	}
		return null;
	}
	
}

