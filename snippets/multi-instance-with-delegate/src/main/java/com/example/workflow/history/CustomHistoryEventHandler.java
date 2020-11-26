package com.example.workflow.history;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoricDecisionEvaluationEvent;
import org.camunda.bpm.engine.impl.history.event.HistoricDetailEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricVariableUpdateEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.springframework.stereotype.Component;

@Slf4j
public class CustomHistoryEventHandler extends DbHistoryEventHandler {

    public void handleEvent(HistoryEvent historyEvent) {

        if (historyEvent instanceof HistoricVariableUpdateEventEntity) {
            HistoricVariableUpdateEventEntity historicVariableUpdateEventEntity = (HistoricVariableUpdateEventEntity) historyEvent;
            if (historicVariableUpdateEventEntity.getVariableName().equalsIgnoreCase("createdInstancesCount")) {
                return;
            }
        }
        super.handleEvent(historyEvent);
    }

}
