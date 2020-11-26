package com.example.workflow.delegates;

import com.example.workflow.config.ProcessBatchConfig;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class GenerateDataDelegate implements JavaDelegate {

    private final ProcessBatchConfig processBatchConfig;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer randomDataSize = (Integer) delegateExecution.getVariable("randomDataSize");
        List<String> records = LongStream.range(0, randomDataSize)
                .mapToObj(operand -> UUID.randomUUID().toString()).collect(Collectors.toList());

        ObjectValue recordsObj = Variables.objectValue(records)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON).create();

        delegateExecution.setVariable("records", recordsObj);
        delegateExecution.setVariable("createdInstancesCount", 0);
        delegateExecution.setVariable("totalInstances", records.size());
    }
}
