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

import java.util.ArrayList;

import mesatests.MesaTestLogger;

import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;

/** Test rig for MESA test 13540.
 * 
 * This tests the logging of data imports.
 * 
 * @see ConnectionFactory
 * @author Josh Flachsbart
 * @version 1.0 - Nov 29, 2006
 */
public class Test13540 {
	
	/**
	 * Run MESA Test 13540
	 */
	public static void main(String[] args) {
		String test = "13540";
		
		ConnectionFactory.loadConnectionDescriptionsFromFile("testkit/certs/AuditRepositoryConnections.xml");
		ArrayList<IConnectionDescription> repositories = new ArrayList<IConnectionDescription>();
		repositories.add(ConnectionFactory.getConnectionDescription("CAM-11A-LOG4J"));
		repositories.add(ConnectionFactory.getConnectionDescription("CAM-11A"));

		// Doctor
//		LogContext context = new LogContext();
//		context.setClientAddress("10.0.1.101");
//		context.setUserId("smm@ihe.org");
//		context.setUserName("Dr. Jones");
//		LogManager.setLogContext(context);
		// Patient
		PatientID pId = new PatientID();
		Identifier aa = new Identifier("Test", "1.2.3.4.5", "ISO");
		aa.addPatientId(pId, "13520");
//		PatientDescriptor pD = new PatientDescriptor(pId);
//		pD.setNameFirst("MARCUS");
//		pD.setNameLast("FILLMORE");
//		ParticipantObject patient = new ParticipantObject(pD);
		// Media Description
		String mediaDesc = "zebra@hosp.org";//"USB Storage"

		MesaTestLogger logger = new MesaTestLogger(System.out);
		logger.writeTestBegin(test);
		IheAuditTrail auditTrail = new IheAuditTrail("XdmDocumentSource", repositories);
//		auditTrail.recordImported(patient, mediaDesc);
		logger.writeTestEnd(test);
	}

}
