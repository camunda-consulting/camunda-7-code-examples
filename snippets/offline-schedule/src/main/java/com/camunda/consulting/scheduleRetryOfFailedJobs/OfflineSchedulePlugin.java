package com.camunda.consulting.scheduleRetryOfFailedJobs;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.camunda.consulting.OfflineSchedule;

public class OfflineSchedulePlugin extends AbstractProcessEnginePlugin {
  private final OfflineSchedule schedule;

  public OfflineSchedulePlugin(OfflineSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setFailedJobCommandFactory(new ScheduledFailedJobCommandFactory(this.schedule));
  }
}
