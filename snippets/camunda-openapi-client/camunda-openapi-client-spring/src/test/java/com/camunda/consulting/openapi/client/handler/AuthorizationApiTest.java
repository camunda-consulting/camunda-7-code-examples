/*
 * Camunda Platform REST API
 * OpenApi Spec for Camunda Platform REST API.
 *
 * The version of the OpenAPI document: 7.16.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.model.AuthorizationCheckResultDto;
import com.camunda.consulting.openapi.client.model.AuthorizationCreateDto;
import com.camunda.consulting.openapi.client.model.AuthorizationDto;
import com.camunda.consulting.openapi.client.model.AuthorizationUpdateDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.ResourceOptionsDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AuthorizationApi
 */
@Ignore
public class AuthorizationApiTest {

    private final AuthorizationApi api = new AuthorizationApi();

    
    /**
     * Authorization Resource Options
     *
     * The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void availableOperationsAuthorizationTest() {
        ResourceOptionsDto response = api.availableOperationsAuthorization();

        // TODO: test validations
    }
    
    /**
     * Authorization Resource Options
     *
     * The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on a given instance of the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void availableOperationsAuthorizationInstanceTest() {
        String id = null;
        ResourceOptionsDto response = api.availableOperationsAuthorizationInstance(id);

        // TODO: test validations
    }
    
    /**
     * Create a New Authorization
     *
     * Creates a new authorization.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createAuthorizationTest() {
        AuthorizationCreateDto authorizationCreateDto = null;
        AuthorizationDto response = api.createAuthorization(authorizationCreateDto);

        // TODO: test validations
    }
    
    /**
     * Delete Authorization
     *
     * Deletes an authorization by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAuthorizationTest() {
        String id = null;
        api.deleteAuthorization(id);

        // TODO: test validations
    }
    
    /**
     * Get Authorization
     *
     * Retrieves an authorization by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAuthorizationTest() {
        String id = null;
        AuthorizationDto response = api.getAuthorization(id);

        // TODO: test validations
    }
    
    /**
     * Get Authorization Count
     *
     * Queries for authorizations using a list of parameters and retrieves the count.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAuthorizationCountTest() {
        String id = null;
        Integer type = null;
        String userIdIn = null;
        String groupIdIn = null;
        Integer resourceType = null;
        String resourceId = null;
        CountResultDto response = api.getAuthorizationCount(id, type, userIdIn, groupIdIn, resourceType, resourceId);

        // TODO: test validations
    }
    
    /**
     * Perform an Authorization Check
     *
     * Performs an authorization check for the currently authenticated user.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void isUserAuthorizedTest() {
        String permissionName = null;
        String resourceName = null;
        Integer resourceType = null;
        String resourceId = null;
        String userId = null;
        AuthorizationCheckResultDto response = api.isUserAuthorized(permissionName, resourceName, resourceType, resourceId, userId);

        // TODO: test validations
    }
    
    /**
     * Get Authorizations
     *
     * Queries for a list of authorizations using a list of parameters. The size of the result set can be retrieved by using the [Get Authorization Count](https://docs.camunda.org/manual/7.16/reference/rest/authorization/get-query-count/) method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void queryAuthorizationsTest() {
        String id = null;
        Integer type = null;
        String userIdIn = null;
        String groupIdIn = null;
        Integer resourceType = null;
        String resourceId = null;
        String sortBy = null;
        String sortOrder = null;
        Integer firstResult = null;
        Integer maxResults = null;
        List<AuthorizationDto> response = api.queryAuthorizations(id, type, userIdIn, groupIdIn, resourceType, resourceId, sortBy, sortOrder, firstResult, maxResults);

        // TODO: test validations
    }
    
    /**
     * Update an Authorization
     *
     * Updates an authorization by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateAuthorizationTest() {
        String id = null;
        AuthorizationUpdateDto authorizationUpdateDto = null;
        api.updateAuthorization(id, authorizationUpdateDto);

        // TODO: test validations
    }
    
}
