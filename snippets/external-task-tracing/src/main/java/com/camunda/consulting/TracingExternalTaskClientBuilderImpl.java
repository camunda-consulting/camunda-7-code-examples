package com.camunda.consulting;

import io.micrometer.observation.ObservationRegistry;
import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.client.impl.RequestExecutor;
import org.camunda.bpm.client.interceptor.impl.RequestInterceptorHandler;

public class TracingExternalTaskClientBuilderImpl extends ExternalTaskClientBuilderImpl {
  private final ObservationRegistry observationRegistry;

  public TracingExternalTaskClientBuilderImpl(ObservationRegistry observationRegistry) {
    this.observationRegistry = observationRegistry;
  }

  @Override
  protected void initEngineClient() {
    RequestInterceptorHandler requestInterceptorHandler = new RequestInterceptorHandler(interceptors);
    RequestExecutor requestExecutor = new RequestExecutor(requestInterceptorHandler, objectMapper) {};
    engineClient = new TracingEngineClient(
        workerId,
        maxTasks,
        asyncResponseTimeout,
        baseUrl,
        requestExecutor,
        usePriority,
        observationRegistry
    );
  }

  @Override
  protected void initTopicSubscriptionManager() {
    topicSubscriptionManager = new TracingTopicSubscriptionManager(engineClient,
        typedValues,
        lockDuration,
        observationRegistry
    );
    topicSubscriptionManager.setBackoffStrategy(getBackoffStrategy());

    if (isBackoffStrategyDisabled) {
      topicSubscriptionManager.disableBackoffStrategy();
    }

    if (isAutoFetchingEnabled()) {
      topicSubscriptionManager.start();
    }
  }
}
