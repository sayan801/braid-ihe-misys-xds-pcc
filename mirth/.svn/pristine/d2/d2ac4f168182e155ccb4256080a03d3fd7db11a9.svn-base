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
package com.misyshealthcare.connect.base;

import java.util.*;

import com.misyshealthcare.connect.base.*;
import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;

import junit.framework.TestCase;

public class DocumentBrokerTest extends TestCase {
	
	private static MockSource ms = null;
	private static MockConsumer mc = null;
	
    public void setUp() throws Exception {
		if (ms == null)	ms = new MockSource();
		DocumentBroker.getInstance().registerDocumentSource(ms);
		if (mc == null)	mc = new MockConsumer();
		DocumentBroker.getInstance().registerDocumentConsumer(mc);
    }
	
    public void tearDown() throws Exception {
    	IheBrokerController controller = new IheBrokerController();
    	DocumentBroker.getInstance().unregisterDocumentConsumers(controller);
    	DocumentBroker.getInstance().unregisterDocumentSources(controller);
    }
	
	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.getInstance()'
	 */
	public void testGetInstance() {
		assertSame(DocumentBroker.getInstance(), DocumentBroker.getInstance());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.registerDocumentSource(IDocumentSource)'
	 */
	public void testRegisterDocumentSource() {
		
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.registerDocumentConsumer(IDocumentConsumer)'
	 */
	public void testRegisterDocumentConsumer() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.findDocuments(DocumentQuery)'
	 */
	public void testFindDocuments() {
		DocumentBroker broker = DocumentBroker.getInstance();
		List<Document> docs = broker.findDocuments(new DocumentQuery());
		assertNotNull(docs);
		assertEquals(4, docs.size());
		assertEquals("C", docs.get(0).getAuthorDescriptors().get(0).getAuthorInstitutions().get(0));
		assertEquals("B", docs.get(1).getAuthorDescriptors().get(0).getAuthorInstitutions().get(0));
		assertEquals("A", docs.get(2).getAuthorDescriptors().get(0).getAuthorInstitutions().get(0));
		assertEquals("A", docs.get(3).getAuthorDescriptors().get(0).getAuthorInstitutions().get(0));
	}


    /*
    * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.findDocumentSets(DocumentQuery)'
    */
	public void testFindDocumentSetsDocumentQuery() {
		DocumentBroker broker = DocumentBroker.getInstance();
		List<DocumentSet> docs = broker.findDocumentSets(new DocumentQuery());
		assertNotNull(docs);
		assertEquals(3, docs.size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.findDocumentSets(PatientDescriptor, Date, Date)'
	 */
	public void testFindDocumentSetsPatientDescriptorDateDate() {
		DocumentBroker broker = DocumentBroker.getInstance();
		List<DocumentSet> docs = broker.findDocumentSets(new PatientDescriptor(), new DateQuery(), new DateQuery());
		assertNotNull(docs);
		assertEquals(3, docs.size());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.submitDocument(Document)'
	 */
	public void testSubmitDocument() {
		DocumentBroker broker = DocumentBroker.getInstance();
		mc.numCalls = 0;
		broker.submitDocument(new Document(), "Test document", XdsContentCode.UNKNOWN);
		assertEquals(1, mc.numCalls);
	}
	
	/*
	 * Test method for 'com.misyshealthcare.connect.base.DocumentBroker.submitDocuments(Document)'
	 */
	public void testSubmitDocuments() {
		DocumentBroker broker = DocumentBroker.getInstance();
		mc.numCalls = 0;
		Vector<Document> docs = new Vector<Document>();
		docs.add(new Document());
		broker.submitDocuments(docs, "Test document set", XdsContentCode.UNKNOWN);
		assertEquals(1, mc.numCalls);
	}
	
	public class MockSource implements IDocumentSource {
		
		public void start() {}
		
		public void stop() {}

		public List<Document> findDocuments(DocumentQuery query) {
			Vector<Document> docs = new Vector<Document>();
			Calendar time = GregorianCalendar.getInstance();
			time.setTime(new Date());
			Document doc = new Document();
            AuthorDescriptor authorA = new AuthorDescriptor();
            authorA.addAuthorInstitution("A");
            doc.addAuthorDescriptor(authorA);
			doc.setCreationTime(time.getTime());
			docs.add(doc);
			doc = new Document();
            AuthorDescriptor authorB = new AuthorDescriptor();
            authorB.addAuthorInstitution("B");
            doc.addAuthorDescriptor(authorB);
			time.add(Calendar.HOUR, 2);
			doc.setCreationTime(time.getTime());
			docs.add(doc);
			doc = new Document();
            authorA = new AuthorDescriptor();
            authorA.addAuthorInstitution("A");
            doc.addAuthorDescriptor(authorA);
			time.add(Calendar.HOUR, -1);
			doc.setCreationTime(time.getTime());
			docs.add(doc);
			doc = new Document();
			time.add(Calendar.HOUR, 2);
			doc.setCreationTime(time.getTime());
            AuthorDescriptor authorC = new AuthorDescriptor();
            authorC.addAuthorInstitution("C");
            doc.addAuthorDescriptor(authorC);
			docs.add(doc);
			return docs;
		}

    }
	
	public class MockConsumer implements IDocumentConsumer {

		public int numCalls = 0;
		
		public void start() {}
		
		public void stop() {}

		public void submitDocument(Document document, String description, XdsContentCode contentCode) throws DocumentException {
			numCalls = numCalls + 1;
		}

		public void submitDocuments(Collection<Document> documents, String description, XdsContentCode contentCode) throws DocumentException {
			numCalls = numCalls + 1;
		}
	}
	
	/**
	 * An implementation of a broker controller that will unregister and IHE actor.
	 * 
	 * @author Jim Firby
	 * @version 2.0 - Jan 11, 2006
	 */
	public class IheBrokerController implements IBrokerController {

		public boolean shouldUnregister(Object actor) {
			// Unregister the mock actors
            if (actor instanceof IDocumentSource) return true;
            if (actor instanceof IDocumentConsumer) return true;

            return false;
		}
		
	}

}
