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
package com.misyshealthcare.connect.base.clinicaldata;

import java.util.Date;

/**
 * This class links a visit to a specific document via the document's id.  
 * In addition, it contains additional information about the document 
 * which may be used to describe the document before retrieving it.
 * 
 * @author : Josh Flachsbart
 * @version : 1.0  Apr 9, 2007
 */
public class VisitDocumentDescriptor {
    private String documentId;
    private String documentTitle;
    private String documentAuthor;
    private Date   documentCreationDate;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentAuthor() {
        return documentAuthor;
    }

    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    public Date getDocumentCreationDate() {
        return documentCreationDate;
    }

    public void setDocumentCreationDate(Date documentCreationDate) {
        this.documentCreationDate = documentCreationDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VisitDocumentDescriptor that = (VisitDocumentDescriptor) o;

        if (!documentId.equals(that.documentId)) return false;

        return true;
    }

    public int hashCode() {
        return documentId.hashCode();
    }
}
