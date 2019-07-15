package com.camunda.demo.rest;

import com.camunda.demo.domain.EventTypeConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class GenerateModelController {
    
    @PostMapping("/model")
	public String createModel(@RequestParam("eventTypeConfiguration") EventTypeConfiguration configuration) {
		
		return null;
	}
}