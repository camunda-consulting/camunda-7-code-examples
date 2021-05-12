package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.plugin;

import com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy.RuntimeQueryRemovalTimeCalculationStrategy;

public class RuntimeQueryRemovalTimeProcessEnginePlugin extends DynamicHistoryRemovalTimeProcessEnginePlugin
{

	public RuntimeQueryRemovalTimeProcessEnginePlugin()
	{
		super(RuntimeQueryRemovalTimeCalculationStrategy::new);
	}

}
