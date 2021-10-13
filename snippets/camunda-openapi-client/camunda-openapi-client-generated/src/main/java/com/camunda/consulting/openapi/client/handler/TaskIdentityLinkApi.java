package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.IdentityLinkDto;

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
@Component("com.camunda.consulting.openapi.client.handler.TaskIdentityLinkApi")
public class TaskIdentityLinkApi {
    private ApiClient apiClient;

    public TaskIdentityLinkApi() {
        this(new ApiClient());
    }

    @Autowired
    public TaskIdentityLinkApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add
     * Adds an identity link to a task by id. Can be used to link any user or group to a task and specify a relation.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to add a link to. (required)
     * @param identityLinkDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void addIdentityLink(String id, IdentityLinkDto identityLinkDto) throws RestClientException {
        addIdentityLinkWithHttpInfo(id, identityLinkDto);
    }

    /**
     * Add
     * Adds an identity link to a task by id. Can be used to link any user or group to a task and specify a relation.
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to add a link to. (required)
     * @param identityLinkDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> addIdentityLinkWithHttpInfo(String id, IdentityLinkDto identityLinkDto) throws RestClientException {
        Object postBody = identityLinkDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling addIdentityLink");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/identity-links", uriVariables);

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
     * Removes an identity link from a task by id
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to remove a link from. (required)
     * @param identityLinkDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteIdentityLink(String id, IdentityLinkDto identityLinkDto) throws RestClientException {
        deleteIdentityLinkWithHttpInfo(id, identityLinkDto);
    }

    /**
     * Delete
     * Removes an identity link from a task by id
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to remove a link from. (required)
     * @param identityLinkDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteIdentityLinkWithHttpInfo(String id, IdentityLinkDto identityLinkDto) throws RestClientException {
        Object postBody = identityLinkDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteIdentityLink");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/identity-links/delete", uriVariables);

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
     * Get List
     * Gets the identity links for a task by id, which are the users and groups that are in *some* relation to it (including assignee and owner).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the identity links for. (required)
     * @param type Filter by the type of links to include. (optional)
     * @return List&lt;IdentityLinkDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<IdentityLinkDto> getIdentityLinks(String id, String type) throws RestClientException {
        return getIdentityLinksWithHttpInfo(id, type).getBody();
    }

    /**
     * Get List
     * Gets the identity links for a task by id, which are the users and groups that are in *some* relation to it (including assignee and owner).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the identity links for. (required)
     * @param type Filter by the type of links to include. (optional)
     * @return ResponseEntity&lt;List&lt;IdentityLinkDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<IdentityLinkDto>> getIdentityLinksWithHttpInfo(String id, String type) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getIdentityLinks");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/identity-links", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<IdentityLinkDto>> returnType = new ParameterizedTypeReference<List<IdentityLinkDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
