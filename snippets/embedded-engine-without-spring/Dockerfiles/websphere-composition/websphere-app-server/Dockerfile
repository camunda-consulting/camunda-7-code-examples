FROM ibmcom/websphere-traditional:profile

COPY postgresql-9.4.1212.jre6.jar /opt/postgresql/

RUN wsadmin.sh -lang jython -conntype NONE -c "AdminTask.createJDBCProvider(['-scope', 'Node=DefaultNode01,Server=server1', '-databaseType', 'User-defined', '-providerType', 'User-defined JDBC Provider', '-implementationType', 'User-defined', '-name', 'Postgres JDBC Provider', '-description', 'Custom JDBC2.0-compliant Provider configuration', '-classpath', '[/opt/postgresql/postgresql-9.4.1212.jre6.jar ]', '-nativePath', '', '-implementationClassName', 'org.postgresql.xa.PGXADataSource' ])"

RUN wsadmin.sh -lang jython -conntype NONE -c "AdminTask.createAuthDataEntry(['-alias', 'Camunda DB', '-user', 'camunda', '-password', 'camunda', '-description', 'Camunda database user' ])" 

RUN wsadmin.sh -lang jython -conntype NONE -c "AdminTask.createDatasource('Postgres JDBC Provider(cells/DefaultCell01/nodes/DefaultNode01/servers/server1|resources.xml#JDBCProvider_1499805626606)', ['-name', 'camunda Datasource', '-jndiName', 'jdbc/ProcessEngine', '-dataStoreHelperClassName', 'com.ibm.websphere.rsadapter.GenericDataStoreHelper', '-containerManagedPersistence', 'true', '-componentManagedAuthenticationAlias', 'DefaultNode01/Camunda DB'])" 

RUN wsadmin.sh -lang jython -conntype NONE -c "AdminConfig.create('J2EEResourceProperty', '(cells/DefaultCell01/nodes/DefaultNode01/servers/server1|resources.xml#J2EEResourcePropertySet_1499807310687)', [['name', 'url'], ['type', 'java.lang.String'], ['description', ''], ['value', 'jdbc:postgresql://bpmdb:5432/camunda'], ['required', 'false']])" 

# JDBC Provider:
# Name: Postgresql Jdbc Provider
# ClassPath: /opt/postgresql/postgresql-9.4.1212.jre6.jar
# ImplementationClassPath: org.postgresql.xa.PGXADataSource
#  
# Datasource:
# JNDI Name: jdbc/ProcessEngine
# Component managed authentication alias
# username: camunda
# password camunda
# Custom Properties:
# serverName: bpmdb
# databaseName: camunda
# portNumber: (Integer) 5432
# url: jdbc:postgresql://bpmdb:5432/camunda