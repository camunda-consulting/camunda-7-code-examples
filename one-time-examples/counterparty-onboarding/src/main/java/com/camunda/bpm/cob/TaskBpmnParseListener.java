package com.camunda.bpm.cob;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.task.TaskDefinition;
import org.camunda.bpm.engine.impl.util.xml.Element;

import com.camunda.bpm.cob.mailNotification.MailNotificationListener;
import com.camunda.bpm.cob.region.AdjustRegionCandidateTaskListener;

public class TaskBpmnParseListener extends AbstractBpmnParseListener {
	
	private static final Logger log = Logger.getLogger(TaskBpmnParseListener.class.getName());

	@Override
	public void parseUserTask(Element userTaskElement, ScopeImpl scope,
			ActivityImpl activity) {
		log.info("register EscalationTaskListener");
		TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activity.getActivityBehavior()).getTaskDefinition();
		EscalationTaskListener escalationTaskListener = new EscalationTaskListener();
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, escalationTaskListener);
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_COMPLETE, escalationTaskListener);
		
		log.info("register AdjustRegionCandidateTaskListener");
		AdjustRegionCandidateTaskListener adjustRegionCandidateTaskListener = new AdjustRegionCandidateTaskListener();
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, adjustRegionCandidateTaskListener);
		
		log.info("register MailNotificationListener");
		MailNotificationListener mailNotificationListener = new MailNotificationListener();
		taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, mailNotificationListener);
	}
}
