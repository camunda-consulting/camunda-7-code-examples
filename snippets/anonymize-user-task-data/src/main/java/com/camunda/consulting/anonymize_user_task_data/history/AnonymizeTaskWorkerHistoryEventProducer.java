package com.camunda.consulting.anonymize_user_task_data.history;

import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.impl.history.event.HistoricTaskInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEventType;
import org.camunda.bpm.engine.impl.history.event.UserOperationLogEntryEventEntity;
import org.camunda.bpm.engine.impl.history.producer.DefaultHistoryEventProducer;
import org.camunda.bpm.engine.impl.history.producer.HistoryEventProducer;
import org.camunda.bpm.engine.impl.oplog.UserOperationLogContext;
import org.camunda.bpm.engine.impl.oplog.UserOperationLogContextEntry;
import org.camunda.bpm.engine.impl.persistence.entity.PropertyChange;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnonymizeTaskWorkerHistoryEventProducer extends DefaultHistoryEventProducer  implements HistoryEventProducer {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(AnonymizeTaskWorkerHistoryEventProducer.class);

  public AnonymizeTaskWorkerHistoryEventProducer() {
    super();
  }

  @Override
  protected void initUserOperationLogEvent(UserOperationLogEntryEventEntity evt, UserOperationLogContext context, UserOperationLogContextEntry contextEntry,
      PropertyChange propertyChange) {

    super.initUserOperationLogEvent(evt, context, contextEntry, propertyChange);
    if (UserOperationLogEntry.CATEGORY_TASK_WORKER.equals(contextEntry.getCategory())) {
      LOGGER.debug("Anonymize UserOperation Event {}", evt);
       evt.setUserId(null);
      if (evt.getProperty().equals(TaskEntity.ASSIGNEE)) {
        LOGGER.debug("anonymizing assignee");
        evt.setOrgValue(null);
        evt.setNewValue(null);
      }
    }
  }

  @Override
  protected void initTaskInstanceEvent(HistoricTaskInstanceEventEntity evt, TaskEntity taskEntity, HistoryEventType eventType) {
    super.initTaskInstanceEvent(evt, taskEntity, eventType);
    LOGGER.debug("Anonymize TaskInstance Event {}", evt);
    evt.setAssignee(null);
  }
}