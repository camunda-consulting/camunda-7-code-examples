package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;

public class MtextAdapter implements JavaDelegate {
  
  private final Logger LOGGER = Logger.getLogger(MtextAdapter.class.getName());
  
  /**
   * "\\\\Police\\Vorlagen\\KFZ-Versicherung.dataBinding"
   * "\\\\Ablehnung\\Vorlagen\\Ablehnung.dataBinding"
   */
  private Expression mtextTemplate; 
  
  private MtextServiceClient mtext = new MtextServiceClient();

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String xml = (String) execution.getVariable(ProcessVariables.VAR_NAME_documentXml);
    
    String templatePath = (String) mtextTemplate.getValue(execution);
    byte[] pdf = mtext.generateAndDistributeDocument(xml, templatePath); 
    
    execution.setVariable("pdf", pdf);
  }

  public Expression getMtextTemplate() {
    return mtextTemplate;
  }

  public void setMtextTemplate(Expression mtextTemplate) {
    this.mtextTemplate = mtextTemplate;
  }

}
