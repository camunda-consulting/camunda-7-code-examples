package com.camunda.demo.authentication;

import de.vonderbeck.bpm.identity.keycloak.plugin.KeycloakIdentityProviderPlugin;
import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Configuration
public class WebAppSecurityConfig {


    @Bean
    public FilterRegistrationBean containerBasedAuthenticationFilter() {

        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
        filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", "com.camunda.demo.authentication.KeycloakAuthenticationProvider"));
        filterRegistration.setOrder(101);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Component
    @ConfigurationProperties(prefix="plugin.identity.keycloak")
    public class KeycloakIdentityProvider extends KeycloakIdentityProviderPlugin {
    }
}