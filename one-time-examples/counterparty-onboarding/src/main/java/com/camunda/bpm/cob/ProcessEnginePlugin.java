package com.camunda.bpm.cob;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

public class ProcessEnginePlugin extends AbstractProcessEnginePlugin {
	
	private static final Logger log = Logger.getLogger(ProcessEnginePlugin.class.getName());

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		List<BpmnParseListener> preParseListeners = processEngineConfiguration.getCustomPreBPMNParseListeners();
		if(preParseListeners == null) {
			preParseListeners = new ArrayList<BpmnParseListener>();
			processEngineConfiguration.setCustomPreBPMNParseListeners(preParseListeners);
		}
		preParseListeners.add(new TaskBpmnParseListener());
		
		log.info("set default serialzation to JSON");
		processEngineConfiguration.setDefaultSerializationFormat("application/json");
		
		log.info("default serialization format now " + processEngineConfiguration.getDefaultSerializationFormat());
	}
}
