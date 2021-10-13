package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.JobDefinitionDto;
import com.camunda.consulting.openapi.client.model.JobDefinitionPriorityDto;
import com.camunda.consulting.openapi.client.model.JobDefinitionQueryDto;
import com.camunda.consulting.openapi.client.model.JobDefinitionSuspensionStateDto;
import com.camunda.consulting.openapi.client.model.JobDefinitionsSuspensionStateDto;
import com.camunda.consulting.openapi.client.model.RetriesDto;

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
@Component("com.camunda.consulting.openapi.client.handler.JobDefinitionApi")
public class JobDefinitionApi {
    private ApiClient apiClient;

    public JobDefinitionApi() {
        this(new ApiClient());
    }

    @Autowired
    public JobDefinitionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Job Definition
     * Retrieves a job definition by id, according to the &#x60;JobDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Job definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to be retrieved. (required)
     * @return JobDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobDefinitionDto getJobDefinition(String id) throws RestClientException {
        return getJobDefinitionWithHttpInfo(id).getBody();
    }

    /**
     * Get Job Definition
     * Retrieves a job definition by id, according to the &#x60;JobDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Job definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to be retrieved. (required)
     * @return ResponseEntity&lt;JobDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobDefinitionDto> getJobDefinitionWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getJobDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job-definition/{id}", uriVariables);

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

        ParameterizedTypeReference<JobDefinitionDto> returnType = new ParameterizedTypeReference<JobDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Definitions
     * Queries for job definitions that fulfill given parameters. The size of the result set can be retrieved by using the [Get Job Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param activityIdIn Only include job definitions which belong to one of the passed and comma-separated activity ids. (optional)
     * @param processDefinitionId Only include job definitions which exist for the given process definition id. (optional)
     * @param processDefinitionKey Only include job definitions which exist for the given process definition key. (optional)
     * @param jobType Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. (optional)
     * @param jobConfiguration Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. (optional)
     * @param active Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withOverridingJobPriority Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. (optional)
     * @param tenantIdIn Only include job definitions which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobDefinitionsWithoutTenantId Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;JobDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<JobDefinitionDto> getJobDefinitions(String jobDefinitionId, String activityIdIn, String processDefinitionId, String processDefinitionKey, String jobType, String jobConfiguration, Boolean active, Boolean suspended, Boolean withOverridingJobPriority, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobDefinitionsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getJobDefinitionsWithHttpInfo(jobDefinitionId, activityIdIn, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, active, suspended, withOverridingJobPriority, tenantIdIn, withoutTenantId, includeJobDefinitionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Job Definitions
     * Queries for job definitions that fulfill given parameters. The size of the result set can be retrieved by using the [Get Job Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param activityIdIn Only include job definitions which belong to one of the passed and comma-separated activity ids. (optional)
     * @param processDefinitionId Only include job definitions which exist for the given process definition id. (optional)
     * @param processDefinitionKey Only include job definitions which exist for the given process definition key. (optional)
     * @param jobType Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. (optional)
     * @param jobConfiguration Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. (optional)
     * @param active Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withOverridingJobPriority Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. (optional)
     * @param tenantIdIn Only include job definitions which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobDefinitionsWithoutTenantId Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;JobDefinitionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<JobDefinitionDto>> getJobDefinitionsWithHttpInfo(String jobDefinitionId, String activityIdIn, String processDefinitionId, String processDefinitionKey, String jobType, String jobConfiguration, Boolean active, Boolean suspended, Boolean withOverridingJobPriority, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobDefinitionsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/job-definition", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobType", jobType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobConfiguration", jobConfiguration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withOverridingJobPriority", withOverridingJobPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeJobDefinitionsWithoutTenantId", includeJobDefinitionsWithoutTenantId));
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

        ParameterizedTypeReference<List<JobDefinitionDto>> returnType = new ParameterizedTypeReference<List<JobDefinitionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Definition Count
     * Queries for the number of job definitions that fulfill given parameters. Takes the same parameters as the [Get Job Definitions](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param activityIdIn Only include job definitions which belong to one of the passed and comma-separated activity ids. (optional)
     * @param processDefinitionId Only include job definitions which exist for the given process definition id. (optional)
     * @param processDefinitionKey Only include job definitions which exist for the given process definition key. (optional)
     * @param jobType Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. (optional)
     * @param jobConfiguration Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. (optional)
     * @param active Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withOverridingJobPriority Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. (optional)
     * @param tenantIdIn Only include job definitions which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobDefinitionsWithoutTenantId Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getJobDefinitionsCount(String jobDefinitionId, String activityIdIn, String processDefinitionId, String processDefinitionKey, String jobType, String jobConfiguration, Boolean active, Boolean suspended, Boolean withOverridingJobPriority, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobDefinitionsWithoutTenantId) throws RestClientException {
        return getJobDefinitionsCountWithHttpInfo(jobDefinitionId, activityIdIn, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, active, suspended, withOverridingJobPriority, tenantIdIn, withoutTenantId, includeJobDefinitionsWithoutTenantId).getBody();
    }

    /**
     * Get Job Definition Count
     * Queries for the number of job definitions that fulfill given parameters. Takes the same parameters as the [Get Job Definitions](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionId Filter by job definition id. (optional)
     * @param activityIdIn Only include job definitions which belong to one of the passed and comma-separated activity ids. (optional)
     * @param processDefinitionId Only include job definitions which exist for the given process definition id. (optional)
     * @param processDefinitionKey Only include job definitions which exist for the given process definition key. (optional)
     * @param jobType Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. (optional)
     * @param jobConfiguration Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. (optional)
     * @param active Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withOverridingJobPriority Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. (optional)
     * @param tenantIdIn Only include job definitions which belong to one of the passed and comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobDefinitionsWithoutTenantId Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getJobDefinitionsCountWithHttpInfo(String jobDefinitionId, String activityIdIn, String processDefinitionId, String processDefinitionKey, String jobType, String jobConfiguration, Boolean active, Boolean suspended, Boolean withOverridingJobPriority, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobDefinitionsWithoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/job-definition/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobType", jobType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobConfiguration", jobConfiguration));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withOverridingJobPriority", withOverridingJobPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeJobDefinitionsWithoutTenantId", includeJobDefinitionsWithoutTenantId));

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
     * Get Job Definitions (POST)
     * Queries for job definitions that fulfill given parameters. This method is slightly more powerful than the [Get Job Definitions](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query/) method because it allows filtering by multiple job definitions of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param jobDefinitionQueryDto  (optional)
     * @return List&lt;JobDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<JobDefinitionDto> queryJobDefinitions(Integer firstResult, Integer maxResults, JobDefinitionQueryDto jobDefinitionQueryDto) throws RestClientException {
        return queryJobDefinitionsWithHttpInfo(firstResult, maxResults, jobDefinitionQueryDto).getBody();
    }

    /**
     * Get Job Definitions (POST)
     * Queries for job definitions that fulfill given parameters. This method is slightly more powerful than the [Get Job Definitions](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query/) method because it allows filtering by multiple job definitions of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param jobDefinitionQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;JobDefinitionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<JobDefinitionDto>> queryJobDefinitionsWithHttpInfo(Integer firstResult, Integer maxResults, JobDefinitionQueryDto jobDefinitionQueryDto) throws RestClientException {
        Object postBody = jobDefinitionQueryDto;
        
        String path = apiClient.expandPath("/job-definition", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<JobDefinitionDto>> returnType = new ParameterizedTypeReference<List<JobDefinitionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Definition Count (POST)
     * Queries for the number of job definitions that fulfill given parameters. This method takes the same message body as the [Get Job Definitions (POST)](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/post-query/) method and therefore it is slightly more powerful than the [Get Job Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryJobDefinitionsCount(JobDefinitionQueryDto jobDefinitionQueryDto) throws RestClientException {
        return queryJobDefinitionsCountWithHttpInfo(jobDefinitionQueryDto).getBody();
    }

    /**
     * Get Job Definition Count (POST)
     * Queries for the number of job definitions that fulfill given parameters. This method takes the same message body as the [Get Job Definitions (POST)](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/post-query/) method and therefore it is slightly more powerful than the [Get Job Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryJobDefinitionsCountWithHttpInfo(JobDefinitionQueryDto jobDefinitionQueryDto) throws RestClientException {
        Object postBody = jobDefinitionQueryDto;
        
        String path = apiClient.expandPath("/job-definition/count", Collections.<String, Object>emptyMap());

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
     * Set Job Definition Priority by Id
     * Sets an overriding execution priority for jobs with the given definition id. Optionally, the priorities of all the definitions&#39; existing jobs are updated accordingly. The priority can be reset by setting it to &#x60;null&#x60;, meaning that a new job&#39;s priority will not be determined based on its definition&#39;s priority any longer. See the [user guide on job prioritization](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#set-job-definition-priorities-via-managementservice-api) for details.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to be updated. (required)
     * @param jobDefinitionPriorityDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setJobPriorityJobDefinition(String id, JobDefinitionPriorityDto jobDefinitionPriorityDto) throws RestClientException {
        setJobPriorityJobDefinitionWithHttpInfo(id, jobDefinitionPriorityDto);
    }

    /**
     * Set Job Definition Priority by Id
     * Sets an overriding execution priority for jobs with the given definition id. Optionally, the priorities of all the definitions&#39; existing jobs are updated accordingly. The priority can be reset by setting it to &#x60;null&#x60;, meaning that a new job&#39;s priority will not be determined based on its definition&#39;s priority any longer. See the [user guide on job prioritization](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#set-job-definition-priorities-via-managementservice-api) for details.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to be updated. (required)
     * @param jobDefinitionPriorityDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setJobPriorityJobDefinitionWithHttpInfo(String id, JobDefinitionPriorityDto jobDefinitionPriorityDto) throws RestClientException {
        Object postBody = jobDefinitionPriorityDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setJobPriorityJobDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job-definition/{id}/jobPriority", uriVariables);

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
     * Set Job Retries By Job Definition Id
     * Sets the number of retries of all **failed** jobs associated with the given job definition id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>500</b> - The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to be updated. (required)
     * @param retriesDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setJobRetriesJobDefinition(String id, RetriesDto retriesDto) throws RestClientException {
        setJobRetriesJobDefinitionWithHttpInfo(id, retriesDto);
    }

    /**
     * Set Job Retries By Job Definition Id
     * Sets the number of retries of all **failed** jobs associated with the given job definition id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>500</b> - The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to be updated. (required)
     * @param retriesDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setJobRetriesJobDefinitionWithHttpInfo(String id, RetriesDto retriesDto) throws RestClientException {
        Object postBody = retriesDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setJobRetriesJobDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job-definition/{id}/retries", uriVariables);

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
     * Activate/Suspend Job Definition By Id
     * Activates or suspends a given job definition by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to activate or suspend. (required)
     * @param jobDefinitionSuspensionStateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateSuspensionStateJobDefinition(String id, JobDefinitionSuspensionStateDto jobDefinitionSuspensionStateDto) throws RestClientException {
        updateSuspensionStateJobDefinitionWithHttpInfo(id, jobDefinitionSuspensionStateDto);
    }

    /**
     * Activate/Suspend Job Definition By Id
     * Activates or suspends a given job definition by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job definition to activate or suspend. (required)
     * @param jobDefinitionSuspensionStateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateSuspensionStateJobDefinitionWithHttpInfo(String id, JobDefinitionSuspensionStateDto jobDefinitionSuspensionStateDto) throws RestClientException {
        Object postBody = jobDefinitionSuspensionStateDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateSuspensionStateJobDefinition");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job-definition/{id}/suspended", uriVariables);

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
     * Activate/Suspend Job Definitions
     * Activates or suspends job definitions with the given process definition id or process definition key.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionsSuspensionStateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateSuspensionStateJobDefinitions(JobDefinitionsSuspensionStateDto jobDefinitionsSuspensionStateDto) throws RestClientException {
        updateSuspensionStateJobDefinitionsWithHttpInfo(jobDefinitionsSuspensionStateDto);
    }

    /**
     * Activate/Suspend Job Definitions
     * Activates or suspends job definitions with the given process definition id or process definition key.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the request parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobDefinitionsSuspensionStateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateSuspensionStateJobDefinitionsWithHttpInfo(JobDefinitionsSuspensionStateDto jobDefinitionsSuspensionStateDto) throws RestClientException {
        Object postBody = jobDefinitionsSuspensionStateDto;
        
        String path = apiClient.expandPath("/job-definition/suspended", Collections.<String, Object>emptyMap());

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
