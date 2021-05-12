package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import java.util.Date;
import java.util.Optional;

import org.camunda.bpm.engine.impl.history.DefaultHistoryRemovalTimeProvider;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy.DynamicRemovalTimeCalculationStrategy;

/**
 * The provider holds the strategy to determine the history time to live
 * dynamically
 * 
 * @author jonathanlukas
 *
 */
public class DynamicHistoryRemovalTimeProvider extends DefaultHistoryRemovalTimeProvider
{
	private Optional<DynamicRemovalTimeCalculationStrategy> strategy = Optional.empty();

	@Override
	public Date calculateRemovalTime(
		HistoricProcessInstanceEventEntity historicRootProcessInstance,
		ProcessDefinition processDefinition)
	{
		if (this.strategy.isPresent())
		{
			Integer historyTimeToLive = this.strategy
				.get()
				.apply(historicRootProcessInstance, processDefinition);
			if (historyTimeToLive != null)
			{
				Date startTime = historicRootProcessInstance.getStartTime();
				return determineRemovalTime(startTime, historyTimeToLive);
			}
		}
		return super.calculateRemovalTime(historicRootProcessInstance, processDefinition);
	}

	public void setDynamicRemovalTimeCalculationStrategy(DynamicRemovalTimeCalculationStrategy strategy)
	{
		this.strategy = Optional.of(strategy);
	}
}
