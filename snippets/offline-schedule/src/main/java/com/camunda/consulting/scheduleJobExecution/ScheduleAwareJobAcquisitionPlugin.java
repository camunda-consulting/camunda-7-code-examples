package com.camunda.consulting;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class ScheduleAwareJobAcquisitionPlugin extends AbstractProcessEnginePlugin {
  private final JobExecutorSchedule schedule;

  public ScheduleAwareJobAcquisitionPlugin(JobExecutorSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.getJobExecutor().setAcquireJobsCmdFactory(
        new ScheduleAwareAquireJobsCommandFactory(processEngineConfiguration.getJobExecutor(), this.schedule));
  };
}
