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

/**
 * The data container for ED Referral
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 21, 2006
 */
public class EDReferralData extends ReferralSummaryData {

    private TransportMode transportMode = null;
    private ProposedDisposition disposition = null;

    public TransportMode getTransportMode() {
        return this.transportMode;
    }
    public void setTransportMode(TransportMode mode) {
        this.transportMode = mode;
    }

    public ProposedDisposition getProposedDisposition() {
        return this.disposition;
    }
    public void setProposedDisposition(ProposedDisposition disposition) {
        this.disposition = disposition;
    }

}
