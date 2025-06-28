package com.example.test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import com.example.worker.CreditService;
import com.example.worker.ExternalTaskWorker;

public class CamundaWorkerTest {

    static GenericContainer<?> camunda;
    static CamundaHelper camundaHelper;
    static CreditService creditServiceMock;
    static String baseUrl;

    @BeforeAll
    static void setup() throws Exception {
        camunda = new GenericContainer<>(DockerImageName.parse("camunda/camunda-bpm-platform:7.22.0"))
                .withExposedPorts(8080);
        camunda.start();

        baseUrl = "http://" + camunda.getHost() + ":" + camunda.getMappedPort(8080) + "/engine-rest";
        waitForCamunda();

        camundaHelper = new CamundaHelper(baseUrl);
        
        creditServiceMock = Mockito.mock(CreditService.class);
        ExternalTaskWorker.start(baseUrl, creditServiceMock);
        
        camundaHelper.deployBpmnDiagram("src/test/resources/java-script-external.bpmn");

        Thread.sleep(5000);
    }

    @AfterAll
    static void tearDown() {
        camunda.stop();
    }

    @BeforeEach
    void resetMocks() {
        reset(creditServiceMock);
    }

    @Test
    void testHappyPath() throws Exception {
        when(creditServiceMock.getCreditScore()).thenReturn(80);
        when(creditServiceMock.getCustomerExists()).thenReturn(false);
        
        String processKey = "ApplicationReceived";
        var processInstance = camundaHelper.startProcessInstance(processKey, Map.of(
            "someInput", Map.of("value", "test", "type", "String")
        ));
        
        sleep(5000);

        assertTrue(camundaHelper.checkElement(processInstance.id, "Add Customer", true));
        assertTrue(camundaHelper.checkElement(processInstance.id, "Send Confirmation", true));
        assertTrue(camundaHelper.instanceIsCompleted(processInstance.id));

        verify(creditServiceMock).getCreditScore();
        verify(creditServiceMock).getCustomerExists();
    }

    @Test
    void testRejectionPath() throws Exception {
        when(creditServiceMock.getCreditScore()).thenReturn(30);
        when(creditServiceMock.getCustomerExists()).thenReturn(true);

        String processKey = "ApplicationReceived";
        var processInstance = camundaHelper.startProcessInstance(processKey, Map.of(
            "someInput", Map.of("value", "test", "type", "String")
        ));

        assertTrue(camundaHelper.checkElement(processInstance.id, "Get Credit Score", true));
        assertTrue(camundaHelper.checkElement(processInstance.id, "Send Rejection", true));
        assertTrue(camundaHelper.instanceIsCompleted(processInstance.id));

        verify(creditServiceMock).getCreditScore();
    }

    @Test
    void testDuplicateCustomerFlow() throws Exception {
        when(creditServiceMock.getCreditScore()).thenReturn(85);
        when(creditServiceMock.getCustomerExists()).thenReturn(true);

        String processKey = "ApplicationReceived";
        var processInstance = camundaHelper.startProcessInstance(processKey, Map.of(
            "someInput", Map.of("value", "test", "type", "String")
        ));
        
        Thread.sleep(5000);

        assertTrue(camundaHelper.checkElement(processInstance.id, "Add Customer", false));
        assertTrue(camundaHelper.isUserTaskActive(processInstance.id, "Send Information"));

        String userTaskId = camundaHelper.getUserTaskId(processInstance.id, "Send Information");
        camundaHelper.completeUserTask(userTaskId);

        assertTrue(camundaHelper.instanceIsCompleted(processInstance.id));

        verify(creditServiceMock).getCreditScore();
        verify(creditServiceMock).getCustomerExists();
    }

    private static void waitForCamunda() throws InterruptedException {
        int retries = 30;
        while (retries-- > 0) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(baseUrl + "/engine").openConnection();
                if (conn.getResponseCode() == 200) {
                	return;
                }
            } catch (Exception ignored) {}
            sleep(1000);
        }
        throw new IllegalStateException("Camunda did not become ready in time.");
    }
}
