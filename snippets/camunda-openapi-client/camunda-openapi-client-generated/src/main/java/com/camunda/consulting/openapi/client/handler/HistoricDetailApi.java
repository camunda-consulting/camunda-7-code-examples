package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.HistoricDetailDto;
import com.camunda.consulting.openapi.client.model.HistoricDetailQueryDto;
import java.time.OffsetDateTime;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricDetailApi")
public class HistoricDetailApi {
    private ApiClient apiClient;

    public HistoricDetailApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricDetailApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Historic Details
     * Queries for historic details that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Detail Count](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIdIn Only include historic details which belong to one of the passed comma-separated process instance ids. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param taskId Filter by task id. (optional)
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param variableInstanceId Filter by variable instance id. (optional)
     * @param variableTypeIn Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#x60;serializable&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic details that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param userOperationId Filter by a user operation id. (optional)
     * @param formFields Only include &#x60;HistoricFormFields&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param variableUpdates Only include &#x60;HistoricVariableUpdates&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param excludeTaskDetails Excludes all task-related &#x60;HistoricDetails&#x60;, so only items which have no task id set will be selected. When this parameter is used together with &#x60;taskId&#x60;, this call is ignored and task details are not excluded. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param initial Restrict to historic variable updates that contain only initial variable values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param occurredBefore Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param occurredAfter Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return List&lt;HistoricDetailDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricDetailDto> getHistoricDetails(String processInstanceId, String processInstanceIdIn, String executionId, String taskId, String activityInstanceId, String caseInstanceId, String caseExecutionId, String variableInstanceId, String variableTypeIn, String tenantIdIn, Boolean withoutTenantId, String userOperationId, Boolean formFields, Boolean variableUpdates, Boolean excludeTaskDetails, Boolean initial, OffsetDateTime occurredBefore, OffsetDateTime occurredAfter, String sortBy, String sortOrder, Integer firstResult, Integer maxResults, Boolean deserializeValues) throws RestClientException {
        return getHistoricDetailsWithHttpInfo(processInstanceId, processInstanceIdIn, executionId, taskId, activityInstanceId, caseInstanceId, caseExecutionId, variableInstanceId, variableTypeIn, tenantIdIn, withoutTenantId, userOperationId, formFields, variableUpdates, excludeTaskDetails, initial, occurredBefore, occurredAfter, sortBy, sortOrder, firstResult, maxResults, deserializeValues).getBody();
    }

    /**
     * Get Historic Details
     * Queries for historic details that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Detail Count](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIdIn Only include historic details which belong to one of the passed comma-separated process instance ids. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param taskId Filter by task id. (optional)
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param variableInstanceId Filter by variable instance id. (optional)
     * @param variableTypeIn Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#x60;serializable&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic details that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param userOperationId Filter by a user operation id. (optional)
     * @param formFields Only include &#x60;HistoricFormFields&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param variableUpdates Only include &#x60;HistoricVariableUpdates&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param excludeTaskDetails Excludes all task-related &#x60;HistoricDetails&#x60;, so only items which have no task id set will be selected. When this parameter is used together with &#x60;taskId&#x60;, this call is ignored and task details are not excluded. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param initial Restrict to historic variable updates that contain only initial variable values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param occurredBefore Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param occurredAfter Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricDetailDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricDetailDto>> getHistoricDetailsWithHttpInfo(String processInstanceId, String processInstanceIdIn, String executionId, String taskId, String activityInstanceId, String caseInstanceId, String caseExecutionId, String variableInstanceId, String variableTypeIn, String tenantIdIn, Boolean withoutTenantId, String userOperationId, Boolean formFields, Boolean variableUpdates, Boolean excludeTaskDetails, Boolean initial, OffsetDateTime occurredBefore, OffsetDateTime occurredAfter, String sortBy, String sortOrder, Integer firstResult, Integer maxResults, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/detail", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceId", activityInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableInstanceId", variableInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableTypeIn", variableTypeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userOperationId", userOperationId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "formFields", formFields));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableUpdates", variableUpdates));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "excludeTaskDetails", excludeTaskDetails));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "initial", initial));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "occurredBefore", occurredBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "occurredAfter", occurredAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deserializeValues", deserializeValues));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricDetailDto>> returnType = new ParameterizedTypeReference<List<HistoricDetailDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Detail Count
     * Queries for the number of historic details that fulfill the given parameters. Takes the same parameters as the [Get Historic Details](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIdIn Only include historic details which belong to one of the passed comma-separated process instance ids. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param taskId Filter by task id. (optional)
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param variableInstanceId Filter by variable instance id. (optional)
     * @param variableTypeIn Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#x60;serializable&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic details that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param userOperationId Filter by a user operation id. (optional)
     * @param formFields Only include &#x60;HistoricFormFields&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param variableUpdates Only include &#x60;HistoricVariableUpdates&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param excludeTaskDetails Excludes all task-related &#x60;HistoricDetails&#x60;, so only items which have no task id set will be selected. When this parameter is used together with &#x60;taskId&#x60;, this call is ignored and task details are not excluded. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param initial Restrict to historic variable updates that contain only initial variable values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param occurredBefore Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param occurredAfter Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricDetailsCount(String processInstanceId, String processInstanceIdIn, String executionId, String taskId, String activityInstanceId, String caseInstanceId, String caseExecutionId, String variableInstanceId, String variableTypeIn, String tenantIdIn, Boolean withoutTenantId, String userOperationId, Boolean formFields, Boolean variableUpdates, Boolean excludeTaskDetails, Boolean initial, OffsetDateTime occurredBefore, OffsetDateTime occurredAfter) throws RestClientException {
        return getHistoricDetailsCountWithHttpInfo(processInstanceId, processInstanceIdIn, executionId, taskId, activityInstanceId, caseInstanceId, caseExecutionId, variableInstanceId, variableTypeIn, tenantIdIn, withoutTenantId, userOperationId, formFields, variableUpdates, excludeTaskDetails, initial, occurredBefore, occurredAfter).getBody();
    }

    /**
     * Get Historic Detail Count
     * Queries for the number of historic details that fulfill the given parameters. Takes the same parameters as the [Get Historic Details](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid.
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIdIn Only include historic details which belong to one of the passed comma-separated process instance ids. (optional)
     * @param executionId Filter by execution id. (optional)
     * @param taskId Filter by task id. (optional)
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by case execution id. (optional)
     * @param variableInstanceId Filter by variable instance id. (optional)
     * @param variableTypeIn Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#x60;serializable&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic details that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param userOperationId Filter by a user operation id. (optional)
     * @param formFields Only include &#x60;HistoricFormFields&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param variableUpdates Only include &#x60;HistoricVariableUpdates&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param excludeTaskDetails Excludes all task-related &#x60;HistoricDetails&#x60;, so only items which have no task id set will be selected. When this parameter is used together with &#x60;taskId&#x60;, this call is ignored and task details are not excluded. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param initial Restrict to historic variable updates that contain only initial variable values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param occurredBefore Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @param occurredAfter Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricDetailsCountWithHttpInfo(String processInstanceId, String processInstanceIdIn, String executionId, String taskId, String activityInstanceId, String caseInstanceId, String caseExecutionId, String variableInstanceId, String variableTypeIn, String tenantIdIn, Boolean withoutTenantId, String userOperationId, Boolean formFields, Boolean variableUpdates, Boolean excludeTaskDetails, Boolean initial, OffsetDateTime occurredBefore, OffsetDateTime occurredAfter) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/detail/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceId", activityInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableInstanceId", variableInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableTypeIn", variableTypeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userOperationId", userOperationId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "formFields", formFields));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableUpdates", variableUpdates));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "excludeTaskDetails", excludeTaskDetails));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "initial", initial));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "occurredBefore", occurredBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "occurredAfter", occurredAfter));

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
     * Get Historic Detail
     * Retrieves a historic detail by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the detail. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return HistoricDetailDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricDetailDto historicDetail(String id, Boolean deserializeValue) throws RestClientException {
        return historicDetailWithHttpInfo(id, deserializeValue).getBody();
    }

    /**
     * Get Historic Detail
     * Retrieves a historic detail by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the detail. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;HistoricDetailDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricDetailDto> historicDetailWithHttpInfo(String id, Boolean deserializeValue) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling historicDetail");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/detail/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deserializeValue", deserializeValue));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<HistoricDetailDto> returnType = new ParameterizedTypeReference<HistoricDetailDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Detail (Binary)
     * Retrieves the content of a historic variable update by id. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Detail with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Detail with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic variable update. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File historicDetailBinary(String id) throws RestClientException {
        return historicDetailBinaryWithHttpInfo(id).getBody();
    }

    /**
     * Get Historic Detail (Binary)
     * Retrieves the content of a historic variable update by id. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Detail with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Detail with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic variable update. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> historicDetailBinaryWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling historicDetailBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/detail/{id}/data", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream", "*/*", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Details (POST)
     * Queries for historic details that fulfill the given parameters. This method is slightly more powerful than the [Get Historic Details](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query/) method because it allows sorting by multiple parameters. The size of the result set can be retrieved by using the [Get Historic Detail Count](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @param historicDetailQueryDto  (optional)
     * @return List&lt;HistoricDetailDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricDetailDto> queryHistoricDetails(Integer firstResult, Integer maxResults, Boolean deserializeValues, HistoricDetailQueryDto historicDetailQueryDto) throws RestClientException {
        return queryHistoricDetailsWithHttpInfo(firstResult, maxResults, deserializeValues, historicDetailQueryDto).getBody();
    }

    /**
     * Get Historic Details (POST)
     * Queries for historic details that fulfill the given parameters. This method is slightly more powerful than the [Get Historic Details](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query/) method because it allows sorting by multiple parameters. The size of the result set can be retrieved by using the [Get Historic Detail Count](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @param historicDetailQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricDetailDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricDetailDto>> queryHistoricDetailsWithHttpInfo(Integer firstResult, Integer maxResults, Boolean deserializeValues, HistoricDetailQueryDto historicDetailQueryDto) throws RestClientException {
        Object postBody = historicDetailQueryDto;
        
        String path = apiClient.expandPath("/history/detail", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deserializeValues", deserializeValues));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricDetailDto>> returnType = new ParameterizedTypeReference<List<HistoricDetailDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
