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
package com.misyshealthcare.connect.base;

import java.util.ArrayList;
import java.util.List;

public class IPatientXrefFactory {
	private static IPatientXrefFactory singleton;
	
	private IPatientXrefSource source;
	
	public static synchronized IPatientXrefFactory getInstance() {
		if (singleton == null) singleton = new IPatientXrefFactory();
		return singleton;
	}
	
	public void setSource(IPatientXrefSource inSource) {
		source = inSource;
	}
	
	public List<IPatientXref> getPatientXrefs() {
		List<IPatientXref> xrefs = null;
		if (source != null) xrefs = source.getPatientXrefs();
		else xrefs = new ArrayList();
		return xrefs;
	}
}
