# EngineApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getProcessEngineNames**](EngineApi.md#getProcessEngineNames) | **GET** /engine | 


<a name="getProcessEngineNames"></a>
# **getProcessEngineNames**
> List&lt;ProcessEngineDto&gt; getProcessEngineNames()



Retrieves the names of all process engines available on your platform. **Note**: You cannot prepend &#x60;/engine/{name}&#x60; to this method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.EngineApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    EngineApi apiInstance = new EngineApi(defaultClient);
    try {
      List<ProcessEngineDto> result = apiInstance.getProcessEngineNames();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EngineApi#getProcessEngineNames");
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

[**List&lt;ProcessEngineDto&gt;**](ProcessEngineDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |

