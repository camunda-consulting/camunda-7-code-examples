package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.CreateIncidentDto;
import com.camunda.consulting.openapi.client.model.EventSubscriptionDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.ExecutionDto;
import com.camunda.consulting.openapi.client.model.ExecutionQueryDto;
import com.camunda.consulting.openapi.client.model.ExecutionTriggerDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.IncidentDto;
import com.camunda.consulting.openapi.client.model.PatchVariablesDto;
import com.camunda.consulting.openapi.client.model.VariableValueDto;

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
@Component("com.camunda.consulting.openapi.client.handler.ExecutionApi")
public class ExecutionApi {
    private ApiClient apiClient;

    public ExecutionApi() {
        this(new ApiClient());
    }

    @Autowired
    public ExecutionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Incident
     * Creates a custom incident with given properties.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if the incident type is null, the execution does not exist or the execution is not related to any activity.
     * @param id The id of the execution to create a new incident for. (required)
     * @param createIncidentDto  (optional)
     * @return IncidentDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public IncidentDto createIncident(String id, CreateIncidentDto createIncidentDto) throws RestClientException {
        return createIncidentWithHttpInfo(id, createIncidentDto).getBody();
    }

    /**
     * Create Incident
     * Creates a custom incident with given properties.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if the incident type is null, the execution does not exist or the execution is not related to any activity.
     * @param id The id of the execution to create a new incident for. (required)
     * @param createIncidentDto  (optional)
     * @return ResponseEntity&lt;IncidentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<IncidentDto> createIncidentWithHttpInfo(String id, CreateIncidentDto createIncidentDto) throws RestClientException {
        Object postBody = createIncidentDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createIncident");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/execution/{id}/create-incident", uriVariables);

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

        ParameterizedTypeReference<IncidentDto> returnType = new ParameterizedTypeReference<IncidentDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Delete Local Execution Variable
     * Deletes a variable in the context of a given execution by id. Deletion does not propagate upwards in the execution hierarchy.
     * <p><b>204</b> - Request successful. This method returns no content.
     * @param id The id of the execution to delete the variable from. (required)
     * @param varName The name of the variable to delete. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteLocalExecutionVariable(String id, String varName) throws RestClientException {
        deleteLocalExecutionVariableWithHttpInfo(id, varName);
    }

    /**
     * Delete Local Execution Variable
     * Deletes a variable in the context of a given execution by id. Deletion does not propagate upwards in the execution hierarchy.
     * <p><b>204</b> - Request successful. This method returns no content.
     * @param id The id of the execution to delete the variable from. (required)
     * @param varName The name of the variable to delete. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteLocalExecutionVariableWithHttpInfo(String id, String varName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteLocalExecutionVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling deleteLocalExecutionVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/execution/{id}/localVariables/{varName}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Execution
     * Retrieves an execution by id, according to the &#x60;Execution&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to be retrieved. (required)
     * @return ExecutionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ExecutionDto getExecution(String id) throws RestClientException {
        return getExecutionWithHttpInfo(id).getBody();
    }

    /**
     * Get Execution
     * Retrieves an execution by id, according to the &#x60;Execution&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to be retrieved. (required)
     * @return ResponseEntity&lt;ExecutionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ExecutionDto> getExecutionWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getExecution");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/execution/{id}", uriVariables);

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

        ParameterizedTypeReference<ExecutionDto> returnType = new ParameterizedTypeReference<ExecutionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Executions
     * Queries for the executions that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of executions. The size of the result set can be retrieved by using the [Get Execution Count](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param businessKey Filter by the business key of the process instances the executions belong to. (optional)
     * @param processDefinitionId Filter by the process definition the executions run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the executions run on. (optional)
     * @param processInstanceId Filter by the id of the process instance the execution belongs to. (optional)
     * @param activityId Filter by the id of the activity the execution currently executes. (optional)
     * @param signalEventSubscriptionName Select only those executions that expect a signal of the given name. (optional)
     * @param messageEventSubscriptionName Select only those executions that expect a message of the given name. (optional)
     * @param active Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids. (optional)
     * @param variables Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;ExecutionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ExecutionDto> getExecutions(String businessKey, String processDefinitionId, String processDefinitionKey, String processInstanceId, String activityId, String signalEventSubscriptionName, String messageEventSubscriptionName, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, String variables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getExecutionsWithHttpInfo(businessKey, processDefinitionId, processDefinitionKey, processInstanceId, activityId, signalEventSubscriptionName, messageEventSubscriptionName, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, variables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Executions
     * Queries for the executions that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of executions. The size of the result set can be retrieved by using the [Get Execution Count](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param businessKey Filter by the business key of the process instances the executions belong to. (optional)
     * @param processDefinitionId Filter by the process definition the executions run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the executions run on. (optional)
     * @param processInstanceId Filter by the id of the process instance the execution belongs to. (optional)
     * @param activityId Filter by the id of the activity the execution currently executes. (optional)
     * @param signalEventSubscriptionName Select only those executions that expect a signal of the given name. (optional)
     * @param messageEventSubscriptionName Select only those executions that expect a message of the given name. (optional)
     * @param active Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids. (optional)
     * @param variables Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;ExecutionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ExecutionDto>> getExecutionsWithHttpInfo(String businessKey, String processDefinitionId, String processDefinitionKey, String processInstanceId, String activityId, String signalEventSubscriptionName, String messageEventSubscriptionName, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, String variables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/execution", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "businessKey", businessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "signalEventSubscriptionName", signalEventSubscriptionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "messageEventSubscriptionName", messageEventSubscriptionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variables", variables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processVariables", processVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
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

        ParameterizedTypeReference<List<ExecutionDto>> returnType = new ParameterizedTypeReference<List<ExecutionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Execution Count
     * Queries for the number of executions that fulfill given parameters. Takes the same parameters as the [Get Executions](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param businessKey Filter by the business key of the process instances the executions belong to. (optional)
     * @param processDefinitionId Filter by the process definition the executions run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the executions run on. (optional)
     * @param processInstanceId Filter by the id of the process instance the execution belongs to. (optional)
     * @param activityId Filter by the id of the activity the execution currently executes. (optional)
     * @param signalEventSubscriptionName Select only those executions that expect a signal of the given name. (optional)
     * @param messageEventSubscriptionName Select only those executions that expect a message of the given name. (optional)
     * @param active Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids. (optional)
     * @param variables Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getExecutionsCount(String businessKey, String processDefinitionId, String processDefinitionKey, String processInstanceId, String activityId, String signalEventSubscriptionName, String messageEventSubscriptionName, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, String variables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        return getExecutionsCountWithHttpInfo(businessKey, processDefinitionId, processDefinitionKey, processInstanceId, activityId, signalEventSubscriptionName, messageEventSubscriptionName, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, variables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase).getBody();
    }

    /**
     * Get Execution Count
     * Queries for the number of executions that fulfill given parameters. Takes the same parameters as the [Get Executions](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param businessKey Filter by the business key of the process instances the executions belong to. (optional)
     * @param processDefinitionId Filter by the process definition the executions run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the executions run on. (optional)
     * @param processInstanceId Filter by the id of the process instance the execution belongs to. (optional)
     * @param activityId Filter by the id of the activity the execution currently executes. (optional)
     * @param signalEventSubscriptionName Select only those executions that expect a signal of the given name. (optional)
     * @param messageEventSubscriptionName Select only those executions that expect a message of the given name. (optional)
     * @param active Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids. (optional)
     * @param variables Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getExecutionsCountWithHttpInfo(String businessKey, String processDefinitionId, String processDefinitionKey, String processInstanceId, String activityId, String signalEventSubscriptionName, String messageEventSubscriptionName, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, String variables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/execution/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "businessKey", businessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "signalEventSubscriptionName", signalEventSubscriptionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "messageEventSubscriptionName", messageEventSubscriptionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variables", variables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processVariables", processVariables));
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
     * Get Local Execution Variable
     * Retrieves a variable from the context of a given execution by id. Does not traverse the parent execution hierarchy.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to retrieve the variable from. (required)
     * @param varName The name of the variable to get. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath. If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return VariableValueDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VariableValueDto getLocalExecutionVariable(String id, String varName, Boolean deserializeValue) throws RestClientException {
        return getLocalExecutionVariableWithHttpInfo(id, varName, deserializeValue).getBody();
    }

    /**
     * Get Local Execution Variable
     * Retrieves a variable from the context of a given execution by id. Does not traverse the parent execution hierarchy.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to retrieve the variable from. (required)
     * @param varName The name of the variable to get. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath. If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VariableValueDto> getLocalExecutionVariableWithHttpInfo(String id, String varName, Boolean deserializeValue) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getLocalExecutionVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling getLocalExecutionVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/execution/{id}/localVariables/{varName}", uriVariables);

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

        ParameterizedTypeReference<VariableValueDto> returnType = new ParameterizedTypeReference<VariableValueDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Local Execution Variable (Binary)
     * Retrieves a binary variable from the context of a given execution by id. Does not traverse the parent execution hierarchy. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set.
     * <p><b>400</b> - Variable instance with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to retrieve the variable from. (required)
     * @param varName The name of the variable to get. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getLocalExecutionVariableBinary(String id, String varName) throws RestClientException {
        return getLocalExecutionVariableBinaryWithHttpInfo(id, varName).getBody();
    }

    /**
     * Get Local Execution Variable (Binary)
     * Retrieves a binary variable from the context of a given execution by id. Does not traverse the parent execution hierarchy. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set.
     * <p><b>400</b> - Variable instance with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to retrieve the variable from. (required)
     * @param varName The name of the variable to get. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getLocalExecutionVariableBinaryWithHttpInfo(String id, String varName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getLocalExecutionVariableBinary");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling getLocalExecutionVariableBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/execution/{id}/localVariables/{varName}/data", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream", "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Local Execution Variables
     * Retrieves all variables of a given execution by id.
     * <p><b>200</b> - Request successful. Returns A JSON object of variables key-value pairs. Each key is a variable name and each value a VariableValueDto
     * <p><b>500</b> - Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to retrieve the variables from. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getLocalExecutionVariables(String id, Boolean deserializeValues) throws RestClientException {
        return getLocalExecutionVariablesWithHttpInfo(id, deserializeValues).getBody();
    }

    /**
     * Get Local Execution Variables
     * Retrieves all variables of a given execution by id.
     * <p><b>200</b> - Request successful. Returns A JSON object of variables key-value pairs. Each key is a variable name and each value a VariableValueDto
     * <p><b>500</b> - Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to retrieve the variables from. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getLocalExecutionVariablesWithHttpInfo(String id, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getLocalExecutionVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/execution/{id}/localVariables", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deserializeValues", deserializeValues));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Map<String, VariableValueDto>> returnType = new ParameterizedTypeReference<Map<String, VariableValueDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Message Event Subscription
     * Retrieves a message event subscription for a given execution by id and a message name.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - A message subscription for the given name and execution does not exist. This may either mean that the execution does not exist, or that it is not subscribed on such a message. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution that holds the subscription. (required)
     * @param messageName The name of the message that the subscription corresponds to. (required)
     * @return EventSubscriptionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public EventSubscriptionDto getMessageEventSubscription(String id, String messageName) throws RestClientException {
        return getMessageEventSubscriptionWithHttpInfo(id, messageName).getBody();
    }

    /**
     * Get Message Event Subscription
     * Retrieves a message event subscription for a given execution by id and a message name.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - A message subscription for the given name and execution does not exist. This may either mean that the execution does not exist, or that it is not subscribed on such a message. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution that holds the subscription. (required)
     * @param messageName The name of the message that the subscription corresponds to. (required)
     * @return ResponseEntity&lt;EventSubscriptionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<EventSubscriptionDto> getMessageEventSubscriptionWithHttpInfo(String id, String messageName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getMessageEventSubscription");
        }
        
        // verify the required parameter 'messageName' is set
        if (messageName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageName' when calling getMessageEventSubscription");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("messageName", messageName);
        String path = apiClient.expandPath("/execution/{id}/messageSubscriptions/{messageName}", uriVariables);

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

        ParameterizedTypeReference<EventSubscriptionDto> returnType = new ParameterizedTypeReference<EventSubscriptionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Update/Delete Local Execution Variables
     * Updates or deletes the variables in the context of an execution by id. The updates do not propagate upwards in the execution hierarchy. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error- handling) for the error response format.
     * <p><b>500</b> - Update or delete could not be executed, for example because the execution does not exist.
     * @param id The id of the execution to set variables for. (required)
     * @param patchVariablesDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void modifyLocalExecutionVariables(String id, PatchVariablesDto patchVariablesDto) throws RestClientException {
        modifyLocalExecutionVariablesWithHttpInfo(id, patchVariablesDto);
    }

    /**
     * Update/Delete Local Execution Variables
     * Updates or deletes the variables in the context of an execution by id. The updates do not propagate upwards in the execution hierarchy. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error- handling) for the error response format.
     * <p><b>500</b> - Update or delete could not be executed, for example because the execution does not exist.
     * @param id The id of the execution to set variables for. (required)
     * @param patchVariablesDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> modifyLocalExecutionVariablesWithHttpInfo(String id, PatchVariablesDto patchVariablesDto) throws RestClientException {
        Object postBody = patchVariablesDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling modifyLocalExecutionVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/execution/{id}/localVariables", uriVariables);

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
     * Put Local Execution Variable
     * Sets a variable in the context of a given execution by id. Update does not propagate upwards in the execution hierarchy.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error- handling) for the error response format.
     * @param id The id of the execution to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param variableValueDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void putLocalExecutionVariable(String id, String varName, VariableValueDto variableValueDto) throws RestClientException {
        putLocalExecutionVariableWithHttpInfo(id, varName, variableValueDto);
    }

    /**
     * Put Local Execution Variable
     * Sets a variable in the context of a given execution by id. Update does not propagate upwards in the execution hierarchy.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error- handling) for the error response format.
     * @param id The id of the execution to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param variableValueDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> putLocalExecutionVariableWithHttpInfo(String id, String varName, VariableValueDto variableValueDto) throws RestClientException {
        Object postBody = variableValueDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling putLocalExecutionVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling putLocalExecutionVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/execution/{id}/localVariables/{varName}", uriVariables);

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
     * Get Executions (POST)
     * Queries for executions that fulfill given parameters through a JSON object. This method is slightly more powerful than the [Get Executions](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query/) method because it allows to filter by multiple instance and execution variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param executionQueryDto  (optional)
     * @return List&lt;ExecutionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ExecutionDto> queryExecutions(Integer firstResult, Integer maxResults, ExecutionQueryDto executionQueryDto) throws RestClientException {
        return queryExecutionsWithHttpInfo(firstResult, maxResults, executionQueryDto).getBody();
    }

    /**
     * Get Executions (POST)
     * Queries for executions that fulfill given parameters through a JSON object. This method is slightly more powerful than the [Get Executions](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query/) method because it allows to filter by multiple instance and execution variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param executionQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;ExecutionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ExecutionDto>> queryExecutionsWithHttpInfo(Integer firstResult, Integer maxResults, ExecutionQueryDto executionQueryDto) throws RestClientException {
        Object postBody = executionQueryDto;
        
        String path = apiClient.expandPath("/execution", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<ExecutionDto>> returnType = new ParameterizedTypeReference<List<ExecutionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Execution Count (POST)
     * Queries for the number of executions that fulfill given parameters. This method takes the same message body as the [Get Executions POST](https://docs.camunda.org/manual/7.16/reference/rest/execution/post-query/) method and therefore it is slightly more powerful than the [Get Execution Count](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param executionQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryExecutionsCount(ExecutionQueryDto executionQueryDto) throws RestClientException {
        return queryExecutionsCountWithHttpInfo(executionQueryDto).getBody();
    }

    /**
     * Get Execution Count (POST)
     * Queries for the number of executions that fulfill given parameters. This method takes the same message body as the [Get Executions POST](https://docs.camunda.org/manual/7.16/reference/rest/execution/post-query/) method and therefore it is slightly more powerful than the [Get Execution Count](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param executionQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryExecutionsCountWithHttpInfo(ExecutionQueryDto executionQueryDto) throws RestClientException {
        Object postBody = executionQueryDto;
        
        String path = apiClient.expandPath("/execution/count", Collections.<String, Object>emptyMap());

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
     * Post Local Execution Variable (Binary)
     * Sets the serialized value for a binary variable or the binary value for a file variable in the context of a given execution by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if no filename is set. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param data The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. (optional)
     * @param valueType The name of the variable type. Either Bytes for a byte array variable or File for a file variable. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setLocalExecutionVariableBinary(String id, String varName, File data, String valueType) throws RestClientException {
        setLocalExecutionVariableBinaryWithHttpInfo(id, varName, data, valueType);
    }

    /**
     * Post Local Execution Variable (Binary)
     * Sets the serialized value for a binary variable or the binary value for a file variable in the context of a given execution by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if no filename is set. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param data The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. (optional)
     * @param valueType The name of the variable type. Either Bytes for a byte array variable or File for a file variable. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setLocalExecutionVariableBinaryWithHttpInfo(String id, String varName, File data, String valueType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setLocalExecutionVariableBinary");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling setLocalExecutionVariableBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/execution/{id}/localVariables/{varName}/data", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (data != null)
            formParams.add("data", new FileSystemResource(data));
        if (valueType != null)
            formParams.add("valueType", valueType);

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "multipart/form-data"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Trigger Execution
     * Signals an execution by id. Can for example be used to explicitly skip user tasks or signal asynchronous continuations.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to signal. (required)
     * @param executionTriggerDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void signalExecution(String id, ExecutionTriggerDto executionTriggerDto) throws RestClientException {
        signalExecutionWithHttpInfo(id, executionTriggerDto);
    }

    /**
     * Trigger Execution
     * Signals an execution by id. Can for example be used to explicitly skip user tasks or signal asynchronous continuations.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to signal. (required)
     * @param executionTriggerDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> signalExecutionWithHttpInfo(String id, ExecutionTriggerDto executionTriggerDto) throws RestClientException {
        Object postBody = executionTriggerDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling signalExecution");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/execution/{id}/signal", uriVariables);

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
     * Trigger Message Event Subscription
     * Delivers a message to a specific execution by id, to trigger an existing message event subscription. Inject process variables as the message&#39;s payload.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The addressed execution has no pending message subscriptions for the given message. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to submit the message to. (required)
     * @param messageName The name of the message that the addressed subscription corresponds to. (required)
     * @param executionTriggerDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void triggerEvent(String id, String messageName, ExecutionTriggerDto executionTriggerDto) throws RestClientException {
        triggerEventWithHttpInfo(id, messageName, executionTriggerDto);
    }

    /**
     * Trigger Message Event Subscription
     * Delivers a message to a specific execution by id, to trigger an existing message event subscription. Inject process variables as the message&#39;s payload.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The addressed execution has no pending message subscriptions for the given message. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the execution to submit the message to. (required)
     * @param messageName The name of the message that the addressed subscription corresponds to. (required)
     * @param executionTriggerDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> triggerEventWithHttpInfo(String id, String messageName, ExecutionTriggerDto executionTriggerDto) throws RestClientException {
        Object postBody = executionTriggerDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling triggerEvent");
        }
        
        // verify the required parameter 'messageName' is set
        if (messageName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'messageName' when calling triggerEvent");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("messageName", messageName);
        String path = apiClient.expandPath("/execution/{id}/messageSubscriptions/{messageName}/trigger", uriVariables);

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
}
