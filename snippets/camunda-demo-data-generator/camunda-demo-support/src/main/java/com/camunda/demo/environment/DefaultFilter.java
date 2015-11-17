package com.camunda.demo.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.task.TaskQuery;

public class DefaultFilter {

  public static String FILTER_myTasks = "FILTER_myTasks";
  public static String FILTER_groupTasksFilter = "FILTER_groupTasksFilter";
  public static String FILTER_management = "FILTER_management";
  public static String FILTER_allTasksFilter = "FILTER_allTasksFilter";
  public static String FILTER_MeineAufgaben = "FILTER_MeineAufgaben";
  public static String FILTER_GruppenAufgaben = "FILTER_GruppenAufgaben";
  public static String FILTER_Wiedervorlage = "FILTER_Wiedervorlage";
  public static String FILTER_Ueberfaellig = "FILTER_Ueberfaellig";
  public static String FILTER_PostkorbManagement = "FILTER_PostkorbManagement";
  public static String FILTER_alleAufgaben = "FILTER_alleAufgaben";

  public static Map<String, String> filterIds = new HashMap<String, String>();

  public static String useFilter(ProcessEngine engine, String filterName) {
    if (filterIds.containsKey(filterName)) {
      return filterIds.get(filterName);
    } else {
      String filterId = createFilter(engine, filterName);
      filterIds.put(filterName, filterId);
      return filterId;
    }
  }

  private static String createFilter(ProcessEngine engine, String filterName) {
    if (FILTER_groupTasksFilter.equals(filterName)) {
      return createFilter(engine, "Group Tasks", -20, "Tasks assigned to my Groups", //
          engine.getTaskService().createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned());
    }
    if (FILTER_myTasks.equals(filterName)) {
      return createFilter(engine, "My Tasks", -10, "Tasks assigned to me", // +
          engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}"));
    }
    if (FILTER_management.equals(filterName)) {
      return createFilter(engine, "Management", 0, "Tasks for 'Management'", //
          engine.getTaskService().createTaskQuery().taskCandidateGroupIn(Arrays.asList("management")).taskUnassigned());
    }
    if (FILTER_allTasksFilter.equals(filterName)) {
      return createFilter(engine, "All Tasks", 20, "All Tasks (not recommended to be used in production)", //
          engine.getTaskService().createTaskQuery());
    }

    if (FILTER_GruppenAufgaben.equals(filterName)) {
      return createFilter(engine, "Meine Gruppen", -20, "Aufgaben in allen meinen Gruppen", //
          engine.getTaskService().createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned());
    }
    if (FILTER_MeineAufgaben.equals(filterName)) {
      return createFilter(engine, "Persönlich", -10, "Meine persönlichen Aufgaben", //
          engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpBeforeOrNotExistentExpression("${now()}"));
    }
    if (FILTER_Wiedervorlage.equals(filterName)) {
      return createFilter(engine, "Wiedervorlage", 5, "Auf Wiedervorlage gelegte Aufgaben", //
          engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpAfterExpression("${now()}"));
    }
    if (FILTER_Ueberfaellig.equals(filterName)) {
      return createFilter(engine, "Überfällig", 10, "Überfällige Aufgaben", //
          engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").dueBeforeExpression("${now()}"), //
          "color", "#f2dede");

    }
    if (FILTER_PostkorbManagement.equals(filterName)) {
      return createFilter(engine, "Management", 0, "Aufgaben für 'Management'", //
          engine.getTaskService().createTaskQuery().taskCandidateGroupIn(Arrays.asList("management")).taskUnassigned());
    }
    if (FILTER_alleAufgaben.equals(filterName)) {
      return createFilter(engine, "Alle Aufgaben", 20, "Alle Aufgaben (z.B. für Team-Leiter)", //
          engine.getTaskService().createTaskQuery());
    }
    throw new RuntimeException("Filter with name '" + filterName + "' not foreseen");
  }

  private static String createFilter(ProcessEngine engine, String name, int priority, String description, TaskQuery query, String... additionalProperties) {
    Map<String, Object> filterProperties = new HashMap<String, Object>();

    filterProperties.put("description", description);
    filterProperties.put("priority", priority);

    String key = null;
    for (String additionalProperty : additionalProperties) {
      if (key == null) {
        key = additionalProperty;
      } else {
        filterProperties.put(key, additionalProperty);
        key = null;
      }
    }

    List<Object> variables = new ArrayList<Object>();
    filterProperties.put("variables", variables);

    Filter myTasksFilter = engine.getFilterService().newTaskFilter() //
        .setName(name) //
        .setProperties(filterProperties)//
        .setOwner("admin")//
        .setQuery(query);
    saveFilterIfNotExistant(engine.getFilterService(), myTasksFilter);
    return myTasksFilter.getId();
  }

  private static void saveFilterIfNotExistant(FilterService filterService, Filter filter) {
    Filter singleResult = filterService.createFilterQuery().filterName(filter.getName()).singleResult();
    if (singleResult != null) {
      return;
    } else {
      filterService.saveFilter(filter);
    }
  }

}
