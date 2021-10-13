package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricIncidentDto;
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
@Component("com.camunda.consulting.openapi.client.handler.HistoricIncidentApi")
public class HistoricIncidentApi {
    private ApiClient apiClient;

    public HistoricIncidentApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricIncidentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Incidents
     * Queries for historic incidents that fulfill given parameters. The size of the result set can be retrieved by using the [Get Incident Count](https://docs.camunda.org/manual/7.16/reference/rest/history/incident/get-incident-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).  (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restricts to incidents that have the given processDefinitionKey. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that have one of the given process definition keys. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param createTimeBefore Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param createTimeAfter Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeBefore Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeAfter Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param historyConfiguration Restricts to incidents that have the given parameter set as history configuration. (optional)
     * @param open Restricts to incidents that are open. (optional)
     * @param resolved Restricts to incidents that are resolved. (optional)
     * @param deleted Restricts to incidents that are deleted. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic incidents that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return List&lt;HistoricIncidentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricIncidentDto> getHistoricIncidents(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime createTimeBefore, OffsetDateTime createTimeAfter, OffsetDateTime endTimeBefore, OffsetDateTime endTimeAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String historyConfiguration, Boolean open, Boolean resolved, Boolean deleted, String tenantIdIn, Boolean withoutTenantId, String jobDefinitionIdIn, String sortBy, String sortOrder) throws RestClientException {
        return getHistoricIncidentsWithHttpInfo(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processInstanceId, executionId, createTimeBefore, createTimeAfter, endTimeBefore, endTimeAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, historyConfiguration, open, resolved, deleted, tenantIdIn, withoutTenantId, jobDefinitionIdIn, sortBy, sortOrder).getBody();
    }

    /**
     * Get Incidents
     * Queries for historic incidents that fulfill given parameters. The size of the result set can be retrieved by using the [Get Incident Count](https://docs.camunda.org/manual/7.16/reference/rest/history/incident/get-incident-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).  (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restricts to incidents that have the given processDefinitionKey. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that have one of the given process definition keys. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param createTimeBefore Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param createTimeAfter Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeBefore Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeAfter Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param historyConfiguration Restricts to incidents that have the given parameter set as history configuration. (optional)
     * @param open Restricts to incidents that are open. (optional)
     * @param resolved Restricts to incidents that are resolved. (optional)
     * @param deleted Restricts to incidents that are deleted. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic incidents that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricIncidentDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricIncidentDto>> getHistoricIncidentsWithHttpInfo(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime createTimeBefore, OffsetDateTime createTimeAfter, OffsetDateTime endTimeBefore, OffsetDateTime endTimeAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String historyConfiguration, Boolean open, Boolean resolved, Boolean deleted, String tenantIdIn, Boolean withoutTenantId, String jobDefinitionIdIn, String sortBy, String sortOrder) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/incident", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createTimeBefore", createTimeBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createTimeAfter", createTimeAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "endTimeBefore", endTimeBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "endTimeAfter", endTimeAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityId", failedActivityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "causeIncidentId", causeIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootCauseIncidentId", rootCauseIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "configuration", _configuration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "historyConfiguration", historyConfiguration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "open", open));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resolved", resolved));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deleted", deleted));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionIdIn", jobDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricIncidentDto>> returnType = new ParameterizedTypeReference<List<HistoricIncidentDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Incident Count
     * Queries for the number of historic incidents that fulfill the given parameters. Takes the same parameters as the [Get Incidents](https://docs.camunda.org/manual/7.16/reference/rest/history/incident/get-incident-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).  (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restricts to incidents that have the given processDefinitionKey. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that have one of the given process definition keys. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param createTimeBefore Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param createTimeAfter Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeBefore Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeAfter Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param historyConfiguration Restricts to incidents that have the given parameter set as history configuration. (optional)
     * @param open Restricts to incidents that are open. (optional)
     * @param resolved Restricts to incidents that are resolved. (optional)
     * @param deleted Restricts to incidents that are deleted. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic incidents that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricIncidentsCount(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime createTimeBefore, OffsetDateTime createTimeAfter, OffsetDateTime endTimeBefore, OffsetDateTime endTimeAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String historyConfiguration, Boolean open, Boolean resolved, Boolean deleted, String tenantIdIn, Boolean withoutTenantId, String jobDefinitionIdIn, String sortBy, String sortOrder) throws RestClientException {
        return getHistoricIncidentsCountWithHttpInfo(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processInstanceId, executionId, createTimeBefore, createTimeAfter, endTimeBefore, endTimeAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, historyConfiguration, open, resolved, deleted, tenantIdIn, withoutTenantId, jobDefinitionIdIn, sortBy, sortOrder).getBody();
    }

    /**
     * Get Incident Count
     * Queries for the number of historic incidents that fulfill the given parameters. Takes the same parameters as the [Get Incidents](https://docs.camunda.org/manual/7.16/reference/rest/history/incident/get-incident-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).  (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restricts to incidents that have the given processDefinitionKey. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that have one of the given process definition keys. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param createTimeBefore Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param createTimeAfter Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeBefore Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param endTimeAfter Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param historyConfiguration Restricts to incidents that have the given parameter set as history configuration. (optional)
     * @param open Restricts to incidents that are open. (optional)
     * @param resolved Restricts to incidents that are resolved. (optional)
     * @param deleted Restricts to incidents that are deleted. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include historic incidents that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricIncidentsCountWithHttpInfo(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime createTimeBefore, OffsetDateTime createTimeAfter, OffsetDateTime endTimeBefore, OffsetDateTime endTimeAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String historyConfiguration, Boolean open, Boolean resolved, Boolean deleted, String tenantIdIn, Boolean withoutTenantId, String jobDefinitionIdIn, String sortBy, String sortOrder) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/incident/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createTimeBefore", createTimeBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createTimeAfter", createTimeAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "endTimeBefore", endTimeBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "endTimeAfter", endTimeAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityId", failedActivityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "causeIncidentId", causeIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootCauseIncidentId", rootCauseIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "configuration", _configuration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "historyConfiguration", historyConfiguration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "open", open));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resolved", resolved));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deleted", deleted));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionIdIn", jobDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));

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
}
