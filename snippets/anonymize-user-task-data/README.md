# Anonymize task worker user ids

## The use case
The Camunda engine saves a lot of information about the process execution in the database.
This allows tracking of the progress and reviews, who did what in the Cockpit.

Some companies want to protect their task workers and don't allow to analyze their performance.
The manager should manage all his employees equally and don't get numbers who completes their tasks faster or does the work more thoroughly.

Data protection plays an important part these days.

The best way to implement data protection is to avoid saving unnessary data.

This process engine plugin anonymizes all the user ids of task workers doing their regular work, i.e. claiming and completing tasks, 
while keeping the user ids of interactions in the cockpit like process instance modifications or variable changes.

![operation log](images/anonymized-op-log-cockpit.png)
## How to use it?

Build the process engine plugin with `mvn clean install` and copy the `anonymize-user-task-data.jar` into the tomcat lib folder next to the camunda-engine-jar

Configure the process engine plugin in the `bpm-platform.xml` like this example:
```xml
    <plugins>
      <plugin>
        <class>com.camunda.consulting.anonymize_user_task_data.plugin.AnonymizeProcessEnginePlugin</class>
      </plugin>
    </plugins>
```
For other environments, have look into the docs: https://docs.camunda.org/manual/latest/user-guide/process-engine/process-engine-plugins/#configure-process-engine-plugins

For Spring Boot specifically, have a look into the following doc: https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/configuration/#adding-additional-configurations

Here is an example of how to use the plugin jar in your Spring Boot configuration:
```java
@Configuration
public class MyCamundaConfiguration {

    @Bean
    @Order(Ordering.DEFAULT_ORDER + 1)
    public static ProcessEnginePlugin anonymizeProcessEnginePlugin() {
        return new AnonymizeProcessEnginePlugin();
    }

}
```
During your development phase, you can specify a local library folder in your project, and a local Maven dependency like this:

```xml
    <dependency>
      <groupId>com.camunda.consulting.anonymize_user_task_data.plugin</groupId>
      <artifactId>AnonymizeProcessEnginePlugin</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <scope>system</scope>
      <systemPath>${project.basedir}/lib/anoymize-user-task-data.jar</systemPath>
    </dependency>
```

until your jar can be uploaded to your own Maven repository. 

### Unit Test
You can run the JUnit test [ProcessEnginePluginTest](/src/test/java/com/camunda/consulting/anonymize_user_task_data/ProcessEnginePluginTest.java) in your IDE or using:
```bash
mvn clean test
```

## Environment Restrictions
Built and tested against Camunda BPM version 7.13.0.

## Known Limitations

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

<!-- HTML snippet for index page
  <tr>
    <td><img src="snippets/user-task-data-examples/src/main/resources/process.png" width="100"></td>
    <td><a href="snippets/user-task-data-examples">Camunda BPM Process Application</a></td>
    <td>A Process Application for [Camunda BPM](http://docs.camunda.org).</td>
  </tr>
-->
<!-- Tweet
New @Camunda example: Camunda BPM Process Application - A Process Application for [Camunda BPM](http://docs.camunda.org). https://github.com/camunda-consulting/code/tree/master/snippets/user-task-data-examples
-->