package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class ProcessVariableDependentHistoryRemovalTimeProcessEnginePlugin extends AbstractProcessEnginePlugin
{
	private final ProcessVariableDependentHistoryRemovalTimeProvider p = new ProcessVariableDependentHistoryRemovalTimeProvider();

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration)
	{
		processEngineConfiguration.setHistoryRemovalTimeProvider(this.p);
	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine)
	{
		this.p.setProcessEngine(processEngine);
	}
}
