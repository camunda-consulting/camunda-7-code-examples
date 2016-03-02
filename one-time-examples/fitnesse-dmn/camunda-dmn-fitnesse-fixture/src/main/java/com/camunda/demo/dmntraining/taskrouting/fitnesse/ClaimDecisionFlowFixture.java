package com.camunda.demo.dmntraining.taskrouting.fitnesse;

import fit.ColumnFixture;

public class ClaimDecisionFlowFixture extends ColumnFixture {

	public String typeOfClaim;
	public long Expenditure;
	public String affectedObject;
	public String Region;
	private String processDefinitionKey = "mitarbeiterBestimmen";

	public String Employee() throws Exception {
			String employee = DecisionFacade.getInstance().startProcessInstance(processDefinitionKey, claim());
			return employee;
	}
	
	public String decisionFlowInstance() {
		return DecisionFacade.getInstance().getLastProcessInstanceCockpitUrl();	
	}

	
	public Claim claim() {
		return new Claim(typeOfClaim, Expenditure, Region, affectedObject);
	}

	@Override
	public void reset() throws Exception {
		DecisionFacade.getInstance().reset();
	}

}