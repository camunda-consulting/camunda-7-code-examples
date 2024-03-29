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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * ExecutionDto
 */
@JsonPropertyOrder({
  ExecutionDto.JSON_PROPERTY_ID,
  ExecutionDto.JSON_PROPERTY_PROCESS_INSTANCE_ID,
  ExecutionDto.JSON_PROPERTY_ENDED,
  ExecutionDto.JSON_PROPERTY_TENANT_ID
})
@JsonTypeName("ExecutionDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class ExecutionDto {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_PROCESS_INSTANCE_ID = "processInstanceId";
  private String processInstanceId;

  public static final String JSON_PROPERTY_ENDED = "ended";
  private Boolean ended;

  public static final String JSON_PROPERTY_TENANT_ID = "tenantId";
  private String tenantId;


  public ExecutionDto id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The id of the Execution.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the Execution.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public ExecutionDto processInstanceId(String processInstanceId) {
    
    this.processInstanceId = processInstanceId;
    return this;
  }

   /**
   * The id of the root of the execution tree representing the process instance.
   * @return processInstanceId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the root of the execution tree representing the process instance.")
  @JsonProperty(JSON_PROPERTY_PROCESS_INSTANCE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProcessInstanceId() {
    return processInstanceId;
  }


  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }


  public ExecutionDto ended(Boolean ended) {
    
    this.ended = ended;
    return this;
  }

   /**
   * Indicates if the execution is ended.
   * @return ended
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Indicates if the execution is ended.")
  @JsonProperty(JSON_PROPERTY_ENDED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getEnded() {
    return ended;
  }


  public void setEnded(Boolean ended) {
    this.ended = ended;
  }


  public ExecutionDto tenantId(String tenantId) {
    
    this.tenantId = tenantId;
    return this;
  }

   /**
   * The id of the tenant this execution belongs to. Can be &#x60;null&#x60; if the execution belongs to no single tenant.
   * @return tenantId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the tenant this execution belongs to. Can be `null` if the execution belongs to no single tenant.")
  @JsonProperty(JSON_PROPERTY_TENANT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTenantId() {
    return tenantId;
  }


  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExecutionDto executionDto = (ExecutionDto) o;
    return Objects.equals(this.id, executionDto.id) &&
        Objects.equals(this.processInstanceId, executionDto.processInstanceId) &&
        Objects.equals(this.ended, executionDto.ended) &&
        Objects.equals(this.tenantId, executionDto.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, processInstanceId, ended, tenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExecutionDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    processInstanceId: ").append(toIndentedString(processInstanceId)).append("\n");
    sb.append("    ended: ").append(toIndentedString(ended)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
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

