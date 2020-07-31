-- process definition key ='tenancy-test'
-- dmn definition key = 'dmn-tenancy-test'
-- tenant id ='tenant1'
-- bpmn file name = 'tenancy-test.bpmn'

update ACT_HI_ACTINST
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_DETAIL
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_IDENTITYLINK
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_JOB_LOG
set TENANT_ID_='tenant1'
where PROCESS_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_PROCINST
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_TASKINST
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_VARINST
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_EXT_TASK_LOG
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_INCIDENT
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_OP_LOG
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_DECINST
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_HI_DEC_IN
set TENANT_ID_='tenant1'
where DEC_INST_ID_ in (
    select id_ from ACT_HI_DECINST where TENANT_ID_='tenant1'
    );

update ACT_HI_DEC_OUT
set TENANT_ID_='tenant1'
where DEC_INST_ID_ in (
    select id_ from ACT_HI_DECINST where TENANT_ID_='tenant1'
    );

update ACT_RE_DEPLOYMENT
set TENANT_ID_='tenant1'
where NAME_ in ('tenancy-test', 'dmn-tenancy-test');

update ACT_RE_PROCDEF
set TENANT_ID_='tenant1'
where KEY_ = 'tenancy-test';

update ACT_RE_DECISION_DEF
set TENANT_ID_='tenant1'
where KEY_ = 'dmn-tenancy-test';

update ACT_RU_JOB
set TENANT_ID_='tenant1'
where PROCESS_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_RU_EXECUTION
set TENANT_ID_='tenant1'
where PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_ = 'tenancy-test'
    );

update ACT_RU_INCIDENT
set TENANT_ID_='tenant1'
where  PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test'
           );

update ACT_RU_JOBDEF
set TENANT_ID_='tenant1'
where PROC_DEF_KEY_='tenancy-test';

update ACT_RU_IDENTITYLINK
set TENANT_ID_='tenant1'
where  PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test'
           );

update ACT_RU_TASK
set TENANT_ID_='tenant1'
where  PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test'
           );

update ACT_RU_EXT_TASK
set TENANT_ID_='tenant1'
where  PROC_DEF_ID_ in (
    select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test'
           );


update ACT_RU_VARIABLE
set TENANT_ID_='tenant1'
where EXECUTION_ID_ in (
    select id_ from ACT_RU_EXECUTION where PROC_DEF_ID_ in (select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test')
    );

update ACT_RU_TASK
set TENANT_ID_='tenant1'
where EXECUTION_ID_ in (
    select id_ from ACT_RU_EXECUTION where PROC_DEF_ID_ in (select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test')
    );

update ACT_RU_EVENT_SUBSCR
set TENANT_ID_='tenant1'
where EXECUTION_ID_ in (
    select id_ from ACT_RU_EXECUTION where PROC_DEF_ID_ in (select ID_ from ACT_RE_PROCDEF where KEY_='tenancy-test')
    );

