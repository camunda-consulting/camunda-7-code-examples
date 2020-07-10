package com.camunda.example.oauth2.config;

import com.camunda.example.oauth2.filter.RestAuthenticationFilter;
import com.microsoft.azure.spring.autoconfigure.aad.AADAppRoleStatelessAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 20)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(RestSecurityConfig.class.getName());

    private AADAppRoleStatelessAuthenticationFilter aadAuthFilter;

    public RestSecurityConfig(AADAppRoleStatelessAuthenticationFilter aadAuthFilter) {
        this.aadAuthFilter = aadAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("++++++++ RestSecurityConfig.configure()....");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.authorizeRequests().antMatchers("/rest/**", "/engine-rest/**").authenticated();
        http.addFilterBefore(aadAuthFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public FilterRegistrationBean statelessUserAuthenticationFilter() {

        logger.info("++++++++ RestSecurityConfig.statelessUserAuthenticationFilter()....");

        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new RestAuthenticationFilter());
        filterRegistration.setOrder(102); // make sure the filter is registered after the Spring Security Filter Chain
        filterRegistration.addUrlPatterns("/rest/*", "/engine-rest/*");
        return filterRegistration;
    }

}