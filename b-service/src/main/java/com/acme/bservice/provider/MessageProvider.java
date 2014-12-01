package com.acme.bservice.provider;

/*
 * implement this to provide your own logic.
 * Check @ConditionalOnMissingBean for similar functionality of default implementation of interfaces
 * -- or even @ConditionalOnClass, @ConditionalOnExpression, @ConditionalOnMissingClass, @ConditionalOnResource, ...
 */
public interface MessageProvider {
	public String getMessage(String key);
}
