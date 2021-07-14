package com.camunda.demo;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaSecurityConfig
{

	@Bean
	public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilter()
	{
		FilterRegistrationBean<ProcessEngineAuthenticationFilter> registration = new FilterRegistrationBean<>();
		registration.setName("camunda-auth");
		registration.setFilter(this.getProcessEngineAuthenticationFilter());
		registration
			.addInitParameter("authentication-provider", HttpBasicAuthenticationProvider.class.getName());
		registration.addUrlPatterns("/engine-rest/*");
		return registration;
	}

	@Bean
	public ProcessEngineAuthenticationFilter getProcessEngineAuthenticationFilter()
	{
		return new ProcessEngineAuthenticationFilter();
	}
}
