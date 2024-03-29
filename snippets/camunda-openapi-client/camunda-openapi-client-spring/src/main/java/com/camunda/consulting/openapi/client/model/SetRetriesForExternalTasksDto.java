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
import com.camunda.consulting.openapi.client.model.ExternalTaskQueryDto;
import com.camunda.consulting.openapi.client.model.HistoricProcessInstanceQueryDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceQueryDto;
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
 * SetRetriesForExternalTasksDto
 */
@JsonPropertyOrder({
  SetRetriesForExternalTasksDto.JSON_PROPERTY_RETRIES,
  SetRetriesForExternalTasksDto.JSON_PROPERTY_EXTERNAL_TASK_IDS,
  SetRetriesForExternalTasksDto.JSON_PROPERTY_PROCESS_INSTANCE_IDS,
  SetRetriesForExternalTasksDto.JSON_PROPERTY_EXTERNAL_TASK_QUERY,
  SetRetriesForExternalTasksDto.JSON_PROPERTY_PROCESS_INSTANCE_QUERY,
  SetRetriesForExternalTasksDto.JSON_PROPERTY_HISTORIC_PROCESS_INSTANCE_QUERY
})
@JsonTypeName("SetRetriesForExternalTasksDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class SetRetriesForExternalTasksDto {
  public static final String JSON_PROPERTY_RETRIES = "retries";
  private Integer retries;

  public static final String JSON_PROPERTY_EXTERNAL_TASK_IDS = "externalTaskIds";
  private List<String> externalTaskIds = null;

  public static final String JSON_PROPERTY_PROCESS_INSTANCE_IDS = "processInstanceIds";
  private List<String> processInstanceIds = null;

  public static final String JSON_PROPERTY_EXTERNAL_TASK_QUERY = "externalTaskQuery";
  private ExternalTaskQueryDto externalTaskQuery;

  public static final String JSON_PROPERTY_PROCESS_INSTANCE_QUERY = "processInstanceQuery";
  private ProcessInstanceQueryDto processInstanceQuery;

  public static final String JSON_PROPERTY_HISTORIC_PROCESS_INSTANCE_QUERY = "historicProcessInstanceQuery";
  private HistoricProcessInstanceQueryDto historicProcessInstanceQuery;


  public SetRetriesForExternalTasksDto retries(Integer retries) {
    
    this.retries = retries;
    return this;
  }

   /**
   * The number of retries to set for the external task.  Must be &gt;&#x3D; 0. If this is 0, an incident is created and the task cannot be fetched anymore unless the retries are increased again. Can not be null.
   * @return retries
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The number of retries to set for the external task.  Must be >= 0. If this is 0, an incident is created and the task cannot be fetched anymore unless the retries are increased again. Can not be null.")
  @JsonProperty(JSON_PROPERTY_RETRIES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getRetries() {
    return retries;
  }


  public void setRetries(Integer retries) {
    this.retries = retries;
  }


  public SetRetriesForExternalTasksDto externalTaskIds(List<String> externalTaskIds) {
    
    this.externalTaskIds = externalTaskIds;
    return this;
  }

  public SetRetriesForExternalTasksDto addExternalTaskIdsItem(String externalTaskIdsItem) {
    if (this.externalTaskIds == null) {
      this.externalTaskIds = new ArrayList<>();
    }
    this.externalTaskIds.add(externalTaskIdsItem);
    return this;
  }

   /**
   * The ids of the external tasks to set the number of retries for.
   * @return externalTaskIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ids of the external tasks to set the number of retries for.")
  @JsonProperty(JSON_PROPERTY_EXTERNAL_TASK_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getExternalTaskIds() {
    return externalTaskIds;
  }


  public void setExternalTaskIds(List<String> externalTaskIds) {
    this.externalTaskIds = externalTaskIds;
  }


  public SetRetriesForExternalTasksDto processInstanceIds(List<String> processInstanceIds) {
    
    this.processInstanceIds = processInstanceIds;
    return this;
  }

  public SetRetriesForExternalTasksDto addProcessInstanceIdsItem(String processInstanceIdsItem) {
    if (this.processInstanceIds == null) {
      this.processInstanceIds = new ArrayList<>();
    }
    this.processInstanceIds.add(processInstanceIdsItem);
    return this;
  }

   /**
   * The ids of process instances containing the tasks to set the number of retries for.
   * @return processInstanceIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ids of process instances containing the tasks to set the number of retries for.")
  @JsonProperty(JSON_PROPERTY_PROCESS_INSTANCE_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getProcessInstanceIds() {
    return processInstanceIds;
  }


  public void setProcessInstanceIds(List<String> processInstanceIds) {
    this.processInstanceIds = processInstanceIds;
  }


  public SetRetriesForExternalTasksDto externalTaskQuery(ExternalTaskQueryDto externalTaskQuery) {
    
    this.externalTaskQuery = externalTaskQuery;
    return this;
  }

   /**
   * Get externalTaskQuery
   * @return externalTaskQuery
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_EXTERNAL_TASK_QUERY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ExternalTaskQueryDto getExternalTaskQuery() {
    return externalTaskQuery;
  }


  public void setExternalTaskQuery(ExternalTaskQueryDto externalTaskQuery) {
    this.externalTaskQuery = externalTaskQuery;
  }


  public SetRetriesForExternalTasksDto processInstanceQuery(ProcessInstanceQueryDto processInstanceQuery) {
    
    this.processInstanceQuery = processInstanceQuery;
    return this;
  }

   /**
   * Get processInstanceQuery
   * @return processInstanceQuery
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PROCESS_INSTANCE_QUERY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ProcessInstanceQueryDto getProcessInstanceQuery() {
    return processInstanceQuery;
  }


  public void setProcessInstanceQuery(ProcessInstanceQueryDto processInstanceQuery) {
    this.processInstanceQuery = processInstanceQuery;
  }


  public SetRetriesForExternalTasksDto historicProcessInstanceQuery(HistoricProcessInstanceQueryDto historicProcessInstanceQuery) {
    
    this.historicProcessInstanceQuery = historicProcessInstanceQuery;
    return this;
  }

   /**
   * Get historicProcessInstanceQuery
   * @return historicProcessInstanceQuery
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_HISTORIC_PROCESS_INSTANCE_QUERY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public HistoricProcessInstanceQueryDto getHistoricProcessInstanceQuery() {
    return historicProcessInstanceQuery;
  }


  public void setHistoricProcessInstanceQuery(HistoricProcessInstanceQueryDto historicProcessInstanceQuery) {
    this.historicProcessInstanceQuery = historicProcessInstanceQuery;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetRetriesForExternalTasksDto setRetriesForExternalTasksDto = (SetRetriesForExternalTasksDto) o;
    return Objects.equals(this.retries, setRetriesForExternalTasksDto.retries) &&
        Objects.equals(this.externalTaskIds, setRetriesForExternalTasksDto.externalTaskIds) &&
        Objects.equals(this.processInstanceIds, setRetriesForExternalTasksDto.processInstanceIds) &&
        Objects.equals(this.externalTaskQuery, setRetriesForExternalTasksDto.externalTaskQuery) &&
        Objects.equals(this.processInstanceQuery, setRetriesForExternalTasksDto.processInstanceQuery) &&
        Objects.equals(this.historicProcessInstanceQuery, setRetriesForExternalTasksDto.historicProcessInstanceQuery);
  }

  @Override
  public int hashCode() {
    return Objects.hash(retries, externalTaskIds, processInstanceIds, externalTaskQuery, processInstanceQuery, historicProcessInstanceQuery);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetRetriesForExternalTasksDto {\n");
    sb.append("    retries: ").append(toIndentedString(retries)).append("\n");
    sb.append("    externalTaskIds: ").append(toIndentedString(externalTaskIds)).append("\n");
    sb.append("    processInstanceIds: ").append(toIndentedString(processInstanceIds)).append("\n");
    sb.append("    externalTaskQuery: ").append(toIndentedString(externalTaskQuery)).append("\n");
    sb.append("    processInstanceQuery: ").append(toIndentedString(processInstanceQuery)).append("\n");
    sb.append("    historicProcessInstanceQuery: ").append(toIndentedString(historicProcessInstanceQuery)).append("\n");
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

