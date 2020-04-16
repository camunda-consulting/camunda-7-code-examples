package org.camunda.example.insuranceapplication.applicationprocessing.rabbit;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.example.insuranceapplication.applicationprocessing.model.Message;
import org.camunda.example.insuranceapplication.applicationprocessing.model.NewApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class EventConsumer {
	private Logger logger = LoggerFactory.getLogger(EventConsumer.class);
	@Autowired
	private RuntimeService runtimeService;
	
	
	public void receive(String messageString) throws JsonMappingException, JsonProcessingException {
		logger.info("Received message '{}'", messageString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		Message message = mapper.readValue(messageString, Message.class);
	    NewApplication app = mapper.readValue((String)message.getData(), NewApplication.class);
	    String traceid = app.getTraceid();
	    Map<String, Object> variables = Variables.createVariables() //
        .putValueTyped("application",
            Variables.objectValue(app).serializationDataFormat(SerializationDataFormats.JSON).create());
		runtimeService.startProcessInstanceByKey("customer_onboarding", traceid, variables);
	}

}