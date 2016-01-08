package com.camunda.demo.dmntraining.taskrouting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.history.HistoricDecisionInputInstance;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class CaptureRelevantHistoricItemsListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		HashMap<String, Object> decisionDetails = new HashMap<String, Object>();
		
		// find decision flow instance (do not use singleResult in case you ran through it multiple times because of Token Move)	
		HistoricProcessInstance decisionFlowPI = execution.getProcessEngineServices().getHistoryService().createHistoricProcessInstanceQuery() //
				.superProcessInstanceId(execution.getProcessInstanceId()) //
				.orderByProcessInstanceEndTime().desc().list().get(0);
		decisionDetails.put("decisionFlowId", decisionFlowPI.getId());
		decisionDetails.put("selectedEmployee", execution.getVariable("selectedEmployee"));
		
		// find DMN decisions
		HistoricDecisionInstance determineSkillsDecision = execution.getProcessEngineServices().getHistoryService().createHistoricDecisionInstanceQuery().processInstanceId(decisionFlowPI.getId()).decisionDefinitionKey(BpmConstants.DECISION_DEFINITION_KEY_notwendigeKompetenz).includeOutputs().orderByEvaluationTime().desc().list().get(0);		
		decisionDetails.put("determineSkillsId", determineSkillsDecision.getId());
		Collection<String> skillOutputs = new HashSet<String>();
		for (HistoricDecisionOutputInstance output : determineSkillsDecision.getOutputs()) {
			skillOutputs.add(output.getValue().toString());
		}
		decisionDetails.put("determineSkillsResult", skillOutputs);
		
		List<HistoricDecisionInstance> list = execution.getProcessEngineServices().getHistoryService() //
				.createHistoricDecisionInstanceQuery() //
				.processInstanceId(decisionFlowPI.getId()) //
				.decisionDefinitionKey(BpmConstants.DECISION_DEFINITION_KEY_mitarbeiterScore) //
				.includeInputs() //
				.includeOutputs() //
				.orderByEvaluationTime().desc() //
				.list();
		List<Map<String, Object>> scoreEmployees = new ArrayList<Map<String, Object>>();
		
		for (HistoricDecisionInstance decision : list) {
			HashMap<String, Object> decisionResult = new HashMap<String, Object>();
			decisionResult.put("id", decision.getId());
			decisionResult.put("result", String.valueOf(decision.getCollectResultValue()));
			for (HistoricDecisionInputInstance input : decision.getInputs()) {
				if ("EmployeeName".equals(input.getClauseId())) {					
					decisionResult.put("employee", input.getValue().toString());
				}
			}
			scoreEmployees.add(decisionResult);
		}
		
		decisionDetails.put("scoreEmployee", scoreEmployees);
		
		execution.setVariable("decisionDetails", Variables.objectValue(decisionDetails).serializationDataFormat(SerializationDataFormats.JSON).create());
	}

}
