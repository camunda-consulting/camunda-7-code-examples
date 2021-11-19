

# MultipleProcessInstanceModificationInstructionDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | **Mandatory**. One of the following values: &#x60;cancel&#x60;, &#x60;startBeforeActivity&#x60;, &#x60;startAfterActivity&#x60;, &#x60;startTransition&#x60;.  * A cancel instruction requests cancellation of a single activity instance or all instances of one activity. * A startBeforeActivity instruction requests to enter a given activity. * A startAfterActivity instruction requests to execute the single outgoing sequence flow of a given activity. * A startTransition instruction requests to execute a specific sequence flow. | 
**activityId** | **String** | Can be used with instructions of types &#x60;startTransition&#x60;. Specifies the sequence flow to start. |  [optional]
**transitionId** | **String** | Can be used with instructions of types &#x60;startTransition&#x60;. Specifies the sequence flow to start. |  [optional]
**cancelCurrentActiveActivityInstances** | **Boolean** | Can be used with instructions of type cancel. Prevents the deletion of new created activity instances. |  [optional]



## Enum: TypeEnum

Name | Value
---- | -----
CANCEL | &quot;cancel&quot;
STARTBEFOREACTIVITY | &quot;startBeforeActivity&quot;
STARTAFTERACTIVITY | &quot;startAfterActivity&quot;
STARTTRANSITION | &quot;startTransition&quot;



