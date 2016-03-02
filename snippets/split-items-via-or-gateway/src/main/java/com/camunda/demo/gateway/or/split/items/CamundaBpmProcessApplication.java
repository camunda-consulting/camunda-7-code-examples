package com.camunda.demo.gateway.or.split.items;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  public static final String PROCESS_DEFINITION_KEY = "order-processing";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {
    if (processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).count()==0) {
      
      Order order = new Order()
        .addItem(new Item().setId("01").setText("First Item"))
        .addItem(new Item().setId("02").setText("Second Item"))
        .addItem(new Item().setId("03").setText("Third Item"));
      
      processEngine.getRuntimeService().startProcessInstanceByKey( //
          PROCESS_DEFINITION_KEY, //
          Variables.createVariables().putValueTyped( //
              ProcessVariables.VAR_NAME_ORDER, //
              Variables.objectValue(order).serializationDataFormat(SerializationDataFormats.JSON).create()));
    }
  }

}
