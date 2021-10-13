package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AuthorizationExceptionDto;
import com.camunda.consulting.openapi.client.model.CompleteTaskDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.FormDto;
import com.camunda.consulting.openapi.client.model.TaskBpmnErrorDto;
import com.camunda.consulting.openapi.client.model.TaskDto;
import com.camunda.consulting.openapi.client.model.TaskEscalationDto;
import com.camunda.consulting.openapi.client.model.TaskQueryDto;
import com.camunda.consulting.openapi.client.model.UserIdDto;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T17:49:51.183809+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.TaskApi")
public class TaskApi {
    private ApiClient apiClient;

    public TaskApi() {
        this(new ApiClient());
    }

    @Autowired
    public TaskApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Claim
     * Claims a task for a specific user.  **Note:** The difference with the [Set Assignee](https://docs.camunda.org/manual/7.16/reference/rest/task/post-assignee/) method is that here a check is performed to see if the task already has a user assigned to it.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - Task with given id does not exist or claiming was not successful. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to claim. (required)
     * @param userIdDto Provide the id of the user that claims the task. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void claim(String id, UserIdDto userIdDto) throws RestClientException {
        claimWithHttpInfo(id, userIdDto);
    }

    /**
     * Claim
     * Claims a task for a specific user.  **Note:** The difference with the [Set Assignee](https://docs.camunda.org/manual/7.16/reference/rest/task/post-assignee/) method is that here a check is performed to see if the task already has a user assigned to it.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - Task with given id does not exist or claiming was not successful. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to claim. (required)
     * @param userIdDto Provide the id of the user that claims the task. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> claimWithHttpInfo(String id, UserIdDto userIdDto) throws RestClientException {
        Object postBody = userIdDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling claim");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/claim", uriVariables);

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
     * Complete
     * Completes a task and updates process variables.
     * <p><b>200</b> - Request successful. The response contains the process variables.
     * <p><b>204</b> - Request successful. The response contains no variables.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - If the task does not exist or the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to complete. (required)
     * @param completeTaskDto  (optional)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> complete(String id, CompleteTaskDto completeTaskDto) throws RestClientException {
        return completeWithHttpInfo(id, completeTaskDto).getBody();
    }

    /**
     * Complete
     * Completes a task and updates process variables.
     * <p><b>200</b> - Request successful. The response contains the process variables.
     * <p><b>204</b> - Request successful. The response contains no variables.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - If the task does not exist or the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to complete. (required)
     * @param completeTaskDto  (optional)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> completeWithHttpInfo(String id, CompleteTaskDto completeTaskDto) throws RestClientException {
        Object postBody = completeTaskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling complete");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/complete", uriVariables);

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

        ParameterizedTypeReference<Map<String, VariableValueDto>> returnType = new ParameterizedTypeReference<Map<String, VariableValueDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Create
     * Creates a new task.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if a not valid &#x60;delegationState&#x60; is supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createTask(TaskDto taskDto) throws RestClientException {
        createTaskWithHttpInfo(taskDto);
    }

    /**
     * Create
     * Creates a new task.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if a not valid &#x60;delegationState&#x60; is supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createTaskWithHttpInfo(TaskDto taskDto) throws RestClientException {
        Object postBody = taskDto;
        
        String path = apiClient.expandPath("/task/create", Collections.<String, Object>emptyMap());

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
     * Delegate
     * Delegates a task to another user.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - If the task does not exist or delegation was not successful. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to delegate. (required)
     * @param userIdDto Provide the id of the user that the task should be delegated to. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void delegateTask(String id, UserIdDto userIdDto) throws RestClientException {
        delegateTaskWithHttpInfo(id, userIdDto);
    }

    /**
     * Delegate
     * Delegates a task to another user.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - If the task does not exist or delegation was not successful. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to delegate. (required)
     * @param userIdDto Provide the id of the user that the task should be delegated to. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> delegateTaskWithHttpInfo(String id, UserIdDto userIdDto) throws RestClientException {
        Object postBody = userIdDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling delegateTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/delegate", uriVariables);

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
     * Delete
     * Removes a task by id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request. The Task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The Task with the given id cannot be deleted because it is part of a running process or case instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to be removed. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteTask(String id) throws RestClientException {
        deleteTaskWithHttpInfo(id);
    }

    /**
     * Delete
     * Removes a task by id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request. The Task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The Task with the given id cannot be deleted because it is part of a running process or case instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to be removed. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteTaskWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}", uriVariables);

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
     * Get Deployed Form
     * Retrieves the deployed form that is referenced from a given task. For further information please refer to the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed form cannot be retrieved due to missing permissions on task resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed form for a given task exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to get the deployed form for. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDeployedForm(String id) throws RestClientException {
        return getDeployedFormWithHttpInfo(id).getBody();
    }

    /**
     * Get Deployed Form
     * Retrieves the deployed form that is referenced from a given task. For further information please refer to the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed form cannot be retrieved due to missing permissions on task resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed form for a given task exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to get the deployed form for. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDeployedFormWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDeployedForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/deployed-form", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/xhtml+xml", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Form Key
     * Retrieves the form key for a task. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine. This key can be used to do task-specific form rendering in client applications. Additionally, the context path of the containing process application is returned.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the form for. (required)
     * @return FormDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FormDto getForm(String id) throws RestClientException {
        return getFormWithHttpInfo(id).getBody();
    }

    /**
     * Get Form Key
     * Retrieves the form key for a task. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine. This key can be used to do task-specific form rendering in client applications. Additionally, the context path of the containing process application is returned.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the form for. (required)
     * @return ResponseEntity&lt;FormDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FormDto> getFormWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/form", uriVariables);

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

        ParameterizedTypeReference<FormDto> returnType = new ParameterizedTypeReference<FormDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Task Form Variables
     * Retrieves the form variables for a task. The form variables take form data specified on the task into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> -  id is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variables for. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getFormVariables(String id, String variableNames, Boolean deserializeValues) throws RestClientException {
        return getFormVariablesWithHttpInfo(id, variableNames, deserializeValues).getBody();
    }

    /**
     * Get Task Form Variables
     * Retrieves the form variables for a task. The form variables take form data specified on the task into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> -  id is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the variables for. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getFormVariablesWithHttpInfo(String id, String variableNames, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getFormVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/form-variables", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNames", variableNames));
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
     * Get Rendered Form
     * Retrieves the rendered form for a task. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The task with the given id does not exist or has no form field metadata defined for this task. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to get the rendered form for. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getRenderedForm(String id) throws RestClientException {
        return getRenderedFormWithHttpInfo(id).getBody();
    }

    /**
     * Get Rendered Form
     * Retrieves the rendered form for a task. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The task with the given id does not exist or has no form field metadata defined for this task. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to get the rendered form for. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getRenderedFormWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRenderedForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/rendered-form", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/xhtml+xml", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get
     * Retrieves a task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to be retrieved. (required)
     * @return TaskDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TaskDto getTask(String id) throws RestClientException {
        return getTaskWithHttpInfo(id).getBody();
    }

    /**
     * Get
     * Retrieves a task by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to be retrieved. (required)
     * @return ResponseEntity&lt;TaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TaskDto> getTaskWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}", uriVariables);

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

        ParameterizedTypeReference<TaskDto> returnType = new ParameterizedTypeReference<TaskDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for tasks that fulfill a given filter. The size of the result set can be retrieved by using the Get Task Count method.  **Security Consideration:** There are several query parameters (such as assigneeExpression) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) for custom code in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Restrict to task with the given id. (optional)
     * @param taskIdIn Restrict to tasks with any of the given ids. (optional)
     * @param processInstanceId Restrict to tasks that belong to process instances with the given id. (optional)
     * @param processInstanceIdIn Restrict to tasks that belong to process instances with the given ids. (optional)
     * @param processInstanceBusinessKey Restrict to tasks that belong to process instances with the given business key. (optional)
     * @param processInstanceBusinessKeyExpression Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param processInstanceBusinessKeyIn Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Restrict to tasks that have a process instance business key that has the parameter  value as a substring. (optional)
     * @param processInstanceBusinessKeyLikeExpression Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param processDefinitionId Restrict to tasks that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionKeyIn Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param processDefinitionNameLike Restrict to tasks that have a process definition name that has the parameter value as  a substring. (optional)
     * @param executionId Restrict to tasks that belong to an execution with the given id. (optional)
     * @param caseInstanceId Restrict to tasks that belong to case instances with the given id. (optional)
     * @param caseInstanceBusinessKey Restrict to tasks that belong to case instances with the given business key. (optional)
     * @param caseInstanceBusinessKeyLike Restrict to tasks that have a case instance business key that has the parameter value  as a substring. (optional)
     * @param caseDefinitionId Restrict to tasks that belong to a case definition with the given id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param caseDefinitionNameLike Restrict to tasks that have a case definition name that has the parameter value as a  substring. (optional)
     * @param caseExecutionId Restrict to tasks that belong to a case execution with the given id. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed and comma-separated activity  instance ids. (optional)
     * @param tenantIdIn Only include tasks which belong to one of the passed and comma-separated  tenant ids. (optional)
     * @param withoutTenantId Only include tasks which belong to no tenant. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param assignee Restrict to tasks that the given user is assigned to. (optional)
     * @param assigneeExpression Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeLike Restrict to tasks that have an assignee that has the parameter  value as a substring. (optional)
     * @param assigneeLikeExpression Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeIn Only include tasks which are assigned to one of the passed and  comma-separated user ids. (optional)
     * @param assigneeNotIn Only include tasks which are not assigned to one of the passed and comma-separated user ids. (optional)
     * @param owner Restrict to tasks that the given user owns. (optional)
     * @param ownerExpression Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateGroup Only include tasks that are offered to the given group. (optional)
     * @param candidateGroupExpression Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateUser Only include tasks that are offered to the given user or to one of his groups. (optional)
     * @param candidateUserExpression Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param includeAssignedTasks Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s). (optional, default to false)
     * @param involvedUser Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee). (optional)
     * @param involvedUserExpression Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional, default to false)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional, default to false)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list. (optional)
     * @param taskDefinitionKeyLike Restrict to tasks that have a key that has the parameter value as a substring. (optional)
     * @param name Restrict to tasks that have the given name. (optional)
     * @param nameNotEqual Restrict to tasks that do not have the given name. (optional)
     * @param nameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param nameNotLike Restrict to tasks that do not have a name with the given parameter value as substring. (optional)
     * @param description Restrict to tasks that have the given description. (optional)
     * @param descriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param priority Restrict to tasks that have the given priority. (optional)
     * @param maxPriority Restrict to tasks that have a lower or equal priority. (optional)
     * @param minPriority Restrict to tasks that have a higher or equal priority. (optional)
     * @param dueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param dueDateExpression Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.435+0200&#x60;. (optional)
     * @param dueAfterExpression Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.243+0200&#x60;. (optional)
     * @param dueBeforeExpression Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param withoutDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param followUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param followUpDateExpression Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.542+0200&#x60;. (optional)
     * @param followUpAfterExpression Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.234+0200&#x60;. (optional)
     * @param followUpBeforeExpression Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBeforeOrNotExistent Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.432+0200&#x60;. The typical use case is to query all &#x60;active&#x60; tasks for a user for a given date. (optional)
     * @param followUpBeforeOrNotExistentExpression Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdOn Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.324+0200&#x60;. (optional)
     * @param createdOnExpression Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdAfter Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param createdAfterExpression Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdBefore Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.332+0200&#x60;. (optional)
     * @param createdBeforeExpression Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param delegationState Restrict to tasks that are in the given delegation state. Valid values are &#x60;PENDING&#x60; and &#x60;RESOLVED&#x60;. (optional)
     * @param candidateGroups Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example &#x60;developers,support,sales&#x60;. (optional)
     * @param candidateGroupsExpression Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to &#x60;java.util.List&#x60; of Strings. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withCandidateUsers Only include tasks which have a candidate user. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateUsers Only include tasks which have no candidate users. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;; &#x60;notLike&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param caseInstanceVariables Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set &#x60;variableName&#x60; and &#x60;variablename&#x60; are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set &#x60;variableValue&#x60; and &#x60;variablevalue&#x60; are treated as equal. (optional, default to false)
     * @param parentTaskId Restrict query to all tasks that are sub tasks of the given task. Takes a task id. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;TaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<TaskDto> getTasks(String taskId, String taskIdIn, String processInstanceId, String processInstanceIdIn, String processInstanceBusinessKey, String processInstanceBusinessKeyExpression, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String processInstanceBusinessKeyLikeExpression, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String executionId, String caseInstanceId, String caseInstanceBusinessKey, String caseInstanceBusinessKeyLike, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String caseDefinitionNameLike, String caseExecutionId, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, String assignee, String assigneeExpression, String assigneeLike, String assigneeLikeExpression, String assigneeIn, String assigneeNotIn, String owner, String ownerExpression, String candidateGroup, String candidateGroupExpression, String candidateUser, String candidateUserExpression, Boolean includeAssignedTasks, String involvedUser, String involvedUserExpression, Boolean assigned, Boolean unassigned, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDefinitionKeyLike, String name, String nameNotEqual, String nameLike, String nameNotLike, String description, String descriptionLike, Integer priority, Integer maxPriority, Integer minPriority, String dueDate, String dueDateExpression, String dueAfter, String dueAfterExpression, String dueBefore, String dueBeforeExpression, Boolean withoutDueDate, String followUpDate, String followUpDateExpression, String followUpAfter, String followUpAfterExpression, String followUpBefore, String followUpBeforeExpression, String followUpBeforeOrNotExistent, String followUpBeforeOrNotExistentExpression, String createdOn, String createdOnExpression, String createdAfter, String createdAfterExpression, String createdBefore, String createdBeforeExpression, String delegationState, String candidateGroups, String candidateGroupsExpression, Boolean withCandidateGroups, Boolean withoutCandidateGroups, Boolean withCandidateUsers, Boolean withoutCandidateUsers, Boolean active, Boolean suspended, String taskVariables, String processVariables, String caseInstanceVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String parentTaskId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getTasksWithHttpInfo(taskId, taskIdIn, processInstanceId, processInstanceIdIn, processInstanceBusinessKey, processInstanceBusinessKeyExpression, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, processInstanceBusinessKeyLikeExpression, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, executionId, caseInstanceId, caseInstanceBusinessKey, caseInstanceBusinessKeyLike, caseDefinitionId, caseDefinitionKey, caseDefinitionName, caseDefinitionNameLike, caseExecutionId, activityInstanceIdIn, tenantIdIn, withoutTenantId, assignee, assigneeExpression, assigneeLike, assigneeLikeExpression, assigneeIn, assigneeNotIn, owner, ownerExpression, candidateGroup, candidateGroupExpression, candidateUser, candidateUserExpression, includeAssignedTasks, involvedUser, involvedUserExpression, assigned, unassigned, taskDefinitionKey, taskDefinitionKeyIn, taskDefinitionKeyLike, name, nameNotEqual, nameLike, nameNotLike, description, descriptionLike, priority, maxPriority, minPriority, dueDate, dueDateExpression, dueAfter, dueAfterExpression, dueBefore, dueBeforeExpression, withoutDueDate, followUpDate, followUpDateExpression, followUpAfter, followUpAfterExpression, followUpBefore, followUpBeforeExpression, followUpBeforeOrNotExistent, followUpBeforeOrNotExistentExpression, createdOn, createdOnExpression, createdAfter, createdAfterExpression, createdBefore, createdBeforeExpression, delegationState, candidateGroups, candidateGroupsExpression, withCandidateGroups, withoutCandidateGroups, withCandidateUsers, withoutCandidateUsers, active, suspended, taskVariables, processVariables, caseInstanceVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, parentTaskId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for tasks that fulfill a given filter. The size of the result set can be retrieved by using the Get Task Count method.  **Security Consideration:** There are several query parameters (such as assigneeExpression) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) for custom code in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Restrict to task with the given id. (optional)
     * @param taskIdIn Restrict to tasks with any of the given ids. (optional)
     * @param processInstanceId Restrict to tasks that belong to process instances with the given id. (optional)
     * @param processInstanceIdIn Restrict to tasks that belong to process instances with the given ids. (optional)
     * @param processInstanceBusinessKey Restrict to tasks that belong to process instances with the given business key. (optional)
     * @param processInstanceBusinessKeyExpression Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param processInstanceBusinessKeyIn Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Restrict to tasks that have a process instance business key that has the parameter  value as a substring. (optional)
     * @param processInstanceBusinessKeyLikeExpression Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param processDefinitionId Restrict to tasks that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionKeyIn Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param processDefinitionNameLike Restrict to tasks that have a process definition name that has the parameter value as  a substring. (optional)
     * @param executionId Restrict to tasks that belong to an execution with the given id. (optional)
     * @param caseInstanceId Restrict to tasks that belong to case instances with the given id. (optional)
     * @param caseInstanceBusinessKey Restrict to tasks that belong to case instances with the given business key. (optional)
     * @param caseInstanceBusinessKeyLike Restrict to tasks that have a case instance business key that has the parameter value  as a substring. (optional)
     * @param caseDefinitionId Restrict to tasks that belong to a case definition with the given id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param caseDefinitionNameLike Restrict to tasks that have a case definition name that has the parameter value as a  substring. (optional)
     * @param caseExecutionId Restrict to tasks that belong to a case execution with the given id. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed and comma-separated activity  instance ids. (optional)
     * @param tenantIdIn Only include tasks which belong to one of the passed and comma-separated  tenant ids. (optional)
     * @param withoutTenantId Only include tasks which belong to no tenant. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param assignee Restrict to tasks that the given user is assigned to. (optional)
     * @param assigneeExpression Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeLike Restrict to tasks that have an assignee that has the parameter  value as a substring. (optional)
     * @param assigneeLikeExpression Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeIn Only include tasks which are assigned to one of the passed and  comma-separated user ids. (optional)
     * @param assigneeNotIn Only include tasks which are not assigned to one of the passed and comma-separated user ids. (optional)
     * @param owner Restrict to tasks that the given user owns. (optional)
     * @param ownerExpression Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateGroup Only include tasks that are offered to the given group. (optional)
     * @param candidateGroupExpression Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateUser Only include tasks that are offered to the given user or to one of his groups. (optional)
     * @param candidateUserExpression Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param includeAssignedTasks Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s). (optional, default to false)
     * @param involvedUser Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee). (optional)
     * @param involvedUserExpression Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional, default to false)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional, default to false)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list. (optional)
     * @param taskDefinitionKeyLike Restrict to tasks that have a key that has the parameter value as a substring. (optional)
     * @param name Restrict to tasks that have the given name. (optional)
     * @param nameNotEqual Restrict to tasks that do not have the given name. (optional)
     * @param nameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param nameNotLike Restrict to tasks that do not have a name with the given parameter value as substring. (optional)
     * @param description Restrict to tasks that have the given description. (optional)
     * @param descriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param priority Restrict to tasks that have the given priority. (optional)
     * @param maxPriority Restrict to tasks that have a lower or equal priority. (optional)
     * @param minPriority Restrict to tasks that have a higher or equal priority. (optional)
     * @param dueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param dueDateExpression Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.435+0200&#x60;. (optional)
     * @param dueAfterExpression Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.243+0200&#x60;. (optional)
     * @param dueBeforeExpression Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param withoutDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param followUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param followUpDateExpression Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.542+0200&#x60;. (optional)
     * @param followUpAfterExpression Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.234+0200&#x60;. (optional)
     * @param followUpBeforeExpression Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBeforeOrNotExistent Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.432+0200&#x60;. The typical use case is to query all &#x60;active&#x60; tasks for a user for a given date. (optional)
     * @param followUpBeforeOrNotExistentExpression Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdOn Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.324+0200&#x60;. (optional)
     * @param createdOnExpression Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdAfter Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param createdAfterExpression Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdBefore Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.332+0200&#x60;. (optional)
     * @param createdBeforeExpression Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param delegationState Restrict to tasks that are in the given delegation state. Valid values are &#x60;PENDING&#x60; and &#x60;RESOLVED&#x60;. (optional)
     * @param candidateGroups Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example &#x60;developers,support,sales&#x60;. (optional)
     * @param candidateGroupsExpression Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to &#x60;java.util.List&#x60; of Strings. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withCandidateUsers Only include tasks which have a candidate user. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateUsers Only include tasks which have no candidate users. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;; &#x60;notLike&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param caseInstanceVariables Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set &#x60;variableName&#x60; and &#x60;variablename&#x60; are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set &#x60;variableValue&#x60; and &#x60;variablevalue&#x60; are treated as equal. (optional, default to false)
     * @param parentTaskId Restrict query to all tasks that are sub tasks of the given task. Takes a task id. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;TaskDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<TaskDto>> getTasksWithHttpInfo(String taskId, String taskIdIn, String processInstanceId, String processInstanceIdIn, String processInstanceBusinessKey, String processInstanceBusinessKeyExpression, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String processInstanceBusinessKeyLikeExpression, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String executionId, String caseInstanceId, String caseInstanceBusinessKey, String caseInstanceBusinessKeyLike, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String caseDefinitionNameLike, String caseExecutionId, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, String assignee, String assigneeExpression, String assigneeLike, String assigneeLikeExpression, String assigneeIn, String assigneeNotIn, String owner, String ownerExpression, String candidateGroup, String candidateGroupExpression, String candidateUser, String candidateUserExpression, Boolean includeAssignedTasks, String involvedUser, String involvedUserExpression, Boolean assigned, Boolean unassigned, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDefinitionKeyLike, String name, String nameNotEqual, String nameLike, String nameNotLike, String description, String descriptionLike, Integer priority, Integer maxPriority, Integer minPriority, String dueDate, String dueDateExpression, String dueAfter, String dueAfterExpression, String dueBefore, String dueBeforeExpression, Boolean withoutDueDate, String followUpDate, String followUpDateExpression, String followUpAfter, String followUpAfterExpression, String followUpBefore, String followUpBeforeExpression, String followUpBeforeOrNotExistent, String followUpBeforeOrNotExistentExpression, String createdOn, String createdOnExpression, String createdAfter, String createdAfterExpression, String createdBefore, String createdBeforeExpression, String delegationState, String candidateGroups, String candidateGroupsExpression, Boolean withCandidateGroups, Boolean withoutCandidateGroups, Boolean withCandidateUsers, Boolean withoutCandidateUsers, Boolean active, Boolean suspended, String taskVariables, String processVariables, String caseInstanceVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String parentTaskId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/task", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskIdIn", taskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKey", processInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyExpression", processInstanceBusinessKeyExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyIn", processInstanceBusinessKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLike", processInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLikeExpression", processInstanceBusinessKeyLikeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionName", processDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionNameLike", processDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceBusinessKey", caseInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceBusinessKeyLike", caseInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionKey", caseDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionName", caseDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionNameLike", caseDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assignee", assignee));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeExpression", assigneeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeLike", assigneeLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeLikeExpression", assigneeLikeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeIn", assigneeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeNotIn", assigneeNotIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "owner", owner));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "ownerExpression", ownerExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroup", candidateGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroupExpression", candidateGroupExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateUser", candidateUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateUserExpression", candidateUserExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeAssignedTasks", includeAssignedTasks));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "involvedUser", involvedUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "involvedUserExpression", involvedUserExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigned", assigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unassigned", unassigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKey", taskDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKeyIn", taskDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKeyLike", taskDefinitionKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameNotEqual", nameNotEqual));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameNotLike", nameNotLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "description", description));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "descriptionLike", descriptionLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priority", priority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxPriority", maxPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "minPriority", minPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueDate", dueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueDateExpression", dueDateExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueAfter", dueAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueAfterExpression", dueAfterExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueBefore", dueBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueBeforeExpression", dueBeforeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutDueDate", withoutDueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpDate", followUpDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpDateExpression", followUpDateExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpAfter", followUpAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpAfterExpression", followUpAfterExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBefore", followUpBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBeforeExpression", followUpBeforeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBeforeOrNotExistent", followUpBeforeOrNotExistent));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBeforeOrNotExistentExpression", followUpBeforeOrNotExistentExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdOn", createdOn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdOnExpression", createdOnExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdAfter", createdAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdAfterExpression", createdAfterExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdBefore", createdBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdBeforeExpression", createdBeforeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "delegationState", delegationState));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroups", candidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroupsExpression", candidateGroupsExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCandidateGroups", withCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutCandidateGroups", withoutCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCandidateUsers", withCandidateUsers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutCandidateUsers", withoutCandidateUsers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskVariables", taskVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processVariables", processVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceVariables", caseInstanceVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "parentTaskId", parentTaskId));
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

        ParameterizedTypeReference<List<TaskDto>> returnType = new ParameterizedTypeReference<List<TaskDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Retrieves the number of tasks that fulfill a provided filter. Corresponds to the size of the result set when using the [Get Tasks](https://docs.camunda.org/manual/7.16/reference/rest/task/) method.  **Security Consideration:** There are several query parameters (such as assigneeExpression) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) for custom code in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Restrict to task with the given id. (optional)
     * @param taskIdIn Restrict to tasks with any of the given ids. (optional)
     * @param processInstanceId Restrict to tasks that belong to process instances with the given id. (optional)
     * @param processInstanceIdIn Restrict to tasks that belong to process instances with the given ids. (optional)
     * @param processInstanceBusinessKey Restrict to tasks that belong to process instances with the given business key. (optional)
     * @param processInstanceBusinessKeyExpression Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param processInstanceBusinessKeyIn Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Restrict to tasks that have a process instance business key that has the parameter  value as a substring. (optional)
     * @param processInstanceBusinessKeyLikeExpression Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param processDefinitionId Restrict to tasks that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionKeyIn Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param processDefinitionNameLike Restrict to tasks that have a process definition name that has the parameter value as  a substring. (optional)
     * @param executionId Restrict to tasks that belong to an execution with the given id. (optional)
     * @param caseInstanceId Restrict to tasks that belong to case instances with the given id. (optional)
     * @param caseInstanceBusinessKey Restrict to tasks that belong to case instances with the given business key. (optional)
     * @param caseInstanceBusinessKeyLike Restrict to tasks that have a case instance business key that has the parameter value  as a substring. (optional)
     * @param caseDefinitionId Restrict to tasks that belong to a case definition with the given id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param caseDefinitionNameLike Restrict to tasks that have a case definition name that has the parameter value as a  substring. (optional)
     * @param caseExecutionId Restrict to tasks that belong to a case execution with the given id. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed and comma-separated activity  instance ids. (optional)
     * @param tenantIdIn Only include tasks which belong to one of the passed and comma-separated  tenant ids. (optional)
     * @param withoutTenantId Only include tasks which belong to no tenant. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param assignee Restrict to tasks that the given user is assigned to. (optional)
     * @param assigneeExpression Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeLike Restrict to tasks that have an assignee that has the parameter  value as a substring. (optional)
     * @param assigneeLikeExpression Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeIn Only include tasks which are assigned to one of the passed and  comma-separated user ids. (optional)
     * @param assigneeNotIn Only include tasks which are not assigned to one of the passed and comma-separated user ids. (optional)
     * @param owner Restrict to tasks that the given user owns. (optional)
     * @param ownerExpression Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateGroup Only include tasks that are offered to the given group. (optional)
     * @param candidateGroupExpression Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateUser Only include tasks that are offered to the given user or to one of his groups. (optional)
     * @param candidateUserExpression Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param includeAssignedTasks Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s). (optional, default to false)
     * @param involvedUser Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee). (optional)
     * @param involvedUserExpression Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional, default to false)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional, default to false)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list. (optional)
     * @param taskDefinitionKeyLike Restrict to tasks that have a key that has the parameter value as a substring. (optional)
     * @param name Restrict to tasks that have the given name. (optional)
     * @param nameNotEqual Restrict to tasks that do not have the given name. (optional)
     * @param nameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param nameNotLike Restrict to tasks that do not have a name with the given parameter value as substring. (optional)
     * @param description Restrict to tasks that have the given description. (optional)
     * @param descriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param priority Restrict to tasks that have the given priority. (optional)
     * @param maxPriority Restrict to tasks that have a lower or equal priority. (optional)
     * @param minPriority Restrict to tasks that have a higher or equal priority. (optional)
     * @param dueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param dueDateExpression Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.435+0200&#x60;. (optional)
     * @param dueAfterExpression Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.243+0200&#x60;. (optional)
     * @param dueBeforeExpression Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param withoutDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param followUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param followUpDateExpression Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.542+0200&#x60;. (optional)
     * @param followUpAfterExpression Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.234+0200&#x60;. (optional)
     * @param followUpBeforeExpression Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBeforeOrNotExistent Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.432+0200&#x60;. The typical use case is to query all &#x60;active&#x60; tasks for a user for a given date. (optional)
     * @param followUpBeforeOrNotExistentExpression Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdOn Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.324+0200&#x60;. (optional)
     * @param createdOnExpression Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdAfter Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param createdAfterExpression Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdBefore Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.332+0200&#x60;. (optional)
     * @param createdBeforeExpression Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param delegationState Restrict to tasks that are in the given delegation state. Valid values are &#x60;PENDING&#x60; and &#x60;RESOLVED&#x60;. (optional)
     * @param candidateGroups Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example &#x60;developers,support,sales&#x60;. (optional)
     * @param candidateGroupsExpression Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to &#x60;java.util.List&#x60; of Strings. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withCandidateUsers Only include tasks which have a candidate user. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateUsers Only include tasks which have no candidate users. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;; &#x60;notLike&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param caseInstanceVariables Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set &#x60;variableName&#x60; and &#x60;variablename&#x60; are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set &#x60;variableValue&#x60; and &#x60;variablevalue&#x60; are treated as equal. (optional, default to false)
     * @param parentTaskId Restrict query to all tasks that are sub tasks of the given task. Takes a task id. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getTasksCount(String taskId, String taskIdIn, String processInstanceId, String processInstanceIdIn, String processInstanceBusinessKey, String processInstanceBusinessKeyExpression, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String processInstanceBusinessKeyLikeExpression, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String executionId, String caseInstanceId, String caseInstanceBusinessKey, String caseInstanceBusinessKeyLike, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String caseDefinitionNameLike, String caseExecutionId, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, String assignee, String assigneeExpression, String assigneeLike, String assigneeLikeExpression, String assigneeIn, String assigneeNotIn, String owner, String ownerExpression, String candidateGroup, String candidateGroupExpression, String candidateUser, String candidateUserExpression, Boolean includeAssignedTasks, String involvedUser, String involvedUserExpression, Boolean assigned, Boolean unassigned, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDefinitionKeyLike, String name, String nameNotEqual, String nameLike, String nameNotLike, String description, String descriptionLike, Integer priority, Integer maxPriority, Integer minPriority, String dueDate, String dueDateExpression, String dueAfter, String dueAfterExpression, String dueBefore, String dueBeforeExpression, Boolean withoutDueDate, String followUpDate, String followUpDateExpression, String followUpAfter, String followUpAfterExpression, String followUpBefore, String followUpBeforeExpression, String followUpBeforeOrNotExistent, String followUpBeforeOrNotExistentExpression, String createdOn, String createdOnExpression, String createdAfter, String createdAfterExpression, String createdBefore, String createdBeforeExpression, String delegationState, String candidateGroups, String candidateGroupsExpression, Boolean withCandidateGroups, Boolean withoutCandidateGroups, Boolean withCandidateUsers, Boolean withoutCandidateUsers, Boolean active, Boolean suspended, String taskVariables, String processVariables, String caseInstanceVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String parentTaskId) throws RestClientException {
        return getTasksCountWithHttpInfo(taskId, taskIdIn, processInstanceId, processInstanceIdIn, processInstanceBusinessKey, processInstanceBusinessKeyExpression, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, processInstanceBusinessKeyLikeExpression, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, executionId, caseInstanceId, caseInstanceBusinessKey, caseInstanceBusinessKeyLike, caseDefinitionId, caseDefinitionKey, caseDefinitionName, caseDefinitionNameLike, caseExecutionId, activityInstanceIdIn, tenantIdIn, withoutTenantId, assignee, assigneeExpression, assigneeLike, assigneeLikeExpression, assigneeIn, assigneeNotIn, owner, ownerExpression, candidateGroup, candidateGroupExpression, candidateUser, candidateUserExpression, includeAssignedTasks, involvedUser, involvedUserExpression, assigned, unassigned, taskDefinitionKey, taskDefinitionKeyIn, taskDefinitionKeyLike, name, nameNotEqual, nameLike, nameNotLike, description, descriptionLike, priority, maxPriority, minPriority, dueDate, dueDateExpression, dueAfter, dueAfterExpression, dueBefore, dueBeforeExpression, withoutDueDate, followUpDate, followUpDateExpression, followUpAfter, followUpAfterExpression, followUpBefore, followUpBeforeExpression, followUpBeforeOrNotExistent, followUpBeforeOrNotExistentExpression, createdOn, createdOnExpression, createdAfter, createdAfterExpression, createdBefore, createdBeforeExpression, delegationState, candidateGroups, candidateGroupsExpression, withCandidateGroups, withoutCandidateGroups, withCandidateUsers, withoutCandidateUsers, active, suspended, taskVariables, processVariables, caseInstanceVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, parentTaskId).getBody();
    }

    /**
     * Get List Count
     * Retrieves the number of tasks that fulfill a provided filter. Corresponds to the size of the result set when using the [Get Tasks](https://docs.camunda.org/manual/7.16/reference/rest/task/) method.  **Security Consideration:** There are several query parameters (such as assigneeExpression) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) for custom code in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Restrict to task with the given id. (optional)
     * @param taskIdIn Restrict to tasks with any of the given ids. (optional)
     * @param processInstanceId Restrict to tasks that belong to process instances with the given id. (optional)
     * @param processInstanceIdIn Restrict to tasks that belong to process instances with the given ids. (optional)
     * @param processInstanceBusinessKey Restrict to tasks that belong to process instances with the given business key. (optional)
     * @param processInstanceBusinessKeyExpression Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param processInstanceBusinessKeyIn Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Restrict to tasks that have a process instance business key that has the parameter  value as a substring. (optional)
     * @param processInstanceBusinessKeyLikeExpression Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param processDefinitionId Restrict to tasks that belong to a process definition with the given id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionKeyIn Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param processDefinitionNameLike Restrict to tasks that have a process definition name that has the parameter value as  a substring. (optional)
     * @param executionId Restrict to tasks that belong to an execution with the given id. (optional)
     * @param caseInstanceId Restrict to tasks that belong to case instances with the given id. (optional)
     * @param caseInstanceBusinessKey Restrict to tasks that belong to case instances with the given business key. (optional)
     * @param caseInstanceBusinessKeyLike Restrict to tasks that have a case instance business key that has the parameter value  as a substring. (optional)
     * @param caseDefinitionId Restrict to tasks that belong to a case definition with the given id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param caseDefinitionNameLike Restrict to tasks that have a case definition name that has the parameter value as a  substring. (optional)
     * @param caseExecutionId Restrict to tasks that belong to a case execution with the given id. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed and comma-separated activity  instance ids. (optional)
     * @param tenantIdIn Only include tasks which belong to one of the passed and comma-separated  tenant ids. (optional)
     * @param withoutTenantId Only include tasks which belong to no tenant. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param assignee Restrict to tasks that the given user is assigned to. (optional)
     * @param assigneeExpression Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeLike Restrict to tasks that have an assignee that has the parameter  value as a substring. (optional)
     * @param assigneeLikeExpression Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param assigneeIn Only include tasks which are assigned to one of the passed and  comma-separated user ids. (optional)
     * @param assigneeNotIn Only include tasks which are not assigned to one of the passed and comma-separated user ids. (optional)
     * @param owner Restrict to tasks that the given user owns. (optional)
     * @param ownerExpression Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateGroup Only include tasks that are offered to the given group. (optional)
     * @param candidateGroupExpression Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param candidateUser Only include tasks that are offered to the given user or to one of his groups. (optional)
     * @param candidateUserExpression Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. (optional)
     * @param includeAssignedTasks Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s). (optional, default to false)
     * @param involvedUser Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee). (optional)
     * @param involvedUserExpression Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional, default to false)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional, default to false)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list. (optional)
     * @param taskDefinitionKeyLike Restrict to tasks that have a key that has the parameter value as a substring. (optional)
     * @param name Restrict to tasks that have the given name. (optional)
     * @param nameNotEqual Restrict to tasks that do not have the given name. (optional)
     * @param nameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param nameNotLike Restrict to tasks that do not have a name with the given parameter value as substring. (optional)
     * @param description Restrict to tasks that have the given description. (optional)
     * @param descriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param priority Restrict to tasks that have the given priority. (optional)
     * @param maxPriority Restrict to tasks that have a lower or equal priority. (optional)
     * @param minPriority Restrict to tasks that have a higher or equal priority. (optional)
     * @param dueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param dueDateExpression Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.435+0200&#x60;. (optional)
     * @param dueAfterExpression Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param dueBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.243+0200&#x60;. (optional)
     * @param dueBeforeExpression Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param withoutDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param followUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param followUpDateExpression Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.542+0200&#x60;. (optional)
     * @param followUpAfterExpression Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.234+0200&#x60;. (optional)
     * @param followUpBeforeExpression Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param followUpBeforeOrNotExistent Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.432+0200&#x60;. The typical use case is to query all &#x60;active&#x60; tasks for a user for a given date. (optional)
     * @param followUpBeforeOrNotExistentExpression Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdOn Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.324+0200&#x60;. (optional)
     * @param createdOnExpression Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdAfter Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. (optional)
     * @param createdAfterExpression Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param createdBefore Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.332+0200&#x60;. (optional)
     * @param createdBeforeExpression Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. (optional)
     * @param delegationState Restrict to tasks that are in the given delegation state. Valid values are &#x60;PENDING&#x60; and &#x60;RESOLVED&#x60;. (optional)
     * @param candidateGroups Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example &#x60;developers,support,sales&#x60;. (optional)
     * @param candidateGroupsExpression Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to &#x60;java.util.List&#x60; of Strings. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withCandidateUsers Only include tasks which have a candidate user. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param withoutCandidateUsers Only include tasks which have no candidate users. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param active Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param suspended Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;; &#x60;notLike&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param caseInstanceVariables Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set &#x60;variableName&#x60; and &#x60;variablename&#x60; are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set &#x60;variableValue&#x60; and &#x60;variablevalue&#x60; are treated as equal. (optional, default to false)
     * @param parentTaskId Restrict query to all tasks that are sub tasks of the given task. Takes a task id. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getTasksCountWithHttpInfo(String taskId, String taskIdIn, String processInstanceId, String processInstanceIdIn, String processInstanceBusinessKey, String processInstanceBusinessKeyExpression, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String processInstanceBusinessKeyLikeExpression, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionName, String processDefinitionNameLike, String executionId, String caseInstanceId, String caseInstanceBusinessKey, String caseInstanceBusinessKeyLike, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String caseDefinitionNameLike, String caseExecutionId, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, String assignee, String assigneeExpression, String assigneeLike, String assigneeLikeExpression, String assigneeIn, String assigneeNotIn, String owner, String ownerExpression, String candidateGroup, String candidateGroupExpression, String candidateUser, String candidateUserExpression, Boolean includeAssignedTasks, String involvedUser, String involvedUserExpression, Boolean assigned, Boolean unassigned, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDefinitionKeyLike, String name, String nameNotEqual, String nameLike, String nameNotLike, String description, String descriptionLike, Integer priority, Integer maxPriority, Integer minPriority, String dueDate, String dueDateExpression, String dueAfter, String dueAfterExpression, String dueBefore, String dueBeforeExpression, Boolean withoutDueDate, String followUpDate, String followUpDateExpression, String followUpAfter, String followUpAfterExpression, String followUpBefore, String followUpBeforeExpression, String followUpBeforeOrNotExistent, String followUpBeforeOrNotExistentExpression, String createdOn, String createdOnExpression, String createdAfter, String createdAfterExpression, String createdBefore, String createdBeforeExpression, String delegationState, String candidateGroups, String candidateGroupsExpression, Boolean withCandidateGroups, Boolean withoutCandidateGroups, Boolean withCandidateUsers, Boolean withoutCandidateUsers, Boolean active, Boolean suspended, String taskVariables, String processVariables, String caseInstanceVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String parentTaskId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/task/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskIdIn", taskIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIdIn", processInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKey", processInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyExpression", processInstanceBusinessKeyExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyIn", processInstanceBusinessKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLike", processInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLikeExpression", processInstanceBusinessKeyLikeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionName", processDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionNameLike", processDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceBusinessKey", caseInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceBusinessKeyLike", caseInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionKey", caseDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionName", caseDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionNameLike", caseDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assignee", assignee));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeExpression", assigneeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeLike", assigneeLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeLikeExpression", assigneeLikeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeIn", assigneeIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigneeNotIn", assigneeNotIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "owner", owner));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "ownerExpression", ownerExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroup", candidateGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroupExpression", candidateGroupExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateUser", candidateUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateUserExpression", candidateUserExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeAssignedTasks", includeAssignedTasks));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "involvedUser", involvedUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "involvedUserExpression", involvedUserExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigned", assigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unassigned", unassigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKey", taskDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKeyIn", taskDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKeyLike", taskDefinitionKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameNotEqual", nameNotEqual));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameNotLike", nameNotLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "description", description));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "descriptionLike", descriptionLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priority", priority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxPriority", maxPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "minPriority", minPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueDate", dueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueDateExpression", dueDateExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueAfter", dueAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueAfterExpression", dueAfterExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueBefore", dueBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueBeforeExpression", dueBeforeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutDueDate", withoutDueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpDate", followUpDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpDateExpression", followUpDateExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpAfter", followUpAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpAfterExpression", followUpAfterExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBefore", followUpBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBeforeExpression", followUpBeforeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBeforeOrNotExistent", followUpBeforeOrNotExistent));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "followUpBeforeOrNotExistentExpression", followUpBeforeOrNotExistentExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdOn", createdOn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdOnExpression", createdOnExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdAfter", createdAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdAfterExpression", createdAfterExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdBefore", createdBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createdBeforeExpression", createdBeforeExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "delegationState", delegationState));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroups", candidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "candidateGroupsExpression", candidateGroupsExpression));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCandidateGroups", withCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutCandidateGroups", withoutCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCandidateUsers", withCandidateUsers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutCandidateUsers", withoutCandidateUsers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskVariables", taskVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processVariables", processVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceVariables", caseInstanceVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "parentTaskId", parentTaskId));

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
     * Handle BPMN Error
     * Reports a business error in the context of a running task by id. The error code must be specified to identify the BPMN error handler. See the documentation for [Reporting Bpmn Error](https://docs.camunda.org/manual/7.16/reference/bpmn20/tasks/user-task/#reporting-bpmn-error) in User Tasks.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the &#x60;errorCode&#x60; or &#x60;id&#x60; are not present in the request. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - If the authenticated user is unauthorized to update the task. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task a BPMN error is reported for. (required)
     * @param taskBpmnErrorDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void handleBpmnError(String id, TaskBpmnErrorDto taskBpmnErrorDto) throws RestClientException {
        handleBpmnErrorWithHttpInfo(id, taskBpmnErrorDto);
    }

    /**
     * Handle BPMN Error
     * Reports a business error in the context of a running task by id. The error code must be specified to identify the BPMN error handler. See the documentation for [Reporting Bpmn Error](https://docs.camunda.org/manual/7.16/reference/bpmn20/tasks/user-task/#reporting-bpmn-error) in User Tasks.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the &#x60;errorCode&#x60; or &#x60;id&#x60; are not present in the request. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - If the authenticated user is unauthorized to update the task. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task a BPMN error is reported for. (required)
     * @param taskBpmnErrorDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> handleBpmnErrorWithHttpInfo(String id, TaskBpmnErrorDto taskBpmnErrorDto) throws RestClientException {
        Object postBody = taskBpmnErrorDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling handleBpmnError");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/bpmnError", uriVariables);

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
     * Handle BPMN Escalation
     * Reports an escalation in the context of a running task by id. The escalation code must be specified to identify the escalation handler. See the documentation for [Reporting Bpmn Escalation](https://docs.camunda.org/manual/7.16/reference/bpmn20/tasks/user-task/#reporting-bpmn-escalation) in User Tasks.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the &lt;code&gt;escalationCode&lt;/code&gt; is not provided in the request. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - If the authenticated user is unauthorized to update the process instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task in which context a BPMN escalation is reported. (required)
     * @param taskEscalationDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void handleEscalation(String id, TaskEscalationDto taskEscalationDto) throws RestClientException {
        handleEscalationWithHttpInfo(id, taskEscalationDto);
    }

    /**
     * Handle BPMN Escalation
     * Reports an escalation in the context of a running task by id. The escalation code must be specified to identify the escalation handler. See the documentation for [Reporting Bpmn Escalation](https://docs.camunda.org/manual/7.16/reference/bpmn20/tasks/user-task/#reporting-bpmn-escalation) in User Tasks.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if the &lt;code&gt;escalationCode&lt;/code&gt; is not provided in the request. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - If the authenticated user is unauthorized to update the process instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Returned if the task does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task in which context a BPMN escalation is reported. (required)
     * @param taskEscalationDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> handleEscalationWithHttpInfo(String id, TaskEscalationDto taskEscalationDto) throws RestClientException {
        Object postBody = taskEscalationDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling handleEscalation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/bpmnEscalation", uriVariables);

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
     * Get List (POST)
     * Queries for tasks that fulfill a given filter. This method is slightly more powerful than the [Get Tasks](https://docs.camunda.org/manual/7.16/reference/rest/task/get-query/) method because it allows filtering by multiple process or task variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;. The size of the result set can be retrieved by using the [Get Task Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/task/post-query-count/) method.  **Security Consideration**: There are several parameters (such as &#x60;assigneeExpression&#x60;) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param taskQueryDto  (optional)
     * @return List&lt;TaskDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<TaskDto> queryTasks(Integer firstResult, Integer maxResults, TaskQueryDto taskQueryDto) throws RestClientException {
        return queryTasksWithHttpInfo(firstResult, maxResults, taskQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for tasks that fulfill a given filter. This method is slightly more powerful than the [Get Tasks](https://docs.camunda.org/manual/7.16/reference/rest/task/get-query/) method because it allows filtering by multiple process or task variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;. The size of the result set can be retrieved by using the [Get Task Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/task/post-query-count/) method.  **Security Consideration**: There are several parameters (such as &#x60;assigneeExpression&#x60;) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param taskQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;TaskDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<TaskDto>> queryTasksWithHttpInfo(Integer firstResult, Integer maxResults, TaskQueryDto taskQueryDto) throws RestClientException {
        Object postBody = taskQueryDto;
        
        String path = apiClient.expandPath("/task", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<TaskDto>> returnType = new ParameterizedTypeReference<List<TaskDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count (POST)
     * Retrieves the number of tasks that fulfill the given filter. Corresponds to the size of the result set of the [Get Tasks (POST)](https://docs.camunda.org/manual/7.16/reference/rest/task/post-query/) method and takes the same parameters.  **Security Consideration**: There are several parameters (such as &#x60;assigneeExpression&#x60;) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryTasksCount(TaskQueryDto taskQueryDto) throws RestClientException {
        return queryTasksCountWithHttpInfo(taskQueryDto).getBody();
    }

    /**
     * Get List Count (POST)
     * Retrieves the number of tasks that fulfill the given filter. Corresponds to the size of the result set of the [Get Tasks (POST)](https://docs.camunda.org/manual/7.16/reference/rest/task/post-query/) method and takes the same parameters.  **Security Consideration**: There are several parameters (such as &#x60;assigneeExpression&#x60;) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryTasksCountWithHttpInfo(TaskQueryDto taskQueryDto) throws RestClientException {
        Object postBody = taskQueryDto;
        
        String path = apiClient.expandPath("/task/count", Collections.<String, Object>emptyMap());

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
     * Resolve
     * Resolves a task and updates execution variables.  Resolving a task marks that the assignee is done with the task delegated to them, and that it can be sent back to the owner. Can only be executed when the task has been delegated. The assignee will be set to the owner, who performed the delegation.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - If the task does not exist or the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to resolve. (required)
     * @param completeTaskDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void resolve(String id, CompleteTaskDto completeTaskDto) throws RestClientException {
        resolveWithHttpInfo(id, completeTaskDto);
    }

    /**
     * Resolve
     * Resolves a task and updates execution variables.  Resolving a task marks that the assignee is done with the task delegated to them, and that it can be sent back to the owner. Can only be executed when the task has been delegated. The assignee will be set to the owner, who performed the delegation.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - If the task does not exist or the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to resolve. (required)
     * @param completeTaskDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> resolveWithHttpInfo(String id, CompleteTaskDto completeTaskDto) throws RestClientException {
        Object postBody = completeTaskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling resolve");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/resolve", uriVariables);

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
     * Set Assignee
     * Changes the assignee of a task to a specific user.  **Note:** The difference with the [Claim Task](https://docs.camunda.org/manual/7.16/reference/rest/task/post-claim/) method is that this method does not check if the task already has a user assigned to it.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - Task with given id does not exist or setting the assignee was not successful. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to set the assignee for. (required)
     * @param userIdDto Provide the id of the user that will be the assignee of the task. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setAssignee(String id, UserIdDto userIdDto) throws RestClientException {
        setAssigneeWithHttpInfo(id, userIdDto);
    }

    /**
     * Set Assignee
     * Changes the assignee of a task to a specific user.  **Note:** The difference with the [Claim Task](https://docs.camunda.org/manual/7.16/reference/rest/task/post-claim/) method is that this method does not check if the task already has a user assigned to it.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - Task with given id does not exist or setting the assignee was not successful. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to set the assignee for. (required)
     * @param userIdDto Provide the id of the user that will be the assignee of the task. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setAssigneeWithHttpInfo(String id, UserIdDto userIdDto) throws RestClientException {
        Object postBody = userIdDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setAssignee");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/assignee", uriVariables);

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
     * Submit Form
     * Completes a task and updates process variables using a form submit. There are two difference between this method and the &#x60;complete&#x60; method:  * If the task is in state &#x60;PENDING&#x60; - i.e., has been delegated before, it is not completed but resolved. Otherwise it will be completed. * If the task has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See the [Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/_index/#generated-task-forms) section of the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/) for more information.
     * <p><b>200</b> - Request successful. The response contains the process variables.
     * <p><b>204</b> - Request successful. The response contains no variables.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - If the task does not exist or the corresponding process instance could not be resumed successfully.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to submit the form for. (required)
     * @param completeTaskDto  (optional)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> submit(String id, CompleteTaskDto completeTaskDto) throws RestClientException {
        return submitWithHttpInfo(id, completeTaskDto).getBody();
    }

    /**
     * Submit Form
     * Completes a task and updates process variables using a form submit. There are two difference between this method and the &#x60;complete&#x60; method:  * If the task is in state &#x60;PENDING&#x60; - i.e., has been delegated before, it is not completed but resolved. Otherwise it will be completed. * If the task has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See the [Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/_index/#generated-task-forms) section of the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/) for more information.
     * <p><b>200</b> - Request successful. The response contains the process variables.
     * <p><b>204</b> - Request successful. The response contains no variables.
     * <p><b>400</b> - The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - If the task does not exist or the corresponding process instance could not be resumed successfully.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to submit the form for. (required)
     * @param completeTaskDto  (optional)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> submitWithHttpInfo(String id, CompleteTaskDto completeTaskDto) throws RestClientException {
        Object postBody = completeTaskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling submit");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/submit-form", uriVariables);

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

        ParameterizedTypeReference<Map<String, VariableValueDto>> returnType = new ParameterizedTypeReference<Map<String, VariableValueDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Unclaim
     * Resets a task&#39;s assignee. If successful, the task is not assigned to a user.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - The Task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to unclaim. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unclaim(String id) throws RestClientException {
        unclaimWithHttpInfo(id);
    }

    /**
     * Unclaim
     * Resets a task&#39;s assignee. If successful, the task is not assigned to a user.
     * <p><b>204</b> - Request successful.
     * <p><b>500</b> - The Task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to unclaim. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unclaimWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling unclaim");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/unclaim", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Update
     * Updates a task.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if a not valid &#x60;delegationState&#x60; is supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - If the corresponding task cannot be found.
     * @param id The id of the task to be updated. (required)
     * @param taskDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateTask(String id, TaskDto taskDto) throws RestClientException {
        updateTaskWithHttpInfo(id, taskDto);
    }

    /**
     * Update
     * Updates a task.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if a not valid &#x60;delegationState&#x60; is supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - If the corresponding task cannot be found.
     * @param id The id of the task to be updated. (required)
     * @param taskDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateTaskWithHttpInfo(String id, TaskDto taskDto) throws RestClientException {
        Object postBody = taskDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateTask");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}", uriVariables);

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
