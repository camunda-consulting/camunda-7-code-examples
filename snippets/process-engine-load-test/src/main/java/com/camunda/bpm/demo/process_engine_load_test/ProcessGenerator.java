package com.camunda.bpm.demo.process_engine_load_test;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.ServiceTaskBuilder;
import org.camunda.bpm.model.bpmn.builder.UserTaskBuilder;

public class ProcessGenerator {
  public static BpmnModelInstance generateSequenceOf100ServiceTasks() {
    ServiceTaskBuilder serviceTaskBuilder = Bpmn.createExecutableProcess("ServiceTasksSR").name("ServiceTasksSR").startEvent().serviceTask()
        .camundaExpression("${true}");
    for (int i = 0; i < 100; i++) {
      serviceTaskBuilder = serviceTaskBuilder.serviceTask().camundaExpression("${true}");
    }
    BpmnModelInstance modelInstance = serviceTaskBuilder.endEvent().done();
    return modelInstance;
  }

  public static BpmnModelInstance generateSequenceOf100AsyncServiceTasks() {
    ServiceTaskBuilder serviceTaskBuilder = Bpmn.createExecutableProcess("SequenceOf100AsyncServiceTasks").name("SequenceOf100AsyncServiceTasks").startEvent()
        .camundaAsyncBefore().serviceTask().camundaDelegateExpression("${logger}").camundaAsyncBefore();
    for (int i = 0; i < 100; i++) {
      serviceTaskBuilder = serviceTaskBuilder.serviceTask().camundaDelegateExpression("${logger}").camundaAsyncBefore();
    }
    BpmnModelInstance modelInstance = serviceTaskBuilder.endEvent().done();
    return modelInstance;
  }

  public static BpmnModelInstance generateMixOf100ServiceAnd10UserTasks() {
    ServiceTaskBuilder serviceTaskBuilder = Bpmn.createExecutableProcess("ServiceUserTasksLR").name("ServiceUserTasksLR").startEvent().serviceTask()
        .camundaExpression("${true}");
    for (int i = 0; i < 100; i++) {
      if (i % 10 == 0) {
        UserTaskBuilder userTaskBuilder = serviceTaskBuilder.userTask().id("UserTask-" + (i / 10));
        serviceTaskBuilder = userTaskBuilder.serviceTask().camundaExpression("${true}");
      } else {
        serviceTaskBuilder = serviceTaskBuilder.serviceTask().camundaExpression("${true}");
      }
    }
    BpmnModelInstance modelInstance = serviceTaskBuilder.endEvent().done();
    return modelInstance;
  }

  public static BpmnModelInstance generateMixOf100ServiceAnd10UserTasksAsync() {
    ServiceTaskBuilder serviceTaskBuilder = Bpmn.createExecutableProcess("ServiceUserTasksLR").name("ServiceUserTasksLR").startEvent().serviceTask()
        .camundaExpression("${true}").camundaAsyncAfter();
    for (int i = 0; i < 100; i++) {
      if (i % 10 == 0) {
        UserTaskBuilder userTaskBuilder = serviceTaskBuilder.userTask().id("UserTask-" + (i / 10)).camundaAsyncBefore().camundaAsyncAfter();
        serviceTaskBuilder = userTaskBuilder.serviceTask().camundaExpression("${true}").camundaAsyncAfter();
      } else {
        serviceTaskBuilder = serviceTaskBuilder.serviceTask().camundaExpression("${true}").camundaAsyncAfter();
      }
    }
    BpmnModelInstance modelInstance = serviceTaskBuilder.endEvent().done();
    return modelInstance;
  }
}
