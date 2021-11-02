# Offline Schedule for Job Retry

This project shows 3 example implementations of how Job Scheduling can be done according to breaks where no job should be executed.

## Configuration

By Example: Have a look at `application.yml` inside `src/main/resources`, then find the according parser as `com.camunda.consulting.JobExecutorBreakParser`. It can be configured for all days (just leave out the day name) or a specific day. By adding multiple rows, you can create a rather complex break schedule.
