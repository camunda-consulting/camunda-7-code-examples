package org.camunda.example.insuranceapplication.input.facade;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.camunda.example.insuranceapplication.input.services.InputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class RestInputController {

	private static final Logger logger = LoggerFactory.getLogger(RestInputController.class);
	
	@Autowired
	InputService inputService;
	
	@RequestMapping(path = "/api/input/request/digital", method = POST)
	public String placeRequestDigital(@RequestBody String application) throws JsonProcessingException {
		inputService.createApplication(application, "digital");
		return "success";
	}
	
	@RequestMapping(path = "/api/input/request/analog", method = POST)
	public String placeRequestAnalog(@RequestBody String application) throws JsonProcessingException {
		inputService.createApplication(application, "analog");
		return "success";
	}
}
