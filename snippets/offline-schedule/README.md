# Offline Schedule for Job Retry

This project shows an example implementation of how a Job Retry can be scheduled according to breaks where no job should be executed.

## Prerequisities

This only works on jobs that have a defined retry time cycle. Always consider setting one or add another plugin that does this for you.

## Configuration

By Example: Have a look at `application.yml` inside `src/main/resources`, then find the according parser as `com.camunda.consulting.JobExecutorBreakParser`. It can be configured for all days (just leave out the day name) or a specific day. By adding multiple rows, you can create a rather complex break schedule.

## How it works

When a job fails, the `com.camunda.consulting.ScheduledJobRetryCmd` is gathered from the `com.camunda.consulting.ScheduledFailedJobCommandFactory` which is registered in the process engine configuration in the `com.camunda.consulting.OfflineSchedulePlugin`. 
There the method `com.camunda.consulting.ScheduledJobRetryCmd.getDurationHelper(String)` is overridden causing the returned `org.camunda.bpm.engine.impl.calendar.DurationHelper` to use a fixed `java.util.Date` to calculate the jobs' next dueDate. This Date will not be during a break.