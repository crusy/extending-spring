package com.acme.aservice.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/*
 * this class is referenced in /src/main/resources/META-INF/spring.factories!
 */
@Configuration
@PropertySource("classpath:acme-aservice.properties")
public class AServiceConfiguration {

	@Autowired
	private Environment env;

	// Debug: is everything up and (hopefully) running?
	@PostConstruct
	public void postConstruct() {
		System.out.println("AServiceConfiguration was constructed!");
	}

	public <T> T getConfigValue(String key, Class<T> targetType, T defaultValue) {
		return env.getProperty(key, targetType, defaultValue);
	}

	public String getMessage(String messageKey) {
		return env.getProperty("aservice.messages." + messageKey, String.class, String.format("[default msg for key %s]", messageKey));
	}
}
