

# AttachmentDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the task attachment. |  [optional]
**name** | **String** | The name of the task attachment. |  [optional]
**description** | **String** | The description of the task attachment. |  [optional]
**taskId** | **String** | The id of the task to which the attachment belongs. |  [optional]
**type** | **String** | Indication of the type of content that this attachment refers to. Can be MIME type or any other indication. |  [optional]
**url** | **String** | The url to the remote content of the task attachment. |  [optional]
**createTime** | [**OffsetDateTime**](OffsetDateTime.md) | The time the variable was inserted. [Default format](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**removalTime** | [**OffsetDateTime**](OffsetDateTime.md) | The time after which the attachment should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing the task. |  [optional]
**links** | [**List&lt;AtomLink&gt;**](AtomLink.md) | The links associated to this resource, with &#x60;method&#x60;, &#x60;href&#x60; and &#x60;rel&#x60;. |  [optional]



