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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
import com.misyshealthcare.connect.ihe.IMesaLogger;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.IBMTest;
import com.misyshealthcare.connect.ihe.TestLogContext;
import com.misyshealthcare.connect.util.OID;

/**
 *  
 *
 * @author Wenzhi Li
 * @version 3.0, Dec 13, 2007
 */
public class TestKit {
	
	public static void configActor(IMesaLogger log, String ... actor) {
        final class OidMock implements OID.OidSource {
            public synchronized String generateId() {
                return Long.toString( System.currentTimeMillis() );
            }
        }
        

		ConfigurationLoader loader = ConfigurationLoader.getInstance();

        try {
            URL url = TestKit.class.getResource("/config/connectathon/IheActors.xml");
            File file = new File(url.toURI());
            loader.loadConfiguration(file, false, "2.16.840.1.113883.3.65.2", new OidMock(), "CCDPreview.xsl", null, CodeMappingManager.getInstance(), new TestLogContext());
            
            List actors = Arrays.asList(actor);
            //reset to add log file
            loader.resetConfiguration(actors, "c:\\mesalog.xml", log);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static String readTextFile(String file) throws IOException {
    	StringBuffer sb = new StringBuffer(1024);
    	BufferedReader reader = new BufferedReader(new FileReader(file));
    			
    	char[] chars = new char[1024];
    	int numRead = 0;
    	while( (numRead = reader.read(chars)) > -1){
    		sb.append(String.valueOf(chars));	
    	}

    	reader.close();

    	return sb.toString();
    }	
}
 
