package com.camunda.consulting.scheduleJobExecution;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.consulting.JobExecutorSchedule;

@Configuration
public class OfflineSchedulePluginConfiguration {

  @Bean
  public ProcessEnginePlugin offlineSchedulePlugin(JobExecutorSchedule schedule) {
    return new ScheduleAwareJobAcquisitionPlugin(schedule);
  }

}
