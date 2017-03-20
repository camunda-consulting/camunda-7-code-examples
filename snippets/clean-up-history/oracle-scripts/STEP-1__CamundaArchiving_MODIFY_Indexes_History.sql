-- +================================================================+
-- | STEP-1__CamundaArchiving_MODIFY_Indexes_History.sql
-- +================================================================+
-- | Camunda Version: 7.6.0; Oracle 12c tested
-- | State as of: 20.03.2017 
-- | S. Hellmann, M. Krassmann: Silpion; T. Hirsch for Haspa; Ingo Richtsmeier for Camunda;
-- | Thomas Lenarz for Talanx;
-- |
-- | Changed: 25.08.2016
-- |   added index of table SOAPMESSAGES for Talanx
-- |   Thomas Lenarz for Talanx
-- |
-- | Changed: 06.12.2016
-- |   Index INDEX IDX_ACT_HI_OP_LOG_PIID ist bereits in den Talanx-Umgebungen angelegt.
-- |   Daher entsprechenden Create auskommentiert.
-- |   Thomas Lenarz for Talanx
-- |
-- | DOC.:
-- |  Add some Camunda Indexes to history schema part (for Archiving)
-- +================================================================+

COLUMN vcomplogfile new_value vcomplogfile NOPRINT;
SELECT 'STEP-1__CamundaArchiving_MODIFY_Indexes_History_'|| TO_CHAR(SYSDATE,'DDMonYYYY_hh24_mi_ss') || '.lst' AS vcomplogfile
FROM dual;
spool &vcomplogfile

whenever sqlerror continue
whenever oserror continue
set autocommit off;
set heading off;
-- +================================================================+
show user;
select 'DATABASE = ' || sys_context('USERENV','DB_NAME') DB from dual;
-- +================================================================+
set heading on;
set echo on;
-- +================================================================+
-- +==== ENDE SQL HEADER ===========================================+
-- +================================================================+

-- Block 1 ohne Abbruch bei Fehler [z.B. drop Table....]

-- Block 2 Abbruch bei Fehler
whenever SQLERROR EXIT sql.sqlcode ROLLBACK
whenever OSERROR EXIT 20001 ROLLBACK

create INDEX IDX_ACT_HI_TASKINST_PIID ON ACT_HI_TASKINST (PROC_INST_ID_); 
create INDEX IDX_ACT_HI_COMMENT_PIID ON ACT_HI_COMMENT (PROC_INST_ID_);
create INDEX IDX_ACT_HI_ATTACHMENT_PIID ON ACT_HI_ATTACHMENT (PROC_INST_ID_);
/* 06.12.2016: Thomas Lenarz, Folgender Index existiert schon
                              in den Talanx-Umgebungen. Muss also nicht mehr
                              angelegt werden.
    create INDEX IDX_ACT_HI_OP_LOG_PIID ON ACT_HI_OP_LOG (PROC_INST_ID_);
*/
create INDEX IDX_ACT_HI_ACTINST_PIID ON ACT_HI_ACTINST(PROC_INST_ID_);

/*Already indexed in camunda-bpm-7.6.0 uncomment for other versions */
/*
create INDEX IDX_ACT_HI_DETAIL_PIID ON ACT_HI_DETAIL(PROC_INST_ID_);
create INDEX IDX_ACT_HI_VARINST_PIID ON ACT_HI_VARINST (PROC_INST_ID_);
create INDEX IDX_ACT_HI_PROCINST_PIID ON ACT_HI_PROCINST(PROC_INST_ID_);
create INDEX IDX_ACT_HI_OP_LOG_PIID ON ACT_HI_OP_LOG (PROC_INST_ID_);
*/

SPOOL OFF

