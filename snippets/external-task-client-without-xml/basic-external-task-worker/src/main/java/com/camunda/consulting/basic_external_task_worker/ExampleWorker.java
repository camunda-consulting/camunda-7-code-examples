package com.camunda.consulting.basic_external_task_worker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleWorker {
  
  private static final Logger LOG = LoggerFactory.getLogger(ExampleWorker.class);

  public static void main(String[] args) {
    ExternalTaskClient client = ExternalTaskClient.create()
        .baseUrl("http://localhost:8080/engine-rest")
        .asyncResponseTimeout(10000) // long polling timeout
        .build();

    // subscribe to an external task topic as specified in the process
    client.subscribe("charge-card")
        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
        .handler((externalTask, externalTaskService) -> {
          // Put your business logic here

          // Get a process variable
          String item = externalTask.getVariable("item");
          Integer amount = externalTask.getVariable("amount");

          LOG.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

          // Complete the task
          externalTaskService.complete(externalTask);
        })
        .open();
  }

}
