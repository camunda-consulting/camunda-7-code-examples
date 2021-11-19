package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.HistoricVariableInstanceDto;
import com.camunda.consulting.openapi.client.model.HistoricVariableInstanceQueryDto;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi")
public class HistoricVariableInstanceApi {
    private ApiClient apiClient;

    public HistoricVariableInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricVariableInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete Variable Instance
     * Deletes a historic variable instance by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteHistoricVariableInstance(String id) throws RestClientException {
        deleteHistoricVariableInstanceWithHttpInfo(id);
    }

    /**
     * Delete Variable Instance
     * Deletes a historic variable instance by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteHistoricVariableInstanceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteHistoricVariableInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/variable-instance/{id}", uriVariables);

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
     * Get Variable Instance
     * Retrieves a historic variable by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return HistoricVariableInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricVariableInstanceDto getHistoricVariableInstance(String id, Boolean deserializeValues) throws RestClientException {
        return getHistoricVariableInstanceWithHttpInfo(id, deserializeValues).getBody();
    }

    /**
     * Get Variable Instance
     * Retrieves a historic variable by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;HistoricVariableInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricVariableInstanceDto> getHistoricVariableInstanceWithHttpInfo(String id, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricVariableInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/variable-instance/{id}", uriVariables);

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

        ParameterizedTypeReference<HistoricVariableInstanceDto> returnType = new ParameterizedTypeReference<HistoricVariableInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Variable Instance (Binary)
     * Retrieves the content of a historic variable by id. Applicable for variables that are serialized as binary data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Variable with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getHistoricVariableInstanceBinary(String id) throws RestClientException {
        return getHistoricVariableInstanceBinaryWithHttpInfo(id).getBody();
    }

    /**
     * Get Variable Instance (Binary)
     * Retrieves the content of a historic variable by id. Applicable for variables that are serialized as binary data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Variable with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getHistoricVariableInstanceBinaryWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricVariableInstanceBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/variable-instance/{id}/data", uriVariables);

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
     * Get Variable Instances
     * Queries for historic variable instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable name. (optional)
     * @param variableNameLike Restrict to variables with a name like the parameter. (optional)
     * @param variableValue Filter by variable value. Is treated as a &#x60;String&#x60; object on server side. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableTypeIn Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. (optional)
     * @param includeDeleted Include variables that has already been deleted during the execution. (optional)
     * @param processInstanceId Filter by the process instance the variable belongs to. (optional)
     * @param processInstanceIdIn Only include historic variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param processDefinitionId Filter by the process definition the variable belongs to. (optional)
     * @param processDefinitionKey Filter by a key of the process definition the variable belongs to. (optional)
     * @param executionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated execution ids. (optional)
     * @param caseInstanceId Filter by the case instance the variable belongs to. (optional)
     * @param caseExecutionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids. (optional)
     * @param caseActivityIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids. (optional)
     * @param taskIdIn Only include historic variable instances which belong to one of the passed and and comma-separated task ids. (optional)
     * @param activityInstanceIdIn Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return List&lt;HistoricVariableInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricVariableInstanceDto> getHistoricVariableInstances(String variableName, String variableNameLike, Object variableValue, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableTypeIn, Boolean includeDeleted, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String processDefinitionKey, String executionIdIn, String caseInstanceId, String caseExecutionIdIn, String caseActivityIdIn, String taskIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults, Boolean deserializeValues) throws RestClientException {
        return getHistoricVariableInstancesWithHttpInfo(variableName, variableNameLike, variableValue, variableNamesIgnoreCase, variableValuesIgnoreCase, variableTypeIn, includeDeleted, processInstanceId, processInstanceIdIn, processDefinitionId, processDefinitionKey, executionIdIn, caseInstanceId, caseExecutionIdIn, caseActivityIdIn, taskIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults, deserializeValues).getBody();
    }

    /**
     * Get Variable Instances
     * Queries for historic variable instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable name. (optional)
     * @param variableNameLike Restrict to variables with a name like the parameter. (optional)
     * @param variableValue Filter by variable value. Is treated as a &#x60;String&#x60; object on server side. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableTypeIn Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. (optional)
     * @param includeDeleted Include variables that has already been deleted during the execution. (optional)
     * @param processInstanceId Filter by the process instance the variable belongs to. (optional)
     * @param processInstanceIdIn Only include historic variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param processDefinitionId Filter by the process definition the variable belongs to. (optional)
     * @param processDefinitionKey Filter by a key of the process definition the variable belongs to. (optional)
     * @param executionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated execution ids. (optional)
     * @param caseInstanceId Filter by the case instance the variable belongs to. (optional)
     * @param caseExecutionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids. (optional)
     * @param caseActivityIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids. (optional)
     * @param taskIdIn Only include historic variable instances which belong to one of the passed and and comma-separated task ids. (optional)
     * @param activityInstanceIdIn Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricVariableInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricVariableInstanceDto>> getHistoricVariableInstancesWithHttpInfo(String variableName, String variableNameLike, Object variableValue, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableTypeIn, Boolean includeDeleted, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String processDefinitionKey, String executionIdIn, String caseInstanceId, String caseExecutionIdIn, String caseActivityIdIn, String taskIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/variable-instance", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableName", variableName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNameLike", variableNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValue", variableValue));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableTypeIn", variableTypeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDeleted", includeDeleted));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionIdIn", caseExecutionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseActivityIdIn", caseActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskIdIn", taskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
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

        ParameterizedTypeReference<List<HistoricVariableInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricVariableInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Variable Instance Count
     * Queries for the number of historic variable instances that fulfill the given parameters. Takes the same parameters as the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable name. (optional)
     * @param variableNameLike Restrict to variables with a name like the parameter. (optional)
     * @param variableValue Filter by variable value. Is treated as a &#x60;String&#x60; object on server side. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableTypeIn Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. (optional)
     * @param includeDeleted Include variables that has already been deleted during the execution. (optional)
     * @param processInstanceId Filter by the process instance the variable belongs to. (optional)
     * @param processInstanceIdIn Only include historic variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param processDefinitionId Filter by the process definition the variable belongs to. (optional)
     * @param processDefinitionKey Filter by a key of the process definition the variable belongs to. (optional)
     * @param executionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated execution ids. (optional)
     * @param caseInstanceId Filter by the case instance the variable belongs to. (optional)
     * @param caseExecutionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids. (optional)
     * @param caseActivityIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids. (optional)
     * @param taskIdIn Only include historic variable instances which belong to one of the passed and and comma-separated task ids. (optional)
     * @param activityInstanceIdIn Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricVariableInstancesCount(String variableName, String variableNameLike, Object variableValue, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableTypeIn, Boolean includeDeleted, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String processDefinitionKey, String executionIdIn, String caseInstanceId, String caseExecutionIdIn, String caseActivityIdIn, String taskIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        return getHistoricVariableInstancesCountWithHttpInfo(variableName, variableNameLike, variableValue, variableNamesIgnoreCase, variableValuesIgnoreCase, variableTypeIn, includeDeleted, processInstanceId, processInstanceIdIn, processDefinitionId, processDefinitionKey, executionIdIn, caseInstanceId, caseExecutionIdIn, caseActivityIdIn, taskIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId).getBody();
    }

    /**
     * Get Variable Instance Count
     * Queries for the number of historic variable instances that fulfill the given parameters. Takes the same parameters as the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable name. (optional)
     * @param variableNameLike Restrict to variables with a name like the parameter. (optional)
     * @param variableValue Filter by variable value. Is treated as a &#x60;String&#x60; object on server side. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableTypeIn Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. (optional)
     * @param includeDeleted Include variables that has already been deleted during the execution. (optional)
     * @param processInstanceId Filter by the process instance the variable belongs to. (optional)
     * @param processInstanceIdIn Only include historic variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param processDefinitionId Filter by the process definition the variable belongs to. (optional)
     * @param processDefinitionKey Filter by a key of the process definition the variable belongs to. (optional)
     * @param executionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated execution ids. (optional)
     * @param caseInstanceId Filter by the case instance the variable belongs to. (optional)
     * @param caseExecutionIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids. (optional)
     * @param caseActivityIdIn Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids. (optional)
     * @param taskIdIn Only include historic variable instances which belong to one of the passed and and comma-separated task ids. (optional)
     * @param activityInstanceIdIn Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. (optional)
     * @param withoutTenantId Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricVariableInstancesCountWithHttpInfo(String variableName, String variableNameLike, Object variableValue, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableTypeIn, Boolean includeDeleted, String processInstanceId, String processInstanceIdIn, String processDefinitionId, String processDefinitionKey, String executionIdIn, String caseInstanceId, String caseExecutionIdIn, String caseActivityIdIn, String taskIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/variable-instance/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableName", variableName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNameLike", variableNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValue", variableValue));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableTypeIn", variableTypeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDeleted", includeDeleted));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionIdIn", caseExecutionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseActivityIdIn", caseActivityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskIdIn", taskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
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
     * Get Variable Instances (POST)
     * Queries for historic variable instances that fulfill the given parameters. This method is slightly more powerful than the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query/) method because it allows filtering by variable values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @param historicVariableInstanceQueryDto  (optional)
     * @return List&lt;HistoricVariableInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricVariableInstanceDto> queryHistoricVariableInstances(Integer firstResult, Integer maxResults, Boolean deserializeValues, HistoricVariableInstanceQueryDto historicVariableInstanceQueryDto) throws RestClientException {
        return queryHistoricVariableInstancesWithHttpInfo(firstResult, maxResults, deserializeValues, historicVariableInstanceQueryDto).getBody();
    }

    /**
     * Get Variable Instances (POST)
     * Queries for historic variable instances that fulfill the given parameters. This method is slightly more powerful than the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query/) method because it allows filtering by variable values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @param historicVariableInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricVariableInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricVariableInstanceDto>> queryHistoricVariableInstancesWithHttpInfo(Integer firstResult, Integer maxResults, Boolean deserializeValues, HistoricVariableInstanceQueryDto historicVariableInstanceQueryDto) throws RestClientException {
        Object postBody = historicVariableInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/variable-instance", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<HistoricVariableInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricVariableInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Variable Instance Count (POST)
     * Queries for historic variable instances that fulfill the given parameters. This method takes the same message body as the [Get Variable Instances (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/post-variable-instance-query/) method and therefore it is more powerful regarding variable values than the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicVariableInstanceQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryHistoricVariableInstancesCount(HistoricVariableInstanceQueryDto historicVariableInstanceQueryDto) throws RestClientException {
        return queryHistoricVariableInstancesCountWithHttpInfo(historicVariableInstanceQueryDto).getBody();
    }

    /**
     * Get Variable Instance Count (POST)
     * Queries for historic variable instances that fulfill the given parameters. This method takes the same message body as the [Get Variable Instances (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/post-variable-instance-query/) method and therefore it is more powerful regarding variable values than the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicVariableInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryHistoricVariableInstancesCountWithHttpInfo(HistoricVariableInstanceQueryDto historicVariableInstanceQueryDto) throws RestClientException {
        Object postBody = historicVariableInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/variable-instance/count", Collections.<String, Object>emptyMap());

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
