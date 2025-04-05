package com.webreach.mirth.plugins.ihe;

import com.misyshealthcare.connect.util.OID;

final class OidMock implements OID.OidSource {
	public synchronized String generateId() {
		return Long.toString(System.currentTimeMillis());
	}
}
