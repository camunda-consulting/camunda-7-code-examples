package com.camunda.demo.subtaskhierarchy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.IdentityLinkType;

@Path("/info")
@ApplicationPath("/api")
public class InfoRestService extends Application {

  @Inject
  private ProcessEngine processEngine;
  
  @POST
  @Path("/task/{id}/subtask")
  @Produces("application/json")
  public List<TaskDto> createNewSubtask(@PathParam("id") String parentTaskId, TaskDto newTaskData) {
    HistoricTaskInstance currentTask = findTask(parentTaskId);
    HashMap<String, Object> variables = new HashMap<String, Object>();
    variables.put("subtaskName", newTaskData.getName());
    variables.put("subtaskAssignee", newTaskData.getAssignee());
    variables.put("subtaskDueDate", newTaskData.getDueDate());
    processEngine.getRuntimeService().messageEventReceived("SUBTASK_WANTED", currentTask.getProcessInstanceId(), variables);  
    return getTaskHierarchy(parentTaskId);
  }

  @GET
  @Path("/task/{id}")
  @Produces("application/json")
  public List<TaskDto> getTaskHierarchy(@PathParam("id") String taskId) {
    ArrayList<TaskDto> tasks = new ArrayList<TaskDto>();

    HistoricTaskInstance currentTask = findTask(taskId);

    HistoricTaskInstance parentTask = getParentTask(currentTask);
    
    tasks.add(createTaskDto(parentTask, 0));
    addChildTasks(tasks, parentTask, 1);

    return tasks;
  }

  private HistoricTaskInstance findTask(String taskId) {
    HistoricTaskInstance currentTask = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
    if (currentTask==null) {
      throw new RuntimeException("Task '" + taskId + "' not found!");
    }
    return currentTask;
  }
  
  private HistoricTaskInstance getParentTask(HistoricTaskInstance currentTask) {
    HistoricProcessInstance pi = processEngine.getHistoryService().createNativeHistoricProcessInstanceQuery()
        .sql("SELECT * FROM ACT_HI_PROCINST RES WHERE RES.ID_ = (select PROC_INST_ID_ from ACT_HI_PROCINST where ID_ = (select SUPER_PROCESS_INSTANCE_ID_   from ACT_HI_PROCINST where ID_ = '"+currentTask.getProcessInstanceId()+"'))").singleResult();       
    if (pi == null) {
      // no parent --> root level reached
      return currentTask;
    }

    HistoricTaskInstance task = processEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(pi.getId()).singleResult();
    return getParentTask(task);
  }

  private void addChildTasks(List<TaskDto> tasks, HistoricTaskInstance currentTask, long hierarchyLevel) {
    List<HistoricProcessInstance> processInstances = processEngine.getHistoryService().createHistoricProcessInstanceQuery()
        .superProcessInstanceId(currentTask.getProcessInstanceId()).list();
    for (HistoricProcessInstance pi : processInstances) {
      HistoricTaskInstance task = processEngine.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(pi.getId()).singleResult();
      tasks.add(createTaskDto(task, hierarchyLevel));
      addChildTasks(tasks, task, hierarchyLevel + 1);
    }
  }

  private TaskDto createTaskDto(HistoricTaskInstance task, long hierarchyLevel) {
    TaskDto dto = new TaskDto();
    dto.setEnded( task.getEndTime()!=null );
    dto.setId(task.getId());
    dto.setAssignee(task.getAssignee());
    dto.setName(task.getName());
    if (task.getDueDate()!=null) {
      dto.setDueDate(new SimpleDateFormat().format(task.getDueDate()));
    } else {
      dto.setDueDate("");
    }
    dto.setHierarchyLevel(hierarchyLevel);
    
    if (processEngine.getTaskService().createTaskQuery().taskId(task.getId()).count()>0) {
      List<IdentityLink> identityLinksForTask = processEngine.getTaskService().getIdentityLinksForTask(task.getId());
      String candidates = "";
      for (IdentityLink identityLink : identityLinksForTask) {
        if (identityLink.getUserId()!=null) {
          candidates += identityLink.getUserId() + ", ";
        } else {
          candidates += identityLink.getGroupId() + ", ";        
        }
      }
      if (candidates.length()>2) { // remove last comma
        candidates.substring(0, candidates.length()-2);
      }
      dto.setCandidates(candidates);
    }    
    return dto;
  }
  
  @GET
  @Path("/user")
  @Produces("application/json")
  public List<UserDto> getUsers() {
    ArrayList<UserDto> result = new ArrayList<UserDto>();
    List<User> users = processEngine.getIdentityService().createUserQuery().list();
    for (User user : users) {
      result.add(createUserDto(user));
    }
    return result;
  }

  private UserDto createUserDto(User user) {
    UserDto dto = new UserDto();
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    dto.setId(user.getId());
    return dto;
  }
}
