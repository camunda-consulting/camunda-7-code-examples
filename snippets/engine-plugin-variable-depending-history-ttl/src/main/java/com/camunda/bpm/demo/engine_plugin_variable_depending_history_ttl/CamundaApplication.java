package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("engine-plugin-variable-depending-history-ttl")
public class CamundaApplication
{

	public static void main(String... args)
	{
		SpringApplication.run(CamundaApplication.class, args);
	}

}
