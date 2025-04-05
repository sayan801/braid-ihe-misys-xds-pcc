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

import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;


/**
 * The XML utility methods.
 *
 * @author Wenzhi Li
 * @version 2.0, May 26, 2006
 */
public class XmlUtil {
	private static final Logger LOGGER = Logger.getLogger(XmlUtil.class);
	
	public final static String SunJDK5MessageFactory = "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl";
	public final static String SunJDK6MessageFactory = "com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl";
	
    public final static String SunJDK5SOAPFactory    = "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl";
    public final static String SunJDK6SOAPFactory    = "com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl";

	private static String JVMVersion = System.getProperty("java.vm.version");
    private static String JVMVendor = System.getProperty("java.vm.vendor");

    /**
     * Formats a node to a pretty xml string.
     *
     * @param node the node to be formatted
     * @return a String representation of the node.
     */
    public static String formatNode(Node node){
        if (null == node ) return null;

        DOMSource source = new DOMSource(node);
        StreamResult result = new StreamResult(new StringWriter());
        //TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            try {
                // Try this to indent the XML output, it works for some XML implementors
                factory.setAttribute("indent-number", new Integer(2));
            } catch (Exception e) {}
            transformer = factory.newTransformer();
            try {
                // Try this to indent the XML output, it works for some other XML implementors
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            } catch (Exception e) {}
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/xml");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getWriter().toString();
    }

    /**
     * Format a SOAP message to a pretty String.
     *
     * @param message The SOAPMessage to be formatted
     */
    public static String formatSoapMessage(SOAPMessage message) {
        if (message == null) return null;

        StringBuffer ret = new StringBuffer();
        // Write out the SOAP part of the message
        DOMSource source = new DOMSource(message.getSOAPPart());
        StreamResult result = new StreamResult(new StringWriter());
        Transformer transformer = null;
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            try {
                // Try this to indent the XML output, it works for some XML implementors
                factory.setAttribute("indent-number", new Integer(2));
            } catch (Exception e) {}
            transformer = factory.newTransformer();
            try {
                // Try this to indent the XML output, it works for some other XML implementors
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            } catch (Exception e) {}
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/xml");
            transformer.transform(source, result);
            ret.append(result.getWriter().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Write out information about the attachments
        Iterator attachments = message.getAttachments();
        while (attachments.hasNext()) {
            AttachmentPart attachment = (AttachmentPart)attachments.next();
            ret.append("Attachment: " + attachment.getContentId() + ", " + attachment.getContentType());
        }
        return ret.toString();
    }


    /**
     * Creates a new SAAJ Message Factory based on the given factory name.
     *
     * @param factoryName the factory name
     * @return an object of MessageFactory
     * @throws SOAPException
     */
    public static MessageFactory newMessageFactoryInstance(String factoryName) throws SOAPException
    {
        if (factoryName == null || factoryName.trim().length() <= 0) {
            throw new IllegalArgumentException("Invalid factory name");
        }
        //The message factory library path is different between jdk 1.5 and jdk 1.6. 
        if (JVMVendor.startsWith("Sun") && JVMVersion.startsWith("1.5") &&
        	factoryName.equals(SunJDK6MessageFactory)) {
        		factoryName = SunJDK5MessageFactory;
        	}

        try {
            Class clsImpl = Class.forName(factoryName);
            return (MessageFactory)clsImpl.newInstance();
        } catch(ClassNotFoundException cnfex) {
        	LOGGER.error("ClassNotFoundException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, cnfex);
        } catch(InstantiationException iex) {
        	LOGGER.error("InstantiationException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, iex);
        } catch(IllegalAccessException iaex) {
        	LOGGER.error("IllegalAccessException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, iaex);
        } catch(ClassCastException ccex) {
        	LOGGER.error("ClassCastException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, ccex);
        }

    }

    /**
     * Creates a new SAAJ SOAPConnection Factory based on the given factory name.
     *
     * @param factoryName the factory name
     * @return an object of SOAPConnectionFactory
     * @throws SOAPException
     */
     public static SOAPConnectionFactory newSOAPConnectionFactoryInstance(String factoryName) throws SOAPException {
        if (factoryName == null || factoryName.trim().length() <= 0) {
            throw new IllegalArgumentException("Invalid factory name");
        }

        try {
            Class clsImpl = Class.forName(factoryName);
            return (SOAPConnectionFactory)clsImpl.newInstance();
        } catch(ClassNotFoundException cnfex) {
        	LOGGER.error("ClassNotFoundException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, cnfex);
        } catch(InstantiationException iex) {
        	LOGGER.error("InstantiationException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, iex);
        } catch(IllegalAccessException iaex) {
        	LOGGER.error("IllegalAccessException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, iaex);
        } catch(ClassCastException ccex) {
        	LOGGER.error("ClassCastException Unable to create message factory");
            throw new SOAPException("Unable to create message factory using " + factoryName, ccex);
        }
    }
     
     public static SOAPFactory newSOAPFactoryInstance(String factoryName) throws SOAPException {
         if (factoryName == null || factoryName.trim().length() <= 0) {
             throw new IllegalArgumentException("Invalid SOAP factory name");
         }
         //The SOAPFactory library path is different between jdk 1.5 and jdk 1.6. 
         if (JVMVendor.startsWith("Sun") && JVMVersion.startsWith("1.5") &&
         	factoryName.equals(SunJDK6SOAPFactory)) {
         		factoryName = SunJDK5SOAPFactory;
         	}
         
         try {
            Class clsImpl = Class.forName(factoryName);
            return (SOAPFactory)clsImpl.newInstance();  
 		} catch (InstantiationException e) {
        	LOGGER.error("InstantiationException Unable to create soap factory");
            throw new SOAPException("Unable to create soap factory using " + factoryName, e);
 		} catch (IllegalAccessException e) {
        	LOGGER.error("IllegalAccessException Unable to create soap factory");
            throw new SOAPException("Unable to create soap factory using " + factoryName, e);
 		} catch (ClassNotFoundException e) {
        	LOGGER.error("ClassNotFoundException Unable to create soap factory");
            throw new SOAPException("Unable to create soap factory using " + factoryName, e);
 		}    
     }
}
