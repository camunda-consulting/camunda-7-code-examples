/*
Camunda Version: 7.3.2-ee; Oracle 12c tested
State as of: 24.02.2015 
S. Hellmann, M. Krassmann: Silpion; T. Hirsch for Haspa; Ingo Richtsmeier for Camunda;

DOC.:
--------------------------------------------------------------------------------------
   Add some Camunda Indexes to history schema part (for Archiving)
*/

WHENEVER SQLERROR EXIT SQL.SQLCODE;
SET SERVEROUTPUT ON;

create INDEX IDX_ACT_HI_TASKINST_PIID ON ACT_HI_TASKINST (PROC_INST_ID_); 
create INDEX IDX_ACT_HI_COMMENT_PIID ON ACT_HI_COMMENT (PROC_INST_ID_);
create INDEX IDX_ACT_HI_ATTACHMENT_PIID ON ACT_HI_ATTACHMENT (PROC_INST_ID_);
create INDEX IDX_ACT_HI_OP_LOG_PIID ON ACT_HI_OP_LOG (PROC_INST_ID_);
create INDEX IDX_ACT_HI_INCIDENT_PIID ON ACT_HI_INCIDENT (PROC_INST_ID_);
create INDEX IDX_ACT_HI_ACTINST_PIID ON ACT_HI_ACTINST(PROC_INST_ID_);
create INDEX IDX_ACT_HI_DETAIL_PIID ON ACT_HI_DETAIL(PROC_INST_ID_);
create INDEX IDX_ACT_HI_VARINST_PIID ON ACT_HI_VARINST (PROC_INST_ID_);
create INDEX IDX_ACT_HI_PROCINST_PIID ON ACT_HI_PROCINST(PROC_INST_ID_);  


quit;
/