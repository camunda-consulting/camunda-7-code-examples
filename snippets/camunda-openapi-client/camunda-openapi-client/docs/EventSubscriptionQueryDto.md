

# EventSubscriptionQueryDto

A event subscription query which retrieves a list of event subscriptions
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**eventSubscriptionId** | **String** | The id of the event subscription. |  [optional]
**eventName** | **String** | The name of the event this subscription belongs to as defined in the process model. |  [optional]
**eventType** | [**EventTypeEnum**](#EventTypeEnum) | The type of the event subscription. |  [optional]
**executionId** | **String** | The execution that is subscribed on the referenced event. |  [optional]
**processInstanceId** | **String** | The process instance this subscription belongs to. |  [optional]
**activityId** | **String** | The identifier of the activity that this event subscription belongs to. This could for example be the id of a receive task. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**includeEventSubscriptionsWithoutTenantId** | **Boolean** | Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;EventSubscriptionQueryDtoSorting&gt;**](EventSubscriptionQueryDtoSorting.md) | Apply sorting of the result |  [optional]



## Enum: EventTypeEnum

Name | Value
---- | -----
MESSAGE | &quot;message&quot;
SIGNAL | &quot;signal&quot;
COMPENSATE | &quot;compensate&quot;
CONDITIONAL | &quot;conditional&quot;



