package org.camunda.bpm.delegate;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.ProcessInstantiationBuilderImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cmd.StartProcessInstanceCmd;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
public class CreateInstancesDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> workItems = (List<String>) execution.getVariable("workItems");

        ProcessEngineConfigurationImpl configuration = (ProcessEngineConfigurationImpl) BpmPlatform.getDefaultProcessEngine().getProcessEngineConfiguration();

        HistoryService historyService = execution.getProcessEngineServices().getHistoryService();
        int totalChildInstances = historyService.createHistoricProcessInstanceQuery()
                .variableValueEquals("parentBusinessKey", execution.getBusinessKey())
                .list().size();

        IntStream.range(totalChildInstances, workItems.size()).forEachOrdered(workItem -> {
            CommandExecutor commandExecutor = configuration.getCommandExecutorTxRequiresNew();
            configuration.getCommandExecutorTxRequiresNew().execute(new Command<Void>() {
                @Override
                public Void execute(CommandContext commandContext) {
                    ProcessInstantiationBuilderImpl.createProcessInstanceByKey(commandExecutor, "ChildProcess")
                            .setVariable("parentBusinessKey", execution.getBusinessKey())
                            .setVariable("workItem", workItems.get(workItem))
                            .execute();
                    return null;
                }
            });
        });
    }
}
