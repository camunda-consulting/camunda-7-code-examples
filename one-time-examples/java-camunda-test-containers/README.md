# Camunda 7 Unit Testing in Java with TestContainers and Service Mocking

## 📌 Overview
This project demonstrates **how to write unit tests for Camunda BPMN processes in Java** using JUnit, Mockiito and TestContainers. It sets up a testable Camunda environment, mocks external dependencies, and validates process execution.

## 🚀 Getting Started

### **1. Prerequisites**
Ensure you have the following installed:
- Maven
- Docker (for running Camunda in TestContainers)

### **2. Installation and Running Tests**
Clone the repository and install dependencies:

```sh
mvn install

```

This will:
1. Start a **Camunda TestContainer**.
2. Deploy a BPMN process.
3. Execute test cases using mocked dependencies.
4. Validate process execution.
5. Tear down the container after tests complete.

## 🛠 How It Works

### **1. Setting Up Camunda for Tests and Deploying the process**
The `CamundaWorkerTest.java` file starts a **Camunda TestContainer**:

```java
        camunda = new GenericContainer<>(DockerImageName.parse("camunda/camunda-bpm-platform:7.22.0"))
                .withExposedPorts(8080);
        camunda.start();

        baseUrl = "http://" + camunda.getHost() + ":" + camunda.getMappedPort(8080) + "/engine-rest";
        waitForCamunda();

        camundaHelper = new CamundaHelper(baseUrl);
        
        creditServiceMock = Mockito.mock(CreditService.class);
        ExternalTaskWorker.start(baseUrl, creditServiceMock);
        
        camundaHelper.deployBpmnDiagram("src/test/resources/java-script-external.bpmn");
...
```

### **2. Writing Unit Tests**
The unit tests are executed thru CamundaWorkerTest which validates Camunda process execution:
```java

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
        
```

### **3. Mocking External Dependencies**
The `CreditService.java` file is **mocked in tests**:

```java
creditServiceMock = Mockito.mock(CreditService.class);
```

## 🔥 Key Features
✔️ Uses **Mockito** for unit testing.  
✔️ Runs Camunda BPM in a **TestContainer** (Docker).  
✔️ Mocks **external dependencies** (e.g., `CreditService`).  
✔️ Validates process execution (completed, user tasks, rejections).  