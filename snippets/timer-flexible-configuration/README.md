# Info

This sample shows how variable-based timer configurations can be used in a flexible way. It uses a list of durations and the API for recalculating job due dates that was introduced with Camunda 7.11.

Key components are the expressions in the timer configuration itself and the listener at the start of the reminder task, which triggers the recalculation of the job.
