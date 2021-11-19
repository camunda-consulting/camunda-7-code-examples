package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricJobLogDto;
import com.camunda.consulting.openapi.client.model.HistoricJobLogQueryDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.HistoricJobLogApi")
public class HistoricJobLogApi {
    private ApiClient apiClient;

    public HistoricJobLogApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricJobLogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Job Log
     * Retrieves a historic job log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic job log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the log entry. (required)
     * @return HistoricJobLogDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricJobLogDto getHistoricJobLog(String id) throws RestClientException {
        return getHistoricJobLogWithHttpInfo(id).getBody();
    }

    /**
     * Get Job Log
     * Retrieves a historic job log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic job log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the log entry. (required)
     * @return ResponseEntity&lt;HistoricJobLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricJobLogDto> getHistoricJobLogWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricJobLog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/job-log/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<HistoricJobLogDto> returnType = new ParameterizedTypeReference<HistoricJobLogDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Logs
     * Queries for historic job logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param logId Filter by historic job log id. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobExceptionMessage Filter by job exception message. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param jobDefinitionType Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. (optional)
     * @param jobDefinitionConfiguration Filter by job definition configuration. (optional)
     * @param activityIdIn Only include historic job logs which belong to one of the passed activity ids. (optional)
     * @param failedActivityIdIn Only include historic job logs which belong to failures of one of the passed activity ids. (optional)
     * @param executionIdIn Only include historic job logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param deploymentId Filter by deployment id. (optional)
     * @param tenantIdIn Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param hostname Filter by hostname. (optional)
     * @param jobPriorityLowerThanOrEquals Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param jobPriorityHigherThanOrEquals Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;HistoricJobLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricJobLogDto> getHistoricJobLogs(String logId, String jobId, String jobExceptionMessage, String jobDefinitionId, String jobDefinitionType, String jobDefinitionConfiguration, String activityIdIn, String failedActivityIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String deploymentId, String tenantIdIn, Boolean withoutTenantId, String hostname, Long jobPriorityLowerThanOrEquals, Long jobPriorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getHistoricJobLogsWithHttpInfo(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Job Logs
     * Queries for historic job logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param logId Filter by historic job log id. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobExceptionMessage Filter by job exception message. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param jobDefinitionType Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. (optional)
     * @param jobDefinitionConfiguration Filter by job definition configuration. (optional)
     * @param activityIdIn Only include historic job logs which belong to one of the passed activity ids. (optional)
     * @param failedActivityIdIn Only include historic job logs which belong to failures of one of the passed activity ids. (optional)
     * @param executionIdIn Only include historic job logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param deploymentId Filter by deployment id. (optional)
     * @param tenantIdIn Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param hostname Filter by hostname. (optional)
     * @param jobPriorityLowerThanOrEquals Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param jobPriorityHigherThanOrEquals Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricJobLogDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricJobLogDto>> getHistoricJobLogsWithHttpInfo(String logId, String jobId, String jobExceptionMessage, String jobDefinitionId, String jobDefinitionType, String jobDefinitionConfiguration, String activityIdIn, String failedActivityIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String deploymentId, String tenantIdIn, Boolean withoutTenantId, String hostname, Long jobPriorityLowerThanOrEquals, Long jobPriorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/job-log", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "logId", logId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobId", jobId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobExceptionMessage", jobExceptionMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionType", jobDefinitionType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionConfiguration", jobDefinitionConfiguration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityIdIn", failedActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "hostname", hostname));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobPriorityLowerThanOrEquals", jobPriorityLowerThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobPriorityHigherThanOrEquals", jobPriorityHigherThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "creationLog", creationLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failureLog", failureLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "successLog", successLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deletionLog", deletionLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricJobLogDto>> returnType = new ParameterizedTypeReference<List<HistoricJobLogDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Log Count
     * Queries for the number of historic job logs that fulfill the given parameters. Takes the same parameters as the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param logId Filter by historic job log id. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobExceptionMessage Filter by job exception message. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param jobDefinitionType Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. (optional)
     * @param jobDefinitionConfiguration Filter by job definition configuration. (optional)
     * @param activityIdIn Only include historic job logs which belong to one of the passed activity ids. (optional)
     * @param failedActivityIdIn Only include historic job logs which belong to failures of one of the passed activity ids. (optional)
     * @param executionIdIn Only include historic job logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param deploymentId Filter by deployment id. (optional)
     * @param tenantIdIn Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param hostname Filter by hostname. (optional)
     * @param jobPriorityLowerThanOrEquals Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param jobPriorityHigherThanOrEquals Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricJobLogsCount(String logId, String jobId, String jobExceptionMessage, String jobDefinitionId, String jobDefinitionType, String jobDefinitionConfiguration, String activityIdIn, String failedActivityIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String deploymentId, String tenantIdIn, Boolean withoutTenantId, String hostname, Long jobPriorityLowerThanOrEquals, Long jobPriorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog) throws RestClientException {
        return getHistoricJobLogsCountWithHttpInfo(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog).getBody();
    }

    /**
     * Get Job Log Count
     * Queries for the number of historic job logs that fulfill the given parameters. Takes the same parameters as the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param logId Filter by historic job log id. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobExceptionMessage Filter by job exception message. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param jobDefinitionType Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. (optional)
     * @param jobDefinitionConfiguration Filter by job definition configuration. (optional)
     * @param activityIdIn Only include historic job logs which belong to one of the passed activity ids. (optional)
     * @param failedActivityIdIn Only include historic job logs which belong to failures of one of the passed activity ids. (optional)
     * @param executionIdIn Only include historic job logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param deploymentId Filter by deployment id. (optional)
     * @param tenantIdIn Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param hostname Filter by hostname. (optional)
     * @param jobPriorityLowerThanOrEquals Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param jobPriorityHigherThanOrEquals Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricJobLogsCountWithHttpInfo(String logId, String jobId, String jobExceptionMessage, String jobDefinitionId, String jobDefinitionType, String jobDefinitionConfiguration, String activityIdIn, String failedActivityIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String deploymentId, String tenantIdIn, Boolean withoutTenantId, String hostname, Long jobPriorityLowerThanOrEquals, Long jobPriorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/job-log/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "logId", logId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobId", jobId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobExceptionMessage", jobExceptionMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionType", jobDefinitionType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionConfiguration", jobDefinitionConfiguration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityIdIn", failedActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "hostname", hostname));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobPriorityLowerThanOrEquals", jobPriorityLowerThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobPriorityHigherThanOrEquals", jobPriorityHigherThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "creationLog", creationLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failureLog", failureLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "successLog", successLog));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deletionLog", deletionLog));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<CountResultDto> returnType = new ParameterizedTypeReference<CountResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Log Exception Stacktrace
     * Retrieves the corresponding exception stacktrace to the passed historic job log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic job log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic job log to get the exception stacktrace for. (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getStacktraceHistoricJobLog(String id) throws RestClientException {
        return getStacktraceHistoricJobLogWithHttpInfo(id).getBody();
    }

    /**
     * Get Job Log Exception Stacktrace
     * Retrieves the corresponding exception stacktrace to the passed historic job log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic job log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic job log to get the exception stacktrace for. (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getStacktraceHistoricJobLogWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getStacktraceHistoricJobLog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/job-log/{id}/stacktrace", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Logs (POST)
     * Queries for historic job logs that fulfill the given parameters. This method is slightly more powerful than the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method because it allows filtering by historic job logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicJobLogQueryDto  (optional)
     * @return List&lt;HistoricJobLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricJobLogDto> queryHistoricJobLogs(Integer firstResult, Integer maxResults, HistoricJobLogQueryDto historicJobLogQueryDto) throws RestClientException {
        return queryHistoricJobLogsWithHttpInfo(firstResult, maxResults, historicJobLogQueryDto).getBody();
    }

    /**
     * Get Job Logs (POST)
     * Queries for historic job logs that fulfill the given parameters. This method is slightly more powerful than the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method because it allows filtering by historic job logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicJobLogQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricJobLogDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricJobLogDto>> queryHistoricJobLogsWithHttpInfo(Integer firstResult, Integer maxResults, HistoricJobLogQueryDto historicJobLogQueryDto) throws RestClientException {
        Object postBody = historicJobLogQueryDto;
        
        String path = apiClient.expandPath("/history/job-log", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricJobLogDto>> returnType = new ParameterizedTypeReference<List<HistoricJobLogDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Log Count (POST)
     * Queries for the number of historic job logs that fulfill the given parameters. This method takes the same message body as the [Get Job Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/post-job-log-query/) method and therefore it is slightly more powerful than the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicJobLogQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryHistoricJobLogsCount(HistoricJobLogQueryDto historicJobLogQueryDto) throws RestClientException {
        return queryHistoricJobLogsCountWithHttpInfo(historicJobLogQueryDto).getBody();
    }

    /**
     * Get Job Log Count (POST)
     * Queries for the number of historic job logs that fulfill the given parameters. This method takes the same message body as the [Get Job Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/post-job-log-query/) method and therefore it is slightly more powerful than the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicJobLogQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryHistoricJobLogsCountWithHttpInfo(HistoricJobLogQueryDto historicJobLogQueryDto) throws RestClientException {
        Object postBody = historicJobLogQueryDto;
        
        String path = apiClient.expandPath("/history/job-log/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<CountResultDto> returnType = new ParameterizedTypeReference<CountResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
