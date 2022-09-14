package com.camunda.consulting.mi_error_handling.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("FlagEntry")
public class FlagEntryDelegate implements JavaDelegate {
  public static final String LOOP_ELEMENT_VARIABLE = "item";
  private final static Logger logger = LoggerFactory.getLogger(FlagEntryDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String item = (String) execution.getVariable(LOOP_ELEMENT_VARIABLE);
    logger.info("Flagging the item {} due to a business error", item);
  }
}
