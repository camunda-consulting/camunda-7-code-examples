package com.camunda.consulting.scheduleJobExecution;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.camunda.consulting.OfflineSchedule;

public class ScheduleAwareJobAcquisitionPlugin extends AbstractProcessEnginePlugin {
  private final OfflineSchedule schedule;
  private final boolean paused;

  public ScheduleAwareJobAcquisitionPlugin(OfflineSchedule schedule, boolean paused) {
    this.schedule = schedule;
    this.paused = paused;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    if (this.paused) {
      processEngineConfiguration.getJobExecutor().setAcquireJobsCmdFactory(
          new ScheduleAwareAquireJobsCommandFactory(processEngineConfiguration.getJobExecutor(), this.schedule));
    }
  };
}
