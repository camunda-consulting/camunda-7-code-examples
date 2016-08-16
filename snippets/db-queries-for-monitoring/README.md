# SQL Queries for Monitoring Camunda BPM

In some use cases you want to query data from the process engine database directly, e.g. health-monitoring, reporting or the like.

In this Snippet we collect helpful SQL queries which can be used - no guarantee they work on your database as some of them are collected in real-life projects (but on certain databases).

# Typical Monitoring Queries

Put the following queries in your favorite monitoring tool, e.g. [Nagios](http://omdistro.org/),
and [track them over time](http://docs.pnp4nagios.org/pnp-0.6/gallery/start)
to monitor [Camunda BPM](http://camunda.org) engine performance.


```sql
--  running process instances
select count(*) from act_ru_execution
where parent_id_ is null;

-- finished process instances
select count(*) from act_hi_procinst where end_time_ is not null;

-- flow nodes count
select count(*) from act_hi_actinst;

-- flow nodes count by month
SELECT year, month, COUNT (*) AS flowNodeCount
    FROM (SELECT TO_CHAR (START_TIME_, 'yyyy')  as year,  TO_CHAR (START_TIME_, 'mm')AS month
            FROM ACT_HI_ACTINST )
GROUP BY (year, month)
order by 1,2

-- number of Jobs that are currently being processed,
-- i.e. are acquired by a Job Executor
select count(*) from act_ru_job res
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

-- number of open incidents in running processes
select count(*) from act_ru_incident;

-- number of all incidents in history
select count(*) from act_hi_incident;

-- number of open incidents with particular error type in running processes
select count(*) from act_ru_incident where lower(incident_msg_) like '%api.twitter.com%';

-- many metrics in one query
SELECT *
FROM
  (
  SELECT 10 AS Position, 'Deployments' AS Metric, COUNT(*) AS Count FROM ACT_RE_DEPLOYMENT
  UNION SELECT 11, 'Process Definitions', COUNT(*) FROM (SELECT DISTINCT KEY_ FROM ACT_RE_PROCDEF)
  UNION SELECT 12, 'Process Definition Versions', COUNT(*) FROM ACT_RE_PROCDEF
  UNION SELECT 20, 'Flow Node Instances (FNI)', COUNT(*) FROM ACT_HI_ACTINST
  UNION SELECT 21, 'Process Instances', COUNT(*) FROM ACT_HI_PROCINST
  UNION SELECT 22, 'Process Instances (finished)', COUNT(*) FROM ACT_HI_PROCINST WHERE END_TIME_ IS NOT NULL
  UNION SELECT 30, 'Process Instances (running)', COUNT(*) FROM ACT_RU_EXECUTION WHERE PARENT_ID_ IS NULL
  UNION SELECT 31, 'User Tasks', COUNT(*) FROM ACT_RU_TASK
  UNION SELECT 32, 'User Tasks (unassigned)', COUNT(*) FROM ACT_RU_TASK WHERE ASSIGNEE_ IS NULL
  UNION SELECT 40, 'Event Subscriptions', COUNT(*) FROM ACT_RU_EVENT_SUBSCR
  UNION SELECT 41, 'Event Subscriptions (type: ' || EVENT_TYPE_ || CASEWHEN (PROC_INST_ID_ IS NULL, ' start', 'intermediate') || ')' AS Metric, COUNT(*) FROM ACT_RU_EVENT_SUBSCR GROUP BY Metric
  UNION SELECT 50, 'Jobs', COUNT(*) FROM ACT_RU_JOB
  UNION SELECT 51, 'Jobs (running)', COUNT(*) FROM ACT_RU_JOB
    WHERE (LOCK_OWNER_ IS NOT NULL AND LOCK_EXP_TIME_ >= SYSDATE)
  UNION SELECT 52, 'Jobs (due)', COUNT(*) FROM ACT_RU_JOB
    WHERE (RETRIES_ > 0)
    AND (DUEDATE_ IS NULL OR DUEDATE_ < SYSDATE)
    AND (LOCK_OWNER_ IS NULL OR LOCK_EXP_TIME_ < SYSDATE)
    AND (SUSPENSION_STATE_ = 1 OR SUSPENSION_STATE_ IS NULL)
  UNION SELECT 53, 'Jobs (waiting)', COUNT(*) FROM ACT_RU_JOB
    WHERE (RETRIES_ > 0)
    AND (DUEDATE_ IS NOT NULL AND DUEDATE_ >= SYSDATE)
    AND (LOCK_OWNER_ IS NULL OR LOCK_EXP_TIME_ < SYSDATE)
    AND (SUSPENSION_STATE_ = 1 OR SUSPENSION_STATE_ IS NULL)
  UNION SELECT 54, 'Jobs (suspended)', COUNT(*) FROM ACT_RU_JOB WHERE SUSPENSION_STATE_ = 2
  UNION SELECT 55, 'Jobs (failed)', COUNT(*) FROM ACT_RU_JOB WHERE RETRIES_ = 0
  UNION SELECT 59, 'Jobs (type: ' || TYPE_ || ')' AS Metric, COUNT(*) FROM ACT_RU_JOB GROUP BY Metric
  UNION SELECT 60, 'Process Variables', COUNT(*) FROM ACT_RU_VARIABLE
  )
ORDER BY Position, Metric
```

## Process/Case instances over time

One count per hour for the last 30 days, can be drawn as line chart to see development, e.g.

![Line Chart][1]

H2:
```sql
-- process instances
select hour_date, ACT_RE_PROCDEF.KEY_ , count(*) as processInstanceCount
FROM (select  DATEADD('HOUR', -1*rownum, sysdate) as hour_date  from SYSTEM_RANGE(1, 168))
     join act_hi_procinst on (start_time_ <= hour_date and (end_time_ is null OR end_time_ > hour_date))
     join ACT_RE_PROCDEF on act_hi_procinst.PROC_DEF_ID_  = ACT_RE_PROCDEF.ID_
group by (hour_date, ACT_RE_PROCDEF.KEY_)
order by ACT_RE_PROCDEF.KEY_, hour_date;

-- case instances
select hour_date, ACT_RE_CASE_DEF.KEY_ , count(*) as caseInstanceCount
FROM (select  DATEADD('HOUR', -1*rownum, sysdate) as hour_date  from SYSTEM_RANGE(1, 168))
     join act_hi_caseinst on (create_time_ <= hour_date and (close_time_ is null OR close_time_ > hour_date))
     join ACT_RE_CASE_DEF on act_hi_caseinst.CASE_DEF_ID_  = ACT_RE_CASE_DEF.ID_
group by (hour_date, ACT_RE_CASE_DEF.KEY_)
order by ACT_RE_CASE_DEF.KEY_, hour_date;
```

Oracle: 
```sql
select hour_date, to_char(hour_date, 'yyyy-mm-dd') as dateString, to_char(hour_date, 'hh24') as hourString, ACT_RE_PROCDEF.KEY_ , count(*) as processInstanceCount
FROM (select  sysdate - rownum/24 as hour_date  from all_objects WHERE rownum < 169)
     join act_hi_procinst on (start_time_ <= hour_date and (end_time_ is null OR end_time_ > hour_date))
     join ACT_RE_PROCDEF on act_hi_procinst.PROC_DEF_ID_  = ACT_RE_PROCDEF.ID_
group by (hour_date, ACT_RE_PROCDEF.KEY_  )
order by ACT_RE_PROCDEF.KEY_, hour_date;
```


# Additional metrics to monitor

- CPU utilization
- Memory usage
- Swap file size
- Database response time
- Database size
- Utilization/size of Job Executor thread pool (Application Server)

[1]: instancesOverTime.png

# Java API Queries

```java
// Number of finished process instances
historyService.createHistoricProcessInstanceQuery().processDefinitionKey("my-process").finished().count();

// Number of running process instances with incidents
runtimeService.createProcessInstanceQuery().processDefinitionKey("my-process").incidentMessageLike("%").active().count();

// Number of running process instances that exceed a given durataion
historyService.createHistoricProcessInstanceQuery().processDefinitionKey("my-process").unfinished().startedBefore(new Date()).count();

// Advanced queries
historyService.createNativeHistoricProcessInstanceQuery().sql(
    "SELECT count(*) FROM "
    + managementService.getTableName(HistoricProcessInstance.class) + " WHERE END_ACT_ID_ = #{endActivityId}")
  .parameter("endActivityId", "endEvent_23")
  .count();

// Number of failed job incidents for a given process definition
runtimeService.createProcessInstanceQuery().processDefinitionKey("my-process").incidentType(Incident.FAILED_JOB_HANDLER_TYPE).count();

// Number of external task incidents for a given process definition
runtimeService.createProcessInstanceQuery().processDefinitionKey("my-process").incidentType(Incident.EXTERNAL_TASK_HANDLER_TYPE).count();
```
