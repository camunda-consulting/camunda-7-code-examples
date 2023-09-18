package com.camunda.consulting.springboot_integration_test_example.integration;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import java.time.Duration;
import java.util.Map;
import org.awaitility.Awaitility;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.community.process_test_coverage.spring_test.platform7.ProcessEngineCoverageConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = { "camunda.bpm.job-execution.enabled=true" })
@Import(ProcessEngineCoverageConfiguration.class)
public class IntegrationTest {

  @Autowired
  ProcessEngine engine;
  
  @Test
  public void testHappyPath() throws InterruptedException {
    ProcessInstance processInstance = engine.getRuntimeService().startProcessInstanceByKey("paymentProcess-delegate", Map.of(
        "customerId", "testCustomer35", 
        "orderTotal", 50.0, 
        "cardNumber", "1234 5678", 
        "cvc", "123", 
        "expiryDate", "09/25"));
    
    waitForProcessInstanceCompleted(processInstance, Duration.ofSeconds(2));
    
    assertThat(processInstance).isEnded().variables().contains(entry("remainingAmount", 15.0));
  }

  @Test
  public void testCreditSufficient() throws InterruptedException {
    ProcessInstance processInstance = engine.getRuntimeService().startProcessInstanceByKey("paymentProcess-delegate", Map.of(
        "customerId", "testCustomer20", 
        "orderTotal", 20.0, 
        "cardNumber", "notRequired", 
        "cvc", "notRequired", 
        "expiryDate", "notREquired"));
    
    waitForProcessInstanceCompleted(processInstance, Duration.ofSeconds(2));
    
    assertThat(processInstance).isEnded().hasNotPassed(findId("Charge credit card"));
  }

  protected void waitForProcessInstanceCompleted(ProcessInstance processInstance, Duration maxWait) {
    Awaitility.await().atMost(maxWait).untilAsserted(() -> {
      Thread.sleep(100);
      assertThat(processInstance).isEnded();
    });
  }
}
