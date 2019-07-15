package com.camunda.consulting.anonymize_user_task_data.plugin;

import com.camunda.consulting.anonymize_user_task_data.history.AnonymizeTaskWorkerHistoryEventProducer;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class AnonymizeProcessEnginePlugin extends AbstractProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setHistoryEventProducer(new AnonymizeTaskWorkerHistoryEventProducer());
    super.preInit(processEngineConfiguration);
  }
}
