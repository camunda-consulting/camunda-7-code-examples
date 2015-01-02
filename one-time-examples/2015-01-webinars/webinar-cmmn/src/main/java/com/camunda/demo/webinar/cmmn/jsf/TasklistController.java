package com.camunda.demo.webinar.cmmn.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.task.Task;

import com.camunda.demo.webinar.cmmn.domain.Application;
import com.camunda.demo.webinar.cmmn.domain.ApplicationDomainService;

@Named
public class TasklistController {

  @Inject
  private ProcessEngine processEngine;

  @Inject
  private ApplicationDomainService service;

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
      
      List<Task> tasksForUser = processEngine.getTaskService().createTaskQuery().taskAssignee(userController.getUserId()).active()
          .orderByTaskCreateTime().desc().list();
      taskRows.addAll(createTaskRows(tasksForUser));

      List<Group> groups = processEngine.getIdentityService().createGroupQuery().groupMember(userController.getUserId()).list();
      List<String> groupIds = new ArrayList<String>();
      for (final Group group : groups) {
        groupIds.add(group.getId());
      }
      if (groupIds.size() > 0) {
        List<Task> tasksForGroups = processEngine.getTaskService().createTaskQuery().taskCandidateGroupIn(groupIds).active().orderByTaskCreateTime()
            .desc().list();
        taskRows.addAll(createTaskRows(tasksForGroups));
      }
    }
    return taskRows;
  }

  private List<TaskRow> createTaskRows(final List<Task> tasks) {
    List<TaskRow> taskRows = new ArrayList<TaskRow>();
    for (Task task : tasks) {
      CaseInstance caseInstance = null;
      Application creditApplication = null;
      CaseDefinition caseDefinition = null;

      if (task.getCaseInstanceId() != null) {
        caseInstance = processEngine.getCaseService().createCaseInstanceQuery().caseInstanceId(task.getCaseInstanceId()).singleResult();
        caseDefinition = processEngine.getRepositoryService().getCaseDefinition(caseInstance.getCaseDefinitionId());
      }
      final Long creditApplicationId = (Long) processEngine.getTaskService().getVariable(task.getId(), ApplicationDomainService.CREDIT_APPLICATION_ID);
      if (creditApplicationId != null) {
        creditApplication = service.findApplicationById(creditApplicationId);
      }
      taskRows.add(new TaskRow(task, creditApplication, caseInstance, caseDefinition));
    }
    return taskRows;
  }
}
