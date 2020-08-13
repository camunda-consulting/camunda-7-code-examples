package com.camunda.com.meetup;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Controller
public class CheckInController {

	private final Logger logger = Logger.getLogger(CheckInController.class.getName());

	@Autowired
	RuntimeService runtimeService;

	@GetMapping("/checkin")
	public String workorderForm(Model model) {
		model.addAttribute("checkin", new CheckIn());
		return "index";
	}

	@PostMapping("/checkin")
	public String workorderSubmit(Model model, @ModelAttribute CheckIn checkin) {

		logger.info(" just received a checkin...");

		Random rd = new Random();

		String email = checkin.getEmail();
		Boolean hotelCreditCard = rd.nextBoolean();
		int yearsWithMembership = (int) ((Math.random() * (20 - 1)) + 1);
		Boolean weekend = rd.nextBoolean();
		List<String> givenList = Arrays.asList("Bronze", "Silver", "Gold");
		Random rand = new Random();
		String clubMembership = givenList.get(rand.nextInt(givenList.size()));

		String completeTaskUrl = "http://localhost:8080/engine-rest/message";
		RestTemplate restTemplateComplete = new RestTemplate();
		HttpHeaders headersComplete = new HttpHeaders();
		headersComplete.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entityComplete = new HttpEntity<String>("{\"messageName\":\"check\",\"processVariables\":"
				+ " {\"bookingInfo\": {\"value\": \"{ \\\"hotelCreditCard\\\":" + hotelCreditCard
				+ ",\\\"yearsWithMembership\\\":" + yearsWithMembership + "," + "\\\"weekend\\\":" + weekend
				+ ",\\\"clubMembership\\\":\\\"" + clubMembership + "\\\",\\\"email\\\":\\\"" + email
				+ "\\\"}\",\"type\": \"object\","
				+ "\"valueInfo\": {\"objectTypeName\": \"java.lang.Object\",\"serializationDataFormat\": "
				+ "\"application/json\"}}}}", headersComplete);
		// HttpEntity<String> entityComplete = new HttpEntity<String>("{\"variables\": {
		// \"approved\": {\"value\": true}}}",
		// headersComplete);
		restTemplateComplete.exchange(completeTaskUrl, HttpMethod.POST, entityComplete, String.class);

		model.addAttribute("checkin", checkin);

		return "result";
	}

}
