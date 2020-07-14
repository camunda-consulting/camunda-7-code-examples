

# ProcessDefinitionSuspensionStateDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**suspended** | **Boolean** | A &#x60;Boolean&#x60; value which indicates whether to activate or suspend all process definitions with the given key. When the value is set to &#x60;true&#x60;, all process definitions with the given key will be suspended and when the value is set to &#x60;false&#x60;, all process definitions with the given key will be activated. |  [optional]
**processDefinitionId** | **String** | The id of the process definitions to activate or suspend. |  [optional]
**processDefinitionKey** | **String** |  The key of the process definitions to activate or suspend. |  [optional]
**includeProcessInstances** | **Boolean** | A &#x60;Boolean&#x60; value which indicates whether to activate or suspend also all process instances of  the process definitions with the given key. When the value is set to &#x60;true&#x60;, all process instances of the process definitions with the given key will be activated or suspended and when the value is set to &#x60;false&#x60;, the suspension state of  all process instances of the process definitions with the given key will not be updated. |  [optional]
**executionDate** | [**OffsetDateTime**](OffsetDateTime.md) | The date on which all process definitions with the given key will be activated or suspended. If &#x60;null&#x60;, the suspension state of all process definitions with the given key is updated immediately. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]



