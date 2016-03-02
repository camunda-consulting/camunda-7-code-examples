package com.camunda.demo.task.escalation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;

public class WatchUserTaskDueDateProcessEnginePlugin extends AbstractProcessEnginePlugin {
	
	private static final Logger log = Logger.getLogger(WatchUserTaskDueDateProcessEnginePlugin.class.getName());

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		// Add Listener to create/delete Jobs
		if(processEngineConfiguration.getCustomPreBPMNParseListeners() == null) {
			processEngineConfiguration.setCustomPreBPMNParseListeners(new ArrayList<BpmnParseListener>());
		}
		processEngineConfiguration.getCustomPreBPMNParseListeners().add(new AddWatchDuedateParseListener());		

		log.info(this.getClass().getName() + " parse listeners added");
	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		// Add Job Handler to execute the Jobs
		if (processEngineConfiguration.getJobHandlers()==null) {
			processEngineConfiguration.setJobHandlers(new HashMap<String, JobHandler>());
		}
		processEngineConfiguration.getJobHandlers().put(UserTaskOverdueJobHandler.USER_TASK_ESCALATION_JOB_HANDLER_TYPE, new UserTaskOverdueJobHandler());
		
		log.info(this.getClass().getName() + " job handler added");
	}
}
