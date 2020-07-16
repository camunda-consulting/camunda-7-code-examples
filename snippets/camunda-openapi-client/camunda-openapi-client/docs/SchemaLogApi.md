# SchemaLogApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getSchemaLog**](SchemaLogApi.md#getSchemaLog) | **GET** /schema/log | 
[**querySchemaLog**](SchemaLogApi.md#querySchemaLog) | **POST** /schema/log | 


<a name="getSchemaLog"></a>
# **getSchemaLog**
> List&lt;SchemaLogEntryDto&gt; getSchemaLog(version, firstResult, maxResults)



Queries for schema log entries that fulfill given parameters.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.SchemaLogApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    SchemaLogApi apiInstance = new SchemaLogApi(defaultClient);
    String version = "version_example"; // String | Only return schema log entries with a specific version.
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    try {
      List<SchemaLogEntryDto> result = apiInstance.getSchemaLog(version, firstResult, maxResults);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SchemaLogApi#getSchemaLog");
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
 **version** | **String**| Only return schema log entries with a specific version. | [optional]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;SchemaLogEntryDto&gt;**](SchemaLogEntryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. **Note**: In order to get any results a user of group &#x60;camunda-admin&#x60; must be authenticated. |  -  |

<a name="querySchemaLog"></a>
# **querySchemaLog**
> List&lt;SchemaLogEntryDto&gt; querySchemaLog(firstResult, maxResults, schemaLogQueryDto)



Queries for schema log entries that fulfill given parameters.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.SchemaLogApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    SchemaLogApi apiInstance = new SchemaLogApi(defaultClient);
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    SchemaLogQueryDto schemaLogQueryDto = {"version":"7.11.0","sortBy":"timestamp","sortOrder":"asc"}; // SchemaLogQueryDto | 
    try {
      List<SchemaLogEntryDto> result = apiInstance.querySchemaLog(firstResult, maxResults, schemaLogQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SchemaLogApi#querySchemaLog");
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
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **schemaLogQueryDto** | [**SchemaLogQueryDto**](SchemaLogQueryDto.md)|  | [optional]

### Return type

[**List&lt;SchemaLogEntryDto&gt;**](SchemaLogEntryDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. **Note**: In order to get any results a user of group camunda-admin must be authenticated. |  -  |

