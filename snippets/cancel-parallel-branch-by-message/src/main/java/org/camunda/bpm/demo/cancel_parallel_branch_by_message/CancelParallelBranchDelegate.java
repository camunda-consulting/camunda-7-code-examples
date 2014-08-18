package org.camunda.bpm.demo.cancel_parallel_branch_by_message;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CancelParallelBranchDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		new MessageCorrelationService().correlate(execution, "cancel");
	}

}
