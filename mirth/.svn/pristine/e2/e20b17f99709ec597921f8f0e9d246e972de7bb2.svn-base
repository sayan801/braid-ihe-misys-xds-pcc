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

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.xml.soap.SOAPException;


/**
 * CommandMap utility.
 * 
 * @author LiW
 *
 * @version 2.1 Jan 10, 2008
 */
public class CommandMapUtil {
	
   	/**
   	 * Add data content handler to the given mail map.
   	 * 
   	 * @param map the given mail map to be updated.
   	 */ 
    public static void addMailmap(CommandMap map) {
        try {
        	if (map instanceof  MailcapCommandMap) {
                MailcapCommandMap mailMap = (MailcapCommandMap) map;
                String hndlrStr = ";;x-java-content-handler=";
                mailMap
                        .addMailcap("text/xml"
                                + hndlrStr
                                + XmlCustomizedDataContentHandler.class.getName());
                mailMap
                .addMailcap("application/xml"
                        + hndlrStr
                        + XmlCustomizedDataContentHandler.class.getName());
                mailMap
                        .addMailcap("application/fastinfoset"
                                + hndlrStr
                                + "com.sun.xml.internal.messaging.saaj.soap.FastInfosetDataContentHandler");
                mailMap
                        .addMailcap("image/jpeg"
                                + hndlrStr
                                + "com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler");
                mailMap
                        .addMailcap("image/gif"
                                + hndlrStr
                                + "com.sun.xml.internal.messaging.saaj.soap.GifDataContentHandler");
                /*mailMap.addMailcap(
                    "multipart/*"
                        + hndlrStr
                        + "com.sun.xml.internal.messaging.saaj.soap.MultipartDataContentHandler");*/
                mailMap
                        .addMailcap("image/*"
                                + hndlrStr
                                + "com.sun.xml.internal.messaging.saaj.soap.ImageDataContentHandler");
                mailMap
                        .addMailcap("text/plain"
                                + hndlrStr
                                + "com.sun.xml.internal.messaging.saaj.soap.StringDataContentHandler");
            } else {
                throw new SOAPException(
                        "Default CommandMap is not a MailcapCommandMap");
            }
        } catch (Throwable t) {           
            if (t instanceof  RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getLocalizedMessage());
            }
        }
    	
    }

}
