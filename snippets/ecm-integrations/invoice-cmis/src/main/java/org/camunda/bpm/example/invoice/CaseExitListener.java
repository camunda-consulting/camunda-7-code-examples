package org.camunda.bpm.example.invoice;

import java.util.List;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.runtime.CaseExecution;

public class CaseExitListener implements CaseExecutionListener {

	@Override
	public void notify(DelegateCaseExecution delegateExecution) throws Exception {
		List<CaseExecution> executions = delegateExecution.getProcessEngineServices().getCaseService().createCaseExecutionQuery().enabled().list();
		for (CaseExecution execution: executions) {
			delegateExecution.getProcessEngineServices().getCaseService().disableCaseExecution(execution.getId());
		}
	}

}
