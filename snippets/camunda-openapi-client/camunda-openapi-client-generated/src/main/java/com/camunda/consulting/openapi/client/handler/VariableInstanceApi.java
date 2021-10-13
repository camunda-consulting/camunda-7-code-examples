package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.VariableInstanceDto;
import com.camunda.consulting.openapi.client.model.VariableInstanceQueryDto;

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
@Component("com.camunda.consulting.openapi.client.handler.VariableInstanceApi")
public class VariableInstanceApi {
    private ApiClient apiClient;

    public VariableInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public VariableInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Variable Instance
     * Retrieves a variable by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:**  While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return VariableInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VariableInstanceDto getVariableInstance(String id, Boolean deserializeValue) throws RestClientException {
        return getVariableInstanceWithHttpInfo(id, deserializeValue).getBody();
    }

    /**
     * Get Variable Instance
     * Retrieves a variable by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:**  While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;VariableInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VariableInstanceDto> getVariableInstanceWithHttpInfo(String id, Boolean deserializeValue) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getVariableInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/variable-instance/{id}", uriVariables);

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

        ParameterizedTypeReference<VariableInstanceDto> returnType = new ParameterizedTypeReference<VariableInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Variable Instance (Binary)
     * Retrieves the content of a variable by id. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful. For binary variables or files without any MIME type information, a byte stream is returned.                       File variables with MIME type information are returned as the saved type. Additionally, for file                       variables the Content-Disposition header will be set.
     * <p><b>400</b> - Variable with given id exists but does not serialize as binary data. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getVariableInstanceBinary(String id) throws RestClientException {
        return getVariableInstanceBinaryWithHttpInfo(id).getBody();
    }

    /**
     * Get Variable Instance (Binary)
     * Retrieves the content of a variable by id. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful. For binary variables or files without any MIME type information, a byte stream is returned.                       File variables with MIME type information are returned as the saved type. Additionally, for file                       variables the Content-Disposition header will be set.
     * <p><b>400</b> - Variable with given id exists but does not serialize as binary data. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the variable instance. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getVariableInstanceBinaryWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getVariableInstanceBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/variable-instance/{id}/data", uriVariables);

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
     * Query for variable instances that fulfill given parameters. Parameters may be the properties of variable instances, such as the name or type. The size of the result set can be retrieved by using the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable instance name. (optional)
     * @param variableNameLike Filter by the variable instance name. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param processInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param executionIdIn Only include variable instances which belong to one of the passed and comma-separated execution ids. (optional)
     * @param caseInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated case instance ids. (optional)
     * @param caseExecutionIdIn Only include variable instances which belong to one of the passed and comma-separated case execution ids. (optional)
     * @param taskIdIn Only include variable instances which belong to one of the passed and comma-separated task ids. (optional)
     * @param batchIdIn Only include variable instances which belong to one of the passed and comma-separated batch ids. (optional)
     * @param activityInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include variable instances which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param variableValues Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableScopeIdIn Only include variable instances which belong to one of passed scope ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return List&lt;VariableInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<VariableInstanceDto> getVariableInstances(String variableName, String variableNameLike, String processInstanceIdIn, String executionIdIn, String caseInstanceIdIn, String caseExecutionIdIn, String taskIdIn, String batchIdIn, String activityInstanceIdIn, String tenantIdIn, String variableValues, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableScopeIdIn, String sortBy, String sortOrder, Integer firstResult, Integer maxResults, Boolean deserializeValues) throws RestClientException {
        return getVariableInstancesWithHttpInfo(variableName, variableNameLike, processInstanceIdIn, executionIdIn, caseInstanceIdIn, caseExecutionIdIn, taskIdIn, batchIdIn, activityInstanceIdIn, tenantIdIn, variableValues, variableNamesIgnoreCase, variableValuesIgnoreCase, variableScopeIdIn, sortBy, sortOrder, firstResult, maxResults, deserializeValues).getBody();
    }

    /**
     * Get Variable Instances
     * Query for variable instances that fulfill given parameters. Parameters may be the properties of variable instances, such as the name or type. The size of the result set can be retrieved by using the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable instance name. (optional)
     * @param variableNameLike Filter by the variable instance name. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param processInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param executionIdIn Only include variable instances which belong to one of the passed and comma-separated execution ids. (optional)
     * @param caseInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated case instance ids. (optional)
     * @param caseExecutionIdIn Only include variable instances which belong to one of the passed and comma-separated case execution ids. (optional)
     * @param taskIdIn Only include variable instances which belong to one of the passed and comma-separated task ids. (optional)
     * @param batchIdIn Only include variable instances which belong to one of the passed and comma-separated batch ids. (optional)
     * @param activityInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include variable instances which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param variableValues Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableScopeIdIn Only include variable instances which belong to one of passed scope ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @return ResponseEntity&lt;List&lt;VariableInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<VariableInstanceDto>> getVariableInstancesWithHttpInfo(String variableName, String variableNameLike, String processInstanceIdIn, String executionIdIn, String caseInstanceIdIn, String caseExecutionIdIn, String taskIdIn, String batchIdIn, String activityInstanceIdIn, String tenantIdIn, String variableValues, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableScopeIdIn, String sortBy, String sortOrder, Integer firstResult, Integer maxResults, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/variable-instance", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableName", variableName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNameLike", variableNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceIdIn", caseInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionIdIn", caseExecutionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskIdIn", taskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "batchIdIn", batchIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValues", variableValues));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableScopeIdIn", variableScopeIdIn));
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

        ParameterizedTypeReference<List<VariableInstanceDto>> returnType = new ParameterizedTypeReference<List<VariableInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Variable Instance Count
     * Query for the number of variable instances that fulfill given parameters. Takes the same parameters as the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable instance name. (optional)
     * @param variableNameLike Filter by the variable instance name. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param processInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param executionIdIn Only include variable instances which belong to one of the passed and comma-separated execution ids. (optional)
     * @param caseInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated case instance ids. (optional)
     * @param caseExecutionIdIn Only include variable instances which belong to one of the passed and comma-separated case execution ids. (optional)
     * @param taskIdIn Only include variable instances which belong to one of the passed and comma-separated task ids. (optional)
     * @param batchIdIn Only include variable instances which belong to one of the passed and comma-separated batch ids. (optional)
     * @param activityInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include variable instances which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param variableValues Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableScopeIdIn Only include variable instances which belong to one of passed scope ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getVariableInstancesCount(String variableName, String variableNameLike, String processInstanceIdIn, String executionIdIn, String caseInstanceIdIn, String caseExecutionIdIn, String taskIdIn, String batchIdIn, String activityInstanceIdIn, String tenantIdIn, String variableValues, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableScopeIdIn, String sortBy, String sortOrder) throws RestClientException {
        return getVariableInstancesCountWithHttpInfo(variableName, variableNameLike, processInstanceIdIn, executionIdIn, caseInstanceIdIn, caseExecutionIdIn, taskIdIn, batchIdIn, activityInstanceIdIn, tenantIdIn, variableValues, variableNamesIgnoreCase, variableValuesIgnoreCase, variableScopeIdIn, sortBy, sortOrder).getBody();
    }

    /**
     * Get Variable Instance Count
     * Query for the number of variable instances that fulfill given parameters. Takes the same parameters as the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableName Filter by variable instance name. (optional)
     * @param variableNameLike Filter by the variable instance name. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param processInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated process instance ids. (optional)
     * @param executionIdIn Only include variable instances which belong to one of the passed and comma-separated execution ids. (optional)
     * @param caseInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated case instance ids. (optional)
     * @param caseExecutionIdIn Only include variable instances which belong to one of the passed and comma-separated case execution ids. (optional)
     * @param taskIdIn Only include variable instances which belong to one of the passed and comma-separated task ids. (optional)
     * @param batchIdIn Only include variable instances which belong to one of the passed and comma-separated batch ids. (optional)
     * @param activityInstanceIdIn Only include variable instances which belong to one of the passed and comma-separated activity instance ids. (optional)
     * @param tenantIdIn Only include variable instances which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param variableValues Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match all variable values provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param variableScopeIdIn Only include variable instances which belong to one of passed scope ids. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getVariableInstancesCountWithHttpInfo(String variableName, String variableNameLike, String processInstanceIdIn, String executionIdIn, String caseInstanceIdIn, String caseExecutionIdIn, String taskIdIn, String batchIdIn, String activityInstanceIdIn, String tenantIdIn, String variableValues, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String variableScopeIdIn, String sortBy, String sortOrder) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/variable-instance/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableName", variableName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNameLike", variableNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionIdIn", executionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceIdIn", caseInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionIdIn", caseExecutionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskIdIn", taskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "batchIdIn", batchIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValues", variableValues));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableScopeIdIn", variableScopeIdIn));
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
    /**
     * Get Variable Instances (POST)
     * Query for variable instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/variable- instance/get-query/) method because it allows filtering by multiple variable instances of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @param variableInstanceQueryDto  (optional)
     * @return List&lt;VariableInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<VariableInstanceDto> queryVariableInstances(Integer firstResult, Integer maxResults, Boolean deserializeValues, VariableInstanceQueryDto variableInstanceQueryDto) throws RestClientException {
        return queryVariableInstancesWithHttpInfo(firstResult, maxResults, deserializeValues, variableInstanceQueryDto).getBody();
    }

    /**
     * Get Variable Instances (POST)
     * Query for variable instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/variable- instance/get-query/) method because it allows filtering by multiple variable instances of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional)
     * @param variableInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;VariableInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<VariableInstanceDto>> queryVariableInstancesWithHttpInfo(Integer firstResult, Integer maxResults, Boolean deserializeValues, VariableInstanceQueryDto variableInstanceQueryDto) throws RestClientException {
        Object postBody = variableInstanceQueryDto;
        
        String path = apiClient.expandPath("/variable-instance", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<VariableInstanceDto>> returnType = new ParameterizedTypeReference<List<VariableInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Variable Instance Count (POST)
     * Query for the number of variable instances that fulfill given parameters. This method takes the same message body as the [Get Variable Instances POST](https://docs.camunda.org/manual/7.16/reference/rest/variable- instance/post-query/) method and therefore it is slightly more powerful than the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableInstanceQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryVariableInstancesCount(VariableInstanceQueryDto variableInstanceQueryDto) throws RestClientException {
        return queryVariableInstancesCountWithHttpInfo(variableInstanceQueryDto).getBody();
    }

    /**
     * Get Variable Instance Count (POST)
     * Query for the number of variable instances that fulfill given parameters. This method takes the same message body as the [Get Variable Instances POST](https://docs.camunda.org/manual/7.16/reference/rest/variable- instance/post-query/) method and therefore it is slightly more powerful than the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param variableInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryVariableInstancesCountWithHttpInfo(VariableInstanceQueryDto variableInstanceQueryDto) throws RestClientException {
        Object postBody = variableInstanceQueryDto;
        
        String path = apiClient.expandPath("/variable-instance/count", Collections.<String, Object>emptyMap());

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
