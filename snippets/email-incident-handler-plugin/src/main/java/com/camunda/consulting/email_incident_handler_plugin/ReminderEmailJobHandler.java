package com.camunda.consulting.email_incident_handler_plugin;

import java.util.List;

import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.TimerEventJobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.IncidentEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReminderEmailJobHandler extends TimerEventJobHandler { 

  public static final String TYPE = "reminder-email";
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ReminderEmailJobHandler.class);
  
  public ReminderEmailJobHandler() {
    super();
    LOGGER.info("Reminder email job handler created");
  }

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public void execute(TimerJobConfiguration configuration, ExecutionEntity execution, CommandContext commandContext, String tenantId) {
    executeReminder((ReminderEmailJobHandlerConfiguration) configuration, execution, commandContext, tenantId);
  }

  public void executeReminder(ReminderEmailJobHandlerConfiguration configuration, ExecutionEntity execution, CommandContext commandContext, String tenantId) {
    LOGGER.info("execute Reminder Job Handler with execution {}", execution);
    LOGGER.info("Incident id from configuration: {}", configuration.getIncidentId());
    List<IncidentEntity> incidents = execution.getIncidents();
    LOGGER.info("incidents: {}", incidents);
    IncidentEntity incident = incidents.get(0);
    LOGGER.info("reminder should be sent by email with incident message: {}, PIID: {}, ACT_ID: {}", 
        incident.getIncidentMessage(), 
        incident.getProcessInstanceId(),
        incident.getActivityId());
  }

  @Override
  public ReminderEmailJobHandlerConfiguration newConfiguration(String canonicalString) {
    LOGGER.info("create new configuration with string {}", canonicalString);
    ReminderEmailJobHandlerConfiguration reminderEmailJobHandlerConfiguration = new ReminderEmailJobHandlerConfiguration(); 
    TimerJobConfiguration timerJobConfiguration = super.newConfiguration(canonicalString);
    reminderEmailJobHandlerConfiguration.setFollowUpJobCreated(timerJobConfiguration.isFollowUpJobCreated());
    reminderEmailJobHandlerConfiguration.setTimerElementKey(timerJobConfiguration.getTimerElementKey());
    reminderEmailJobHandlerConfiguration.setIncidentId(canonicalString);
    LOGGER.info("configuration: {}", reminderEmailJobHandlerConfiguration);
    return reminderEmailJobHandlerConfiguration;
  }

  public void onDelete(ReminderEmailJobHandlerConfiguration configuration, JobEntity jobEntity) {
    LOGGER.info("on delete for job entity {}", jobEntity);
    LOGGER.info("job will be deleted by the engine when it deletes the execution {}", jobEntity.getExecutionId());
  }
  
  public static class ReminderEmailJobHandlerConfiguration extends TimerJobConfiguration {
    
    private String incidentId;

    public String getIncidentId() {
      return incidentId;
    }

    public void setIncidentId(String incidentId) {
      this.incidentId = incidentId;
    }

    @Override
    public String toCanonicalString() {
      LOGGER.info("return configuration: {}", incidentId);
      String superCanonicalString = super.toCanonicalString();
      LOGGER.info("canonical string from TimerJobConfiguration: {}", superCanonicalString);
      return incidentId;
    }

    @Override
    public String toString() {
      return "ReminderEmailJobHandlerConfiguration [incidentId=" + incidentId + ", timerElementKey=" + timerElementKey + ", followUpJobCreated="
          + followUpJobCreated + "]";
    }
    
  }

}
