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


package com.misyshealthcare.connect.base.clinicaldata;

import com.misyshealthcare.connect.base.Attachment;

/**
 * <Descriptions of the class>
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 8, 2005
 * @version 2.0, Oct 8, 2005
 */
public class ClinicalNotes {
    private java.util.Calendar dateTime;
    private String name;
    private String content;
    private String noteType;
    private Provider provider;
    private Attachment[] attachment;

    public ClinicalNotes() {
    }

    public ClinicalNotes(
           java.util.Calendar dateTime,
           String name,
           String content,
           String noteType,
           Provider provider,
           Attachment[] attachment) {
           this.dateTime = dateTime;
           this.name = name;
           this.content = content;
           this.noteType = noteType;
           this.provider = provider;
           this.attachment = attachment;
    }


    /**
     * Gets the dateTime value for this ClinicalNotes.
     *
     * @return dateTime
     */
    public java.util.Calendar getDateTime() {
        return dateTime;
    }


    /**
     * Sets the dateTime value for this ClinicalNotes.
     *
     * @param dateTime
     */
    public void setDateTime(java.util.Calendar dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * Gets the name value for this ClinicalNotes.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this ClinicalNotes.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the content value for this ClinicalNotes.
     *
     * @return content
     */
    public String getContent() {
        return content;
    }


    /**
     * Sets the content value for this ClinicalNotes.
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the note type for this ClinicalNotes.
     *
     * @return the note type
     */
    public String getNoteType() {
    	if (this.noteType == null || this.noteType.equals("")){
    		return this.getName();
    	}
    	else{
    		return this.noteType;
    	}
    }

    /**
     * Sets the note type for this ClinicalNotes
     *
     * @param noteType the note type
     */
    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
    /**
     * Gets the provider value for this ClinicalNotes.
     *
     * @return provider
     */
    public Provider getProvider() {
        return provider;
    }


    /**
     * Sets the provider value for this ClinicalNotes.
     *
     * @param provider
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }


    /**
     * Gets the attachment value for this ClinicalNotes.
     *
     * @return attachment
     */
    public Attachment[] getAttachment() {
        return attachment;
    }


    /**
     * Sets the attachment value for this ClinicalNotes.
     *
     * @param attachment
     */
    public void setAttachment(Attachment[] attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment(int i) {
        return this.attachment[i];
    }

    public void setAttachment(int i, Attachment _value) {
        this.attachment[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClinicalNotes)) return false;
        ClinicalNotes other = (ClinicalNotes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.dateTime==null && other.getDateTime()==null) ||
             (this.dateTime!=null &&
              this.dateTime.equals(other.getDateTime()))) &&
            ((this.name==null && other.getName()==null) ||
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.content==null && other.getContent()==null) ||
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.noteType==null && other.getNoteType()==null) ||
             (this.noteType!=null &&
              this.noteType == other.getNoteType())) &&
            ((this.provider==null && other.getProvider()==null) ||
             (this.provider!=null &&
              this.provider.equals(other.getProvider()))) &&
            ((this.attachment==null && other.getAttachment()==null) ||
             (this.attachment!=null &&
              java.util.Arrays.equals(this.attachment, other.getAttachment())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDateTime() != null) {
            _hashCode += getDateTime().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getNoteType() != null) {
            _hashCode += getNoteType().hashCode();
        }
        if (getProvider() != null) {
            _hashCode += getProvider().hashCode();
        }
        if (getAttachment() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttachment());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttachment(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
