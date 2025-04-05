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
package com.misyshealthcare.connect.base;

import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
import com.misyshealthcare.connect.base.audit.ActiveParticipant;
import com.misyshealthcare.connect.base.audit.AuditCodeMappings;
import com.misyshealthcare.connect.base.audit.IAuditTrail;
//import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
//import com.misyshealthcare.connect.ihe.unitest.TestLogContext;
//import com.misyshealthcare.connect.log.LogContext;
//import com.misyshealthcare.connect.log.LogManager;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class presents a virtual interface to a collection of
 * audit trail sources.  Some of these sources will be external 
 * IHE sources.
 * <p>
 * This class presents a single global <code>AuditBroker</code>
 * instance to the Misys Connect 2.0 code.  That way it need
 * not be passed around in global web state.  It can simply be
 * initialized and then requested from any code whenever necessary.
 * 
 * @author Josh Flachsbart
 * @version 2.0 - Jan 5, 2006
 */
public class AuditBroker {
	
	/* A single instance for this class */
	private static AuditBroker singleton = null;
	
	/* All the audit trail connections. */
	private IAuditTrail auditTrail;
	
	/**
	 * A private constructor for creating the singleton instance.
	 */
	private AuditBroker() {
		super();
	}
	
	/**
	 * Get the single global instance of the <code>AuditBroker</code>.
	 * 
	 * @return The patient broker
	 */
	public static synchronized AuditBroker getInstance() {
		if (singleton == null) singleton = new AuditBroker();
		return singleton;
	}
	
	/**
	 * Set the AuditTrail for this broker.  May only be set once.
	 * 
	 * @param source A complete audit trail.
	 * @return True if this source was successfully added
	 */
	public synchronized boolean registerAuditSource(IAuditTrail source) {
		boolean added = false;
		if ((auditTrail == null) && (source != null)) {
			source.start();
			auditTrail = source;
			added = true;
		}
		return added;
	}
	
	/**
	 * Unregister the audit sources the controller suggests.  If the controller
	 * is null unregister all sources.
	 * 
	 * @param controller The controller to ask about the audit sources.
	 * @return
	 */
	public synchronized boolean unregisterAuditSources(IBrokerController controller) {
		boolean removed = false;
		if (auditTrail != null) {
			if ((controller == null) || controller.shouldUnregister(auditTrail)) {
				auditTrail.stop();
				auditTrail = null;
				removed = true;
			}
		}
		return removed;
	}
	
	public synchronized void nodeAuthenticationFailure(IConnectionDescription otherServer) {
		if (auditTrail != null) {
			auditTrail.nodeAuthenticationFailure(AuditCodeMappings.SuccessCode.MinorFailure, otherServer);
		}
	}

	/** Call on successful login.
	 * 
	 * This function will send a login message to the secure node
	 * audit repositories.  The user info should be set in the thread
	 * context before calling this method.
	 */
	public synchronized void userLogin() {
		if (auditTrail != null) {
			auditTrail.userLogin(AuditCodeMappings.SuccessCode.Success, null);
		}
	}
	
	/** Call just before logout.
	 * 
	 * This function will send a logout message to the secure node
	 * audit repositories.  The user info should be set in the thread
	 * context before calling this method.
	 */
	public synchronized void userLogout() {
		if (auditTrail != null) {
			auditTrail.userLogout(AuditCodeMappings.SuccessCode.Success, null);
		}
	}
	
	/** Call on failed login.
	 * 
	 * This function will send a login message to the secure node
	 * audit repositories.  The available info should be handed to
	 * this function, since a full user name/id etc. is not available.
	 * @param clientAddress IP or hostname for the machine the user tried to log in to.
	 * @param userName The name that was used to attempt to log in.
	 */
	public synchronized void failedLogin(String userName, String clientAddress) {
		if (auditTrail != null) {
			ActiveParticipant intruder = new ActiveParticipant(userName, "Failed Login Username", clientAddress);
			auditTrail.userLogin(AuditCodeMappings.SuccessCode.MinorFailure, intruder);
		}
	}

	/** For unit test only. */
	public int auditConsumerCount() {
		int count = 0;
		if (auditTrail != null) count = 1;
		return count;		
	}
	
	/** For testing only
	 * 
	 * @param args
	 */
	static public void  main(String args[]) {
//		LogContext context = new LogContext();
//		context.setClientAddress("10.0.1.101");
//		context.setUserId("jones@sunroom.hosp.org");
//		context.setUserName("Dr. Jones");
//		LogManager.setLogContext(context);
//		try {
//			ConfigurationLoader loader = ConfigurationLoader.getInstance();
//			loader.loadConfiguration("testIheConfiguration.xml", "2.16.840.1.113883.3.65.2", null, null, CodeMappingManager.getInstance(), new TestLogContext());
//		} catch (Exception e) {}
//		
//		AuditBroker.getInstance().failedLogin("FakeName", "login hostname");
//		AuditBroker.getInstance().userLogin();
//		AuditBroker.getInstance().userLogout();
	}
}
