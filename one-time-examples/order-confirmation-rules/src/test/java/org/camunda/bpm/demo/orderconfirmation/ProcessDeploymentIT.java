package org.camunda.bpm.demo.orderconfirmation;

import java.io.File;

import org.camunda.bpm.engine.RuntimeService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class ProcessDeploymentIT {

    @Deployment
    public static WebArchive createDeployment() {

      File[] libs = Maven.resolver()
          .offline(false)
          .loadPomFromFile("pom.xml")
          .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class, "process-deployment-and-start-test.war")
                .addAsLibraries(libs)

                .addAsResource("OrderConfirmation.bpmn")

                .addPackage("org.camunda.bpm.demo.orderconfirmation.bean")
                .addPackage("org.camunda.bpm.demo.orderconfirmation.model")

                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")

                // prepare as process application archive for the camunda BPM platform
                .addAsResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml");
    }

    @Inject
    private RuntimeService runtimeService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testDeployment() throws InterruptedException {

        assertThat(runtimeService).isNotNull();

//        HashMap<String, Object> variables = new HashMap<String, Object>();
//        variables.put("orderId", 1);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("OrderConfirmation", variables);
//        String id = processInstance.getId();
//        assertThat(processInstance).isNotNull();
//        System.out.println("Started process instance id " + id);
//        List<String> activityIds = runtimeService.getActiveActivityIds(id);
//
//        assertThat(activityIds.size()).isEqualTo(1);
//        assertThat("edit").isIn(activityIds);
    }

}
