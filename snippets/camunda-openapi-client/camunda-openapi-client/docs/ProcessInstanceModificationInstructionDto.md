

# ProcessInstanceModificationInstructionDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | **Mandatory**. One of the following values: &#x60;cancel&#x60;, &#x60;startBeforeActivity&#x60;, &#x60;startAfterActivity&#x60;, &#x60;startTransition&#x60;.  * A cancel instruction requests cancellation of a single activity instance or all instances of one activity. * A startBeforeActivity instruction requests to enter a given activity. * A startAfterActivity instruction requests to execute the single outgoing sequence flow of a given activity. * A startTransition instruction requests to execute a specific sequence flow. | 
**variables** | [**TriggerVariableValueDto**](TriggerVariableValueDto.md) |  |  [optional]
**activityId** | **String** | Can be used with instructions of types &#x60;startTransition&#x60;. Specifies the sequence flow to start. |  [optional]
**transitionId** | **String** | Can be used with instructions of types &#x60;startTransition&#x60;. Specifies the sequence flow to start. |  [optional]
**activityInstanceId** | **String** | Can be used with instructions of type &#x60;cancel&#x60;. Specifies the activity instance to cancel. Valid values are the activity instance IDs supplied by the [Get Activity Instance request](https://docs.camunda.org/manual/7.13/reference/rest/process-instance/get-activity-instances/). |  [optional]
**transitionInstanceId** | **String** | Can be used with instructions of type &#x60;cancel&#x60;. Specifies the transition instance to cancel. Valid values are the transition instance IDs supplied by the [Get Activity Instance request](https://docs.camunda.org/manual/7.13/reference/rest/process-instance/get-activity-instances/). |  [optional]
**ancestorActivityInstanceId** | **String** | Can be used with instructions of type &#x60;startBeforeActivity&#x60;, &#x60;startAfterActivity&#x60;, and &#x60;startTransition&#x60;. Valid values are the activity instance IDs supplied by the Get Activity Instance request. If there are multiple parent activity instances of the targeted activity, this specifies the ancestor scope in which hierarchy the activity/transition is to be instantiated.  Example: When there are two instances of a subprocess and an activity contained in the subprocess is to be started, this parameter allows to specifiy under which subprocess instance the activity should be started. |  [optional]
**cancelCurrentActiveActivityInstances** | **Boolean** | Can be used with instructions of type cancel. Prevents the deletion of new created activity instances. |  [optional]



## Enum: TypeEnum

Name | Value
---- | -----
CANCEL | &quot;cancel&quot;
STARTBEFOREACTIVITY | &quot;startBeforeActivity&quot;
STARTAFTERACTIVITY | &quot;startAfterActivity&quot;
STARTTRANSITION | &quot;startTransition&quot;



