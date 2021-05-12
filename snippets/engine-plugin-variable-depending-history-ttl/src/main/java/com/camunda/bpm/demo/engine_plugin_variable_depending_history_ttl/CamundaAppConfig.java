package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.plugin.RuntimeQueryRemovalTimeProcessEnginePlugin;

@Configuration
public class CamundaAppConfig
{
	@Bean
	public ProcessEnginePlugin getPlugin()
	{
		// return the plugin you want to use here
		return new RuntimeQueryRemovalTimeProcessEnginePlugin();
	}
}
