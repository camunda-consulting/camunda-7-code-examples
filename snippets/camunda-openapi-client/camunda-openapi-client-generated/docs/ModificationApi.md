# ModificationApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeModification**](ModificationApi.md#executeModification) | **POST** /modification/execute | Execute Modification
[**executeModificationAsync**](ModificationApi.md#executeModificationAsync) | **POST** /modification/executeAsync | Execute Modification Async (Batch)



## executeModification

> executeModification(modificationDto)

Execute Modification

Executes a modification synchronously for multiple process instances. To modify a single process instance, use the [Modify Process Instance Execution State](https://docs.camunda.org/manual/7.16/reference/rest/process-instance/post-modification/) method. To execute a modification asynchronously, use the [Execute Modification Async (Batch)](https://docs.camunda.org/manual/7.16/reference/rest/modification/post-modification-async/) method.  For more information about the difference between synchronous and asynchronous execution of a modification, please refer to the related section of the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration.md#executing-a-migration-plan).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ModificationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ModificationApi apiInstance = new ModificationApi(defaultClient);
        ModificationDto modificationDto = new ModificationDto(); // ModificationDto | 
        try {
            apiInstance.executeModification(modificationDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling ModificationApi#executeModification");
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
 **modificationDto** | [**ModificationDto**](ModificationDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |
| **400** |  In case following parameters are missing: instructions, processDefinitionId, activityId or transitionId, processInstanceIds or processInstanceQuery, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## executeModificationAsync

> BatchDto executeModificationAsync(modificationDto)

Execute Modification Async (Batch)

Executes a modification asynchronously for multiple process instances. To execute a modification synchronously, use the [Execute Modification](https://docs.camunda.org/manual/7.16/reference/rest/modification/post-modification-sync/) method.  For more information about the difference between synchronous and asynchronous execution of a modification, please refer to the related section of the [user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration.md#executing-a-migration-plan).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ModificationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ModificationApi apiInstance = new ModificationApi(defaultClient);
        ModificationDto modificationDto = new ModificationDto(); // ModificationDto | 
        try {
            BatchDto result = apiInstance.executeModificationAsync(modificationDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ModificationApi#executeModificationAsync");
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
 **modificationDto** | [**ModificationDto**](ModificationDto.md)|  | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** |  In case following parameters are missing: instructions, processDefinitionId, activityId or transitionId, processInstanceIds or processInstanceQuery, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |

