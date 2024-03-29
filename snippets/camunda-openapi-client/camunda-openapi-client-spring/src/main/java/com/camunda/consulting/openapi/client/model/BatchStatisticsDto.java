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
import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.BatchStatisticsDtoAllOf;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * BatchStatisticsDto
 */
@JsonPropertyOrder({
  BatchStatisticsDto.JSON_PROPERTY_REMAINING_JOBS,
  BatchStatisticsDto.JSON_PROPERTY_COMPLETED_JOBS,
  BatchStatisticsDto.JSON_PROPERTY_FAILED_JOBS,
  BatchStatisticsDto.JSON_PROPERTY_ID,
  BatchStatisticsDto.JSON_PROPERTY_TYPE,
  BatchStatisticsDto.JSON_PROPERTY_TOTAL_JOBS,
  BatchStatisticsDto.JSON_PROPERTY_JOBS_CREATED,
  BatchStatisticsDto.JSON_PROPERTY_BATCH_JOBS_PER_SEED,
  BatchStatisticsDto.JSON_PROPERTY_INVOCATIONS_PER_BATCH_JOB,
  BatchStatisticsDto.JSON_PROPERTY_SEED_JOB_DEFINITION_ID,
  BatchStatisticsDto.JSON_PROPERTY_MONITOR_JOB_DEFINITION_ID,
  BatchStatisticsDto.JSON_PROPERTY_BATCH_JOB_DEFINITION_ID,
  BatchStatisticsDto.JSON_PROPERTY_SUSPENDED,
  BatchStatisticsDto.JSON_PROPERTY_TENANT_ID,
  BatchStatisticsDto.JSON_PROPERTY_CREATE_USER_ID
})
@JsonTypeName("BatchStatisticsDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class BatchStatisticsDto {
  public static final String JSON_PROPERTY_REMAINING_JOBS = "remainingJobs";
  private Integer remainingJobs;

  public static final String JSON_PROPERTY_COMPLETED_JOBS = "completedJobs";
  private Integer completedJobs;

  public static final String JSON_PROPERTY_FAILED_JOBS = "failedJobs";
  private Integer failedJobs;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_TOTAL_JOBS = "totalJobs";
  private Integer totalJobs;

  public static final String JSON_PROPERTY_JOBS_CREATED = "jobsCreated";
  private Integer jobsCreated;

  public static final String JSON_PROPERTY_BATCH_JOBS_PER_SEED = "batchJobsPerSeed";
  private Integer batchJobsPerSeed;

  public static final String JSON_PROPERTY_INVOCATIONS_PER_BATCH_JOB = "invocationsPerBatchJob";
  private Integer invocationsPerBatchJob;

  public static final String JSON_PROPERTY_SEED_JOB_DEFINITION_ID = "seedJobDefinitionId";
  private String seedJobDefinitionId;

  public static final String JSON_PROPERTY_MONITOR_JOB_DEFINITION_ID = "monitorJobDefinitionId";
  private String monitorJobDefinitionId;

  public static final String JSON_PROPERTY_BATCH_JOB_DEFINITION_ID = "batchJobDefinitionId";
  private String batchJobDefinitionId;

  public static final String JSON_PROPERTY_SUSPENDED = "suspended";
  private Boolean suspended;

  public static final String JSON_PROPERTY_TENANT_ID = "tenantId";
  private String tenantId;

  public static final String JSON_PROPERTY_CREATE_USER_ID = "createUserId";
  private String createUserId;


  public BatchStatisticsDto remainingJobs(Integer remainingJobs) {
    
    this.remainingJobs = remainingJobs;
    return this;
  }

   /**
   * The number of remaining batch execution jobs. This does include failed batch execution jobs and batch execution jobs which still have to be created by the seed job.
   * @return remainingJobs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The number of remaining batch execution jobs. This does include failed batch execution jobs and batch execution jobs which still have to be created by the seed job.")
  @JsonProperty(JSON_PROPERTY_REMAINING_JOBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getRemainingJobs() {
    return remainingJobs;
  }


  public void setRemainingJobs(Integer remainingJobs) {
    this.remainingJobs = remainingJobs;
  }


  public BatchStatisticsDto completedJobs(Integer completedJobs) {
    
    this.completedJobs = completedJobs;
    return this;
  }

   /**
   * The number of completed batch execution jobs. This does include aborted/deleted batch execution jobs.
   * @return completedJobs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The number of completed batch execution jobs. This does include aborted/deleted batch execution jobs.")
  @JsonProperty(JSON_PROPERTY_COMPLETED_JOBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getCompletedJobs() {
    return completedJobs;
  }


  public void setCompletedJobs(Integer completedJobs) {
    this.completedJobs = completedJobs;
  }


  public BatchStatisticsDto failedJobs(Integer failedJobs) {
    
    this.failedJobs = failedJobs;
    return this;
  }

   /**
   * The number of failed batch execution jobs. This does not include aborted or deleted batch execution jobs.
   * @return failedJobs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The number of failed batch execution jobs. This does not include aborted or deleted batch execution jobs.")
  @JsonProperty(JSON_PROPERTY_FAILED_JOBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getFailedJobs() {
    return failedJobs;
  }


  public void setFailedJobs(Integer failedJobs) {
    this.failedJobs = failedJobs;
  }


  public BatchStatisticsDto id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The id of the batch.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the batch.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public BatchStatisticsDto type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * The type of the batch. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types.
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The type of the batch. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types.")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public BatchStatisticsDto totalJobs(Integer totalJobs) {
    
    this.totalJobs = totalJobs;
    return this;
  }

   /**
   * The total jobs of a batch is the number of batch execution jobs required to complete the batch.
   * @return totalJobs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The total jobs of a batch is the number of batch execution jobs required to complete the batch.")
  @JsonProperty(JSON_PROPERTY_TOTAL_JOBS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getTotalJobs() {
    return totalJobs;
  }


  public void setTotalJobs(Integer totalJobs) {
    this.totalJobs = totalJobs;
  }


  public BatchStatisticsDto jobsCreated(Integer jobsCreated) {
    
    this.jobsCreated = jobsCreated;
    return this;
  }

   /**
   * The number of batch execution jobs already created by the seed job.
   * @return jobsCreated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The number of batch execution jobs already created by the seed job.")
  @JsonProperty(JSON_PROPERTY_JOBS_CREATED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getJobsCreated() {
    return jobsCreated;
  }


  public void setJobsCreated(Integer jobsCreated) {
    this.jobsCreated = jobsCreated;
  }


  public BatchStatisticsDto batchJobsPerSeed(Integer batchJobsPerSeed) {
    
    this.batchJobsPerSeed = batchJobsPerSeed;
    return this;
  }

   /**
   * The number of batch execution jobs created per seed job invocation. The batch seed job is invoked until it has created all batch execution jobs required by the batch (see &#x60;totalJobs&#x60; property).
   * @return batchJobsPerSeed
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The number of batch execution jobs created per seed job invocation. The batch seed job is invoked until it has created all batch execution jobs required by the batch (see `totalJobs` property).")
  @JsonProperty(JSON_PROPERTY_BATCH_JOBS_PER_SEED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getBatchJobsPerSeed() {
    return batchJobsPerSeed;
  }


  public void setBatchJobsPerSeed(Integer batchJobsPerSeed) {
    this.batchJobsPerSeed = batchJobsPerSeed;
  }


  public BatchStatisticsDto invocationsPerBatchJob(Integer invocationsPerBatchJob) {
    
    this.invocationsPerBatchJob = invocationsPerBatchJob;
    return this;
  }

   /**
   * Every batch execution job invokes the command executed by the batch &#x60;invocationsPerBatchJob&#x60; times. E.g., for a process instance migration batch this specifies the number of process instances which are migrated per batch execution job.
   * @return invocationsPerBatchJob
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Every batch execution job invokes the command executed by the batch `invocationsPerBatchJob` times. E.g., for a process instance migration batch this specifies the number of process instances which are migrated per batch execution job.")
  @JsonProperty(JSON_PROPERTY_INVOCATIONS_PER_BATCH_JOB)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getInvocationsPerBatchJob() {
    return invocationsPerBatchJob;
  }


  public void setInvocationsPerBatchJob(Integer invocationsPerBatchJob) {
    this.invocationsPerBatchJob = invocationsPerBatchJob;
  }


  public BatchStatisticsDto seedJobDefinitionId(String seedJobDefinitionId) {
    
    this.seedJobDefinitionId = seedJobDefinitionId;
    return this;
  }

   /**
   * The job definition id for the seed jobs of this batch.
   * @return seedJobDefinitionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The job definition id for the seed jobs of this batch.")
  @JsonProperty(JSON_PROPERTY_SEED_JOB_DEFINITION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSeedJobDefinitionId() {
    return seedJobDefinitionId;
  }


  public void setSeedJobDefinitionId(String seedJobDefinitionId) {
    this.seedJobDefinitionId = seedJobDefinitionId;
  }


  public BatchStatisticsDto monitorJobDefinitionId(String monitorJobDefinitionId) {
    
    this.monitorJobDefinitionId = monitorJobDefinitionId;
    return this;
  }

   /**
   * The job definition id for the monitor jobs of this batch.
   * @return monitorJobDefinitionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The job definition id for the monitor jobs of this batch.")
  @JsonProperty(JSON_PROPERTY_MONITOR_JOB_DEFINITION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMonitorJobDefinitionId() {
    return monitorJobDefinitionId;
  }


  public void setMonitorJobDefinitionId(String monitorJobDefinitionId) {
    this.monitorJobDefinitionId = monitorJobDefinitionId;
  }


  public BatchStatisticsDto batchJobDefinitionId(String batchJobDefinitionId) {
    
    this.batchJobDefinitionId = batchJobDefinitionId;
    return this;
  }

   /**
   * The job definition id for the batch execution jobs of this batch.
   * @return batchJobDefinitionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The job definition id for the batch execution jobs of this batch.")
  @JsonProperty(JSON_PROPERTY_BATCH_JOB_DEFINITION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getBatchJobDefinitionId() {
    return batchJobDefinitionId;
  }


  public void setBatchJobDefinitionId(String batchJobDefinitionId) {
    this.batchJobDefinitionId = batchJobDefinitionId;
  }


  public BatchStatisticsDto suspended(Boolean suspended) {
    
    this.suspended = suspended;
    return this;
  }

   /**
   * Indicates whether this batch is suspended or not.
   * @return suspended
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Indicates whether this batch is suspended or not.")
  @JsonProperty(JSON_PROPERTY_SUSPENDED)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSuspended() {
    return suspended;
  }


  public void setSuspended(Boolean suspended) {
    this.suspended = suspended;
  }


  public BatchStatisticsDto tenantId(String tenantId) {
    
    this.tenantId = tenantId;
    return this;
  }

   /**
   * The tenant id of the batch.
   * @return tenantId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The tenant id of the batch.")
  @JsonProperty(JSON_PROPERTY_TENANT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTenantId() {
    return tenantId;
  }


  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }


  public BatchStatisticsDto createUserId(String createUserId) {
    
    this.createUserId = createUserId;
    return this;
  }

   /**
   * The id of the user that created the batch.
   * @return createUserId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the user that created the batch.")
  @JsonProperty(JSON_PROPERTY_CREATE_USER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCreateUserId() {
    return createUserId;
  }


  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchStatisticsDto batchStatisticsDto = (BatchStatisticsDto) o;
    return Objects.equals(this.remainingJobs, batchStatisticsDto.remainingJobs) &&
        Objects.equals(this.completedJobs, batchStatisticsDto.completedJobs) &&
        Objects.equals(this.failedJobs, batchStatisticsDto.failedJobs) &&
        Objects.equals(this.id, batchStatisticsDto.id) &&
        Objects.equals(this.type, batchStatisticsDto.type) &&
        Objects.equals(this.totalJobs, batchStatisticsDto.totalJobs) &&
        Objects.equals(this.jobsCreated, batchStatisticsDto.jobsCreated) &&
        Objects.equals(this.batchJobsPerSeed, batchStatisticsDto.batchJobsPerSeed) &&
        Objects.equals(this.invocationsPerBatchJob, batchStatisticsDto.invocationsPerBatchJob) &&
        Objects.equals(this.seedJobDefinitionId, batchStatisticsDto.seedJobDefinitionId) &&
        Objects.equals(this.monitorJobDefinitionId, batchStatisticsDto.monitorJobDefinitionId) &&
        Objects.equals(this.batchJobDefinitionId, batchStatisticsDto.batchJobDefinitionId) &&
        Objects.equals(this.suspended, batchStatisticsDto.suspended) &&
        Objects.equals(this.tenantId, batchStatisticsDto.tenantId) &&
        Objects.equals(this.createUserId, batchStatisticsDto.createUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(remainingJobs, completedJobs, failedJobs, id, type, totalJobs, jobsCreated, batchJobsPerSeed, invocationsPerBatchJob, seedJobDefinitionId, monitorJobDefinitionId, batchJobDefinitionId, suspended, tenantId, createUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchStatisticsDto {\n");
    sb.append("    remainingJobs: ").append(toIndentedString(remainingJobs)).append("\n");
    sb.append("    completedJobs: ").append(toIndentedString(completedJobs)).append("\n");
    sb.append("    failedJobs: ").append(toIndentedString(failedJobs)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    totalJobs: ").append(toIndentedString(totalJobs)).append("\n");
    sb.append("    jobsCreated: ").append(toIndentedString(jobsCreated)).append("\n");
    sb.append("    batchJobsPerSeed: ").append(toIndentedString(batchJobsPerSeed)).append("\n");
    sb.append("    invocationsPerBatchJob: ").append(toIndentedString(invocationsPerBatchJob)).append("\n");
    sb.append("    seedJobDefinitionId: ").append(toIndentedString(seedJobDefinitionId)).append("\n");
    sb.append("    monitorJobDefinitionId: ").append(toIndentedString(monitorJobDefinitionId)).append("\n");
    sb.append("    batchJobDefinitionId: ").append(toIndentedString(batchJobDefinitionId)).append("\n");
    sb.append("    suspended: ").append(toIndentedString(suspended)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    createUserId: ").append(toIndentedString(createUserId)).append("\n");
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

