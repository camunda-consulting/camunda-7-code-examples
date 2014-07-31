# How to migrate processes from existing solutions to camunda BPM

**Table of Contents**

- [Introduction](#introduction)
- [Show me the important parts](#user-content-show-me-the-important-parts)
	- [Migration Scenarios in your process model](#user-content-migration-scenarios-in-your-process-model)
		- [Message Start Events](#user-content-message-start-events)
		- [Intermediate None Events](#user-content-intermediate-none-events)
		- [Call Activities](#user-content-call-activities)
		- [Necessary adjustments on your process models](#necessary-adjustments-on-your-process-models)
  - [Process Engine Plugin](#user-content-process-engine-plugin)
	- [TestCase](#user-content-testcase)
- [Important Limitation on camunda BPM version.](#user-content-important-limitation-on-camunda-bpm-version)
- [Further Reading](#user-content-further-reading)

# Introduction

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

![Scenario 1][2]


# Show me the important parts 


## Migration Scenarios in your process model


### Message Start Events

Message start events are used in processes to take a shortcut directly to the state you want to be. They can be started by handing in the message via the public API.

![Message Start Events][4]

For every message start event we have two conventions
* The *message name* must be #{process name}#MIGRATION_SCENARIO_#{number}, e.g. *process-b#MIGRATION_SCENARIO_01*. The process name is contained since message names must be unique on the engine and the same migration scenario may be modeled in different processes (e.g. for sub processes). The message is used if you want to start this process instance from the outside world.
* The *id* of the start event must be MIGRATION_SCENARIO_#{number}, e.g. *MIGRATION_SCENARIO_01*. The id is used if this process is started via a Call Activity. 

If a process is only started by Call Activity and never as top level process instance, you might name the message as you want. But we recommend to stick to the convention.

Example (see [process-b.bpmn](src/main/resources/example/process-b.bpmn)): 

```xml
    <bpmn2:startEvent id="MIGRATION_SCENARIO_02" name="Migration Scenario 02: Do the work">
      <bpmn2:messageEventDefinition id="_MessageEventDefinition_5" messageRef="Message_Scenario_2"/>
    </bpmn2:startEvent>
    ...
  <bpmn2:message id="Message_Scenario_2" name="migration-example-process-b#MIGRATION_SCENARIO_02"/>
```

### Intermediate None Events

Intermediate none events are used as a workaround if you directly want to jump into a sub process. This is actually not valid BPMN 2.0 - but as our core engine can run this pretty well we see this as the smallest trade-off.

![Intermediate None Events][5]

The convention is
* The *id* of the intermediate event must be MIGRATION_SCENARIO_#{number}, e.g. *MIGRATION_SCENARIO_01*. 

Note that this construct can only be started via a call activity. If it is a top level process, use a message start event and route the flow to the according sub process where you might have to add xome XOR-Gateway. 

Example (see [process-b.bpmn](src/main/resources/example/process-b.bpmn)): 

```xml
      <bpmn2:intermediateThrowEvent id="MIGRATION_SCENARIO_01" name="Migration Scenario 01: Handle Manually">
      </bpmn2:intermediateThrowEvent>
```

### Call Activities

In the Call Activity an extension is added to control which activity of the called process is started. This might be either a Message Start Event or an Intermediate None Event.

Please note that a process variable containing the scenario number is required to be passed into the process in order to be evaluated correctly. This is also used to determine if we are in a migration - so if this variable does not exist the Call Activity behaves normal - which means that "normal" process instances are not influenced.

```xml
    <bpmn2:callActivity id="CallActivityProcessA" name="Process A" calledElement="migration-example-process-a">
      <bpmn2:extensionElements>
        <camunda:properties>
          <camunda:property name="MIGRATION_SCENARIO" value="MIGRATION_SCENARIO_#{migrationScenario}"/>
        </camunda:properties>
        <camunda:in variables="all"/>
      </bpmn2:extensionElements>
    </bpmn2:callActivity>
```

You can easily edit this via the camunda Modeler: 

![camunda Modeler][3]


### Necessary adjustments on your process models

This approach tries to limit hacks to a minimum. But as BPMN cannot "jump" into scopes or parallel paths there are situations which cannot be handled out-of-the-box by the migration scenarios. Then you have to adjust the process model as sketched in the following example:

![Adjusted Process Model][6]

The migration scenario defines that we want to end up in Task A - and only Task A (so Task B was already completed). In order to jump into the parallel paths we need to have an additional parallel gateway for the migration and a decision within the subprocess to bypass "Task 0" - as we cannot directly go into the subprocess on Task A. The changes are marked in red.


## Process Engine Plugin

In order to get this running we implemented a [Process Engine Plugin](http://docs.camunda.org/latest/guides/user-guide/#process-engine-process-engine-plugins) which basically pimps the behavior of the CallActivity. 

In order to do this the Plugin adds a [ParseListener](http://docs.camunda.org/7.1/api-references/javadoc/org/camunda/bpm/engine/impl/bpmn/parser/BpmnParseListener.html). This [MigrationParseListener](src/main/java/com/camunda/demo/migration/MigrationParseListener.java) exchanges the behavior of the call activity on the fly:

```java
  public void parseCallActivity(Element callActivityElement, ScopeImpl scope, ActivityImpl activity) {
    CallActivityBehavior behavior = (CallActivityBehavior) activity.getActivityBehavior();
        
    // parse additional extension elements defined in the BPMN 2.0:
    Element extensionElement = callActivityElement.element("extensionElements");
    if (extensionElement != null) {
      // get the <camunda:properties ...> element 
      Element propertiesElement = extensionElement.element("properties");
      if (propertiesElement != null) {
        //  get list of <camunda:property ...> elements
        List<Element> propertyList = propertiesElement.elements("property");
        for (Element property : propertyList) {

          if("MIGRATION_SCENARIO".equals(property.attribute("name"))) {
            
            // exchange behavior
            MigrationEnabledCallActivityBehavior newBehavior = new MigrationEnabledCallActivityBehavior(behavior);
            activity.setActivityBehavior(newBehavior);
            
            // read expression to control which Migration Scenario is active
            String migrationScenarioExpression = property.attribute("value");            
            Expression expression = Context.getProcessEngineConfiguration().getExpressionManager().createExpression(migrationScenarioExpression);
            newBehavior.addMigrationScenarioExpression(expression);                        
          }
        }
      }
    }
  }
```

We exchanged the behavior always when the MIGRATION_SCENARIO extension property is set. 

The [MigrationEnabledCallActivityBehavior](src/main/java/com/camunda/demo/migration/MigrationEnabledCallActivityBehavior.java) is a bit more complex but basically extends the current behavior by a switch which not only can start process instances in the none start event, but also in the migration scenarios. The basic code parts are:

```java
   // existing behavior omitted
   String migrationScenarioStartActivityId = null;
    try {
      migrationScenarioStartActivityId = (String) migrationScenarioExpression.getValue(execution);
    }
    catch (ProcessEngineException ex) {
      // might be the case if the process run in "normal" mode and some variables have not be passed id
    }
    
    if (migrationScenario==null) {
      // normal behavior omitted...
    }
    else {
      ActivityImpl startActivity = processDefinition.findActivity(migrationScenarioStartActivityId);            
      ExecutionEntity subProcessInstance = processDefinition.createProcessInstance(businessKey, startActivity);
      
      // some details omitted....
      
      // and start
      subProcessInstance.start(callActivityVariables);
    }
```

## TestCase

We implemented a couple of test cases checking that normal process instances are not broken. But of course we test the migration scenarios as the following example shows (leveraging [camunda-bpm-assert](https://github.com/camunda/camunda-bpm-assert)):

```java
 @Deployment(resources = {"example/super-process.bpmn", "example/process-a.bpmn", "example/process-b.bpmn"})
  public void testMigrationScenario01() {
    // Scenario 01: Jump into second hierarchy and there into the Human Task within a Sub Process started by an error    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIO_01", withVariables("migrationScenario", "01"));

    assertThat(piSuper).isStarted().isNotEnded() 
      .isWaitingAtExactly("CallActivityProcessA");
    
    // search for existing called process instance and assert it as well
    ProcessInstance piA = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piA).isStarted().isNotEnded().isWaitingAtExactly("CallActivityProcessB");
    
    // and the next pi down the hierarchy
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piA.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded().isWaitingAtExactly("UserTaskHandleManually");

    // now continue in the process execution
    assertThat(piB).isWaitingAtExactly("UserTaskDoTheWork").task("UserTaskHandleManually");
    complete(task());
    
    assertThat(piB).isWaitingAtExactly("UserTaskDoTheWork").task("UserTaskDoTheWork");
    complete(task());

    assertThat(piB).isEnded();
    assertThat(piA).isEnded();
    assertThat(piSuper).isEnded();
  }
```

# Important Limitation on camunda BPM version.

The current implementation requires some bug fixes to the core engine which are contained in the [org.camunda folder](src/main/java/org/camunda) of this project. These bugs occured as we did some stuff not valid in BPMN 2.0 and hence was not properly tested on the core engine. 

However these bug fixes will be added to
* 7.2 development head
* 7.1.5 (next enterprise patch release).

If you do not have these versions please patch the engine yourself with the code applied.

# Further Reading

See whitepaper: TODO-add-link


[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/example-process.png
[2]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/scenario1.png
[3]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/migration-extension-in-modeler.png
[4]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/message-start-event.png
[5]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/intermediate-none-event.png
[6]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/scenario4.png