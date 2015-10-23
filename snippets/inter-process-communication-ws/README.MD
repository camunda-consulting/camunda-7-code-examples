Communication among Processes using Web Services
=========================

Let's assume that you have two processes running on different Process Engines on different servers. This could for example be a system with a central process engine that orchestrates several application-specific process engines:

![Inter Process Communication Webservice](inter-process-communication-ws.png)

The collaboration above contains two processes: The parent and the child.

The communication is done conceptually via messages, technologically by SOAP Messages via Web Services. This means that:

 *   The parent process uses a Service Task to invoke a Web Service that starts the child process
 *   The parent waits for completion of the child by using a Receive Task
 *   At the end of the child process, a Service Task performs the callback to the parent by calling another Web Service provided by the parent. The callback URL can be a parameter of the communication
 *   A unique id is generated for this communication and sent to and returned by the child as a correlation key that identifies the process instance that is to be called back


# Web Services for Invocation and Callback

This scenario requires two Web Services to be provided – one on each side:

 * Invocation Service: The first service is provided on the server where the child is deployed and will be called by the parent to start the child.
 * Callback Service: The second service is provided on the server where the parent process is deployed and will be called by the child to signal its completion to the parent.

A common way to implement Web Services in Java is to use JAX-WS annotations on a POJO and use a Web Service framework like Apache Axis or Apache CXF as a provider for the underlying protocols and tools. The examples below use Apache CXF, which is available out of the box in JBoss AS 7.


# Process Invocation Web Service

The Process Invocation Service has four parameters: The process to be started, a URL to call back when the process completed, a correlation id to identify the process instance to call back and a String payload. The latter three are stored as process variables into the new process instance. The payload could of course use more complex types. We just use String here for simplicity of the example.

[ProcessInvocation.java:](https://github.com/camunda/camunda-consulting/blob/master/snippets/inter-process-communication-ws/src/main/java/org/camunda/demo/interpocesscommunication/ws/ProcessInvocation.java)

```java
@WebService(name = "ProcessInvocationService")
public class ProcessInvocation {
  public static final String CALLBACK_URL = "callbackURL";
  public static final String CALLBACK_CORRELATION_ID = "callbackCorrelationId";
  public static final String PAYLOAD = "payload";

  @Inject
  private RuntimeService runtimeService;

  public void invokeProcess(String processDefinitionKey, String callbackUrl, String correlationId, String payload) {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put(CALLBACK_URL, callbackUrl);
    variables.put(CALLBACK_CORRELATION_ID, correlationId);
    variables.put(PAYLOAD, payload);
    runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
  }
}
```


# Process Callback Web Service

The Process Callback Service takes three arguments: The process that has completed, the correlation id that has been assigned during the invocation of that process, and a payload, which is again just a String for simplicity. With the first two arguments the process instance waiting for that callback is located and resumed while storing the payload as a process variable.

[ProcessCallback.java:](https://github.com/camunda/camunda-consulting/blob/master/snippets/inter-process-communication-ws/src/main/java/org/camunda/demo/interpocesscommunication/ws/ProcessCallback.java)

```java
@WebService(name = "ProcessCallbackService")
public class ProcessCallback {
  public static final String PAYLOAD_RECEIVED_FROM_CALLBACK = "payloadReceivedFromCallback";
  @Inject
  private RuntimeService runtimeService;

  public void invokeProcessCallback(String calledProcess, String correlationId, String payload) {
    Execution execution = runtimeService
            .createExecutionQuery()
            .variableValueEquals(ProcessInvocationClient.CORRELATION_ID_PREFIX + calledProcess, correlationId)
            .singleResult();

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put(PAYLOAD_RECEIVED_FROM_CALLBACK, payload);
    runtimeService.signal(execution.getId(), variables);
  }
}
```


# Generation of WSDL and Web Service Clients

When a Java class with a @WebService annocation is deployed, the application server automatically generates a WSDL description and provides the according Web Service, on a default JBoss AS 7 installation you will find the two WSDL's here:

*   [http://localhost:8080/inter-process-communication-ws/ProcessInvocation?wsdl](http://localhost:8080/inter-process-communication-ws/ProcessInvocation?wsdl)
*   [http://localhost:8080/inter-process-communication-ws/ProcessCallback?wsdl](http://localhost:8080/inter-process-communication-ws/ProcessCallback?wsdl)

A Maven plugin provided by CXF can then be used to generate a Java client out of the WSDL, just add this to your `pom.xml`:

```xml
...
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.cxf</groupId>
      <artifactId>cxf-codegen-plugin</artifactId>
      <version>2.5.2</version>
      <executions>
        <execution>
          <id>generate-sources</id>
          <phase>generate-sources</phase>
          <configuration>
            <sourceRoot>${project.build.directory}/generated/cxf</sourceRoot>
            <wsdlOptions>
              <wsdlOption>
                <wsdl>${basedir}/src/main/resources/ProcessInvocationService.wsdl</wsdl>
              </wsdlOption>
              <wsdlOption>
                <wsdl>${basedir}/src/main/resources/ProcessCallbackService.wsdl</wsdl>
              </wsdlOption>
            </wsdlOptions>
          </configuration>
          <goals>
            <goal>wsdl2java</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
...
```


# Integration of Web Services into Processes

The Web Services clients generated by CXF are wrapped into CDI beans that are called by the processes using expressions.

[parent.bpmn:](https://github.com/camunda/camunda-consulting/blob/master/snippets/inter-process-communication-ws/src/main/resources/parent.bpmn)

```xml
<serviceTask id="ServiceTask_1" activiti:expression="#{processInvocationClient.invokeProcess('inter-process-communication-ws-child', execution)}" name="Invoke child process" />
```

[ProcessInvocationClient.java:](https://github.com/camunda/camunda-consulting/blob/master/snippets/inter-process-communication-ws/src/main/java/org/camunda/demo/interpocesscommunication/ws/ProcessInvocationClient.java)

```java
@Named
public class ProcessInvocationClient {

  public static final String CORRELATION_ID_PREFIX = "correlationIdForInvocationOf_";
  public static final String SAMPLE_PAYLOAD_PREFIX = "sample-payload-";

  @Inject
  ServiceRegistry serviceRegistry;

  public void invokeProcess(String processDefinitionKey, DelegateExecution execution) {
    // lookup service URL
    URL wsdlLocation = serviceRegistry.getWsdlLocation(processDefinitionKey);
    // prepare CXF client
    ProcessInvocationService service = new ProcessInvocationService_Service(wsdlLocation)
        .getProcessInvocationServicePort();
    // generate callback URL and correlation ID
    String callbackUrl = serviceRegistry.getWsdlLocation("inter-process-communication-ws-parent").toString();
    String correlationId = UUID.randomUUID().toString();
    // store correlation ID
    execution.setVariable(CORRELATION_ID_PREFIX + processDefinitionKey, correlationId);
    // call service
    service.invokeProcess(processDefinitionKey, callbackUrl , correlationId, SAMPLE_PAYLOAD_PREFIX + correlationId);
  }
}
```

You can see that we used a simple "ServiceRegistry" to query the right WSDL. This is basically a simple Java map, but could be exchanged by any existing Registry. In a customer project we for example used WSO2 for this purpose.

[child.bpmn:](https://github.com/camunda/camunda-consulting/blob/master/snippets/inter-process-communication-ws/src/main/resources/child.bpmn)

```xml
<serviceTask id="ServiceTask_1" activiti:expression="#{processCallbackClient.invokeProcessCallback(payload, execution)};" name="Invoke callback">
```

[ProcessCallbackClient.java:](https://github.com/camunda/camunda-consulting/blob/master/snippets/inter-process-communication-ws/src/main/java/org/camunda/demo/interpocesscommunication/ws/ProcessCallbackClient.java)

```java
@Named
public class ProcessCallbackClient {
  public void invokeProcessCallback(String payload, DelegateExecution execution) throws MalformedURLException {
    // lookup service URL
    URL wsdlLocation = new URL((String) execution.getVariable(ProcessInvocation.CALLBACK_URL));
    // prepare CXF client
    ProcessCallbackService service = new ProcessCallbackService_Service(wsdlLocation)
        .getProcessCallbackServicePort();
    // restore correlation information
    String calledProcess = "inter-process-communication-ws-child";
    String correlationId = (String) execution.getVariable(ProcessInvocation.CALLBACK_CORRELATION_ID);
    // call service
    service.invokeProcessCallback(calledProcess, correlationId, payload);
  }
}
```
