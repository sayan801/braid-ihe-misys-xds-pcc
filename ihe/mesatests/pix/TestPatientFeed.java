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
package mesatests.pix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import mesatests.MesaTestLogger;
import ca.uhn.hl7v2.HL7Exception;

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
import com.misyshealthcare.connect.ihe.PatientIdentityException;
import com.misyshealthcare.connect.ihe.PatientIdentitySource;
import com.misyshealthcare.connect.ihe.PatientIdentitySource.MesaInterface;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;

/**
 * @author Jim Firby
 * @version MESA 2005 - Nov 22, 2005
 */
public class TestPatientFeed {

    @SuppressWarnings("unused")
    private MesaTestLogger logger = null;
    private IConnectionDescription connection = null;
    private PatientIdentitySource pis = null;

    public TestPatientFeed(IConnectionDescription connection, MesaTestLogger logger) {
        this.connection = connection;
        this.logger = logger;
        pis = new PatientIdentitySource(connection, null);
        pis.setMesaLogger(logger);
    }

    private PatientDescriptor newSamplePatient() {
        // Create a sample patient
        PatientID pid = new PatientID("PIX10512");
        pid.addId("Misys", "MI-1234");
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

    public boolean sendFeed(String authority, boolean pause) {

        // Wait until query is requested by MESA test
        if (pause) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nHit ENTER to send Patient Feed using '" + authority + "' (or 'q' to quit)");
            try {
                String input = reader.readLine();
                if (input.equalsIgnoreCase("q")) return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Send the query
        PatientDescriptor patient = newSamplePatient();
        boolean response = false;
        try {
            Identifier misys = Configuration.getIdentifier(connection, authority, true);
            MesaInterface mesaInterface = pis.getMesaInterface();
            response = mesaInterface.sendA04PatientFeed(patient, misys);
        } catch (IheConfigurationException e) {
            logger.writeString("Connection not configured properly.");
            e.printStackTrace();
            return false;
        } catch (PatientException e) {
            logger.writeString("Patient missing required data.");
            e.printStackTrace();
            return false;
        } catch (HL7Exception e) {
            logger.writeString("Patient data cannot be encoded into valid HL7.");
            e.printStackTrace();
            return false;
        } catch (PatientIdentityException e) {
            logger.writeString("Message not accepted by patient feed consumer.");
            e.printStackTrace();
        } catch (IOException e) {
            logger.writeString("Cannot communicate over connection.");
            e.printStackTrace();
        }

        // Process the response
        return response;
    }

}
