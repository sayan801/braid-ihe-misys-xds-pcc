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

import com.misyshealthcare.connect.util.OID.OidSource;

/**
 * A memory-based OID Source which increments a unique id, but will not be unique on jvm restart. Not meant
 * for production use, but for testing.
 * 
 * @author Michael Traum
 * @version 2.0, 6/8/2007
 */
public class MemoryOidSource implements OidSource {

    private static MemoryOidSource ourInstance = null;
    
    private int id;
    
	public static synchronized MemoryOidSource getInstance() {
		if (ourInstance == null) {
			ourInstance = new MemoryOidSource();
		}
		return ourInstance;
	}
	
    private MemoryOidSource() {
        id = 1;
    }
    
    public static synchronized String getId() {
        if(ourInstance == null)
            ourInstance = new MemoryOidSource();
        return ourInstance.generateId();

    }
    
    /**
     * Generate a unique id to be used as the id part of an OID.
     *
     * @return the id
     */
    public synchronized String generateId() {
        return Long.toString( System.currentTimeMillis() );
    }
}
