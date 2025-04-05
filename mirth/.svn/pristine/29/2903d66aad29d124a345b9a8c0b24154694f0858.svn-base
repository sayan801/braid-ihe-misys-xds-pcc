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

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.DocumentRequest;
import com.misyshealthcare.connect.base.DocumentResponse;
import com.misyshealthcare.connect.base.IDocumentRetrieve;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.registry.XdsResponse;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class implements IDocumentRetrieve. It handles retrieving document
 * set transaction in XDS.b 
 *   
 * @author Wenzhi Li
 * @version 2.1 Jan 28, 2008
 */
public class XdsDocumentRetrieve extends XdsDocumentActor implements IDocumentRetrieve {
    private final static Logger log = Logger.getLogger(XdsDocumentRetrieve.class);

    private IConnectionDescription connection = null;
    private XdsbMessenger messenger = null;
    private IheAuditTrail auditTrail = null;

    /**
     * Create a new document retriever that will retrieve document set from
     * the system described by the connection. The system can be from 2 sources:
     * 1. Document Repository Actor as specified by XDS.b profile
     * 2. Init Gateway Actor, as specified by XCA profile.   
     *
     * @param connection The system to receive new documents
     */
    public XdsDocumentRetrieve(IConnectionDescription connection, IheAuditTrail auditTrail) {        
        super(connection, log, auditTrail);
        this.connection = connection;
        this.auditTrail = auditTrail;
        this.messenger = new XdsbMessenger(connection);
    }
    
	/**
	 * Get the contents of the supplied documents from document repository.
	 * 
	 * @return The DocumentResponse representing the document content.
	 * @throws DocumentException if retrieving document fails
	 */
	public List<DocumentResponse> retrieveDocumentContents(List<DocumentRequest> docRequests) throws DocumentException {
        XdsbMessenger messenger = new XdsbMessenger(connection);
        OMElement request = messenger.createRetrieveDocumentSetRequest( docRequests );
        OMElement message = messenger.send(request, getMesaLogger(), "retrieving", getOkayToSend());
        XdsResponse response = messenger.getXdsResponse((OMElement)message);
        if (response == null) {
            throwBadCommunicationException();
        } else if (response.isError()) {
            logSoapMessageError(message, response.getErrorMessage());
            throwBadCommunicationException();
        } else if (!response.isSuccess()) {
            // The registry returned failure message
            String error = "Retrieving document set failed at repository";
            logSoapMessageError(message, error);
            throwBadCommunicationException();
        }
	    List<DocumentResponse> ret = response.getDocumentResponses();	 
		//log it first
	    for (DocumentRequest req : docRequests) {
			if (auditTrail != null) {
				ParticipantObject patientobject = null; //new ParticipantObject(document);
				ParticipantObject documentobject = new ParticipantObject(null, req.getDocumentUniqueId());
				auditTrail.documentRetrieved(connection, patientobject, documentobject, true);
			}
	    }
		return ret;
	}
	
	/**
     * Throw an exception because the incorrect response.
     *
     * @throws DocumentException The exception being thrown
     */
    private void throwBadCommunicationException() throws DocumentException {
        String message = "Unexpected document set retriving response from connection \"" + connection.getDescription() + "\" (see log for details)";
        log.error(message);
        throw new DocumentException(message);
    }
	

}
