package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AuthorizationCheckResultDto;
import com.camunda.consulting.openapi.client.model.AuthorizationCreateDto;
import com.camunda.consulting.openapi.client.model.AuthorizationDto;
import com.camunda.consulting.openapi.client.model.AuthorizationUpdateDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T16:56:52.297572+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.AuthorizationApi")
public class AuthorizationApi {
    private ApiClient apiClient;

    public AuthorizationApi() {
        this(new ApiClient());
    }

    @Autowired
    public AuthorizationApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Authorization Resource Options
     * The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableOperationsAuthorization() throws RestClientException {
        return availableOperationsAuthorizationWithHttpInfo().getBody();
    }

    /**
     * Authorization Resource Options
     * The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableOperationsAuthorizationWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/authorization", Collections.<String, Object>emptyMap());

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
     * Authorization Resource Options
     * The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on a given instance of the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the authorization to be retrieved. (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableOperationsAuthorizationInstance(String id) throws RestClientException {
        return availableOperationsAuthorizationInstanceWithHttpInfo(id).getBody();
    }

    /**
     * Authorization Resource Options
     * The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on a given instance of the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the authorization to be retrieved. (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableOperationsAuthorizationInstanceWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableOperationsAuthorizationInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/authorization/{id}", uriVariables);

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
     * Create a New Authorization
     * Creates a new authorization.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the properties in the request body are invalid, for example if a permission parameter is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The authenticated user is unauthorized to create an instance of this resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The authorization could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param authorizationCreateDto  (optional)
     * @return AuthorizationDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AuthorizationDto createAuthorization(AuthorizationCreateDto authorizationCreateDto) throws RestClientException {
        return createAuthorizationWithHttpInfo(authorizationCreateDto).getBody();
    }

    /**
     * Create a New Authorization
     * Creates a new authorization.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the properties in the request body are invalid, for example if a permission parameter is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The authenticated user is unauthorized to create an instance of this resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>500</b> - The authorization could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param authorizationCreateDto  (optional)
     * @return ResponseEntity&lt;AuthorizationDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AuthorizationDto> createAuthorizationWithHttpInfo(AuthorizationCreateDto authorizationCreateDto) throws RestClientException {
        Object postBody = authorizationCreateDto;
        
        String path = apiClient.expandPath("/authorization/create", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<AuthorizationDto> returnType = new ParameterizedTypeReference<AuthorizationDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Delete Authorization
     * Deletes an authorization by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - If the authenticated user is unauthorized to delete the resource instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Authorization cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the authorization to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAuthorization(String id) throws RestClientException {
        deleteAuthorizationWithHttpInfo(id);
    }

    /**
     * Delete Authorization
     * Deletes an authorization by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - If the authenticated user is unauthorized to delete the resource instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Authorization cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the authorization to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAuthorizationWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteAuthorization");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/authorization/{id}", uriVariables);

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
     * Get Authorization
     * Retrieves an authorization by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Authorization with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the authorization to be retrieved. (required)
     * @return AuthorizationDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AuthorizationDto getAuthorization(String id) throws RestClientException {
        return getAuthorizationWithHttpInfo(id).getBody();
    }

    /**
     * Get Authorization
     * Retrieves an authorization by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Authorization with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the authorization to be retrieved. (required)
     * @return ResponseEntity&lt;AuthorizationDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AuthorizationDto> getAuthorizationWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAuthorization");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/authorization/{id}", uriVariables);

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

        ParameterizedTypeReference<AuthorizationDto> returnType = new ParameterizedTypeReference<AuthorizationDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Authorization Count
     * Queries for authorizations using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the authorization. (optional)
     * @param type Filter by authorization type. (0&#x3D;global, 1&#x3D;grant, 2&#x3D;revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types. (optional)
     * @param userIdIn Filter by a comma-separated list of userIds. (optional)
     * @param groupIdIn Filter by a comma-separated list of groupIds. (optional)
     * @param resourceType Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. (optional)
     * @param resourceId Filter by resource id. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getAuthorizationCount(String id, Integer type, String userIdIn, String groupIdIn, Integer resourceType, String resourceId) throws RestClientException {
        return getAuthorizationCountWithHttpInfo(id, type, userIdIn, groupIdIn, resourceType, resourceId).getBody();
    }

    /**
     * Get Authorization Count
     * Queries for authorizations using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the authorization. (optional)
     * @param type Filter by authorization type. (0&#x3D;global, 1&#x3D;grant, 2&#x3D;revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types. (optional)
     * @param userIdIn Filter by a comma-separated list of userIds. (optional)
     * @param groupIdIn Filter by a comma-separated list of groupIds. (optional)
     * @param resourceType Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. (optional)
     * @param resourceId Filter by resource id. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getAuthorizationCountWithHttpInfo(String id, Integer type, String userIdIn, String groupIdIn, Integer resourceType, String resourceId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/authorization/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userIdIn", userIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupIdIn", groupIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceType", resourceType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceId", resourceId));

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
     * Perform an Authorization Check
     * Performs an authorization check for the currently authenticated user.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a permission parameterName is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>401</b> - The user is not authenticated. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - When a &#x60;userId&#x60; is passed and the user does not possess a READ permission for the Authorization resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Authorization with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param permissionName String value representing the permission name to check for. (required)
     * @param resourceName String value for the name of the resource to check permissions for. (required)
     * @param resourceType An integer representing the resource type to check permissions for. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. (required)
     * @param resourceId The id of the resource to check permissions for. If left blank, a check for global permissions on the resource is performed. (optional)
     * @param userId The id of the user to check permissions for. The currently authenticated user must have a READ permission for the Authorization resource. If &#x60;userId&#x60; is blank, a check for the currently authenticated user is performed. (optional)
     * @return AuthorizationCheckResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AuthorizationCheckResultDto isUserAuthorized(String permissionName, String resourceName, Integer resourceType, String resourceId, String userId) throws RestClientException {
        return isUserAuthorizedWithHttpInfo(permissionName, resourceName, resourceType, resourceId, userId).getBody();
    }

    /**
     * Perform an Authorization Check
     * Performs an authorization check for the currently authenticated user.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a permission parameterName is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>401</b> - The user is not authenticated. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - When a &#x60;userId&#x60; is passed and the user does not possess a READ permission for the Authorization resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Authorization with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param permissionName String value representing the permission name to check for. (required)
     * @param resourceName String value for the name of the resource to check permissions for. (required)
     * @param resourceType An integer representing the resource type to check permissions for. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. (required)
     * @param resourceId The id of the resource to check permissions for. If left blank, a check for global permissions on the resource is performed. (optional)
     * @param userId The id of the user to check permissions for. The currently authenticated user must have a READ permission for the Authorization resource. If &#x60;userId&#x60; is blank, a check for the currently authenticated user is performed. (optional)
     * @return ResponseEntity&lt;AuthorizationCheckResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AuthorizationCheckResultDto> isUserAuthorizedWithHttpInfo(String permissionName, String resourceName, Integer resourceType, String resourceId, String userId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'permissionName' is set
        if (permissionName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'permissionName' when calling isUserAuthorized");
        }
        
        // verify the required parameter 'resourceName' is set
        if (resourceName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resourceName' when calling isUserAuthorized");
        }
        
        // verify the required parameter 'resourceType' is set
        if (resourceType == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resourceType' when calling isUserAuthorized");
        }
        
        String path = apiClient.expandPath("/authorization/check", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "permissionName", permissionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceType", resourceType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceId", resourceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<AuthorizationCheckResultDto> returnType = new ParameterizedTypeReference<AuthorizationCheckResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Authorizations
     * Queries for a list of authorizations using a list of parameters. The size of the result set can be retrieved by using the [Get Authorization Count](https://docs.camunda.org/manual/7.16/reference/rest/authorization/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the authorization. (optional)
     * @param type Filter by authorization type. (0&#x3D;global, 1&#x3D;grant, 2&#x3D;revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types. (optional)
     * @param userIdIn Filter by a comma-separated list of userIds. (optional)
     * @param groupIdIn Filter by a comma-separated list of groupIds. (optional)
     * @param resourceType Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. (optional)
     * @param resourceId Filter by resource id. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;AuthorizationDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<AuthorizationDto> queryAuthorizations(String id, Integer type, String userIdIn, String groupIdIn, Integer resourceType, String resourceId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return queryAuthorizationsWithHttpInfo(id, type, userIdIn, groupIdIn, resourceType, resourceId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Authorizations
     * Queries for a list of authorizations using a list of parameters. The size of the result set can be retrieved by using the [Get Authorization Count](https://docs.camunda.org/manual/7.16/reference/rest/authorization/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the authorization. (optional)
     * @param type Filter by authorization type. (0&#x3D;global, 1&#x3D;grant, 2&#x3D;revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types. (optional)
     * @param userIdIn Filter by a comma-separated list of userIds. (optional)
     * @param groupIdIn Filter by a comma-separated list of groupIds. (optional)
     * @param resourceType Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. (optional)
     * @param resourceId Filter by resource id. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;AuthorizationDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<AuthorizationDto>> queryAuthorizationsWithHttpInfo(String id, Integer type, String userIdIn, String groupIdIn, Integer resourceType, String resourceId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/authorization", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userIdIn", userIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupIdIn", groupIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceType", resourceType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceId", resourceId));
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

        ParameterizedTypeReference<List<AuthorizationDto>> returnType = new ParameterizedTypeReference<List<AuthorizationDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Update an Authorization
     * Updates an authorization by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the properties in the request body are invalid, for example if a permission parameter is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The authenticated user is unauthorized to update this resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - The authorization with the requested Id cannot be found.
     * <p><b>500</b> - The authorization could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the authorization to be updated. (required)
     * @param authorizationUpdateDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateAuthorization(String id, AuthorizationUpdateDto authorizationUpdateDto) throws RestClientException {
        updateAuthorizationWithHttpInfo(id, authorizationUpdateDto);
    }

    /**
     * Update an Authorization
     * Updates an authorization by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - Returned if some of the properties in the request body are invalid, for example if a permission parameter is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The authenticated user is unauthorized to update this resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - The authorization with the requested Id cannot be found.
     * <p><b>500</b> - The authorization could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the authorization to be updated. (required)
     * @param authorizationUpdateDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateAuthorizationWithHttpInfo(String id, AuthorizationUpdateDto authorizationUpdateDto) throws RestClientException {
        Object postBody = authorizationUpdateDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateAuthorization");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/authorization/{id}", uriVariables);

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
