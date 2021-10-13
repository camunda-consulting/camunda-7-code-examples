package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CleanableHistoricProcessInstanceReportResultDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricActivityStatisticsDto;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T17:49:51.183809+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.HistoricProcessDefinitionApi")
public class HistoricProcessDefinitionApi {
    private ApiClient apiClient;

    public HistoricProcessDefinitionApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricProcessDefinitionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Cleanable Process Instance Report
     * Retrieves a report about a process definition and finished process instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup))  so that you can tune the history time to live. These reports include the count of the finished historic process instances, cleanable process instances and basic process definition data - id, key, name and version. The size of the result set can be retrieved by using the [Get Cleanable Process Instance Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-definition/get-cleanable-process-instance-report-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionIdIn Filter by process definition ids. Must be a comma-separated list of process definition ids. (optional)
     * @param processDefinitionKeyIn Filter by process definition keys. Must be a comma-separated list of process definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param compact Only include process instances which have more than zero finished instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;CleanableHistoricProcessInstanceReportResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CleanableHistoricProcessInstanceReportResultDto> getCleanableHistoricProcessInstanceReport(String processDefinitionIdIn, String processDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getCleanableHistoricProcessInstanceReportWithHttpInfo(processDefinitionIdIn, processDefinitionKeyIn, tenantIdIn, withoutTenantId, compact, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Cleanable Process Instance Report
     * Retrieves a report about a process definition and finished process instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup))  so that you can tune the history time to live. These reports include the count of the finished historic process instances, cleanable process instances and basic process definition data - id, key, name and version. The size of the result set can be retrieved by using the [Get Cleanable Process Instance Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-definition/get-cleanable-process-instance-report-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionIdIn Filter by process definition ids. Must be a comma-separated list of process definition ids. (optional)
     * @param processDefinitionKeyIn Filter by process definition keys. Must be a comma-separated list of process definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param compact Only include process instances which have more than zero finished instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;CleanableHistoricProcessInstanceReportResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CleanableHistoricProcessInstanceReportResultDto>> getCleanableHistoricProcessInstanceReportWithHttpInfo(String processDefinitionIdIn, String processDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/process-definition/cleanable-process-instance-report", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionIdIn", processDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "compact", compact));
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

        ParameterizedTypeReference<List<CleanableHistoricProcessInstanceReportResultDto>> returnType = new ParameterizedTypeReference<List<CleanableHistoricProcessInstanceReportResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Cleanable Process Instance Report Count
     * Queries for the number of report results about a process definition and finished process instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)). Takes the same parameters as the [Get Cleanable Process Instance Report](https://docs.camunda.org/manual/7.16/reference/rest/history/process-definition/get-cleanable-process-instance-report/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionIdIn Filter by process definition ids. Must be a comma-separated list of process definition ids. (optional)
     * @param processDefinitionKeyIn Filter by process definition keys. Must be a comma-separated list of process definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param compact Only include process instances which have more than zero finished instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getCleanableHistoricProcessInstanceReportCount(String processDefinitionIdIn, String processDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact) throws RestClientException {
        return getCleanableHistoricProcessInstanceReportCountWithHttpInfo(processDefinitionIdIn, processDefinitionKeyIn, tenantIdIn, withoutTenantId, compact).getBody();
    }

    /**
     * Get Cleanable Process Instance Report Count
     * Queries for the number of report results about a process definition and finished process instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)). Takes the same parameters as the [Get Cleanable Process Instance Report](https://docs.camunda.org/manual/7.16/reference/rest/history/process-definition/get-cleanable-process-instance-report/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionIdIn Filter by process definition ids. Must be a comma-separated list of process definition ids. (optional)
     * @param processDefinitionKeyIn Filter by process definition keys. Must be a comma-separated list of process definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param compact Only include process instances which have more than zero finished instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getCleanableHistoricProcessInstanceReportCountWithHttpInfo(String processDefinitionIdIn, String processDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/process-definition/cleanable-process-instance-report/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionIdIn", processDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "compact", compact));

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
     * Get Historic Activity Statistics
     * Retrieves historic statistics of a given process definition, grouped by activities. These statistics include the number of running activity instances and, optionally, the number of canceled activity instances, finished activity instances and activity instances which completed a scope (i.e., in BPMN 2.0 manner: a scope is completed by an activity instance when the activity instance consumed a token but did not emit a new token). **Note:** This only includes historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @param canceled Whether to include the number of canceled activity instances in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param finished Whether to include the number of finished activity instances in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param completeScope Whether to include the number of activity instances which completed a scope in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param incidents Whether to include the number of incidents. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param startedBefore Restrict to process instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to process instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to process instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to process instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param processInstanceIdIn Restrict to process instances with the given IDs. The IDs must be provided as a comma- separated list. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return List&lt;HistoricActivityStatisticsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricActivityStatisticsDto> getHistoricActivityStatistics(String id, Boolean canceled, Boolean finished, Boolean completeScope, Boolean incidents, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String processInstanceIdIn, String sortBy, String sortOrder) throws RestClientException {
        return getHistoricActivityStatisticsWithHttpInfo(id, canceled, finished, completeScope, incidents, startedBefore, startedAfter, finishedBefore, finishedAfter, processInstanceIdIn, sortBy, sortOrder).getBody();
    }

    /**
     * Get Historic Activity Statistics
     * Retrieves historic statistics of a given process definition, grouped by activities. These statistics include the number of running activity instances and, optionally, the number of canceled activity instances, finished activity instances and activity instances which completed a scope (i.e., in BPMN 2.0 manner: a scope is completed by an activity instance when the activity instance consumed a token but did not emit a new token). **Note:** This only includes historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @param canceled Whether to include the number of canceled activity instances in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param finished Whether to include the number of finished activity instances in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param completeScope Whether to include the number of activity instances which completed a scope in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param incidents Whether to include the number of incidents. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. (optional)
     * @param startedBefore Restrict to process instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to process instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to process instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to process instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param processInstanceIdIn Restrict to process instances with the given IDs. The IDs must be provided as a comma- separated list. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricActivityStatisticsDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricActivityStatisticsDto>> getHistoricActivityStatisticsWithHttpInfo(String id, Boolean canceled, Boolean finished, Boolean completeScope, Boolean incidents, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String processInstanceIdIn, String sortBy, String sortOrder) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricActivityStatistics");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/process-definition/{id}/statistics", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "canceled", canceled));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completeScope", completeScope));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidents", incidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricActivityStatisticsDto>> returnType = new ParameterizedTypeReference<List<HistoricActivityStatisticsDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
