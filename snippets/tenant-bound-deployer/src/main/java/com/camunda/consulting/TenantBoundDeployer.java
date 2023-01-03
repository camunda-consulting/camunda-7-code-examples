package com.camunda.consulting;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;

public class TenantBoundDeployer implements Deployer {

  @Override
  public void deploy(DeploymentEntity deployment) {
    Authentication authentication = Context
        .getCommandContext()
        .getAuthentication();
    // only handle authorized access
    if (isAuthenticated(authentication)) {
      boolean isDeploymentWithoutTenant = isDeploymentWithoutTenant(deployment);
      boolean isNoTenantAssignedToAuthentication = isNoTenantAssignedToAuthentication(authentication);
      if (isDeploymentWithoutTenant && isNoTenantAssignedToAuthentication) {
        // TODO what if the deployment has no tenant and the authentication also has no tenant?
      }
      if (!isDeploymentWithoutTenant && isNoTenantAssignedToAuthentication) {
        // TODO what if the deployment has a tenant and the authentication has no tenant?
      }
      if (isDeploymentWithoutTenant && !isNoTenantAssignedToAuthentication) {
        // TODO what if the deployment has no tenant and the authentication has a tenant?
      }
      if (!isDeploymentWithoutTenant && !isNoTenantAssignedToAuthentication) {
        // nobody can deploy to a tenant he is not assigned to
        if (!isDeploymentTenantMatchesAssignedTenant(authentication, deployment)) {
          throw new ProcessEngineException("You don't have access to tenant '" + deployment.getTenantId() + "'.");
        }
      }

    }
  }

  private boolean isAuthenticated(Authentication authentication) {
    return authentication != null;
  }

  private boolean isDeploymentWithoutTenant(DeploymentEntity deployment) {
    return StringUtils.isBlank(deployment.getTenantId());
  }

  private boolean isNoTenantAssignedToAuthentication(Authentication authentication) {
    return authentication.getTenantIds() == null || authentication
        .getTenantIds()
        .isEmpty();
  }

  private boolean isDeploymentTenantMatchesAssignedTenant(Authentication authentication, DeploymentEntity deployment) {
    return authentication
        .getTenantIds()
        .contains(deployment.getTenantId());
  }

}
