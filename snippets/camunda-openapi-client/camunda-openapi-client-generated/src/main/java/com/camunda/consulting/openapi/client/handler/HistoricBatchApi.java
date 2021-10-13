package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CleanableHistoricBatchReportResultDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricBatchDto;
import com.camunda.consulting.openapi.client.model.SetRemovalTimeToHistoricBatchesDto;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricBatchApi")
public class HistoricBatchApi {
    private ApiClient apiClient;

    public HistoricBatchApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricBatchApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete Historic Batch
     * Deletes a historic batch by id, including related historic job logs.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Historic batch with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the batch to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteHistoricBatch(String id) throws RestClientException {
        deleteHistoricBatchWithHttpInfo(id);
    }

    /**
     * Delete Historic Batch
     * Deletes a historic batch by id, including related historic job logs.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>404</b> - Historic batch with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the batch to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteHistoricBatchWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteHistoricBatch");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/batch/{id}", uriVariables);

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
     * Get Cleanable Batch Report
     * Retrieves a report about a historic batch operations relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup) ) so that you can tune the history time to live. These reports include the count of the finished batches, cleanable batches and type of the batch. The size of the result set can be retrieved by using the [Get Cleanable Batch Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-cleanable-batch-report-count/) method.  **Please note:** The history time to live for batch operations does not support [Multi-Tenancy](https://docs.camunda.org/manual/7.16/user-guide/process-engine/multi-tenancy.md). The report will return an information for all batch operations (for all tenants) if you have permissions to see the history. 
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;CleanableHistoricBatchReportResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CleanableHistoricBatchReportResultDto> getCleanableHistoricBatchesReport(String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getCleanableHistoricBatchesReportWithHttpInfo(sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Cleanable Batch Report
     * Retrieves a report about a historic batch operations relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup) ) so that you can tune the history time to live. These reports include the count of the finished batches, cleanable batches and type of the batch. The size of the result set can be retrieved by using the [Get Cleanable Batch Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-cleanable-batch-report-count/) method.  **Please note:** The history time to live for batch operations does not support [Multi-Tenancy](https://docs.camunda.org/manual/7.16/user-guide/process-engine/multi-tenancy.md). The report will return an information for all batch operations (for all tenants) if you have permissions to see the history. 
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;CleanableHistoricBatchReportResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CleanableHistoricBatchReportResultDto>> getCleanableHistoricBatchesReportWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/batch/cleanable-batch-report", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

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

        ParameterizedTypeReference<List<CleanableHistoricBatchReportResultDto>> returnType = new ParameterizedTypeReference<List<CleanableHistoricBatchReportResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Cleanable Batch Report Count
     * Queries for the number of report results about a historic batch operations relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup) ). Takes the same parameters as the [Get Cleanable Batch Report](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-cleanable-batch-report/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getCleanableHistoricBatchesReportCount(String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getCleanableHistoricBatchesReportCountWithHttpInfo(sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Cleanable Batch Report Count
     * Queries for the number of report results about a historic batch operations relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup) ). Takes the same parameters as the [Get Cleanable Batch Report](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-cleanable-batch-report/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getCleanableHistoricBatchesReportCountWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/batch/cleanable-batch-report/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

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

        ParameterizedTypeReference<CountResultDto> returnType = new ParameterizedTypeReference<CountResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Batch
     * Retrieves a historic batch by id, according to the &#x60;HistoricBatch&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> -  Historic batch with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the historic batch to be retrieved. (required)
     * @return HistoricBatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricBatchDto getHistoricBatch(String id) throws RestClientException {
        return getHistoricBatchWithHttpInfo(id).getBody();
    }

    /**
     * Get Historic Batch
     * Retrieves a historic batch by id, according to the &#x60;HistoricBatch&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> -  Historic batch with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the historic batch to be retrieved. (required)
     * @return ResponseEntity&lt;HistoricBatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricBatchDto> getHistoricBatchWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricBatch");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/batch/{id}", uriVariables);

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

        ParameterizedTypeReference<HistoricBatchDto> returnType = new ParameterizedTypeReference<HistoricBatchDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Batches
     * Queries for historic batches that fulfill given parameters. Parameters may be the properties of batches, such as the id or type. The size of the result set can be retrieved by using the [Get Historic Batch Count](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param batchId Filter by batch id. (optional)
     * @param type Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. (optional)
     * @param completed  Filter completed or not completed batches. If the value is &#x60;true&#x60;, only completed batches, i.e., end time is set, are returned. Otherwise, if the value is &#x60;false&#x60;, only running batches, i.e., end time is null, are returned. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A batch matches if it has one of the given tenant ids. (optional)
     * @param withoutTenantId Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;HistoricBatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricBatchDto> getHistoricBatches(String batchId, String type, Boolean completed, String tenantIdIn, Boolean withoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getHistoricBatchesWithHttpInfo(batchId, type, completed, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Historic Batches
     * Queries for historic batches that fulfill given parameters. Parameters may be the properties of batches, such as the id or type. The size of the result set can be retrieved by using the [Get Historic Batch Count](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param batchId Filter by batch id. (optional)
     * @param type Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. (optional)
     * @param completed  Filter completed or not completed batches. If the value is &#x60;true&#x60;, only completed batches, i.e., end time is set, are returned. Otherwise, if the value is &#x60;false&#x60;, only running batches, i.e., end time is null, are returned. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A batch matches if it has one of the given tenant ids. (optional)
     * @param withoutTenantId Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricBatchDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricBatchDto>> getHistoricBatchesWithHttpInfo(String batchId, String type, Boolean completed, String tenantIdIn, Boolean withoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/batch", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "batchId", batchId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completed", completed));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
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

        ParameterizedTypeReference<List<HistoricBatchDto>> returnType = new ParameterizedTypeReference<List<HistoricBatchDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Batch Count
     * Requests the number of historic batches that fulfill the query criteria. Takes the same filtering parameters as the [Get Historic Batches](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param batchId Filter by batch id. (optional)
     * @param type Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. (optional)
     * @param completed  Filter completed or not completed batches. If the value is &#x60;true&#x60;, only completed batches, i.e., end time is set, are returned. Otherwise, if the value is &#x60;false&#x60;, only running batches, i.e., end time is null, are returned. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A batch matches if it has one of the given tenant ids. (optional)
     * @param withoutTenantId Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricBatchesCount(String batchId, String type, Boolean completed, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        return getHistoricBatchesCountWithHttpInfo(batchId, type, completed, tenantIdIn, withoutTenantId).getBody();
    }

    /**
     * Get Historic Batch Count
     * Requests the number of historic batches that fulfill the query criteria. Takes the same filtering parameters as the [Get Historic Batches](https://docs.camunda.org/manual/7.16/reference/rest/history/batch/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param batchId Filter by batch id. (optional)
     * @param type Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. (optional)
     * @param completed  Filter completed or not completed batches. If the value is &#x60;true&#x60;, only completed batches, i.e., end time is set, are returned. Otherwise, if the value is &#x60;false&#x60;, only running batches, i.e., end time is null, are returned. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A batch matches if it has one of the given tenant ids. (optional)
     * @param withoutTenantId Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricBatchesCountWithHttpInfo(String batchId, String type, Boolean completed, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/batch/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "batchId", batchId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completed", completed));
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
     * Set Removal Time Async (POST)
     * Sets the removal time to multiple historic batches asynchronously (batch).  At least __historicBatchIds__ or __historicBatchQuery__ has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Request was unsuccessful due to a bad user request. This occurs if some of the query parameters are invalid, e.g. if neither historicBatchIds nor historicBatchQuery is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param setRemovalTimeToHistoricBatchesDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setRemovalTimeAsyncHistoricBatch(SetRemovalTimeToHistoricBatchesDto setRemovalTimeToHistoricBatchesDto) throws RestClientException {
        return setRemovalTimeAsyncHistoricBatchWithHttpInfo(setRemovalTimeToHistoricBatchesDto).getBody();
    }

    /**
     * Set Removal Time Async (POST)
     * Sets the removal time to multiple historic batches asynchronously (batch).  At least __historicBatchIds__ or __historicBatchQuery__ has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Request was unsuccessful due to a bad user request. This occurs if some of the query parameters are invalid, e.g. if neither historicBatchIds nor historicBatchQuery is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param setRemovalTimeToHistoricBatchesDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setRemovalTimeAsyncHistoricBatchWithHttpInfo(SetRemovalTimeToHistoricBatchesDto setRemovalTimeToHistoricBatchesDto) throws RestClientException {
        Object postBody = setRemovalTimeToHistoricBatchesDto;
        
        String path = apiClient.expandPath("/history/batch/set-removal-time", Collections.<String, Object>emptyMap());

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
}
