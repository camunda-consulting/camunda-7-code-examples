package com.camunda.demo.underwriting.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.task.Task;

import com.camunda.demo.underwriting.Constants;
import com.camunda.demo.underwriting.domain.Application;

@Named
public class TasklistController {

  @Inject
  private ProcessEngine processEngine;

  @Inject
  @Named
  private CaseController caseController;

  @Inject
  @Named
  private UserController userController;

  private List<TaskRow> taskRows;

  public List<TaskRow> getAllTasks() {
    // lazy load tasks if not yet done
    if (taskRows == null) {
      taskRows = new ArrayList<TaskRow>();
      // Todo: check that somebody is logged in
      
      List<Task> tasksForUser = processEngine.getTaskService().createTaskQuery() //
          .taskAssignee(userController.getUserId()) //
          .active() //
          .orderByTaskCreateTime().desc() //
          .initializeFormKeys() //
          .list();
      taskRows.addAll(createTaskRows(tasksForUser));

      List<Group> groups = processEngine.getIdentityService().createGroupQuery().groupMember(userController.getUserId()).list();
      List<String> groupIds = new ArrayList<String>();
      for (final Group group : groups) {
        groupIds.add(group.getId());
      }
      if (groupIds.size() > 0) {
        List<Task> tasksForGroups = processEngine.getTaskService().createTaskQuery() //
            .taskCandidateGroupIn(groupIds) //
            .active() //
            .orderByTaskCreateTime().desc() //
            .initializeFormKeys() //
            .list();
        taskRows.addAll(createTaskRows(tasksForGroups));
      }
    }
    return taskRows;
  }

  private List<TaskRow> createTaskRows(final List<Task> tasks) {
    List<TaskRow> taskRows = new ArrayList<TaskRow>();
    for (Task task : tasks) {
      CaseInstance caseInstance = null;
      Application application = null;
      CaseDefinition caseDefinition = null;

      if (task.getCaseInstanceId() != null) {
        caseInstance = processEngine.getCaseService().createCaseInstanceQuery().caseInstanceId(task.getCaseInstanceId()).singleResult();
        caseDefinition = processEngine.getRepositoryService().getCaseDefinition(caseInstance.getCaseDefinitionId());
      }

      application = (Application) processEngine.getTaskService().getVariable(task.getId(), Constants.VAR_NAME_APPLICATION);
      taskRows.add(new TaskRow(task, application, caseInstance, caseDefinition));
    }
    return taskRows;
  }
}
