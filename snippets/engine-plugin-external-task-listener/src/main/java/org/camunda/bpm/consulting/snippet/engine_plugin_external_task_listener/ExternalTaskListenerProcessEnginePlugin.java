package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

public class ExternalTaskListenerProcessEnginePlugin implements ProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<CommandInterceptor> postCommandInterceptors = processEngineConfiguration.getCustomPostCommandInterceptorsTxRequired();
    if (postCommandInterceptors == null) {
      postCommandInterceptors = new ArrayList<>();
      processEngineConfiguration.setCustomPostCommandInterceptorsTxRequired(postCommandInterceptors);
    }
    postCommandInterceptors.add(new ExternalTaskListenerCommandInterceptor(new LockingExternalTaskListener()));
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {

  }

}
