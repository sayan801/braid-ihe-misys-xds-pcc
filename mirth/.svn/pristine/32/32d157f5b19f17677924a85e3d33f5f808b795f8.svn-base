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
package com.misyshealthcare.connect.net;

import java.util.List;

import junit.framework.TestCase;

import com.misyshealthcare.connect.base.SharedEnums;

public class StandardConnectionDescriptionTest extends TestCase {
	protected StandardConnectionDescription scd = null;
	protected static final String name = "The name";
	protected static final int port = 1234;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUp();
		scd = new StandardConnectionDescription();
		scd.setHostname(name);
		scd.setPort(port);
		scd.complete();
	}
	
	/** Test method to see whether the factory works. */
	public void testGetWithFactory() {
		ConnectionFactory.loadConnectionDescriptionsFromFile(StandardConnectionDescriptionTest.class.getResource("testdata/testConfig.xml").getPath());
		IConnectionDescription cd = ConnectionFactory.getConnectionDescription("Standard");
		super.assertTrue(cd instanceof StandardConnectionDescription);
		super.assertEquals(cd.getHostname(), "127.0.0.1");
		super.assertEquals(cd.getPort(), 5000);
		super.assertEquals(cd.getUrlPath(), "/index.html");
		super.assertFalse(cd.isSecure());
		CodeSet scheme = cd.getCodeSet("testCodes.Size");
		super.assertNotNull(scheme);
		super.assertEquals(2, scheme.size());
		super.assertEquals("Big", scheme.getDisplayName("1"));
		super.assertEquals("Small", scheme.getDisplayName("2"));
		super.assertEquals("Test size codes", scheme.getCodingScheme("1"));
		scheme = cd.getCodeSet("testCodes.Color");
		super.assertNotNull(scheme);
		super.assertEquals(3, scheme.size());
		super.assertEquals("Red", scheme.getDisplayName("1"));
		super.assertEquals("Green", scheme.getDisplayName("2"));
		super.assertEquals("Blue", scheme.getDisplayName("3"));
		super.assertEquals("Test color codes", scheme.getCodingScheme("3"));
		scheme = cd.getCodeSet("contentTypeCode");
		super.assertNotNull(scheme);
		super.assertEquals("urn:uuid:aa543740-bdda-424e-8c96-df4873be8500", scheme.getClassificationScheme());
		super.assertEquals(28, scheme.size());
		super.assertEquals("Autopsy", scheme.getDisplayName("Autopsy"));
		EnumMap emap = cd.getEnumMap(SharedEnums.XdsContentCode.class);
		assertNotNull(emap);
		assertEquals(2, emap.size());
		assertFalse(emap.containsEnumValue(SharedEnums.XdsContentCode.UNKNOWN));
		assertEquals("d.s", emap.getCodeValue(SharedEnums.XdsContentCode.Discharge_Summarization));
		assertEquals("r.s", emap.getCodeValue(SharedEnums.XdsContentCode.Transfer_Summarization));
		StringMap smap = cd.getStringMap("sourceId");
		assertNotNull(smap);
		assertEquals(1, smap.size());
		assertTrue(smap.containsStringValue("Misys"));
		assertEquals("123.456", smap.getCodeValue("Misys"));
		Identifier authority = cd.getIdentifier("AssigningAuthority");
		assertNotNull(authority);
		assertEquals("MISYS", authority.getNamespaceId());
		assertEquals("1.2.3.4.5", authority.getUniversalId());
		assertEquals("ISO", authority.getUniversalIdType());
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.StandardConnectionDescription.StandardConnectionDescription(String, String)'
	 */
	public void testStandardConnectionDescriptionStringString() {
		scd = new StandardConnectionDescription();
		scd.setHostname(name);
		scd.complete();
		super.assertNotNull(scd);
		testGetHostname();
		testIsSecure();
		super.assertEquals("Defaut port should be -1.", scd.getPort(), -1);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.StandardConnectionDescription.getHostname()'
	 */
	public void testGetHostname() {
		super.assertEquals(scd.getHostname(), name);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.StandardConnectionDescription.getPort()'
	 */
	public void testGetPort() {
		super.assertEquals(scd.getPort(), port);
	}

	/*
	 * Test method for 'com.misyshealthcare.connect.net.StandardConnectionDescription.isSecure()'
	 */
	public void testIsSecure() {
		super.assertFalse(scd.isSecure());
	}
	
	public void testLoadConfiguration() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		scd.setHostname(name);
		scd.loadConfiguration(StandardConnectionDescriptionTest.class.getResource("testdata/testCodes.xml").getPath());
		scd.complete();
		super.assertNotNull(scd);
		testGetHostname();
		testIsSecure();
		super.assertEquals("Defaut port should be -1.", scd.getPort(), -1);
		CodeSet scheme = scd.getCodeSet("contentTypeCode");
		super.assertNotNull(scheme);
		super.assertEquals("urn:uuid:aa543740-bdda-424e-8c96-df4873be8500", scheme.getClassificationScheme());
		super.assertEquals(28, scheme.size());
		super.assertEquals("Autopsy", scheme.getDisplayName("Autopsy"));
		scheme = scd.getCodeSet("mimeType");
		super.assertNotNull(scheme);
		super.assertNull(scheme.getClassificationScheme());
		super.assertEquals(7, scheme.size());
		super.assertTrue(scheme.containsCode("application/pdf"));		
	}

	public void testAddCodeSet() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		CodeSet codes = new CodeSet("codes", "test");
		scd.addCodeSet(codes);
		assertSame(codes, scd.getCodeSet("codes"));
	}
	
	public void testSetProperty() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		scd.setProperty("prop1", "value1");
		assertEquals("value1", scd.getProperty("prop1"));
		scd.setProperty("prop2", "value2");
		assertEquals("value1", scd.getProperty("prop1"));
		assertEquals("value2", scd.getProperty("prop2"));
		scd.setProperty("prop3", "value3");
		assertEquals("value1", scd.getProperty("prop1"));
		assertEquals("value2", scd.getProperty("prop2"));
		assertEquals("value3", scd.getProperty("prop3"));
		scd.setProperty("prop2", null);
		assertEquals("value1", scd.getProperty("prop1"));
		assertEquals("value3", scd.getProperty("prop3"));
		assertNull(scd.getProperty("prop2"));
	}
	
	public void testAddEnumMap() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		EnumMap map = null;
		try {
			map = new EnumMap("XdsContentCode");
		} catch (ClassNotFoundException e) {
			fail(e.toString());
		}
		scd.addEnumMap(map);
		assertSame(map, scd.getEnumMap(SharedEnums.XdsContentCode.class));
		map.addEntry("Discharge_Summarization", "Discharge");
		assertTrue(map.containsEnumValue(SharedEnums.XdsContentCode.Discharge_Summarization));
		assertEquals("Discharge", map.getCodeValue(SharedEnums.XdsContentCode.Discharge_Summarization));
	}
	
	public void testAddStringMap() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		StringMap map = new StringMap("foo");
		scd.addStringMap(map);
		assertSame(map, scd.getStringMap("foo"));
		map.addEntry("DISCHARGE_SUMMARY", "Discharge");
		assertTrue(map.containsStringValue("DISCHARGE_SUMMARY"));
		assertEquals("Discharge", map.getCodeValue("DISCHARGE_SUMMARY"));
	}

	public void testAddPropertySet() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		PropertySet map = new PropertySet("foo");
		scd.addPropertySet(map);
		assertSame(map, scd.getPropertySet("foo"));
		map.addValue("id", "1");
		map.addValue("domain", "misys");
		assertTrue(map.containsValue("id"));
		assertEquals("1", map.getValue("id"));
		assertTrue(map.containsValue("domain"));
		assertEquals("misys", map.getValue("domain"));
	}

	public void testAddAssigningAuthority() {
		StandardConnectionDescription scd = new StandardConnectionDescription();
		Identifier authority = new Identifier("MISYS", "1.2.3.4.5", "ISO");
		scd.addIdentifier("foo", "test", authority);
		assertSame(authority, scd.getIdentifier("foo"));
		assertEquals("MISYS", authority.getNamespaceId());
		assertEquals("1.2.3.4.5", authority.getUniversalId());
		assertEquals("ISO", authority.getUniversalIdType());
		List<Identifier> ids = scd.getAllIdentifiersByType("test");
		assertEquals(1,  ids.size());
		assertEquals("MISYS",  ids.get(0).getNamespaceId());
		
	}

}
