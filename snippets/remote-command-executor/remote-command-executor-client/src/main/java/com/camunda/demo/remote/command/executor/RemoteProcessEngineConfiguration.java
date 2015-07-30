package com.camunda.demo.remote.command.executor;

import java.util.Collection;

import org.camunda.bpm.engine.impl.cfg.JtaProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.camunda.bpm.engine.impl.persistence.StrongUuidGenerator;

public class RemoteProcessEngineConfiguration extends JtaProcessEngineConfiguration {
  
  public RemoteProcessEngineConfiguration() {
    initialize();
    actualCommandExecutor = new RemoteCommandExecutor();
  }

  public RemoteProcessEngineConfiguration(CommandExecutionService commandExecutionService) {
    initialize();
    actualCommandExecutor = new RemoteCommandExecutor(commandExecutionService);
  }

  private void initialize() {
    setDatabaseSchemaUpdate(DB_SCHEMA_UPDATE_FALSE);
    setJdbcUrl(null);
    setJobExecutorActivate(false);
  }
  
  @Override
  protected void initIdGenerator() {
    if (idGenerator == null) {
      idGenerator = new StrongUuidGenerator();
    }
  }
  
  @Override
  protected void initCommandExecutors() {
    setCommandExecutorTxRequired(actualCommandExecutor);
    setCommandExecutorTxRequiresNew(actualCommandExecutor);
    setCommandExecutorSchemaOperations(actualCommandExecutor);
  }
  
  @Override
  protected void initTransactionManager() {
  }
  
  @Override
  protected void initDbSchemaOperationsCommandContextFactory() {
  }

  
  @Override
  protected void initDataSource() {
  }

  @Override
  protected void initSqlSessionFactory() {
  }
    
  @Override
  protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequired() {
    throw new UnsupportedOperationException();
  }
  
  @Override
  protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequiresNew() {
    throw new UnsupportedOperationException();
  }
  
}
