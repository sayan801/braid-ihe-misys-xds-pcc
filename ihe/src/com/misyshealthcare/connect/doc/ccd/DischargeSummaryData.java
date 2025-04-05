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

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import com.misyshealthcare.connect.base.clinicaldata.Medication;
import com.misyshealthcare.connect.base.clinicaldata.SimpleProblem;

/**
 * The data container for Discharge Summary.
 *
 * @author Wenzhi Li
 * @version 2.0, Oct 14, 2006
 */
public class DischargeSummaryData extends XDSMSData {

	private SimpleProblem[] dischargeDiagnosis = null;
	private SimpleProblem[] admittingDiagnosis = null;
    private String hospitalCourse = null;		
    private Calendar admissionDate = null;
    private Calendar dischargeDate = null;
    /**A list of patient's administered medications*/
    private Medication[] administeredMedications = null;
    /**A list of patient's admission medications*/
    private Medication[] admissionMedications = null;
    private String[] dischargeDiets = null;

	public SimpleProblem[] getDischargeDiagnosis() {
		return dischargeDiagnosis;
	}
	public void setDischargeDiagnosis(SimpleProblem[] dischargeDiagnosis) {
		this.dischargeDiagnosis = dischargeDiagnosis;
	}
	public SimpleProblem[] getAdmittingDiagnosis() {
		return admittingDiagnosis;
	}
	public void setAdmittingDiagnosis(SimpleProblem[] admittingDiagnosis) {
		this.admittingDiagnosis = admittingDiagnosis;
	}
	/**
	 * @return the hospitalCourse
	 */
	public String getHospitalCourse() {
		return hospitalCourse;
	}
	/**
	 * @param hospitalCourse the hospitalCourse to set
	 */
	public void setHospitalCourse(String hospitalCourse) {
		this.hospitalCourse = hospitalCourse;
	}
	/**
	 * @return the admissionDate
	 */
	public Calendar getAdmissionDate() {
		return admissionDate;
	}
	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(Calendar admissionDate) {
		this.admissionDate = admissionDate;
	}
	/**
	 * @return the dischargeDate
	 */
	public Calendar getDischargeDate() {
		return dischargeDate;
	}
	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(Calendar dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	/**
	 * @return the administeredMedications
	 */
	public Medication[] getAdministeredMedications() {
		return administeredMedications;
	}
	/**
	 * @param administeredMedications the historyMedications to set
	 */
	public void setAdministeredMedications(Medication[] administeredMedications) {
		this.administeredMedications = administeredMedications;
	}
	/**
	 * @return the admissionMedications
	 */
	public Medication[] getAdmissionMedications() {
		return admissionMedications;
	}
	/**
	 * @param admissionMedications the admissionMedications to set
	 */
	public void setAdmissionMedications(Medication[] admissionMedications) {
		this.admissionMedications = admissionMedications;
	}
	/**
	 * @return the dischargeDiets
	 */
	public String[] getDischargeDiets() {
		return dischargeDiets;
	}
	/**
	 * @param dischargeDiets the dischargeDiets to set
	 */
	public void setDischargeDiets(String[] dischargeDiets) {
		this.dischargeDiets = dischargeDiets;
	}
   
    

}

