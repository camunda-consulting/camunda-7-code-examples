-- alter table ACT_HI_IDENTITYLINK to match Camundas version, note that some data may not be filled for old instances! 
alter table ACT_HI_IDENTITYLINK add column (
    ASSIGNER_ID_ varchar(64),
    OPERATION_TYPE_ varchar(64),
    PROC_DEF_ID_ varchar(64),
    PROC_DEF_KEY_ varchar(255),
    TENANT_ID_ varchar(64),
    TIMESTAMP_ timestamp);
--    drop PROC_INST_ID_, -- done in drop script

create index ACT_IDX_HI_IDENT_LNK_TENANT_ID on ACT_HI_IDENTITYLINK(TENANT_ID_);

-- changes in attributes
alter table ACT_HI_ACTINST      MODIFY COLUMN ASSIGNEE_ varchar(64);

alter table ACT_HI_ACTINST      MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_HI_PROCINST     MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_HI_TASKINST     MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_RE_DEPLOYMENT   MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_RE_PROCDEF      MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_RU_EVENT_SUBSCR MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_RU_EXECUTION    MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_RU_JOB          MODIFY COLUMN TENANT_ID_ varchar(64);
alter table ACT_RU_TASK         MODIFY COLUMN TENANT_ID_ varchar(64);

-- delete users and groups, as you have to re-create them (hashed passwords, different group types, required authorizations)
delete from ACT_ID_MEMBERSHIP;
delete from ACT_ID_TENANT_MEMBER;
delete from ACT_ID_INFO;
delete from ACT_ID_USER;
delete from ACT_ID_GROUP;
