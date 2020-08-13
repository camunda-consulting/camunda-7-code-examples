package com.camunda.com.meetup;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
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
import java.util.HashMap;
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
		checkin.setHotelCreditCard(rd.nextBoolean());
		checkin.setWeekend(rd.nextBoolean());
		checkin.setYearsWithMembership((int) ((Math.random() * (20 - 1)) + 1));
		List<String> givenList = Arrays.asList("Bronze", "Silver", "Gold");
		checkin.setClubMembership(givenList.get(rd.nextInt(givenList.size())));
		
		ObjectValue typedCustomerValue = Variables.objectValue(checkin).serializationDataFormat("application/json").create();
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("bookingInfo", typedCustomerValue);
		
		runtimeService.startProcessInstanceByMessage("check", variables);
		
	
		
		
		model.addAttribute("checkin", checkin);

		return "result";
	}

}
