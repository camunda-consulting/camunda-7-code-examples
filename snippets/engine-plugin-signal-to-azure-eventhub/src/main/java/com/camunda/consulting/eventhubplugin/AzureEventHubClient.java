package com.camunda.consulting.eventhubplugin;

import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import org.camunda.spin.json.SpinJsonNode;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AzureEventHubClient {

  static private String namespaceName = null;
  static private String eventHubName = null;
  static private String sasKeyName = null;
  static private String sasKey = null;
  static private String endpoint = null;
  static private EventHubClient eventHubClient = null;

  private static EventHubClient client() {
    if (eventHubClient == null) {
      eventHubClient = init();
    }
    return eventHubClient;
  }

  private static EventHubClient init() {
    final ConnectionStringBuilder connStr = new ConnectionStringBuilder();
    if (namespaceName != null && !namespaceName.isEmpty()) {
      connStr.setNamespaceName(namespaceName);
    }
    if (eventHubName != null && !eventHubName.isEmpty()) {
      connStr.setEventHubName(eventHubName);
    }
    if (sasKeyName != null && !sasKeyName.isEmpty()) {
      connStr.setSasKeyName(sasKeyName);
    }
    if (sasKey != null && !sasKey.isEmpty()) {
      connStr.setSasKey(sasKey);
    }
    if (endpoint != null && !endpoint.isEmpty()) {
      connStr.setEndpoint(URI.create(endpoint));
    }

    // TODO: in production, do better thread pool handling
    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    try {
      return EventHubClient.createSync(connStr.toString(), executorService);
    } catch (EventHubException | IOException e) {
      throw new RuntimeException("Could not create event hub client", e);
    }
  }

  public static void sendMessage(SpinJsonNode messageJson) {
    byte[] payloadBytes = messageJson.toString().getBytes(Charset.defaultCharset());
    EventData sendEvent = EventData.create(payloadBytes);
    try {
      client().sendSync(sendEvent);
    } catch (EventHubException e) {
      throw new RuntimeException("Error sending message to event hub", e);
    }
  }

  public static void setConfig(String namespaceName, String eventHubName, String sasKeyName, String sasKey, String endpoint) {
    AzureEventHubClient.namespaceName = namespaceName;
    AzureEventHubClient.eventHubName = eventHubName;
    AzureEventHubClient.sasKeyName = sasKeyName;
    AzureEventHubClient.sasKey = sasKey;
    AzureEventHubClient.endpoint = endpoint;
  }

  // mainly for testsing/mocking
  protected static void setEventHubClient(EventHubClient eventHubClient) {
    AzureEventHubClient.eventHubClient = eventHubClient;
  }
}
