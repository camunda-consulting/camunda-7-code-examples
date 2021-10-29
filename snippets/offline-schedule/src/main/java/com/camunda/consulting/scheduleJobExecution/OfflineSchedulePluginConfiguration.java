package com.camunda.consulting.scheduleJobExecution;

import java.util.stream.Collectors;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.consulting.JobExecutorBreakParser;
import com.camunda.consulting.JobExecutorSchedule;
import com.camunda.consulting.OfflineSchedulePluginProperties;

@Configuration
public class OfflineSchedulePluginConfiguration extends OfflineSchedulePluginProperties {

  @Bean
  public ProcessEnginePlugin offlineSchedulePlugin(JobExecutorSchedule schedule) {
    return new ScheduleAwareJobAcquisitionPlugin(schedule);
  }

  @Bean
  public JobExecutorSchedule schedule() {
    return new JobExecutorSchedule(
        this.getBreaks().stream().map(JobExecutorBreakParser::parse).collect(Collectors.toSet()));
  }
}
