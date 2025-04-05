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
package com.misyshealthcare.connect.ihe.configuration;

/**
 * This interface is used to access the properties of IHE Actor
 * definition.  Typically IHE Actors are defined in XML configuration
 * files and loaded by the ConfigurationLoader.
 * 
 * @author Jim Firby
 * @version 2.0 - Jan 10, 2006
 * @see com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader
 */
public interface IheActorDescription {

	/**
	 * Get a human-readable description of this actor.  This
	 * description can be used in a GUI to name this actor.
	 * 
	 * @return A human-readable description of this actor.
	 */
	public String getDescription();
	
	/**
	 * Get a unique ID for this actor.  This ID can be used to
	 * look the actor up in the ConfigurationLoader.  The ID is
	 * <i>not</i> intended for use in a GUI.
	 * 
	 * @return Returns the id.
	 */
	public String getId();
	
	/**
	 * Get a human-readable string naming the type of this actor
	 * (ie. "PDQ Server", "PIX Manager", "Audit Repository").  All
	 * actors of the same type will return the same string.  The
	 * type is designed to be used in a GUI.
	 * 
	 * @return Returns the type.
	 */
	public String getType();
	
	/**
	 * Returns true if this actor is believed to be installed into
	 * a broker.  False otherwise.
	 * 
	 * @return 'true' if this actor is believed to be installed
	 */
	public boolean isInstalled();
	
}
