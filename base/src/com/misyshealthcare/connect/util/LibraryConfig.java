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
package com.misyshealthcare.connect.util;

import java.util.Properties;

import com.misyshealthcare.connect.base.PatientException;
import com.misyshealthcare.connect.base.codemapping.ICodeMappingManager;

/**
 * Configure the connection library with necessary elements
 *
 * @author Josh Flachsbart
 * @version 2.0, Mar 13, 2007
 *
 */
public class LibraryConfig {
	static LibraryConfig lc;
	
	private String oidRoot;
    private OID.OidSource oidSource;
    private String styleSheetLocation;
	private Properties patientQueryDesignProps;
	private ICodeMappingManager codeMappingManager;
	private ILogContext logContext;
	private IPatientIdConverter pidConverter;

	
	public static synchronized LibraryConfig getInstance() {
		if (lc == null) {
			lc = new LibraryConfig();
		}
		return lc;
	}

	/**
	 * Get the assigned CodeMappingManager
	 * 
	 * @return CodeMappingManager
	 */
	public ICodeMappingManager getCodeMappingManager() {
		return codeMappingManager;
	}

	/**
	 * Set the CodeMappingManager to be used for code transformation
	 * 
	 * @param codeMappingManager the CodeMappingManager
	 */
	public void setCodeMappingManager(ICodeMappingManager codeMappingManager) {
		this.codeMappingManager = codeMappingManager;
	}

	/**
	 * Get the OID Root
	 * 
	 * @return the OID root
	 */
	public String getOidRoot() {
		return oidRoot;
	}

	/**
	 * Set the OID Root of the system
	 * 
	 * @param oidRoot the OID root
	 */
	public void setOidRoot(String oidRoot) {
		this.oidRoot = oidRoot;
	}

    /**
     * Get the OID Source which provides a unique id for an OID.
     *
     * @return the OidSource class
     * @see com.misyshealthcare.connect.util.OID.OidSource
     */
    public OID.OidSource getOidSource() {
        return oidSource;
    }

    /**
     * Set the OID Source which provides a unique id for an OID.
     *
     * @param oidSource the OidSource class to be set
     * @see com.misyshealthcare.connect.util.OID.OidSource
     */
    public void setOidSource(OID.OidSource oidSource) {
        this.oidSource = oidSource;
    }

    /**
	 * Get the Patient Query Design Properties
	 * 
	 * @return the patient query design properties
	 */
	public Properties getPatientQueryDesignProps() {
		return patientQueryDesignProps;
	}


	/**
	 * Set the patient query design properties
	 * @param patientQueryDesignProps  the patient query design properties
	 */
	public void setPatientQueryDesignProps(Properties patientQueryDesignProps) {
		this.patientQueryDesignProps = patientQueryDesignProps;
	}


	/**
	 * Get the xsl stylesheet location for CDA transforms
	 * 
	 * @return the xsl stylesheet location
	 */
	public String getStyleSheetLocation() {
		return styleSheetLocation;
	}


	/**
	 * Set the xsl stylesheet location for CDA transforms
	 * 
	 * @param styleSheetLocation the xsl stylesheet location
	 */
	public void setStyleSheetLocation(String styleSheetLocation) {
		this.styleSheetLocation = styleSheetLocation;
	}


	/**
	 * Get the LogContext to be used for auditing
	 * @return logContext the LogContext
	 */
	public ILogContext getLogContext() {
		return logContext;
	}
	
	/**
	 * Set the LogContext to be used for auditing
	 * @param logContext the LogContext
	 */
	public void setLogContext(ILogContext logContext) {
		this.logContext = logContext;
	}

	/**
	 * Get the PatientIdConverter.
	 * @return the patientIdConverter
	 */
	public IPatientIdConverter getPatientIdConverter() {
		return pidConverter;
	}
	/**
	 * Set the PatientIdConverter.
	 * @param pidConverter the PatientIdConverter
	 */
	public void setPatientIdConverter(IPatientIdConverter pidConverter) {
		this.pidConverter = pidConverter;
	}
	
	
	public interface ILogContext {
		String getUserId();
		String getClientAddress();
		String getUserSystem();
		String getUserName();
	}
	/**
	 * The default Patient Id converter.
	 * 
	 * @author Wenzhi Li
	 * @version 2.1 Jan 16, 2008
	 */
	public static class DefaultPatientIdConverter implements IPatientIdConverter {
		private static DefaultPatientIdConverter instance = null;
		public synchronized static DefaultPatientIdConverter getInstance() {
			if (instance==null) {
				instance = new DefaultPatientIdConverter();
			}
			return instance;
		}
		/**
		 * Gets the local assigning authority patient id. The default
		 * local patient id is the same as sourcePatientId.  
		 * 
		 * @param sourcePatientId the patient id of the source system
		 * @param patientIdSystemName The system name of this patient
		 * @return The sourcePatientId given in the parameter.
		 */
		public String getPixLocalPatientId(String sourcePatientId, String patientIdSystemName) throws PatientException {
			return sourcePatientId;
		}
	}
    /**
     * The interface that converts a source patient id to a local
     * patient id used for PIX feed. The assigning authority of the
     * local patient id should be LocalAssigninngAuthortiy in the
     * PIX actor configuration.
     *
	 * @author Wenzhi Li
	 * @version 2.1 Jan 16, 2008
     */
	public interface IPatientIdConverter {
		/**
		 * Gets a local assigning authority patient id from a source patient id.
		 * 
		 * @param sourcePatientId The patient id from a source, for example, Misys EMR.  
		 * @param patientIdSystemName The system name of this patient
		 * @return The local patient id used for PIX Feed, whose assiging authority
		 *  is LocalAssigningAuthority
		 */
		public String getPixLocalPatientId(String sourcePatientId, String patientIdSystemName) throws PatientException;
	}
}
