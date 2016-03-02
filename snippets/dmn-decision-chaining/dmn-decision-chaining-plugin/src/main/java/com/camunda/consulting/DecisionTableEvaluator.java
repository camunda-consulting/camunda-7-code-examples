package com.camunda.consulting;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.deploy.DeploymentCache;
import org.camunda.bpm.engine.variable.context.VariableContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecisionTableEvaluator {

	private final static Logger LOG = LoggerFactory.getLogger(DecisionTableEvaluator.class);

	public static Object singleResult(String decisionKey, VariableContext variables) {
		return evaluate(decisionKey, variables).getFirstResult().getSingleEntry();
	}
	
	/**
	 * ${evaluateDecision(decisionKey, variableContext).getFirstResult().get(resultVariableName)}
	 */
	public static DmnDecisionTableResult evaluate(String decisionKey, VariableContext variables) {
//		ProcessEngineImpl processEngine = Context.getProcessEngineConfiguration().getProcessEngine();
		// Do it "the internal way" to be able to use VariableContext directly
		final DmnEngine dmnEngine = Context.getProcessEngineConfiguration().getDmnEngine();
		DeploymentCache deploymentCache = Context.getProcessEngineConfiguration().getDeploymentCache();
		DmnDecision decisionDefinition = (DmnDecision) deploymentCache.findDeployedLatestDecisionDefinitionByKey(decisionKey);
		LOG.info("Evaluate chained decision: {} ", decisionDefinition);
		return dmnEngine.evaluateDecisionTable(decisionDefinition, variables);
	}

}
