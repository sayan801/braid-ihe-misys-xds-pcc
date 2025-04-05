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

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit Tests for the classes in the base package.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 24, 2005
 */
public class BaseTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Tests for com.misyshealthcare.connect.base");
		//$JUnit-BEGIN$
		suite.addTestSuite(PhoneNumberTest.class);
		suite.addTestSuite(FormatterTest.class);
		suite.addTestSuite(DocumentTest.class);
		suite.addTestSuite(DocumentSetTest.class);
//		suite.addTestSuite(PatientBrokerTest.class);
		suite.addTestSuite(PatientDescriptorTest.class);
		suite.addTestSuite(PatientIDTest.class);
		suite.addTestSuite(PatientQueryTest.class);
		suite.addTestSuite(DocumentQueryTest.class);
		suite.addTestSuite(DateQueryTest.class);
//		suite.addTestSuite(DocumentBrokerTest.class);
		suite.addTestSuite(PatientDescriptorSetTest.class);
		//$JUnit-END$
		return suite;
	}

}
