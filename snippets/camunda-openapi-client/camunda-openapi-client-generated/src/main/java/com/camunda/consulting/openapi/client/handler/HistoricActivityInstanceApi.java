package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricActivityInstanceDto;
import com.camunda.consulting.openapi.client.model.HistoricActivityInstanceQueryDto;
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
@Component("com.camunda.consulting.openapi.client.handler.HistoricActivityInstanceApi")
public class HistoricActivityInstanceApi {
    private ApiClient apiClient;

    public HistoricActivityInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricActivityInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get
     * Retrieves a historic activity instance by id, according to the &#x60;HistoricActivityInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Not Found Historic activity instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic activity instance to be retrieved. (required)
     * @return HistoricActivityInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricActivityInstanceDto getHistoricActivityInstance(String id) throws RestClientException {
        return getHistoricActivityInstanceWithHttpInfo(id).getBody();
    }

    /**
     * Get
     * Retrieves a historic activity instance by id, according to the &#x60;HistoricActivityInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Not Found Historic activity instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic activity instance to be retrieved. (required)
     * @return ResponseEntity&lt;HistoricActivityInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricActivityInstanceDto> getHistoricActivityInstanceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricActivityInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/activity-instance/{id}", uriVariables);

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

        ParameterizedTypeReference<HistoricActivityInstanceDto> returnType = new ParameterizedTypeReference<HistoricActivityInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for historic activity instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Activity Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param executionId Filter by the id of the execution that executed the activity instance. (optional)
     * @param activityId Filter by the activity id (according to BPMN 2.0 XML). (optional)
     * @param activityName Filter by the activity name (according to BPMN 2.0 XML). (optional)
     * @param activityType Filter by activity type. (optional)
     * @param taskAssignee Only include activity instances that are user tasks and assigned to a given user. (optional)
     * @param finished Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param unfinished Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param canceled Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param completeScope Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return List&lt;HistoricActivityInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricActivityInstanceDto> getHistoricActivityInstances(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String activityInstanceId, String processInstanceId, String processDefinitionId, String executionId, String activityId, String activityName, String activityType, String taskAssignee, Boolean finished, Boolean unfinished, Boolean canceled, Boolean completeScope, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        return getHistoricActivityInstancesWithHttpInfo(sortBy, sortOrder, firstResult, maxResults, activityInstanceId, processInstanceId, processDefinitionId, executionId, activityId, activityName, activityType, taskAssignee, finished, unfinished, canceled, completeScope, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId).getBody();
    }

    /**
     * Get List
     * Queries for historic activity instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Activity Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param executionId Filter by the id of the execution that executed the activity instance. (optional)
     * @param activityId Filter by the activity id (according to BPMN 2.0 XML). (optional)
     * @param activityName Filter by the activity name (according to BPMN 2.0 XML). (optional)
     * @param activityType Filter by activity type. (optional)
     * @param taskAssignee Only include activity instances that are user tasks and assigned to a given user. (optional)
     * @param finished Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param unfinished Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param canceled Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param completeScope Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricActivityInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricActivityInstanceDto>> getHistoricActivityInstancesWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String activityInstanceId, String processInstanceId, String processDefinitionId, String executionId, String activityId, String activityName, String activityType, String taskAssignee, Boolean finished, Boolean unfinished, Boolean canceled, Boolean completeScope, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/activity-instance", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceId", activityInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityName", activityName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityType", activityType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskAssignee", taskAssignee));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unfinished", unfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "canceled", canceled));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completeScope", completeScope));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricActivityInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricActivityInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of historic activity instances that fulfill the given parameters. Takes the same parameters as the [Get Historic Activity Instance](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param executionId Filter by the id of the execution that executed the activity instance. (optional)
     * @param activityId Filter by the activity id (according to BPMN 2.0 XML). (optional)
     * @param activityName Filter by the activity name (according to BPMN 2.0 XML). (optional)
     * @param activityType Filter by activity type. (optional)
     * @param taskAssignee Only include activity instances that are user tasks and assigned to a given user. (optional)
     * @param finished Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param unfinished Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param canceled Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param completeScope Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricActivityInstancesCount(String activityInstanceId, String processInstanceId, String processDefinitionId, String executionId, String activityId, String activityName, String activityType, String taskAssignee, Boolean finished, Boolean unfinished, Boolean canceled, Boolean completeScope, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        return getHistoricActivityInstancesCountWithHttpInfo(activityInstanceId, processInstanceId, processDefinitionId, executionId, activityId, activityName, activityType, taskAssignee, finished, unfinished, canceled, completeScope, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of historic activity instances that fulfill the given parameters. Takes the same parameters as the [Get Historic Activity Instance](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param activityInstanceId Filter by activity instance id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param executionId Filter by the id of the execution that executed the activity instance. (optional)
     * @param activityId Filter by the activity id (according to BPMN 2.0 XML). (optional)
     * @param activityName Filter by the activity name (according to BPMN 2.0 XML). (optional)
     * @param activityType Filter by activity type. (optional)
     * @param taskAssignee Only include activity instances that are user tasks and assigned to a given user. (optional)
     * @param finished Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param unfinished Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param canceled Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param completeScope Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricActivityInstancesCountWithHttpInfo(String activityInstanceId, String processInstanceId, String processDefinitionId, String executionId, String activityId, String activityName, String activityType, String taskAssignee, Boolean finished, Boolean unfinished, Boolean canceled, Boolean completeScope, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/activity-instance/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceId", activityInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityName", activityName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityType", activityType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskAssignee", taskAssignee));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unfinished", unfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "canceled", canceled));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completeScope", completeScope));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));

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
     * Get List (POST)
     * Queries for historic activity instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Activity Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicActivityInstanceQueryDto  (optional)
     * @return List&lt;HistoricActivityInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricActivityInstanceDto> queryHistoricActivityInstances(Integer firstResult, Integer maxResults, HistoricActivityInstanceQueryDto historicActivityInstanceQueryDto) throws RestClientException {
        return queryHistoricActivityInstancesWithHttpInfo(firstResult, maxResults, historicActivityInstanceQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for historic activity instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Activity Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicActivityInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricActivityInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricActivityInstanceDto>> queryHistoricActivityInstancesWithHttpInfo(Integer firstResult, Integer maxResults, HistoricActivityInstanceQueryDto historicActivityInstanceQueryDto) throws RestClientException {
        Object postBody = historicActivityInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/activity-instance", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<HistoricActivityInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricActivityInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count (POST)
     * Queries for the number of historic activity instances that fulfill the given parameters.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicActivityInstanceQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryHistoricActivityInstancesCount(HistoricActivityInstanceQueryDto historicActivityInstanceQueryDto) throws RestClientException {
        return queryHistoricActivityInstancesCountWithHttpInfo(historicActivityInstanceQueryDto).getBody();
    }

    /**
     * Get List Count (POST)
     * Queries for the number of historic activity instances that fulfill the given parameters.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicActivityInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryHistoricActivityInstancesCountWithHttpInfo(HistoricActivityInstanceQueryDto historicActivityInstanceQueryDto) throws RestClientException {
        Object postBody = historicActivityInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/activity-instance/count", Collections.<String, Object>emptyMap());

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
