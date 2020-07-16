package com.camunda.consulting.simulator;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SampleProcessSimulator implements ProcessInstanceExecutor<Long> {

    private final ProcessEngine processEngine;
    private static final String PROCESS_KEY = "postgresql-demo-process";

    @Override
    public void executeInstance(Long payload) {
        VariableMap variables = Variables.createVariables()
                .putValue("number", payload);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_KEY,
                UUID.randomUUID().toString(), variables);

        Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        processEngine.getTaskService().complete(task.getId());

        Job job = processEngine.getManagementService().createJobQuery().processInstanceId(processInstance.getId()).singleResult();
        processEngine.getManagementService().executeJob(job.getId());

        List<LockedExternalTask> externalTasks = processEngine.getExternalTaskService().fetchAndLock(1, "sample")
                .topic("external-task", 10000).execute();

        externalTasks.forEach(lockedExternalTask -> processEngine.getExternalTaskService().complete(lockedExternalTask.getId(), "sample"));
    }
}
