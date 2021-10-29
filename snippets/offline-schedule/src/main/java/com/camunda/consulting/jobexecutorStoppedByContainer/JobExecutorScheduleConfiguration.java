package com.camunda.consulting.jobexecutorStoppedByContainer;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class JobExecutorScheduleConfiguration {
  private final ProcessEngine processEngine;

  @Autowired
  public JobExecutorScheduleConfiguration(ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

  @Scheduled(cron = "${job.schedule.shutdown}")
  public void stopJobExecutor() {
    getProcessEngineConfigurationImpl(this.processEngine).getJobExecutor().shutdown();
  }

  @Scheduled(cron = "${job.schedule.start}")
  public void startJobExecutor() {
    getProcessEngineConfigurationImpl(this.processEngine).getJobExecutor().start();
  }

  private static ProcessEngineConfigurationImpl getProcessEngineConfigurationImpl(ProcessEngine processEngine) {
    return (ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration();
  }
}