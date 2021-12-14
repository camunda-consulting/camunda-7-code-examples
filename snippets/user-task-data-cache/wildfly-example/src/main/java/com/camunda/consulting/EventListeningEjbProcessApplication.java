package com.camunda.consulting;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.ProcessApplicationInterface;
import org.camunda.bpm.application.impl.EjbProcessApplication;
import org.camunda.bpm.engine.cdi.impl.event.CdiEventListener;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@ProcessApplication
@Local(ProcessApplicationInterface.class)
public class EventListeningEjbProcessApplication extends EjbProcessApplication {

  protected CdiEventListener cdiEventListener = new CdiEventListener();

  @PostConstruct
  public void start() {
    deploy();
  }

  @PreDestroy
  public void stop() {
    undeploy();
  }

  public ExecutionListener getExecutionListener() {
    return cdiEventListener;
  }

  public TaskListener getTaskListener() {
    return cdiEventListener;
  }
}
