package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.TelemetryConfigurationDto;

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
@Component("com.camunda.consulting.openapi.client.handler.TelemetryApi")
public class TelemetryApi {
    private ApiClient apiClient;

    public TelemetryApi() {
        this(new ApiClient());
    }

    @Autowired
    public TelemetryApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Configure Telemetry
     * Configures whether Camunda receives data collection of the process engine setup and usage.
     * <p><b>204</b> - Request successful.
     * <p><b>401</b> - If the user who perform the operation is not a &lt;b&gt;camunda-admin&lt;/b&gt; user.
     * @param telemetryConfigurationDto  (optional)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void configureTelemetry(TelemetryConfigurationDto telemetryConfigurationDto) throws RestClientException {
        configureTelemetryWithHttpInfo(telemetryConfigurationDto);
    }

    /**
     * Configure Telemetry
     * Configures whether Camunda receives data collection of the process engine setup and usage.
     * <p><b>204</b> - Request successful.
     * <p><b>401</b> - If the user who perform the operation is not a &lt;b&gt;camunda-admin&lt;/b&gt; user.
     * @param telemetryConfigurationDto  (optional)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> configureTelemetryWithHttpInfo(TelemetryConfigurationDto telemetryConfigurationDto) throws RestClientException {
        Object postBody = telemetryConfigurationDto;
        
        String path = apiClient.expandPath("/telemetry/configuration", Collections.<String, Object>emptyMap());

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
     * Fetch Telemetry Configuration
     * Fetches Telemetry Configuration.
     * <p><b>200</b> - Request successful.
     * <p><b>401</b> - If the user who perform the operation is not a &lt;b&gt;camunda-admin&lt;/b&gt; user.
     * @return TelemetryConfigurationDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TelemetryConfigurationDto getTelemetryConfiguration() throws RestClientException {
        return getTelemetryConfigurationWithHttpInfo().getBody();
    }

    /**
     * Fetch Telemetry Configuration
     * Fetches Telemetry Configuration.
     * <p><b>200</b> - Request successful.
     * <p><b>401</b> - If the user who perform the operation is not a &lt;b&gt;camunda-admin&lt;/b&gt; user.
     * @return ResponseEntity&lt;TelemetryConfigurationDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TelemetryConfigurationDto> getTelemetryConfigurationWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/telemetry/configuration", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<TelemetryConfigurationDto> returnType = new ParameterizedTypeReference<TelemetryConfigurationDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
