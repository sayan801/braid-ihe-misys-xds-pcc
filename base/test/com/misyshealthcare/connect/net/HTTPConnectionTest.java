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

import java.io.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.methods.GetMethod;

import com.misyshealthcare.connect.net.*;

import junit.framework.TestCase;

/** These tests generate a fake web server and test the web 
 * server connections.
 * 
 * This covers using the secure and nonsecure connection, and 
 * using the factory to generate the connections.
 *
 * @author Josh Flachsbart
 * @version 2.0 - Nov 2, 2005
 */
public class HTTPConnectionTest extends TestCase {
	TestServer server = null;
	BufferedReader reader = null;
	PrintStream writer = null;
	

	String code = "HTTP/1.1 200 OK";
	String date = "Date: Wed, 02 Nov 2005 19:35:16 GMT";
	String sType = "Server: Apache/1.3.33 (Darwin) PHP/4.3.11";
	String lastMod = "Last-Modified: Thu, 03 Nov 2005 00:57:17 GMT";
	String eTag = "ETag: \"4c9981-c8-4369606d\"";
	String xTag = "X-Powered-By: PHP/4.3.11";
	String xfer = "Accept-Ranges: bytes";
	String type = "Content-Type: text/html";
	String html = 
		"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
		"<html>\n<head>\n<title>My Great Page</title>\n</head>\n" +
		"<body>\nNo, really.  \n\nIt's great\n</body>\n</html>\n";
	String length = "Content-Length: " + html.length();

	String greeting = null;
	String message = "GET /index.html HTTP/1.1\nhost: localhost.com\n\n";
	String reply = code + "\n" + date + "\n" + sType + "\n" + lastMod + "\n" + length + "\n" + type + "\n\n" + html;
	
	private static final String keystoreFile = HTTPConnectionTest.class.getResource("/certs/keystore.jks").getPath();
	private static final String truststoreFile = HTTPConnectionTest.class.getResource("/certs/truststore.jks").getPath();
	private static final String pkcs12File = HTTPConnectionTest.class.getResource("/certs/test.p12").getPath();
		
	/**	Used to start a non secure http server.
	 *
	 * Sends member "greeting" on connect, sends
	 * "reply" on receipt of message.  Greeting 
	 * should be null for a web server.
	 * @param portNumber Port to run the server on.
	 */
	private void startServer(int portNumber) {
		server = new TestServer(greeting, reply, portNumber);
		server.start();	
		try { Thread.sleep(200); } catch (Exception e) { System.out.println("I woke up early: " + e); }
	}
	
	/**	Used to start a secure http server.
	 *
	 * Sends member "greeting" on connect, sends
	 * "reply" on receipt of message.  Greeting 
	 * should be null for a web server.
	 * @param portNumber Port to run the server on.
	 * @param keyStore Key store to use for server verification.
	 * @param trustStore Trust store to use for client verification.
	 */
	private void startSslServer(int portNumber, String keyStore, String trustStore) {
		server = new TestSslServer(greeting, reply, portNumber, keyStore, trustStore);
		server.start();
		try { Thread.sleep(300); } catch (Exception e) { System.out.println("I woke up early: " + e); }
	}
	
	private void buildStreams(IConnection connection) {
		super.assertNotNull("Error getting connection from factory", connection);
		// There is some sort of timing issue here:
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
		
	/**
	 * Test method for making sure the test server works as expected.
	 */
	public final void testHttpServer() {
		startServer(5655);
		StandardConnectionDescription description = new StandardConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5655);
		description.complete();
		StandardConnection sc = new StandardConnection(description);
		sc.connect();
		buildStreams(sc);
		
		try {
			writer.println(message);
			String buffer;
			String collected;
			buffer = reader.readLine();
			super.assertEquals(buffer, code);
			collected = buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, date);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, sType);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, lastMod);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, length);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, type);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer.length(), 0);
			collected += "\n";
			
			while (reader.ready()) {
				buffer = reader.readLine();
				collected += buffer + "\n";
			}
			super.assertEquals(collected.trim(), reply.trim());
			sc.closeConnection();
			
		} catch (IOException e) { super.fail("Error communicating with server: " + e); }
	}

	/**
	 * Test method for making sure the test server works as expected.
	 */
	public final void testSecureHttpServer() {
		startSslServer(5656, keystoreFile, truststoreFile);
		SecureConnectionDescription description = new SecureConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5656);
		description.setTrustStore(truststoreFile);
		description.setTrustStorePassword("password");
		description.setKeyStore(keystoreFile);
		description.setKeyStorePassword("password");
		description.complete();
		SecureConnection sc = new SecureConnection(description);
		sc.connect();
		buildStreams(sc);
		
		try {
			writer.println(message);
			String buffer;
			String collected;
			buffer = reader.readLine();
			super.assertEquals(buffer, code);
			collected = buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, date);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, sType);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, lastMod);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, length);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer, type);
			collected += buffer + "\n";
			buffer = reader.readLine();
			super.assertEquals(buffer.length(), 0);
			collected += "\n";
			
			while (reader.ready()) {
				buffer = reader.readLine();
				collected += buffer + "\n";
			}
			super.assertEquals(collected.trim(), reply.trim());
			sc.closeConnection();
			
		} catch (IOException e) { super.fail("Error communicating with server: " + e); }
	}

	/**
	 * Test method for making sure the connection works as expected.
	 */
	public final void testStandardConnect() {
		startServer(5657);
		HttpClient http = new HttpClient();
		HttpMethod get = new GetMethod("http://127.0.0.1:" + 5657 + "/index.html");

		try {
			// Execute the method.
			int statusCode = http.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				super.fail("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			String response = new String(responseBody);
			super.assertEquals(response.trim(), html.trim());

		} catch (IOException e) {
			super.fail("Failed to download file." + e);
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
	}

	/**
	 * Test method for making sure the connection works as expected.
	 */
	public final void testSecureConnect() {
		startSslServer(5658, keystoreFile, truststoreFile);
		HttpMethod get = null;
		try {
			HttpClient http = new HttpClient();
			SecureConnectionDescription scd = new SecureConnectionDescription();
			scd.setKeyStore(keystoreFile);
			scd.setKeyStorePassword("password");
			scd.setTrustStore(truststoreFile);
			scd.setTrustStorePassword("password");
			scd.setHostname("127.0.0.1");
			scd.setPort(5658);
			scd.complete();
			Protocol https = new Protocol("https", new SecureSocketFactory(scd), 5658);
			http.getHostConfiguration().setHost("127.0.0.1", 5658, https);
			get = new GetMethod("/index.html");
			// Execute the method.
			int statusCode = http.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				super.fail("Method failed: " + get.getStatusLine());
			}
			
			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			String response = new String(responseBody);
			super.assertEquals(response.trim(), html.trim());

		} catch (IOException e) {
			super.fail("Failed to download file.\n" + e);
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
	}

	/** 
	 * Testing whether the factory method of getting the httpclient works.
	 */
	public final void testFactoryConnect() {
		startServer(5659);
		StandardConnectionDescription description = new StandardConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5659);
		
		HttpClient http = ConnectionFactory.getHttpConnection(description);
		HttpMethod get = new GetMethod("/index.html");

		try {
			// Execute the method.
			int statusCode = http.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				super.fail("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			String response = new String(responseBody);
			super.assertEquals(response.trim(), html.trim());

		} catch (IOException e) {
			super.fail("Failed to download file. " + e);
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
	}
	
	/** 
	 * Testing whether the factory method of getting the httpclient works.
	 */
	public final void testSecureFactoryConnect() {
		startSslServer(5658, keystoreFile, truststoreFile);
		SecureConnectionDescription description = new SecureConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5658);
		description.setKeyStore(keystoreFile);
		description.setKeyStorePassword("password");
		description.setTrustStore(truststoreFile);
		description.setTrustStorePassword("password");
		description.complete();
		
		HttpClient http = ConnectionFactory.getHttpConnection(description);
		HttpMethod get = new GetMethod("/index.html");

		try {
			// Execute the method.
			int statusCode = http.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				super.fail("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			String response = new String(responseBody);
			super.assertEquals(response.trim(), html.trim());

		} catch (IOException e) {
			super.fail("Failed to download file. " + e);
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
	}
	
	/** 
	 * Testing whether the factory method of getting the httpclient works.
	 */
	public final void testSecurePcks12FactoryConnect() {
		startSslServer(5660, pkcs12File, pkcs12File);
		SecureConnectionDescription description = new SecureConnectionDescription();
		description.setHostname("127.0.0.1");
		description.setPort(5660);
		description.setTrustStore(pkcs12File);
		description.setTrustStorePassword("password");
		description.setKeyStore(pkcs12File);
		description.setKeyStorePassword("password");
		description.complete();
		
		HttpClient http = ConnectionFactory.getHttpConnection(description);
		HttpMethod get = new GetMethod("/index.html");

		try {
			// Execute the method.
			int statusCode = http.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				super.fail("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			String response = new String(responseBody);
			super.assertEquals(response.trim(), html.trim());

		} catch (IOException e) {
			super.fail("Failed to download file. " + e);
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
	}
	
	/** Just for testing. */	
	public static void main(String[] args) {
		HTTPConnectionTest huct = new HTTPConnectionTest();
		huct.testHttpServer();
//		huct.testConnect();
	}
}
