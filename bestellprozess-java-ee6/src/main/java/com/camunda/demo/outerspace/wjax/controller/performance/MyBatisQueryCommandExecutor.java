package com.camunda.demo.outerspace.wjax.controller.performance;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;


public class MyBatisQueryCommandExecutor {
  private MyBatisExtendedSessionFactory myBatisExtendedSessionFactory;

  public MyBatisQueryCommandExecutor(ProcessEngineConfigurationImpl processEngineConfiguration, String mappingResourceName) {
    myBatisExtendedSessionFactory = new MyBatisExtendedSessionFactory();
    myBatisExtendedSessionFactory.initFromProcessEngineConfiguration(processEngineConfiguration, mappingResourceName);
  }
  
  public <T> T executeQueryCommand(Command<T> command) {
    return myBatisExtendedSessionFactory.getCommandExecutorTxRequired().execute(command);
  }
}
