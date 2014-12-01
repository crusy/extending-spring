# About

This is a "dummy" project for wiring our custom Spring Boot starters "service-a" and "service-b". It is used as a prove-of-concept that standalone starters can be used as libraries in other projects.

See [this link](https://blog.codecentric.de/en/2014/11/extending-spring-boot-five-steps-writing-spring-boot-starter/) for details; this code is heavily based on their example.

This project overwrites certain settings from service-a (see /src/main/resources/acme-aservice.properties), including its request mapping! Furthermore it implements com.acme.bservice.provider.MessageProvider to replace the default provider (com.acme.bservice.provider.DefaultMessageProvider).

# Setup

## compile

	$ mvn package

## compile and run

	$ mvn package && java -jar target/some-project-0.0.1.jar

## nice to know

- in case you update service-a or service-b you have to clear your local version of these JARs: `rm -R ~/.m2/repository/com/acme`. If this is not sufficient, clear the local repository: `mvn dependency:purge-local-repository -DreResolve=false`. If *this* is not sufficient, increase the version number of the updated JAR
- rebuild your dependecies (`mvn dependency:tree`) and update the Eclipse project-file (`mvn eclipse:eclipse`)

