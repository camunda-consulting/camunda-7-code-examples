package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.CreateFilterDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.FilterDto;
import com.camunda.consulting.openapi.client.model.ResourceOptionsDto;

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
@Component("com.camunda.consulting.openapi.client.handler.FilterApi")
public class FilterApi {
    private ApiClient apiClient;

    public FilterApi() {
        this(new ApiClient());
    }

    @Autowired
    public FilterApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Filter
     * Creates a new filter.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Filter was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to create a new filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param createFilterDto  (optional)
     * @return FilterDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FilterDto createFilter(CreateFilterDto createFilterDto) throws RestClientException {
        return createFilterWithHttpInfo(createFilterDto).getBody();
    }

    /**
     * Create Filter
     * Creates a new filter.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Filter was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to create a new filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param createFilterDto  (optional)
     * @return ResponseEntity&lt;FilterDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FilterDto> createFilterWithHttpInfo(CreateFilterDto createFilterDto) throws RestClientException {
        Object postBody = createFilterDto;
        
        String path = apiClient.expandPath("/filter/create", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<FilterDto> returnType = new ParameterizedTypeReference<FilterDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Delete Filter
     * Deletes a filter by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> -  The authenticated user is unauthorized to delete this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteFilter(String id) throws RestClientException {
        deleteFilterWithHttpInfo(id);
    }

    /**
     * Delete Filter
     * Deletes a filter by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> -  The authenticated user is unauthorized to delete this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteFilterWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteFilter");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}", uriVariables);

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
     * Execute Filter Count
     * Executes the saved query of the filter by id and returns the count.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto executeFilterCount(String id) throws RestClientException {
        return executeFilterCountWithHttpInfo(id).getBody();
    }

    /**
     * Execute Filter Count
     * Executes the saved query of the filter by id and returns the count.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> executeFilterCountWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling executeFilterCount");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}/count", uriVariables);

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

        ParameterizedTypeReference<CountResultDto> returnType = new ParameterizedTypeReference<CountResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Execute Filter List
     * Executes the saved query of the filter by id and returns the result list.
     * <p><b>200</b> - Request successful. A JSON array containing JSON objects corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Object> executeFilterList(String id, Integer firstResult, Integer maxResults) throws RestClientException {
        return executeFilterListWithHttpInfo(id, firstResult, maxResults).getBody();
    }

    /**
     * Execute Filter List
     * Executes the saved query of the filter by id and returns the result list.
     * <p><b>200</b> - Request successful. A JSON array containing JSON objects corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;Object&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Object>> executeFilterListWithHttpInfo(String id, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling executeFilterList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}/list", uriVariables);

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
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<Object>> returnType = new ParameterizedTypeReference<List<Object>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Execute Filter Single Result
     * Executes the saved query of the filter by id and returns the single result.
     * <p><b>200</b> - Request successful. A JSON object corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>204</b> - Request successful, but the result was empty. This method returns no content.
     * <p><b>400</b> -  The executed filter returned more than one single result. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object executeFilterSingleResult(String id) throws RestClientException {
        return executeFilterSingleResultWithHttpInfo(id).getBody();
    }

    /**
     * Execute Filter Single Result
     * Executes the saved query of the filter by id and returns the single result.
     * <p><b>200</b> - Request successful. A JSON object corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>204</b> - Request successful, but the result was empty. This method returns no content.
     * <p><b>400</b> -  The executed filter returned more than one single result. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> executeFilterSingleResultWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling executeFilterSingleResult");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}/singleResult", uriVariables);

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

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Filter Resource Options
     * The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto filterResourceOptions() throws RestClientException {
        return filterResourceOptionsWithHttpInfo().getBody();
    }

    /**
     * Filter Resource Options
     * The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> filterResourceOptionsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/filter", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<ResourceOptionsDto> returnType = new ParameterizedTypeReference<ResourceOptionsDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.OPTIONS, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Filter Resource Options
     * The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the filter to be checked. (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto filterResourceOptionsSingle(String id) throws RestClientException {
        return filterResourceOptionsSingleWithHttpInfo(id).getBody();
    }

    /**
     * Filter Resource Options
     * The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the filter to be checked. (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> filterResourceOptionsSingleWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling filterResourceOptionsSingle");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}", uriVariables);

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

        ParameterizedTypeReference<ResourceOptionsDto> returnType = new ParameterizedTypeReference<ResourceOptionsDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.OPTIONS, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Filter Count
     * Retrieves the number of filters that fulfill a provided query. Corresponds to the size of the result set when using the  [Get Filters](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60;parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param filterId Filter by the id of the filter. (optional)
     * @param resourceType Filter by the resource type of the filter, e.g., &#x60;Task&#x60;. (optional)
     * @param name Filter by the name of the filter. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param owner Filter by the user id of the owner of the filter. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getFilterCount(String filterId, String resourceType, String name, String nameLike, String owner) throws RestClientException {
        return getFilterCountWithHttpInfo(filterId, resourceType, name, nameLike, owner).getBody();
    }

    /**
     * Get Filter Count
     * Retrieves the number of filters that fulfill a provided query. Corresponds to the size of the result set when using the  [Get Filters](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60;parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param filterId Filter by the id of the filter. (optional)
     * @param resourceType Filter by the resource type of the filter, e.g., &#x60;Task&#x60;. (optional)
     * @param name Filter by the name of the filter. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param owner Filter by the user id of the owner of the filter. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getFilterCountWithHttpInfo(String filterId, String resourceType, String name, String nameLike, String owner) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/filter/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterId", filterId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceType", resourceType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "owner", owner));

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
     * Get Filters
     * Queries for a list of filters using a list of parameters. The size of the result set can be retrieved by using the [Get Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param filterId Filter by the id of the filter. (optional)
     * @param resourceType Filter by the resource type of the filter, e.g., &#x60;Task&#x60;. (optional)
     * @param name Filter by the name of the filter. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param owner Filter by the user id of the owner of the filter. (optional)
     * @param itemCount If set to &#x60;true&#x60;, each filter result will contain an &#x60;itemCount&#x60; property with the number of items matched by the filter itself. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;FilterDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<FilterDto> getFilterList(String filterId, String resourceType, String name, String nameLike, String owner, Boolean itemCount, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getFilterListWithHttpInfo(filterId, resourceType, name, nameLike, owner, itemCount, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Filters
     * Queries for a list of filters using a list of parameters. The size of the result set can be retrieved by using the [Get Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param filterId Filter by the id of the filter. (optional)
     * @param resourceType Filter by the resource type of the filter, e.g., &#x60;Task&#x60;. (optional)
     * @param name Filter by the name of the filter. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param owner Filter by the user id of the owner of the filter. (optional)
     * @param itemCount If set to &#x60;true&#x60;, each filter result will contain an &#x60;itemCount&#x60; property with the number of items matched by the filter itself. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;FilterDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<FilterDto>> getFilterListWithHttpInfo(String filterId, String resourceType, String name, String nameLike, String owner, Boolean itemCount, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/filter", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterId", filterId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceType", resourceType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "owner", owner));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "itemCount", itemCount));
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

        ParameterizedTypeReference<List<FilterDto>> returnType = new ParameterizedTypeReference<List<FilterDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Single Filter
     * Retrieves a single filter by id, according to the &#x60;Filter&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> - The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the filter to be retrieved. (required)
     * @param itemCount If set to &#x60;true&#x60;, each filter result will contain an &#x60;itemCount&#x60; property with the number of items matched by the filter itself. (optional)
     * @return FilterDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public FilterDto getSingleFilter(String id, Boolean itemCount) throws RestClientException {
        return getSingleFilterWithHttpInfo(id, itemCount).getBody();
    }

    /**
     * Get Single Filter
     * Retrieves a single filter by id, according to the &#x60;Filter&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>403</b> - The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the filter to be retrieved. (required)
     * @param itemCount If set to &#x60;true&#x60;, each filter result will contain an &#x60;itemCount&#x60; property with the number of items matched by the filter itself. (optional)
     * @return ResponseEntity&lt;FilterDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<FilterDto> getSingleFilterWithHttpInfo(String id, Boolean itemCount) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getSingleFilter");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "itemCount", itemCount));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<FilterDto> returnType = new ParameterizedTypeReference<FilterDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Execute Filter Count (POST)
     * Executes the saved query of the filter by id and returns the count. This method is slightly more powerful then the [Get Execute Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-count/)  method because it allows to extend the saved query of the filter.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  The extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param body A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto postExecuteFilterCount(String id, Object body) throws RestClientException {
        return postExecuteFilterCountWithHttpInfo(id, body).getBody();
    }

    /**
     * Execute Filter Count (POST)
     * Executes the saved query of the filter by id and returns the count. This method is slightly more powerful then the [Get Execute Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-count/)  method because it allows to extend the saved query of the filter.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  The extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param body A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> postExecuteFilterCountWithHttpInfo(String id, Object body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling postExecuteFilterCount");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}/count", uriVariables);

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
     * Execute Filter List (POST)
     * Executes the saved query of the filter by id and returns the result list. This method is slightly more powerful then the  [Get Execute FilterList](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-list/) method because it allows to extend the saved query of the filter.
     * <p><b>200</b> - Request successful. A JSON array containing JSON objects corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>400</b> -  The extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param body A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. (optional)
     * @return List&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Object> postExecuteFilterList(String id, Integer firstResult, Integer maxResults, Object body) throws RestClientException {
        return postExecuteFilterListWithHttpInfo(id, firstResult, maxResults, body).getBody();
    }

    /**
     * Execute Filter List (POST)
     * Executes the saved query of the filter by id and returns the result list. This method is slightly more powerful then the  [Get Execute FilterList](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-list/) method because it allows to extend the saved query of the filter.
     * <p><b>200</b> - Request successful. A JSON array containing JSON objects corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>400</b> -  The extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param body A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. (optional)
     * @return ResponseEntity&lt;List&lt;Object&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Object>> postExecuteFilterListWithHttpInfo(String id, Integer firstResult, Integer maxResults, Object body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling postExecuteFilterList");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}/list", uriVariables);

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

        ParameterizedTypeReference<List<Object>> returnType = new ParameterizedTypeReference<List<Object>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Execute Filter Single Result (POST)
     * Executes the saved query of the filter by id and returns the single result. This method is slightly more powerful then the [Get Execute Filter Single Result](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-single-result/) method because it allows to extend the saved query of the filter.
     * <p><b>200</b> - Request successful. A JSON object corresponding to the corresponding entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>204</b> - Request successful, but the result was empty. This method returns no content.
     * <p><b>400</b> -  The executed filter returned more than one single result or the extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param body A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. (optional)
     * @return Object
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Object postExecuteFilterSingleResult(String id, Object body) throws RestClientException {
        return postExecuteFilterSingleResultWithHttpInfo(id, body).getBody();
    }

    /**
     * Execute Filter Single Result (POST)
     * Executes the saved query of the filter by id and returns the single result. This method is slightly more powerful then the [Get Execute Filter Single Result](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-single-result/) method because it allows to extend the saved query of the filter.
     * <p><b>200</b> - Request successful. A JSON object corresponding to the corresponding entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine.
     * <p><b>204</b> - Request successful, but the result was empty. This method returns no content.
     * <p><b>400</b> -  The executed filter returned more than one single result or the extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to execute. (required)
     * @param body A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. (optional)
     * @return ResponseEntity&lt;Object&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Object> postExecuteFilterSingleResultWithHttpInfo(String id, Object body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling postExecuteFilterSingleResult");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}/singleResult", uriVariables);

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

        ParameterizedTypeReference<Object> returnType = new ParameterizedTypeReference<Object>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Update Filter
     * Updates an existing filter.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> -  Filter was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to update this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to be updated. (required)
     * @param createFilterDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateFilter(String id, CreateFilterDto createFilterDto) throws RestClientException {
        updateFilterWithHttpInfo(id, createFilterDto);
    }

    /**
     * Update Filter
     * Updates an existing filter.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> -  Filter was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>403</b> -  The authenticated user is unauthorized to update this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * <p><b>404</b> -  Filter cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param id The id of the filter to be updated. (required)
     * @param createFilterDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateFilterWithHttpInfo(String id, CreateFilterDto createFilterDto) throws RestClientException {
        Object postBody = createFilterDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateFilter");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/filter/{id}", uriVariables);

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
