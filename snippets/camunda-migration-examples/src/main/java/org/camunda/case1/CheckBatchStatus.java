package org.camunda.case1;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.batch.BatchStatistics;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("case1CheckStatus")
public class CheckBatchStatus implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(CheckBatchStatus.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        ProcessEngine processEngine = execution.getProcessEngine();
        boolean isComplete = false;

        String batchId = (String) execution.getVariable("batchId");

        LOGGER.info("CheckBatchStatus.execute():  batchId = " + batchId);

        BatchStatistics statistics =
                processEngine.getManagementService()
                        .createBatchStatisticsQuery()
                        .batchId(batchId)
                        .singleResult();

        if (statistics == null || statistics.getRemainingJobs() == 0) {
            LOGGER.info("  BATCH IS COMPLETE...");
            isComplete = true;
        } else {
            LOGGER.info("  BATCH STILL RUNNING......");
        }

        execution.setVariable("isComplete", isComplete);

    }
}
