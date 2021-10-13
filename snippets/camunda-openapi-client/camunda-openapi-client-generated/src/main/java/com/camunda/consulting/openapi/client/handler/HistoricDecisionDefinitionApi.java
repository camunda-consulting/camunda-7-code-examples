package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CleanableHistoricDecisionInstanceReportResultDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T16:56:52.297572+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.HistoricDecisionDefinitionApi")
public class HistoricDecisionDefinitionApi {
    private ApiClient apiClient;

    public HistoricDecisionDefinitionApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricDecisionDefinitionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Cleanable Decision Instance Report
     * Retrieves a report about a decision definition and finished decision instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)),  so that you can tune the history time to live. These reports include the count of the finished historic decision instances, cleanable decision instances and basic decision definition data - id, key, name and version. The size of the result set can be retrieved by using the  [Get Cleanable Decision Instance Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-definition/get-cleanable-decision-instance-report-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionDefinitionIdIn Filter by decision definition ids. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKeyIn Filter by decision definition keys. Must be a comma-separated list of decision definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60;  is the default behavior. (optional)
     * @param compact Only include decision instances which have more than zero finished instances. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;CleanableHistoricDecisionInstanceReportResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<CleanableHistoricDecisionInstanceReportResultDto> getCleanableHistoricDecisionInstanceReport(String decisionDefinitionIdIn, String decisionDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getCleanableHistoricDecisionInstanceReportWithHttpInfo(decisionDefinitionIdIn, decisionDefinitionKeyIn, tenantIdIn, withoutTenantId, compact, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Cleanable Decision Instance Report
     * Retrieves a report about a decision definition and finished decision instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)),  so that you can tune the history time to live. These reports include the count of the finished historic decision instances, cleanable decision instances and basic decision definition data - id, key, name and version. The size of the result set can be retrieved by using the  [Get Cleanable Decision Instance Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-definition/get-cleanable-decision-instance-report-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionDefinitionIdIn Filter by decision definition ids. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKeyIn Filter by decision definition keys. Must be a comma-separated list of decision definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60;  is the default behavior. (optional)
     * @param compact Only include decision instances which have more than zero finished instances. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;CleanableHistoricDecisionInstanceReportResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<CleanableHistoricDecisionInstanceReportResultDto>> getCleanableHistoricDecisionInstanceReportWithHttpInfo(String decisionDefinitionIdIn, String decisionDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/decision-definition/cleanable-decision-instance-report", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionIdIn", decisionDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionKeyIn", decisionDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "compact", compact));
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

        ParameterizedTypeReference<List<CleanableHistoricDecisionInstanceReportResultDto>> returnType = new ParameterizedTypeReference<List<CleanableHistoricDecisionInstanceReportResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Cleanable Decision Instance Report Count
     * Queries for the number of report results about a decision definition and finished decision instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)). Takes the same parameters as the [Get Cleanable Decision Instance Report](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-definition/get-cleanable-decision-instance-report/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionDefinitionIdIn Filter by decision definition ids. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKeyIn Filter by decision definition keys. Must be a comma-separated list of decision definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60;  is the default behavior. (optional)
     * @param compact Only include decision instances which have more than zero finished instances. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getCleanableHistoricDecisionInstanceReportCount(String decisionDefinitionIdIn, String decisionDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact) throws RestClientException {
        return getCleanableHistoricDecisionInstanceReportCountWithHttpInfo(decisionDefinitionIdIn, decisionDefinitionKeyIn, tenantIdIn, withoutTenantId, compact).getBody();
    }

    /**
     * Get Cleanable Decision Instance Report Count
     * Queries for the number of report results about a decision definition and finished decision instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)). Takes the same parameters as the [Get Cleanable Decision Instance Report](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-definition/get-cleanable-decision-instance-report/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>500</b> - See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionDefinitionIdIn Filter by decision definition ids. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKeyIn Filter by decision definition keys. Must be a comma-separated list of decision definition keys. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60;  is the default behavior. (optional)
     * @param compact Only include decision instances which have more than zero finished instances. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getCleanableHistoricDecisionInstanceReportCountWithHttpInfo(String decisionDefinitionIdIn, String decisionDefinitionKeyIn, String tenantIdIn, Boolean withoutTenantId, Boolean compact) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/decision-definition/cleanable-decision-instance-report/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionIdIn", decisionDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionKeyIn", decisionDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "compact", compact));

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
