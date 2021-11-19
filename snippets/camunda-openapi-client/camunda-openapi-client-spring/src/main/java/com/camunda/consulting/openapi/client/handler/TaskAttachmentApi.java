package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AttachmentDto;
import com.camunda.consulting.openapi.client.model.AuthorizationExceptionDto;
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
@Component("com.camunda.consulting.openapi.client.handler.TaskAttachmentApi")
public class TaskAttachmentApi {
    private ApiClient apiClient;

    public TaskAttachmentApi() {
        this(new ApiClient());
    }

    @Autowired
    public TaskAttachmentApi(ApiClient apiClient) {
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
     * Creates an attachment for a task.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The task does not exists or task id is null. No content or url to remote content exists. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The history of the engine is disabled. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to add the attachment to. (required)
     * @param attachmentName The name of the attachment. (optional)
     * @param attachmentDescription The description of the attachment. (optional)
     * @param attachmentType The type of the attachment. (optional)
     * @param url The url to the remote content of the attachment. (optional)
     * @param content The content of the attachment. (optional)
     * @return AttachmentDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AttachmentDto addAttachment(String id, String attachmentName, String attachmentDescription, String attachmentType, String url, File content) throws RestClientException {
        return addAttachmentWithHttpInfo(id, attachmentName, attachmentDescription, attachmentType, url, content).getBody();
    }

    /**
     * Create
     * Creates an attachment for a task.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The task does not exists or task id is null. No content or url to remote content exists. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>403</b> - The history of the engine is disabled. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to add the attachment to. (required)
     * @param attachmentName The name of the attachment. (optional)
     * @param attachmentDescription The description of the attachment. (optional)
     * @param attachmentType The type of the attachment. (optional)
     * @param url The url to the remote content of the attachment. (optional)
     * @param content The content of the attachment. (optional)
     * @return ResponseEntity&lt;AttachmentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AttachmentDto> addAttachmentWithHttpInfo(String id, String attachmentName, String attachmentDescription, String attachmentType, String url, File content) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling addAttachment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/attachment/create", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (attachmentName != null)
            formParams.add("attachment-name", attachmentName);
        if (attachmentDescription != null)
            formParams.add("attachment-description", attachmentDescription);
        if (attachmentType != null)
            formParams.add("attachment-type", attachmentType);
        if (url != null)
            formParams.add("url", url);
        if (content != null)
            formParams.add("content", new FileSystemResource(content));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "multipart/form-data"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<AttachmentDto> returnType = new ParameterizedTypeReference<AttachmentDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Delete
     * Removes an attachment from a task by id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - The history of the engine is disabled. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - A Task Attachment for the given task id and attachment id does not exist. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task. (required)
     * @param attachmentId The id of the attachment to be removed. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteAttachment(String id, String attachmentId) throws RestClientException {
        deleteAttachmentWithHttpInfo(id, attachmentId);
    }

    /**
     * Delete
     * Removes an attachment from a task by id.
     * <p><b>204</b> - Request successful.
     * <p><b>403</b> - The history of the engine is disabled. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * <p><b>404</b> - A Task Attachment for the given task id and attachment id does not exist. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task. (required)
     * @param attachmentId The id of the attachment to be removed. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteAttachmentWithHttpInfo(String id, String attachmentId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling deleteAttachment");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling deleteAttachment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("attachmentId", attachmentId);
        String path = apiClient.expandPath("/task/{id}/attachment/{attachmentId}", uriVariables);

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
     * Get
     * Retrieves a task attachment by task id and attachment id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - The attachment for the given task and attachment id does not exist or the history of the engine is disabled.  See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task. (required)
     * @param attachmentId The id of the attachment to be retrieved. (required)
     * @return AttachmentDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AttachmentDto getAttachment(String id, String attachmentId) throws RestClientException {
        return getAttachmentWithHttpInfo(id, attachmentId).getBody();
    }

    /**
     * Get
     * Retrieves a task attachment by task id and attachment id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - The attachment for the given task and attachment id does not exist or the history of the engine is disabled.  See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task. (required)
     * @param attachmentId The id of the attachment to be retrieved. (required)
     * @return ResponseEntity&lt;AttachmentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AttachmentDto> getAttachmentWithHttpInfo(String id, String attachmentId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAttachment");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling getAttachment");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("attachmentId", attachmentId);
        String path = apiClient.expandPath("/task/{id}/attachment/{attachmentId}", uriVariables);

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

        ParameterizedTypeReference<AttachmentDto> returnType = new ParameterizedTypeReference<AttachmentDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get (Binary)
     * Retrieves the binary content of a task attachment by task id and attachment id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - The attachment content for the given task id and attachment id does not exist, or the history of the engine is disabled.  See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task. (required)
     * @param attachmentId The id of the attachment to be retrieved. (required)
     * @return File
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public File getAttachmentData(String id, String attachmentId) throws RestClientException {
        return getAttachmentDataWithHttpInfo(id, attachmentId).getBody();
    }

    /**
     * Get (Binary)
     * Retrieves the binary content of a task attachment by task id and attachment id.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - The attachment content for the given task id and attachment id does not exist, or the history of the engine is disabled.  See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task. (required)
     * @param attachmentId The id of the attachment to be retrieved. (required)
     * @return ResponseEntity&lt;File&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<File> getAttachmentDataWithHttpInfo(String id, String attachmentId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAttachmentData");
        }
        
        // verify the required parameter 'attachmentId' is set
        if (attachmentId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'attachmentId' when calling getAttachmentData");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        uriVariables.put("attachmentId", attachmentId);
        String path = apiClient.expandPath("/task/{id}/attachment/{attachmentId}/data", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/octet-stream", "text/plain", "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<File> returnType = new ParameterizedTypeReference<File>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get List
     * Gets the attachments for a task.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - No task exists for the given task id. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the attachments for. (required)
     * @return List&lt;AttachmentDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<AttachmentDto> getAttachments(String id) throws RestClientException {
        return getAttachmentsWithHttpInfo(id).getBody();
    }

    /**
     * Get List
     * Gets the attachments for a task.
     * <p><b>200</b> - Request successful.
     * <p><b>404</b> - No task exists for the given task id. See the [Introduction](/reference/rest/overview/#error-handling) for the error response format.
     * @param id The id of the task to retrieve the attachments for. (required)
     * @return ResponseEntity&lt;List&lt;AttachmentDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<AttachmentDto>> getAttachmentsWithHttpInfo(String id) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'id' when calling getAttachments");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("id", id);
        String path = apiClient.expandPath("/task/{id}/attachment", uriVariables);

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

        ParameterizedTypeReference<List<AttachmentDto>> returnType = new ParameterizedTypeReference<List<AttachmentDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
