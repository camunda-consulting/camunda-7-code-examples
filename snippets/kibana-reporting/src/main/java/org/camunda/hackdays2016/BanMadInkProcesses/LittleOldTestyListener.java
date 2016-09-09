package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class LittleOldTestyListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		  
		  RandomGeneratorUtil randomUtil = new RandomGeneratorUtil();
		  
		  execution.setVariable("paymentRejected", randomUtil.getRandomPaymentRejected());
		  execution.setVariable("numberOfPayouts", randomUtil.getRandomNumberOfPayments());
		  execution.setVariable("historyOfFraud", randomUtil.getRandomHistoryOfFraud());
		  execution.setVariable("claimAmount", randomUtil.getRandomClaimAmount());
		  

	}

}
