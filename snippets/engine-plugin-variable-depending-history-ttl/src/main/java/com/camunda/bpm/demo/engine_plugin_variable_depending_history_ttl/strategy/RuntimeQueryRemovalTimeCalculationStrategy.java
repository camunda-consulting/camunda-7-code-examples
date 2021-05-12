package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy;

import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * This strategy performs a runtime query and searches for the variable in the
 * process instance variables
 * 
 * @author jonathanlukas
 *
 */
public class RuntimeQueryRemovalTimeCalculationStrategy implements DynamicRemovalTimeCalculationStrategy
{
	private final ProcessEngine processEngine;

	public RuntimeQueryRemovalTimeCalculationStrategy(ProcessEngine processEngine)
	{
		this.processEngine = processEngine;
	}

	@Override
	public Integer apply(HistoricProcessInstanceEventEntity t, ProcessDefinition u)
	{
		return this.findInRuntime(t);
	}

	private Integer findInRuntime(HistoricProcessInstanceEventEntity historicRootProcessInstance)
	{
		Map<String, Object> variables = this.processEngine
			.getRuntimeService()
			.getVariables(historicRootProcessInstance.getProcessInstanceId());
		if (variables.isEmpty() || (variables.size() > 1))
		{
			return null;
		}
		return (Integer) variables.get("historyTimeToLive");
	}

}
