package org.camunda.bpm.extension;

import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.Collections;

import static org.springframework.security.config.Customizer.*;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;

/**
 * Camunda Web application SSO configuration for usage with KeycloakIdentityProviderPlugin.
 */
@Configuration
public class WebAppSecurityConfig {
  private static final Logger LOG = LoggerFactory.getLogger(WebAppSecurityConfig.class);

  @Bean
  @Order(2)
  public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
    LOG.info("Configuring HTTP security");
    return http
        .csrf(csrf -> csrf.ignoringRequestMatchers(antMatcher("/camunda/api/**"), antMatcher("/engine-rest/**")))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(antMatcher("/camunda/assets/**"),
                antMatcher("/camunda/app/**"),
                antMatcher("/camunda/api/**"),
                antMatcher("/camunda/lib/**")
            )
            .authenticated()
            .anyRequest()
            .permitAll())
        .oauth2Login(withDefaults())
        .build();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Bean
  public FilterRegistrationBean containerBasedAuthenticationFilter() {
    LOG.info("Configuring authentication filter");
    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
    filterRegistration.setInitParameters(Collections.singletonMap(
        "authentication-provider",
        KeycloakAuthenticationProvider.class.getName()
    ));
    filterRegistration.setOrder(201); // make sure the filter is registered after the Spring Security Filter Chain
    filterRegistration.addUrlPatterns("/camunda/app/*");
    return filterRegistration;
  }

  // The ForwardedHeaderFilter is required to correctly assemble the redirect URL for OAUth2 login.
  // Without the filter, Spring generates an HTTP URL even though the container route is accessed through HTTPS.
  @Bean
  public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() {
    LOG.info("Configuring forwarded header filter");
    FilterRegistrationBean<ForwardedHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new ForwardedHeaderFilter());
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return filterRegistrationBean;
  }

  @Bean
  @Order(0)
  public RequestContextListener requestContextListener() {
    LOG.info("Configuring request context listener");
    return new RequestContextListener();
  }
}
