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
import com.camunda.consulting.openapi.client.model.JobQueryDto;
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
 * Defines the number of retries for a selection of jobs. Please note that if both jobIds and jobQuery are provided, then retries will be set on the union of these sets.
 */
@ApiModel(description = "Defines the number of retries for a selection of jobs. Please note that if both jobIds and jobQuery are provided, then retries will be set on the union of these sets.")
@JsonPropertyOrder({
  SetJobRetriesDto.JSON_PROPERTY_JOB_IDS,
  SetJobRetriesDto.JSON_PROPERTY_JOB_QUERY,
  SetJobRetriesDto.JSON_PROPERTY_RETRIES
})
@JsonTypeName("SetJobRetriesDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class SetJobRetriesDto {
  public static final String JSON_PROPERTY_JOB_IDS = "jobIds";
  private List<String> jobIds = null;

  public static final String JSON_PROPERTY_JOB_QUERY = "jobQuery";
  private JobQueryDto jobQuery;

  public static final String JSON_PROPERTY_RETRIES = "retries";
  private Integer retries;


  public SetJobRetriesDto jobIds(List<String> jobIds) {
    
    this.jobIds = jobIds;
    return this;
  }

  public SetJobRetriesDto addJobIdsItem(String jobIdsItem) {
    if (this.jobIds == null) {
      this.jobIds = new ArrayList<>();
    }
    this.jobIds.add(jobIdsItem);
    return this;
  }

   /**
   * A list of job ids to set retries for.
   * @return jobIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A list of job ids to set retries for.")
  @JsonProperty(JSON_PROPERTY_JOB_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getJobIds() {
    return jobIds;
  }


  public void setJobIds(List<String> jobIds) {
    this.jobIds = jobIds;
  }


  public SetJobRetriesDto jobQuery(JobQueryDto jobQuery) {
    
    this.jobQuery = jobQuery;
    return this;
  }

   /**
   * Get jobQuery
   * @return jobQuery
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_JOB_QUERY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JobQueryDto getJobQuery() {
    return jobQuery;
  }


  public void setJobQuery(JobQueryDto jobQuery) {
    this.jobQuery = jobQuery;
  }


  public SetJobRetriesDto retries(Integer retries) {
    
    this.retries = retries;
    return this;
  }

   /**
   * An integer representing the number of retries. Please note that the value cannot be negative or null.
   * minimum: 0
   * @return retries
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An integer representing the number of retries. Please note that the value cannot be negative or null.")
  @JsonProperty(JSON_PROPERTY_RETRIES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getRetries() {
    return retries;
  }


  public void setRetries(Integer retries) {
    this.retries = retries;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetJobRetriesDto setJobRetriesDto = (SetJobRetriesDto) o;
    return Objects.equals(this.jobIds, setJobRetriesDto.jobIds) &&
        Objects.equals(this.jobQuery, setJobRetriesDto.jobQuery) &&
        Objects.equals(this.retries, setJobRetriesDto.retries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobIds, jobQuery, retries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetJobRetriesDto {\n");
    sb.append("    jobIds: ").append(toIndentedString(jobIds)).append("\n");
    sb.append("    jobQuery: ").append(toIndentedString(jobQuery)).append("\n");
    sb.append("    retries: ").append(toIndentedString(retries)).append("\n");
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

