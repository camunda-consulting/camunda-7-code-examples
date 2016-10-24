Customize the Camunda Webapp (Tasklist/Cockpit)
=========================

This project shows how to easily customize the Camunda Webapp by using the Maven Overlay mechanism.

It customizes:

* The color schema (see [Logo and Header Color](https://docs.camunda.org/manual/7.5/webapps/tasklist/configuration/#logo-and-header-color))
* The logo (see [Logo and Header Color](https://docs.camunda.org/manual/7.5/webapps/tasklist/configuration/#logo-and-header-color))
* Add German language taken from [Community Extension: Tasklist Translations](https://github.com/camunda/camunda-tasklist-translations/) (see [Localization](https://docs.camunda.org/manual/7.5/webapps/tasklist/configuration/#localization))
* Removes some built in plugins (see [Plugin exclusion](http://docs.camunda.org/latest/guides/user-guide/#cockpit-plugins-plugin-exclusion-client-side)): start-process-action, tasklist.navbar.action:create-task-action, task-detail-form, task-detail-history, task-search
* Adds own Plugins (see [Tasklist Plugins](http://docs.camunda.org/latest/guides/user-guide/#tasklist-plugins) and [Cockpit Plugins](http://docs.camunda.org/latest/guides/user-guide/#cockpit-plugins))

*Important Note:* You have to specify the webapp corresponding to your container (Tomcat, JBoss, WildFly, ...) in the pom.xml. This example builts the webapp for JBoss.

Example Screenshot
----------------------------

![Screenshot](screenshot.png)

How to use it?
--------------

Just run

 mvn install


and copy the resulting camunda-webapp-customized.war to your deploy folder of the container. Make sure you delete the original webapp beforehand.
Before you need to download the EE version here: https://docs.camunda.org/enterprise/download/

Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.6.0-alpha5 using WildFly 10.


Improvements Backlog
--------------------

* Improve demo color schema

License
-------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
