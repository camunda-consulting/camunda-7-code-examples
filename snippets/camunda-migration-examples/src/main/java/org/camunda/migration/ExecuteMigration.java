package org.camunda.migration;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.migration.MigrationPlan;
import org.camunda.bpm.engine.migration.MigrationPlanExecutionBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("execMigrationAsync")
public class ExecuteMigration implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(ExecuteMigration.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String origProcessDefKey = (String) execution.getVariable("origProcessDefKey");
        Integer origProcessDefVersion = (Integer) execution.getVariable("origProcessDefVersion");
        String destProcessDefKey = (String) execution.getVariable("destProcessDefKey");
        Integer destProcessDefVersion = (Integer) execution.getVariable("destProcessDefVersion");
        String origTargetTask = (String) execution.getVariable("origTargetTask");
        String destTargetTask = (String) execution.getVariable("destTargetTask");

//        String fromUserTaskKey = (String) execution.getVariable("fromUserTaskKey");
//        String toUserTaskKey = (String) execution.getVariable("toUserTaskKey");
        Integer maxPerBatch = (Integer) execution.getVariable("maxPerBatch");
        Boolean skipIoMappings = (Boolean) execution.getVariable("skipIoMappings");
        Boolean skipCustomListeners = (Boolean) execution.getVariable("skipCustomListeners");

        ProcessEngine processEngine = execution.getProcessEngine();

        // query to get the process definition metadata
        ProcessDefinition v1def = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(origProcessDefKey)
                .processDefinitionVersion(origProcessDefVersion)
                .singleResult();

        ProcessDefinition v2def = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(destProcessDefKey)
                .processDefinitionVersion(destProcessDefVersion)
                .singleResult();

        LOGGER.info(" *** v1def.getId() = " + v1def.getId() + " *** ");
        LOGGER.info(" *** v2def.getId() = " + v2def.getId() + " *** ");

        MigrationPlan migrationPlan = processEngine.getRuntimeService()
                .createMigrationPlan(v1def.getId(), v2def.getId())
                .mapEqualActivities()
                .mapActivities(origTargetTask, destTargetTask)
                .build();

        ProcessInstanceQuery query =
                processEngine.getRuntimeService()
                        .createProcessInstanceQuery()
                        .processDefinitionId(v1def.getId());

        Integer instanceListSize = 0;

        List<ProcessInstance> piList = query.list();

        if (piList != null) {
            instanceListSize = piList.size();
        }

        LOGGER.info("  process instance query size = " + instanceListSize);

        MigrationPlanExecutionBuilder builder =
                processEngine.getRuntimeService().newMigration(migrationPlan);

        if (instanceListSize <= maxPerBatch) {
            builder.processInstanceQuery(query);
        } else {
            List<String> idList = new ArrayList<>();
            for (ProcessInstance inst : Objects.requireNonNull(piList)) {
                idList.add(inst.getProcessInstanceId());
            }
            builder.processInstanceIds(idList.subList(0, maxPerBatch));
        }

        if (skipIoMappings) {
            builder.skipIoMappings();
        }

        if (skipCustomListeners) {
            builder.skipCustomListeners();
        }

        Batch mybatch = builder.executeAsync();
        LOGGER.info("  batch id = " + mybatch.getId());
        execution.setVariable("batchId", mybatch.getId());

    }

}
