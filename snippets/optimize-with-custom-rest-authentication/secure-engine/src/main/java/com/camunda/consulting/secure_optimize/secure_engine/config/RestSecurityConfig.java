package com.camunda.consulting.secure_optimize.secure_engine.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.camunda.consulting.secure_optimize.secure_engine.filter.rest.CustomTokenFilter;
import com.camunda.consulting.secure_optimize.secure_engine.filter.rest.StatelessUserAuthenticationFilter;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 20)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .antMatcher("/rest/**")
        .authorizeRequests().anyRequest().authenticated()
      .and()
        .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .addFilterBefore(new CustomTokenFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new StatelessUserAuthenticationFilter(), FilterSecurityInterceptor.class);
  }

}
