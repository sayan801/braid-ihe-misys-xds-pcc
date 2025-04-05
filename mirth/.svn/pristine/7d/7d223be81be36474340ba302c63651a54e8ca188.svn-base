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
package com.misyshealthcare.connect.base.codemapping;

/**
 * The interface defines the functionality of a code mapping manager object.
 * 
 *
 * @author Michael Traum
 * @version 2.0, Apr 4, 2007
 *
 */
public interface ICodeMappingManager {
	
	/**
	 * Get an external system mapping.
	 * 
	 * @param category the mapping category
	 * @param fromSystemId the system ID to map from
	 * @param toSystemId the system ID to map to
	 * @param value the value to map
	 * @return the Mapping value, or null if none found
	 */
	public String getExternalMappingFromCode(CategoryEnum category, String fromSystemId, String toSystemId, String value);

}
