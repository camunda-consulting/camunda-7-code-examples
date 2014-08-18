package org.camunda.bpm.demo.cancel_parallel_branch_by_message;

import org.apache.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;


public class MessageCorrelationService {

	private static final Logger log = Logger.getLogger(MessageCorrelationService.class);

	public void correlate(DelegateExecution ctx, String correlationKey) {

		RuntimeService runtimeService = ctx.getProcessEngineServices().getRuntimeService();

		long waitingProcessInstances = runtimeService
				.createExecutionQuery().messageEventSubscriptionName(correlationKey)
				.processInstanceId(ctx.getProcessInstanceId()).count();


		// if no one is waiting - do not trigger correlation because of https://app.camunda.com/jira/browse/CAM-2537
		if (waitingProcessInstances >= 1) {

			log.info("correlating [" + waitingProcessInstances + "] instances with correlationKey["
					+ correlationKey + "] and processInstanceId[" + ctx.getProcessInstanceId()
					+ "]");

			// >1 should not happen in the current process model (but maybe the process model will be changed in future as this is incredibly simple with BPMN :-)
			runtimeService.createMessageCorrelation(
					correlationKey).processInstanceId(ctx.getProcessInstanceId()).correlate();
		} else {
			if (log.isDebugEnabled()) {
				log.debug("no message for correlationKey[" + correlationKey
						+ "] and processInstanceId[" + ctx.getProcessInstanceId()
						+ "] to correlate...");
			}
		}

	}
}
