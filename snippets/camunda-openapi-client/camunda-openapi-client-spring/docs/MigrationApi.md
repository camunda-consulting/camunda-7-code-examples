# MigrationApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeMigrationPlan**](MigrationApi.md#executeMigrationPlan) | **POST** /migration/execute | Execute Migration Plan
[**executeMigrationPlanAsync**](MigrationApi.md#executeMigrationPlanAsync) | **POST** /migration/executeAsync | Execute Migration Plan Async (Batch)
[**generateMigrationPlan**](MigrationApi.md#generateMigrationPlan) | **POST** /migration/generate | Generate Migration Plan
[**validateMigrationPlan**](MigrationApi.md#validateMigrationPlan) | **POST** /migration/validate | Validate Migration Plan



## executeMigrationPlan

> executeMigrationPlan(migrationExecutionDto)

Execute Migration Plan

Executes a migration plan synchronously for multiple process instances. To execute a migration plan asynchronously, use the [Execute Migration Plan Async(Batch)](https://docs.camunda.org/manual/7.16/reference/rest/migration/execute-migration-async/) method.  For more information about the difference between synchronous and asynchronous execution of a migration plan, please refer to the related section of [the user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#executing-a-migration-plan).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MigrationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MigrationApi apiInstance = new MigrationApi(defaultClient);
        MigrationExecutionDto migrationExecutionDto = new MigrationExecutionDto(); // MigrationExecutionDto | 
        try {
            apiInstance.executeMigrationPlan(migrationExecutionDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling MigrationApi#executeMigrationPlan");
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
 **migrationExecutionDto** | [**MigrationExecutionDto**](MigrationExecutionDto.md)|  | [optional]

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
| **400** | The request is not valid if one or more of the following statements apply:  * The provided migration plan is not valid, so an exception of type &#x60;MigrationPlanValidationException&#x60; is returned. * The provided migration plan is not valid for a specific process instance it is applied to, so an exception of type &#x60;MigratingProcessInstanceValidationException&#x60; is returned. * In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## executeMigrationPlanAsync

> BatchDto executeMigrationPlanAsync(migrationExecutionDto)

Execute Migration Plan Async (Batch)

Executes a migration plan asynchronously (batch) for multiple process instances. To execute a migration plan synchronously, use the [Execute MigrationPlan](https://docs.camunda.org/manual/7.16/reference/rest/migration/execute-migration/) method.  For more information about the difference between synchronous and asynchronous execution of a migration plan, please refer to the related section of [the user guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#executing-a-migration-plan).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MigrationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MigrationApi apiInstance = new MigrationApi(defaultClient);
        MigrationExecutionDto migrationExecutionDto = new MigrationExecutionDto(); // MigrationExecutionDto | 
        try {
            BatchDto result = apiInstance.executeMigrationPlanAsync(migrationExecutionDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MigrationApi#executeMigrationPlanAsync");
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
 **migrationExecutionDto** | [**MigrationExecutionDto**](MigrationExecutionDto.md)|  | [optional]

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
| **400** | The request is not valid if one or more of the following statements apply:  * The provided migration plan is not valid, so an exception of type &#x60;MigrationPlanValidationException&#x60; is returned. * In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## generateMigrationPlan

> MigrationPlanDto generateMigrationPlan(migrationPlanGenerationDto)

Generate Migration Plan

Generates a migration plan for two process definitions. The generated migration plan contains migration instructions which map equal activities between the two process definitions.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MigrationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MigrationApi apiInstance = new MigrationApi(defaultClient);
        MigrationPlanGenerationDto migrationPlanGenerationDto = new MigrationPlanGenerationDto(); // MigrationPlanGenerationDto | 
        try {
            MigrationPlanDto result = apiInstance.generateMigrationPlan(migrationPlanGenerationDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MigrationApi#generateMigrationPlan");
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
 **migrationPlanGenerationDto** | [**MigrationPlanGenerationDto**](MigrationPlanGenerationDto.md)|  | [optional]

### Return type

[**MigrationPlanDto**](MigrationPlanDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** |  The requested migration was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## validateMigrationPlan

> MigrationPlanReportDto validateMigrationPlan(migrationPlanDto)

Validate Migration Plan

Validates a migration plan statically without executing it. This corresponds to the [creation time validation](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-migration/#creation-time-validation) described in the user guide.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MigrationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MigrationApi apiInstance = new MigrationApi(defaultClient);
        MigrationPlanDto migrationPlanDto = new MigrationPlanDto(); // MigrationPlanDto | 
        try {
            MigrationPlanReportDto result = apiInstance.validateMigrationPlan(migrationPlanDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MigrationApi#validateMigrationPlan");
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
 **migrationPlanDto** | [**MigrationPlanDto**](MigrationPlanDto.md)|  | [optional]

### Return type

[**MigrationPlanReportDto**](MigrationPlanReportDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. The validation report was returned. |  -  |
| **400** | In case additional parameters of the request are unexpected, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

