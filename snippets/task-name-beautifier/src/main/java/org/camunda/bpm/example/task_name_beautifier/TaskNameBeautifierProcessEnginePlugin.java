package org.camunda.bpm.example.task_name_beautifier;

import java.util.ArrayList;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

public class TaskNameBeautifierProcessEnginePlugin implements
		ProcessEnginePlugin {

	@Override
	public void preInit(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		// normally no parse listeners should be set, so create an own list for it
		if (processEngineConfiguration.getCustomPostBPMNParseListeners() == null) {
			processEngineConfiguration.setCustomPostBPMNParseListeners(new ArrayList<BpmnParseListener>());
		}

		// add parse listener
		processEngineConfiguration.getCustomPostBPMNParseListeners().add(new TaskNameBeautifierBpmnParseListener());
	}

	@Override
	public void postInit(
			ProcessEngineConfigurationImpl processEngineConfiguration) {
	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {
	}

}
