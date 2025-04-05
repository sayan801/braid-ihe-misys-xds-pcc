package com.webreach.mirth.connectors.ihe;

import org.mule.umo.UMOException;
import org.mule.umo.provider.UMOConnector;
import org.mule.umo.provider.UMOMessageDispatcher;
import org.mule.umo.provider.UMOMessageDispatcherFactory;

public class IheMessageDispatcherFactory implements UMOMessageDispatcherFactory {
	public UMOMessageDispatcher create(UMOConnector connector) throws UMOException {
		return new IheMessageDispatcher((IheConnector) connector);
	}
}