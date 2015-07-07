/* 
Camunda Version: 7.3.2-ee; Oracle 12c tested
State as of: 23.02.2015 
S. Hellmann, M. Krassmann: Silpion; T. Hirsch for Haspa; Ingo Richtsmeier for Camunda;

DOC.:
--------------------------------------------------------------------------------------
Create/Replace  ARCHIVE_CAMUNDA_HISTORY StoreProcedure -function for archiving of history camunda tables.
*/

WHENEVER SQLERROR EXIT SQL.SQLCODE;
SET SERVEROUTPUT ON;


CREATE OR REPLACE TYPE TextArrayType IS TABLE OF VARCHAR2(80);
/
 
CREATE OR REPLACE FUNCTION ARCHIVE_CAMUNDA_HISTORY(IN_periodInDays IN NUMBER DEFAULT 1,
                                                   IN_maxProcessInstances IN NUMBER DEFAULT 10)
RETURN NVARCHAR2
AS

 PRAGMA AUTONOMOUS_TRANSACTION;
 P_archiveTablesArray TextArrayType;
 P_executionId number(20);    /* generate from STAT_EXECUTION_SEQ a STAT_EXECUTION_ID for this RUN */
 P_piProcessed number;        /* Number of PI's workout */
 P_baProcessed number;        /* Number of Bytearray's workout */
 P_tableName NVARCHAR2(80);   /* temp tableName */
 P_query VARCHAR2(400);       /* temp query */ 
 P_startDate DATE;            /* start Timestamp */
 P_executionDuration number;  /* duration */
 P_result NVARCHAR2(400);     /* Result as String (for information) */

BEGIN
/* ------------------------------------------------- 
Manual archiving of Camunda History tables:

   Pre-CONDITION:
     Create scripts executed:
      STEP-1__CamundaArchiving_MODIFY_Indexes_History_CamundaEdition.sql (Optional)
      STEP-2__CamundaArchiving_CREATE_Archive_Tables_CamundaEdition.sql
     
   PARAMS:
     IN_periodInDays        : archive all ProcessInstances with END_TIME_ + IN_periodInDays 
                              (DEFAULT=1)
                              
     IN_maxProcessInstances : limit max. number of ProcessInstances 
                              (DEFAULT=10 for testing and to prevent archiving desaster ;-)
                              ( 0 : unlimited, all ) 
                              

     
    HOW TO EXECUTE: 
        SET SERVEROUTPUT ON;
        DBMS_OUTPUT.enable;
        select ARCHIVE_CAMUNDA_HISTORY(1, 10) from DUAL; -- Archive PI's, older then 1 day, max. 10
        --DBMS_OUTPUT.disable;
        SET SERVEROUTPUT OFF;
       
    -- CHECK BEFORE: Number of candidates to archive 
       SELECT count(*), min(END_TIME_), max(END_TIME_) FROM (
          SELECT hi.PROC_INST_ID_, hi.END_TIME_ 
            FROM ACT_HI_PROCINST hi 
                           WHERE hi.END_TIME_ IS NOT NULL  
                                 AND hi.END_TIME_ <= (TRUNC(SYSDATE) - 1 )  
        ) WHERE ROWNUM <= 10;
        
    -- CHECK AFTER: Number completed PI's in archive 
       select count(*), min(END_TIME_), max(END_TIME_), STAT_EXECUTION_ID  
         from ARCHIVE_ACT_HI_PROCINST
         GROUP BY STAT_EXECUTION_ID ORDER BY STAT_EXECUTION_ID;

-----------------------------------------------------------------------*/
    
    P_archiveTablesArray := TextArrayType('ACT_HI_PROCINST', 'ACT_HI_ACTINST', 'ACT_HI_TASKINST', 'ACT_HI_VARINST', 'ACT_HI_DETAIL', 
                                          'ACT_HI_COMMENT', 'ACT_HI_ATTACHMENT', 'ACT_HI_OP_LOG', 'ACT_HI_INCIDENT');
                      
    /* START TRANSACTION */
    P_startDate := sysdate;
    P_executionId := STAT_EXECUTION_SEQ.NEXTVAL;
    
    dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:  START EXECUTION: ' || to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') ||
                           '; STAT_EXECUTION_ID: '|| P_executionId || chr(13)||chr(10) ||
                           ' PARAMS: IN_maxProcessInstances: ' || IN_maxProcessInstances || '; IN_periodInDays: ' || IN_periodInDays );
                                                            
    /* 1. truncate TMP_ARCHIVING_PROCINST and TMP_ARCHIVING_BYTEARRAY */
    dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:  Delete TMP_ARCHIVING_PROCINST and TMP_ARCHIVING_BYTEARRAY...' );
    DELETE TMP_ARCHIVING_PROCINST;
    DELETE TMP_ARCHIVING_BYTEARRAY;  
    
    /* 2. Fill TMP_ARCHIVING_PROCINST with candidates: */
    dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:  INSERT INTO TMP_ARCHIVING_PROCINST ...' );
    
    IF IN_maxProcessInstances = 0 THEN /* all */
    
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:  INSERT INTO TMP_ARCHIVING_PROCINST ... UNLIMITED ' );
        INSERT INTO TMP_ARCHIVING_PROCINST
              SELECT hi.PROC_INST_ID_, hi.END_TIME_ 
                FROM ACT_HI_PROCINST hi 
                               WHERE hi.END_TIME_ IS NOT NULL  
                                     AND hi.END_TIME_ <= (TRUNC(SYSDATE) - IN_periodInDays );
    ELSE /* limit: IN_maxProcessInstances */
    
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:  INSERT INTO TMP_ARCHIVING_PROCINST ... LIMIT TO IN_maxProcessInstances: ' || IN_maxProcessInstances);
        
        INSERT INTO TMP_ARCHIVING_PROCINST
            SELECT PROC_INST_ID_, END_TIME_ FROM ( 
              SELECT hi.PROC_INST_ID_, hi.END_TIME_ 
                FROM ACT_HI_PROCINST hi 
                               WHERE hi.END_TIME_ IS NOT NULL  
                                     AND hi.END_TIME_ <= (TRUNC(SYSDATE) - IN_periodInDays )  
            ) WHERE ROWNUM <= IN_maxProcessInstances;
    END IF;
    
    /* 3. Check PI's im TEMP if any found, ready for ACHIVING */
    select count(*) INTO P_piProcessed FROM TMP_ARCHIVING_PROCINST;
    
    IF P_piProcessed = 0 THEN /* keine Kandidaten gefunden */
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: NO ProcessInstance-Candidates for Arching found!');
        dbms_output.put_line('Try TA-ROLLBACK ...');
        ROLLBACK;  /*-- TMP_ARCHIVING_PROCINST un-Delete */
        dbms_output.put_line('TA-ROLLBACK DONE! ...' ||chr(13)||chr(10));
        
        P_result := '[ARCHIVE_CAMUNDA_HISTORY]:  NO ProcessInstance candidates for archiving found! ' || chr(13)||chr(10)||
                to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') || '; STAT_EXECUTION_ID: '|| P_executionId || chr(13)||chr(10)||
                ' Used PARAMS: IN_maxProcessInstances: ' || IN_maxProcessInstances || '; IN_periodInDays: ' || IN_periodInDays;
        
        RETURN 'RESULT: ' || P_result;    
 
    /* 4. Move data from history to archive (insert to archive and delete in history) */ 
    ELSE
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: '|| P_piProcessed ||' ProcessInstance candidates for archiving found!' || chr(13)||chr(10));
    
        /* LOOP over tables */
        FOR i IN 1 .. P_archiveTablesArray.count LOOP 
        
            P_tableName := P_archiveTablesArray(i);
            
            /* INSERT */
            dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: #######   Copy from History into ARCHIVE_' || P_tableName ||'  ...');
            P_query := ' INSERT INTO ARCHIVE_' || P_tableName || ' ar ' || chr(13)||chr(10)|| 
                       '   SELECT hi.*, ' ||  P_executionId || ', CURRENT_TIMESTAMP FROM '|| P_tableName ||' hi ' || chr(13)||chr(10)||
                       '     WHERE hi.PROC_INST_ID_ in ( SELECT PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST)';
            dbms_output.put_line('QUERY (before execute): /copy to archive from history/ ' || P_query);
            EXECUTE IMMEDIATE P_query;
            dbms_output.put_line('.... rows inserted: ' || TO_CHAR(SQL%ROWCOUNT));
            
            /* DELETE */
            dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:         Delete in History ' || P_tableName ||'  ...');
            P_query := ' DELETE ' || P_tableName || ' WHERE PROC_INST_ID_ in (select PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST)';
            dbms_output.put_line('QUERY (before execute):  /delete in history/' || P_query);
            EXECUTE IMMEDIATE P_query;
            dbms_output.put_line('.... rows deleted: ' || TO_CHAR(SQL%ROWCOUNT) || chr(13)||chr(10));
        END LOOP;
        
        /* select bytearray_ids */
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: INSERT INTO TMP_ARCHIVING_BYTEARRAY ...');
        INSERT INTO TMP_ARCHIVING_BYTEARRAY
            SELECT BYTEARRAY_ID_, PROC_INST_ID_ FROM ARCHIVE_ACT_HI_VARINST archvar
            where archvar.PROC_INST_ID_ in (SELECT PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST)
            AND archvar.BYTEARRAY_ID_ is not null;
        
        INSERT INTO TMP_ARCHIVING_BYTEARRAY
            SELECT BYTEARRAY_ID_, PROC_INST_ID_ FROM ARCHIVE_ACT_HI_DETAIL archvar
            where archvar.PROC_INST_ID_ in (SELECT PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST)
            AND archvar.BYTEARRAY_ID_ is not null;  
        
        /* 5. Check Bytearrays im TEMP if any found, ready for ACHIVING */
        select count(*) INTO P_baProcessed FROM TMP_ARCHIVING_BYTEARRAY;
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: '|| P_baProcessed ||' ByteArray candidates for archiving found!' || chr(13)||chr(10));
        
        /* INSERT */
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: Copy from history to ARCHIVE_ACT_GE_BYTEARRAY ...');
        INSERT INTO ARCHIVE_ACT_GE_BYTEARRAY ar  
            SELECT hi.*, P_executionId, CURRENT_TIMESTAMP FROM ACT_GE_BYTEARRAY hi 
            WHERE hi.ID_ in ( SELECT BYTEARRAY_ID_ FROM TMP_ARCHIVING_BYTEARRAY);
        dbms_output.put_line('.... rows inserted: ' || TO_CHAR(SQL%ROWCOUNT));
        
        /* DELETE */
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]:         Delete in History ACT_GE_BYTEARRAY ...');
        DELETE ACT_GE_BYTEARRAY WHERE ID_ in (select BYTEARRAY_ID_ FROM TMP_ARCHIVING_BYTEARRAY);
        dbms_output.put_line('.... rows deleted: ' || TO_CHAR(SQL%ROWCOUNT) || chr(13)||chr(10)); 
        
        /* COMMIT TRANSACTION */
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: PIs processed: ' || P_piProcessed || '; STAT_EXECUTION_ID: '|| P_executionId);
        dbms_output.put_line('TRY TA-COMMIT ...');
        COMMIT;
        dbms_output.put_line('TA-COMMIT DONE!' ||chr(13)||chr(10));
        
        P_executionDuration := sysdate - P_startDate;
        
        P_result := '[ARCHIVE_CAMUNDA_HISTORY]:  EXECUTED (commited) successfully! ' || chr(13)||chr(10)||
            to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') || '; Duration: ' || to_char(round(P_executionDuration*24*60*60, 1)) || ' sec.' ||  chr(13)||chr(10) ||
            ' PIs processed: ' || P_piProcessed || '; STAT_EXECUTION_ID: '|| P_executionId  || chr(13)||chr(10)||
            ' PARAMS: IN_maxProcessInstances: ' || IN_maxProcessInstances || '; IN_periodInDays: ' || IN_periodInDays;
        
        dbms_output.put_line(P_result);
        
        RETURN 'RESULT: ' || P_result;
    END IF;
    
    
  EXCEPTION
    /* WHEN NO_DATA_FOUND THEN  */
    WHEN OTHERS THEN 
        dbms_output.put_line('[ARCHIVE_CAMUNDA_HISTORY]: executed with ERRORS!');
        dbms_output.put_line('ERROR! ' || SQLERRM || ' ' || DBMS_UTILITY.FORMAT_ERROR_STACK || ' ' || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);
        dbms_output.put_line('TRY TA-ROLLBACK ...');
        ROLLBACK;
        dbms_output.put_line('TA-ROLLBACK DONE!' ||chr(13)||chr(10));
        
        P_executionDuration := sysdate - P_startDate;
        
        P_result := '[ARCHIVE_CAMUNDA_HISTORY]:  EXECUTED with ERRORs! ' || chr(13)||chr(10)||
                    to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') || '; Duration: ' || to_char(round(P_executionDuration*24*60*60, 1)) || ' sec.' || 
                    '; STAT_EXECUTION_ID: '|| P_executionId || chr(13)||chr(10) ||
                    ' ERROR: ' || SQLERRM || ' ' || DBMS_UTILITY.FORMAT_ERROR_STACK || ' ' || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
        
        dbms_output.put_line(P_result);
        /* --INSERT INTO errors VALUES(SQLCODE, substr(SQLERRM, 64, 1), SYSTIMESTAMP); */
        
        RETURN 'RESULT: ' || P_result;
END ARCHIVE_CAMUNDA_HISTORY;
/

quit;
/