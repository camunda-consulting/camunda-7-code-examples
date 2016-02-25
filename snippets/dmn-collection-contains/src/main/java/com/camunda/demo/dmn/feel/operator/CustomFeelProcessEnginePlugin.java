package com.camunda.demo.dmn.feel.operator;

import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

public class CustomFeelProcessEnginePlugin implements ProcessEnginePlugin {

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
      DefaultDmnEngineConfiguration dmnEngineConfiguration = (DefaultDmnEngineConfiguration) processEngineConfiguration.getDmnEngineConfiguration();
      dmnEngineConfiguration.setFeelEngineFactory(new CustomFeelEngineFactory());      
  }

  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
  }

  public void postProcessEngineBuild(ProcessEngine processEngine) {
  }
}
