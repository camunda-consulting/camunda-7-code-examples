package org.camunda.bpm.example.invoice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.CaseExecution;

public class ActivateCaseTask  {
	@Inject
	private ProcessEngine engine;
	
	void execute(CaseExecution execution) {
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("uploadInvoice", true);
		engine.getCaseService().manuallyStartCaseExecution(execution.getCaseInstanceId(),variables);
	}
}
