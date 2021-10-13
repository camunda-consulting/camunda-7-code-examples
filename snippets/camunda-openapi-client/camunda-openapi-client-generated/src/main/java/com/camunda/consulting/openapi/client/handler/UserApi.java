package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.ResourceOptionsDto;
import com.camunda.consulting.openapi.client.model.UserCredentialsDto;
import com.camunda.consulting.openapi.client.model.UserDto;
import com.camunda.consulting.openapi.client.model.UserProfileDto;

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
@Component("com.camunda.consulting.openapi.client.handler.UserApi")
public class UserApi {
    private ApiClient apiClient;

    public UserApi() {
        this(new ApiClient());
    }

    @Autowired
    public UserApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Options
     * The &#x60;/user&#x60; resource supports two custom &#x60;OPTIONS&#x60; requests, one for the resource as such and one for individual user instances. The &#x60;OPTIONS&#x60; request allows checking for the set of available operations that the currently authenticated user can perform on the /user resource. If the user can perform an operation or not may depend on various things, including the user&#39;s authorizations to interact with this resource and the internal configuration of the process engine. &#x60;OPTIONS /user&#x60; returns available interactions on the resource.
     * <p><b>200</b> - Request successful.
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableOperations() throws RestClientException {
        return availableOperationsWithHttpInfo().getBody();
    }

    /**
     * Options
     * The &#x60;/user&#x60; resource supports two custom &#x60;OPTIONS&#x60; requests, one for the resource as such and one for individual user instances. The &#x60;OPTIONS&#x60; request allows checking for the set of available operations that the currently authenticated user can perform on the /user resource. If the user can perform an operation or not may depend on various things, including the user&#39;s authorizations to interact with this resource and the internal configuration of the process engine. &#x60;OPTIONS /user&#x60; returns available interactions on the resource.
     * <p><b>200</b> - Request successful.
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableOperationsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/user", Collections.<String, Object>emptyMap());

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
     * Options
     * The &#x60;/user&#x60; resource supports two custom &#x60;OPTIONS&#x60; requests, one for the resource as such and one for individual user instances. The &#x60;OPTIONS&#x60; request allows checking for the set of available operations that the currently authenticated user can perform on the /user resource. If the user can perform an operation or not may depend on various things, including the user&#39;s authorizations to interact with this resource and the internal configuration of the process engine. &#x60;OPTIONS /user/{id}&#x60; returns available interactions on a resource instance.
     * <p><b>200</b> - Request successful.
     * @param id The id of the user to be deleted. (required)
     * @return ResourceOptionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResourceOptionsDto availableUserOperations(String id) throws RestClientException {
        return availableUserOperationsWithHttpInfo(id).getBody();
    }

    /**
     * Options
     * The &#x60;/user&#x60; resource supports two custom &#x60;OPTIONS&#x60; requests, one for the resource as such and one for individual user instances. The &#x60;OPTIONS&#x60; request allows checking for the set of available operations that the currently authenticated user can perform on the /user resource. If the user can perform an operation or not may depend on various things, including the user&#39;s authorizations to interact with this resource and the internal configuration of the process engine. &#x60;OPTIONS /user/{id}&#x60; returns available interactions on a resource instance.
     * <p><b>200</b> - Request successful.
     * @param id The id of the user to be deleted. (required)
     * @return ResponseEntity&lt;ResourceOptionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ResourceOptionsDto> availableUserOperationsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling availableUserOperations");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/user/{id}", uriVariables);

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
     * Create
     * Create a new user.
     * <p><b>204</b> - Request successful.
     * @param userDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void createUser(UserDto userDto) throws RestClientException {
        createUserWithHttpInfo(userDto);
    }

    /**
     * Create
     * Create a new user.
     * <p><b>204</b> - Request successful.
     * @param userDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> createUserWithHttpInfo(UserDto userDto) throws RestClientException {
        Object postBody = userDto;
        
        String path = apiClient.expandPath("/user/create", Collections.<String, Object>emptyMap());

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
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Delete
     * Deletes a user by id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - A Deployment with the provided id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to be deleted. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteUser(String id) throws RestClientException {
        deleteUserWithHttpInfo(id);
    }

    /**
     * Delete
     * Deletes a user by id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - A Deployment with the provided id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to be deleted. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteUserWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/user/{id}", uriVariables);

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
     * Get List Count
     * Queries for the number of deployments that fulfill given parameters. Takes the same parameters as the [Get Users](https://docs.camunda.org/manual/7.16/reference/rest/user/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by user id (optional)
     * @param idIn Filter by a comma-separated list of user ids. (optional)
     * @param firstName Filter by the first name of the user. Exact match. (optional)
     * @param firstNameLike Filter by the first name that the parameter is a substring of. (optional)
     * @param lastName Filter by the last name of the user. Exact match. (optional)
     * @param lastNameLike Filter by the last name that the parameter is a substring of. (optional)
     * @param email Filter by the email of the user. Exact match. (optional)
     * @param emailLike Filter by the email that the parameter is a substring of. (optional)
     * @param memberOfGroup Filter for users which are members of the given group. (optional)
     * @param memberOfTenant Filter for users which are members of the given tenant. (optional)
     * @param potentialStarter Only select Users that are potential starter for the given process definition. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getUserCount(String id, String idIn, String firstName, String firstNameLike, String lastName, String lastNameLike, String email, String emailLike, String memberOfGroup, String memberOfTenant, String potentialStarter) throws RestClientException {
        return getUserCountWithHttpInfo(id, idIn, firstName, firstNameLike, lastName, lastNameLike, email, emailLike, memberOfGroup, memberOfTenant, potentialStarter).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of deployments that fulfill given parameters. Takes the same parameters as the [Get Users](https://docs.camunda.org/manual/7.16/reference/rest/user/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by user id (optional)
     * @param idIn Filter by a comma-separated list of user ids. (optional)
     * @param firstName Filter by the first name of the user. Exact match. (optional)
     * @param firstNameLike Filter by the first name that the parameter is a substring of. (optional)
     * @param lastName Filter by the last name of the user. Exact match. (optional)
     * @param lastNameLike Filter by the last name that the parameter is a substring of. (optional)
     * @param email Filter by the email of the user. Exact match. (optional)
     * @param emailLike Filter by the email that the parameter is a substring of. (optional)
     * @param memberOfGroup Filter for users which are members of the given group. (optional)
     * @param memberOfTenant Filter for users which are members of the given tenant. (optional)
     * @param potentialStarter Only select Users that are potential starter for the given process definition. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getUserCountWithHttpInfo(String id, String idIn, String firstName, String firstNameLike, String lastName, String lastNameLike, String email, String emailLike, String memberOfGroup, String memberOfTenant, String potentialStarter) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/user/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "idIn", idIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstName", firstName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstNameLike", firstNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lastName", lastName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lastNameLike", lastNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "email", email));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "emailLike", emailLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "memberOfGroup", memberOfGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "memberOfTenant", memberOfTenant));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "potentialStarter", potentialStarter));

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
     * Get Profile
     * Retrieves a user&#39;s profile.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to retrieve. (required)
     * @return UserProfileDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UserProfileDto getUserProfile(String id) throws RestClientException {
        return getUserProfileWithHttpInfo(id).getBody();
    }

    /**
     * Get Profile
     * Retrieves a user&#39;s profile.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to retrieve. (required)
     * @return ResponseEntity&lt;UserProfileDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UserProfileDto> getUserProfileWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getUserProfile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/user/{id}/profile", uriVariables);

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

        ParameterizedTypeReference<UserProfileDto> returnType = new ParameterizedTypeReference<UserProfileDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Query for a list of users using a list of parameters. The size of the result set can be retrieved by using the Get User Count method. [Get User Count](https://docs.camunda.org/manual/7.16/reference/rest/user/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by user id (optional)
     * @param idIn Filter by a comma-separated list of user ids. (optional)
     * @param firstName Filter by the first name of the user. Exact match. (optional)
     * @param firstNameLike Filter by the first name that the parameter is a substring of. (optional)
     * @param lastName Filter by the last name of the user. Exact match. (optional)
     * @param lastNameLike Filter by the last name that the parameter is a substring of. (optional)
     * @param email Filter by the email of the user. Exact match. (optional)
     * @param emailLike Filter by the email that the parameter is a substring of. (optional)
     * @param memberOfGroup Filter for users which are members of the given group. (optional)
     * @param memberOfTenant Filter for users which are members of the given tenant. (optional)
     * @param potentialStarter Only select Users that are potential starter for the given process definition. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;UserProfileDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<UserProfileDto> getUsers(String id, String idIn, String firstName, String firstNameLike, String lastName, String lastNameLike, String email, String emailLike, String memberOfGroup, String memberOfTenant, String potentialStarter, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getUsersWithHttpInfo(id, idIn, firstName, firstNameLike, lastName, lastNameLike, email, emailLike, memberOfGroup, memberOfTenant, potentialStarter, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Query for a list of users using a list of parameters. The size of the result set can be retrieved by using the Get User Count method. [Get User Count](https://docs.camunda.org/manual/7.16/reference/rest/user/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by user id (optional)
     * @param idIn Filter by a comma-separated list of user ids. (optional)
     * @param firstName Filter by the first name of the user. Exact match. (optional)
     * @param firstNameLike Filter by the first name that the parameter is a substring of. (optional)
     * @param lastName Filter by the last name of the user. Exact match. (optional)
     * @param lastNameLike Filter by the last name that the parameter is a substring of. (optional)
     * @param email Filter by the email of the user. Exact match. (optional)
     * @param emailLike Filter by the email that the parameter is a substring of. (optional)
     * @param memberOfGroup Filter for users which are members of the given group. (optional)
     * @param memberOfTenant Filter for users which are members of the given tenant. (optional)
     * @param potentialStarter Only select Users that are potential starter for the given process definition. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;UserProfileDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<UserProfileDto>> getUsersWithHttpInfo(String id, String idIn, String firstName, String firstNameLike, String lastName, String lastNameLike, String email, String emailLike, String memberOfGroup, String memberOfTenant, String potentialStarter, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/user", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "idIn", idIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstName", firstName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstNameLike", firstNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lastName", lastName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lastNameLike", lastNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "email", email));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "emailLike", emailLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "memberOfGroup", memberOfGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "memberOfTenant", memberOfTenant));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "potentialStarter", potentialStarter));
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

        ParameterizedTypeReference<List<UserProfileDto>> returnType = new ParameterizedTypeReference<List<UserProfileDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Unlock User
     * Unlocks a user by id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - The user who performed the operation is not a Camunda admin user.
     * <p><b>404</b> - User cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to be unlocked. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void unlockUser(String id) throws RestClientException {
        unlockUserWithHttpInfo(id);
    }

    /**
     * Unlock User
     * Unlocks a user by id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - The user who performed the operation is not a Camunda admin user.
     * <p><b>404</b> - User cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to be unlocked. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> unlockUserWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling unlockUser");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/user/{id}/unlock", uriVariables);

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
     * Update Credentials
     * Updates a user&#39;s credentials (password)
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>400</b> - The authenticated user password does not match
     * <p><b>404</b> - If the corresponding user cannot be found
     * <p><b>500</b> - The user could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to be updated. (required)
     * @param userCredentialsDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateCredentials(String id, UserCredentialsDto userCredentialsDto) throws RestClientException {
        updateCredentialsWithHttpInfo(id, userCredentialsDto);
    }

    /**
     * Update Credentials
     * Updates a user&#39;s credentials (password)
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>400</b> - The authenticated user password does not match
     * <p><b>404</b> - If the corresponding user cannot be found
     * <p><b>500</b> - The user could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user to be updated. (required)
     * @param userCredentialsDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateCredentialsWithHttpInfo(String id, UserCredentialsDto userCredentialsDto) throws RestClientException {
        Object postBody = userCredentialsDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateCredentials");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/user/{id}/credentials", uriVariables);

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
     * Update User Profile
     * Updates the profile information of an already existing user.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - If the user with the requested Id cannot be found.
     * <p><b>500</b> - The user could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user. (required)
     * @param userProfileDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateProfile(String id, UserProfileDto userProfileDto) throws RestClientException {
        updateProfileWithHttpInfo(id, userProfileDto);
    }

    /**
     * Update User Profile
     * Updates the profile information of an already existing user.
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>403</b> - Identity service is read-only (Cannot modify users / groups / memberships).
     * <p><b>404</b> - If the user with the requested Id cannot be found.
     * <p><b>500</b> - The user could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the user. (required)
     * @param userProfileDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateProfileWithHttpInfo(String id, UserProfileDto userProfileDto) throws RestClientException {
        Object postBody = userProfileDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateProfile");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/user/{id}/profile", uriVariables);

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
