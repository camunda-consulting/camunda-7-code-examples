package com.camunda.example.oauth2.config;

import com.camunda.example.oauth2.filter.CamundaAuthenticationFilter;
import com.camunda.example.oauth2.filter.WebAppAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collections;

@Configuration
@EnableWebSecurity(debug = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebAppSecurityConfig.class.getName());

    @Autowired
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/camunda/app/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService);

        http.csrf().disable();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
    public FilterRegistrationBean<CamundaAuthenticationFilter> containerBasedAuthenticationFilter() {

        logger.info("++++++++ WebAppSecurityConfig.containerBasedAuthenticationFilter()....");
        FilterRegistrationBean<CamundaAuthenticationFilter> filterRegistration
                = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new CamundaAuthenticationFilter());
        filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", WebAppAuthenticationProvider.class.getName()));
        filterRegistration.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;

    }

    //@Bean
    //public RequestContextListener requestContextListener() {
    //    return new RequestContextListener();
    //}

}
