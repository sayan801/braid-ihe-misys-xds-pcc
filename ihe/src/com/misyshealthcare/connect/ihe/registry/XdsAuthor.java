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

import com.misyshealthcare.connect.base.demographicdata.AuthorDescriptor;
import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.ihe.configuration.Configuration;
import com.misyshealthcare.connect.ihe.configuration.IheConfigurationException;
import com.misyshealthcare.connect.net.IConnectionDescription;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Description of an author for use by XDS style messages.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 8, 2006
 */
public class XdsAuthor {
    private String authorPerson = null;
    private List<String> authorInstitutions = new ArrayList<String>();
    private List<String> authorRoles = new ArrayList<String>();
    private List<String> authorSpecialities = new ArrayList<String>();

    public String getAuthorPerson() {
        return this.authorPerson;
    }
    public void setAuthorPerson(String authorPerson) {
        this.authorPerson = authorPerson;
    }

    public List<String> getAuthorInstitutions() {
        return this.authorInstitutions;
    }
    public void addAuthorInstitution(String authorInstitution) {
        this.authorInstitutions.add( authorInstitution );
    }

    public List<String> getAuthorRoles() {
        return this.authorRoles;
    }
    public void addAuthorRole(String authorRole) {
        this.authorRoles.add( authorRole );
    }

    public List<String> getAuthorSpecialities() {
        return this.authorSpecialities;
    }
    public void addAuthorSpeciality(String authorSpecialty) {
        this.authorSpecialities.add( authorSpecialty );
    }

    /**
     * Get a map of author information including authorInstitute, authorRole and authorSpeciality.
     *
     * @return The map
     */
    public Map<String, List<String>> getAuthorInfo() {
        Map<String, List<String>> authorInfo = new HashMap<String, List<String>>();
        List<String> institutes = this.getAuthorInstitutions();
        if (institutes != null && institutes.size() > 0) {
            authorInfo.put("authorInstitution", institutes);
        }
        List<String> roles = this.getAuthorRoles();
        if (roles != null && roles.size() > 0) {
            authorInfo.put("authorRole", roles);
        }
        List<String> specialities = this.getAuthorSpecialities();
        if (specialities != null && specialities.size() > 0) {
            authorInfo.put("authorSpecialty", specialities);
        }
        return authorInfo;
    }

    /**
     * Initialize an XdsAuthor object from an author classification Element.
     *
     * @param root The author classification
     * @param rimNameSpace
     * @return an XdsAuthor object
     */
    static XdsAuthor getAuthorFromClassificationElements(Element root, String rimNameSpace) {
        XdsAuthor author = new XdsAuthor();
        NodeList nodes = RimXml.getRimSlots(root, rimNameSpace);
        for (int j =0; j <nodes.getLength(); j++) {
            Node node = nodes.item(j);
            if (node instanceof Element) {
                Element slot = (Element)node;
                String name = RimXml.getAttributeValue(slot, "name");
                if (name.equalsIgnoreCase("authorPerson")) {
                    String authorPerson = RimXml.getRimSlotValue(slot, rimNameSpace);
                    author.setAuthorPerson( authorPerson );
                } else if (name.equalsIgnoreCase("authorInstitution")) {
                    List<String> authorInstitutes = RimXml.getRimSlotValues(slot, rimNameSpace);
                    if (authorInstitutes != null) {
                        for (String institue : authorInstitutes) author.addAuthorInstitution(institue);
                    }
                } else if (name.equalsIgnoreCase("authorRole")) {
                    List<String> authorRoles = RimXml.getRimSlotValues(slot, rimNameSpace);
                    if (authorRoles != null) {
                        for (String role : authorRoles) author.addAuthorRole(role);
                    }
                } else if (name.equalsIgnoreCase("authorSpecialty")) {
                    List<String> authorSpecialities = RimXml.getRimSlotValues(slot, rimNameSpace);
                    if (authorSpecialities != null) {
                        for (String speciality : authorSpecialities) author.addAuthorSpeciality(speciality);
                    }
                }
            }
        }
        return author;
    }
    
    /**
     * Initalize and XdsAuthor from an author descriptor.
     *
     * @param authorDescriptor The AuthorDescriptor to be converted
     * @param connection The connection to get the string maps from.
     * @return An XdsAuthor object
     * @throws IheConfigurationException
     */
    static public XdsAuthor getAuthorFromDescriptor(AuthorDescriptor authorDescriptor, IConnectionDescription connection) throws IheConfigurationException  {
        XdsAuthor author = new XdsAuthor();
        Provider authorPerson = authorDescriptor.getAuthorPerson();
        if (authorPerson != null) {
            author.setAuthorPerson( HL7.toXCN(authorPerson) );
        }
        List<String> institutes = authorDescriptor.getAuthorInstitutions();
        for (String authorInstitute : institutes) {
            author.addAuthorInstitution(HL7.toXON( authorInstitute ));
        }
        List<String> roles = authorDescriptor.getAuthorRoles();
        for (String authorRole : roles) {
            author.addAuthorRole(Configuration.applyStringMap(connection, authorRole, "authorRole", false));
        }
        List<String> specialities = authorDescriptor.getAuthorSpecialities();
        for (String authorSpeciality : specialities) {
            author.addAuthorSpeciality(Configuration.applyStringMap(connection, authorSpeciality, "authorSpecialty", false));
        }
        return author;
    }
}
