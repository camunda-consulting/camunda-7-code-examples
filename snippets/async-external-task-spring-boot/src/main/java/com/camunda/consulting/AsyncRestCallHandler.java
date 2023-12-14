package com.camunda.consulting;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription(topicName = "asyncRestCalling")
public class AsyncRestCallHandler implements ExternalTaskHandler {

  private static final Logger LOG = LoggerFactory.getLogger(AsyncRestCallHandler.class);

  HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
      .connectTimeout(Duration.ofSeconds(10)).build();

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    String path = externalTask.getVariable("path");
    if (path == null) {
      path = "/random/delayed";
      // Alternative from wiremock is "failed"
    }

    String result;
    HttpRequest request =
        HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8091/" + path))
            .setHeader("User-Agent", "Java 11 HttpClient Bot").build();

    LOG.info("request created for {}", path);

    CompletableFuture<HttpResponse<String>> response =
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

    LOG.info("request sent to {}", path);

    try {
      result = response.thenApply(responseAsync -> {
        if (responseAsync.statusCode() == 200) {
          Map<String, Object> variables = Map.of("asyncResponse", responseAsync.body());
          LOG.info("Completing task {}", externalTask.getId());
          externalTaskService.complete(externalTask, variables);
          return responseAsync.body();
        } else {
          LOG.info("failing task from status {}", responseAsync.statusCode());
          externalTaskService.handleFailure(externalTask, "Error 500", responseAsync.body(), 0, 0);
          return responseAsync.body();
        }
      }).exceptionally(string -> {
        return "failed";
      }).get(5, TimeUnit.SECONDS);
      LOG.info("response: {}", result);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      LOG.warn("failing task {}", externalTask.getId());
      response.cancel(true);
      externalTaskService.handleFailure(externalTask, "Interrupted, likely a timeout",
          stacktraceToString(e), 0, 0);
    }
  }

  String stacktraceToString(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    return sw.toString();
  }

}
