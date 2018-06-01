-- This is an example of how one could fix certain issues regarding a bpmn model in the database.
-- In this case it's a missing activiti namespace
-- start /export/home/oracle/scripts/Camunda/fix-skipExpression-without-namespace.sql
spool /export/home/oracle/scripts/Camunda/fix-skipExpression-without-namespace.txt

select * from ACT_GE_BYTEARRAY where NAME_ LIKE '%.bpmn' and dbms_lob.instr(BYTES_, utl_raw.cast_to_raw(' skipExpression='))>0;

CREATE OR REPLACE FUNCTION convert_to_clob(l_blob BLOB) RETURN CLOB IS
      l_clob         CLOB;
      l_dest_offset  NUMBER := 1;
      l_src_offset   NUMBER := 1;
      l_lang_context NUMBER := dbms_lob.default_lang_ctx;
      l_warning      NUMBER;
   BEGIN
      dbms_lob.createtemporary(l_clob, TRUE);
      dbms_lob.converttoclob(dest_lob     => l_clob,
                             src_blob     => l_blob,
                             amount       => dbms_lob.lobmaxsize,
                             dest_offset  => l_dest_offset,
                             src_offset   => l_src_offset,
                             blob_csid    => nls_charset_id('AL32UTF8'),
                             lang_context => l_lang_context,
                             warning      => l_warning);
      RETURN l_clob;
   END convert_to_clob;
/

CREATE OR REPLACE FUNCTION convert_to_blob(l_clob CLOB) RETURN BLOB IS
      l_blob         BLOB;
      l_dest_offset  NUMBER := 1;
      l_src_offset   NUMBER := 1;
      l_lang_context NUMBER := dbms_lob.default_lang_ctx;
      l_warning      NUMBER;
   BEGIN
      dbms_lob.createtemporary(l_blob, TRUE);
      dbms_lob.converttoblob(dest_lob     => l_blob,
                             src_clob     => l_clob,
                             amount       => dbms_lob.lobmaxsize,
                             dest_offset  => l_dest_offset,
                             src_offset   => l_src_offset,
                             blob_csid    => nls_charset_id('AL32UTF8'),
                             lang_context => l_lang_context,
                             warning      => l_warning);
      RETURN l_blob;
   END convert_to_blob;
   /

UPDATE ACT_GE_BYTEARRAY
     SET BYTES_ = CONVERT_TO_BLOB(
                            REPLACE(convert_to_clob(BYTES_),
                                   ' skipExpression=',
                                   ' activiti:skipExpression=')
                            )
	WHERE NAME_ LIKE '%.bpmn' and dbms_lob.instr(BYTES_, utl_raw.cast_to_raw(' skipExpression='))>0;

drop function CONVERT_TO_BLOB;
drop function CONVERT_TO_CLOB;

commit;
spool off
