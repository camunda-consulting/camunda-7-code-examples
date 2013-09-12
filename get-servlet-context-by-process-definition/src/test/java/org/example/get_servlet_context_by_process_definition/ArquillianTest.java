package org.example.get_servlet_context_by_process_definition;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {
  
  private static final String PROCESS_DEFINITION_KEY = "get-servlet-context-by-process-definition";

  @Deployment
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
      .goOffline()
      .loadMetadataFromPom("pom.xml");
    
    return ShrinkWrap
            .create(WebArchive.class, "get-servlet-context-by-process-definition.war")
            // prepare as process application archive for camunda BPM Platform
            .addAsLibraries(resolver.artifact("org.camunda.bpm.javaee:camunda-ejb-client").resolveAsFiles())
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // boot persistence unit
            // add your own classes (could be done one by one as well)
            .addPackages(false, "org.example.get_servlet_context_by_process_definition") // not recursive to skip package 'nonarquillian'
            // add process definition
            .addAsResource("process.bpmn")
            // add process image for visualizations
            .addAsResource("process.png")
            // now you can add additional stuff required for your test case
    ;
  }
  
  @Inject
  private ProcessEngine processEngine;

  @Test
  public void testGetServletContextByProcessDefinition() {
	String processDefinitionId = processEngine.getRepositoryService()
		.createProcessDefinitionQuery()
		.processDefinitionKey(PROCESS_DEFINITION_KEY)
		.latestVersion()
		.singleResult()
		.getId();
	String servletContextPath = ProcessApplicationHelper.getServletContextPath(processDefinitionId);
	assertEquals("/" + PROCESS_DEFINITION_KEY, servletContextPath);
	
	servletContextPath = ProcessApplicationHelper.getServletContextPath(processEngine, processDefinitionId);
	assertEquals("/" + PROCESS_DEFINITION_KEY, servletContextPath);
  }

}
