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

package com.misyshealthcare.connect.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.ihe.TestLogContext;
import com.misyshealthcare.connect.ihe.XdsDocumentConsumerTest;
import com.misyshealthcare.connect.ihe.XdsDocumentConsumerTest.OidMock;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.MailConnection;
import com.misyshealthcare.connect.net.SecureConnectionDescription;
import com.misyshealthcare.connect.util.EmailException;
import com.misyshealthcare.connect.util.EmailSender;

/**
 * Tests the Email Sender
 *
 * @author Michael Traum
 * @version 2.0, 12/22/2006
 */
public class EmailSenderTest extends TestCase {

    
    public void testSecurePrebuiltMessage() {
//        SecureConnectionDescription description = new SecureConnectionDescription();
//        description.setHostname("127.0.0.1");
//        description.setPort(5556);
//        description.setTrustStore("certs/truststore.jks");
//        description.setTrustStorePassword("password");
//        description.setKeyStore("certs/keystore.jks");
//        description.setKeyStorePassword("password");
//        
//        // TODO add in smpt and pop3 properties...
//        description.complete();
    	
/*  Not working, need to come back here when we work on XDR profile. 
        ConfigurationLoader loader = ConfigurationLoader.getInstance();
        loader = ConfigurationLoader.getInstance();
          try {
              URL url = XdsDocumentConsumerTest.class.getResource("/config/connectathon/IheActors.xml");
              File file = new File(url.toURI());
              loader.loadConfiguration(file, false, "2.16.840.1.113883.3.65.2", null, null, null, CodeMappingManager.getInstance(), new TestLogContext());
              //Collection actors = loader.getActorDescriptions();
              Collection actors = new ArrayList<String>();
              actors.add( "xdrs" );
              //reset to add log file
              loader.resetConfiguration(actors, "c:\\testlog.xml");
          } catch (IheConfigurationException e) {
              e.printStackTrace();
              fail("Cannot load the actor property");
          }catch (URISyntaxException e) {
              e.printStackTrace();
              fail("Cannot load the actor property");
          }
      	IConnectionDescription description = loader.getDescriptionById("xdrs").getConnection();

        MailConnection connection = ConnectionFactory.getMailConnection(description);
        EmailSender emailSender = new EmailSender(connection);
        emailSender.setFromAddress("misys@misyshealthcare.com");
        emailSender.setToAddress("wenzhi.li@misyshealthcare.com");
        emailSender.setRecipientCertFile("C:\\temp\\traum_misys_outlook_export.cer");
        emailSender.setSecure(true);
		
        MimeMessage body = new MimeMessage(emailSender.getSession());
        try {
			body.setFrom(new InternetAddress(emailSender.getFromAddress()));
			body.setRecipient(Message.RecipientType.TO, new InternetAddress(
					emailSender.getToAddress()));
			body.setSubject("this is a test subject");
			body.setContent("this is a test body", "text/plain");
			body.saveChanges();
			emailSender.setMessage(body);
		} catch (AddressException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
    	try {
			emailSender.send();
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}*/
    }
}
