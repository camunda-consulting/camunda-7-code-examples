package com.camunda.consulting.eventhub.plugin;

import org.junit.Test;

import static org.camunda.spin.Spin.JSON;

public class AzureEventHubClientTest {

    public static final String CONNECTION_STRING = "Endpoint=sb://camunda.servicebus.windows.net/;SharedAccessKeyName=demoPolicy;SharedAccessKey=2QSvZoTenHa4Ga5JdAiETyQUC2QrK+VvFz1UZohfqPM=";
    public static final String EVENTHUB = "demoeventhub";

//    @Test
    public void sendMessage() {
        AzureEventHubClient.setConfig(CONNECTION_STRING, EVENTHUB);
        AzureEventHubClient.sendMessage(JSON("{\"someVar\" : \"someString\"}"));
        AzureEventHubClient.sendMessage(JSON("{\"someVar\" : \"someOtherString\"}"));
    }
}