package com.camunda.demo.dmntraining.taskrouting.fitnesse;

import fit.ColumnFixture;

public class RoadshowFixture extends ColumnFixture {

	public String typeOfClaim;
	public long Expenditure;
	private String decisionDefinitionKey = "mitarbeiterBestimmen";

	public String Employee() throws Exception {
			String employee = DecisionFacade.getInstance().evaluateDecision(decisionDefinitionKey, typeOfClaim, Expenditure, "Mitarbeiter");
			return employee;
	}
	
	public String decisionInstance() {
		return DecisionFacade.getInstance().getLastDecisionInstanceCockpitUrl();
	}

	@Override
	public void reset() throws Exception {
		DecisionFacade.getInstance().reset();
	}

}