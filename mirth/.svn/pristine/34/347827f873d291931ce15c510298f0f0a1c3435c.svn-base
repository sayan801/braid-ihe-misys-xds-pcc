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

import com.misyshealthcare.connect.net.EnumMap;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.net.PropertySet;
import com.misyshealthcare.connect.net.StringMap;

/**
 * This class contains a number of utilities for getting configuration
 * and using configuration information from a connection description.
 * 
 * @author Jim Firby
 * @version 2.0 - Nov 22, 2005
 */
public class Configuration {

	/**
	 * Get an identifier description from the connection this
	 * actor is using.
	 * 
	 * @param connection The connection description being used
	 * @param name The name of the identifier in the configuration
	 * @param isRequired True if this identifier must exist
	 * @return The identifier
	 * @throws IheConfigurationException If this identifier must be in the configuration and it is not
	 */
	public static Identifier getIdentifier(IConnectionDescription connection, String name, boolean isRequired) throws IheConfigurationException {
		if (connection == null)
			throw new IheConfigurationException("Invalid connection description (NULL)");
		if (name == null)
			throw new IheConfigurationException("Invalid identifier name (NULL)");
		Identifier identifier = connection.getIdentifier(name);
		if ((identifier == null) && isRequired) {
			throw new IheConfigurationException("No identifier '" + name + "' defined for connection \"" + connection.getDescription() + "\"");
		}
		return identifier;
	}

	/**
	 * Get a property set from the connection being used by this
	 * actor.
	 * 
	 * @param connection The connection description being used
	 * @param name The name of the property set in the configuration
	 * @param isRequired True if this property set must exist
	 * @return The property set
	 * @throws IheConfigurationException  If this property set must be in the configuration and it is not
	 */
	public static PropertySet getPropertySet(IConnectionDescription connection, String name, boolean isRequired) throws IheConfigurationException {
		if (connection == null)
			throw new IheConfigurationException("Invalid connection description (NULL)");
		if (connection == null)
			throw new IheConfigurationException("Invalid property set name (NULL)");
		PropertySet set = connection.getPropertySet(name);
		if ((set == null) && isRequired) {
			throw new IheConfigurationException("No property set '" + name + "' defined for connection \"" + connection.getDescription() + "\"");
		}
		return set;
	}
	
	/**
	 * Get a value from a property set defined for a connection.
	 * 
	 * @param connection The connection description holding the set
	 * @param setName The name of the property set
	 * @param valueName The name of the value within the property set
	 * @param isRequired True if this value must be defined
	 * @return The value of the property
	 * @throws IheConfigurationException If this value must be in the configuration and it is not
	 */
	public static String getPropertySetValue(IConnectionDescription connection, String setName, String valueName, boolean isRequired) throws IheConfigurationException {
		if (valueName == null)
			throw new IheConfigurationException("Invalid property set value name (NULL)");
		PropertySet pset = getPropertySet(connection, setName, isRequired);
		if (pset == null) return null;
		String result = pset.getValue(valueName);
		if ((result == null) && isRequired)
			throw new IheConfigurationException("Property set '" + setName + "' for connection \"" + connection.getDescription() + "\" has no value for \'" + valueName + "'");
		return result;
	}

    /**
     * Get a value of a property defined for a connection.
     *
     * @param connection The connection description holding the set
     * @param valueName The name of property
     * @param isRequired True if this property must be defined
     * @return The value of the property
     * @throws IheConfigurationException If this value must be in the configuration and it is not
     */
    public static String getPropertyValue(IConnectionDescription connection, String valueName, boolean isRequired) throws IheConfigurationException {
		if (valueName == null)
			throw new IheConfigurationException("Invalid property value name (NULL)");
		String result = connection.getProperty(valueName);
		if ((result == null) && isRequired)
			throw new IheConfigurationException("Connection \"" + connection.getDescription() + "\" has no value for \'" + valueName + "'");
		return result;
	}

    /**
	 * Translate an enum value into a Code appropriate for this connection.
	 * 
	 * @param connection The connection being submitted to
	 * @param value The enum value used within Misys Connect
	 * @param enumType The enum Class for this value, ie. the name of the EnumMap to use for translation
	 * @param isRequired True if a translation of this value is required, False if the value can default to itself
	 * @return The translated Code
	 * @throws IheConfigurationException When the required EnumMap is not defined for this connection
	 */
	
	public static String applyEnumMap(IConnectionDescription connection, Enum value, Class enumType, boolean isRequired) throws IheConfigurationException {
		if (value == null) {
			if (isRequired) throw new IheConfigurationException("Invalid enum value (NULL)");
			return null;
		}
		if (enumType == null)
			throw new IheConfigurationException("Invalid enum type (NULL)");
		if (connection == null)
			throw new IheConfigurationException("Invalid connection description (NULL)");
		EnumMap emap = connection.getEnumMap(enumType);
		if (emap == null) {
			if (isRequired)
				throw new IheConfigurationException("No enum map to translate '" + enumType.getSimpleName() + "' values for connection \"" + connection.getDescription() + "\"");
			return null;
		} else {
			String translation = emap.getCodeValue(value);
			if (isRequired && (translation == null))
				throw new IheConfigurationException("No translation of '" + value.toString() + "' in string map '" + enumType.getSimpleName() + "' for connection \"" + connection.getDescription() + "\"");
			return translation;
		}
	}
	
	/**
	 * Translate a code to an internal Misys Connect enum value.
	 * 
	 * @param connection The connection to use
	 * @param code The code used by that connection
	 * @param enumType The Misys Connect enum type
	 * @return The Misys Connect enum value
	 */
	public static Enum reverseEnumMap(IConnectionDescription connection, String code, Class enumType) {
		if (code == null) return null;
		if (connection == null) return null;
		EnumMap emap = connection.getEnumMap(enumType);
		if (emap == null) return null;
		return emap.getEnumValue(code);
	}

	/**
	 * Translate a symbolic string into a Code appropriate for this connection.
	 * 
	 * @param connection The connection being submitted to
	 * @param value The symbolic value used within Misys Connect
	 * @param valueType The kind of value this is, ie. the name of the StringMap to use for translation
	 * @param isRequired True if a translation of this value is required, False if the value can default to itself
	 * @return The translated Code
	 * @throws IheConfigurationException When the required StringMap is not defined for this connection
	 */
	public static String applyStringMap(IConnectionDescription connection, String value, String valueType, boolean isRequired) throws IheConfigurationException {
		if (value == null)  {
            if (isRequired) throw new IheConfigurationException("Invalid string value (NULL) " + (valueType==null? "" : "of " + valueType));
			return null;
		}
		if (valueType == null)
			throw new IheConfigurationException("Invalid string value type (NULL)");
		if (connection == null) 
			throw new IheConfigurationException("Invalid connection description (NULL)");
		StringMap smap = connection.getStringMap(valueType);
		if (smap == null) {
			if (isRequired)
				throw new IheConfigurationException("No string map to translate '" + valueType + "' values for connection \"" + connection.getDescription() + "\"");
			return value;
		} else {
			String translation = smap.getCodeValue(value);
			if (isRequired && (translation == null))
				throw new IheConfigurationException("No translation of '" + value + "' in string map '" + valueType + "' for connection \"" + connection.getDescription() + "\"");
			return translation;
		}
	}
	
	/**
	 * Translate a Code from this connection into a Misys Connect string value.
	 * 
	 * @param connection The connection being submitted to
	 * @param code The Code used by the connection
	 * @param valueType The kind of value this is, ie. the name of the StringMap to use for translation
	 * @return The Misys Connect symbolic value for this Code
	 */
	public static String reverseStringMap(IConnectionDescription connection, String code, String valueType) {
		if (code == null) return null;
		if (connection == null) return null;
		StringMap smap = connection.getStringMap(valueType);
		if (smap == null) return null;
		return smap.getStringValue(code);
	}

}
