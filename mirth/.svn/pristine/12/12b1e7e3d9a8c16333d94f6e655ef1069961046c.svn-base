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


package mesatests.atna;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import mesatests.MesaTestLogger;

import com.misyshealthcare.connect.base.AuditBroker;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnection;
import com.misyshealthcare.connect.net.IConnectionDescription;

/** Test rig for MESA test 11143.
 * 
 * This tests the basic TLS connectivity.
 * 
 * @see ConnectionFactory
 * @author Josh Flachsbart
 * @version 2.0 - Nov 09, 2005
 */
public class Test11143 {

	/**
	 * Run MESA Test
	 */
	public static void main(String[] args) {
		String test = "11143";
		MesaTestLogger logger = new MesaTestLogger(System.out);
		logger.writeTestBegin(test);

		// -------  TEST SETUP  --------
		ConnectionFactory.loadConnectionDescriptionsFromFile("src/config/connectathon/AuditRepositoryConnections.xml");
		// Set up audit trail.
		ArrayList<IConnectionDescription> repositories = new ArrayList<IConnectionDescription>();
		repositories.add(ConnectionFactory.getConnectionDescription("log4j_audittrail"));
		repositories.add(ConnectionFactory.getConnectionDescription("mesa_arr_bsd"));
		AuditBroker broker = AuditBroker.getInstance();
		broker.registerAuditSource(new IheAuditTrail("SecureNode", repositories));
		
		IConnectionDescription scd = ConnectionFactory.getConnectionDescription("MISYS-SECURE-3");
		// -------  TEST  --------		
		IConnection secureConnection = ConnectionFactory.getConnection(scd);
		OutputStream os = secureConnection.getOutputStream();
		if (os == null) return;
		PrintStream writer = new PrintStream(os);
		if (writer == null) return;
		writer.println("Hello world");
		writer.flush();
	    if (writer.checkError()) System.out.println("SSLSocketClient: java.io.PrintWriter error");
		secureConnection.closeConnection();
		// -------  END TEST  --------
		
		logger.writeTestEnd(test);
	}

}
