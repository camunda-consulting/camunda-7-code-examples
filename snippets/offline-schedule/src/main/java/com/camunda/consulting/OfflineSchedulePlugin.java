package com.camunda.consulting;

import java.util.Set;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class OfflineSchedulePlugin extends AbstractProcessEnginePlugin {
  private final Set<JobExecutorBreak> schedule;

  public OfflineSchedulePlugin(Set<JobExecutorBreak> schedule) {
    this.schedule = schedule;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setFailedJobCommandFactory(new ScheduledFailedJobCommandFactory(this.schedule));
  }
}
