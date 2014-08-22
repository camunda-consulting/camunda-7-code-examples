package com.camunda.consulting.changeJobRetry;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.camunda.bpm.engine.impl.interceptor.RetryInterceptor;
import org.camunda.bpm.engine.impl.jobexecutor.FailedJobCommandFactory;

/**
 * @author Ingo Richtsmeier
 *
 */
public class NoRetryEnginePlugin extends AbstractProcessEnginePlugin {

	@Override
	public void preInit(
			ProcessEngineConfigurationImpl configuration) {
		configuration.setFailedJobCommandFactory(new FailedJobCommandFactory() {
			
			@Override
			public Command<Object> getCommand(String jobId, Throwable exception) {
				return new NoRetryJobCommand(jobId, exception);
			}
		});
	}

//  @Override
//  public void postInit(ProcessEngineConfigurationImpl configuration) {
//    CommandExecutor beforeTxRequired = configuration.getCommandExecutorTxRequired();
//    CommandInterceptor retryInterceptor = new RetryInterceptor();
//    retryInterceptor.setNext(beforeTxRequired);
//    configuration.setCommandExecutorTxRequired(retryInterceptor);
//    
//    CommandExecutor beforeTxRequiresNew = configuration.getCommandExecutorTxRequiresNew();
//    CommandInterceptor retryRequiresNew = new RetryInterceptor();
//    retryRequiresNew.setNext(beforeTxRequiresNew);
//    configuration.setCommandExecutorTxRequiresNew(retryRequiresNew);
//  }
	
	
}
