package com.webreach.mirth.connectors.ihe;

import java.util.Properties;

import com.webreach.mirth.model.ComponentProperties;

public class IheSenderProperties implements ComponentProperties
{
	public static final String name = "IHE Sender";
	
	public static final String DATATYPE = "DataType";
    public static final String JAVASCRIPT_HOST = "host";
    public static final String JAVASCRIPT_SCRIPT = "script";

    public Properties getDefaults()
    {
        Properties properties = new Properties();
        properties.put(DATATYPE, name);
        properties.put(JAVASCRIPT_HOST, "sink");
        properties.put(JAVASCRIPT_SCRIPT, "");
        return properties;
    }
}