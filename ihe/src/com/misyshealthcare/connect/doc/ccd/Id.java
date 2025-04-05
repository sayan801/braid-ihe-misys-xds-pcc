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

/**
 * Represents an OID used for document builder.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 25, 2005
 */
public class Id { 
    private String root;
    private String extension;

    /**
     * Constructor
     *
     * @param root the root of the OID
     * @param extension the extension of the OID
     */
    public Id(String root, String extension) {
        this.root = root;
        this.extension = extension;
    }

    /**
     * Gets the root of the OID
     *
     * @return the root
     */
    public String getRoot() {
        return this.root;
    }

    /**
     * Gets the extension of the OID
     *
     * @return the extension
     */
    public String getExtension() {
        return this.extension;
    }
}
