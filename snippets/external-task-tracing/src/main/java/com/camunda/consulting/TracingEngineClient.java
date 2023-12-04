package com.camunda.consulting;

import io.micrometer.observation.ObservationRegistry;
import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.client.impl.RequestExecutor;

import java.util.Map;

public class TracingEngineClient extends EngineClient {
  private final ObservationRegistry observationRegistry;

  public TracingEngineClient(
      String workerId,
      int maxTasks,
      Long asyncResponseTimeout,
      String baseUrl,
      RequestExecutor engineInteraction,
      ObservationRegistry observationRegistry
  ) {
    super(workerId, maxTasks, asyncResponseTimeout, baseUrl, engineInteraction);
    this.observationRegistry = observationRegistry;
  }

  public TracingEngineClient(
      String workerId,
      int maxTasks,
      Long asyncResponseTimeout,
      String baseUrl,
      RequestExecutor engineInteraction,
      boolean usePriority,
      ObservationRegistry observationRegistry
  ) {
    super(workerId, maxTasks, asyncResponseTimeout, baseUrl, engineInteraction, usePriority);
    this.observationRegistry = observationRegistry;
  }

  @Override
  public void failure(
      String taskId,
      String errorMessage,
      String errorDetails,
      int retries,
      long retryTimeout,
      Map<String, Object> variables,
      Map<String, Object> localVariables
  ) {
    observationRegistry
        .getCurrentObservation()
        .error(new Exception(errorMessage + ": " + errorDetails));
    super.failure(taskId, errorMessage, errorDetails, retries, retryTimeout, variables, localVariables);
  }
}
