package org.camunda.bpm.delegate;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.ProcessInstantiationBuilderImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cmd.SetExecutionVariablesCmd;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class CreateInstancesDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> workItems = (List<String>) execution.getVariable("workItems");

        ProcessEngineConfigurationImpl configuration = (ProcessEngineConfigurationImpl) BpmPlatform.getDefaultProcessEngine().getProcessEngineConfiguration();

        int createdInstancesCount = (int) execution.getVariable("createdInstancesCount");

        IntStream.range(createdInstancesCount, workItems.size()).forEachOrdered(workItem -> {
            CommandExecutor commandExecutor = configuration.getCommandExecutorTxRequiresNew();
            commandExecutor.execute(new Command<Void>() {
                @Override
                public Void execute(CommandContext commandContext) {
                    ProcessInstantiationBuilderImpl.createProcessInstanceByKey(commandExecutor, "ChildProcess")
                            .setVariable("parentBusinessKey", execution.getBusinessKey())
                            .setVariable("workItem", workItems.get(workItem))
                            .execute();
                    Map<String, Object> variables = new HashMap<String, Object>();
                    variables.put("createdInstancesCount", workItem+1);
                    commandExecutor.execute(new SetExecutionVariablesCmd(execution.getId(), variables, false));
                    return null;
                }
            });
        });
    }
}
