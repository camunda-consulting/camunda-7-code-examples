package com.camunda.demo.config;

import com.camunda.demo.filter.webapp.SpringSecurityBasedUserAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/app/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic() // this is just an example, use any auth mechanism you like
                .and()
                .addFilterAfter(new SpringSecurityBasedUserAuthenticationFilter(),FilterSecurityInterceptor.class);
    }

}
