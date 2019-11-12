-- drop tables not used in Camunda
drop table if exists  ACT_EVT_LOG cascade; 
drop table if exists  ACT_PROCDEF_INFO cascade;
drop table if exists  ACT_RE_MODEL cascade;

-- columns not used in camunda                                                               
alter table ACT_HI_IDENTITYLINK DROP COLUMN PROC_INST_ID_;

alter table ACT_HI_TASKINST     DROP COLUMN CATEGORY_;
alter table ACT_RE_DEPLOYMENT   DROP COLUMN CATEGORY_;
alter table ACT_RU_TASK         DROP COLUMN CATEGORY_;
alter table ACT_HI_TASKINST     DROP COLUMN CLAIM_TIME_;
alter table ACT_HI_VARINST      DROP COLUMN CREATE_TIME_;
alter table ACT_RE_PROCDEF      DROP COLUMN DESCRIPTION_;
alter table ACT_HI_VARINST      DROP COLUMN LAST_UPDATED_TIME_;
alter table ACT_RU_EXECUTION    DROP COLUMN LOCK_TIME_;
alter table ACT_HI_PROCINST     DROP COLUMN NAME_;
alter table ACT_RU_EXECUTION    DROP COLUMN NAME_;
ALTER TABLE ACT_RU_IDENTITYLINK DROP FOREIGN KEY ACT_FK_IDL_PROCINST;
alter table ACT_RU_IDENTITYLINK DROP COLUMN PROC_INST_ID_;
alter table ACT_RU_EVENT_SUBSCR DROP COLUMN PROC_DEF_ID_;
alter table ACT_RU_JOB          DROP FOREIGN KEY ACT_FK_JOB_PROC_DEF;
alter table ACT_RU_JOB          DROP COLUMN PROC_DEF_ID_;
alter table ACT_HI_ATTACHMENT   DROP COLUMN TIME_;
alter table ACT_HI_TASKINST     DROP COLUMN FORM_KEY_;
alter table ACT_RU_TASK         DROP COLUMN FORM_KEY_;
alter table ACT_RE_PROCDEF      DROP COLUMN HAS_GRAPHICAL_NOTATION_;

-- drop indexes not used in Camunda
ALTER TABLE act_hi_actinst drop index ACT_IDX_HI_ACT_INST_EXEC;
ALTER TABLE act_hi_identitylink drop index ACT_IDX_HI_IDENT_LNK_TASK;
ALTER TABLE act_hi_varinst drop index ACT_IDX_HI_PROCVAR_TASK_ID;
ALTER TABLE act_hi_taskinst drop index ACT_IDX_HI_TASK_INST_PROCINST;
