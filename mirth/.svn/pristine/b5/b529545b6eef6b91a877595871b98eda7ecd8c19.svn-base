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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.util.List;
import java.util.ArrayList;

/**
 * Class to hold information about intended recipients of XDR (or XDS offline) messages.
 *
 * An intended recipient may have either an institution or person.  It should have both if
 * it is a person.  Lists of recipients should have one institution recipient without a person
 * to default to.
 *
 * @author Josh Flachsbart
 * @version 1.0, Nov 29, 2006
 */
public class XdrIntendedRecipient {
	private String recipientPerson = null;
	private String recipientInstitution = null;

	public XdrIntendedRecipient(String recipientPerson, String recipientInstitution) {
		this.recipientPerson = recipientPerson;
		this.recipientInstitution = recipientInstitution;		
	}
	
	public XdrIntendedRecipient(String codedRecipient) {
		String [] recipientParts = codedRecipient.split("\\|");
		if (recipientParts.length == 1) recipientPerson = "";
		else recipientPerson = recipientParts[1];
		recipientInstitution = recipientParts[0];
	}
	
	public boolean recipientIsPerson() {
		boolean isPerson = false;
		if (recipientPerson != null && recipientPerson.length() > 0) isPerson = true;
		return isPerson;
	}
	
	public String getRecipientPerson() {
		return this.recipientPerson;
	}

	public String getRecipientInstitution() {
		String institutionString = "";
		if (recipientInstitution != null) institutionString = recipientInstitution;
		return institutionString;
	}

	public String getCodedValue() {
		String codedValue = getRecipientInstitution();
		if (recipientIsPerson()) codedValue += "|" + getRecipientPerson();
		return codedValue;
	}
	
	/**
	 * Initialize an XdrIntendedRecipient object from an intended recipient classification Element.
	 *
	 * @param root The recipient
	 * @param rimNameSpace
	 * @return The representation of the intended recipient.
	 */
	static List<XdrIntendedRecipient> getIntendedRecipientsFromClassificationElements(Element root, String rimNameSpace) {
		List<XdrIntendedRecipient> recipients = new ArrayList<XdrIntendedRecipient>();
		NodeList nodes = RimXml.getRimSlots(root, rimNameSpace);
		for (int j =0; j <nodes.getLength(); j++) {
			Node node = nodes.item(j);
			if (node instanceof Element) {
				Element slot = (Element)node;
				String name = RimXml.getAttributeValue(slot, "name");
				if (name.equalsIgnoreCase("intendedRecipient")) {
					List<String> recipientStrings = RimXml.getRimSlotValues(slot, rimNameSpace);
					for (String recipientString: recipientStrings) {
						recipients.add(new XdrIntendedRecipient(recipientString));
					}
				}
			}
		}
		return recipients;
	}

}
