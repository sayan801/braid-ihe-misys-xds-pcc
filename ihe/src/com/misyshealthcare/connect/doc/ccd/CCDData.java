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

import org.apache.log4j.Logger;

/**
 * This is the container class that contains all the data used to build a CDA document.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 26, 2005
 */
abstract public class CCDData implements Cloneable {
    protected static final Logger log = Logger.getLogger(CCDData.class);

    protected MetaData metadata = null;
    
    /**
     * Default constructor
     */
    public CCDData() {}

	/**
	 * @return the metadata
	 */
	public MetaData getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}

    
}
