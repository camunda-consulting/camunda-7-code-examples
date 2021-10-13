package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.SchemaLogEntryDto;
import com.camunda.consulting.openapi.client.model.SchemaLogQueryDto;

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
@Component("com.camunda.consulting.openapi.client.handler.SchemaLogApi")
public class SchemaLogApi {
    private ApiClient apiClient;

    public SchemaLogApi() {
        this(new ApiClient());
    }

    @Autowired
    public SchemaLogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get List
     * Queries for schema log entries that fulfill given parameters.
     * <p><b>200</b> - Request successful. **Note**: In order to get any results a user of group &#x60;camunda-admin&#x60; must be authenticated.
     * @param version Only return schema log entries with a specific version. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;SchemaLogEntryDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<SchemaLogEntryDto> getSchemaLog(String version, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getSchemaLogWithHttpInfo(version, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for schema log entries that fulfill given parameters.
     * <p><b>200</b> - Request successful. **Note**: In order to get any results a user of group &#x60;camunda-admin&#x60; must be authenticated.
     * @param version Only return schema log entries with a specific version. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;SchemaLogEntryDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<SchemaLogEntryDto>> getSchemaLogWithHttpInfo(String version, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/schema/log", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
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

        ParameterizedTypeReference<List<SchemaLogEntryDto>> returnType = new ParameterizedTypeReference<List<SchemaLogEntryDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List (POST)
     * Queries for schema log entries that fulfill given parameters.
     * <p><b>200</b> - Request successful. **Note**: In order to get any results a user of group camunda-admin must be authenticated.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param schemaLogQueryDto  (optional)
     * @return List&lt;SchemaLogEntryDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<SchemaLogEntryDto> querySchemaLog(Integer firstResult, Integer maxResults, SchemaLogQueryDto schemaLogQueryDto) throws RestClientException {
        return querySchemaLogWithHttpInfo(firstResult, maxResults, schemaLogQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for schema log entries that fulfill given parameters.
     * <p><b>200</b> - Request successful. **Note**: In order to get any results a user of group camunda-admin must be authenticated.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param schemaLogQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;SchemaLogEntryDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<SchemaLogEntryDto>> querySchemaLogWithHttpInfo(Integer firstResult, Integer maxResults, SchemaLogQueryDto schemaLogQueryDto) throws RestClientException {
        Object postBody = schemaLogQueryDto;
        
        String path = apiClient.expandPath("/schema/log", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<SchemaLogEntryDto>> returnType = new ParameterizedTypeReference<List<SchemaLogEntryDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
