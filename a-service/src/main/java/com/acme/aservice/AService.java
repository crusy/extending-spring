package com.acme.aservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.aservice.configuration.AServiceConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AService {

	// Debug: is everything up and (hopefully) running?
	@PostConstruct
	public void postConstruct() {
		System.out.println("Service A was constructed!");
	}

	public static void main(String[] args) {
		SpringApplication.run(AService.class, args);
	}
}

// name the controller to avoid ConflictingBeanDefinitionException, TODO: Use @ComponentScan.nameGenerator instead!
@RestController
@Component("ServiceAIndexController")
class IndexController {

	@Autowired
	private AServiceConfiguration config;

	// ${batch.web.monitoring.base:/batch/monitoring}
	@RequestMapping("${aservice.mapping.url:/service-a}")
	public String index() {
		return config.getMessage("welcome");
	}
}