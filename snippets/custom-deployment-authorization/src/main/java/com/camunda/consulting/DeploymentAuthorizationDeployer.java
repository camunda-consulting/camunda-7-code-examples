package com.camunda.consulting;

import com.camunda.consulting.DeploymentAuthorizationProperties.DeploymentAuthorizationListing;
import org.camunda.bpm.engine.AuthorizationException;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.dmn.entity.repository.DecisionDefinitionEntity;
import org.camunda.bpm.engine.impl.dmn.entity.repository.DecisionRequirementsDefinitionEntity;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.repository.ResourceDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DeploymentAuthorizationDeployer implements Deployer {
  private static final Logger LOG = LoggerFactory.getLogger(DeploymentAuthorizationDeployer.class);
  private final DeploymentAuthorizationProperties deploymentAuthorizationDescription;

  public DeploymentAuthorizationDeployer(DeploymentAuthorizationProperties deploymentAuthorizationDescription) {this.deploymentAuthorizationDescription = deploymentAuthorizationDescription;}

  @Override
  public void deploy(DeploymentEntity deployment) {
    Authentication authentication = Context
        .getCommandContext()
        .getAuthentication();
    if (authentication == null) {
      return;
    }
    Map<String, Set<String>> unauthorizedResources = deployment
        .getDeployedArtifacts()
        .values()
        .stream()
        .map(l -> (List<?>) l)
        .flatMap(Collection::stream)
        .filter(r -> ResourceDefinition.class.isAssignableFrom(r.getClass()))
        .map(ResourceDefinition.class::cast)
        .map(resourceDefinition -> check(resourceDefinition, authentication))
        .filter(r -> r.failed)
        .collect(Collectors.groupingBy(r -> r.resourceType,
            Collectors.mapping(r -> r.resourceKey, Collectors.toSet())
        ));
    if (unauthorizedResources.isEmpty()) {
      return;
    }
    throw new AuthorizationException("User " + authentication.getUserId() + " is not allowed to deploy resources: " + unauthorizedResources);
  }

  private AuthorizationResult check(ResourceDefinition resourceDefinition, Authentication authentication) {
    ResourceBinding resourceBinding = ResourceBinding.findForClass(resourceDefinition.getClass());
    AuthorizationResult result = new AuthorizationResult();
    result.resourceKey = resourceDefinition.getKey();
    result.resourceType = resourceBinding.getResourceType();
    result.failed = true;
    DeploymentAuthorizationListing listing = resourceBinding.findListing(deploymentAuthorizationDescription);
    if (listing
        .getByUser()
        .getOrDefault(authentication.getUserId(), Collections.emptySet())
        .contains(resourceDefinition.getKey())) {
      result.failed = false;
    }
    if (authentication.getGroupIds() != null) {
      for (String groupId : authentication.getGroupIds()) {
        if (listing
            .getByGroup()
            .getOrDefault(groupId, Collections.emptySet())
            .contains(resourceDefinition.getKey())) {
          result.failed = false;
        }
      }
    }
    LOG.info("{}",result);
    return result;
  }

  private static class AuthorizationResult {
    private String resourceType;
    private String resourceKey;
    private Boolean failed;

    @Override
    public String toString() {
      return "AuthorizationResult{" + "resourceType='" + resourceType + '\'' + ", resourceKey='" + resourceKey + '\'' + ", failed=" + failed + '}';
    }
  }

  private enum ResourceBinding {
    BPMN {
      @Override
      public String getResourceType() {
        return "process";
      }

      @Override
      public DeploymentAuthorizationListing findListing(DeploymentAuthorizationProperties deploymentAuthorizationProperties) {
        return deploymentAuthorizationProperties.getBpmn();
      }

      @Override
      public Class<? extends ResourceDefinition> getAssociatedResourceClass() {
        return ProcessDefinitionEntity.class;
      }
    }, DMN {
      @Override
      public String getResourceType() {
        return "decision";
      }

      @Override
      public DeploymentAuthorizationListing findListing(DeploymentAuthorizationProperties deploymentAuthorizationProperties) {
        return deploymentAuthorizationProperties.getDmn();
      }

      @Override
      public Class<? extends ResourceDefinition> getAssociatedResourceClass() {
        return DecisionDefinitionEntity.class;
      }
    }, DRD {
      @Override
      public String getResourceType() {
        return "DRD";
      }

      @Override
      public DeploymentAuthorizationListing findListing(DeploymentAuthorizationProperties deploymentAuthorizationProperties) {
        return deploymentAuthorizationProperties.getDrd();
      }

      @Override
      public Class<? extends ResourceDefinition> getAssociatedResourceClass() {
        return DecisionRequirementsDefinitionEntity.class;
      }
    };

    public abstract String getResourceType();

    public abstract DeploymentAuthorizationListing findListing(DeploymentAuthorizationProperties deploymentAuthorizationProperties);

    public abstract Class<? extends ResourceDefinition> getAssociatedResourceClass();

    public static ResourceBinding findForClass(Class<? extends ResourceDefinition> resourceClass) {
      for (ResourceBinding binding : ResourceBinding.values()) {
        if (binding
            .getAssociatedResourceClass()
            .equals(resourceClass)) {
          return binding;
        }
      }
      throw new IllegalStateException("Cannot find resource class for " + resourceClass);
    }
  }
}
