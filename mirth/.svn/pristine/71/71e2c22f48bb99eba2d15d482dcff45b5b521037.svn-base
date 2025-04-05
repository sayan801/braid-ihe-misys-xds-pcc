/* Copyright 2009 Misys PLC
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
package mesatests.pcc;

import java.util.GregorianCalendar;

import mesatests.MesaTestLogger;
import mesatests.xds.TestKit;

import com.misyshealthcare.connect.base.SharedEnums.ConfidentialityCode;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.ConsentDocumentBuilder;
import com.misyshealthcare.connect.doc.ccd.ConsentDocumentData;
import com.misyshealthcare.connect.doc.ccd.DataEnterer;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentData;

/**
 * Create a BPPC Sample
 * 
 * @author Wenzhi Li
 * @version 1.0, Jan 14, 2009
 */
public class Test40152 extends TestPCCData{
	public static void main(String[] args) {
		MesaTestLogger log = new MesaTestLogger(System.out);
	    log.writeTestBegin("40152");
		TestKit.configActor(log, "xdsnist");
	
	    String outputFile = "c:\\temp\\pcctest\\EHR_ALLSCRIPTS_CONNECT_40152.xml";
		try {	
			  ConsentDocumentData  data = new ConsentDocumentData ();
	          //1. Creat PDF Sample	    		 
              data.setMetadata( getMetaData());
			  data.setMediaType(ScannedDocumentData.MediaType.PDF);
	          data.setFileContent(Test14101.class.getResourceAsStream("pdfSample.pdf"));
	          data.setConsentPolicies(new ConfidentialityCode[]{ConfidentialityCode.Normal});
	          ConsentDocumentBuilder  builder = new ConsentDocumentBuilder (data);
	          CCDDocument doc_pdf = builder.build(outputFile);
	          // System.out.println(doc_pdf.getContent());
	                    
		} catch(Exception e) {
			e.printStackTrace(); 
			log.writeString("Test Failed: " + e.getMessage());
		}
		log.writeTestEnd("40152");
	}

   private static MetaData getMetaData() {
       MetaData md = createMetaData("Allscripts", "EHR_ALLSCRIPTS_CONNECT");
       DataEnterer enterer = new DataEnterer();
       	enterer.setTime(GregorianCalendar.getInstance());
       	enterer.setPersonName( new PersonName("Susan", "Smith", "F", null, null));
       	md.setDataEnterers(new DataEnterer[]{enterer});

       	return md;
   }

}


