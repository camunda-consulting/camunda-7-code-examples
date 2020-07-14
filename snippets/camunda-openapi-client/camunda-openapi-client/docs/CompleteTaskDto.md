

# CompleteTaskDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing variable key-value pairs. |  [optional]
**withVariablesInReturn** | **Boolean** | Indicates whether the response should contain the process variables or not. The default is &#x60;false&#x60; with a response code of &#x60;204&#x60;. If set to &#x60;true&#x60; the response contains the process variables and has a response code of &#x60;200&#x60;. If the task is not associated with a process instance (e.g. if it&#39;s part of a case instance) no variables will be returned. |  [optional]



