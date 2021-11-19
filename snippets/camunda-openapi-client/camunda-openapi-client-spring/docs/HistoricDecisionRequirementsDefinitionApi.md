# HistoricDecisionRequirementsDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getDecisionStatistics**](HistoricDecisionRequirementsDefinitionApi.md#getDecisionStatistics) | **GET** /history/decision-requirements-definition/{id}/statistics | Get DRD Statistics



## getDecisionStatistics

> List&lt;HistoricDecisionInstanceStatisticsDto&gt; getDecisionStatistics(id, decisionInstanceId)

Get DRD Statistics

Retrieves evaluation statistics of a given decision requirements definition.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionRequirementsDefinitionApi apiInstance = new HistoricDecisionRequirementsDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision requirements definition.
        String decisionInstanceId = "decisionInstanceId_example"; // String | Restrict query results to be based only on specific evaluation instance of a given decision requirements definition.
        try {
            List<HistoricDecisionInstanceStatisticsDto> result = apiInstance.getDecisionStatistics(id, decisionInstanceId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionRequirementsDefinitionApi#getDecisionStatistics");
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
 **id** | **String**| The id of the decision requirements definition. |
 **decisionInstanceId** | **String**| Restrict query results to be based only on specific evaluation instance of a given decision requirements definition. | [optional]

### Return type

[**List&lt;HistoricDecisionInstanceStatisticsDto&gt;**](HistoricDecisionInstanceStatisticsDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

