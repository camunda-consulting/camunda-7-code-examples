# IdentityApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**checkPassword**](IdentityApi.md#checkPassword) | **POST** /identity/password-policy | Validate Password
[**getGroupInfo**](IdentityApi.md#getGroupInfo) | **GET** /identity/groups | Get a User&#39;s Groups
[**getPasswordPolicy**](IdentityApi.md#getPasswordPolicy) | **GET** /identity/password-policy | Get Password Policy
[**verifyUser**](IdentityApi.md#verifyUser) | **POST** /identity/verify | Verify User



## checkPassword

> CheckPasswordPolicyResultDto checkPassword(passwordPolicyRequestDto)

Validate Password

A password policy consists of a list of rules that new passwords must follow to be policy compliant. A password can be checked for compliancy via this end point. More information on password policies in Camunda can be found in the password policy [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/password-policy/) and in the [security instructions](https://docs.camunda.org/manual/7.16/user-guide/security/).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.IdentityApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        IdentityApi apiInstance = new IdentityApi(defaultClient);
        PasswordPolicyRequestDto passwordPolicyRequestDto = new PasswordPolicyRequestDto(); // PasswordPolicyRequestDto | 
        try {
            CheckPasswordPolicyResultDto result = apiInstance.checkPassword(passwordPolicyRequestDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling IdentityApi#checkPassword");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **passwordPolicyRequestDto** | [**PasswordPolicyRequestDto**](PasswordPolicyRequestDto.md)|  | [optional]

### Return type

[**CheckPasswordPolicyResultDto**](CheckPasswordPolicyResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. This example uses the built-in password policy that enforces a minimum password length, and some complexity rules. The checked password is myPassword which is not complex enough to match all of the policy rules. |  -  |
| **404** | No password policy was found to check the password against. |  -  |


## getGroupInfo

> IdentityServiceGroupInfoDto getGroupInfo(userId)

Get a User&#39;s Groups

Gets the groups of a user by id and includes all users that share a group with the given user.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.IdentityApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        IdentityApi apiInstance = new IdentityApi(defaultClient);
        String userId = "userId_example"; // String | The id of the user to get the groups for.
        try {
            IdentityServiceGroupInfoDto result = apiInstance.getGroupInfo(userId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling IdentityApi#getGroupInfo");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| The id of the user to get the groups for. |

### Return type

[**IdentityServiceGroupInfoDto**](IdentityServiceGroupInfoDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | If the &#x60;userId&#x60; query parameter is missing. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getPasswordPolicy

> PasswordPolicyDto getPasswordPolicy()

Get Password Policy

A password policy consists of a list of rules that new passwords must follow to be policy compliant. This end point returns a JSON representation of the list of policy rules. More information on password policies in Camunda can be found in the password policy [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/password-policy/) and in the [security instructions](https://docs.camunda.org/manual/7.16/user-guide/security/).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.IdentityApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        IdentityApi apiInstance = new IdentityApi(defaultClient);
        try {
            PasswordPolicyDto result = apiInstance.getPasswordPolicy();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling IdentityApi#getPasswordPolicy");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**PasswordPolicyDto**](PasswordPolicyDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. This example uses the built-in password policy that enforces a minimum password length, and some complexity rules. |  -  |
| **404** | No password policy was found. |  -  |


## verifyUser

> AuthenticationResult verifyUser(basicUserCredentialsDto)

Verify User

Verifies that user credentials are valid.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.IdentityApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        IdentityApi apiInstance = new IdentityApi(defaultClient);
        BasicUserCredentialsDto basicUserCredentialsDto = new BasicUserCredentialsDto(); // BasicUserCredentialsDto | 
        try {
            AuthenticationResult result = apiInstance.verifyUser(basicUserCredentialsDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling IdentityApi#verifyUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **basicUserCredentialsDto** | [**BasicUserCredentialsDto**](BasicUserCredentialsDto.md)|  | [optional]

### Return type

[**AuthenticationResult**](AuthenticationResult.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | If body does not contain username or password. |  -  |

