package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.plugin;

import java.util.function.Function;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

import com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.DynamicHistoryRemovalTimeProvider;
import com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy.DynamicRemovalTimeCalculationStrategy;

public abstract class DynamicHistoryRemovalTimeProcessEnginePlugin extends AbstractProcessEnginePlugin
{
	private final DynamicHistoryRemovalTimeProvider p = new DynamicHistoryRemovalTimeProvider();
	private final Function<ProcessEngine, DynamicRemovalTimeCalculationStrategy> provider;

	public DynamicHistoryRemovalTimeProcessEnginePlugin(
		Function<ProcessEngine, DynamicRemovalTimeCalculationStrategy> provider)
	{
		this.provider = provider;
	}

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration)
	{
		processEngineConfiguration.setHistoryRemovalTimeProvider(this.p);
	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine)
	{
		this.p.setDynamicRemovalTimeCalculationStrategy(this.provider.apply(processEngine));
	}
}
