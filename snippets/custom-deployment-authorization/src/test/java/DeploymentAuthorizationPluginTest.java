import org.camunda.bpm.engine.AuthorizationException;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ProcessEngineExtension.class)
public class DeploymentAuthorizationPluginTest {

  @BeforeEach
  public void reset(ProcessEngine processEngine) {
    processEngine
        .getIdentityService()
        .clearAuthentication();
    processEngine
        .getRepositoryService()
        .createDeploymentQuery()
        .list()
        .stream()
        .map(Deployment::getId)
        .forEach(processEngine.getRepositoryService()::deleteDeployment);
  }

  @Test
  void shouldNotDeployUnauthorizedResource(ProcessEngine processEngine) {
    processEngine
        .getIdentityService()
        .setAuthenticatedUserId("demo");
    assertThrows(
        AuthorizationException.class,
        () -> processEngine
            .getRepositoryService()
            .createDeployment()
            .addClasspathResource("test-decision.dmn")
            .name("test-deployment-1")
            .source("test")
            .deployWithResult()
    );
    assertEquals(
        0L,
        processEngine
            .getRepositoryService()
            .createDeploymentQuery()
            .count()
    );
  }

  @Test
  void shouldDeployAuthorizedResource(ProcessEngine processEngine) {
    processEngine
        .getIdentityService()
        .setAuthenticatedUserId("demo");
    processEngine
        .getRepositoryService()
        .createDeployment()
        .addClasspathResource("test-process.bpmn")
        .name("test-deployment-2")
        .source("test")
        .deployWithResult();
    assertEquals(
        1,
        processEngine
            .getRepositoryService()
            .createDeploymentQuery()
            .count()
    );
  }

}
