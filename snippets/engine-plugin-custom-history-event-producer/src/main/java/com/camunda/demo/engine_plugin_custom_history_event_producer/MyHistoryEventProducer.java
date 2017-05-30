package com.camunda.demo.engine_plugin_custom_history_event_producer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.producer.CacheAwareHistoryEventProducer;
import org.camunda.bpm.engine.impl.history.producer.HistoryEventProducer;

public class MyHistoryEventProducer extends CacheAwareHistoryEventProducer implements HistoryEventProducer {
private static final MyHistoryEventProducer INSTANCE = new MyHistoryEventProducer();
	
	public static MyHistoryEventProducer getInstance(){
		return INSTANCE;
	}
	
	@Override
	public HistoryEvent createActivityInstanceStartEvt(DelegateExecution execution) {
		HistoricActivityInstanceEventEntity event = (HistoricActivityInstanceEventEntity) super.createActivityInstanceStartEvt(execution);
		if (event.getActivityType().equals("intermediateMessageCatch")) {
			event.setTaskAssignee(Context.getCommandContext().getAuthenticatedUserId());
		}
		
		return event;
	}
	@Override
	public HistoryEvent createActivityInstanceEndEvt(DelegateExecution execution) {
		HistoricActivityInstanceEventEntity event = (HistoricActivityInstanceEventEntity) super.createActivityInstanceEndEvt(execution);
		if (event.getActivityType().equals("intermediateMessageCatch")) {
			event.setTaskAssignee(Context.getCommandContext().getAuthenticatedUserId());
		}
		return event;
	}
}
