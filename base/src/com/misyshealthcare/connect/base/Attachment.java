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

/**
 * <Descriptions of the class>
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 8, 2005
 */
public class Attachment {
    private String attachmentName;
    private String attachmentType;
    private String attachmentUrl;
    private String attachmentUid;

    public Attachment() {
    }

    public Attachment(
           String attachmentName,
           String attachmentType,
           String attachmentUrl,
           String attachmentUid) {
           this.attachmentName = attachmentName;
           this.attachmentType = attachmentType;
           this.attachmentUrl = attachmentUrl;
           this.attachmentUid = attachmentUid;
    }


    /**
     * Gets the attachmentName value for this Attachment.
     *
     * @return attachmentName
     */
    public String getAttachmentName() {
        return attachmentName;
    }


    /**
     * Sets the attachmentName value for this Attachment.
     *
     * @param attachmentName
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }


    /**
     * Gets the attachmentType value for this Attachment.
     *
     * @return attachmentType
     */
    public String getAttachmentType() {
        return attachmentType;
    }


    /**
     * Sets the attachmentType value for this Attachment.
     *
     * @param attachmentType
     */
    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }


    /**
     * Gets the attachmentUrl value for this Attachment.
     *
     * @return attachmentUrl
     */
    public String getAttachmentUrl() {
        return attachmentUrl;
    }


    /**
     * Sets the attachmentUrl value for this Attachment.
     *
     * @param attachmentUrl
     */
    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }


    /**
     * Gets the attachmentUid value for this Attachment.
     *
     * @return attachmentUid
     */
    public String getAttachmentUid() {
        return attachmentUid;
    }


    /**
     * Sets the attachmentUid value for this Attachment.
     *
     * @param attachmentUid
     */
    public void setAttachmentUid(String attachmentUid) {
        this.attachmentUid = attachmentUid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Attachment)) return false;
        Attachment other = (Attachment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.attachmentName==null && other.getAttachmentName()==null) ||
             (this.attachmentName!=null &&
              this.attachmentName.equals(other.getAttachmentName()))) &&
            ((this.attachmentType==null && other.getAttachmentType()==null) ||
             (this.attachmentType!=null &&
              this.attachmentType.equals(other.getAttachmentType()))) &&
            ((this.attachmentUrl==null && other.getAttachmentUrl()==null) ||
             (this.attachmentUrl!=null &&
              this.attachmentUrl.equals(other.getAttachmentUrl()))) &&
            ((this.attachmentUid==null && other.getAttachmentUid()==null) ||
             (this.attachmentUid!=null &&
              this.attachmentUid.equals(other.getAttachmentUid())));
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
        if (getAttachmentName() != null) {
            _hashCode += getAttachmentName().hashCode();
        }
        if (getAttachmentType() != null) {
            _hashCode += getAttachmentType().hashCode();
        }
        if (getAttachmentUrl() != null) {
            _hashCode += getAttachmentUrl().hashCode();
        }
        if (getAttachmentUid() != null) {
            _hashCode += getAttachmentUid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }
}
