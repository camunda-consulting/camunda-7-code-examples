package org.camunda.bpm.unittest;

import java.util.logging.Logger;

import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.AsyncContinuationJobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

public class AuthenticatedAsyncContinuationJobHandler extends AsyncContinuationJobHandler {
  
  private static final Logger log = Logger.getLogger(AuthenticatedAsyncContinuationJobHandler.class.getName());

  @Override
  public void execute(String configuration, ExecutionEntity execution, CommandContext commandContext) {
    HistoricProcessInstance historicProcessInstance = execution
        .getProcessEngineServices()
        .getHistoryService()
        .createHistoricProcessInstanceQuery()
        .processInstanceId(execution.getProcessInstanceId())
        .singleResult();
    String startUserId = historicProcessInstance.getStartUserId();
    if (startUserId != null) {
      execution.getProcessEngineServices().getIdentityService().setAuthenticatedUserId(startUserId);
      log.info("Continue with authenticatedUserId: " + startUserId);
    } else {
      log.info("No startUserId, no authentication");
    }
    super.execute(configuration, execution, commandContext);
  }

}
