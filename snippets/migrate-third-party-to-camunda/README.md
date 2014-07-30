# How to migrate processes from existing solutions to camunda BPM

In every company you will sooner or later migrate to camunda BPM as your favorite BPM platform :-) When doing this you often do not start on a green field - but have ruinning process instances. How to handle this?

There are different approaches to migrate process definitions and process instances, this whitepaper gives an overview and introduction into the topic: TODO-add-link.

This project is an example implementation of migration scenarios, where we add elements to the BPMN in order to do the migration and afterwards are able to steer the migration via the public API. Please read the whitepaper for a details.

Lets make a simple example - which is available as test case in this project:

![Process Model][1]

The two red dots mark the places where we do want to migrate to. 

After adding the migration scenarios we can start a process instance which directly moves to these states:

```java
 Map<String, Object> variables = new HashMap<String, Object>();
 variables.put("migrationScenario", "01");

 runtimeService.startProcessInstanceByMessage(
        "migration-example-super-process#MIGRATION_SCENARIO_01", variables);
```

And this is how it looks like in the BPMN:

![Scenario 1][4]


# Show me the important parts 



## Extensions in BPMN 2.0 

In order to get this running smoothly we implemented a [Process Engine Plugin](http://docs.camunda.org/latest/guides/user-guide/#process-engine-process-engine-plugins) which basically pimps the behavior of the CallActivity. Then it is able to read a migration scenario expression and starting the called process in the according activity - which might even be nested in a subprocess:

```xml
    <bpmn2:callActivity id="CallActivityProcessA" name="Process A" calledElement="migration-example-process-a">
      <bpmn2:extensionElements>
        <camunda:properties>
          <camunda:property name="MIGRATION_SCENARIO" value="#{migrationScenario}"/>
        </camunda:properties>
        <camunda:in variables="all"/>
      </bpmn2:extensionElements>
    </bpmn2:callActivity>
```

You can easily edit this via the camunda Modeler: 

![camunda Modeler][3]

## Process Engine Plugin to exchange CallActivityBehavior

## Extended CallActivityBehavior

## TestCase


# Further Reading

See whitepaper: TODO-add-link


[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/example-process.png
[2]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/scenario1.png
[3]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/migration-extension-in-modeler.png