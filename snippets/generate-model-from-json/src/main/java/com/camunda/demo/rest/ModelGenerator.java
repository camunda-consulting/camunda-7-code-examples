package com.camunda.demo.rest;

import com.camunda.demo.domain.EventTypeConfiguration;
import com.camunda.demo.domain.UserTaskData;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.ProcessBuilder;
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder;

public class ModelGenerator {

  public static BpmnModelInstance generateModel(EventTypeConfiguration configuration) {

    ProcessBuilder process = Bpmn.createExecutableProcess(configuration.getEventType());

    StartEventBuilder startEventBuilder = process.startEvent();

    configuration.getConfiguration().forEach(ModelGenerator::addModelElement);


    return process.done();
  }

  private static void addModelElement(UserTaskData userTaskData) {



  }
}
