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

package com.misyshealthcare.connect.ihe.audit.jaxb.impl.runtime;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.helpers.AbstractMarshallerImpl;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.JAXBAssertionError;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.DataWriter;
import com.sun.xml.bind.marshaller.DumbEscapeHandler;
import com.sun.xml.bind.marshaller.Messages;
import com.sun.xml.bind.marshaller.MinimumEscapeHandler;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.marshaller.NioEscapeHandler;
import com.sun.xml.bind.marshaller.SAX2DOMEx;
import com.sun.xml.bind.marshaller.SchemaLocationFilter;
import com.sun.xml.bind.marshaller.XMLWriter;

/**
 * Implementation of {@link Marshaller} interface for JAXB RI.
 * 
 * @author Kohsuke Kawaguchi
 * @author Vivek Pandey
 */
public class MarshallerImpl extends AbstractMarshallerImpl
{
    /** Indentation string. Default is four whitespaces. */
    private String indent = "    ";
    
    /** Used to assign prefixes to namespace URIs. */
    private NamespacePrefixMapper prefixMapper = null;
    
    /** Object that handles character escaping. */
    private CharacterEscapeHandler escapeHandler = null; 
    
    /** Whether the xml declaration will be printed or not. */
    private boolean printXmlDeclaration = true;
    
    /** XML BLOB written after the XML declaration. */
    private String header=null;
    
    /** reference to the context that created this object */
    final DefaultJAXBContextImpl context;
    
    public MarshallerImpl( DefaultJAXBContextImpl c ) {
        // initialize datatype converter with ours
        DatatypeConverter.setDatatypeConverter(DatatypeConverterImpl.theInstance);
        
        context = c;
    }
    
    public void marshal(Object obj, Result result) throws JAXBException {
        //XMLSerializable so = Util.toXMLSerializable(obj);
        XMLSerializable so = context.getGrammarInfo().castToXMLSerializable(obj);

        if(so==null)
            throw new MarshalException( 
                Messages.format( Messages.NOT_MARSHALLABLE ) );


        if (result instanceof SAXResult) {
            write(so, ((SAXResult) result).getHandler());
            return;
        }
        if (result instanceof DOMResult) {
            Node node = ((DOMResult) result).getNode();

            if (node == null) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    dbf.setNamespaceAware(true);
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.newDocument();
                    ((DOMResult) result).setNode(doc);
                    write(so, new SAX2DOMEx(doc));
                } catch (ParserConfigurationException pce) {
                    throw new JAXBAssertionError(pce);
                }
            } else {
                write(so, new SAX2DOMEx(node));
            }

            return;
        }
        if (result instanceof StreamResult) {
            StreamResult sr = (StreamResult) result;
            XMLWriter w = null;

            if (sr.getWriter() != null)
                w = createWriter(sr.getWriter());
            else if (sr.getOutputStream() != null)
                w = createWriter(sr.getOutputStream());
            else if (sr.getSystemId() != null) {
                String fileURL = sr.getSystemId();

                if (fileURL.startsWith("file:///")) {
                    if (fileURL.substring(8).indexOf(":") > 0)
                        fileURL = fileURL.substring(8);
                    else
                        fileURL = fileURL.substring(7);
                } // otherwise assume that it's a file name

                try {
                    w = createWriter(new FileOutputStream(fileURL));
                } catch (IOException e) {
                    throw new MarshalException(e);
                }
            }

            if (w == null)
                throw new IllegalArgumentException();

            write(so, w);
            return;
        }

        // unsupported parameter type
        throw new MarshalException( 
            Messages.format( Messages.UNSUPPORTED_RESULT ) );
    }
    
    private void write( XMLSerializable obj, ContentHandler writer )
        throws JAXBException {

        try {
            if( getSchemaLocation()!=null || getNoNSSchemaLocation()!=null ) {
                // if we need to add xsi:schemaLocation or its brother,
                // throw in the component to do that.
                writer = new SchemaLocationFilter(
                    getSchemaLocation(),
                    getNoNSSchemaLocation(),
                    writer );
            }
            
            SAXMarshaller serializer = new SAXMarshaller(writer,prefixMapper,this);
        
            // set a DocumentLocator that doesn't provide any information
            writer.setDocumentLocator( new LocatorImpl() );
            writer.startDocument();
            serializer.childAsBody(obj,null);
            writer.endDocument();
            
            serializer.reconcileID();   // extra check
        } catch( SAXException e ) {
            throw new MarshalException(e);
        }
    }
    
    
    //
    //
    // create XMLWriter by specifing various type of output.
    //
    //
    
    protected CharacterEscapeHandler createEscapeHandler( String encoding ) {
        if( escapeHandler!=null )
            // user-specified one takes precedence.
            return escapeHandler;
        
        if( encoding.startsWith("UTF") )
            // no need for character reference. Use the handler
            // optimized for that pattern.
            return MinimumEscapeHandler.theInstance;
        
        // otherwise try to find one from the encoding
        try {
            // try new JDK1.4 NIO
            return new NioEscapeHandler( getJavaEncoding(encoding) );
        } catch( Throwable e ) {
            // if that fails, fall back to the dumb mode
            return DumbEscapeHandler.theInstance;
        }
    }
            
    public XMLWriter createWriter( Writer w, String encoding ) throws JAXBException {
        
        // buffering improves the performance
        w = new BufferedWriter(w);
        
        CharacterEscapeHandler ceh = createEscapeHandler(encoding);
        XMLWriter xw;
        
        if(isFormattedOutput()) {
            DataWriter d = new DataWriter(w,encoding,ceh);
            d.setIndentStep(indent);
            xw=d;
        } 
        else
            xw = new XMLWriter(w,encoding,ceh);
            
        xw.setXmlDecl(printXmlDeclaration);
        xw.setHeader(header);
        return xw;
    }

    public XMLWriter createWriter(Writer w) throws JAXBException{
        return createWriter(w, getEncoding());
    }
    
    public XMLWriter createWriter( OutputStream os ) throws JAXBException {
        return createWriter(os, getEncoding());
    }
    
    public XMLWriter createWriter( OutputStream os, String encoding ) throws JAXBException {
        try {
            return createWriter(
                new OutputStreamWriter(os,getJavaEncoding(encoding)),
                encoding );
        } catch( UnsupportedEncodingException e ) {
            throw new MarshalException(
                Messages.format( Messages.UNSUPPORTED_ENCODING, encoding ),
                e );
        }
    }
    
    
    public Object getProperty(String name) throws PropertyException {
        if( INDENT_STRING.equals(name) )
            return indent;
        if( ENCODING_HANDLER.equals(name) )
            return escapeHandler;
        if( PREFIX_MAPPER.equals(name) )
            return prefixMapper;
        if( XMLDECLARATION.equals(name) )
            return printXmlDeclaration ? Boolean.TRUE : Boolean.FALSE;
        if( XML_HEADERS.equals(name) )
            return header;
        
        return super.getProperty(name);
    }

    public void setProperty(String name, Object value) throws PropertyException {
        if( INDENT_STRING.equals(name) && value instanceof String ) {
            indent = (String)value;
            return;
        }
        if( ENCODING_HANDLER.equals(name) ) {
            escapeHandler = (CharacterEscapeHandler)value;
            return;
        }
        if( PREFIX_MAPPER.equals(name) ) {
            prefixMapper = (NamespacePrefixMapper)value;
            return;
        }
        if( XMLDECLARATION.equals(name) ) {
            printXmlDeclaration = ((Boolean)value).booleanValue();
            return;
        }
        if( XML_HEADERS.equals(name) ) {
            header = (String)value;
            return;
        }
            
        super.setProperty(name, value);
    }
    
    private static final String INDENT_STRING = "com.sun.xml.bind.indentString"; 
    private static final String PREFIX_MAPPER = "com.sun.xml.bind.namespacePrefixMapper"; 
    private static final String ENCODING_HANDLER = "com.sun.xml.bind.characterEscapeHandler"; 
    private static final String XMLDECLARATION = "com.sun.xml.bind.xmlDeclaration"; 
    private static final String XML_HEADERS = "com.sun.xml.bind.xmlHeaders";
}
