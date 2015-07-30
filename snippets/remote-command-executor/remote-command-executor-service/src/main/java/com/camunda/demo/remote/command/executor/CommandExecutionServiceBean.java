package com.camunda.demo.remote.command.executor;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;

@Stateless
@Remote(CommandExecutionService.class)
public class CommandExecutionServiceBean implements CommandExecutionService {

  private ProcessEngine processEngine;

  public CommandExecutionServiceBean() {
    this.processEngine = BpmPlatform.getDefaultProcessEngine();
  }

  public CommandExecutionServiceBean(ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

  public <T> T execute(Command<T> command) {
    ProcessEngineConfiguration configuration = processEngine.getProcessEngineConfiguration();
    return ((ProcessEngineConfigurationImpl) configuration).getCommandExecutorTxRequired().execute(command);
  }

}
