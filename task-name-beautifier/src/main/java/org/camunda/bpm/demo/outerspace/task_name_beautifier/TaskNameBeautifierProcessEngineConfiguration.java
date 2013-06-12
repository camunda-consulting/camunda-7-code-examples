package org.camunda.bpm.demo.outerspace.task_name_beautifier;

import java.util.ArrayList;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.container.impl.jboss.config.ManagedJtaProcessEngineConfiguration;

/**
 * {@link ProcessEngineConfiguration} that customizes the BPMN Parser with a
 * {@link BpmnParseListener} that enhances all user tasks with a
 * {@link TaskListener} that removes hyphens from {@link Task} names.
 * 
 * @author Falko Menge (camunda)
 */
public class TaskNameBeautifierProcessEngineConfiguration extends
ManagedJtaProcessEngineConfiguration {

	@Override
	protected void init() {
		initCustomPostParseListener();
		super.init();
	}

	protected void initCustomPostParseListener() {
		// normally no parse listeners should be set, so create an own list for it
		if (getCustomPostBPMNParseListeners() == null) {
			setCustomPostBPMNParseListeners(new ArrayList<BpmnParseListener>());
		}

		// add parse listener
		getCustomPostBPMNParseListeners().add(new TaskNameBeautifierBpmnParseListener());

	}

}
