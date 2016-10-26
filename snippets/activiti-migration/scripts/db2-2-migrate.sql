--- alter table ACT_HI_IDENTITYLINK to match Camundas version, note that some data may not be filled for old instances! 
alter table ACT_HI_IDENTITYLINK     add ASSIGNER_ID_ varchar(64);
alter table ACT_HI_IDENTITYLINK     add OPERATION_TYPE_ varchar(64);
alter table ACT_HI_IDENTITYLINK     add PROC_DEF_ID_ varchar(64);
alter table ACT_HI_IDENTITYLINK     add PROC_DEF_KEY_ varchar(255);
alter table ACT_HI_IDENTITYLINK     add TENANT_ID_ varchar(64);
alter table ACT_HI_IDENTITYLINK     add TIMESTAMP_ timestamp;
--    drop PROC_INST_ID_, -- done in drop script

create index ACT_IDX_HI_IDENT_LNK_TENANT_ID on ACT_HI_IDENTITYLINK(TENANT_ID_);

--- changes in attributes
alter table ACT_HI_ACTINST      alter ASSIGNEE_ varchar(64);

alter table ACT_HI_ACTINST      alter TENANT_ID_ drop default; alter table ACT_HI_ACTINST         alter TENANT_ID_ varchar(64);
alter table ACT_HI_PROCINST     alter TENANT_ID_ drop default; alter table ACT_HI_PROCINST        alter TENANT_ID_ varchar(64);
alter table ACT_HI_TASKINST     alter TENANT_ID_ drop default; alter table ACT_HI_TASKINST        alter TENANT_ID_ varchar(64);
alter table ACT_RE_DEPLOYMENT   alter TENANT_ID_ drop default; alter table ACT_RE_DEPLOYMENT      alter TENANT_ID_ varchar(64);
alter table ACT_RE_PROCDEF      alter TENANT_ID_ drop default; alter table ACT_RE_PROCDEF         alter TENANT_ID_ varchar(64);
alter table ACT_RU_EVENT_SUBSCR alter TENANT_ID_ drop default; alter table ACT_RU_EVENT_SUBSCR    alter TENANT_ID_ varchar(64);
alter table ACT_RU_EXECUTION    alter TENANT_ID_ drop default; alter table ACT_RU_EXECUTION       alter TENANT_ID_ varchar(64);
alter table ACT_RU_JOB          alter TENANT_ID_ drop default; alter table ACT_RU_JOB             alter TENANT_ID_ varchar(64);
alter table ACT_RU_TASK         alter TENANT_ID_ drop default; alter table ACT_RU_TASK            alter TENANT_ID_ varchar(64);


--- delete users and groups, as you have to re-create them (hashed passwords, different group types, required authorizations)
delete from ACT_ID_MEMBERSHIP;
delete from ACT_ID_TENANT_MEMBER;
delete from ACT_ID_INFO;
delete from ACT_ID_USER;
delete from ACT_ID_GROUP;
