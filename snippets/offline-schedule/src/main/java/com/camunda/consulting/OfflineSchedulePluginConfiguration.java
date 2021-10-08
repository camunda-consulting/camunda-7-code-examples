package com.camunda.consulting;

import java.util.Set;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfflineSchedulePluginConfiguration extends OfflineSchedulePluginProperties {

  @Bean
  public ProcessEnginePlugin offlineSchedulePlugin(Set<JobExecutorBreak> schedule) {
    return new OfflineSchedulePlugin(schedule);
  }

  @Bean
  public Set<JobExecutorBreak> breaks() {
    return this.getBreaks().stream().map(JobExecutorBreakParser::parse).collect(Collectors.toSet());
  }
}
