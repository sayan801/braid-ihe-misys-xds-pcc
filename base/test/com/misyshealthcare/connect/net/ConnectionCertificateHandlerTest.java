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

import java.net.URL;
import java.security.KeyStore;
import java.util.Enumeration;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import com.misyshealthcare.connect.net.ConnectionCertificateHandler;

import junit.framework.TestCase;

public class ConnectionCertificateHandlerTest extends TestCase {

	private static final Logger LOG = Logger.getLogger("com.misyshealthcare.connect.net");
	
	private static final String keystoreFileURL = ConnectionCertificateHandlerTest.class.getResource("/certs/keystore.jks").toString();
	private static final String truststoreFileURL = ConnectionCertificateHandlerTest.class.getResource("/certs/truststore.jks").toString();
	
	/*
	 * Test method for 'com.misyshealthcare.connect.net.ConnectionCertificateHandler.createKeyStore(URL, String)'
	 */
	public void testCreateKeyStore() {
		LOG.setLevel(Level.ALL);

		try {
			KeyStore ks = ConnectionCertificateHandler.createKeyStore(new URL(keystoreFileURL), "password");
			Enumeration<String> aliases = ks.aliases();
			super.assertEquals(aliases.nextElement(), "test");
			ConnectionCertificateHandler.printKeyCertificates(ks);
			// TODO put more tests in here.
		} catch (Exception e) { LOG.error("Problem in unit test.", e); }
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.ConnectionCertificateHandler.createKeyManagers(KeyStore, String)'
	 */
	public void testCreateKeyManagers() {
		try {
			KeyStore ks = ConnectionCertificateHandler.createKeyStore(new URL(keystoreFileURL), "password");
			KeyManager[] kms = ConnectionCertificateHandler.createKeyManagers(ks, "password");
			super.assertEquals(kms.length, 1);
			// TODO put more tests in here.
		} catch (Exception e) { LOG.error("Problem in unit test.", e); }
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.ConnectionCertificateHandler.createTrustManagers(KeyStore)'
	 */
	public void testCreateTrustManagers() {
		try {
			KeyStore ts = ConnectionCertificateHandler.createKeyStore(new URL(truststoreFileURL), "password");
			ConnectionCertificateHandler.printTrustCerts(ts);
			TrustManager[] tms = ConnectionCertificateHandler.createTrustManagers(ts, null);
			super.assertEquals(tms.length, 1);
			// TODO put more tests in here.
		} catch (Exception e) { LOG.error("Problem in unit test.", e); }
	}

}
