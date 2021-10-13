

# HistoricTaskInstanceReportResultDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**taskName** | **String** | The name of the task. It is only available when the &#x60;groupBy&#x60; parameter is set to &#x60;taskName&#x60;. Else the value is &#x60;null&#x60;.  **Note:** This property is only set for a historic task report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;count&#x60;. |  [optional]
**count** | **Long** | The number of tasks which have the given definition.  **Note:** This property is only set for a historic task report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;count&#x60;. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition.  **Note:** This property is only set for a historic task report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;count&#x60;. |  [optional]
**processDefinitionId** | **String** | The id of the process definition.  **Note:** This property is only set for a historic task report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;count&#x60;. |  [optional]
**processDefinitionName** | **String** | The name of the process definition.  **Note:** This property is only set for a historic task report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;count&#x60;. |  [optional]
**period** | **Integer** | Specifies a span of time within a year. **Note:** The period must be interpreted in conjunction with the returned &#x60;periodUnit&#x60;.  **Note:** This property is only set for a duration report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;duration&#x60;. |  [optional]
**periodUnit** | [**PeriodUnitEnum**](#PeriodUnitEnum) | The unit of the given period. Possible values are &#x60;MONTH&#x60; and &#x60;QUARTER&#x60;.  **Note:** This property is only set for a duration report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;duration&#x60;. |  [optional]
**minimum** | **Long** | The smallest duration in milliseconds of all completed process instances which were started in the given period.  **Note:** This property is only set for a duration report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;duration&#x60;. |  [optional]
**maximum** | **Long** | The greatest duration in milliseconds of all completed process instances which were started in the given period.  **Note:** This property is only set for a duration report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;duration&#x60;. |  [optional]
**average** | **Long** | The average duration in milliseconds of all completed process instances which were started in the given period.  **Note:** This property is only set for a duration report object. In these cases, the value of the &#x60;reportType&#x60; query parameter is &#x60;duration&#x60;. |  [optional]



## Enum: PeriodUnitEnum

Name | Value
---- | -----
MONTH | &quot;MONTH&quot;
QUARTER | &quot;QUARTER&quot;



