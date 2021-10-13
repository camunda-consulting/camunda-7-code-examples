package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.MigrationExecutionDto;
import com.camunda.consulting.openapi.client.model.MigrationPlanDto;
import com.camunda.consulting.openapi.client.model.MigrationPlanGenerationDto;
import com.camunda.consulting.openapi.client.model.MigrationPlanReportDto;

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
@Component("com.camunda.consulting.openapi.client.handler.MigrationApi")
public class MigrationApi {
    private ApiClient apiClient;

    public MigrationApi() {
        this(new ApiClient());
    }

    @Autowired
    public MigrationApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Execute Migration Plan
     * Executes a migration plan synchronously for multiple process instances. To execute a migration plan asynchronously, use the [Execute Migration Plan Async(Batch)](https://docs.camunda.org/manual/7.16/reference/rest/migration/execute-migration-async/) method.  For more information about the difference between synchronous and asynchronous execution of a migration plan, please refer to the related section of [the user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#executing-a-migration-plan).
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The request is not valid if one or more of the following statements apply:  * The provided migration plan is not valid, so an exception of type &#x60;MigrationPlanValidationException&#x60; is returned. * The provided migration plan is not valid for a specific process instance it is applied to, so an exception of type &#x60;MigratingProcessInstanceValidationException&#x60; is returned. * In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param migrationExecutionDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void executeMigrationPlan(MigrationExecutionDto migrationExecutionDto) throws RestClientException {
        executeMigrationPlanWithHttpInfo(migrationExecutionDto);
    }

    /**
     * Execute Migration Plan
     * Executes a migration plan synchronously for multiple process instances. To execute a migration plan asynchronously, use the [Execute Migration Plan Async(Batch)](https://docs.camunda.org/manual/7.16/reference/rest/migration/execute-migration-async/) method.  For more information about the difference between synchronous and asynchronous execution of a migration plan, please refer to the related section of [the user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#executing-a-migration-plan).
     * <p><b>204</b> - Request successful. This method returns no content.
     * <p><b>400</b> - The request is not valid if one or more of the following statements apply:  * The provided migration plan is not valid, so an exception of type &#x60;MigrationPlanValidationException&#x60; is returned. * The provided migration plan is not valid for a specific process instance it is applied to, so an exception of type &#x60;MigratingProcessInstanceValidationException&#x60; is returned. * In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param migrationExecutionDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> executeMigrationPlanWithHttpInfo(MigrationExecutionDto migrationExecutionDto) throws RestClientException {
        Object postBody = migrationExecutionDto;
        
        String path = apiClient.expandPath("/migration/execute", Collections.<String, Object>emptyMap());

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
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Execute Migration Plan Async (Batch)
     * Executes a migration plan asynchronously (batch) for multiple process instances. To execute a migration plan synchronously, use the [Execute MigrationPlan](https://docs.camunda.org/manual/7.16/reference/rest/migration/execute-migration/) method.  For more information about the difference between synchronous and asynchronous execution of a migration plan, please refer to the related section of [the user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#executing-a-migration-plan).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The request is not valid if one or more of the following statements apply:  * The provided migration plan is not valid, so an exception of type &#x60;MigrationPlanValidationException&#x60; is returned. * In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param migrationExecutionDto  (optional)
     * @return BatchDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BatchDto executeMigrationPlanAsync(MigrationExecutionDto migrationExecutionDto) throws RestClientException {
        return executeMigrationPlanAsyncWithHttpInfo(migrationExecutionDto).getBody();
    }

    /**
     * Execute Migration Plan Async (Batch)
     * Executes a migration plan asynchronously (batch) for multiple process instances. To execute a migration plan synchronously, use the [Execute MigrationPlan](https://docs.camunda.org/manual/7.16/reference/rest/migration/execute-migration/) method.  For more information about the difference between synchronous and asynchronous execution of a migration plan, please refer to the related section of [the user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#executing-a-migration-plan).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - The request is not valid if one or more of the following statements apply:  * The provided migration plan is not valid, so an exception of type &#x60;MigrationPlanValidationException&#x60; is returned. * In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param migrationExecutionDto  (optional)
     * @return ResponseEntity&lt;BatchDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<BatchDto> executeMigrationPlanAsyncWithHttpInfo(MigrationExecutionDto migrationExecutionDto) throws RestClientException {
        Object postBody = migrationExecutionDto;
        
        String path = apiClient.expandPath("/migration/executeAsync", Collections.<String, Object>emptyMap());

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
     * Generate Migration Plan
     * Generates a migration plan for two process definitions. The generated migration plan contains migration instructions which map equal activities between the two process definitions.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  The requested migration was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param migrationPlanGenerationDto  (optional)
     * @return MigrationPlanDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MigrationPlanDto generateMigrationPlan(MigrationPlanGenerationDto migrationPlanGenerationDto) throws RestClientException {
        return generateMigrationPlanWithHttpInfo(migrationPlanGenerationDto).getBody();
    }

    /**
     * Generate Migration Plan
     * Generates a migration plan for two process definitions. The generated migration plan contains migration instructions which map equal activities between the two process definitions.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> -  The requested migration was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. 
     * @param migrationPlanGenerationDto  (optional)
     * @return ResponseEntity&lt;MigrationPlanDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MigrationPlanDto> generateMigrationPlanWithHttpInfo(MigrationPlanGenerationDto migrationPlanGenerationDto) throws RestClientException {
        Object postBody = migrationPlanGenerationDto;
        
        String path = apiClient.expandPath("/migration/generate", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<MigrationPlanDto> returnType = new ParameterizedTypeReference<MigrationPlanDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Validate Migration Plan
     * Validates a migration plan statically without executing it. This corresponds to the [creation time validation](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#creation-time-validation) described in the user guide.
     * <p><b>200</b> - Request successful. The validation report was returned.
     * <p><b>400</b> - In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param migrationPlanDto  (optional)
     * @return MigrationPlanReportDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public MigrationPlanReportDto validateMigrationPlan(MigrationPlanDto migrationPlanDto) throws RestClientException {
        return validateMigrationPlanWithHttpInfo(migrationPlanDto).getBody();
    }

    /**
     * Validate Migration Plan
     * Validates a migration plan statically without executing it. This corresponds to the [creation time validation](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#creation-time-validation) described in the user guide.
     * <p><b>200</b> - Request successful. The validation report was returned.
     * <p><b>400</b> - In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param migrationPlanDto  (optional)
     * @return ResponseEntity&lt;MigrationPlanReportDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<MigrationPlanReportDto> validateMigrationPlanWithHttpInfo(MigrationPlanDto migrationPlanDto) throws RestClientException {
        Object postBody = migrationPlanDto;
        
        String path = apiClient.expandPath("/migration/validate", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<MigrationPlanReportDto> returnType = new ParameterizedTypeReference<MigrationPlanReportDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
