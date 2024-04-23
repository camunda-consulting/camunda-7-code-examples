package org.camunda.consulting;

import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.identity.db.DbGroupQueryImpl;
import org.camunda.bpm.engine.impl.identity.db.DbIdentityServiceProvider;
import org.camunda.bpm.engine.impl.identity.db.DbUserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.camunda.bpm.engine.spring.SpringProcessEnginePlugin;
import org.camunda.consulting.util.UserExtractionUtility;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Configuration
public class IdentityProviderPlugin extends SpringProcessEnginePlugin {

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setIdentityProviderSessionFactory(new SessionFactory() {
      @Override
      public Class<?> getSessionType() {
        return ReadOnlyIdentityProvider.class;
      }

      @Override
      public Session openSession() {
        return new DbIdentityServiceProvider() {

          @Override
          public List<Group> findGroupByQueryCriteria(DbGroupQueryImpl query) {
            OidcUser oidcUser = UserExtractionUtility.getOidcUser();
            List<String> groupIds = UserExtractionUtility.extractGroups(oidcUser);

            if (groupIds != null && !groupIds.isEmpty()) {
              return groupIds.stream()
                  .map(groupId -> {
                    Group group = createNewGroup(groupId);
                    group.setName(groupId);
                    return group;
                  })
                  .collect(Collectors.toList());
            }
            return Collections.emptyList();
          }

          @Override
          public List<User> findUserByQueryCriteria(DbUserQueryImpl query) {
            String userId = query.getId();
            if (userId != null) {
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication != null && authentication.getPrincipal() instanceof OidcUser) {
                OidcUser oidcUser = UserExtractionUtility.getOidcUser();
                String name = oidcUser.getGivenName();
                if (name != null && !name.isEmpty()) {
                  UserEntity user = new UserEntity();
                  user.setId(oidcUser.getPreferredUsername());
                  user.setFirstName(oidcUser.getGivenName());
                  user.setLastName(oidcUser.getFamilyName());
                  user.setEmail(oidcUser.getEmail());
                  return Collections.singletonList(user);
                }
              }
            }
            return Collections.emptyList();
          }
        };
      }
    });
  }
}