

# DurationReportResultDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**period** | **Integer** | Specifies a timespan within a year. **Note:** The period must be interpreted in conjunction with the returned &#x60;periodUnit&#x60;. |  [optional]
**periodUnit** | [**PeriodUnitEnum**](#PeriodUnitEnum) | The unit of the given period. Possible values are &#x60;MONTH&#x60; and &#x60;QUARTER&#x60;. |  [optional]
**minimum** | **Long** | The smallest duration in milliseconds of all completed process instances which were started in the given period. |  [optional]
**maximum** | **Long** | The greatest duration in milliseconds of all completed process instances which were started in the given period. |  [optional]
**average** | **Long** | The average duration in milliseconds of all completed process instances which were started in the given period. |  [optional]



## Enum: PeriodUnitEnum

Name | Value
---- | -----
MONTH | &quot;MONTH&quot;
QUARTER | &quot;QUARTER&quot;



