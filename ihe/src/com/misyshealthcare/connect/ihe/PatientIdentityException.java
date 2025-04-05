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
package com.misyshealthcare.connect.ihe;

/**
 * This exception is thrown when there is a communication error
 * with a patient identify feed consumer.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 23, 2005
 */
public class PatientIdentityException extends Exception {

	private static final long serialVersionUID = 7904874561485667217L;

	public PatientIdentityException(String message) {
		super(message);
	}
	
	public PatientIdentityException(String message, Throwable e) {
		super(message, e);
	}
}
