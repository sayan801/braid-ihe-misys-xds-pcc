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

import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.List;

import mesatests.MesaTestLogger;

import com.misyshealthcare.connect.base.DateQuery;
import com.misyshealthcare.connect.base.Document;
import com.misyshealthcare.connect.base.DocumentBroker;
import com.misyshealthcare.connect.base.DocumentQuery;
import com.misyshealthcare.connect.base.PatientID;

/**
 * Mesa Test Test12075 Document Retrieve with Audit Log
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 21, 2007
 */
public class Test12075 {
    public static void main(String[] args) {
    	MesaTestLogger log = new MesaTestLogger(System.out);
    	log.writeTestBegin("12075");

    	//TestKit.configActor(log, "xdsqnist");
    	TestKit.configActor(log, "xdsqnist", "localaudit", "arrnist");  // Test12075
        try {	
        	DocumentQuery query = new DocumentQuery();
        	DateQuery startDate = new DateQuery(2006, 1, 1);
        	query.setStartTime(startDate);
        	query.setEndTime( new DateQuery(GregorianCalendar.getInstance()));
        	//use a dummy id, the real id is provided in the actor config
        	query.setPatientId(new PatientID("23456456"));
        	List<Document> docs = DocumentBroker.getInstance().findDocuments(query);
        	if (docs.size() == 0) {
        		log.writeString("No document is found.");
        	} else {
        		log.writeString("Documents returned were:");
        	}
        	for (int i=0; i<docs.size(); i++) {
        		Document doc = docs.get(i);
        		log.writeString(" " + i + ") \"" + doc.getTitle() + "\", ClassCode:" + 
        	      doc.getClassCode().getValue() + ", Unique ID:" + doc.getUniqueId()) ;
    		}
        	
        	//Now, do the document retrieval test
        	if (docs.size() > 0) {
        		Document doc = docs.get(0);
        		InputStream is = doc.getContentStream();
        	}
    	} catch(Exception e) {
    		e.printStackTrace(); 
    		log.writeString("Test Failed: " + e.getMessage());
    	}
    	
    	
		log.writeTestEnd("12075");
    }
}