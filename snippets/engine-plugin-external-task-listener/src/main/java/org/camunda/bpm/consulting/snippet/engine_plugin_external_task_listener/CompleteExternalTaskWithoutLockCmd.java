package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cmd.ExternalTaskCmd;
import org.camunda.bpm.engine.impl.persistence.entity.ExternalTaskEntity;

public class CompleteExternalTaskWithoutLockCmd extends ExternalTaskCmd {

  CompleteExternalTaskWithoutLockCmd(String externalTaskId) {
    super(externalTaskId);
  }

  @Override
  protected void execute(ExternalTaskEntity externalTask) {
    externalTask.complete(null, null);
  }

  @Override
  protected void validateInput() {
  }

  public static void completeExternalTaskWithoutLock(ProcessEngine processEngine, ExternalTask externalTask) {
    completeExternalTaskWithoutLock(processEngine.getProcessEngineConfiguration(), externalTask);
  }

  public static void completeExternalTaskWithoutLock(ProcessEngine processEngine, String externalTaskId) {
    completeExternalTaskWithoutLock(processEngine.getProcessEngineConfiguration(), externalTaskId);
  }

  public static void completeExternalTaskWithoutLock(ProcessEngineConfiguration configuration, ExternalTask externalTask) {
    completeExternalTaskWithoutLock(configuration, externalTask.getId());
  }

  public static void completeExternalTaskWithoutLock(ProcessEngineConfiguration configuration, String externalTaskId) {
    ((ProcessEngineConfigurationImpl) configuration).getCommandExecutorTxRequired()
        .execute(new CompleteExternalTaskWithoutLockCmd(externalTaskId));
  }

}