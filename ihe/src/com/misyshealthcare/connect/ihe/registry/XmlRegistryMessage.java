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
package com.misyshealthcare.connect.ihe.registry;

/**
 * This class is the root of all XDS ebXML Registry Messages.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 5, 2005
 */
public abstract class XmlRegistryMessage {

	/* ebXML Namespaces used in ebXML Registry messages */
	static final String XDS_REGISTRY_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:registry:xsd:2.1";
	static final String XDS_QUERY_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:query:xsd:2.1" ;
	static final String XDS_RIM_NAMESPACE = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1";

}
