package com.webreach.mirth.plugins.ihe;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.misyshealthcare.connect.base.codemapping.CodeMappingManager;
import com.misyshealthcare.connect.ihe.configuration.ConfigurationLoader;
import com.misyshealthcare.connect.ihe.configuration.IheActorDescription;
import com.webreach.mirth.plugins.ServerPlugin;

public class IheConfigurationLoader implements ServerPlugin {
	public static final String CONFIGURATION_PATH = "configFile";
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String USER_SYSTEM = "userSystem";
	public static final String CLIENT_ADDRESS = "clientAddress";
	
	private Logger logger = Logger.getLogger(this.getClass());
	private Properties properties;
	List<String[]> actors;
	
	public void init(Properties properties) {
		this.properties = properties;
		actors = new ArrayList<String[]>();
		loadConfiguration(properties);
	}

	public void start() {

	}

	public void update(Properties properties) {
		this.properties = properties;
		loadConfiguration(properties);
	}

	public boolean loadConfiguration(Properties properties) {
	
		if (properties.get(CONFIGURATION_PATH) != null && ((String) properties.get(CONFIGURATION_PATH)).length() > 0) {
			String userId = "";
			String userName = "";
			String userSystem = "";
			String clientAddress = "";
			
			if (properties.get(USER_ID) != null && ((String) properties.get(USER_ID)).length() > 0) {
				userId = (String) properties.get(USER_ID);
			}
			if (properties.get(USER_NAME) != null && ((String) properties.get(USER_NAME)).length() > 0) {
				userName = (String) properties.get(USER_NAME);
			}
			if (properties.get(USER_SYSTEM) != null && ((String) properties.get(USER_SYSTEM)).length() > 0) {
				userSystem = (String) properties.get(USER_SYSTEM);
			}
			if (properties.get(CLIENT_ADDRESS) != null && ((String) properties.get(CLIENT_ADDRESS)).length() > 0) {
				clientAddress = (String) properties.get(CLIENT_ADDRESS);
			}
			
			String configFile = (String) properties.get(CONFIGURATION_PATH);
			ConfigurationLoader loader = ConfigurationLoader.getInstance();

			try {
				File file = new File(configFile);
				loader.loadConfiguration(file, false, "2.16.840.1.113883.3.65.2", new OidMock(), null, null, CodeMappingManager.getInstance(), new IheLogContext(userId, userName, userSystem, clientAddress));
				actors = cacheActors();
				loader.resetConfiguration((Collection) loader.getActorDescriptions(), "testlog.xml");
                return true;
			} catch (Exception e) {
				logger.error("Could not initialize IHE configuration: " + configFile, e);
			}
		}
        actors = new ArrayList<String[]>();
        return true;
	}

	public List<String[]> cacheActors() {
		List<String[]> actors = new ArrayList<String[]>();

		for (IheActorDescription description : ConfigurationLoader.getInstance().getActorDescriptions()) {
			actors.add(new String[] { description.getId(), description.getType(), description.getDescription() });
		}

		return actors;
	}
	
	public List<String> getActorNames() {
		List<String> actorNames = new ArrayList<String>();

		for (String[] s : actors) {
            actorNames.add(s[0]);
		}

		return actorNames;
	}

	public void onDeploy() {
		loadConfiguration(properties);
	}

	public void stop() {

	}

	public Object invoke(String method, Object object, String sessionId) {
		if (method.equals("getActors")) {
			return actors;
		} else if (method.equals("getActorNames")) {
			return getActorNames();
		} else if (method.equals("reloadConfiguration")) {
			return new Boolean(loadConfiguration(properties));
		}

		return null;
	}

	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		properties.put(CONFIGURATION_PATH, "");
		return properties;
	}
}
