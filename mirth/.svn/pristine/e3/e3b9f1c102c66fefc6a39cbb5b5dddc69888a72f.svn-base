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
package mesatests.xds;

import java.util.GregorianCalendar;

import javax.xml.soap.SOAPMessage;

import mesatests.MesaTestLogger;

import com.misyshealthcare.connect.base.DateQuery;
import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.ihe.XdsDocumentConsumer;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 21, 2007
 */
public class Test11949 {
	public static void main(String[] args) {
	    String test = "11949";
		MesaTestLogger log = new MesaTestLogger("mesatests/submitted/log11949.txt");
		log.writeTestBegin(test);

		TestKit.configActor(log, "xdsqnist");
		//TestKit.configActor(log, "xdsqnist", "localaudit", "arrnist");  //Test 12025

        try {	
        	DocumentQuery query = new DocumentQuery();
        	DateQuery startDate = new DateQuery(2006, 1, 1);
        	query.setStartTime(startDate);
        	query.setEndTime( new DateQuery(GregorianCalendar.getInstance()));
        	//use a dummy id, the real id is provided in the actor config
        	query.setPatientId(new PatientID("23456456"));
        	//List<Document> docs = DocumentBroker.getInstance().findDocuments(query);
        	
    		ConfigurationLoader loader = ConfigurationLoader.getInstance();
            ConfigurationLoader.ActorDescription actor = loader.getDescriptionById("xdsqnist");
            IConnectionDescription connection = actor.getConnection();
        	XdsDocumentConsumer consumer = new XdsDocumentConsumer(connection, null);
            SOAPMessage reply = consumer.getMesaInterface().sendXdsRegistryQuery( query, "ObjectRef", "true");
            log.writeSoapMessage(reply);

    	} catch(Exception e) {
    		e.printStackTrace(); 
    		log.writeString("Test Failed: " + e.getMessage());
    	}
		log.writeTestEnd(test);
		
	}
	

}
