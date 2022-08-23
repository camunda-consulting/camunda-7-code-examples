package com.camunda.consulting.example.filter;

import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FilterCreatingProcessEnginePlugin extends AbstractProcessEnginePlugin {

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
    getOrCreateFilters(processEngine);
  }

  private void getOrCreateFilters(ProcessEngine processEngine) {
    getOrCreateFilter(
        processEngine.getFilterService(),
        "My Group Tasks",
        processEngine
            .getTaskService()
            .createTaskQuery()
            .taskCandidateGroupInExpression("${currentUserGroups()}")
    );
    getOrCreateFilter(
        processEngine.getFilterService(),
        "My Tasks",
        processEngine
            .getTaskService()
            .createTaskQuery()
            .taskAssigneeExpression("${currentUser()}")
    );
    getOrCreateFilter(
        processEngine.getFilterService(),
        "All Tasks",
        processEngine
            .getTaskService()
            .createTaskQuery()
    );
    getOrCreateFilter(
        processEngine.getFilterService(),
        "My Candidate User Tasks",
        processEngine
            .getTaskService()
            .createTaskQuery()
            .taskCandidateUserExpression("${currentUser()}")
    );
  }

  private void getOrCreateFilter(FilterService filterService, String filterName, TaskQuery query) {
    Optional
        .ofNullable(filterService
            .createTaskFilterQuery()
            .filterName(filterName)
            .singleResult())
        .orElseGet(() -> filterService.saveFilter(filterService
            .newTaskFilter(filterName)
            .extend(query)));
  }
}
