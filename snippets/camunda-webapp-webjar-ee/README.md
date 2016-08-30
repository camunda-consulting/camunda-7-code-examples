Build camunda-webapp-webjar for Enterprise Edition
=========================

Currently there is no webjar build for Enterprise Edition as it is not yet defined how to do QA for such a build. See [https://app.camunda.com/jira/browse/CAM-5834](CAM-5834).

However it is very easy to build the webjar yourself, e.g. to use it within your own Spring Boot application.

Simply 

* Adjust dependency version in pom.xml
* Run Maven build (make sure you can access the Enterprise artifacts by using your Enterprise credentials):

```
 mvn install
```

Now you can use this dependency:

```
<dependency>
  <groupId>com.camunda.consulting.demo</groupId>
  <artifactId>camunda-webapp-webjar-ee</artifactId>
  <version>${camunda.webjar.version}</version>		  
</dependency>			
```

If used in [](Spring Boot Starter) make sure you exclude the Community Edition artifact:

```
<dependency>
	<groupId>org.camunda.bpm.extension</groupId>
	<artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
	<version>${camunda.spring.starter.version}</version>
	<exclusions>
		<exclusion>
			<artifactId>camunda-webapp-webjar</artifactId>
			<groupId>org.camunda.bpm.webapp</groupId>
		</exclusion>
	</exclusions>
</dependency>
<dependency>
  <groupId>com.camunda.consulting.demo</groupId>
  <artifactId>camunda-webapp-webjar-ee</artifactId>
  <version>${camunda.webjar.version}</version>		  
</dependency>	
```

Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.6.0-alpha3-ee.


License
-------
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
