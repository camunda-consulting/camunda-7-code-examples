package org.camunda.demo.interpocesscommunication.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.demo.interpocesscommunication.ws.ProcessCallback;
import org.camunda.demo.interpocesscommunication.ws.ProcessInvocation;
import org.camunda.demo.interpocesscommunication.ws.ProcessInvocationClient;
import org.camunda.demo.interpocesscommunication.ws.ServiceRegistry;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTestCase {

  @Deployment
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

    return ShrinkWrap.create(WebArchive.class, "inter-process-communication-ws.war")

    .addAsLibraries(resolver.artifact("org.camunda.bpm.javaee:camunda-ejb-client").resolveAsFiles())
        .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
        .addAsLibraries(resolver.artifact("commons-io:commons-io").resolveAsFiles())
        .addAsWebResource(EmptyAsset.INSTANCE, "WEB-INF/classes/META-INF/processes.xml")
        // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")

        // add your own classes (could be done one by one as well)
        .addPackages(true, "org.camunda.demo.interpocesscommunication.ws")
        // add process definition
        .addAsResource("parent.bpmn").addAsResource("child.bpmn")
        // now you can add additional stuff required for your test case
        .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/beans.xml"), "beans.xml");
  }

  @Inject
  private RuntimeService runtimeService;

  @Inject
  private TaskService taskService;

  @Test
  public void test() {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("inter-process-communication-ws-parent");
    String pid = processInstance.getId();
    String correlationId = (String) runtimeService.getVariable(pid, ProcessInvocationClient.CORRELATION_ID_PREFIX + "inter-process-communication-ws-child");

    Task task = taskService.createTaskQuery().processDefinitionKey("inter-process-communication-ws-child")
        .processVariableValueEquals(ProcessInvocation.CALLBACK_CORRELATION_ID, correlationId).singleResult();
    assertNotNull(task);
    assertEquals("Child Process Called", task.getName());
    taskService.complete(task.getId());

    task = taskService.createTaskQuery().processInstanceId(pid).singleResult();
    assertNotNull(task);
    assertEquals("Callback reveived", task.getName());

    assertPayloadTransmittedCorrectly(pid, correlationId);

    taskService.complete(task.getId());
  }

  private void assertPayloadTransmittedCorrectly(String pid, String correlationId) {
    assertEquals(ProcessInvocationClient.SAMPLE_PAYLOAD_PREFIX + correlationId, runtimeService.getVariable(pid, ProcessCallback.PAYLOAD_RECEIVED_FROM_CALLBACK));
  }

  @RunAsClient
  @Test
  public void downloadWSDL() throws IOException {
    ServiceRegistry serviceRegistry = new ServiceRegistry();
    serviceRegistry.init();
    for (Entry<String, String> entry : serviceRegistry.getUrls().entrySet()) {
      String url = entry.getValue();
      System.out.println("Donwloading WSDL from " + url);
      String fileName = url.replace("http://localhost:8080/inter-process-communication-ws/", "").replace("?", "Service.");
      FileUtils.copyURLToFile(new URL(url), new File("src/main/resources/" + fileName));
    }
  }
}
