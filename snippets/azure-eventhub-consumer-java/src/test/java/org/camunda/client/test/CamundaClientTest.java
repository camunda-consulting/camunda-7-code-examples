package org.camunda.client.test;

import org.camunda.client.CamundaClient;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class CamundaClientTest {

    @Test
    void startProcess() {
        CamundaClient.startProcess("{\"someKey\" : \"someValue\", \"someOtherKey\" : \"someOtherValue\"}","BC0");
    }
}