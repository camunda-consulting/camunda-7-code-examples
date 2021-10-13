

# LockExternalTaskDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lockDuration** | **Long** | The duration to lock the external task for in milliseconds. **Note:** Attempting to lock an already locked external task with the same &#x60;workerId&#x60; will succeed and a new lock duration will be set, starting from the current moment. |  [optional]
**workerId** | **String** | **Mandatory.** The ID of the worker who is performing the operation on the external task. If the task is already locked, must match the id of the worker who has most recently locked the task. |  [optional]



