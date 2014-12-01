package com.acme.bservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.bservice.provider.DefaultMessageProvider;
import com.acme.bservice.provider.MessageProvider;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BService {

	// Debug: is everything up and (hopefully) running?
	@PostConstruct
	public void postConstruct() {
		System.out.println("Service B was constructed!");
	}

	public static void main(String[] args) {
		SpringApplication.run(BService.class, args);
	}
}

// name the controller to avoid ConflictingBeanDefinitionException, TODO: Use @ComponentScan.nameGenerator instead!
@RestController
@Component("ServiceBIndexController")
class IndexController {

	private MessageProvider messageProvider = new DefaultMessageProvider();

	// may be overwritten in actual project, see com.acme.someproject.SomeProject for example
	@Autowired(required = false)
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}

	@RequestMapping("/service-b")
	public String index() {
		return messageProvider.getMessage("welcome");
	}
}