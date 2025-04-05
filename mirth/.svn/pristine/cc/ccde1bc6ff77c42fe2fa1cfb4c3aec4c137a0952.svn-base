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

import mesatests.MesaTestLogger;
import mesatests.xds.TestKit;

import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * Test PIX Feed
 * 
 * @author Jim Firby
 * @version MESA 2005 - Nov 22, 2005
 */
public class Test10512a {


    public static void main(String[] args) {
        String test = "10512a";
    	MesaTestLogger logger = new MesaTestLogger("mesatests/submitted/10512/log10512a.txt");
		logger.writeTestBegin(test);
		
		TestKit.configActor(logger, "lpfmesa");
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
        ConfigurationLoader.ActorDescription actor = loader.getDescriptionById("lpfmesa");
        IConnectionDescription connection = actor.getConnection();

        TestPatientFeed tester = new TestPatientFeed(connection, logger);

        // The first query in the test
        if (!tester.sendFeed("TestA", true)) {
            System.out.println("Test aborted!");
            return;
        } else {
            logger.writeString("Message accepted!");
        }

        logger.writeTestEnd(test);
    }

}
