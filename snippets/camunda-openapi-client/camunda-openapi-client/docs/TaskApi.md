# TaskApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**claim**](TaskApi.md#claim) | **POST** /task/{id}/claim | 
[**complete**](TaskApi.md#complete) | **POST** /task/{id}/complete | 
[**createTask**](TaskApi.md#createTask) | **POST** /task/create | 
[**delegateTask**](TaskApi.md#delegateTask) | **POST** /task/{id}/delegate | 
[**deleteTask**](TaskApi.md#deleteTask) | **DELETE** /task/{id} | 
[**getDeployedForm**](TaskApi.md#getDeployedForm) | **GET** /task/{id}/deployed-form | 
[**getForm**](TaskApi.md#getForm) | **GET** /task/{id}/form | 
[**getFormVariables**](TaskApi.md#getFormVariables) | **GET** /task/{id}/form-variables | 
[**getRenderedForm**](TaskApi.md#getRenderedForm) | **GET** /task/{id}/rendered-form | 
[**getTask**](TaskApi.md#getTask) | **GET** /task/{id} | 
[**getTasks**](TaskApi.md#getTasks) | **GET** /task | 
[**getTasksCount**](TaskApi.md#getTasksCount) | **GET** /task/count | 
[**handleBpmnError**](TaskApi.md#handleBpmnError) | **POST** /task/{id}/bpmnError | 
[**handleEscalation**](TaskApi.md#handleEscalation) | **POST** /task/{id}/bpmnEscalation | 
[**queryTasks**](TaskApi.md#queryTasks) | **POST** /task | 
[**queryTasksCount**](TaskApi.md#queryTasksCount) | **POST** /task/count | 
[**resolve**](TaskApi.md#resolve) | **POST** /task/{id}/resolve | 
[**setAssignee**](TaskApi.md#setAssignee) | **POST** /task/{id}/assignee | 
[**submit**](TaskApi.md#submit) | **POST** /task/{id}/submit-form | 
[**unclaim**](TaskApi.md#unclaim) | **POST** /task/{id}/unclaim | 
[**updateTask**](TaskApi.md#updateTask) | **PUT** /task/{id} | 


<a name="claim"></a>
# **claim**
> claim(id, userIdDto)



Claims a task for a specific user.  **Note:** The difference with the [Set Assignee](https://docs.camunda.org/manual/7.13/reference/rest/task/post-assignee/) method is that here a check is performed to see if the task already has a user assigned to it.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to claim.
    UserIdDto userIdDto = {"userId":"aUserId"}; // UserIdDto | Provide the id of the user that claims the task.
    try {
      apiInstance.claim(id, userIdDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#claim");
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
 **id** | **String**| The id of the task to claim. |
 **userIdDto** | [**UserIdDto**](UserIdDto.md)| Provide the id of the user that claims the task. | [optional]

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
**204** | Request successful. |  -  |
**500** | Task with given id does not exist or claiming was not successful. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="complete"></a>
# **complete**
> Map&lt;String, VariableValueDto&gt; complete(id, completeTaskDto)



Completes a task and updates process variables.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to complete.
    CompleteTaskDto completeTaskDto = {"variables":{"aVariable":{"value":"aStringValue"},"anotherVariable":{"value":42},"aThirdVariable":{"value":true}},"withVariablesInReturn":true}; // CompleteTaskDto | 
    try {
      Map<String, VariableValueDto> result = apiInstance.complete(id, completeTaskDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#complete");
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
 **id** | **String**| The id of the task to complete. |
 **completeTaskDto** | [**CompleteTaskDto**](CompleteTaskDto.md)|  | [optional]

### Return type

[**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. The response contains the process variables. |  -  |
**204** | Request successful. The response contains no variables. |  -  |
**400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | If the task does not exist or the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="createTask"></a>
# **createTask**
> createTask(taskDto)



Creates a new task.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    TaskDto taskDto = {"id":"aTaskId","name":"My Task","description":"This have to be done very urgent","priority":30,"assignee":"peter","owner":"mary","delegationState":"PENDING","due":"2014-08-30T10:00:00.000+0200","followUp":"2014-08-25T10:00:00.000+0200","parentTaskId":"aParentTaskId","caseInstanceId":"aCaseInstanceId","tenantId":null}; // TaskDto | 
    try {
      apiInstance.createTask(taskDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#createTask");
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
 **taskDto** | [**TaskDto**](TaskDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Returned if a not valid &#x60;delegationState&#x60; is supplied. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="delegateTask"></a>
# **delegateTask**
> delegateTask(id, userIdDto)



Delegates a task to another user.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to delegate.
    UserIdDto userIdDto = {"userId":"aUserId"}; // UserIdDto | Provide the id of the user that the task should be delegated to.
    try {
      apiInstance.delegateTask(id, userIdDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#delegateTask");
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
 **id** | **String**| The id of the task to delegate. |
 **userIdDto** | [**UserIdDto**](UserIdDto.md)| Provide the id of the user that the task should be delegated to. | [optional]

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
**204** | Request successful. |  -  |
**500** | If the task does not exist or delegation was not successful. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="deleteTask"></a>
# **deleteTask**
> deleteTask(id)



Removes a task by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to be removed.
    try {
      apiInstance.deleteTask(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#deleteTask");
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
 **id** | **String**| The id of the task to be removed. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Request successful. |  -  |
**400** | Bad Request. The Task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The Task with the given id cannot be deleted because it is part of a running process or case instance. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeployedForm"></a>
# **getDeployedForm**
> File getDeployedForm(id)



Retrieves the deployed form that is referenced from a given task. For further information please refer to the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#embedded-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to get the deployed form for.
    try {
      File result = apiInstance.getDeployedForm(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getDeployedForm");
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
 **id** | **String**| The id of the task to get the deployed form for. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The form key has wrong format. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**403** | The deployed form cannot be retrieved due to missing permissions on task resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | No deployed form for a given task exists. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getForm"></a>
# **getForm**
> FormDto getForm(id)



Retrieves the form key for a task. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine. This key can be used to do task-specific form rendering in client applications. Additionally, the context path of the containing process application is returned.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the form for.
    try {
      FormDto result = apiInstance.getForm(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getForm");
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
 **id** | **String**| The id of the task to retrieve the form for. |

### Return type

[**FormDto**](FormDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getFormVariables"></a>
# **getFormVariables**
> Map&lt;String, VariableValueDto&gt; getFormVariables(id, variableNames, deserializeValues)



Retrieves the form variables for a task. The form variables take form data specified on the task into account. If form fields are defined, the variable types and default values of the form fields are taken into account.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the variables for.
    String variableNames = "variableNames_example"; // String | A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored.
    Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      Map<String, VariableValueDto> result = apiInstance.getFormVariables(id, variableNames, deserializeValues);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getFormVariables");
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
 **id** | **String**| The id of the task to retrieve the variables for. |
 **variableNames** | **String**| A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

### Return type

[**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. A JSON object containing a property for each variable returned. |  -  |
**404** |  id is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getRenderedForm"></a>
# **getRenderedForm**
> File getRenderedForm(id)



Retrieves the rendered form for a task. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to get the rendered form for.
    try {
      File result = apiInstance.getRenderedForm(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getRenderedForm");
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
 **id** | **String**| The id of the task to get the rendered form for. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The task with the given id does not exist or has no form field metadata defined for this task. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTask"></a>
# **getTask**
> TaskDto getTask(id)



Retrieves a task by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to be retrieved.
    try {
      TaskDto result = apiInstance.getTask(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getTask");
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
 **id** | **String**| The id of the task to be retrieved. |

### Return type

[**TaskDto**](TaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTasks"></a>
# **getTasks**
> List&lt;TaskDto&gt; getTasks(processInstanceId, processInstanceIdIn, processInstanceBusinessKey, processInstanceBusinessKeyExpression, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, processInstanceBusinessKeyLikeExpression, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, executionId, caseInstanceId, caseInstanceBusinessKey, caseInstanceBusinessKeyLike, caseDefinitionId, caseDefinitionKey, caseDefinitionName, caseDefinitionNameLike, caseExecutionId, activityInstanceIdIn, tenantIdIn, withoutTenantId, assignee, assigneeExpression, assigneeLike, assigneeLikeExpression, assigneeIn, owner, ownerExpression, candidateGroup, candidateGroupExpression, candidateUser, candidateUserExpression, includeAssignedTasks, involvedUser, involvedUserExpression, assigned, unassigned, taskDefinitionKey, taskDefinitionKeyIn, taskDefinitionKeyLike, name, nameNotEqual, nameLike, nameNotLike, description, descriptionLike, priority, maxPriority, minPriority, dueDate, dueDateExpression, dueAfter, dueAfterExpression, dueBefore, dueBeforeExpression, followUpDate, followUpDateExpression, followUpAfter, followUpAfterExpression, followUpBefore, followUpBeforeExpression, followUpBeforeOrNotExistent, followUpBeforeOrNotExistentExpression, createdOn, createdOnExpression, createdAfter, createdAfterExpression, createdBefore, createdBeforeExpression, delegationState, candidateGroups, candidateGroupsExpression, withCandidateGroups, withoutCandidateGroups, withCandidateUsers, withoutCandidateUsers, active, suspended, taskVariables, processVariables, caseInstanceVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, parentTaskId, sortBy, sortOrder, firstResult, maxResults)



Queries for tasks that fulfill a given filter. The size of the result set can be retrieved by using the Get Task Count method.  **Security Consideration:** There are several query parameters (such as assigneeExpression) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations](https://docs.camunda.org/manual/7.13/user-guide/process-engine/securing-custom-code/) for custom code in the user guide for details.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String processInstanceId = "processInstanceId_example"; // String | Restrict to tasks that belong to process instances with the given id.
    String processInstanceIdIn = "processInstanceIdIn_example"; // String | Restrict to tasks that belong to process instances with the given ids.
    String processInstanceBusinessKey = "processInstanceBusinessKey_example"; // String | Restrict to tasks that belong to process instances with the given business key.
    String processInstanceBusinessKeyExpression = "processInstanceBusinessKeyExpression_example"; // String | Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions.
    String processInstanceBusinessKeyIn = "processInstanceBusinessKeyIn_example"; // String | Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list.
    String processInstanceBusinessKeyLike = "processInstanceBusinessKeyLike_example"; // String | Restrict to tasks that have a process instance business key that has the parameter  value as a substring.
    String processInstanceBusinessKeyLikeExpression = "processInstanceBusinessKeyLikeExpression_example"; // String | Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String processDefinitionId = "processDefinitionId_example"; // String | Restrict to tasks that belong to a process definition with the given id.
    String processDefinitionKey = "processDefinitionKey_example"; // String | Restrict to tasks that belong to a process definition with the given key.
    String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list.
    String processDefinitionName = "processDefinitionName_example"; // String | Restrict to tasks that belong to a process definition with the given name.
    String processDefinitionNameLike = "processDefinitionNameLike_example"; // String | Restrict to tasks that have a process definition name that has the parameter value as  a substring.
    String executionId = "executionId_example"; // String | Restrict to tasks that belong to an execution with the given id.
    String caseInstanceId = "caseInstanceId_example"; // String | Restrict to tasks that belong to case instances with the given id.
    String caseInstanceBusinessKey = "caseInstanceBusinessKey_example"; // String | Restrict to tasks that belong to case instances with the given business key.
    String caseInstanceBusinessKeyLike = "caseInstanceBusinessKeyLike_example"; // String | Restrict to tasks that have a case instance business key that has the parameter value  as a substring.
    String caseDefinitionId = "caseDefinitionId_example"; // String | Restrict to tasks that belong to a case definition with the given id.
    String caseDefinitionKey = "caseDefinitionKey_example"; // String | Restrict to tasks that belong to a case definition with the given key.
    String caseDefinitionName = "caseDefinitionName_example"; // String | Restrict to tasks that belong to a case definition with the given name.
    String caseDefinitionNameLike = "caseDefinitionNameLike_example"; // String | Restrict to tasks that have a case definition name that has the parameter value as a  substring.
    String caseExecutionId = "caseExecutionId_example"; // String | Restrict to tasks that belong to a case execution with the given id.
    String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include tasks which belong to one of the passed and comma-separated activity  instance ids.
    String tenantIdIn = "tenantIdIn_example"; // String | Only include tasks which belong to one of the passed and comma-separated  tenant ids.
    Boolean withoutTenantId = false; // Boolean | Only include tasks which belong to no tenant. Value may only be `true`,  as `false` is the default behavior.
    String assignee = "assignee_example"; // String | Restrict to tasks that the given user is assigned to.
    String assigneeExpression = "assigneeExpression_example"; // String | Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String assigneeLike = "assigneeLike_example"; // String | Restrict to tasks that have an assignee that has the parameter  value as a substring.
    String assigneeLikeExpression = "assigneeLikeExpression_example"; // String | Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String assigneeIn = "assigneeIn_example"; // String | Only include tasks which are assigned to one of the passed and  comma-separated user ids.
    String owner = "owner_example"; // String | Restrict to tasks that the given user owns.
    String ownerExpression = "ownerExpression_example"; // String | Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String candidateGroup = "candidateGroup_example"; // String | Only include tasks that are offered to the given group.
    String candidateGroupExpression = "candidateGroupExpression_example"; // String | Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String candidateUser = "candidateUser_example"; // String | Only include tasks that are offered to the given user or to one of his groups.
    String candidateUserExpression = "candidateUserExpression_example"; // String | Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    Boolean includeAssignedTasks = false; // Boolean | Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s).
    String involvedUser = "involvedUser_example"; // String | Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee).
    String involvedUserExpression = "involvedUserExpression_example"; // String | Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions.
    Boolean assigned = false; // Boolean | If set to `true`, restricts the query to all tasks that are assigned.
    Boolean unassigned = false; // Boolean | If set to `true`, restricts the query to all tasks that are unassigned.
    String taskDefinitionKey = "taskDefinitionKey_example"; // String | Restrict to tasks that have the given key.
    String taskDefinitionKeyIn = "taskDefinitionKeyIn_example"; // String | Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list.
    String taskDefinitionKeyLike = "taskDefinitionKeyLike_example"; // String | Restrict to tasks that have a key that has the parameter value as a substring.
    String name = "name_example"; // String | Restrict to tasks that have the given name.
    String nameNotEqual = "nameNotEqual_example"; // String | Restrict to tasks that do not have the given name.
    String nameLike = "nameLike_example"; // String | Restrict to tasks that have a name with the given parameter value as substring.
    String nameNotLike = "nameNotLike_example"; // String | Restrict to tasks that do not have a name with the given parameter value as substring.
    String description = "description_example"; // String | Restrict to tasks that have the given description.
    String descriptionLike = "descriptionLike_example"; // String | Restrict to tasks that have a description that has the parameter value as a substring.
    Integer priority = 56; // Integer | Restrict to tasks that have the given priority.
    Integer maxPriority = 56; // Integer | Restrict to tasks that have a lower or equal priority.
    Integer minPriority = 56; // Integer | Restrict to tasks that have a higher or equal priority.
    String dueDate = "dueDate_example"; // String | Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.546+0200`.
    String dueDateExpression = "dueDateExpression_example"; // String | Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String dueAfter = "dueAfter_example"; // String | Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.435+0200`.
    String dueAfterExpression = "dueAfterExpression_example"; // String | Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String dueBefore = "dueBefore_example"; // String | Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.243+0200`.
    String dueBeforeExpression = "dueBeforeExpression_example"; // String | Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpDate = "followUpDate_example"; // String | Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.342+0200`.
    String followUpDateExpression = "followUpDateExpression_example"; // String | Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpAfter = "followUpAfter_example"; // String | Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.542+0200`.
    String followUpAfterExpression = "followUpAfterExpression_example"; // String | Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpBefore = "followUpBefore_example"; // String | Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.234+0200`.
    String followUpBeforeExpression = "followUpBeforeExpression_example"; // String | Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpBeforeOrNotExistent = "followUpBeforeOrNotExistent_example"; // String | Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.432+0200`. The typical use case is to query all `active` tasks for a user for a given date.
    String followUpBeforeOrNotExistentExpression = "followUpBeforeOrNotExistentExpression_example"; // String | Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String createdOn = "createdOn_example"; // String | Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.324+0200`.
    String createdOnExpression = "createdOnExpression_example"; // String | Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String createdAfter = "createdAfter_example"; // String | Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.342+0200`.
    String createdAfterExpression = "createdAfterExpression_example"; // String | Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String createdBefore = "createdBefore_example"; // String | Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.332+0200`.
    String createdBeforeExpression = "createdBeforeExpression_example"; // String | Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String delegationState = "delegationState_example"; // String | Restrict to tasks that are in the given delegation state. Valid values are `PENDING` and `RESOLVED`.
    String candidateGroups = "candidateGroups_example"; // String | Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example `developers,support,sales`.
    String candidateGroupsExpression = "candidateGroupsExpression_example"; // String | Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to `java.util.List` of Strings.
    Boolean withCandidateGroups = false; // Boolean | Only include tasks which have a candidate group. Value may only be `true`, as `false` is the default behavior.
    Boolean withoutCandidateGroups = false; // Boolean | Only include tasks which have no candidate group. Value may only be `true`, as `false` is the default behavior.
    Boolean withCandidateUsers = false; // Boolean | Only include tasks which have a candidate user. Value may only be `true`, as `false` is the default behavior.
    Boolean withoutCandidateUsers = false; // Boolean | Only include tasks which have no candidate users. Value may only be `true`, as `false` is the default behavior.
    Boolean active = false; // Boolean | Only include active tasks. Value may only be `true`, as `false` is the default behavior.
    Boolean suspended = false; // Boolean | Only include suspended tasks. Value may only be `true`, as `false` is the default behavior.
    String taskVariables = "taskVariables_example"; // String | Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    String processVariables = "processVariables_example"; // String | Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    String caseInstanceVariables = "caseInstanceVariables_example"; // String | Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    Boolean variableNamesIgnoreCase = false; // Boolean | Match all variable names in this query case-insensitively. If set `variableName` and `variablename` are treated as equal.
    Boolean variableValuesIgnoreCase = false; // Boolean | Match all variable values in this query case-insensitively. If set `variableValue` and `variablevalue` are treated as equal.
    String parentTaskId = "parentTaskId_example"; // String | Restrict query to all tasks that are sub tasks of the given task. Takes a task id.
    String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
    String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    try {
      List<TaskDto> result = apiInstance.getTasks(processInstanceId, processInstanceIdIn, processInstanceBusinessKey, processInstanceBusinessKeyExpression, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, processInstanceBusinessKeyLikeExpression, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, executionId, caseInstanceId, caseInstanceBusinessKey, caseInstanceBusinessKeyLike, caseDefinitionId, caseDefinitionKey, caseDefinitionName, caseDefinitionNameLike, caseExecutionId, activityInstanceIdIn, tenantIdIn, withoutTenantId, assignee, assigneeExpression, assigneeLike, assigneeLikeExpression, assigneeIn, owner, ownerExpression, candidateGroup, candidateGroupExpression, candidateUser, candidateUserExpression, includeAssignedTasks, involvedUser, involvedUserExpression, assigned, unassigned, taskDefinitionKey, taskDefinitionKeyIn, taskDefinitionKeyLike, name, nameNotEqual, nameLike, nameNotLike, description, descriptionLike, priority, maxPriority, minPriority, dueDate, dueDateExpression, dueAfter, dueAfterExpression, dueBefore, dueBeforeExpression, followUpDate, followUpDateExpression, followUpAfter, followUpAfterExpression, followUpBefore, followUpBeforeExpression, followUpBeforeOrNotExistent, followUpBeforeOrNotExistentExpression, createdOn, createdOnExpression, createdAfter, createdAfterExpression, createdBefore, createdBeforeExpression, delegationState, candidateGroups, candidateGroupsExpression, withCandidateGroups, withoutCandidateGroups, withCandidateUsers, withoutCandidateUsers, active, suspended, taskVariables, processVariables, caseInstanceVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, parentTaskId, sortBy, sortOrder, firstResult, maxResults);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getTasks");
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
 **processInstanceId** | **String**| Restrict to tasks that belong to process instances with the given id. | [optional]
 **processInstanceIdIn** | **String**| Restrict to tasks that belong to process instances with the given ids. | [optional]
 **processInstanceBusinessKey** | **String**| Restrict to tasks that belong to process instances with the given business key. | [optional]
 **processInstanceBusinessKeyExpression** | **String**| Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. | [optional]
 **processInstanceBusinessKeyIn** | **String**| Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list. | [optional]
 **processInstanceBusinessKeyLike** | **String**| Restrict to tasks that have a process instance business key that has the parameter  value as a substring. | [optional]
 **processInstanceBusinessKeyLikeExpression** | **String**| Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **processDefinitionId** | **String**| Restrict to tasks that belong to a process definition with the given id. | [optional]
 **processDefinitionKey** | **String**| Restrict to tasks that belong to a process definition with the given key. | [optional]
 **processDefinitionKeyIn** | **String**| Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list. | [optional]
 **processDefinitionName** | **String**| Restrict to tasks that belong to a process definition with the given name. | [optional]
 **processDefinitionNameLike** | **String**| Restrict to tasks that have a process definition name that has the parameter value as  a substring. | [optional]
 **executionId** | **String**| Restrict to tasks that belong to an execution with the given id. | [optional]
 **caseInstanceId** | **String**| Restrict to tasks that belong to case instances with the given id. | [optional]
 **caseInstanceBusinessKey** | **String**| Restrict to tasks that belong to case instances with the given business key. | [optional]
 **caseInstanceBusinessKeyLike** | **String**| Restrict to tasks that have a case instance business key that has the parameter value  as a substring. | [optional]
 **caseDefinitionId** | **String**| Restrict to tasks that belong to a case definition with the given id. | [optional]
 **caseDefinitionKey** | **String**| Restrict to tasks that belong to a case definition with the given key. | [optional]
 **caseDefinitionName** | **String**| Restrict to tasks that belong to a case definition with the given name. | [optional]
 **caseDefinitionNameLike** | **String**| Restrict to tasks that have a case definition name that has the parameter value as a  substring. | [optional]
 **caseExecutionId** | **String**| Restrict to tasks that belong to a case execution with the given id. | [optional]
 **activityInstanceIdIn** | **String**| Only include tasks which belong to one of the passed and comma-separated activity  instance ids. | [optional]
 **tenantIdIn** | **String**| Only include tasks which belong to one of the passed and comma-separated  tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include tasks which belong to no tenant. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **assignee** | **String**| Restrict to tasks that the given user is assigned to. | [optional]
 **assigneeExpression** | **String**| Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **assigneeLike** | **String**| Restrict to tasks that have an assignee that has the parameter  value as a substring. | [optional]
 **assigneeLikeExpression** | **String**| Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **assigneeIn** | **String**| Only include tasks which are assigned to one of the passed and  comma-separated user ids. | [optional]
 **owner** | **String**| Restrict to tasks that the given user owns. | [optional]
 **ownerExpression** | **String**| Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **candidateGroup** | **String**| Only include tasks that are offered to the given group. | [optional]
 **candidateGroupExpression** | **String**| Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **candidateUser** | **String**| Only include tasks that are offered to the given user or to one of his groups. | [optional]
 **candidateUserExpression** | **String**| Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **includeAssignedTasks** | **Boolean**| Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s). | [optional] [default to false]
 **involvedUser** | **String**| Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee). | [optional]
 **involvedUserExpression** | **String**| Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. | [optional]
 **assigned** | **Boolean**| If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. | [optional] [default to false]
 **unassigned** | **Boolean**| If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. | [optional] [default to false]
 **taskDefinitionKey** | **String**| Restrict to tasks that have the given key. | [optional]
 **taskDefinitionKeyIn** | **String**| Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list. | [optional]
 **taskDefinitionKeyLike** | **String**| Restrict to tasks that have a key that has the parameter value as a substring. | [optional]
 **name** | **String**| Restrict to tasks that have the given name. | [optional]
 **nameNotEqual** | **String**| Restrict to tasks that do not have the given name. | [optional]
 **nameLike** | **String**| Restrict to tasks that have a name with the given parameter value as substring. | [optional]
 **nameNotLike** | **String**| Restrict to tasks that do not have a name with the given parameter value as substring. | [optional]
 **description** | **String**| Restrict to tasks that have the given description. | [optional]
 **descriptionLike** | **String**| Restrict to tasks that have a description that has the parameter value as a substring. | [optional]
 **priority** | **Integer**| Restrict to tasks that have the given priority. | [optional]
 **maxPriority** | **Integer**| Restrict to tasks that have a lower or equal priority. | [optional]
 **minPriority** | **Integer**| Restrict to tasks that have a higher or equal priority. | [optional]
 **dueDate** | **String**| Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. | [optional]
 **dueDateExpression** | **String**| Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **dueAfter** | **String**| Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.435+0200&#x60;. | [optional]
 **dueAfterExpression** | **String**| Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **dueBefore** | **String**| Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.243+0200&#x60;. | [optional]
 **dueBeforeExpression** | **String**| Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpDate** | **String**| Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. | [optional]
 **followUpDateExpression** | **String**| Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpAfter** | **String**| Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.542+0200&#x60;. | [optional]
 **followUpAfterExpression** | **String**| Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpBefore** | **String**| Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.234+0200&#x60;. | [optional]
 **followUpBeforeExpression** | **String**| Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpBeforeOrNotExistent** | **String**| Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.432+0200&#x60;. The typical use case is to query all &#x60;active&#x60; tasks for a user for a given date. | [optional]
 **followUpBeforeOrNotExistentExpression** | **String**| Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **createdOn** | **String**| Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.324+0200&#x60;. | [optional]
 **createdOnExpression** | **String**| Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **createdAfter** | **String**| Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. | [optional]
 **createdAfterExpression** | **String**| Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **createdBefore** | **String**| Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.332+0200&#x60;. | [optional]
 **createdBeforeExpression** | **String**| Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **delegationState** | **String**| Restrict to tasks that are in the given delegation state. Valid values are &#x60;PENDING&#x60; and &#x60;RESOLVED&#x60;. | [optional] [enum: PENDING, RESOLVED]
 **candidateGroups** | **String**| Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example &#x60;developers,support,sales&#x60;. | [optional]
 **candidateGroupsExpression** | **String**| Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to &#x60;java.util.List&#x60; of Strings. | [optional]
 **withCandidateGroups** | **Boolean**| Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **withoutCandidateGroups** | **Boolean**| Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **withCandidateUsers** | **Boolean**| Only include tasks which have a candidate user. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **withoutCandidateUsers** | **Boolean**| Only include tasks which have no candidate users. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **active** | **Boolean**| Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **suspended** | **Boolean**| Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **taskVariables** | **String**| Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **processVariables** | **String**| Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **caseInstanceVariables** | **String**| Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names in this query case-insensitively. If set &#x60;variableName&#x60; and &#x60;variablename&#x60; are treated as equal. | [optional] [default to false]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values in this query case-insensitively. If set &#x60;variableValue&#x60; and &#x60;variablevalue&#x60; are treated as equal. | [optional] [default to false]
 **parentTaskId** | **String**| Restrict query to all tasks that are sub tasks of the given task. Takes a task id. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: instanceId, caseInstanceId, dueDate, executionId, caseExecutionId, assignee, created, description, id, name, nameCaseInsensitive, priority, processVariable, executionVariable, taskVariable, caseExecutionVariable, caseInstanceVariable]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;TaskDto&gt;**](TaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTasksCount"></a>
# **getTasksCount**
> CountResultDto getTasksCount(processInstanceId, processInstanceIdIn, processInstanceBusinessKey, processInstanceBusinessKeyExpression, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, processInstanceBusinessKeyLikeExpression, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, executionId, caseInstanceId, caseInstanceBusinessKey, caseInstanceBusinessKeyLike, caseDefinitionId, caseDefinitionKey, caseDefinitionName, caseDefinitionNameLike, caseExecutionId, activityInstanceIdIn, tenantIdIn, withoutTenantId, assignee, assigneeExpression, assigneeLike, assigneeLikeExpression, assigneeIn, owner, ownerExpression, candidateGroup, candidateGroupExpression, candidateUser, candidateUserExpression, includeAssignedTasks, involvedUser, involvedUserExpression, assigned, unassigned, taskDefinitionKey, taskDefinitionKeyIn, taskDefinitionKeyLike, name, nameNotEqual, nameLike, nameNotLike, description, descriptionLike, priority, maxPriority, minPriority, dueDate, dueDateExpression, dueAfter, dueAfterExpression, dueBefore, dueBeforeExpression, followUpDate, followUpDateExpression, followUpAfter, followUpAfterExpression, followUpBefore, followUpBeforeExpression, followUpBeforeOrNotExistent, followUpBeforeOrNotExistentExpression, createdOn, createdOnExpression, createdAfter, createdAfterExpression, createdBefore, createdBeforeExpression, delegationState, candidateGroups, candidateGroupsExpression, withCandidateGroups, withoutCandidateGroups, withCandidateUsers, withoutCandidateUsers, active, suspended, taskVariables, processVariables, caseInstanceVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, parentTaskId)



Retrieves the number of tasks that fulfill a provided filter. Corresponds to the size of the result set when using the [Get Tasks](https://docs.camunda.org/manual/7.13/reference/rest/task/) method.  **Security Consideration:** There are several query parameters (such as assigneeExpression) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations](https://docs.camunda.org/manual/7.13/user-guide/process-engine/securing-custom-code/) for custom code in the user guide for details.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String processInstanceId = "processInstanceId_example"; // String | Restrict to tasks that belong to process instances with the given id.
    String processInstanceIdIn = "processInstanceIdIn_example"; // String | Restrict to tasks that belong to process instances with the given ids.
    String processInstanceBusinessKey = "processInstanceBusinessKey_example"; // String | Restrict to tasks that belong to process instances with the given business key.
    String processInstanceBusinessKeyExpression = "processInstanceBusinessKeyExpression_example"; // String | Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions.
    String processInstanceBusinessKeyIn = "processInstanceBusinessKeyIn_example"; // String | Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list.
    String processInstanceBusinessKeyLike = "processInstanceBusinessKeyLike_example"; // String | Restrict to tasks that have a process instance business key that has the parameter  value as a substring.
    String processInstanceBusinessKeyLikeExpression = "processInstanceBusinessKeyLikeExpression_example"; // String | Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String processDefinitionId = "processDefinitionId_example"; // String | Restrict to tasks that belong to a process definition with the given id.
    String processDefinitionKey = "processDefinitionKey_example"; // String | Restrict to tasks that belong to a process definition with the given key.
    String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list.
    String processDefinitionName = "processDefinitionName_example"; // String | Restrict to tasks that belong to a process definition with the given name.
    String processDefinitionNameLike = "processDefinitionNameLike_example"; // String | Restrict to tasks that have a process definition name that has the parameter value as  a substring.
    String executionId = "executionId_example"; // String | Restrict to tasks that belong to an execution with the given id.
    String caseInstanceId = "caseInstanceId_example"; // String | Restrict to tasks that belong to case instances with the given id.
    String caseInstanceBusinessKey = "caseInstanceBusinessKey_example"; // String | Restrict to tasks that belong to case instances with the given business key.
    String caseInstanceBusinessKeyLike = "caseInstanceBusinessKeyLike_example"; // String | Restrict to tasks that have a case instance business key that has the parameter value  as a substring.
    String caseDefinitionId = "caseDefinitionId_example"; // String | Restrict to tasks that belong to a case definition with the given id.
    String caseDefinitionKey = "caseDefinitionKey_example"; // String | Restrict to tasks that belong to a case definition with the given key.
    String caseDefinitionName = "caseDefinitionName_example"; // String | Restrict to tasks that belong to a case definition with the given name.
    String caseDefinitionNameLike = "caseDefinitionNameLike_example"; // String | Restrict to tasks that have a case definition name that has the parameter value as a  substring.
    String caseExecutionId = "caseExecutionId_example"; // String | Restrict to tasks that belong to a case execution with the given id.
    String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include tasks which belong to one of the passed and comma-separated activity  instance ids.
    String tenantIdIn = "tenantIdIn_example"; // String | Only include tasks which belong to one of the passed and comma-separated  tenant ids.
    Boolean withoutTenantId = false; // Boolean | Only include tasks which belong to no tenant. Value may only be `true`,  as `false` is the default behavior.
    String assignee = "assignee_example"; // String | Restrict to tasks that the given user is assigned to.
    String assigneeExpression = "assigneeExpression_example"; // String | Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String assigneeLike = "assigneeLike_example"; // String | Restrict to tasks that have an assignee that has the parameter  value as a substring.
    String assigneeLikeExpression = "assigneeLikeExpression_example"; // String | Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String assigneeIn = "assigneeIn_example"; // String | Only include tasks which are assigned to one of the passed and  comma-separated user ids.
    String owner = "owner_example"; // String | Restrict to tasks that the given user owns.
    String ownerExpression = "ownerExpression_example"; // String | Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String candidateGroup = "candidateGroup_example"; // String | Only include tasks that are offered to the given group.
    String candidateGroupExpression = "candidateGroupExpression_example"; // String | Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    String candidateUser = "candidateUser_example"; // String | Only include tasks that are offered to the given user or to one of his groups.
    String candidateUserExpression = "candidateUserExpression_example"; // String | Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions.
    Boolean includeAssignedTasks = false; // Boolean | Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s).
    String involvedUser = "involvedUser_example"; // String | Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee).
    String involvedUserExpression = "involvedUserExpression_example"; // String | Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions.
    Boolean assigned = false; // Boolean | If set to `true`, restricts the query to all tasks that are assigned.
    Boolean unassigned = false; // Boolean | If set to `true`, restricts the query to all tasks that are unassigned.
    String taskDefinitionKey = "taskDefinitionKey_example"; // String | Restrict to tasks that have the given key.
    String taskDefinitionKeyIn = "taskDefinitionKeyIn_example"; // String | Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list.
    String taskDefinitionKeyLike = "taskDefinitionKeyLike_example"; // String | Restrict to tasks that have a key that has the parameter value as a substring.
    String name = "name_example"; // String | Restrict to tasks that have the given name.
    String nameNotEqual = "nameNotEqual_example"; // String | Restrict to tasks that do not have the given name.
    String nameLike = "nameLike_example"; // String | Restrict to tasks that have a name with the given parameter value as substring.
    String nameNotLike = "nameNotLike_example"; // String | Restrict to tasks that do not have a name with the given parameter value as substring.
    String description = "description_example"; // String | Restrict to tasks that have the given description.
    String descriptionLike = "descriptionLike_example"; // String | Restrict to tasks that have a description that has the parameter value as a substring.
    Integer priority = 56; // Integer | Restrict to tasks that have the given priority.
    Integer maxPriority = 56; // Integer | Restrict to tasks that have a lower or equal priority.
    Integer minPriority = 56; // Integer | Restrict to tasks that have a higher or equal priority.
    String dueDate = "dueDate_example"; // String | Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.546+0200`.
    String dueDateExpression = "dueDateExpression_example"; // String | Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String dueAfter = "dueAfter_example"; // String | Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.435+0200`.
    String dueAfterExpression = "dueAfterExpression_example"; // String | Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String dueBefore = "dueBefore_example"; // String | Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.243+0200`.
    String dueBeforeExpression = "dueBeforeExpression_example"; // String | Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpDate = "followUpDate_example"; // String | Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.342+0200`.
    String followUpDateExpression = "followUpDateExpression_example"; // String | Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpAfter = "followUpAfter_example"; // String | Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.542+0200`.
    String followUpAfterExpression = "followUpAfterExpression_example"; // String | Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpBefore = "followUpBefore_example"; // String | Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.234+0200`.
    String followUpBeforeExpression = "followUpBeforeExpression_example"; // String | Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String followUpBeforeOrNotExistent = "followUpBeforeOrNotExistent_example"; // String | Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.432+0200`. The typical use case is to query all `active` tasks for a user for a given date.
    String followUpBeforeOrNotExistentExpression = "followUpBeforeOrNotExistentExpression_example"; // String | Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String createdOn = "createdOn_example"; // String | Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.324+0200`.
    String createdOnExpression = "createdOnExpression_example"; // String | Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String createdAfter = "createdAfter_example"; // String | Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.342+0200`.
    String createdAfterExpression = "createdAfterExpression_example"; // String | Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String createdBefore = "createdBefore_example"; // String | Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.332+0200`.
    String createdBeforeExpression = "createdBeforeExpression_example"; // String | Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a `java.util.Date` or `org.joda.time.DateTime` object.
    String delegationState = "delegationState_example"; // String | Restrict to tasks that are in the given delegation state. Valid values are `PENDING` and `RESOLVED`.
    String candidateGroups = "candidateGroups_example"; // String | Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example `developers,support,sales`.
    String candidateGroupsExpression = "candidateGroupsExpression_example"; // String | Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to `java.util.List` of Strings.
    Boolean withCandidateGroups = false; // Boolean | Only include tasks which have a candidate group. Value may only be `true`, as `false` is the default behavior.
    Boolean withoutCandidateGroups = false; // Boolean | Only include tasks which have no candidate group. Value may only be `true`, as `false` is the default behavior.
    Boolean withCandidateUsers = false; // Boolean | Only include tasks which have a candidate user. Value may only be `true`, as `false` is the default behavior.
    Boolean withoutCandidateUsers = false; // Boolean | Only include tasks which have no candidate users. Value may only be `true`, as `false` is the default behavior.
    Boolean active = false; // Boolean | Only include active tasks. Value may only be `true`, as `false` is the default behavior.
    Boolean suspended = false; // Boolean | Only include suspended tasks. Value may only be `true`, as `false` is the default behavior.
    String taskVariables = "taskVariables_example"; // String | Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    String processVariables = "processVariables_example"; // String | Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    String caseInstanceVariables = "caseInstanceVariables_example"; // String | Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    Boolean variableNamesIgnoreCase = false; // Boolean | Match all variable names in this query case-insensitively. If set `variableName` and `variablename` are treated as equal.
    Boolean variableValuesIgnoreCase = false; // Boolean | Match all variable values in this query case-insensitively. If set `variableValue` and `variablevalue` are treated as equal.
    String parentTaskId = "parentTaskId_example"; // String | Restrict query to all tasks that are sub tasks of the given task. Takes a task id.
    try {
      CountResultDto result = apiInstance.getTasksCount(processInstanceId, processInstanceIdIn, processInstanceBusinessKey, processInstanceBusinessKeyExpression, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, processInstanceBusinessKeyLikeExpression, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, executionId, caseInstanceId, caseInstanceBusinessKey, caseInstanceBusinessKeyLike, caseDefinitionId, caseDefinitionKey, caseDefinitionName, caseDefinitionNameLike, caseExecutionId, activityInstanceIdIn, tenantIdIn, withoutTenantId, assignee, assigneeExpression, assigneeLike, assigneeLikeExpression, assigneeIn, owner, ownerExpression, candidateGroup, candidateGroupExpression, candidateUser, candidateUserExpression, includeAssignedTasks, involvedUser, involvedUserExpression, assigned, unassigned, taskDefinitionKey, taskDefinitionKeyIn, taskDefinitionKeyLike, name, nameNotEqual, nameLike, nameNotLike, description, descriptionLike, priority, maxPriority, minPriority, dueDate, dueDateExpression, dueAfter, dueAfterExpression, dueBefore, dueBeforeExpression, followUpDate, followUpDateExpression, followUpAfter, followUpAfterExpression, followUpBefore, followUpBeforeExpression, followUpBeforeOrNotExistent, followUpBeforeOrNotExistentExpression, createdOn, createdOnExpression, createdAfter, createdAfterExpression, createdBefore, createdBeforeExpression, delegationState, candidateGroups, candidateGroupsExpression, withCandidateGroups, withoutCandidateGroups, withCandidateUsers, withoutCandidateUsers, active, suspended, taskVariables, processVariables, caseInstanceVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, parentTaskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#getTasksCount");
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
 **processInstanceId** | **String**| Restrict to tasks that belong to process instances with the given id. | [optional]
 **processInstanceIdIn** | **String**| Restrict to tasks that belong to process instances with the given ids. | [optional]
 **processInstanceBusinessKey** | **String**| Restrict to tasks that belong to process instances with the given business key. | [optional]
 **processInstanceBusinessKeyExpression** | **String**| Restrict to tasks that belong to process instances with the given business key which  is described by an expression. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. | [optional]
 **processInstanceBusinessKeyIn** | **String**| Restrict to tasks that belong to process instances with one of the give business keys.  The keys need to be in a comma-separated list. | [optional]
 **processInstanceBusinessKeyLike** | **String**| Restrict to tasks that have a process instance business key that has the parameter  value as a substring. | [optional]
 **processInstanceBusinessKeyLikeExpression** | **String**| Restrict to tasks that have a process instance business key that has the parameter  value as a substring and is described by an expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **processDefinitionId** | **String**| Restrict to tasks that belong to a process definition with the given id. | [optional]
 **processDefinitionKey** | **String**| Restrict to tasks that belong to a process definition with the given key. | [optional]
 **processDefinitionKeyIn** | **String**| Restrict to tasks that belong to a process definition with one of the given keys. The  keys need to be in a comma-separated list. | [optional]
 **processDefinitionName** | **String**| Restrict to tasks that belong to a process definition with the given name. | [optional]
 **processDefinitionNameLike** | **String**| Restrict to tasks that have a process definition name that has the parameter value as  a substring. | [optional]
 **executionId** | **String**| Restrict to tasks that belong to an execution with the given id. | [optional]
 **caseInstanceId** | **String**| Restrict to tasks that belong to case instances with the given id. | [optional]
 **caseInstanceBusinessKey** | **String**| Restrict to tasks that belong to case instances with the given business key. | [optional]
 **caseInstanceBusinessKeyLike** | **String**| Restrict to tasks that have a case instance business key that has the parameter value  as a substring. | [optional]
 **caseDefinitionId** | **String**| Restrict to tasks that belong to a case definition with the given id. | [optional]
 **caseDefinitionKey** | **String**| Restrict to tasks that belong to a case definition with the given key. | [optional]
 **caseDefinitionName** | **String**| Restrict to tasks that belong to a case definition with the given name. | [optional]
 **caseDefinitionNameLike** | **String**| Restrict to tasks that have a case definition name that has the parameter value as a  substring. | [optional]
 **caseExecutionId** | **String**| Restrict to tasks that belong to a case execution with the given id. | [optional]
 **activityInstanceIdIn** | **String**| Only include tasks which belong to one of the passed and comma-separated activity  instance ids. | [optional]
 **tenantIdIn** | **String**| Only include tasks which belong to one of the passed and comma-separated  tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include tasks which belong to no tenant. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **assignee** | **String**| Restrict to tasks that the given user is assigned to. | [optional]
 **assigneeExpression** | **String**| Restrict to tasks that the user described by the given expression is assigned to.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **assigneeLike** | **String**| Restrict to tasks that have an assignee that has the parameter  value as a substring. | [optional]
 **assigneeLikeExpression** | **String**| Restrict to tasks that have an assignee that has the parameter value described by the  given expression as a substring. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **assigneeIn** | **String**| Only include tasks which are assigned to one of the passed and  comma-separated user ids. | [optional]
 **owner** | **String**| Restrict to tasks that the given user owns. | [optional]
 **ownerExpression** | **String**| Restrict to tasks that the user described by the given expression owns. See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **candidateGroup** | **String**| Only include tasks that are offered to the given group. | [optional]
 **candidateGroupExpression** | **String**| Only include tasks that are offered to the group described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **candidateUser** | **String**| Only include tasks that are offered to the given user or to one of his groups. | [optional]
 **candidateUserExpression** | **String**| Only include tasks that are offered to the user described by the given expression.  See the  [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions)  for more information on available functions. | [optional]
 **includeAssignedTasks** | **Boolean**| Also include tasks that are assigned to users in candidate queries. Default is to only  include tasks that are not assigned to any user if you query by candidate user or group(s). | [optional] [default to false]
 **involvedUser** | **String**| Only include tasks that the given user is involved in. A user is involved in a task if  an identity link exists between task and user (e.g., the user is the assignee). | [optional]
 **involvedUserExpression** | **String**| Only include tasks that the user described by the given expression is involved in. A user is involved in a task if an identity link exists between task and user (e.g., the user is the assignee). See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. | [optional]
 **assigned** | **Boolean**| If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. | [optional] [default to false]
 **unassigned** | **Boolean**| If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. | [optional] [default to false]
 **taskDefinitionKey** | **String**| Restrict to tasks that have the given key. | [optional]
 **taskDefinitionKeyIn** | **String**| Restrict to tasks that have one of the given keys. The keys need to be in a comma-separated list. | [optional]
 **taskDefinitionKeyLike** | **String**| Restrict to tasks that have a key that has the parameter value as a substring. | [optional]
 **name** | **String**| Restrict to tasks that have the given name. | [optional]
 **nameNotEqual** | **String**| Restrict to tasks that do not have the given name. | [optional]
 **nameLike** | **String**| Restrict to tasks that have a name with the given parameter value as substring. | [optional]
 **nameNotLike** | **String**| Restrict to tasks that do not have a name with the given parameter value as substring. | [optional]
 **description** | **String**| Restrict to tasks that have the given description. | [optional]
 **descriptionLike** | **String**| Restrict to tasks that have a description that has the parameter value as a substring. | [optional]
 **priority** | **Integer**| Restrict to tasks that have the given priority. | [optional]
 **maxPriority** | **Integer**| Restrict to tasks that have a lower or equal priority. | [optional]
 **minPriority** | **Integer**| Restrict to tasks that have a higher or equal priority. | [optional]
 **dueDate** | **String**| Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. | [optional]
 **dueDateExpression** | **String**| Restrict to tasks that are due on the date described by the given expression. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **dueAfter** | **String**| Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.435+0200&#x60;. | [optional]
 **dueAfterExpression** | **String**| Restrict to tasks that are due after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **dueBefore** | **String**| Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.243+0200&#x60;. | [optional]
 **dueBeforeExpression** | **String**| Restrict to tasks that are due before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpDate** | **String**| Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. | [optional]
 **followUpDateExpression** | **String**| Restrict to tasks that have a followUp date on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpAfter** | **String**| Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.542+0200&#x60;. | [optional]
 **followUpAfterExpression** | **String**| Restrict to tasks that have a followUp date after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpBefore** | **String**| Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.234+0200&#x60;. | [optional]
 **followUpBeforeExpression** | **String**| Restrict to tasks that have a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **followUpBeforeOrNotExistent** | **String**| Restrict to tasks that have no followUp date or a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.432+0200&#x60;. The typical use case is to query all &#x60;active&#x60; tasks for a user for a given date. | [optional]
 **followUpBeforeOrNotExistentExpression** | **String**| Restrict to tasks that have no followUp date or a followUp date before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **createdOn** | **String**| Restrict to tasks that were created on the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.324+0200&#x60;. | [optional]
 **createdOnExpression** | **String**| Restrict to tasks that were created on the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **createdAfter** | **String**| Restrict to tasks that were created after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.342+0200&#x60;. | [optional]
 **createdAfterExpression** | **String**| Restrict to tasks that were created after the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **createdBefore** | **String**| Restrict to tasks that were created before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.332+0200&#x60;. | [optional]
 **createdBeforeExpression** | **String**| Restrict to tasks that were created before the date described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to a &#x60;java.util.Date&#x60; or &#x60;org.joda.time.DateTime&#x60; object. | [optional]
 **delegationState** | **String**| Restrict to tasks that are in the given delegation state. Valid values are &#x60;PENDING&#x60; and &#x60;RESOLVED&#x60;. | [optional] [enum: PENDING, RESOLVED]
 **candidateGroups** | **String**| Restrict to tasks that are offered to any of the given candidate groups. Takes a comma-separated list of group names, so for example &#x60;developers,support,sales&#x60;. | [optional]
 **candidateGroupsExpression** | **String**| Restrict to tasks that are offered to any of the candidate groups described by the given expression. See the [user guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/expression-language/#internal-context-functions) for more information on available functions. The expression must evaluate to &#x60;java.util.List&#x60; of Strings. | [optional]
 **withCandidateGroups** | **Boolean**| Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **withoutCandidateGroups** | **Boolean**| Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **withCandidateUsers** | **Boolean**| Only include tasks which have a candidate user. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **withoutCandidateUsers** | **Boolean**| Only include tasks which have no candidate users. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **active** | **Boolean**| Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **suspended** | **Boolean**| Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **taskVariables** | **String**| Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **processVariables** | **String**| Only include tasks that belong to process instances that have variables with certain  values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **caseInstanceVariables** | **String**| Only include tasks that belong to case instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names in this query case-insensitively. If set &#x60;variableName&#x60; and &#x60;variablename&#x60; are treated as equal. | [optional] [default to false]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values in this query case-insensitively. If set &#x60;variableValue&#x60; and &#x60;variablevalue&#x60; are treated as equal. | [optional] [default to false]
 **parentTaskId** | **String**| Restrict query to all tasks that are sub tasks of the given task. Takes a task id. | [optional]

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
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="handleBpmnError"></a>
# **handleBpmnError**
> handleBpmnError(id, taskBpmnErrorDto)



Reports a business error in the context of a running task by id. The error code must be specified to identify the BPMN error handler. See the documentation for [Reporting Bpmn Error](https://docs.camunda.org/manual/7.13/reference/bpmn20/tasks/user-task/#reporting-bpmn-error) in User Tasks.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task a BPMN error is reported for.
    TaskBpmnErrorDto taskBpmnErrorDto = {"errorCode":"bpmn-error-543","errorMessage":"anErrorMessage","variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}}}; // TaskBpmnErrorDto | 
    try {
      apiInstance.handleBpmnError(id, taskBpmnErrorDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#handleBpmnError");
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
 **id** | **String**| The id of the task a BPMN error is reported for. |
 **taskBpmnErrorDto** | [**TaskBpmnErrorDto**](TaskBpmnErrorDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**403** | If the authenticated user is unauthorized to update the process instance. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist or &lt;code&gt;errorCode&lt;/code&gt; is not presented in the request. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="handleEscalation"></a>
# **handleEscalation**
> handleEscalation(id, taskEscalationDto)



Reports an escalation in the context of a running task by id. The escalation code must be specified to identify the escalation handler. See the documentation for [Reporting Bpmn Escalation](https://docs.camunda.org/manual/7.13/reference/bpmn20/tasks/user-task/#reporting-bpmn-escalation) in User Tasks.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task in which context a BPMN escalation is reported.
    TaskEscalationDto taskEscalationDto = {"escalationCode":"bpmn-escalation-432","variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}}}; // TaskEscalationDto | 
    try {
      apiInstance.handleEscalation(id, taskEscalationDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#handleEscalation");
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
 **id** | **String**| The id of the task in which context a BPMN escalation is reported. |
 **taskEscalationDto** | [**TaskEscalationDto**](TaskEscalationDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**403** | If the authenticated user is unauthorized to update the process instance. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist or &lt;code&gt;errorCode&lt;/code&gt; is not presented in the request. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="queryTasks"></a>
# **queryTasks**
> List&lt;TaskDto&gt; queryTasks(firstResult, maxResults, taskQueryDto)



Queries for tasks that fulfill a given filter. This method is slightly more powerful than the [Get Tasks](https://docs.camunda.org/manual/7.13/reference/rest/task/get-query/) method because it allows filtering by multiple process or task variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;. The size of the result set can be retrieved by using the [Get Task Count (POST)](https://docs.camunda.org/manual/7.13/reference/rest/task/post-query-count/) method.  **Security Consideration**: There are several parameters (such as &#x60;assigneeExpression&#x60;) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.13/user-guide/process-engine/securing-custom-code/) in the user guide for details.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    TaskQueryDto taskQueryDto = {"taskVariables":[{"name":"varName","value":"varValue","operator":"eq"},{"name":"anotherVarName","value":30,"operator":"neq"}],"processInstanceBusinessKeyIn":"aBusinessKey,anotherBusinessKey","assigneeIn":"anAssignee,anotherAssignee","priority":10,"sorting":[{"sortBy":"dueDate","sortOrder":"asc"},{"sortBy":"processVariable","sortOrder":"desc","parameters":{"variable":"orderId","type":"String"}}]}; // TaskQueryDto | 
    try {
      List<TaskDto> result = apiInstance.queryTasks(firstResult, maxResults, taskQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#queryTasks");
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
 **taskQueryDto** | [**TaskQueryDto**](TaskQueryDto.md)|  | [optional]

### Return type

[**List&lt;TaskDto&gt;**](TaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="queryTasksCount"></a>
# **queryTasksCount**
> CountResultDto queryTasksCount(taskQueryDto)



Retrieves the number of tasks that fulfill the given filter. Corresponds to the size of the result set of the [Get Tasks (POST)](https://docs.camunda.org/manual/7.13/reference/rest/task/post-query/) method and takes the same parameters.  **Security Consideration**: There are several parameters (such as &#x60;assigneeExpression&#x60;) for specifying an EL expression. These are disabled by default to prevent remote code execution. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.13/user-guide/process-engine/securing-custom-code/) in the user guide for details.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    TaskQueryDto taskQueryDto = {"taskVariables":[{"name":"varName","value":"varValue","operator":"eq"},{"name":"anotherVarName","value":30,"operator":"neq"}],"processInstanceBusinessKeyIn":"aBusinessKey,anotherBusinessKey","assigneeIn":"anAssignee,anotherAssignee","priority":10,"sorting":[{"sortBy":"dueDate","sortOrder":"asc"},{"sortBy":"processVariable","sortOrder":"desc","parameters":{"variable":"orderId","type":"String"}}]}; // TaskQueryDto | 
    try {
      CountResultDto result = apiInstance.queryTasksCount(taskQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#queryTasksCount");
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
 **taskQueryDto** | [**TaskQueryDto**](TaskQueryDto.md)|  | [optional]

### Return type

[**CountResultDto**](CountResultDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="resolve"></a>
# **resolve**
> resolve(id, completeTaskDto)



Resolves a task and updates execution variables.  Resolving a task marks that the assignee is done with the task delegated to them, and that it can be sent back to the owner. Can only be executed when the task has been delegated. The assignee will be set to the owner, who performed the delegation.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to resolve.
    CompleteTaskDto completeTaskDto = {"variables":{"aVariable":{"value":"aStringValue"},"anotherVariable":{"value":42},"aThirdVariable":{"value":true}}}; // CompleteTaskDto | 
    try {
      apiInstance.resolve(id, completeTaskDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#resolve");
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
 **id** | **String**| The id of the task to resolve. |
 **completeTaskDto** | [**CompleteTaskDto**](CompleteTaskDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | If the task does not exist or the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="setAssignee"></a>
# **setAssignee**
> setAssignee(id, userIdDto)



Changes the assignee of a task to a specific user.  **Note:** The difference with the [Claim Task](https://docs.camunda.org/manual/7.13/reference/rest/task/post-claim/) method is that this method does not check if the task already has a user assigned to it.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to set the assignee for.
    UserIdDto userIdDto = {"userId":"aUserId"}; // UserIdDto | Provide the id of the user that will be the assignee of the task.
    try {
      apiInstance.setAssignee(id, userIdDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#setAssignee");
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
 **id** | **String**| The id of the task to set the assignee for. |
 **userIdDto** | [**UserIdDto**](UserIdDto.md)| Provide the id of the user that will be the assignee of the task. | [optional]

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
**204** | Request successful. |  -  |
**500** | Task with given id does not exist or setting the assignee was not successful. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="submit"></a>
# **submit**
> Map&lt;String, VariableValueDto&gt; submit(id, completeTaskDto)



Completes a task and updates process variables using a form submit. There are two difference between this method and the &#x60;complete&#x60; method:  * If the task is in state &#x60;PENDING&#x60; - i.e., has been delegated before, it is not completed but resolved. Otherwise it will be completed. * If the task has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See the [Generated Task Forms](https://docs.camunda.org/manual/7.13/user-guide/task-forms/_index/#generated-task-forms) section of the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/) for more information.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to submit the form for.
    CompleteTaskDto completeTaskDto = {"variables":{"aVariable":{"value":"aStringValue"},"anotherVariable":{"value":42},"aThirdVariable":{"value":true},"aFileVariable":{"value":"TG9yZW0gaXBzdW0=","type":"File","valueInfo":{"filename":"myFile.txt"}}}}; // CompleteTaskDto | 
    try {
      Map<String, VariableValueDto> result = apiInstance.submit(id, completeTaskDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#submit");
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
 **id** | **String**| The id of the task to submit the form for. |
 **completeTaskDto** | [**CompleteTaskDto**](CompleteTaskDto.md)|  | [optional]

### Return type

[**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. The response contains the process variables. |  -  |
**204** | Request successful. The response contains no variables. |  -  |
**400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | If the task does not exist or the corresponding process instance could not be resumed successfully.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="unclaim"></a>
# **unclaim**
> unclaim(id)



Resets a task&#39;s assignee. If successful, the task is not assigned to a user.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to unclaim.
    try {
      apiInstance.unclaim(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#unclaim");
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
 **id** | **String**| The id of the task to unclaim. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Request successful. |  -  |
**500** | The Task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateTask"></a>
# **updateTask**
> updateTask(id, taskDto)



Updates a task.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskApi apiInstance = new TaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to be updated.
    TaskDto taskDto = {"name":"My Task","description":"This have to be done very urgent","priority":30,"assignee":"peter","owner":"mary","delegationState":"PENDING","due":"2014-08-30T10:00:00.000+0200","followUp":"2014-08-25T10:00:00.000+0200","parentTaskId":"aParentTaskId","caseInstanceId":"aCaseInstanceId","tenantId":"tenantId"}; // TaskDto | 
    try {
      apiInstance.updateTask(id, taskDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskApi#updateTask");
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
 **id** | **String**| The id of the task to be updated. |
 **taskDto** | [**TaskDto**](TaskDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Returned if a not valid &#x60;delegationState&#x60; is supplied. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | If the corresponding task cannot be found. |  -  |

