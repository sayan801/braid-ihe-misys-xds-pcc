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
package com.misyshealthcare.connect.ihe.registry;

import org.w3c.dom.Element;

/**
 * This class is a wrapper for the rim:ObjectRef registry
 * object.
 * <p>
 * This class simply wraps the XML DOM returned from the
 * registry in query results.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 5, 2005
 */
public class XdsObjectReference {

	private String id = null;
	
	XdsObjectReference(Element xml) {
		id = RimXml.getAttributeValue(xml, "id");
	}
	
	/**
	 * Get the id attribute of this rim:ObjectRef
	 * 
	 * @return The rim:ObjectRef id.
	 */
	public String getId() {
		return id;
	}
	
}
