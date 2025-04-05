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

import java.io.InputStream;

/**
 * The data container for a Wrapper PDF(or Text) CDA document
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 22, 2006
 */
public class ScannedDocumentData extends CCDData {
    /**The base64 encoded file content to be wrapped within CDA*/
	protected InputStream fileContent = null;
    /**MediaType of the scanned document  */
    protected MediaType mediaType = null;
    /**Data Enterer*/
    //protected DataEnterer dataEnterer = null;

    public enum MediaType{
        PDF { String getValue(){ return "application/pdf"; }},
        TEXT { String getValue(){ return "text/plain"; }};
        abstract String getValue();
    }

    /**
     * Gets the file to be uploaded, and whose content is to be included in the CDA document. For example, a PDF file,
     * which is used to build a CDA wrapped PDF document.
     *
     * @return the fileContent to be wrapped in this scanned document
     */
    public InputStream getFileContent() {
        return this.fileContent;
    }

    /**
     * Sets the file to be uploaded, and whose content is to be included in the CDA document. For examle, a PDF file,
     * which is used to build a CDA wrapped PDF (or Text) document.
     *
     * @param fileContent the base64 encoded file content to be wrapped in this scanned document.
     */
    public void setFileContent(InputStream fileContent) {
        this.fileContent = fileContent;
    }

    public MediaType getMediaType(){
        return mediaType;
    }
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    /*public DataEnterer getDataEnterer() {
        return this.dataEnterer;
    }
    public void setDataEnterer(DataEnterer dataEnterer) {
        this.dataEnterer = dataEnterer;
    }*/


}


