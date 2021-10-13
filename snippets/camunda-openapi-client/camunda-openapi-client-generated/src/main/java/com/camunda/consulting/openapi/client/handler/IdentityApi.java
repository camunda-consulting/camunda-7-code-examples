package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.AuthenticationResult;
import com.camunda.consulting.openapi.client.model.BasicUserCredentialsDto;
import com.camunda.consulting.openapi.client.model.CheckPasswordPolicyResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.IdentityServiceGroupInfoDto;
import com.camunda.consulting.openapi.client.model.PasswordPolicyDto;
import com.camunda.consulting.openapi.client.model.PasswordPolicyRequestDto;

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
@Component("com.camunda.consulting.openapi.client.handler.IdentityApi")
public class IdentityApi {
    private ApiClient apiClient;

    public IdentityApi() {
        this(new ApiClient());
    }

    @Autowired
    public IdentityApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Validate Password
     * A password policy consists of a list of rules that new passwords must follow to be policy compliant. A password can be checked for compliancy via this end point. More information on password policies in Camunda can be found in the password policy [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/password-policy/) and in the [security instructions](https://docs.camunda.org/manual/7.16/user-guide/security/).
     * <p><b>200</b> - Request successful. This example uses the built-in password policy that enforces a minimum password length, and some complexity rules. The checked password is myPassword which is not complex enough to match all of the policy rules.
     * <p><b>404</b> - No password policy was found to check the password against.
     * @param passwordPolicyRequestDto  (optional)
     * @return CheckPasswordPolicyResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CheckPasswordPolicyResultDto checkPassword(PasswordPolicyRequestDto passwordPolicyRequestDto) throws RestClientException {
        return checkPasswordWithHttpInfo(passwordPolicyRequestDto).getBody();
    }

    /**
     * Validate Password
     * A password policy consists of a list of rules that new passwords must follow to be policy compliant. A password can be checked for compliancy via this end point. More information on password policies in Camunda can be found in the password policy [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/password-policy/) and in the [security instructions](https://docs.camunda.org/manual/7.16/user-guide/security/).
     * <p><b>200</b> - Request successful. This example uses the built-in password policy that enforces a minimum password length, and some complexity rules. The checked password is myPassword which is not complex enough to match all of the policy rules.
     * <p><b>404</b> - No password policy was found to check the password against.
     * @param passwordPolicyRequestDto  (optional)
     * @return ResponseEntity&lt;CheckPasswordPolicyResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CheckPasswordPolicyResultDto> checkPasswordWithHttpInfo(PasswordPolicyRequestDto passwordPolicyRequestDto) throws RestClientException {
        Object postBody = passwordPolicyRequestDto;
        
        String path = apiClient.expandPath("/identity/password-policy", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<CheckPasswordPolicyResultDto> returnType = new ParameterizedTypeReference<CheckPasswordPolicyResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get a User&#39;s Groups
     * Gets the groups of a user by id and includes all users that share a group with the given user.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If the &#x60;userId&#x60; query parameter is missing. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param userId The id of the user to get the groups for. (required)
     * @return IdentityServiceGroupInfoDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public IdentityServiceGroupInfoDto getGroupInfo(String userId) throws RestClientException {
        return getGroupInfoWithHttpInfo(userId).getBody();
    }

    /**
     * Get a User&#39;s Groups
     * Gets the groups of a user by id and includes all users that share a group with the given user.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If the &#x60;userId&#x60; query parameter is missing. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param userId The id of the user to get the groups for. (required)
     * @return ResponseEntity&lt;IdentityServiceGroupInfoDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<IdentityServiceGroupInfoDto> getGroupInfoWithHttpInfo(String userId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'userId' when calling getGroupInfo");
        }
        
        String path = apiClient.expandPath("/identity/groups", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "userId", userId));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<IdentityServiceGroupInfoDto> returnType = new ParameterizedTypeReference<IdentityServiceGroupInfoDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Password Policy
     * A password policy consists of a list of rules that new passwords must follow to be policy compliant. This end point returns a JSON representation of the list of policy rules. More information on password policies in Camunda can be found in the password policy [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/password-policy/) and in the [security instructions](https://docs.camunda.org/manual/7.16/user-guide/security/).
     * <p><b>200</b> - Request successful. This example uses the built-in password policy that enforces a minimum password length, and some complexity rules.
     * <p><b>404</b> - No password policy was found.
     * @return PasswordPolicyDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PasswordPolicyDto getPasswordPolicy() throws RestClientException {
        return getPasswordPolicyWithHttpInfo().getBody();
    }

    /**
     * Get Password Policy
     * A password policy consists of a list of rules that new passwords must follow to be policy compliant. This end point returns a JSON representation of the list of policy rules. More information on password policies in Camunda can be found in the password policy [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/password-policy/) and in the [security instructions](https://docs.camunda.org/manual/7.16/user-guide/security/).
     * <p><b>200</b> - Request successful. This example uses the built-in password policy that enforces a minimum password length, and some complexity rules.
     * <p><b>404</b> - No password policy was found.
     * @return ResponseEntity&lt;PasswordPolicyDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PasswordPolicyDto> getPasswordPolicyWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/identity/password-policy", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<PasswordPolicyDto> returnType = new ParameterizedTypeReference<PasswordPolicyDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Verify User
     * Verifies that user credentials are valid.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If body does not contain username or password.
     * @param basicUserCredentialsDto  (optional)
     * @return AuthenticationResult
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public AuthenticationResult verifyUser(BasicUserCredentialsDto basicUserCredentialsDto) throws RestClientException {
        return verifyUserWithHttpInfo(basicUserCredentialsDto).getBody();
    }

    /**
     * Verify User
     * Verifies that user credentials are valid.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - If body does not contain username or password.
     * @param basicUserCredentialsDto  (optional)
     * @return ResponseEntity&lt;AuthenticationResult&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<AuthenticationResult> verifyUserWithHttpInfo(BasicUserCredentialsDto basicUserCredentialsDto) throws RestClientException {
        Object postBody = basicUserCredentialsDto;
        
        String path = apiClient.expandPath("/identity/verify", Collections.<String, Object>emptyMap());

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

        ParameterizedTypeReference<AuthenticationResult> returnType = new ParameterizedTypeReference<AuthenticationResult>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
