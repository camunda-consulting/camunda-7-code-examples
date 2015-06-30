package com.camunda.demo.outerspace.wjax.controller;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.task.Task;

import com.camunda.demo.outerspace.wjax.controller.performance.MyBatisQueryCommandExecutor;
import com.camunda.demo.outerspace.wjax.controller.performance.OrderProcessDTO;

public class TaskListController {
  
  @Inject 
  private TaskService taskService;
    
  @Produces
  @Named("tasks")
  public List<Task> getTaskList() {
    return taskService.createTaskQuery()
      .processDefinitionKey("wjax2012-bestellprozess")
      .list();
  }
  
  
//  @EJB(lookup = ProcessArchiveSupport.PROCESS_ENGINE_SERVICE_NAME)
//  private ProcessEngineService processEngineService;

  /**
   * See https://app.camunda.com/confluence/display/foxUserGuide/Performance+Tuning+with+custom+Queries
   */
  @Produces
  @Named("orderProcesses")
  public List<OrderProcessDTO> getOrders() {
    ProcessEngineImpl processEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    ProcessEngineConfigurationImpl processEngineConfiguration = processEngine.getProcessEngineConfiguration();

    MyBatisQueryCommandExecutor commandExecutor = new MyBatisQueryCommandExecutor(processEngineConfiguration, "customMappings.xml");
    return commandExecutor.executeQueryCommand(new Command<List<OrderProcessDTO>>() {

      @SuppressWarnings("unchecked")
      public List<OrderProcessDTO> execute(CommandContext commandContext) {
        return (List<OrderProcessDTO>) commandContext.getDbSqlSession().selectList("selectOrdersWithProcessInformation", null);
      }
    });
  }  
  
  @Inject
  private BusinessProcess bp;
  
  @Produces
  @Named("otherAssignee")
  public String getOtherAssignee() {
    List<Task> tasks = taskService.createTaskQuery().processInstanceId(bp.getProcessInstanceId()).list();
    for (Task task : tasks) {
      if (!task.getId().equals(bp.getTask())) {
        return task.getAssignee();        
      }
    }
    return null;
  }
}
