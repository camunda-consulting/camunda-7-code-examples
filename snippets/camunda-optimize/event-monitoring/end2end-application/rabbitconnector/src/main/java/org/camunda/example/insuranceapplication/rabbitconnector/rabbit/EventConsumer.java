package org.camunda.example.insuranceapplication.rabbitconnector.rabbit;

import java.util.UUID;

import org.camunda.example.insuranceapplication.rabbitconnector.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class EventConsumer {
	private Logger logger = LoggerFactory.getLogger(EventConsumer.class);


	public void receive(String messageString) throws JsonMappingException, JsonProcessingException {
		logger.info("Received message '{}'", messageString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		Message message = mapper.readValue(messageString, Message.class);

		//send to optimize
		RestTemplate restTemplate = new RestTemplate();
		
		final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "mytoken");
        headers.set("Content-Type", "application/json");
        
        //Create a new HttpEntity
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode application = objectMapper.readTree((String) message.getData());
        String traceid = application.get("traceid").asText();
        
        ObjectNode optimizeEvent = objectMapper.createObjectNode();
        
        UUID uuid = UUID.randomUUID();
        optimizeEvent.put("id", uuid.toString());
        optimizeEvent.put("source", "rabbit");
        optimizeEvent.put("group", "application");
        optimizeEvent.put("specversion", "1.0");
        optimizeEvent.put("traceid", traceid);
        optimizeEvent.put("type", message.getEventName());
        optimizeEvent.put("time", message.getTime().toString()+("Z"));
        
        ArrayNode myArray = objectMapper.createArrayNode();
        myArray.add(optimizeEvent);
        
        final HttpEntity<String> entity = new HttpEntity<String>(myArray.toString(), headers);
        
        restTemplate.postForEntity(
        		"http://optimize:8090/api/ingestion/event/batch",
        		entity, String.class, headers);
	}

}
