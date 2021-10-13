package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
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
@Component("com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi")
public class TaskLocalVariableApi {
    private ApiClient apiClient;

    public TaskLocalVariableApi() {
        this(new ApiClient());
    }

    @Autowired
    public TaskLocalVariableApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete Local Task Variable
     * Removes a local variable from a task by id.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to delete the variable from. (required)
     * @param varName The name of the variable to be removed. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteTaskLocalVariable(String id, String varName) throws RestClientException {
        deleteTaskLocalVariableWithHttpInfo(id, varName);
    }

    /**
     * Delete Local Task Variable
     * Removes a local variable from a task by id.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to delete the variable from. (required)
     * @param varName The name of the variable to be removed. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteTaskLocalVariableWithHttpInfo(String id, String varName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteTaskLocalVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling deleteTaskLocalVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/task/{id}/localVariables/{varName}", uriVariables);

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
     * Get Local Task Variable
     * Retrieves a variable from the context of a given task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variable from. (required)
     * @param varName The name of the variable to get (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return VariableValueDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VariableValueDto getTaskLocalVariable(String id, String varName, Boolean deserializeValue) throws RestClientException {
        return getTaskLocalVariableWithHttpInfo(id, varName, deserializeValue).getBody();
    }

    /**
     * Get Local Task Variable
     * Retrieves a variable from the context of a given task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variable from. (required)
     * @param varName The name of the variable to get (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VariableValueDto> getTaskLocalVariableWithHttpInfo(String id, String varName, Boolean deserializeValue) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getTaskLocalVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling getTaskLocalVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/task/{id}/localVariables/{varName}", uriVariables);

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
     * Get Local Task Variable (Binary)
     * Retrieves a binary variable from the context of a given task by id. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set.
     * <p><b>400</b> - Variable with given id exists but is not a binary variable.See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getTaskLocalVariableBinary(String id, String varName) throws RestClientException {
        return getTaskLocalVariableBinaryWithHttpInfo(id, varName).getBody();
    }

    /**
     * Get Local Task Variable (Binary)
     * Retrieves a binary variable from the context of a given task by id. Applicable for byte array and file variables.
     * <p><b>200</b> - Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set.
     * <p><b>400</b> - Variable with given id exists but is not a binary variable.See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getTaskLocalVariableBinaryWithHttpInfo(String id, String varName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getTaskLocalVariableBinary");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling getTaskLocalVariableBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/task/{id}/localVariables/{varName}/data", uriVariables);

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
     * Get Local Task Variables
     * Retrieves all variables of a given task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variables from. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getTaskLocalVariables(String id, Boolean deserializeValues) throws RestClientException {
        return getTaskLocalVariablesWithHttpInfo(id, deserializeValues).getBody();
    }

    /**
     * Get Local Task Variables
     * Retrieves all variables of a given task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variables from. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getTaskLocalVariablesWithHttpInfo(String id, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getTaskLocalVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/localVariables", uriVariables);

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
     * Update/Delete Local Task Variables
     * Updates or deletes the variables in the context of a task. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable value or type is invalid. For example the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Update or delete could not be executed because the task is &#x60;null&#x60; or does not exist.. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to set variables for. (required)
     * @param patchVariablesDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void modifyTaskLocalVariables(String id, PatchVariablesDto patchVariablesDto) throws RestClientException {
        modifyTaskLocalVariablesWithHttpInfo(id, patchVariablesDto);
    }

    /**
     * Update/Delete Local Task Variables
     * Updates or deletes the variables in the context of a task. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable value or type is invalid. For example the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Update or delete could not be executed because the task is &#x60;null&#x60; or does not exist.. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to set variables for. (required)
     * @param patchVariablesDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> modifyTaskLocalVariablesWithHttpInfo(String id, PatchVariablesDto patchVariablesDto) throws RestClientException {
        Object postBody = patchVariablesDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling modifyTaskLocalVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/localVariables", uriVariables);

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
     * Update Local Task Variable
     * Sets a variable in the context of a given task.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable name, value or type is invalid, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported or a new transient variable has the name that is already persisted. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The variable name is &#x60;null&#x60;, or the Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param variableValueDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void putTaskLocalVariable(String id, String varName, VariableValueDto variableValueDto) throws RestClientException {
        putTaskLocalVariableWithHttpInfo(id, varName, variableValueDto);
    }

    /**
     * Update Local Task Variable
     * Sets a variable in the context of a given task.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable name, value or type is invalid, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported or a new transient variable has the name that is already persisted. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The variable name is &#x60;null&#x60;, or the Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param variableValueDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> putTaskLocalVariableWithHttpInfo(String id, String varName, VariableValueDto variableValueDto) throws RestClientException {
        Object postBody = variableValueDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling putTaskLocalVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling putTaskLocalVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/task/{id}/localVariables/{varName}", uriVariables);

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
     * Update Local Task Variable (Binary)
     * Sets the serialized value for a binary variable or the binary value for a file variable.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable value or type is invalid, for example if no filename is set. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Variable name is &#x60;null&#x60;, or the Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @param data The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. (optional)
     * @param valueType The name of the variable type. Either Bytes for a byte array variable or File for a file variable. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setBinaryTaskLocalVariable(String id, String varName, File data, String valueType) throws RestClientException {
        setBinaryTaskLocalVariableWithHttpInfo(id, varName, data, valueType);
    }

    /**
     * Update Local Task Variable (Binary)
     * Sets the serialized value for a binary variable or the binary value for a file variable.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable value or type is invalid, for example if no filename is set. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - Variable name is &#x60;null&#x60;, or the Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @param data The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. (optional)
     * @param valueType The name of the variable type. Either Bytes for a byte array variable or File for a file variable. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setBinaryTaskLocalVariableWithHttpInfo(String id, String varName, File data, String valueType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setBinaryTaskLocalVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling setBinaryTaskLocalVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/task/{id}/localVariables/{varName}/data", uriVariables);

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
}
