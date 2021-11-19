

# ExternalTaskFailureDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**errorMessage** | **String** | An message indicating the reason of the failure. |  [optional]
**errorDetails** | **String** | A detailed error description. |  [optional]
**retries** | **Integer** | A number of how often the task should be retried. Must be &gt;&#x3D; 0. If this is 0, an incident is created and the task cannot be fetched anymore unless the retries are increased again. The incident&#39;s message is set to the &#x60;errorMessage&#x60; parameter. |  [optional]
**retryTimeout** | **Long** | A timeout in milliseconds before the external task becomes available again for fetching. Must be &gt;&#x3D; 0. |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing variable key-value pairs. Each key is a variable name and each value a JSON variable value object with the following properties: |  [optional]
**localVariables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing local variable key-value pairs. Local variables are set only in the scope of external task. Each key is a variable name and each value a JSON variable value object with the following properties: |  [optional]
**workerId** | **String** | **Mandatory.** The ID of the worker who is performing the operation on the external task. If the task is already locked, must match the id of the worker who has most recently locked the task. |  [optional]



