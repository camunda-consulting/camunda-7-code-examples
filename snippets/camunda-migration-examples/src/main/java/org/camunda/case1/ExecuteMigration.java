package org.camunda.case1;

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

@Component("case1ExecMigration")
public class ExecuteMigration implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(ExecuteMigration.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String processDefKey = (String) execution.getVariable("processDefKey");
        Integer fromVersion = (Integer) execution.getVariable("fromVersion");
        Integer toVersion = (Integer) execution.getVariable("toVersion");
        String fromUserTaskKey = (String) execution.getVariable("fromUserTaskKey");
        String toUserTaskKey = (String) execution.getVariable("toUserTaskKey");
        Integer maxPerBatch = (Integer) execution.getVariable("maxPerBatch");
        Boolean skipIoMappings = (Boolean) execution.getVariable("skipIoMappings");
        Boolean skipCustomListeners = (Boolean) execution.getVariable("skipCustomListeners");

        ProcessEngine processEngine = execution.getProcessEngine();

        // query to get the process definition metadata
        ProcessDefinition v1def = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefKey)
                .processDefinitionVersion(fromVersion)
                .singleResult();

        ProcessDefinition v2def = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefKey)
                .processDefinitionVersion(toVersion)
                .singleResult();

        LOGGER.info(" *** v1def.getId() = " + v1def.getId() + " *** ");
        LOGGER.info(" *** v2def.getId() = " + v2def.getId() + " *** ");

        MigrationPlan migrationPlan = processEngine.getRuntimeService()
                .createMigrationPlan(v1def.getId(), v2def.getId())
                .mapEqualActivities()
                .mapActivities(fromUserTaskKey, toUserTaskKey)
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

        if (instanceListSize.intValue() <= maxPerBatch.intValue()) {
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
