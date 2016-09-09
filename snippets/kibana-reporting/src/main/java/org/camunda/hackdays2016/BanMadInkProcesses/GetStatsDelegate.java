package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.history.HistoricDecisionInputInstance;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;

public class GetStatsDelegate implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		//HashMap<String, Integer> claimTypes = new HashMap<String, Integer>();
		int numberOfFrauds = 0;
		HashMap<String, Integer> fraudScores = new HashMap<String, Integer>();
		
		List<HistoricDecisionInstance> fraudDecisionsList = execution.getProcessEngineServices().getHistoryService().createHistoricDecisionInstanceQuery()
			.decisionDefinitionKey("fraudRating")
			.includeInputs()
			.includeOutputs()
			.list();
		
		for (Iterator<HistoricDecisionInstance> iterator = fraudDecisionsList.iterator(); iterator.hasNext();) {
			HistoricDecisionInstance historicDecisionInstance = (HistoricDecisionInstance) iterator.next();
			
			List<HistoricDecisionInputInstance> inputs =  historicDecisionInstance.getInputs();
			for (HistoricDecisionInputInstance historicDecisionInputInstance : inputs) {
				String inputName = historicDecisionInputInstance.getTypeName();
				if(inputName.equals("historyOfFraud")){
					if((Boolean)historicDecisionInputInstance.getValue())
					numberOfFrauds++;
				}
			}
			
			List<HistoricDecisionOutputInstance> outputs = historicDecisionInstance.getOutputs();
			
			for (HistoricDecisionOutputInstance historicDecisionOutputInstance : outputs) {
				int fraudScore = (Integer)historicDecisionOutputInstance.getValue();
				
				if(fraudScores.containsKey(fraudScore)){
					int currentFraudScore = fraudScores.get(fraudScore);
					fraudScores.put(Integer.toString(fraudScore), currentFraudScore++);
				}else{
					fraudScores.put(Integer.toString(fraudScore), 1);
				}
				
			}

			
		}
		

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Number of Frauds Detected: " + numberOfFrauds);
		System.out.println();
		System.out.println();
		System.out.println();
		
		for (String score: fraudScores.keySet()){

            String key =score.toString();
            String value = fraudScores.get(score).toString();  
            System.out.println("The Fraud Score --"+ key + "-- Was detected --" + value);  

} 
		
	}

}
