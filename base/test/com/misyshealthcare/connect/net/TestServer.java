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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.misyshealthcare.connect.net.SecureConnectionDescription;
import com.misyshealthcare.connect.net.SecureSocketFactory;


/**
 * A fake server for testing the connections.
 * 
 * @author Josh Flachsbart
 * @version 2.0 - Oct 27, 2005
 */
class TestServer extends Thread {
	String greeting, reply;
	int portNumber;
	boolean serverRunning = true;
	ServerSocket ss = null;
			
	TestServer(String greeting, String reply, int portNumber) {
		this.greeting = greeting;
		this.reply = reply;
		this.portNumber = portNumber;
	}

	/** Override to make a different server type. */
	protected void getSocket() {
		boolean worked = true;
		try { ss = new ServerSocket(portNumber); }
		catch (IOException e) { System.out.println("Unable to start server: " + e); worked = false; }
		if (ss == null) worked = false;
		serverRunning = worked;
	}
	
	/** Override to make a different response server. */
	private void processMessage(BufferedReader reader) {
		try { System.out.println("Server read message: " + reader.readLine()); } 
		catch (IOException e) { System.out.println("Server unable to read message: " + e); }
		serverRunning = false;
	}

	public void run() {
		getSocket();
		
		while (serverRunning) {
			BufferedReader reader = null;	    				      
			PrintStream writer = null;
			Socket connection = null;
			try {
				// Listen for connection.  Will block here.
				connection = ss.accept();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				OutputStream out = connection.getOutputStream();
				writer = new PrintStream(out);

				if (greeting != null) writer.println(greeting);
				processMessage(reader);
				if (reply != null) writer.println(reply);
			} catch (IOException e) {
				System.err.println("IO Error reading exception: " + e);
				serverRunning = false;
			} catch (Exception e) {
				System.err.println("Other kind of error: " + e);
				serverRunning = false;
			}
			finally { // ensure close happens
				try {
					sleep(200);
					if (reader != null) reader.close();
					if (writer != null) writer.close();
					if (connection != null) connection.close();
				} catch (IOException e) { System.err.println("Unable to close connection: " + e); } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try { if (ss != null) ss.close(); }
		catch (IOException e) { System.out.println("Unable to stop server: " + e); return; }
	}	
}

/**
 * A secure version of the test server.
 * 
 * @author Josh Flachsbart
 * @version 2.0 - Oct 27, 2005
 */
final class TestSslServer extends TestServer {
	private String keyStoreFile;
	private String trustStoreFile;
	
	TestSslServer(String greeting, String reply, int portNumber, String keyStore, String trustStore) {
		super(greeting, reply, portNumber);
		this.keyStoreFile = keyStore;
		this.trustStoreFile = trustStore;
	}

	/** Override to make a different server type. */
	protected void getSocket() {
		boolean worked = true;
		//System.setProperty("javax.net.ssl.keyStore", keyStore);
		//System.setProperty("javax.net.ssl.keyStorePassword", "password");
		SecureConnectionDescription scd = new SecureConnectionDescription();
		scd.setKeyStore(keyStoreFile);
		scd.setKeyStorePassword("password");
		scd.setTrustStore(trustStoreFile);
		scd.setTrustStorePassword("password");
		scd.setHostname("127.0.0.1");
		scd.setPort(5658);
		scd.complete();
		SecureSocketFactory ssf = new SecureSocketFactory(scd);
		ss = ssf.createServerSocket(portNumber);
		//SSLServerSocketFactory sslSrvFact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		//ss = (SSLServerSocket) sslSrvFact.createServerSocket(portNumber);

		if (ss == null) {worked = false;System.out.println("No server started."); }
		serverRunning = worked;
	}
	
	public static void main(String[] args) {
		TestServer server;
		if (args.length == 1) {
			System.out.println("Starting server as port 5555");
			server = new TestServer(null, "Hello, I am a server", 5555);
		} else {
			System.out.println("Starting secure server as port 5555");
			server = new TestSslServer(null, "Hello, I am a secure server", 5555, "../MesaTests/testkit/certs/mesa_1.p12", "../MesaTests/testkit/certs/mesa_1.p12");
		}
		server.run();
		System.out.println("Stopping server");
	}	
}