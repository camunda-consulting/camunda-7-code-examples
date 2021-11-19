package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.EventSubscriptionDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;

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
@Component("com.camunda.consulting.openapi.client.handler.EventSubscriptionApi")
public class EventSubscriptionApi {
    private ApiClient apiClient;

    public EventSubscriptionApi() {
        this(new ApiClient());
    }

    @Autowired
    public EventSubscriptionApi(ApiClient apiClient) {
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
     * Queries for event subscriptions that fulfill given parameters. The size of the result set can be retrieved by using the [Get Event Subscriptions count](https://docs.camunda.org/manual/7.16/reference/rest/event-subscription/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param eventSubscriptionId Only select subscription with the given id. (optional)
     * @param eventName Only select subscriptions for events with the given name. (optional)
     * @param eventType Only select subscriptions for events with the given type. Valid values: &#x60;message&#x60;, &#x60;signal&#x60;, &#x60;compensate&#x60; and &#x60;conditional&#x60;. (optional)
     * @param executionId Only select subscriptions that belong to an execution with the given id. (optional)
     * @param processInstanceId Only select subscriptions that belong to a process instance with the given id. (optional)
     * @param activityId Only select subscriptions that belong to an activity with the given id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. (optional)
     * @param withoutTenantId Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeEventSubscriptionsWithoutTenantId Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;EventSubscriptionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<EventSubscriptionDto> getEventSubscriptions(String eventSubscriptionId, String eventName, String eventType, String executionId, String processInstanceId, String activityId, String tenantIdIn, Boolean withoutTenantId, Boolean includeEventSubscriptionsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getEventSubscriptionsWithHttpInfo(eventSubscriptionId, eventName, eventType, executionId, processInstanceId, activityId, tenantIdIn, withoutTenantId, includeEventSubscriptionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for event subscriptions that fulfill given parameters. The size of the result set can be retrieved by using the [Get Event Subscriptions count](https://docs.camunda.org/manual/7.16/reference/rest/event-subscription/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param eventSubscriptionId Only select subscription with the given id. (optional)
     * @param eventName Only select subscriptions for events with the given name. (optional)
     * @param eventType Only select subscriptions for events with the given type. Valid values: &#x60;message&#x60;, &#x60;signal&#x60;, &#x60;compensate&#x60; and &#x60;conditional&#x60;. (optional)
     * @param executionId Only select subscriptions that belong to an execution with the given id. (optional)
     * @param processInstanceId Only select subscriptions that belong to a process instance with the given id. (optional)
     * @param activityId Only select subscriptions that belong to an activity with the given id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. (optional)
     * @param withoutTenantId Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeEventSubscriptionsWithoutTenantId Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;EventSubscriptionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<EventSubscriptionDto>> getEventSubscriptionsWithHttpInfo(String eventSubscriptionId, String eventName, String eventType, String executionId, String processInstanceId, String activityId, String tenantIdIn, Boolean withoutTenantId, Boolean includeEventSubscriptionsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/event-subscription", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "eventSubscriptionId", eventSubscriptionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "eventName", eventName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "eventType", eventType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeEventSubscriptionsWithoutTenantId", includeEventSubscriptionsWithoutTenantId));
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

        ParameterizedTypeReference<List<EventSubscriptionDto>> returnType = new ParameterizedTypeReference<List<EventSubscriptionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of event subscriptions that fulfill given parameters. Takes the same parameters as the [Get Event Subscriptions](https://docs.camunda.org/manual/7.16/reference/rest/event-subscription/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param eventSubscriptionId Only select subscription with the given id. (optional)
     * @param eventName Only select subscriptions for events with the given name. (optional)
     * @param eventType Only select subscriptions for events with the given type. Valid values: &#x60;message&#x60;, &#x60;signal&#x60;, &#x60;compensate&#x60; and &#x60;conditional&#x60;. (optional)
     * @param executionId Only select subscriptions that belong to an execution with the given id. (optional)
     * @param processInstanceId Only select subscriptions that belong to a process instance with the given id. (optional)
     * @param activityId Only select subscriptions that belong to an activity with the given id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. (optional)
     * @param withoutTenantId Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeEventSubscriptionsWithoutTenantId Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getEventSubscriptionsCount(String eventSubscriptionId, String eventName, String eventType, String executionId, String processInstanceId, String activityId, String tenantIdIn, Boolean withoutTenantId, Boolean includeEventSubscriptionsWithoutTenantId) throws RestClientException {
        return getEventSubscriptionsCountWithHttpInfo(eventSubscriptionId, eventName, eventType, executionId, processInstanceId, activityId, tenantIdIn, withoutTenantId, includeEventSubscriptionsWithoutTenantId).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of event subscriptions that fulfill given parameters. Takes the same parameters as the [Get Event Subscriptions](https://docs.camunda.org/manual/7.16/reference/rest/event-subscription/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param eventSubscriptionId Only select subscription with the given id. (optional)
     * @param eventName Only select subscriptions for events with the given name. (optional)
     * @param eventType Only select subscriptions for events with the given type. Valid values: &#x60;message&#x60;, &#x60;signal&#x60;, &#x60;compensate&#x60; and &#x60;conditional&#x60;. (optional)
     * @param executionId Only select subscriptions that belong to an execution with the given id. (optional)
     * @param processInstanceId Only select subscriptions that belong to a process instance with the given id. (optional)
     * @param activityId Only select subscriptions that belong to an activity with the given id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. (optional)
     * @param withoutTenantId Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeEventSubscriptionsWithoutTenantId Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getEventSubscriptionsCountWithHttpInfo(String eventSubscriptionId, String eventName, String eventType, String executionId, String processInstanceId, String activityId, String tenantIdIn, Boolean withoutTenantId, Boolean includeEventSubscriptionsWithoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/event-subscription/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "eventSubscriptionId", eventSubscriptionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "eventName", eventName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "eventType", eventType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityId", activityId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeEventSubscriptionsWithoutTenantId", includeEventSubscriptionsWithoutTenantId));

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
}
