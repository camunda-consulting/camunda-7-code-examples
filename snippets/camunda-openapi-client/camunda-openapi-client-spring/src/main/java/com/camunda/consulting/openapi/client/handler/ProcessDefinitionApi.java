package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.ActivityStatisticsResultDto;
import com.camunda.consulting.openapi.client.model.AuthorizationExceptionDto;
import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CalledProcessDefinitionDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.FormDto;
import com.camunda.consulting.openapi.client.model.HistoryTimeToLiveDto;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.ProcessDefinitionDiagramDto;
import com.camunda.consulting.openapi.client.model.ProcessDefinitionDto;
import com.camunda.consulting.openapi.client.model.ProcessDefinitionStatisticsResultDto;
import com.camunda.consulting.openapi.client.model.ProcessDefinitionSuspensionStateDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceWithVariablesDto;
import com.camunda.consulting.openapi.client.model.RestartProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.StartProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.StartProcessInstanceFormDto;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi")
public class ProcessDefinitionApi {
    private ApiClient apiClient;

    public ProcessDefinitionApi() {
        this(new ApiClient());
    }

    @Autowired
    public ProcessDefinitionApi(ApiClient apiClient) {
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
     * Deletes a running process instance by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to be deleted. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. (optional)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteProcessDefinition(String id, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        deleteProcessDefinitionWithHttpInfo(id, cascade, skipCustomListeners, skipIoMappings);
    }

    /**
     * Delete
     * Deletes a running process instance by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - Not found Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to be deleted. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. (optional)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteProcessDefinitionWithHttpInfo(String id, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteProcessDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "cascade", cascade));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipCustomListeners", skipCustomListeners));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipIoMappings", skipIoMappings));

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
     * Delete By Key
     * Deletes process definitions by a given key which belong to no tenant id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Forbidden The process definitions with the given &#x60;key&#x60; cannot be deleted due to missing permissions. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Not found Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definitions to be deleted. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. (optional)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteProcessDefinitionsByKey(String key, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        deleteProcessDefinitionsByKeyWithHttpInfo(key, cascade, skipCustomListeners, skipIoMappings);
    }

    /**
     * Delete By Key
     * Deletes process definitions by a given key which belong to no tenant id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Forbidden The process definitions with the given &#x60;key&#x60; cannot be deleted due to missing permissions. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Not found Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definitions to be deleted. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. (optional)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteProcessDefinitionsByKeyWithHttpInfo(String key, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling deleteProcessDefinitionsByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "cascade", cascade));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipCustomListeners", skipCustomListeners));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipIoMappings", skipIoMappings));

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
     * Delete By Key
     * Deletes process definitions by a given key and which belong to a tenant id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Forbidden The process definitions with the given &#x60;key&#x60; cannot be deleted due to missing permissions. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Not found Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definitions to be deleted. (required)
     * @param tenantId The id of the tenant the process definitions belong to. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. (optional)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteProcessDefinitionsByKeyAndTenantId(String key, String tenantId, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        deleteProcessDefinitionsByKeyAndTenantIdWithHttpInfo(key, tenantId, cascade, skipCustomListeners, skipIoMappings);
    }

    /**
     * Delete By Key
     * Deletes process definitions by a given key and which belong to a tenant id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Forbidden The process definitions with the given &#x60;key&#x60; cannot be deleted due to missing permissions. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Not found Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definitions to be deleted. (required)
     * @param tenantId The id of the tenant the process definitions belong to. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. (optional)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteProcessDefinitionsByKeyAndTenantIdWithHttpInfo(String key, String tenantId, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling deleteProcessDefinitionsByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling deleteProcessDefinitionsByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "cascade", cascade));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipCustomListeners", skipCustomListeners));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipIoMappings", skipIoMappings));

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
     * Get Activity Instance Statistics
     * Retrieves runtime statistics of a given process definition, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @return List&lt;ActivityStatisticsResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ActivityStatisticsResultDto> getActivityStatistics(String id, Boolean failedJobs, Boolean incidents, String incidentsForType) throws RestClientException {
        return getActivityStatisticsWithHttpInfo(id, failedJobs, incidents, incidentsForType).getBody();
    }

    /**
     * Get Activity Instance Statistics
     * Retrieves runtime statistics of a given process definition, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @return ResponseEntity&lt;List&lt;ActivityStatisticsResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ActivityStatisticsResultDto>> getActivityStatisticsWithHttpInfo(String id, Boolean failedJobs, Boolean incidents, String incidentsForType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getActivityStatistics");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/statistics", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedJobs", failedJobs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidents", incidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentsForType", incidentsForType));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<ActivityStatisticsResultDto>> returnType = new ParameterizedTypeReference<List<ActivityStatisticsResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Activity Instance Statistics
     * Retrieves runtime statistics of the latest version of the given process definition which belongs to no tenant, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @return List&lt;ActivityStatisticsResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ActivityStatisticsResultDto> getActivityStatisticsByProcessDefinitionKey(String key, Boolean failedJobs, Boolean incidents, String incidentsForType) throws RestClientException {
        return getActivityStatisticsByProcessDefinitionKeyWithHttpInfo(key, failedJobs, incidents, incidentsForType).getBody();
    }

    /**
     * Get Activity Instance Statistics
     * Retrieves runtime statistics of the latest version of the given process definition which belongs to no tenant, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @return ResponseEntity&lt;List&lt;ActivityStatisticsResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ActivityStatisticsResultDto>> getActivityStatisticsByProcessDefinitionKeyWithHttpInfo(String key, Boolean failedJobs, Boolean incidents, String incidentsForType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getActivityStatisticsByProcessDefinitionKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/statistics", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedJobs", failedJobs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidents", incidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentsForType", incidentsForType));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<ActivityStatisticsResultDto>> returnType = new ParameterizedTypeReference<List<ActivityStatisticsResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Activity Instance Statistics
     * Retrieves runtime statistics of the latest version of the given process definition for a tenant, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @return List&lt;ActivityStatisticsResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ActivityStatisticsResultDto> getActivityStatisticsByProcessDefinitionKeyAndTenantId(String key, String tenantId, Boolean failedJobs, Boolean incidents, String incidentsForType) throws RestClientException {
        return getActivityStatisticsByProcessDefinitionKeyAndTenantIdWithHttpInfo(key, tenantId, failedJobs, incidents, incidentsForType).getBody();
    }

    /**
     * Get Activity Instance Statistics
     * Retrieves runtime statistics of the latest version of the given process definition for a tenant, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @return ResponseEntity&lt;List&lt;ActivityStatisticsResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ActivityStatisticsResultDto>> getActivityStatisticsByProcessDefinitionKeyAndTenantIdWithHttpInfo(String key, String tenantId, Boolean failedJobs, Boolean incidents, String incidentsForType) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getActivityStatisticsByProcessDefinitionKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getActivityStatisticsByProcessDefinitionKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/statistics", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedJobs", failedJobs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidents", incidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentsForType", incidentsForType));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<ActivityStatisticsResultDto>> returnType = new ParameterizedTypeReference<List<ActivityStatisticsResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Deployed Start Form
     * Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to get the deployed start form for. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDeployedStartForm(String id) throws RestClientException {
        return getDeployedStartFormWithHttpInfo(id).getBody();
    }

    /**
     * Get Deployed Start Form
     * Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to get the deployed start form for. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDeployedStartFormWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDeployedStartForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/deployed-start-form", uriVariables);

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
     * Get Deployed Start Form
     * Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDeployedStartFormByKey(String key) throws RestClientException {
        return getDeployedStartFormByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Deployed Start Form
     * Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDeployedStartFormByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDeployedStartFormByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/deployed-start-form", uriVariables);

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
     * Get Deployed Start Form
     * Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definitions belong to. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDeployedStartFormByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getDeployedStartFormByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Deployed Start Form
     * Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#embedded-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definitions belong to. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDeployedStartFormByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDeployedStartFormByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDeployedStartFormByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/deployed-start-form", uriVariables);

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
     * Retrieves the latest version of the process definition for tenant according to the &#x60;ProcessDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ProcessDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessDefinitionDto getLatestProcessDefinitionByTenantId(String key, String tenantId) throws RestClientException {
        return getLatestProcessDefinitionByTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get
     * Retrieves the latest version of the process definition for tenant according to the &#x60;ProcessDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ResponseEntity&lt;ProcessDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessDefinitionDto> getLatestProcessDefinitionByTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getLatestProcessDefinitionByTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getLatestProcessDefinitionByTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}", uriVariables);

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

        ParameterizedTypeReference<ProcessDefinitionDto> returnType = new ParameterizedTypeReference<ProcessDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get
     * Retrieves a process definition according to the &#x60;ProcessDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition with given &#x60;id&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to be retrieved. (required)
     * @return ProcessDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessDefinitionDto getProcessDefinition(String id) throws RestClientException {
        return getProcessDefinitionWithHttpInfo(id).getBody();
    }

    /**
     * Get
     * Retrieves a process definition according to the &#x60;ProcessDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition with given &#x60;id&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to be retrieved. (required)
     * @return ResponseEntity&lt;ProcessDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessDefinitionDto> getProcessDefinitionWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}", uriVariables);

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

        ParameterizedTypeReference<ProcessDefinitionDto> returnType = new ParameterizedTypeReference<ProcessDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get XML
     * Retrieves the BPMN 2.0 XML of a process definition.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return ProcessDefinitionDiagramDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessDefinitionDiagramDto getProcessDefinitionBpmn20Xml(String id) throws RestClientException {
        return getProcessDefinitionBpmn20XmlWithHttpInfo(id).getBody();
    }

    /**
     * Get XML
     * Retrieves the BPMN 2.0 XML of a process definition.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return ResponseEntity&lt;ProcessDefinitionDiagramDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessDefinitionDiagramDto> getProcessDefinitionBpmn20XmlWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessDefinitionBpmn20Xml");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/xml", uriVariables);

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

        ParameterizedTypeReference<ProcessDefinitionDiagramDto> returnType = new ParameterizedTypeReference<ProcessDefinitionDiagramDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get XML
     * Retrieves latest version the BPMN 2.0 XML of a process definition.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> - The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) whose XML should be retrieved. (required)
     * @return ProcessDefinitionDiagramDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessDefinitionDiagramDto getProcessDefinitionBpmn20XmlByKey(String key) throws RestClientException {
        return getProcessDefinitionBpmn20XmlByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get XML
     * Retrieves latest version the BPMN 2.0 XML of a process definition.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> - The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) whose XML should be retrieved. (required)
     * @return ResponseEntity&lt;ProcessDefinitionDiagramDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessDefinitionDiagramDto> getProcessDefinitionBpmn20XmlByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getProcessDefinitionBpmn20XmlByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/xml", uriVariables);

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

        ParameterizedTypeReference<ProcessDefinitionDiagramDto> returnType = new ParameterizedTypeReference<ProcessDefinitionDiagramDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get XML
     * Retrieves latest version the BPMN 2.0 XML of a process definition. Returns the XML for the latest version of the process definition for tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> - The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) whose XML should be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ProcessDefinitionDiagramDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessDefinitionDiagramDto getProcessDefinitionBpmn20XmlByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getProcessDefinitionBpmn20XmlByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get XML
     * Retrieves latest version the BPMN 2.0 XML of a process definition. Returns the XML for the latest version of the process definition for tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> - The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) whose XML should be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ResponseEntity&lt;ProcessDefinitionDiagramDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessDefinitionDiagramDto> getProcessDefinitionBpmn20XmlByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getProcessDefinitionBpmn20XmlByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getProcessDefinitionBpmn20XmlByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/xml", uriVariables);

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

        ParameterizedTypeReference<ProcessDefinitionDiagramDto> returnType = new ParameterizedTypeReference<ProcessDefinitionDiagramDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get
     * Retrieves the latest version of the process definition which belongs to no tenant according to the &#x60;ProcessDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @return ProcessDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessDefinitionDto getProcessDefinitionByKey(String key) throws RestClientException {
        return getProcessDefinitionByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get
     * Retrieves the latest version of the process definition which belongs to no tenant according to the &#x60;ProcessDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;ProcessDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessDefinitionDto> getProcessDefinitionByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getProcessDefinitionByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}", uriVariables);

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

        ParameterizedTypeReference<ProcessDefinitionDto> returnType = new ParameterizedTypeReference<ProcessDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Diagram
     * Retrieves the diagram of a process definition.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The process definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getProcessDefinitionDiagram(String id) throws RestClientException {
        return getProcessDefinitionDiagramWithHttpInfo(id).getBody();
    }

    /**
     * Get Diagram
     * Retrieves the diagram of a process definition.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The process definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getProcessDefinitionDiagramWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getProcessDefinitionDiagram");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/diagram", uriVariables);

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
     * Get Diagram
     * Retrieves the diagram for the latest version of the process definition which belongs to no tenant.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The process definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getProcessDefinitionDiagramByKey(String key) throws RestClientException {
        return getProcessDefinitionDiagramByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Diagram
     * Retrieves the diagram for the latest version of the process definition which belongs to no tenant.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The process definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getProcessDefinitionDiagramByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getProcessDefinitionDiagramByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/diagram", uriVariables);

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
     * Get Diagram
     * Retrieves the diagram for the latest version of the process definition for tenant.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The process definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getProcessDefinitionDiagramByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getProcessDefinitionDiagramByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Diagram
     * Retrieves the diagram for the latest version of the process definition for tenant.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The process definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getProcessDefinitionDiagramByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getProcessDefinitionDiagramByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getProcessDefinitionDiagramByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/diagram", uriVariables);

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
     * Get Process Instance Statistics
     * Retrieves runtime statistics of the process engine, grouped by process definitions. These statistics include the number of running process instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param rootIncidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of root incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60; or &#x60;incidents&#x60;. (optional)
     * @return List&lt;ProcessDefinitionStatisticsResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ProcessDefinitionStatisticsResultDto> getProcessDefinitionStatistics(Boolean failedJobs, Boolean incidents, String incidentsForType, Boolean rootIncidents) throws RestClientException {
        return getProcessDefinitionStatisticsWithHttpInfo(failedJobs, incidents, incidentsForType, rootIncidents).getBody();
    }

    /**
     * Get Process Instance Statistics
     * Retrieves runtime statistics of the process engine, grouped by process definitions. These statistics include the number of running process instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param failedJobs Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. (optional)
     * @param incidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. (optional)
     * @param incidentsForType If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param rootIncidents Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of root incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60; or &#x60;incidents&#x60;. (optional)
     * @return ResponseEntity&lt;List&lt;ProcessDefinitionStatisticsResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ProcessDefinitionStatisticsResultDto>> getProcessDefinitionStatisticsWithHttpInfo(Boolean failedJobs, Boolean incidents, String incidentsForType, Boolean rootIncidents) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/process-definition/statistics", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedJobs", failedJobs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidents", incidents));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentsForType", incidentsForType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootIncidents", rootIncidents));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<ProcessDefinitionStatisticsResultDto>> returnType = new ParameterizedTypeReference<List<ProcessDefinitionStatisticsResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for process definitions that fulfill given parameters. Parameters may be the properties of  process definitions, such as the name, key or version. The size of the result set can be retrieved by using the [Get Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionIdIn Filter by a comma-separated list of process definition ids. (optional)
     * @param name Filter by process definition name. (optional)
     * @param nameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param key Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match. (optional)
     * @param keysIn Filter by a comma-separated list of process definition keys. (optional)
     * @param keyLike Filter by process definition keys that the parameter is a substring of. (optional)
     * @param category Filter by process definition category. Exact match. (optional)
     * @param categoryLike Filter by process definition categories that the parameter is a substring of. (optional)
     * @param version Filter by process definition version. (optional)
     * @param latestVersion Only include those process definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the process definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those process definition resources that the parameter is a substring of. (optional)
     * @param startableBy Filter by a user name who is allowed to start the process. (optional)
     * @param active Only include active process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior. (optional)
     * @param includeProcessDefinitionsWithoutTenantId Include process definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tag that the parameter is a substring of. (optional)
     * @param withoutVersionTag Only include process definitions without a &#x60;versionTag&#x60;. (optional)
     * @param startableInTasklist Filter by process definitions which are startable in Tasklist.. (optional)
     * @param notStartableInTasklist Filter by process definitions which are not startable in Tasklist. (optional)
     * @param startablePermissionCheck Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn&#39;t have these permissions the result will be empty list. The permissions are: * &#x60;CREATE&#x60; permission for all Process instances * &#x60;CREATE_INSTANCE&#x60; and &#x60;READ&#x60; permission on Process definition level (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;ProcessDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ProcessDefinitionDto> getProcessDefinitions(String processDefinitionId, String processDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keysIn, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String startableBy, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeProcessDefinitionsWithoutTenantId, String versionTag, String versionTagLike, Boolean withoutVersionTag, Boolean startableInTasklist, Boolean notStartableInTasklist, Boolean startablePermissionCheck, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getProcessDefinitionsWithHttpInfo(processDefinitionId, processDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keysIn, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, startableBy, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, includeProcessDefinitionsWithoutTenantId, versionTag, versionTagLike, withoutVersionTag, startableInTasklist, notStartableInTasklist, startablePermissionCheck, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for process definitions that fulfill given parameters. Parameters may be the properties of  process definitions, such as the name, key or version. The size of the result set can be retrieved by using the [Get Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionIdIn Filter by a comma-separated list of process definition ids. (optional)
     * @param name Filter by process definition name. (optional)
     * @param nameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param key Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match. (optional)
     * @param keysIn Filter by a comma-separated list of process definition keys. (optional)
     * @param keyLike Filter by process definition keys that the parameter is a substring of. (optional)
     * @param category Filter by process definition category. Exact match. (optional)
     * @param categoryLike Filter by process definition categories that the parameter is a substring of. (optional)
     * @param version Filter by process definition version. (optional)
     * @param latestVersion Only include those process definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the process definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those process definition resources that the parameter is a substring of. (optional)
     * @param startableBy Filter by a user name who is allowed to start the process. (optional)
     * @param active Only include active process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior. (optional)
     * @param includeProcessDefinitionsWithoutTenantId Include process definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tag that the parameter is a substring of. (optional)
     * @param withoutVersionTag Only include process definitions without a &#x60;versionTag&#x60;. (optional)
     * @param startableInTasklist Filter by process definitions which are startable in Tasklist.. (optional)
     * @param notStartableInTasklist Filter by process definitions which are not startable in Tasklist. (optional)
     * @param startablePermissionCheck Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn&#39;t have these permissions the result will be empty list. The permissions are: * &#x60;CREATE&#x60; permission for all Process instances * &#x60;CREATE_INSTANCE&#x60; and &#x60;READ&#x60; permission on Process definition level (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;ProcessDefinitionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ProcessDefinitionDto>> getProcessDefinitionsWithHttpInfo(String processDefinitionId, String processDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keysIn, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String startableBy, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeProcessDefinitionsWithoutTenantId, String versionTag, String versionTagLike, Boolean withoutVersionTag, Boolean startableInTasklist, Boolean notStartableInTasklist, Boolean startablePermissionCheck, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/process-definition", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionIdIn", processDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAfter", deployedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAt", deployedAt));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keysIn", keysIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keyLike", keyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryLike", categoryLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "latestVersion", latestVersion));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceNameLike", resourceNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startableBy", startableBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeProcessDefinitionsWithoutTenantId", includeProcessDefinitionsWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTag", versionTag));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTagLike", versionTagLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutVersionTag", withoutVersionTag));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startableInTasklist", startableInTasklist));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "notStartableInTasklist", notStartableInTasklist));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startablePermissionCheck", startablePermissionCheck));
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

        ParameterizedTypeReference<List<ProcessDefinitionDto>> returnType = new ParameterizedTypeReference<List<ProcessDefinitionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Requests the number of process definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Definitions](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionIdIn Filter by a comma-separated list of process definition ids. (optional)
     * @param name Filter by process definition name. (optional)
     * @param nameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param key Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match. (optional)
     * @param keysIn Filter by a comma-separated list of process definition keys. (optional)
     * @param keyLike Filter by process definition keys that the parameter is a substring of. (optional)
     * @param category Filter by process definition category. Exact match. (optional)
     * @param categoryLike Filter by process definition categories that the parameter is a substring of. (optional)
     * @param version Filter by process definition version. (optional)
     * @param latestVersion Only include those process definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the process definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those process definition resources that the parameter is a substring of. (optional)
     * @param startableBy Filter by a user name who is allowed to start the process. (optional)
     * @param active Only include active process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior. (optional)
     * @param includeProcessDefinitionsWithoutTenantId Include process definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tag that the parameter is a substring of. (optional)
     * @param withoutVersionTag Only include process definitions without a &#x60;versionTag&#x60;. (optional)
     * @param startableInTasklist Filter by process definitions which are startable in Tasklist.. (optional)
     * @param notStartableInTasklist Filter by process definitions which are not startable in Tasklist. (optional)
     * @param startablePermissionCheck Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn&#39;t have these permissions the result will be empty list. The permissions are: * &#x60;CREATE&#x60; permission for all Process instances * &#x60;CREATE_INSTANCE&#x60; and &#x60;READ&#x60; permission on Process definition level (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getProcessDefinitionsCount(String processDefinitionId, String processDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keysIn, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String startableBy, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeProcessDefinitionsWithoutTenantId, String versionTag, String versionTagLike, Boolean withoutVersionTag, Boolean startableInTasklist, Boolean notStartableInTasklist, Boolean startablePermissionCheck) throws RestClientException {
        return getProcessDefinitionsCountWithHttpInfo(processDefinitionId, processDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keysIn, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, startableBy, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, includeProcessDefinitionsWithoutTenantId, versionTag, versionTagLike, withoutVersionTag, startableInTasklist, notStartableInTasklist, startablePermissionCheck).getBody();
    }

    /**
     * Get List Count
     * Requests the number of process definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Definitions](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionIdIn Filter by a comma-separated list of process definition ids. (optional)
     * @param name Filter by process definition name. (optional)
     * @param nameLike Filter by process definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. (optional)
     * @param key Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match. (optional)
     * @param keysIn Filter by a comma-separated list of process definition keys. (optional)
     * @param keyLike Filter by process definition keys that the parameter is a substring of. (optional)
     * @param category Filter by process definition category. Exact match. (optional)
     * @param categoryLike Filter by process definition categories that the parameter is a substring of. (optional)
     * @param version Filter by process definition version. (optional)
     * @param latestVersion Only include those process definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the process definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those process definition resources that the parameter is a substring of. (optional)
     * @param startableBy Filter by a user name who is allowed to start the process. (optional)
     * @param active Only include active process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param incidentId Filter by the incident id. (optional)
     * @param incidentType Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. (optional)
     * @param incidentMessage Filter by the incident message. Exact match. (optional)
     * @param incidentMessageLike Filter by the incident message that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior. (optional)
     * @param includeProcessDefinitionsWithoutTenantId Include process definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tag that the parameter is a substring of. (optional)
     * @param withoutVersionTag Only include process definitions without a &#x60;versionTag&#x60;. (optional)
     * @param startableInTasklist Filter by process definitions which are startable in Tasklist.. (optional)
     * @param notStartableInTasklist Filter by process definitions which are not startable in Tasklist. (optional)
     * @param startablePermissionCheck Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn&#39;t have these permissions the result will be empty list. The permissions are: * &#x60;CREATE&#x60; permission for all Process instances * &#x60;CREATE_INSTANCE&#x60; and &#x60;READ&#x60; permission on Process definition level (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getProcessDefinitionsCountWithHttpInfo(String processDefinitionId, String processDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keysIn, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String startableBy, Boolean active, Boolean suspended, String incidentId, String incidentType, String incidentMessage, String incidentMessageLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeProcessDefinitionsWithoutTenantId, String versionTag, String versionTagLike, Boolean withoutVersionTag, Boolean startableInTasklist, Boolean notStartableInTasklist, Boolean startablePermissionCheck) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/process-definition/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionIdIn", processDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAfter", deployedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAt", deployedAt));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keysIn", keysIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keyLike", keyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryLike", categoryLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "latestVersion", latestVersion));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceNameLike", resourceNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startableBy", startableBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentId", incidentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentType", incidentType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessage", incidentMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "incidentMessageLike", incidentMessageLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeProcessDefinitionsWithoutTenantId", includeProcessDefinitionsWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTag", versionTag));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTagLike", versionTagLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutVersionTag", withoutVersionTag));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startableInTasklist", startableInTasklist));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "notStartableInTasklist", notStartableInTasklist));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startablePermissionCheck", startablePermissionCheck));

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
     * Get Rendered Start Form
     * Retrieves the rendered form for a process definition. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to get the rendered start form for. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getRenderedStartForm(String id) throws RestClientException {
        return getRenderedStartFormWithHttpInfo(id).getBody();
    }

    /**
     * Get Rendered Start Form
     * Retrieves the rendered form for a process definition. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to get the rendered start form for. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getRenderedStartFormWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getRenderedStartForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/rendered-form", uriVariables);

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
     * Get Rendered Start Form
     * Retrieves  the rendered form for the latest version of the process definition which belongs to no tenant. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getRenderedStartFormByKey(String key) throws RestClientException {
        return getRenderedStartFormByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Rendered Start Form
     * Retrieves  the rendered form for the latest version of the process definition which belongs to no tenant. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getRenderedStartFormByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getRenderedStartFormByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/rendered-form", uriVariables);

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
     * Get Rendered Start Form
     * Retrieves  the rendered form for the latest version of the process definition for a tenant. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getRenderedStartFormByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getRenderedStartFormByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Rendered Start Form
     * Retrieves  the rendered form for the latest version of the process definition for a tenant. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getRenderedStartFormByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getRenderedStartFormByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getRenderedStartFormByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/rendered-form", uriVariables);

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
     * Get Start Form Key
     * Retrieves the key of the start form for a process definition. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to get the start form key for. (required)
     * @return FormDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FormDto getStartForm(String id) throws RestClientException {
        return getStartFormWithHttpInfo(id).getBody();
    }

    /**
     * Get Start Form Key
     * Retrieves the key of the start form for a process definition. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to get the start form key for. (required)
     * @return ResponseEntity&lt;FormDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FormDto> getStartFormWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getStartForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/startForm", uriVariables);

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
     * Get Start Form Key
     * Retrieves the key of the start form for the latest version of the process definition which belongs to no tenant. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) for which the form key is to be retrieved. (required)
     * @return FormDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FormDto getStartFormByKey(String key) throws RestClientException {
        return getStartFormByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Start Form Key
     * Retrieves the key of the start form for the latest version of the process definition which belongs to no tenant. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) for which the form key is to be retrieved. (required)
     * @return ResponseEntity&lt;FormDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FormDto> getStartFormByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getStartFormByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/startForm", uriVariables);

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
     * Get Start Form Key
     * Retrieves the key of the start form for the latest version of the process definition for a tenant. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) for which the form key is to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return FormDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FormDto getStartFormByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getStartFormByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Start Form Key
     * Retrieves the key of the start form for the latest version of the process definition for a tenant. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) for which the form key is to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @return ResponseEntity&lt;FormDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FormDto> getStartFormByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getStartFormByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getStartFormByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/startForm", uriVariables);

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
     * Get Start Form Variables
     * Retrieves the start form variables for a process definition (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> - The id is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to retrieve the variables for. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getStartFormVariables(String id, String variableNames, Boolean deserializeValues) throws RestClientException {
        return getStartFormVariablesWithHttpInfo(id, variableNames, deserializeValues).getBody();
    }

    /**
     * Get Start Form Variables
     * Retrieves the start form variables for a process definition (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> - The id is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to retrieve the variables for. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getStartFormVariablesWithHttpInfo(String id, String variableNames, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getStartFormVariables");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/form-variables", uriVariables);

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
     * Get Start Form Variables
     * Retrieves the start form variables for the latest process definition which belongs to no tenant (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> - The key is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getStartFormVariablesByKey(String key, String variableNames, Boolean deserializeValues) throws RestClientException {
        return getStartFormVariablesByKeyWithHttpInfo(key, variableNames, deserializeValues).getBody();
    }

    /**
     * Get Start Form Variables
     * Retrieves the start form variables for the latest process definition which belongs to no tenant (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> - The key is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getStartFormVariablesByKeyWithHttpInfo(String key, String variableNames, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getStartFormVariablesByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/form-variables", uriVariables);

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
     * Get Start Form Variables
     * Retrieves the start form variables for the latest process definition for a tenant (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> - The key is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return Map&lt;String, VariableValueDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Map<String, VariableValueDto> getStartFormVariablesByKeyAndTenantId(String key, String tenantId, String variableNames, Boolean deserializeValues) throws RestClientException {
        return getStartFormVariablesByKeyAndTenantIdWithHttpInfo(key, tenantId, variableNames, deserializeValues).getBody();
    }

    /**
     * Get Start Form Variables
     * Retrieves the start form variables for the latest process definition for a tenant (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.
     * <p><b>200</b> - Request successful. A JSON object containing a property for each variable returned.
     * <p><b>404</b> - The key is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param variableNames A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. (optional)
     * @param deserializeValues Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. (optional, default to true)
     * @return ResponseEntity&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Map<String, VariableValueDto>> getStartFormVariablesByKeyAndTenantIdWithHttpInfo(String key, String tenantId, String variableNames, Boolean deserializeValues) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getStartFormVariablesByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getStartFormVariablesByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/form-variables", uriVariables);

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
     * Get Static Called Process Definitions
     * For the given process, returns a list of called process definitions corresponding to the &#x60;CalledProcessDefinition&#x60; interface in the engine. The list contains all process definitions that are referenced statically by call activities in the given process. This endpoint does not resolve process definitions that are referenced with expressions. Each called process definition contains a list of call activity ids, which specifies the call activities that are calling that process. This endpoint does not resolve references to case definitions.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return List&lt;CalledProcessDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CalledProcessDefinitionDto> getStaticCalledProcessDefinitions(String id) throws RestClientException {
        return getStaticCalledProcessDefinitionsWithHttpInfo(id).getBody();
    }

    /**
     * Get Static Called Process Definitions
     * For the given process, returns a list of called process definitions corresponding to the &#x60;CalledProcessDefinition&#x60; interface in the engine. The list contains all process definitions that are referenced statically by call activities in the given process. This endpoint does not resolve process definitions that are referenced with expressions. Each called process definition contains a list of call activity ids, which specifies the call activities that are calling that process. This endpoint does not resolve references to case definitions.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return ResponseEntity&lt;List&lt;CalledProcessDefinitionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CalledProcessDefinitionDto>> getStaticCalledProcessDefinitionsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getStaticCalledProcessDefinitions");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/static-called-process-definitions", uriVariables);

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

        ParameterizedTypeReference<List<CalledProcessDefinitionDto>> returnType = new ParameterizedTypeReference<List<CalledProcessDefinitionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Restart Process Instance
     * Restarts process instances that were canceled or terminated synchronously. Can also restart completed process instances. It will create a new instance using the original instance information. To execute the restart asynchronously, use the [Restart Process Instance Async](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/post-restart-process-instance-async/) method.  For more information about the difference between synchronous and asynchronous execution, please refer to the related section of the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-restart/#execution).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case following parameters are missing: &#x60;instructions&#x60;, &#x60;activityId&#x60; or &#x60;transitionId&#x60;, &#x60;processInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60;, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition of the process instances to restart. (required)
     * @param restartProcessInstanceDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void restartProcessInstance(String id, RestartProcessInstanceDto restartProcessInstanceDto) throws RestClientException {
        restartProcessInstanceWithHttpInfo(id, restartProcessInstanceDto);
    }

    /**
     * Restart Process Instance
     * Restarts process instances that were canceled or terminated synchronously. Can also restart completed process instances. It will create a new instance using the original instance information. To execute the restart asynchronously, use the [Restart Process Instance Async](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/post-restart-process-instance-async/) method.  For more information about the difference between synchronous and asynchronous execution, please refer to the related section of the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-restart/#execution).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - In case following parameters are missing: &#x60;instructions&#x60;, &#x60;activityId&#x60; or &#x60;transitionId&#x60;, &#x60;processInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60;, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition of the process instances to restart. (required)
     * @param restartProcessInstanceDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> restartProcessInstanceWithHttpInfo(String id, RestartProcessInstanceDto restartProcessInstanceDto) throws RestClientException {
        Object postBody = restartProcessInstanceDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling restartProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/restart", uriVariables);

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
     * Restart Process Instance Async
     * Restarts process instances that were canceled or terminated asynchronously. Can also restart completed process instances. It will create a new instance using the original instance information. To execute the restart asynchronously, use the [Restart Process Instance](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/post-restart-process-instance-sync/) method.  For more information about the difference between synchronous and asynchronous execution, please refer to the related section of the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-restart/#execution).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - In case following parameters are missing: &#x60;instructions&#x60;, &#x60;activityId&#x60; or &#x60;transitionId&#x60;, &#x60;processInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60;, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition of the process instances to restart. (required)
     * @param restartProcessInstanceDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto restartProcessInstanceAsyncOperation(String id, RestartProcessInstanceDto restartProcessInstanceDto) throws RestClientException {
        return restartProcessInstanceAsyncOperationWithHttpInfo(id, restartProcessInstanceDto).getBody();
    }

    /**
     * Restart Process Instance Async
     * Restarts process instances that were canceled or terminated asynchronously. Can also restart completed process instances. It will create a new instance using the original instance information. To execute the restart asynchronously, use the [Restart Process Instance](https://docs.camunda.org/manual/7.16/reference/rest/process-definition/post-restart-process-instance-sync/) method.  For more information about the difference between synchronous and asynchronous execution, please refer to the related section of the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-restart/#execution).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - In case following parameters are missing: &#x60;instructions&#x60;, &#x60;activityId&#x60; or &#x60;transitionId&#x60;, &#x60;processInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60;, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition of the process instances to restart. (required)
     * @param restartProcessInstanceDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> restartProcessInstanceAsyncOperationWithHttpInfo(String id, RestartProcessInstanceDto restartProcessInstanceDto) throws RestClientException {
        Object postBody = restartProcessInstanceDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling restartProcessInstanceAsyncOperation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/restart-async", uriVariables);

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
     * Start Instance
     * Instantiates a given process definition. Process variables and business key may be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to be retrieved. (required)
     * @param startProcessInstanceDto  (optional)
     * @return ProcessInstanceWithVariablesDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceWithVariablesDto startProcessInstance(String id, StartProcessInstanceDto startProcessInstanceDto) throws RestClientException {
        return startProcessInstanceWithHttpInfo(id, startProcessInstanceDto).getBody();
    }

    /**
     * Start Instance
     * Instantiates a given process definition. Process variables and business key may be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to be retrieved. (required)
     * @param startProcessInstanceDto  (optional)
     * @return ResponseEntity&lt;ProcessInstanceWithVariablesDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceWithVariablesDto> startProcessInstanceWithHttpInfo(String id, StartProcessInstanceDto startProcessInstanceDto) throws RestClientException {
        Object postBody = startProcessInstanceDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling startProcessInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/start", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceWithVariablesDto> returnType = new ParameterizedTypeReference<ProcessInstanceWithVariablesDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Start Instance
     * Instantiates a given process definition, starts the latest version of the process definition which belongs to no tenant. Process variables and business key may be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param startProcessInstanceDto  (optional)
     * @return ProcessInstanceWithVariablesDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceWithVariablesDto startProcessInstanceByKey(String key, StartProcessInstanceDto startProcessInstanceDto) throws RestClientException {
        return startProcessInstanceByKeyWithHttpInfo(key, startProcessInstanceDto).getBody();
    }

    /**
     * Start Instance
     * Instantiates a given process definition, starts the latest version of the process definition which belongs to no tenant. Process variables and business key may be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param startProcessInstanceDto  (optional)
     * @return ResponseEntity&lt;ProcessInstanceWithVariablesDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceWithVariablesDto> startProcessInstanceByKeyWithHttpInfo(String key, StartProcessInstanceDto startProcessInstanceDto) throws RestClientException {
        Object postBody = startProcessInstanceDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling startProcessInstanceByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/start", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceWithVariablesDto> returnType = new ParameterizedTypeReference<ProcessInstanceWithVariablesDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Start Instance
     * Instantiates a given process definition, starts the latest version of the process definition for tenant. Process variables and business key may be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param startProcessInstanceDto  (optional)
     * @return ProcessInstanceWithVariablesDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceWithVariablesDto startProcessInstanceByKeyAndTenantId(String key, String tenantId, StartProcessInstanceDto startProcessInstanceDto) throws RestClientException {
        return startProcessInstanceByKeyAndTenantIdWithHttpInfo(key, tenantId, startProcessInstanceDto).getBody();
    }

    /**
     * Start Instance
     * Instantiates a given process definition, starts the latest version of the process definition for tenant. Process variables and business key may be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param startProcessInstanceDto  (optional)
     * @return ResponseEntity&lt;ProcessInstanceWithVariablesDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceWithVariablesDto> startProcessInstanceByKeyAndTenantIdWithHttpInfo(String key, String tenantId, StartProcessInstanceDto startProcessInstanceDto) throws RestClientException {
        Object postBody = startProcessInstanceDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling startProcessInstanceByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling startProcessInstanceByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/start", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceWithVariablesDto> returnType = new ParameterizedTypeReference<ProcessInstanceWithVariablesDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Submit Start Form
     * Starts a process instance using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to submit the form for. (required)
     * @param startProcessInstanceFormDto  (optional)
     * @return ProcessInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceDto submitForm(String id, StartProcessInstanceFormDto startProcessInstanceFormDto) throws RestClientException {
        return submitFormWithHttpInfo(id, startProcessInstanceFormDto).getBody();
    }

    /**
     * Submit Start Form
     * Starts a process instance using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to submit the form for. (required)
     * @param startProcessInstanceFormDto  (optional)
     * @return ResponseEntity&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceDto> submitFormWithHttpInfo(String id, StartProcessInstanceFormDto startProcessInstanceFormDto) throws RestClientException {
        Object postBody = startProcessInstanceFormDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling submitForm");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/submit-form", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceDto> returnType = new ParameterizedTypeReference<ProcessInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Submit Start Form
     * Starts the latest version of the process definition which belongs to no tenant using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to submit the form for. (required)
     * @param startProcessInstanceFormDto  (optional)
     * @return ProcessInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceDto submitFormByKey(String key, StartProcessInstanceFormDto startProcessInstanceFormDto) throws RestClientException {
        return submitFormByKeyWithHttpInfo(key, startProcessInstanceFormDto).getBody();
    }

    /**
     * Submit Start Form
     * Starts the latest version of the process definition which belongs to no tenant using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to submit the form for. (required)
     * @param startProcessInstanceFormDto  (optional)
     * @return ResponseEntity&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceDto> submitFormByKeyWithHttpInfo(String key, StartProcessInstanceFormDto startProcessInstanceFormDto) throws RestClientException {
        Object postBody = startProcessInstanceFormDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling submitFormByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/submit-form", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceDto> returnType = new ParameterizedTypeReference<ProcessInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Submit Start Form
     * Starts the latest version of the process definition for a tenant using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to submit the form for. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param startProcessInstanceFormDto  (optional)
     * @return ProcessInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ProcessInstanceDto submitFormByKeyAndTenantId(String key, String tenantId, StartProcessInstanceFormDto startProcessInstanceFormDto) throws RestClientException {
        return submitFormByKeyAndTenantIdWithHttpInfo(key, tenantId, startProcessInstanceFormDto).getBody();
    }

    /**
     * Submit Start Form
     * Starts the latest version of the process definition for a tenant using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.16/user-guide/task-forms/#generated-task-forms).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to submit the form for. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param startProcessInstanceFormDto  (optional)
     * @return ResponseEntity&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ProcessInstanceDto> submitFormByKeyAndTenantIdWithHttpInfo(String key, String tenantId, StartProcessInstanceFormDto startProcessInstanceFormDto) throws RestClientException {
        Object postBody = startProcessInstanceFormDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling submitFormByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling submitFormByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/submit-form", uriVariables);

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

        ParameterizedTypeReference<ProcessInstanceDto> returnType = new ParameterizedTypeReference<ProcessInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Update History Time to Live
     * Updates history time to live for process definition. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateHistoryTimeToLiveByProcessDefinitionId(String id, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        updateHistoryTimeToLiveByProcessDefinitionIdWithHttpInfo(id, historyTimeToLiveDto);
    }

    /**
     * Update History Time to Live
     * Updates history time to live for process definition. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateHistoryTimeToLiveByProcessDefinitionIdWithHttpInfo(String id, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        Object postBody = historyTimeToLiveDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateHistoryTimeToLiveByProcessDefinitionId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/history-time-to-live", uriVariables);

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
     * Update History Time to Live
     * Updates history time to live for the latest version of the process definition which belongs to no tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateHistoryTimeToLiveByProcessDefinitionKey(String key, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        updateHistoryTimeToLiveByProcessDefinitionKeyWithHttpInfo(key, historyTimeToLiveDto);
    }

    /**
     * Update History Time to Live
     * Updates history time to live for the latest version of the process definition which belongs to no tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateHistoryTimeToLiveByProcessDefinitionKeyWithHttpInfo(String key, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        Object postBody = historyTimeToLiveDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling updateHistoryTimeToLiveByProcessDefinitionKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/history-time-to-live", uriVariables);

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
     * Update History Time to Live
     * Updates history time to live for the latest version of the process definition for a tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to change history time to live. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param historyTimeToLiveDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId(String key, String tenantId, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantIdWithHttpInfo(key, tenantId, historyTimeToLiveDto);
    }

    /**
     * Update History Time to Live
     * Updates history time to live for the latest version of the process definition for a tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition to change history time to live. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param historyTimeToLiveDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantIdWithHttpInfo(String key, String tenantId, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        Object postBody = historyTimeToLiveDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/history-time-to-live", uriVariables);

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
     * Activate/Suspend By Key
     * Activates or suspends process definitions with the given process definition key.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed property is &#x60;processDefinitionId&#x60;. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateProcessDefinitionSuspensionState(ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        updateProcessDefinitionSuspensionStateWithHttpInfo(processDefinitionSuspensionStateDto);
    }

    /**
     * Activate/Suspend By Key
     * Activates or suspends process definitions with the given process definition key.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed property is &#x60;processDefinitionId&#x60;. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateProcessDefinitionSuspensionStateWithHttpInfo(ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        Object postBody = processDefinitionSuspensionStateDto;
        
        String path = apiClient.expandPath("/process-definition/suspended", Collections.<String, Object>emptyMap());

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
     * Activate/Suspend By Id
     * Activates or suspends a given process definition by id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to activate or suspend. (required)
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateProcessDefinitionSuspensionStateById(String id, ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        updateProcessDefinitionSuspensionStateByIdWithHttpInfo(id, processDefinitionSuspensionStateDto);
    }

    /**
     * Activate/Suspend By Id
     * Activates or suspends a given process definition by id.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition to activate or suspend. (required)
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateProcessDefinitionSuspensionStateByIdWithHttpInfo(String id, ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        Object postBody = processDefinitionSuspensionStateDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateProcessDefinitionSuspensionStateById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/process-definition/{id}/suspended", uriVariables);

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
     * Activate/Suspend by Id
     * Activates or suspends a given process definition by latest version of process definition key which belongs to no tenant.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be activated/suspended. (required)
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateProcessDefinitionSuspensionStateByKey(String key, ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        updateProcessDefinitionSuspensionStateByKeyWithHttpInfo(key, processDefinitionSuspensionStateDto);
    }

    /**
     * Activate/Suspend by Id
     * Activates or suspends a given process definition by latest version of process definition key which belongs to no tenant.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be activated/suspended. (required)
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateProcessDefinitionSuspensionStateByKeyWithHttpInfo(String key, ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        Object postBody = processDefinitionSuspensionStateDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling updateProcessDefinitionSuspensionStateByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/process-definition/key/{key}/suspended", uriVariables);

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
     * Activate/Suspend by Id
     * Activates or suspends a given process definition by the latest version of the process definition for tenant.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be activated/suspended. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateProcessDefinitionSuspensionStateByKeyAndTenantId(String key, String tenantId, ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        updateProcessDefinitionSuspensionStateByKeyAndTenantIdWithHttpInfo(key, tenantId, processDefinitionSuspensionStateDto);
    }

    /**
     * Activate/Suspend by Id
     * Activates or suspends a given process definition by the latest version of the process definition for tenant.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the process definition (the latest version thereof) to be activated/suspended. (required)
     * @param tenantId The id of the tenant the process definition belongs to. (required)
     * @param processDefinitionSuspensionStateDto **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateProcessDefinitionSuspensionStateByKeyAndTenantIdWithHttpInfo(String key, String tenantId, ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto) throws RestClientException {
        Object postBody = processDefinitionSuspensionStateDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling updateProcessDefinitionSuspensionStateByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling updateProcessDefinitionSuspensionStateByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/process-definition/key/{key}/tenant-id/{tenant-id}/suspended", uriVariables);

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
