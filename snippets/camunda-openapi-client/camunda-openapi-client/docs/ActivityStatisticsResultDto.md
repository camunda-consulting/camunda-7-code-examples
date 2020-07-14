

# ActivityStatisticsResultDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the activity the results are aggregated for. |  [optional]
**instances** | **Integer** | The total number of running process instances of this activity. |  [optional]
**failedJobs** | **Integer** | The total number of failed jobs for the running instances. **Note**: Will be &#x60;0&#x60; (not &#x60;null&#x60;), if failed jobs were excluded. |  [optional]
**incidents** | [**List&lt;IncidentStatisticsResultDto&gt;**](IncidentStatisticsResultDto.md) | Each item in the resulting array is an object which contains &#x60;incidentType&#x60; and &#x60;incidentCount&#x60;. **Note**: Will be an empty array, if &#x60;incidents&#x60; or &#x60;incidentsForType&#x60; were excluded. Furthermore, the array will be also empty if no incidents were found. |  [optional]



