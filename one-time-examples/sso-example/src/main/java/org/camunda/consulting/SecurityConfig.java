package org.camunda.consulting;


import java.util.Collections;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.plugin.AdministratorAuthorizationPlugin;
import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/camunda/app/**").authenticated() // All requests require authentication
        )
        .oauth2Login(oauth2Login ->
            oauth2Login
                .defaultSuccessUrl("/", true)
        )
        .csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }

  @Bean
  public ProcessEnginePlugin administratorAuthorizationPlugin() {
    AdministratorAuthorizationPlugin administratorAuthorizationPlugin = new AdministratorAuthorizationPlugin();
    administratorAuthorizationPlugin.setAdministratorGroupName("your-admin-group");
    administratorAuthorizationPlugin.setAdministratorUserName("your-admin-user");
    return administratorAuthorizationPlugin;
  }

  @Bean
  public FilterRegistrationBean containerBasedAuthenticationFilter(){

    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
    filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", CustomAuthenticationProvider.class.getCanonicalName()));
    filterRegistration.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
    filterRegistration.addUrlPatterns("/camunda/app/*");
    return filterRegistration;
  }
}