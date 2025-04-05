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

import com.misyshealthcare.connect.base.audit.AuditCodeMappings.SuccessCode;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.TestLogContext;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.LibraryConfig;

/** Test rig for MESA test 11104.
 * 
 * This tests the logging of user authentication.
 * 
 * @see ConnectionFactory
 * @author Josh Flachsbart
 * @version 2.0 - Nov 10, 2005
 */
public class Test11104 {
	
	/**
	 * Run MESA Test 11104
	 */
	public static void main(String[] args) {
		String test = "11104";
		
		ConnectionFactory.loadConnectionDescriptionsFromFile("src/config/connectathon/AuditRepositoryConnections.xml");
		ArrayList<IConnectionDescription> repositories = new ArrayList<IConnectionDescription>();
		repositories.add(ConnectionFactory.getConnectionDescription("log4j_audittrail"));
		repositories.add(ConnectionFactory.getConnectionDescription("mesa_arr_bsd"));

		// Doctor
//		LogContext context = new LogContext();
//		context.setClientAddress("10.0.1.101");
//		context.setUserId("jones@sunroom.hosp.org");
//		context.setUserName("Dr. Jones");
//		LogManager.setLogContext(context);
		LibraryConfig.getInstance().setLogContext(new TestLogContext());

		MesaTestLogger logger = new MesaTestLogger(System.out);
		logger.writeTestBegin(test);
		IheAuditTrail snat = new IheAuditTrail("SecureNode", repositories);
		snat.start();
		snat.userLogin(SuccessCode.Success, null);
		logger.writeTestEnd(test);
	}

}
