package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AuthorizationExceptionDto;
import com.camunda.consulting.openapi.client.model.EvaluationConditionDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceDto;

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
@Component("com.camunda.consulting.openapi.client.handler.ConditionApi")
public class ConditionApi {
    private ApiClient apiClient;

    public ConditionApi() {
        this(new ApiClient());
    }

    @Autowired
    public ConditionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Evaluate
     * Triggers evaluation of conditions for conditional start event(s).  Internally this maps to the engines condition evaluation builder method ConditionEvaluationBuilder#evaluateStartConditions().  For more information see the [Conditional Start Events](https://docs.camunda.org/manual/7.16/reference/bpmn20/events/conditional-events/#conditional-start-event) section of the [BPMN 2.0 Implementation Reference](https://docs.camunda.org/manual/7.16/reference/bpmn20/).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If both tenantId and withoutTenantId are supplied.
     * <p><b>403</b> - If the user is not allowed to start the process instance of the process definition, which start condition was evaluated to &#x60;true&#x60;.
     * @param evaluationConditionDto  (optional)
     * @return List&lt;ProcessInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ProcessInstanceDto> evaluateCondition(EvaluationConditionDto evaluationConditionDto) throws RestClientException {
        return evaluateConditionWithHttpInfo(evaluationConditionDto).getBody();
    }

    /**
     * Evaluate
     * Triggers evaluation of conditions for conditional start event(s).  Internally this maps to the engines condition evaluation builder method ConditionEvaluationBuilder#evaluateStartConditions().  For more information see the [Conditional Start Events](https://docs.camunda.org/manual/7.16/reference/bpmn20/events/conditional-events/#conditional-start-event) section of the [BPMN 2.0 Implementation Reference](https://docs.camunda.org/manual/7.16/reference/bpmn20/).
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If both tenantId and withoutTenantId are supplied.
     * <p><b>403</b> - If the user is not allowed to start the process instance of the process definition, which start condition was evaluated to &#x60;true&#x60;.
     * @param evaluationConditionDto  (optional)
     * @return ResponseEntity&lt;List&lt;ProcessInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ProcessInstanceDto>> evaluateConditionWithHttpInfo(EvaluationConditionDto evaluationConditionDto) throws RestClientException {
        Object postBody = evaluationConditionDto;
        
        String path = apiClient.expandPath("/condition", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<List<ProcessInstanceDto>> returnType = new ParameterizedTypeReference<List<ProcessInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
