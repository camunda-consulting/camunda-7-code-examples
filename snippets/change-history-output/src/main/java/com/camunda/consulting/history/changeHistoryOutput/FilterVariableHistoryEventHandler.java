package com.camunda.consulting.history.changeHistoryOutput;

import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.db.DbSqlSession;
import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricVariableUpdateEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricVariableInstanceEntity;

/**
 * Process engine plugin to avoid saving variables in the history if the history level is set to full.<br>
 * And it does't save exclusive gateways in the history.
 * 
 * @author Ingo Richtsmeier
 *
 */
public class FilterVariableHistoryEventHandler extends DbHistoryEventHandler {
  
  private static final Logger log = Logger.getLogger(FilterVariableHistoryEventHandler.class.getName());

  @Override
  public void handleEvent(HistoryEvent historyEvent) {
    if (historyEvent == null) {
      log.fine("null event: nothing to save");
    } else {
      super.handleEvent(historyEvent);
    }
  }

  @Override
  protected void insertHistoricVariableUpdateEntity(HistoricVariableUpdateEventEntity historyEvent) {
    log.fine("insert historic variable update " + historyEvent.getEventType());
    DbSqlSession dbSqlSession = getDbSqlSession();
    // always insert/update HistoricProcessVariableInstance
    if(HistoryEvent.VARIABLE_EVENT_TYPE_CREATE.equals(historyEvent.getEventType())) {
      HistoricVariableInstanceEntity persistentObject = new HistoricVariableInstanceEntity(historyEvent);
      dbSqlSession.insert(persistentObject);

    } else if(HistoryEvent.VARIABLE_EVENT_TYPE_UPDATE.equals(historyEvent.getEventType())) {
      HistoricVariableInstanceEntity historicVariableInstanceEntity = dbSqlSession.selectById(HistoricVariableInstanceEntity.class, historyEvent.getVariableInstanceId());
      if(historicVariableInstanceEntity != null) {
        historicVariableInstanceEntity.updateFromEvent(historyEvent);

      } else {
        // #CAM-1344 / #SUPPORT-688
        // this is a FIX for process instances which were started in camunda fox 6.1 and migrated to camunda BPM 7.0.
        // in fox 6.1 the HistoricVariable instances were flushed to the DB when the process instance completed.
        // Since fox 6.2 we populate the HistoricVariable table as we go.
        HistoricVariableInstanceEntity persistentObject = new HistoricVariableInstanceEntity(historyEvent);
        dbSqlSession.insert(persistentObject);
      }

    } else if(HistoryEvent.VARIABLE_EVENT_TYPE_DELETE.equals(historyEvent.getEventType())) {
      HistoricVariableInstanceEntity historicVariableInstanceEntity = dbSqlSession.selectById(HistoricVariableInstanceEntity.class, historyEvent.getVariableInstanceId());
      if(historicVariableInstanceEntity != null) {
        historicVariableInstanceEntity.delete();
      }
    }
    
  }

  @Override
  protected void insertOrUpdate(HistoryEvent historyEvent) {
    if (historyEvent instanceof HistoricActivityInstanceEventEntity
        && ((HistoricActivityInstanceEventEntity) historyEvent).getActivityType().equals("exclusiveGateway")) {
      log.fine("Don't persist gateway " + ((HistoricActivityInstanceEventEntity) historyEvent).getActivityId());
    } else {
      super.insertOrUpdate(historyEvent);
    }
  }
  
  

}
