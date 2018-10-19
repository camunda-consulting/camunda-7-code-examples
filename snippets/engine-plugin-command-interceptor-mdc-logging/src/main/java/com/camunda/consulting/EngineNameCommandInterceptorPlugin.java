package com.camunda.consulting;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

import java.util.ArrayList;
import java.util.List;

public class EngineNameCommandInterceptorPlugin extends AbstractProcessEnginePlugin {

  protected EngineNameCommandInterceptor engineNameCommandInterceptor = new EngineNameCommandInterceptor();

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

    // add command interceptor to configuration
    List<CommandInterceptor> customPreCommandInterceptorsTxRequired = processEngineConfiguration.getCustomPreCommandInterceptorsTxRequired();
    if(customPreCommandInterceptorsTxRequired == null) {
      customPreCommandInterceptorsTxRequired = new ArrayList<CommandInterceptor>();
      processEngineConfiguration.setCustomPreCommandInterceptorsTxRequired(customPreCommandInterceptorsTxRequired);
    }
    customPreCommandInterceptorsTxRequired.add(engineNameCommandInterceptor);

    List<CommandInterceptor> customPreCommandInterceptorsTxRequiresNew = processEngineConfiguration.getCustomPreCommandInterceptorsTxRequiresNew();
    if(customPreCommandInterceptorsTxRequiresNew == null) {
      customPreCommandInterceptorsTxRequiresNew = new ArrayList<CommandInterceptor>();
      processEngineConfiguration.setCustomPreCommandInterceptorsTxRequiresNew(customPreCommandInterceptorsTxRequiresNew);
    }
    customPreCommandInterceptorsTxRequired.add(engineNameCommandInterceptor);

    engineNameCommandInterceptor.setEngineName(processEngineConfiguration.getProcessEngineName());
  }

}