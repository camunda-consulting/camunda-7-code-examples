package com.camunda.demo.engine_plugin_custom_history_event_producer;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.history.HistoryLevel;

public class MySampleProcessEnginePlugin implements ProcessEnginePlugin {

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

		List<HistoryLevel> customHistoryLevels = processEngineConfiguration.getCustomHistoryLevels();
		if (customHistoryLevels == null) {
			customHistoryLevels = new ArrayList<HistoryLevel>();
			processEngineConfiguration.setCustomHistoryLevels(customHistoryLevels);
		}
		customHistoryLevels.add(MyCustomHistoryLevel.getInstance());

		processEngineConfiguration.setHistoryEventProducer(new MyHistoryEventProducer());
		processEngineConfiguration.setHistoryEventHandler(new MyCustomHistoryEventHandler());
	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {

	}

}
