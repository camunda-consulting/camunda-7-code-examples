package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaAppConfig
{
	@Bean
	public ProcessEnginePlugin getPlugin()
	{
		return new ProcessVariableDependentHistoryRemovalTimeProcessEnginePlugin();
	}
}
