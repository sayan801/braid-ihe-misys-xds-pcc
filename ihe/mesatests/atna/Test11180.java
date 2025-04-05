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

import com.misyshealthcare.connect.base.AuditBroker;
import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.audit.ParticipantObject;
import com.misyshealthcare.connect.ihe.registry.XmlRegistry;
import com.misyshealthcare.connect.ihe.TestLogContext;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.util.Pair;

/** Test rig for MESA test 11180.
 * 
 * This tests all of the required audit messages.
 * 
 * @see ConnectionFactory
 * @author Josh Flachsbart
 * @version 2.0 - Nov 09, 2005
 */
public class Test11180 {

	/**
	 * Run MESA Test 11180
	 */
	public static void main(String[] args) {
		String test = "11180";
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
		IheAuditTrail doccons = new IheAuditTrail("DocumentConsumer", repositories);
		// Doctor
//		LogContext context = new LogContext();
//		context.setClientAddress("10.0.1.101");
//		context.setUserId("jones@sunroom.hosp.org");
//		context.setUserName("Dr. Jones");
//		LogManager.setLogContext(context);
		LibraryConfig.getInstance().setLogContext(new TestLogContext());
		// Patient
		PatientID pId = new PatientID();
		Identifier aa = new Identifier("Test", "1.2.3.4.5", "ISO");
		aa.addPatientId(pId, "misys-id-02344321183");
		PatientDescriptor pD = new PatientDescriptor(pId);
		pD.setNameFirst("Susan");
		pD.setNameLast("Formaldehyde");
		ParticipantObject patient = new ParticipantObject(pD);
		
		ParticipantObject submissionsetobject = new ParticipantObject(null, "123123");
		
		DocumentQuery query = new DocumentQuery();
		query.setDocumentId("123456789");
		Pair queryId = XmlRegistry.getStoredQueryId(query, null);
    	ParticipantObject queryobject = new ParticipantObject((String)queryId._second, (String)queryId._first);					
    	queryobject.query = "test query message";
		 

    	// -------  TEST  --------		
//		broker.userLogin();
    	//11181
//		doccons.start();
    	
    	//11190
//		broker.nodeAuthenticationFailure(ConnectionFactory.getConnectionDescription("MISYS-SECURE-2"));

    	//11199
		doccons.patientQueryIssued(ConnectionFactory.getConnectionDescription("MISYS-SECURE-1"), patient, true);
//		doccons.documentSubmitted(ConnectionFactory.getConnectionDescription("MISYS-SECURE-1"), patient, submissionsetobject);
//    	doccons.documentStoredQuery(ConnectionFactory.getConnectionDescription("MISYS-SECURE-1"), patient, queryobject );
    	//doccons.documentRetrieved(ConnectionFactory.getConnectionDescription("MISYS-SECURE-1"), patient, documentobject);

    	//11195
//    	doccons.recordAccessed(patient);
    	
    	//11196
//		doccons.recordExported(patient, "USB Media");
		//11197
//		doccons.recordImported(patient, "USB Media");
       
    	//11182
//		doccons.stop();  
//		broker.userLogout();
		
		// -------  END TEST  --------
		
		logger.writeTestEnd(test);
	}

}
