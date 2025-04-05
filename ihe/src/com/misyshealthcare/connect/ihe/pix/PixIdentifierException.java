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
 * This exception is thrown when the PIX server does not recognize
 * an identifier or domain sent to it for cross-referencing.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 21, 2005
 */
public class PixIdentifierException extends PixException {

	private static final long serialVersionUID = -2780196287094636067L;

	public PixIdentifierException(String message) {
		super(message);
	}
	
	public PixIdentifierException(String message, Throwable e) {
		super(message, e);
	}

}
