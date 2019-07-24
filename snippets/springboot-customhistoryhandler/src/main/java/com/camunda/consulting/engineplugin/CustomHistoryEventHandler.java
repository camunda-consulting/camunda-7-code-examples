package com.camunda.consulting.engineplugin;

import org.camunda.bpm.engine.ActivityTypes;
import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricExternalTaskLogEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomHistoryEventHandler implements HistoryEventHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomHistoryEventHandler.class);

  @Override
  @EventListener
  public void handleEvent(HistoryEvent historyEvent) {

    if (historyEvent instanceof HistoricActivityInstanceEventEntity) {
      HistoricActivityInstanceEventEntity activityInstanceEventEntity =
        (HistoricActivityInstanceEventEntity) historyEvent;

      if (historyEvent.getEventType().equals(HistoryEventTypes.ACTIVITY_INSTANCE_START.getEventName()) || historyEvent.getEventType()
        .equals(HistoryEventTypes.ACTIVITY_INSTANCE_END.getEventName())) {

        List<String> relevantActivityTypes = new ArrayList<>();
        relevantActivityTypes.add(ActivityTypes.TASK_USER_TASK);
        relevantActivityTypes.add(ActivityTypes.INTERMEDIATE_EVENT_MESSAGE);
        relevantActivityTypes.add(ActivityTypes.INTERMEDIATE_EVENT_TIMER);

        if (relevantActivityTypes.contains(activityInstanceEventEntity.getActivityType())) {
          log.info("Received <" + historyEvent.getEventType() + "> event for <" + activityInstanceEventEntity.getActivityType() + "> with activityId <" + activityInstanceEventEntity
            .getActivityId() + ">");
        }
      }
    } else if (historyEvent instanceof HistoricExternalTaskLogEntity) {
      HistoricExternalTaskLogEntity externalTaskLogEvent = (HistoricExternalTaskLogEntity) historyEvent;

      log.info("Received <" + historyEvent.getEventType() + "> event for external task with activityId <" + externalTaskLogEvent
        .getActivityId() + ">");
    }
  }

  @Override
  public void handleEvents(List<HistoryEvent> historyEvents) {
    for (HistoryEvent historyEvent : historyEvents) {
      handleEvent(historyEvent);
    }
  }
}
