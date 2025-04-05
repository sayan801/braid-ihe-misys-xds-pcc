package com.webreach.mirth.connectors.ihe;

import org.mule.providers.AbstractMessageAdapter;

public class IheMessageAdapter extends AbstractMessageAdapter {
	private String message;

	public IheMessageAdapter(Object obj) {
		this.message = (String) obj;
	}

	public String getPayloadAsString() throws Exception {
		return message;
	}

	public byte[] getPayloadAsBytes() throws Exception {
		return message.getBytes();
	}

	public Object getPayload() {
		return message;
	}
}