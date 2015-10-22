package com.camunda.demo.gateway.or.split.items;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class SetLocalVariablesListener implements ExecutionListener {

  @SuppressWarnings("unchecked")
  @Override
  public void notify(DelegateExecution ctx) throws Exception {
    // load new approved items from the process variables and remove it there (as it is no longer needed)
    List<String> newApprovedItems = (List<String>) ctx.getVariable(ProcessVariables.VAR_NAME_NEW_APPROVED_ITEMS);
    ctx.removeVariable(ProcessVariables.VAR_NAME_NEW_APPROVED_ITEMS);
    
    // store it in a execution local variable (as we might have multiple parallel paths later on!)
    ctx.setVariableLocal(ProcessVariables.VAR_NAME_APPROVED_ITEMS, Variables.objectValue(newApprovedItems).serializationDataFormat(SerializationDataFormats.JSON).create());
    
    // It might also be a good idea to initialize variables you need later in this path
    // as you might not want to use "setVariableLocal" later on but simply
    // "setVariable". The latter searches the scopes for the variable recursively
    // and sets the variable on the first scope it finds on. Thats why we initialize it here
    ctx.setVariableLocal(ProcessVariables.VAR_NAME_HANDLING_INFO, null);
  }

}
