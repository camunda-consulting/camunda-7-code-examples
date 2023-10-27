# Camunda Webapp JBoss Standalone

The standalone web-application including Cockpit, Tasklist and Admin are
discontinued from official Camunda releases since Camunda 7.20.

This snippet provides the sources that you need to build the web-application as
a deployable .war file for JBoss by yourself.

It is already built for version 7.20 using the versions from the Camunda BOM and
Spring 5.3.27.

Spring is important to get the connection to the embedded process engine, which
is created with the
[applicationContext.xml](src/main/webapp/WEB-INF/applicationContext.xml).

This project simplifies the configuration of the embedded engine to use an
existing database. Just add it to your source control and change the
applicationContext.xml according to your requirements.

**Disclaimer:** As there is no integration test happening right now, the
programs comes without any warranties. Camunda consultants will try to help you
on the best effort.
