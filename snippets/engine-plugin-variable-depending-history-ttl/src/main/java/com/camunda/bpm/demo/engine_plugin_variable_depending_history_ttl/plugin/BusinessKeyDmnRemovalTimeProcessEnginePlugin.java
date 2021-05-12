package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.plugin;

import com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy.BusinessKeyDmnRemovalTimeCalculationStrategy;

public class BusinessKeyDmnRemovalTimeProcessEnginePlugin extends DynamicHistoryRemovalTimeProcessEnginePlugin
{

	public BusinessKeyDmnRemovalTimeProcessEnginePlugin()
	{
		super(BusinessKeyDmnRemovalTimeCalculationStrategy::new);
	}

}
