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
package com.misyshealthcare.connect.ihe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import mesatests.MesaTestLogger;
import mesatests.xds.TestKit;

import com.misyshealthcare.connect.base.IPatientConsumer;
import com.misyshealthcare.connect.base.PatientBroker;
import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.SharedEnums.AddressType;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.clinicaldata.Visit;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientDescriptor;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.ihe.PixConsumer;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * This class tests PIX Feed and PIX Query, with audit trail log enabled.
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 31, 2007
 */
public class PixTest {
    public static void main(String[] args) {
        
    	MesaTestLogger logger = new MesaTestLogger(System.out);
		//Initiate PIX manager
		TestKit.configActor(logger, "lpfinit", "gpfinit", "pixinit", "localaudit"); 
    	//Accenx PIX manager
    	//TestKit.configActor(logger, "lpfaccenx", "gpfaccenx", "pixaccenx", "localaudit");
		
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
        ConfigurationLoader.ActorDescription pixqactor = loader.getDescriptionById("pixinit");
        ConfigurationLoader.ActorDescription auditactor = loader.getDescriptionById("localaudit");
    
        testPixFeed(); 
        testPixQuery(pixqactor.getConnection(), new IheAuditTrail("localaudit", auditactor.getLogConnection() ));

    }
    
    private static void testPixFeed() {
        PatientDescriptor patient = newSamplePatient();
        try {
			PatientBroker.getInstance().createPatient(patient, IPatientConsumer.CreationReason.OUTPATIENT_REGISTER);
		} catch (PatientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    private static void testPixQuery(IConnectionDescription connection, IheAuditTrail audittrail) {
    	PixConsumer consumer;
		try {
			consumer = new PixConsumer(connection, audittrail);
	    	PatientID pid = new PatientID("23436");
	    	consumer.getPatientIds(pid);
	    	
	        Set<String> aas = pid.getHomeSystems();
	        if (aas.size() > 0 ) {
	        	System.out.println("Found patient for local id:" + 23436);         
	        }
	        for (String aa : aas) {
	        	String id = pid.getId( aa );
	        	System.out.println("AssigningAuthority: "  + aa + ", id: " + id);
	        }
        } catch (IheConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (PatientException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
    }
    
    private static PatientDescriptor newSamplePatient() {
        // Create a sample patient
        PatientID pid = new PatientID("23436"); 
        pid.addId("Misys", "Mi-1234");
        PatientDescriptor pd = new PatientDescriptor(pid);
        pd.setNameLast("EPSILON");
        pd.setNameFirst("ELLIE");
        pd.setMotherMaidenName("ZIMMER");
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(1938, 1, 24);
        pd.setBirthDateTime(cal.getTime());
        pd.setAdministrativeSex(SexType.FEMALE);
        Address address = new Address();
        address.setAddType(AddressType.OTHER);
        address.setAddLine1("1 PINETREE");
        address.setAddCity("WEBSTER");
        address.setAddState("MO");
        address.setAddZip("63119");
        pd.addAddress(address);
        PhoneNumber phone = new PhoneNumber();
        phone.setType(PhoneType.HOME);
        phone.setNumber("5551234");
        phone.setAreaCode("314");
        pd.addPhoneNumber(phone);
        phone = new PhoneNumber();
        phone.setType(PhoneType.WORK);
        phone.setNumber("5554444");
        phone.setAreaCode("314");
        pd.addPhoneNumber(phone);
        pd.setSsn("444-70-9999");
        pd.setDriverLicense("MODOTJ432");
        Visit visit = new Visit("Misys", "10512-001");
        Provider provider = new Provider();
        provider.setProviderId("5101");
        provider.setProvNameTitle("DR");
        provider.setProvNameFirst("FREDERICK");
        provider.setProvNameMiddle("P");
        provider.setProvNameLast("NELL");
        visit.addProvider(provider);
        visit.setVisitStartTimestamp(new GregorianCalendar(2003, 7, 8, 10, 45, 0).getTime());
        pd.addVisit(visit);
        return pd;
    }
    
}