package com.camunda.demo.versicherungsneuantrag.adapter;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

@Named
public class IssuePolicyAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Neuantrag antrag = (Neuantrag) execution.getVariable(ProcessVariables.VAR_NAME_neuantrag);
    
    antrag.setBeitragInCent(21300);
    antrag.setVertragsnummer(String.valueOf(System.currentTimeMillis()));
    
    execution.setVariable(ProcessVariables.VAR_NAME_neuantrag, antrag);
  }

}
