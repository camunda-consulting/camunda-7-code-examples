

# HistoricActivityStatisticsDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the activity the results are aggregated for. |  [optional]
**instances** | **Long** | The total number of all running instances of the activity. |  [optional]
**canceled** | **Long** | The total number of all canceled instances of the activity. **Note:** Will be &#x60;0&#x60; (not &#x60;null&#x60;), if canceled activity instances were excluded. |  [optional]
**finished** | **Long** | The total number of all finished instances of the activity. **Note:** Will be &#x60;0&#x60; (not &#x60;null&#x60;), if finished activity instances were excluded. |  [optional]
**completeScope** | **Long** | The total number of all instances which completed a scope of the activity. **Note:** Will be &#x60;0&#x60; (not &#x60;null&#x60;), if activity instances which completed a scope were excluded. |  [optional]
**openIncidents** | **Long** | The total number of open incidents for the activity. **Note:** Will be &#x60;0&#x60; (not &#x60;null&#x60;), if &#x60;incidents&#x60; is set to &#x60;false&#x60;. |  [optional]
**resolvedIncidents** | **Long** | The total number of resolved incidents for the activity. **Note:** Will be &#x60;0&#x60; (not &#x60;null&#x60;), if &#x60;incidents&#x60; is set to &#x60;false&#x60;. |  [optional]
**deletedIncidents** | **Long** | The total number of deleted incidents for the activity. **Note:** Will be &#x60;0&#x60; (not &#x60;null&#x60;), if &#x60;incidents&#x60; is set to &#x60;false&#x60;. |  [optional]



