package com.camunda.demo.engine_plugin_custom_history_event_handler;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.history.handler.CompositeDbHistoryEventHandler;
import org.camunda.bpm.engine.impl.history.handler.CompositeHistoryEventHandler;

public class MySampleProcessEnginePlugin implements ProcessEnginePlugin {

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

		List<HistoryLevel> customHistoryLevels = processEngineConfiguration.getCustomHistoryLevels();
		if (customHistoryLevels == null) {
			customHistoryLevels = new ArrayList<HistoryLevel>();
			processEngineConfiguration.setCustomHistoryLevels(customHistoryLevels);
		}
		customHistoryLevels.add(MyCustomHistoryLevel.getInstance());


		processEngineConfiguration.setHistoryEventHandler(new CompositeDbHistoryEventHandler(new MyCustomHistoryEventHandler()));


		//processEngineConfiguration.setHistoryEventHandler(MyCustomHistoryEventHandler.getInstance());

	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {

	}

}
