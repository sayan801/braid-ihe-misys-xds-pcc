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
package com.misyshealthcare.connect.base.codemapping;

/**
 * TODO comment
 * @author Michael Traum
 * @version 2.0, 12/13/2005
 */
public enum CategoryEnum {
	Gender("Gender"),
	Measurement("Measurement"),
	AddressType("Address Type"),
	CommunicationType("Communication Type"),
	MaritalStatus("Marital Status"),
	ResultInterpretation("Result Interpretation"),
	MedicationStatus("Medication Status"),
	ProblemStatus("Problem Status"),
	ConditionStatus("Condition Status"),
	ResultsCategorization("Results Categorization"),
	ResultsCodeCategorization("Results Code Categorization", true),
	HPNotes("H&P Notes"),
	SubscriberToPatientRelationshipType("Subscriber to Patient Relationship"),
	Default("None");

    private String displayValue = null;
    private boolean rangedCode = false;
    
    private CategoryEnum(String displayValue) {
        this.displayValue = displayValue;
    }
    private CategoryEnum(String displayValue, boolean rangedCode) {
        this.displayValue = displayValue;
        this.rangedCode = rangedCode;
    }
    
    public String getDisplayValue() { return  this.displayValue; }
    public boolean isRangedCode() { return  this.rangedCode; }
    
    public static CategoryEnum getByString(String categoryValue){
    	
    	if(categoryValue.equalsIgnoreCase("Gender")){
    		return Gender;
    	}
    	if(categoryValue.equalsIgnoreCase("Measurement")){
    		return Measurement;
    	}
    	if(categoryValue.equalsIgnoreCase("AddressType")){
    		return AddressType;
    	}
    	if(categoryValue.equalsIgnoreCase("CommunicationType")){
    		return CommunicationType;
    	}
    	if(categoryValue.equalsIgnoreCase("MaritalStatus")){
    		return MaritalStatus;
    	}
    	if(categoryValue.equalsIgnoreCase("ResultInterpretation")){
    		return ResultInterpretation;
    	}
    	if(categoryValue.equalsIgnoreCase("MedicationStatus")){
    		return MedicationStatus;
    	}
    	if(categoryValue.equalsIgnoreCase("ProblemStatus")){
    		return ProblemStatus;
    	}
    	if(categoryValue.equalsIgnoreCase("ConditionStatus")){
    		return ConditionStatus;
    	}
    	if(categoryValue.equalsIgnoreCase("ResultsCategorization")){
    		return ResultsCategorization;
    	}
    	if(categoryValue.equalsIgnoreCase("ResultsCodeCategorization")){
    		return ResultsCodeCategorization;
    	}
    	if(categoryValue.equalsIgnoreCase("HPNotes")){
    		return HPNotes;
    	}
    	else
    	return Default;
    }
    
}
