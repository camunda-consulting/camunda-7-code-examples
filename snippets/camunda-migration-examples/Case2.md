## Use Case 2 - Using an Intermediate Process for Migration

Just like in Use Case 1 we have a process that loses a User Task from version 1 to version 2.  But instead of using a "migration island" pattern, we instead do two migrations.

The first migration will be to an "intermediate process" whose sole purpose is to handle the data manipulation required for the final migration.  Once that is done, a final migration is made to the new version of the original process.  Therefore, the intermediate process takes the place of the "migration island".  

The upside with this approach is that we never have any migration-specific modeling hanging around in our models moving forward as we would with the migration island approach.  This can be distracting, depending on your models and current design.

The downside is that you are doing twice the number of migrations.  This might be difficult depending on your volume.  In the end you have flexibility and choices to do what is right for your situation.

Just like in Use Case 1, **Version 1** looks like this.  Notice on path 2 that there is a "User Task 0".

![src/main/resources/case1.png](src/main/resources/case1.png)

In **Version 2**, "user task 0" no longer exists, so we need to migrate all existing tokens on "user task 0" to "user task 2" over on path 3.  

![src/main/resources/case2-version2.png](src/main/resources/case2-version2.png)

The problem is that the development team is telling us that if we do this token migration directly then those process instances might not function properly.  Some kind of "data massage" is required in order to make sure that migration from "user task 0" to "user task 2" happens without incident.

(NOTE:  For the purposes of this exercise, the actual data that needs to be prepped for this to work correctly is irrelevant and out of scope.  Let us just pretend that many things needs to be carefully changed for the migration to work correctly)

## Use Case 2 - Solution

So the development team decides to design the migration as follows:

- migrate all v1 instance to an intermediate process
- alter the process instance variables as needed for the final migration
- migrate from the intermediate process to the v2 process.

That intermediate process would look something like this:

![src/main/resources/case2-int.png](src/main/resources/case2-int.png)

Since there are two migrations taking place, the migration process looks somewhat different that in Use Case 1.

![src/main/resources/migrate-case-2.png](src/main/resources/migrate-case-2.png)

The top part of the model handles migrating all process instances (in controlled batches) to the intermediate process, then completing the start task to push it forward.  The bottom part of the model handles migrating from the intermediate process to v2.

The "Migrate Instances in Batch" process being called by the Call Activity is the same "migration-core" process from Use Case 1.

![](src/main/resources/migration-core.png)

The DevOps team also has a concern during this migration.  They are concerned that there might be over 50,000 instances to migrate on deployment night, and that the migration itself might cause unexpected load on the infrastructure.  Therefore, they are asking the development team to migrate in controlled batches, setting a proper maximum number of process instances to migrate during each batch.  For instance, let us say 500 at a time.  Therefore the same batch mechanism used in Use Case 1 is used here.

Migration is triggered by creating a process instance of this Migration process using the REST API.  The payload that initializes process instance variables in our local example looks like this:
```json
{
    "variables": {
        "processDefKey": {
            "value": "use-case-2",
            "type": "String"
        },
        "fromVersion": {
            "value": 1,
            "type": "Integer"
        },
        "toVersion": {
            "value": 2,
            "type": "Integer"
        },
        "fromUserTaskKey": {
            "value": "user-task-0-c2",
            "type": "String"
        },
        "toUserTaskKey": {
            "value": "user-task-2-c2",
            "type": "String"
        },
        "intermediateProcessDefKey": {
            "value": "use-case-2-migrate-intermediate",
            "type": "String"
        },
        "intermediateProcessVersion": {
            "value": 1,
            "type": "Integer"
        },
        "intStepOneTask": {
            "value": "token-pitstop-1",
            "type": "String"
        },
        "intStepTwoTask": {
            "value": "token-pitstop-2",
            "type": "String"
        },
        "maxPerBatch": {
            "value": 50,
            "type": "Integer"
        },
        "maxUserComplete": {
            "value": 10,
            "type": "Integer"
        },
        "skipIoMappings": {
            "value": true,
            "type": "Boolean"
        },
        "skipCustomListeners": {
            "value": true,
            "type": "Boolean"
        }
    }
}
```


Notice the "maxPerBatch" variable.  In our local example, we will create 120 instances of version 1 with a token sitting on "user task 0".  Then we will run this migration process, instructing it to migrate in batches of 50 at a time.

Also, note the "maxUserComplete" variable.  Here we are saying "complete no more than 10 user tasks in a single transaction".

The migration process will loop for each batch of process instances to migrate.  For each batch, it will:

-   suspend the processes
-   execute the migration
-   activate the processes
-   using the API, complete the User migration task.  This will trigger the "Prepare Data After Migration" service task, and allow the token to rest at it's final destination, the "user task 2" task.

### Steps to execute the solution locally

1.  Start the Spring Boot application.  The application class is CamundaApplication.java.  Use either your IDE, or using maven with:

        mvn spring-boot:run

This will start Camunda BPM, as well as auto-deploy the first version of the process (case1.bpmn).

2.  Open Postman.  Import the following 4 files into Postman.  They are located in the src/test/resources directory:

    ![](src/main/resources/postman-case2.png)

Once the environment file is loaded, make sure it is selected in your Postman IDE:

![](src/main/resources/postman_2.png)

Once all files are imported, you should see the following three collections:

![](src/main/resources/postman-case2-2.png)

3.  Notice that the "Step 1" collection for generating the data actually does the following:

    -   Starts a process instance that will go down path 1 after the gateway.  That process will auto-complete.
    -   Starts a process instance that will go down path 2 after the gateway.  It will stop at "user task 1".
    -   Get that task instance and complete it, to push the token to "user task 0".
    -   Starts a process instance that will go down path 3, then push that token all the way to completion.

    This means that every time we run this collection of calls, we will have two completed instances (path 1 and 3), and one incomplete instance with a token sitting at "user task 0".

    We want to run this collection 120 times by using the Collection Runner.  Select the "play" button in the upper right portion of the collection.  It should take you to this interface:

    ![](src/main/resources/postman-case2-3.png)

Select "Run", then enter "120" for iterations, with a delay of 20 milliseconds.  Run the collection.  When it is complete, log into Cockpit (<http://localhost:8080/app/cockpit/default/>) with demo/demo.  Select the "use-case-1" process definition.  You should see 120 tokens on "user task 0".  If you are running Camunda EE and have History view, it should look like this:

![](src/main/resources/step1_complete.png)

4.  In Postman, go to the "Deploy Version 2" request in the Step 2 collection.  Select the "Body" tab of the request, and reload the file provided for the "data" parameter.  It is the "case2_ver2_template.bpmn" file that is located in the root of the Spring Boot project.  Once complete, select the "Save" button, then the "Run" button.  This should deploy version 2 of the use-case-2 process.  Confirm deployment by going into Cockpit to see both versions.

5.  In Postman, go to the "Start process migrate-case-2" request in the "Case 2 - Step 3" collection.  Run the request to start the migration.  Observe the log in Spring Boot to watch the process navigation.  When complete, go into Cockpit to see the results.

    The migration should have run in three batches (50, 50, 20).  The core migration process would have run twice in Use Case 2, so history would show something like this:

    ![](src/main/resources/cockpit-case2.png)

Migration success would look like this:

![](src/main/resources/cockpit-case2-2.png)
