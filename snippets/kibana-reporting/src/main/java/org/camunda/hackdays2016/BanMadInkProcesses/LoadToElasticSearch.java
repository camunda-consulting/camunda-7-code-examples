package org.camunda.hackdays2016.BanMadInkProcesses;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricDecisionInputInstance;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoadToElasticSearch implements JavaDelegate {
	
	protected static final Logger LOGGER = Logger.getLogger(LoadToElasticSearch.class.getName());
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
			Boolean getAllData = (Boolean)execution.getVariable("GettAllData");
			if(getAllData == null ){
				getAllData = false;
			}
			
			HistoryService historyService = execution.getProcessEngineServices().getHistoryService();
			RandomGeneratorUtil randomGenerator = new RandomGeneratorUtil();
			Date twentyMinsAgo = new Date(System.currentTimeMillis()-20*60*1000);
			
			List<HistoricDecisionInstance> historicDecisionInstances = historyService.createHistoricDecisionInstanceQuery()
					.includeInputs()
					.includeOutputs()
					.evaluatedAfter(twentyMinsAgo)
					.decisionDefinitionKey("fraudRating")
					.list();

			
		
			Client client = TransportClient.builder().build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

			
			for (HistoricDecisionInstance historicDecisionInstance : historicDecisionInstances) 
			{
				//Fill a new object with stuff... 
				FraudScoreTableObject fraudData = new FraudScoreTableObject();
				fraudData.setFraudScore(0);
	
				fraudData.setFraudInstanceID(historicDecisionInstance.getId());
				
				List<HistoricDecisionInputInstance> inputs = historicDecisionInstance.getInputs();
				for (HistoricDecisionInputInstance historicDecisionInputInstance : inputs) {
					String inputName = historicDecisionInputInstance.getClauseName();
					if(inputName.equals("Payment Has Been Rejected In the Past")){
						fraudData.setPaymentRejected((Boolean)historicDecisionInputInstance.getValue());
					}
					else if(inputName.equals("Number of Past Payouts")){
						fraudData.setNumberOfPayouts((Integer)historicDecisionInputInstance.getValue());
					}
					else if(inputName.equals("History of Fraud")){
						fraudData.setHistoryOfFraud((Boolean)historicDecisionInputInstance.getValue());
					}
//					else if(inputName.equals("Payout Amount")){
//						fraudData.setCalimAmount((Double)historicDecisionInputInstance.getValue());			
//					}
	
					
				}
				List<HistoricDecisionOutputInstance> outputs = historicDecisionInstance.getOutputs();
				for (HistoricDecisionOutputInstance historicDecisionOutputInstance : outputs) {
					
					Integer fraudScore = (Integer) historicDecisionOutputInstance.getValue();
	
					fraudData.setFraudScore(fraudData.getFraudScore()+fraudScore);
					
				}
				
				if(historicDecisionInstance.getProcessInstanceId() != null){
					
					List<HistoricVariableInstance> processVariables = execution.getProcessEngineServices().getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(historicDecisionInstance.getProcessInstanceId()).list();
				
					for (HistoricVariableInstance historicVariableInstance : processVariables) {
						if(historicVariableInstance.getName().equals("claimType")){
							fraudData.setClaimType((String)historicVariableInstance.getValue());
						}
						else if(historicVariableInstance.getName().equals("region")){
							fraudData.setRegion((String)historicVariableInstance.getValue());
						}
						else if(historicVariableInstance.getName().equals("audit")){
							fraudData.setRequiredAudit((Boolean)historicVariableInstance.getValue());
						}
						
						else if(historicVariableInstance.getName().equals("userName")){
							fraudData.setNameOfAssignedUser((String)historicVariableInstance.getValue());
						}
						else if(historicVariableInstance.getName().equals("userName")){
							fraudData.setNameOfAssignedUser((String)historicVariableInstance.getValue());
						}
						else if(historicVariableInstance.getName().equals("claimAmount")){
							fraudData.setCalimAmount((Double)historicVariableInstance.getValue());
						}
					}

					fraudData.setDateOfClaim(randomGenerator.getRandomDate());
				
				}
				//2015-05-18T09:09:36.889Z
				ObjectMapper mapper = new ObjectMapper();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				mapper.setDateFormat(df);
	
				 String serializedHistoricDecisionInstance = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fraudData);
									
	
				IndexResponse response = client.prepareIndex("camunda", "fraudData", historicDecisionInstance.getId())
				        .setSource(serializedHistoricDecisionInstance)
				        .get();
				
				LOGGER.info(response.getId());
			
			}

	}
}
