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
package com.misyshealthcare.connect.base.demographicdata;

import java.util.ArrayList;
import java.util.List;

import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.util.StringUtil;

public class AuthorDescriptor {
    private Provider authorPerson = null;
    private List<String> authorInstitutions = new ArrayList<String>();
    private List<String> authorRoles = new ArrayList<String>();
    private List<String> authorSpecialities = new ArrayList<String>();

    public Provider getAuthorPerson() {
        return this.authorPerson;
    }
    public void setAuthorPerson(Provider authorPerson) {
        this.authorPerson = authorPerson;
    }

    public List<String> getAuthorInstitutions() {
        return this.authorInstitutions;
    }
    public void setAuthorInstitutions(List<String> authorInstitutions) {
        this.authorInstitutions = authorInstitutions;
    }
    public void addAuthorInstitution(String authorInstitution) {
        if ( StringUtil.goodString(authorInstitution) )
            this.authorInstitutions.add( authorInstitution );
    }

    public List<String> getAuthorRoles() {
        return this.authorRoles;
    }
    public void setAuthorRoles(List<String> authorRoles) {
        this.authorRoles = authorRoles;
    }
    public void addAuthorRole(String authorRole) {
        if ( StringUtil.goodString(authorRole) )
             this.authorRoles.add( authorRole );
    }

    public List<String> getAuthorSpecialities() {
        return this.authorSpecialities;
    }
    public void setAuthorSpecialities(List<String> authorSpecialties) {
        this.authorSpecialities = authorSpecialties;
    }
    public void addAuthorSpeciality(String authorSpeciality) {
        if ( StringUtil.goodString(authorSpeciality) )
            this.authorSpecialities.add( authorSpeciality );
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof AuthorDescriptor)) return false;

        final AuthorDescriptor otherAuthor = (AuthorDescriptor) other;
        if (authorPerson!=null ? !authorPerson.equals(otherAuthor.authorPerson) : otherAuthor != null) return false;
        if (!authorInstitutions.equals(otherAuthor.authorInstitutions)) return false;
        if (!authorRoles.equals(otherAuthor.authorRoles)) return false;
        if (!authorSpecialities.equals(otherAuthor.authorSpecialities)) return false;

        return true;
    }

    public int hashCode() {
        int result =1;
        result = 31 * result + (authorPerson==null? 0 : authorPerson.hashCode() );
        result = 31 * result + (authorInstitutions ==null ? 0 : authorInstitutions.hashCode());
        result = 31 * result + (authorRoles==null? 0 : authorRoles.hashCode());
        result = 31 * result + (authorSpecialities==null ? 0 : authorSpecialities.hashCode());
        return result;
    }
}
