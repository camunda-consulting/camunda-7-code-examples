package com.camunda.consulting;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class OfflineSchedulePlugin extends AbstractProcessEnginePlugin {
  private final JobExecutorSchedule schedule;

  public OfflineSchedulePlugin(JobExecutorSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setFailedJobCommandFactory(new ScheduledFailedJobCommandFactory(this.schedule));
  }
}
