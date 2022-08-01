package com.camunda.consulting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DeploymentAuthorizationPlugin extends AbstractProcessEnginePlugin {
  private static final Logger LOG = LoggerFactory.getLogger(DeploymentAuthorizationPlugin.class);
  private static final String RESOURCE_NAME = "deployment-authorizations.yaml";
  private final ObjectMapper objectMapper = new YAMLMapper();

  private DeploymentAuthorizationProperties description;

  public void init() throws Exception {
    try (
        InputStream deploymentAuthorizationResource = getClass()
            .getClassLoader()
            .getResourceAsStream(RESOURCE_NAME)
    ) {
      if (deploymentAuthorizationResource != null) {
        description = objectMapper.readValue(deploymentAuthorizationResource, DeploymentAuthorizationProperties.class);
      } else {
        LOG.warn("No deployment authorizations found. Will apply engine default mechanism.");
      }
    }
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    if (processEngineConfiguration.isAuthorizationEnabled() && description != null) {
      Deployer authorizationDeployer = new DeploymentAuthorizationDeployer(description);
      init(
          processEngineConfiguration.getCustomPostDeployers(),
          ArrayList::new,
          processEngineConfiguration::setCustomPostDeployers
      ).add(authorizationDeployer);
    }
  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
    if (processEngine
        .getProcessEngineConfiguration()
        .isAuthorizationEnabled() && description != null) {

      Authorization authorization = processEngine
          .getAuthorizationService()
          .createAuthorizationQuery()
          .authorizationType(Authorization.AUTH_TYPE_GLOBAL)
          .resourceType(Resources.DEPLOYMENT)
          .hasPermission(Permissions.CREATE)
          .resourceId(Authorization.ANY)
          .singleResult();
      if (authorization == null) {
        authorization = processEngine
            .getAuthorizationService()
            .createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
        authorization.setResource(Resources.DEPLOYMENT);
        authorization.addPermission(Permissions.CREATE);
        authorization.setResourceId(Authorization.ANY);
        processEngine
            .getAuthorizationService()
            .saveAuthorization(authorization);
      }
    }
  }

  private <T> T init(T potentiallyNull, Supplier<T> supplier, Consumer<T> initializer) {
    if (potentiallyNull == null) {
      potentiallyNull = supplier.get();
      initializer.accept(potentiallyNull);
    }
    return potentiallyNull;
  }

}
