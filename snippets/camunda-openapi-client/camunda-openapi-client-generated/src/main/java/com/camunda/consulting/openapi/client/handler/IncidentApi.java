package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AnnotationDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.IncidentDto;
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
@Component("com.camunda.consulting.openapi.client.handler.IncidentApi")
public class IncidentApi {
    private ApiClient apiClient;

    public IncidentApi() {
        this(new ApiClient());
    }

    @Autowired
    public IncidentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Clear Incident Annotation
     * Clears the annotation of an incident with given id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if no incident can be found for the given id.
     * @param id The id of the incident to clear the annotation at. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void clearIncidentAnnotation(String id) throws RestClientException {
        clearIncidentAnnotationWithHttpInfo(id);
    }

    /**
     * Clear Incident Annotation
     * Clears the annotation of an incident with given id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if no incident can be found for the given id.
     * @param id The id of the incident to clear the annotation at. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> clearIncidentAnnotationWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling clearIncidentAnnotation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/incident/{id}/annotation", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Incident
     * Retrieves an incident by ID.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Returned if an incident with given id does not exist.
     * @param id The id of the incident to be retrieved. (required)
     * @return IncidentDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public IncidentDto getIncident(String id) throws RestClientException {
        return getIncidentWithHttpInfo(id).getBody();
    }

    /**
     * Get Incident
     * Retrieves an incident by ID.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Returned if an incident with given id does not exist.
     * @param id The id of the incident to be retrieved. (required)
     * @return ResponseEntity&lt;IncidentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<IncidentDto> getIncidentWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getIncident");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/incident/{id}", uriVariables);

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

        ParameterizedTypeReference<IncidentDto> returnType = new ParameterizedTypeReference<IncidentDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for incidents that fulfill given parameters. The size of the result set can be retrieved by using the [Get Incident Count](https://docs.camunda.org/manual/7.16/reference/rest/incident/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (&#x60;string%&#x60;), ends with (&#x60;%string&#x60;) or contains (&#x60;%string%&#x60;). (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that belong to a process definition with the given keys. Must be a comma-separated list. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param incidentTimestampBefore Restricts to incidents that have an incidentTimestamp date before the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param incidentTimestampAfter Restricts to incidents that have an incidentTimestamp date after the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;IncidentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<IncidentDto> getIncidents(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime incidentTimestampBefore, OffsetDateTime incidentTimestampAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String tenantIdIn, String jobDefinitionIdIn, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getIncidentsWithHttpInfo(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKeyIn, processInstanceId, executionId, incidentTimestampBefore, incidentTimestampAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, tenantIdIn, jobDefinitionIdIn, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for incidents that fulfill given parameters. The size of the result set can be retrieved by using the [Get Incident Count](https://docs.camunda.org/manual/7.16/reference/rest/incident/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (&#x60;string%&#x60;), ends with (&#x60;%string&#x60;) or contains (&#x60;%string%&#x60;). (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that belong to a process definition with the given keys. Must be a comma-separated list. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param incidentTimestampBefore Restricts to incidents that have an incidentTimestamp date before the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param incidentTimestampAfter Restricts to incidents that have an incidentTimestamp date after the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;IncidentDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<IncidentDto>> getIncidentsWithHttpInfo(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime incidentTimestampBefore, OffsetDateTime incidentTimestampAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String tenantIdIn, String jobDefinitionIdIn, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/incident", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentTimestampBefore", incidentTimestampBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentTimestampAfter", incidentTimestampAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityId", failedActivityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "causeIncidentId", causeIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootCauseIncidentId", rootCauseIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "configuration", _configuration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionIdIn", jobDefinitionIdIn));
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

        ParameterizedTypeReference<List<IncidentDto>> returnType = new ParameterizedTypeReference<List<IncidentDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of incidents that fulfill given parameters. Takes the same parameters as the [Get Incidents](https://docs.camunda.org/manual/7.16/reference/rest/incident/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (&#x60;string%&#x60;), ends with (&#x60;%string&#x60;) or contains (&#x60;%string%&#x60;). (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that belong to a process definition with the given keys. Must be a comma-separated list. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param incidentTimestampBefore Restricts to incidents that have an incidentTimestamp date before the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param incidentTimestampAfter Restricts to incidents that have an incidentTimestamp date after the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getIncidentsCount(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime incidentTimestampBefore, OffsetDateTime incidentTimestampAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String tenantIdIn, String jobDefinitionIdIn) throws RestClientException {
        return getIncidentsCountWithHttpInfo(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKeyIn, processInstanceId, executionId, incidentTimestampBefore, incidentTimestampAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, tenantIdIn, jobDefinitionIdIn).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of incidents that fulfill given parameters. Takes the same parameters as the [Get Incidents](https://docs.camunda.org/manual/7.16/reference/rest/incident/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param incidentId Restricts to incidents that have the given id. (optional)
     * @param incidentType Restricts to incidents that belong to the given incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Restricts to incidents that have the given incident message. (optional)
     * @param incidentMessageLike Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (&#x60;string%&#x60;), ends with (&#x60;%string&#x60;) or contains (&#x60;%string%&#x60;). (optional)
     * @param processDefinitionId Restricts to incidents that belong to a process definition with the given id. (optional)
     * @param processDefinitionKeyIn Restricts to incidents that belong to a process definition with the given keys. Must be a comma-separated list. (optional)
     * @param processInstanceId Restricts to incidents that belong to a process instance with the given id. (optional)
     * @param executionId Restricts to incidents that belong to an execution with the given id. (optional)
     * @param incidentTimestampBefore Restricts to incidents that have an incidentTimestamp date before the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param incidentTimestampAfter Restricts to incidents that have an incidentTimestamp date after the given date.  By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param activityId Restricts to incidents that belong to an activity with the given id. (optional)
     * @param failedActivityId Restricts to incidents that were created due to the failure of an activity with the given id. (optional)
     * @param causeIncidentId Restricts to incidents that have the given incident id as cause incident. (optional)
     * @param rootCauseIncidentId Restricts to incidents that have the given incident id as root cause incident. (optional)
     * @param _configuration Restricts to incidents that have the given parameter set as configuration. (optional)
     * @param tenantIdIn Restricts to incidents that have one of the given comma-separated tenant ids. (optional)
     * @param jobDefinitionIdIn Restricts to incidents that have one of the given comma-separated job definition ids. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getIncidentsCountWithHttpInfo(String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String processDefinitionId, String processDefinitionKeyIn, String processInstanceId, String executionId, OffsetDateTime incidentTimestampBefore, OffsetDateTime incidentTimestampAfter, String activityId, String failedActivityId, String causeIncidentId, String rootCauseIncidentId, String _configuration, String tenantIdIn, String jobDefinitionIdIn) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/incident/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentTimestampBefore", incidentTimestampBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentTimestampAfter", incidentTimestampAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityId", failedActivityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "causeIncidentId", causeIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootCauseIncidentId", rootCauseIncidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "configuration", _configuration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionIdIn", jobDefinitionIdIn));

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
     * Resolve Incident
     * Resolves an incident with given id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Returned if an incident with given id does not exist.
     * <p><b>400</b> - Returned if an incident is not related to any execution or an incident is of type &#x60;failedJob&#x60; or &#x60;failedExternalTask&#x60;. To resolve such an incident, please refer to the [Incident Types](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) section.
     * @param id The id of the incident to be resolved. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void resolveIncident(String id) throws RestClientException {
        resolveIncidentWithHttpInfo(id);
    }

    /**
     * Resolve Incident
     * Resolves an incident with given id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Returned if an incident with given id does not exist.
     * <p><b>400</b> - Returned if an incident is not related to any execution or an incident is of type &#x60;failedJob&#x60; or &#x60;failedExternalTask&#x60;. To resolve such an incident, please refer to the [Incident Types](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) section.
     * @param id The id of the incident to be resolved. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> resolveIncidentWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling resolveIncident");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/incident/{id}", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Set Incident Annotation
     * Sets the annotation of an incident with given id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if no incident can be found for the given id.
     * @param id The id of the incident to clear the annotation at. (required)
     * @param annotationDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setIncidentAnnotation(String id, AnnotationDto annotationDto) throws RestClientException {
        setIncidentAnnotationWithHttpInfo(id, annotationDto);
    }

    /**
     * Set Incident Annotation
     * Sets the annotation of an incident with given id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if no incident can be found for the given id.
     * @param id The id of the incident to clear the annotation at. (required)
     * @param annotationDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setIncidentAnnotationWithHttpInfo(String id, AnnotationDto annotationDto) throws RestClientException {
        Object postBody = annotationDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setIncidentAnnotation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/incident/{id}/annotation", uriVariables);

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
