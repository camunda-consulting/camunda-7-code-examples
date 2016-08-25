Helper to Setup Environment for Demo Use Cases
=========================

Ass this dependency to your project (available via Maven Central to avoid problems with building demos/showcases):

```
    <dependency>
        <groupId>org.camunda.consulting.snippets</groupId>
        <artifactId>camunda-demo-support</artifactId>
        <version>0.3.7</version>    
    </dependency>
```

Now you can do a couple of things in a @PostDeploy hook of a process application:

```
@ProcessApplication
public class MyProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void setupEnvironmentForDemo(ProcessEngine engine) {

    LicenseHelper.setLicense(engine);
    DemoDataGenerator.autoGenerateFor(engine, "insurance-application", 14, getReference());
    UserDataGenerator.createDefaultUsers(engine);

    addUser(engine, "ben", "ben", "Ben", "Brooks");
    addGroup(engine, "clerk", "Clerk", "ben");  
    addFilterGroupAuthorization(engine, "clerk", FILTER_myTasks, FILTER_groupTasksFilter, FILTER_allTasksFilter);   

    addUser(engine, "lisa", "lisa", "Lisa", "Floyd");
    addGroup(engine, "management", "Management", "lisa");
    addFilterUserAuthorization(engine, "lisa", FILTER_myTasks, FILTER_groupTasksFilter, FILTER_allTasksFilter);

    createGrantGroupAuthorization(engine, //
        new String[] { "clerk" }, //
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.UPDATE_INSTANCE }, //
        Resources.PROCESS_DEFINITION, //
        new String[] { "insurance-application" });
```

See [InsuranceProcessApplication](https://github.com/camunda/camunda-consulting/blob/master/showcases/en/insurance-application/process-application/src/main/java/com/camunda/demo/insuranceapplication/InsuranceProcessApplication.java) for an example of using it.

Features
-------------
Now the process application will automatically:

* Set a license found in USER-HOME/.camunda/build.properties
* Create default user "admin" and group "management"
* Create users & groups as specified (if not already existant)
* Set the password of created users to the password given + a suffix read from USER-HOME/.camunda/build.properties
* Create Demo Data for a given time range in the past as described in [Camunda Demo Data Generator](https://github.com/camunda/camunda-consulting/tree/master/snippets/camunda-demo-data-generator)

Configuration
---------------
In order to configure the demo data generation create a file **USER-HOME/.camunda/build.properties**:

```
camunda.license=...(your camunda ee license key)...
camunda.password.suffix=MySecret
```


Environment Restrictions
------------------------

You need Camunda BPM 7.4.0-alpha2 onwards


License
-------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
