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
import java.util.List;

import mesatests.MesaTestLogger;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;

import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.ihe.PixConsumer;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.pix.PixCommunicationException;
import com.misyshealthcare.connect.ihe.pix.PixIdentifierException;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * @author Jim Firby
 * @version MESA 2005 - Nov 20, 2005
 */
public class TestPixQuery {
	
	private MesaTestLogger logger = null;
	private IConnectionDescription connection = null;
	private PixConsumer pix = null;

	public TestPixQuery(IConnectionDescription connection, MesaTestLogger logger) {
		this.connection = connection;
		this.logger = logger;
		try {
			pix = new PixConsumer(connection, null);
		} catch (IheConfigurationException e) {
			e.printStackTrace();
			logger.writeString("Cannot initialize PixConsumer");
		}
		pix.setMesaLogger(logger);
	}
	
	public boolean sendPixQuery(String id, String authority, boolean pause) throws PixIdentifierException {
		
		// Wait until query is requested by MESA test
		if (pause) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\nHit ENTER to send PIX query about ID '" + id + "' (or 'q' to quit)");
			try {
				String input = reader.readLine();
				if (input.equalsIgnoreCase("q")) return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		// Send the query
		List<String> responses;
		try {
			responses = pix.getMesaInterface().doPixQuery(id, connection.getIdentifier(authority));
		} catch (DataTypeException e) {
			logger.writeString("Cannot encode patient information into valid HL7");
			e.printStackTrace();
			return false;
		} catch (IheConfigurationException e) {
			logger.writeString("Connection not properly configured");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			logger.writeString("Cannot communicate with PIX server");
			e.printStackTrace();
			return false;			
		} catch(PixCommunicationException e) {
			logger.writeString("Cannot communicate with PIX server");
			e.printStackTrace();
			return false;			
		}
		
	
		if ((responses != null) && (responses.size() > 0)) {
			logger.writeString("The following xref IDs were returned:");
			for (String idString: responses) logger.writeString(idString);
		} else {
			logger.writeString("No xref IDs were returned!");
		}
		return true;
	}
	
}
