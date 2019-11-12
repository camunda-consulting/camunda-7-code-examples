package org.camunda.case1;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("case1CompleteTasks")
public class CompleteTasks implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(CompleteTasks.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String toUserTaskKey = (String) execution.getVariable("toUserTaskKey");

        ProcessEngine processEngine = execution.getProcessEngine();
        List<Task> taskList =
                processEngine.getTaskService()
                        .createTaskQuery()
                        .taskDefinitionKey(toUserTaskKey)
                        .list();

        for (Task task : taskList) {
            LOGGER.info("+++ about to complete '" + toUserTaskKey + "' id = " + task.getId());
            processEngine.getTaskService().complete(task.getId());
        }

    }
}
