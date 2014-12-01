# About

This is a "dummy"-/prove-of-concept-implementation of custom Spring Boot starters.

See [this link](https://blog.codecentric.de/en/2014/11/extending-spring-boot-five-steps-writing-spring-boot-starter/) for details; this code is heavily based on their example.

Service A returns a String read from a config file; service B returns some random Strings from MessageProvider

# Setup

## compile

	$ mvn package

## compile and run

	$ mvn package && java -jar target/service-b-0.0.1.jar

## install

For the sake of this dummy project you can [install the JAR into your local repository](http://softwarecave.org/2014/06/14/adding-external-jars-into-maven-project/):

	$ mvn install:install-file -Dfile=./target/service-b-0.0.1.jar -DgroupId=com.acme -DartifactId=service-b -Dversion=0.0.1 -Dpackaging=jar

, just replace "0.0.1" with the according version number.

## use from another project

After providing the JAR in some repository (e.g. your local repository, see above) you can add it to another project:

	<dependency>
		<groupId>com.acme</groupId>
		<artifactId>service-b</artifactId>
		<version>0.0.1</version>
	</dependency>
