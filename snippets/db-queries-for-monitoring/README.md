# SQL Queries for Monitoring Camunda BPM

Put the following queries in your favorite monitoring tool, e.g. Nagios,
and [track them over time](http://docs.pnp4nagios.org/pnp-0.6/gallery/start)
to monitor [Camunda BPM](http://camunda.org) engine performance.

```sql
--  running process instances
select count(*) from act_ru_execution
where parent_id_ is null;

-- finished process instances
select count(*) from act_hi_procinst where end_time_ is not null;

-- flow nodes count
select count(*) from act_hi_actinst where end_time_ is not null;

-- number of Jobs that are currently being processed,
-- i.e. are acquired by a Job Executor
select coun(*) from act_ru_job res
where (RES.LOCK_OWNER_ is not null);

-- number of Jobs that are due to be processed,
-- but not yet aquired by a Job Executor
select count(*) from act_ru_job res
where (RES.RETRIES_ > 0)
and (RES.DUEDATE_ is null or RES.DUEDATE_ < sysdate)
and (RES.LOCK_OWNER_ is null or RES.LOCK_EXP_TIME_ < sysdate)
and (RES.SUSPENSION_STATE_ = 1 or RES.SUSPENSION_STATE_ is null);

-- number of Jobs that are failed,
-- i.e. have no more automatic retries
select count(*) from act_ru_job where retries_ = 0;

-- number incidents with particular error type in running processes
select count(*) from act_ru_incident where lower(incident_msg_) like '%deadlock%';

-- number incidents with particular error type in history
select count(*) from act_hi_incident where lower(incident_msg_) like '%deadlock%';
```

# Additional metrics to monitor

- CPU utilization
- Memory usage
- Database response time
- Database size
- Utilization/size of Job Executor thread pool (Application Server)
