package org.camunda.bpm.example.cockpit.plugin.bpmncollaboration;


import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Does nothing (but small logging)
 * 
 * @author ruecker
 */
public class NoopDelegate implements JavaDelegate {
  
  private Logger log = Logger.getLogger(NoopDelegate.class.getName());

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.warning("NOOP triggered in activity " + execution.getCurrentActivityName());

  }

}
