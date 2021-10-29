package com.camunda.consulting;

import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfflineScheduleConfiguration extends OfflineSchedulePluginProperties {

  @Bean
  public OfflineSchedule schedule() {
    return new OfflineSchedule(
        this.getBreaks().stream().map(JobExecutorBreakParser::parse).collect(Collectors.toSet()));
  }
}
