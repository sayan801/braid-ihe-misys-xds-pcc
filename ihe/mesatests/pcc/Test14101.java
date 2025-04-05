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

import com.misyshealthcare.connect.doc.ccd.Author;
import com.misyshealthcare.connect.doc.ccd.AuthorDevice;
import com.misyshealthcare.connect.doc.ccd.CCDDocument;
import com.misyshealthcare.connect.doc.ccd.DataEnterer;
import com.misyshealthcare.connect.doc.ccd.MetaData;
import com.misyshealthcare.connect.doc.ccd.PersonName;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentBuilder;
import com.misyshealthcare.connect.doc.ccd.ScannedDocumentData;

/**
 * Create two XDS-SD(Scanned Document) Samples 
 *   1. scanned PDF doc
 *   2. scanned txt doc
 * 
 * @author Wenzhi Li
 * @version 1.0, Jan 14, 2009
 */
public class Test14101 extends TestPCCData{
	   public static void main(String[] args) {
	    	MesaTestLogger log = new MesaTestLogger(System.out);
	        log.writeTestBegin("14101");
	    	TestKit.configActor(log, "xdsnist");

	        String outputFile_pdf = "c:\\temp\\pcctest\\EHR_ALLSCRIPTS_CONNECT_14101_pdf.xml";
	        String outputFile_txt = "c:\\temp\\pcctest\\EHR_ALLSCRIPTS_CONNECT_14101_text.xml";
	    	try {	
	    		 ScannedDocumentData  data = new ScannedDocumentData ();
	              //1. Creat PDF Sample	    		 
	              data.setMetadata( getMetaData() );
        		  data.setMediaType(ScannedDocumentData.MediaType.PDF);
	              data.setFileContent(Test14101.class.getResourceAsStream("pdfSample.pdf"));
	            
	              ScannedDocumentBuilder  builder = new ScannedDocumentBuilder (data);
	              CCDDocument doc_pdf = builder.build(outputFile_pdf);
	              // System.out.println(doc_pdf.getContent());
	              
        		  data.setMediaType(ScannedDocumentData.MediaType.TEXT);
	              data.setFileContent(Test14101.class.getResourceAsStream("txtSample.txt"));
	            
	              ScannedDocumentBuilder  builder2 = new ScannedDocumentBuilder (data);
	              CCDDocument doc_txt = builder2.build(outputFile_txt);
		          // System.out.println(doc_txt.getContent());
	              
	    	} catch(Exception e) {
	    		e.printStackTrace(); 
	    		log.writeString("Test Failed: " + e.getMessage());
	    	}
			log.writeTestEnd("14101");
	    }

	   private static MetaData getMetaData() {
           MetaData md = createMetaData("Allscripts", "EHR_ALLSCRIPTS_CONNECT");
	       DataEnterer enterer = new DataEnterer();
	       	enterer.setTime(GregorianCalendar.getInstance());
	       	enterer.setPersonName( new PersonName("Susan", "Smith", "F", null, null));
	       	md.setDataEnterers(new DataEnterer[]{enterer});
	
	       	Author author = new Author();
            author.setOrganization(getOrganization()); 
	       	AuthorDevice device = new AuthorDevice();
	       	device.setManufacturerModelName("HP All-in-one 603");
	       	device.setSoftwareName("HP Scan Software");
	       	author.setAuthorDevice( device );
	       	md.setAuthors(new Author[]{author});
	       	return md;
	   }
}
