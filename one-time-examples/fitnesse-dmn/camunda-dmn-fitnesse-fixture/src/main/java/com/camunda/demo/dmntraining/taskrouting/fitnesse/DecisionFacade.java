package com.camunda.demo.dmntraining.taskrouting.fitnesse;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class DecisionFacade {

	private static final String CAMUNDA_BASE_URL = "http://localhost:8080/engine-rest/engine/default";
	public static DecisionFacade singleInstance;

	public static DecisionFacade getInstance() {
		if (singleInstance == null) {
			singleInstance = new DecisionFacade();
		}
		return singleInstance;
	}

	private String lastProcessDefinitionKey;
	private String lastProcessInstanceId;
	private String lastDecisionInstanceId;

	public String evaluateDecision(String decisionDefinitionKey, String type, long expenditure, String outputVariableName) throws Exception {
		decisionDefinitionKey = "mitarbeiterBestimmen"; // temp
		outputVariableName = "employee"; // temp

		ClientRequest request = new ClientRequest(CAMUNDA_BASE_URL + "/decision-definition/key/" + decisionDefinitionKey + "/evaluate");
		request.accept("application/json");

		JSONObject body = new JSONObject() //
				.put("variables", new JSONObject() //
						.put("type", new JSONObject() //
								.put("value", type) //
								.put("type", "String") //
					    )
						.put("expenditure", new JSONObject() //
							.put("value", expenditure) //
							.put("type", "Long") //
						)
					);
		request.body(MediaType.APPLICATION_JSON, body.toString());

		ClientResponse<String> response = request.post(String.class);
		if (response.getStatus() == 200) {

			ClientRequest request2 = new ClientRequest(CAMUNDA_BASE_URL + "/history/decision-instance?sortBy=evaluationTime&sortOrder=desc&maxResults=1&includeOutputs=true");
			request2.accept("application/json");
//			request2.body(MediaType.APPLICATION_JSON, new JSONObject().put("processInstanceId", lastDecisionId).put("variableName", "selectedEmployee").toString());
			ClientResponse<String> response2 = request2.get(String.class);
			JSONArray historicDecisionResponseJson = new JSONArray(response2.getEntity());
			
			JSONObject historicDecisionInstance = historicDecisionResponseJson.getJSONObject(0);

			lastDecisionInstanceId = historicDecisionInstance.getString("id");
			
			JSONArray outputs = historicDecisionInstance.getJSONArray("outputs");
			for (int i = 0; i < outputs.length(); i++) {
				JSONObject output = outputs.getJSONObject(i);
				if (outputVariableName.equals(output.get("variableName"))) {
					return output.getString("value");
				}
			}
			return null;
		} else {
			return response.getResponseStatus().getReasonPhrase();
		}
	}
	
	public String startProcessInstance(String processDefinitionKey, Claim claim) throws Exception {
		processDefinitionKey = "mitarbeiterBestimmen"; // temp

		ClientRequest request = new ClientRequest(CAMUNDA_BASE_URL + "/process-definition/key/" + processDefinitionKey + "/start");
		request.accept("application/json");

		JSONObject body = new JSONObject() //
				.put("variables", new JSONObject() //
						.put("claim", new JSONObject() //
								.put("value", new JSONObject(claim).toString()) //
								.put("type", "Object") //
								.put("valueInfo", new JSONObject() //
										.put("serializationDataFormat", "application/json") //
										.put("objectTypeName", "com.camunda.demo.dmntraining.taskrouting.Claim"))));
		request.body(MediaType.APPLICATION_JSON, body.toString());

		ClientResponse<String> response = request.post(String.class);
		if (response.getStatus() == 200) {

			JSONObject startPiResponseJson = new JSONObject(response.getEntity());
			lastProcessInstanceId = startPiResponseJson.getString("id");

			ClientRequest request2 = new ClientRequest(CAMUNDA_BASE_URL + "/history/variable-instance");
			request2.accept("application/json");
			request2.body(MediaType.APPLICATION_JSON, new JSONObject().put("processInstanceId", lastProcessInstanceId).put("variableName", "selectedEmployee").toString());
			ClientResponse<String> response2 = request2.post(String.class);
			JSONArray historicPiResponseJson = new JSONArray(response2.getEntity());

			String selectedEmployee = historicPiResponseJson.getJSONObject(0).getString("value");
			return selectedEmployee;
		} else {
			return response.getResponseStatus().getReasonPhrase();
		}
	}

	public static void main(String[] args) throws Exception {
		Claim claim = new Claim("Unfall KFZ", 1000, "10xxx", null);
		System.out.println(new DecisionFacade().startProcessInstance(null, claim));
	}

	public String getLastProcessInstanceCockpitUrl() {
		return "<a href=\"http://localhost:8080/camunda/app/cockpit/default/#/process-instance/" + lastProcessInstanceId + "/history?detailsTab=decision-instances-tab\">" + lastProcessInstanceId + "</a>";
	}

	public String getLastDecisionInstanceCockpitUrl() {
		return "<a href=\"http://localhost:8080/camunda/app/cockpit/default/#/decision-instance/" + lastDecisionInstanceId + "/history\">" + lastDecisionInstanceId + "</a>";
	}
	
	public void reset() {
		this.lastProcessInstanceId = null;
		this.lastProcessDefinitionKey = null;
	}

	public String getLastProcessDefinitionKey() {
		return lastProcessDefinitionKey;
	}

	public String getLastProcessInstanceId() {
		return lastProcessInstanceId;
	}

}
