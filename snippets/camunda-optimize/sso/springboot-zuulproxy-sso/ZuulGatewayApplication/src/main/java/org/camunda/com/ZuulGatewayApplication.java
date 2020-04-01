package org.camunda.com;

import org.camunda.com.filter.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Main class for Proxy Application.
 * Enable additional filter from Zuul Request Lifecycle.
 * See: https://github.com/Netflix/zuul/wiki/How-it-Works
 * @author NormanLüring
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulGatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();		
	}

}
