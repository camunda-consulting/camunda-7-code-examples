# Composed Identity Provider Example

This example shows how a composed identity provider can be implemented.

It uses:

* a list of LDAP configurations
* a flag for database identity provider

## Goal

Using this composed identity provider, you will be able to define multiple LDAP connections at the same time or bypass a ldap connection with a database identity provider.

## Configuration

### Spring Boot

To use the plugin with Spring Boot, the easiest way would be to add it to the context as `@Bean` programmatically. From there, it will be registered to the process engine.

### Camunda Run

Here, the same mechanism as with Spring Boot would apply as the plugin needs to be configured.

### Tomcat

To use the plugin with a Tomcat, you will need to register it to the `bpm-platform.xml` using:

```xml
<plugin>
  <class>com.camunda.consulting.ComposedIdentityProviderPlugin</class>
  <properties>
    <property name="useDatabase">true</property>
    <property name="ldapConfigurationFiles">my-first-ldap-config.properties, my-second-ldap-config.properties</property>
  </properties>
</plugin>
```

Then, you will have to add `my-first-ldap-config.properties` and `my-second-ldap-config.properties` to the `conf` folder of the Tomcat.

They both should include all relevant attributes:
```properties
initialContextFactory =com.sun.jndi.ldap.LdapCtxFactory
securityAuthentication =simple

serverUrl=
managerDn=
managerPassword=

baseDn=

userDnPattern=

userSearchBase=
userSearchFilter=(objectclass=person)

groupSearchBase=
groupSearchFilter=(objectclass=groupOfNames)

userIdAttribute=uid
userFirstnameAttribute=cn
userLastnameAttribute=sn
userEmailAttribute=email
userPasswordAttribute=userpassword

groupIdAttribute=ou
groupNameAttribute=cn
groupTypeAttribute=
groupMemberAttribute=memberOf

sortControlSupported=false
useSsl=false
usePosixGroups=false
allowAnonymousLogin=false

authorizationCheckEnabled=true
# if not set, it is disabled
pageSize=
```
>Note: The above mentioned values are default values and can be omitted.

The plugin will then use all configurations (ldap, database) to search users and groups. Also, it will use the database to modify users there.

## Components

### Plugin

The process engine plugin that registers the session factory to the engine.

Here, all configuration from outside is taken and processed before the factory receives session factories.

### Session Factory

The session factory that provides the composed identity provider itself. It holds a list of session factories that are then filtered and the opened sessions are injected to the session.

### Identity Provider

The session for one request. It can handle any amount of `ReadOnlyIdentityProvider` sessions and one session of `WritableIdentityProvider`.

This makes sure that no more than one writable resource is written to at the same time.

