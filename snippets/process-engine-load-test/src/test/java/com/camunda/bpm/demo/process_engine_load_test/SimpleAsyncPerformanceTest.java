package com.camunda.bpm.demo.process_engine_load_test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.junit.Test;

public class SimpleAsyncPerformanceTest {
  static final String CAMUNDA_REST_API = "http://localhost:8080/engine-rest";

  @Test
  public void deploy() {
    // deploymentCreate();
  }

@Test
public void testPerformance() throws Exception
{
  Client client = ClientBuilder.newClient();
  WebTarget target =
  client
    .target(CAMUNDA_REST_API)
    .path("/process-definition/key/SequenceOf100AsyncServiceTasks/start");
  long startTS = System.currentTimeMillis();
  int n = 1000;
  int sleepTime = 1;
  int pause = 100;
  int threads = 10;
  final List<Runnable> threadList = new LinkedList<Runnable>();
  final String testName = "AsyncTest9";
  //for ( int j = 0; j < threads; j++ )
  //{
  //Runnable r = new Runnable()
  //{
  //public void run()
  //{
  //try
  //{
  for ( int i = 0; i < n; i++ )
  {
    Response response = target.request()
      .accept(MediaType.APPLICATION_JSON)
      //.post(Entity.json(new StartProcessInstanceDto()));
      .post(Entity.json("{\"businessKey\" :\""+testName+"-"+i+"@"+Thread.currentThread().getName()+"\"}"));
    assertEquals(200, response.getStatus());
  //System.out.println(response);
  //try
  //{
  //Thread.sleep(sleepTime);
  //}
  //catch (InterruptedException ie)
  //{
  //ie.printStackTrace();
  //}
  //}
  //}
  //finally
  //{
  //threadList.remove(this);
  //System.out.println("Thread-List: removed Runnable "+this);
  //}
  //}
  //};
  //threadList.add(r);
  //System.out.println("Thread-List: added Runnable "+r);
  //Thread t = new Thread(r,"ServiceTasksSR-Starter-"+(j+1));
  //t.setDaemon(true);
  //t.start();
  //}
  //while ( threadList.size() > 0 )
  //{
  //try
  //{
  //Thread.sleep(pause);
  //}
  //catch (InterruptedException ie)
  //{
  //ie.printStackTrace();
  //}
  //}
  System.out.println(
      "Start time for "+n+" instances of ServiceTasksSR (Type: SR, SvcTasks: 100, UsrTasks:0) = "
      +(System.currentTimeMillis()-(startTS+(n*sleepTime)))+" ms.");
  client.close();
  }
}

private void deploymentCreate()
{
WebClient client =
WebClient.create(CAMUNDA_REST_API);
client.type(MediaType.MULTIPART_FORM_DATA).path("/deployment/create");
List<Attachment> atts = new LinkedList<Attachment>();
String modelXml =
Bpmn.convertToString(ProcessGenerator.generateSequenceOf100AsyncServiceTasks());
ContentDisposition cd =
new ContentDisposition("form-data; name=\"data\";filename=\"process.bpmn\"");
atts.add(new Attachment("data", new ByteArrayInputStream(modelXml.getBytes()),cd));
ContentDisposition cd1 = new ContentDisposition("form-data;name=\"enable-duplicate-filtering\";");
atts.add(new Attachment("enable-duplicate-filtering",new
ByteArrayInputStream("true".getBytes()),cd1));
//
ContentDisposition cd2 = new ContentDisposition("form-data;name=\"deployment-source\";");
//
atts.add(new Attachment("deployment-source",new ByteArrayInputStream("performance test".getBytes()),cd2));
ContentDisposition cd3 = new ContentDisposition("form-data;name=\"deployment-name\";");
atts.add(new Attachment("deployment-name",new ByteArrayInputStream("performance test".getBytes()),cd3));
MultipartBody body = new MultipartBody(atts);
Response response = client.post(body);
assertEquals(200, response.getStatus());
}

  private class DeploymentResponse {
    String id;
    String name;
    String source;
    String tenantId;
    Date deploymentTime;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getTenantId() {
      return tenantId;
    }

    public void setTenantId(String tenantId) {
      this.tenantId = tenantId;
    }

    public Date getDeploymentTime() {
      return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
      this.deploymentTime = deploymentTime;
    }
  }
}