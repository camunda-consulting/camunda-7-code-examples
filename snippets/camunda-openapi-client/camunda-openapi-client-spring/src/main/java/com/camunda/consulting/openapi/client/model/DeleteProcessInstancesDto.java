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
 * DeleteProcessInstancesDto
 */
@JsonPropertyOrder({
  DeleteProcessInstancesDto.JSON_PROPERTY_PROCESS_INSTANCE_IDS,
  DeleteProcessInstancesDto.JSON_PROPERTY_DELETE_REASON,
  DeleteProcessInstancesDto.JSON_PROPERTY_SKIP_CUSTOM_LISTENERS,
  DeleteProcessInstancesDto.JSON_PROPERTY_SKIP_SUBPROCESSES,
  DeleteProcessInstancesDto.JSON_PROPERTY_PROCESS_INSTANCE_QUERY,
  DeleteProcessInstancesDto.JSON_PROPERTY_HISTORIC_PROCESS_INSTANCE_QUERY
})
@JsonTypeName("DeleteProcessInstancesDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class DeleteProcessInstancesDto {
  public static final String JSON_PROPERTY_PROCESS_INSTANCE_IDS = "processInstanceIds";
  private List<String> processInstanceIds = null;

  public static final String JSON_PROPERTY_DELETE_REASON = "deleteReason";
  private String deleteReason;

  public static final String JSON_PROPERTY_SKIP_CUSTOM_LISTENERS = "skipCustomListeners";
  private Boolean skipCustomListeners;

  public static final String JSON_PROPERTY_SKIP_SUBPROCESSES = "skipSubprocesses";
  private Boolean skipSubprocesses;

  public static final String JSON_PROPERTY_PROCESS_INSTANCE_QUERY = "processInstanceQuery";
  private ProcessInstanceQueryDto processInstanceQuery;

  public static final String JSON_PROPERTY_HISTORIC_PROCESS_INSTANCE_QUERY = "historicProcessInstanceQuery";
  private HistoricProcessInstanceQueryDto historicProcessInstanceQuery;


  public DeleteProcessInstancesDto processInstanceIds(List<String> processInstanceIds) {
    
    this.processInstanceIds = processInstanceIds;
    return this;
  }

  public DeleteProcessInstancesDto addProcessInstanceIdsItem(String processInstanceIdsItem) {
    if (this.processInstanceIds == null) {
      this.processInstanceIds = new ArrayList<>();
    }
    this.processInstanceIds.add(processInstanceIdsItem);
    return this;
  }

   /**
   * A list process instance ids to delete.
   * @return processInstanceIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list process instance ids to delete.")
  @JsonProperty(JSON_PROPERTY_PROCESS_INSTANCE_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getProcessInstanceIds() {
    return processInstanceIds;
  }


  public void setProcessInstanceIds(List<String> processInstanceIds) {
    this.processInstanceIds = processInstanceIds;
  }


  public DeleteProcessInstancesDto deleteReason(String deleteReason) {
    
    this.deleteReason = deleteReason;
    return this;
  }

   /**
   * A string with delete reason.
   * @return deleteReason
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A string with delete reason.")
  @JsonProperty(JSON_PROPERTY_DELETE_REASON)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDeleteReason() {
    return deleteReason;
  }


  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }


  public DeleteProcessInstancesDto skipCustomListeners(Boolean skipCustomListeners) {
    
    this.skipCustomListeners = skipCustomListeners;
    return this;
  }

   /**
   * Skip execution listener invocation for activities that are started or ended as part of this request.
   * @return skipCustomListeners
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Skip execution listener invocation for activities that are started or ended as part of this request.")
  @JsonProperty(JSON_PROPERTY_SKIP_CUSTOM_LISTENERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSkipCustomListeners() {
    return skipCustomListeners;
  }


  public void setSkipCustomListeners(Boolean skipCustomListeners) {
    this.skipCustomListeners = skipCustomListeners;
  }


  public DeleteProcessInstancesDto skipSubprocesses(Boolean skipSubprocesses) {
    
    this.skipSubprocesses = skipSubprocesses;
    return this;
  }

   /**
   * Skip deletion of the subprocesses related to deleted processes as part of this request.
   * @return skipSubprocesses
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Skip deletion of the subprocesses related to deleted processes as part of this request.")
  @JsonProperty(JSON_PROPERTY_SKIP_SUBPROCESSES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSkipSubprocesses() {
    return skipSubprocesses;
  }


  public void setSkipSubprocesses(Boolean skipSubprocesses) {
    this.skipSubprocesses = skipSubprocesses;
  }


  public DeleteProcessInstancesDto processInstanceQuery(ProcessInstanceQueryDto processInstanceQuery) {
    
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


  public DeleteProcessInstancesDto historicProcessInstanceQuery(HistoricProcessInstanceQueryDto historicProcessInstanceQuery) {
    
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
    DeleteProcessInstancesDto deleteProcessInstancesDto = (DeleteProcessInstancesDto) o;
    return Objects.equals(this.processInstanceIds, deleteProcessInstancesDto.processInstanceIds) &&
        Objects.equals(this.deleteReason, deleteProcessInstancesDto.deleteReason) &&
        Objects.equals(this.skipCustomListeners, deleteProcessInstancesDto.skipCustomListeners) &&
        Objects.equals(this.skipSubprocesses, deleteProcessInstancesDto.skipSubprocesses) &&
        Objects.equals(this.processInstanceQuery, deleteProcessInstancesDto.processInstanceQuery) &&
        Objects.equals(this.historicProcessInstanceQuery, deleteProcessInstancesDto.historicProcessInstanceQuery);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processInstanceIds, deleteReason, skipCustomListeners, skipSubprocesses, processInstanceQuery, historicProcessInstanceQuery);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteProcessInstancesDto {\n");
    sb.append("    processInstanceIds: ").append(toIndentedString(processInstanceIds)).append("\n");
    sb.append("    deleteReason: ").append(toIndentedString(deleteReason)).append("\n");
    sb.append("    skipCustomListeners: ").append(toIndentedString(skipCustomListeners)).append("\n");
    sb.append("    skipSubprocesses: ").append(toIndentedString(skipSubprocesses)).append("\n");
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

