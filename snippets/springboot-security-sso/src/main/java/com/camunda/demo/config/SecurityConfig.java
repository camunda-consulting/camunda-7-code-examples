package com.camunda.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    // This is just a very simple Identity Management solution for demo purposes.
    // In real world scenarios, this would be replaced by the actual IAM solution
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("demo").password("demo").roles("ACTUATOR", "camunda-admin").build());
        manager.createUser(User.withUsername("john").password("john").roles("camunda-user").build());
        return manager;
    }

}