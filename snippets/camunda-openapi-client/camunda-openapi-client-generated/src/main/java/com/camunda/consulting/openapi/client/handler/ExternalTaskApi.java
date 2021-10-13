package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CompleteExternalTaskDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.ExtendLockOnExternalTaskDto;
import com.camunda.consulting.openapi.client.model.ExternalTaskBpmnError;
import com.camunda.consulting.openapi.client.model.ExternalTaskDto;
import com.camunda.consulting.openapi.client.model.ExternalTaskFailureDto;
import com.camunda.consulting.openapi.client.model.ExternalTaskQueryDto;
import com.camunda.consulting.openapi.client.model.FetchExternalTasksDto;
import com.camunda.consulting.openapi.client.model.LockExternalTaskDto;
import com.camunda.consulting.openapi.client.model.LockedExternalTaskDto;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.PriorityDto;
import com.camunda.consulting.openapi.client.model.RetriesDto;
import com.camunda.consulting.openapi.client.model.SetRetriesForExternalTasksDto;

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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T16:56:52.297572+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.ExternalTaskApi")
public class ExternalTaskApi {
    private ApiClient apiClient;

    public ExternalTaskApi() {
        this(new ApiClient());
    }

    @Autowired
    public ExternalTaskApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Complete
     * Completes an external task by id and updates process variables.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task&#39;s most recent lock was not acquired by the provided worker. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Returned if the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to complete. (required)
     * @param completeExternalTaskDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void completeExternalTaskResource(String id, CompleteExternalTaskDto completeExternalTaskDto) throws RestClientException {
        completeExternalTaskResourceWithHttpInfo(id, completeExternalTaskDto);
    }

    /**
     * Complete
     * Completes an external task by id and updates process variables.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task&#39;s most recent lock was not acquired by the provided worker. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Returned if the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to complete. (required)
     * @param completeExternalTaskDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> completeExternalTaskResourceWithHttpInfo(String id, CompleteExternalTaskDto completeExternalTaskDto) throws RestClientException {
        Object postBody = completeExternalTaskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling completeExternalTaskResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/complete", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Extend Lock
     * Extends the timeout of the lock by a given amount of time.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case the new lock duration is negative or the external task is not locked by the given worker or not  locked at all, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task. (required)
     * @param extendLockOnExternalTaskDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void extendLock(String id, ExtendLockOnExternalTaskDto extendLockOnExternalTaskDto) throws RestClientException {
        extendLockWithHttpInfo(id, extendLockOnExternalTaskDto);
    }

    /**
     * Extend Lock
     * Extends the timeout of the lock by a given amount of time.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case the new lock duration is negative or the external task is not locked by the given worker or not  locked at all, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task. (required)
     * @param extendLockOnExternalTaskDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> extendLockWithHttpInfo(String id, ExtendLockOnExternalTaskDto extendLockOnExternalTaskDto) throws RestClientException {
        Object postBody = extendLockOnExternalTaskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling extendLock");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/extendLock", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Fetch and Lock
     * Fetches and locks a specific number of external tasks for execution by a worker. Query can be restricted to specific task topics and for each task topic an individual lock time can be provided.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param fetchExternalTasksDto  (optional)
     * @return List&lt;LockedExternalTaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<LockedExternalTaskDto> fetchAndLock(FetchExternalTasksDto fetchExternalTasksDto) throws RestClientException {
        return fetchAndLockWithHttpInfo(fetchExternalTasksDto).getBody();
    }

    /**
     * Fetch and Lock
     * Fetches and locks a specific number of external tasks for execution by a worker. Query can be restricted to specific task topics and for each task topic an individual lock time can be provided.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param fetchExternalTasksDto  (optional)
     * @return ResponseEntity&lt;List&lt;LockedExternalTaskDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<LockedExternalTaskDto>> fetchAndLockWithHttpInfo(FetchExternalTasksDto fetchExternalTasksDto) throws RestClientException {
        Object postBody = fetchExternalTasksDto;
        
        String path = apiClient.expandPath("/external-task/fetchAndLock", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<LockedExternalTaskDto>> returnType = new ParameterizedTypeReference<List<LockedExternalTaskDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get
     * Retrieves an external task by id, corresponding to the &#x60;ExternalTask&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - External task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to be retrieved. (required)
     * @return ExternalTaskDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExternalTaskDto getExternalTask(String id) throws RestClientException {
        return getExternalTaskWithHttpInfo(id).getBody();
    }

    /**
     * Get
     * Retrieves an external task by id, corresponding to the &#x60;ExternalTask&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - External task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to be retrieved. (required)
     * @return ResponseEntity&lt;ExternalTaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExternalTaskDto> getExternalTaskWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getExternalTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}", uriVariables);

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

        ParameterizedTypeReference<ExternalTaskDto> returnType = new ParameterizedTypeReference<ExternalTaskDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Error Details
     * Retrieves the error details in the context of a running external task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>204</b> - Request successful. In case the external task has no error details.
     * <p><b>500</b> - An external task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task for which the error details should be retrieved. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String getExternalTaskErrorDetails(String id) throws RestClientException {
        return getExternalTaskErrorDetailsWithHttpInfo(id).getBody();
    }

    /**
     * Get Error Details
     * Retrieves the error details in the context of a running external task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>204</b> - Request successful. In case the external task has no error details.
     * <p><b>500</b> - An external task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task for which the error details should be retrieved. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> getExternalTaskErrorDetailsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getExternalTaskErrorDetails");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/errorDetails", uriVariables);

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

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for the external tasks that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of executions. The size of the result set can be retrieved by using the [Get External Task Count](https://docs.camunda.org/manual/7.16/reference/rest/external-task/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param externalTaskId Filter by an external task&#39;s id. (optional)
     * @param externalTaskIdIn Filter by the comma-separated list of external task ids. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param locked Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param notLocked Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withRetriesLeft Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param noRetriesLeft Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param lockExpirationAfter Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param lockExpirationBefore Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Filter by the id of the activity that an external task is created for. (optional)
     * @param activityIdIn Filter by the comma-separated list of ids of the activities that an external task is created for. (optional)
     * @param executionId Filter by the id of the execution that an external task belongs to. (optional)
     * @param processInstanceId Filter by the id of the process instance that an external task belongs to. (optional)
     * @param processInstanceIdIn Filter by a comma-separated list of process instance ids that an external task may belong to. (optional)
     * @param processDefinitionId Filter by the id of the process definition that an external task belongs to. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. (optional)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;ExternalTaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ExternalTaskDto> getExternalTasks(String externalTaskId, String externalTaskIdIn, String topicName, String workerId, Boolean locked, Boolean notLocked, Boolean withRetriesLeft, Boolean noRetriesLeft, OffsetDateTime lockExpirationAfter, OffsetDateTime lockExpirationBefore, String activityId, String activityIdIn, String executionId, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String tenantIdIn, Boolean active, Boolean suspended, Long priorityHigherThanOrEquals, Long priorityLowerThanOrEquals, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getExternalTasksWithHttpInfo(externalTaskId, externalTaskIdIn, topicName, workerId, locked, notLocked, withRetriesLeft, noRetriesLeft, lockExpirationAfter, lockExpirationBefore, activityId, activityIdIn, executionId, processInstanceId, processInstanceIdIn, processDefinitionId, tenantIdIn, active, suspended, priorityHigherThanOrEquals, priorityLowerThanOrEquals, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for the external tasks that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of executions. The size of the result set can be retrieved by using the [Get External Task Count](https://docs.camunda.org/manual/7.16/reference/rest/external-task/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param externalTaskId Filter by an external task&#39;s id. (optional)
     * @param externalTaskIdIn Filter by the comma-separated list of external task ids. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param locked Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param notLocked Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withRetriesLeft Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param noRetriesLeft Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param lockExpirationAfter Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param lockExpirationBefore Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Filter by the id of the activity that an external task is created for. (optional)
     * @param activityIdIn Filter by the comma-separated list of ids of the activities that an external task is created for. (optional)
     * @param executionId Filter by the id of the execution that an external task belongs to. (optional)
     * @param processInstanceId Filter by the id of the process instance that an external task belongs to. (optional)
     * @param processInstanceIdIn Filter by a comma-separated list of process instance ids that an external task may belong to. (optional)
     * @param processDefinitionId Filter by the id of the process definition that an external task belongs to. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. (optional)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;ExternalTaskDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ExternalTaskDto>> getExternalTasksWithHttpInfo(String externalTaskId, String externalTaskIdIn, String topicName, String workerId, Boolean locked, Boolean notLocked, Boolean withRetriesLeft, Boolean noRetriesLeft, OffsetDateTime lockExpirationAfter, OffsetDateTime lockExpirationBefore, String activityId, String activityIdIn, String executionId, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String tenantIdIn, Boolean active, Boolean suspended, Long priorityHigherThanOrEquals, Long priorityLowerThanOrEquals, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/external-task", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskId", externalTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskIdIn", externalTaskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "topicName", topicName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "workerId", workerId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "locked", locked));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "notLocked", notLocked));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRetriesLeft", withRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "noRetriesLeft", noRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lockExpirationAfter", lockExpirationAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lockExpirationBefore", lockExpirationBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityHigherThanOrEquals", priorityHigherThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityLowerThanOrEquals", priorityLowerThanOrEquals));
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

        ParameterizedTypeReference<List<ExternalTaskDto>> returnType = new ParameterizedTypeReference<List<ExternalTaskDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of external tasks that fulfill given parameters. Takes the same parameters as the [Get External Tasks](https://docs.camunda.org/manual/7.16/reference/rest/external-task/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param externalTaskId Filter by an external task&#39;s id. (optional)
     * @param externalTaskIdIn Filter by the comma-separated list of external task ids. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param locked Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param notLocked Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withRetriesLeft Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param noRetriesLeft Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param lockExpirationAfter Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param lockExpirationBefore Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Filter by the id of the activity that an external task is created for. (optional)
     * @param activityIdIn Filter by the comma-separated list of ids of the activities that an external task is created for. (optional)
     * @param executionId Filter by the id of the execution that an external task belongs to. (optional)
     * @param processInstanceId Filter by the id of the process instance that an external task belongs to. (optional)
     * @param processInstanceIdIn Filter by a comma-separated list of process instance ids that an external task may belong to. (optional)
     * @param processDefinitionId Filter by the id of the process definition that an external task belongs to. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. (optional)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getExternalTasksCount(String externalTaskId, String externalTaskIdIn, String topicName, String workerId, Boolean locked, Boolean notLocked, Boolean withRetriesLeft, Boolean noRetriesLeft, OffsetDateTime lockExpirationAfter, OffsetDateTime lockExpirationBefore, String activityId, String activityIdIn, String executionId, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String tenantIdIn, Boolean active, Boolean suspended, Long priorityHigherThanOrEquals, Long priorityLowerThanOrEquals) throws RestClientException {
        return getExternalTasksCountWithHttpInfo(externalTaskId, externalTaskIdIn, topicName, workerId, locked, notLocked, withRetriesLeft, noRetriesLeft, lockExpirationAfter, lockExpirationBefore, activityId, activityIdIn, executionId, processInstanceId, processInstanceIdIn, processDefinitionId, tenantIdIn, active, suspended, priorityHigherThanOrEquals, priorityLowerThanOrEquals).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of external tasks that fulfill given parameters. Takes the same parameters as the [Get External Tasks](https://docs.camunda.org/manual/7.16/reference/rest/external-task/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param externalTaskId Filter by an external task&#39;s id. (optional)
     * @param externalTaskIdIn Filter by the comma-separated list of external task ids. (optional)
     * @param topicName Filter by an external task topic. (optional)
     * @param workerId Filter by the id of the worker that the task was most recently locked by. (optional)
     * @param locked Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param notLocked Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withRetriesLeft Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param noRetriesLeft Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param lockExpirationAfter Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param lockExpirationBefore Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Filter by the id of the activity that an external task is created for. (optional)
     * @param activityIdIn Filter by the comma-separated list of ids of the activities that an external task is created for. (optional)
     * @param executionId Filter by the id of the execution that an external task belongs to. (optional)
     * @param processInstanceId Filter by the id of the process instance that an external task belongs to. (optional)
     * @param processInstanceIdIn Filter by a comma-separated list of process instance ids that an external task may belong to. (optional)
     * @param processDefinitionId Filter by the id of the process definition that an external task belongs to. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. (optional)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getExternalTasksCountWithHttpInfo(String externalTaskId, String externalTaskIdIn, String topicName, String workerId, Boolean locked, Boolean notLocked, Boolean withRetriesLeft, Boolean noRetriesLeft, OffsetDateTime lockExpirationAfter, OffsetDateTime lockExpirationBefore, String activityId, String activityIdIn, String executionId, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String tenantIdIn, Boolean active, Boolean suspended, Long priorityHigherThanOrEquals, Long priorityLowerThanOrEquals) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/external-task/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskId", externalTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskIdIn", externalTaskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "topicName", topicName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "workerId", workerId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "locked", locked));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "notLocked", notLocked));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRetriesLeft", withRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "noRetriesLeft", noRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lockExpirationAfter", lockExpirationAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lockExpirationBefore", lockExpirationBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityHigherThanOrEquals", priorityHigherThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityLowerThanOrEquals", priorityLowerThanOrEquals));

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
     * Get External Task Topic Names
     * Queries for distinct topic names of external tasks that fulfill given parameters. Query can be restricted to only tasks with retries left, tasks that are locked, or tasks that are unlocked. The parameters withLockedTasks and withUnlockedTasks are exclusive. Setting them both to true will return an empty list. Providing no parameters will return a list of all distinct topic names with external tasks.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param withLockedTasks Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withUnlockedTasks Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withRetriesLeft Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @return List&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<String> getTopicNames(Boolean withLockedTasks, Boolean withUnlockedTasks, Boolean withRetriesLeft) throws RestClientException {
        return getTopicNamesWithHttpInfo(withLockedTasks, withUnlockedTasks, withRetriesLeft).getBody();
    }

    /**
     * Get External Task Topic Names
     * Queries for distinct topic names of external tasks that fulfill given parameters. Query can be restricted to only tasks with retries left, tasks that are locked, or tasks that are unlocked. The parameters withLockedTasks and withUnlockedTasks are exclusive. Setting them both to true will return an empty list. Providing no parameters will return a list of all distinct topic names with external tasks.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param withLockedTasks Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withUnlockedTasks Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @param withRetriesLeft Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. (optional)
     * @return ResponseEntity&lt;List&lt;String&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<String>> getTopicNamesWithHttpInfo(Boolean withLockedTasks, Boolean withUnlockedTasks, Boolean withRetriesLeft) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/external-task/topic-names", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withLockedTasks", withLockedTasks));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withUnlockedTasks", withUnlockedTasks));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRetriesLeft", withRetriesLeft));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<String>> returnType = new ParameterizedTypeReference<List<String>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Handle BPMN Error
     * Reports a business error in the context of a running external task by id. The error code must be specified to identify the BPMN error handler.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task&#39;s most recent lock was not acquired by the provided worker.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Returned if the corresponding process instance could not be resumed successfully.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task in which context a BPMN error is reported. (required)
     * @param externalTaskBpmnError  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void handleExternalTaskBpmnError(String id, ExternalTaskBpmnError externalTaskBpmnError) throws RestClientException {
        handleExternalTaskBpmnErrorWithHttpInfo(id, externalTaskBpmnError);
    }

    /**
     * Handle BPMN Error
     * Reports a business error in the context of a running external task by id. The error code must be specified to identify the BPMN error handler.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task&#39;s most recent lock was not acquired by the provided worker.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Returned if the corresponding process instance could not be resumed successfully.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task in which context a BPMN error is reported. (required)
     * @param externalTaskBpmnError  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> handleExternalTaskBpmnErrorWithHttpInfo(String id, ExternalTaskBpmnError externalTaskBpmnError) throws RestClientException {
        Object postBody = externalTaskBpmnError;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling handleExternalTaskBpmnError");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/bpmnError", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Handle Failure
     * Reports a failure to execute an external task by id. A number of retries and a timeout until the task can be retried can be specified. If retries are set to 0, an incident for this task is created.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task&#39;s most recent lock was not acquired by the provided worker. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Returned if the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to report a failure for. (required)
     * @param externalTaskFailureDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void handleFailure(String id, ExternalTaskFailureDto externalTaskFailureDto) throws RestClientException {
        handleFailureWithHttpInfo(id, externalTaskFailureDto);
    }

    /**
     * Handle Failure
     * Reports a failure to execute an external task by id. A number of retries and a timeout until the task can be retried can be specified. If retries are set to 0, an incident for this task is created.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task&#39;s most recent lock was not acquired by the provided worker. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Returned if the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to report a failure for. (required)
     * @param externalTaskFailureDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> handleFailureWithHttpInfo(String id, ExternalTaskFailureDto externalTaskFailureDto) throws RestClientException {
        Object postBody = externalTaskFailureDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling handleFailure");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/failure", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * 
     * Lock an external task by a given id for a specified worker and amount of time.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case the lock duration is negative or the external task is already locked by a different worker, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task. (required)
     * @param lockExternalTaskDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void lock(String id, LockExternalTaskDto lockExternalTaskDto) throws RestClientException {
        lockWithHttpInfo(id, lockExternalTaskDto);
    }

    /**
     * 
     * Lock an external task by a given id for a specified worker and amount of time.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case the lock duration is negative or the external task is already locked by a different worker, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task. (required)
     * @param lockExternalTaskDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> lockWithHttpInfo(String id, LockExternalTaskDto lockExternalTaskDto) throws RestClientException {
        Object postBody = lockExternalTaskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling lock");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/lock", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List (POST)
     * Queries for external tasks that fulfill given parameters in the form of a JSON object.  This method is slightly more powerful than the [Get External Tasks](https://docs.camunda.org/manual/7.16/reference/rest/external-task/get-query/) method because it allows to specify a hierarchical result sorting.
     * <p><b>200</b> - Request successful. The Response is a JSON array of external task objects.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param externalTaskQueryDto  (optional)
     * @return List&lt;ExternalTaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ExternalTaskDto> queryExternalTasks(Integer firstResult, Integer maxResults, ExternalTaskQueryDto externalTaskQueryDto) throws RestClientException {
        return queryExternalTasksWithHttpInfo(firstResult, maxResults, externalTaskQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for external tasks that fulfill given parameters in the form of a JSON object.  This method is slightly more powerful than the [Get External Tasks](https://docs.camunda.org/manual/7.16/reference/rest/external-task/get-query/) method because it allows to specify a hierarchical result sorting.
     * <p><b>200</b> - Request successful. The Response is a JSON array of external task objects.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param externalTaskQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;ExternalTaskDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ExternalTaskDto>> queryExternalTasksWithHttpInfo(Integer firstResult, Integer maxResults, ExternalTaskQueryDto externalTaskQueryDto) throws RestClientException {
        Object postBody = externalTaskQueryDto;
        
        String path = apiClient.expandPath("/external-task", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<ExternalTaskDto>> returnType = new ParameterizedTypeReference<List<ExternalTaskDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count (POST)
     * Queries for the number of external tasks that fulfill given parameters. This method takes the same message body as the [Get External Tasks (POST)](https://docs.camunda.org/manual/7.16/reference/rest/external-task/post-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param externalTaskQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryExternalTasksCount(ExternalTaskQueryDto externalTaskQueryDto) throws RestClientException {
        return queryExternalTasksCountWithHttpInfo(externalTaskQueryDto).getBody();
    }

    /**
     * Get List Count (POST)
     * Queries for the number of external tasks that fulfill given parameters. This method takes the same message body as the [Get External Tasks (POST)](https://docs.camunda.org/manual/7.16/reference/rest/external-task/post-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param externalTaskQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryExternalTasksCountWithHttpInfo(ExternalTaskQueryDto externalTaskQueryDto) throws RestClientException {
        Object postBody = externalTaskQueryDto;
        
        String path = apiClient.expandPath("/external-task/count", Collections.<String, Object>emptyMap());

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
    /**
     * Set Priority
     * Sets the priority of an existing external task by id. The default value of a priority is 0.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to set the priority for. (required)
     * @param priorityDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setExternalTaskResourcePriority(String id, PriorityDto priorityDto) throws RestClientException {
        setExternalTaskResourcePriorityWithHttpInfo(id, priorityDto);
    }

    /**
     * Set Priority
     * Sets the priority of an existing external task by id. The default value of a priority is 0.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to set the priority for. (required)
     * @param priorityDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setExternalTaskResourcePriorityWithHttpInfo(String id, PriorityDto priorityDto) throws RestClientException {
        Object postBody = priorityDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setExternalTaskResourcePriority");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/priority", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Set Retries
     * Sets the number of retries left to execute an external task by id. If retries are set to 0, an  incident is created.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - In case the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to set the number of retries for. (required)
     * @param retriesDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setExternalTaskResourceRetries(String id, RetriesDto retriesDto) throws RestClientException {
        setExternalTaskResourceRetriesWithHttpInfo(id, retriesDto);
    }

    /**
     * Set Retries
     * Sets the number of retries left to execute an external task by id. If retries are set to 0, an  incident is created.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - In case the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to set the number of retries for. (required)
     * @param retriesDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setExternalTaskResourceRetriesWithHttpInfo(String id, RetriesDto retriesDto) throws RestClientException {
        Object postBody = retriesDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setExternalTaskResourceRetries");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/retries", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Set Retries Sync
     * Sets the number of retries left to execute external tasks by id synchronously. If retries are set to 0,  an incident is created.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task,  e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRetriesForExternalTasksDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setExternalTaskRetries(SetRetriesForExternalTasksDto setRetriesForExternalTasksDto) throws RestClientException {
        setExternalTaskRetriesWithHttpInfo(setRetriesForExternalTasksDto);
    }

    /**
     * Set Retries Sync
     * Sets the number of retries left to execute external tasks by id synchronously. If retries are set to 0,  an incident is created.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task,  e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRetriesForExternalTasksDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setExternalTaskRetriesWithHttpInfo(SetRetriesForExternalTasksDto setRetriesForExternalTasksDto) throws RestClientException {
        Object postBody = setRetriesForExternalTasksDto;
        
        String path = apiClient.expandPath("/external-task/retries", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Set Retries Async
     * Sets the number of retries left to execute external tasks by id asynchronously. If retries are set to 0, an incident is created.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If neither externalTaskIds nor externalTaskQuery are present or externalTaskIds contains null value or  the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task,  e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRetriesForExternalTasksDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setExternalTaskRetriesAsyncOperation(SetRetriesForExternalTasksDto setRetriesForExternalTasksDto) throws RestClientException {
        return setExternalTaskRetriesAsyncOperationWithHttpInfo(setRetriesForExternalTasksDto).getBody();
    }

    /**
     * Set Retries Async
     * Sets the number of retries left to execute external tasks by id asynchronously. If retries are set to 0, an incident is created.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If neither externalTaskIds nor externalTaskQuery are present or externalTaskIds contains null value or  the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task,  e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRetriesForExternalTasksDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setExternalTaskRetriesAsyncOperationWithHttpInfo(SetRetriesForExternalTasksDto setRetriesForExternalTasksDto) throws RestClientException {
        Object postBody = setRetriesForExternalTasksDto;
        
        String path = apiClient.expandPath("/external-task/retries-async", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<BatchDto> returnType = new ParameterizedTypeReference<BatchDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Unlock
     * Unlocks an external task by id. Clears the task&#39;s lock expiration time and worker id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to unlock. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unlock(String id) throws RestClientException {
        unlockWithHttpInfo(id);
    }

    /**
     * Unlock
     * Unlocks an external task by id. Clears the task&#39;s lock expiration time and worker id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the external task to unlock. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unlockWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling unlock");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/external-task/{id}/unlock", uriVariables);

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

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
