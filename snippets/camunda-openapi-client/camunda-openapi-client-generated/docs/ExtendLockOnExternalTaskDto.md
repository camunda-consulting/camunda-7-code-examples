

# ExtendLockOnExternalTaskDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**newDuration** | **Long** | An amount of time (in milliseconds). This is the new lock duration starting from the current moment. |  [optional]
**workerId** | **String** | **Mandatory.** The ID of the worker who is performing the operation on the external task. If the task is already locked, must match the id of the worker who has most recently locked the task. |  [optional]



