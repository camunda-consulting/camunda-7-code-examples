package com.camunda.consulting;

import java.util.ArrayList;
import java.util.List;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class ConditionTriggerHandler {
  private static final int PAGE_SIZE = 1000;
  private static final Logger LOG = LoggerFactory.getLogger(ConditionTriggerHandler.class);
  private final ProcessEngine processEngine;

  @Autowired
  public ConditionTriggerHandler(ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

  @Scheduled(fixedDelay = 10000)
  public void trigger() {
    getAllEventSubscriptions().stream()
        .map(EventSubscription::getProcessInstanceId)
        .distinct()
        .forEach(
            processInstance -> {
              LOG.debug("Triggering condition evaluation for process instance {}", processInstance);
              processEngine
                  .getRuntimeService()
                  .setVariable(processInstance, "trigger", Variables.booleanValue(true, true));
            });
  }

  private List<EventSubscription> getAllEventSubscriptions() {
    int count = getEventSubscriptionCount();
    LOG.debug("There are {} conditional event subscriptions", count);
    int currentIndex = 0;
    List<EventSubscription> result = new ArrayList<>();
    while (currentIndex < count) {
      int maxResults = Math.min(PAGE_SIZE, count - currentIndex);
      LOG.debug("Current index is {}, fetching the next {} results", currentIndex, maxResults);
      result.addAll(getEventSubscriptions(currentIndex, maxResults));
      currentIndex += maxResults;
    }
    return result;
  }

  private int getEventSubscriptionCount() {
    return Long.valueOf(
            processEngine
                .getRuntimeService()
                .createEventSubscriptionQuery()
                .eventType("conditional")
                .count())
        .intValue();
  }

  private List<EventSubscription> getEventSubscriptions(int firstResult, int maxResults) {
    return processEngine
        .getRuntimeService()
        .createEventSubscriptionQuery()
        .eventType("conditional")
        .listPage(firstResult, maxResults);
  }
}
