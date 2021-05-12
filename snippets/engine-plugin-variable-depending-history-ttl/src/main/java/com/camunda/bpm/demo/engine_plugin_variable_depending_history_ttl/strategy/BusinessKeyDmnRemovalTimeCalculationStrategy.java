package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy;

import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * This strategy finds a history time to live depending on the business key
 * using a dmn table
 * 
 * @author jonathanlukas
 *
 */
public class BusinessKeyDmnRemovalTimeCalculationStrategy implements DynamicRemovalTimeCalculationStrategy
{
	private final ProcessEngine processEngine;

	public BusinessKeyDmnRemovalTimeCalculationStrategy(ProcessEngine processEngine)
	{
		this.processEngine = processEngine;
	}

	@Override
	public Integer apply(HistoricProcessInstanceEventEntity t, ProcessDefinition u)
	{
		return this.processEngine
			.getDecisionService()
			.evaluateDecisionTableByKey("historyTtlRules", Map.of("businessKey", t.getBusinessKey()))
			.getSingleEntry();
	}

}
