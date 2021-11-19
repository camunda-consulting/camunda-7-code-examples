package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DecisionDefinitionDiagramDto;
import com.camunda.consulting.openapi.client.model.DecisionDefinitionDto;
import com.camunda.consulting.openapi.client.model.EvaluateDecisionDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.HistoryTimeToLiveDto;
import java.time.OffsetDateTime;
import com.camunda.consulting.openapi.client.model.VariableValueDto;

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
@Component("com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi")
public class DecisionDefinitionApi {
    private ApiClient apiClient;

    public DecisionDefinitionApi() {
        this(new ApiClient());
    }

    @Autowired
    public DecisionDefinitionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Evaluate By Id
     * Evaluates a given decision and returns the result. The input values of the decision have to be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition to be evaluated. (required)
     * @param evaluateDecisionDto  (optional)
     * @return List&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Map<String, VariableValueDto>> evaluateDecisionById(String id, EvaluateDecisionDto evaluateDecisionDto) throws RestClientException {
        return evaluateDecisionByIdWithHttpInfo(id, evaluateDecisionDto).getBody();
    }

    /**
     * Evaluate By Id
     * Evaluates a given decision and returns the result. The input values of the decision have to be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition to be evaluated. (required)
     * @param evaluateDecisionDto  (optional)
     * @return ResponseEntity&lt;List&lt;Map&lt;String, VariableValueDto&gt;&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Map<String, VariableValueDto>>> evaluateDecisionByIdWithHttpInfo(String id, EvaluateDecisionDto evaluateDecisionDto) throws RestClientException {
        Object postBody = evaluateDecisionDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling evaluateDecisionById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-definition/{id}/evaluate", uriVariables);

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

        ParameterizedTypeReference<List<Map<String, VariableValueDto>>> returnType = new ParameterizedTypeReference<List<Map<String, VariableValueDto>>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Evaluate By Key
     * Evaluates the latest version of the decision definition which belongs to no tenant. The input values of the decision have to be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be evaluated. (required)
     * @param evaluateDecisionDto  (optional)
     * @return List&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Map<String, VariableValueDto>> evaluateDecisionByKey(String key, EvaluateDecisionDto evaluateDecisionDto) throws RestClientException {
        return evaluateDecisionByKeyWithHttpInfo(key, evaluateDecisionDto).getBody();
    }

    /**
     * Evaluate By Key
     * Evaluates the latest version of the decision definition which belongs to no tenant. The input values of the decision have to be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be evaluated. (required)
     * @param evaluateDecisionDto  (optional)
     * @return ResponseEntity&lt;List&lt;Map&lt;String, VariableValueDto&gt;&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Map<String, VariableValueDto>>> evaluateDecisionByKeyWithHttpInfo(String key, EvaluateDecisionDto evaluateDecisionDto) throws RestClientException {
        Object postBody = evaluateDecisionDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling evaluateDecisionByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-definition/key/{key}/evaluate", uriVariables);

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

        ParameterizedTypeReference<List<Map<String, VariableValueDto>>> returnType = new ParameterizedTypeReference<List<Map<String, VariableValueDto>>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Evaluate By Key And Tenant
     * Evaluates the latest version of the decision definition for tenant. The input values of the decision have to be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be evaluated. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @param evaluateDecisionDto  (optional)
     * @return List&lt;Map&lt;String, VariableValueDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Map<String, VariableValueDto>> evaluateDecisionByKeyAndTenant(String key, String tenantId, EvaluateDecisionDto evaluateDecisionDto) throws RestClientException {
        return evaluateDecisionByKeyAndTenantWithHttpInfo(key, tenantId, evaluateDecisionDto).getBody();
    }

    /**
     * Evaluate By Key And Tenant
     * Evaluates the latest version of the decision definition for tenant. The input values of the decision have to be supplied in the request body.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be evaluated. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @param evaluateDecisionDto  (optional)
     * @return ResponseEntity&lt;List&lt;Map&lt;String, VariableValueDto&gt;&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<Map<String, VariableValueDto>>> evaluateDecisionByKeyAndTenantWithHttpInfo(String key, String tenantId, EvaluateDecisionDto evaluateDecisionDto) throws RestClientException {
        Object postBody = evaluateDecisionDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling evaluateDecisionByKeyAndTenant");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling evaluateDecisionByKeyAndTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-definition/key/{key}/tenant-id/{tenant-id}/evaluate", uriVariables);

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

        ParameterizedTypeReference<List<Map<String, VariableValueDto>>> returnType = new ParameterizedTypeReference<List<Map<String, VariableValueDto>>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Definition By Id
     * Retrieves a decision definition by id, according to the &#x60;DecisionDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition to be retrieved. (required)
     * @return DecisionDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionDefinitionDto getDecisionDefinitionById(String id) throws RestClientException {
        return getDecisionDefinitionByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get Decision Definition By Id
     * Retrieves a decision definition by id, according to the &#x60;DecisionDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition to be retrieved. (required)
     * @return ResponseEntity&lt;DecisionDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionDefinitionDto> getDecisionDefinitionByIdWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDecisionDefinitionById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-definition/{id}", uriVariables);

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

        ParameterizedTypeReference<DecisionDefinitionDto> returnType = new ParameterizedTypeReference<DecisionDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Definition By Key
     * Retrieves the latest version of the decision definition which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @return DecisionDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionDefinitionDto getDecisionDefinitionByKey(String key) throws RestClientException {
        return getDecisionDefinitionByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Decision Definition By Key
     * Retrieves the latest version of the decision definition which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;DecisionDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionDefinitionDto> getDecisionDefinitionByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionDefinitionByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-definition/key/{key}", uriVariables);

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

        ParameterizedTypeReference<DecisionDefinitionDto> returnType = new ParameterizedTypeReference<DecisionDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Definition By Key And Tenant Id
     * Retrieves the latest version of the decision definition for tenant
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @return DecisionDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionDefinitionDto getDecisionDefinitionByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getDecisionDefinitionByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Decision Definition By Key And Tenant Id
     * Retrieves the latest version of the decision definition for tenant
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @return ResponseEntity&lt;DecisionDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionDefinitionDto> getDecisionDefinitionByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionDefinitionByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDecisionDefinitionByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-definition/key/{key}/tenant-id/{tenant-id}", uriVariables);

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

        ParameterizedTypeReference<DecisionDefinitionDto> returnType = new ParameterizedTypeReference<DecisionDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Diagram
     * Retrieves the diagram of a decision definition.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The decision definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDecisionDefinitionDiagram(String id) throws RestClientException {
        return getDecisionDefinitionDiagramWithHttpInfo(id).getBody();
    }

    /**
     * Get Diagram
     * Retrieves the diagram of a decision definition.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The decision definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the process definition. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDecisionDefinitionDiagramWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDecisionDefinitionDiagram");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-definition/{id}/diagram", uriVariables);

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
     * Get Diagram By Key
     * Returns the diagram for the latest version of the decision definition which belongs to no tenant
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The decision definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDecisionDefinitionDiagramByKey(String key) throws RestClientException {
        return getDecisionDefinitionDiagramByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Diagram By Key
     * Returns the diagram for the latest version of the decision definition which belongs to no tenant
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The decision definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDecisionDefinitionDiagramByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionDefinitionDiagramByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-definition/key/{key}/diagram", uriVariables);

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
     * Get Diagram By Key And Tenant
     * Returns the XML of the latest version of the decision definition for tenant.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The decision definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDecisionDefinitionDiagramByKeyAndTenant(String key, String tenantId) throws RestClientException {
        return getDecisionDefinitionDiagramByKeyAndTenantWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Diagram By Key And Tenant
     * Returns the XML of the latest version of the decision definition for tenant.
     * <p><b>200</b> - Request successful. The image diagram of this process.
     * <p><b>204</b> - The decision definition doesn&#39;t have an associated diagram.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDecisionDefinitionDiagramByKeyAndTenantWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionDefinitionDiagramByKeyAndTenant");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDecisionDefinitionDiagramByKeyAndTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-definition/key/{key}/tenant-id/{tenant-id}/diagram", uriVariables);

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
     * Get XML By Id
     * Retrieves the DMN XML of a decision definition.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition. (required)
     * @return DecisionDefinitionDiagramDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionDefinitionDiagramDto getDecisionDefinitionDmnXmlById(String id) throws RestClientException {
        return getDecisionDefinitionDmnXmlByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get XML By Id
     * Retrieves the DMN XML of a decision definition.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition. (required)
     * @return ResponseEntity&lt;DecisionDefinitionDiagramDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionDefinitionDiagramDto> getDecisionDefinitionDmnXmlByIdWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDecisionDefinitionDmnXmlById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-definition/{id}/xml", uriVariables);

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

        ParameterizedTypeReference<DecisionDefinitionDiagramDto> returnType = new ParameterizedTypeReference<DecisionDefinitionDiagramDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get XML By Key
     * Retrieves the XML for the latest version of the decision definition which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof). (required)
     * @return DecisionDefinitionDiagramDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionDefinitionDiagramDto getDecisionDefinitionDmnXmlByKey(String key) throws RestClientException {
        return getDecisionDefinitionDmnXmlByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get XML By Key
     * Retrieves the XML for the latest version of the decision definition which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof). (required)
     * @return ResponseEntity&lt;DecisionDefinitionDiagramDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionDefinitionDiagramDto> getDecisionDefinitionDmnXmlByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionDefinitionDmnXmlByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-definition/key/{key}/xml", uriVariables);

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

        ParameterizedTypeReference<DecisionDefinitionDiagramDto> returnType = new ParameterizedTypeReference<DecisionDefinitionDiagramDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get XML By Key and Tenant
     * Retrieves the XML of the latest version of the decision definition for tenant
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof). (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @return DecisionDefinitionDiagramDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionDefinitionDiagramDto getDecisionDefinitionDmnXmlByKeyAndTenant(String key, String tenantId) throws RestClientException {
        return getDecisionDefinitionDmnXmlByKeyAndTenantWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get XML By Key and Tenant
     * Retrieves the XML of the latest version of the decision definition for tenant
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definition (the latest version thereof). (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @return ResponseEntity&lt;DecisionDefinitionDiagramDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionDefinitionDiagramDto> getDecisionDefinitionDmnXmlByKeyAndTenantWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionDefinitionDmnXmlByKeyAndTenant");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDecisionDefinitionDmnXmlByKeyAndTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-definition/key/{key}/tenant-id/{tenant-id}/xml", uriVariables);

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

        ParameterizedTypeReference<DecisionDefinitionDiagramDto> returnType = new ParameterizedTypeReference<DecisionDefinitionDiagramDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Queries for decision definitions that fulfill given parameters. Parameters may be the properties of decision definitions, such as the name, key or version. The size of the result set can be retrieved by using the [Get Decision Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/decision-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param decisionDefinitionId Filter by decision definition id. (optional)
     * @param decisionDefinitionIdIn Filter by decision definition ids. (optional)
     * @param name Filter by decision definition name. (optional)
     * @param nameLike Filter by decision definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match). (optional)
     * @param key Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match. (optional)
     * @param keyLike Filter by decision definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision definition category. Exact match. (optional)
     * @param categoryLike Filter by decision definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision definition version. (optional)
     * @param latestVersion Only include those decision definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision definition resources that the parameter is a substring of. (optional)
     * @param decisionRequirementsDefinitionId Filter by the id of the decision requirements definition this decision definition belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition this decision definition belongs to. (optional)
     * @param withoutDecisionRequirementsDefinition Only include decision definitions which does not belongs to any decision requirements definition. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param tenantIdIn Filter by a comma-separated list of &#x60;Strings&#x60;. A decision definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionDefinitionsWithoutTenantId Include decision definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tags of those decision definition resources that the parameter is a substring of. (optional)
     * @return List&lt;DecisionDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<DecisionDefinitionDto> getDecisionDefinitions(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String decisionDefinitionId, String decisionDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey, Boolean withoutDecisionRequirementsDefinition, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionDefinitionsWithoutTenantId, String versionTag, String versionTagLike) throws RestClientException {
        return getDecisionDefinitionsWithHttpInfo(sortBy, sortOrder, firstResult, maxResults, decisionDefinitionId, decisionDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, withoutDecisionRequirementsDefinition, tenantIdIn, withoutTenantId, includeDecisionDefinitionsWithoutTenantId, versionTag, versionTagLike).getBody();
    }

    /**
     * Get List
     * Queries for decision definitions that fulfill given parameters. Parameters may be the properties of decision definitions, such as the name, key or version. The size of the result set can be retrieved by using the [Get Decision Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/decision-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param decisionDefinitionId Filter by decision definition id. (optional)
     * @param decisionDefinitionIdIn Filter by decision definition ids. (optional)
     * @param name Filter by decision definition name. (optional)
     * @param nameLike Filter by decision definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match). (optional)
     * @param key Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match. (optional)
     * @param keyLike Filter by decision definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision definition category. Exact match. (optional)
     * @param categoryLike Filter by decision definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision definition version. (optional)
     * @param latestVersion Only include those decision definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision definition resources that the parameter is a substring of. (optional)
     * @param decisionRequirementsDefinitionId Filter by the id of the decision requirements definition this decision definition belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition this decision definition belongs to. (optional)
     * @param withoutDecisionRequirementsDefinition Only include decision definitions which does not belongs to any decision requirements definition. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param tenantIdIn Filter by a comma-separated list of &#x60;Strings&#x60;. A decision definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionDefinitionsWithoutTenantId Include decision definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tags of those decision definition resources that the parameter is a substring of. (optional)
     * @return ResponseEntity&lt;List&lt;DecisionDefinitionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<DecisionDefinitionDto>> getDecisionDefinitionsWithHttpInfo(String sortBy, String sortOrder, Integer firstResult, Integer maxResults, String decisionDefinitionId, String decisionDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey, Boolean withoutDecisionRequirementsDefinition, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionDefinitionsWithoutTenantId, String versionTag, String versionTagLike) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/decision-definition", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionId", decisionDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionIdIn", decisionDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAfter", deployedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAt", deployedAt));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keyLike", keyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryLike", categoryLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "latestVersion", latestVersion));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceNameLike", resourceNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionId", decisionRequirementsDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionKey", decisionRequirementsDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutDecisionRequirementsDefinition", withoutDecisionRequirementsDefinition));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDecisionDefinitionsWithoutTenantId", includeDecisionDefinitionsWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTag", versionTag));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTagLike", versionTagLike));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<DecisionDefinitionDto>> returnType = new ParameterizedTypeReference<List<DecisionDefinitionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List Count
     * Requests the number of decision definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Decision Definition](https://docs.camunda.org/manual/7.16/reference/rest/decision-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionDefinitionId Filter by decision definition id. (optional)
     * @param decisionDefinitionIdIn Filter by decision definition ids. (optional)
     * @param name Filter by decision definition name. (optional)
     * @param nameLike Filter by decision definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match). (optional)
     * @param key Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match. (optional)
     * @param keyLike Filter by decision definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision definition category. Exact match. (optional)
     * @param categoryLike Filter by decision definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision definition version. (optional)
     * @param latestVersion Only include those decision definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision definition resources that the parameter is a substring of. (optional)
     * @param decisionRequirementsDefinitionId Filter by the id of the decision requirements definition this decision definition belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition this decision definition belongs to. (optional)
     * @param withoutDecisionRequirementsDefinition Only include decision definitions which does not belongs to any decision requirements definition. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param tenantIdIn Filter by a comma-separated list of &#x60;Strings&#x60;. A decision definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionDefinitionsWithoutTenantId Include decision definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tags of those decision definition resources that the parameter is a substring of. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getDecisionDefinitionsCount(String decisionDefinitionId, String decisionDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey, Boolean withoutDecisionRequirementsDefinition, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionDefinitionsWithoutTenantId, String versionTag, String versionTagLike) throws RestClientException {
        return getDecisionDefinitionsCountWithHttpInfo(decisionDefinitionId, decisionDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, withoutDecisionRequirementsDefinition, tenantIdIn, withoutTenantId, includeDecisionDefinitionsWithoutTenantId, versionTag, versionTagLike).getBody();
    }

    /**
     * Get List Count
     * Requests the number of decision definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Decision Definition](https://docs.camunda.org/manual/7.16/reference/rest/decision-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionDefinitionId Filter by decision definition id. (optional)
     * @param decisionDefinitionIdIn Filter by decision definition ids. (optional)
     * @param name Filter by decision definition name. (optional)
     * @param nameLike Filter by decision definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the deployment the id belongs to. (optional)
     * @param deployedAfter Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time. (optional)
     * @param deployedAt Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match). (optional)
     * @param key Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match. (optional)
     * @param keyLike Filter by decision definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision definition category. Exact match. (optional)
     * @param categoryLike Filter by decision definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision definition version. (optional)
     * @param latestVersion Only include those decision definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision definition resources that the parameter is a substring of. (optional)
     * @param decisionRequirementsDefinitionId Filter by the id of the decision requirements definition this decision definition belongs to. (optional)
     * @param decisionRequirementsDefinitionKey Filter by the key of the decision requirements definition this decision definition belongs to. (optional)
     * @param withoutDecisionRequirementsDefinition Only include decision definitions which does not belongs to any decision requirements definition. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param tenantIdIn Filter by a comma-separated list of &#x60;Strings&#x60;. A decision definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision definitions which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionDefinitionsWithoutTenantId Include decision definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param versionTag Filter by the version tag. (optional)
     * @param versionTagLike Filter by the version tags of those decision definition resources that the parameter is a substring of. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getDecisionDefinitionsCountWithHttpInfo(String decisionDefinitionId, String decisionDefinitionIdIn, String name, String nameLike, String deploymentId, OffsetDateTime deployedAfter, OffsetDateTime deployedAt, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionKey, Boolean withoutDecisionRequirementsDefinition, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionDefinitionsWithoutTenantId, String versionTag, String versionTagLike) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/decision-definition/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionId", decisionDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionDefinitionIdIn", decisionDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAfter", deployedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deployedAt", deployedAt));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keyLike", keyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryLike", categoryLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "latestVersion", latestVersion));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceNameLike", resourceNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionId", decisionRequirementsDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionKey", decisionRequirementsDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutDecisionRequirementsDefinition", withoutDecisionRequirementsDefinition));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDecisionDefinitionsWithoutTenantId", includeDecisionDefinitionsWithoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTag", versionTag));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "versionTagLike", versionTagLike));

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
     * Update History Time to Live
     * Updates history time to live for decision definition. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateHistoryTimeToLiveByDecisionDefinitionId(String id, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        updateHistoryTimeToLiveByDecisionDefinitionIdWithHttpInfo(id, historyTimeToLiveDto);
    }

    /**
     * Update History Time to Live
     * Updates history time to live for decision definition. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision definition to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateHistoryTimeToLiveByDecisionDefinitionIdWithHttpInfo(String id, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        Object postBody = historyTimeToLiveDto;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling updateHistoryTimeToLiveByDecisionDefinitionId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-definition/{id}/history-time-to-live", uriVariables);

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
     * Update History Time to Live By Key
     * Updates the latest version of the decision definition which belongs to no tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definitions to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateHistoryTimeToLiveByDecisionDefinitionKey(String key, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        updateHistoryTimeToLiveByDecisionDefinitionKeyWithHttpInfo(key, historyTimeToLiveDto);
    }

    /**
     * Update History Time to Live By Key
     * Updates the latest version of the decision definition which belongs to no tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definitions to change history time to live. (required)
     * @param historyTimeToLiveDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateHistoryTimeToLiveByDecisionDefinitionKeyWithHttpInfo(String key, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        Object postBody = historyTimeToLiveDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling updateHistoryTimeToLiveByDecisionDefinitionKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-definition/key/{key}/history-time-to-live", uriVariables);

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
     * Update History Time to Live By Key And Tenant
     * Updates the latest version of the decision definition for tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definitions to change history time to live. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @param historyTimeToLiveDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant(String key, String tenantId, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenantWithHttpInfo(key, tenantId, historyTimeToLiveDto);
    }

    /**
     * Update History Time to Live By Key And Tenant
     * Updates the latest version of the decision definition for tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).
     * <p><b>204</b> - Request successful.
     * <p><b>400</b> - Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision definitions to change history time to live. (required)
     * @param tenantId The id of the tenant the decision definition belongs to. (required)
     * @param historyTimeToLiveDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenantWithHttpInfo(String key, String tenantId, HistoryTimeToLiveDto historyTimeToLiveDto) throws RestClientException {
        Object postBody = historyTimeToLiveDto;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-definition/key/{key}/tenant-id/{tenant-id}/history-time-to-live", uriVariables);

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
