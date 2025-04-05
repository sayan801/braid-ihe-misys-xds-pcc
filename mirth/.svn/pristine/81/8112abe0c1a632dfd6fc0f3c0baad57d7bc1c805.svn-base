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

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public class XmlCustomizedDataContentHandler implements DataContentHandler {
	private static Logger log = Logger.getLogger(XmlCustomizedDataContentHandler.class.getName() );
	public final String STR_SRC = "javax.xml.transform.stream.StreamSource";
	private static Class streamSourceClass = null;
   
	private static Transformer transformer = null;
	 static {
         String version = System.getProperty("java.vm.version");
         String vendor = System.getProperty("java.vm.vendor");
         Class c = null;
         try{ 
	         if (vendor.startsWith("Sun") && version.startsWith("1.6"))	        	 
	        	 c = Class.forName("com.sun.xml.internal.messaging.saaj.util.transform.EfficientStreamingTransformer"); 
	         else if (vendor.startsWith("Sun") &&  version.startsWith("1.5"))
	        	 c = Class.forName("com.sun.xml.messaging.saaj.util.transform.EfficientStreamingTransformer");
	         else 
	        	 log.error("Unsported JVM. Use Jdk1.5 or Jdk1.6");
	         
	         Method method = c.getMethod("newTransformer");
	         transformer = (Transformer)method.invoke(c);
         } catch(ClassNotFoundException e) {
        	 log.error("Fail to initialize transformer", e);
         } catch(NoSuchMethodException e) {
        	 log.error("Fail to initialize transformer", e);
         } catch(InvocationTargetException e) {
        	 log.error("Fail to initialize transformer", e);
         } catch(IllegalAccessException e) {
        	 log.error("Fail to initialize transformer", e);
         }
	 }
	 
    public XmlCustomizedDataContentHandler() throws ClassNotFoundException {
        if (streamSourceClass == null) {
             streamSourceClass = Class.forName(STR_SRC);
        }
    }

	/**
     * return the DataFlavors for this <code>DataContentHandler</code>
     * @return The DataFlavors.
     */
    public DataFlavor[] getTransferDataFlavors() { // throws Exception;
        DataFlavor flavors[] = new DataFlavor[2];
 
        flavors[0] = new ActivationDataFlavor(streamSourceClass,
                        "text/xml", "XML");
        flavors[1] = new ActivationDataFlavor(streamSourceClass,
                    "application/xml", "XML");
        return flavors;
    }

	
    /**
     * return the Transfer Data of type DataFlavor from InputStream
     * @param df The DataFlavor.
     * @param ins The InputStream corresponding to the data.
     * @return The constructed Object.
     */
     public Object getTransferData(DataFlavor flavor,
             DataSource dataSource) throws IOException {
         if (flavor.getMimeType().startsWith("text/xml") ||
             flavor.getMimeType().startsWith("application/xml")) {
             if (flavor.getRepresentationClass().getName().equals(STR_SRC)) {
                 return new StreamSource(dataSource.getInputStream());
             }
         }
         return null;
     }
	
	
	public Object getContent(DataSource dataSource) throws IOException {
        return new StreamSource(dataSource.getInputStream());
    }

	
    public void writeTo(Object obj, String mimeType, OutputStream outputstream)
    throws IOException
   {      //Handles the special long content type 
    	 if (!mimeType.equals("text/xml") && !mimeType.equals("application/xml") &&
    	     !mimeType.equalsIgnoreCase("text/xml; charset=utf-8") &&
    	     !mimeType.equalsIgnoreCase("application/xml; charset=utf-8"))
                   throw new IOException("Invalid content type \"" + mimeType
                           + "\" for XmlDCH");
    	 
    	 try
        {
            StreamResult streamresult = new StreamResult(outputstream);
            if(obj instanceof DataSource)
                transformer.transform((Source)getContent((DataSource)obj), streamresult);
            else if(obj instanceof String) {
            	OutputStreamWriter osw = new OutputStreamWriter(outputstream);
            	osw.write((String)obj);
            	osw.flush(); 	
            }	            	 
            else
                transformer.transform((Source)obj, streamresult);
        }
        catch(Exception exception)
        {
            throw new IOException((new StringBuilder()).append("Unable to run the JAXP transformer on a stream ").append(exception.getMessage()).toString());
        }	    	
    		       	
   }
}
