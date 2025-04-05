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
package com.misyshealthcare.connect.ihe;

import java.io.File;
import java.util.Collection;

import com.misyshealthcare.connect.base.AuditBroker;
import com.misyshealthcare.connect.base.DocumentBroker;
import com.misyshealthcare.connect.base.PatientBroker;
import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.configuration.IheActorDescription;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.util.LibraryConfig.ILogContext;
import com.misyshealthcare.connect.util.OID;

import junit.framework.TestCase;

public class ConfigurationLoaderTest extends TestCase {
	private static final String testIheConfigFile = "testdata/testIheConfiguration.xml";

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader.ConfigurationLoader(String)'
	 */
	public void testConfigurationLoaderString() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader.ConfigurationLoader(File)'
	 */
	public void testConfigurationLoaderFile() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader.loadConfiguration()'
	 */
	public void testLoadConfiguration() {
		// Try a couple of error cases
		loadConfigFile(null, false);
		loadConfigFile("src" + File.separator + "doesntExist.xml", false);
		// Try a good case
		loadConfigFile(ConfigurationLoaderTest.class.getResource(testIheConfigFile).getPath(), true);
		DocumentBroker documentBroker = DocumentBroker.getInstance();
		assertEquals(1, documentBroker.documentSourceCount());
		assertEquals(3, documentBroker.documentConsumerCount());
		PatientBroker patientBroker = PatientBroker.getInstance();
		assertEquals(1, patientBroker.patientSourceCount());
		assertEquals(1, patientBroker.patientConsumerCount());
		assertEquals(1, patientBroker.patientXrefCount());
		AuditBroker auditBroker = AuditBroker.getInstance();
		assertEquals(1, auditBroker.auditConsumerCount());
		// Try loading only the actors
		ConfigurationLoader loader = loadConfigActors(ConfigurationLoaderTest.class.getResource(testIheConfigFile).getPath());
		Collection<IheActorDescription> actors = loader.getActorDescriptions();
		assertEquals(8, actors.size());
		boolean found = false;
		for (IheActorDescription actor: actors) {
			if (actor.getId().equalsIgnoreCase("PDQ-1")) {
				found = true;
				assertEquals("Test PDQ Server 127.0.0.1:5000", actor.getDescription());
				assertTrue(actor.isInstalled());
			}
		}
		assertTrue(found);
		// A quick connectathon test
//		loader = loadConfigActors("connectathon/IheActors.xml");
//		actors = loader.getActorDescriptions();
//		assertEquals(43, actors.size());
//		for (IheActorDescription actor: actors) System.out.println(actor.getDescription());
		// Another test
//	loader = loadConfigActors("testIheActors.xml");
//	actors = loader.getActorDescriptions();
//	assertEquals(1, actors.size());
//	IConnectionDescription conn = ConnectionFactory.getConnectionDescription("nist_query");
//	assertNotNull(conn);
//	assertEquals("b8721bf0e3a342b^^^&1.3.6.1.4.1.21367.2005.3.7&ISO", conn.getProperty("TestPatientId"));
	}
	
	private void loadConfigFile(String name, boolean isOkay) {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		try {
			if (isOkay) {
				assertTrue(loader.loadConfiguration(name, "2.16.840.1.113883.3.65.2", new OidMock(),  null, null, CodeMappingManager.getInstance(), new TestLogContext()));
			} else {
				assertFalse(loader.loadConfiguration(name, "2.16.840.1.113883.3.65.2", new OidMock(), null, null, CodeMappingManager.getInstance(), new TestLogContext()));
			}
		} catch (IheConfigurationException e) {
			if (isOkay) {
				System.out.println(e.toString());
				fail("Should be no error loading configuration file '" + name + "'");
			}
		}
	}

	private ConfigurationLoader loadConfigActors(String name) {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		try {
			assertTrue(loader.loadConfiguration(name, false, "2.16.840.1.113883.3.65.2", new OidMock(), null, null, CodeMappingManager.getInstance(), new TestLogContext()));
		} catch (IheConfigurationException e) {
			System.out.println(e.toString());
			fail("Should be no error loading configuration file '" + name + "'");
		}
		return loader;
	}

    public class OidMock implements OID.OidSource {
        public synchronized String generateId() {
            return Long.toString( System.currentTimeMillis() );
        }
    }
}
