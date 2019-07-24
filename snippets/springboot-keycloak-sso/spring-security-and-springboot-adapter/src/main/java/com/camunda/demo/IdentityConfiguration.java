package com.camunda.demo;

import de.vonderbeck.bpm.identity.keycloak.plugin.KeycloakIdentityProviderPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityConfiguration {

  @Bean
  @ConfigurationProperties(prefix="plugin.identity.keycloak")
  public KeycloakIdentityProviderPlugin keycloakIdentityProvider (){
    return new KeycloakIdentityProviderPlugin();
  }

}
