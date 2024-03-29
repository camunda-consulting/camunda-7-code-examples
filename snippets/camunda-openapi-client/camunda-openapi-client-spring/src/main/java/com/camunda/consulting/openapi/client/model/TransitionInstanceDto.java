/*
 * Camunda Platform REST API
 * OpenApi Spec for Camunda Platform REST API.
 *
 * The version of the OpenAPI document: 7.16.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.camunda.consulting.openapi.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.camunda.consulting.openapi.client.model.ActivityInstanceIncidentDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A JSON object corresponding to the Activity Instance tree of the given process instance.
 */
@ApiModel(description = "A JSON object corresponding to the Activity Instance tree of the given process instance.")
@JsonPropertyOrder({
  TransitionInstanceDto.JSON_PROPERTY_ID,
  TransitionInstanceDto.JSON_PROPERTY_PARENT_ACTIVITY_INSTANCE_ID,
  TransitionInstanceDto.JSON_PROPERTY_ACTIVITY_ID,
  TransitionInstanceDto.JSON_PROPERTY_ACTIVITY_NAME,
  TransitionInstanceDto.JSON_PROPERTY_ACTIVITY_TYPE,
  TransitionInstanceDto.JSON_PROPERTY_PROCESS_INSTANCE_ID,
  TransitionInstanceDto.JSON_PROPERTY_PROCESS_DEFINITION_ID,
  TransitionInstanceDto.JSON_PROPERTY_EXECUTION_ID,
  TransitionInstanceDto.JSON_PROPERTY_INCIDENT_IDS,
  TransitionInstanceDto.JSON_PROPERTY_INCIDENTS
})
@JsonTypeName("TransitionInstanceDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class TransitionInstanceDto {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_PARENT_ACTIVITY_INSTANCE_ID = "parentActivityInstanceId";
  private String parentActivityInstanceId;

  public static final String JSON_PROPERTY_ACTIVITY_ID = "activityId";
  private String activityId;

  public static final String JSON_PROPERTY_ACTIVITY_NAME = "activityName";
  private String activityName;

  public static final String JSON_PROPERTY_ACTIVITY_TYPE = "activityType";
  private String activityType;

  public static final String JSON_PROPERTY_PROCESS_INSTANCE_ID = "processInstanceId";
  private String processInstanceId;

  public static final String JSON_PROPERTY_PROCESS_DEFINITION_ID = "processDefinitionId";
  private String processDefinitionId;

  public static final String JSON_PROPERTY_EXECUTION_ID = "executionId";
  private String executionId;

  public static final String JSON_PROPERTY_INCIDENT_IDS = "incidentIds";
  private List<String> incidentIds = null;

  public static final String JSON_PROPERTY_INCIDENTS = "incidents";
  private List<ActivityInstanceIncidentDto> incidents = null;


  public TransitionInstanceDto id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The id of the transition instance.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the transition instance.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public TransitionInstanceDto parentActivityInstanceId(String parentActivityInstanceId) {
    
    this.parentActivityInstanceId = parentActivityInstanceId;
    return this;
  }

   /**
   * The id of the parent activity instance, for example a sub process instance.
   * @return parentActivityInstanceId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the parent activity instance, for example a sub process instance.")
  @JsonProperty(JSON_PROPERTY_PARENT_ACTIVITY_INSTANCE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getParentActivityInstanceId() {
    return parentActivityInstanceId;
  }


  public void setParentActivityInstanceId(String parentActivityInstanceId) {
    this.parentActivityInstanceId = parentActivityInstanceId;
  }


  public TransitionInstanceDto activityId(String activityId) {
    
    this.activityId = activityId;
    return this;
  }

   /**
   * The id of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job)
   * @return activityId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job)")
  @JsonProperty(JSON_PROPERTY_ACTIVITY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getActivityId() {
    return activityId;
  }


  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }


  public TransitionInstanceDto activityName(String activityName) {
    
    this.activityName = activityName;
    return this;
  }

   /**
   * The name of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job)
   * @return activityName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job)")
  @JsonProperty(JSON_PROPERTY_ACTIVITY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getActivityName() {
    return activityName;
  }


  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }


  public TransitionInstanceDto activityType(String activityType) {
    
    this.activityType = activityType;
    return this;
  }

   /**
   * The type of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job)
   * @return activityType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The type of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job)")
  @JsonProperty(JSON_PROPERTY_ACTIVITY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getActivityType() {
    return activityType;
  }


  public void setActivityType(String activityType) {
    this.activityType = activityType;
  }


  public TransitionInstanceDto processInstanceId(String processInstanceId) {
    
    this.processInstanceId = processInstanceId;
    return this;
  }

   /**
   * The id of the process instance this instance is part of.
   * @return processInstanceId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the process instance this instance is part of.")
  @JsonProperty(JSON_PROPERTY_PROCESS_INSTANCE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProcessInstanceId() {
    return processInstanceId;
  }


  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }


  public TransitionInstanceDto processDefinitionId(String processDefinitionId) {
    
    this.processDefinitionId = processDefinitionId;
    return this;
  }

   /**
   * The id of the process definition.
   * @return processDefinitionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the process definition.")
  @JsonProperty(JSON_PROPERTY_PROCESS_DEFINITION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProcessDefinitionId() {
    return processDefinitionId;
  }


  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }


  public TransitionInstanceDto executionId(String executionId) {
    
    this.executionId = executionId;
    return this;
  }

   /**
   * The execution id.
   * @return executionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The execution id.")
  @JsonProperty(JSON_PROPERTY_EXECUTION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExecutionId() {
    return executionId;
  }


  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }


  public TransitionInstanceDto incidentIds(List<String> incidentIds) {
    
    this.incidentIds = incidentIds;
    return this;
  }

  public TransitionInstanceDto addIncidentIdsItem(String incidentIdsItem) {
    if (this.incidentIds == null) {
      this.incidentIds = new ArrayList<>();
    }
    this.incidentIds.add(incidentIdsItem);
    return this;
  }

   /**
   * A list of incident ids.
   * @return incidentIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list of incident ids.")
  @JsonProperty(JSON_PROPERTY_INCIDENT_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getIncidentIds() {
    return incidentIds;
  }


  public void setIncidentIds(List<String> incidentIds) {
    this.incidentIds = incidentIds;
  }


  public TransitionInstanceDto incidents(List<ActivityInstanceIncidentDto> incidents) {
    
    this.incidents = incidents;
    return this;
  }

  public TransitionInstanceDto addIncidentsItem(ActivityInstanceIncidentDto incidentsItem) {
    if (this.incidents == null) {
      this.incidents = new ArrayList<>();
    }
    this.incidents.add(incidentsItem);
    return this;
  }

   /**
   * A list of JSON objects containing incident specific properties: * &#x60;id&#x60;: the id of the incident * &#x60;activityId&#x60;: the activity id in which the incident occurred
   * @return incidents
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list of JSON objects containing incident specific properties: * `id`: the id of the incident * `activityId`: the activity id in which the incident occurred")
  @JsonProperty(JSON_PROPERTY_INCIDENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<ActivityInstanceIncidentDto> getIncidents() {
    return incidents;
  }


  public void setIncidents(List<ActivityInstanceIncidentDto> incidents) {
    this.incidents = incidents;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransitionInstanceDto transitionInstanceDto = (TransitionInstanceDto) o;
    return Objects.equals(this.id, transitionInstanceDto.id) &&
        Objects.equals(this.parentActivityInstanceId, transitionInstanceDto.parentActivityInstanceId) &&
        Objects.equals(this.activityId, transitionInstanceDto.activityId) &&
        Objects.equals(this.activityName, transitionInstanceDto.activityName) &&
        Objects.equals(this.activityType, transitionInstanceDto.activityType) &&
        Objects.equals(this.processInstanceId, transitionInstanceDto.processInstanceId) &&
        Objects.equals(this.processDefinitionId, transitionInstanceDto.processDefinitionId) &&
        Objects.equals(this.executionId, transitionInstanceDto.executionId) &&
        Objects.equals(this.incidentIds, transitionInstanceDto.incidentIds) &&
        Objects.equals(this.incidents, transitionInstanceDto.incidents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentActivityInstanceId, activityId, activityName, activityType, processInstanceId, processDefinitionId, executionId, incidentIds, incidents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransitionInstanceDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    parentActivityInstanceId: ").append(toIndentedString(parentActivityInstanceId)).append("\n");
    sb.append("    activityId: ").append(toIndentedString(activityId)).append("\n");
    sb.append("    activityName: ").append(toIndentedString(activityName)).append("\n");
    sb.append("    activityType: ").append(toIndentedString(activityType)).append("\n");
    sb.append("    processInstanceId: ").append(toIndentedString(processInstanceId)).append("\n");
    sb.append("    processDefinitionId: ").append(toIndentedString(processDefinitionId)).append("\n");
    sb.append("    executionId: ").append(toIndentedString(executionId)).append("\n");
    sb.append("    incidentIds: ").append(toIndentedString(incidentIds)).append("\n");
    sb.append("    incidents: ").append(toIndentedString(incidents)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

