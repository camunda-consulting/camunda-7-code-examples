package com.camunda.consulting.email_incident_handler_plugin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.camunda.bpm.engine.impl.incident.DefaultIncidentHandler;
import org.camunda.bpm.engine.impl.incident.IncidentContext;
import org.camunda.bpm.engine.impl.persistence.entity.IncidentEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.camunda.bpm.engine.runtime.Incident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camunda.consulting.email_incident_handler_plugin.ReminderEmailJobHandler.ReminderEmailJobHandlerConfiguration;

public class EmailIncidentHandler extends DefaultIncidentHandler {
  
  // JobConfiguration for Reminder: R3/starttime in ISO format/PT30M, three times each 30 Minutes
  public static final int REMINDER_MINUTES = 5;
  public static final String REPEAT_INTERVAL = "/PT" + REMINDER_MINUTES + "M";
  public static final String REPEAT = "R3/";
  
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailIncidentHandler.class);

  public EmailIncidentHandler(String type) {
    super(type);
    LOGGER.info("EmailIncidentHandler created for type '{}'", type);
  }

  public Date calculateDueDate() {
    // REMINDER_MINUTES Minutes from now
    return Date.from(
        LocalDateTime.now().plusMinutes(REMINDER_MINUTES).atZone(ZoneId.systemDefault()).toInstant()
    );
  }

  @Override
  public String getIncidentHandlerType() {
    LOGGER.info("get handler type '{}'", type);
    return super.getIncidentHandlerType();
  }

  @Override
  public Incident handleIncident(IncidentContext context, String message) {
    IncidentEntity incidentEntity = (IncidentEntity) super.handleIncident(context, message);
    LOGGER.info("incident {} created for execution {}", incidentEntity.getId(), context.getExecutionId());
    
    LOGGER.info("create reminder job in the engine");
    createReminderJob(incidentEntity);
    
    return incidentEntity;
  }

  @Override
  public void resolveIncident(IncidentContext context) {
    LOGGER.info("resolve incident for execution {}", context.getExecutionId());
    LOGGER.info("context configuration: {}", context.getConfiguration());
    super.resolveIncident(context);
    
    LOGGER.info("delete reminder job in the engine");
    deleteReminderJob(context);
  }

  @Override
  public void deleteIncident(IncidentContext context) {
    LOGGER.info("delete incident for execution {}", context.getExecutionId());
    super.deleteIncident(context);
    
    LOGGER.info("delete reminder job in the engine");
    deleteReminderJob(context);
  }
  
  public void createReminderJob(IncidentEntity incident) {
    Date dueDate = calculateDueDate();
    TimerEntity reminderJob = new TimerEntity();
    reminderJob.setProcessDefinitionId(incident.getProcessDefinitionId());
    reminderJob.setProcessDefinitionKey(incident.getProcessDefinition().getKey());
    reminderJob.setProcessInstanceId(incident.getProcessInstanceId());
    
    reminderJob.setExecutionId(incident.getExecutionId());
    
    String dateTimeNowIso = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    reminderJob.setRepeat(REPEAT + dateTimeNowIso + REPEAT_INTERVAL);
    ReminderEmailJobHandlerConfiguration jobHandlerConfiguration = new ReminderEmailJobHandlerConfiguration();
    jobHandlerConfiguration.setIncidentId(incident.getId());
    reminderJob.setJobHandlerConfiguration(jobHandlerConfiguration);
    reminderJob.setJobHandlerType(ReminderEmailJobHandler.TYPE);
    reminderJob.createNewTimerJob(dueDate);
    
    LOGGER.info("reminder job created: {}", reminderJob);
  }

  public void deleteReminderJob(IncidentContext incidentContext) {
    LOGGER.info("deletion of execution {} deletes the reminder job for incident {},", 
        incidentContext.getExecutionId(),
        incidentContext.getConfiguration()); 
  }
}
