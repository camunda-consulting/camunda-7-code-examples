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

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.CreateFilterDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.FilterDto;
import com.camunda.consulting.openapi.client.model.ResourceOptionsDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for FilterApi
 */
@Ignore
public class FilterApiTest {

    private final FilterApi api = new FilterApi();

    
    /**
     * Create Filter
     *
     * Creates a new filter.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createFilterTest() {
        CreateFilterDto createFilterDto = null;
        FilterDto response = api.createFilter(createFilterDto);

        // TODO: test validations
    }
    
    /**
     * Delete Filter
     *
     * Deletes a filter by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteFilterTest() {
        String id = null;
        api.deleteFilter(id);

        // TODO: test validations
    }
    
    /**
     * Execute Filter Count
     *
     * Executes the saved query of the filter by id and returns the count.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeFilterCountTest() {
        String id = null;
        CountResultDto response = api.executeFilterCount(id);

        // TODO: test validations
    }
    
    /**
     * Execute Filter List
     *
     * Executes the saved query of the filter by id and returns the result list.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeFilterListTest() {
        String id = null;
        Integer firstResult = null;
        Integer maxResults = null;
        List<Object> response = api.executeFilterList(id, firstResult, maxResults);

        // TODO: test validations
    }
    
    /**
     * Execute Filter Single Result
     *
     * Executes the saved query of the filter by id and returns the single result.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeFilterSingleResultTest() {
        String id = null;
        Object response = api.executeFilterSingleResult(id);

        // TODO: test validations
    }
    
    /**
     * Filter Resource Options
     *
     * The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void filterResourceOptionsTest() {
        ResourceOptionsDto response = api.filterResourceOptions();

        // TODO: test validations
    }
    
    /**
     * Filter Resource Options
     *
     * The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void filterResourceOptionsSingleTest() {
        String id = null;
        ResourceOptionsDto response = api.filterResourceOptionsSingle(id);

        // TODO: test validations
    }
    
    /**
     * Get Filter Count
     *
     * Retrieves the number of filters that fulfill a provided query. Corresponds to the size of the result set when using the  [Get Filters](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query/) method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getFilterCountTest() {
        String filterId = null;
        String resourceType = null;
        String name = null;
        String nameLike = null;
        String owner = null;
        CountResultDto response = api.getFilterCount(filterId, resourceType, name, nameLike, owner);

        // TODO: test validations
    }
    
    /**
     * Get Filters
     *
     * Queries for a list of filters using a list of parameters. The size of the result set can be retrieved by using the [Get Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query-count/) method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getFilterListTest() {
        String filterId = null;
        String resourceType = null;
        String name = null;
        String nameLike = null;
        String owner = null;
        Boolean itemCount = null;
        String sortBy = null;
        String sortOrder = null;
        Integer firstResult = null;
        Integer maxResults = null;
        List<FilterDto> response = api.getFilterList(filterId, resourceType, name, nameLike, owner, itemCount, sortBy, sortOrder, firstResult, maxResults);

        // TODO: test validations
    }
    
    /**
     * Get Single Filter
     *
     * Retrieves a single filter by id, according to the &#x60;Filter&#x60; interface in the engine.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSingleFilterTest() {
        String id = null;
        Boolean itemCount = null;
        FilterDto response = api.getSingleFilter(id, itemCount);

        // TODO: test validations
    }
    
    /**
     * Execute Filter Count (POST)
     *
     * Executes the saved query of the filter by id and returns the count. This method is slightly more powerful then the [Get Execute Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-count/)  method because it allows to extend the saved query of the filter.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postExecuteFilterCountTest() {
        String id = null;
        Object body = null;
        CountResultDto response = api.postExecuteFilterCount(id, body);

        // TODO: test validations
    }
    
    /**
     * Execute Filter List (POST)
     *
     * Executes the saved query of the filter by id and returns the result list. This method is slightly more powerful then the  [Get Execute FilterList](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-list/) method because it allows to extend the saved query of the filter.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postExecuteFilterListTest() {
        String id = null;
        Integer firstResult = null;
        Integer maxResults = null;
        Object body = null;
        List<Object> response = api.postExecuteFilterList(id, firstResult, maxResults, body);

        // TODO: test validations
    }
    
    /**
     * Execute Filter Single Result (POST)
     *
     * Executes the saved query of the filter by id and returns the single result. This method is slightly more powerful then the [Get Execute Filter Single Result](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-single-result/) method because it allows to extend the saved query of the filter.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postExecuteFilterSingleResultTest() {
        String id = null;
        Object body = null;
        Object response = api.postExecuteFilterSingleResult(id, body);

        // TODO: test validations
    }
    
    /**
     * Update Filter
     *
     * Updates an existing filter.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateFilterTest() {
        String id = null;
        CreateFilterDto createFilterDto = null;
        api.updateFilter(id, createFilterDto);

        // TODO: test validations
    }
    
}
