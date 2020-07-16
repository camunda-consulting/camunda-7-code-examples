

# RestartProcessInstanceModificationInstructionDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | **Mandatory**. One of the following values: &#x60;startBeforeActivity&#x60;, &#x60;startAfterActivity&#x60;, &#x60;startTransition&#x60;.  * A &#x60;startBeforeActivity&#x60; instruction requests to enter a given activity. * A &#x60;startAfterActivity&#x60; instruction requests to execute the single outgoing sequence flow of a given activity. * A &#x60;startTransition&#x60; instruction requests to execute a specific sequence flow. | 
**activityId** | **String** | **Can be used with instructions of types** &#x60;startBeforeActivity&#x60; and &#x60;startAfterActivity&#x60;. Specifies the sequence flow to start. |  [optional]
**transitionId** | **String** | **Can be used with instructions of types** &#x60;startTransition&#x60;. Specifies the sequence flow to start. |  [optional]



## Enum: TypeEnum

Name | Value
---- | -----
STARTBEFOREACTIVITY | &quot;startBeforeActivity&quot;
STARTAFTERACTIVITY | &quot;startAfterActivity&quot;
STARTTRANSITION | &quot;startTransition&quot;



