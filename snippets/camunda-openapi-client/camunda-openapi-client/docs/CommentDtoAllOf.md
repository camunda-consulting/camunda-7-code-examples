

# CommentDtoAllOf

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the task comment. |  [optional]
**userId** | **String** | The id of the user who created the comment. |  [optional]
**taskId** | **String** | The id of the task to which the comment belongs. |  [optional]
**time** | [**OffsetDateTime**](OffsetDateTime.md) | The time when the comment was created. [Default format]($(docsUrl)/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**message** | **String** | The content of the comment. |  [optional]
**removalTime** | [**OffsetDateTime**](OffsetDateTime.md) | The time after which the comment should be removed by the History Cleanup job. [Default format]($(docsUrl)/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing the task. |  [optional]



