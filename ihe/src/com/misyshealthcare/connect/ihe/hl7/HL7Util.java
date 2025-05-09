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


package com.misyshealthcare.connect.ihe.hl7;

import ca.uhn.hl7v2.util.Terser;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segment.QPD;
import ca.uhn.hl7v2.model.Type;

/**
 * This class defines some utility methods used by all actor implementation
 *
 * @author Wenzhi Li
 * @version 2.1, Apr 26, 2007
 */
public class HL7Util {
    /**
     * Echo and Populate QPD segment from an existing QPD segment. This method only echoes up to
     * the fifth component and the fifth sub-component.
     *
     * @param out The out Terser including the QPD segment to be populated
     * @param in The in Terser including the QPD segment that containing the query parameters
     * @throws ca.uhn.hl7v2.HL7Exception When the QPD segment cannot be echoed
     */
    public static void echoQPD(Terser out, Terser in) throws HL7Exception {
          QPD qpdIn = (QPD)in.getSegment("/.QPD");
          QPD qpdOut = (QPD)out.getSegment("/.QPD");
          int numFields = qpdIn.numFields();
          for (int f=1; f<=numFields; f++) {
            Type[] reps = qpdIn.getField(f);
            for (int i=0; i<reps.length; i++) {
               //Consider only 5 components and 5 subcomponents
               for (int j=1; j<=5; j++) {
                   for (int k=1; k<=5; k++) {
                       out.set(qpdOut, f, i, j, k, in.get(qpdIn, f, i, j, k));
                   }
               }
            }
          }
    }
}
