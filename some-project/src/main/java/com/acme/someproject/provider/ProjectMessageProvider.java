package com.acme.someproject.provider;

import org.springframework.stereotype.Component;

import com.acme.bservice.provider.MessageProvider;

/*
 * @Component enables this to overwrite com.acme.bservice.provider.MessageProvider
 */
@Component
public class ProjectMessageProvider implements MessageProvider {

	@Override
	public String getMessage(String key) {
		return String.format("overwritten message provider's message with key %s!", key);
	}

}
