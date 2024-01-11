package com.camunda.consulting;

import org.camunda.bpm.engine.OptimisticLockingException;
import org.camunda.bpm.engine.exception.NotFoundException;
import org.camunda.community.extension.internalTaskHandler.InternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InboundMessageHandler {
  private static final Logger LOG = LoggerFactory.getLogger(InboundMessageHandler.class);
  private final InternalTaskService internalTaskService;
  private final ExecutorService retryOnOptimisticLockingException = Executors.newFixedThreadPool(100);

  @Autowired
  public InboundMessageHandler(InternalTaskService internalTaskService) {
    this.internalTaskService = internalTaskService;
  }

  @RabbitListener(queues = "q.example")
  public void handleResponse(Message message) {
    LOG.info("Handling response '{}' for task '{}'", message.content(), message.taskId());
    try {
      if (message.content() == null) {
        // there must be a result
        internalTaskService.handleFailure(message.taskId(), "Response is null!", "Response is null!", 0, 0);
      } else if (message
          .content()
          .equals("error")) {
        // the result should not be an error
        internalTaskService.handleBpmnError(message.taskId(), "error-response", "Response is error", null);
      } else {
        // all good, we can continue from here
        internalTaskService.complete(message.taskId(), Map.of("response", message.content()), null);
      }
    } catch (OptimisticLockingException e) {
      LOG.warn(
          "There was an Optimistic locking exception while handling the task with id '" + message.taskId() + "', automatically retrying...",
          e
      );
      retryOnOptimisticLockingException.submit(() -> handleResponse(message));
    } catch (NotFoundException e) {
      LOG.warn(
          "There was a Not found exception while handling the task with id '" + message.taskId() + "', the message will be ignored",
          e
      );
    }
  }
}
