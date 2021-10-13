# SchemaLogApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getSchemaLog**](SchemaLogApi.md#getSchemaLog) | **GET** /schema/log | Get List
[**querySchemaLog**](SchemaLogApi.md#querySchemaLog) | **POST** /schema/log | Get List (POST)



## getSchemaLog

> List&lt;SchemaLogEntryDto&gt; getSchemaLog(version, sortBy, sortOrder, firstResult, maxResults)

Get List

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
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<SchemaLogEntryDto> result = apiInstance.getSchemaLog(version, sortBy, sortOrder, firstResult, maxResults);
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: timestamp]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
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
| **200** | Request successful. **Note**: In order to get any results a user of group &#x60;camunda-admin&#x60; must be authenticated. |  -  |


## querySchemaLog

> List&lt;SchemaLogEntryDto&gt; querySchemaLog(firstResult, maxResults, schemaLogQueryDto)

Get List (POST)

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
        SchemaLogQueryDto schemaLogQueryDto = new SchemaLogQueryDto(); // SchemaLogQueryDto | 
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
| **200** | Request successful. **Note**: In order to get any results a user of group camunda-admin must be authenticated. |  -  |

