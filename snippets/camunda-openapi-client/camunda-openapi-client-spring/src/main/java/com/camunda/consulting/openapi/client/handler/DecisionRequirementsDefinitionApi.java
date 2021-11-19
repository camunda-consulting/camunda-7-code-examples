package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DecisionRequirementsDefinitionDto;
import com.camunda.consulting.openapi.client.model.DecisionRequirementsDefinitionXmlDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;

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
@Component("com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi")
public class DecisionRequirementsDefinitionApi {
    private ApiClient apiClient;

    public DecisionRequirementsDefinitionApi() {
        this(new ApiClient());
    }

    @Autowired
    public DecisionRequirementsDefinitionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Decision Requirements Definition by ID
     * Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision requirements definition to be retrieved. (required)
     * @return DecisionRequirementsDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionRequirementsDefinitionDto getDecisionRequirementsDefinitionById(String id) throws RestClientException {
        return getDecisionRequirementsDefinitionByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get Decision Requirements Definition by ID
     * Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision requirements definition to be retrieved. (required)
     * @return ResponseEntity&lt;DecisionRequirementsDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionRequirementsDefinitionDto> getDecisionRequirementsDefinitionByIdWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDecisionRequirementsDefinitionById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-requirements-definition/{id}", uriVariables);

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

        ParameterizedTypeReference<DecisionRequirementsDefinitionDto> returnType = new ParameterizedTypeReference<DecisionRequirementsDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Definition by Key
     * Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine.  Returns the latest version of the decision requirements definition  which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @return DecisionRequirementsDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionRequirementsDefinitionDto getDecisionRequirementsDefinitionByKey(String key) throws RestClientException {
        return getDecisionRequirementsDefinitionByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Decision Requirements Definition by Key
     * Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine.  Returns the latest version of the decision requirements definition  which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;DecisionRequirementsDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionRequirementsDefinitionDto> getDecisionRequirementsDefinitionByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionRequirementsDefinitionByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-requirements-definition/key/{key}", uriVariables);

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

        ParameterizedTypeReference<DecisionRequirementsDefinitionDto> returnType = new ParameterizedTypeReference<DecisionRequirementsDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Definition by Key and Tenant ID
     * Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine. Returns the latest version of the decision requirements definition  for a tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant to which the decision requirements definition belongs to. (required)
     * @return DecisionRequirementsDefinitionDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionRequirementsDefinitionDto getDecisionRequirementsDefinitionByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getDecisionRequirementsDefinitionByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Decision Requirements Definition by Key and Tenant ID
     * Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine. Returns the latest version of the decision requirements definition  for a tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant to which the decision requirements definition belongs to. (required)
     * @return ResponseEntity&lt;DecisionRequirementsDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionRequirementsDefinitionDto> getDecisionRequirementsDefinitionByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionRequirementsDefinitionByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDecisionRequirementsDefinitionByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-requirements-definition/key/{key}/tenant-id/{tenant-id}", uriVariables);

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

        ParameterizedTypeReference<DecisionRequirementsDefinitionDto> returnType = new ParameterizedTypeReference<DecisionRequirementsDefinitionDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Diagram by ID
     * Retrieves the diagram of a decision requirements definition.
     * <p><b>200</b> - The image diagram of the decision requirements definition.
     * <p><b>204</b> - The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision requirements definition. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDecisionRequirementsDefinitionDiagramById(String id) throws RestClientException {
        return getDecisionRequirementsDefinitionDiagramByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get Decision Requirements Diagram by ID
     * Retrieves the diagram of a decision requirements definition.
     * <p><b>200</b> - The image diagram of the decision requirements definition.
     * <p><b>204</b> - The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision requirements definition. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDecisionRequirementsDefinitionDiagramByIdWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDecisionRequirementsDefinitionDiagramById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-requirements-definition/{id}/diagram", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "image/_*", "application/octet-stream", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Diagram by Key
     * Retrieves the diagram of a decision requirements definition. Returns the diagram for the latest version of the decision requirements  definition which belongs to no tenant.
     * <p><b>200</b> - The image diagram of the decision requirements definition.
     * <p><b>204</b> - The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDecisionRequirementsDefinitionDiagramByKey(String key) throws RestClientException {
        return getDecisionRequirementsDefinitionDiagramByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get Decision Requirements Diagram by Key
     * Retrieves the diagram of a decision requirements definition. Returns the diagram for the latest version of the decision requirements  definition which belongs to no tenant.
     * <p><b>200</b> - The image diagram of the decision requirements definition.
     * <p><b>204</b> - The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDecisionRequirementsDefinitionDiagramByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionRequirementsDefinitionDiagramByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-requirements-definition/key/{key}/diagram", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "image/_*", "application/octet-stream", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Diagram by Key and Tenant ID
     * Retrieves the diagram of a decision requirements definition. Returns the diagram of the latest version of the decision requirements  definition for a tenant.
     * <p><b>200</b> - The image diagram of the decision requirements definition.
     * <p><b>204</b> - The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant to which the decision requirements definition belongs to. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getDecisionRequirementsDefinitionDiagramByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getDecisionRequirementsDefinitionDiagramByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get Decision Requirements Diagram by Key and Tenant ID
     * Retrieves the diagram of a decision requirements definition. Returns the diagram of the latest version of the decision requirements  definition for a tenant.
     * <p><b>200</b> - The image diagram of the decision requirements definition.
     * <p><b>204</b> - The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant to which the decision requirements definition belongs to. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getDecisionRequirementsDefinitionDiagramByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionRequirementsDefinitionDiagramByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDecisionRequirementsDefinitionDiagramByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-requirements-definition/key/{key}/tenant-id/{tenant-id}/diagram", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "image/_*", "application/octet-stream", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get DMN XML by ID
     * Retrieves the DMN XML of a decision requirements definition.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision requirements definition. (required)
     * @return DecisionRequirementsDefinitionXmlDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionRequirementsDefinitionXmlDto getDecisionRequirementsDefinitionDmnXmlById(String id) throws RestClientException {
        return getDecisionRequirementsDefinitionDmnXmlByIdWithHttpInfo(id).getBody();
    }

    /**
     * Get DMN XML by ID
     * Retrieves the DMN XML of a decision requirements definition.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the decision requirements definition. (required)
     * @return ResponseEntity&lt;DecisionRequirementsDefinitionXmlDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionRequirementsDefinitionXmlDto> getDecisionRequirementsDefinitionDmnXmlByIdWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getDecisionRequirementsDefinitionDmnXmlById");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/decision-requirements-definition/{id}/xml", uriVariables);

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

        ParameterizedTypeReference<DecisionRequirementsDefinitionXmlDto> returnType = new ParameterizedTypeReference<DecisionRequirementsDefinitionXmlDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get DMN XML by Key
     * Retrieves the DMN XML of a decision requirements definition. Returns the XML for the latest version of the decision requirements  definition which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> -  Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @return DecisionRequirementsDefinitionXmlDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionRequirementsDefinitionXmlDto getDecisionRequirementsDefinitionDmnXmlByKey(String key) throws RestClientException {
        return getDecisionRequirementsDefinitionDmnXmlByKeyWithHttpInfo(key).getBody();
    }

    /**
     * Get DMN XML by Key
     * Retrieves the DMN XML of a decision requirements definition. Returns the XML for the latest version of the decision requirements  definition which belongs to no tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> -  Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @return ResponseEntity&lt;DecisionRequirementsDefinitionXmlDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionRequirementsDefinitionXmlDto> getDecisionRequirementsDefinitionDmnXmlByKeyWithHttpInfo(String key) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionRequirementsDefinitionDmnXmlByKey");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        String path = apiClient.expandPath("/decision-requirements-definition/key/{key}/xml", uriVariables);

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

        ParameterizedTypeReference<DecisionRequirementsDefinitionXmlDto> returnType = new ParameterizedTypeReference<DecisionRequirementsDefinitionXmlDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get DMN XML by Key and Tenant ID
     * Retrieves the DMN XML of a decision requirements definition. Returns the XML of the latest version of the decision requirements  definition for a tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> -  Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant to which the decision requirements definition belongs to. (required)
     * @return DecisionRequirementsDefinitionXmlDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public DecisionRequirementsDefinitionXmlDto getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId(String key, String tenantId) throws RestClientException {
        return getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantIdWithHttpInfo(key, tenantId).getBody();
    }

    /**
     * Get DMN XML by Key and Tenant ID
     * Retrieves the DMN XML of a decision requirements definition. Returns the XML of the latest version of the decision requirements  definition for a tenant.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> -  Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param key The key of the decision requirements definition (the latest version thereof) to be retrieved. (required)
     * @param tenantId The id of the tenant to which the decision requirements definition belongs to. (required)
     * @return ResponseEntity&lt;DecisionRequirementsDefinitionXmlDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<DecisionRequirementsDefinitionXmlDto> getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantIdWithHttpInfo(String key, String tenantId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'key' is set
        if (key == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'key' when calling getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId");
        }
        
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tenantId' when calling getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("key", key);
        uriVariables.put("tenant-id", tenantId);
        String path = apiClient.expandPath("/decision-requirements-definition/key/{key}/tenant-id/{tenant-id}/xml", uriVariables);

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

        ParameterizedTypeReference<DecisionRequirementsDefinitionXmlDto> returnType = new ParameterizedTypeReference<DecisionRequirementsDefinitionXmlDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Definitions
     * Queries for decision requirements definitions that fulfill given parameters. Parameters may be the properties of decision requirements definitions, such as the name, key or version.  The size of the result set can be retrieved by using the [Get Decision Requirements Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/decision-requirements-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionRequirementsDefinitionId Filter by decision requirements definition id. (optional)
     * @param decisionRequirementsDefinitionIdIn Filter by decision requirements definition ids. (optional)
     * @param name Filter by decision requirements definition name. (optional)
     * @param nameLike Filter by decision requirements definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the id of the deployment a decision requirement definition belongs to. (optional)
     * @param key Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match. (optional)
     * @param keyLike Filter by decision requirements definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision requirements definition category. Exact match. (optional)
     * @param categoryLike Filter by decision requirements definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision requirements definition version. (optional)
     * @param latestVersion Only include those decision requirements definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision requirements definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision requirements definition resources that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision requirements definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionRequirementsDefinitionsWithoutTenantId Include decision requirements definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;DecisionRequirementsDefinitionDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<DecisionRequirementsDefinitionDto> getDecisionRequirementsDefinitions(String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionIdIn, String name, String nameLike, String deploymentId, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionRequirementsDefinitionsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getDecisionRequirementsDefinitionsWithHttpInfo(decisionRequirementsDefinitionId, decisionRequirementsDefinitionIdIn, name, nameLike, deploymentId, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, tenantIdIn, withoutTenantId, includeDecisionRequirementsDefinitionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Decision Requirements Definitions
     * Queries for decision requirements definitions that fulfill given parameters. Parameters may be the properties of decision requirements definitions, such as the name, key or version.  The size of the result set can be retrieved by using the [Get Decision Requirements Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/decision-requirements-definition/get-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionRequirementsDefinitionId Filter by decision requirements definition id. (optional)
     * @param decisionRequirementsDefinitionIdIn Filter by decision requirements definition ids. (optional)
     * @param name Filter by decision requirements definition name. (optional)
     * @param nameLike Filter by decision requirements definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the id of the deployment a decision requirement definition belongs to. (optional)
     * @param key Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match. (optional)
     * @param keyLike Filter by decision requirements definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision requirements definition category. Exact match. (optional)
     * @param categoryLike Filter by decision requirements definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision requirements definition version. (optional)
     * @param latestVersion Only include those decision requirements definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision requirements definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision requirements definition resources that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision requirements definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionRequirementsDefinitionsWithoutTenantId Include decision requirements definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;DecisionRequirementsDefinitionDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<DecisionRequirementsDefinitionDto>> getDecisionRequirementsDefinitionsWithHttpInfo(String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionIdIn, String name, String nameLike, String deploymentId, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionRequirementsDefinitionsWithoutTenantId, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/decision-requirements-definition", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionId", decisionRequirementsDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionIdIn", decisionRequirementsDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keyLike", keyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryLike", categoryLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "latestVersion", latestVersion));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceNameLike", resourceNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDecisionRequirementsDefinitionsWithoutTenantId", includeDecisionRequirementsDefinitionsWithoutTenantId));
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

        ParameterizedTypeReference<List<DecisionRequirementsDefinitionDto>> returnType = new ParameterizedTypeReference<List<DecisionRequirementsDefinitionDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Decision Requirements Definition Count
     * Requests the number of decision requirements definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Decision Requirements Definitions](https://docs.camunda.org/manual/7.16/reference/rest/decision-requirements-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionRequirementsDefinitionId Filter by decision requirements definition id. (optional)
     * @param decisionRequirementsDefinitionIdIn Filter by decision requirements definition ids. (optional)
     * @param name Filter by decision requirements definition name. (optional)
     * @param nameLike Filter by decision requirements definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the id of the deployment a decision requirement definition belongs to. (optional)
     * @param key Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match. (optional)
     * @param keyLike Filter by decision requirements definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision requirements definition category. Exact match. (optional)
     * @param categoryLike Filter by decision requirements definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision requirements definition version. (optional)
     * @param latestVersion Only include those decision requirements definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision requirements definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision requirements definition resources that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision requirements definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionRequirementsDefinitionsWithoutTenantId Include decision requirements definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getDecisionRequirementsDefinitionsCount(String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionIdIn, String name, String nameLike, String deploymentId, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionRequirementsDefinitionsWithoutTenantId) throws RestClientException {
        return getDecisionRequirementsDefinitionsCountWithHttpInfo(decisionRequirementsDefinitionId, decisionRequirementsDefinitionIdIn, name, nameLike, deploymentId, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, tenantIdIn, withoutTenantId, includeDecisionRequirementsDefinitionsWithoutTenantId).getBody();
    }

    /**
     * Get Decision Requirements Definition Count
     * Requests the number of decision requirements definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Decision Requirements Definitions](https://docs.camunda.org/manual/7.16/reference/rest/decision-requirements-definition/get-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param decisionRequirementsDefinitionId Filter by decision requirements definition id. (optional)
     * @param decisionRequirementsDefinitionIdIn Filter by decision requirements definition ids. (optional)
     * @param name Filter by decision requirements definition name. (optional)
     * @param nameLike Filter by decision requirements definition names that the parameter is a substring of. (optional)
     * @param deploymentId Filter by the id of the deployment a decision requirement definition belongs to. (optional)
     * @param key Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match. (optional)
     * @param keyLike Filter by decision requirements definition keys that the parameter is a substring of. (optional)
     * @param category Filter by decision requirements definition category. Exact match. (optional)
     * @param categoryLike Filter by decision requirements definition categories that the parameter is a substring of. (optional)
     * @param version Filter by decision requirements definition version. (optional)
     * @param latestVersion Only include those decision requirements definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param resourceName Filter by the name of the decision requirements definition resource. Exact match. (optional)
     * @param resourceNameLike Filter by names of those decision requirements definition resources that the parameter is a substring of. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include decision requirements definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param includeDecisionRequirementsDefinitionsWithoutTenantId Include decision requirements definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getDecisionRequirementsDefinitionsCountWithHttpInfo(String decisionRequirementsDefinitionId, String decisionRequirementsDefinitionIdIn, String name, String nameLike, String deploymentId, String key, String keyLike, String category, String categoryLike, Integer version, Boolean latestVersion, String resourceName, String resourceNameLike, String tenantIdIn, Boolean withoutTenantId, Boolean includeDecisionRequirementsDefinitionsWithoutTenantId) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/decision-requirements-definition/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionId", decisionRequirementsDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "decisionRequirementsDefinitionIdIn", decisionRequirementsDefinitionIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "nameLike", nameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "deploymentId", deploymentId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "keyLike", keyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "category", category));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "categoryLike", categoryLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "version", version));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "latestVersion", latestVersion));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceName", resourceName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "resourceNameLike", resourceNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "includeDecisionRequirementsDefinitionsWithoutTenantId", includeDecisionRequirementsDefinitionsWithoutTenantId));

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
