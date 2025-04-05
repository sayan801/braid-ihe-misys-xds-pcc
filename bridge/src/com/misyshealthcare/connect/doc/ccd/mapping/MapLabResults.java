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
package com.misyshealthcare.connect.doc.ccd.mapping;

import com.misyshealthcare.connect.base.clinicaldata.Attachment;
import com.misyshealthcare.connect.base.clinicaldata.Battery;
import com.misyshealthcare.connect.base.clinicaldata.Code;
import com.misyshealthcare.connect.base.clinicaldata.LabResult;
import com.misyshealthcare.connect.base.clinicaldata.Order;
import com.misyshealthcare.connect.base.clinicaldata.Result;
import com.misyshealthcare.connect.base.clinicaldata.Test;
/*
 * 
 * Copyright 2005 Misys Healthcare Systems. All rights reserved.
 * This software is protected by international copyright laws and
 * treaties, and may be protected by other law. Violation of copyright
 * laws may result in civil liability and criminal penalties.
 */
/**
 * Title                :   
 * Description          :   This class is used to Transfer LabResults object from webservice request
 * 							to manipulation layer data object
 * Copyright            :   Copyright (c) 2007
 * Company              :   Misys Healthcare Systems
 * Created By           :   Venkata Pragallapati    
 * Created Date         :   Dec 11, 2007
 * Last Modified By     :   
 * Last Modified Date   :
 */
public class MapLabResults {

	public static LabResult[] process(
			com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult[] wsLabResults) {
		if(wsLabResults != null && wsLabResults.length > 0){
			LabResult[] labResults = new LabResult[wsLabResults.length];
			int count = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult wsLabresult : wsLabResults){
				labResults[count++] = createLabResult(wsLabresult); 
			}
			return labResults;
		}
		return null;
	}

	public static LabResult createLabResult(
			com.misyshealthcare.connect.base.clinicaldata.xsd.LabResult wsLabresult) {
		if(wsLabresult == null)
			return null;
		
		Test[] tests = null;
		Battery[] batterys = null;
		
		com.misyshealthcare.connect.base.clinicaldata.xsd.Test[] wsTests= wsLabresult.getTest();
		if(wsTests != null && wsTests.length > 0){
			tests = new Test[wsTests.length];
			int testCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Test wsTest : wsTests){
				tests[testCount++] = createTest(wsTest);
			}
		}
		
		com.misyshealthcare.connect.base.clinicaldata.xsd.Battery[] wsBatterys= wsLabresult.getBattery();
		if(wsBatterys != null && wsBatterys.length > 0){
			batterys = new Battery[wsBatterys.length];
			int batteryCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Battery wsBattery : wsBatterys){
				batterys[batteryCount++] = createBattery(wsBattery);
			}
		}
		
		LabResult labResult = new LabResult(tests,batterys);
		return labResult;
	}

	public static Battery createBattery(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Battery wsBattery) {
		if(wsBattery == null) return null;
		
		Battery battery = new Battery();
		battery.setCategory(wsBattery.getCategory());
		battery.setCode(MapCode.process(wsBattery.getCode()));
		com.misyshealthcare.connect.base.clinicaldata.xsd.Code[] wsCodes = wsBattery.getCodes(); 
		if(wsCodes != null && wsCodes.length > 0){
			Code[] codes = new Code[wsCodes.length];
			int codeCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Code wsCode : wsCodes){
				codes[codeCount++] = MapCode.process(wsCode);
			}
			battery.setCodes(codes);
		}
		battery.setName(wsBattery.getName());
		battery.setOrder(createOrder(wsBattery.getOrder()));
		com.misyshealthcare.connect.base.clinicaldata.xsd.Test[] wsTests = wsBattery.getTest();
		if(wsTests != null && wsTests.length > 0){
			Test[] tests = new Test[wsTests.length];
			for(int i =0; i<wsTests.length; i++){
				tests[i] = createTest(wsTests[i]);
			}
			battery.setTest(tests);
		}
		
		return battery;
	}

	public static Test createTest(com.misyshealthcare.connect.base.clinicaldata.xsd.Test wsTest) {
		if(wsTest == null)
			return null;
		
		Test test = new Test();
		test.setCategory(wsTest.getCategory());
		test.setCode(MapCode.process(wsTest.getCode()));
		com.misyshealthcare.connect.base.clinicaldata.xsd.Code[] wsCodes = wsTest.getCodes(); 
		if(wsCodes != null && wsCodes.length > 0){
			Code[] codes = new Code[wsCodes.length];
			int codeCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Code wsCode : wsCodes){
				codes[codeCount++] = MapCode.process(wsCode);
			}
			test.setCodes(codes);
		}
		test.setName(wsTest.getName());
		test.setOrder(createOrder(wsTest.getOrder()));
		com.misyshealthcare.connect.base.clinicaldata.xsd.Result[] wsResults = wsTest.getResult();
		if(wsResults != null && wsResults.length > 0){
			Result[] results = new Result[wsResults.length];
			int resCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Result wsResult : wsResults){
				results[resCount++] = creatResult(wsResult); 
			}
			test.setResult(results);
		}
		return test;	
	}

	public static Result creatResult(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Result wsResult) {
		if(wsResult == null)
			return null;
		Result result = new Result();
		com.misyshealthcare.connect.base.clinicaldata.xsd.Attachment[] wsAttachments = wsResult.getAttachments();
		if(wsAttachments != null && wsAttachments.length > 0){
			Attachment[] attachments = new Attachment[wsAttachments.length];
			int attachCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Attachment wsAttachment : wsAttachments){
				attachments[attachCount++] = createAttachment(wsAttachment);
			}
			result.setAttachments(attachments);			
		}
		com.misyshealthcare.connect.base.clinicaldata.xsd.Code[] wsCodes = wsResult.getCodes(); 
		if(wsCodes != null && wsCodes.length > 0){
			Code[] codes = new Code[wsCodes.length];
			int codeCount = 0;
			for(com.misyshealthcare.connect.base.clinicaldata.xsd.Code wsCode : wsCodes){
				codes[codeCount++] = MapCode.process(wsCode);
			}
			result.setCodes(codes);
		}
		result.setComments(wsResult.getComments());
		result.setDate(wsResult.getDate());
		result.setInterpretation(wsResult.getInterpretation());
		result.setName(wsResult.getName());
		result.setRange(wsResult.getRange());
		result.setStatus(wsResult.getStatus());
		result.setType(wsResult.getType());
		result.setUom(wsResult.getUom());
		result.setValue(wsResult.getValue());
		
		return result;
	}

	public static Attachment createAttachment(com.misyshealthcare.connect.base.clinicaldata.xsd.Attachment wsAttachment) {
		if(wsAttachment == null)
			return null;
		//Attachment attachment = new Attachment();
		// needs to discuss
		return null;
	}

	public static Order createOrder(
			com.misyshealthcare.connect.base.clinicaldata.xsd.Order wsOrder) {
		if(wsOrder == null) return null;
		
		Order order = new Order();
		order.setOrderid(wsOrder.getOrderid());
		order.setProvider(wsOrder.getProvider());
		order.setSource(wsOrder.getSource());
		order.setStatus(wsOrder.getStatus());
		
		return order;
	}

}
