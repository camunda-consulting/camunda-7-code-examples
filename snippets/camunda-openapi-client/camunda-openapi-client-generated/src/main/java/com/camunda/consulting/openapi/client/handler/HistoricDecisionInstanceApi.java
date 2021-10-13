package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DeleteHistoricDecisionInstancesDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricDecisionInstanceDto;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.SetRemovalTimeToHistoricDecisionInstancesDto;

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
@Component("com.camunda.consulting.openapi.client.handler.HistoricDecisionInstanceApi")
public class HistoricDecisionInstanceApi {
    private ApiClient apiClient;

    public HistoricDecisionInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricDecisionInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete Async (POST)
     * Delete multiple historic decision instances asynchronously (batch). At least &#x60;historicDecisionInstanceIds&#x60; or &#x60;historicDecisionInstanceQuery&#x60;  has to be provided. If both are provided then all instances matching query  criterion and instances from the list will be deleted.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, i.e. neither &#x60;historicDecisionInstanceIds&#x60; nor &#x60;historicDecisionInstanceQuery&#x60; is present. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling)  for the error response format.
     * @param deleteHistoricDecisionInstancesDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto deleteAsync(DeleteHistoricDecisionInstancesDto deleteHistoricDecisionInstancesDto) throws RestClientException {
        return deleteAsyncWithHttpInfo(deleteHistoricDecisionInstancesDto).getBody();
    }

    /**
     * Delete Async (POST)
     * Delete multiple historic decision instances asynchronously (batch). At least &#x60;historicDecisionInstanceIds&#x60; or &#x60;historicDecisionInstanceQuery&#x60;  has to be provided. If both are provided then all instances matching query  criterion and instances from the list will be deleted.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, i.e. neither &#x60;historicDecisionInstanceIds&#x60; nor &#x60;historicDecisionInstanceQuery&#x60; is present. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling)  for the error response format.
     * @param deleteHistoricDecisionInstancesDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> deleteAsyncWithHttpInfo(DeleteHistoricDecisionInstancesDto deleteHistoricDecisionInstancesDto) throws RestClientException {
        Object postBody = deleteHistoricDecisionInstancesDto;
        
        String path = apiClient.expandPath("/history/decision-instance/delete", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<BatchDto> returnType = new ParameterizedTypeReference<BatchDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Decision Instance
     * Retrieves a historic decision instance by id, according to the  &#x60;HistoricDecisionInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic decision instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic decision instance to be retrieved. (required)
     * @param includeInputs Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeOutputs Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableBinaryFetching Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableCustomObjectDeserialization Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return HistoricDecisionInstanceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HistoricDecisionInstanceDto getHistoricDecisionInstance(String id, Boolean includeInputs, Boolean includeOutputs, Boolean disableBinaryFetching, Boolean disableCustomObjectDeserialization) throws RestClientException {
        return getHistoricDecisionInstanceWithHttpInfo(id, includeInputs, includeOutputs, disableBinaryFetching, disableCustomObjectDeserialization).getBody();
    }

    /**
     * Get Historic Decision Instance
     * Retrieves a historic decision instance by id, according to the  &#x60;HistoricDecisionInstance&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Historic decision instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the historic decision instance to be retrieved. (required)
     * @param includeInputs Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeOutputs Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableBinaryFetching Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableCustomObjectDeserialization Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;HistoricDecisionInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<HistoricDecisionInstanceDto> getHistoricDecisionInstanceWithHttpInfo(String id, Boolean includeInputs, Boolean includeOutputs, Boolean disableBinaryFetching, Boolean disableCustomObjectDeserialization) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getHistoricDecisionInstance");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/history/decision-instance/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeInputs", includeInputs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeOutputs", includeOutputs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "disableBinaryFetching", disableBinaryFetching));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "disableCustomObjectDeserialization", disableCustomObjectDeserialization));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<HistoricDecisionInstanceDto> returnType = new ParameterizedTypeReference<HistoricDecisionInstanceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Decision Instances
     * Queries for historic decision instances that fulfill the given parameters.  The size of the result set can be retrieved by using the  [Get Historic Decision Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-instance/get-decision-instance-query-count/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionInstanceId Filter by decision instance id. (optional)
     * @param decisionInstanceIdIn Filter by decision instance ids. Must be a comma-separated list of decision instance ids. (optional)
     * @param decisionDefinitionId Filter by the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionIdIn Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKey Filter by the key of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionKeyIn Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. (optional)
     * @param decisionDefinitionName Filter by the name of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionNameLike Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. (optional)
     * @param processDefinitionId Filter by the process definition the instances belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances belongs to. (optional)
     * @param processInstanceId Filter by the process instance the instances belongs to. (optional)
     * @param caseDefinitionId Filter by the case definition the instances belongs to. (optional)
     * @param caseDefinitionKey Filter by the key of the case definition the instances belongs to. (optional)
     * @param caseInstanceId Filter by the case instance the instances belongs to. (optional)
     * @param activityIdIn Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. (optional)
     * @param activityInstanceIdIn Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param evaluatedBefore Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param evaluatedAfter Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param userId Restrict to instances that were evaluated by the given user. (optional)
     * @param rootDecisionInstanceId Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. (optional)
     * @param rootDecisionInstancesOnly Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param decisionRequirementsDefinitionId Filter by the decision requirements definition the instances belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition the instances belongs to. (optional)
     * @param includeInputs Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeOutputs Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableBinaryFetching Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableCustomObjectDeserialization Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;HistoricDecisionInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricDecisionInstanceDto> getHistoricDecisionInstances(String decisionInstanceId, String decisionInstanceIdIn, String decisionDefinitionId, String decisionDefinitionIdIn, String decisionDefinitionKey, String decisionDefinitionKeyIn, String decisionDefinitionName, String decisionDefinitionNameLike, String processDefinitionId, String processDefinitionKey, String processInstanceId, String caseDefinitionId, String caseDefinitionKey, String caseInstanceId, String activityIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, OffsetDateTime evaluatedBefore, OffsetDateTime evaluatedAfter, String userId, String rootDecisionInstanceId, Boolean rootDecisionInstancesOnly, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey, Boolean includeInputs, Boolean includeOutputs, Boolean disableBinaryFetching, Boolean disableCustomObjectDeserialization, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getHistoricDecisionInstancesWithHttpInfo(decisionInstanceId, decisionInstanceIdIn, decisionDefinitionId, decisionDefinitionIdIn, decisionDefinitionKey, decisionDefinitionKeyIn, decisionDefinitionName, decisionDefinitionNameLike, processDefinitionId, processDefinitionKey, processInstanceId, caseDefinitionId, caseDefinitionKey, caseInstanceId, activityIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, evaluatedBefore, evaluatedAfter, userId, rootDecisionInstanceId, rootDecisionInstancesOnly, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, includeInputs, includeOutputs, disableBinaryFetching, disableCustomObjectDeserialization, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Historic Decision Instances
     * Queries for historic decision instances that fulfill the given parameters.  The size of the result set can be retrieved by using the  [Get Historic Decision Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-instance/get-decision-instance-query-count/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionInstanceId Filter by decision instance id. (optional)
     * @param decisionInstanceIdIn Filter by decision instance ids. Must be a comma-separated list of decision instance ids. (optional)
     * @param decisionDefinitionId Filter by the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionIdIn Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKey Filter by the key of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionKeyIn Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. (optional)
     * @param decisionDefinitionName Filter by the name of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionNameLike Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. (optional)
     * @param processDefinitionId Filter by the process definition the instances belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances belongs to. (optional)
     * @param processInstanceId Filter by the process instance the instances belongs to. (optional)
     * @param caseDefinitionId Filter by the case definition the instances belongs to. (optional)
     * @param caseDefinitionKey Filter by the key of the case definition the instances belongs to. (optional)
     * @param caseInstanceId Filter by the case instance the instances belongs to. (optional)
     * @param activityIdIn Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. (optional)
     * @param activityInstanceIdIn Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param evaluatedBefore Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param evaluatedAfter Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param userId Restrict to instances that were evaluated by the given user. (optional)
     * @param rootDecisionInstanceId Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. (optional)
     * @param rootDecisionInstancesOnly Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param decisionRequirementsDefinitionId Filter by the decision requirements definition the instances belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition the instances belongs to. (optional)
     * @param includeInputs Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeOutputs Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableBinaryFetching Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param disableCustomObjectDeserialization Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricDecisionInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricDecisionInstanceDto>> getHistoricDecisionInstancesWithHttpInfo(String decisionInstanceId, String decisionInstanceIdIn, String decisionDefinitionId, String decisionDefinitionIdIn, String decisionDefinitionKey, String decisionDefinitionKeyIn, String decisionDefinitionName, String decisionDefinitionNameLike, String processDefinitionId, String processDefinitionKey, String processInstanceId, String caseDefinitionId, String caseDefinitionKey, String caseInstanceId, String activityIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, OffsetDateTime evaluatedBefore, OffsetDateTime evaluatedAfter, String userId, String rootDecisionInstanceId, Boolean rootDecisionInstancesOnly, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey, Boolean includeInputs, Boolean includeOutputs, Boolean disableBinaryFetching, Boolean disableCustomObjectDeserialization, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/decision-instance", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionInstanceId", decisionInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionInstanceIdIn", decisionInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionId", decisionDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionIdIn", decisionDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionKey", decisionDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionKeyIn", decisionDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionName", decisionDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionNameLike", decisionDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionKey", caseDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "evaluatedBefore", evaluatedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "evaluatedAfter", evaluatedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootDecisionInstanceId", rootDecisionInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootDecisionInstancesOnly", rootDecisionInstancesOnly));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionId", decisionRequirementsDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionKey", decisionRequirementsDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeInputs", includeInputs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeOutputs", includeOutputs));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "disableBinaryFetching", disableBinaryFetching));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "disableCustomObjectDeserialization", disableCustomObjectDeserialization));
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

        ParameterizedTypeReference<List<HistoricDecisionInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricDecisionInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Historic Decision Instance Count
     * Queries for the number of historic decision instances that fulfill the given parameters.  Takes the same parameters as the  [Get Historic Decision Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-instance/get-decision-instance-query/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionInstanceId Filter by decision instance id. (optional)
     * @param decisionInstanceIdIn Filter by decision instance ids. Must be a comma-separated list of decision instance ids. (optional)
     * @param decisionDefinitionId Filter by the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionIdIn Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKey Filter by the key of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionKeyIn Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. (optional)
     * @param decisionDefinitionName Filter by the name of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionNameLike Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. (optional)
     * @param processDefinitionId Filter by the process definition the instances belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances belongs to. (optional)
     * @param processInstanceId Filter by the process instance the instances belongs to. (optional)
     * @param caseDefinitionId Filter by the case definition the instances belongs to. (optional)
     * @param caseDefinitionKey Filter by the key of the case definition the instances belongs to. (optional)
     * @param caseInstanceId Filter by the case instance the instances belongs to. (optional)
     * @param activityIdIn Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. (optional)
     * @param activityInstanceIdIn Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param evaluatedBefore Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param evaluatedAfter Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param userId Restrict to instances that were evaluated by the given user. (optional)
     * @param rootDecisionInstanceId Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. (optional)
     * @param rootDecisionInstancesOnly Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param decisionRequirementsDefinitionId Filter by the decision requirements definition the instances belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition the instances belongs to. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricDecisionInstancesCount(String decisionInstanceId, String decisionInstanceIdIn, String decisionDefinitionId, String decisionDefinitionIdIn, String decisionDefinitionKey, String decisionDefinitionKeyIn, String decisionDefinitionName, String decisionDefinitionNameLike, String processDefinitionId, String processDefinitionKey, String processInstanceId, String caseDefinitionId, String caseDefinitionKey, String caseInstanceId, String activityIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, OffsetDateTime evaluatedBefore, OffsetDateTime evaluatedAfter, String userId, String rootDecisionInstanceId, Boolean rootDecisionInstancesOnly, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey) throws RestClientException {
        return getHistoricDecisionInstancesCountWithHttpInfo(decisionInstanceId, decisionInstanceIdIn, decisionDefinitionId, decisionDefinitionIdIn, decisionDefinitionKey, decisionDefinitionKeyIn, decisionDefinitionName, decisionDefinitionNameLike, processDefinitionId, processDefinitionKey, processInstanceId, caseDefinitionId, caseDefinitionKey, caseInstanceId, activityIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, evaluatedBefore, evaluatedAfter, userId, rootDecisionInstanceId, rootDecisionInstancesOnly, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey).getBody();
    }

    /**
     * Get Historic Decision Instance Count
     * Queries for the number of historic decision instances that fulfill the given parameters.  Takes the same parameters as the  [Get Historic Decision Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-instance/get-decision-instance-query/)  method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionInstanceId Filter by decision instance id. (optional)
     * @param decisionInstanceIdIn Filter by decision instance ids. Must be a comma-separated list of decision instance ids. (optional)
     * @param decisionDefinitionId Filter by the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionIdIn Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. (optional)
     * @param decisionDefinitionKey Filter by the key of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionKeyIn Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. (optional)
     * @param decisionDefinitionName Filter by the name of the decision definition the instances belongs to. (optional)
     * @param decisionDefinitionNameLike Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. (optional)
     * @param processDefinitionId Filter by the process definition the instances belongs to. (optional)
     * @param processDefinitionKey Filter by the key of the process definition the instances belongs to. (optional)
     * @param processInstanceId Filter by the process instance the instances belongs to. (optional)
     * @param caseDefinitionId Filter by the case definition the instances belongs to. (optional)
     * @param caseDefinitionKey Filter by the key of the case definition the instances belongs to. (optional)
     * @param caseInstanceId Filter by the case instance the instances belongs to. (optional)
     * @param activityIdIn Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. (optional)
     * @param activityInstanceIdIn Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param evaluatedBefore Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param evaluatedAfter Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param userId Restrict to instances that were evaluated by the given user. (optional)
     * @param rootDecisionInstanceId Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. (optional)
     * @param rootDecisionInstancesOnly Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param decisionRequirementsDefinitionId Filter by the decision requirements definition the instances belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition the instances belongs to. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricDecisionInstancesCountWithHttpInfo(String decisionInstanceId, String decisionInstanceIdIn, String decisionDefinitionId, String decisionDefinitionIdIn, String decisionDefinitionKey, String decisionDefinitionKeyIn, String decisionDefinitionName, String decisionDefinitionNameLike, String processDefinitionId, String processDefinitionKey, String processInstanceId, String caseDefinitionId, String caseDefinitionKey, String caseInstanceId, String activityIdIn, String activityInstanceIdIn, String tenantIdIn, Boolean withoutTenantId, OffsetDateTime evaluatedBefore, OffsetDateTime evaluatedAfter, String userId, String rootDecisionInstanceId, Boolean rootDecisionInstancesOnly, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/decision-instance/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionInstanceId", decisionInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionInstanceIdIn", decisionInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionId", decisionDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionIdIn", decisionDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionKey", decisionDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionKeyIn", decisionDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionName", decisionDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionNameLike", decisionDefinitionNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionKey", caseDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityIdIn", activityIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "evaluatedBefore", evaluatedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "evaluatedAfter", evaluatedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootDecisionInstanceId", rootDecisionInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "rootDecisionInstancesOnly", rootDecisionInstancesOnly));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionId", decisionRequirementsDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionKey", decisionRequirementsDefinitionKey));

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
     * Set Removal Time Async (POST)
     * Sets the removal time to multiple historic decision instances asynchronously (batch).  At least &#x60;historicDecisionInstanceIds&#x60; or &#x60;historicDecisionInstanceQuery&#x60; has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Request was unsuccessfull due to a bad user request. This occurs if some of the query parameters are invalid, e. g. if neither historicDecisionInstances nor historicDecisionInstanceQuery is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRemovalTimeToHistoricDecisionInstancesDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto setRemovalTimeAsyncHistoricDecisionInstance(SetRemovalTimeToHistoricDecisionInstancesDto setRemovalTimeToHistoricDecisionInstancesDto) throws RestClientException {
        return setRemovalTimeAsyncHistoricDecisionInstanceWithHttpInfo(setRemovalTimeToHistoricDecisionInstancesDto).getBody();
    }

    /**
     * Set Removal Time Async (POST)
     * Sets the removal time to multiple historic decision instances asynchronously (batch).  At least &#x60;historicDecisionInstanceIds&#x60; or &#x60;historicDecisionInstanceQuery&#x60; has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Request was unsuccessfull due to a bad user request. This occurs if some of the query parameters are invalid, e. g. if neither historicDecisionInstances nor historicDecisionInstanceQuery is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param setRemovalTimeToHistoricDecisionInstancesDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> setRemovalTimeAsyncHistoricDecisionInstanceWithHttpInfo(SetRemovalTimeToHistoricDecisionInstancesDto setRemovalTimeToHistoricDecisionInstancesDto) throws RestClientException {
        Object postBody = setRemovalTimeToHistoricDecisionInstancesDto;
        
        String path = apiClient.expandPath("/history/decision-instance/set-removal-time", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<BatchDto> returnType = new ParameterizedTypeReference<BatchDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
