package org.company.camunda.plugin.jobfail;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.FailedJobCommandFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobFailureHandlerProcessEnginePlugin extends AbstractProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl engineConfig) {
        // replace DefaultJobCommandFactory with own Factory, which will use our custom command
        engineConfig.setFailedJobCommandFactory(new FailedJobCommandFactory() {
            @Override
            public Command<Object> getCommand(String jobId, Throwable exception) {
                return new MyJobRetryCmd(jobId, exception);
            }
        });
    }
}

