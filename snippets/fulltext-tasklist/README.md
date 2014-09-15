# Fulltext Task List

If you want to search for long variable content, longer than the 4000 characters that you can save
in a process variable, this snippet shows you, how to save your tasks with a CLOB containing the large text. 

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

## Remarks to run this application
There is no web interface to access the application. To get started refer to the
Arquillian test case, which by default connects to a camunda BPM Platform running
locally on JBoss AS 7.

