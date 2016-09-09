package org.camunda.hackdays2016.BanMadInkProcesses;

import java.net.InetAddress;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.history.HistoricDecisionInputInstance;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.fasterxml.jackson.databind.ObjectMapper;


public class HistoricDecisionListener implements ExecutionListener {

	protected static final Logger LOGGER = Logger.getLogger(HistoricDecisionListener.class.getName());
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {

		HistoryService historyService = execution.getProcessEngineServices().getHistoryService();
		
		HistoricDecisionInstance historicDecisionInstance = historyService.createHistoricDecisionInstanceQuery()
																	.includeInputs()
																	.includeOutputs()
																	.decisionDefinitionKey((String)execution.getVariableLocal("tableName"))
																	.processInstanceId(execution.getProcessInstanceId())
																	.singleResult();
	
		//Fill a new object with stuff... 
		FraudScoreTableObject fraudData = new FraudScoreTableObject();
		
		fraudData.setFraudInstanceID(historicDecisionInstance.getId());
		
		List<HistoricDecisionInputInstance> inputs = historicDecisionInstance.getInputs();
		for (HistoricDecisionInputInstance historicDecisionInputInstance : inputs) {
			String inputName = historicDecisionInputInstance.getTypeName();
			if(inputName.equals("paymentRejected")){
				fraudData.setPaymentRejected((Boolean)historicDecisionInputInstance.getValue());
			}
			else if(inputName.equals("numberOfPayouts")){
				fraudData.setNumberOfPayouts((Integer)historicDecisionInputInstance.getValue());
			}
			else if(inputName.equals("historyOfFraud")){
				fraudData.setHistoryOfFraud((Boolean)historicDecisionInputInstance.getValue());
			}
			else if(inputName.equals("claimAmount")){
				fraudData.setCalimAmount((Long)historicDecisionInputInstance.getValue());			}

			
		}
		List<HistoricDecisionOutputInstance> outputs = historicDecisionInstance.getOutputs();
		for (HistoricDecisionOutputInstance historicDecisionOutputInstance : outputs) {
			
			String inputName = historicDecisionOutputInstance.getTypeName();
			if(inputName.equals("frausScore")){
				fraudData.setFraudScore((Integer)historicDecisionOutputInstance.getValue());
				
			}
			
			
		}		
		
		ObjectMapper mapper = new ObjectMapper();

		 String serializedHistoricDecisionInstance = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fraudData);
					
		Client client = TransportClient.builder().build()
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
				
		
		IndexResponse response = client.prepareIndex("camunda", "fraudData", historicDecisionInstance.getId())
		        .setSource(serializedHistoricDecisionInstance)
		        .get();
		
		LOGGER.info(response.getId());
		
	}

}
