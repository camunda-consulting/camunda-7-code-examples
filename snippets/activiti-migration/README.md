# Database migration scripts for Activiti 5.21

With the Activiti Core Developers having left Alfresco (the company behind Activiti) the future of the Activiti project got quite questionable. That's why we provide an easy database upgrade script to update your Activiti 5.21 to Camunda BPM 7.5 (both are the current version at the time the Activiti core team left Alfresco).

Basically you have to apply all provided [Camunda upgrade scripts](https://github.com/camunda/camunda-bpm-platform/blob/master/distro/sql-script/upgrade/) from version 6.2 (the last camunda version before the fork) to 7.5. But you have to take care of some conflicts with changes in Activiti (you can see the changes in the beginning of [this commit](https://github.com/camunda/camunda-consulting/commit/f31ecc94bc825c5702376887b10da4d6b0cf6381) if you are interessted in the details).

To make it easy for you we prepared special upgrade files you can apply without  modifications. Make sure you run them in this order (e.g. by command line using ```psql -U postgres -d activiti < postgres-1-upgrade.sql`):

* [postgres-1-upgrade.sql](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/scripts/postgres-1-upgrade.sql): Upgrade the database to a 7.5. No need to review this file.
* [postgres-2-migrate.sql](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/scripts/postgres-2-migrate.sql): Migrates some tables/columns to be compliant with Camunda database. You might want to have a look to make sure you are fine with the changes.
* [postgres-3-drop.sql](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/scripts/postgres-3-drop.sql): Drops unused tables and columns from the database. You should double-check that you do not loose data you rely on.

Nothe that there are corresponding files for your database [in this github folder](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/scripts/).

After this procedure your database is a Camunda BPM 7.5 database. Congrats! You can now run Camunda and your runtime data is still there.

*Warning:* This migration script is provided without any warranty. We haven't tested it for all circumstances and you might loose data, especially if you used features not supported by Camunda. Make a backup before applying the migration and verify everything is working afterwards! And let us know if you experience errors.

In case of any trouble contact us! Our consulting team can help you!

# Migration for Activiti > 5.11 and < 5.21

If you have an Activiti version > 5.11 && < 5.21 then migrate to 5.21 by the Activiti upgrade mechansim. Afterwards follow the instructions above.

# Migration for Activiti <= 5.11
If you have Activiti < 5.11, upgrade to 5.11 first (by upgrade scripts provided by Activiti) and then move to Camunda. You have to run a 6.2 (the Camunda version based on Activiti) to 7.0 (the first forked Camunda version) database upgrade script provided. See [postgres_engine_6.2_to_7.0.sql](https://github.com/camunda/camunda-bpm-platform/blob/master/distro/sql-script/upgrade/postgres_engine_6.2_to_7.0.sql) for Postgres, scripts for other database are provided in the same folder.

# Analyze and fix problematic BPMN files

When you completed all the before mentioned upgrades, you might end up with some broken BPMN models in your database. This can happen due to fact that some versions of the activiti designer do not produce 100% standard-compliant BPMN XML. Another reason could be that you used activiti features that were introduced after the Camunda fork.

[This folder](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/activiti-bpmn-xml-checks/) contains some scripts that can help you find possible problems in your models.

[This folder](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/activiti-bpmn-xml-fixes/) contains scripts that help you fix these issues.

*Warning:* Again, we do not guarantee that the scripts will run on your system without any errors or that they meet the requirements of your exact scenario. We rather want to provide you with an idea on how to analyze and fix broken models.

If you find broken models, you probably want to fix all deployed versions of those models. Otherwise, you might run into situations where you can't analyze historic process instances in Cockpit because the BPMN model could not be parsed by the engine. The  [bpmn-db-utils](https://github.com/camunda/camunda-consulting/blob/master/snippets/activiti-migration/bpmn-db-utils/) can help you extract all models from your database, run a test to verify that they can be parsed by the engine and reimport the models back into the database after applying the fixes.
