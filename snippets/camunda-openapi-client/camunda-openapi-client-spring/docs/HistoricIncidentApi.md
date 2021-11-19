# HistoricIncidentApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricIncidents**](HistoricIncidentApi.md#getHistoricIncidents) | **GET** /history/incident | Get Incidents
[**getHistoricIncidentsCount**](HistoricIncidentApi.md#getHistoricIncidentsCount) | **GET** /history/incident/count | Get Incident Count



## getHistoricIncidents

> List&lt;HistoricIncidentDto&gt; getHistoricIncidents(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processInstanceId, executionId, createTimeBefore, createTimeAfter, endTimeBefore, endTimeAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, historyConfiguration, open, resolved, deleted, tenantIdIn, withoutTenantId, jobDefinitionIdIn, sortBy, sortOrder)

Get Incidents

Queries for historic incidents that fulfill given parameters. The size of the result set can be retrieved by using the [Get Incident Count](https://docs.camunda.org/manual/7.16/reference/rest/history/incident/get-incident-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricIncidentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricIncidentApi apiInstance = new HistoricIncidentApi(defaultClient);
        String incidentId = "incidentId_example"; // String | Restricts to incidents that have the given id.
        String incidentType = "incidentType_example"; // String | Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
        String incidentMessage = "incidentMessage_example"; // String | Restricts to incidents that have the given incident message.
        String incidentMessageLike = "incidentMessageLike_example"; // String | Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character '%' to express like-strategy: starts with (string%), ends with (%string) or contains (%string%). 
        String processDefinitionId = "processDefinitionId_example"; // String | Restricts to incidents that belong to a process definition with the given id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Restricts to incidents that have the given processDefinitionKey.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Restricts to incidents that have one of the given process definition keys.
        String processInstanceId = "processInstanceId_example"; // String | Restricts to incidents that belong to a process instance with the given id.
        String executionId = "executionId_example"; // String | Restricts to incidents that belong to an execution with the given id.
        OffsetDateTime createTimeBefore = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime createTimeAfter = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime endTimeBefore = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime endTimeAfter = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String activityId = "activityId_example"; // String | Restricts to incidents that belong to an activity with the given id.
        String failedActivityId = "failedActivityId_example"; // String | Restricts to incidents that were created due to the failure of an activity with the given id.
        String causeIncidentId = "causeIncidentId_example"; // String | Restricts to incidents that have the given incident id as cause incident.
        String rootCauseIncidentId = "rootCauseIncidentId_example"; // String | Restricts to incidents that have the given incident id as root cause incident.
        String _configuration = "_configuration_example"; // String | Restricts to incidents that have the given parameter set as configuration.
        String historyConfiguration = "historyConfiguration_example"; // String | Restricts to incidents that have the given parameter set as history configuration.
        Boolean open = true; // Boolean | Restricts to incidents that are open.
        Boolean resolved = true; // Boolean | Restricts to incidents that are resolved.
        Boolean deleted = true; // Boolean | Restricts to incidents that are deleted.
        String tenantIdIn = "tenantIdIn_example"; // String | Restricts to incidents that have one of the given comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic incidents that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String jobDefinitionIdIn = "jobDefinitionIdIn_example"; // String | Restricts to incidents that have one of the given comma-separated job definition ids.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        try {
            List<HistoricIncidentDto> result = apiInstance.getHistoricIncidents(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processInstanceId, executionId, createTimeBefore, createTimeAfter, endTimeBefore, endTimeAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, historyConfiguration, open, resolved, deleted, tenantIdIn, withoutTenantId, jobDefinitionIdIn, sortBy, sortOrder);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricIncidentApi#getHistoricIncidents");
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
 **incidentId** | **String**| Restricts to incidents that have the given id. | [optional]
 **incidentType** | **String**| Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Restricts to incidents that have the given incident message. | [optional]
 **incidentMessageLike** | **String**| Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).  | [optional]
 **processDefinitionId** | **String**| Restricts to incidents that belong to a process definition with the given id. | [optional]
 **processDefinitionKey** | **String**| Restricts to incidents that have the given processDefinitionKey. | [optional]
 **processDefinitionKeyIn** | **String**| Restricts to incidents that have one of the given process definition keys. | [optional]
 **processInstanceId** | **String**| Restricts to incidents that belong to a process instance with the given id. | [optional]
 **executionId** | **String**| Restricts to incidents that belong to an execution with the given id. | [optional]
 **createTimeBefore** | **OffsetDateTime**| Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **createTimeAfter** | **OffsetDateTime**| Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **endTimeBefore** | **OffsetDateTime**| Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **endTimeAfter** | **OffsetDateTime**| Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **activityId** | **String**| Restricts to incidents that belong to an activity with the given id. | [optional]
 **failedActivityId** | **String**| Restricts to incidents that were created due to the failure of an activity with the given id. | [optional]
 **causeIncidentId** | **String**| Restricts to incidents that have the given incident id as cause incident. | [optional]
 **rootCauseIncidentId** | **String**| Restricts to incidents that have the given incident id as root cause incident. | [optional]
 **_configuration** | **String**| Restricts to incidents that have the given parameter set as configuration. | [optional]
 **historyConfiguration** | **String**| Restricts to incidents that have the given parameter set as history configuration. | [optional]
 **open** | **Boolean**| Restricts to incidents that are open. | [optional]
 **resolved** | **Boolean**| Restricts to incidents that are resolved. | [optional]
 **deleted** | **Boolean**| Restricts to incidents that are deleted. | [optional]
 **tenantIdIn** | **String**| Restricts to incidents that have one of the given comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic incidents that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **jobDefinitionIdIn** | **String**| Restricts to incidents that have one of the given comma-separated job definition ids. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: incidentId, incidentMessage, createTime, endTime, incidentType, executionId, activityId, processInstanceId, processDefinitionId, processDefinitionKey, causeIncidentId, rootCauseIncidentId, configuration, historyConfiguration, tenantId, incidentState]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]

### Return type

[**List&lt;HistoricIncidentDto&gt;**](HistoricIncidentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricIncidentsCount

> CountResultDto getHistoricIncidentsCount(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processInstanceId, executionId, createTimeBefore, createTimeAfter, endTimeBefore, endTimeAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, historyConfiguration, open, resolved, deleted, tenantIdIn, withoutTenantId, jobDefinitionIdIn, sortBy, sortOrder)

Get Incident Count

Queries for the number of historic incidents that fulfill the given parameters. Takes the same parameters as the [Get Incidents](https://docs.camunda.org/manual/7.16/reference/rest/history/incident/get-incident-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricIncidentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricIncidentApi apiInstance = new HistoricIncidentApi(defaultClient);
        String incidentId = "incidentId_example"; // String | Restricts to incidents that have the given id.
        String incidentType = "incidentType_example"; // String | Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
        String incidentMessage = "incidentMessage_example"; // String | Restricts to incidents that have the given incident message.
        String incidentMessageLike = "incidentMessageLike_example"; // String | Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character '%' to express like-strategy: starts with (string%), ends with (%string) or contains (%string%). 
        String processDefinitionId = "processDefinitionId_example"; // String | Restricts to incidents that belong to a process definition with the given id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Restricts to incidents that have the given processDefinitionKey.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Restricts to incidents that have one of the given process definition keys.
        String processInstanceId = "processInstanceId_example"; // String | Restricts to incidents that belong to a process instance with the given id.
        String executionId = "executionId_example"; // String | Restricts to incidents that belong to an execution with the given id.
        OffsetDateTime createTimeBefore = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime createTimeAfter = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime endTimeBefore = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime endTimeAfter = OffsetDateTime.now(); // OffsetDateTime | Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String activityId = "activityId_example"; // String | Restricts to incidents that belong to an activity with the given id.
        String failedActivityId = "failedActivityId_example"; // String | Restricts to incidents that were created due to the failure of an activity with the given id.
        String causeIncidentId = "causeIncidentId_example"; // String | Restricts to incidents that have the given incident id as cause incident.
        String rootCauseIncidentId = "rootCauseIncidentId_example"; // String | Restricts to incidents that have the given incident id as root cause incident.
        String _configuration = "_configuration_example"; // String | Restricts to incidents that have the given parameter set as configuration.
        String historyConfiguration = "historyConfiguration_example"; // String | Restricts to incidents that have the given parameter set as history configuration.
        Boolean open = true; // Boolean | Restricts to incidents that are open.
        Boolean resolved = true; // Boolean | Restricts to incidents that are resolved.
        Boolean deleted = true; // Boolean | Restricts to incidents that are deleted.
        String tenantIdIn = "tenantIdIn_example"; // String | Restricts to incidents that have one of the given comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic incidents that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String jobDefinitionIdIn = "jobDefinitionIdIn_example"; // String | Restricts to incidents that have one of the given comma-separated job definition ids.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        try {
            CountResultDto result = apiInstance.getHistoricIncidentsCount(incidentId, incidentType, incidentMessage, incidentMessageLike, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processInstanceId, executionId, createTimeBefore, createTimeAfter, endTimeBefore, endTimeAfter, activityId, failedActivityId, causeIncidentId, rootCauseIncidentId, _configuration, historyConfiguration, open, resolved, deleted, tenantIdIn, withoutTenantId, jobDefinitionIdIn, sortBy, sortOrder);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricIncidentApi#getHistoricIncidentsCount");
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
 **incidentId** | **String**| Restricts to incidents that have the given id. | [optional]
 **incidentType** | **String**| Restricts to incidents that belong to the given incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Restricts to incidents that have the given incident message. | [optional]
 **incidentMessageLike** | **String**| Restricts to incidents that incidents message is a substring of the given value. The string can include the wildcard character &#39;%&#39; to express like-strategy: starts with (string%), ends with (%string) or contains (%string%).  | [optional]
 **processDefinitionId** | **String**| Restricts to incidents that belong to a process definition with the given id. | [optional]
 **processDefinitionKey** | **String**| Restricts to incidents that have the given processDefinitionKey. | [optional]
 **processDefinitionKeyIn** | **String**| Restricts to incidents that have one of the given process definition keys. | [optional]
 **processInstanceId** | **String**| Restricts to incidents that belong to a process instance with the given id. | [optional]
 **executionId** | **String**| Restricts to incidents that belong to an execution with the given id. | [optional]
 **createTimeBefore** | **OffsetDateTime**| Restricts to incidents that have a createTime date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **createTimeAfter** | **OffsetDateTime**| Restricts to incidents that have a createTime date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **endTimeBefore** | **OffsetDateTime**| Restricts to incidents that have an endTimeBefore date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **endTimeAfter** | **OffsetDateTime**| Restricts to incidents that have an endTimeAfter date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **activityId** | **String**| Restricts to incidents that belong to an activity with the given id. | [optional]
 **failedActivityId** | **String**| Restricts to incidents that were created due to the failure of an activity with the given id. | [optional]
 **causeIncidentId** | **String**| Restricts to incidents that have the given incident id as cause incident. | [optional]
 **rootCauseIncidentId** | **String**| Restricts to incidents that have the given incident id as root cause incident. | [optional]
 **_configuration** | **String**| Restricts to incidents that have the given parameter set as configuration. | [optional]
 **historyConfiguration** | **String**| Restricts to incidents that have the given parameter set as history configuration. | [optional]
 **open** | **Boolean**| Restricts to incidents that are open. | [optional]
 **resolved** | **Boolean**| Restricts to incidents that are resolved. | [optional]
 **deleted** | **Boolean**| Restricts to incidents that are deleted. | [optional]
 **tenantIdIn** | **String**| Restricts to incidents that have one of the given comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic incidents that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **jobDefinitionIdIn** | **String**| Restricts to incidents that have one of the given comma-separated job definition ids. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: incidentId, incidentMessage, createTime, endTime, incidentType, executionId, activityId, processInstanceId, processDefinitionId, processDefinitionKey, causeIncidentId, rootCauseIncidentId, configuration, historyConfiguration, tenantId, incidentState]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]

### Return type

[**CountResultDto**](CountResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

