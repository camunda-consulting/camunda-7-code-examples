# Fulltext Task List

If you want to search for long variable content, longer than the 4000 characters that you can save
in a process variable, this snippet shows you how to save your tasks with a CLOB containing the large text. 

## The detailed parts

We introduce a new table USER_TASK, which is handled by JPA. It has columns for task data and columns for data 
that are stored in process variables, too. In our example, we store data from an incident in process variables and 
propagate these data together with the full stacktrace of the exception from the incident into the USER_TASK table.  

You can do a fulltext query on this column to find all tasks that contain a special exception or a line or a keyword in the 
exception stacktrace.

The entity object is filled and persisted by the FulltextTaskHandler. All important attributes are set in the UserTask entity object.
The primary key of the user task entity is stored as a local variable at the bpmn task. 

To use this taskHandler in your processes, add the FulltextTaskListener as delegate expressions for the events 'create' and 
'complete' to the user tasks you want to. See the 'User Task 1' in the process 'process.bpmn' for an example:

    <bpmn2:userTask id="UserTask_1" camunda:assignee="demo" name="User Task 1">
      <bpmn2:extensionElements>
        <camunda:taskListener delegateExpression="${fulltextTaskHandler}" event="create"/>
        <camunda:taskListener delegateExpression="${fulltextTaskHandler}" event="complete"/>
      </bpmn2:extensionElements>
    </bpmn2:userTask>

## Query for CLOB column

The query to the CLOB column is built in the FulltextTaskListener, too. If you inject the named bean, you can call 
  
	List<UserTask> userTasks = fulltext.findUserTasksWithExceptionLike("Service Call should");
  
to get all user tasks including a text like the method parameter. It sends a query with <code>where inc\_exception\_ like '%Servcie Call should%'</code>
to the database. 

## Query with REST-Service

The projects includes a REST-Application with a GET-Resource to execute the query.

The url is: <code>http://localhost:8080/fulltext/tasksearch/exceptiontext?exceptionSnippet=Service%20Call</code>

The query text must be URL-encoded.

A response looks like this:

	0:  {
		id: "94a6b3a7-3d6d-11e4-a81d-760220524153"
		name: "User Task 1"
		taskId: "94a66586-3d6d-11e4-a81d-760220524153"
		taskDefinitionKey: "UserTask_1"
		processInstanceId: "94a63e67-3d6d-11e4-a81d-760220524153"
		processDefinitionId: "fulltext-tasklist:2:8c71e716-3d6a-11e4-a81d-760220524153"
		candidates: null
		assignee: "demo"
		businessKey: "415"
		createTime: 1410850158262
		incidentActivityId: "ServiceTask_1"
		incidentProcessDefinitionId: "incident-process:1:8c71c004-3d6a-11e4-a81d-760220524153"
		incidentJobId: "940d59c1-3d6d-11e4-a81d-760220524153"
		incidentExecutionId: "940d59bf-3d6d-11e4-a81d-760220524153"
		incidentProcessInstanceId: "940d59bf-3d6d-11e4-a81d-760220524153"
		incidentMessage: "this Service Call should provoke an incident"
		incidentException: "org.camunda.bpm.engine.ProcessEngineException: this Service Call should provoke an incident 			at com.camunda.consulting.tasklist.fulltext.RepairableDelegate.execute(RepairableDelegate.java:22) 
			at org.camunda.bpm.engine.impl.delegate.JavaDelegateInvocation.invoke(JavaDelegateInvocation.java:34) 
			..."
	}  


## Remarks to run this application
To get started refer to the
Arquillian test case, which by default connects to a camunda BPM Platform running
locally on JBoss AS 7.

