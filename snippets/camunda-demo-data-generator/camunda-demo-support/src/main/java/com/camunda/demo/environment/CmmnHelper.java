package com.camunda.demo.environment;

import java.util.List;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstance;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstanceQuery;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

public class CmmnHelper {

	public static void printCaseStatusAndTasklist(ProcessEngine processEngine) {
		System.out.println("Case Status:");
		CaseService caseService = processEngine.getCaseService();
		List<CaseExecution> caseExecutions = caseService.createCaseExecutionQuery().list();
		for (CaseExecution caseExecution : caseExecutions) {
			if (caseExecution.isActive()) {
				System.out.println("  active:  " + caseExecution.getActivityName() + " (" + caseExecution.getActivityType() + ")");
			} else if (caseExecution.isEnabled()) {
				System.out.println("  enabled: " + caseExecution.getActivityName() + " (" + caseExecution.getActivityType() + ")");
			}
		}

		HistoricCaseActivityInstanceQuery historicCaseActivityInstanceQuery = processEngine.getHistoryService().createHistoricCaseActivityInstanceQuery().ended();

		System.out.println("Case History (" + historicCaseActivityInstanceQuery.count() + "):");
		List<HistoricCaseActivityInstance> completedCaseActivities = historicCaseActivityInstanceQuery.list();
		for (HistoricCaseActivityInstance completedCaseActivity : completedCaseActivities) {
			System.out.println("  ended:  " + completedCaseActivity.getCaseActivityName() + " (" + completedCaseActivity.getCaseActivityType() + ")");
		}
		
		TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
		System.out.println("Task List (" + taskQuery.count() + "):");
		List<Task> tasklist = taskQuery.list();
		for (Task task : tasklist) {
			System.out.println("  " + (task.getAssignee() == null ? "unassigned" : task.getAssignee()) + ": " + task.getName());
		}
		System.out.println();
	}

}
