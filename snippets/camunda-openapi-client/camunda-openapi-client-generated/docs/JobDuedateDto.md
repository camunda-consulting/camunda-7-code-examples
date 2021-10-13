

# JobDuedateDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**duedate** | **OffsetDateTime** | The date to set when the job has the next execution. |  [optional]
**cascade** | **Boolean** | A boolean value to indicate if modifications to the due date should cascade to subsequent jobs. (e.g. Modify the due date of a timer by +15 minutes. This flag indicates if a +15 minutes should be applied to all subsequent timers.) This flag only affects timer jobs and only works if due date is not null. Default: &#x60;false&#x60; |  [optional]



