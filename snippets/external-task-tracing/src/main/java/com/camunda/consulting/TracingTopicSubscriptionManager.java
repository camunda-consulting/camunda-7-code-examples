package com.camunda.consulting;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Scope;
import io.micrometer.observation.ObservationRegistry;
import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.topic.impl.TopicSubscriptionManager;
import org.camunda.bpm.client.variable.impl.TypedValues;

public class TracingTopicSubscriptionManager extends TopicSubscriptionManager {
  private final ObservationRegistry observationRegistry;

  public TracingTopicSubscriptionManager(
      EngineClient engineClient,
      TypedValues typedValues,
      long clientLockDuration,
      ObservationRegistry observationRegistry
  ) {
    super(engineClient, typedValues, clientLockDuration);
    this.observationRegistry = observationRegistry;
  }

  @Override
  protected void handleExternalTask(ExternalTask externalTask, ExternalTaskHandler taskHandler) {
    Observation observation = Observation.createNotStarted(externalTask.getProcessInstanceId(), observationRegistry);
    observation
        .contextualName(externalTask.getId())
        .start();
    try (Scope ignored = observation.openScope()) {
      super.handleExternalTask(externalTask, taskHandler);
    } catch (Exception e) {
      observation.error(e);
    } finally {
      observation.stop();
    }

  }
}
