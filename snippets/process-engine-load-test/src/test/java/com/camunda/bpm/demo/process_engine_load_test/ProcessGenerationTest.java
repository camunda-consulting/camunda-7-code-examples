package com.camunda.bpm.demo.process_engine_load_test;

import java.io.File;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.junit.Test;

public class ProcessGenerationTest {

  @Test
  public void testProcessGeneration() {
    BpmnModelInstance sequenceOf100ServiceTasks = ProcessGenerator.generateSequenceOf100ServiceTasks();
    Bpmn.writeModelToFile(new File("target/SequenceOf100ServiceTasks.bpmn"), sequenceOf100ServiceTasks);
  }

}