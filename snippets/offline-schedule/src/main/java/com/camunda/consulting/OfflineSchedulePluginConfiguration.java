package com.camunda.consulting;

import java.util.Set;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

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

  @Scheduled(cron = "0 0 3 * *")
  public void stopJobExecutor(ProcessEngine processEngine) {
    getProcessEngineConfigurationImpl(processEngine).getJobExecutor().shutdown();
  }

  @Scheduled(cron = "0 0 5 * *")
  public void startJobExecutor(ProcessEngine processEngine) {
    getProcessEngineConfigurationImpl(processEngine).getJobExecutor().start();
  }

  private static ProcessEngineConfigurationImpl getProcessEngineConfigurationImpl(ProcessEngine processEngine) {
    return (ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration();
  }
}
