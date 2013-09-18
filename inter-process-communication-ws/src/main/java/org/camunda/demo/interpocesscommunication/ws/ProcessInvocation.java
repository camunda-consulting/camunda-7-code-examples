package org.camunda.demo.interpocesscommunication.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.jws.WebService;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;


@WebService(name = "ProcessInvocationService")
public class ProcessInvocation {


  public static final String CALLBACK_URL = "callbackURL";
  public static final String CALLBACK_CORRELATION_ID = "callbackCorrelationId";
  public static final String PAYLOAD = "payload";
  
  @Inject
  private RuntimeService runtimeService;
  
  @Inject
  private TaskService taskService;
  
  public void invokeProcess(String processDefinitionKey, String callbackUrl, String correlationId, String payload) {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put(CALLBACK_URL, callbackUrl);
    variables.put(CALLBACK_CORRELATION_ID, correlationId);
    variables.put(PAYLOAD, payload);

    runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
  }

  // TaskDto
  public List<String> getTaskList(List<String> correlationIds) {
    ArrayList<String> tasks = new ArrayList<String>();
    // Better than the loop would be to introduce an own query doing this in one SQL, see
    // https://app.camunda.com/confluence/display/foxUserGuide/Performance+Tuning+with+custom+Queries
    for (String id : correlationIds) {      
      List<Task> tasksForCorrelationId = taskService.createTaskQuery().processVariableValueEquals(CALLBACK_CORRELATION_ID, id).list();
      for (Task task : tasksForCorrelationId) {        
        tasks.add(task.getName());
                //new TaskDto(task.getName(), task.getDescription(), task.getAssignee(), task.getId(), task.getProcessInstanceId(), task.getExecutionId(), task.getProcessDefinitionId()));
      }
    }
    return tasks;
  }
  
}
