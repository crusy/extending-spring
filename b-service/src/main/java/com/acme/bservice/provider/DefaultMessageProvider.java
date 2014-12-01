package com.acme.bservice.provider;

public class DefaultMessageProvider implements MessageProvider {

	@Override
	public String getMessage(String key) {
		return String.format("[default msg for key %s]", key);
	}

}
