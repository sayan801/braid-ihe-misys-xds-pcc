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


package com.misyshealthcare.connect.doc.ccd;

import com.misyshealthcare.connect.base.clinicaldata.Provider;
import com.misyshealthcare.connect.base.clinicaldata.SimpleProblem;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.util.StringUtil;
import com.misyshealthcare.connect.util.OID;

import java.util.*;

/**
 * This is the base class of building XDS-MS such as Referral Summary and Discharge Summary.
 *
 * @author Wenzhi Li
 * @version 2.0, Nov 15, 2005
 * @see ReferralSummaryBuilder
 */
abstract public class XDSMSBuider extends BaseBuilder implements IDocBuilder {

    protected XDSMSData data;

    /**
     * Constructor
     * @param data
     */
    XDSMSBuider(XDSMSData data) {
        super(data);
        this.data = data;
    }

    /**
     * Make sure all required data are provided.
     */
    protected boolean isValid() {
    	//Add local validation here
    	return super.isValid();
    }

    protected List<Id> getTemplateId() {
        List<Id> templateIds = super.getTemplateId();
        templateIds.add(new Id(CCDTID_CCDv1, null));
        templateIds.add(new Id(TID_MEDICAL_SUMMARY_TEMPLATE_ID, null));
        return templateIds;
    }



}
