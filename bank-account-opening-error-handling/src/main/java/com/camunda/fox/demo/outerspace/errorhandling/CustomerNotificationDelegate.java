package com.camunda.fox.demo.outerspace.errorhandling;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.time.DateUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricActivityInstance;

@Named
public class CustomerNotificationDelegate implements JavaDelegate {

  @Inject
  private HistoryService historyService;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    simulateServiceOutage(execution);
    System.out.println("The Customer has been informed.");
  }

  private void simulateServiceOutage(DelegateExecution execution) throws IOException {
    List<HistoricActivityInstance> previousActivities = historyService
      .createHistoricActivityInstanceQuery()
      .processInstanceId(execution.getProcessInstanceId())
      .finished()
      .list();
    Date endTimeOfPreviousActivity = previousActivities.get(previousActivities.size() - 1).getEndTime();
    if (DateUtils.addSeconds(endTimeOfPreviousActivity, 30).after(new Date())) {
      throw new IOException("Customer Notification Service unreachable");
    }
  }

}
