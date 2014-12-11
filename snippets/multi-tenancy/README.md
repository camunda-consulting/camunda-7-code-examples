# Introduction

# Environment Restrictions

JBoss Application Server only

# Remarks to run this application

This example demonstrates how to use multiple process engines for multi tenancy. Each process
engine represents a tenant and connects to its own schema inside the database.
The example uses two tenants (`tenant-1` and `tenant-2`).

## Configuration

Before you can run this example, you need to perform the following configuration steps:

### Setup the database

Open the jboss standalone.xml configuration file. Setup two additional datasources:

```xml
<datasource jta="true" jndi-name="java:jboss/datasources/ProcessEngine-tenant1" pool-name="ProcessEngine-tenant1" enabled="true" use-java-context="true" use-ccm="true">
  <connection-url>jdbc:h2:./camunda-h2-dbs/process-engine-tenant1;DB_CLOSE_DELAY=-1;MVCC=TRUE;DB_CLOSE_ON_EXIT=FALSE</connection-url>
  <driver>h2</driver>
  <security>
    <user-name>sa</user-name>
    <password>sa</password>
  </security>
</datasource>
<datasource jta="true" jndi-name="java:jboss/datasources/ProcessEngine-tenant2" pool-name="ProcessEngine-tenant2" enabled="true" use-java-context="true" use-ccm="true">
  <connection-url>jdbc:h2:./camunda-h2-dbs/process-engine-tenant2;DB_CLOSE_DELAY=-1;MVCC=TRUE;DB_CLOSE_ON_EXIT=FALSE</connection-url>
  <driver>h2</driver>
  <security>
    <user-name>sa</user-name>
    <password>sa</password>
  </security>
</datasource>
```

> **Note**: on a production system it is usually better to setup a single datasource for all the
> process engines and use a different database schema for each tenant. This way you can share a
> database connection pool for all process engines which allows you limit the number of database
> connections over all process engines.

### Setup the process engines:

Next we setup two additonal process engines, one for each tenant. Note that each process engine uses
a different datasource:

```xml
<process-engine name="tenant1" default="false">
  <datasource>java:jboss/datasources/ProcessEngine-tenant1</datasource>
  <history-level>full </history-level>
  <configuration>org.camunda.bpm.container.impl.jboss.config.ManagedJtaProcessEngineConfiguration</configuration>
  <properties>
    <property name="jobExecutorAcquisitionName">default</property>
    <property name="isAutoSchemaUpdate">true</property>
    <property name="authorizationEnabled">true</property>
    <property name="jobExecutorDeploymentAware">true</property>
  </properties>
</process-engine>
<process-engine name="tenant2" default="false">
  <datasource>java:jboss/datasources/ProcessEngine-tenant2</datasource>
  <history-level>full</history-level>
  <configuration>org.camunda.bpm.container.impl.jboss.config.ManagedJtaProcessEngineConfiguration</configuration>
  <properties>
    <property name="jobExecutorAcquisitionName">default</property>
    <property name="isAutoSchemaUpdate">true</property>
    <property name="authorizationEnabled">true</property>
    <property name="jobExecutorDeploymentAware">true</property>
  </properties>
</process-engine>
```
# Running the example

Execute the arquillian based testcase using maven:

```shell
mvn clean test -Parq-jbossas-remote
```

# Known Issues

# Improvements Backlog
