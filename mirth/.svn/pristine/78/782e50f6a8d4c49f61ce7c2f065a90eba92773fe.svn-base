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
package com.misyshealthcare.connect.net;

import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.SecureConnectionDescription;

import java.net.URL;
import junit.framework.TestCase;

public class SecureConnectionDescriptionTest extends TestCase {
	protected SecureConnectionDescription scd = null;
	protected static final String name = "The name";
	protected static final int port = 1234;
	protected static final String keyLoc = "The key loc";
	protected static final String keyPass = "The key pass";
	protected static final String trustLoc = "The trust loc";
	protected static final String trustPass = "The trust pass";
	
	private static final String keystoreFile = SecureConnectionDescriptionTest.class.getResource("/certs/keystore.jks").getPath();
	private static final String truststoreFile = SecureConnectionDescriptionTest.class.getResource("/certs/truststore.jks").getPath();

	private static final String testConfigFile = "testdata/testConfig.xml";


	protected void setUp() throws Exception {
		super.setUp();
 
		scd = new SecureConnectionDescription();
		scd.setHostname(name);
		scd.setPort(port);
		scd.setTrustStore(trustLoc);
		scd.setTrustStorePassword(trustPass);
		scd.setKeyStore(keyLoc);
		scd.setKeyStorePassword(keyPass);
		scd.complete();
	}
	
	/** Test method to see whether the factory works. */
	public void testGetWithFactory() {
		String file = SecureConnectionDescriptionTest.class.getResource(testConfigFile).getPath();
		ConnectionFactory.loadConnectionDescriptionsFromFile(file);
		IConnectionDescription cd = ConnectionFactory.getConnectionDescription("Secure");
		super.assertTrue(cd instanceof SecureConnectionDescription);
		SecureConnectionDescription scd = (SecureConnectionDescription) cd;
		super.assertEquals(scd.getKeyStorePassword(), "password");
		super.assertEquals(scd.getTrustStorePassword(), "password");
		URL loc = scd.getKeyStore();
		super.assertNotNull(loc);
		super.assertEquals(loc.getProtocol(), "file");
		loc = scd.getTrustStore();
		super.assertNotNull(loc);
		super.assertEquals(loc.getProtocol(), "file");
		super.assertEquals(cd.getHostname(), "127.0.0.1");
		super.assertEquals(cd.getPort(), 5001);
		super.assertEquals(cd.getUrlPath(), "/index.html");
		super.assertTrue(cd.isSecure());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.SecureConnectionDescription.SecureConnectionDescription(String, int, String, String, String)'
	 */
	public void testSecureConnectionDescriptionStringIntStringStringString() {
		scd = new SecureConnectionDescription();
		scd.setHostname(name);
		scd.setPort(port);
		scd.setTrustStore(trustLoc);
		scd.setTrustStorePassword(trustPass);
		scd.complete();
		testIsSecure();
		testGetTrustStore();
		testGetTrustStorePassword();
		super.assertNull(scd.getKeyStore());
		super.assertNull(scd.getKeyStorePassword());
		testGetHostname();
		testGetPort();
	}
		
	/*
	 * Test method for 'com.misyshealthcare.connect.net.SecureConnectionDescription.isSecure()'
	 */
	public void testIsSecure() {
		super.assertTrue(scd.isSecure());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.SecureConnectionDescription.getKeyStore()'
	 */
	public void testGetKeyStore() {
		URL loc = scd.getKeyStore();
		super.assertNotNull(loc);
		super.assertEquals(loc.getPath(), keyLoc);
		super.assertEquals(loc.getProtocol(), "file");
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.SecureConnectionDescription.getTrustStore()'
	 */
	public void testGetTrustStore() {
		URL loc = scd.getTrustStore();
		super.assertNotNull(loc);
		super.assertEquals(loc.getPath(), trustLoc);
		super.assertEquals(loc.getProtocol(), "file");
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.SecureConnectionDescription.getKeyStorePassword()'
	 */
	public void testGetKeyStorePassword() {
		super.assertEquals(scd.getKeyStorePassword(), keyPass);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.SecureConnectionDescription.getTrustStorePassword()'
	 */
	public void testGetTrustStorePassword() {
		super.assertEquals(scd.getTrustStorePassword(), trustPass);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.StandardConnectionDescription.getHostname()'
	 */
	public void testGetHostname() {
		super.assertEquals(scd.getHostname(), name);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.StandardConnectionDescription.getPort()'
	 */
	public void testGetPort() {
		super.assertEquals(scd.getPort(), port);
	}

}
