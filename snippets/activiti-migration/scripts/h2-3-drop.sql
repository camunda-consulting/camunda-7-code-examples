--- drop tables not used in Camunda
drop table if exists  ACT_EVT_LOG cascade; 
drop table if exists  ACT_PROCDEF_INFO cascade;
drop table if exists  ACT_RE_MODEL cascade;

--- columns not used in camunda                                                               
alter table ACT_HI_IDENTITYLINK 
    drop PROC_INST_ID_;

alter table ACT_HI_TASKINST     drop CATEGORY_;                
alter table ACT_RE_DEPLOYMENT   drop CATEGORY_;                
alter table ACT_RU_TASK         drop CATEGORY_;                
alter table ACT_HI_TASKINST     drop CLAIM_TIME_ ;             
alter table ACT_HI_VARINST      drop CREATE_TIME_ ;            
alter table ACT_RE_PROCDEF      drop DESCRIPTION_ ;            
alter table ACT_HI_VARINST      drop LAST_UPDATED_TIME_ ;      
alter table ACT_RU_EXECUTION    drop LOCK_TIME_ ;              
alter table ACT_HI_PROCINST     drop NAME_ ;                   
alter table ACT_RU_EXECUTION    drop NAME_ ;                   
alter table ACT_RU_IDENTITYLINK drop PROC_INST_ID_ ;           
alter table ACT_RU_EVENT_SUBSCR drop PROC_DEF_ID_ ;            
alter table ACT_RU_JOB          drop PROC_DEF_ID_ ;            
alter table ACT_HI_ATTACHMENT   drop TIME_ ;                   
alter table ACT_HI_TASKINST     drop FORM_KEY_ ;               
alter table ACT_RU_TASK         drop FORM_KEY_ ;               
alter table ACT_RE_PROCDEF      drop HAS_GRAPHICAL_NOTATION_;  

--- drop indexes not used in Camunda
drop index ACT_IDX_HI_ACT_INST_EXEC;
drop index ACT_IDX_HI_IDENT_LNK_TASK;
drop index ACT_IDX_HI_PROCVAR_TASK_ID;
drop index ACT_IDX_HI_TASK_INST_PROCINST;
