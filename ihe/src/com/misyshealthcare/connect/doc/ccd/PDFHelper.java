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


package com.misyshealthcare.connect.doc.ccd;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.log4j.Logger;
import hl7OrgV3.ClinicalDocumentDocument1;

import java.io.*;

import com.misyshealthcare.connect.util.Base64EncoderDecoder;
import com.misyshealthcare.connect.util.StringUtil;

/**
 * This class provides some help methods to process CDA wrapped PDF files.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 22, 2005
 */
public class PDFHelper {
    private static final Logger log = Logger.getLogger(PDFHelper.class);
    private static final String nsDeclare = "declare namespace ns='urn:hl7-org:v3';";
    private static final String PDF_MEDIA_TYPE="application/pdf";

    /**
     * Tests whether or not a CDA document InputStream contains a Base64 encoded PDF.
     * @param inputCDA the InputStream of CDA wrapped PDF file
     * @return <code>true</code> if the CDA InputStream contains PDF content; <code>false</code> otherwise.
     * @throws CDAException
     */
    public static boolean containsPDF(InputStream inputCDA) throws CCDException {
        ClinicalDocumentDocument1 doc1 = getDoc(inputCDA);
        XmlCursor pathCursor = doc1.newCursor();
        String mediaType = selectPath(pathCursor, "ns:component/ns:nonXMLBody/ns:text/@mediaType");
        String encoding = selectPath(pathCursor, "ns:component/ns:nonXMLBody/ns:text/@representation");
        if ( null != mediaType && mediaType.equalsIgnoreCase(PDF_MEDIA_TYPE) &&
             null != encoding  && encoding.equals("B64"))
            return true;
        else
            return false;
    }

    /**
     * Extracts the encoded PDF content from a CDA document, and decodes the PDF.
     *
     * @param inputCDA the InputStream which is the source of the CDA document
     * @param os the OutputStream where the decoded PDF is to be placed.
     * @throws CDAException if the CDA document does not contain a valid PDF content or there is a decoding error.
     */
    public static void extractAndDecodePDFFromCDA(InputStream inputCDA, OutputStream os) throws CCDException {
        ClinicalDocumentDocument1 doc1 = getDoc(inputCDA);
        XmlCursor pathCursor = doc1.newCursor();
        String rawPDF = selectPath(pathCursor, "ns:component/ns:nonXMLBody/ns:text");
        rawPDF = rawPDF.replaceAll("\\s", "");    //remove carrage return characters
        if (!StringUtil.goodString(rawPDF)) {
            throw new CCDException("Input CDA does not contain valid PDF content");
        }
        try {
            Base64EncoderDecoder.decode(rawPDF,  os);
        } catch (IOException e) {
            throw new CCDException(e);
        }
    }

   //Gets the doc from an InputStream
   private static ClinicalDocumentDocument1 getDoc(InputStream inputCDA) throws CCDException {
        ClinicalDocumentDocument1 doc1 = null;
        try {
            doc1 = ClinicalDocumentDocument1.Factory.parse(inputCDA);
        } catch (XmlException e) {
            throw new CCDException(e);
        } catch (IOException e) {
            throw new CCDException(e);
        }
       return doc1;
   }

    //Select a path, and retrieve its value
    private static String selectPath(XmlCursor pathCursor, String path) {
        String ret = null;
        pathCursor.push();
        pathCursor.selectPath(nsDeclare + "$this/ns:ClinicalDocument/" + path);
        if (pathCursor.getSelectionCount() > 0) {
            pathCursor.toNextSelection();
            ret = pathCursor.getTextValue();
            log.debug("path=" + path + ", value="  + ret );
        }
        pathCursor.pop();

        return ret;
    }

}
