# SQL Queries for Monitoring Camunda BPM

In some use cases you want to query data from the process engine database directly, e.g. health-monitoring, reporting or the like.

In this Snippet we collect helpful SQL queries which can be used - no guarantee they work on your database as some of them are collected in real-life projects (but on certain databases).

# Typical Monitoring Queries

Put the following queries in your favorite monitoring tool, e.g. [Nagios](http://omdistro.org/),
and [track them over time](http://docs.pnp4nagios.org/pnp-0.6/gallery/start)
to monitor [Camunda BPM](http://camunda.org) engine performance.

Note: On big databases some of these queries can get quite slow. If you run them regularly, you might want to add indexes.

```sql
--  running process instances
select count(*) from act_ru_execution
where parent_id_ is null;

-- finished process instances
select count(*) from act_hi_procinst where end_time_ is not null;

-- finished process instances by process definition key
SELECT ACT_RE_PROCDEF.NAME_, KEY_, COUNT(*) FROM ACT_HI_PROCINST
  JOIN ACT_RE_PROCDEF ON PROC_DEF_KEY_ = ACT_RE_PROCDEF.KEY_
  GROUP BY  ACT_RE_PROCDEF.KEY_
  ORDER BY COUNT(*) DESC;
```
<table cellspacing="0" cellpadding="0"><tbody>
<tr><th>NAME_</th><th>KEY_</th><th>COUNT(*)</th></tr>
<tr><td>TwitterDemoProcess</td><td>TwitterDemoProcess</td><td>48</td></tr><tr><td>Lastschriftverfahren</td><td>lastschriftverfahren</td><td>32</td></tr><tr><td>Invoice Receipt</td><td>invoice</td><td>14</td></tr><tr><td>Dummy Child Process</td><td>dummy-child-process</td><td>2</td></tr><tr><td>Insurance Underwriting</td><td>underwriting</td><td>1</td></tr></tbody>
</table>

```sql
-- running and finished instances by version
SELECT KEY_, VERSION_, VERSION_TAG_,
  (SELECT COUNT(*) FROM ACT_RU_EXECUTION WHERE ACT_RU_EXECUTION.PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_ ) AS Running,
  (SELECT COUNT(*) FROM ACT_HI_PROCINST WHERE ACT_HI_PROCINST.PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_) AS Finished
  FROM ACT_RE_PROCDEF
  GROUP BY ACT_RE_PROCDEF.KEY_ ,ACT_RE_PROCDEF.ID_
  ORDER BY KEY_, VERSION_

```
<table cellspacing="0" cellpadding="0"><tbody>
<tr><th>KEY_&nbsp;&nbsp;</th><th>VERSION_&nbsp;&nbsp;</th><th>RUNNING&nbsp;&nbsp;</th><th>FINISHED</th></tr>
<tr><td>DoctorInformationRequest</td><td>1</td><td>0</td><td>0</td></tr><tr><td>TwitterDemoProcess</td><td>1</td><td>1</td><td>2</td></tr><tr><td>TwitterDemoProcess</td><td>2</td><td>0</td><td>1</td></tr><tr><td>TwitterDemoProcess</td><td>3</td><td>0</td><td>1</td></tr><tr><td>TwitterDemoProcess</td><td>4</td><td>1</td><td>1</td></tr><tr><td>TwitterDemoProcess</td><td>5</td><td>0</td><td>1</td></tr><tr><td>TwitterDemoProcess</td><td>6</td><td>0</td><td>2</td></tr>
<tr><td>dummy-child-process</td><td>1</td><td>0</td><td>2</td></tr><tr><td>invoice</td><td>1</td><td>3</td><td>3</td></tr><tr><td>invoice</td><td>2</td><td>4</td><td>4</td></tr><tr><td>underwriting</td><td>1</td><td>2</td><td>1</td></tr>
</tbody>
</table>

```sql
-- running process instances by process definition key
SELECT ACT_RE_PROCDEF.KEY_, COUNT(*) FROM ACT_RU_EXECUTION
  JOIN ACT_RE_PROCDEF ON PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_
  WHERE PARENT_ID_ IS NULL
  GROUP BY  ACT_RE_PROCDEF.KEY_;

-- activity instance count (successful BPMN FNIs)
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

-- task durations
SELECT ACT_RE_PROCDEF.NAME_ AS process_name, ACT_HI_TASKINST.name_ AS task_name,
  round(min(duration_)/60000.0,1) AS min_duration,
  round(avg(duration_)/60000.0,1) AS avg_duration,
  round(max(duration_)/60000.0,1) AS max_duration,
  round(stddev(duration_)/60000.0,1) AS stddev
  FROM act_hi_taskinst
  JOIN ACT_RE_PROCDEF ON PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_
  WHERE duration_ > 0
  GROUP BY process_name, task_name
  order by process_name, avg_duration desc
```

<table cellspacing="0" border="0">
	<colgroup width="135"></colgroup>
	<colgroup width="297"></colgroup>
	<colgroup width="120"></colgroup>
	<colgroup width="160"></colgroup>
	<colgroup width="125"></colgroup>
	<colgroup width="166"></colgroup>
	<tr>
		<td height="17" align="left"><b>PROCESS_NAME&nbsp;&nbsp;</b></td>
		<td align="left"><b>TASK_NAME&nbsp;&nbsp;</b></td>
		<td align="left"><b>MIN_DURATION&nbsp;&nbsp;</b></td>
		<td align="left"><b>AVG_DURATION&nbsp;&nbsp;</b></td>
		<td align="left"><b>MAX_DURATION&nbsp;&nbsp;</b></td>
		<td align="left"><b>STDDEV</b></td>
	</tr>
	<tr>
		<td height="17" align="left">Invoice Receipt</td>
		<td align="left">Review Invoice</td>
		<td align="left">18849.0</td>
		<td align="left">24500.5</td>
		<td align="left">30151.9</td>
		<td align="left">7992.4</td>
	</tr>
	<tr>
		<td height="17" align="left">Invoice Receipt</td>
		<td align="left">Approve Invoice</td>
		<td align="left">2.5</td>
		<td align="left">19366.8</td>
		<td align="left">41920.9</td>
		<td align="left">16142.4</td>
	</tr>
	<tr>
		<td height="47" align="left">Invoice Receipt</td>
		<td align="left">Prepare Bank Transfer</td>
		<td align="left">2.9</td>
		<td align="left">2.9</td>
		<td align="left">2.9</td>
		<td align="left">null</td>
	</tr>
	<tr>
		<td height="17" align="left">TwitterDemoProcess</td>
		<td align="left">Handle duplicate</td>
		<td align="left">1.1</td>
		<td align="left">1.1</td>
		<td align="left">1.1</td>
		<td align="left">null</td>
	</tr>
	<tr>
		<td height="17" align="left">TwitterDemoProcess</td>
		<td align="left">Handle Duplicate</td>
		<td align="left">0.6</td>
		<td align="left">0.6</td>
		<td align="left">0.6</td>
		<td align="left">null</td>
	</tr>
	<tr>
		<td height="17" align="left">TwitterDemoProcess</td>
		<td align="left">Review Tweet</td>
		<td align="left">0.1</td>
		<td align="left">0.2</td>
		<td align="left">0.7</td>
		<td align="left">0.2</td>
	</tr>
</table>


## Many Metrics in one Query
You can use the following query to get a snapshot at a single point in time:

* [metrics.h2.sql](metrics.h2.sql)
* [metrics.oracle.sql](metrics.oracle.sql)
* [metrics.postgres.sql](metrics.postgres.sql)

### Example results from different systems
<table cellspacing="0" border="0">
	<colgroup width="98"></colgroup>
	<colgroup width="346"></colgroup>
	<colgroup width="86"></colgroup>
	<colgroup span="3" width="87"></colgroup>
	<tr>
		<td height="20" align="left"><b><font size=3>POSITION&nbsp;&nbsp;</font></b></td>
		<td align="left"><b><font size=3>METRIC</font></b></td>
		<td align="right"><b><font size=3>System A</font></b></td>
		<td align="right"><b><font size=3>System B</font></b></td>
		<td align="right"><b><font size=3>System C</font></b></td>
		<td align="right"><b><font size=3>System D</font></b></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="10" sdnum="1031;"><font size=3>10</font></td>
		<td align="left"><font size=3>Deployments</font></td>
		<td align="right" sdval="24" sdnum="1031;"><font size=3>24</font></td>
		<td align="right" sdval="1" sdnum="1031;"><font size=3>1</font></td>
		<td align="right" sdval="4" sdnum="1031;"><font size=3>4</font></td>
		<td align="right" sdval="81" sdnum="1031;"><font size=3>81</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="11" sdnum="1031;"><font size=3>11</font></td>
		<td align="left"><font size=3>Process Definitions</font></td>
		<td align="right" sdval="10" sdnum="1031;"><font size=3>10</font></td>
		<td align="right" sdval="5" sdnum="1031;"><font size=3>5</font></td>
		<td align="right" sdval="21" sdnum="1031;"><font size=3>21</font></td>
		<td align="right" sdval="7" sdnum="1031;"><font size=3>7</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="12" sdnum="1031;"><font size=3>12</font></td>
		<td align="left"><font size=3>Process Definition Versions</font></td>
		<td align="right" sdval="23" sdnum="1031;"><font size=3>23</font></td>
		<td align="right" sdval="5" sdnum="1031;"><font size=3>5</font></td>
		<td align="right" sdval="36" sdnum="1031;"><font size=3>36</font></td>
		<td align="right" sdval="81" sdnum="1031;"><font size=3>81</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="20" sdnum="1031;"><font size=3>20</font></td>
		<td align="left"><font size=3>Activity Instances</font></td>
		<td align="right" sdval="186" sdnum="1031;"><font size=3>186</font></td>
		<td align="right" sdval="380984" sdnum="1031;"><font size=3>380984</font></td>
		<td align="right" sdval="67050" sdnum="1031;"><font size=3>67050</font></td>
		<td align="right" sdval="7537175" sdnum="1031;"><font size=3>7537175</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="21" sdnum="1031;"><font size=3>21</font></td>
		<td align="left"><font size=3>Process Instances</font></td>
		<td align="right" sdval="42" sdnum="1031;"><font size=3>42</font></td>
		<td align="right" sdval="23905" sdnum="1031;"><font size=3>23905</font></td>
		<td align="right" sdval="11536" sdnum="1031;"><font size=3>11536</font></td>
		<td align="right" sdval="780806" sdnum="1031;"><font size=3>780806</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="22" sdnum="1031;"><font size=3>22</font></td>
		<td align="left"><font size=3>Process Instances (finished)</font></td>
		<td align="right" sdval="17" sdnum="1031;"><font size=3>17</font></td>
		<td align="right" sdval="22186" sdnum="1031;"><font size=3>22186</font></td>
		<td align="right" sdval="9906" sdnum="1031;"><font size=3>9906</font></td>
		<td align="right" sdval="596929" sdnum="1031;"><font size=3>596929</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="30" sdnum="1031;"><font size=3>30</font></td>
		<td align="left"><font size=3>Process Instances (running)</font></td>
		<td align="right" sdval="25" sdnum="1031;"><font size=3>25</font></td>
		<td align="right" sdval="1719" sdnum="1031;"><font size=3>1719</font></td>
		<td align="right" sdval="1630" sdnum="1031;"><font size=3>1630</font></td>
		<td align="right" sdval="183877" sdnum="1031;"><font size=3>183877</font></td>
	</tr>
	<tr>
		<td height="20" align="left"><font size=3>30.1</font></td>
		<td align="left"><font size=3>Executions (running)</font></td>
		<td align="right" sdval="35" sdnum="1031;"><font size=3>35</font></td>
		<td align="right"><font size=3>?</font></td>
		<td align="right"><font size=3>?</font></td>
		<td align="right"><font size=3>?</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="31" sdnum="1031;"><font size=3>31</font></td>
		<td align="left"><font size=3>User Tasks</font></td>
		<td align="right" sdval="25" sdnum="1031;"><font size=3>25</font></td>
		<td align="right" sdval="1715" sdnum="1031;"><font size=3>1715</font></td>
		<td align="right" sdval="630" sdnum="1031;"><font size=3>630</font></td>
		<td align="right" sdval="146672" sdnum="1031;"><font size=3>146672</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="32" sdnum="1031;"><font size=3>32</font></td>
		<td align="left"><font size=3>User Tasks (unassigned)</font></td>
		<td align="right" sdval="11" sdnum="1031;"><font size=3>11</font></td>
		<td align="right" sdval="1715" sdnum="1031;"><font size=3>1715</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="146649" sdnum="1031;"><font size=3>146649</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="40" sdnum="1031;"><font size=3>40</font></td>
		<td align="left"><font size=3>Event Subscriptions</font></td>
		<td align="right" sdval="3" sdnum="1031;"><font size=3>3</font></td>
		<td align="right" sdval="1" sdnum="1031;"><font size=3>1</font></td>
		<td align="right" sdval="844" sdnum="1031;"><font size=3>844</font></td>
		<td align="right" sdval="36585" sdnum="1031;"><font size=3>36585</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="41" sdnum="1031;"><font size=3>41</font></td>
		<td align="left"><font size=3>Event Subscriptions (type: message start)&nbsp;&nbsp;</font></td>
		<td align="right" sdval="3" sdnum="1031;"><font size=3>3</font></td>
		<td align="right" sdval="1" sdnum="1031;"><font size=3>1</font></td>
		<td align="right" sdval="844" sdnum="1031;"><font size=3>844</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="41" sdnum="1031;"><font size=3>41</font></td>
		<td align="left"><font size=3>Event Subscriptions (type: signal intermediate)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="36585" sdnum="1031;"><font size=3>36585</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="50" sdnum="1031;"><font size=3>50</font></td>
		<td align="left"><font size=3>Jobs</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="1723" sdnum="1031;"><font size=3>1723</font></td>
		<td align="right" sdval="328" sdnum="1031;"><font size=3>328</font></td>
		<td align="right" sdval="806" sdnum="1031;"><font size=3>806</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="51" sdnum="1031;"><font size=3>51</font></td>
		<td align="left"><font size=3>Jobs (running)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="804" sdnum="1031;"><font size=3>804</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="52" sdnum="1031;"><font size=3>52</font></td>
		<td align="left"><font size=3>Jobs (due)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="2" sdnum="1031;"><font size=3>2</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="53" sdnum="1031;"><font size=3>53</font></td>
		<td align="left"><font size=3>Jobs (waiting for timer)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="1719" sdnum="1031;"><font size=3>1719</font></td>
		<td align="right" sdval="327" sdnum="1031;"><font size=3>327</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="54" sdnum="1031;"><font size=3>54</font></td>
		<td align="left"><font size=3>Jobs (suspended)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="55" sdnum="1031;"><font size=3>55</font></td>
		<td align="left"><font size=3>Jobs (failed)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="4" sdnum="1031;"><font size=3>4</font></td>
		<td align="right" sdval="1" sdnum="1031;"><font size=3>1</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="56" sdnum="1031;"><font size=3>56</font></td>
		<td align="left"><font size=3>Jobs (timeout)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="59" sdnum="1031;"><font size=3>59</font></td>
		<td align="left"><font size=3>Jobs (type: message)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="4" sdnum="1031;"><font size=3>4</font></td>
		<td align="right" sdval="1" sdnum="1031;"><font size=3>1</font></td>
		<td align="right" sdval="806" sdnum="1031;"><font size=3>806</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="59" sdnum="1031;"><font size=3>59</font></td>
		<td align="left"><font size=3>Jobs (type: timer)</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
		<td align="right" sdval="1719" sdnum="1031;"><font size=3>1719</font></td>
		<td align="right" sdval="327" sdnum="1031;"><font size=3>327</font></td>
		<td align="right" sdval="0" sdnum="1031;"><font size=3>0</font></td>
	</tr>
	<tr>
		<td height="20" align="left" sdval="60" sdnum="1031;"><font size=3>60</font></td>
		<td align="left"><font size=3>Process Variables</font></td>
		<td align="right" sdval="79" sdnum="1031;"><font size=3>79</font></td>
		<td align="right" sdval="15651" sdnum="1031;"><font size=3>15651</font></td>
		<td align="right" sdval="8610" sdnum="1031;"><font size=3>8610</font></td>
		<td align="right" sdval="361211" sdnum="1031;"><font size=3>361211</font></td>
	</tr>
</table>

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
    - Acquired Jobs (maybe by destinguished by high, low, standard prio)
    - Acquired Exclusive Jobs
    - Completed Jobs
    - Exception count (TX rollbacks)
        - Optimistic Locking Exceptions
        - Deadlocks
    - execution time
    - hint count/cache hits
    - queue usage
    - thread pool size

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
