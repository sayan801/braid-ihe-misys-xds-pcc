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

import org.apache.log4j.Logger;

import java.net.ServerSocket;
import java.io.IOException;

/** An abstract implementation of IServerConnection which does a number of items required by all connections. <p />
 *
 * To make a new type of connection which requires these features simply extend this class
 * and implement the additionally required features.  Remember that the connect call is
 * where the socket (or other connection type) should be made.
 *
 * @author Wenzhi Li
 * @version 2.0, May 9, 2007
 */
public abstract class GenericServerConnection implements IServerConnection {
    /** The actual connection. */
    protected ServerSocket ssocket = null;
    /** The description of the connection. This includes everything needed to connect. */
    protected IConnectionDescription description = null;
    /** Package level logger for debugging only. */
    protected static final Logger LOG = Logger.getLogger(GenericServerConnection.class.getPackage().getName());

    public GenericServerConnection(IConnectionDescription connectionDescription) {
        description = connectionDescription;
    }

    /* (non-Javadoc)
     * @see com.misyshealthcare.connect.net.IConnection#getConnectionDescription()
     */
    public IConnectionDescription getConnectionDescription() {
        return description;
    }

    /* (non-Javadoc)
     * @see com.misyshealthcare.connect.net.IServerConnection#isServerConnectionValid()
     */
    public boolean isServerConnectionValid() {
        boolean isValid = false;
        if (ssocket != null) isValid = ssocket.isBound();
        return isValid;
    }

    /* (non-Javadoc)
     * @see com.misyshealthcare.connect.net.IConnection#getSocket()
     */
    public ServerSocket getServerSocket() {
        ServerSocket returnVal = null;
        if (isServerConnectionValid()) returnVal = ssocket;
        // TODO add logging message.
        return returnVal;
    }

    /* (non-Javadoc)
     * @see com.misyshealthcare.connect.net.IConnection#closeConnection()
     */
    public void closeServerConnection() {
        if (isServerConnectionValid()) {
            try { ssocket.close(); }
            catch (IOException e) { ; } // TODO add logging message.
            // TODO add ATNA message?
        }
        ssocket = null;
    }

    /** This function must be instantiated by the subclasses
     * because it generates all the actual server sockets when the
     * connection is made.*/
    public abstract void connect();

}
