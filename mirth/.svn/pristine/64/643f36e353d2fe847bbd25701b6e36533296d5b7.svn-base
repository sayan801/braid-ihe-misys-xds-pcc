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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.misyshealthcare.connect.base.AuditBroker;
import com.misyshealthcare.connect.base.DocumentBroker;
import com.misyshealthcare.connect.base.IBrokerController;
import com.misyshealthcare.connect.base.PatientBroker;
import com.misyshealthcare.connect.base.PatientID;
import com.misyshealthcare.connect.base.codemapping.ICodeMappingManager;
import com.misyshealthcare.connect.ihe.IMesaLogger;
import com.misyshealthcare.connect.ihe.PatientIdentitySource;
import com.misyshealthcare.connect.ihe.PdqConsumer;
import com.misyshealthcare.connect.ihe.PixConsumer;
import com.misyshealthcare.connect.ihe.XdmDocumentSource;
import com.misyshealthcare.connect.ihe.XdrDocumentConsumer;
import com.misyshealthcare.connect.ihe.XdrDocumentSource;
import com.misyshealthcare.connect.ihe.XdsDocumentConsumer;
import com.misyshealthcare.connect.ihe.XdsDocumentRetrieve;
import com.misyshealthcare.connect.ihe.XdsDocumentSource;
import com.misyshealthcare.connect.ihe.XdsbDocumentContents;
import com.misyshealthcare.connect.ihe.audit.IheAuditTrail;
import com.misyshealthcare.connect.ihe.log.Log4jLogger;
import com.misyshealthcare.connect.net.ConnectionFactory;
import com.misyshealthcare.connect.net.IConnectionDescription;
import com.misyshealthcare.connect.util.LibraryConfig;
import com.misyshealthcare.connect.util.OID;
import com.misyshealthcare.connect.util.LibraryConfig.ILogContext;
import com.misyshealthcare.connect.util.LibraryConfig.IPatientIdConverter;

/**
 * This class loads an IHE Actor configuration file and initializes all of the
 * appropriate actors within the DocumentBroker, PatientBroker and AuditBroker.
 * 
 * @author Jim Firby
 * @version 2.0 - Dec 26, 2005
 * @see com.misyshealthcare.connect.base.DocumentBroker
 * @see com.misyshealthcare.connect.base.PatientBroker
 * @see com.misyshealthcare.connect.base.AuditBroker
 */
public class ConfigurationLoader {

	/* Logger for debugging messages */
	private static final Logger log = Logger.getLogger(ConfigurationLoader.class);
	
	/* Logger for IHE Actor message traffic */
	private static final Log4jLogger iheLog = new Log4jLogger();
	
	/* Singleton instance */
	private static ConfigurationLoader instance = null;
	
	/* The configuration file to load */
	//private File configFile = null;
	
	/* Current root logger appender */
	Appender currentAppender = null;
	
	/* The actor definitions loaded by the config file */
	private Vector<ActorDescription> actorDefinitions = null;
	
	/* The IDs of the actors last installed */
	/* This is a very weak set, it may not match the actual set of installed actors */
	private Vector<String> actorsInstalled = new Vector<String>();
		
	/**
	 * Get the singleton instance for this class.
	 * 
	 * @return The singleton ConfigurationLoader
	 */
	public static synchronized ConfigurationLoader getInstance() {
		if (instance == null) instance = new ConfigurationLoader();
		return instance;
	}
	
	/**
	 * Load the supplied configuration file and
	 * create all of the IHE actors that it defines.
	 * 
	 * @param filename The name of the configuration file
	 * @param oidRoot the OID root
     * @param oidSource the OID.OidSource interface which provides a unique oid id.
	 * @param styleSheetLocation the stylesheet location for CDA transformation
	 * @param patientQueryDesignProps the query design properties
	 * @param codeMappingManager the CodeMappingManager to be used for data transformation
	 * @param logContext the LogContext to be used for audit logging
	 * @return True if the configuration file was processed successfully
	 * @throws IheConfigurationException When there is something wrong with the specified configuration
	 */
	public boolean loadConfiguration(String filename, String oidRoot, OID.OidSource oidSource, String styleSheetLocation, Properties patientQueryDesignProps, ICodeMappingManager codeMappingManager, ILogContext logContext) throws IheConfigurationException {
		if (filename == null) return false;
		return loadConfiguration(new File(filename), true, oidRoot, oidSource, styleSheetLocation, patientQueryDesignProps, codeMappingManager, logContext);
	}
	
	/**
	 * Load the supplied configuration file and
	 * create all of the IHE actors that it defines.
	 * 
	 * @param file The configuration file
	 * @param oidRoot the OID root
     * @param oidSource the OID.OidSource interface which provides a unique oid id.
	 * @param styleSheetLocation the stylesheet location for CDA transformation
	 * @param patientQueryDesignProps the query design properties
	 * @param codeMappingManager the CodeMappingManager to be used for data transformation
	 * @param logContext the LogContext to be used for audit logging
	 * @return True if the configuration file was processed successfully
	 * @throws IheConfigurationException When there is something wrong with the specified configuration
	 */
	public boolean loadConfiguration(File file, String oidRoot, OID.OidSource oidSource, String styleSheetLocation, Properties patientQueryDesignProps, ICodeMappingManager codeMappingManager, ILogContext logContext) throws IheConfigurationException {
		if (file == null) return false;
		return loadConfiguration(file, true, oidRoot, oidSource, styleSheetLocation, patientQueryDesignProps, codeMappingManager, logContext);
	}
	
	/**
	 * Load the supplied configuration file.  If the argument is
	 * 'true', then create an initialize all of the IHE actors in the file.  If the
	 * argument is 'false', save the actors away for GUI access.
	 * 
	 * @param filename The name of the configuration file
	 * @param autoInstallActors If 'true' create the actors in this configuration, else store them up
	 * @param oidRoot the OID root
     * @param oidSource the OID.OidSource interface which provides a unique oid id.
	 * @param styleSheetLocation the stylesheet location for CDA transformation
	 * @param patientQueryDesignProps the query design properties
	 * @param codeMappingManager the CodeMappingManager to be used for data transformation
	 * @param logContext the LogContext to be used for audit logging
	 * @return 'true' if the configuration was loaded successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */
	public boolean loadConfiguration(String filename, boolean autoInstallActors, String oidRoot, OID.OidSource oidSource, String styleSheetLocation,
         Properties patientQueryDesignProps, ICodeMappingManager codeMappingManager, ILogContext logContext) throws IheConfigurationException {
		return loadConfiguration(new File(filename), autoInstallActors, oidRoot, oidSource, styleSheetLocation, patientQueryDesignProps, codeMappingManager, logContext);
	}

    /**
	 * Load the supplied configuration file.  If the argument is
	 * 'true', then create an initialize all of the IHE actors in the file.  If the
	 * argument is 'false', save the actors away for GUI access.
	 *
	 * @param file The configuration file
	 * @param autoInstallActors If 'true' create the actors in this configuration, else store them up
	 * @param oidRoot the OID root
     * @param oidSource the OID.OidSource interface which provides a unique oid id.
	 * @param styleSheetLocation the stylesheet location for CDA transformation
	 * @param patientQueryDesignProps the query design properties
	 * @param codeMappingManager the CodeMappingManager to be used for data transformation
	 * @param logContext the LogContext to be used for audit logging
	 * @return 'true' if the configuration was loaded successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */
	public synchronized boolean loadConfiguration(File file, boolean autoInstallActors, String oidRoot, OID.OidSource oidSource, String styleSheetLocation, Properties patientQueryDesignProps, ICodeMappingManager codeMappingManager, ILogContext logContext) throws IheConfigurationException {
		return loadConfiguration(file, autoInstallActors, true, oidRoot, oidSource, styleSheetLocation, patientQueryDesignProps, codeMappingManager, logContext);
	}
    /**
	 * Load the supplied configuration file.  If the argument is
	 * 'true', then create an initialize all of the IHE actors in the file.  If the
	 * argument is 'false', save the actors away for GUI access.
	 *
	 * @param file The configuration file
	 * @param autoInstallActors If 'true' create the actors in this configuration, else store them up
	 * @param oidRoot the OID root
     * @param oidSource the OID.OidSource interface which provides a unique oid id.
	 * @param styleSheetLocation the stylesheet location for CDA transformation
	 * @param patientQueryDesignProps the query design properties
	 * @param codeMappingManager the CodeMappingManager to be used for data transformation
	 * @param logContext the LogContext to be used for audit logging
 	 * @param pidConvert the patientIdConverter to be used to convert the sourcePatientId to the PixLocalPatientId
 	 * @return 'true' if the configuration was loaded successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */	
	public synchronized boolean loadConfiguration(File file, boolean autoInstallActors, String oidRoot, OID.OidSource oidSource, String styleSheetLocation, Properties patientQueryDesignProps, ICodeMappingManager codeMappingManager, ILogContext logContext, IPatientIdConverter pidConverter) throws IheConfigurationException {
		return loadConfiguration(file, autoInstallActors, true, oidRoot, oidSource, styleSheetLocation, patientQueryDesignProps, codeMappingManager, logContext);
	}     
    /**
	 * Load the supplied configuration file.  If the argument is
	 * 'true', then create an initialize all of the IHE actors in the file.  If the
	 * argument is 'false', save the actors away for GUI access.
	 * 
	 * @param file The configuration file
	 * @param autoInstallActors If 'true' create the actors in this configuration, else store them up
     * @param reset whether to reset actorDefinitions or resetAllBrokers
	 * @param oidRoot the OID root
     * @param oidSource the OID.OidSource interface which provides a unique oid id.
	 * @param styleSheetLocation the stylesheet location for CDA transformation
	 * @param patientQueryDesignProps the query design properties
	 * @param codeMappingManager the CodeMappingManager to be used for data transformation
	 * @param logContext the LogContext to be used for audit logging
	 * @param pidConvert the patientIdConverter to be used to convert the sourcePatientId to the PixLocalPatientId
	 * @return 'true' if the configuration was loaded successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */
	private boolean loadConfiguration(File file, boolean autoInstallActors, boolean reset, String oidRoot, 
			    OID.OidSource oidSource, String styleSheetLocation, Properties patientQueryDesignProps, 
			    ICodeMappingManager codeMappingManager, ILogContext logContext) throws IheConfigurationException {
		LibraryConfig libConfig = LibraryConfig.getInstance();
		libConfig.setOidRoot(oidRoot);
        libConfig.setOidSource(oidSource);
        libConfig.setStyleSheetLocation(styleSheetLocation);
		libConfig.setPatientQueryDesignProps(patientQueryDesignProps);
		libConfig.setCodeMappingManager(codeMappingManager);
		libConfig.setLogContext(logContext);
		if(libConfig.getPatientIdConverter()==null) {
			libConfig.setPatientIdConverter(LibraryConfig.DefaultPatientIdConverter.getInstance());
		}
		boolean okay = true;
		// Reset the list of loaded actors
		if (reset) actorDefinitions = new Vector<ActorDescription>();
		// If we are auto-installing, reset all the brokers
		if (autoInstallActors && reset) resetAllBrokers();
		// Make sure we have a configuration file
		File configFile = file;
		if (configFile == null) {
			throw new IheConfigurationException("No file given to configuration loader");
		} else if (!configFile.exists()) {
			throw new IheConfigurationException("The configuration file \"" + configFile.getAbsolutePath() + "\" does not exist");
		}
		// Create a builder factory and a builder, and get the configuration document.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		Document configuration = null;
		try {
			configuration = factory.newDocumentBuilder().parse(configFile);
		} catch (SAXException e) {
			// An XML exception
			throw new IheConfigurationException("Invalid XML in configuration file '" + configFile.getAbsolutePath() + "'", e);
		} catch (IOException e) {
			// A problem reading the file
			throw new IheConfigurationException("Cannot read configuration file '" + configFile.getAbsolutePath() + "'", e);
		} catch (ParserConfigurationException e) {
			// No XML implementation
			throw new IheConfigurationException("No XML implementation to process configuration file '" + configFile.getAbsolutePath() + "'", e);
		}
		// Get the list of XML elements in the configuration file
		NodeList configurationElements = configuration.getDocumentElement().getChildNodes();
		// Load all the connection definitions first
		for (int elementIndex = 0; elementIndex < configurationElements.getLength(); elementIndex++) {
			Node element = configurationElements.item(elementIndex);
			if (element instanceof Element) {
				// See what type of element it is
				String name = element.getNodeName();
				if (name.equalsIgnoreCase("CONNECTIONFILE")) {
					// An included connection file, load it
					if (!processConnectionFile((Element) element, configFile)) okay = false;
				} else if (name.equalsIgnoreCase("SECURECONNECTION") || name.equalsIgnoreCase("STANDARDCONNECTION")) {
					// An included connection, load it
					if (!ConnectionFactory.loadConnectionDescriptionsFromXmlNode(element, configFile)) {
						throwIheConfigurationException("Error loading configuration file '" + configFile.getAbsolutePath() + "'", configFile);
						okay = false;
					}
				}
			}
		}
		// If all the connection files loaded okay, define the various actors
		if (okay) {
			for (int elementIndex = 0; elementIndex < configurationElements.getLength(); elementIndex++) {
				Node element = configurationElements.item(elementIndex);
				if (element instanceof Element) {
					// See what type of element it is
					String name = element.getNodeName();
                    if (name.equalsIgnoreCase("ACTORFILE")) {
                        if (!processActorFile((Element) element, autoInstallActors, configFile, false)) okay = false;
                    } else if (name.equalsIgnoreCase("ACTOR")) {
						// An IHE actor definition
						if (!processActorDefinition((Element) element, autoInstallActors, configFile)) okay = false;
					}
				}
			}
		}
		// Done
		return true;
	}
	
	/** Sets the default file to write to for the root.
	 * Also sets the level.  Note: if level is null 
	 * the rool level will be set to INFO.  Note that
	 * if there already is a file appender set this way
	 * it will be removed before the new one is added.
	 * All other appenders are left untouched.
	 * 
	 * @param fullPathToLogFile Path to append the log to.
	 * @param level Level to log, null for INFO.
	 * @param pattern Some other pattern to use for logging.  null ok.
	 */
	public synchronized void setLoggingFile(String fullPathToLogFile, Level level, String pattern) {
		Logger root = Logger.getRootLogger();
		try {
			if (pattern == null)
				pattern =  "Milliseconds since program start: %r %n" +
					"Date of message: %d %n" +
					//"Classname of caller: %C %n" +
					"Location: %l %n" +
					"Message: %m %n %n";
			if (currentAppender != null) root.removeAppender(currentAppender);
			if (fullPathToLogFile != null) {
				currentAppender = new FileAppender(new PatternLayout(pattern), fullPathToLogFile);
				if (currentAppender != null) root.addAppender(currentAppender);
				if (level == null) level = Level.INFO;
				root.setLevel(level);
			}
		} catch(Exception e) { log.error("Unable to set output file for logger: " + fullPathToLogFile, e); }
	}
	
	/**
	 * Get the actor descriptions loaded in the configuration.
	 * 
	 * @return The actor descriptions
	 */
	public synchronized Collection<IheActorDescription> getActorDescriptions() {
		Vector<IheActorDescription> actors = new Vector<IheActorDescription>();
		if (actorDefinitions != null) {
			for (ActorDescription actor: actorDefinitions) {
				actor.isInstalled = actorsInstalled.contains(actor.id);
				actors.add(actor);
			}
		}
		return actors;
	}
	
	/**
	 * Reset the current IHE brokers to use the actors described in the list passed
	 * in.  These may be actor descriptions objects or the IDs of actor description
	 * objects.  This call will not do any logging.
	 * 
	 * @param actorDescriptions The actor descriptions or IDs to use to define the actors
	 * @return 'true' if the actors were created and initialized successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */
	public synchronized boolean resetConfiguration(Collection<Object> actorDescriptions) throws IheConfigurationException {
		return resetConfiguration(actorDescriptions, null);
	}
	/**
	 * Reset the current IHE brokers to use the actors described in the list passed
	 * in.  These may be actor descriptions objects or the IDs of actor description
	 * objects.
	 * 
	 * @param actorDescriptions The actor descriptions or IDs to use to define the actors
	 * @param logFilename The log file to install for this set of actors
	 * @return 'true' if the actors were created and initialized successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */
	public synchronized boolean resetConfiguration(Collection<Object> actorDescriptions, String logFilename) throws IheConfigurationException {  
		return resetConfiguration(actorDescriptions, logFilename, null);
	}

	/**
	 * Reset the current IHE brokers to use the actors described in the list passed
	 * in.  These may be actor descriptions objects or the IDs of actor description
	 * objects.
	 * 
	 * @param actorDescriptions The actor descriptions or IDs to use to define the actors
	 * @param logFilename The log file to install for this set of actors
     * @param mesaLog The mesa log used for mesa tests
	 * @return 'true' if the actors were created and initialized successfully
	 * @throws IheConfigurationException When there is a problem with the configuration file
	 */
	public synchronized boolean resetConfiguration(Collection<Object> actorDescriptions, String logFilename, IMesaLogger mesaLog) throws IheConfigurationException {
		// Reset all the brokers
		resetAllBrokers();
		// Setup the log
		log.info("Log file closed.");
		setLoggingFile(logFilename, null, null);
		log.info("Log file opened.");
		// Jump out if nothing to start up
		if (actorDescriptions == null) return true;
		// First, map all the supplied actor/actor names into descriptions
		ArrayList<ActorDescription> actors = new ArrayList<ActorDescription>();
		for (Object thing: actorDescriptions) {
			if (thing instanceof ActorDescription) {
				// Its an actor
				actors.add((ActorDescription) thing);
			} else if (thing instanceof String) {
				// Its an actor name
				ActorDescription actor = getDescriptionById((String) thing);
				if (actor != null) actors.add(actor);
			}
		}
		// Second, pull out the connections from any Secure Node actors and the source from the XdsDocumentConsumer
		ArrayList<IConnectionDescription> auditConnections = new ArrayList<IConnectionDescription>();
		IConnectionDescription source = null;
		for (ActorDescription actor: actors) {
			if (actor.actorType.equalsIgnoreCase("SecureNode")) {
				// This is an audit repository, save its connections for all actors to use
				Collection<IConnectionDescription> logConnections = actor.logConnections;
				if (logConnections != null) auditConnections.addAll(logConnections);
			} else if (actor.actorType.equalsIgnoreCase("XdsDocumentConsumer")) {
				// This actor gets documents, save it as a replace query source
				if (actor.sourceConnection != null) source = actor.sourceConnection;
			}
		}
		// Third, create all the actors
		boolean okay = true;
		IMesaLogger log = mesaLog==null ? iheLog : mesaLog;
		for (ActorDescription actor: actors) {
			Collection<IConnectionDescription> actorAudit = actor.logConnections;
			if ((actorAudit == null) || actorAudit.isEmpty()) actorAudit = auditConnections;
			IConnectionDescription sourceConnection = actor.sourceConnection;
			if (actor.actorType.equalsIgnoreCase("XdsDocumentSource")) {
				if (sourceConnection == null) sourceConnection = source;
			}
			if (!createIheActor(actor.actorType, actor.id, sourceConnection, actor.consumerConnection, actorAudit, log, null))
				okay = false;
		}
		// Finally, tell PatientID cross-referencing to default to MisysUniqueID if no xref sources
		//  This behavior is needed in the connectathon
		PatientID.setDefaultToLocalId(true);
		// Done
		return okay;
	}
	
	private void resetAllBrokers() {
		// Create a controller that will reset all IHE actors
		IheBrokerController controller = new IheBrokerController();
		// Apply it to the AuditBroker
		AuditBroker abroker = AuditBroker.getInstance();
		abroker.unregisterAuditSources(controller);
		// Apply it to the DocumentBroker
		DocumentBroker dbroker = DocumentBroker.getInstance();
		dbroker.unregisterDocumentSources(controller);
		dbroker.unregisterDocumentConsumers(controller);
		// Apply it to the PatientBroker
		PatientBroker pbroker = PatientBroker.getInstance();
		pbroker.unregisterPatientSources(controller);
		pbroker.unregisterPatientConsumers(controller);
		pbroker.unregisterPatientXrefs(controller);
        //Apply it to DocumentContent
		XdsbDocumentContents.unregisterDocumentRepository(controller);
        // Okay, nothing is installed
		actorsInstalled.clear();
	}
	
	/**
	 * Look up an actor description given an ID.
	 * 
	 * @param id The actor description ID
	 * @return The actor description, if there is one
	 */
	public ActorDescription getDescriptionById(String id) {
		if (id == null) return null;
		for (ActorDescription actor: actorDefinitions) {
			if (id.equalsIgnoreCase(actor.getId())) return actor;
		}
		return null;
	}
	
	/**
	 * Process a ConnectionFile element.  This element will specify a file that includes
	 * definitions of various connections that can be used by the IHE actors.
	 * 
	 * @param element The XML DOM element defining the connection file
	 * @return True if the file was loaded successfully
	 * @throws IheConfigurationException When there is a problem with the configuration
	 */
	private boolean processConnectionFile(Element element, File configFile) throws IheConfigurationException {
		boolean okay = false;
		// Get out the file name
		String filename = getAttributeValue(element, "file");
		if (filename == null) filename = getAttributeValue(element, "name");
		if (filename == null) filename = getNodeAsText(element);
		if (filename != null) {
			// Got the connection file name, load it
			File includeFile = new File(configFile.getParentFile(), filename);
			if (ConnectionFactory.loadConnectionDescriptionsFromFile(includeFile)) {
				okay = true;
			} else {
				throwIheConfigurationException("Error loading connection file \"" + filename + "\"", configFile);
			}
		} else {
			// No connection file name given
			logConfigurationWarning("Missing attribute 'name' in 'ConnectionFile' definition", configFile);
		}
		// Done
		return okay;
	}

    private boolean processActorFile(Element element, boolean autoInstall, File configFile, boolean resetActorDefinition) throws IheConfigurationException {
        boolean okay = false;
        // Get out the file name
        String filename = getAttributeValue(element, "file");
        if (filename == null) filename = getAttributeValue(element, "name");
        if (filename == null) filename = getNodeAsText(element);
        if (filename != null) {
            // Got the actor file name, load it
            File includeFile = new File(configFile.getParentFile(), filename);
            if (loadConfiguration(includeFile, autoInstall, false, LibraryConfig.getInstance().getOidRoot(),
                   LibraryConfig.getInstance().getOidSource(), LibraryConfig.getInstance().getStyleSheetLocation(),
                   LibraryConfig.getInstance().getPatientQueryDesignProps(), LibraryConfig.getInstance().getCodeMappingManager(),
                   LibraryConfig.getInstance().getLogContext())) {
                okay = true;
            } else {
                throwIheConfigurationException("Error loading actor file \"" + filename + "\"", configFile);
            }
        } else {
            // No connection file name given
            logConfigurationWarning("Missing attribute 'name' in 'ActorFile' definition", configFile);
        }
        // Done
        return okay;
    }

    /**
	 * Process an Actor element.  This element will specify a single IHE actor
	 * and the connection(s) it should use.
	 * 
	 * @param element The XML DOM element defining the actor
	 * @return True if the actor was created successfully
	 * @throws IheConfigurationException When there is a problem with the configuration
	 */
	private boolean processActorDefinition(Element element, boolean autoInstall, File configFile) throws IheConfigurationException {
		boolean okay = false;
		// Get out the actor name and type
		String actorName = getAttributeValue(element, "name");
		if (actorName == null)
			throwIheConfigurationException("Missing attribute 'name' in 'Actor' definition", configFile);
		String actorType = getAttributeValue(element, "type");
		if (actorType == null)
			throwIheConfigurationException("Missing attribute 'type' in 'Actor' definition", configFile);
		// Process the definition
		okay = processIheActorDefinition(actorType, actorName, element, autoInstall, configFile);
		return okay;
	}
	
	/**
	 * Process an Actor element to extract the parameters and create and install the appropriate
	 * object.
	 * 
	 * @param actorType The type of the actor to create
	 * @param actorName The name for this actor within the configuration file
	 * @param definition The XML DOM element defining the actor
	 * @return True if the actor was create successfully
	 * @throws IheConfigurationException When there is a problem with the configuration
	 */
	private boolean processIheActorDefinition(String actorType, String actorName, Element definition, boolean autoInstall, File configFile) throws IheConfigurationException {
		// Parse out the following information
		String description = null;
		IConnectionDescription sourceConnection = null;
		IConnectionDescription consumerConnection = null;
		ArrayList<IConnectionDescription> logConnections = new ArrayList<IConnectionDescription>();
		// Look at each child node in turn
		NodeList elements = definition.getChildNodes();
		for (int elementIndex = 0; elementIndex < elements.getLength(); elementIndex++) {
			Node element = elements.item(elementIndex);
			if (element instanceof Element) {
				// See what type of element it is
				String kind = element.getNodeName();
				if (kind.equalsIgnoreCase("CONNECTION")) {
					// It is a connection element, get out the connection description
					String sourceName = getAttributeValue(element, "source");
					String consumerName = getAttributeValue(element, "consumer");
					if ((sourceName == null) && (consumerName == null) ) {
						logConfigurationWarning("Connection element with no 'source' or 'consumer' attribute", configFile);
					} else {
						// Pull out the source connection
						if (sourceName != null) {
							if (sourceConnection != null) {
								logConfigurationWarning("Duplicate 'source' connection attributes", configFile);
							} else {
								sourceConnection = ConnectionFactory.getConnectionDescription(sourceName);
								if (sourceConnection == null) {
									throwIheConfigurationException("Connection '" + sourceName + "' in actor '" + actorName + "' is not defined", configFile);
								}
							}
						}
						// Pull out the consumer connection
						if (consumerName != null) {
							if (consumerConnection != null) {
								logConfigurationWarning("Duplicate 'consumer' connection attributes", configFile);
							} else {
								consumerConnection = ConnectionFactory.getConnectionDescription(consumerName);
								if (consumerConnection == null) {
									throwIheConfigurationException("Connection '" + consumerName + "' in actor '" + actorName + "' is not defined", configFile);
								}
							}
						}					
					}
					
				} else if (kind.equalsIgnoreCase("AUDITTRAIL")) {
					// An ATNA logger definition
					String logName = getAttributeValue(element, "consumer");
					if (logName == null) {
						logConfigurationWarning("AuditTrail element with no 'consumer' attribute", configFile);
					}
					IConnectionDescription logConnection = ConnectionFactory.getConnectionDescription(logName);
					if (logConnection == null) {
						throwIheConfigurationException("AuditTrail connection '" + logName + "' in actor '" + actorName + "' is not defined", configFile);
					}
					logConnections.add(logConnection);
					
				} else if (kind.equalsIgnoreCase("DESCRIPTION")) {
					// A description of this actor for GUI presentation
					description = getAttributeValue(element, "value");
					if (description == null) description = getNodeAsText(element);
					
				} else {
					// Not an element we know about
					logConfigurationWarning("Unknown actor XML element '" + kind + "'", configFile);
				}
			}
		}
		// Allow for some sloppiness in Secure Nodes
		if (actorType.equalsIgnoreCase("SecureNode")) {
			boolean warn = false;
			if (sourceConnection != null) {
				warn = true;
				logConnections.add(sourceConnection);
			}
			if (consumerConnection != null) {
				warn = true;
				logConnections.add(consumerConnection);
			}
			if (warn)
				log.warn("Actor '" + actorName + "' specifies a connection instead of an auditTrail in configuration file \"" + configFile.getAbsolutePath() + "\"");
		}
		// Make sure we got out a valid definition
		if ((!actorType.equalsIgnoreCase("SecureNode")) && (sourceConnection == null) && (consumerConnection == null))
			throw new IheConfigurationException("Actor '" + actorName + "' must specify a valid connection in configuration file \"" + configFile.getAbsolutePath() + "\"");
		if (actorType.equalsIgnoreCase("SecureNode") && logConnections.isEmpty())
			throw new IheConfigurationException("Actor '" + actorName + "' must specify a valid auditTrail in configuration file \"" + configFile.getAbsolutePath() + "\"");
			// Actually create the actor
		if (autoInstall) {
			return createIheActor(actorType, actorName, sourceConnection, consumerConnection, logConnections, null, configFile);
		} else {
			ActorDescription actor = new ActorDescription();
			actor.id = actorName;
			actor.type = getHumanActorTypeString(actorType, configFile);
			actor.actorType = actorType;
			actor.sourceConnection = sourceConnection;
			actor.consumerConnection = consumerConnection;
			actor.logConnections = logConnections;
			if (consumerConnection != null) {
				actor.description = getHumanConnectionDescription(description, consumerConnection);
			} else if (sourceConnection != null) {
				actor.description = getHumanConnectionDescription(description, sourceConnection);
			} else if (actorType.equalsIgnoreCase("SecureNode") && !logConnections.isEmpty()) {
				actor.description = getHumanConnectionDescription(description, logConnections.get(0));
			} else {
				actor.description = actorName;
			}
			actorDefinitions.add(actor);
			return true;
		}
	}
	
	private String getHumanActorTypeString(String type, File configFile) throws IheConfigurationException {
		if (type.equalsIgnoreCase("PatientIdentitySource")) {
			return "Patient Identity Feed Consumer";
		} else if (type.equalsIgnoreCase("PdqConsumer")) {
			return "Patient Demographics Source";
		} else if (type.equalsIgnoreCase("SecureNode")) {
			return "Audit Record Repository";
		} else if (type.equalsIgnoreCase("PixConsumer")) {
			return "PIX Cross-Reference Manager";
		} else if (type.equalsIgnoreCase("XdsDocumentConsumer")) {
			return "XDS Document Registry (Query)";
		} else if (type.equalsIgnoreCase("XdsDocumentSource")) {
			return "XDS Document Repository (Submit)";
		} else if (type.equalsIgnoreCase("XdsDocumentRetrieve")){
			return "XDS.b Document Repository/Init Gateway (Retrieve)";
		} else if (type.equalsIgnoreCase("XdrDocumentSource")) {
			return "XDR Document Source";
		} else if (type.equalsIgnoreCase("XdrDocumentConsumer")) {
			return "XDR Document Recipient";
		} else if (type.equalsIgnoreCase("XdmDocumentSource")) {
			return "XDM Document Source";
		} 
		else {
			throwIheConfigurationException("Invalid actor type '" + type + "'", configFile);
			return null;
		}
	}
	
	private String getHumanConnectionDescription(String description, IConnectionDescription connection) {
		StringBuffer sb = new StringBuffer();
		String hostName = connection.getHostname();
		int port = connection.getPort();
		if (description != null)  {
			// "description host:port (TLS)"
			sb.append(description);
			if (connection.isSecure()) sb.append(" (TLS)");
			if (hostName != null) {
				sb.append(' ');
				sb.append(hostName);
				if (port >= 0) {
					sb.append(':');
					sb.append(port);
				}
			}
		} else if (hostName != null) {
			// "host:port (TLS)"
			sb.append(hostName);
			if (port >= 0) {
				sb.append(':');
				sb.append(port);
			}
			if (connection.isSecure()) sb.append(" (TLS)");
		}
		// Done
		return sb.toString();
	}
	
	/**
	 * Create an IHE actor and install it into the PatientBroker or DocumentBroker.
	 * 
	 * @param type The type of actor to create
	 * @param name The name of the actor to create (used in audit messages)
	 * @param sourceConnection The connection it should use to get information
	 * @param consumerConnection The connection it should use to send information
	 * @param auditConnections The audit trail connections this actor should log to
	 * @param logger The IHE actor message logger to use for this actor, null means no message logging
	 * @return True if the actor is created successfully
	 * @throws IheConfigurationException When there is a problem with the configuration
	 */
	private boolean createIheActor(String type, String name, IConnectionDescription sourceConnection, IConnectionDescription consumerConnection, Collection<IConnectionDescription> auditConnections, IMesaLogger logger, File configFile) throws IheConfigurationException {
		boolean okay = false;
		IheAuditTrail auditTrail = null;
		// Build a new audit trail if there are any connections to audit repositories.
		if (!auditConnections.isEmpty()) auditTrail = new IheAuditTrail(name, auditConnections);
		// Create the actor
		if (type.equalsIgnoreCase("PatientIdentitySource")) {
			// Create the new patient identity source (a consumer of our patient creations)
			IConnectionDescription connection = consumerConnection;
			if (connection == null) connection = sourceConnection;
			PatientIdentitySource consumer = new PatientIdentitySource(connection, auditTrail);
			if (consumer != null) {
				consumer.setMesaLogger(logger);
				PatientBroker broker = PatientBroker.getInstance();
				broker.registerPatientConsumer(consumer);
				okay = true;
			}
		} else if (type.equalsIgnoreCase("PdqConsumer")) {
			IConnectionDescription connection = sourceConnection;
			if (connection == null) connection = consumerConnection;
			// Create the new PDQ consumer (a source of patient demographics for us)
			log.info("PDQ Create: connection=" + connection + " auditTrail=" + auditTrail);
			PdqConsumer source = new PdqConsumer(connection, auditTrail);
			if (source != null) {
				source.setMesaLogger(logger);
				PatientBroker broker = PatientBroker.getInstance();
				broker.registerPatientSource(source);
				okay = true;
			}			
		} else if (type.equalsIgnoreCase("SecureNode")) {
			// TODO: add error message if there is no audit trail.
			if (auditTrail != null) {
				AuditBroker broker = AuditBroker.getInstance();
				broker.registerAuditSource(auditTrail);
				okay = true;
			}
		} else if (type.equalsIgnoreCase("PixConsumer")) {
			// Create the new PIX consumer (a source of patient xref information for us)
			IConnectionDescription connection = sourceConnection;
			if (connection == null) connection = consumerConnection;
			PixConsumer xref = new PixConsumer(connection, auditTrail);
			if (xref != null) {
				xref.setMesaLogger(logger);
				PatientBroker broker = PatientBroker.getInstance();
				broker.registerPatientXref(xref);
				okay = true;
			}			
		} else if (type.equalsIgnoreCase("XdsDocumentConsumer")) {
			// Create the new XDS document consumer -XDS Registry (a source of documents for us)
			XdsDocumentConsumer source = new XdsDocumentConsumer(sourceConnection, auditTrail);
			if (source != null) {
				source.setMesaLogger(logger);
				DocumentBroker broker = DocumentBroker.getInstance();
				broker.registerDocumentSource(source);
				okay = true;
			}			
		} else if (type.equalsIgnoreCase("XdsDocumentSource")) {
			// Create a new XDS document source - XDS Repository (a consumer of documents we build)
			XdsDocumentSource consumer = new XdsDocumentSource(consumerConnection, sourceConnection, auditTrail);
			if (consumer != null) {
				consumer.setMesaLogger(logger);
				DocumentBroker broker = DocumentBroker.getInstance();
				broker.registerDocumentConsumer(consumer);
				okay = true;
			}						
		} else if (type.equalsIgnoreCase("XdsDocumentRetrieve")) {
			// Create a new XDS document retrieve - XDS Repository or Init Gateway (a source of documents)  
			XdsDocumentRetrieve source = new XdsDocumentRetrieve(sourceConnection, auditTrail);
			if (source != null) {
				source.setMesaLogger(logger);				
				XdsbDocumentContents.registerDocumentRepository( source );
				okay = true;
			}						
		} else if (type.equalsIgnoreCase("XdrDocumentConsumer")) {
			// Create the new XDS document consumer (a source of documents for us)
			XdrDocumentConsumer source = new XdrDocumentConsumer(sourceConnection, auditTrail);
			if (source != null) {
				source.setMesaLogger(logger);
				DocumentBroker broker = DocumentBroker.getInstance();
				broker.registerDocumentSource(source);
				okay = true;
			}			
		} else if (type.equalsIgnoreCase("XdrDocumentSource")) {
			// Create a new XDS document source (a consumer of documents we build)
			XdrDocumentSource consumer = new XdrDocumentSource(consumerConnection, sourceConnection, auditTrail);
			if (consumer != null) {
				consumer.setMesaLogger(logger);
				DocumentBroker broker = DocumentBroker.getInstance();
				broker.registerXdrDocumentConsumer(consumer);
				okay = true;
			}						
		} else if (type.equalsIgnoreCase("XdmDocumentSource")) {
			// Create a new XDM document source (a consumer of documents we build)
			XdmDocumentSource consumer = new XdmDocumentSource(consumerConnection, sourceConnection, auditTrail);
			if (consumer != null) {
				consumer.setMesaLogger(logger);
				DocumentBroker broker = DocumentBroker.getInstance();
				broker.registerXdmDocumentConsumer(consumer);
				okay = true;
			}						
		}   		else {
			throwIheConfigurationException("Invalid actor type '" + type + "'", configFile);
		}
		// Record this installation, if it succeeded
		if (okay) actorsInstalled.add(name);
		return okay;
	}

    /**
     * Get an instance of a Class. This method try to instantiate by newInstance first. If it
     * does not exist, it will try to invoke getInstance();
     *
     * @param c The class whose object is to be instantiated
     * @return an Instance of Class c
     * @throws Exception If the object cannot be instantiated
     */
    private Object getObjectInstance(Class c) throws Exception {
        Object ret = null;
        try {
            //try new instance first
            ret = c.newInstance();
        } catch (InstantiationException e) {
           //try getInstance() method
            Method method = c.getMethod("getInstance");
            ret = method.invoke(null);
        }
        return ret;
    }
    /**
	 * Log a configuration file warning.
	 * 
	 * @param message The warning to log
	 */
	private void logConfigurationWarning(String message, File configFile) {
		String filename = null;
		if (configFile != null) filename = configFile.getAbsolutePath();
		String warning = message;
		if (filename != null) warning = message + " in configuration file \"" + filename + "\"";
		log.warn(warning);
	}

	/**
	 * Throw a new IheConfigurationException.
	 * 
	 * @param message The message to include in the exception
	 * @throws IheConfigurationException The exception
	 */
	private void throwIheConfigurationException(String message, File configFile) throws IheConfigurationException {
		String filename = null;
		if (configFile != null) filename = configFile.getAbsolutePath();
		String error = message;
		if (filename != null) error = message + " in configuration file \"" + filename + "\"";
		log.error(error);
		throw new IheConfigurationException(message);
	}
	
	/**
	 * Get an atribute value
	 * 
	 * @param node The XML DOM node holding the attribute
	 * @param name The name of the attribute
	 * @return The value of the attribute
	 */
	private String getAttributeValue(Node node, String name) {
		NamedNodeMap attributes = node.getAttributes();
		if (attributes == null) return null;
		Node attribute = attributes.getNamedItem(name);
		if (attribute == null) return null;
		return attribute.getNodeValue();
	}
	
	/**
	 * Get the text included within an XML DOM element
	 * 
	 * @param node The XML DOM node holding the text
	 * @return The text
	 */
	private String getNodeAsText(Node node) {
		if (!node.hasChildNodes()) return null;
		Text nodeTextContents = (Text) node.getFirstChild();
		return nodeTextContents.getData();
	}	
	
	/**
	 * An implementation of the IheActorDescription class to be used by 
	 * GUI elements and other things.
	 * 
	 * @author Jim Firby
	 * @version 2.0 - Jan 11, 2006
	 */
	public class ActorDescription implements IheActorDescription {
		
		private String id = null;
		private String type = null;
		private String description = null;
		private boolean isInstalled = false;
		
		private String actorType = null;
		private IConnectionDescription sourceConnection = null;
		private IConnectionDescription consumerConnection = null;
		private Collection<IConnectionDescription> logConnections = null;

		public String getDescription() {
			return description;
		}

		public String getId() {
			return id;
		}

		public String getType() {
			return type;
		}
		
		public boolean isInstalled() {
			return isInstalled;
		}
		public IConnectionDescription getConnection() {
			if (sourceConnection != null) return sourceConnection;
			else return consumerConnection;
		}
		public Collection<IConnectionDescription> getLogConnection() {
			return logConnections;
		}
	}
	
	/**
	 * An implementation of a broker controller that will unregister and IHE actor.
	 * 
	 * @author Jim Firby
	 * @version 2.0 - Jan 11, 2006
	 */
	public class IheBrokerController implements IBrokerController {

		public boolean shouldUnregister(Object actor) {
			// Unregister any IHE Actor
			if (actor instanceof XdsDocumentConsumer) return true;
			if (actor instanceof XdsDocumentSource) return true;
			if (actor instanceof XdsDocumentRetrieve) return true;
			if (actor instanceof PixConsumer) return true;
			if (actor instanceof PdqConsumer) return true;
			if (actor instanceof PatientIdentitySource) return true;
			if (actor instanceof IheAuditTrail) return true;

            return false;
		}
		
	}
	
	/** testing only **/
	public static void main(String args[]) {
		ConfigurationLoader.getInstance().setLoggingFile("testfile.txt", null, null);
		Logger log = Logger.getLogger("mylogger");
		log.fatal("**my fatal error**");
		log.error("**my error error**");
		log.warn("**my warn error**");
		log.info("**my info error**");
		log.debug("**my debug error**");
	}
}
