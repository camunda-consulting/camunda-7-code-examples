package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import java.util.concurrent.CompletableFuture;

import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cmd.CompleteExternalTaskCmd;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.ExternalTaskEntity;
import org.camunda.bpm.engine.variable.VariableMap;
import org.joda.time.DateTime;

public class LockingExternalTaskListener implements ExternalTaskListener {

  @Override
  public void notify(ExternalTask externalTask) {
    String externalTaskId = externalTask.getId();
    System.out.println("New External Task: " + externalTaskId);

    // lock the task in the same TX that creates it
    ExternalTaskEntity entity = (ExternalTaskEntity) externalTask;
    String workerId = "PushedToWorkerInsideSameJVM";
    entity.setWorkerId(workerId);
    entity.setLockExpirationTime(new DateTime().plusMinutes(5).toDate());
    
    // get variables
    VariableMap variables = entity.getExecution().getVariables();
    VariableMap localVariables = entity.getExecution().getVariablesLocal();
    
    ProcessEngineConfigurationImpl processEngineConfiguration = Context.getProcessEngineConfiguration();

    CompletableFuture.runAsync(() -> { 
      // give the database enough time to commit the TX that creates the external task
      try {
        Thread.sleep(200L); // TODO use ScheduledExecutorService and/or Queue
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      
      // TODO do some meaningful work here
      String variableValue = (String) variables.get("foo");
      variables.put("foo", variableValue + " changed by worker");
      
      System.out.println("Completing External Task: " + externalTaskId);
      processEngineConfiguration.getCommandExecutorTxRequiresNew()
        .execute(
            new CompleteExternalTaskCmd(externalTaskId, workerId, variables, localVariables));
    }).exceptionally(e -> {
      System.out.println("Oops! We have an exception - " + e.getMessage());
      return null;
    });
  }

}
