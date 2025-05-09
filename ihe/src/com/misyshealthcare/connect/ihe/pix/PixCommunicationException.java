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
package com.misyshealthcare.connect.ihe.pix;

/**
 * This exception is thrown when there is a problem communicating with
 * the PIX server.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 21, 2005
 */
public class PixCommunicationException extends PixException {

	private static final long serialVersionUID = 8439863496536504120L;

	public PixCommunicationException(String message) {
		super(message);
	}
	
	public PixCommunicationException(String message, Throwable e) {
		super(message, e);
	}

}
