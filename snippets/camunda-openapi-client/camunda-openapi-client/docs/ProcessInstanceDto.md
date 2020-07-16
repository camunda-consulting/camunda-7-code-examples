

# ProcessInstanceDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the process instance. |  [optional]
**definitionId** | **String** | The id of the process definition that this process instance belongs to. |  [optional]
**businessKey** | **String** | The business key of the process instance. |  [optional]
**caseInstanceId** | **String** | The id of the case instance associated with the process instance. |  [optional]
**ended** | **Boolean** | A flag indicating whether the process instance has ended or not. Deprecated: will always be false! |  [optional]
**suspended** | **Boolean** | A flag indicating whether the process instance is suspended or not. |  [optional]
**tenantId** | **String** | The tenant id of the process instance. |  [optional]
**links** | [**List&lt;AtomLink&gt;**](AtomLink.md) | The links associated to this resource, with &#x60;method&#x60;, &#x60;href&#x60; and &#x60;rel&#x60;. |  [optional]



