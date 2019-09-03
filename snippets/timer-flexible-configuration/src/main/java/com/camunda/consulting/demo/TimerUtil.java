package com.camunda.consulting.demo;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.Job;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimerUtil {

  private final String defaultValue = "PT10H";

  public String getNextTimer(DelegateExecution execution){

    Integer timerCount = (Integer) execution.getVariable("timerCount");

    if(timerCount == null){
      timerCount = 0;
    }

    List<String> timerConfig = (List<String>) execution.getVariable("timerConfig");

    Integer newTimerCount = timerCount+1;
    execution.setVariable("timerCount", newTimerCount);

    if(newTimerCount > timerConfig.size()){
      return "R/" + defaultValue;
    }

    return "R/" + timerConfig.get(timerCount);

  }

  public void recalculateTimer(DelegateExecution execution){

    ManagementService managementService = execution.getProcessEngineServices().getManagementService();

    Job job = managementService.createJobQuery().timers().activityId("timerEvent").singleResult();

    managementService.recalculateJobDuedate(job.getId(),true);

  }

}
