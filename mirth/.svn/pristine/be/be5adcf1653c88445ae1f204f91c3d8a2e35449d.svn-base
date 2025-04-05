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

import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Date;

/**
 * This class holds a collection of documents that all come from
 * the same facility.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 28, 2005
 */
public class DocumentSet implements Iterable {

	/* The "facility" that all the documents share */
	public String facility = null;
	
	/* The documents in the set */
	public List<Document> documents = null;
	
	/**
	 * Create a new, empty document set.  Documents are
	 * added to this set using the 'add' method.
	 * @see DocumentSet#add
	 */
	public DocumentSet() {
		this(null);
	}
	
	/**
	 * Create a new document set and add the supplied document.
	 * Additional documents are added to this set using the 'add' method.
	 *
	 * @param document
	 * @see DocumentSet#add
	 */
	public DocumentSet(Document document) {
		documents = new Vector<Document>();
		if (document != null) add(document);
	}
	
	/* --------------------------------------------------- */
	/* Enforce all documents be for the same facility      */
	
	/**
	 * @return Returns the facility.
	 */
	public String getFacility() {
		return facility;
	}

	/**
	 * @param facility The facility to set.
	 */
	public void setFacility(String facility) {
		this.facility = facility;
	}

	/**
	 * Add a new document to this set.  Keep the documents sorted
	 * according to the criteria defined in the DocumentBroker.
	 * 
	 * @param document The document to add to this set
	 * @return True if this document could be added, false if the document being added is not from the same facility
	 * @see com.misyshealthcare.connect.base.DocumentSet#insertDocumentIntoList(List, Document)
	 */
	public boolean add(Document document) {
        if (facility == null) {
            List<AuthorDescriptor> ads = document.getAuthorDescriptors();
            for (AuthorDescriptor ad : ads) {
                List<String> institutions = ad.getAuthorInstitutions();
                for (String institution : institutions) {
                    //grab the first facility
                    facility = institution;
                    DocumentSet.insertDocumentIntoList(documents, document);
                    return true;
                }
            }
        }

        //Verfy facility is matched
        boolean found = false;
        List<AuthorDescriptor> ads = document.getAuthorDescriptors();
   ONE: for (AuthorDescriptor ad : ads) {
            List<String> institutions = ad.getAuthorInstitutions();
            for (String institution : institutions) {
                if (facility.equals(institution)) {
                    found = true;
                    break ONE;
                }

            }
        }
        if (found) {
            DocumentSet.insertDocumentIntoList(documents, document);
            return true;
        } else
            return false;
	}

    /**
     * Sort a new document into a document list.  Sort the list with
     * the newest documents first.  Documents with no creation time get
     * added to the end.  Duplicate documents are not added.
     *
     * @param documents The current sorted list of documents
     * @param document The new document to sort into the list
     * @return The new list of sorted documents
     */
    static List<Document> insertDocumentIntoList(List<Document> documents, Document document) {
        // First, see if this document is already in the list
        String globalId = document.getUniqueId();
        if (globalId != null) {
            for (Document doc: documents) {
                if (globalId.equalsIgnoreCase(doc.getUniqueId())) {
                    // It is already in the list, just ignore it
                    return documents;
                }
            }
        }
        // We need to add it, sort it in based on creation time, newest first
        Date documentTime = document.getCreationTime();
        if (documentTime == null) {
            // Don't know time, just add to the end
            documents.add(document);
        } else {
            boolean found = false;
            int i = 0;
            while (i < documents.size()) {
                // Find the right place to put this document
                Date listTime = documents.get(i).getCreationTime();
                if (listTime == null) {
                    found = true;
                    break;
                } else if (listTime.before(documentTime)) {
                    found = true;
                    break;
                }
                i = i + 1;
            }
            // Squeeze it in
            if (found && (i <= documents.size())) {
                documents.add(i, document);
            } else {
                documents.add(document);
            }
        }
        return documents;
    }

  /* --------------------------------------------------- */
  /* Enable this object to act as a collection iterator  */
	
	/**
	 * @return The number of documents in this set
	 */
	public int size() {
		return documents.size();
	}
	
	/**
	 * @return True if there are no documents in this set, False otherwise
	 */
	public boolean isEmpty() {
		return documents.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator iterator() {
		return documents.iterator();
	}


}
