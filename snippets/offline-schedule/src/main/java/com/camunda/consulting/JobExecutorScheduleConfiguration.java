package com.camunda.consulting;

import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobExecutorScheduleConfiguration extends OfflineSchedulePluginProperties {

  @Bean
  public JobExecutorSchedule schedule() {
    return new JobExecutorSchedule(
        this.getBreaks().stream().map(JobExecutorBreakParser::parse).collect(Collectors.toSet()));
  }
}
