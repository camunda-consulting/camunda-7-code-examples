package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import static org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener.CompleteExternalTaskWithoutLockCmd.completeExternalTaskWithoutLock;

import java.util.concurrent.CompletableFuture;

import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.context.Context;

public class CompleteWithoutLockExternalTaskListener implements ExternalTaskListener {

  @Override
  public void notify(ExternalTask externalTask) {
    String externalTaskId = externalTask.getId();
    System.out.println("New External Task: " + externalTaskId);
    ProcessEngineConfigurationImpl processEngineConfiguration = Context.getProcessEngineConfiguration();

    CompletableFuture.runAsync(() -> {
      // give the database enough time to commit the TX that creates the external task
      try {
        Thread.sleep(200L); // TODO use ScheduledExecutorService and or Queue
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      completeExternalTaskWithoutLock(processEngineConfiguration, externalTaskId);
    }).exceptionally(e -> {
      System.out.println("Oops! We have an exception - " + e.getMessage());
      return null;
    });
  }

}
