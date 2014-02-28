package org.camunda.bpm.example.acm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.engine.acm.CaseInstance;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.example.acm.domain.CreditApplication;
import org.camunda.bpm.example.acm.domain.CreditApplicationService;

@Named
public class TasklistController {

    @Inject
    private ProcessEngine processEngine;

    @Inject
    private CreditApplicationService service;

    @Inject
    @Named
    private CaseController caseController;

    @Inject
    @Named
    private UserController userController;

    private List<TaskRow> taskRows;

    public List<TaskRow> getAllTasks() {
        if (taskRows == null) {
            taskRows = new ArrayList<TaskRow>();
            // Todo: check that somebody is logged in
            final List<Task> tasksForUser = processEngine.getTaskService()
                                                         .createTaskQuery()
                                                         .taskAssignee(userController.getUserId())
                                                         .active()
                                                         .orderByTaskCreateTime()
                                                         .desc()
                                                         .list();
            taskRows.addAll(createTaskRows(tasksForUser));

            final List<Group> groups = processEngine.getIdentityService().createGroupQuery().groupMember(userController.getUserId()).list();
            // Todo: check that user is in any groups
            final List<String> groupIds = new ArrayList<String>();
            for (final Group group : groups) {
                groupIds.add(group.getId());
            }
            if (groupIds.size()>0) {
              final List<Task> tasksForGroups = processEngine.getTaskService()
                                                             .createTaskQuery()
                                                             .taskCandidateGroupIn(groupIds)
                                                             .active()
                                                             .orderByTaskCreateTime()
                                                             .desc()
                                                             .list();
              taskRows.addAll(createTaskRows(tasksForGroups));
            }
        }
        return taskRows;
    }

//    public String selectCaseAndTask(final String caseId, final String taskId) {
//        caseController.initByCaseId(caseId);
//        caseController.selectTask(taskId);
//        return "case-form";
//    }

    private List<TaskRow> createTaskRows(final List<Task> tasks) {
        final List<TaskRow> taskRows = new ArrayList<TaskRow>();
        for (final Task task : tasks) {
            CaseInstance caseInstance = null;
            CreditApplication creditApplication = null;
            CaseDefinition caseDefinition = null;

            if (task.getCaseInstanceId() != null) {
                caseInstance = processEngine.getCaseService().createCaseInstanceQuery().caseInstanceId(task.getCaseInstanceId()).singleResult();
                caseDefinition = CaseDefinition.mockCaseDefinitions.get(caseInstance.getCaseDefinitionId());
            }
            final Long creditApplicationId = (Long) processEngine.getTaskService().getVariable(task.getId(), CreditApplicationService.CREDIT_APPLICATION_ID);
            if (creditApplicationId != null) {
                creditApplication = service.findCreditApplicationById(creditApplicationId);
            }
            taskRows.add(new TaskRow(task, creditApplication, caseInstance, caseDefinition));
        }
        return taskRows;
    }
}
