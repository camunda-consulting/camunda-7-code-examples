# SQL Queries for Monitoring Camunda BPM

```sql
--  running process instances
select count(*) from camunda.act_ru_execution
where parent_id_ is null;

-- finished process instances
select count(*) from camunda.act_hi_procinst where end_time_ is not null;

-- flow nodes count
select count(*) from camunda.act_hi_actinst where end_time_ is not null;

-- failed jobs count
select count(*) from camunda.act_ru_job where retries_ = 0;

-- number incidents with particular error type in running processes
select count(*) from camunda.act_ru_incident where lower(incident_msg_) like '%deadlock%';

-- number incidents with particular error type in history
select count(*) from camunda.act_hi_incident where lower(incident_msg_) like '%deadlock%';

```
