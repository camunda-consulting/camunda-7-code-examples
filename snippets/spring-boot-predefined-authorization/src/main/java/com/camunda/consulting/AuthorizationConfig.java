package com.camunda.consulting;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Configuration
public class AuthorizationConfig extends AuthorizationProperties {
  private static final Logger LOG = LoggerFactory.getLogger(AuthorizationConfig.class);

  @Bean
  public AuthorizationRules getRules() {
    return this
        .getRuleProperties()
        .stream()
        .map(this::create)
        .collect(collect());
  }

  private AuthorizationRuleCreationResult create(AuthorizationRuleProperties properties) {
    AuthorizationRuleCreationResult result = new AuthorizationRuleCreationResult();
    // user and group
    this.setPermissions(result, properties.getPermissions());
    this.setResource(result, properties.getResource());
    result.rule.setResourceId(properties.getResourceId() != null ? properties.getResourceId() : Authorization.ANY);
    this.setType(result, properties.getType());
    this.setUserOrGroup(result, properties.getUser(), properties.getGroup());
    return result;
  }

  private void setPermissions(AuthorizationRuleCreationResult result, Set<String> permissions) {
    permissions.forEach(permission -> {
      try {
        Permissions p = Permissions.valueOf(permission);
        result.rule
            .getPermissions()
            .add(p);
      } catch (Exception e) {
        String message = permission + " is not a valid permission";
        LOG.error(message, e);
        result.errors.add(message);
      }
    });
  }

  private void setResource(AuthorizationRuleCreationResult result, String resource) {
    try {
      Resources r = Resources.valueOf(resource);
      result.rule.setResource(r);
    } catch (Exception e) {
      String message = resource + " is not a valid resource";
      LOG.error(message, e);
      result.errors.add(message);
    }
  }

  private void setType(AuthorizationRuleCreationResult result, String type) {
    try {
      AuthorizationType t = AuthorizationType.valueOf(type);
      result.rule.setType(t.getType());
    } catch (Exception e) {
      String message = type + " is not a valid authorization type";
      LOG.error(message, e);
      result.errors.add(message);
    }
  }

  private void setUserOrGroup(AuthorizationRuleCreationResult result, String user, String group) {
    boolean globalType = result.rule.getType() == Authorization.AUTH_TYPE_GLOBAL;
    if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(group)) {
      result.errors.add("Either user OR group must be set for one rule");
      return;
    }
    if (StringUtils.isBlank(user) && StringUtils.isBlank(group)) {
      if (globalType) {
        return;
      } else {
        result.errors.add("Either user OR group must be set for one rule");
        return;
      }
    }
    if (globalType) {
      result.errors.add("On type GLOBAL, no user or group must be set");
      return;
    }
    result.rule.setGroup(group);
    result.rule.setUser(user);
  }

  private static class AuthorizationRuleCreationResult {
    private final AuthorizationRule rule = new AuthorizationRule();
    private final Set<String> errors = new HashSet<>();
  }

  private enum AuthorizationType {
    GRANT(Authorization.AUTH_TYPE_GRANT),
    REVOKE(Authorization.AUTH_TYPE_REVOKE),
    GLOBAL(Authorization.AUTH_TYPE_GLOBAL);

    private final int type;

    AuthorizationType(int type) {
      this.type = type;
    }

    public int getType() {
      return this.type;
    }

  }

  private static Collector<AuthorizationRuleCreationResult, Set<AuthorizationRuleCreationResult>, AuthorizationRules>

  collect() {
    return new Collector<AuthorizationConfig.AuthorizationRuleCreationResult, Set<AuthorizationRuleCreationResult>, AuthorizationRules>() {

      @Override
      public Supplier<Set<AuthorizationRuleCreationResult>> supplier() {
        return HashSet::new;
      }

      @Override
      public BiConsumer<Set<AuthorizationRuleCreationResult>, AuthorizationRuleCreationResult> accumulator() {
        return Set::add;
      }

      @Override
      public BinaryOperator<Set<AuthorizationRuleCreationResult>> combiner() {
        return (left, right) -> {
          if (left.size() > right.size()) {
            left.addAll(right);
            return left;
          } else {
            right.addAll(left);
            return right;
          }
        };
      }

      @Override
      public Function<Set<AuthorizationRuleCreationResult>, AuthorizationRules> finisher() {
        return set -> {
          Set<String> errors = set
              .stream()
              .flatMap(result -> result.errors.stream())
              .collect(Collectors.toSet());
          if (errors.isEmpty()) {
            return new AuthorizationRules(set
                .stream()
                .map(result -> result.rule)
                .collect(Collectors.toSet()));
          } else {
            throw new RuntimeException("Please adjust authorization properties: " + StringUtils.LF + String.join(
                StringUtils.LF,
                errors
            ));
          }
        };
      }

      @Override
      public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
      }

    };
  }
}
