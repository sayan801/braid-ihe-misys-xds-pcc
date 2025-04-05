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


package com.misyshealthcare.connect.doc.ccd;

import com.misyshealthcare.connect.base.Document;

/**
 * The interface defines the method for a document builder.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 25, 2005
 * @see ReferralSummaryBuilder
 * @see ScannedDocumentBuilder
 */
public interface IDocBuilder {

    /**
     * Builds a CDA document such as Referral Summary. The method builds not only the document itself,
     * but also retrive the metadata from the document.
     *
     * @return the CCDDocument object to be built.
     * @throws CDAException if it fails to build a document.
     */
    public CCDDocument build() throws CCDException;

    /**
     * Builds a CDA document such as Referral Summary, and output the document to a file. The method
     * builds not only the document itself, but also retrive the metadata from the document.
     *
     * @param fileName the output file for the document to be built.
     * @return the CCDDocument object to be built.
     * @throws CDAException if it fails to build a document.
     */
    public CCDDocument build(String fileName) throws CCDException;
}
