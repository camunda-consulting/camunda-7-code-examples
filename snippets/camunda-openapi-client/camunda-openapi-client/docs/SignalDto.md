

# SignalDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **String** | The name of the signal to deliver.  **Note**: This property is mandatory. |  [optional]
**executionId** | **String** | Optionally specifies a single execution which is notified by the signal.  **Note**: If no execution id is defined the signal is broadcasted to all subscribed handlers.  |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing variable key-value pairs. Each key is a variable name and each value a JSON variable value object. |  [optional]
**tenantId** | **String** | Specifies a tenant to deliver the signal. The signal can only be received on executions or process definitions which belongs to the given tenant.  **Note**: Cannot be used in combination with executionId. |  [optional]
**withoutTenantId** | **Boolean** | If true the signal can only be received on executions or process definitions which belongs to no tenant. Value may not be false as this is the default behavior.  **Note**: Cannot be used in combination with &#x60;executionId&#x60;. |  [optional]



