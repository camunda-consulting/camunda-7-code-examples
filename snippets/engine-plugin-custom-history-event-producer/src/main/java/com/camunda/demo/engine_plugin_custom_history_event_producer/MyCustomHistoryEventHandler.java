package com.camunda.demo.engine_plugin_custom_history_event_producer;

import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;


public class MyCustomHistoryEventHandler extends DbHistoryEventHandler implements HistoryEventHandler {

	private final Logger LOGGER = Logger.getLogger(MyCustomHistoryEventHandler.class.getName());

	private static final MyCustomHistoryEventHandler INSTANCE = new MyCustomHistoryEventHandler();
	
	public static MyCustomHistoryEventHandler getInstance(){
		return INSTANCE;
	}

	@Override
	public void handleEvent(HistoryEvent historyEvent) {
		LOGGER.info("----- HISTORY EVENT PRODUCED: "+ historyEvent.toString());
		super.handleEvent(historyEvent);
	}

}
