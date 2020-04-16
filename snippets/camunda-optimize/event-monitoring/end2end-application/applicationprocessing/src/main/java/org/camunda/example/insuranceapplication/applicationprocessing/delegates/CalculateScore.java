package org.camunda.example.insuranceapplication.applicationprocessing.delegates;

import java.time.ZoneId;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CalculateScore implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		/*NewApplication application = (NewApplication) execution.getVariable("application");
		int yearLastDigit = application.getApplicant().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() % 10;
		 */
		int yearLastDigit = 3;
		int score = 97;
		
		if(yearLastDigit == 3) {
			score = 93;
		} else if (yearLastDigit == 5) {
			score = 82;
		} 
		
		execution.setVariable("score", score);
	}

}
