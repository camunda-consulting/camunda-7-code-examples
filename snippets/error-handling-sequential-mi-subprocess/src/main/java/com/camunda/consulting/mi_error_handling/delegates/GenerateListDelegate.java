package com.camunda.consulting.mi_error_handling.delegates;

import camundajar.impl.scala.Int;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("GenerateList")
public class GenerateListDelegate implements JavaDelegate {
  public static final String FAILURE_INDEX_VARIABLE_NAME = "failureIndex";
  public static final String NUMBER_OF_ITEMS_VARIABLE_NAME = "numberOfItems";
  private final static Logger logger = LoggerFactory.getLogger(GenerateListDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    int nbrOfItems = (Integer) execution.getVariable(NUMBER_OF_ITEMS_VARIABLE_NAME);
    int failureIndex = (Integer) execution.getVariable(FAILURE_INDEX_VARIABLE_NAME);
    //Generating a list with the Integer as ID for testing purpose
    List<String> list = new ArrayList<String>();
    for (int i = 0; i < nbrOfItems; i++) {
      list.add(String.valueOf(i));
    }
    logger.info("Generating a list with {} entries, Failure Index={}", list.size(), failureIndex);
    // The list variable is used as an input for the sequential multi-instance subprocess
    execution.setVariable("list", list);
  }
}
