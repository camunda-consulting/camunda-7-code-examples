package com.camunda.consulting.scheduleJobExecution;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.consulting.OfflineSchedule;

@Configuration
public class ScheduleAwareJobAcquisitionPluginConfiguration {

  @Value("${job.scheduler.paused}")
  boolean paused;

  @Bean
  public ProcessEnginePlugin scheduleAwareJobAcquisitionPlugin(OfflineSchedule schedule) {
    return new ScheduleAwareJobAcquisitionPlugin(schedule, this.paused);
  }

}
