--- alter table ACT_HI_IDENTITYLINK to match Camundas version, note that some data may not be filled for old instances! 
alter table ACT_HI_IDENTITYLINK add
--    drop PROC_INST_ID_, -- done in drop script
    ASSIGNER_ID_ nvarchar(64),
    OPERATION_TYPE_ nvarchar(64),
    PROC_DEF_ID_ nvarchar(64),
    PROC_DEF_KEY_ nvarchar(255),
    TENANT_ID_ nvarchar(64),
    TIMESTAMP_ datetime2;

create index ACT_IDX_HI_IDENT_LNK_TENANT_ID on ACT_HI_IDENTITYLINK(TENANT_ID_);

--- changes in attributes
alter table ACT_HI_ACTINST      alter column ASSIGNEE_ nvarchar(64);

alter table ACT_HI_ACTINST      alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_HI_PROCINST     alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_HI_TASKINST     alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_RE_DEPLOYMENT   alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_RE_PROCDEF      alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_RU_EVENT_SUBSCR alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_RU_EXECUTION    alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_RU_JOB          alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   
alter table ACT_RU_TASK         alter column TENANT_ID_ nvarchar(64); --TODO: alter column TENANT_ID_ drop default;   

--- delete users and groups, as you have to re-create them (hashed passwords, different group types, required authorizations)
delete from ACT_ID_MEMBERSHIP;
delete from ACT_ID_TENANT_MEMBER;
delete from ACT_ID_INFO;
delete from ACT_ID_USER;
delete from ACT_ID_GROUP;