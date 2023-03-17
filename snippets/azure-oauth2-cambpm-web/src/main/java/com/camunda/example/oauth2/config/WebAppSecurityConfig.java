package com.camunda.example.oauth2.config;

import com.azure.spring.cloud.autoconfigure.aad.AadWebSecurityConfigurerAdapter;
import java.util.Collections;
import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.context.request.RequestContextListener;

/** Used for Azure AD security. */
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
@Configuration
public class WebAppSecurityConfig extends AadWebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // use required configuration form AADWebSecurityAdapter.configure:
    super.configure(http);
    // add custom configuration:
    http.authorizeRequests()
        .antMatchers("/camunda/**")
        .authenticated() // limit these pages to authenticated users (default: /token_details)
        .antMatchers("/**")
        .permitAll(); // allow all other routes.
  }

  @Bean
  public FilterRegistrationBean<ContainerBasedAuthenticationFilter>
      containerBasedAuthenticationFilter() {

    FilterRegistrationBean<ContainerBasedAuthenticationFilter> filterRegistration =
        new FilterRegistrationBean<>();
    filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
    filterRegistration.setInitParameters(
        Collections.singletonMap(
            "authentication-provider",
            SpringSecurityOAuth2AuthenticationProvider.class.getCanonicalName()));
    filterRegistration.setOrder(
        101); // make sure the filter is registered after the Spring Security Filter Chain
    filterRegistration.addUrlPatterns("/camunda/*");
    return filterRegistration;
  }

  @Bean
  @Order(0)
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }
}
