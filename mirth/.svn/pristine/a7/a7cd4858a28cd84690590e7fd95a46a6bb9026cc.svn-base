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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.misyshealthcare.connect.base.SharedEnums.XdsContentCode;
import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.XdmDocumentSource;
import com.misyshealthcare.connect.ihe.XdrDocumentConsumer;
import com.misyshealthcare.connect.ihe.XdrDocumentSource;
import com.misyshealthcare.connect.ihe.XdsDocumentActor;
import com.misyshealthcare.connect.ihe.registry.XdrIntendedRecipient;

/**
 * This class manages the storage of IHE documents.  It hides
 * all of the complexity involved in dealing with IHE document
 * registries and repositories.  All operations are managed using
 * Document and DocumentDescriptor objects.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 19, 2005
 */
public class DocumentBroker {

    /* A single instance for this class */
    private static DocumentBroker singleton = null;

    /* A list of all known sources of documents */
    private Vector<IDocumentSource> sources = new Vector<IDocumentSource>();
    /* A list of all known consumers of documents */
    private Vector<IDocumentConsumer> consumers = new Vector<IDocumentConsumer>();
    // The following should probably be used in a more simmilar manner to the IDocument*
    // interfaces.  Should probably make this more generic in the future...  
    // Ideas: XDR send to the appropriate consumer for the recipient chain, and search all
    // sources for a given patient?  Only get from the sources that have messages for you?
    // XDM only have one and let it do the logic?  Have a couple and only send to the one you
    // are using right now?  Scratch that, for now we only use one.
    // List of known XDR sources of documents
    private Vector<XdrDocumentConsumer> xdrSources = new Vector<XdrDocumentConsumer>();
    /* A list of all known consumers of documents */
    private Vector<XdrDocumentSource> xdrConsumers = new Vector<XdrDocumentSource>();
    /* A list of all known consumers of documents */
    private Vector<XdmDocumentSource> xdmConsumers = new Vector<XdmDocumentSource>();
    
    /**
     * A private constructor for creating the singleton instance.
     */
    private DocumentBroker() {}

    /**
     * Get the single global instance of the <code>DocumentBroker</code>.
     *
     * @return The patient broker
     */
    public static synchronized DocumentBroker getInstance() {
        if (singleton == null) singleton = new DocumentBroker();
        return singleton;
    }

    /**
     * Register a new source of documents.  This method
     * is typically called when Misys Connect is initialized to
     * load up all of the known sources of IHE documents.  A source
     * is started when it is registered.
     *
     * @param source A source of documents
     * @return True if this source was successfully added
     */
    public synchronized boolean registerDocumentSource(IDocumentSource source) {
        // If the source is new, add it to the list
        if ((source != null) && (!sources.contains(source))) {
            source.start();
            sources.add(source);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Register a new source of XDR documents.  This method
     * is typically called when Misys Connect is initialized to
     * load up all of the known sources of IHE documents.  A source
     * is started when it is registered.
     *
     * @param source A source of documents
     * @return True if this source was successfully added
     */
    public synchronized boolean registerDocumentSource(XdrDocumentConsumer source) {
        // If the source is new, add it to the list
        if ((source != null) && (!xdrSources.contains(source))) {
            source.start();
            xdrSources.add(source);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unregister the active document sources specified by the controller.  If the
     * controller is null unregister all sources.  A source is stopped when it is
     * unregistered.
     *
     * @param controller The controller specifying which sources to unregister
     * @return 'true' if any sources were actually unregistered
     */
    public synchronized boolean unregisterDocumentSources(IBrokerController controller) {
        ArrayList<IDocumentSource> removed = new ArrayList<IDocumentSource>();
        ArrayList<XdrDocumentConsumer> xdrRemoved = new ArrayList<XdrDocumentConsumer>();
        // Find all the sources to remove
        for (IDocumentSource source: sources) {
            if ((controller == null) || controller.shouldUnregister(source)) {
                removed.add(source);
            }
        }
        for (XdrDocumentConsumer source: xdrSources) {
            if ((controller == null) || controller.shouldUnregister(source)) {
                xdrRemoved.add(source);
            }
        }
        if (removed.isEmpty() && xdrRemoved.isEmpty()) return false;
        // Remove them
        sources.removeAll(removed);
        xdrSources.removeAll(xdrRemoved);
        // Stop them all too
        for (IDocumentSource source: removed) source.stop();
        for (XdrDocumentConsumer source: xdrRemoved) source.stop();
        return true;
    }

    /**
     * Get the number of document sources known to this broker.  Used
     * for JUnit tests.
     *
     * @return The number of document sources known to this broker
     */
    public int documentSourceCount() {
        return sources.size();
    }

    /**
     * Register a new repository of documents.  This method
     * is typically called when Misys Connect is initialized to
     * load up all of the known consumers of IHE documents.  A
     * consumer is started when it is registered.
     *
     * @param consumer A consumer of new documents
     * @return True if this consumer was successfully added
     */
    public synchronized boolean registerDocumentConsumer(IDocumentConsumer consumer) {
        // If the source is new, add it to the list
        if ((consumer != null) && (!consumers.contains(consumer))) {
            consumer.start();
            consumers.add(consumer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Register a new xdr recipient machine.  This method
     * is typically called when Misys Connect is initialized to
     * load up all of the known consumers of IHE documents.  A
     * consumer is started when it is registered.
     *
     * @param consumer A consumer of new documents
     * @return True if this consumer was successfully added
     */
    public synchronized boolean registerXdrDocumentConsumer(XdrDocumentSource consumer) {
        // If the source is new, add it to the list
        if ((consumer != null) && (!xdrConsumers.contains(consumer))) {
            consumer.start();
            xdrConsumers.add(consumer);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Register a new xdr recipient machine.  This method
     * is typically called when Misys Connect is initialized to
     * load up all of the known consumers of IHE documents.  A
     * consumer is started when it is registered.
     *
     * @param consumer A consumer of new documents
     * @return True if this consumer was successfully added
     */
    public synchronized boolean registerXdmDocumentConsumer(XdmDocumentSource consumer) {
        // If the source is new, add it to the list
        if ((consumer != null) && (!xdmConsumers.contains(consumer))) {
            consumer.start();
            xdmConsumers.add(consumer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unregister the active document consumers specified by the controller.  If the
     * controller is null unregister all consumers.  A consumer is stopped when it is
     * unregistered.
     *
     * @param controller The controller specifying which consumers to unregister
     * @return 'true' if any consumers were actually unregistered
     */
    public synchronized boolean unregisterDocumentConsumers(IBrokerController controller) {
        ArrayList<IDocumentConsumer> removed = new ArrayList<IDocumentConsumer>();
        ArrayList<XdrDocumentSource> xdrRemoved = new ArrayList<XdrDocumentSource>();
        ArrayList<XdmDocumentSource> xdmRemoved = new ArrayList<XdmDocumentSource>();
        // Find all the sources to remove
        for (IDocumentConsumer consumer: consumers) {
            if ((controller == null) || controller.shouldUnregister(consumer)) {
                removed.add(consumer);
            }
        }
        for (XdrDocumentSource consumer: xdrConsumers) {
            if ((controller == null) || controller.shouldUnregister(consumer)) {
                xdrRemoved.add(consumer);
            }
        }
        for (XdmDocumentSource consumer: xdmConsumers) {
            if ((controller == null) || controller.shouldUnregister(consumer)) {
                xdmRemoved.add(consumer);
            }
        }
        if (removed.isEmpty() && xdrRemoved.isEmpty()) return false;
        // Remove them
        consumers.removeAll(removed);
        xdrConsumers.removeAll(xdrRemoved);
        // Stop them all too
        for (IDocumentConsumer consumer: removed) consumer.stop();
        for (XdrDocumentSource consumer: xdrRemoved) consumer.stop();
        for (XdmDocumentSource consumer: xdmRemoved) consumer.stop();
        return true;
    }

    /**
     * Get the number of document consumers known to this broker.  Used
     * for JUnit tests.
     *
     * @return The number of document consumers known to this broker
     */
    public int documentConsumerCount() {
        return consumers.size() + xdrConsumers.size();
    }

    /**
     * Find all of the documents that match the given document query
     * and return them in a list.
     *
     * @param query A document query of the type of document sought
     * @return A list of descriptors for documents that match the desired description
     */
    public synchronized List<Document> findDocuments(DocumentQuery query) {

        if (sources.isEmpty()) {
            // Not sources to check
            return new Vector<Document>();
        } else {
            // Try to find matches in every known source
            Vector<Document> allDocuments = new Vector<Document>();
            for (IDocumentSource source: sources) {
                List<Document> documents = source.findDocuments(query);
                // For every document found, sort it into the list
                for (Document document: documents) {
                    DocumentSet.insertDocumentIntoList(allDocuments, document);
                }
            }
            // Enforce the maximum document query limit
            int maxCount = query.getMaxDocumentCount();
            if ((maxCount < 1) || (maxCount <= allDocuments.size())) {
                // No limit
                return allDocuments;
            } else {
                return allDocuments.subList(0, maxCount);
            }
        }
    }

    /**
     * This function returns a map containing all of the possible intended recipient sets.
     * If we were cool, we would send to the appropriate XDR connection based on the IRSet 
     * handed in.  We are not cool, and assume only one XDR connection.
     * @return All possible intended recipients for the first XDR connection.
     */
    public synchronized Map<String, List<XdrIntendedRecipient>> getIntendedRecipients() {
    	if (xdrConsumers.size() > 0)
    		return xdrConsumers.firstElement().getIntendedRecipients();
    	Map m = new HashMap();
    	return m;
	}

    
    /**
     * Find all of the documents that match the given document query
     * and return them in a list.  In the future we could filter on the patient id.
     *
     * @return A list of descriptors for documents that match the desired description
     */
    public synchronized List<Document> findXdrDocuments(PatientID patientId, int maxCount) {

        if (sources.isEmpty()) {
            // Not sources to check
            return new Vector<Document>();
        } else {
            // Try to find matches in every known source
            Vector<Document> allDocuments = new Vector<Document>();
            for (XdrDocumentConsumer source: xdrSources) {
                List<Document> documents = source.getAvailableDocuments(patientId);
                // For every document found, sort it into the list
                for (Document document: documents) {
                    DocumentSet.insertDocumentIntoList(allDocuments, document);
                }
            }
            // Enforce the maximum document query limit
            if ((maxCount < 1) || (maxCount <= allDocuments.size())) {
                // No limit
                return allDocuments;
            } else {
                return allDocuments.subList(0, maxCount);
            }
        }
    }


    /**
     * Find all the documents that match the document query and
     * sort them into sets that all come from the same facility.
     *
     * @param query The document description defining the query
     * @return A list of document sets that each hold documents from a single facility
     */
    public List<DocumentSet> findDocumentSets(DocumentQuery query) {
        List<DocumentSet> vds = new Vector<DocumentSet>();
        // Try to find matches in every known source
        Collection<Document> documents = findDocuments(query);
        // For every document found, fit it into a descriptor set
        for (Document document: documents) {
            // Try and merge this document into an existing set

            //First, get a set of unique facilities of this document
            Set<String> sfacilites = new HashSet<String>();
            List<AuthorDescriptor> ads =  document.getAuthorDescriptors();
            for (AuthorDescriptor ad : ads) {
                List<String> facilities = ad.getAuthorInstitutions();
                for (String facility : facilities) {
                    sfacilites.add( facility );
                }
            }
            //We need to add this document to each facility (DocumentSet)
            for (String facility : sfacilites) {
                boolean found = false;
                for (DocumentSet ds : vds) {
                    if (facility.equals(ds.getFacility()) ) {
                        if (ds.add(document)) {
                            found = true;
                            break;
                        }
                    }
                }
                // If the document doesn't fit in any set, make a new one for this facility
                if (!found) {
                    DocumentSet ds = new DocumentSet();
                    ds.setFacility( facility );
                    vds.add( ds );
                }
            }
        }
        // Sort the list of document sets via facility
        vds = sortDocumentSets(vds);
        // Enforce the maximum document query limit
        int maxCount = query.getMaxDocumentCount();
        if ((maxCount < 1) || (maxCount <= vds.size())) {
            // No limit
            return vds;
        } else {
            return vds.subList(0, maxCount);
        }
    }

    private List<DocumentSet> sortDocumentSets(List<DocumentSet> documentSets) {
        // Only one set, no need to sort
        if ((documentSets == null) || (documentSets.size() <= 1))
            return documentSets;
        // Sort by facility name
        List<DocumentSet> result = new Vector<DocumentSet>();
        for (DocumentSet documentSet: documentSets) {
            // We need to add it, sort it in based on creation time, newest first
            String documentFacility = documentSet.getFacility();
            if (documentFacility == null) {
                // Don't know name, just add to the end
                result.add(documentSet);
            } else {
                boolean found = false;
                int i = 0;
                while (i < result.size()) {
                    // Find the right place to put this document
                    String listFacility = result.get(i).getFacility();
                    if (listFacility == null) {
                        found = true;
                        break;
                    } else if (documentFacility.compareToIgnoreCase(listFacility) < 0) {
                        found = true;
                        break;
                    }
                    i = i + 1;
                }
                // Squeeze it in
                if (found && (i <= result.size())) {
                    result.add(i, documentSet);
                } else {
                    result.add(documentSet);
                }
            }
        }
        return result;
    }

    /**
     * Find all the documents for the given patient that were created
     * between the start time and the end time given.
     *
     * @param patient The patient that the documents should be about
     * @param startTime The date and time of the earliest document of interest
     * @param endTime The date and time of the latest document of interest
     * @return  A list of document sets that each hold documents from a single facility
     */
    public List<DocumentSet> findDocumentSets(PatientDescriptor patient, DateQuery startTime, DateQuery endTime) {
        DocumentQuery query = new DocumentQuery();
        query.setPatientId(patient.getPatientId());
        query.setStartTime(startTime);
        query.setEndTime(endTime);
        return findDocumentSets(query);
    }

    /**
     * Find all the documents for the given patient.
     *
     * @param patient The patient that the documents should be about
     * @return  A list of document sets that each hold documents from a single facility
     */
    public List<DocumentSet> findDocumentSets(PatientDescriptor patient) {
        return findDocumentSets(patient, null, null);
    }


    /**
     * Submit a new IHE document to the document repository(s).
     *
     * @param document The complete document to submit, metadata and content
     * @param description Description of this document
     * @param contentCode The content code for this document
     * @throws DocumentException When there is a problem submitting the documents
     */
    public void submitDocument(Document document, String description, XdsContentCode contentCode) throws DocumentException {
        //TODO Decide if all valid submits should be done before signalling an error
        if (document == null)
            throw new DocumentException("Invalid document to submit");

        for (IDocumentConsumer consumer: consumers) {
            consumer.submitDocument(document, description, contentCode);
        }
    }

    /**
     * Send a new IHE document to the document repository(s).
     *
     * @param document The complete document to submit, metadata and content
     * @param description Description of this document
     * @param contentCode The content code for this document
     * @throws DocumentException When there is a problem submitting the documents
     */
    public void sendXdrDocument(Document document, String description, XdsContentCode contentCode, List<XdrIntendedRecipient> intendedRecipients) throws DocumentException {
        //TODO Decide if all valid submits should be done before signalling an error
        if (document == null)
            throw new DocumentException("Invalid document to submit");

        // could choose consumer based on intended recipients?
        for (XdrDocumentSource consumer: xdrConsumers) {
            consumer.submitDocument(document, description, contentCode, intendedRecipients);
        }
    }

    /**
     * Submit a set of new IHE documents to the document repository(s).
     *
     * @param documents The collection of documents to submit, metadata and content
     * @param description Description of this document set
     * @param contentCode The content code for this document set
     * @throws DocumentException When there is a problem submitting the documents
     */
    public void submitDocuments(Collection<Document> documents, String description, XdsContentCode contentCode) throws DocumentException {
        //TODO Decide if all valid submits should be done before signalling an error
        if (documents == null || documents.size() == 0)
            throw new DocumentException("Invalid document to submit");

        for (IDocumentConsumer consumer: consumers) {
            consumer.submitDocuments(documents, description, contentCode); 
        }
    }

    /**
     * Send a new IHE document to the document repository(s).
     *
     * @param documents The complete collection of documents to submit, metadata and content
     * @param description Description of this document
     * @param contentCode The content code for this document
     * @throws DocumentException When there is a problem submitting the documents
     */
    public void sendXdrDocuments(Collection<Document> documents, String description, XdsContentCode contentCode, List<XdrIntendedRecipient> intendedRecipients) throws DocumentException {
        //TODO Decide if all valid submits should be done before signalling an error
        if (documents == null || documents.size() == 0)
            throw new DocumentException("Invalid document to submit");
        // could choose consumer based on intended recipients?
        for (XdrDocumentSource consumer: xdrConsumers) {
            consumer.submitDocuments(documents, description, contentCode, intendedRecipients);
        }
    }
    
    /**
     * Send a new IHE document to the document repository(s).
     *
     * @param document The complete document to submit, metadata and content
     * @param description Description of this document
     * @param contentCode The content code for this document
     * @throws DocumentException When there is a problem submitting the documents
     */
    public void sendXdmDocument(Document document, String description, XdsContentCode contentCode, String directory, String mediaDescription) throws DocumentException {
        //TODO Decide if all valid submits should be done before signalling an error
        if (document == null)
            throw new DocumentException("Invalid document to submit");

        // could choose consumer based on intended recipients?
        for (XdmDocumentSource consumer: xdmConsumers) {
            consumer.submitDocument(document, description, contentCode, directory, mediaDescription);
        }
    }
    
    /**
     * Send a new IHE document to the document repository(s).
     *
     * @param documents The complete collection of documents to submit, metadata and content
     * @param description Description of this document
     * @param contentCode The content code for this document
     * @throws DocumentException When there is a problem submitting the documents
     */
    public void sendXdmDocuments(Collection<Document> documents, String description, XdsContentCode contentCode, String directory, String mediaDescription) throws DocumentException {
        if (documents == null || documents.size() == 0)
            throw new DocumentException("Invalid document to submit");
        for (XdmDocumentSource consumer: xdmConsumers) {
            consumer.submitDocuments(documents, description, contentCode, directory, mediaDescription);
        }
    }

    /**
     * Hack to allow outside apps to set test patient id in all XDS
     * sources and consumers.
     *
     * @param patientId The fully qualified id of the patient to set.
     */
    public void setTestPatientId(String patientId) {
    	for (IDocumentConsumer consumer: consumers) {
    		if (consumer instanceof XdsDocumentActor) {
    			((XdsDocumentActor) consumer).setTestPatientId(patientId);
    		}
    	}
    	for (IDocumentSource source: sources) {
    		if (source instanceof XdsDocumentActor) {
    			((XdsDocumentActor) source).setTestPatientId(patientId);
    		}
    	}
    }

}
