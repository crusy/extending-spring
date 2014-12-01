package com.acme.someproject;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * start scanning in com.acme.* to enable the starters (which all are in this package)!
 * name @PropertySource to access it later, see initialize()
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.acme" })
@PropertySource(name="main-config", value="classpath:some-project.properties")
public class SomeProject {

	@Autowired
	private Environment env;

	/*
	 * partial overwriting of configuration, see
	 * http://stackoverflow.com/a/20213990/3890673
	 */
	@PostConstruct
	public void initialize() throws IOException {
		Resource props = new ClassPathResource("acme-aservice.properties");
		if (env instanceof ConfigurableEnvironment && props.exists()) {
			MutablePropertySources sources = ((ConfigurableEnvironment) env).getPropertySources();
			sources.addAfter("main-config", new ResourcePropertySource(props));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SomeProject.class, args);
	}
}

@RestController
class IndexController {

	// should be read from acme-aservice.properties
	@Value("${aservice.messages.test1}")
	private String test1;

	// should be read from some-project.properties
	@Value("${aservice.messages.test2}")
	private String test2;

	@RequestMapping("/")
	public String index() {
		System.out.println("aservice.messages.test1: " + test1);
		System.out.println("aservice.messages.test2: " + test2);
		return "This is some project!";
	}
}