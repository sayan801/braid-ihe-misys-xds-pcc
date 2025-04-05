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

import java.util.List;
import java.util.Vector;

import com.misyshealthcare.connect.base.IBrokerController;
import com.misyshealthcare.connect.base.IPatientConsumer;
import com.misyshealthcare.connect.base.IPatientSource;
import com.misyshealthcare.connect.base.PatientBroker;
import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.PatientQuery;
import com.misyshealthcare.connect.base.IPatientConsumer.CreationReason;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptorSet;

import junit.framework.TestCase;

public class PatientBrokerTest extends TestCase {
	
	private static MockSource ms = null;
	private static MockConsumer mc = null;
	
    public void setUp() throws Exception {
		if (ms == null)	ms = new MockSource();
		PatientBroker.getInstance().registerPatientSource(ms);
		if (mc == null)	mc = new MockConsumer();
		PatientBroker.getInstance().registerPatientConsumer(mc);
    }
	
    public void tearDown() throws Exception {
    	IheBrokerController controller = new IheBrokerController();
    	PatientBroker.getInstance().unregisterPatientSources(controller);
    	PatientBroker.getInstance().unregisterPatientConsumers(controller);
    }
	
	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.getInstance()'
	 */
	public void testGetInstance() {
		assertSame(PatientBroker.getInstance(), PatientBroker.getInstance());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.registerPatientSource(IPatientSource)'
	 */
	public void testRegisterPatientSource() {
		
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.registerPatientConsumer(IPatientConsumer)'
	 */
	public void testRegisterPatientConsumer() {

	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.findPatients(PatientQuery)'
	 */
	public void testFindPatients() {
		PatientBroker broker = PatientBroker.getInstance();
		List<PatientDescriptor> pds = broker.findPatients(new PatientQuery());
		assertNotNull(pds);
		assertEquals(5, pds.size());
		assertEquals("Abrams", pds.get(0).getNameLast());
		assertEquals("Barstow", pds.get(1).getNameLast());
		assertEquals("Clark", pds.get(2).getNameLast());
		assertEquals("Clarkson", pds.get(3).getNameLast());
		assertEquals("Clarkson", pds.get(4).getNameLast());
		// Now try a restriction
		PatientQuery query = new PatientQuery();
		query.setMaxPatientCount(3);
		pds = broker.findPatients(query);
		assertNotNull(pds);
		assertEquals(3, pds.size());
		assertEquals("Abrams", pds.get(0).getNameLast());
		assertEquals("Barstow", pds.get(1).getNameLast());
		assertEquals("Clark", pds.get(2).getNameLast());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.findPatientSets(PatientQuery)'
	 */
	public void testFindPatientSets() {
		PatientBroker broker = PatientBroker.getInstance();
		List<PatientDescriptorSet> pds = broker.findPatientSets(new PatientQuery());
		assertNotNull(pds);
		assertEquals(3, pds.size());
		assertEquals("Abrams", pds.get(0).getAlphabeticalNameString());
		assertEquals("Barstow", pds.get(1).getAlphabeticalNameString());
		assertEquals("Clark", pds.get(2).getAlphabeticalNameString());
		// Now try a restriction
		PatientQuery query = new PatientQuery();
		query.setMaxPatientCount(2);
		pds = broker.findPatientSets(query);
		assertNotNull(pds);
		assertEquals(2, pds.size());
		assertEquals("Abrams", pds.get(0).getAlphabeticalNameString());
		assertEquals("Barstow", pds.get(1).getAlphabeticalNameString());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.createPatient(PatientDescriptor, CreationReason)'
	 */
	public void testCreatePatient() {
		mc.numCCalls = 0;
		PatientBroker broker = PatientBroker.getInstance();
		PatientDescriptor pd = new PatientDescriptor();
		try {
			broker.createPatient(pd, CreationReason.INPATIENT_ADMIT);
			broker.createPatient(pd, CreationReason.INPATIENT_PREADMIT);
			broker.createPatient(pd, CreationReason.OUTPATIENT_REGISTER);
		} catch (PatientException e) {
			fail(e.toString());
		}
		assertEquals(3, mc.numCCalls);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.updatePatient(PatientDescriptor)'
	 */
	public void testUpdatePatient() {
		mc.numUCalls = 0;
		PatientBroker broker = PatientBroker.getInstance();
		PatientDescriptor pd = new PatientDescriptor();
		try {
			broker.updatePatient(pd);
		} catch (PatientException e) {
			fail(e.toString());
		}
		assertEquals(1, mc.numUCalls);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.base.PatientBroker.mergePatients(PatientDescriptor, PatientDescriptor)'
	 */
	public void testMergePatients() {
		mc.numMCalls = 0;
		PatientBroker broker = PatientBroker.getInstance();
		PatientDescriptor pd = new PatientDescriptor();
		PatientDescriptor pd1 = new PatientDescriptor();
		try {
			broker.mergePatients(pd, pd1);
		} catch (PatientException e) {
			fail(e.toString());
		}
		assertEquals(1, mc.numMCalls);
	}
	
	/* -------------- Mock Test Classes -------------------- */
	
	public class MockSource implements IPatientSource {

		public void start() {}
		
		public void stop() {}

		public List<PatientDescriptor> findPatients(PatientQuery query) throws PatientException {
			Vector<PatientDescriptor> vpd = new Vector<PatientDescriptor>();
			PatientDescriptor patient = new PatientDescriptor(new PatientID("1234"));
			patient.setNameLast("Clarkson");
			vpd.add(patient);
			PatientID pid = new PatientID();
			patient = new PatientDescriptor(pid);
			pid.addId("Home", "23");
			patient.setNameLast("Clarkson");
			vpd.add(patient);
			patient = new PatientDescriptor(new PatientID("2345"));
			patient.setNameLast("Barstow");
			vpd.add(patient);
			pid = new PatientID("1234");
			patient = new PatientDescriptor(pid);
			pid.addId("Home", "23");
			patient.setNameLast("Clark");
			vpd.add(patient);
			patient = new PatientDescriptor(new PatientID("3456"));
			patient.setNameLast("Abrams");
			vpd.add(patient);
			return vpd;
		}

		public String getName() {
			return "MockSource";
		}
		
	}
	
	public class MockConsumer implements IPatientConsumer {
		
		public int numCCalls = 0;
		public int numUCalls = 0;
		public int numMCalls = 0;

		public void start() {}
		
		public void stop() {}

		public void createPatient(PatientDescriptor patient, CreationReason reason) throws PatientException {
			numCCalls = numCCalls + 1;
		}

		public void updatePatient(PatientDescriptor patient) throws PatientException {
			numUCalls = numUCalls + 1;
		}

		public void mergePatients(PatientDescriptor patientMain, PatientDescriptor patientOld) throws PatientException {
			numMCalls = numMCalls + 1;
		}
		
	}
	
	/**
	 * An implementation of a broker controller that will unregister and IHE actor.
	 * 
	 * @author Jim Firby
	 * @version 2.0 - Jan 11, 2006
	 */
	public class IheBrokerController implements IBrokerController {

		public boolean shouldUnregister(Object actor) {
			// Unregister the mock actors
            if (actor instanceof IPatientSource) return true;
            if (actor instanceof IPatientConsumer) return true;

            return false;
		}
		
	}

}
