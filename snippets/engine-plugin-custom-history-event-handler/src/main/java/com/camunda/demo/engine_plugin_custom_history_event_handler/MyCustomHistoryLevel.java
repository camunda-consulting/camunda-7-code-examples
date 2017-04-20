package com.camunda.demo.engine_plugin_custom_history_event_handler;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.history.AbstractHistoryLevel;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.history.event.HistoryEventType;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;

public class MyCustomHistoryLevel extends AbstractHistoryLevel implements HistoryLevel {

	public static final MyCustomHistoryLevel INSTANCE = new MyCustomHistoryLevel();

	private static List<HistoryEventType> eventTypes = new ArrayList<HistoryEventType>();

	static{
		eventTypes.add(HistoryEventTypes.PROCESS_INSTANCE_START);
		eventTypes.add(HistoryEventTypes.PROCESS_INSTANCE_END);
	}

	public static HistoryLevel getInstance() {
		return INSTANCE;
	}

	@Override
	public int getId() {
		return 222;
	}

	@Override
	public String getName() {
		return "my-awesome-history-level";
	}

	@Override
	public boolean isHistoryEventProduced(HistoryEventType eventType, Object entity) {

		return eventTypes.contains(eventType);

	}

}
