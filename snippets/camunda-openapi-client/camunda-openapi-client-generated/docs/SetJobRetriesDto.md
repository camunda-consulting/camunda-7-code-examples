

# SetJobRetriesDto

Defines the number of retries for a selection of jobs. Please note that if both jobIds and jobQuery are provided, then retries will be set on the union of these sets.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**jobIds** | **List&lt;String&gt;** | A list of job ids to set retries for. |  [optional]
**jobQuery** | [**JobQueryDto**](JobQueryDto.md) |  |  [optional]
**retries** | **Integer** | An integer representing the number of retries. Please note that the value cannot be negative or null. |  [optional]



