package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.GroupDto;
import com.camunda.consulting.openapi.client.model.GroupQueryDto;
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
@Component("com.camunda.consulting.openapi.client.handler.GroupApi")
public class GroupApi {
    private ApiClient apiClient;

    public GroupApi() {
        this(new ApiClient());
    }

    @Autowired
    public GroupApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Group Resource Instance Options
     * The &#x60;/group&#x60; resource supports two custom OPTIONS requests, one for the resource as such and this one for individual group instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/group/{id}&#x60; resource instance. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the group. (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableGroupInstanceOperations(String id) throws RestClientException {
        return availableGroupInstanceOperationsWithHttpInfo(id).getBody();
    }

    /**
     * Group Resource Instance Options
     * The &#x60;/group&#x60; resource supports two custom OPTIONS requests, one for the resource as such and this one for individual group instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/group/{id}&#x60; resource instance. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the group. (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableGroupInstanceOperationsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableGroupInstanceOperations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/group/{id}", uriVariables);

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
     * Group Membership Resource Options
     * The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the group. (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableGroupMembersOperations(String id) throws RestClientException {
        return availableGroupMembersOperationsWithHttpInfo(id).getBody();
    }

    /**
     * Group Membership Resource Options
     * The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @param id The id of the group. (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableGroupMembersOperationsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableGroupMembersOperations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/group/{id}/members", uriVariables);

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
     * Group Resource Options
     * The &#x60;/group&#x60; resource supports two custom OPTIONS requests, this one for the resource as such and one for individual group instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/group&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableGroupOperations() throws RestClientException {
        return availableGroupOperationsWithHttpInfo().getBody();
    }

    /**
     * Group Resource Options
     * The &#x60;/group&#x60; resource supports two custom OPTIONS requests, this one for the resource as such and one for individual group instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/group&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     * <p><b>200</b> - Request successful.
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableGroupOperationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/group", Collections.<String, Object>emptyMap());

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
     * Create Group
     * Creates a new group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>500</b> - The group could not be created due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param groupDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createGroup(GroupDto groupDto) throws RestClientException {
        createGroupWithHttpInfo(groupDto);
    }

    /**
     * Create Group
     * Creates a new group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>500</b> - The group could not be created due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param groupDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createGroupWithHttpInfo(GroupDto groupDto) throws RestClientException {
        Object postBody = groupDto;
        
        String path = apiClient.expandPath("/group/create", Collections.<String, Object>emptyMap());

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
     * Create Group Member
     * Adds a member to a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group. (required)
     * @param userId The id of user to add to the group. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createGroupMember(String id, String userId) throws RestClientException {
        createGroupMemberWithHttpInfo(id, userId);
    }

    /**
     * Create Group Member
     * Adds a member to a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>500</b> - In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group. (required)
     * @param userId The id of user to add to the group. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createGroupMemberWithHttpInfo(String id, String userId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling createGroupMember");
        }
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userId' when calling createGroupMember");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("userId", userId);
        String path = apiClient.expandPath("/group/{id}/members/{userId}", uriVariables);

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
     * Delete Group
     * Deletes a group by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - Group cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteGroup(String id) throws RestClientException {
        deleteGroupWithHttpInfo(id);
    }

    /**
     * Delete Group
     * Deletes a group by id.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - Group cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteGroupWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/group/{id}", uriVariables);

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
     * Delete a Group Member
     * Removes a member from a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>500</b> - In case an error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group. (required)
     * @param userId The id of user to remove from the group. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteGroupMember(String id, String userId) throws RestClientException {
        deleteGroupMemberWithHttpInfo(id, userId);
    }

    /**
     * Delete a Group Member
     * Removes a member from a group.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>500</b> - In case an error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group. (required)
     * @param userId The id of user to remove from the group. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteGroupMemberWithHttpInfo(String id, String userId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteGroupMember");
        }
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userId' when calling deleteGroupMember");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("userId", userId);
        String path = apiClient.expandPath("/group/{id}/members/{userId}", uriVariables);

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
     * Get Group
     * Retrieves a group by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Group with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group to be retrieved. (required)
     * @return GroupDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GroupDto getGroup(String id) throws RestClientException {
        return getGroupWithHttpInfo(id).getBody();
    }

    /**
     * Get Group
     * Retrieves a group by id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Group with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group to be retrieved. (required)
     * @return ResponseEntity&lt;GroupDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GroupDto> getGroupWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/group/{id}", uriVariables);

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

        ParameterizedTypeReference<GroupDto> returnType = new ParameterizedTypeReference<GroupDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for groups using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the group. (optional)
     * @param idIn Filter by a comma seperated list of group ids. (optional)
     * @param name Filter by the name of the group. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param type Filter by the type of the group. (optional)
     * @param member Only retrieve groups where the given user id is a member of. (optional)
     * @param memberOfTenant Only retrieve groups which are members of the given tenant. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getGroupCount(String id, String idIn, String name, String nameLike, String type, String member, String memberOfTenant) throws RestClientException {
        return getGroupCountWithHttpInfo(id, idIn, name, nameLike, type, member, memberOfTenant).getBody();
    }

    /**
     * Get List Count
     * Queries for groups using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by the id of the group. (optional)
     * @param idIn Filter by a comma seperated list of group ids. (optional)
     * @param name Filter by the name of the group. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param type Filter by the type of the group. (optional)
     * @param member Only retrieve groups where the given user id is a member of. (optional)
     * @param memberOfTenant Only retrieve groups which are members of the given tenant. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getGroupCountWithHttpInfo(String id, String idIn, String name, String nameLike, String type, String member, String memberOfTenant) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/group/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "idIn", idIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "member", member));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "memberOfTenant", memberOfTenant));

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
     * Get List
     * Queries for a list of groups using a list of parameters. The size of the result set can be retrieved by using the [Get Group Count](https://docs.camunda.org/manual/7.16/reference/rest/group/get-query-count) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param id Filter by the id of the group. (optional)
     * @param idIn Filter by a comma seperated list of group ids. (optional)
     * @param name Filter by the name of the group. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param type Filter by the type of the group. (optional)
     * @param member Only retrieve groups where the given user id is a member of. (optional)
     * @param memberOfTenant Only retrieve groups which are members of the given tenant. (optional)
     * @return List&lt;GroupDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<GroupDto> getQueryGroups(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String id, String idIn, String name, String nameLike, String type, String member, String memberOfTenant) throws RestClientException {
        return getQueryGroupsWithHttpInfo(sortBy, sortOrder, firstResult, maxResults, id, idIn, name, nameLike, type, member, memberOfTenant).getBody();
    }

    /**
     * Get List
     * Queries for a list of groups using a list of parameters. The size of the result set can be retrieved by using the [Get Group Count](https://docs.camunda.org/manual/7.16/reference/rest/group/get-query-count) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param id Filter by the id of the group. (optional)
     * @param idIn Filter by a comma seperated list of group ids. (optional)
     * @param name Filter by the name of the group. (optional)
     * @param nameLike Filter by the name that the parameter is a substring of. (optional)
     * @param type Filter by the type of the group. (optional)
     * @param member Only retrieve groups where the given user id is a member of. (optional)
     * @param memberOfTenant Only retrieve groups which are members of the given tenant. (optional)
     * @return ResponseEntity&lt;List&lt;GroupDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<GroupDto>> getQueryGroupsWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String id, String idIn, String name, String nameLike, String type, String member, String memberOfTenant) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/group", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "idIn", idIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "member", member));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "memberOfTenant", memberOfTenant));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<GroupDto>> returnType = new ParameterizedTypeReference<List<GroupDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List (POST)
     * Queries for a list of groups using a list of parameters. The size of the result set can be retrieved by using the [Get Group Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/group/post-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param groupQueryDto  (optional)
     * @return List&lt;GroupDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<GroupDto> postQueryGroups(Integer firstResult, Integer maxResults, GroupQueryDto groupQueryDto) throws RestClientException {
        return postQueryGroupsWithHttpInfo(firstResult, maxResults, groupQueryDto).getBody();
    }

    /**
     * Get List (POST)
     * Queries for a list of groups using a list of parameters. The size of the result set can be retrieved by using the [Get Group Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/group/post-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param groupQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;GroupDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<GroupDto>> postQueryGroupsWithHttpInfo(Integer firstResult, Integer maxResults, GroupQueryDto groupQueryDto) throws RestClientException {
        Object postBody = groupQueryDto;
        
        String path = apiClient.expandPath("/group", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<GroupDto>> returnType = new ParameterizedTypeReference<List<GroupDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count (POST)
     * Queries for groups using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param groupQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryGroupCount(GroupQueryDto groupQueryDto) throws RestClientException {
        return queryGroupCountWithHttpInfo(groupQueryDto).getBody();
    }

    /**
     * Get List Count (POST)
     * Queries for groups using a list of parameters and retrieves the count.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param groupQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryGroupCountWithHttpInfo(GroupQueryDto groupQueryDto) throws RestClientException {
        Object postBody = groupQueryDto;
        
        String path = apiClient.expandPath("/group/count", Collections.<String, Object>emptyMap());

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
     * Update Group
     * Updates a given group by id.
     * <p><b>204</b> - Request successful. No content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - If the group with the requested Id cannot be found.
     * <p><b>500</b> - The group could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group. (required)
     * @param groupDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateGroup(String id, GroupDto groupDto) throws RestClientException {
        updateGroupWithHttpInfo(id, groupDto);
    }

    /**
     * Update Group
     * Updates a given group by id.
     * <p><b>204</b> - Request successful. No content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - If the group with the requested Id cannot be found.
     * <p><b>500</b> - The group could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the group. (required)
     * @param groupDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateGroupWithHttpInfo(String id, GroupDto groupDto) throws RestClientException {
        Object postBody = groupDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/group/{id}", uriVariables);

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
