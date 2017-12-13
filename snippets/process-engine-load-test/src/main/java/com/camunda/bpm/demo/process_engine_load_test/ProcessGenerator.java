package com.camunda.bpm.demo.process_engine_load_test;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.ServiceTaskBuilder;

public class ProcessGenerator {

  public static BpmnModelInstance generateSequenceOf100ServiceTasks() {
    ServiceTaskBuilder serviceTaskBuilder = Bpmn.createExecutableProcess("SequenceOf100ServiceTasks")
        .startEvent()
        .serviceTask().camundaExpression("${true}");
    for(int i = 0; i < 100; i++) {
      serviceTaskBuilder = serviceTaskBuilder.serviceTask().camundaExpression("${true}");
    }
    BpmnModelInstance modelInstance = serviceTaskBuilder
        .endEvent()
        .done();
    return modelInstance;
  }

}
