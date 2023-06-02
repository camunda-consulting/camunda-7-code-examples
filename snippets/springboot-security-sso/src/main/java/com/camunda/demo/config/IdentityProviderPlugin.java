package com.camunda.demo.config;

import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.identity.WritableIdentityProvider;
import org.camunda.bpm.engine.impl.identity.db.DbGroupQueryImpl;
import org.camunda.bpm.engine.impl.identity.db.DbIdentityServiceProvider;
import org.camunda.bpm.engine.impl.identity.db.DbUserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.camunda.bpm.engine.spring.SpringProcessEnginePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class IdentityProviderPlugin extends SpringProcessEnginePlugin {

  @Autowired
  public UserDetailsService userDetailsService;

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.setIdentityProviderSessionFactory(new SessionFactory() {
      @Override
      public Class<?> getSessionType() {
        return WritableIdentityProvider.class;
      }

      @Override
      public Session openSession() {
        return new DbIdentityServiceProvider() {

          @Override
          public List<Group> findGroupByQueryCriteria(DbGroupQueryImpl query) {
            List<Group> groups = super.findGroupByQueryCriteria(query);
            if (!groups.isEmpty()) {
              return groups;

            } else {
              String userId = query.getUserId();
              if (userId != null) {
                UserDetails userDetails = null;

                try {
                  userDetails = userDetailsService.loadUserByUsername(userId);

                } catch (UsernameNotFoundException e) {
                  return Collections.emptyList();
                }

                List<String> groupIds = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(res -> res.substring(5)) // Strip "ROLE_"
                    .collect(Collectors.toList());

                if (!groupIds.isEmpty()) {
                  return groupIds.stream()
                      .map(groupId -> {
                        Group group = createNewGroup(groupId);
                        group.setName(groupId);
                        return group;
                      })
                      .collect(Collectors.toList());

                } else {
                  return Collections.emptyList();

                }
              } else {
                return Collections.emptyList();

              }
            }
          }

          @Override
          public List<User> findUserByQueryCriteria(DbUserQueryImpl query) {
            List<User> users = super.findUserByQueryCriteria(query);
            if (!users.isEmpty()) {
              return users;

            } else {
              String userId = query.getId();
              if (userId != null) {
                UserDetails userDetails = null;

                try {
                  userDetails = userDetailsService.loadUserByUsername(userId);

                } catch (UsernameNotFoundException e) {
                  return Collections.emptyList();
                }

                if (userDetails != null) {
                  UserEntity userEntity = new UserEntity();
                  String username = userDetails.getUsername();
                  userEntity.setId(username);

                  userEntity.setFirstName(username);
                  userEntity.setLastName(username);

                  return Collections.singletonList(userEntity);

                } else {
                  return Collections.emptyList();

                }

              } else {
                return Collections.emptyList();

              }
            }
          }
        };
      }
    });
  }

}
