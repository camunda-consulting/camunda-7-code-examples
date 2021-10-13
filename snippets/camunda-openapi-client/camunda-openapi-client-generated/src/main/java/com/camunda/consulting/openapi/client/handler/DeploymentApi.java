package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DeploymentDto;
import com.camunda.consulting.openapi.client.model.DeploymentResourceDto;
import com.camunda.consulting.openapi.client.model.DeploymentWithDefinitionsDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.ParseExceptionDto;
import com.camunda.consulting.openapi.client.model.RedeploymentDto;

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
@Component("com.camunda.consulting.openapi.client.handler.DeploymentApi")
public class DeploymentApi {
    private ApiClient apiClient;

    public DeploymentApi() {
        this(new ApiClient());
    }

    @Autowired
    public DeploymentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create
     * Creates a deployment.  **Security Consideration**  Deployments can contain custom code in form of scripts or EL expressions to customize process behavior. This may be abused for remote execution of arbitrary code.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request. In case one of the bpmn resources cannot be parsed.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#parse-exceptions) for the error response format.
     * @param tenantId The tenant id for the deployment to be created. (optional)
     * @param deploymentSource The source for the deployment to be created. (optional)
     * @param deployChangedOnly A flag indicating whether the process engine should perform duplicate checking on a per-resource basis. If set to true, only those resources that have actually changed are deployed. Checks are made against resources included previous deployments of the same name and only against the latest versions of those resources. If set to true, the option enable-duplicate-filtering is overridden and set to true. (optional, default to false)
     * @param enableDuplicateFiltering A flag indicating whether the process engine should perform duplicate checking for the deployment or not. This allows you to check if a deployment with the same name and the same resouces already exists and if true, not create a new deployment but instead return the existing deployment. The default value is false. (optional, default to false)
     * @param deploymentName The name for the deployment to be created. (optional)
     * @param deploymentActivationTime Sets the date on which the process definitions contained in this deployment will be activated. This means that all process definitions will be deployed as usual, but they will be suspended from the start until the given activation date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param data The binary data to create the deployment resource. It is possible to have more than one form part with different form part names for the binary data to create a deployment. (optional)
     * @return DeploymentWithDefinitionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeploymentWithDefinitionsDto createDeployment(String tenantId, String deploymentSource, Boolean deployChangedOnly, Boolean enableDuplicateFiltering, String deploymentName, OffsetDateTime deploymentActivationTime, File data) throws RestClientException {
        return createDeploymentWithHttpInfo(tenantId, deploymentSource, deployChangedOnly, enableDuplicateFiltering, deploymentName, deploymentActivationTime, data).getBody();
    }

    /**
     * Create
     * Creates a deployment.  **Security Consideration**  Deployments can contain custom code in form of scripts or EL expressions to customize process behavior. This may be abused for remote execution of arbitrary code.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Bad Request. In case one of the bpmn resources cannot be parsed.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#parse-exceptions) for the error response format.
     * @param tenantId The tenant id for the deployment to be created. (optional)
     * @param deploymentSource The source for the deployment to be created. (optional)
     * @param deployChangedOnly A flag indicating whether the process engine should perform duplicate checking on a per-resource basis. If set to true, only those resources that have actually changed are deployed. Checks are made against resources included previous deployments of the same name and only against the latest versions of those resources. If set to true, the option enable-duplicate-filtering is overridden and set to true. (optional, default to false)
     * @param enableDuplicateFiltering A flag indicating whether the process engine should perform duplicate checking for the deployment or not. This allows you to check if a deployment with the same name and the same resouces already exists and if true, not create a new deployment but instead return the existing deployment. The default value is false. (optional, default to false)
     * @param deploymentName The name for the deployment to be created. (optional)
     * @param deploymentActivationTime Sets the date on which the process definitions contained in this deployment will be activated. This means that all process definitions will be deployed as usual, but they will be suspended from the start until the given activation date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param data The binary data to create the deployment resource. It is possible to have more than one form part with different form part names for the binary data to create a deployment. (optional)
     * @return ResponseEntity&lt;DeploymentWithDefinitionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeploymentWithDefinitionsDto> createDeploymentWithHttpInfo(String tenantId, String deploymentSource, Boolean deployChangedOnly, Boolean enableDuplicateFiltering, String deploymentName, OffsetDateTime deploymentActivationTime, File data) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/deployment/create", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (tenantId != null)
            formParams.add("tenant-id", tenantId);
        if (deploymentSource != null)
            formParams.add("deployment-source", deploymentSource);
        if (deployChangedOnly != null)
            formParams.add("deploy-changed-only", deployChangedOnly);
        if (enableDuplicateFiltering != null)
            formParams.add("enable-duplicate-filtering", enableDuplicateFiltering);
        if (deploymentName != null)
            formParams.add("deployment-name", deploymentName);
        if (deploymentActivationTime != null)
            formParams.add("deployment-activation-time", deploymentActivationTime);
        if (data != null)
            formParams.add("data", new FileSystemResource(data));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "multipart/form-data"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<DeploymentWithDefinitionsDto> returnType = new ParameterizedTypeReference<DeploymentWithDefinitionsDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Delete
     * Deletes a deployment by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - A Deployment with the provided id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment to be deleted. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this deployment should be deleted. (optional, default to false)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings &#x60;true&#x60;, if all input/output mappings should not be invoked. (optional, default to false)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteDeployment(String id, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        deleteDeploymentWithHttpInfo(id, cascade, skipCustomListeners, skipIoMappings);
    }

    /**
     * Delete
     * Deletes a deployment by id.
     * <p><b>204</b> - Request successful.
     * <p><b>404</b> - A Deployment with the provided id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment to be deleted. (required)
     * @param cascade &#x60;true&#x60;, if all process instances, historic process instances and jobs for this deployment should be deleted. (optional, default to false)
     * @param skipCustomListeners &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. (optional, default to false)
     * @param skipIoMappings &#x60;true&#x60;, if all input/output mappings should not be invoked. (optional, default to false)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteDeploymentWithHttpInfo(String id, Boolean cascade, Boolean skipCustomListeners, Boolean skipIoMappings) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteDeployment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/deployment/{id}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "cascade", cascade));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipCustomListeners", skipCustomListeners));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "skipIoMappings", skipIoMappings));

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
     * Get
     * Retrieves a deployment by id, according to the &#x60;Deployment&#x60; interface of the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment. (required)
     * @return DeploymentDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeploymentDto getDeployment(String id) throws RestClientException {
        return getDeploymentWithHttpInfo(id).getBody();
    }

    /**
     * Get
     * Retrieves a deployment by id, according to the &#x60;Deployment&#x60; interface of the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment. (required)
     * @return ResponseEntity&lt;DeploymentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeploymentDto> getDeploymentWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDeployment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/deployment/{id}", uriVariables);

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

        ParameterizedTypeReference<DeploymentDto> returnType = new ParameterizedTypeReference<DeploymentDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Resource
     * Retrieves a deployment resource by resource id for the given deployment.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment Resource with given resource id or deployment id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment (required)
     * @param resourceId The id of the deployment resource (required)
     * @return DeploymentResourceDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeploymentResourceDto getDeploymentResource(String id, String resourceId) throws RestClientException {
        return getDeploymentResourceWithHttpInfo(id, resourceId).getBody();
    }

    /**
     * Get Resource
     * Retrieves a deployment resource by resource id for the given deployment.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment Resource with given resource id or deployment id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment (required)
     * @param resourceId The id of the deployment resource (required)
     * @return ResponseEntity&lt;DeploymentResourceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeploymentResourceDto> getDeploymentResourceWithHttpInfo(String id, String resourceId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDeploymentResource");
        }
        
        // verify the required parameter 'resourceId' is set
        if (resourceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resourceId' when calling getDeploymentResource");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("resourceId", resourceId);
        String path = apiClient.expandPath("/deployment/{id}/resources/{resourceId}", uriVariables);

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

        ParameterizedTypeReference<DeploymentResourceDto> returnType = new ParameterizedTypeReference<DeploymentResourceDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Resource (Binary)
     * Retrieves the binary content of a deployment resource for the given deployment by id.
     * <p><b>200</b> - Request successful. The media type of the response depends on the filename.
     * <p><b>400</b> - Deployment Resource with given resource id or deployment id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment. (required)
     * @param resourceId The id of the deployment resource. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDeploymentResourceData(String id, String resourceId) throws RestClientException {
        return getDeploymentResourceDataWithHttpInfo(id, resourceId).getBody();
    }

    /**
     * Get Resource (Binary)
     * Retrieves the binary content of a deployment resource for the given deployment by id.
     * <p><b>200</b> - Request successful. The media type of the response depends on the filename.
     * <p><b>400</b> - Deployment Resource with given resource id or deployment id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment. (required)
     * @param resourceId The id of the deployment resource. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDeploymentResourceDataWithHttpInfo(String id, String resourceId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDeploymentResourceData");
        }
        
        // verify the required parameter 'resourceId' is set
        if (resourceId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'resourceId' when calling getDeploymentResourceData");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("resourceId", resourceId);
        String path = apiClient.expandPath("/deployment/{id}/resources/{resourceId}/data", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream", "*/*", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Resources
     * Retrieves all deployment resources of a given deployment.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment resources for the given deployment do not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment to retrieve the deployment resources for. (required)
     * @return List&lt;DeploymentResourceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<DeploymentResourceDto> getDeploymentResources(String id) throws RestClientException {
        return getDeploymentResourcesWithHttpInfo(id).getBody();
    }

    /**
     * Get Resources
     * Retrieves all deployment resources of a given deployment.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment resources for the given deployment do not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment to retrieve the deployment resources for. (required)
     * @return ResponseEntity&lt;List&lt;DeploymentResourceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<DeploymentResourceDto>> getDeploymentResourcesWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDeploymentResources");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/deployment/{id}/resources", uriVariables);

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

        ParameterizedTypeReference<List<DeploymentResourceDto>> returnType = new ParameterizedTypeReference<List<DeploymentResourceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for deployments that fulfill given parameters. Parameters may be the properties of deployments, such as the id or name or a range of the deployment time. The size of the result set can be retrieved by using the [Get Deployment count](https://docs.camunda.org/manual/7.16/reference/rest/deployment/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by deployment id (optional)
     * @param name Filter by the deployment name. Exact match. (optional)
     * @param nameLike Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param source Filter by the deployment source. (optional)
     * @param withoutSource Filter by the deployment source whereby source is equal to &#x60;null&#x60;. (optional, default to false)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include deployments which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param includeDeploymentsWithoutTenantId Include deployments which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param after Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param before Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;DeploymentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<DeploymentDto> getDeployments(String id, String name, String nameLike, String source, Boolean withoutSource, String tenantIdIn, Boolean withoutTenantId, Boolean includeDeploymentsWithoutTenantId, OffsetDateTime after, OffsetDateTime before, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getDeploymentsWithHttpInfo(id, name, nameLike, source, withoutSource, tenantIdIn, withoutTenantId, includeDeploymentsWithoutTenantId, after, before, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get List
     * Queries for deployments that fulfill given parameters. Parameters may be the properties of deployments, such as the id or name or a range of the deployment time. The size of the result set can be retrieved by using the [Get Deployment count](https://docs.camunda.org/manual/7.16/reference/rest/deployment/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by deployment id (optional)
     * @param name Filter by the deployment name. Exact match. (optional)
     * @param nameLike Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param source Filter by the deployment source. (optional)
     * @param withoutSource Filter by the deployment source whereby source is equal to &#x60;null&#x60;. (optional, default to false)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include deployments which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param includeDeploymentsWithoutTenantId Include deployments which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param after Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param before Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;DeploymentDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<DeploymentDto>> getDeploymentsWithHttpInfo(String id, String name, String nameLike, String source, Boolean withoutSource, String tenantIdIn, Boolean withoutTenantId, Boolean includeDeploymentsWithoutTenantId, OffsetDateTime after, OffsetDateTime before, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/deployment", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "source", source));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutSource", withoutSource));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDeploymentsWithoutTenantId", includeDeploymentsWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));
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

        ParameterizedTypeReference<List<DeploymentDto>> returnType = new ParameterizedTypeReference<List<DeploymentDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Queries for the number of deployments that fulfill given parameters. Takes the same parameters as the [Get Deployments](https://docs.camunda.org/manual/7.16/reference/rest/deployment/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by deployment id (optional)
     * @param name Filter by the deployment name. Exact match. (optional)
     * @param nameLike Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param source Filter by the deployment source. (optional)
     * @param withoutSource Filter by the deployment source whereby source is equal to &#x60;null&#x60;. (optional, default to false)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include deployments which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param includeDeploymentsWithoutTenantId Include deployments which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param after Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param before Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getDeploymentsCount(String id, String name, String nameLike, String source, Boolean withoutSource, String tenantIdIn, Boolean withoutTenantId, Boolean includeDeploymentsWithoutTenantId, OffsetDateTime after, OffsetDateTime before) throws RestClientException {
        return getDeploymentsCountWithHttpInfo(id, name, nameLike, source, withoutSource, tenantIdIn, withoutTenantId, includeDeploymentsWithoutTenantId, after, before).getBody();
    }

    /**
     * Get List Count
     * Queries for the number of deployments that fulfill given parameters. Takes the same parameters as the [Get Deployments](https://docs.camunda.org/manual/7.16/reference/rest/deployment/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example, if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id Filter by deployment id (optional)
     * @param name Filter by the deployment name. Exact match. (optional)
     * @param nameLike Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). (optional)
     * @param source Filter by the deployment source. (optional)
     * @param withoutSource Filter by the deployment source whereby source is equal to &#x60;null&#x60;. (optional, default to false)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include deployments which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param includeDeploymentsWithoutTenantId Include deployments which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional, default to false)
     * @param after Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param before Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getDeploymentsCountWithHttpInfo(String id, String name, String nameLike, String source, Boolean withoutSource, String tenantIdIn, Boolean withoutTenantId, Boolean includeDeploymentsWithoutTenantId, OffsetDateTime after, OffsetDateTime before) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/deployment/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "id", id));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "source", source));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutSource", withoutSource));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDeploymentsWithoutTenantId", includeDeploymentsWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));

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
     * Redeploy
     * Re-deploys an existing deployment.  The deployment resources to re-deploy can be restricted by using the properties &#x60;resourceIds&#x60; or &#x60;resourceNames&#x60;. If no deployment resources to re-deploy are passed then all existing resources of the given deployment are re-deployed.  **Warning**: Deployments can contain custom code in form of scripts or EL expressions to customize process behavior. This may be abused for remote execution of arbitrary code. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment or a deployment resource for the given deployment does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment to re-deploy. (required)
     * @param redeploymentDto  (optional)
     * @return DeploymentWithDefinitionsDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DeploymentWithDefinitionsDto redeploy(String id, RedeploymentDto redeploymentDto) throws RestClientException {
        return redeployWithHttpInfo(id, redeploymentDto).getBody();
    }

    /**
     * Redeploy
     * Re-deploys an existing deployment.  The deployment resources to re-deploy can be restricted by using the properties &#x60;resourceIds&#x60; or &#x60;resourceNames&#x60;. If no deployment resources to re-deploy are passed then all existing resources of the given deployment are re-deployed.  **Warning**: Deployments can contain custom code in form of scripts or EL expressions to customize process behavior. This may be abused for remote execution of arbitrary code. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.16/user-guide/process-engine/securing-custom-code/) in the user guide for details.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Deployment or a deployment resource for the given deployment does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the deployment to re-deploy. (required)
     * @param redeploymentDto  (optional)
     * @return ResponseEntity&lt;DeploymentWithDefinitionsDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DeploymentWithDefinitionsDto> redeployWithHttpInfo(String id, RedeploymentDto redeploymentDto) throws RestClientException {
        Object postBody = redeploymentDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling redeploy");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/deployment/{id}/redeploy", uriVariables);

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

        ParameterizedTypeReference<DeploymentWithDefinitionsDto> returnType = new ParameterizedTypeReference<DeploymentWithDefinitionsDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
