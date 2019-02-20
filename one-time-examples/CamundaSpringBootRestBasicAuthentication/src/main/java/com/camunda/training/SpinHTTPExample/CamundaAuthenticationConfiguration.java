package com.camunda.training.SpinHTTPExample;

import javax.servlet.Filter;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaAuthenticationConfiguration {

	@Bean
	public FilterRegistrationBean processEngineAuthenticationFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setName("camunda-auth");
		registration.setFilter(getProcessEngineAuthenticationFilter());
		registration.addInitParameter("authentication-provider",
				"org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public Filter getProcessEngineAuthenticationFilter() {
		return new ProcessEngineAuthenticationFilter();
	}
}
