# VersionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getRestAPIVersion**](VersionApi.md#getRestAPIVersion) | **GET** /version | 


<a name="getRestAPIVersion"></a>
# **getRestAPIVersion**
> VersionDto getRestAPIVersion()



Retrieves the version of the Rest API.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VersionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    VersionApi apiInstance = new VersionApi(defaultClient);
    try {
      VersionDto result = apiInstance.getRestAPIVersion();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VersionApi#getRestAPIVersion");
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

[**VersionDto**](VersionDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |

