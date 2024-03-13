# Camunda Webapp Tomcat Standalone

<b>For this project, camunda-webapp-jboss-standalone has been used as a template</b>

The standalone web-application including Cockpit, Tasklist and Admin are
discontinued from official Camunda releases since Camunda 7.20.

This snippet provides the sources that you need to build the web-application as
a deployable .war file for Tomcat by yourself.

It is already built for version 7.20.0-ee using the versions from the Camunda
BOM and Spring 5.3.27.

Spring is important to get the connection to the embedded process engine, which
is created with the
[applicationContext.xml](src/main/webapp/WEB-INF/applicationContext.xml).

This project simplifies the configuration of the embedded engine to use an
existing database. Just add it to your source control and change the
applicationContext.xml according to your requirements.

If you cannot access the enterprise artifactory, just change the Camunda version
to a community version and remove the repository configuration from the pom.xml.

## Changes to the last version maintained by Camunda

The pom.xml comes without the parent pom. All versions of dependencies are
included in the pom. And they are extracted to properties, to get all versions
of dependencies and plugins close together.

**Disclaimer:** As there is no integration test happening right now, the
application comes without any warranties. Camunda consultants will try to help
you on the best effort.
