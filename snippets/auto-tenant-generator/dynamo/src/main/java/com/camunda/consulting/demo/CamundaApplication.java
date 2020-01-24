package com.camunda.consulting.demo;

//import org.camunda.bpm.application.PostDeploy;
//import org.camunda.bpm.application.impl.metadata.ProcessArchiveXmlImpl;
//import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("poc-camunda-mindstrong")
public class CamundaApplication {
  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }
  
//  @PostDeploy
//  private void onPostDeployment(ProcessEngine processEngine) {	  
//	  processEngine.getRepositoryService().createDeployment()
//	  	.tenantId("tenant-1")
//	  	.addClasspathResource("")
//	  	.deploy().getTenantId();
//  }
  
}
