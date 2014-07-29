# How to migrate processes from existing solutions to camunda BPM

In every company you will sooner or later migrate to camunda BPM as your favorite BPM platform :-) When doing this you often do not start on a green field - but have ruinning process instances:
* In some third party process engine which might be outdated, discontinued or just crap.
* In some hard coded application which shall be replaced by a real workflow engine (good decision by the way).
* The business process is burried in  some clumsy construct consisting of code, database tables, triggers and magic scripts.
* ...

To deploy BPMN 2.0 processes on camunda BPM you now have to migrate process definitions and running process instances. 

## Migrate process definitions

Camunda needs process definitions as valid BPMN 2.0 files (XML). In order to get them you have these options:

1. Your old engine was BPMN 2.0 as well - so there is no migration.
   * Well, theoretically yes. In real-life you always have to migrate some nuts and bolts, e.g. extensions used by the vendors.
2. Automated conversion from the old format to the new format.
   * This might work if you already had a process engine with a proper XML format or some magic process mining tool generating meaningful data to create process definitions.
   * However - we never did this in bigger projects and advise against it. Normally it is a really good idea to re-model the processes using BPMN and the BPMN best practices. You will get better models - and this could be already worth the whole migration :-)
3. Re-Model the processes.
   * This is actually what we did in each and every project till now. And it always turned out to be a good idea. BPMN is really a great language to model processes and you can leverage this power if you start from scratch.
   * Normally the number of process definitions is limited and the time you need to re-model is much less than the time you would need to create and test an automated conversion.


## Migrate process instances

This is actually the more thrilling part - and it is unavoidable. Options:

1. *Run in parallel*: You still operate the old solution until no process instances are left there. In the meanwhile you start new process instances on camunda BPM. 
   * You need some kind of switch to route incoming requests to the old or new system.
   * You need to take care of operating both systems, e.g. checking failures, calculating KPI's or doing instance counts.
   * THUMBS UP: You do not need to migrate running process instances and save hazzle for this. So the going live of camunda BPM is much less risky.
   * THUMBS DOWN: Operating two systems in parallel for maybe a long time is quite some effort. You might face problems with the switch sooner or later. And in most companies we were the people were really eager to throw out the old soltution - so it might be even a downer for motivation to keep it running.
2. *Migrate in big bang*: You stop the old system and make sure all process instances have reached a wait state. Now you migrate all instances to camunda BPM and fire up the new system. 
   * You need a mapping for all possible wait states of the old process. This means you need to know in which node a process waiting in "wait for payment" in the old system should be moved to.
   * You need to be able to read the process instances from the old system.
   * You need a proper migration script to "generate" the new process instances in camunda BPM in the correct state. And for this I want to sketch a solution in this example.
   * THUMBS UP: After the migration you have to operate only one system. You can test the migration beforehand to avoid any surprises.
   * THUMBS DOWN: You have to do migration.
   
In real-life we saw both approaches applied. Both can work well. It actually depends on your exact situation and requirements.

Running different workflow engines in parallel and implement some clever switch is actaully pretty specific to the concrete technology and architecture used. Hence this cannot described in detail here.

But what we will describe in detail is how to create process instances in the correct state in camunda BPM to allow big bang migration.



# Create new process instances in the correct state during migration

The basic idea of migration is simple: Create a version of the process definition with seperate Start Events only for migration. This allows to start new process instances and directly move them to the desired state.

We extended this to be able to go into hierarchies of call activities (sub processes). There we added an own extension element to "jump" to the correct node of the called process. And this can be easily done recursively. 

Lets make this more clear by sketching a simple example:

![Process Model][1]

The two red dots mark the places where we do want to migrate to. We work with "scenarios" marking the desired state of the process instance. Now we have to add these scenarios to the process model. We discussed various alternatives and come up with the following solution:

* Add Message Start Events with a special naming convention to start process instances in the desired activity. *No* vendor specific workaround is necessary, this works by leveraging the BPMN standard.
* Add Intermediate None Events as "start points" in sub processes. This is actually a workaround and violates the BPMN standard - but otherwise it is not possible to start a sub process in an arbritrary activity. So we agreed that this still is the best solution

You can see the process models containing the migration scenarios in the following picture:

![Migration Scenarios][2]

Now we can easily start a new process instance by handing in a special process variable and starting by a Message named accordingly:

```java
 Map<String, Object> variables = new HashMap<String, Object>();
 variables.put("migrationScenario", "01");

 runtimeService.startProcessInstanceByMessage(
        "migration-example-super-process#MIGRATION_SCENARIO_01", variables);
```

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




# Limitations and advantages of approach

Limitations:
* History data (e.g. Audit Trail) is *not* migrated
* Parallel paths

Advantages:
* Visible in history / cockpit
* No Magic



# Show me the important parts in the code


## Extensions in BPMN 2.0 

## Process Engine Plugin to exchange CallActivityBehavior

## Extended CallActivityBehavior

## TestCase


# Possible alternatives not used


[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/example-process.png
[2]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/migration-scenarios.png
[3]: https://raw.github.com/camunda/camunda-consulting/master/snippets/migrate-third-party-to-camunda/docs/migration-extension-in-modeler.png
