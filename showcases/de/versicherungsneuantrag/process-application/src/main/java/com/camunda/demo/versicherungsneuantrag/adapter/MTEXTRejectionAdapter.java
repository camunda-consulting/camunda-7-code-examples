package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;

public class MTEXTRejectionAdapter implements JavaDelegate {
  
  private final Logger LOGGER = Logger.getLogger(MTEXTRejectionAdapter.class.getName());

  @Override
  public void execute(DelegateExecution execution) throws Exception {
//    Neuantrag antrag = (Neuantrag) execution.getVariable(ProcessVariables.VAR_NAME_neuantrag);
//    String xml = XML(antrag).toString();
    String xml = (String) execution.getVariable(ProcessVariables.VAR_NAME_documentXml);
    byte[] pdf = MTEXT.generateAndDistributeDocument(xml, "\\\\Ablehnung\\Vorlagen\\Ablehnung.dataBinding");
    execution.setVariable("pdf", pdf);
  }
}
