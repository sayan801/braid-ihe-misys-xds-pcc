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

import java.net.ServerSocket;
import java.io.IOException;

import org.apache.log4j.Logger;

/** A standard non encrypted tcp server connection.
 *
 * This should not be created directly but rather, requested from the ConnectionFactory.
 *
 * @author Wenzhi Li
 * @version 2.0, May 9, 2007
 */
public class StandardServerConnection extends GenericServerConnection {
	private static final Logger LOG = Logger.getLogger(StandardServerConnection.class.getName());

	/** Used by the factory to create a connection. */
	public StandardServerConnection(IConnectionDescription connectionDescription) {
		super(connectionDescription);
	}

	/** Used by factory to start the connection. */
	public void connect() {
		try {
			ssocket = new ServerSocket(description.getPort());
            // TODO add ATNA logging message, perhaps in finally?
		} catch (IOException e) {
			LOG.error("Failed to create a server socket on port:" + description.getPort(), e);
			ssocket = null;
		}
	}
}
