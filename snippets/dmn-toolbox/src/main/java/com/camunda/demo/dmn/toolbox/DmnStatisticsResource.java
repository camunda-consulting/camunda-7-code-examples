package com.camunda.demo.dmn.toolbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionOutputInstance;

@Path("/statistics")
public class DmnStatisticsResource {
	
	@GET
	@Path("/{decisionDefinitionKey}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Map<String, StatisticResultDto> getStatistics(@PathParam("decisionDefinitionKey") String decisionDefinitionKey) {
		HashMap<String, StatisticResultDto> result = new HashMap<String, StatisticResultDto>();
		
		List<HistoricDecisionInstance> decisions = BpmPlatform.getDefaultProcessEngine().getHistoryService().createHistoricDecisionInstanceQuery()
			.decisionDefinitionKey(decisionDefinitionKey)
			.includeOutputs()			
			.list();
		
		for (HistoricDecisionInstance decision : decisions) {
			try {
				List<HistoricDecisionOutputInstance> outputs = decision.getOutputs();
				for (HistoricDecisionOutputInstance output : outputs) {
					String ruleId = output.getRuleId();
					if (result.containsKey(ruleId)) {
						result.get(ruleId).incCount();					
					} else {
						result.put(ruleId, new StatisticResultDto());
					}
				}
			}
			catch (Exception ex) {
				// ignore - might be that we have a rule without Output!
			}
		}
		for (StatisticResultDto statistics : result.values()) {
			statistics.calculatePercentage(decisions.size());
		}
		
		return result;
	}

	public static class StatisticResultDto {
		private long count = 1;
		private int percentage;
		public long getCount() {
			return count;
		}
		public void calculatePercentage(int overallCount) {
			percentage = Math.round(count * 100 / overallCount); 
		}
		public void incCount() {
			count++;
		}
		public void setCount(long count) {
			this.count = count;
		}
		public int getPercentage() {
			return percentage;
		}
		public void setPercentage(int percentage) {
			this.percentage = percentage;
		}
	}

}
