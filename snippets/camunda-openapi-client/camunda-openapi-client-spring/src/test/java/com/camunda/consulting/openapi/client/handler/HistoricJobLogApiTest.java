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


package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricJobLogDto;
import com.camunda.consulting.openapi.client.model.HistoricJobLogQueryDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for HistoricJobLogApi
 */
@Ignore
public class HistoricJobLogApiTest {

    private final HistoricJobLogApi api = new HistoricJobLogApi();

    
    /**
     * Get Job Log
     *
     * Retrieves a historic job log by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getHistoricJobLogTest() {
        String id = null;
        HistoricJobLogDto response = api.getHistoricJobLog(id);

        // TODO: test validations
    }
    
    /**
     * Get Job Logs
     *
     * Queries for historic job logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getHistoricJobLogsTest() {
        String logId = null;
        String jobId = null;
        String jobExceptionMessage = null;
        String jobDefinitionId = null;
        String jobDefinitionType = null;
        String jobDefinitionConfiguration = null;
        String activityIdIn = null;
        String failedActivityIdIn = null;
        String executionIdIn = null;
        String processInstanceId = null;
        String processDefinitionId = null;
        String processDefinitionKey = null;
        String deploymentId = null;
        String tenantIdIn = null;
        Boolean withoutTenantId = null;
        String hostname = null;
        Long jobPriorityLowerThanOrEquals = null;
        Long jobPriorityHigherThanOrEquals = null;
        Boolean creationLog = null;
        Boolean failureLog = null;
        Boolean successLog = null;
        Boolean deletionLog = null;
        String sortBy = null;
        String sortOrder = null;
        Integer firstResult = null;
        Integer maxResults = null;
        List<HistoricJobLogDto> response = api.getHistoricJobLogs(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults);

        // TODO: test validations
    }
    
    /**
     * Get Job Log Count
     *
     * Queries for the number of historic job logs that fulfill the given parameters. Takes the same parameters as the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getHistoricJobLogsCountTest() {
        String logId = null;
        String jobId = null;
        String jobExceptionMessage = null;
        String jobDefinitionId = null;
        String jobDefinitionType = null;
        String jobDefinitionConfiguration = null;
        String activityIdIn = null;
        String failedActivityIdIn = null;
        String executionIdIn = null;
        String processInstanceId = null;
        String processDefinitionId = null;
        String processDefinitionKey = null;
        String deploymentId = null;
        String tenantIdIn = null;
        Boolean withoutTenantId = null;
        String hostname = null;
        Long jobPriorityLowerThanOrEquals = null;
        Long jobPriorityHigherThanOrEquals = null;
        Boolean creationLog = null;
        Boolean failureLog = null;
        Boolean successLog = null;
        Boolean deletionLog = null;
        CountResultDto response = api.getHistoricJobLogsCount(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog);

        // TODO: test validations
    }
    
    /**
     * Get Job Log Exception Stacktrace
     *
     * Retrieves the corresponding exception stacktrace to the passed historic job log by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getStacktraceHistoricJobLogTest() {
        String id = null;
        Object response = api.getStacktraceHistoricJobLog(id);

        // TODO: test validations
    }
    
    /**
     * Get Job Logs (POST)
     *
     * Queries for historic job logs that fulfill the given parameters. This method is slightly more powerful than the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method because it allows filtering by historic job logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void queryHistoricJobLogsTest() {
        Integer firstResult = null;
        Integer maxResults = null;
        HistoricJobLogQueryDto historicJobLogQueryDto = null;
        List<HistoricJobLogDto> response = api.queryHistoricJobLogs(firstResult, maxResults, historicJobLogQueryDto);

        // TODO: test validations
    }
    
    /**
     * Get Job Log Count (POST)
     *
     * Queries for the number of historic job logs that fulfill the given parameters. This method takes the same message body as the [Get Job Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/post-job-log-query/) method and therefore it is slightly more powerful than the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void queryHistoricJobLogsCountTest() {
        HistoricJobLogQueryDto historicJobLogQueryDto = null;
        CountResultDto response = api.queryHistoricJobLogsCount(historicJobLogQueryDto);

        // TODO: test validations
    }
    
}
