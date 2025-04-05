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

//import com.sun.tools.xjc.generator.validator.StringOutputStream;
//import com.sun.xml.bind.StringInputStream;

import java.io.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.log4j.Logger;

/**
 * This class provides the utility to encode and decode an input source, and output the result to a
 * variety of different formats. The encoding decoding scheme is Base64 algorithm.
 *
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 22, 2005
 */
public class Base64EncoderDecoder {
    static BASE64Encoder encoder = new BASE64Encoder();
    static BASE64Decoder decoder = new BASE64Decoder();
    static Logger log = Logger.getLogger(Base64EncoderDecoder.class);
    /**
     *  Encodes a file content to a String output using base64 encoding
     *
     * @param file the file whose content is to be encoded
     * @return a Base64 encoded string
     * @throws IOException
     */
    public static String encode(File file) throws IOException {
        return encode(new FileInputStream(file));
    }

    /**
     *  Encodes an inputstream content to a String output using base64 encoding
     *
     * @param inputStream the input stream whose content is to be encoded
     * @return a Base64 encoded string
     * @throws IOException
     */
    public static String encode(InputStream inputStream) throws IOException {
       //StringWriter sw = new StringWriter();
       BufferedInputStream is = null;
       ByteArrayOutputStream os = null;
       try {
           is = new BufferedInputStream(inputStream);
           os = new ByteArrayOutputStream();
           encoder.encode(is, os);
       } finally {
           try {
               if (is != null)
                is.close();
               if( os != null)
                os.close();
           } catch (IOException e) {
               log.error("Fail to close IO resource", e);
           }
       }
       return os.toString();//sw.getBuffer().toString();
   }
    /**
     * Decodes a string using Base64, and output the result to a file.
     *
     * @param input the string to be decoded using Base64
     * @param output the output file name of decoded content.
     * @throws IOException
     */
   public static void decode(String input, File output) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new BufferedInputStream(new ByteArrayInputStream(input.getBytes()));
            os = new BufferedOutputStream(new FileOutputStream( output ));
            decoder.decodeBuffer(is, os);
        } finally {
           try {
               if (is != null)
                is.close();
               if( os != null)
                os.close();
           } catch (IOException e) {
               log.error("Fail to close IO resource", e);
           }
       }
   }

    /**
     * Decodes a string using Base64, and output the result to an OutputStream.
     *
     * @param input the string to be decoded using Base64
     * @param output the output stream to which the decoded content is to be returned.
     * @throws IOException
     */
   public static void decode(String input, OutputStream output) throws IOException {
        InputStream is = null;
        try {
            is = new BufferedInputStream(new ByteArrayInputStream(input.getBytes()));
            decoder.decodeBuffer(is, output);
        } finally {
           try {
               if (is != null)
                is.close();
           } catch (IOException e) {
               log.error("Fail to close IO resource", e);
           }
       }
   }

    /**
     * Decodes a string using Base64, and output the result to a String.
     *
     * @param input the string to be decoded using Base64
     * @return the decode string value
     * @throws IOException
     */
   public static String decode(String input) throws IOException {
        InputStream is = null;
        OutputStream os = null;
      //  StringWriter sw = new StringWriter();
        try {
            is = new BufferedInputStream(new ByteArrayInputStream(input.getBytes()));
            os = new ByteArrayOutputStream();
            decoder.decodeBuffer(is, os);
        } finally {
           try {
               if (is != null)
                is.close();
               if (os != null)
                os.close();
           } catch (IOException e) {
               log.error("Fail to close IO resource", e);
           }
       }
       return os.toString();//sw.toString();
   }

}
