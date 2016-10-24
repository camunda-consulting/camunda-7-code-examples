--- drop tables not used in Camunda
drop table ACT_EVT_LOG cascade; 
drop table ACT_PROCDEF_INFO cascade;
drop table ACT_RE_MODEL cascade;

--- columns not used in camunda                                                               
alter table ACT_HI_IDENTITYLINK drop column PROC_INST_ID_;

alter table ACT_HI_TASKINST     drop column CATEGORY_;                
alter table ACT_RE_DEPLOYMENT   drop column CATEGORY_;                
alter table ACT_RU_TASK         drop column CATEGORY_;                
alter table ACT_HI_TASKINST     drop column CLAIM_TIME_ ;             
alter table ACT_HI_VARINST      drop column CREATE_TIME_ ;            
alter table ACT_RE_PROCDEF      drop column DESCRIPTION_ ;            
alter table ACT_HI_VARINST      drop column LAST_UPDATED_TIME_ ;      
alter table ACT_RU_EXECUTION    drop column LOCK_TIME_ ;              
alter table ACT_HI_PROCINST     drop column NAME_ ;                   
alter table ACT_RU_EXECUTION    drop column NAME_ ;                   
alter table ACT_RU_IDENTITYLINK drop column PROC_INST_ID_ ;           
alter table ACT_RU_EVENT_SUBSCR drop column PROC_DEF_ID_ ;            
alter table ACT_RU_JOB          drop column PROC_DEF_ID_ ;            
alter table ACT_HI_ATTACHMENT   drop column TIME_ ;                   
alter table ACT_HI_TASKINST     drop column FORM_KEY_ ;               
alter table ACT_RU_TASK         drop column FORM_KEY_ ;               
alter table ACT_RE_PROCDEF      drop column HAS_GRAPHICAL_NOTATION_;  

--- drop indexes not used in Camunda
drop index ACT_IDX_HI_ACT_INST_EXEC;
drop index ACT_IDX_HI_IDENT_LNK_TASK;
drop index ACT_IDX_HI_PROCVAR_TASK_ID;
drop index ACT_IDX_HI_TASK_INST_PROCINST;
