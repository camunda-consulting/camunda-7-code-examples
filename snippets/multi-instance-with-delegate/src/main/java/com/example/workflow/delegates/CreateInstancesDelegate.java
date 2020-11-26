package com.example.workflow.delegates;

import com.example.workflow.config.ProcessBatchConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.context.ProcessEngineContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateInstancesDelegate implements JavaDelegate {

    private final ProcessBatchConfig processBatchConfig;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RuntimeService runtimeService = delegateExecution.getProcessEngine().getRuntimeService();

        List<String> records = (List<String>) delegateExecution.getVariable("records");

        Integer createdInstancesCount = (Integer) delegateExecution.getVariable("createdInstancesCount");

        Integer batchNumber = Math.min(records.size() - createdInstancesCount, processBatchConfig.getBlockSize()) + createdInstancesCount;
        log.debug("Created instances count: {} ", createdInstancesCount);
        log.debug("Batch number: {} ", batchNumber);

        IntStream.range(createdInstancesCount, batchNumber).forEachOrdered(index ->
                 createInstance(records.get(index), index, delegateExecution));

        createdInstancesCount = (Integer) delegateExecution.getVariable("createdInstancesCount");

        delegateExecution.setVariable("allCreated", createdInstancesCount == records.size());

    }

    private void createInstance(String record, Integer index, DelegateExecution execution) {
        try {
            ProcessEngineContext.requiresNew();
            execution.getProcessEngine().getRuntimeService().createProcessInstanceByKey("ChildProcess")
                    .setVariable("parentBusinessKey", execution.getBusinessKey())
                    .setVariable("record", record)
                    .businessKey(execution.getBusinessKey() + "-" + (index + 1)).execute();

            execution.setVariable("createdInstancesCount", index + 1);
        }
        finally {
            ProcessEngineContext.clear();
        }
    }
}