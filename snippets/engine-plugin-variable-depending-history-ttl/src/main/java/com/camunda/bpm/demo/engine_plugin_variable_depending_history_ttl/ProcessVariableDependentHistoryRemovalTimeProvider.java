package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.impl.history.DefaultHistoryRemovalTimeProvider;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

public class ProcessVariableDependentHistoryRemovalTimeProvider extends DefaultHistoryRemovalTimeProvider
{
	private Optional<ProcessEngine> processEngine = Optional.empty();

	@Override
	public Date calculateRemovalTime(
		HistoricProcessInstanceEventEntity historicRootProcessInstance,
		ProcessDefinition processDefinition)
	{
		Integer historyTimeToLive = this.determineHistoryTimeToLiveFromVariable(historicRootProcessInstance);
		if (historyTimeToLive != null)
		{
			Date startTime = historicRootProcessInstance.getStartTime();
			return determineRemovalTime(startTime, historyTimeToLive);
		}
		return super.calculateRemovalTime(historicRootProcessInstance, processDefinition);
	}

	private Integer determineHistoryTimeToLiveFromVariable(
		HistoricProcessInstanceEventEntity historicRootProcessInstance)
	{
		if (this.processEngine.isPresent())
		{
			Integer findInHistory = this.findInHistory(historicRootProcessInstance);
			Integer findInRuntime = this.findInRuntime(historicRootProcessInstance);
			if (findInHistory == null)
			{
				return findInRuntime;
			}
			if (findInRuntime == null)
			{
				return findInHistory;
			}
		}
		return null;
	}

	private Integer findInHistory(HistoricProcessInstanceEventEntity historicRootProcessInstance)
	{
		List<HistoricVariableInstance> list = this.processEngine
			.get()
			.getHistoryService()
			.createHistoricVariableInstanceQuery()
			.processInstanceId(historicRootProcessInstance.getProcessInstanceId())
			.variableName("historyTimeToLive")
			.list();
		if (list.isEmpty() || (list.size() > 1))
		{
			return null;
		}
		HistoricVariableInstance variableInstance = list.get(0);
		return (Integer) variableInstance.getValue();
	}

	private Integer findInRuntime(HistoricProcessInstanceEventEntity historicRootProcessInstance)
	{
		Map<String, Object> variables = this.processEngine
			.get()
			.getRuntimeService()
			.getVariables(historicRootProcessInstance.getProcessInstanceId());
		if (variables.isEmpty() || (variables.size() > 1))
		{
			return null;
		}
		return (Integer) variables.get("historyTimeToLive");
	}

	public void setProcessEngine(ProcessEngine processEngine)
	{
		this.processEngine = Optional.of(processEngine);
	}
}
