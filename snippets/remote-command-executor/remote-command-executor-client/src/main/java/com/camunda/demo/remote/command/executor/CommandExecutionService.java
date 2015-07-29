package com.camunda.demo.remote.command.executor;

import org.camunda.bpm.engine.impl.interceptor.Command;

public interface CommandExecutionService {

  <T> T execute(Command<T> command);

}
