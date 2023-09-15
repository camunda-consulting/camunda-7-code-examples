package com.camunda.consulting.scheduleWithCommandInterceptor;

import com.camunda.consulting.OfflineSchedule;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.stereotype.Component;

@Component
public class OfflineSchedulePlugin extends AbstractProcessEnginePlugin {

  private final OfflineSchedule schedule;

  public OfflineSchedulePlugin(OfflineSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration
        .getCustomPostCommandInterceptorsTxRequired()
        .add(new OfflineScheduleCommandInterceptor(schedule));
  }
}
