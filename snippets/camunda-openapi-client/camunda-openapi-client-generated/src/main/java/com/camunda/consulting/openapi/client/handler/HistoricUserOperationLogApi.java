package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AnnotationDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.UserOperationLogEntryDto;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricUserOperationLogApi")
public class HistoricUserOperationLogApi {
    private ApiClient apiClient;

    public HistoricUserOperationLogApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricUserOperationLogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Clear Annotation of an User Operation Log (Historic)
     * Clear the annotation which was previously set for auditing reasons.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the &#x60;operationId&#x60; path parameter value does not exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param operationId The operation id of the operation log to be updated. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void clearAnnotationUserOperationLog(String operationId) throws RestClientException {
        clearAnnotationUserOperationLogWithHttpInfo(operationId);
    }

    /**
     * Clear Annotation of an User Operation Log (Historic)
     * Clear the annotation which was previously set for auditing reasons.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the &#x60;operationId&#x60; path parameter value does not exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param operationId The operation id of the operation log to be updated. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> clearAnnotationUserOperationLogWithHttpInfo(String operationId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'operationId' is set
        if (operationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'operationId' when calling clearAnnotationUserOperationLog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("operationId", operationId);
        String path = apiClient.expandPath("/history/user-operation/{operationId}/clear-annotation", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get User Operation Log Count
     * Queries for the number of user operation log entries that fulfill the given parameters. Takes the same parameters as the [Get User Operation Log (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/get-user-operation-log-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param deploymentId Filter by deployment id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param taskId Only include operations on this task. (optional)
     * @param externalTaskId Only include operations on this external task. (optional)
     * @param batchId Only include operations on this batch. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param userId Only include operations of this user. (optional)
     * @param operationId Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation. (optional)
     * @param operationType Filter by the type of the operation like &#x60;Claim&#x60; or &#x60;Delegate&#x60;. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types. (optional)
     * @param entityType Filter by the type of the entity that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param entityTypeIn Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param category Filter by the category that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param categoryIn Filter by a comma-separated list of categories that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param property Only include operations that changed this property, e.g., &#x60;owner&#x60; or &#x60;assignee&#x60;. (optional)
     * @param afterTimestamp Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param beforeTimestamp Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryUserOperationCount(String deploymentId, String processDefinitionId, String processDefinitionKey, String processInstanceId, String executionId, String caseDefinitionId, String caseInstanceId, String caseExecutionId, String taskId, String externalTaskId, String batchId, String jobId, String jobDefinitionId, String userId, String operationId, String operationType, String entityType, String entityTypeIn, String category, String categoryIn, String property, OffsetDateTime afterTimestamp, OffsetDateTime beforeTimestamp) throws RestClientException {
        return queryUserOperationCountWithHttpInfo(deploymentId, processDefinitionId, processDefinitionKey, processInstanceId, executionId, caseDefinitionId, caseInstanceId, caseExecutionId, taskId, externalTaskId, batchId, jobId, jobDefinitionId, userId, operationId, operationType, entityType, entityTypeIn, category, categoryIn, property, afterTimestamp, beforeTimestamp).getBody();
    }

    /**
     * Get User Operation Log Count
     * Queries for the number of user operation log entries that fulfill the given parameters. Takes the same parameters as the [Get User Operation Log (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/get-user-operation-log-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param deploymentId Filter by deployment id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param taskId Only include operations on this task. (optional)
     * @param externalTaskId Only include operations on this external task. (optional)
     * @param batchId Only include operations on this batch. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param userId Only include operations of this user. (optional)
     * @param operationId Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation. (optional)
     * @param operationType Filter by the type of the operation like &#x60;Claim&#x60; or &#x60;Delegate&#x60;. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types. (optional)
     * @param entityType Filter by the type of the entity that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param entityTypeIn Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param category Filter by the category that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param categoryIn Filter by a comma-separated list of categories that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param property Only include operations that changed this property, e.g., &#x60;owner&#x60; or &#x60;assignee&#x60;. (optional)
     * @param afterTimestamp Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param beforeTimestamp Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryUserOperationCountWithHttpInfo(String deploymentId, String processDefinitionId, String processDefinitionKey, String processInstanceId, String executionId, String caseDefinitionId, String caseInstanceId, String caseExecutionId, String taskId, String externalTaskId, String batchId, String jobId, String jobDefinitionId, String userId, String operationId, String operationType, String entityType, String entityTypeIn, String category, String categoryIn, String property, OffsetDateTime afterTimestamp, OffsetDateTime beforeTimestamp) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/user-operation/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskId", externalTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "batchId", batchId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobId", jobId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "operationId", operationId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "operationType", operationType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "entityType", entityType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "entityTypeIn", entityTypeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryIn", categoryIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "property", property));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "afterTimestamp", afterTimestamp));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "beforeTimestamp", beforeTimestamp));

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
     * Get User Operation Log (Historic)
     * Queries for user operation log entries that fulfill the given parameters. The size of the result set can be retrieved by using the [Get User Operation Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/get-user-operation-log-query-count/) method.  Note that the properties of operation log entries are interpreted as restrictions on the entities they apply to. That means, if a single process instance is updated, the field &#x60;processInstanceId&#x60; is populated. If a single operation updates all process instances of the same process definition, the field &#x60;processInstanceId&#x60; is &#x60;null&#x60; (a &#x60;null&#x60; restriction is viewed as a wildcard, i.e., matches a process instance with any id) and the field &#x60;processDefinitionId&#x60; is populated. This way, which entities were changed by a user operation can easily be reconstructed.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param deploymentId Filter by deployment id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param taskId Only include operations on this task. (optional)
     * @param externalTaskId Only include operations on this external task. (optional)
     * @param batchId Only include operations on this batch. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param userId Only include operations of this user. (optional)
     * @param operationId Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation. (optional)
     * @param operationType Filter by the type of the operation like &#x60;Claim&#x60; or &#x60;Delegate&#x60;. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types. (optional)
     * @param entityType Filter by the type of the entity that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param entityTypeIn Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param category Filter by the category that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param categoryIn Filter by a comma-separated list of categories that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param property Only include operations that changed this property, e.g., &#x60;owner&#x60; or &#x60;assignee&#x60;. (optional)
     * @param afterTimestamp Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param beforeTimestamp Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;UserOperationLogEntryDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserOperationLogEntryDto> queryUserOperationEntries(String deploymentId, String processDefinitionId, String processDefinitionKey, String processInstanceId, String executionId, String caseDefinitionId, String caseInstanceId, String caseExecutionId, String taskId, String externalTaskId, String batchId, String jobId, String jobDefinitionId, String userId, String operationId, String operationType, String entityType, String entityTypeIn, String category, String categoryIn, String property, OffsetDateTime afterTimestamp, OffsetDateTime beforeTimestamp, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return queryUserOperationEntriesWithHttpInfo(deploymentId, processDefinitionId, processDefinitionKey, processInstanceId, executionId, caseDefinitionId, caseInstanceId, caseExecutionId, taskId, externalTaskId, batchId, jobId, jobDefinitionId, userId, operationId, operationType, entityType, entityTypeIn, category, categoryIn, property, afterTimestamp, beforeTimestamp, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get User Operation Log (Historic)
     * Queries for user operation log entries that fulfill the given parameters. The size of the result set can be retrieved by using the [Get User Operation Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/get-user-operation-log-query-count/) method.  Note that the properties of operation log entries are interpreted as restrictions on the entities they apply to. That means, if a single process instance is updated, the field &#x60;processInstanceId&#x60; is populated. If a single operation updates all process instances of the same process definition, the field &#x60;processInstanceId&#x60; is &#x60;null&#x60; (a &#x60;null&#x60; restriction is viewed as a wildcard, i.e., matches a process instance with any id) and the field &#x60;processDefinitionId&#x60; is populated. This way, which entities were changed by a user operation can easily be reconstructed.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param deploymentId Filter by deployment id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Filter by process definition key. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param taskId Only include operations on this task. (optional)
     * @param externalTaskId Only include operations on this external task. (optional)
     * @param batchId Only include operations on this batch. (optional)
     * @param jobId Filter by job id. (optional)
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param userId Only include operations of this user. (optional)
     * @param operationId Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation. (optional)
     * @param operationType Filter by the type of the operation like &#x60;Claim&#x60; or &#x60;Delegate&#x60;. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types. (optional)
     * @param entityType Filter by the type of the entity that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param entityTypeIn Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. (optional)
     * @param category Filter by the category that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param categoryIn Filter by a comma-separated list of categories that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. (optional)
     * @param property Only include operations that changed this property, e.g., &#x60;owner&#x60; or &#x60;assignee&#x60;. (optional)
     * @param afterTimestamp Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param beforeTimestamp Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;UserOperationLogEntryDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserOperationLogEntryDto>> queryUserOperationEntriesWithHttpInfo(String deploymentId, String processDefinitionId, String processDefinitionKey, String processInstanceId, String executionId, String caseDefinitionId, String caseInstanceId, String caseExecutionId, String taskId, String externalTaskId, String batchId, String jobId, String jobDefinitionId, String userId, String operationId, String operationType, String entityType, String entityTypeIn, String category, String categoryIn, String property, OffsetDateTime afterTimestamp, OffsetDateTime beforeTimestamp, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/user-operation", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externalTaskId", externalTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "batchId", batchId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobId", jobId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "operationId", operationId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "operationType", operationType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "entityType", entityType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "entityTypeIn", entityTypeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryIn", categoryIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "property", property));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "afterTimestamp", afterTimestamp));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "beforeTimestamp", beforeTimestamp));
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

        ParameterizedTypeReference<List<UserOperationLogEntryDto>> returnType = new ParameterizedTypeReference<List<UserOperationLogEntryDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Set Annotation to an User Operation Log (Historic)
     * Set an annotation for auditing reasons.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the &#x60;operationId&#x60; path parameter value does not exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param operationId The operation id of the operation log to be updated. (required)
     * @param annotationDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setAnnotationUserOperationLog(String operationId, AnnotationDto annotationDto) throws RestClientException {
        setAnnotationUserOperationLogWithHttpInfo(operationId, annotationDto);
    }

    /**
     * Set Annotation to an User Operation Log (Historic)
     * Set an annotation for auditing reasons.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the &#x60;operationId&#x60; path parameter value does not exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param operationId The operation id of the operation log to be updated. (required)
     * @param annotationDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setAnnotationUserOperationLogWithHttpInfo(String operationId, AnnotationDto annotationDto) throws RestClientException {
        Object postBody = annotationDto;
        
        // verify the required parameter 'operationId' is set
        if (operationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'operationId' when calling setAnnotationUserOperationLog");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("operationId", operationId);
        String path = apiClient.expandPath("/history/user-operation/{operationId}/set-annotation", uriVariables);

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
}
