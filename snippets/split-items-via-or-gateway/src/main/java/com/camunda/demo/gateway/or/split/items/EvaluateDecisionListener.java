package com.camunda.demo.gateway.or.split.items;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class EvaluateDecisionListener implements ExecutionListener {

  @SuppressWarnings("unchecked")
  @Override
  public void notify(DelegateExecution ctx) throws Exception {
    // remember already processed decisions
    List<String> processedItems = (List<String>) ctx.getVariable(ProcessVariables.VAR_NAME_PROCESSED_ITEMS);
    if (processedItems==null) {
      // This is the first time we run through this listener
      processedItems = new ArrayList<String>();
    }
    
    // load order
    Order order = (Order) ctx.getVariable(ProcessVariables.VAR_NAME_ORDER);
    
    // check decisions made in this round
    List<String> newApprovedItems = new ArrayList<String>();
    boolean undecidedItemsExist = false;
    
    for (Item item : order.getItems()) {
      if (!processedItems.contains(item.getId()) && item.getDecision()!=null) {
        // A decision is set and it was not made in an earlier round -> new decided item!
        if (item.getDecision()) {
          // item was approved
          newApprovedItems.add(item.getId());
        } else {
          // item was rejected
        }
        processedItems.add(item.getId());
      } else if (item.getDecision()==null) {
        // still undecided
        undecidedItemsExist = true;
      }
    }
    
    ctx.setVariable(ProcessVariables.VAR_NAME_PROCESSED_ITEMS, Variables.objectValue(processedItems).serializationDataFormat(SerializationDataFormats.JSON).create());
    ctx.setVariable(ProcessVariables.VAR_NAME_UNDECIDED_ITEMS, Variables.objectValue(undecidedItemsExist).serializationDataFormat(SerializationDataFormats.JSON).create());
    ctx.setVariable(ProcessVariables.VAR_NAME_NEW_APPROVED_ITEMS, Variables.objectValue(newApprovedItems).serializationDataFormat(SerializationDataFormats.JSON).create());
  }

}
