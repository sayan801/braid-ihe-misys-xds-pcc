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

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.codemapping.CategoryEnum;
import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;

/**
 * Test the CodeMappingManager
 *
 * @author Michael Traum
 * @version 2.0, Mar 27, 2007
 *
 */
public class CodeMappingTest extends TestCase {

	/*
	 * Test a valid mapping
	 */
	public void testValidMapping() {
		CodeMappingManager cmm = CodeMappingManager.getInstance();
		String value = cmm.getExternalMappingFromCode(CategoryEnum.Gender, "test", "ihe", "Male");
		assertEquals("M", value);
	}
	
	/*
	 * Test a valid mapping
	 */
	public void testInvalidMapping() {
		CodeMappingManager cmm = CodeMappingManager.getInstance();
		String value = cmm.getExternalMappingFromCode(CategoryEnum.Gender, "test", "ihe", "Neutral");
		assertEquals("Neutral", value);
	}

}
