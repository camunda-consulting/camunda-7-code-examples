package com.camunda.consulting;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ExternalTaskSubscription(topicName = "doSomething")
public class MyTaskImplementation implements ExternalTaskHandler {
  private static final Logger LOG = LoggerFactory.getLogger(MyTaskImplementation.class);

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    // this implementation is generic and could be abstracted
    Request request = buildRequest(externalTask.getAllVariables());
    Response response = doSomething(request);
    Map<String, Object> responseVariables = buildResponse(response);
    externalTaskService.complete(externalTask, responseVariables);
  }

  private Map<String, Object> buildResponse(Response response) {
    // also this part could be abstracted
    Map<String, Object> responseVariables = new HashMap<>();
    responseVariables.put("var1", response.var1());
    responseVariables.put("var2", response.var2());
    return responseVariables;
  }

  private Request buildRequest(Map<String, Object> allVariables) {
    // also this part could be abstracted
    return new Request((String) allVariables.get("var1"), (String) allVariables.get("var2"));
  }

  @JobWorker
  public Response doSomething(@VariablesAsType Request request) {
    // this is the actual business logic
    LOG.info("doSomething has been invoked with {}", request);
    return new Response(request.var1(), request.var2());
  }

  public record Request(String var1, String var2) {}

  public record Response(String var1, String var2) {}
}
