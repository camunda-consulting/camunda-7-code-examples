package com.camunda.demo.dmntraining.taskrouting;

public class DemoData {

	public static Claim createClaimCarAccident(long expenditure, String region) {
		Claim claim = new Claim();
		claim.setType("Unfall KFZ");
		claim.setExpenditure(expenditure);
		claim.setRegion(region);
		return claim;
	}

	public static Claim createClaimThirdPartyLiability(long expenditure, String region, String... affectedObject) {
		Claim claim = new Claim();
		claim.setType("Haftpflicht");
		claim.setExpenditure(expenditure);
		claim.setRegion(region);
		if (affectedObject.length>0) {
			claim.setAffectedObject(affectedObject[0]);
		}
		return claim;
	}
}
