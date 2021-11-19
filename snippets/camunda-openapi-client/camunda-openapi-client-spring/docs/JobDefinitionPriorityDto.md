

# JobDefinitionPriorityDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**priority** | **Long** | The new execution priority number for jobs of the given definition. The definition&#39;s priority can be reset by using the value &#x60;null&#x60;. In that case, the job definition&#39;s priority no longer applies but a new job&#39;s priority is determined as specified in the process model. |  [optional]
**includeJobs** | **Boolean** | A boolean value indicating whether existing jobs of the given definition should receive the priority as well. Default value is &#x60;false&#x60;. Can only be &#x60;true&#x60; when the __priority__ parameter is not &#x60;null&#x60;. |  [optional]



