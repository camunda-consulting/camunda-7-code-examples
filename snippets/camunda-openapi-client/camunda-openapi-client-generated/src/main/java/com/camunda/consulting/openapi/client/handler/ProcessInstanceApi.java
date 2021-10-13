package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.ActivityInstanceDto;
import com.camunda.consulting.openapi.client.model.AuthorizationExceptionDto;
import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CorrelationMessageAsyncDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DeleteProcessInstancesDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.PatchVariablesDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceModificationDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceQueryDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceSuspensionStateAsyncDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceSuspensionStateDto;
import com.camunda.consulting.openapi.client.model.SetJobRetriesByProcessDto;
import com.camunda.consulting.openapi.client.model.SetVariablesAsyncDto;
import com.camunda.consulting.openapi.client.model.SuspensionStateDto;
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
@Component("com.camunda.consulting.openapi.client.handler.ProcessInstanceApi")
public class ProcessInstanceApi {
    private ApiClient apiClient;

    public ProcessInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public ProcessInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Correlate Message Async (POST)
     * Correlates a message asynchronously to executions that are waiting for this message.  Messages will not be correlated to process definition-level start message events to start process instances.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request * If none of &#x60;processInstanceIds&#x60;, &#x60;processInstanceQuery&#x60;, and &#x60;historicProcessInstanceQuery&#x60; is given * If no process instance ids where found
     * <p><b>403</b> - Returned if the user is not allowed to create the batch.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param correlationMessageAsyncDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto correlateMessageAsyncOperation(CorrelationMessageAsyncDto correlationMessageAsyncDto) throws RestClientException {
        return correlateMessageAsyncOperationWithHttpInfo(correlationMessageAsyncDto).getBody();
    }

    /**
     * Correlate Message Async (POST)
     * Correlates a message asynchronously to executions that are waiting for this message.  Messages will not be correlated to process definition-level start message events to start process instances.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request * If none of &#x60;processInstanceIds&#x60;, &#x60;processInstanceQuery&#x60;, and &#x60;historicProcessInstanceQuery&#x60; is given * If no process instance ids where found
     * <p><b>403</b> - Returned if the user is not allowed to create the batch.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param correlationMessageAsyncDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> correlateMessageAsyncOperationWithHttpInfo(CorrelationMessageAsyncDto correlationMessageAsyncDto) throws RestClientException {
        Object postBody = correlationMessageAsyncDto;
        
        String path = apiClient.expandPath("/process-instance/message-async", Collections.<String, Object>emptyMap());

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
     * Delete Async Historic Query Based (POST)
     * Deletes a set of process instances asynchronously (batch) based on a historic process instance query.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, i.e., neither processInstanceIds, nor historicProcessInstanceQuery is present
     * @param deleteProcessInstancesDto **Unallowed property**: &#x60;processInstanceQuery&#x60; (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto deleteAsyncHistoricQueryBased(DeleteProcessInstancesDto deleteProcessInstancesDto) throws RestClientException {
        return deleteAsyncHistoricQueryBasedWithHttpInfo(deleteProcessInstancesDto).getBody();
    }

    /**
     * Delete Async Historic Query Based (POST)
     * Deletes a set of process instances asynchronously (batch) based on a historic process instance query.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, i.e., neither processInstanceIds, nor historicProcessInstanceQuery is present
     * @param deleteProcessInstancesDto **Unallowed property**: &#x60;processInstanceQuery&#x60; (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> deleteAsyncHistoricQueryBasedWithHttpInfo(DeleteProcessInstancesDto deleteProcessInstancesDto) throws RestClientException {
        Object postBody = deleteProcessInstancesDto;
        
        String path = apiClient.expandPath("/process-instance/delete-historic-query-based", Collections.<String, Object>emptyMap());

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
     * Delete
     * Deletes a running process instance by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Process instance with given id does not exist. 
     * @param id The id of the process instance to be deleted. (required)
     * @param skipCustomListeners If set to true, the custom listeners will be skipped. (optional, default to false)
     * @param skipIoMappings If set to true, the input/output mappings will be skipped. (optional, default to false)
     * @param skipSubprocesses If set to true, subprocesses related to deleted processes will be skipped. (optional, default to false)
     * @param failIfNotExists If set to false, the request will still be successful if the process id is not found. (optional, default to true)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteProcessInstance(String id, Boolean skipCustomListeners, Boolean skipIoMappings, Boolean skipSubprocesses, Boolean failIfNotExists) throws RestClientException {
        deleteProcessInstanceWithHttpInfo(id, skipCustomListeners, skipIoMappings, skipSubprocesses, failIfNotExists);
    }

    /**
     * Delete
     * Deletes a running process instance by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Process instance with given id does not exist. 
     * @param id The id of the process instance to be deleted. (required)
     * @param skipCustomListeners If set to true, the custom listeners will be skipped. (optional, default to false)
     * @param skipIoMappings If set to true, the input/output mappings will be skipped. (optional, default to false)
     * @param skipSubprocesses If set to true, subprocesses related to deleted processes will be skipped. (optional, default to false)
     * @param failIfNotExists If set to false, the request will still be successful if the process id is not found. (optional, default to true)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteProcessInstanceWithHttpInfo(String id, Boolean skipCustomListeners, Boolean skipIoMappings, Boolean skipSubprocesses, Boolean failIfNotExists) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipCustomListeners", skipCustomListeners));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipIoMappings", skipIoMappings));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipSubprocesses", skipSubprocesses));
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
     * Delete Process Variable
     * Deletes a variable of a process instance by id.
     * <p><b>204</b> - Request successful.
     * @param id The id of the process instance to delete the variable from. (required)
     * @param varName The name of the variable to delete. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteProcessInstanceVariable(String id, String varName) throws RestClientException {
        deleteProcessInstanceVariableWithHttpInfo(id, varName);
    }

    /**
     * Delete Process Variable
     * Deletes a variable of a process instance by id.
     * <p><b>204</b> - Request successful.
     * @param id The id of the process instance to delete the variable from. (required)
     * @param varName The name of the variable to delete. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteProcessInstanceVariableWithHttpInfo(String id, String varName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteProcessInstanceVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling deleteProcessInstanceVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/process-instance/{id}/variables/{varName}", uriVariables);

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
     * Delete Async (POST)
     * Deletes multiple process instances asynchronously (batch).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, i.e., neither processInstanceIds, nor processInstanceQuery is present
     * @param deleteProcessInstancesDto **Unallowed property**: &#x60;historicProcessInstanceQuery&#x60; (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto deleteProcessInstancesAsyncOperation(DeleteProcessInstancesDto deleteProcessInstancesDto) throws RestClientException {
        return deleteProcessInstancesAsyncOperationWithHttpInfo(deleteProcessInstancesDto).getBody();
    }

    /**
     * Delete Async (POST)
     * Deletes multiple process instances asynchronously (batch).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, i.e., neither processInstanceIds, nor processInstanceQuery is present
     * @param deleteProcessInstancesDto **Unallowed property**: &#x60;historicProcessInstanceQuery&#x60; (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> deleteProcessInstancesAsyncOperationWithHttpInfo(DeleteProcessInstancesDto deleteProcessInstancesDto) throws RestClientException {
        Object postBody = deleteProcessInstancesDto;
        
        String path = apiClient.expandPath("/process-instance/delete", Collections.<String, Object>emptyMap());

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
     * Get Activity Instance
     * Retrieves an Activity Instance (Tree) for a given process instance by id.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - Process instance with given id does not exist.
     * @param id The id of the process instance for which the activity instance should be retrieved. (required)
     * @return ActivityInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ActivityInstanceDto getActivityInstanceTree(String id) throws RestClientException {
        return getActivityInstanceTreeWithHttpInfo(id).getBody();
    }

    /**
     * Get Activity Instance
     * Retrieves an Activity Instance (Tree) for a given process instance by id.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - Process instance with given id does not exist.
     * @param id The id of the process instance for which the activity instance should be retrieved. (required)
     * @return ResponseEntity&lt;ActivityInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ActivityInstanceDto> getActivityInstanceTreeWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getActivityInstanceTree");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}/activity-instances", uriVariables);

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

        ParameterizedTypeReference<ActivityInstanceDto> returnType = new ParameterizedTypeReference<ActivityInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Process Instance
     * Retrieves a process instance by id, according to the &#x60;ProcessInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Process instance with given id does not exist. See the  [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the process instance to be retrieved. (required)
     * @return ProcessInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceDto getProcessInstance(String id) throws RestClientException {
        return getProcessInstanceWithHttpInfo(id).getBody();
    }

    /**
     * Get Process Instance
     * Retrieves a process instance by id, according to the &#x60;ProcessInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Process instance with given id does not exist. See the  [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the process instance to be retrieved. (required)
     * @return ResponseEntity&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceDto> getProcessInstanceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceDto> returnType = new ParameterizedTypeReference<ProcessInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Process Variable
     * Retrieves a variable of a given process instance by id.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Variable with given id does not exist.
     * @param id The id of the process instance to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return VariableValueDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VariableValueDto getProcessInstanceVariable(String id, String varName, Boolean deserializeValue) throws RestClientException {
        return getProcessInstanceVariableWithHttpInfo(id, varName, deserializeValue).getBody();
    }

    /**
     * Get Process Variable
     * Retrieves a variable of a given process instance by id.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Variable with given id does not exist.
     * @param id The id of the process instance to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @param deserializeValue Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VariableValueDto> getProcessInstanceVariableWithHttpInfo(String id, String varName, Boolean deserializeValue) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessInstanceVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling getProcessInstanceVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/process-instance/{id}/variables/{varName}", uriVariables);

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
     * Get Process Variable (Binary)
     * Retrieves the content of a Process Variable by the Process Instance id and the Process Variable name. Applicable for byte array or file Process Variables.
     * <p><b>200</b> - Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set.
     * <p><b>400</b> - Bad Request A Process Variable with the given id exists but does not serialize as binary data.
     * <p><b>404</b> - Not Found A Process Variable with the given id does not exist. 
     * @param id The id of the process instance to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getProcessInstanceVariableBinary(String id, String varName) throws RestClientException {
        return getProcessInstanceVariableBinaryWithHttpInfo(id, varName).getBody();
    }

    /**
     * Get Process Variable (Binary)
     * Retrieves the content of a Process Variable by the Process Instance id and the Process Variable name. Applicable for byte array or file Process Variables.
     * <p><b>200</b> - Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set.
     * <p><b>400</b> - Bad Request A Process Variable with the given id exists but does not serialize as binary data.
     * <p><b>404</b> - Not Found A Process Variable with the given id does not exist. 
     * @param id The id of the process instance to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getProcessInstanceVariableBinaryWithHttpInfo(String id, String varName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessInstanceVariableBinary");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling getProcessInstanceVariableBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/process-instance/{id}/variables/{varName}/data", uriVariables);

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
     * Get Process Variables
     * Retrieves all variables of a given process instance by id.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - Process instance with given id does not exist.
     * @param id The id of the process instance to retrieve the variables from. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getProcessInstanceVariables(String id, Boolean deserializeValues) throws RestClientException {
        return getProcessInstanceVariablesWithHttpInfo(id, deserializeValues).getBody();
    }

    /**
     * Get Process Variables
     * Retrieves all variables of a given process instance by id.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - Process instance with given id does not exist.
     * @param id The id of the process instance to retrieve the variables from. (required)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getProcessInstanceVariablesWithHttpInfo(String id, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessInstanceVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}/variables", uriVariables);

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
     * Get List
     * Queries for process instances that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of process instances. The size of the result set can be retrieved by using the Get Instance Count method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy, or if an invalid operator for variable comparison is used.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param processInstanceIds Filter by a comma-separated list of process instance ids. (optional)
     * @param businessKey Filter by process instance business key. (optional)
     * @param businessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param processDefinitionId Filter by the deployment the id belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys. (optional)
     * @param processDefinitionKeyNotIn Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param superProcessInstance Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstance Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. (optional)
     * @param superCaseInstance Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstance Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. (optional)
     * @param active Only include active process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param suspended Only include suspended process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param withIncident Filter by presence of incidents. Selects only process instances that have an incident. (optional, default to false)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process instances which belong to no tenant. (optional, default to false)
     * @param processDefinitionWithoutTenantId Only include process instances which process definition has no tenant id. (optional, default to false)
     * @param activityIdIn Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional, default to false)
     * @param leafProcessInstances Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances). (optional, default to false)
     * @param variables Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. (optional, default to false)
     * @return List&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ProcessInstanceDto> getProcessInstances(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String processInstanceIds, String businessKey, String businessKeyLike, String caseInstanceId, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionKeyNotIn, String deploymentId, String superProcessInstance, String subProcessInstance, String superCaseInstance, String subCaseInstance, Boolean active, Boolean suspended, Boolean withIncident, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean processDefinitionWithoutTenantId, String activityIdIn, Boolean rootProcessInstances, Boolean leafProcessInstances, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        return getProcessInstancesWithHttpInfo(sortBy, sortOrder, firstResult, maxResults, processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase).getBody();
    }

    /**
     * Get List
     * Queries for process instances that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of process instances. The size of the result set can be retrieved by using the Get Instance Count method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy, or if an invalid operator for variable comparison is used.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param processInstanceIds Filter by a comma-separated list of process instance ids. (optional)
     * @param businessKey Filter by process instance business key. (optional)
     * @param businessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param processDefinitionId Filter by the deployment the id belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys. (optional)
     * @param processDefinitionKeyNotIn Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param superProcessInstance Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstance Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. (optional)
     * @param superCaseInstance Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstance Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. (optional)
     * @param active Only include active process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param suspended Only include suspended process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param withIncident Filter by presence of incidents. Selects only process instances that have an incident. (optional, default to false)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process instances which belong to no tenant. (optional, default to false)
     * @param processDefinitionWithoutTenantId Only include process instances which process definition has no tenant id. (optional, default to false)
     * @param activityIdIn Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional, default to false)
     * @param leafProcessInstances Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances). (optional, default to false)
     * @param variables Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. (optional, default to false)
     * @return ResponseEntity&lt;List&lt;ProcessInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ProcessInstanceDto>> getProcessInstancesWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String processInstanceIds, String businessKey, String businessKeyLike, String caseInstanceId, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionKeyNotIn, String deploymentId, String superProcessInstance, String subProcessInstance, String superCaseInstance, String subCaseInstance, Boolean active, Boolean suspended, Boolean withIncident, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean processDefinitionWithoutTenantId, String activityIdIn, Boolean rootProcessInstances, Boolean leafProcessInstances, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/process-instance", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIds", processInstanceIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "businessKey", businessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "businessKeyLike", businessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyNotIn", processDefinitionKeyNotIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superProcessInstance", superProcessInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subProcessInstance", subProcessInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superCaseInstance", superCaseInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subCaseInstance", subCaseInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withIncident", withIncident));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionWithoutTenantId", processDefinitionWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootProcessInstances", rootProcessInstances));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "leafProcessInstances", leafProcessInstances));
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

        ParameterizedTypeReference<List<ProcessInstanceDto>> returnType = new ParameterizedTypeReference<List<ProcessInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of process instances that fulfill given parameters.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example an invalid operator for variable comparison is used.
     * @param processInstanceIds Filter by a comma-separated list of process instance ids. (optional)
     * @param businessKey Filter by process instance business key. (optional)
     * @param businessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param processDefinitionId Filter by the deployment the id belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys. (optional)
     * @param processDefinitionKeyNotIn Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param superProcessInstance Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstance Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. (optional)
     * @param superCaseInstance Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstance Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. (optional)
     * @param active Only include active process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param suspended Only include suspended process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param withIncident Filter by presence of incidents. Selects only process instances that have an incident. (optional, default to false)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process instances which belong to no tenant. (optional, default to false)
     * @param processDefinitionWithoutTenantId Only include process instances which process definition has no tenant id. (optional, default to false)
     * @param activityIdIn Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional, default to false)
     * @param leafProcessInstances Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances). (optional, default to false)
     * @param variables Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. (optional, default to false)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getProcessInstancesCount(String processInstanceIds, String businessKey, String businessKeyLike, String caseInstanceId, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionKeyNotIn, String deploymentId, String superProcessInstance, String subProcessInstance, String superCaseInstance, String subCaseInstance, Boolean active, Boolean suspended, Boolean withIncident, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean processDefinitionWithoutTenantId, String activityIdIn, Boolean rootProcessInstances, Boolean leafProcessInstances, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        return getProcessInstancesCountWithHttpInfo(processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of process instances that fulfill given parameters.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example an invalid operator for variable comparison is used.
     * @param processInstanceIds Filter by a comma-separated list of process instance ids. (optional)
     * @param businessKey Filter by process instance business key. (optional)
     * @param businessKeyLike Filter by process instance business key that the parameter is a substring of. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param processDefinitionId Filter by the deployment the id belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances run on. (optional)
     * @param processDefinitionKeyIn Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys. (optional)
     * @param processDefinitionKeyNotIn Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param superProcessInstance Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. (optional)
     * @param subProcessInstance Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. (optional)
     * @param superCaseInstance Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. (optional)
     * @param subCaseInstance Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. (optional)
     * @param active Only include active process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param suspended Only include suspended process instances. Value may only be true, as false is the default behavior. (optional, default to false)
     * @param withIncident Filter by presence of incidents. Selects only process instances that have an incident. (optional, default to false)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process instances which belong to no tenant. (optional, default to false)
     * @param processDefinitionWithoutTenantId Only include process instances which process definition has no tenant id. (optional, default to false)
     * @param activityIdIn Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. (optional)
     * @param rootProcessInstances Restrict the query to all process instances that are top level process instances. (optional, default to false)
     * @param leafProcessInstances Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances). (optional, default to false)
     * @param variables Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. (optional, default to false)
     * @param variableValuesIgnoreCase Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. (optional, default to false)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getProcessInstancesCountWithHttpInfo(String processInstanceIds, String businessKey, String businessKeyLike, String caseInstanceId, String processDefinitionId, String processDefinitionKey, String processDefinitionKeyIn, String processDefinitionKeyNotIn, String deploymentId, String superProcessInstance, String subProcessInstance, String superCaseInstance, String subCaseInstance, Boolean active, Boolean suspended, Boolean withIncident, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean processDefinitionWithoutTenantId, String activityIdIn, Boolean rootProcessInstances, Boolean leafProcessInstances, String variables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/process-instance/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIds", processInstanceIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "businessKey", businessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "businessKeyLike", businessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyIn", processDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKeyNotIn", processDefinitionKeyNotIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superProcessInstance", superProcessInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subProcessInstance", subProcessInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "superCaseInstance", superCaseInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "subCaseInstance", subCaseInstance));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withIncident", withIncident));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionWithoutTenantId", processDefinitionWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootProcessInstances", rootProcessInstances));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "leafProcessInstances", leafProcessInstances));
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
     * Modify Process Instance Execution State
     * Submits a list of modification instructions to change a process instance&#39;s execution state. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Canceling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed immediately and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/develop/user-guide/process-engine/process-instance-modification/).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - At least one modification instruction misses required parameters.
     * <p><b>500</b> - The modification cannot be performed, for example because it starts a failing activity.
     * @param id The id of the process instance to modify. (required)
     * @param processInstanceModificationDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void modifyProcessInstance(String id, ProcessInstanceModificationDto processInstanceModificationDto) throws RestClientException {
        modifyProcessInstanceWithHttpInfo(id, processInstanceModificationDto);
    }

    /**
     * Modify Process Instance Execution State
     * Submits a list of modification instructions to change a process instance&#39;s execution state. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Canceling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed immediately and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/develop/user-guide/process-engine/process-instance-modification/).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - At least one modification instruction misses required parameters.
     * <p><b>500</b> - The modification cannot be performed, for example because it starts a failing activity.
     * @param id The id of the process instance to modify. (required)
     * @param processInstanceModificationDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> modifyProcessInstanceWithHttpInfo(String id, ProcessInstanceModificationDto processInstanceModificationDto) throws RestClientException {
        Object postBody = processInstanceModificationDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling modifyProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}/modification", uriVariables);

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
     * Modify Process Instance Execution State Async
     * Submits a list of modification instructions to change a process instance&#39;s execution state async. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Cancelling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed asynchronous and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-modification/).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request At least one modification instruction misses required parameters.
     * <p><b>403</b> - Forbidden If the user is not allowed to execute batches. See the Introduction for the error response format.
     * <p><b>500</b> - The modification cannot be performed, for example because it starts a failing activity.
     * @param id The id of the process instance to modify. (required)
     * @param processInstanceModificationDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto modifyProcessInstanceAsyncOperation(String id, ProcessInstanceModificationDto processInstanceModificationDto) throws RestClientException {
        return modifyProcessInstanceAsyncOperationWithHttpInfo(id, processInstanceModificationDto).getBody();
    }

    /**
     * Modify Process Instance Execution State Async
     * Submits a list of modification instructions to change a process instance&#39;s execution state async. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Cancelling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed asynchronous and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-modification/).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request At least one modification instruction misses required parameters.
     * <p><b>403</b> - Forbidden If the user is not allowed to execute batches. See the Introduction for the error response format.
     * <p><b>500</b> - The modification cannot be performed, for example because it starts a failing activity.
     * @param id The id of the process instance to modify. (required)
     * @param processInstanceModificationDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> modifyProcessInstanceAsyncOperationWithHttpInfo(String id, ProcessInstanceModificationDto processInstanceModificationDto) throws RestClientException {
        Object postBody = processInstanceModificationDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling modifyProcessInstanceAsyncOperation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}/modification-async", uriVariables);

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
     * Update/Delete Process Variables
     * Updates or deletes the variables of a process instance by id. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.
     * <p><b>500</b> - Update or delete could not be executed, for example because the process instance does not exist.
     * @param id The id of the process instance to set variables for. (required)
     * @param patchVariablesDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void modifyProcessInstanceVariables(String id, PatchVariablesDto patchVariablesDto) throws RestClientException {
        modifyProcessInstanceVariablesWithHttpInfo(id, patchVariablesDto);
    }

    /**
     * Update/Delete Process Variables
     * Updates or deletes the variables of a process instance by id. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.
     * <p><b>500</b> - Update or delete could not be executed, for example because the process instance does not exist.
     * @param id The id of the process instance to set variables for. (required)
     * @param patchVariablesDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> modifyProcessInstanceVariablesWithHttpInfo(String id, PatchVariablesDto patchVariablesDto) throws RestClientException {
        Object postBody = patchVariablesDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling modifyProcessInstanceVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}/variables", uriVariables);

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
     * Queries for process instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the Get Instances method because it allows filtering by multiple process variables of types &#x60;string&#x60;, &#x60;number&#x60; or &#x60;boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy, or if an invalid operator for variable comparison is used.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param processInstanceQueryDto  (optional)
     * @return List&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ProcessInstanceDto> queryProcessInstances(Integer firstResult, Integer maxResults, ProcessInstanceQueryDto processInstanceQueryDto) throws RestClientException {
        return queryProcessInstancesWithHttpInfo(firstResult, maxResults, processInstanceQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for process instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the Get Instances method because it allows filtering by multiple process variables of types &#x60;string&#x60;, &#x60;number&#x60; or &#x60;boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy, or if an invalid operator for variable comparison is used.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param processInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;ProcessInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ProcessInstanceDto>> queryProcessInstancesWithHttpInfo(Integer firstResult, Integer maxResults, ProcessInstanceQueryDto processInstanceQueryDto) throws RestClientException {
        Object postBody = processInstanceQueryDto;
        
        String path = apiClient.expandPath("/process-instance", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<ProcessInstanceDto>> returnType = new ParameterizedTypeReference<List<ProcessInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count (POST)
     * Queries for the number of process instances that fulfill the given parameters. This method takes the same message body as the Get Instances (POST) method and therefore it is slightly more powerful than the Get Instance Count method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used.
     * @param processInstanceQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryProcessInstancesCount(ProcessInstanceQueryDto processInstanceQueryDto) throws RestClientException {
        return queryProcessInstancesCountWithHttpInfo(processInstanceQueryDto).getBody();
    }

    /**
     * Get List Count (POST)
     * Queries for the number of process instances that fulfill the given parameters. This method takes the same message body as the Get Instances (POST) method and therefore it is slightly more powerful than the Get Instance Count method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used.
     * @param processInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryProcessInstancesCountWithHttpInfo(ProcessInstanceQueryDto processInstanceQueryDto) throws RestClientException {
        Object postBody = processInstanceQueryDto;
        
        String path = apiClient.expandPath("/process-instance/count", Collections.<String, Object>emptyMap());

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
     * Update Process Variable
     * Sets a variable of a given process instance by id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.
     * @param id The id of the process instance to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param variableValueDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setProcessInstanceVariable(String id, String varName, VariableValueDto variableValueDto) throws RestClientException {
        setProcessInstanceVariableWithHttpInfo(id, varName, variableValueDto);
    }

    /**
     * Update Process Variable
     * Sets a variable of a given process instance by id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.
     * @param id The id of the process instance to set the variable for. (required)
     * @param varName The name of the variable to set. (required)
     * @param variableValueDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setProcessInstanceVariableWithHttpInfo(String id, String varName, VariableValueDto variableValueDto) throws RestClientException {
        Object postBody = variableValueDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setProcessInstanceVariable");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling setProcessInstanceVariable");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/process-instance/{id}/variables/{varName}", uriVariables);

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
     * Update Process Variable (Binary)
     * Sets the serialized value for a binary variable or the binary value for a file variable.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request The variable value or type is invalid, for example if no filename is set.
     * @param id The id of the process instance to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @param data The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. (optional)
     * @param valueType The name of the variable type. Either Bytes for a byte array variable or File for a file variable. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setProcessInstanceVariableBinary(String id, String varName, File data, String valueType) throws RestClientException {
        setProcessInstanceVariableBinaryWithHttpInfo(id, varName, data, valueType);
    }

    /**
     * Update Process Variable (Binary)
     * Sets the serialized value for a binary variable or the binary value for a file variable.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request The variable value or type is invalid, for example if no filename is set.
     * @param id The id of the process instance to retrieve the variable for. (required)
     * @param varName The name of the variable to retrieve. (required)
     * @param data The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. (optional)
     * @param valueType The name of the variable type. Either Bytes for a byte array variable or File for a file variable. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setProcessInstanceVariableBinaryWithHttpInfo(String id, String varName, File data, String valueType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setProcessInstanceVariableBinary");
        }
        
        // verify the required parameter 'varName' is set
        if (varName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'varName' when calling setProcessInstanceVariableBinary");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("varName", varName);
        String path = apiClient.expandPath("/process-instance/{id}/variables/{varName}/data", uriVariables);

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
     * Set Job Retries Async (POST)
     * Create a batch to set retries of jobs associated with given processes asynchronously.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if neither processInstanceIds, nor processInstanceQuery is present. Or if the retry count is not specified.
     * @param setJobRetriesByProcessDto Please note that if both processInstances and processInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: &#x60;historicProcessInstanceQuery&#x60; (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setRetriesByProcess(SetJobRetriesByProcessDto setJobRetriesByProcessDto) throws RestClientException {
        return setRetriesByProcessWithHttpInfo(setJobRetriesByProcessDto).getBody();
    }

    /**
     * Set Job Retries Async (POST)
     * Create a batch to set retries of jobs associated with given processes asynchronously.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if neither processInstanceIds, nor processInstanceQuery is present. Or if the retry count is not specified.
     * @param setJobRetriesByProcessDto Please note that if both processInstances and processInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: &#x60;historicProcessInstanceQuery&#x60; (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setRetriesByProcessWithHttpInfo(SetJobRetriesByProcessDto setJobRetriesByProcessDto) throws RestClientException {
        Object postBody = setJobRetriesByProcessDto;
        
        String path = apiClient.expandPath("/process-instance/job-retries", Collections.<String, Object>emptyMap());

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
     * Set Job Retries Async Historic Query Based (POST)
     * Create a batch to set retries of jobs asynchronously based on a historic process instance query.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if neither processInstanceIds, nor historicProcessInstanceQuery is present. Or if the retry count is not specified.
     * @param setJobRetriesByProcessDto Please note that if both processInstances and historicProcessInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: &#x60;processInstanceQuery&#x60; (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setRetriesByProcessHistoricQueryBased(SetJobRetriesByProcessDto setJobRetriesByProcessDto) throws RestClientException {
        return setRetriesByProcessHistoricQueryBasedWithHttpInfo(setJobRetriesByProcessDto).getBody();
    }

    /**
     * Set Job Retries Async Historic Query Based (POST)
     * Create a batch to set retries of jobs asynchronously based on a historic process instance query.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if neither processInstanceIds, nor historicProcessInstanceQuery is present. Or if the retry count is not specified.
     * @param setJobRetriesByProcessDto Please note that if both processInstances and historicProcessInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: &#x60;processInstanceQuery&#x60; (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setRetriesByProcessHistoricQueryBasedWithHttpInfo(SetJobRetriesByProcessDto setJobRetriesByProcessDto) throws RestClientException {
        Object postBody = setJobRetriesByProcessDto;
        
        String path = apiClient.expandPath("/process-instance/job-retries-historic-query-based", Collections.<String, Object>emptyMap());

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
     * Set Variables Async (POST)
     * Update or create runtime process variables in the root scope of process instances.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request * The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported * If none of &#x60;processInstanceIds&#x60;, &#x60;processInstanceQuery&#x60; and &#x60;historicProcessInstanceQuery&#x60; is given * If no or an empty array of &#x60;variables&#x60; is given * If no process instance ids where found * If a transient variable is set * If the engine config flag &#x60;javaSerializationFormatEnabled&#x60; is &#x60;false&#x60; and a Java serialized variable is given
     * <p><b>403</b> - Returned if the user is not allowed to create the batch.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setVariablesAsyncDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setVariablesAsyncOperation(SetVariablesAsyncDto setVariablesAsyncDto) throws RestClientException {
        return setVariablesAsyncOperationWithHttpInfo(setVariablesAsyncDto).getBody();
    }

    /**
     * Set Variables Async (POST)
     * Update or create runtime process variables in the root scope of process instances.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request * The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported * If none of &#x60;processInstanceIds&#x60;, &#x60;processInstanceQuery&#x60; and &#x60;historicProcessInstanceQuery&#x60; is given * If no or an empty array of &#x60;variables&#x60; is given * If no process instance ids where found * If a transient variable is set * If the engine config flag &#x60;javaSerializationFormatEnabled&#x60; is &#x60;false&#x60; and a Java serialized variable is given
     * <p><b>403</b> - Returned if the user is not allowed to create the batch.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setVariablesAsyncDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setVariablesAsyncOperationWithHttpInfo(SetVariablesAsyncDto setVariablesAsyncDto) throws RestClientException {
        Object postBody = setVariablesAsyncDto;
        
        String path = apiClient.expandPath("/process-instance/variables-async", Collections.<String, Object>emptyMap());

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
     * Activate/Suspend In Group
     * Activates or suspends process instances by providing certain criteria:  # Activate/Suspend Process Instance By Process Definition Id * &#x60;suspend&#x60; * &#x60;processDefinitionId&#x60;  # Activate/Suspend Process Instance By Process Definition Key  * &#x60;suspend&#x60; * &#x60;processDefinitionKey&#x60; * &#x60;processDefinitionTenantId&#x60; * &#x60;processDefinitionWithoutTenantId&#x60;  # Activate/Suspend Process Instance In Group * &#x60;suspend&#x60; * &#x60;processInstanceIds&#x60; * &#x60;processInstanceQuery&#x60; * &#x60;historicProcessInstanceQuery&#x60;
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the request parameters are invalid, for example if the provided processDefinitionId or processDefinitionKey parameter is null.
     * @param processInstanceSuspensionStateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateSuspensionState(ProcessInstanceSuspensionStateDto processInstanceSuspensionStateDto) throws RestClientException {
        updateSuspensionStateWithHttpInfo(processInstanceSuspensionStateDto);
    }

    /**
     * Activate/Suspend In Group
     * Activates or suspends process instances by providing certain criteria:  # Activate/Suspend Process Instance By Process Definition Id * &#x60;suspend&#x60; * &#x60;processDefinitionId&#x60;  # Activate/Suspend Process Instance By Process Definition Key  * &#x60;suspend&#x60; * &#x60;processDefinitionKey&#x60; * &#x60;processDefinitionTenantId&#x60; * &#x60;processDefinitionWithoutTenantId&#x60;  # Activate/Suspend Process Instance In Group * &#x60;suspend&#x60; * &#x60;processInstanceIds&#x60; * &#x60;processInstanceQuery&#x60; * &#x60;historicProcessInstanceQuery&#x60;
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the request parameters are invalid, for example if the provided processDefinitionId or processDefinitionKey parameter is null.
     * @param processInstanceSuspensionStateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateSuspensionStateWithHttpInfo(ProcessInstanceSuspensionStateDto processInstanceSuspensionStateDto) throws RestClientException {
        Object postBody = processInstanceSuspensionStateDto;
        
        String path = apiClient.expandPath("/process-instance/suspended", Collections.<String, Object>emptyMap());

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
     * Activate/Suspend In Batch
     * Activates or suspends process instances asynchronously with a list of process instance ids, a process instance query, and/or a historical process instance query.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the request parameters are invalid, for example if the provided processDefinitionId or processDefinitionKey parameter is null.
     * @param processInstanceSuspensionStateAsyncDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto updateSuspensionStateAsyncOperation(ProcessInstanceSuspensionStateAsyncDto processInstanceSuspensionStateAsyncDto) throws RestClientException {
        return updateSuspensionStateAsyncOperationWithHttpInfo(processInstanceSuspensionStateAsyncDto).getBody();
    }

    /**
     * Activate/Suspend In Batch
     * Activates or suspends process instances asynchronously with a list of process instance ids, a process instance query, and/or a historical process instance query.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the request parameters are invalid, for example if the provided processDefinitionId or processDefinitionKey parameter is null.
     * @param processInstanceSuspensionStateAsyncDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> updateSuspensionStateAsyncOperationWithHttpInfo(ProcessInstanceSuspensionStateAsyncDto processInstanceSuspensionStateAsyncDto) throws RestClientException {
        Object postBody = processInstanceSuspensionStateAsyncDto;
        
        String path = apiClient.expandPath("/process-instance/suspended-async", Collections.<String, Object>emptyMap());

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
     * Activate/Suspend Process Instance By Id
     * Activates or suspends a given process instance by id.
     * <p><b>204</b> - Request successful.
     * @param id The id of the process instance to activate or suspend. (required)
     * @param suspensionStateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateSuspensionStateById(String id, SuspensionStateDto suspensionStateDto) throws RestClientException {
        updateSuspensionStateByIdWithHttpInfo(id, suspensionStateDto);
    }

    /**
     * Activate/Suspend Process Instance By Id
     * Activates or suspends a given process instance by id.
     * <p><b>204</b> - Request successful.
     * @param id The id of the process instance to activate or suspend. (required)
     * @param suspensionStateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateSuspensionStateByIdWithHttpInfo(String id, SuspensionStateDto suspensionStateDto) throws RestClientException {
        Object postBody = suspensionStateDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateSuspensionStateById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-instance/{id}/suspended", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = {  };
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
