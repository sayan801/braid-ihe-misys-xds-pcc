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

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.sun.xml.bind.GrammarImpl;
import com.sun.xml.bind.Messages;
import com.sun.xml.bind.ProxyGroup;

/**
 * This class is a facade to a collection of GrammarInfo objects.  It
 * dispatches rootElement requests to the underlying GrammarInfo objects.
 *
 * @version $Revision: 1.1 $
 */
class GrammarInfoFacade implements GrammarInfo {

    private GrammarInfo[] grammarInfos = null;
    
    
    
    public GrammarInfoFacade( GrammarInfo[] items ) throws JAXBException {
        grammarInfos = items;

        detectRootElementCollisions( getProbePoints() );
    }

    /*
     * Gets a generated implementation class for the specified root element.
     * This method is used to determine the first object to be unmarshalled.
     */
    public UnmarshallingEventHandler createUnmarshaller(String namespaceUri, String localName, UnmarshallingContext context) {
        // find the root element among the GrammarInfos
        for( int i = 0; i < grammarInfos.length; i++ ) {
            UnmarshallingEventHandler ueh = grammarInfos[i].createUnmarshaller( namespaceUri, localName, context );
            if( ueh != null ) {
                return ueh;
            }
        }
        
        // the element was not located in any of the grammar infos...
        return null;
    }

    public Class getRootElement(String namespaceUri, String localName) {
        // find the root element among the GrammarInfos
        for( int i = 0; i < grammarInfos.length; i++ ) {
            Class c = grammarInfos[i].getRootElement( namespaceUri, localName );
            if( c != null ) {
                return c;
            }
        }
        
        // the element was not located in any of the grammar infos...
        return null;
    }

    public boolean recognize( String nsUri, String localName ) {
        for( int i = 0; i < grammarInfos.length; i++ )
            if( grammarInfos[i].recognize(nsUri, localName) )
                return true;
        return false;
    }
    
    /*
     * Return the probe points for this GrammarInfo, which are used to detect 
     * {namespaceURI,localName} collisions across the GrammarInfo's on the
     * schemaPath.  This is a slightly more complex implementation than a simple
     * hashmap, but it is more flexible in supporting additional schema langs.
     */
    public String[] getProbePoints() {
        ArrayList probePointList = new ArrayList();
        
        for( int i = 0; i < grammarInfos.length; i++ ) {
            String[] points = grammarInfos[i].getProbePoints();
            for( int j = 0; j < points.length; j++ ) {
                probePointList.add( points[j] );
            }
        }

        // once per JAXBContext creation, so it may not be worth it.
        return (String[])probePointList.toArray( new String[ probePointList.size() ] );        
    }
    
    
    /**
     * Iterate through the probe points looking for root element collisions.
     * If a duplicate is detected, then multiple root element componenets
     * exist with the same uri:localname
     */
    private void detectRootElementCollisions( String[] points ) 
    throws JAXBException {
        
        // the array of probe points contain uri:localname pairs
        for( int i = 0; i < points.length; i += 2 ) {
            // iterate over GrammarInfos - if more than one GI returns
            // a class from getRootElement, then there is a collision
            boolean elementFound = false;
            for( int j = grammarInfos.length-1; j >= 0; j -- ) {
                if( grammarInfos[j].recognize( points[i], points[i+1] ) ) {
                    if( elementFound == false ) {
                        elementFound = true;
                    } else {
                        throw new JAXBException( 
                                Messages.format( Messages.COLLISION_DETECTED,
                                        points[i], points[i+1] ) );
                    }
                }
            }
        }
    }
       
    /*
     * This static method is used to setup the GrammarInfoFacade.  It 
     * is invoked by the DefaultJAXBContextImpl constructor
     */
    static GrammarInfo createGrammarInfoFacade( String contextPath, 
                                                ClassLoader classLoader ) 
        throws JAXBException {
            
        String version=null;
        
        // array of GrammarInfo objs
        ArrayList gis = new ArrayList();

        StringTokenizer st = new StringTokenizer( contextPath, ":;" );

        // instantiate all of the specified JAXBContextImpls
        while( st.hasMoreTokens() ) {
            String targetPackage = st.nextToken();
            String objectFactoryName = targetPackage + ".ObjectFactory";
            
            try {
                JAXBContext c = (JAXBContext)Class.forName(
                        objectFactoryName, true, classLoader ).newInstance();
                
                // check version
                if( version==null )  version = getVersion(c);
                else
                if( !version.equals(getVersion(c)) )
                    throw new JAXBException( Messages.format(
                        Messages.INCOMPATIBLE_VERSION, new Object[]{
                            version,
                            c.getClass().getName(),
                            getVersion(c) } ) );
               
                // use reflection to get GrammarInfo
                Object grammarInfo = c.getClass().getField("grammarInfo").get(null);
               
                // wrap the grammarInfo into a proxy if necessary
                grammarInfo = ProxyGroup.blindWrap(
                    grammarInfo, GrammarInfo.class,
                    new Class[]{
                        GrammarInfo.class,
                        UnmarshallingContext.class,
                        UnmarshallingEventHandler.class,
                        XMLSerializer.class,
                        XMLSerializable.class,
                        NamespaceContext2.class,
                        ValidatableObject.class
                    } );
                
                gis.add( grammarInfo );
            } catch( ClassNotFoundException e ) {
                throw new NoClassDefFoundError(e.getMessage());
            } catch( Exception e ) {
                throw new JAXBException(e);
            }
        }

        if( gis.size()==1 )
            // if there's only one path, no need to use a facade.
            return (GrammarInfo)gis.get(0);
        
        return new GrammarInfoFacade( 
            (GrammarInfo[])(gis.toArray( new GrammarInfo[ gis.size() ] ) ) );
    }
    
    /**
     * Obtains a version number of the JAXB RI that has generated
     * the specified context, or null if it fails (for example
     * because it's not generated by JAXB RI.)
     * 
     * @param c
     *         an instance of a generated ObjectFactory class.
     *         This will return the version number written into
     *         the corresponding JAXBVersion class.
     */
    private static String getVersion(JAXBContext c) throws JAXBException {
        try {
            Class jaxbBersionClass = (Class)c.getClass().getField("version").get(null);
            return (String)jaxbBersionClass.getField("version").get(null);
        } catch( Throwable t ) {
            return null;
        }
    }

    public Class getDefaultImplementation( Class javaContentInterface ) {
        for( int i=0; i<grammarInfos.length; i++ ) {
            Class c = grammarInfos[i].getDefaultImplementation( javaContentInterface );
            if(c!=null)     return c;
        }
        return null;
    }

    private com.sun.msv.grammar.Grammar bgm = null;
    
    public com.sun.msv.grammar.Grammar getGrammar() throws JAXBException {
        if(bgm==null) {
            com.sun.msv.grammar.Grammar[] grammars = new com.sun.msv.grammar.Grammar[grammarInfos.length];
            
            // load al the grammars individually
            for( int i=0; i<grammarInfos.length; i++ )
                grammars[i] = grammarInfos[i].getGrammar();
            
            // connect them to each other
            for( int i=0; i<grammarInfos.length; i++ )
                if( grammars[i] instanceof GrammarImpl )
                    ((GrammarImpl)grammars[i]).connect(grammars);
            
            // take union of them
            for( int i=0; i<grammarInfos.length; i++ ) {
                com.sun.msv.grammar.Grammar n = grammars[i];
                if( bgm == null )   bgm = n;
                else                bgm = union( bgm, n );
            }
        }
        return bgm;
    }


    /**
     * Computes the union of two grammars.
     */
    private com.sun.msv.grammar.Grammar union( com.sun.msv.grammar.Grammar g1, com.sun.msv.grammar.Grammar g2 ) {
        // either g1.getPool() or g2.getPool() is OK.
        // this is just a metter of performance problem.
        final com.sun.msv.grammar.ExpressionPool pool = g1.getPool();
        final com.sun.msv.grammar.Expression top = pool.createChoice(g1.getTopLevel(),g2.getTopLevel());
        
        return new com.sun.msv.grammar.Grammar() {
            public com.sun.msv.grammar.ExpressionPool getPool() {
                return pool;
            }
            public com.sun.msv.grammar.Expression getTopLevel() {
                return top;
            }
        };
    }

    
    /**
     * @see com.sun.tools.xjc.runtime.GrammarInfo#castToXMLSerializable(java.lang.Object)
     */
    public XMLSerializable castToXMLSerializable(Object o) {
        XMLSerializable result = null;
        for( int i = 0; i < grammarInfos.length; i++ ) {
            result = grammarInfos[i].castToXMLSerializable( o );
            if( result != null ) {
                return result;
            }
        }
        return null;
    }

    /**
     * @see com.sun.tools.xjc.runtime.GrammarInfo#castToValidatableObject(java.lang.Object)
     */
    public ValidatableObject castToValidatableObject(Object o) {
        ValidatableObject result = null;
        for( int i = 0; i < grammarInfos.length; i++ ) {
            result = grammarInfos[i].castToValidatableObject( o );
            if( result != null ) {
                return result;
            }
        }
        return null;
    }
}
