package com.camunda.demo.failedjobhandler;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.FailedJobCommandFactory;

public class MyFailedJobPlugin implements ProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setFailedJobCommandFactory(new FailedJobCommandFactory() {      
      @Override
      public Command<Object> getCommand(String jobId, Throwable exception) {
        // TODO Auto-generated method stub
        return new MyFailedJobCmd(jobId, exception);
      }
    });
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
  }

}
