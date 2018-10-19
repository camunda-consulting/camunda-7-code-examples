package com.camunda.consulting;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.jboss.logging.MDC;

public class EngineNameCommandInterceptor extends CommandInterceptor {

  private String engineName;

  public void setEngineName(String engineNameInput) {
    engineName = engineNameInput;
  }

  @Override
  public <T> T execute(Command<T> command) {

    MDC.put("engine-name", engineName);
    return next.execute(command);
  }


}
