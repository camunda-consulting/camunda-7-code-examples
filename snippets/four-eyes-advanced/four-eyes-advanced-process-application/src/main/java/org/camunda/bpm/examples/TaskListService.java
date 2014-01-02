package org.camunda.bpm.examples;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.RepositoryServiceImpl;
import org.camunda.bpm.engine.impl.pvm.PvmActivity;
import org.camunda.bpm.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.camunda.bpm.engine.task.Task;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@Stateless
public class TaskListService {

  public static final String PROCESS_DEFINITION_KEY = "four-eyes-advanced";

  @Inject
  private AuthenticationService authenticationService;
  @Inject
  private TaskService taskService;
  @Inject
  private RuntimeService runtimeService;
  @Inject
  private ProcessEngine processEngine;

  public List<Task> getTaskForCurrentUser() {
    List<Task> tasks = new ArrayList<Task>();

    // we do not filter anything specific here, check out four-eyes-simple to see how we could do this
    tasks.addAll(taskService.createTaskQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).taskAssignee(authenticationService.getLoggedInUser()).list());
    tasks.addAll(taskService.createTaskQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).taskCandidateGroupIn(authenticationService.getGroupsOfLoggedInUser()).list());
    
    return tasks;
  }

  public void claim(Task task) {
    check4EyesPrinciple(task);
    taskService.claim(task.getId(), authenticationService.getLoggedInUser());
  }

  public void complete(Task task) {
    check4EyesPrinciple(task);
    taskService.complete(task.getId());
  }

  private void check4EyesPrinciple(Task task) {
    // query the current state (ActivityId)
    List<String> activityIds = runtimeService.getActiveActivityIds(task.getExecutionId());
    if (activityIds.size() != 1) {
      // TODO: Double check with problem cause by timer scope (like this:
      // http://jira.codehaus.org/browse/ACT-1106)
      throw new IllegalStateException("Execution is in more than one activity at the same time for the task: " + activityIds);
    }
    String activityId = activityIds.get(0);

    ReadOnlyProcessDefinition deployedProcessDefinition = ((RepositoryServiceImpl) processEngine.getRepositoryService()).getDeployedProcessDefinition(task.getProcessDefinitionId());
    PvmActivity activity = deployedProcessDefinition.findActivity(activityId);
    String fourEyesGroupName = (String) activity.getProperty(Helper.FOUR_EYES_GROUP_NAME);
    String fourEyesRoleName = (String) activity.getProperty(Helper.FOUR_EYES_ROLE_NAME);
    boolean firstReview = "first-review".equals(fourEyesRoleName);

    // First of all we have
    //InputStream inputStream = processEngine.getRepositoryService().getProcessModel(task.getProcessDefinitionId());

    //Element userTaskExtensionFoxGroup = Helper.getUserTaskExtensions(inputStream, activityId, Helper.FOUR_EYES_GROUP_NAME);
    //if (userTaskExtensionFoxGroup == null) {
    //  throw new RuntimeException("Task '" + task.getId() + "' has no fourEyesGroup configured, which is invalid. Fix usage.");
    //}
    
    //String fourEyesGroupName = userTaskExtensionFoxGroup.getAttribute("name");
    //boolean firstReview = "first-review".equals(userTaskExtensionFoxGroup.getAttribute("role"));
    
    if (!firstReview) {     
      String lastUser = (String) runtimeService.getVariable(task.getProcessInstanceId(), Helper.getVariableName(fourEyesGroupName));
      if (authenticationService.getLoggedInUser().equals(lastUser)) {
        throw new RuntimeException("You cannot claim or complete task '" + task.getId() + "' because this would violate the 4 eyes principle.");
      }
    }
  }

}
