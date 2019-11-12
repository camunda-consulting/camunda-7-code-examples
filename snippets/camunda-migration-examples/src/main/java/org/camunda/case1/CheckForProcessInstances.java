package org.camunda.case1;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("case1CheckForInstances")
public class CheckForProcessInstances implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(CheckForProcessInstances.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        ProcessEngine processEngine = execution.getProcessEngine();

        boolean hasPI = false;
        String processDefKey = (String) execution.getVariable("processDefKey");
        Integer fromVersion = (Integer) execution.getVariable("fromVersion");
        Integer toVersion = (Integer) execution.getVariable("toVersion");
        String fromUserTaskKey = (String) execution.getVariable("fromUserTaskKey");
        String toUserTaskKey = (String) execution.getVariable("toUserTaskKey");
        Integer maxPerBatch = (Integer) execution.getVariable("maxPerBatch");
        Boolean skipIoMappings = (Boolean) execution.getVariable("skipIoMappings");
        Boolean skipCustomListeners = (Boolean) execution.getVariable("skipCustomListeners");

        if (processDefKey == null || fromVersion == null || toVersion == null ||
                fromUserTaskKey == null || toUserTaskKey == null) {
            LOGGER.info("******* WARNING:  Insufficient Data.  No Migration will take place. ********");
            execution.setVariable("hasPI", false);
        } else {
            if (maxPerBatch == null || maxPerBatch == 0) {
                // use default value of 1000
                maxPerBatch = 1000;
                execution.setVariable("maxPerBatch", maxPerBatch);
            }
            if (skipIoMappings == null) {
                // default to true
                skipIoMappings = true;
                execution.setVariable("skipIoMappings", true);
            }
            if (skipCustomListeners == null) {
                // default to true
                skipCustomListeners = true;
                execution.setVariable("skipCustomListeners", true);
            }

            // query to get the process definition metadata
            ProcessDefinition v1def = processEngine.getRepositoryService()
                    .createProcessDefinitionQuery()
                    .processDefinitionKey(processDefKey)
                    .processDefinitionVersion(fromVersion)
                    .singleResult();

            // check for process instances eligible for migration
            ProcessInstanceQuery query =
                    processEngine.getRuntimeService()
                            .createProcessInstanceQuery()
                            .processDefinitionId(v1def.getId());

            List<ProcessInstance> piList = query.list();

            if (piList != null && piList.size() > 0) {
                LOGGER.info("********** " + piList.size() + " process instances found for process def "
                        + processDefKey
                        + " v" + fromVersion + ".  Migrating to version " + toVersion + "....");
                hasPI = true;
            } else {
                LOGGER.info("********** No process instances found.  Migration process ending.");
            }

            execution.setVariable("hasPI", hasPI);
        }
    }
}
