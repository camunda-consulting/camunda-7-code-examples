Howto archive the history
=========================

This is a collection of examples how to archive and purge the history. 

Oracle database
---------------

If you are running an Oracle database, you might use [these](oracle-scripts) PL/SQL-scripts to clean up the history. There are four scripts:

1. create indicies to get fast access to the tables. (STEP-1)
2. create archive tables to save the data (STEP-2)
3. copy all data of completed process instances from the history to the archive and delete the data from the history (STEP-3)
4. Restore data from some certain archive runs back to the history to show them in cockpit again (STEP-4)

### How to archive process instances

Open a sql console and run

    select ARCHIVE_CAMUNDA_HISTORY(9, 20) from DUAL; -- Archive PI's older then 9 days and max. 20 instances

or run

    select ARCHIVE_CAMUNDA_HISTORY(180, 0) from DUAL;

to move all process instances completed more than 180 days in the past into the archive tables.

### How to restore the data from the archive

Open a sql console and run

    select ROLLB_ARCHIVE_CAMUNDA_HISTORY(7, -1, 10) from DUAL;

to restore the process instance only from archive run 7 `(7, -1)` and maximum 10 process instances.

If you run

    select ROLLB_ARCHIVE_CAMUNDA_HISTORY(8, 10, 0) from DUAL;

you will restore all process instances from archive run 8 to 10.

### Improvements Backlog

- Handle INDENITYLINK
- Handle Cases
- Find Batches even if no process instances were selected

