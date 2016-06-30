package com.camunda.demo.task.escalation;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.task.TaskDefinition;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class AddWatchDuedateParseListener extends AbstractBpmnParseListener {
	
	private static final Logger log = Logger.getLogger(AddWatchDuedateParseListener.class.getName());

	@Override
	public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
		log.info("register " + DeleteWatchDuedateJobTaskListener.class.getName() + " on activity " + activity.getId());
			
		TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activity.getActivityBehavior()).getTaskDefinition();
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, new CreateWatchDuedateJobTaskListener());
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_COMPLETE, new DeleteWatchDuedateJobTaskListener());		
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_DELETE, new DeleteWatchDuedateJobTaskListener());		
	}
}
