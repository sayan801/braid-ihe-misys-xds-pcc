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

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.net.IConnectionDescription;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingCharacters;
import ca.uhn.hl7v2.parser.PipeParser;

/**
 * This class implements a number of useful methods shared by all
 * IHE Actors that talk to HL7 servers.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 20, 2005
 */
public class HL7Actor {
	
	private Logger log = null;

	/* The logger used for capturing MESA test output */
	private IMesaLogger mesaLogger = null;
	/* The IHE Audit Trail for this actor. */
	private IheAuditTrail auditTrail = null;
 
	/* The connection this actor will be using */
	private IConnectionDescription connection = null;
  
	/**
	 * Create a new HL7 Actor that logs to the given logger.
	 * 
	 * @param logger
	 */
	HL7Actor(IConnectionDescription connection, Logger logger) {
		log = logger;
		this.connection = connection;
	}
  
	/**
	 * Create a new HL7 Actor that logs to the given logger.
	 * 
	 * @param logger
	 */
	HL7Actor(IConnectionDescription connection, Logger logger, IheAuditTrail auditTrail) {
		log = logger;
		this.auditTrail = auditTrail;
		this.connection = connection;
	}
  
	/** Must be called once for each actor when the program starts. */ 
	public void start() {
		if (auditTrail != null) auditTrail.start();  
	}

	/** Must be called once for each actor just before the program quits. */ 
	public void stop() {
		if (auditTrail != null) auditTrail.stop();  	  
	}
  
	/**
	 * Return a useful name for this Actor so that it can be put into
	 * debugging and logging messages.
	 */
	public String getName() {
		if (connection != null) {
			return connection.getDescription();
		} else {
			return "unnamed";
		}
	}

	/**
	 * Log an HL7 message and description to the error log.
	 * 
	 * @param message The HL7 message
	 * @param description A description of the problem with the message
	 */
	void logHL7MessageError(Message message, String description) {
		if (mesaLogger != null) {
			// Just log the description
			mesaLogger.writeString(description);
		} else {
			// Log the description and the HL7 message itself
			log.error(description);
			try {
				log.error(PipeParser.encode(message, new EncodingCharacters('|', "^~\\&")));
			} catch (HL7Exception e) {
				log.error("Error reporting HL7 error", e);
			}
		}
	}

	/**
	 * Check whether this actor has a MESA test logger.
	 * 
	 * @return True if there is a defined MESA test logger.
	 */
  boolean hasMesaLogger() {
  	return (mesaLogger != null);
  }
  
  /**
   * Get the MESA test logger.
   * 
   * @return The MESA test logger.
   */
  public IMesaLogger getMesaLogger() {
  	return mesaLogger;
  }
  
	/**
	 * Set the custom logger for MESA test messages.  This is
	 * only used by MESA testing programs.
	 * 
	 * @param stream The logger for MESA messages
	 */
	public void setMesaLogger(IMesaLogger logger) {
		mesaLogger = logger;
	}

}
