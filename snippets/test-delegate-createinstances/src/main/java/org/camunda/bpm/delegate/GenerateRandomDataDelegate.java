package org.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class GenerateRandomDataDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int listSize = (int) execution.getVariable("listSize");
        List<String> workItems = new ArrayList<String>();
        IntStream.range(0, listSize).forEachOrdered(workItem -> {
            workItems.add(UUID.randomUUID().toString());
        });
        execution.setVariable("workItems", Variables.objectValue(workItems)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON).create());
    }
}
