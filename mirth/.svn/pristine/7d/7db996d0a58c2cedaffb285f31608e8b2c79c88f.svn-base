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
package com.misyshealthcare.connect.ihe.pdq;

import java.util.Properties;

import com.misyshealthcare.connect.base.DateQuery;
import com.misyshealthcare.connect.base.PatientQuery;
import com.misyshealthcare.connect.base.SharedEnums.PhoneType;
import com.misyshealthcare.connect.base.SharedEnums.SexType;
import com.misyshealthcare.connect.base.demographicdata.Address;
import com.misyshealthcare.connect.base.demographicdata.PatientMRN;
import com.misyshealthcare.connect.base.demographicdata.PhoneNumber;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.net.Identifier;
import com.misyshealthcare.connect.net.PropertySet;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.ihe.registry.HL7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Varies;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.QIP;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.segment.QPD;

/**
 * This class populate an HL7 query from a PatientQuery object.
 * <p>
 * Which fields to include in the HL7 query are controlled in
 * two ways:
 * <ol>
 * <li> Only those fields with values in the PatientQuery are
 * included.  If a field is 'null' it will not be included in
 * the HL7 query.
 * <li> Any field which is explictly excluded in the "QueryFields"
 * configuration PropertySet will be excluded even if it has a
 * value in the PatientQuery.
 * </ol>
 * The "QueryFields" configuration enables query values to be
 * excluded in those cases where they are not supported by the PDQ
 * server and including them will confuse things.
 * <p>
 * This class also converts wildcard values from those used in Misys
 * Connect to those used by the PDQ server at the end of this connection.
 * Wildcards are translated as follows:
 * <ol>
 * <li> If a Misys wildcard is specified in the value, it is translated
 * to a wildcard appropriate for this connection.
 * <li> If no Misys wildcard is specified, then the Misys Connect matching
 * type for the parameter is looked up as follows:
 *   <ol>
 *   <li> The field id (ie. PID.3.1) is translated to a Misys value name
 *   using the "QueryFieldMisysNames" connection PeropertySet (if supplied).
 *   <li> The Misys value name is then looked up in the Misys Query Design
 *   Properties file.  The result will be "END", "START", "LIKE", etc.
 *   </ol>
 * <li> The Misys matching type is then used to add approporiate wildcards
 * to the query value in the HL7 message.  If a Misys matching type cannot
 * be determined it defaults to "EXACT" and no wildcards are added.
 * </ol>
 * This class will also convert all parameter values to upper or lower case
 * if the connection requires it.
 * 
 * @author Jim Firby
 * @version 2.0 - Dec 9, 2005
 */
public class QueryBuilder {
	
	/* The value that Misys Connect uses for wildcards in Patient Queries (or null) */
	private static final String misysConnectWildcard = "%";
	
	/* The query fields to be used in queries over this connection */
	private PropertySet queryFields = null;
	
	/* The tables needed to translate values to same matching type as Misys Connect uses internally */
	private PropertySet queryMisysPropertyNames = null;
	private Properties misysQueryDesignProperties = null;
	
	/* The various wildcards to be used for queries over this connection */
	private String beforeWildcardPrefix = null;
	private String beforeWildcardSuffix = null;
	private String afterWildcardPrefix = null;
	private String afterWildcardSuffix = null;
	private String bothWildcardPrefix = null;
	private String bothWildcardSuffix = null;
	
	/* Whether or not parameter values in the HL7 query should be forced to upper or lower case */
	private boolean forceUppercase = false;
	private boolean forceLowercase = false;

    /** Query assigning auth */
    private Identifier queryAssigningAuth = null;

    /**
	 * Create a new query builder for making Patient Queries to the PDQ server
	 * at the specified connection.
	 * 
	 * @param connection The connection to the PDQ server
	 */

	public QueryBuilder(IConnectionDescription connection) {
		// Extract the property set holding the query field values
		queryFields = connection.getPropertySet("QueryFields");
		// Extract the wildcard values for this connection
		PropertySet wildcards = connection.getPropertySet("QueryProperties");
		if (wildcards != null) {
			afterWildcardPrefix = wildcards.getValue("WildcardAfterPrefix");
			afterWildcardSuffix = wildcards.getValue("WildcardAfter");
			beforeWildcardPrefix = wildcards.getValue("WildcardBefore");
			beforeWildcardSuffix = wildcards.getValue("WildcardBeforeSuffix");
			bothWildcardPrefix = wildcards.getValue("WildcardBothPrefix");
			if (bothWildcardPrefix == null) bothWildcardPrefix = wildcards.getValue("WildcardBefore");
			bothWildcardSuffix = wildcards.getValue("WildcardBothSuffix");
			if (bothWildcardSuffix == null) bothWildcardSuffix = wildcards.getValue("WildcardAfter");
			// Extract upper and lower case specifications
			forceUppercase = getBooleanProperty(wildcards, "Uppercase", false);
			if (!forceUppercase)
				forceLowercase = getBooleanProperty(wildcards, "Lowercase", false);
		}
		// Try to load the Misys Connect Query Design Properties
		queryMisysPropertyNames = connection.getPropertySet("QueryFieldMisysNames");
		misysQueryDesignProperties = LibraryConfig.getInstance().getPatientQueryDesignProps();
        queryAssigningAuth = connection.getIdentifier("QueryAssigningAuthority");
    }
	
	/**
	 * Checks whether a string begins with the Misys Connect wildcard
	 * character.
	 * 
	 * @param value The string
	 * @return True if this string starts with the Misys Connect wildcard
	 */
	private  boolean hasPrefixWildcard(String value) {
		if (value == null) return false;
		if (misysConnectWildcard == null) return false;
		return value.startsWith(misysConnectWildcard);
	}
	
	/**
	 * Checks whether a string ends with the Misys Connect wildcard
	 * character.
	 * 
	 * @param value The string
	 * @return True if this string starts with the Misys Connect wildcard
	 */
	private boolean hasSuffixWildcard(String value) {
		if (value == null) return false;
		if (misysConnectWildcard == null) return false;
		return value.endsWith(misysConnectWildcard);
	}
	
	/**
	 * Extract the part of a value string that holds no Misys Connect wildcard
	 * characters.
	 * 
	 * @param value The string that may hold wildcards
	 * @return The part of the string with no Misys Connect wildcards
	 */
	private String getNonWildcardComponent(String value) {
		if (value == null) return value;
		if (misysConnectWildcard == null) return value;
		if (!value.contains(misysConnectWildcard)) return value;
		// Okay, the string contains the character, take it out
		// This method is a little stronger than just beginning and ending characters
		return value.replaceAll("\\Q" + misysConnectWildcard + "\\E", "");
	}
	
	/**
	 * Populate an HL7 PDQ query with the parameters contained in the supplied
	 * PatientQuery.  Exclude any parameters that are labelled as "false" in the
	 * 'QueryFields' configuration PropertySet for this connection.
	 * <p>
	 * In addition, add wildcards as appropriate according to wildcards in the
	 * PatientQuery entries and the default search mechanism defined for
	 * Misys Connect.
	 * 
	 * @param message The message to populate
	 * @param query The patient query structure describing the desired patient
	 * @return True if anything was put into the query message
	 * @throws HL7Exception If there is a problem encoding a value into HL7
	 */
	public boolean populatePdqQuery(QBP_Q21 message, PatientQuery query) throws HL7Exception {
		boolean okay = false;
		// PID-3 - Patient ID
		//  Queries based in Patient ID are not really within the Misys Connect 2.0 world view.
		//  However, some mesa tests rely on being about to make queries based on ID.
		if (useQueryField("PID.3.1", true)) {
			okay = addPdqQueryField(message, "PID.3.1", query.getMisysPatientId(), null) || okay;

            if (okay && queryAssigningAuth != null && useQueryField("PID.3.4", true))
                okay = addPdqQueryField(message, "PID.3.4", HL7.toCXAuth(queryAssigningAuth), null) || okay;
        }
        // PID-5 - Patient Name
		if (useQueryField("PID.5.1", true))
			okay = addPdqQueryField(message, "PID.5.1", query.getNameLast(), "nameLast") || okay;
		if (useQueryField("PID.5.2", true))
			okay = addPdqQueryField(message, "PID.5.2", query.getNameFirst(), "nameFirst") || okay;
		if (useQueryField("PID.5.3", true))
			okay = addPdqQueryField(message, "PID.5.3", query.getNameMiddle(), "nameMiddle") || okay;
		if (useQueryField("PID.5.4", true))
			okay = addPdqQueryField(message, "PID.5.4", query.getNameSuffix(), "nameSuffix") || okay;
		if (useQueryField("PID.5.5", true))
			okay = addPdqQueryField(message, "PID.5.5", query.getNameTitle(), "nameTitle") || okay;
		// PID-6 - Maiden Name
		if (useQueryField("PID.6", false)) {
			okay = addPdqQueryField(message, "PID.6", query.getMotherMaidenName(), "motherMaidenName") || okay;
		} else if (useQueryField("PID.6.1", true)) {
			okay = addPdqQueryField(message, "PID.6.1", query.getMotherMaidenName(), "motherMaidenName") || okay;
		}
		// PID-7 - Birth date
		if (useQueryField("PID.7", true)) {
			DateQuery date = query.getBirthDateTime();
			if (date != null) {
				okay = addFormattedDateQueryField(message, "PID.7", date, "birthDate") || okay;
			}
		}
		// PID-8 - Gender
		if (useQueryField("PID.8", true)) {
			String gender = null;
			if (query.getAdministrativeSex() == SexType.MALE) gender = "M";
			else if (query.getAdministrativeSex() == SexType.FEMALE) gender = "F";
			else if (query.getAdministrativeSex() == SexType.OTHER) gender = "O";
			okay = addPdqQueryField(message, "PID.8", gender, "adminstrativeSex") || okay;
		}
		// PID-11 - Address
		Address address = query.getAddress();
		if (address != null) {
			if (useQueryField("PID.11.1", true))
				okay = addPdqQueryField(message, "PID.11.1", address.getAddLine1(), "addLine1") || okay;
			if (useQueryField("PID.11.2", true))
				okay = addPdqQueryField(message, "PID.11.2", address.getAddLine2(), "addLine2") || okay;
			if (useQueryField("PID.11.3", true))
				okay = addPdqQueryField(message, "PID.11.3", address.getAddCity(), "addCity") || okay;
			if (useQueryField("PID.11.4", true))
				okay = addPdqQueryField(message, "PID.11.4", address.getAddState(), "addState") || okay;
			if (useQueryField("PID.11.5", true))
				okay = addPdqQueryField(message, "PID.11.5", address.getAddZip(), "addZip") || okay;
			if (useQueryField("PID.11.6", true))
				okay = addPdqQueryField(message, "PID.11.6", address.getAddCountry(), "addCountry") || okay;
		}
		// PID-13/14 - Phone number
		// There are two fields where the phone number might be but we cannot make
		//  a disjunctive query.  So we assume it is a home phone number unless
		//  specifically specified as a work number
		PhoneNumber phone = query.getPhone();
		if (phone != null) {
			if ((phone.getType() != null) && (phone.getType() == PhoneType.WORK)) {
				// PID-14 - Phone number, business
				if (useQueryField("PID.14.1", true))
					okay = addFormattedPhoneQueryField(message, "PID.14.1", phone) || okay;
				if (useQueryField("PID.14.5", true))
					okay = addPdqQueryField(message, "PID.14.5", phone.getCountryCode(), "countryCode") || okay;
				if (useQueryField("PID.14.6", true))
					okay = addPdqQueryField(message, "PID.14.6", phone.getAreaCode(), "areaCode") || okay;
				if (useQueryField("PID.14.7", true))
					okay = addPdqQueryField(message, "PID.14.7", phone.getNumber(), "number") || okay;
				if (useQueryField("PID.14.8", true))
					okay = addPdqQueryField(message, "PID.14.8", phone.getExtension(), "extension") || okay;
				if (useQueryField("PID.14.9", true))
					okay = addPdqQueryField(message, "PID.14.9", phone.getNote(), "note") || okay;
			} else {
				// PID-13 - Phone number, home
				if (useQueryField("PID.13.1", true))
					okay = addFormattedPhoneQueryField(message, "PID.13.1", phone) || okay;
				if (useQueryField("PID.13.5", true))
					okay = addPdqQueryField(message, "PID.13.5", phone.getCountryCode(), "countryCode") || okay;
				if (useQueryField("PID.13.6", true))
					okay = addPdqQueryField(message, "PID.13.6", phone.getAreaCode(), "areaCode") || okay;
				if (useQueryField("PID.13.7", true))
					okay = addPdqQueryField(message, "PID.13.7", phone.getNumber(), "number") || okay;
				if (useQueryField("PID.13.8", true))
					okay = addPdqQueryField(message, "PID.13.8", phone.getExtension(), "extension") || okay;
				if (useQueryField("PID.13.9", true))
					okay = addPdqQueryField(message, "PID.13.9", phone.getNote(), "note") || okay;
			}
		}
		// PID-18 - Patient Account Number
		//   We send an MRN as a value in PatientIdentitySource messages and can query on it here.
		//   (See PatientIdentitySource and PdqConsumer)
		PatientMRN mrn = query.getMrn();
		if (mrn != null) {
			if (useQueryField("PID.18.1", true))
				okay = addPdqQueryField(message, "PID.18.1", mrn.getMrn(), "mrn") || okay;
		}
		// PID-19 - SSN
		if (useQueryField("PID.19", true))
			okay = addPdqQueryField(message, "PID.19", query.getSsn(), "ssn") || okay;
		// PID-20 - Drivers License
		if (useQueryField("PID.20", false)) {
			okay = addPdqQueryField(message, "PID.20", query.getDriverLicense(), "driverLicense") || okay;
		} else if (useQueryField("PID.20.1", true)) {
			okay = addPdqQueryField(message, "PID.20.1", query.getDriverLicense(), "driverLicense") || okay;
		}
		// Done
		return okay;
	}
	
	/**
	 * Restrict a query to returning only those patient IDs that were assigned by 
	 * the specified authority.  If a query is restricted multiple times, IDs from
	 * each restricting authority will be returned.  If a query is not restricted,
	 * IDs from all known domains will be returned.
	 * 
	 * @param message The query message to restrict
	 * @param assigningAuthority The assigning authority to be returned
	 * @throws HL7Exception When there is a problem encoding the authority into HL7
	 */
	public void restrictQueryDomain(QBP_Q21 message, Identifier assigningAuthority) throws HL7Exception {
		// Grab out the assigning authority values
		String namespaceId = assigningAuthority.getNamespaceId();
		String universalId = assigningAuthority.getUniversalId();
		String universalIdType = assigningAuthority.getUniversalIdType();
		// Populate a new CX structure to hold the restriction domain
		boolean okay = false;
		CX cx = new CX(message);
		if (namespaceId != null) {
			cx.getAssigningAuthority().getNamespaceID().setValue(namespaceId);
			okay = true;
		}
		if (universalId != null) {
			if (universalIdType != null) {
				cx.getAssigningAuthority().getUniversalID().setValue(universalId);
				cx.getAssigningAuthority().getUniversalIDType().setValue(universalIdType);
				okay = true;
			}
		}
		// If the assigning authority holds a reasonable value, add it to the message
		if (okay) {
			QPD qpd = message.getQPD();
			int i = qpd.getField(8).length;
			Varies rep = (Varies) qpd.getField(8,i);
			rep.setData(cx);
		}
	}
	
	/**
	 * Add a birth date parameter to the query message.
	 * 
	 * @param message The query message
	 * @param field The PID field the data parameter must match
	 * @param date The date parameter to be encoded
	 * @param misysField The name of this field in the Misys Connect query design properties
	 * @return True if this date was added to the query
	 * @throws HL7Exception When there is problem encoding an HL7 value
	 * 
	 * @see com.misyshealthcare.connect.hiber.mpi.QueryObject#getPatientQueryDesignProperties()
	 */
	private boolean addFormattedDateQueryField(QBP_Q21 message, String field, DateQuery date, String misysField) throws HL7Exception {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		boolean haveYear = false;
		boolean haveDay = false;
		// Format the date
		StringBuffer sb = new StringBuffer();
		if (year > 0) {
			// Add the year
			sb.append(Integer.toString(year));
			haveYear = true;
		}
		if (month > 0) {
			// Add the month
			if (month < 10) sb.append('0');
			sb.append(Integer.toString(month));
			if (day > 0) {
				// Add the day, if there is a month
				if (day < 10) sb.append('0');
				sb.append(Integer.toString(day));
				haveDay = true;
			}
		}
		String dateString = sb.toString();
		if (dateString.length() < 1) return false;
		// We have a formatted date, add any required wildcards
		if (!haveYear && !haveDay) {
			dateString = formatWithWildcardBoth(dateString);
		} else if (!haveYear) {
			dateString = formatWithWildcardBefore(dateString);
		} else if (!haveDay) {
			dateString = formatWithWildcardAfter(dateString);
		}
		// Okay, add it to the query
		return addRawPdqQueryField(message, field, dateString);
	}
	
	/**
	 * Add a phone number to a query as a formatted field.  The HL7 v2.3.1 spec
	 * suggests a format for North American phone numbers for the first component of
	 * phone number fields.  This method builds a query using this format.
	 * <p>
	 * This method only makes a query using the local part of the phone number.
	 * There is some ambiguity about whether spaces are allowed between the 
	 * area code and the local number in a formatted number.  So, we just ignore
	 * the area code.  This means the match isn't quite as a specific, but it 
	 * should return all matches.
	 * 
	 * @param message The HL7 to add the phone number to
	 * @param field The specific field name for the query (usually, PID.13.1 or PID.14.1)
	 * @param phone The phone number to format
	 * @return True if this query was added to the message
	 * @throws HL7Exception When there is a problem formatting the field
	 * 
	 * @see com.misyshealthcare.connect.hiber.mpi.QueryObject#getPatientQueryDesignProperties()
	 */
	private boolean addFormattedPhoneQueryField(QBP_Q21 message, String field, PhoneNumber phone) throws HL7Exception {
		// Ignore everything but the number
		String number = phone.getNumber();
		if ((number != null) && (number.length() >= 7)) {
			// Format the number to look like 555-5555
			StringBuffer sb = new StringBuffer();
			int j = 0;
			for(int i=0; i<number.length(); i++) {
				char c = number.charAt(i);
				if (Character.isDigit(c)) {
					j = j + 1;
					if (j == 4) sb.append('-');
					sb.append(c);
				}
			}
			// If it is seven digits use it, otherwise use the original value
			if (j == 7) number = sb.toString();
		}
		// Add the query
		if (number != null) {
			return addRawPdqQueryField(message, field, formatWithWildcardBoth(number));
		} else {
			return false;
		}
	}
	
	/**
	 * Add a simple text string field to a query message.
	 * 
	 * @param message The query message
	 * @param field The specific field name for this value in the query
	 * @param value The value to add
	 * @param misysField The name of this field in the Misys Connect Query Design properties
	 * @return True if this value was added to the query
	 * @throws HL7Exception When there is a problem encoding an HL7 value
	 * 
	 * @see com.misyshealthcare.connect.hiber.mpi.QueryObject#getPatientQueryDesignProperties()
	 */
	private boolean addPdqQueryField(QBP_Q21 message, String field, String value, String misysField) throws HL7Exception {
		if (value == null) return false;
		// Check for wildcard values
		boolean prefix = hasPrefixWildcard(value);
		boolean suffix = hasSuffixWildcard(value);
		String base = getNonWildcardComponent(value);
		if (base == null) return false;
		// Change case, if required
		if (forceUppercase) {
			base = base.toUpperCase();
		} else if (forceLowercase){
			base = base.toLowerCase();
		}
		// Reformat according to wildcards
		if (prefix && suffix) {
			base = formatWithWildcardBoth(base);
		} else if (suffix) {
			base = formatWithWildcardAfter(base);
		} else if (prefix) {
			base = formatWithWildcardBefore(base);
		} else {
			base = formatWithMisysQueryDesignWildcards(base, field, misysField);
		}
		if (base.equals("")) return false;
		// Okay, actually add it

        return addRawPdqQueryField(message, field, base);
	}
	
	/**
	 * Add a formatted query parameter to the HL7 message.
	 * 
	 * @param message The HL7 query message
	 * @param field The name of the query field
	 * @param value The value of the query field
	 * @return True if this value is added to the query
	 * @throws HL7Exception When there is a problem encoding an HL7 value
	 */
	private boolean addRawPdqQueryField(QBP_Q21 message, String field, String value) throws HL7Exception {
		if (value == null) return false;
		QPD qpd = message.getQPD();
		// Query parameters are all jammed into QPD.3
		int i = qpd.getField(3).length;
		Varies rep = (Varies) qpd.getField(3,i);
		// Encode the parameter
		QIP qip = new QIP(message);
		qip.getSegmentFieldName().setValue("@" + field);
		qip.getValues().setValue(value);
		rep.setData(qip);
		return true;
	}
	
	/**
	 * Format a value with a wildcard at the end according to the syntax for this
	 * connection.
	 * 
	 * @param value The value to be wildcarded
	 * @return The value with the wildcard added
	 */
	private String formatWithWildcardAfter(String value) {
		return formatWithWildcard(value, afterWildcardPrefix, afterWildcardSuffix);
	}
	
	/**
	 * Format a value with a wildcard at the beginning according to the syntax for this
	 * connection.
	 * 
	 * @param value The value to be wildcarded
	 * @return The value with the wildcard added
	 */
	private String formatWithWildcardBefore(String value) {
		return formatWithWildcard(value, beforeWildcardPrefix, beforeWildcardSuffix);
	}
	
	/**
	 * Format a value with a wildcard at both the beginning and end according to the syntax for this
	 * connection.
	 * 
	 * @param value The value to be wildcarded
	 * @return The value with the wildcard added
	 */
	private String formatWithWildcardBoth(String value) {
		if (value == "") {
			return formatWithWildcardAfter(value);
		} else {
			return formatWithWildcard(value, bothWildcardPrefix, bothWildcardSuffix);
		}
	}
	
	/**
	 * Format a value with the specified wildcards.
	 * 
	 * @param value The value
	 * @param prefix The wildcard prefix to add
	 * @param suffix The wildcard suffix to add
	 * @return The value with wildcards added
	 */
	private String formatWithWildcard(String value, String prefix, String suffix) {
		if ((prefix != null) && (suffix != null)) {
			return prefix + value + suffix;
		} else if ((prefix != null)) {
			return prefix + value;
		} else if ((suffix != null)) {
			return value + suffix;
		} else {
			return value;
		}
	}
	
	/**
	 * Format a value with wildcards as specified in the Misys Connect Query Design Properties.
	 * The idea here is to get the IHE PDQ query to behave as close to the Misys internal
	 * query as possible.
	 * <p>
	 * The appropriate wildcards are determined by first translating the field name into a
	 * Misys Connect field name using the "QueryFieldMisysNames" PropertySet.  Then this
	 * field name is looked up in the Misys Query Design Properties file.  The result of this
	 * is the matching type for this field.  That type is them translated into the appropriate
	 * wildcards for this connection.
	 * <p>
	 * The way this is actually done is a little crude.  There should be a slightly tighter
	 * integration.  However, this part of the IHE spec is a rapidly moving target and
	 * trying to do better probably isn't worth the trouble at the moment.
	 * 
	 * @param value The value to be wildcarded
	 * @param field The parameter name for this field (ie. PID.3.1)
	 * @param suffix The default Misys Connect name for this value (ie. "nameFirst")
	 * @return The value with appropriate wildcards added
	 * 
	 * @see com.misyshealthcare.connect.hiber.mpi.QueryObject#getPatientQueryDesignProperties()
	 */
	private String formatWithMisysQueryDesignWildcards(String value, String field, String misysField) {
		if (misysField == null) return value;
		if (misysQueryDesignProperties == null) return value;
		// Get the name of this field in the misys query design properties
		String fieldName = misysField;
		if (queryMisysPropertyNames != null) {
			String configuredFieldName = queryMisysPropertyNames.getValue(field);
			if (configuredFieldName != null) {
				fieldName = configuredFieldName;
			}
		}
		// Now look up the Misys Connect Query Design Property for this field, if any
		if (misysQueryDesignProperties.containsKey(fieldName)) {
			String queryDesignMatchType = misysQueryDesignProperties.getProperty(fieldName);
			if (queryDesignMatchType == null) return value;
			if (queryDesignMatchType.equalsIgnoreCase("END")) {
				// Matches the end, add a wildcard to the beginning
				return formatWithWildcardBefore(value);
			} else if (queryDesignMatchType.equalsIgnoreCase("START")) {
				// Matches the begining, add a wildcard at the end
				return formatWithWildcardAfter(value);
			} else if (queryDesignMatchType.equalsIgnoreCase("LIKE")) {
				// Matches anywhere, add both wildcards
				return formatWithWildcardBoth(value);
			}
		}
		// No known conversion, leave alone
		return value;
	}
	
	/**
	 * Check whether a specfic query parameter should be used with this connection.
	 * This is checked by looking up the field name in the QueryFields connection
	 * PropertySet.
	 * 
	 * @param field The name of the field (ie. PID.3.1)
	 * @param defaultValue Whether or not to include this field by default
	 * @return True if this field should be included in the query
	 */
	private boolean useQueryField(String field, boolean defaultValue) {
		if (queryFields == null) return defaultValue;
		return getBooleanProperty(queryFields, field, defaultValue);
	}
	
	/**
	 * Get a boolean value from a PropertySet.
	 * 
	 * @param pset The property set
	 * @param valueName The name of the value to look up
	 * @param defaultValue The default value to use if the property is not found
	 * @return The boolean value for this property in the set
	 */
	private boolean getBooleanProperty(PropertySet pset, String valueName, boolean defaultValue) {
		if (pset == null) return defaultValue;
		if (valueName == null) return defaultValue;
		String valueString = pset.getValue(valueName);
		if (valueString == null) return defaultValue;
		return Boolean.parseBoolean(valueString);
	}

}
