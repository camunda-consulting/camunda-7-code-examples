package com.camunda.consulting.history.changeHistoryOutput;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.producer.CacheAwareHistoryEventProducer;
import org.camunda.bpm.engine.impl.persistence.entity.VariableInstanceEntity;

/**
 * @author Ingo Richtsmeier
 *
 */
public class FilterVariableHistoryEventProducer extends CacheAwareHistoryEventProducer {
  
  private List<String> historyVariableNames;
  
  private static final Logger log = Logger.getLogger(FilterVariableHistoryEventProducer.class.getName());
  
  public FilterVariableHistoryEventProducer(List<String> historyVariableNameList) {
    log.info("reading history variable names from properties");
    if (historyVariableNameList != null) {
      historyVariableNames = historyVariableNameList;
    } 
  }

  @Override
  public HistoryEvent createHistoricVariableCreateEvt(VariableInstanceEntity variableInstance, VariableScope sourceVariableScope) {
    // check if variable is in the list
    if (historyVariableNames != null 
        && historyVariableNames.contains(variableInstance.getName())) {
      return super.createHistoricVariableCreateEvt(variableInstance, sourceVariableScope);
    } else {
      return emtpyHistoryEvent();
    }
  }

  @Override
  public HistoryEvent createHistoricVariableDeleteEvt(VariableInstanceEntity variableInstance, VariableScope sourceVariableScope) {
    if(historyVariableNames != null 
        && historyVariableNames.contains(variableInstance.getName())) {
      return super.createHistoricVariableDeleteEvt(variableInstance, sourceVariableScope);
    } else {
      return emtpyHistoryEvent();
    }
  }

  @Override
  public HistoryEvent createHistoricVariableUpdateEvt(VariableInstanceEntity variableInstance, VariableScope sourceVariableScope) {
    if (historyVariableNames != null
        && historyVariableNames.contains(variableInstance.getName())) {
      return super.createHistoricVariableUpdateEvt(variableInstance, sourceVariableScope);
    } else {
      return emtpyHistoryEvent();
    }
  }

  private HistoryEvent emtpyHistoryEvent() {
    log.fine("No History Variables should be saved");
    return null;
  }
  
}
