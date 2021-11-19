package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.JobDto;
import com.camunda.consulting.openapi.client.model.JobDuedateDto;
import com.camunda.consulting.openapi.client.model.JobQueryDto;
import com.camunda.consulting.openapi.client.model.JobSuspensionStateDto;
import com.camunda.consulting.openapi.client.model.PriorityDto;
import com.camunda.consulting.openapi.client.model.RetriesDto;
import com.camunda.consulting.openapi.client.model.SetJobRetriesDto;
import com.camunda.consulting.openapi.client.model.SuspensionStateDto;

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
@Component("com.camunda.consulting.openapi.client.handler.JobApi")
public class JobApi {
    private ApiClient apiClient;

    public JobApi() {
        this(new ApiClient());
    }

    @Autowired
    public JobApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Execute Job
     * Executes a job by id. **Note:** The execution of the job happens synchronously in the same thread.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The job could not be executed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be executed. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void executeJob(String id) throws RestClientException {
        executeJobWithHttpInfo(id);
    }

    /**
     * Execute Job
     * Executes a job by id. **Note:** The execution of the job happens synchronously in the same thread.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The job could not be executed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be executed. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> executeJobWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling executeJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/execute", uriVariables);

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
     * Get Job
     * Retrieves a job by id, according to the &#x60;Job&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be retrieved. (required)
     * @return JobDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobDto getJob(String id) throws RestClientException {
        return getJobWithHttpInfo(id).getBody();
    }

    /**
     * Get Job
     * Retrieves a job by id, according to the &#x60;Job&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be retrieved. (required)
     * @return ResponseEntity&lt;JobDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobDto> getJobWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getJob");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}", uriVariables);

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

        ParameterizedTypeReference<JobDto> returnType = new ParameterizedTypeReference<JobDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Jobs
     * Queries for jobs that fulfill given parameters. The size of the result set can be retrieved by using the [Get Job Count](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobId Filter by job id. (optional)
     * @param jobIds Filter by a comma-separated list of job ids. (optional)
     * @param jobDefinitionId Only select jobs which exist for the given job definition. (optional)
     * @param processInstanceId Only select jobs which exist for the given process instance. (optional)
     * @param processInstanceIds Only select jobs which exist for the given comma-separated list of process instance ids. (optional)
     * @param executionId Only select jobs which exist for the given execution. (optional)
     * @param processDefinitionId Filter by the id of the process definition the jobs run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the jobs run on. (optional)
     * @param activityId Only select jobs which exist for an activity with the given id. (optional)
     * @param withRetriesLeft Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executable Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param timers Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param messages Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param dueDates Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param createTimes Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param withException Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param exceptionMessage Only select jobs that failed due to an exception with the given message. (optional)
     * @param failedActivityId Only select jobs that failed due to an exception at an activity with the given id. (optional)
     * @param noRetriesLeft Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param active Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param tenantIdIn Only include jobs which belong to one of the passed comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobsWithoutTenantId Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;JobDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<JobDto> getJobs(String jobId, String jobIds, String jobDefinitionId, String processInstanceId, String processInstanceIds, String executionId, String processDefinitionId, String processDefinitionKey, String activityId, Boolean withRetriesLeft, Boolean executable, Boolean timers, Boolean messages, String dueDates, String createTimes, Boolean withException, String exceptionMessage, String failedActivityId, Boolean noRetriesLeft, Boolean active, Boolean suspended, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getJobsWithHttpInfo(jobId, jobIds, jobDefinitionId, processInstanceId, processInstanceIds, executionId, processDefinitionId, processDefinitionKey, activityId, withRetriesLeft, executable, timers, messages, dueDates, createTimes, withException, exceptionMessage, failedActivityId, noRetriesLeft, active, suspended, priorityLowerThanOrEquals, priorityHigherThanOrEquals, tenantIdIn, withoutTenantId, includeJobsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Jobs
     * Queries for jobs that fulfill given parameters. The size of the result set can be retrieved by using the [Get Job Count](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobId Filter by job id. (optional)
     * @param jobIds Filter by a comma-separated list of job ids. (optional)
     * @param jobDefinitionId Only select jobs which exist for the given job definition. (optional)
     * @param processInstanceId Only select jobs which exist for the given process instance. (optional)
     * @param processInstanceIds Only select jobs which exist for the given comma-separated list of process instance ids. (optional)
     * @param executionId Only select jobs which exist for the given execution. (optional)
     * @param processDefinitionId Filter by the id of the process definition the jobs run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the jobs run on. (optional)
     * @param activityId Only select jobs which exist for an activity with the given id. (optional)
     * @param withRetriesLeft Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executable Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param timers Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param messages Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param dueDates Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param createTimes Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param withException Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param exceptionMessage Only select jobs that failed due to an exception with the given message. (optional)
     * @param failedActivityId Only select jobs that failed due to an exception at an activity with the given id. (optional)
     * @param noRetriesLeft Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param active Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param tenantIdIn Only include jobs which belong to one of the passed comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobsWithoutTenantId Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;JobDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<JobDto>> getJobsWithHttpInfo(String jobId, String jobIds, String jobDefinitionId, String processInstanceId, String processInstanceIds, String executionId, String processDefinitionId, String processDefinitionKey, String activityId, Boolean withRetriesLeft, Boolean executable, Boolean timers, Boolean messages, String dueDates, String createTimes, Boolean withException, String exceptionMessage, String failedActivityId, Boolean noRetriesLeft, Boolean active, Boolean suspended, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/job", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobId", jobId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobIds", jobIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIds", processInstanceIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRetriesLeft", withRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executable", executable));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "timers", timers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "messages", messages));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueDates", dueDates));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createTimes", createTimes));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withException", withException));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "exceptionMessage", exceptionMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityId", failedActivityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "noRetriesLeft", noRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityLowerThanOrEquals", priorityLowerThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityHigherThanOrEquals", priorityHigherThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeJobsWithoutTenantId", includeJobsWithoutTenantId));
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

        ParameterizedTypeReference<List<JobDto>> returnType = new ParameterizedTypeReference<List<JobDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Count
     * Queries for the number of jobs that fulfill given parameters. Takes the same parameters as the [Get Jobs](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobId Filter by job id. (optional)
     * @param jobIds Filter by a comma-separated list of job ids. (optional)
     * @param jobDefinitionId Only select jobs which exist for the given job definition. (optional)
     * @param processInstanceId Only select jobs which exist for the given process instance. (optional)
     * @param processInstanceIds Only select jobs which exist for the given comma-separated list of process instance ids. (optional)
     * @param executionId Only select jobs which exist for the given execution. (optional)
     * @param processDefinitionId Filter by the id of the process definition the jobs run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the jobs run on. (optional)
     * @param activityId Only select jobs which exist for an activity with the given id. (optional)
     * @param withRetriesLeft Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executable Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param timers Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param messages Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param dueDates Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param createTimes Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param withException Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param exceptionMessage Only select jobs that failed due to an exception with the given message. (optional)
     * @param failedActivityId Only select jobs that failed due to an exception at an activity with the given id. (optional)
     * @param noRetriesLeft Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param active Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param tenantIdIn Only include jobs which belong to one of the passed comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobsWithoutTenantId Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getJobsCount(String jobId, String jobIds, String jobDefinitionId, String processInstanceId, String processInstanceIds, String executionId, String processDefinitionId, String processDefinitionKey, String activityId, Boolean withRetriesLeft, Boolean executable, Boolean timers, Boolean messages, String dueDates, String createTimes, Boolean withException, String exceptionMessage, String failedActivityId, Boolean noRetriesLeft, Boolean active, Boolean suspended, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobsWithoutTenantId) throws RestClientException {
        return getJobsCountWithHttpInfo(jobId, jobIds, jobDefinitionId, processInstanceId, processInstanceIds, executionId, processDefinitionId, processDefinitionKey, activityId, withRetriesLeft, executable, timers, messages, dueDates, createTimes, withException, exceptionMessage, failedActivityId, noRetriesLeft, active, suspended, priorityLowerThanOrEquals, priorityHigherThanOrEquals, tenantIdIn, withoutTenantId, includeJobsWithoutTenantId).getBody();
    }

    /**
     * Get Job Count
     * Queries for the number of jobs that fulfill given parameters. Takes the same parameters as the [Get Jobs](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobId Filter by job id. (optional)
     * @param jobIds Filter by a comma-separated list of job ids. (optional)
     * @param jobDefinitionId Only select jobs which exist for the given job definition. (optional)
     * @param processInstanceId Only select jobs which exist for the given process instance. (optional)
     * @param processInstanceIds Only select jobs which exist for the given comma-separated list of process instance ids. (optional)
     * @param executionId Only select jobs which exist for the given execution. (optional)
     * @param processDefinitionId Filter by the id of the process definition the jobs run on. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the jobs run on. (optional)
     * @param activityId Only select jobs which exist for an activity with the given id. (optional)
     * @param withRetriesLeft Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param executable Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param timers Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param messages Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param dueDates Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param createTimes Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param withException Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param exceptionMessage Only select jobs that failed due to an exception with the given message. (optional)
     * @param failedActivityId Only select jobs that failed due to an exception at an activity with the given id. (optional)
     * @param noRetriesLeft Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param active Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param suspended Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param priorityLowerThanOrEquals Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param priorityHigherThanOrEquals Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. (optional)
     * @param tenantIdIn Only include jobs which belong to one of the passed comma-separated tenant ids. (optional)
     * @param withoutTenantId Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeJobsWithoutTenantId Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getJobsCountWithHttpInfo(String jobId, String jobIds, String jobDefinitionId, String processInstanceId, String processInstanceIds, String executionId, String processDefinitionId, String processDefinitionKey, String activityId, Boolean withRetriesLeft, Boolean executable, Boolean timers, Boolean messages, String dueDates, String createTimes, Boolean withException, String exceptionMessage, String failedActivityId, Boolean noRetriesLeft, Boolean active, Boolean suspended, Long priorityLowerThanOrEquals, Long priorityHigherThanOrEquals, String tenantIdIn, Boolean withoutTenantId, Boolean includeJobsWithoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/job/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobId", jobId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobIds", jobIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "jobDefinitionId", jobDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceIds", processInstanceIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withRetriesLeft", withRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executable", executable));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "timers", timers));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "messages", messages));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dueDates", dueDates));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "createTimes", createTimes));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withException", withException));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "exceptionMessage", exceptionMessage));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "failedActivityId", failedActivityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "noRetriesLeft", noRetriesLeft));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "active", active));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "suspended", suspended));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityLowerThanOrEquals", priorityLowerThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "priorityHigherThanOrEquals", priorityHigherThanOrEquals));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeJobsWithoutTenantId", includeJobsWithoutTenantId));

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
     * Get Exception Stacktrace
     * Retrieves the exception stacktrace corresponding to the passed job id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to get the exception stacktrace for. (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object getStacktrace(String id) throws RestClientException {
        return getStacktraceWithHttpInfo(id).getBody();
    }

    /**
     * Get Exception Stacktrace
     * Retrieves the exception stacktrace corresponding to the passed job id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to get the exception stacktrace for. (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> getStacktraceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getStacktrace");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/stacktrace", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Jobs (POST)
     * Queries for jobs that fulfill given parameters. This method is slightly more powerful than the [Get Jobs](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query/) method because it allows filtering by multiple jobs of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param jobQueryDto  (optional)
     * @return List&lt;JobDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<JobDto> queryJobs(Integer firstResult, Integer maxResults, JobQueryDto jobQueryDto) throws RestClientException {
        return queryJobsWithHttpInfo(firstResult, maxResults, jobQueryDto).getBody();
    }

    /**
     * Get Jobs (POST)
     * Queries for jobs that fulfill given parameters. This method is slightly more powerful than the [Get Jobs](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query/) method because it allows filtering by multiple jobs of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param jobQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;JobDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<JobDto>> queryJobsWithHttpInfo(Integer firstResult, Integer maxResults, JobQueryDto jobQueryDto) throws RestClientException {
        Object postBody = jobQueryDto;
        
        String path = apiClient.expandPath("/job", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<JobDto>> returnType = new ParameterizedTypeReference<List<JobDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Job Count (POST)
     * Queries for jobs that fulfill given parameters. This method takes the same message body as the [Get Jobs POST](https://docs.camunda.org/manual/7.16/reference/rest/job/post- query/) method and therefore it is slightly more powerful than the [Get Job Count](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryJobsCount(JobQueryDto jobQueryDto) throws RestClientException {
        return queryJobsCountWithHttpInfo(jobQueryDto).getBody();
    }

    /**
     * Get Job Count (POST)
     * Queries for jobs that fulfill given parameters. This method takes the same message body as the [Get Jobs POST](https://docs.camunda.org/manual/7.16/reference/rest/job/post- query/) method and therefore it is slightly more powerful than the [Get Job Count](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryJobsCountWithHttpInfo(JobQueryDto jobQueryDto) throws RestClientException {
        Object postBody = jobQueryDto;
        
        String path = apiClient.expandPath("/job/count", Collections.<String, Object>emptyMap());

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
     * Recalculate Job Due Date
     * Recalculates the due date of a job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The due date could not be recalculated successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param creationDateBased Recalculate the due date based on the creation date of the job or the current date. Value may only be &#x60;false&#x60;, as &#x60;true&#x60; is the default behavior.  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void recalculateDuedate(String id, Boolean creationDateBased) throws RestClientException {
        recalculateDuedateWithHttpInfo(id, creationDateBased);
    }

    /**
     * Recalculate Job Due Date
     * Recalculates the due date of a job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The due date could not be recalculated successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param creationDateBased Recalculate the due date based on the creation date of the job or the current date. Value may only be &#x60;false&#x60;, as &#x60;true&#x60; is the default behavior.  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> recalculateDuedateWithHttpInfo(String id, Boolean creationDateBased) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling recalculateDuedate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/duedate/recalculate", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "creationDateBased", creationDateBased));

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
     * Set Job Due Date
     * Updates the due date of a job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The due date could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param jobDuedateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setJobDuedate(String id, JobDuedateDto jobDuedateDto) throws RestClientException {
        setJobDuedateWithHttpInfo(id, jobDuedateDto);
    }

    /**
     * Set Job Due Date
     * Updates the due date of a job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The due date could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param jobDuedateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setJobDuedateWithHttpInfo(String id, JobDuedateDto jobDuedateDto) throws RestClientException {
        Object postBody = jobDuedateDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setJobDuedate");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/duedate", uriVariables);

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
     * Set Job Priority
     * Sets the execution priority of a job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The priority could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param priorityDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setJobPriority(String id, PriorityDto priorityDto) throws RestClientException {
        setJobPriorityWithHttpInfo(id, priorityDto);
    }

    /**
     * Set Job Priority
     * Sets the execution priority of a job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The priority could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param priorityDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setJobPriorityWithHttpInfo(String id, PriorityDto priorityDto) throws RestClientException {
        Object postBody = priorityDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setJobPriority");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/priority", uriVariables);

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
     * Set Job Retries
     * Sets the retries of the job to the given number of retries by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param retriesDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void setJobRetries(String id, RetriesDto retriesDto) throws RestClientException {
        setJobRetriesWithHttpInfo(id, retriesDto);
    }

    /**
     * Set Job Retries
     * Sets the retries of the job to the given number of retries by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the job to be updated. (required)
     * @param retriesDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> setJobRetriesWithHttpInfo(String id, RetriesDto retriesDto) throws RestClientException {
        Object postBody = retriesDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling setJobRetries");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/retries", uriVariables);

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
     * Set Job Retries Async (POST)
     * Create a batch to set retries of jobs asynchronously.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if neither processInstanceIds nor processInstanceQuery is present. Or if the retry count is not specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setJobRetriesDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setJobRetriesAsyncOperation(SetJobRetriesDto setJobRetriesDto) throws RestClientException {
        return setJobRetriesAsyncOperationWithHttpInfo(setJobRetriesDto).getBody();
    }

    /**
     * Set Job Retries Async (POST)
     * Create a batch to set retries of jobs asynchronously.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if neither processInstanceIds nor processInstanceQuery is present. Or if the retry count is not specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setJobRetriesDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setJobRetriesAsyncOperationWithHttpInfo(SetJobRetriesDto setJobRetriesDto) throws RestClientException {
        Object postBody = setJobRetriesDto;
        
        String path = apiClient.expandPath("/job/retries", Collections.<String, Object>emptyMap());

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
     * Activate/Suspend Job By Id
     * Activates or suspends a given job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * @param id The id of the job to activate or suspend. (required)
     * @param suspensionStateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateJobSuspensionState(String id, SuspensionStateDto suspensionStateDto) throws RestClientException {
        updateJobSuspensionStateWithHttpInfo(id, suspensionStateDto);
    }

    /**
     * Activate/Suspend Job By Id
     * Activates or suspends a given job by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * @param id The id of the job to activate or suspend. (required)
     * @param suspensionStateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateJobSuspensionStateWithHttpInfo(String id, SuspensionStateDto suspensionStateDto) throws RestClientException {
        Object postBody = suspensionStateDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateJobSuspensionState");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/job/{id}/suspended", uriVariables);

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
    /**
     * Activate/Suspend Jobs
     * Activates or suspends jobs matching the given criterion. This can only be on of: * &#x60;jobDefinitionId&#x60; * &#x60;processDefinitionId&#x60; * &#x60;processInstanceId&#x60; * &#x60;processDefinitionKey&#x60;
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if the request parameters are invalid, for example, if &#x60;jobDefinitionId&#x60; and &#x60;processDefinitionId&#x60; are both specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobSuspensionStateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateSuspensionStateBy(JobSuspensionStateDto jobSuspensionStateDto) throws RestClientException {
        updateSuspensionStateByWithHttpInfo(jobSuspensionStateDto);
    }

    /**
     * Activate/Suspend Jobs
     * Activates or suspends jobs matching the given criterion. This can only be on of: * &#x60;jobDefinitionId&#x60; * &#x60;processDefinitionId&#x60; * &#x60;processInstanceId&#x60; * &#x60;processDefinitionKey&#x60;
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if the request parameters are invalid, for example, if &#x60;jobDefinitionId&#x60; and &#x60;processDefinitionId&#x60; are both specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param jobSuspensionStateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateSuspensionStateByWithHttpInfo(JobSuspensionStateDto jobSuspensionStateDto) throws RestClientException {
        Object postBody = jobSuspensionStateDto;
        
        String path = apiClient.expandPath("/job/suspended", Collections.<String, Object>emptyMap());

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
