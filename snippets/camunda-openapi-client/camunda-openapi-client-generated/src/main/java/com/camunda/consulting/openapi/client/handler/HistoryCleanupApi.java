package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoryCleanupConfigurationDto;
import com.camunda.consulting.openapi.client.model.JobDto;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoryCleanupApi")
public class HistoryCleanupApi {
    private ApiClient apiClient;

    public HistoryCleanupApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoryCleanupApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Clean up history (POST)
     * Schedules asynchronous history cleanup (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).  **Note:** This endpoint will return at most a single history cleanup job. Since version &#x60;7.9.0&#x60; it is possible to configure multiple [parallel history cleanup jobs](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#parallel-execution). Use [&#x60;GET /history/cleanup/jobs&#x60;](https://docs.camunda.org/manual/7.16/reference/rest/history/history-cleanup/get-history-cleanup-jobs) to find all the available history cleanup jobs.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid or the engine does not participate in history cleanup. See [Cleanup Execution Participation per Node](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#cleanup-execution-participation-per-node).
     * @param immediatelyDue When true the job will be scheduled for nearest future. When &#x60;false&#x60;, the job will be scheduled for next batch window start time. Default is &#x60;true&#x60;. (optional)
     * @return JobDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public JobDto cleanupAsync(Boolean immediatelyDue) throws RestClientException {
        return cleanupAsyncWithHttpInfo(immediatelyDue).getBody();
    }

    /**
     * Clean up history (POST)
     * Schedules asynchronous history cleanup (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).  **Note:** This endpoint will return at most a single history cleanup job. Since version &#x60;7.9.0&#x60; it is possible to configure multiple [parallel history cleanup jobs](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#parallel-execution). Use [&#x60;GET /history/cleanup/jobs&#x60;](https://docs.camunda.org/manual/7.16/reference/rest/history/history-cleanup/get-history-cleanup-jobs) to find all the available history cleanup jobs.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid or the engine does not participate in history cleanup. See [Cleanup Execution Participation per Node](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#cleanup-execution-participation-per-node).
     * @param immediatelyDue When true the job will be scheduled for nearest future. When &#x60;false&#x60;, the job will be scheduled for next batch window start time. Default is &#x60;true&#x60;. (optional)
     * @return ResponseEntity&lt;JobDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<JobDto> cleanupAsyncWithHttpInfo(Boolean immediatelyDue) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/cleanup", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "immediatelyDue", immediatelyDue));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<JobDto> returnType = new ParameterizedTypeReference<JobDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Find clean up history job (GET)
     * **Deprecated!** Use &#x60;GET /history/cleanup/jobs&#x60; instead.  Finds history cleanup job (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - History clean up job does not exist.
     * @return JobDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    @Deprecated
    public JobDto findCleanupJob() throws RestClientException {
        return findCleanupJobWithHttpInfo().getBody();
    }

    /**
     * Find clean up history job (GET)
     * **Deprecated!** Use &#x60;GET /history/cleanup/jobs&#x60; instead.  Finds history cleanup job (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - History clean up job does not exist.
     * @return ResponseEntity&lt;JobDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    @Deprecated
    public ResponseEntity<JobDto> findCleanupJobWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/cleanup/job", Collections.<String, Object>emptyMap());

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
     * Find clean up history jobs (GET)
     * Finds history cleanup jobs (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - History clean up jobs are empty.
     * @return List&lt;JobDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<JobDto> findCleanupJobs() throws RestClientException {
        return findCleanupJobsWithHttpInfo().getBody();
    }

    /**
     * Find clean up history jobs (GET)
     * Finds history cleanup jobs (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - History clean up jobs are empty.
     * @return ResponseEntity&lt;List&lt;JobDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<JobDto>> findCleanupJobsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/cleanup/jobs", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<JobDto>> returnType = new ParameterizedTypeReference<List<JobDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get History Cleanup Configuration
     * Retrieves history cleanup batch window configuration (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).
     * <p><b>200</b> - Request successful.
     * @return HistoryCleanupConfigurationDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoryCleanupConfigurationDto getHistoryCleanupConfiguration() throws RestClientException {
        return getHistoryCleanupConfigurationWithHttpInfo().getBody();
    }

    /**
     * Get History Cleanup Configuration
     * Retrieves history cleanup batch window configuration (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).
     * <p><b>200</b> - Request successful.
     * @return ResponseEntity&lt;HistoryCleanupConfigurationDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoryCleanupConfigurationDto> getHistoryCleanupConfigurationWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/cleanup/configuration", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<HistoryCleanupConfigurationDto> returnType = new ParameterizedTypeReference<HistoryCleanupConfigurationDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
