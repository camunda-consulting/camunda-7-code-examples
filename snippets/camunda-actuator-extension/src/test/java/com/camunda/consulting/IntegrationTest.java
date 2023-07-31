package com.camunda.consulting;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.assertj.core.api.Assertions.*;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.camunda.consulting.actuator.JobExecutorMetricsConfiguration;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootTest
public class IntegrationTest {
  
  @Autowired
  RuntimeService runtimeService;
  
  @Autowired
  JobExecutorMetricsConfiguration jobExecutorMetrics;
  
  @Autowired
  MeterRegistry registry;
  
  @Test
  public void runProcess() throws InterruptedException {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("HttpsExampleProcess");
    
    Thread.sleep(3000);
    assertThat(processInstance).isEnded();
    
    assertThat(jobExecutorMetrics.jobExecutionsSuccessful(registry).value()).isEqualTo(2);
    assertThat(jobExecutorMetrics.jobAcquisitionsAttempted(registry).value()).isEqualTo(5);
    assertThat(jobExecutorMetrics.jobAcquistionsFailed(registry).value()).isEqualTo(0);
    assertThat(jobExecutorMetrics.jobAcquisitionsSuccessful(registry).value()).isEqualTo(1); // see jobexector logs
    assertThat(jobExecutorMetrics.jobLocksExclusive(registry).value()).isEqualTo(1);
  }

}
