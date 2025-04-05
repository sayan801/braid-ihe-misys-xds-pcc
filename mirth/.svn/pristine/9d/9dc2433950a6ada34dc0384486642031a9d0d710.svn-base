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

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentException;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.net.IConnectionDescription;


/**
 * @author Michael Traum
 * @version 2.0, December 12, 2006
 *
 */
public class XdmDocumentSource extends XdDocumentSource { 

	private static final Logger log = Logger.getLogger(XdsDocumentConsumer.class);

	private XdmMessenger messenger = null;


	/**
	 * Create a new document source that will send documents to the system
	 * described by the connection.
	 * 
	 * @param connection
	 *            The system to receive new documents
	 */
	public XdmDocumentSource(IConnectionDescription connection,
			IheAuditTrail auditTrail) {
		this(connection, null, auditTrail);
	}

	/**
	 * Create a new document source that will send documents to the system
	 * described by the connection and will allow document replacements that
	 * require querying the registry.
	 * 
	 * @param connection
	 *            The system to receive new documents
	 * @param queryConnection
	 *            The system to return document registry IDs when replacing
	 */
	public XdmDocumentSource(IConnectionDescription connection,
			IConnectionDescription queryConnection, IheAuditTrail auditTrail) 
	{
		super(queryConnection, log, auditTrail);

		messenger = new XdmMessenger(queryConnection, auditTrail);
	}

	/**
	 * The submitDocument method defined in the IDocumentConsumer interface.
	 * 
	 * @see com.misyshealthcare.connect.base.IDocumentConsumer#submitDocument(Document,
	 *      String, XdsContentCode)
	 */
	public void submitDocument(Document document, String description,
			XdsContentCode contentCode,
			String directory, String mediaDescription)
			throws DocumentException 
	{
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(document);
		submitDocuments(documents, description, contentCode, directory, mediaDescription);
	}

	/**
	 * The submitDocuments method defined in the IDocumentConsumer interface.
	 * 
	 * @see com.misyshealthcare.connect.base.IDocumentConsumer#submitDocuments
	 *      (Collection<Document>, String, XdsContentCode)
	 */
	public void submitDocuments(Collection<Document> documents,
			String description, XdsContentCode contentCode,
			String directory, String mediaDescription)
			throws DocumentException 
	{
		submitDocumentSet(documents, description, contentCode,	directory, mediaDescription);
	}

	/**
	 * Submit a set of documents to the repository.
	 * 
	 * @param documents
	 *            The documents to be submitted
	 * @param description
	 *            A description of these documents
	 * @param contentCode
	 *            A code describing the contents of these documents
	 * @throws DocumentException
	 *             When there is a problem with the submission
	 */
	private void submitDocumentSet(Collection<Document> documents,
			String description, XdsContentCode contentCode,
			String directory, String mediaDescription)
			throws DocumentException 
	{
		messenger.send(documents, description, contentCode, directory, mediaDescription);
	}
}
