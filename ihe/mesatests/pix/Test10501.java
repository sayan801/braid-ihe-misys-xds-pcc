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
import com.misyshealthcare.connect.ihe.pix.PixIdentifierException;
import com.misyshealthcare.connect.net.IConnectionDescription;

/**
 * @author Jim Firby
 * @version MESA 2005 - Nov 20, 2005
 */
public class Test10501 {
	

	public static void main(String[] args) {
		String test = "10501";
		MesaTestLogger logger = new MesaTestLogger(System.out);
		logger.writeTestBegin(test);
		
		TestKit.configActor(logger, "pix_q_mesa");
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
        ConfigurationLoader.ActorDescription actor = loader.getDescriptionById("pix_q_mesa");
        IConnectionDescription connection = actor.getConnection();
        
		TestPixQuery tester = new TestPixQuery(connection, logger);
		
		// The first query in the test
		try {
			if (!tester.sendPixQuery("PIX10501", "Authority1", true)) {
				System.out.println("Test aborted!");
				return;
			}
		} catch (PixIdentifierException e) {
			System.out.println("PIX error: " + e.toString());
			System.out.println("Test aborted!");
			return;
		}
		
		// The second query in the test
		try {
			if (!tester.sendPixQuery("ABC10501", "Authority1", true)) {
				System.out.println("Test aborted!");
				return;
			}
		} catch (PixIdentifierException e) {
			System.out.println("PIX error: " + e.toString());
			System.out.println("Test aborted!");
			return;
		}
		
		logger.writeTestEnd(test);
	}

}
