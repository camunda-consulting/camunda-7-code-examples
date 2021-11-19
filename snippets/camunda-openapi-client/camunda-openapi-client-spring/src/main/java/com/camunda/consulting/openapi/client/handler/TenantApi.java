package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.ResourceOptionsDto;
import com.camunda.consulting.openapi.client.model.TenantDto;

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
@Component("com.camunda.consulting.openapi.client.handler.TenantApi")
public class TenantApi {
    private ApiClient apiClient;

    public TenantApi() {
        this(new ApiClient());
    }

    @Autowired
    public TenantApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Tenant Group Membership Resource Options
     * The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the tenant (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableTenantGroupMembersOperations(String id) throws RestClientException {
        return availableTenantGroupMembersOperationsWithHttpInfo(id).getBody();
    }

    /**
     * Tenant Group Membership Resource Options
     * The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the tenant (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableTenantGroupMembersOperationsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableTenantGroupMembersOperations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/tenant/{id}/group-members", uriVariables);

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
     * Tenant Resource Options
     * The &#x60;/tenant&#x60; resource supports two custom OPTIONS requests, one for the resource as such and this one for individual tenant instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/tenant/{id}&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the tenant (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableTenantInstanceOperations(String id) throws RestClientException {
        return availableTenantInstanceOperationsWithHttpInfo(id).getBody();
    }

    /**
     * Tenant Resource Options
     * The &#x60;/tenant&#x60; resource supports two custom OPTIONS requests, one for the resource as such and this one for individual tenant instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/tenant/{id}&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the tenant (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableTenantInstanceOperationsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableTenantInstanceOperations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/tenant/{id}", uriVariables);

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
     * Tenant Resource Options
     * The &#x60;/tenant&#x60; resource supports two custom OPTIONS requests, this one for the resource as such and one for individual tenant instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/tenant&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableTenantResourceOperations() throws RestClientException {
        return availableTenantResourceOperationsWithHttpInfo().getBody();
    }

    /**
     * Tenant Resource Options
     * The &#x60;/tenant&#x60; resource supports two custom OPTIONS requests, this one for the resource as such and one for individual tenant instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/tenant&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableTenantResourceOperationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tenant", Collections.<String, Object>emptyMap());

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
     * Tenant User Membership Resource Options
     * The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the tenant (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableTenantUserMembersOperations(String id) throws RestClientException {
        return availableTenantUserMembersOperationsWithHttpInfo(id).getBody();
    }

    /**
     * Tenant User Membership Resource Options
     * The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the tenant (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableTenantUserMembersOperationsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableTenantUserMembersOperations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/tenant/{id}/user-members", uriVariables);

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
     * Create Tenant Group Membership
     * Creates a membership between a tenant and a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param groupId The id of the group. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createGroupMembership(String id, String groupId) throws RestClientException {
        createGroupMembershipWithHttpInfo(id, groupId);
    }

    /**
     * Create Tenant Group Membership
     * Creates a membership between a tenant and a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param groupId The id of the group. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createGroupMembershipWithHttpInfo(String id, String groupId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createGroupMembership");
        }
        
        // verify the required parameter 'groupId' is set
        if (groupId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupId' when calling createGroupMembership");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("groupId", groupId);
        String path = apiClient.expandPath("/tenant/{id}/group-members/{groupId}", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Create Tenant
     * Create a new tenant.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - The tenant could not be created due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param tenantDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createTenant(TenantDto tenantDto) throws RestClientException {
        createTenantWithHttpInfo(tenantDto);
    }

    /**
     * Create Tenant
     * Create a new tenant.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - The tenant could not be created due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param tenantDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createTenantWithHttpInfo(TenantDto tenantDto) throws RestClientException {
        Object postBody = tenantDto;
        
        String path = apiClient.expandPath("/tenant/create", Collections.<String, Object>emptyMap());

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
     * Create Tenant User Membership
     * Creates a membership between a tenant and an user.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param userId The id of the user. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createUserMembership(String id, String userId) throws RestClientException {
        createUserMembershipWithHttpInfo(id, userId);
    }

    /**
     * Create Tenant User Membership
     * Creates a membership between a tenant and an user.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param userId The id of the user. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createUserMembershipWithHttpInfo(String id, String userId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createUserMembership");
        }
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userId' when calling createUserMembership");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("userId", userId);
        String path = apiClient.expandPath("/tenant/{id}/user-members/{userId}", uriVariables);

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
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Create Tenant Group Membership
     * Creates a membership between a tenant and a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param groupId The id of the group. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteGroupMembership(String id, String groupId) throws RestClientException {
        deleteGroupMembershipWithHttpInfo(id, groupId);
    }

    /**
     * Create Tenant Group Membership
     * Creates a membership between a tenant and a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param groupId The id of the group. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteGroupMembershipWithHttpInfo(String id, String groupId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteGroupMembership");
        }
        
        // verify the required parameter 'groupId' is set
        if (groupId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'groupId' when calling deleteGroupMembership");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("groupId", groupId);
        String path = apiClient.expandPath("/tenant/{id}/group-members/{groupId}", uriVariables);

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
     * Delete Tenant
     * Deletes a tenant by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>404</b> - Tenant cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteTenant(String id) throws RestClientException {
        deleteTenantWithHttpInfo(id);
    }

    /**
     * Delete Tenant
     * Deletes a tenant by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>404</b> - Tenant cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteTenantWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/tenant/{id}", uriVariables);

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
     * Delete a Tenant User Membership
     * Deletes a membership between a tenant and an user.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param userId The id of the user. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteUserMembership(String id, String userId) throws RestClientException {
        deleteUserMembershipWithHttpInfo(id, userId);
    }

    /**
     * Delete a Tenant User Membership
     * Deletes a membership between a tenant and an user.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>500</b> - In case an error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param userId The id of the user. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteUserMembershipWithHttpInfo(String id, String userId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteUserMembership");
        }
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userId' when calling deleteUserMembership");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("userId", userId);
        String path = apiClient.expandPath("/tenant/{id}/user-members/{userId}", uriVariables);

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
     * Get Tenant
     * Retrieves a tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Tenant with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant to be retrieved. (required)
     * @return TenantDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TenantDto getTenant(String id) throws RestClientException {
        return getTenantWithHttpInfo(id).getBody();
    }

    /**
     * Get Tenant
     * Retrieves a tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Tenant with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant to be retrieved. (required)
     * @return ResponseEntity&lt;TenantDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TenantDto> getTenantWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/tenant/{id}", uriVariables);

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

        ParameterizedTypeReference<TenantDto> returnType = new ParameterizedTypeReference<TenantDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Tenant Count
     * Query for tenants using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the tenant. (optional)
     * @param name Filter by the name of the tenant. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param userMember Select only tenants where the given user is a member of. (optional)
     * @param groupMember Select only tenants where the given group is a member of. (optional)
     * @param includingGroupsOfUser Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the &#x60;userMember&#x60; parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getTenantCount(String id, String name, String nameLike, String userMember, String groupMember, Boolean includingGroupsOfUser) throws RestClientException {
        return getTenantCountWithHttpInfo(id, name, nameLike, userMember, groupMember, includingGroupsOfUser).getBody();
    }

    /**
     * Get Tenant Count
     * Query for tenants using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the tenant. (optional)
     * @param name Filter by the name of the tenant. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param userMember Select only tenants where the given user is a member of. (optional)
     * @param groupMember Select only tenants where the given group is a member of. (optional)
     * @param includingGroupsOfUser Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the &#x60;userMember&#x60; parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getTenantCountWithHttpInfo(String id, String name, String nameLike, String userMember, String groupMember, Boolean includingGroupsOfUser) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tenant/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userMember", userMember));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupMember", groupMember));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includingGroupsOfUser", includingGroupsOfUser));

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
     * Get Tenants
     * Query for a list of tenants using a list of parameters. The size of the result set can be retrieved by using the [Get Tenant Count](https://docs.camunda.org/manual/7.16/reference/rest/tenant/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param id Filter by the id of the tenant. (optional)
     * @param name Filter by the name of the tenant. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param userMember Select only tenants where the given user is a member of. (optional)
     * @param groupMember Select only tenants where the given group is a member of. (optional)
     * @param includingGroupsOfUser Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the &#x60;userMember&#x60; parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return List&lt;TenantDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<TenantDto> queryTenants(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String id, String name, String nameLike, String userMember, String groupMember, Boolean includingGroupsOfUser) throws RestClientException {
        return queryTenantsWithHttpInfo(sortBy, sortOrder, firstResult, maxResults, id, name, nameLike, userMember, groupMember, includingGroupsOfUser).getBody();
    }

    /**
     * Get Tenants
     * Query for a list of tenants using a list of parameters. The size of the result set can be retrieved by using the [Get Tenant Count](https://docs.camunda.org/manual/7.16/reference/rest/tenant/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param id Filter by the id of the tenant. (optional)
     * @param name Filter by the name of the tenant. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param userMember Select only tenants where the given user is a member of. (optional)
     * @param groupMember Select only tenants where the given group is a member of. (optional)
     * @param includingGroupsOfUser Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the &#x60;userMember&#x60; parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;List&lt;TenantDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<TenantDto>> queryTenantsWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String id, String name, String nameLike, String userMember, String groupMember, Boolean includingGroupsOfUser) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/tenant", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userMember", userMember));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupMember", groupMember));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includingGroupsOfUser", includingGroupsOfUser));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<TenantDto>> returnType = new ParameterizedTypeReference<List<TenantDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Update Tenant
     * Updates a given tenant.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>404</b> - If the tenant with the requested Id cannot be found.
     * <p><b>500</b> - The tenant could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param tenantDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateTenant(String id, TenantDto tenantDto) throws RestClientException {
        updateTenantWithHttpInfo(id, tenantDto);
    }

    /**
     * Update Tenant
     * Updates a given tenant.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Identity service is read-only.
     * <p><b>404</b> - If the tenant with the requested Id cannot be found.
     * <p><b>500</b> - The tenant could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the tenant. (required)
     * @param tenantDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateTenantWithHttpInfo(String id, TenantDto tenantDto) throws RestClientException {
        Object postBody = tenantDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/tenant/{id}", uriVariables);

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
