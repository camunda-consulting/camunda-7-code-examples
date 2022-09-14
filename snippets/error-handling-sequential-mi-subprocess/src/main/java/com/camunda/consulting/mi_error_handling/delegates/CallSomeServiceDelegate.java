package com.camunda.consulting.mi_error_handling.delegates;

import camundajar.impl.scala.Int;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("CallSomeService")
public class CallSomeServiceDelegate implements JavaDelegate {
  public static final String FAILURE_INDEX_VARIABLE_NAME = "failureIndex";
  public static final String BUSINESS_ERROR_INDEX_NAME = "businessErrorIndex";
  public static final String LOOP_COUNTER_VARIABLE_NAME = "loopCounter";
  public static final String LOOP_ELEMENT_VARIABLE = "item";
  private final static Logger logger = LoggerFactory.getLogger(CallSomeServiceDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Integer loopCounter = (Integer) execution.getVariable(LOOP_COUNTER_VARIABLE_NAME);
    int failureIndex = (Integer) execution.getVariable(FAILURE_INDEX_VARIABLE_NAME);
    int businessErrorIndex = (Integer) execution.getVariable(BUSINESS_ERROR_INDEX_NAME);
    // Get the item Variable
    String item = (String) execution.getVariable(LOOP_ELEMENT_VARIABLE);
    // Execute some services call; here simulated with Thread.sleep.
    // In case loop count == failureIndex throw an exception to simulate a failure.
    try {
      logger.info("Start Processing item {}", item);
      Thread.sleep(200);
      if (loopCounter == failureIndex) {
        logger.info("Failed item {}", item);
        throw new ProcessEngineException("Failure in Process");
      } else if (loopCounter == businessErrorIndex) {
        logger.info("A business error occurred for item {}", item);
        throw new BpmnError("BusinessError", "Flag " + item + ", since it cannot be processed.");
      }
      logger.info("End Processing item {}", item);
    } catch (InterruptedException e) {}
  }


}
