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

import junit.framework.TestCase;
import com.misyshealthcare.connect.net.*;
import java.io.*;


/** These tests generate a socket server and test the connections.
 * 
 * This covers using the secure and nonsecure connection and using 
 * the factory to generate the connections.
 *
 * @author Josh Flachsbart
 * @version 2.0 - Oct 25, 2005
 */
public class SocketConnectionTest extends TestCase {
	TestServer server = null;

	IConnection connection = null;
	BufferedReader reader = null;
	PrintStream writer = null;
	
	String greeting = "I am a server";
	String reply = "I got your message";
	
	private static final String keystoreFile = SocketConnectionTest.class.getResource("/certs/keystore.jks").getPath();
	private static final String truststoreFile = SocketConnectionTest.class.getResource("/certs/truststore.jks").getPath();
	private static final String pkcs12File = SocketConnectionTest.class.getResource("/certs/test.p12").getPath();


	/** Use to generate a standard socket server. */
	private void startServer(int portNumber) {
		server = new TestServer(greeting, reply, portNumber);
		server.start();	
		try { Thread.sleep(200); } catch (Exception e) { System.out.println("I woke up early: " + e); }
	}
	
	/** Use to generate a secure socket server.
	 * @param portNumber Port to listen on.
	 * @param keyStore Store to use to authenticate server with client.
	 * @param trustStore Store to use to have server trust client. 
	 */
	private void startSslServer(int portNumber, String keyStore, String trustStore) {
		server = new TestSslServer(greeting, reply, portNumber, keyStore, trustStore);
		server.start();
		try { Thread.sleep(300); } catch (Exception e) { System.out.println("I woke up early: " + e); }
	}
	
	/** Call to fill the class with the required stream connections.  Also tests the initial state of the connection. */
	private void buildStreams() {
		super.assertNotNull("Error getting connection from factory", connection);
		// Why doesn't this work...????
		//super.assertTrue("Connection needs to be made before building streams", connection.isConnectionValid());
		InputStream is = connection.getInputStream();
		super.assertNotNull("Error getting input stream from connection", is);
		reader = new BufferedReader(new InputStreamReader(is));
		super.assertNotNull(reader);
		OutputStream os = connection.getOutputStream();
		super.assertNotNull("Error getting output stream from connection", os);
		writer = new PrintStream(os);
		super.assertNotNull(writer);
	}
	
	/** Reads a line from the server and returns it as a string. */
	private String readFromServer() {
		String readString = "";
		try { readString = reader.readLine(); }
		catch (IOException e) { super.fail("Error reading from server: "+ e); }
		return readString;
	}
	
	/** Bad connections should fail gracefully. */
	public void testGetBadConnection() {
		IConnectionDescription description = 
			ConnectionFactory.getConnectionDescription("Bad Connection Name");
		
		super.assertNull("Bad connection descriptions shouldn't be valid.", description);
	}

	/** Try the standard connection. */
	public void testSimpleConnection() {
		startServer(5555);
		StandardConnectionDescription description = new StandardConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5555);
		description.complete();
		StandardConnection sc = new StandardConnection(description);
		sc.connect();
		connection = sc;
		buildStreams();

		super.assertEquals("Greeting recieved from server", readFromServer(), greeting);
		writer.println("Simple client message");
		super.assertEquals("Greeting recieved from server", readFromServer(), reply);
		try {
			reader.close();
			writer.close();
		} catch (IOException e) { System.out.println("Error closing streams: " + e); }
	}
	
	/** Try the standard connection. */
	public void testSslConnection() {
		startSslServer(5556, keystoreFile, truststoreFile);
		SecureConnectionDescription description = new SecureConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5556);
		description.setTrustStore(truststoreFile);
		description.setTrustStorePassword("password");
		description.setKeyStore(keystoreFile);
		description.setKeyStorePassword("password");
		description.complete();
		SecureConnection sc = new SecureConnection(description);
		sc.connect();
		connection = sc;
		buildStreams();

		super.assertEquals("Greeting recieved from server", readFromServer(), greeting);
		writer.println("Secure client message");
		super.assertEquals("Greeting recieved from server", readFromServer(), reply);
		try {
			reader.close();
			writer.close();
		} catch (IOException e) { System.out.println("Error closing streams: " + e); }
	}
	
	/** Tests the server and the client. */
	public void testGetStandardConnection() {
		startServer(5557);
		StandardConnectionDescription description = new StandardConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5557);
		description.complete();
		connection = ConnectionFactory.getConnection(description);
		buildStreams();

		super.assertEquals("Greeting recieved from server", readFromServer(), greeting);
		writer.println("Simple client message");
		super.assertEquals("Greeting recieved from server", readFromServer(), reply);
		try {
			reader.close();
			writer.close();
			connection.closeConnection();
		} catch (IOException e) { System.out.println("Error closing streams: " + e); }
	}

	/** Tests the server and the client. */
	public void testGetSecureConnection() {
		startSslServer(5558, keystoreFile, truststoreFile);
		SecureConnectionDescription description = new SecureConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5558);
		description.setTrustStore(truststoreFile);
		description.setTrustStorePassword("password");
		description.setKeyStore(keystoreFile);
		description.setKeyStorePassword("password");
		description.complete();
		connection = ConnectionFactory.getConnection(description);
		buildStreams();

		super.assertEquals("Greeting recieved from server", readFromServer(), greeting);
		writer.println("Secure client message");
		super.assertEquals("Greeting recieved from server", readFromServer(), reply);
		try {
			reader.close();
			writer.close();
			connection.closeConnection();
		} catch (IOException e) { System.out.println("Error closing streams: " + e); }
	}

	/** Tests the server and the client. */
	public void testGetSecurePcks12Connection() {
		startSslServer(5559, pkcs12File, pkcs12File);
		SecureConnectionDescription description = new SecureConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5559);
		description.setTrustStore(pkcs12File);
		description.setTrustStorePassword("password");
		description.setKeyStore(pkcs12File);
		description.setKeyStorePassword("password");
		description.complete();
		connection = ConnectionFactory.getConnection(description);
		buildStreams();

		super.assertEquals("Greeting recieved from server", readFromServer(), greeting);
		writer.println("Secure client message");
		super.assertEquals("Greeting recieved from server", readFromServer(), reply);
		try {
			reader.close();
			writer.close();
			connection.closeConnection();
		} catch (IOException e) { System.out.println("Error closing streams: " + e); }
	}

	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl:handshake:verbose");
		SocketConnectionTest cft = new SocketConnectionTest();
		cft.testSslConnection();
		//cft.testGetStandardConnection();
		//cft.testGetSecureConnection();		
	}
}
