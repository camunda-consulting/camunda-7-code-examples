package com.camunda.ige.process.sample.test;

import java.util.Map;

import org.camunda.bpm.cockpit.ige.adapter.RepairableFailingDelegate;
import org.camunda.bpm.engine.impl.test.PluggableProcessEngineTestCase;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

public class TestProcesses extends PluggableProcessEngineTestCase {
  
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  @Deployment(resources="process4.bpmn")
  public void testProcessWithIncident() {
    ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_4");
    assertNotNull(pi);
    Task ersteAufgabe = taskQuery().processInstanceId(pi.getId()).singleResult();
    complete(ersteAufgabe);
    
    // fire the async Service Task
    executeAvailableJobs();
    assertTrue("kein Incident angelegt", runtimeService.createIncidentQuery().count() > 0);
    
    Map<String, Object> procVars = withVariables(RepairableFailingDelegate.REPAIRED, "true");
    runtimeService.setVariables(pi.getProcessInstanceId(), procVars);
    
    // retry
    execute(jobQuery().processInstanceId(pi.getProcessInstanceId()).singleResult());
    assertThat(pi).task(taskQuery().processInstanceId(pi.getProcessInstanceId())).hasName("Task nach Incident");
  }

}
