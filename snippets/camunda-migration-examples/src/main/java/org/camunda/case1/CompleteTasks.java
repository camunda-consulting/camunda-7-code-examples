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
        Integer maxUserComplete = (Integer) execution.getVariable("maxUserComplete");

        if (maxUserComplete == null || maxUserComplete.intValue() < 1) {
            maxUserComplete = new Integer(1);
        }

        ProcessEngine processEngine = execution.getProcessEngine();
        List<Task> taskList =
                processEngine.getTaskService()
                        .createTaskQuery()
                        .taskDefinitionKey(toUserTaskKey)
                        .list();

        // if there are tasks to complete
        int count = maxUserComplete.intValue();
        if (taskList != null && taskList.size() > 0) {
            if (taskList.size() > maxUserComplete.intValue()) {
                taskList = taskList.subList(0, maxUserComplete.intValue());
                execution.setVariable("moreTasksToComplete", true);
            } else {
                count = taskList.size();
                execution.setVariable("moreTasksToComplete", false);
            }
            LOGGER.info("+++ about to complete " + count + " instances of '" + toUserTaskKey + "'");
            for (Task task : taskList) {
                LOGGER.info("+++ about to complete '" + toUserTaskKey + "' id = " + task.getId());
                processEngine.getTaskService().complete(task.getId());
            }
        } else {
            LOGGER.info("+++ all tasks are complete...");
            execution.setVariable("moreTasksToComplete", false);
        }

    }
}
