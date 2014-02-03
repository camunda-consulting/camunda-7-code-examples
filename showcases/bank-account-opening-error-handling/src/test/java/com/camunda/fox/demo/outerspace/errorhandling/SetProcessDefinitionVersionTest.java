package com.camunda.fox.demo.outerspace.errorhandling;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cmd.SetProcessDefinitionVersionCmd;
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
public class SetProcessDefinitionVersionTest {

  @Deployment
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
      .goOffline()
      .loadMetadataFromPom("pom.xml");

    // if you experience problems with the authentication to the camunda bpm
    // repository the wrong maven configuration might be used.
    // use this code to use your maven settings.xml in this case:
    // .configureFrom(".../settings.xml")

    return ShrinkWrap
            .create(WebArchive.class, "bank-account-opening-error-handling.war")
            // prepare as process application archive for camunda BPM platform
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            .addAsLibraries(resolver.artifact("commons-lang:commons-lang").resolveAsFiles())
//            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "com.camunda.fox.demo.outerspace.errorhandling") // not recursive to skip package 'nonarquillian'
            // add process definition
//            .addAsResource("open-account-errorhandling.bpmn")
    // now you can add additional stuff required for your test case
    ;
  }
  
  @Test
  public void testSetProcessDefinitionVersionCommand() {
    String processInstanceId =
      "10bea61f-8a60-11e3-8827-f0def18a13c6";
    int newVersion = 13;
    SetProcessDefinitionVersionCmd command = 
      new SetProcessDefinitionVersionCmd(processInstanceId, newVersion);
    ((ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine())
        .getProcessEngineConfiguration()
        .getCommandExecutorTxRequired().execute(command);  
  }
}
