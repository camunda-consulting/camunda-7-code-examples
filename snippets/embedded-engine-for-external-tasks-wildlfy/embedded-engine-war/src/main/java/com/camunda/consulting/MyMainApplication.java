package com.camunda.consulting;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Startup
@Singleton
public class MyMainApplication {
  
  private static final Logger LOG = LoggerFactory.getLogger(MyMainApplication.class);
  
  public static ProcessEngine processEngine;

  @PostConstruct
  public void startEngine() {
    LOG.info("Starting engine");
    
    processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().buildProcessEngine();
    
    deployProcessDiagrams();
  }
  
  @PreDestroy
  public void stopEngine() {
    LOG.info("Stopping engine");
    
    processEngine.close();
  }
  
  public void deployProcessDiagrams() {
    /* suboptimal, weil der temp dateiname genutzt wird. Wo wird aus ProcessApplication deployed?
     * org.camunda.bpm.container.impl.deployment.scanning.VfsProcessApplicationScanner
    try {
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      Resource[] resources = resolver.getResources("classpath*:*.bpmn");
      LOG.info("resources: {}", resources);

      RepositoryService repositoryService = processEngine.getRepositoryService();

      DeploymentBuilder deploymentBuilder = repositoryService
        .createDeployment()
        .enableDuplicateFiltering(true)
        .name("processDeployment");
      
      for (Resource resource : resources) {
        String resourceName = null;

        if (resource instanceof ContextResource) {
          resourceName = ((ContextResource) resource).getPathWithinContext();

        } else if (resource instanceof ByteArrayResource) {
          resourceName = resource.getDescription();

        } else {
          resourceName = getFileResourceName(resource);
        }
        deploymentBuilder.addInputStream(resourceName, resource.getInputStream());
          
      }
      deploymentBuilder.deploy();
    } catch (IOException e) {
      LOG.error("Deployment of bpmn files failed");
    }
    */
  }
  
  protected String getFileResourceName(Resource resource) {
    try {
      return resource.getFile().getAbsolutePath();
    } catch (IOException e) {
      return resource.getFilename();
    }
  }
  
}
