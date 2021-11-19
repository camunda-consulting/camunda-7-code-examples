package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricIdentityLinkLogDto;
import java.time.OffsetDateTime;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricIdentityLinkLogApi")
public class HistoricIdentityLinkLogApi {
    private ApiClient apiClient;

    public HistoricIdentityLinkLogApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricIdentityLinkLogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Identity Link Logs
     * Queries for historic identity link logs that fulfill given parameters. The size of the result set can be retrieved by using the [Get Identity-Link-Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/identity-links/get-identity-link-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param type Restricts to identity links that have the given type (candidate/assignee/owner). (optional)
     * @param userId Restricts to identity links that have the given user id. (optional)
     * @param groupId Restricts to identity links that have the given group id. (optional)
     * @param dateBefore Restricts to identity links that have the time before the given time. (optional)
     * @param dateAfter Restricts to identity links that have the time after the given time. (optional)
     * @param taskId Restricts to identity links that have the given task id. (optional)
     * @param processDefinitionId Restricts to identity links that have the given process definition id. (optional)
     * @param processDefinitionKey Restricts to identity links that have the given process definition key. (optional)
     * @param operationType Restricts to identity links that have the given operationType (add/delete). (optional)
     * @param assignerId Restricts to identity links that have the given assigner id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic identity links that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;HistoricIdentityLinkLogDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricIdentityLinkLogDto> getHistoricIdentityLinks(String type, String userId, String groupId, OffsetDateTime dateBefore, OffsetDateTime dateAfter, String taskId, String processDefinitionId, String processDefinitionKey, String operationType, String assignerId, String tenantIdIn, Boolean withoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getHistoricIdentityLinksWithHttpInfo(type, userId, groupId, dateBefore, dateAfter, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Identity Link Logs
     * Queries for historic identity link logs that fulfill given parameters. The size of the result set can be retrieved by using the [Get Identity-Link-Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/identity-links/get-identity-link-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param type Restricts to identity links that have the given type (candidate/assignee/owner). (optional)
     * @param userId Restricts to identity links that have the given user id. (optional)
     * @param groupId Restricts to identity links that have the given group id. (optional)
     * @param dateBefore Restricts to identity links that have the time before the given time. (optional)
     * @param dateAfter Restricts to identity links that have the time after the given time. (optional)
     * @param taskId Restricts to identity links that have the given task id. (optional)
     * @param processDefinitionId Restricts to identity links that have the given process definition id. (optional)
     * @param processDefinitionKey Restricts to identity links that have the given process definition key. (optional)
     * @param operationType Restricts to identity links that have the given operationType (add/delete). (optional)
     * @param assignerId Restricts to identity links that have the given assigner id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic identity links that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricIdentityLinkLogDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricIdentityLinkLogDto>> getHistoricIdentityLinksWithHttpInfo(String type, String userId, String groupId, OffsetDateTime dateBefore, OffsetDateTime dateAfter, String taskId, String processDefinitionId, String processDefinitionKey, String operationType, String assignerId, String tenantIdIn, Boolean withoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/identity-link-log", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupId", groupId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateBefore", dateBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateAfter", dateAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "operationType", operationType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assignerId", assignerId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
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

        ParameterizedTypeReference<List<HistoricIdentityLinkLogDto>> returnType = new ParameterizedTypeReference<List<HistoricIdentityLinkLogDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Identity Link Log Count
     * Queries for the number of historic identity link logs that fulfill the given parameters. Takes the same parameters as the [Get Identity-Link-Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/identity-links/get-identity-link-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param type Restricts to identity links that have the given type (candidate/assignee/owner). (optional)
     * @param userId Restricts to identity links that have the given user id. (optional)
     * @param groupId Restricts to identity links that have the given group id. (optional)
     * @param dateBefore Restricts to identity links that have the time before the given time. (optional)
     * @param dateAfter Restricts to identity links that have the time after the given time. (optional)
     * @param taskId Restricts to identity links that have the given task id. (optional)
     * @param processDefinitionId Restricts to identity links that have the given process definition id. (optional)
     * @param processDefinitionKey Restricts to identity links that have the given process definition key. (optional)
     * @param operationType Restricts to identity links that have the given operationType (add/delete). (optional)
     * @param assignerId Restricts to identity links that have the given assigner id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic identity links that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricIdentityLinksCount(String type, String userId, String groupId, OffsetDateTime dateBefore, OffsetDateTime dateAfter, String taskId, String processDefinitionId, String processDefinitionKey, String operationType, String assignerId, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        return getHistoricIdentityLinksCountWithHttpInfo(type, userId, groupId, dateBefore, dateAfter, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantIdIn, withoutTenantId).getBody();
    }

    /**
     * Get Identity Link Log Count
     * Queries for the number of historic identity link logs that fulfill the given parameters. Takes the same parameters as the [Get Identity-Link-Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/identity-links/get-identity-link-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param type Restricts to identity links that have the given type (candidate/assignee/owner). (optional)
     * @param userId Restricts to identity links that have the given user id. (optional)
     * @param groupId Restricts to identity links that have the given group id. (optional)
     * @param dateBefore Restricts to identity links that have the time before the given time. (optional)
     * @param dateAfter Restricts to identity links that have the time after the given time. (optional)
     * @param taskId Restricts to identity links that have the given task id. (optional)
     * @param processDefinitionId Restricts to identity links that have the given process definition id. (optional)
     * @param processDefinitionKey Restricts to identity links that have the given process definition key. (optional)
     * @param operationType Restricts to identity links that have the given operationType (add/delete). (optional)
     * @param assignerId Restricts to identity links that have the given assigner id. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. (optional)
     * @param withoutTenantId Only include historic identity links that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricIdentityLinksCountWithHttpInfo(String type, String userId, String groupId, OffsetDateTime dateBefore, OffsetDateTime dateAfter, String taskId, String processDefinitionId, String processDefinitionKey, String operationType, String assignerId, String tenantIdIn, Boolean withoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/identity-link-log/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupId", groupId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateBefore", dateBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateAfter", dateAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "operationType", operationType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assignerId", assignerId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));

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
