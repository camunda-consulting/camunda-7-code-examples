package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricExternalTaskLogDto;
import com.camunda.consulting.openapi.client.model.HistoricExternalTaskLogQueryDto;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T17:49:51.183809+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi")
public class HistoricExternalTaskLogApi {
    private ApiClient apiClient;

    public HistoricExternalTaskLogApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricExternalTaskLogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get External Task Log Error Details
     * Retrieves the corresponding error details of the passed historic external task log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic external task log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic external task log to get the error details for. (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getErrorDetailsHistoricExternalTaskLog(String id) throws RestClientException {
        return getErrorDetailsHistoricExternalTaskLogWithHttpInfo(id).getBody();
    }

    /**
     * Get External Task Log Error Details
     * Retrieves the corresponding error details of the passed historic external task log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic external task log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic external task log to get the error details for. (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getErrorDetailsHistoricExternalTaskLogWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getErrorDetailsHistoricExternalTaskLog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/external-task-log/{id}/error-details", uriVariables);

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
     * Get External Task Log
     * Retrieves a historic external task log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic external task log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the log entry. (required)
     * @return HistoricExternalTaskLogDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricExternalTaskLogDto getHistoricExternalTaskLog(String id) throws RestClientException {
        return getHistoricExternalTaskLogWithHttpInfo(id).getBody();
    }

    /**
     * Get External Task Log
     * Retrieves a historic external task log by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic external task log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the log entry. (required)
     * @return ResponseEntity&lt;HistoricExternalTaskLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricExternalTaskLogDto> getHistoricExternalTaskLogWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricExternalTaskLog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/external-task-log/{id}", uriVariables);

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

        ParameterizedTypeReference<HistoricExternalTaskLogDto> returnType = new ParameterizedTypeReference<HistoricExternalTaskLogDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get External Task Logs
     * Queries for historic external task logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get External Task Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param logId Filter by historic external task log id. (optional)
     * @param externalTaskId Filter by external task id. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param errorMessage Filter by external task exception message. (optional)
     * @param activityIdIn Only include historic external task logs which belong to one of the passed activity ids. (optional)
     * @param activityInstanceIdIn Only include historic external task logs which belong to one of the passed activity instance ids. (optional)
     * @param executionIdIn Only include historic external task logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param tenantIdIn Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;HistoricExternalTaskLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricExternalTaskLogDto> getHistoricExternalTaskLogs(String logId, String externalTaskId, String topicName, String workerId, String errorMessage, String activityIdIn, String activityInstanceIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String tenantIdIn, Boolean withoutTenantId, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getHistoricExternalTaskLogsWithHttpInfo(logId, externalTaskId, topicName, workerId, errorMessage, activityIdIn, activityInstanceIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, tenantIdIn, withoutTenantId, priorityLowerThanOrEquals, priorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get External Task Logs
     * Queries for historic external task logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get External Task Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param logId Filter by historic external task log id. (optional)
     * @param externalTaskId Filter by external task id. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param errorMessage Filter by external task exception message. (optional)
     * @param activityIdIn Only include historic external task logs which belong to one of the passed activity ids. (optional)
     * @param activityInstanceIdIn Only include historic external task logs which belong to one of the passed activity instance ids. (optional)
     * @param executionIdIn Only include historic external task logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param tenantIdIn Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricExternalTaskLogDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricExternalTaskLogDto>> getHistoricExternalTaskLogsWithHttpInfo(String logId, String externalTaskId, String topicName, String workerId, String errorMessage, String activityIdIn, String activityInstanceIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String tenantIdIn, Boolean withoutTenantId, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/external-task-log", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "logId", logId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskId", externalTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "topicName", topicName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "workerId", workerId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "errorMessage", errorMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityLowerThanOrEquals", priorityLowerThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityHigherThanOrEquals", priorityHigherThanOrEquals));
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

        ParameterizedTypeReference<List<HistoricExternalTaskLogDto>> returnType = new ParameterizedTypeReference<List<HistoricExternalTaskLogDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get External Task Log Count
     * Queries for the number of historic external task logs that fulfill the given parameters. Takes the same parameters as the [Get External Task Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param logId Filter by historic external task log id. (optional)
     * @param externalTaskId Filter by external task id. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param errorMessage Filter by external task exception message. (optional)
     * @param activityIdIn Only include historic external task logs which belong to one of the passed activity ids. (optional)
     * @param activityInstanceIdIn Only include historic external task logs which belong to one of the passed activity instance ids. (optional)
     * @param executionIdIn Only include historic external task logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param tenantIdIn Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricExternalTaskLogsCount(String logId, String externalTaskId, String topicName, String workerId, String errorMessage, String activityIdIn, String activityInstanceIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String tenantIdIn, Boolean withoutTenantId, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog) throws RestClientException {
        return getHistoricExternalTaskLogsCountWithHttpInfo(logId, externalTaskId, topicName, workerId, errorMessage, activityIdIn, activityInstanceIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, tenantIdIn, withoutTenantId, priorityLowerThanOrEquals, priorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog).getBody();
    }

    /**
     * Get External Task Log Count
     * Queries for the number of historic external task logs that fulfill the given parameters. Takes the same parameters as the [Get External Task Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param logId Filter by historic external task log id. (optional)
     * @param externalTaskId Filter by external task id. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param errorMessage Filter by external task exception message. (optional)
     * @param activityIdIn Only include historic external task logs which belong to one of the passed activity ids. (optional)
     * @param activityInstanceIdIn Only include historic external task logs which belong to one of the passed activity instance ids. (optional)
     * @param executionIdIn Only include historic external task logs which belong to one of the passed execution ids. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param tenantIdIn Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param creationLog Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param failureLog Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param successLog Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param deletionLog Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricExternalTaskLogsCountWithHttpInfo(String logId, String externalTaskId, String topicName, String workerId, String errorMessage, String activityIdIn, String activityInstanceIdIn, String executionIdIn, String processInstanceId, String processDefinitionId, String processDefinitionKey, String tenantIdIn, Boolean withoutTenantId, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, Boolean creationLog, Boolean failureLog, Boolean successLog, Boolean deletionLog) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/external-task-log/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "logId", logId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskId", externalTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "topicName", topicName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "workerId", workerId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "errorMessage", errorMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityLowerThanOrEquals", priorityLowerThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityHigherThanOrEquals", priorityHigherThanOrEquals));
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
     * Get External Task Logs (POST)
     * Queries for historic external task logs that fulfill the given parameters. This method is slightly more powerful than the [Get External Task Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query/) method because it allows filtering by historic external task logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicExternalTaskLogQueryDto  (optional)
     * @return List&lt;HistoricExternalTaskLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricExternalTaskLogDto> queryHistoricExternalTaskLogs(HistoricExternalTaskLogQueryDto historicExternalTaskLogQueryDto) throws RestClientException {
        return queryHistoricExternalTaskLogsWithHttpInfo(historicExternalTaskLogQueryDto).getBody();
    }

    /**
     * Get External Task Logs (POST)
     * Queries for historic external task logs that fulfill the given parameters. This method is slightly more powerful than the [Get External Task Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query/) method because it allows filtering by historic external task logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicExternalTaskLogQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricExternalTaskLogDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricExternalTaskLogDto>> queryHistoricExternalTaskLogsWithHttpInfo(HistoricExternalTaskLogQueryDto historicExternalTaskLogQueryDto) throws RestClientException {
        Object postBody = historicExternalTaskLogQueryDto;
        
        String path = apiClient.expandPath("/history/external-task-log", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<HistoricExternalTaskLogDto>> returnType = new ParameterizedTypeReference<List<HistoricExternalTaskLogDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get External Task Log Count (POST)
     * Queries for the number of historic external task logs that fulfill the given parameters. This method takes the same message body as the [Get External Task Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/post-external-task-log-query/) method and therefore it is slightly more powerful than the [Get External Task Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicExternalTaskLogQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryHistoricExternalTaskLogsCount(HistoricExternalTaskLogQueryDto historicExternalTaskLogQueryDto) throws RestClientException {
        return queryHistoricExternalTaskLogsCountWithHttpInfo(historicExternalTaskLogQueryDto).getBody();
    }

    /**
     * Get External Task Log Count (POST)
     * Queries for the number of historic external task logs that fulfill the given parameters. This method takes the same message body as the [Get External Task Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/post-external-task-log-query/) method and therefore it is slightly more powerful than the [Get External Task Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicExternalTaskLogQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryHistoricExternalTaskLogsCountWithHttpInfo(HistoricExternalTaskLogQueryDto historicExternalTaskLogQueryDto) throws RestClientException {
        Object postBody = historicExternalTaskLogQueryDto;
        
        String path = apiClient.expandPath("/history/external-task-log/count", Collections.<String, Object>emptyMap());

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
