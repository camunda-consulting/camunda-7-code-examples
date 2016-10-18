--- alter table ACT_HI_IDENTITYLINK to match Camundas version, note that some data may not be filled for old instances! 
alter table ACT_HI_IDENTITYLINK 
--    drop PROC_INST_ID_, -- done in drop script
    add ASSIGNER_ID_ varchar(64),
    add OPERATION_TYPE_ varchar(64),
    add PROC_DEF_ID_ varchar(64),
    add PROC_DEF_KEY_ varchar(255),
    add TENANT_ID_ varchar(64),
    add TIMESTAMP_ timestamp;

create index ACT_IDX_HI_IDENT_LNK_TENANT_ID on ACT_HI_IDENTITYLINK(TENANT_ID_);

--- changes in attributes
alter table ACT_HI_ACTINST      alter ASSIGNEE_ type varchar(64);

alter table ACT_HI_ACTINST      alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_HI_PROCINST     alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_HI_TASKINST     alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_RE_DEPLOYMENT   alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_RE_PROCDEF      alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_RU_EVENT_SUBSCR alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_RU_EXECUTION    alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_RU_JOB          alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
alter table ACT_RU_TASK         alter TENANT_ID_ drop default,   alter TENANT_ID_ type varchar(64);
