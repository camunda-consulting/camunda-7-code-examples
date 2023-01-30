package com.camunda.consulting.externalTask.ejb;

import org.camunda.bpm.client.ExternalTaskClient;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

@Singleton
public class ExternalTaskClientFactory {

  @Produces
  public ExternalTaskClient get() {
    return ExternalTaskClient.create().baseUrl("http://camunda:8080/engine-rest").build();
  }
}
