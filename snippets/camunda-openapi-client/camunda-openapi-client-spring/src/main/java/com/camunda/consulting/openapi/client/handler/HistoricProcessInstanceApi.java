package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DeleteHistoricProcessInstancesDto;
import com.camunda.consulting.openapi.client.model.DurationReportResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.HistoricProcessInstanceQueryDto;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.SetRemovalTimeToHistoricProcessInstancesDto;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi")
public class HistoricProcessInstanceApi {
    private ApiClient apiClient;

    public HistoricProcessInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricProcessInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete
     * Deletes a process instance from the history by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Historic process instance with given id does not exist.
     * @param id The id of the historic process instance to be deleted. (required)
     * @param failIfNotExists If set to &#x60;false&#x60;, the request will still be successful if the process id is not found. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteHistoricProcessInstance(String id, Boolean failIfNotExists) throws RestClientException {
        deleteHistoricProcessInstanceWithHttpInfo(id, failIfNotExists);
    }

    /**
     * Delete
     * Deletes a process instance from the history by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Historic process instance with given id does not exist.
     * @param id The id of the historic process instance to be deleted. (required)
     * @param failIfNotExists If set to &#x60;false&#x60;, the request will still be successful if the process id is not found. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteHistoricProcessInstanceWithHttpInfo(String id, Boolean failIfNotExists) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteHistoricProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/process-instance/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failIfNotExists", failIfNotExists));

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
     * Delete Async (POST)
     * Delete multiple historic process instances asynchronously (batch). At least &#x60;historicProcessInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60; has to be provided. If both are provided then all instances matching query criterion and instances from the list will be deleted.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, i.e. neither historicProcessInstanceIds, nor historicProcessInstanceQuery is present. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param deleteHistoricProcessInstancesDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto deleteHistoricProcessInstancesAsync(DeleteHistoricProcessInstancesDto deleteHistoricProcessInstancesDto) throws RestClientException {
        return deleteHistoricProcessInstancesAsyncWithHttpInfo(deleteHistoricProcessInstancesDto).getBody();
    }

    /**
     * Delete Async (POST)
     * Delete multiple historic process instances asynchronously (batch). At least &#x60;historicProcessInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60; has to be provided. If both are provided then all instances matching query criterion and instances from the list will be deleted.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, i.e. neither historicProcessInstanceIds, nor historicProcessInstanceQuery is present. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param deleteHistoricProcessInstancesDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> deleteHistoricProcessInstancesAsyncWithHttpInfo(DeleteHistoricProcessInstancesDto deleteHistoricProcessInstancesDto) throws RestClientException {
        Object postBody = deleteHistoricProcessInstancesDto;
        
        String path = apiClient.expandPath("/history/process-instance/delete", Collections.<String, Object>emptyMap());

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
     * Delete Variable Instances
     * Deletes all variables of a process instance from the history by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Historic process instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#parse-exceptions) for the error response format.
     * @param id The id of the process instance for which all historic variables are to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteHistoricVariableInstancesOfHistoricProcessInstance(String id) throws RestClientException {
        deleteHistoricVariableInstancesOfHistoricProcessInstanceWithHttpInfo(id);
    }

    /**
     * Delete Variable Instances
     * Deletes all variables of a process instance from the history by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Historic process instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#parse-exceptions) for the error response format.
     * @param id The id of the process instance for which all historic variables are to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteHistoricVariableInstancesOfHistoricProcessInstanceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteHistoricVariableInstancesOfHistoricProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/process-instance/{id}/variable-instances", uriVariables);

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
     * Get
     * Retrieves a historic process instance by id, according to the &#x60;HistoricProcessInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Not Found Historic process instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic process instance to be retrieved. (required)
     * @return HistoricProcessInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricProcessInstanceDto getHistoricProcessInstance(String id) throws RestClientException {
        return getHistoricProcessInstanceWithHttpInfo(id).getBody();
    }

    /**
     * Get
     * Retrieves a historic process instance by id, according to the &#x60;HistoricProcessInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Not Found Historic process instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic process instance to be retrieved. (required)
     * @return ResponseEntity&lt;HistoricProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricProcessInstanceDto> getHistoricProcessInstanceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/process-instance/{id}", uriVariables);

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

        ParameterizedTypeReference<HistoricProcessInstanceDto> returnType = new ParameterizedTypeReference<HistoricProcessInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Duration Report
     * Retrieves a report about the duration of completed process instances, grouped by a period. These reports include the maximum, minimum and average duration of all completed process instances which were started in a given period.  **Note:** This only includes historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid or mandatory parameters are not supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - If the authenticated user is unauthorized to read the history. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param reportType **Mandatory.** Specifies the type of the report to retrieve. To retrieve a report about the duration of process instances, the value must be set to &#x60;duration&#x60;. (required)
     * @param periodUnit **Mandatory.** Specifies the granularity of the report. Valid values are &#x60;month&#x60; and &#x60;quarter&#x60;. (required)
     * @param processDefinitionIdIn Filter by process definition ids. Must be a comma-separated list of process definition ids. (optional)
     * @param processDefinitionKeyIn Filter by process definition keys. Must be a comma-separated list of process definition keys. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2016-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2016-01-23T14:42:45.000+0200&#x60;. (optional)
     * @return List&lt;DurationReportResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<DurationReportResultDto> getHistoricProcessInstanceDurationReport(String reportType, String periodUnit, String processDefinitionIdIn, String processDefinitionKeyIn, OffsetDateTime startedBefore, OffsetDateTime startedAfter) throws RestClientException {
        return getHistoricProcessInstanceDurationReportWithHttpInfo(reportType, periodUnit, processDefinitionIdIn, processDefinitionKeyIn, startedBefore, startedAfter).getBody();
    }

    /**
     * Get Duration Report
     * Retrieves a report about the duration of completed process instances, grouped by a period. These reports include the maximum, minimum and average duration of all completed process instances which were started in a given period.  **Note:** This only includes historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid or mandatory parameters are not supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - If the authenticated user is unauthorized to read the history. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param reportType **Mandatory.** Specifies the type of the report to retrieve. To retrieve a report about the duration of process instances, the value must be set to &#x60;duration&#x60;. (required)
     * @param periodUnit **Mandatory.** Specifies the granularity of the report. Valid values are &#x60;month&#x60; and &#x60;quarter&#x60;. (required)
     * @param processDefinitionIdIn Filter by process definition ids. Must be a comma-separated list of process definition ids. (optional)
     * @param processDefinitionKeyIn Filter by process definition keys. Must be a comma-separated list of process definition keys. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2016-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2016-01-23T14:42:45.000+0200&#x60;. (optional)
     * @return ResponseEntity&lt;List&lt;DurationReportResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<DurationReportResultDto>> getHistoricProcessInstanceDurationReportWithHttpInfo(String reportType, String periodUnit, String processDefinitionIdIn, String processDefinitionKeyIn, OffsetDateTime startedBefore, OffsetDateTime startedAfter) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'reportType' is set
        if (reportType == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'reportType' when calling getHistoricProcessInstanceDurationReport");
        }
        
        // verify the required parameter 'periodUnit' is set
        if (periodUnit == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'periodUnit' when calling getHistoricProcessInstanceDurationReport");
        }
        
        String path = apiClient.expandPath("/history/process-instance/report", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "reportType", reportType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "periodUnit", periodUnit));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionIdIn", processDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));

        final String[] localVarAccepts = { 
            "application/json", "application/csv", "text/csv"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<DurationReportResultDto>> returnType = new ParameterizedTypeReference<List<DurationReportResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for historic process instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Process Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIds Filter by process instance ids. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionId Filter by the process definition the instances run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionName Filter by the name of the process definition the instances run on. (optional)
     * @param processDefinitionNameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param processDefinitionKeyNotIn Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional)
     * @param finished Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished process instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withIncidents Only include process instances which have an incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withRootIncidents Only include process instances which have a root incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentStatus Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityAfter Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityBefore Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobAfter Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobBefore Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBy Only include process instances that were started by the given user. (optional)
     * @param superProcessInstanceId Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstanceId Restrict query to one process instance that has a sub process instance with the given id. (optional)
     * @param superCaseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstanceId Restrict query to one process instance that has a sub case instance with the given id. (optional)
     * @param caseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param tenantIdIn Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param withoutTenantId Only include historic process instances which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executedActivityIdIn Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param activeActivityIdIn Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param active Restrict to instances that are active. (optional)
     * @param suspended Restrict to instances that are suspended. (optional)
     * @param completed Restrict to instances that are completed. (optional)
     * @param externallyTerminated Restrict to instances that are externallyTerminated. (optional)
     * @param internallyTerminated Restrict to instances that are internallyTerminated. (optional)
     * @param variables Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;.  Key and value may not contain underscore or comma characters.  (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in variables case-insensitively. If set to &#x60;true&#x60; variableName and variablename are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in variables case-insensitively. If set to &#x60;true&#x60; variableValue and variablevalue are treated as equal. (optional)
     * @return List&lt;HistoricProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricProcessInstanceDto> getHistoricProcessInstances(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String processInstanceId, String processInstanceIds, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String processDefinitionKeyNotIn, String processInstanceBusinessKey, String processInstanceBusinessKeyLike, Boolean rootProcessInstances, Boolean finished, Boolean unfinished, Boolean withIncidents, Boolean withRootIncidents, String incidentType, String incidentStatus, String incidentMessage, String incidentMessageLike, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, OffsetDateTime executedActivityAfter, OffsetDateTime executedActivityBefore, OffsetDateTime executedJobAfter, OffsetDateTime executedJobBefore, String startedBy, String superProcessInstanceId, String subProcessInstanceId, String superCaseInstanceId, String subCaseInstanceId, String caseInstanceId, String tenantIdIn, Boolean withoutTenantId, String executedActivityIdIn, String activeActivityIdIn, Boolean active, Boolean suspended, Boolean completed, Boolean externallyTerminated, Boolean internallyTerminated, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        return getHistoricProcessInstancesWithHttpInfo(sortBy, sortOrder, firstResult, maxResults, processInstanceId, processInstanceIds, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, processDefinitionKeyNotIn, processInstanceBusinessKey, processInstanceBusinessKeyLike, rootProcessInstances, finished, unfinished, withIncidents, withRootIncidents, incidentType, incidentStatus, incidentMessage, incidentMessageLike, startedBefore, startedAfter, finishedBefore, finishedAfter, executedActivityAfter, executedActivityBefore, executedJobAfter, executedJobBefore, startedBy, superProcessInstanceId, subProcessInstanceId, superCaseInstanceId, subCaseInstanceId, caseInstanceId, tenantIdIn, withoutTenantId, executedActivityIdIn, activeActivityIdIn, active, suspended, completed, externallyTerminated, internallyTerminated, variables, variableNamesIgnoreCase, variableValuesIgnoreCase).getBody();
    }

    /**
     * Get List
     * Queries for historic process instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Process Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIds Filter by process instance ids. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionId Filter by the process definition the instances run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionName Filter by the name of the process definition the instances run on. (optional)
     * @param processDefinitionNameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param processDefinitionKeyNotIn Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional)
     * @param finished Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished process instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withIncidents Only include process instances which have an incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withRootIncidents Only include process instances which have a root incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentStatus Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityAfter Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityBefore Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobAfter Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobBefore Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBy Only include process instances that were started by the given user. (optional)
     * @param superProcessInstanceId Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstanceId Restrict query to one process instance that has a sub process instance with the given id. (optional)
     * @param superCaseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstanceId Restrict query to one process instance that has a sub case instance with the given id. (optional)
     * @param caseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param tenantIdIn Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param withoutTenantId Only include historic process instances which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executedActivityIdIn Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param activeActivityIdIn Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param active Restrict to instances that are active. (optional)
     * @param suspended Restrict to instances that are suspended. (optional)
     * @param completed Restrict to instances that are completed. (optional)
     * @param externallyTerminated Restrict to instances that are externallyTerminated. (optional)
     * @param internallyTerminated Restrict to instances that are internallyTerminated. (optional)
     * @param variables Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;.  Key and value may not contain underscore or comma characters.  (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in variables case-insensitively. If set to &#x60;true&#x60; variableName and variablename are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in variables case-insensitively. If set to &#x60;true&#x60; variableValue and variablevalue are treated as equal. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricProcessInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricProcessInstanceDto>> getHistoricProcessInstancesWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String processInstanceId, String processInstanceIds, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String processDefinitionKeyNotIn, String processInstanceBusinessKey, String processInstanceBusinessKeyLike, Boolean rootProcessInstances, Boolean finished, Boolean unfinished, Boolean withIncidents, Boolean withRootIncidents, String incidentType, String incidentStatus, String incidentMessage, String incidentMessageLike, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, OffsetDateTime executedActivityAfter, OffsetDateTime executedActivityBefore, OffsetDateTime executedJobAfter, OffsetDateTime executedJobBefore, String startedBy, String superProcessInstanceId, String subProcessInstanceId, String superCaseInstanceId, String subCaseInstanceId, String caseInstanceId, String tenantIdIn, Boolean withoutTenantId, String executedActivityIdIn, String activeActivityIdIn, Boolean active, Boolean suspended, Boolean completed, Boolean externallyTerminated, Boolean internallyTerminated, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/process-instance", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIds", processInstanceIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionName", processDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionNameLike", processDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyNotIn", processDefinitionKeyNotIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKey", processInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLike", processInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootProcessInstances", rootProcessInstances));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unfinished", unfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withIncidents", withIncidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRootIncidents", withRootIncidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentStatus", incidentStatus));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedActivityAfter", executedActivityAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedActivityBefore", executedActivityBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedJobAfter", executedJobAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedJobBefore", executedJobBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBy", startedBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superProcessInstanceId", superProcessInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subProcessInstanceId", subProcessInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superCaseInstanceId", superCaseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subCaseInstanceId", subCaseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedActivityIdIn", executedActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activeActivityIdIn", activeActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completed", completed));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externallyTerminated", externallyTerminated));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "internallyTerminated", internallyTerminated));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variables", variables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricProcessInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricProcessInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of historic process instances that fulfill the given parameters. Takes the same parameters as the [Get Process Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIds Filter by process instance ids. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionId Filter by the process definition the instances run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionName Filter by the name of the process definition the instances run on. (optional)
     * @param processDefinitionNameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param processDefinitionKeyNotIn Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional)
     * @param finished Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished process instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withIncidents Only include process instances which have an incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withRootIncidents Only include process instances which have a root incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentStatus Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityAfter Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityBefore Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobAfter Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobBefore Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBy Only include process instances that were started by the given user. (optional)
     * @param superProcessInstanceId Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstanceId Restrict query to one process instance that has a sub process instance with the given id. (optional)
     * @param superCaseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstanceId Restrict query to one process instance that has a sub case instance with the given id. (optional)
     * @param caseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param tenantIdIn Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param withoutTenantId Only include historic process instances which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executedActivityIdIn Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param activeActivityIdIn Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param active Restrict to instances that are active. (optional)
     * @param suspended Restrict to instances that are suspended. (optional)
     * @param completed Restrict to instances that are completed. (optional)
     * @param externallyTerminated Restrict to instances that are externallyTerminated. (optional)
     * @param internallyTerminated Restrict to instances that are internallyTerminated. (optional)
     * @param variables Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;.  Key and value may not contain underscore or comma characters.  (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in variables case-insensitively. If set to &#x60;true&#x60; variableName and variablename are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in variables case-insensitively. If set to &#x60;true&#x60; variableValue and variablevalue are treated as equal. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricProcessInstancesCount(String processInstanceId, String processInstanceIds, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String processDefinitionKeyNotIn, String processInstanceBusinessKey, String processInstanceBusinessKeyLike, Boolean rootProcessInstances, Boolean finished, Boolean unfinished, Boolean withIncidents, Boolean withRootIncidents, String incidentType, String incidentStatus, String incidentMessage, String incidentMessageLike, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, OffsetDateTime executedActivityAfter, OffsetDateTime executedActivityBefore, OffsetDateTime executedJobAfter, OffsetDateTime executedJobBefore, String startedBy, String superProcessInstanceId, String subProcessInstanceId, String superCaseInstanceId, String subCaseInstanceId, String caseInstanceId, String tenantIdIn, Boolean withoutTenantId, String executedActivityIdIn, String activeActivityIdIn, Boolean active, Boolean suspended, Boolean completed, Boolean externallyTerminated, Boolean internallyTerminated, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        return getHistoricProcessInstancesCountWithHttpInfo(processInstanceId, processInstanceIds, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, processDefinitionKeyNotIn, processInstanceBusinessKey, processInstanceBusinessKeyLike, rootProcessInstances, finished, unfinished, withIncidents, withRootIncidents, incidentType, incidentStatus, incidentMessage, incidentMessageLike, startedBefore, startedAfter, finishedBefore, finishedAfter, executedActivityAfter, executedActivityBefore, executedJobAfter, executedJobBefore, startedBy, superProcessInstanceId, subProcessInstanceId, superCaseInstanceId, subCaseInstanceId, caseInstanceId, tenantIdIn, withoutTenantId, executedActivityIdIn, activeActivityIdIn, active, suspended, completed, externallyTerminated, internallyTerminated, variables, variableNamesIgnoreCase, variableValuesIgnoreCase).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of historic process instances that fulfill the given parameters. Takes the same parameters as the [Get Process Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceIds Filter by process instance ids. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionId Filter by the process definition the instances run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processDefinitionName Filter by the name of the process definition the instances run on. (optional)
     * @param processDefinitionNameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param processDefinitionKeyNotIn Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of &#x60;Strings&#x60;. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional)
     * @param finished Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished process instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withIncidents Only include process instances which have an incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withRootIncidents Only include process instances which have a root incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentStatus Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param startedBefore Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityAfter Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedActivityBefore Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobAfter Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param executedJobBefore Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBy Only include process instances that were started by the given user. (optional)
     * @param superProcessInstanceId Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstanceId Restrict query to one process instance that has a sub process instance with the given id. (optional)
     * @param superCaseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstanceId Restrict query to one process instance that has a sub case instance with the given id. (optional)
     * @param caseInstanceId Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param tenantIdIn Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param withoutTenantId Only include historic process instances which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executedActivityIdIn Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param activeActivityIdIn Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; (optional)
     * @param active Restrict to instances that are active. (optional)
     * @param suspended Restrict to instances that are suspended. (optional)
     * @param completed Restrict to instances that are completed. (optional)
     * @param externallyTerminated Restrict to instances that are externallyTerminated. (optional)
     * @param internallyTerminated Restrict to instances that are internallyTerminated. (optional)
     * @param variables Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;.  Key and value may not contain underscore or comma characters.  (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in variables case-insensitively. If set to &#x60;true&#x60; variableName and variablename are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in variables case-insensitively. If set to &#x60;true&#x60; variableValue and variablevalue are treated as equal. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricProcessInstancesCountWithHttpInfo(String processInstanceId, String processInstanceIds, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String processDefinitionKeyNotIn, String processInstanceBusinessKey, String processInstanceBusinessKeyLike, Boolean rootProcessInstances, Boolean finished, Boolean unfinished, Boolean withIncidents, Boolean withRootIncidents, String incidentType, String incidentStatus, String incidentMessage, String incidentMessageLike, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, OffsetDateTime executedActivityAfter, OffsetDateTime executedActivityBefore, OffsetDateTime executedJobAfter, OffsetDateTime executedJobBefore, String startedBy, String superProcessInstanceId, String subProcessInstanceId, String superCaseInstanceId, String subCaseInstanceId, String caseInstanceId, String tenantIdIn, Boolean withoutTenantId, String executedActivityIdIn, String activeActivityIdIn, Boolean active, Boolean suspended, Boolean completed, Boolean externallyTerminated, Boolean internallyTerminated, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/process-instance/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIds", processInstanceIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionName", processDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionNameLike", processDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyNotIn", processDefinitionKeyNotIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKey", processInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLike", processInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootProcessInstances", rootProcessInstances));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unfinished", unfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withIncidents", withIncidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRootIncidents", withRootIncidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentStatus", incidentStatus));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedActivityAfter", executedActivityAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedActivityBefore", executedActivityBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedJobAfter", executedJobAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedJobBefore", executedJobBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBy", startedBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superProcessInstanceId", superProcessInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subProcessInstanceId", subProcessInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superCaseInstanceId", superCaseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subCaseInstanceId", subCaseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executedActivityIdIn", executedActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activeActivityIdIn", activeActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completed", completed));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "externallyTerminated", externallyTerminated));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "internallyTerminated", internallyTerminated));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variables", variables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));

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
     * Queries for historic process instances that fulfill the given parameters. This method is slightly more powerful than the [Get Process Instance](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) because it allows filtering by multiple process variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicProcessInstanceQueryDto  (optional)
     * @return List&lt;HistoricProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricProcessInstanceDto> queryHistoricProcessInstances(Integer firstResult, Integer maxResults, HistoricProcessInstanceQueryDto historicProcessInstanceQueryDto) throws RestClientException {
        return queryHistoricProcessInstancesWithHttpInfo(firstResult, maxResults, historicProcessInstanceQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for historic process instances that fulfill the given parameters. This method is slightly more powerful than the [Get Process Instance](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) because it allows filtering by multiple process variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicProcessInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricProcessInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricProcessInstanceDto>> queryHistoricProcessInstancesWithHttpInfo(Integer firstResult, Integer maxResults, HistoricProcessInstanceQueryDto historicProcessInstanceQueryDto) throws RestClientException {
        Object postBody = historicProcessInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/process-instance", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<HistoricProcessInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricProcessInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count (POST)
     * Queries for the number of historic process instances that fulfill the given parameters. This method takes the same message body as the [Get Process Instances (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) method and therefore it is slightly more powerful than the [Get Process Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/post-process-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicProcessInstanceQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryHistoricProcessInstancesCount(HistoricProcessInstanceQueryDto historicProcessInstanceQueryDto) throws RestClientException {
        return queryHistoricProcessInstancesCountWithHttpInfo(historicProcessInstanceQueryDto).getBody();
    }

    /**
     * Get List Count (POST)
     * Queries for the number of historic process instances that fulfill the given parameters. This method takes the same message body as the [Get Process Instances (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) method and therefore it is slightly more powerful than the [Get Process Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/post-process-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicProcessInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryHistoricProcessInstancesCountWithHttpInfo(HistoricProcessInstanceQueryDto historicProcessInstanceQueryDto) throws RestClientException {
        Object postBody = historicProcessInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/process-instance/count", Collections.<String, Object>emptyMap());

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
     * Set Removal Time Async (POST)
     * Sets the removal time to multiple historic process instances asynchronously (batch).  At least &#x60;historicProcessInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60; has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Request was unsuccessfull due to a bad user request. This occurs if some of the query parameters are invalid, e. g. if neither &#x60;historicProcessInstances&#x60; nor &#x60;historicProcessInstanceQuery&#x60; is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRemovalTimeToHistoricProcessInstancesDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setRemovalTimeAsync(SetRemovalTimeToHistoricProcessInstancesDto setRemovalTimeToHistoricProcessInstancesDto) throws RestClientException {
        return setRemovalTimeAsyncWithHttpInfo(setRemovalTimeToHistoricProcessInstancesDto).getBody();
    }

    /**
     * Set Removal Time Async (POST)
     * Sets the removal time to multiple historic process instances asynchronously (batch).  At least &#x60;historicProcessInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60; has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Request was unsuccessfull due to a bad user request. This occurs if some of the query parameters are invalid, e. g. if neither &#x60;historicProcessInstances&#x60; nor &#x60;historicProcessInstanceQuery&#x60; is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRemovalTimeToHistoricProcessInstancesDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setRemovalTimeAsyncWithHttpInfo(SetRemovalTimeToHistoricProcessInstancesDto setRemovalTimeToHistoricProcessInstancesDto) throws RestClientException {
        Object postBody = setRemovalTimeToHistoricProcessInstancesDto;
        
        String path = apiClient.expandPath("/history/process-instance/set-removal-time", Collections.<String, Object>emptyMap());

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
}
