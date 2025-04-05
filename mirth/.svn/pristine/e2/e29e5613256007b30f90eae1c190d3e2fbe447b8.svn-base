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


/**
 * <Descriptions of the class>
 *
 * @author Wenzhi Li
 * @version 2.0, Aug 26, 2005
 */
public class SubscriberNameAddress {
    private String nameFirst;
    private String nameLast;
    private String nameMiddle;
    private String nameSuffix;
    private String nameTitle;
    private Address address;

    public SubscriberNameAddress() {
    }

    public SubscriberNameAddress(
           String nameFirst,
           String nameLast,
           String nameMiddle,
           String nameSuffix,
           String nameTitle,
           Address address) {
           this.nameFirst = nameFirst;
           this.nameLast = nameLast;
           this.nameMiddle = nameMiddle;
           this.nameSuffix = nameSuffix;
           this.nameTitle = nameTitle;
           this.address = address;
    }


    /**
     * Gets the nameFirst value for this SubscriberNameAddress.
     *
     * @return nameFirst
     */
    public String getNameFirst() {
        return nameFirst;
    }


    /**
     * Sets the nameFirst value for this SubscriberNameAddress.
     *
     * @param nameFirst
     */
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }


    /**
     * Gets the nameLast value for this SubscriberNameAddress.
     *
     * @return nameLast
     */
    public String getNameLast() {
        return nameLast;
    }


    /**
     * Sets the nameLast value for this SubscriberNameAddress.
     *
     * @param nameLast
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }


    /**
     * Gets the nameMiddle value for this SubscriberNameAddress.
     *
     * @return nameMiddle
     */
    public String getNameMiddle() {
        return nameMiddle;
    }


    /**
     * Sets the nameMiddle value for this SubscriberNameAddress.
     *
     * @param nameMiddle
     */
    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }


    /**
     * Gets the nameSuffix value for this SubscriberNameAddress.
     *
     * @return nameSuffix
     */
    public String getNameSuffix() {
        return nameSuffix;
    }


    /**
     * Sets the nameSuffix value for this SubscriberNameAddress.
     *
     * @param nameSuffix
     */
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }


    /**
     * Gets the nameTitle value for this SubscriberNameAddress.
     *
     * @return nameTitle
     */
    public String getNameTitle() {
        return nameTitle;
    }


    /**
     * Sets the nameTitle value for this SubscriberNameAddress.
     *
     * @param nameTitle
     */
    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }


    /**
     * Gets the address value for this SubscriberNameAddress.
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }


    /**
     * Sets the address value for this SubscriberNameAddress.
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubscriberNameAddress)) return false;
        SubscriberNameAddress other = (SubscriberNameAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.nameFirst==null && other.getNameFirst()==null) ||
             (this.nameFirst!=null &&
              this.nameFirst.equals(other.getNameFirst()))) &&
            ((this.nameLast==null && other.getNameLast()==null) ||
             (this.nameLast!=null &&
              this.nameLast.equals(other.getNameLast()))) &&
            ((this.nameMiddle==null && other.getNameMiddle()==null) ||
             (this.nameMiddle!=null &&
              this.nameMiddle.equals(other.getNameMiddle()))) &&
            ((this.nameSuffix==null && other.getNameSuffix()==null) ||
             (this.nameSuffix!=null &&
              this.nameSuffix.equals(other.getNameSuffix()))) &&
            ((this.nameTitle==null && other.getNameTitle()==null) ||
             (this.nameTitle!=null &&
              this.nameTitle.equals(other.getNameTitle()))) &&
            ((this.address==null && other.getAddress()==null) ||
             (this.address!=null &&
              this.address.equals(other.getAddress())));
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
        if (getNameFirst() != null) {
            _hashCode += getNameFirst().hashCode();
        }
        if (getNameLast() != null) {
            _hashCode += getNameLast().hashCode();
        }
        if (getNameMiddle() != null) {
            _hashCode += getNameMiddle().hashCode();
        }
        if (getNameSuffix() != null) {
            _hashCode += getNameSuffix().hashCode();
        }
        if (getNameTitle() != null) {
            _hashCode += getNameTitle().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
