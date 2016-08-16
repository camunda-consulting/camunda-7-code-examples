package org.camunda.consulting.cockpit.plugin.kpioverview.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.consulting.cockpit.plugin.kpioverview.dto.TaskStatisticHistoryCountDto;
import org.camunda.consulting.cockpit.plugin.kpioverview.dto.TaskStatisticHistoryDto;
import org.camunda.consulting.cockpit.plugin.kpioverview.StatisticData;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KPIStatisticsResource extends AbstractCockpitPluginResource {
	private String statisticData;
	private String processDefinitionId;

	public KPIStatisticsResource(String engineName, String processDefinitionId, String statisticData) {
		super(engineName);
		this.processDefinitionId = processDefinitionId;
		this.statisticData = statisticData;
	}

	@GET
	public List<TaskStatisticHistoryCountDto> getTaskStatisticHistoryResource() {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("processDefinitionId", processDefinitionId);

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<StatisticData> data = mapper.readValue(statisticData, new TypeReference<List<StatisticData>>() { });
			List<TaskStatisticHistoryCountDto> results = new ArrayList<TaskStatisticHistoryCountDto>();

			for(int i = 0; i < data.size(); i++) {

				StatisticData statisticData = data.get(i);
				if (statisticData.getKpi() != null && !statisticData.getKpi().isEmpty()) {
					TaskStatisticHistoryCountDto countDto = new TaskStatisticHistoryCountDto();
					countDto.setActivityId(statisticData.getTaskId());
					parameters.put("statisticDataUnit", statisticData.getKpiUnit());
					parameters.put("taskId", statisticData.getTaskId());
					QueryParameters<TaskStatisticHistoryDto> queryParameters = new QueryParameters<TaskStatisticHistoryDto>();
					queryParameters.setParameter(parameters);
					List<TaskStatisticHistoryDto> result = getQueryService().executeQuery("cockpit.kpi.selectTaskStatisticHistory", queryParameters);

					if (result.size() > 0) {
						int count = 0;
						for (TaskStatisticHistoryDto dto : result) {
							if (dto.getDiff() > Integer.parseInt(statisticData.getKpi())) {
								count++;
							}
						}	
						countDto.setCount(count);
					}
					results.add(countDto);
				}
			}
			return results;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
