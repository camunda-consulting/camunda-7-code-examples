package com.camunda.consulting.engineplugin;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.history.handler.CompositeDbHistoryEventHandler;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(Ordering.DEFAULT_ORDER + 1)
public class CustomHistoryEventHandlerPlugin implements ProcessEnginePlugin {

	private CustomHistoryEventHandler customHistoryEventHandler;

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		customHistoryEventHandler = new CustomHistoryEventHandler();
		processEngineConfiguration
				.setHistoryEventHandler(new CompositeDbHistoryEventHandler(customHistoryEventHandler));
	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {

	}
}
